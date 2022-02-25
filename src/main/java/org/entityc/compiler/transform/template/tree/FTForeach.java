/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.TemplateInstruction;
import org.entityc.compiler.doc.annotation.TemplateInstructionArgument;
import org.entityc.compiler.doc.annotation.TemplateInstructionCategory;
import org.entityc.compiler.doc.annotation.TemplateInstructionUsage;
import org.entityc.compiler.model.MTModule;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.domain.MTDEAttribute;
import org.entityc.compiler.model.domain.MTDEAttributeBitField;
import org.entityc.compiler.model.domain.MTDEInterface;
import org.entityc.compiler.model.domain.MTDEInterfaceOperation;
import org.entityc.compiler.model.domain.MTDERelationship;
import org.entityc.compiler.model.domain.MTDomain;
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.model.entity.MTBitField;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTEnumItem;
import org.entityc.compiler.model.entity.MTRelationship;
import org.entityc.compiler.model.entity.MTTypedef;
import org.entityc.compiler.model.entity.MTView;
import org.entityc.compiler.model.foundation.MFArray;
import org.entityc.compiler.model.foundation.MFSet;
import org.entityc.compiler.model.interop.MTInterface;
import org.entityc.compiler.model.interop.MTInterfaceOperation;
import org.entityc.compiler.model.interop.MTRequestEndpointParam;
import org.entityc.compiler.model.interop.MTResponseStatus;
import org.entityc.compiler.transform.template.TemplateLexer;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.Collection;

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.InstructionArgument;
import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.InstructionKeyword;

@TemplateInstruction(category = TemplateInstructionCategory.CONTROL_FLOW, name = "foreach",
    usage = "",
    summary = "With this instruction you can iterate through collections of objects.",
    contents = "The block of template code this instruction surrounds is executed for each iteration of the `foreach` "
               + "loop based on the resulting collection after optional conditional filtering. The `#first`, `#last`, "
               + "and `#count` loop variable suffixes apply to the post-filtered collection. This is the advantage "
               + "of using the conditional usage of this instruction over simply having an `if` inside the loop.",
    description = "There are a couple of variations of this instruction, each described by its own usage below.")
@TemplateInstructionUsage(
    title = "Simple Collection Iteration",
    usage = "`foreach `*loopVariable*` in `*collectionExpression*",
    description = "This is the most common usage where the `foreach` instruction iterates over a provided collection, "
                  + "and for each iteration, assigns the item in the collection to the provided loop variable.")
@TemplateInstructionUsage(
    title = "Conditional Collection Iteration",
    usage = "`foreach `*loopVariable*` in `*collectionExpression*` if `*conditionalExpression*",
    description = "This usage iterates over the provided collection but also allows you to conditionally include "
                  + "(or exclude) items based on a provided expression.")
// Not implemented yet!
//@TemplateInstructionUsage(
//    title = "Numeric Sequence",
//    usage = "`foreach `*loopVariable*` in `*fromExpression*` ... `*toExpression* [`by `*byExpression*]",
//    description = "This usage is used for simple counting of numeric values starting at a *fromExpression* value "
//                  + "and going to a *toExpression* value stepping by the *byExpression* value. For example, "
//                  + "`$[foreach i in 2 ... 8 by 2]` would create a sequence of `i` as 2, 4, 6, 8")
public class FTForeach extends FTContainerNode {

    private final FTExpression collectionExpression;
    private final FTExpression conditionalFilterExpression;
    private       String       loopVariableName;

    public FTForeach(ParserRuleContext ctx, FTContainerNode parent,
                     @TemplateInstructionArgument(
                         description = "The loop variable. Each iteration of the collection represented by the "
                                       + "collection expression will be assigned to a variable by this name.")
                         String loopVariable,
                     @TemplateInstructionArgument(
                         description = "This is the expression that represents a collection through which to iterate.")
                         FTExpression collectionExpression) {
        super(ctx, parent);
        this.loopVariableName            = loopVariable;
        this.collectionExpression        = collectionExpression;
        this.conditionalFilterExpression = null;
    }

    public FTForeach(ParserRuleContext ctx, FTContainerNode parent, String loopVariableName, FTExpression collectionExpression,
                     @TemplateInstructionArgument(
                         description = "This expression allows you to conditionally include item by item in the "
                                       + "collection. For each item in the collection, it will evaluate this expression. "
                                       + "If it is `true` then that item is included in the collection, otherwise it "
                                       + "is excluded. For this to work, this expression typically includes the loop "
                                       + "variable but can in fact be any expression.")
                         FTExpression conditionalExpression) {
        super(ctx, parent);
        this.loopVariableName            = loopVariableName;
        this.collectionExpression        = collectionExpression;
        this.conditionalFilterExpression = conditionalExpression;
    }

    public String getLoopVariableName() {
        return loopVariableName;
    }

    @Override
    public void transform(FTTransformSession session) {
        // convert expression to a type of list
        Object forValue = collectionExpression.getValue(session);
        if (forValue == null) {
            ECLog.logFatal(this, "Unable to evaluate 'foreach' expression: " + collectionExpression);
        }
        if (forValue instanceof MFArray) {
            forValue = ((MFArray) forValue).getValues();
        }
        if (forValue instanceof MFSet) {
            forValue = ((MFSet) forValue).getValues();
        }
        if (!(forValue instanceof Collection)) {
            ECLog.logFatal(this,
                           "Foreach expecting a collection to iterate through, instead it was given an object of class: "
                           + forValue.getClass().getSimpleName());
        }
        Collection list = (Collection) forValue;

        if (this.conditionalFilterExpression != null) {
            // make a new list by filtering items through this expression
            ArrayList filteredList = new ArrayList();
            FTLet     let          = new FTLet(null, "__", this.conditionalFilterExpression);
            for (Object item : list) {
                session.setLoopValue(loopVariableName, item, -1, list);
                let.transform(session);
                Object result = session.getValue("__");
                if (result instanceof Boolean) {
                    if (((Boolean) result).booleanValue()) {
                        filteredList.add(item);
                    }
                }
                else {
                    ECLog.logFatal(this.conditionalFilterExpression,
                                   "The foreach where condition must return a boolean result.");
                }
            }
            list = filteredList;
        }

        if (list.size() == 0) {
            return; // nothing to do
        }

        Object firstItem = list.iterator().next();
        Class  listClass = firstItem.getClass();

        if (loopVariableName == null) {
            loopVariableName = getLoopVariableName(listClass);
            if (loopVariableName == null) {
                ECLog.logFatal(this, "Unable to determine default for loop variable name for class: "
                                     + listClass.getSimpleName());
            }
        }
        int i = 0;
        for (Object item : list) {
            if (item instanceof MTNode) {
                if (((MTNode) item).isIncluded()) {
                    continue;
                }
            }

            session.setLoopValue(loopVariableName, item, i, list);

            // run the inside of the loop
            super.transform(session);

            // if there was a continue in there then continue the loop
            if (session.isPendingContinue()) {
                session.setPendingContinue(false);
                continue;
            }

            // if there was a break, stop the loop
            if (session.isPendingBreak()) {
                session.setPendingBreak(false);
                break;
            }

            if (session.isPendingReturn()) {
                break;
            }
            i++;
        }
    }

    private String getLoopVariableName(Class listClass) {
        if (listClass.equals(MTEntity.class)) {
            return "entity";
        }
        else if (listClass.equals(MTAttribute.class)) {
            return "attribute";
        }
        else if (listClass.equals(MTRelationship.class)) {
            return "relationship";
        }
        else if (listClass.equals(MTBitField.class)) {
            return "bitfield";
        }
        else if (listClass.equals(MTView.class)) {
            return "view";
        }
        else if (listClass.equals(MTDomain.class)) {
            return "domain";
        }
        else if (listClass.equals(MTDEAttribute.class)) {
            return "domainAttribute";
        }
        else if (listClass.equals(MTDEAttributeBitField.class)) {
            return "domainAttributeBitfield";
        }
        else if (listClass.equals(MTDERelationship.class)) {
            return "domainEntityRelationship";
        }
        else if (listClass.equals(MTDEInterface.class)) {
            return "domainEntityInterface";
        }
        else if (listClass.equals(MTDEInterfaceOperation.class)) {
            return "domainEntityInterfaceOperation";
        }
        else if (listClass.equals(MTEnum.class)) {
            return "enum";
        }
        else if (listClass.equals(MTEnumItem.class)) {
            return "item";
        }
        else if (listClass.equals(MTTypedef.class)) {
            return "typedef";
        }
        else if (listClass.equals(MTInterface.class)) {
            return "interface";
        }
        else if (listClass.equals(MTInterfaceOperation.class)) {
            return "operation";
        }
        else if (listClass.equals(MTRequestEndpointParam.class)) {
            return "param";
        }
        else if (listClass.equals(MTResponseStatus.class)) {
            return "status";
        }
        else if (listClass.equals(MTModule.class)) {
            return "module";
        }

        return null;
    }

    @Override
    public void accept(FTVisitor visitor) {
        super.accept(visitor);
        collectionExpression.accept(visitor);
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;

        formatController.addInstructionStart(indentLevel, this);
        if (loopVariableName != null && !loopVariableName.isEmpty()) {
            formatController.addInstructionInside(InstructionArgument, loopVariableName, this.getStartLineNumber());
            formatController.addInstructionInside(InstructionKeyword, GetInstructionNameOfLexerSymbol(TemplateLexer.In),
                                                  this.getStartLineNumber());
        }
        this.collectionExpression.format(formatController, indentLevel);
        if (this.conditionalFilterExpression != null) {
            formatController.addInstructionInside(InstructionKeyword, GetInstructionNameOfLexerSymbol(TemplateLexer.If),
                                                  this.getStartLineNumber());
            this.conditionalFilterExpression.format(formatController, indentLevel);
        }

        formatController.addInstructionEnd(this);
        super.formatChildren(formatController, indentLevel);
        formatController.addInstructionBlockEnd(indentLevel, this);
        return success;
    }

    @Override
    public int getTemplateLexerSymbol() {
        return TemplateLexer.Foreach;
    }
}

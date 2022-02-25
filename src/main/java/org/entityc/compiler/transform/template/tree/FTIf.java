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
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.transform.template.TemplateGrammer;
import org.entityc.compiler.transform.template.TemplateLexer;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;

@TemplateInstruction(category = TemplateInstructionCategory.CONTROL_FLOW,
    name = "if",
    usage = "`if `*expression*",
    summary = "Allows you to conditionally execute template code.",
    description = "This instruction evaluates the provided instruction and if it resolves to `true` it will "
                  + "execute its template code. If it is `false` and is followed by an `elseif`, execution will "
                  + "move to that instruction. If `false` and is instead followed by an `else` statement, the "
                  + "code contained in the `else` instruction is executed.",
    contents = "This instruction represents the top of a possible `if`...`elseif`...`else` structure, where the "
               + "`elseif` and `else` are optional. Between this instruction and either the next `elseif`, `else` "
               + "or this instructions terminator is template code.",
    seeAlso = {"elseif", "else"})
public class FTIf extends FTContainerNode {

    private final FTExpression condition;
    private       boolean      elseif = false;

    public FTIf(ParserRuleContext ctx, FTContainerNode parent,
                @TemplateInstructionArgument(
                    description = "The expression that is evaluated to determine whether to execute the template code "
                                  + "of this instruction."
                )
                    FTExpression expression) {
        super(ctx, parent);
        if (ctx instanceof TemplateGrammer.ElseifTagContext) {
            elseif = true;
        }
        this.condition = expression;
    }

    public FTExpression getCondition() {
        return condition;
    }

    @Override
    public void transform(FTTransformSession session) {
        boolean conditionMet = false;
        Object  value        = condition.getValue(session);
        if (value != null) {
            if (value instanceof Boolean) {
                if (((Boolean) value).booleanValue()) {
                    conditionMet = true;
                }
            }
            else if (value instanceof String) {
                if ((((String) value).length() > 0)) {
                    conditionMet = true;
                }
            }
            else if ((value instanceof MTEnum)) {
                conditionMet = true;
            }
            else if (value instanceof Integer) {
                conditionMet = (((Integer) value) != 0);
            }
            else if (value instanceof Long) {
                conditionMet = (((Long) value) != 0);
            }
        }
        else {
            conditionMet = false;
        }
        if (conditionMet) {
            super.transformChildren(session, true);
        }
        else if (getChildren().size() > 0) {
            // look for else and pickup there
            FTNode lastNode = getChildren().get(getChildren().size() - 1);
            if (lastNode instanceof FTElseIf || lastNode instanceof FTElse) {
                lastNode.transform(session);
            }
        }
    }

    @Override
    public void accept(FTVisitor visitor) {
        super.accept(visitor);
        condition.accept(visitor);
    }

    @Override
    public int getTemplateLexerSymbol() {
        return elseif ?
               TemplateLexer.Elseif :
               TemplateLexer.If;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;

        formatController.addInstructionStart(indentLevel, this);
        if (!condition.format(formatController, indentLevel)) {
            success = false;
        }
        formatController.addInstructionEnd(this);
        if (!super.formatChildren(formatController, indentLevel)) {
            success = false;
        }
        formatController.addInstructionBlockEnd(indentLevel, this);
        return success;
    }
}

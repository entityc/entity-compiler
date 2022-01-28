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
import org.entityc.compiler.model.entity.MTType;
import org.entityc.compiler.model.expression.MTOperation;
import org.entityc.compiler.transform.template.TemplateLexer;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.exit;

@TemplateInstruction(category = TemplateInstructionCategory.CONTROL_FLOW,
    name = "switch",
    usage = "`switch `*expression*",
    summary = "The start of a switch/case structure for conditionally executing template code.",
    description = "This `switch` instruction works much like it does in most languages. The provided expression is "
                  + "evaluated and based on its value it will try to find a `case` statement contained in it that "
                  + "matches in value. When it finds a case statement it changes the control flow of the template "
                  + "to the code located there (branches). If no `case` is found that matches it will branch to a "
                  + "`default` instruction if that is specified. Otherwise it exits to just below the `switch` "
                  + "statement. One aspect that is different from C or Java is that it does not fall through a case "
                  + "statement, once the code for a `case` statement is executed, it exits the `switch`. This "
                  + "eliminates the need for `break` statements, although you can still use them if needed.",
    contents = "Inside this `switch` block are zero or more `case` instructions and up to one `default` instruction. "
               + "See their descriptions for information about them specifically.",
    seeAlso = {"case", "default"})
public class FTSwitch extends FTContainerNode {

    private final FTExpression        condition;
    private final Map<String, FTCase> caseMap     = new HashMap();
    private       FTCase              defaultCase = null;

    public FTSwitch(ParserRuleContext ctx, FTContainerNode parent,
                    @TemplateInstructionArgument(
                        description = "The instruction evaluates this expression and branches to the matching case."
                    )
                        FTExpression expression) {
        super(ctx, parent);
        this.condition = expression;
    }

    public boolean hasCase() {
        return caseMap.size() > 0 || defaultCase != null;
    }

    public void addCase(FTCase ftCase) {
        if (ftCase.isDefaultCase()) {
            defaultCase = ftCase;
            return;
        }
        for (String key : ftCase.getIdentifiers()) {
            caseMap.put(key, ftCase);
        }
    }

    @Override
    public void transform(FTTransformSession session) {
        Object value = condition.getValue(session);
        if (value == null) {
            ECLog.logError(this, "Unable to get value for: " + condition.getText() + " in switch expression.");
            exit(1);
        }
        FTCase ftCase = null;
        if (value instanceof MTType) {
            ftCase = caseMap.get(((MTType) value).getTypeAsCaseValue());
        }
        else if (value instanceof MTOperation.Operator) {
            ftCase = caseMap.get(((MTOperation.Operator) value).name());
        }
        else {
            ftCase = caseMap.get("" + value);
        }
        if (ftCase == null) {
            ftCase = defaultCase;
        }
        if (ftCase == null) {
            ECLog.logError(this, "Unable find case for value \"" + value + "\" of " + condition.getText()
                                 + " in switch expression.");
            exit(1);
        }
        ftCase.transform(session);
    }

    @Override
    public void accept(FTVisitor visitor) {
        super.accept(visitor);
        condition.accept(visitor);
    }

    @Override
    public List<FTNode> getChildren() {
        ArrayList<FTNode> allChildren = new ArrayList<>();
        allChildren.addAll(super.getChildren());
        allChildren.addAll(caseMap.values());
        allChildren.add(defaultCase);
        return allChildren;
    }

    @Override
    public int getTemplateLexerSymbol() {
        return TemplateLexer.Switch;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;

        formatController.addInstructionStart(indentLevel, this);
        if (!condition.format(formatController, indentLevel)) {
            success = false;
        }
        formatController.addInstructionEnd(this);
        super.formatChildren(formatController, indentLevel);
        formatController.addInstructionBlockEnd(indentLevel, this);
        return success;
    }
}

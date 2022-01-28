/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

import org.entityc.compiler.doc.annotation.TemplateInstruction;
import org.entityc.compiler.doc.annotation.TemplateInstructionArgument;
import org.entityc.compiler.doc.annotation.TemplateInstructionCategory;
import org.entityc.compiler.transform.template.TemplateLexer;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.antlr.v4.runtime.ParserRuleContext;

@TemplateInstruction(category = TemplateInstructionCategory.CONTROL_FLOW,
    name = "elseif",
    fullUsage = "`$[elseif `*expression*`]`... ***template-block*** ...`$[/if]`",
    summary = "Executes template code conditionally when prior `if` or `elseif` instructions fail.",
    description = "This instruction must be preceded by an `if` instruction. If the expression of that prior `if` "
                  + "instruction evaluated to `false`, then this instruction will evaluate its expression. "
                  + "If it resolves to `true` it will execute its template code. If it is false and is followed by "
                  + "another `elseif`, execution will move to that other `elseif` instruction. If false and is "
                  + "instead followed by an `else` statement, the code contained in the `else` instruction is executed.",
    contents = "Between this instruction and either the next `elseif`, `else` "
               + "or the `if` instruction terminator is template code that will be execution if the condition is met "
               + "for this instruction.",
    seeAlso = {"if", "else"})
public class FTElseIf extends FTIf {

    public FTElseIf(ParserRuleContext ctx, FTContainerNode parent,
                    @TemplateInstructionArgument(
                        description = "The expression that is evaluated to determine whether to execute the template  "
                                      + "code of this instruction."
                    )
                    FTExpression expression) {
        super(ctx, parent, expression);
    }

    @Override
    public int getTemplateLexerSymbol() {
        return TemplateLexer.Elseif;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;
        indentLevel--;
        formatController.addInstructionStart(indentLevel, this);
        if (!super.getCondition().format(formatController, indentLevel)) {
            success = false;
        }
        formatController.addInstructionEnd(this);
        if (!super.formatChildren(formatController, indentLevel)) {
            success = false;
        }
        return success;
    }
}

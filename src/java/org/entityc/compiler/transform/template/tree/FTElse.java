/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.TemplateInstruction;
import org.entityc.compiler.doc.annotation.TemplateInstructionCategory;
import org.entityc.compiler.transform.template.TemplateLexer;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;

@TemplateInstruction(category = TemplateInstructionCategory.CONTROL_FLOW,
    name = "else",
    fullUsage = "`$[else]`...***template-block***...`$[/if]`",
    summary = "Executes template code when all prior `if` and `elseif` instructions fail.",
    description = "This instruction is the last one in the `if`...`elseif`...`else` structure. If all of the "
                  + "conditional expressions in the structure above it evaluate to `false`, then its template "
                  + "code executes.",
    contents = "Template code that executes if all other conditions in the structure fail.",
    seeAlso = {"if", "elseif"})
public class FTElse extends FTContainerNode {

    public FTElse(ParserRuleContext ctx, FTContainerNode parent) {
        super(ctx, parent);
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        indentLevel--;
        boolean success      = true;
        int     symbolNumber = getTemplateLexerSymbol();
        if (symbolNumber != NO_SYMBOL) {
            formatController.addSimpleInstruction(indentLevel, this);
        }
        if (!formatChildren(formatController, indentLevel)) {
            success = false;
        }
        return success;
    }

    @Override
    public int getTemplateLexerSymbol() {
        return TemplateLexer.Else;
    }
}

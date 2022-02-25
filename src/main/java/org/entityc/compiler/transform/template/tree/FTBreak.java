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
    name = "break",
    usage = "`break`",
    summary = "Allows you to break out of a foreach loop or a switch/case block.",
    description = "When inside a `foreach` or a `case`/`default` instruction block, this instruction will allow you "
                  + "to branch out of that block to the code just below that block.")
public class FTBreak extends FTNode {

    public FTBreak(ParserRuleContext ctx) {
        super(ctx);
    }

    @Override
    public void transform(FTTransformSession session) {
        session.setPendingBreak(true);
    }

    @Override
    public void accept(FTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        super.format(formatController, indentLevel);
        return true;
    }

    @Override
    public int getTemplateLexerSymbol() {
        return GetTemplateLexerSymbol();
    }

    public static int GetTemplateLexerSymbol() {
        return TemplateLexer.Break;
    }
}

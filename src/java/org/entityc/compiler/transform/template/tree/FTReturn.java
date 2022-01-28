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

@TemplateInstruction(category = TemplateInstructionCategory.FUNCTION,
    name = "return",
    usage = "`return`",
    summary = "Returns from a function.",
    description = "This instruction simply returns from the current function it is within.",
    seeAlso = {"function"})
public class FTReturn extends FTNode {

    public FTReturn(ParserRuleContext ctx) {
        super(ctx);
    }

    @Override
    public void transform(FTTransformSession session) {
        session.setPendingReturn(true);
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
        return TemplateLexer.Return;
    }
}

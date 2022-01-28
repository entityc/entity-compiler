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
import org.entityc.compiler.transform.template.TemplateLexer;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;

@TemplateInstruction(category = TemplateInstructionCategory.MISC,
    name = "do",
    usage = "`do `*expression*",
    summary = "With this instruction you can call functions on an object to do something.",
    description = "This instruction simply evaluates an expression. It is useful when the expression contains a method that "
                  + "actually performs some operation that you want performed.")
public class FTDo extends FTNode {

    private final FTExpression rightExpression;

    public FTDo(ParserRuleContext ctx,
                @TemplateInstructionArgument(description = "The expression you want to evaluate.")
                    FTExpression expression) {
        super(ctx);
        this.rightExpression = expression;
    }

    @Override
    public void transform(FTTransformSession session) {
        if (this.rightExpression != null) {
            this.rightExpression.getValue(session);
        }
    }

    @Override
    public void accept(FTVisitor visitor) {
        visitor.visit(this);
        rightExpression.accept(visitor);
    }

    @Override
    public int getTemplateLexerSymbol() {
        return TemplateLexer.Do;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;

        formatController.addInstructionStart(indentLevel, this);
        if (!rightExpression.format(formatController, indentLevel)) {
            success = false;
        }
        formatController.addInstructionEnd(this);
        return success;
    }
}

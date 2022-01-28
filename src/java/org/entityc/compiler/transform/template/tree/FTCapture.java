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

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.InstructionArgument;

@TemplateInstruction(category = TemplateInstructionCategory.ASSIGNMENT,
    name = "capture",
    usage = "`capture `*variable*",
    summary = "Captures text into a variable instead of going to the output.",
    description = "This instruction will execute a piece of the template but instead of it going out to a file it "
                  + "capture it into a string variable. Its analogous to a `sprintf` in C. You could use a `let` "
                  + "instruction to do the same thing but its much more readable and natural to use this `capture` "
                  + "instruction.",
    contents = "This template code inside a `capture` block is just like any other template code, the only difference "
               + "is that its output is directed into the specified string variable.")
public class FTCapture extends FTContainerNode implements FTBodyBlock {

    private final String variableName;
    private final FTBody body = new FTBody();

    public FTCapture(ParserRuleContext ctx, FTContainerNode parent,
                     @TemplateInstructionArgument(
                         description = "The name of the variable you wish to be assigned with the captured string value."
                     )
                         String variable) {
        super(ctx, parent);
        this.variableName = variable;
    }

    @Override
    public FTBody getBody() {
        return body;
    }

    public String getVariableName() {
        return variableName;
    }

    @Override
    public void transform(FTTransformSession session) {
        session.pushCaptureBlock(this);
        super.transformChildren(session, false);
        session.popCaptureBlock();
    }

    @Override
    public int getTemplateLexerSymbol() {
        return GetTemplateLexerSymbol();
    }

    public static int GetTemplateLexerSymbol() {
        return TemplateLexer.Capture;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;

        formatController.addInstructionStart(indentLevel, this);
        formatController.addInstructionInside(InstructionArgument, variableName, this.getStartLineNumber());
        formatController.addInstructionEnd(this);
        super.formatChildren(formatController, indentLevel);
        formatController.addInstructionBlockEnd(indentLevel, this);
        return success;
    }

    @Override
    public boolean hasOwnBody() {
        return true;
    }
}

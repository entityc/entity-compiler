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

@TemplateInstruction(category = TemplateInstructionCategory.MOVEMENT,
    name = "send",
    usage = "`send `*receiveName*",
    summary = "Allows you to send text to a declared receive point.",
    description =
        "Sends its block of text to the specified receiver. This receiver must have been declared prior to this "
        + "instruction executes.",
    contents = "The text to send to the receiver."
)
public class FTSend extends FTContainerNode implements FTBodyBlock {

    private final String variableName;
    private final FTBody body = new FTBody();

    public FTSend(ParserRuleContext ctx, FTContainerNode parent,
                  @TemplateInstructionArgument(description =
                      "The name of the receiver (declared with a `receive` instruction) "
                      + "where the block of this instruction should be sent.")
                      String receiveName) {
        super(ctx, parent);
        this.variableName = receiveName;
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
        session.pushSendBlock(this);
        super.transformChildren(session, false);
        session.popSendBlock();
    }

    @Override
    public int getTemplateLexerSymbol() {
        return GetTemplateLexerSymbol();
    }

    public static int GetTemplateLexerSymbol() {
        return TemplateLexer.Send;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;
        formatController.addInstructionStart(indentLevel, this);
        formatController.addInstructionInside(InstructionArgument, variableName, this.getStartLineNumber());
        formatController.addInstructionEnd(this);
        if (!super.formatChildren(formatController, indentLevel)) {
            success = false;
        }
        formatController.addInstructionBlockEnd(indentLevel, this);
        return success;
    }

    @Override
    public boolean hasOwnBody() {
        return true;
    }

    @Override
    public boolean canSuppressIndent() {
        return true;
    }
}

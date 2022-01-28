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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.InstructionArgument;

@TemplateInstruction(category = TemplateInstructionCategory.MOVEMENT,
    name = "receive",
    usage = "`receive `[`distinct`]` `*name*",
    summary = "Defines a receive point where text can be sent.",
    description = "Creates a receive point with a give name to where a `send` instruction can send it text. A good example for "
                  + "this is to place a `receive` instruction where you want to accept import statements. Then farther down "
                  + "in the template execution just run a `send` instruction to send the import statement up to this receive "
                  + "point.")
public class FTReceive extends FTNode {

    private final String       variableName;
    private       boolean      distinct     = false;
    private       StringBuffer stringBuffer = new StringBuffer();

    public FTReceive(ParserRuleContext ctx,
                     @TemplateInstructionArgument(optional = true, keyword = true,
                     description = "An option that, if specified, will make sure that all lines of text that are sent to "
                                   + "this receive point are distinct, that is, there are no duplicates. This is useful for "
                                   + "a receive point for import statements since you don't want to duplicate them but you also "
                                   + "don't want to complicate the send logic to avoid them.")
                     Boolean distinct,
                     @TemplateInstructionArgument(
                         description = "The name of the receive point. The `send` instruction will reference this name when "
                                       + "sending code/text here."
                     )
                     String name) {
        super(ctx);
        this.distinct     = distinct;
        this.variableName = name;
    }

    public static FTReceive Copy(FTReceive from) {
        FTReceive copy = new FTReceive(null, from.distinct, from.variableName);
        copy.stringBuffer = new StringBuffer(from.stringBuffer);
        return copy;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public String getVariableName() {
        return variableName;
    }

    public void receive(String text) {
        stringBuffer.append(text);
    }

    public String getText() {
        if (distinct) {
            String[]     lines     = stringBuffer.toString().split("\\r?\\n");
            List<String> linesList = Arrays.asList(lines);

            List<String> distinctList = linesList.stream().distinct().collect(Collectors.toList());
            StringBuffer buffer       = new StringBuffer();
            for (String line : distinctList) {
                buffer.append(line + "\n");
            }
            return buffer.toString();
        }
        return stringBuffer.toString();
    }

    public void clear() {
        stringBuffer = new StringBuffer();
    }

    @Override
    public void transform(FTTransformSession session) {
        session.sendToOutput(this);
    }

    @Override
    public void accept(FTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public int getTemplateLexerSymbol() {
        return GetTemplateLexerSymbol();
    }

    public static int GetTemplateLexerSymbol() {
        return TemplateLexer.Receive;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;

        formatController.addInstructionStart(indentLevel, this);
        if (distinct) {
            formatController.addInstructionInside(InstructionArgument,
                                                  GetInstructionNameOfLexerSymbol(TemplateLexer.Distinct),
                                                  this.getStartLineNumber());
        }
        formatController.addInstructionInside(InstructionArgument, variableName, this.getStartLineNumber());
        this.addDescriptionToFormatController(formatController);
        formatController.addInstructionEnd(this);
        return success;
    }

    @Override
    public boolean canSuppressIndent() {
        return true;
    }
}

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

import java.util.ArrayList;
import java.util.List;

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.CaseItemDelim;
import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.InstructionArgument;

@TemplateInstruction(category = TemplateInstructionCategory.CONTROL_FLOW,
    name = "case",
    usage = "`case `*identifier*[`, `*identifier*]",
    summary = "The case part of a switch/case block.",
    description = "The `case` instruction is part of a `switch` instruction that provides a branch target and code "
                  + "to execute when the switch expression value matches the case value.",
    contents = "",
    seeAlso = {"switch", "default"})
public class FTCase extends FTContainerNode {

    private final List<String> identifiers = new ArrayList<>();

    public FTCase(ParserRuleContext ctx, FTContainerNode parent) {
        super(ctx, parent);
    }

    public FTCase(ParserRuleContext ctx, FTContainerNode parent,
                  @TemplateInstructionArgument(description = "An identifier that represents ...")
                      String identifier) {
        super(ctx, parent);
        this.identifiers.add(identifier);
    }

    public FTCase(ParserRuleContext ctx, FTContainerNode parent, List<String> identifiers) {
        super(ctx, parent);
        this.identifiers.addAll(identifiers);
    }

    public static Integer[] GetTemplateLexerSymbols() {
        return new Integer[]{TemplateLexer.Default, TemplateLexer.Case};
    }

    public List<String> getIdentifiers() {
        return identifiers;
    }

    @Override
    public int getTemplateLexerSymbol() {
        return isDefaultCase() ?
               TemplateLexer.Default :
               TemplateLexer.Case;
    }

    public boolean isDefaultCase() {
        return identifiers.size() == 0;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;

        formatController.addInstructionStart(indentLevel, this);
        boolean first = true;
        for (String ident : identifiers) {
            if (!first) {
                formatController.addInstructionInside(CaseItemDelim, ",", this.getStartLineNumber());
            }
            formatController.addInstructionInside(InstructionArgument, ident, this.getStartLineNumber());
            first = false;
        }
        formatController.addInstructionEnd(this);
        if (!super.formatChildren(formatController, indentLevel)) {
            success = false;
        }
        return success;
    }
}

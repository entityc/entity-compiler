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
import org.antlr.v4.runtime.ParserRuleContext;

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.InstructionArgument;
import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.InstructionKeyword;

/**
 * This is ONLY used in the event the template is being
 * reformatted and it needs to be put back into the reformatted template output.
 * Normally, a FTTemplate object is used when something is imported.
 */
@TemplateInstruction(category = TemplateInstructionCategory.FILE_IO,
    name = "import",
    usage = "`import \"`*templatePath*`\"` [ `from `*repositoryName* ]",
    summary = "Imports a file into the template engine and executes it.",
    description = "You can import template files that simply contain function declarations so that you can call "
                  + "those functions, or you can import templates you want to execute right after the import.")
public class FTImport extends FTNode {

    String templatePath;
    String fromRepositoryName;

    public FTImport(ParserRuleContext ctx,
                    @TemplateInstructionArgument(
                        description = "The path to the template file relative to the current repository importer.")
                    String templatePath,
                    @TemplateInstructionArgument(optional = true,
                    description = "This is an optional repository name when you want to import from a different "
                                  + "repository than the current repository.")
                    String repositoryName) {
        super(ctx);
        this.templatePath = templatePath;
        this.fromRepositoryName = repositoryName;
    }

    @Override
    public void accept(FTVisitor visitor) {

    }

    @Override
    public int getTemplateLexerSymbol() {
        return TemplateLexer.Import;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        formatController.addInstructionStart(indentLevel, this);
        formatController.addInstructionInside(InstructionArgument, "\"" + templatePath + "\"", this.getStartLineNumber());
        if (fromRepositoryName != null) {
            formatController.addInstructionInside(InstructionKeyword, GetInstructionNameOfLexerSymbol(TemplateLexer.From), -1);
            formatController.addInstructionInside(InstructionArgument, fromRepositoryName, -1);
        }
        formatController.addInstructionEnd(this);
        return true;
    }

    @Override
    public boolean canSuppressIndent() {
        return true;
    }
}

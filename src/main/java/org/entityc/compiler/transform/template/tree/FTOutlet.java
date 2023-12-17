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

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.InstructionArgument;

@TemplateInstruction(category = TemplateInstructionCategory.PUBLISHING,
    name = "outlet",
    usage = "`outlet `*name*",
    summary = "Defines a place where authors can send code/text.",
    description = "Specifies an outlet inside a publisher. This will be a place where authors can send code (or text). "
                  + "When author template code is executed for this outlet, template variables called `__outlet` and `__author` "
                  + "will be made available so the authoring code. This gives the ability to create comments in code that "
                  + "can help a developer know how that code got there.",
    contents = "If no authors are matched up to an outlet then the contents of this outlet block is executed."
)
public class FTOutlet extends FTContainerNode {

    private final String         name;
    private final FTPublisher    publisher;
    private final List<FTAuthor> authors = new ArrayList<>();

    public FTOutlet(ParserRuleContext ctx, FTContainerNode parent,
                    @TemplateInstructionArgument(
                        description = "The name of the outlet. Authors will use this name along with that of its publisher's "
                                      + "namespace to identify the outlet.")
                        String name,
                    FTPublisher publisher) {
        super(ctx, parent);
        this.name      = name;
        this.publisher = publisher;
    }

    public String getName() {
        return name;
    }

    public FTPublisher getPublisher() {
        return publisher;
    }

    @Override
    public void transform(FTTransformSession session) {
        if (authors.size() == 0) {
            super.transform(session); // default to its own content if there are no authors
            return;
        }
        for (FTPublishPhase phase : FTPublishPhase.values()) {
            for (FTAuthor author : authors) {
                if (author.getPhase() == phase) {
                    if (author.getScope() == FTPublishScope.Author) {
                        session.pushAuthorScope(author);
                    }
                    session.setValue("__outlet", this);
                    session.setValue("__author", author);
                    author.transformFromOutlet(session);
                    if (session.isPendingReturn()) {
                        session.setPendingReturn(false); // now clear it
                    }
                    session.removeValue("__outlet");
                    session.removeValue("__author");
                    if (author.getScope() == FTPublishScope.Author) {
                        session.popAuthorScope();
                    }
                }
            }
        }
    }

    public void addAuthor(FTAuthor author) {
        authors.add(author);
    }

    @Override
    public int getTemplateLexerSymbol() {
        return GetTemplateLexerSymbol();
    }

    public static int GetTemplateLexerSymbol() {
        return TemplateLexer.Outlet;
    }

    @Override
    public boolean canSuppressIndent() {
        return true;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;

        formatController.addInstructionStart(indentLevel, this);
        formatController.addInstructionInside(InstructionArgument, name, this.getStartLineNumber());
        this.addDescriptionToFormatController(formatController);
        formatController.addInstructionEnd(this);
        super.formatChildren(formatController, indentLevel);
        formatController.addInstructionBlockEnd(indentLevel, this);
        return success;
    }
}

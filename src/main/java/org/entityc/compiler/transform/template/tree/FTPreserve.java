/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

import org.entityc.compiler.transform.template.TemplateLexer;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.InstructionArgument;

public class FTPreserve extends FTContainerNode implements FTBodyBlock {

    private final String       MARKER_BASE         = "=====preserve=====";
    private final String       MARKER_TAIL         = "=====";
    private final String       MARKER_START_PREFIX = "start";
    private final String       MARKER_END_PREFIX   = "end";
    private final String       FULL_START_MARKER;
    private final String       FULL_END_MARKER;
    private final String       name;
    private final FTBody       body                = new FTBody();
    private final List<String> deprecatedNames     = new ArrayList<>();

    public FTPreserve(ParserRuleContext ctx, FTContainerNode parent, String name) {
        super(ctx, parent);
        this.name = name;
        FULL_START_MARKER = MARKER_BASE + " " + MARKER_START_PREFIX + "-" + name + " " + MARKER_TAIL;
        FULL_END_MARKER = MARKER_BASE + " " + MARKER_END_PREFIX + "-" + name + " " + MARKER_TAIL;
    }

    public void addDeprecatedName(String name) {
        deprecatedNames.add(name);
    }

    public List<String> getDeprecatedNames() {
        return deprecatedNames;
    }

    public String getStartMarker(String blockCommentStart, String blockCommentEnd) {
        return blockCommentStart + " " + FULL_START_MARKER + (blockCommentEnd != null ? (" " + blockCommentEnd) : "");
    }

    public String getStartMarker(String blockCommentStart, String blockCommentEnd, String deprecatedName) {
        return blockCommentStart + " " + MARKER_BASE + " " + MARKER_START_PREFIX + "-" + deprecatedName + " " + MARKER_TAIL + (blockCommentEnd != null ? (" " + blockCommentEnd) : "");
    }

    public String getEndMarker(String blockCommentStart, String blockCommentEnd) {
        return blockCommentStart + " " + FULL_END_MARKER + (blockCommentEnd != null ? (" " + blockCommentEnd) : "");
    }

    public String getEndMarker(String blockCommentStart, String blockCommentEnd, String deprecatedName) {
        return blockCommentStart + " " + MARKER_BASE + " " + MARKER_END_PREFIX + "-" + deprecatedName + " " + MARKER_TAIL + (blockCommentEnd != null ? (" " + blockCommentEnd) : "");
    }

    @Override
    public FTBody getBody() {
        return body;
    }

    @Override
    public void transform(FTTransformSession session) {
        session.pushPreserveBlock(this);
        super.transformChildren(session, false);
        session.popPreserveBlock(session);
    }

    @Override
    public int getTemplateLexerSymbol() {
        return TemplateLexer.Preserve;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;

        formatController.addInstructionStart(indentLevel, this);
        formatController.addInstructionInside(InstructionArgument, name, this.getStartLineNumber());
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

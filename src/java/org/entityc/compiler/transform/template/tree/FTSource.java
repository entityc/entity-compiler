/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.antlr.v4.runtime.ParserRuleContext;

public class FTSource extends FTNode {

    private final String text;
    private final int    startLineNumber, startCharPosition;
    private final int endLineNumber, endCharPosition;
    private boolean possibleTemplateIndent;
    private boolean shouldRemove;

    public FTSource(ParserRuleContext ctx, String text, int startLineNumber, int startCharPosition, int endLineNumber, int endCharPosition) {
        super(ctx);
        this.startLineNumber = startLineNumber;
        this.startCharPosition = startCharPosition;
        this.endLineNumber = endLineNumber;
        this.endCharPosition = endCharPosition;
        this.text = text;
    }

    @Override
    public int getStartCharPosition() {
        return startCharPosition;
    }

    @Override
    public int getEndCharPosition() {
        return endCharPosition;
    }

    public boolean isShouldRemove() {
        return shouldRemove;
    }

    public void setShouldRemove(boolean shouldRemove) {
        this.shouldRemove = shouldRemove;
    }

    public boolean isJustSpaces() {
        return text.length() > 1 && text.trim().isEmpty();
    }

    public boolean hasJustSpacesOnLastLine() {
        int lastIndexOfReturn = text.lastIndexOf('\n');
        if (lastIndexOfReturn == -1) {
            lastIndexOfReturn = 0;
        }
        return text.length() > 0 && text.substring(lastIndexOfReturn).trim().isEmpty();
    }

    public boolean isPossibleTemplateIndent() {
        return possibleTemplateIndent;
    }

    public void setPossibleTemplateIndent(boolean possibleTemplateIndent) {
//        if (possibleTemplateIndent) {
//            ECLog.logInfo("Line: " + line + " -- Possible spaces to remove.");
//        }
        this.possibleTemplateIndent = possibleTemplateIndent;
    }

    @Override
    public void transform(FTTransformSession session) {
        if (!shouldRemove) {
            session.sendToOutput(text);
        }
    }

    @Override
    public void accept(FTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        formatController.addSourceString(getText(), this.getStartLineNumber(), this.getEndLineNumber());
        return true;
    }

    public String getText() {
        return text;
    }

    @Override
    public int getStartLineNumber() {
        return startLineNumber;
    }

    @Override
    public int getEndLineNumber() {
        return endLineNumber;
    }

    @Override
    public int getTemplateLexerSymbol() {
        return NO_SYMBOL;
    }
}

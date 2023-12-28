/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.formatter;

import org.entityc.compiler.transform.template.tree.expression.FTOperation;
import org.entityc.compiler.util.ECLog;

class TextSegment {

    TextSegmentType      type;
    int                  indentLevel;
    boolean              enableIndent;
    boolean              enableNewLineBefore;
    boolean              enableNewLineAfter;
    int                  startLineNumber;
    int                  endLineNumber;
    ConfigurableElement  element;
    boolean              spaceBefore;
    String               text;
    boolean              spaceAfter;
    FTOperation.Operator operator;
    int                  textBodyLevel; // for source types
    boolean              suppressIndent;
    int                  finalCharPos;
    private TextSegment  alignToSegment;
    private boolean      copied = false;

    TextSegment(TextSegmentType type, ConfigurableElement element, int startLineNumber, int endLineNumber, String text) {
        this.type            = type;
        this.element         = element;
        this.startLineNumber = startLineNumber;
        this.endLineNumber   = endLineNumber;
        this.text            = text;
    }

    TextSegment(TextSegmentType type, ConfigurableElement element, String text) {
        this.type            = type;
        this.element         = element;
        this.startLineNumber = -1;
        this.endLineNumber   = -1;
        this.text            = text;
    }

    TextSegment(TextSegmentType type, ConfigurableElement element) {
        this.type            = type;
        this.element         = element;
        this.startLineNumber = -1;
        this.endLineNumber   = -1;
        this.text            = null;
    }

    TextSegment(TextSegment otherSegment) {
        this.type                = otherSegment.type;
        this.indentLevel         = otherSegment.indentLevel;
        this.enableIndent        = otherSegment.enableIndent;
        this.enableNewLineBefore = otherSegment.enableNewLineBefore;
        this.enableNewLineAfter  = otherSegment.enableNewLineAfter;
        this.startLineNumber     = otherSegment.startLineNumber;
        this.endLineNumber       = otherSegment.endLineNumber;
        this.element             = otherSegment.element;
        this.spaceBefore         = otherSegment.spaceBefore;
        this.text                = otherSegment.text;
        this.spaceAfter          = otherSegment.spaceAfter;
        this.operator            = otherSegment.operator;
        this.textBodyLevel       = otherSegment.textBodyLevel;
        this.suppressIndent      = otherSegment.suppressIndent;
        this.finalCharPos        = otherSegment.finalCharPos;
        this.alignToSegment      = otherSegment.alignToSegment;
        this.copied              = true;
    }

    public TextSegment getAlignToSegment() {
        return alignToSegment;
    }

    public boolean isCopied() {
        return copied;
    }

    public void setAlignToSegment(TextSegment alignToSegment) {
        this.alignToSegment = alignToSegment;
    }

    public boolean hasJustSpacesOnLastLine() {
        int lastIndexOfReturn = text.lastIndexOf('\n');
        if (lastIndexOfReturn == -1) {
            lastIndexOfReturn = 0;
        }
        return text.substring(lastIndexOfReturn).trim().isEmpty(); //  text.length() > 0 &&
    }

    public String textExceptBlankLine() {
        int lastIndexOfReturn = text.lastIndexOf('\n');
        if (lastIndexOfReturn == -1) {
            lastIndexOfReturn = 0;
        }
        if (text.substring(lastIndexOfReturn).trim().isEmpty()) {
            return text.substring(0, lastIndexOfReturn);
        }
        return text;
    }

    public boolean isBlank() {
        return text.trim().isEmpty();
    }

    public void trimSpacesOnLastLine() {
        int lastIndexOfReturn = text.lastIndexOf('\n');
        if (lastIndexOfReturn == -1) {
            lastIndexOfReturn = 0;
        } else {
            lastIndexOfReturn++;
        }
        text = text.substring(0, lastIndexOfReturn);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                sb.append(' ');
            } else {
                sb.append(text.charAt(i));
            }
        }
        text = sb.toString();
    }

    boolean isSource() {
        return type == TextSegmentType.Source;
    }

    boolean isVariable() {
        return type == TextSegmentType.Variable;
    }
}

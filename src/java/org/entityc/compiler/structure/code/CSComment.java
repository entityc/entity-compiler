/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

public class CSComment extends CSNode {

    private final String         text;
    private final CSCommentStyle style;

    public CSComment(CSCommentStyle style, String text) {
        this.style = style;
        this.text = text;
    }

    public CSCommentStyle getStyle() {
        return style;
    }

    public String getText() {
        return text;
    }

    @Override
    public void accept(CSVisitor visitor) {

    }

    public enum CSCommentStyle {
        BLOCK,
        LINE
    }
}

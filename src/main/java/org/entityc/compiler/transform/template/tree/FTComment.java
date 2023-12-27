/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.antlr.v4.runtime.ParserRuleContext;

public class FTComment extends FTNode {

    public FTComment(ParserRuleContext ctx) {
        super(ctx);
    }

    @Override
    public void accept(FTVisitor visitor) {

    }

    @Override
    public int getTemplateLexerSymbol() {
        return 0;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        formatController.addInstructionPrefix(indentLevel, this);
        formatController.addComment(this);
        formatController.addInstructionEnd(this);
        return true;
    }

    public static String GetInstructionName() {
        return "comment";
    }
}

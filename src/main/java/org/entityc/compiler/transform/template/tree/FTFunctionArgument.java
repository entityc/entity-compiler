/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

import org.antlr.v4.runtime.ParserRuleContext;

public class FTFunctionArgument extends FTNode {

    private final String name;

    public FTFunctionArgument(ParserRuleContext ctx, String name) {
        super(ctx);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(FTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public int getTemplateLexerSymbol() {
        return NO_SYMBOL;
    }
}

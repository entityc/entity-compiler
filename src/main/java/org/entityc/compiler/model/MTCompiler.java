/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model;

import org.entityc.compiler.EntityCompiler;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * The purpose of this class is simply to provide its version to a template via compiler.version.
 */
public class MTCompiler extends MTNode {

    public MTCompiler(ParserRuleContext ctx) {
        super(ctx);
    }

    public String getVersion() {
        return EntityCompiler.COMPILER_VERSION;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }
}

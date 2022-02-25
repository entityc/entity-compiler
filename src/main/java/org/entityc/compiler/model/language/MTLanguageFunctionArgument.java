/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.language;

import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.entity.MTNativeType;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class MTLanguageFunctionArgument extends MTNode {

    private final MTLanguageFunction    function;
    private final MTNativeType.DataType type;
    private final String                name;

    public MTLanguageFunctionArgument(ParserRuleContext ctx, MTLanguageFunction function, MTNativeType.DataType type, String name) {
        super(ctx);
        this.function = function;
        this.type = type;
        this.name = name;
    }

    public MTLanguageFunction getFunction() {
        return function;
    }

    public MTNativeType.DataType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }
}

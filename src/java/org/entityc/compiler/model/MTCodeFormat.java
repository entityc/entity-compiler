/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model;

import org.entityc.compiler.model.visitor.MTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

import javax.json.JsonObject;

public class MTCodeFormat extends MTNode {

    private String     name;
    private JsonObject settings;

    public MTCodeFormat(ParserRuleContext ctx, String name, JsonObject settings) {
        super(ctx);
        this.name = name;
        this.settings = settings;
    }

    public JsonObject getSettings() {
        return settings;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }
}

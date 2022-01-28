/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.entity;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.config.MTSpace;

public class MTAssetType extends MTType {

    public MTAssetType(ParserRuleContext ctx) {
        super(ctx);
    }

    public boolean resolveReferences(MTSpace space, int pass) {
        return false;
    }
}

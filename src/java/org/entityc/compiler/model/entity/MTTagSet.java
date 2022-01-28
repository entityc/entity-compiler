/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.entity;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.visitor.MTVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains a list of tags used to check if something contains all
 * of the tags in the set.
 */
public class MTTagSet extends MTNode {

    List<String> tags = new ArrayList<>();

    public MTTagSet(ParserRuleContext ctx) {
        super(ctx);
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public boolean matches(List<String> tags) {
        return tags.containsAll(tags);
    }

    @Override
    public void accept(MTVisitor visitor) {

    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.entity;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.visitor.MTVisitor;

/**
 * Represents a notation among one or more entities about uniqueness.
 * This can be used to establish certain uniqueness in objects of specified entities,
 * particularly when the unique keyword is used in relationships.
 */
public class MTUniqueness extends MTNode {

    private final MTRelationship parentRelationship;
    private       MTRelationship relationship;

    public MTUniqueness(ParserRuleContext ctx, MTRelationship parentRelationship) {
        super(ctx);
        this.parentRelationship = parentRelationship;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    public MTRelationship getRelationship() {
        return relationship;
    }

    public void setRelationship(MTRelationship relationship) {
        this.relationship = relationship;
    }

    public MTRelationship getParentRelationship() {
        return parentRelationship;
    }
}

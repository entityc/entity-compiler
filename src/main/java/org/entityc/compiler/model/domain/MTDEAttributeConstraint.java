/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.domain;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.entity.MTAttributeConstraint;
import org.entityc.compiler.model.visitor.MTVisitor;

import java.util.List;

public class MTDEAttributeConstraint extends MTNode implements MTDomainBased, MTNamed {

    MTAttributeConstraint attributeConstraint;
    MTDEAttribute         domainAttribute;

    public MTDEAttributeConstraint(ParserRuleContext ctx, MTDEAttribute domainAttribute, MTAttributeConstraint constraint) {
        super(ctx);
        this.domainAttribute     = domainAttribute;
        this.attributeConstraint = constraint;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @Override
    public MTDomain getDomain() {
        return domainAttribute.getDomain();
    }

    @Override
    public String getFullname(String delim) {
        return domainAttribute.getFullname(delim) + delim + attributeConstraint.getName();
    }

    @Override
    public String getName() {
        return attributeConstraint.getName();
    }

    public MTDEAttributeConstraintExpression getExpression() {
        return new MTDEAttributeConstraintExpression(getParserRuleContext(), domainAttribute,
                                                     domainAttribute.getConstraintByName(attributeConstraint.getName()),
                                                     attributeConstraint.getExpression());
    }

    @Override
    public boolean hasDescription() {
        return attributeConstraint.hasDescription();
    }

    @Override
    public String getDescription() {
        return attributeConstraint.getDescription();
    }

    @Override
    public boolean hasTags() {
        return attributeConstraint.hasTags();
    }

    @Override
    public boolean hasTag(String tag) {
        return attributeConstraint.hasTag(tag);
    }

    @Override
    public List<String> getTags() {
        return attributeConstraint.getTags();
    }

    @Override
    public String tagsSeparatedBy(String delimiter) {
        return attributeConstraint.tagsSeparatedBy(delimiter);
    }
}

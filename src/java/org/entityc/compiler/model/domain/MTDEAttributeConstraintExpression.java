/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.domain;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.expression.MTExpression;
import org.entityc.compiler.model.language.MTLanguage;
import org.entityc.compiler.model.visitor.MTVisitor;

@ModelClass(type = ModelClassType.DOMAIN, description = "Represents a constraint on an attribute in the form of an expression.")
public class MTDEAttributeConstraintExpression extends MTNode implements MTDomainBased {

    private final MTDEAttribute           domainAttribute;
    private final MTDEAttributeConstraint constraint;
    private final MTExpression            expression;

    public MTDEAttributeConstraintExpression(ParserRuleContext ctx, MTDEAttribute domainAttribute, MTDEAttributeConstraint constraint, MTExpression expression) {
        super(ctx);
        this.domainAttribute = domainAttribute;
        this.constraint      = constraint;
        this.expression      = expression;
    }

    public MTDEAttribute getDomainAttribute() {
        return domainAttribute;
    }

    public MTDEAttributeConstraint getConstraint() {
        return constraint;
    }

    public MTExpression getExpression() {
        return expression;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    public String mapToLanguage(MTLanguage language, MTDEntity domainEntity, String objectName) {
        return expression.mapToLanguage(language, domainEntity, objectName);
    }

    @Override
    public MTDomain getDomain() {
        return null;
    }

    @Override
    public String getFullname(String delim) {
        return constraint.getFullname(delim) + delim + "expression";
    }
}

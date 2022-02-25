/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.entity;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.doc.annotation.ModelMethod;
import org.entityc.compiler.doc.annotation.ModelMethodCategory;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.domain.MTNamed;
import org.entityc.compiler.model.expression.MTExpression;
import org.entityc.compiler.model.visitor.MTVisitor;

@ModelClass(type = ModelClassType.ENTITY,
    description =
        "Represents some type of constraint placed upon an attribute. Constraints can help prevent attributes "
        + "from receiving values that are not valid for a specific application.")
public class MTAttributeConstraint extends MTNode implements MTNamed {

    private final MTAttribute  attribute;
    private final String       name;
    private final MTExpression expression;

    public MTAttributeConstraint(ParserRuleContext ctx, MTAttribute attribute, String name, MTExpression expression) {
        super(ctx);
        this.attribute  = attribute;
        this.name       = name;
        this.expression = expression;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns the attribute of this attribute constraint.")
    public MTAttribute getAttribute() {
        return attribute;
    }

    @Override
    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns the name of this attribute constraint.")
    public String getName() {
        return name;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns the expression of this attribute constraint.")
    public MTExpression getExpression() {
        return expression;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }
}

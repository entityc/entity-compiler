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
import org.entityc.compiler.model.visitor.MTVisitor;

@ModelClass(type = ModelClassType.ENTITY, description = "Represents an enum item.")
public class MTEnumItem extends MTNode implements MTNamed, MTTemplateSupport {

    private final MTEnum parentEnum;
    private final String name;
    private final int    value;

    public MTEnumItem(ParserRuleContext ctx, MTEnum parentEnum, String name, int value) {
        super(ctx);
        this.parentEnum = parentEnum;
        this.name       = name;
        this.value      = value;
    }

    @ModelMethod(category = ModelMethodCategory.ENUM, description = "Gets the enum of this item.")
    public MTEnum getEnum() {
        return parentEnum;
    }

    @ModelMethod(category = ModelMethodCategory.ENUM, description = "Gets the name of this item.")
    @Override
    public String getName() {
        return name;
    }

    @ModelMethod(category = ModelMethodCategory.ENUM, description = "Gets the numeric value of this item.")
    public int getValue() {
        return value;
    }

    public void accept(MTVisitor visitor) {

    }
}

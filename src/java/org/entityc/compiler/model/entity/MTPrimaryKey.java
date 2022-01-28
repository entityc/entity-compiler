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
import org.entityc.compiler.model.visitor.MTVisitor;

import java.util.ArrayList;
import java.util.List;

@ModelClass(type = ModelClassType.ENTITY, description = "Represents the primary key of an entity.")
public class MTPrimaryKey extends MTNode implements MTTemplateSupport {

    private final List<MTAttribute> attributes = new ArrayList<>();

    public MTPrimaryKey(ParserRuleContext ctx) {
        super(ctx);
    }

    public void addAttribute(MTAttribute attribute) {
        attribute.setPrimaryKey(true);
        attributes.add(attribute);
    }

    public List<MTAttribute> getAttributes() {
        return attributes;
    }

    public MTAttribute getAttributeByName(String name) {
        for (MTAttribute attribute : attributes) {
            if (attribute.getName().equals(name)) {
                return attribute;
            }
        }
        return null;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @ModelMethod(category = ModelMethodCategory.PRIMARY_KEY,
        description = "Gets the data type of the primary key.")
    public MTType getType() {
        if (attributes.size() == 1) {
            return attributes.get(0).getType();
        }
        return null;
    }
}

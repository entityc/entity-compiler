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
 * Represents an instantiation of an entity template. The only reason this is necessary is because
 * I want to allow the entity template to be declared later in the source input.
 */
public class MTEntityTemplateInstantiation extends MTNode {

    private final String        entityTemplateName;
    private final List<String>  templateArgEntityNames = new ArrayList<>();
    private final List<Boolean> templateArgUnique      = new ArrayList<>();

    public MTEntityTemplateInstantiation(ParserRuleContext ctx, String entityTemplateName) {
        super(ctx);
        this.entityTemplateName = entityTemplateName;
    }

    public List<String> getTemplateArgEntityNames() {
        return templateArgEntityNames;
    }

    public List<Boolean> getTemplateArgUnique() {
        return templateArgUnique;
    }

    public void addTemplateArgEntityName(String entityName, boolean unique) {
        this.templateArgEntityNames.add(entityName);
        this.templateArgUnique.add(unique);
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    public String getEntityTemplateName() {
        return entityTemplateName;
    }
}

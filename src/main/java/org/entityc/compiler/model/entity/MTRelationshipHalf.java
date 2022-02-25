/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.entity;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.visitor.MTVisitor;

public class MTRelationshipHalf extends MTNode {

    private final String                        entityName;
    private       HalfRelationshipPlurality     plurality;
    private       MTEntity                      entity;
    private       MTEntityTemplateInstantiation templateInstantiation;

    public MTRelationshipHalf(ParserRuleContext ctx, String entityName) {
        super(ctx);
        this.entityName = entityName;
    }

    public MTRelationshipHalf(ParserRuleContext ctx, HalfRelationshipPlurality plurality, String entityName) {
        super(ctx);
        this.plurality  = plurality;
        this.entityName = entityName;
    }

    public HalfRelationshipPlurality getPlurality() {
        return plurality;
    }

    public void setPlurality(HalfRelationshipPlurality plurality) {
        this.plurality = plurality;
    }

    public String getEntityName() {
        return entityName;
    }

    public MTEntity getEntity() {
        return entity;
    }

    public void setEntity(MTEntity entity) {
        this.entity = entity;
    }

    public MTEntityTemplateInstantiation getTemplateInstantiation() {
        return templateInstantiation;
    }

    public void setTemplateInstantiation(MTEntityTemplateInstantiation templateInstantiation) {
        this.templateInstantiation = templateInstantiation;
    }

    public boolean isMany() {
        return plurality == HalfRelationshipPlurality.MANY;
    }

    public boolean isOne() {
        return plurality == HalfRelationshipPlurality.ONE;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }
}

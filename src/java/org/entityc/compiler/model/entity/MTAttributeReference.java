/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.entity;

import org.entityc.compiler.model.foundation.MFObject;
import org.entityc.compiler.util.ECLog;

/**
 * Simply used temporarily to contain a reference to an attribute from outside the entity level
 * by way of a relationship to that entity.
 * <p>
 * The references are text based so they can be established before the resolution stage.
 */
public class MTAttributeReference extends MFObject {

    private final String         relationshipName;
    private final String         attributeName;
    private final String         attributeAsName;
    private       String         entityName;
    private       MTEntity       resolvedEntity;
    private       MTRelationship resolvedRelationship;
    private       MTAttribute    resolvedAttribute;

    public MTAttributeReference(String entityName, String relationshipName, String attributeName, String attributeAsName) {
        this.entityName       = entityName;
        this.relationshipName = relationshipName;
        this.attributeName    = attributeName;
        this.attributeAsName  = attributeAsName;
        if (entityName == null && relationshipName == null) {
            ECLog.logFatal("An attribute reference requires at least and entity or relationship name!");
        }
    }

    public MTEntity getResolvedEntity() {
        return resolvedEntity;
    }

    public void setResolvedEntity(MTEntity resolvedEntity) {
        this.resolvedEntity = resolvedEntity;
    }

    public MTRelationship getResolvedRelationship() {
        return resolvedRelationship;
    }

    public void setResolvedRelationship(MTRelationship resolvedRelationship) {
        this.resolvedRelationship = resolvedRelationship;
    }

    public MTAttribute getResolvedAttribute() {
        return resolvedAttribute;
    }

    public void setResolvedAttribute(MTAttribute resolvedAttribute) {
        this.resolvedAttribute = resolvedAttribute;
    }

    public String getRelationshipName() {
        return relationshipName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getAttributeAsName() {
        return attributeAsName;
    }

    public String getResolvedAttributeName() {
        if (resolvedAttribute == null) {
            return null;
        }
        if (attributeAsName != null) {
            return attributeAsName;
        }
        return attributeName;
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.entity;

/**
 * Simply used temporarily to contain a reference to an attribute from outside the entity level
 * by way of a relationship to that entity.
 * <p>
 * The references are text based so they can be established before the resolution stage.
 */
public class MTViewReference {

    private final String         relationshipName;
    private final String         viewName;
    private final String         asName;
    private final boolean        asArray;
    private       String         entityName;
    private       MTEntity       resolvedEntity;
    private       MTRelationship resolvedRelationship;
    private       MTView         resolvedView;

    public MTViewReference(String entityName, String relationshipName, String viewName, String asName, boolean asArray) {
        this.entityName       = entityName;
        this.relationshipName = relationshipName;
        this.viewName         = viewName;
        this.asName           = asName;
        this.asArray          = asArray;
    }

    public boolean isAsArray() {
        return asArray;
    }

    public String getViewName() {
        return viewName;
    }

    public String getAsName() {
        return asName;
    }

    public MTView getResolvedView() {
        return resolvedView;
    }

    public void setResolvedView(MTView resolvedView) {
        this.resolvedView = resolvedView;
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

    public String getRelationshipName() {
        return relationshipName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}

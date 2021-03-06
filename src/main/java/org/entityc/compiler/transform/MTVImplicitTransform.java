/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform;

import org.entityc.compiler.model.MTRoot;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.entity.FullRelationshipPlurality;
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTPrimaryKey;
import org.entityc.compiler.model.entity.MTRelationship;

// TODO: Create index from parent relationships

public class MTVImplicitTransform extends MTBaseTransform {

    public MTVImplicitTransform(MTRoot root, String configurationName) {
        super("Implicit", root, configurationName);
    }

    @Override
    public void visitRoot(MTRoot root) {
        root.accept(this);
    }

    @Override
    public void visitSpace(MTSpace space) {
        space.accept(this);
    }

    @Override
    public void visitEntity(MTEntity entity) {

        if (entity.isIncluded() || entity.isExtern() || entity.isTransient()) {
            return;
        }

        if (entity.isSecondary() && !entity.isImplicit()) {
            return; // skip secondary entities
        }

        entity.accept(this);
    }

    @Override
    public void visitEnum(MTEnum mtenum) {
        mtenum.accept(this);
    }

    @Override
    public void visitPrimaryKey(MTPrimaryKey primaryKey) {
        if (primaryKey.getAttributes().size() == 1) {
            visitAttribute(primaryKey.getAttributes().get(0));
        }
    }

    @Override
    public void visitAttribute(MTAttribute attribute) {
    }

    @Override
    public void visitRelationship(MTRelationship relationship) {

        MTEntity fromEntity = relationship.getFrom().getEntity();
        MTEntity toEntity   = relationship.getTo().getEntity();
        if (toEntity == null || fromEntity == null) {
            relationship.resolveReferences(root.getSpace(), 0);
            toEntity = relationship.getTo().getEntity();
            fromEntity = relationship.getFrom().getEntity();
        }
        MTPrimaryKey toPrimaryKey = toEntity.getPrimaryKey();
        if (toPrimaryKey == null) {
            return;
        }
        FullRelationshipPlurality plurality      = relationship.getFullRelationshipPlurality();
        MTPrimaryKey              fromPrimaryKey = fromEntity.getPrimaryKey();
        if (!relationship.getFrom().getEntity().isImplicit()) {
            if (fromPrimaryKey == null && plurality != FullRelationshipPlurality.MANY_TO_ONE) {
                return;
            }
        }
        if (toPrimaryKey.getAttributes().size() != 1
                || (fromPrimaryKey != null && fromPrimaryKey.getAttributes().size() != 1)) {
            return; // unsupported right now
        }

        switch (plurality) {
            case MANY_TO_MANY: {
                // create table to support relationship
                String virtualEntityName = fromEntity.getName() + "-" + toEntity.getName();
                MTEntity.AddImplicitManyToManyEntity(fromEntity.getSpace(), fromEntity, toEntity);
            }
            break;
        }
    }

    @Override
    public String[] requiredDomainNames() {
        return new String[]{"Database"};
    }

    @Override
    public boolean canStart() {
        return hasRequiredDomains();
    }
}

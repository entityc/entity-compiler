/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.domain;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.MTReferenceResolution;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.entity.HalfRelationshipPlurality;
import org.entityc.compiler.model.entity.MTRelationshipHalf;
import org.entityc.compiler.model.visitor.MTVisitor;

public class MTDERelationshipHalf extends MTNode implements MTDomainBased, MTReferenceResolution {

    private final MTRelationshipHalf relationshipHalf;
    private final String             entityName;
    private final String             viewName;
    private       MTDomain           domain;
    private       MTDEntity          domainEntity;
    private       MTDView            domainEntityView;
    private       boolean            asPrimaryKey;

    public MTDERelationshipHalf(ParserRuleContext ctx, MTDomain domain, MTRelationshipHalf relationshipHalf, String viewName) {
        super(ctx);
        this.domain           = domain;
        this.relationshipHalf = relationshipHalf;
        this.entityName       = relationshipHalf.getEntityName();
        this.viewName         = viewName;
    }

    public boolean isAsPrimaryKey() {
        return asPrimaryKey;
    }

    public void setAsPrimaryKey(boolean asPrimaryKey) {
        this.asPrimaryKey = asPrimaryKey;
    }

    public HalfRelationshipPlurality getPlurality() {
        return relationshipHalf.getPlurality();
    }

    public boolean isMany() {
        return relationshipHalf.isMany();
    }

    public boolean isOne() {
        return relationshipHalf.isOne();
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        boolean anotherPass = false;

        if (domain != null && domain.isSpecialized()) {
            domain = domain.getBaseDomain();
        }

        if (domainEntity == null) {
            domainEntity = domain.getDomainEntityByName(entityName);
            if (domainEntity == null) {
                anotherPass = true;
            }
        }

        if (domainEntity != null && domainEntityView == null && viewName != null) {
            domainEntityView = domainEntity.getDomainEntityViewByName(viewName, true);
            if (domainEntityView == null) {
                anotherPass = true;
            }
        }
        return anotherPass;
    }

    @Override
    public MTDomain getDomain() {
        return domain;
    }

    @Override
    public String getFullname(String delim) {
        return null;
    }

    public boolean hasView() {
        return domainEntityView != null;
    }

    public MTDEntity getEntity() {
        return domainEntity;
    }

    public MTDView getView() {
        return domainEntityView;
    }
}

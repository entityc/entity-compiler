/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.visitor;

import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.MTRoot;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.domain.MTDEAttribute;
import org.entityc.compiler.model.domain.MTDEntity;
import org.entityc.compiler.model.domain.MTDomain;
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTPrimaryKey;
import org.entityc.compiler.model.entity.MTRelationship;

public abstract class MTBaseVisitor implements MTVisitor {

    protected MTRoot root;

    public MTBaseVisitor(MTRoot root) {
        this.root = root;
    }

    public void start() {
        //ECLog.logInfo("Starting to visit model: " + model.getName());
        visit(this.root);
    }

    @Override
    public void visit(MTNode node) {
        if (node instanceof MTRoot) {
            visitRoot((MTRoot) node);
        } else if (node instanceof MTSpace) {
            visitSpace((MTSpace) node);
        } else if (node instanceof MTEntity) {
            visitEntity((MTEntity) node);
        } else if (node instanceof MTAttribute) {
            visitAttribute((MTAttribute) node);
        } else if (node instanceof MTEnum) {
            visitEnum((MTEnum) node);
        } else if (node instanceof MTPrimaryKey) {
            visitPrimaryKey((MTPrimaryKey) node);
        } else if (node instanceof MTRelationship) {
            visitRelationship((MTRelationship) node);
        } else if (node instanceof MTDEntity) {
            visitDomainEntity(((MTDEntity) node));
        } else if (node instanceof MTDEAttribute) {
            visitDomainAttribute(((MTDEAttribute) node));
        }
    }

//    protected MTDomain domain(String name) {
//        return currentSpace().getDomainWithName(name);
//    }

    public boolean hasRequiredDomains() {
        for (String domainName : requiredDomainNames()) {
            MTDomain domain = this.root.getSpace().getDomainWithName(domainName);
            if (domain == null) {
                System.err.println("ERROR: Can't find domain: " + domainName);
                return false;
            }
        }
        return true;
    }

    public String[] requiredDomainNames() {
        return new String[0];
    }

    public boolean domainHasNamespace(MTDomain domain) {
        if (domain.getNamespace() == null) {
            System.err.println("Domain " + domain.getName() + " does not have a namespace defined.");
            return false;
        }
        return true;
    }

    public abstract boolean canStart();
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform;

import org.entityc.compiler.model.MTRoot;
import org.entityc.compiler.model.config.MTConfiguration;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.domain.MTDEAttribute;
import org.entityc.compiler.model.domain.MTDEntity;
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTPrimaryKey;
import org.entityc.compiler.model.entity.MTRelationship;
import org.entityc.compiler.model.entity.MTView;
import org.entityc.compiler.model.visitor.MTBaseVisitor;

public class MTBaseTransform extends MTBaseVisitor {

    private final String          name;
    private       MTConfiguration configuration;

    public MTBaseTransform(String name, MTRoot root, String configurationName) {
        super(root);
        if (root != null && configurationName != null) {
            this.configuration = root.getConfiguration(configurationName);
        }
        if (this.configuration == null) {
            //Exception e = new Exception();
            //e.printStackTrace();
            //ECLog.logFatal("[" + name + "] HEY can't find configuration named: " + configurationName);
        }
        this.name = name;
    }

    @Override
    public boolean canStart() {
        return false;
    }

    @Override
    public void visitRoot(MTRoot model) {

    }

    @Override
    public void visitSpace(MTSpace space) {

    }

    @Override
    public void visitEntity(MTEntity entity) {

    }

    @Override
    public void visitAttribute(MTAttribute attribute) {

    }

    @Override
    public void visitEnum(MTEnum mtEnum) {

    }

    @Override
    public void visitPrimaryKey(MTPrimaryKey primaryKey) {

    }

    @Override
    public void visitRelationship(MTRelationship relationship) {

    }

    @Override
    public void visitView(MTView view) {

    }

    @Override
    public void visitDomainEntity(MTDEntity domainEntity) {

    }

    @Override
    public void visitDomainAttribute(MTDEAttribute domainAttribute) {

    }

    @Override
    public boolean flattenSecondaryEntities() {
        return false;
    }

    public String getName() {
        return name;
    }

    public MTConfiguration getConfiguration() {
        return configuration;
    }
}

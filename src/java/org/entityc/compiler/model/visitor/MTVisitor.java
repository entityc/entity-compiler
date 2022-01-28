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
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTPrimaryKey;
import org.entityc.compiler.model.entity.MTRelationship;
import org.entityc.compiler.model.entity.MTView;

public interface MTVisitor {

    void visit(MTNode node);

    void visitRoot(MTRoot model);

    void visitSpace(MTSpace space);

    void visitEntity(MTEntity entity);

    void visitAttribute(MTAttribute attribute);

    void visitEnum(MTEnum mtEnum);

    void visitPrimaryKey(MTPrimaryKey primaryKey);

    void visitRelationship(MTRelationship relationship);

    void visitView(MTView view);

    void visitDomainEntity(MTDEntity domainEntity);

    void visitDomainAttribute(MTDEAttribute domainAttribute);

    boolean flattenSecondaryEntities();
}

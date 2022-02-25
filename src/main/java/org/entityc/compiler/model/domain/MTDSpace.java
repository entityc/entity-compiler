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
import org.entityc.compiler.model.visitor.MTVisitor;

import java.util.ArrayList;
import java.util.List;

public class MTDSpace extends MTNode implements MTReferenceResolution, MTDomainBased, MTNamed {

    private final MTDomain domain;
    private final MTSpace  space;

    public MTDSpace(ParserRuleContext ctx, MTDomain domain, MTSpace space) {
        super(ctx);
        this.domain = domain;
        this.space  = space;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        return false;
    }

    @Override
    public MTDomain getDomain() {
        return domain;
    }

    @Override
    public String getFullname(String delim) {
        return null;
    }

    @Override
    public String getName() {
        return domain.getNameFromDomainNaming(space);
    }

    public List<MTDEntity> entitiesTagged(String tag) {
        ArrayList<MTDEntity> list = new ArrayList<>();
        for (MTDEntity entity : domain.getDomainEntities()) {
            if (entity.hasTag(tag)) {
                list.add(entity);
            }
        }
        return list;
    }

    public boolean hasEntityTagged(String tag) {
        for (MTDEntity entity : domain.getDomainEntities()) {
            if (entity.hasTag(tag)) {
                return true;
            }
        }
        return false;
    }

    public MTDEntity entityTagged(String tag) {
        for (MTDEntity entity : domain.getDomainEntities()) {
            if (entity.hasTag(tag)) {
                return entity;
            }
        }
        return null;
    }

    public MTDEnum enumTagged(String tag) {
        for (MTDEnum mtdEnum : domain.getDomainEnums()) {
            if (mtdEnum.hasTag(tag)) {
                return mtdEnum;
            }
        }
        return null;
    }

    public boolean hasEnumTagged(String tag) {
        for (MTDEnum mtdEnum : domain.getDomainEnums()) {
            if (mtdEnum.hasTag(tag)) {
                return true;
            }
        }
        return false;
    }
}

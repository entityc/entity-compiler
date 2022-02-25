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
import org.entityc.compiler.model.entity.MTTypedef;
import org.entityc.compiler.model.visitor.MTVisitor;

public class MTDTypedef extends MTNode implements MTNamed, MTDomainBased, MTReferenceResolution {

    private final MTTypedef typedef;
    private final String    typedefName;
    private       MTDomain  domain;

    public MTDTypedef(ParserRuleContext ctx, MTDomain domain, MTTypedef typedef) {
        super(ctx);
        this.domain      = domain;
        this.typedef     = typedef;
        this.typedefName = typedef.getName();
    }

    public MTTypedef getTypedef() {
        return typedef;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        if (domain != null && domain.isSpecialized()) {
            domain = domain.getBaseDomain();
        }
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
        return domain.getNameFromDomainNaming(typedef);
    }
}

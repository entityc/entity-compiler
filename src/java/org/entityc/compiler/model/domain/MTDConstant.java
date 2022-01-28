/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.domain;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.expression.MTConstant;
import org.entityc.compiler.model.visitor.MTVisitor;

public class MTDConstant extends MTNode {

    private final MTDomain   domain;
    private final MTConstant constant;

    public MTDConstant(ParserRuleContext ctx, MTDomain domain, MTConstant constant) {
        super(ctx);
        this.domain   = domain;
        this.constant = constant;
    }

    public MTDomain getDomain() {
        return domain;
    }

    public MTConstant getConstant() {
        return constant;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    public String toString() {
        if (constant.isEnumItem()) {
            return domain.getNameFromDomainNaming(constant.getEnumItem().getEnum(), null) + "."
                   + domain.getNameFromDomainNaming(constant.getEnumItem(), null);
        }
        return constant.toString();
    }
}

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
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.util.ECLog;

public class MTDEAttributeBitField extends MTNode implements MTReferenceResolution, MTDomainBased {

    private final int           lowBit;
    private final int           highBit;
    private final String        replacedAttributeName; // until resolved
    private       MTDEAttribute domainAttribute;
    private       MTAttribute   replacedAttribute;

    public MTDEAttributeBitField(ParserRuleContext ctx, MTDEAttribute domainAttribute, int highBit, int lowBit, String replacedAttributeName) {
        super(ctx);
        this.domainAttribute       = domainAttribute;
        this.highBit               = highBit;
        this.lowBit                = lowBit;
        this.replacedAttributeName = replacedAttributeName;
    }

    public MTDEAttribute getDomainAttribute() {
        return domainAttribute;
    }

    public void setDomainAttribute(MTDEAttribute domainAttribute) {
        this.domainAttribute = domainAttribute;
    }

    public MTAttribute getReplacedAttribute() {
        return replacedAttribute;
    }

    public void setReplacedAttribute(MTAttribute replacedAttribute) {
        this.replacedAttribute = replacedAttribute;
    }

    public int getLowBit() {
        return lowBit;
    }

    public int getHighBit() {
        return highBit;
    }

    public String getReplacedAttributeName() {
        return replacedAttributeName;
    }

    @Override
    public String getFullname(String delim) {
        return domainAttribute.getFullname(delim) + highBit + "_" + lowBit; // ?? not sure this is really ever used
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @Override
    public MTDomain getDomain() {
        return domainAttribute.getDomain();
    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        boolean anotherPass = false;

        MTDEntity domainEntity = domainAttribute.getDomainEntity();
        if (domainEntity == null || domainEntity.getEntity() == null) {
            anotherPass = true;
        }
        else {
            MTEntity    entity    = domainEntity.getEntity();
            MTAttribute attribute = entity.getAttributeByName(replacedAttributeName);
            if (attribute == null) {
                ECLog.logFatal(this, "Not able to find attribute \"" + replacedAttributeName + "\" of entity \""
                                     + entity.getName() + "\".");
            }
            this.replacedAttribute = attribute;
        }
        return anotherPass;
    }
}

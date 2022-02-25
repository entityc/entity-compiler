/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.domain;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.MTReferenceResolution;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTEnumItem;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.util.ECLog;

@ModelClass(type = ModelClassType.DOMAIN, description = "Represents an enum item in the context of a domain.")
public class MTDEnumItem extends MTNode implements MTNamed, MTDomainBased, MTReferenceResolution {

    private final MTDEnum    domainEnum;
    private       MTDomain   domain;
    private       MTEnumItem item;
    private       String     itemName;
    private       String     explicitName;

    public MTDEnumItem(ParserRuleContext ctx, MTDEnum domainEnum, MTEnumItem item) {
        super(ctx);
        this.domain     = domainEnum.getDomain();
        this.domainEnum = domainEnum;
        this.item       = item;
    }

    public MTDEnumItem(ParserRuleContext ctx, MTDEnum domainEnum, String itemName) {
        super(ctx);
        this.domain     = domainEnum.getDomain();
        this.domainEnum = domainEnum;
        this.itemName   = itemName;
    }

    public MTDEnum getDomainEnum() {
        return domainEnum;
    }

    public MTEnum getEnum() {
        return domainEnum.getEnum();
    }

    public String getExplicitName() {
        return explicitName;
    }

    public void setExplicitName(String explicitName) {
        this.explicitName = explicitName;
    }

    public MTEnumItem getItem() {
        return item;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        if (domain != null && domain.isSpecialized()) {
            domain = domain.getBaseDomain();
        }

        if (item == null) {
            if (domainEnum.getEnum() != null) {
                item = domainEnum.getEnum().getItemByName(itemName);
                if (item == null) {
                    ECLog.logFatal(this, "Specified item \"" + itemName + "\" is not a member of enum \""
                                         + domainEnum.getEnum().getName() + "\".");
                }
            }
            else {
                return true;
            }
        }
        return false;
    }

    @Override
    public MTDomain getDomain() {
        return domain;
    }

    @Override
    public String getFullname(String delim) {
        return domainEnum.getFullname(delim) + delim + getName();
    }

    @Override
    public String getName() {
        if (explicitName != null) {
            return explicitName;
        }
        return domain.getNameFromDomainNaming(item);
    }

    public String getItemName() {
        return itemName;
    }
}

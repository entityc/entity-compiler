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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ModelClass(type = ModelClassType.DOMAIN, description = "Represents an enum in the context of a domain.")
public class MTDEnum extends MTNode implements MTNamed, MTDomainBased, MTReferenceResolution {

    private final String                   enumName;
    private final Map<String, MTDEnumItem> itemMap = new HashMap<>();
    private       MTDomain                 domain;
    private       MTEnum                   mtEnum;
    private       String                   explicitName;

    public MTDEnum(ParserRuleContext ctx, MTDomain domain, String enumName) {
        super(ctx);
        this.domain   = domain;
        this.enumName = enumName;
    }

    public MTDEnum(ParserRuleContext ctx, MTDomain domain, MTEnum mtEnum) {
        super(ctx);
        this.domain   = domain;
        this.mtEnum   = mtEnum;
        this.enumName = mtEnum.getName();
    }

    public String getEnumName() {
        return enumName;
    }

    public String getExplicitName() {
        return explicitName;
    }

    public void setExplicitName(String explicitName) {
        this.explicitName = explicitName;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    public MTEnum getEnum() {
        return mtEnum;
    }

    @Override
    public MTDomain getDomain() {
        return domain;
    }

    @Override
    public String getFullname(String delim) {
        return domain.getNamespace().getFullname(delim) + delim + getName();
    }

    @Override
    public String getName() {
        if (this.explicitName != null) {
            return explicitName;
        }
        return domain.getNameFromDomainNaming(mtEnum);
    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        boolean anotherPass = false;

        if (domain != null && domain.isSpecialized()) {
            domain = domain.getBaseDomain();
        }

        if (mtEnum == null) {
            mtEnum      = space.getEnumWithName(enumName);
            anotherPass = pass < 7 && mtEnum == null;
        }
        if (mtEnum != null) {
            for (MTDEnumItem mtdEnumItem : itemMap.values()) {
                if (mtdEnumItem.resolveReferences(space, pass)) {
                    anotherPass = true;
                }
            }
        }

        return anotherPass;
    }

    public MTDEnumItem getDomainEnumItem(MTEnumItem enumItem, boolean createIfNeeded) {
        MTDEnumItem domainEnumItem = itemMap.get(enumItem.getName());
        if (createIfNeeded && domainEnumItem == null) {
            domainEnumItem = new MTDEnumItem(null, this, enumItem);
            itemMap.put(enumItem.getName(), domainEnumItem);
        }
        return domainEnumItem;
    }

    public MTDEnumItem getDomainEnumItem(String itemName, boolean createIfNeeded) {
        MTDEnumItem domainEnumItem = itemMap.get(itemName);
        if (createIfNeeded && domainEnumItem == null) {
            domainEnumItem = new MTDEnumItem(null, this, itemName);
            itemMap.put(itemName, domainEnumItem);
        }
        return domainEnumItem;
    }

    public Collection<MTDEnumItem> getItems() {
        return itemMap.values();
    }

    public boolean hasItemTagged(String tag) {
        for (MTDEnumItem item : itemMap.values()) {
            if (item.hasTag(tag)) {
                return true;
            }
        }
        return false;
    }

    public Collection<MTDEnumItem> itemsTagged(String tag) {
        List<MTDEnumItem> taggedItems = new ArrayList<>();
        for (MTDEnumItem item : itemMap.values()) {
            if (item.hasTag(tag)) {
                taggedItems.add(item);
            }
        }
        return taggedItems;
    }

    public MTDEnumItem itemTagged(String tag) {
        for (MTDEnumItem item : itemMap.values()) {
            if (item.hasTag(tag)) {
                return item;
            }
        }
        return null;
    }
}

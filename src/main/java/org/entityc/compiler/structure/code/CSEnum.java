/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

import org.entityc.compiler.model.domain.MTDomain;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTEnumItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSEnum extends CSAnnotatableNode {

    private final String      name;
    private final CSNamespace namespace;
    List<CSEnumItem>        items         = new ArrayList<>();
    Map<String, MTEnumItem> mtEnumItemMap = new HashMap<>();
    private CSComment comment;

    public CSEnum(MTDomain domain, MTEnum mtEnum) {
        this.name = domain.getNameOfNode(mtEnum);
        this.namespace = new CSNamespace(domain.getNamespace().getSegments());
        if (mtEnum.getDescription() != null) {
            this.comment = new CSComment(CSComment.CSCommentStyle.BLOCK, mtEnum.getDescription());
        }

        for (MTEnumItem item : mtEnum.getItems()) {
            CSEnumItem enumItem = new CSEnumItem(domain, this, item);
            items.add(enumItem);
            mtEnumItemMap.put(enumItem.getName(), item);
        }
    }

    public String getName() {
        return name;
    }

    public List<CSEnumItem> getItems() {
        return items;
    }

    @Override
    public void accept(CSVisitor visitor) {

    }

    public CSNamespace getNamespace() {
        return namespace;
    }

    public CSComment getComment() {
        return comment;
    }

    public MTEnumItem getMTItem(CSEnumItem csEnumItem) {
        return mtEnumItemMap.get(csEnumItem.getName());
    }
}

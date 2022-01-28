/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

import org.entityc.compiler.model.domain.MTDomain;
import org.entityc.compiler.model.entity.MTEnumItem;

public class CSEnumItem extends CSNode {

    private final String    name;
    private final int       value;
    private final CSEnum    parentEnum;
    private       CSComment comment;

    public CSEnumItem(MTDomain domain, CSEnum csEnum, MTEnumItem mtEnumItem) {
        this.parentEnum = csEnum;
        this.name = domain.getNameOfNode(mtEnumItem);
        this.value = mtEnumItem.getValue();
        if (mtEnumItem.getDescription() != null) {
            this.comment = new CSComment(CSComment.CSCommentStyle.BLOCK, mtEnumItem.getDescription());
        }
    }

    public CSEnum getParentEnum() {
        return parentEnum;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void accept(CSVisitor visitor) {

    }

    public CSComment getComment() {
        return comment;
    }
}

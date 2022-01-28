/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.tagging;

import org.entityc.compiler.EntityLanguageParser;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.doc.annotation.ModelMethod;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.entity.MTNativeType;
import org.entityc.compiler.model.visitor.MTVisitor;

@ModelClass(type = ModelClassType.TAGGING,
        description = "This class represents the definition of an optional value that can be assigned to a tag.")
public class MTTagValueDef extends MTNode {

    private MTNativeType.DataType dataType;

    public MTTagValueDef(EntityLanguageParser.DomainTaggingTagValueContext ctx, MTNativeType.DataType dataType) {
        super(ctx);
        this.dataType = dataType;
    }

    @ModelMethod(description = "Returns the data type expected for a value set on a tag.")
    public MTNativeType.DataType getDataType() {
        return dataType;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }
}

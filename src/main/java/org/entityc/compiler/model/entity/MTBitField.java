/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.entity;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.doc.annotation.ModelMethod;
import org.entityc.compiler.doc.annotation.ModelMethodCategory;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.visitor.MTVisitor;

@ModelClass(type = ModelClassType.ENTITY, description = "Represents a bit field of an attribute.")
public class MTBitField extends MTNode implements MTTemplateSupport {

    private final int     width;
    private final String  name;
    private       int     low;
    private       boolean unused;

    public MTBitField(ParserRuleContext ctx, int width, String name) {
        super(ctx);
        this.name  = name;
        this.width = width;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE, description = "The low bit number of the bit field.")
    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE, description = "The number of bits wide of the bit field.")
    public int getWidth() {
        return width;
    }

    public String getName() {
        return name;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE, description = "The high bit number of the bit field.")
    public int getHigh() {
        return low + width - 1;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE, description = "Indicates this bit field is declared as not used.")
    public boolean isUnused() {
        return unused;
    }

    public void setUnused(boolean unused) {
        this.unused = unused;
    }
}

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
import org.entityc.compiler.model.MTModule;
import org.entityc.compiler.model.domain.MTNamed;
import org.entityc.compiler.model.visitor.MTVisitor;

import java.util.ArrayList;
import java.util.List;

@ModelClass(type = ModelClassType.ENTITY,
    description = "Represents a type definition useful in dealing with bit fields.")
public class MTTypedef extends MTType implements MTNamed, MTTemplateSupport {

    private final MTModule         module;
    private final int              bitWidth;
    private final String           name;
    private final List<MTBitField> bitFields = new ArrayList<>();
    private       int              lastLowBit;

    public MTTypedef(ParserRuleContext ctx, MTModule module, int bitWidth, String name) {
        super(ctx);
        this.module     = module;
        this.bitWidth   = bitWidth;
        this.name       = name;
        this.lastLowBit = 0;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @ModelMethod(category = ModelMethodCategory.TYPEDEF,
        description = "Returns the bit width of this typedef.")
    public int getBitWidth() {
        return bitWidth;
    }

    @ModelMethod(category = ModelMethodCategory.TYPEDEF,
        description = "Returns the name of this typedef.")
    @Override
    public String getName() {
        return name;
    }

    public void addBitField(MTBitField bitField) {
        bitField.setLow(lastLowBit);
        lastLowBit += bitField.getWidth();

        bitFields.add(bitField);
    }

    @ModelMethod(category = ModelMethodCategory.TYPEDEF,
        description = "Returns the bit fields declared in this typedef.")
    public List<MTBitField> getBitFields() {
        return bitFields;
    }

    @ModelMethod(category = ModelMethodCategory.TYPEDEF,
        description = "Returns the module in which this typedef was declared.")
    public MTModule getModule() {
        return module;
    }
}

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
import org.entityc.compiler.doc.annotation.ModelMethodParameter;
import org.entityc.compiler.model.MTModule;
import org.entityc.compiler.model.domain.MTNamed;
import org.entityc.compiler.model.visitor.MTVisitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ModelClass(type = ModelClassType.ENTITY, description = "Represents an enum in your model.")
public class MTEnum extends MTType implements MTNamed, MTTemplateSupport {

    /**
     * Template Support
     */
    public static final String           MTVariableEnum       = "enum";
    public static final String           MTVariableEnumExists = "enum#exists";
    private final       MTModule         module;
    private final       String           name;
    private final       List<MTEnumItem> items                = new ArrayList<>();
    private             MTEntity         parentEntity;
    private             boolean          extern;

    public MTEnum(ParserRuleContext ctx, MTModule module, String name) {
        super(ctx);
        this.module = module;
        this.name   = name;
    }

    public MTEnum(ParserRuleContext ctx, MTModule module, MTEntity entity, String name) {
        super(ctx);
        this.parentEntity = entity;
        this.module       = module;
        this.name         = name;
    }

    @ModelMethod(category = ModelMethodCategory.ENUM, description = "Indicates whether this was declared as `extern`.")
    public boolean isExtern() {
        return extern;
    }

    public void setExtern(boolean extern) {
        this.extern = extern;
    }

    @ModelMethod(category = ModelMethodCategory.ENUM, description = "Gets the enum items.")
    public List<MTEnumItem> getItems() {
        return items;
    }

    public int getItemCount() {
        return items.size();
    }

    public MTEnumItem getItem(int index) {
        return items.get(index);
    }

    public MTEnumItem getItemByName(String name) {
        for (MTEnumItem item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public MTEnumItem addItem(ParserRuleContext ctx, String name, int value) {
        MTEnumItem item = new MTEnumItem(ctx, this, name, value);
        items.add(item);
        return item;
    }

    @ModelMethod(category = ModelMethodCategory.ENUM, description = "Indicates if this enum was defined inside an entity.")
    public boolean hasParentEntity() {
        return parentEntity != null;
    }

    public MTEntity getParentEntity() {
        return parentEntity;
    }

    public void accept(MTVisitor visitor) {

    }

    @ModelMethod(category = ModelMethodCategory.ENUM, description = "Gets the name of this enum.")
    @Override
    public String getName() {
        return name;
    }

    @ModelMethod(category = ModelMethodCategory.ENUM, description = "Gets the module in which this enum was defined.")
    public MTModule getModule() {
        return module;
    }

    @ModelMethod(category = ModelMethodCategory.ENUM, description = "Indicates if this enum was defined inside a module.")
    public boolean hasParentModule() {
        return module != null;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
        description = "Indicates if any of the enum's items have been tagged with the specified tag.")
    public boolean hasItemTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        for (MTEnumItem item : items) {
            if (item.hasTag(tag)) {
                return true;
            }
        }
        return false;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
        description = "Returns any of the enum items tagged with the specified tag.")
    public Collection<MTEnumItem> itemsTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        List<MTEnumItem> taggedItems = new ArrayList<>();
        for (MTEnumItem item : items) {
            if (item.hasTag(tag)) {
                taggedItems.add(item);
            }
        }
        return taggedItems;
    }
}

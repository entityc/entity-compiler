/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.tagging;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.doc.annotation.ModelMethod;
import org.entityc.compiler.doc.annotation.ModelMethodCategory;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.domain.MTNamed;
import org.entityc.compiler.model.visitor.MTVisitor;

@ModelClass(
        type = ModelClassType.TAGGING,
        description = "This class represents the definition of a tag. This is mostly to allow documentation of tags that are used in "
                      + "templates and placed on entities and attributes (including domain entities and domain "
                      + "attributes).")
public class MTTagDef extends MTNode implements MTNamed {

    private final String        tag;
    private       MTTagContext  tagContext;
    private       MTTagValueDef valueDef;
    private       boolean       startsWith;

    public MTTagDef(ParserRuleContext ctx, String tag, boolean startsWith) {
        super(ctx);
        this.tag        = tag;
        this.startsWith = startsWith;
        this.valueDef   = null; // assume the tag can have no value
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
            description = "Indicates whether this tag is one in which the developer would use as a tag prefix, that is "
                          + "all tags that start with this string value are associated with this tag.")
    public boolean isStartsWith() {
        return startsWith;
    }

    @ModelMethod(category = ModelMethodCategory.TYPE,
            description = "Returns an object that describes the data type of a value assigned to this tag.")
    public MTTagValueDef getValueDef() {
        return valueDef;
    }

    public void setValueDef(MTTagValueDef valueDef) {
        this.valueDef = valueDef;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
            description = "Returns an object that describes the context when this tag would be used.")
    public MTTagContext getTagContext() {
        return tagContext;
    }

    public void setTagContext(MTTagContext tagContext) {
        this.tagContext = tagContext;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING, description = "Returns the tag as a string.")
    public String getTag() {
        return tag;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING, description = "Indicates whether this tag is assigned a value.")
    public boolean hasValue() {
        return valueDef != null;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
            description = "Returns the tag name (this is the same as the `tag` property).")
    @Override
    public String getName() {
        return tag;
    }
}

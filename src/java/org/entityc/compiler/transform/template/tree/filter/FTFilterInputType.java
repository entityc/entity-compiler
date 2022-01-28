/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.filter;

import org.entityc.compiler.transform.template.tree.FTNode;
import org.entityc.compiler.transform.template.tree.FTVisitor;

public class FTFilterInputType extends FTNode {

    private final Class   typeClass;
    private final String  name;
    private final boolean collection;
    private       String  description;

    public FTFilterInputType(Class typeClass, boolean collection) {
        super(null);
        this.typeClass  = typeClass;
        this.name       = typeClass.getSimpleName();
        this.collection = collection;
    }

    public FTFilterInputType(Class typeClass, boolean collection, String description) {
        super(null);
        this.typeClass   = typeClass;
        this.name        = typeClass.getSimpleName();
        this.collection  = collection;
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public boolean hasDescription() {
        return description != null && !description.isEmpty();
    }

    public Class getTypeClass() {
        return typeClass;
    }

    public String getName() {
        return name;
    }

    public boolean isCollection() {
        return collection;
    }

    @Override
    public void accept(FTVisitor visitor) {

    }

    @Override
    public int getTemplateLexerSymbol() {
        return 0;
    }
}

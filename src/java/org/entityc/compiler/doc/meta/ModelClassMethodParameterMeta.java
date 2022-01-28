/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.doc.meta;

import org.entityc.compiler.model.foundation.MFObject;
import org.entityc.compiler.util.ECStringUtil;

import java.lang.reflect.Type;

public class ModelClassMethodParameterMeta extends MFObject {

    private ModelClassMethodMeta modelClassMethodMeta;
    private Type                 type;
    private String               name;
    private String               description;

    public ModelClassMethodParameterMeta(ModelClassMethodMeta modelClassMethodMeta, Type type, String name, String description) {
        this.modelClassMethodMeta = modelClassMethodMeta;
        this.type                 = type;
        this.name                 = name;
        this.description          = description;
    }

    public Type getType() {
        return type;
    }

    public String typeName() {
        return ECStringUtil.GetClassNameFromFullName(type.getTypeName());
    }

    public boolean hasDescription() {
        return description != null && !description.equals("");
    }
    public String getDescription() {
        return description;
    }

    public ModelClassMethodMeta getClassMethodMeta() {
        return modelClassMethodMeta;
    }

    public String getName() {
        return name;
    }
}

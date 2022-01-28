/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.doc.meta;

import org.entityc.compiler.doc.annotation.ModelMethodCategory;
import org.entityc.compiler.model.foundation.MFObject;
import org.entityc.compiler.util.ECStringUtil;

import java.util.ArrayList;
import java.util.List;

public class ModelClassMethodMeta extends MFObject implements Comparable<ModelClassMethodMeta>{

    private ModelMethodCategory                 category;
    private ModelClassMeta                      classMeta;
    private boolean                             property;
    private String                              name;
    private String                              description;
    private List<ModelClassMethodParameterMeta> parameters = new ArrayList<>();
    private String                              returnType;

    public ModelClassMethodMeta(ModelClassMeta modelClassMeta, ModelMethodCategory category, boolean property, String name, String description, String returnType) {
        this.classMeta   = modelClassMeta;
        this.category    = category;
        this.property    = property;
        this.name        = name;
        this.description = description;
        this.returnType  = returnType;
    }

    public ModelMethodCategory getCategory() {
        return category;
    }

    public String getReturnType() {
        return returnType;
    }

    public List<ModelClassMethodParameterMeta> getParameters() {
        return parameters;
    }

    public String getDescription() {
        return description;
    }

    public ModelClassMeta getClassMeta() {
        return classMeta;
    }

    public String getName() {
        return name;
    }

    public boolean isProperty() {
        return property;
    }

    public void addParameter(ModelClassMethodParameterMeta parameterMeta) {
        parameters.add(parameterMeta);
    }

    public String signature(String delimiter, Boolean includeArgNames) {
        StringBuilder builder      = new StringBuilder();
        boolean       addDelimiter = false;
        for (ModelClassMethodParameterMeta parameterMeta : parameters) {
            if (addDelimiter) {
                builder.append(delimiter);
            }

            String shortName = ECStringUtil.GetClassNameFromFullName(parameterMeta.getType().getTypeName());
            builder.append(shortName);
            if (includeArgNames) {
                builder.append(" ");
                builder.append(parameterMeta.getName());
            }
            addDelimiter = true;
        }
        return builder.toString();
    }
    @Override
    public int compareTo(ModelClassMethodMeta otherObject) {
        return this.name.compareTo(otherObject.name);
    }
}

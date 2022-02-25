/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.doc.meta;

import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.doc.annotation.ModelMethodCategory;
import org.entityc.compiler.model.foundation.MFArray;
import org.entityc.compiler.model.foundation.MFObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModelClassMeta extends MFObject {

    private String                                               name;
    private String                                               description;
    private ModelClassType                                       type;
    private Map<String, ModelClassMethodMeta>                    methodsByName     = new HashMap<>();
    private Map<ModelMethodCategory, List<ModelClassMethodMeta>> methodsByCategory = new HashMap<>();

    public ModelClassMeta(ModelClassType type, String name, String description) {
        this.type        = type;
        this.name        = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ModelClassType getType() {
        return type;
    }

    public void addMethod(ModelClassMethodMeta method) {
        methodsByName.put(method.getName(), method);
        List<ModelClassMethodMeta> methodMetas;
        if (methodsByCategory.containsKey(method.getCategory())) {
            methodMetas = methodsByCategory.get(method.getCategory());
        } else {
            methodMetas = new ArrayList<>();
        }
        methodMetas.add(method);
        methodsByCategory.put(method.getCategory(), methodMetas);
    }

    public ModelClassMethodMeta getMethodMeta(String name) {
        return methodsByName.get(name);
    }

    public MFArray methodMetasByCategory(ModelMethodCategory category) {
        if (!methodsByCategory.containsKey(category)) {
            return new MFArray();
        }
        ArrayList<ModelClassMethodMeta> methods = new ArrayList<>(methodsByCategory.get(category));
        Collections.sort(methods);
        return new MFArray(methods);
    }

    public List<String> getMethodNames() {
        return methodsByName.keySet().stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        }).collect(Collectors.toList());
    }

    public int methodCount() {
        return methodsByName.size();
    }
    public int categoryCount() {
        return methodsByCategory.keySet().size();
    }
}

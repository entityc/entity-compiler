/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.doc.annotation;

import java.util.ArrayList;
import java.util.List;

public enum ModelClassType {
    CONFIGURATION("config",
                  "Configuration",
                  "This represents a grouping of classes that deal with how an "
                  + "application or synthesis of an application is configured."),
    ENTITY("entity",
           "Entity",
           "Model classes of this type are directly related to the entities in your model."),
    DOMAIN("domain",
           "Domain",
           "Model classes that are domain specific are of this type."),
    INTERFACE("interop",
              "Interface",
              "Model classes used to describe interfaces are of this type."),
    EXPRESSION("expression",
               "Expression",
               "Model classes used to describe expressions are of this type."),
    LANGUAGE("language",
             "Language",
             "Model classes used to describe languages are of this type."),
    FOUNDATION("foundation",
               "Foundation",
               "The model classes of this type are intended to provide support for foundation type "
               + "functions. Currently only array/set classes are supported."),
    TAGGING("tagging",
            "Tagging",
            "Model classes used to describe tags are of this type."),
    TEMPLATE("template",
            "Template",
            "Classes used by the template engine to structure and execute the code."),
    ;

    private String packageName;
    private String title;
    private String description;

    ModelClassType(String packageName, String title, String description) {
        this.packageName = packageName;
        this.title       = title;
        this.description = description;
    }

    public static List<ModelClassType> getTypes() {
        List<ModelClassType> types = new ArrayList<>();
        for (ModelClassType t : values()) {
            types.add(t);
        }
        return types;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}

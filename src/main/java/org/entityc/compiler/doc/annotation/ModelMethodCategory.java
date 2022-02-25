/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.doc.annotation;

import java.util.ArrayList;
import java.util.List;

public enum ModelMethodCategory {
    DOMAIN("Domain", "These methods relate to a domain."),
    MODULE("Module", "These methods relate to a module."),
    ENTITY("Entity", "These methods relate to an entity."),
    ENTITY_TEMPLATE("Entity Template", "These methods relate to entity templates."),
    PRIMARY_KEY("Primary Key", "These methods relate to a primary key."),
    ATTRIBUTE("Attribute", "These methods relate to attributes."),
    RELATIONSHIP("Relationship", "These methods relate to relationships."),
    NAMESPACE("Namespace", "These methods relate to a namespace."),
    TYPE("Type", "These methods relate to data types."),
    VIEW("View", "These methods relate to views."),
    ENUM("Enum", "These methods relate to enums."),
    FUNCTION("Function", "These methods relate to functions."),
    TYPEDEF("Typedef", "These methods relate to typedefs."),
    LANGUAGE("Language", "These methods relate to language definitions."),
    TAGGING("Tagging", "These methods relate to the tagging."),
    CONFIGURATION("Configuration", "These methods relate to a part of application configuration."),
    INTERFACE("Interface", "These methods relate to an interface."),
    TEMPLATE("Template", "These methods relate to a template."),
    PUBLISHER("Publisher", "These methods relate to a publisher."),
    AUTHOR("Author", "These methods relate to an author."),
    OUTLET("Outlet", "These methods relate to an outlet."),
    OTHER("Other", "These methods don't really have a category.")
    ;
    private String title;
    private String description;

    ModelMethodCategory(String title, String description) {
        this.title       = title;
        this.description = description;
    }

    public static List<ModelMethodCategory> getCategories() {
        List<ModelMethodCategory> categories = new ArrayList<>();
        for (ModelMethodCategory c : values()) {
            categories.add(c);
        }
        return categories;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}

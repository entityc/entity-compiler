/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.doc.annotation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public enum TemplateInstructionCategory {
    CONTEXT("Context", "Instructions that define the context in which a template runs (such as "
                       + "language and domain)."),
    ASSIGNMENT("Assignment", "Instructions that result in a variable being assigned a value."),
    FILE_IO("File I/O", "Instructions that perform some type of file operation."),
    MOVEMENT("Movement", "These are instructions that help you to move code/text up in the "
                         + "template output."),
    CONTROL_FLOW("Control Flow", "Instructions that alter the flow of execution such as loop and "
                                 + "conditional instructions."),
    FUNCTION("Function", "Instructions related to defining and calling functions."),
    PUBLISHING("Publishing", "Instructions related to the publishing feature that allows you "
                             + "to establish publishers with outlets then else author code to those outlets."),
    MISC("Miscellaneous", "These are instructions that didn't fit into any particular category."),
    ;

    private String title;
    private String description;

    TemplateInstructionCategory(String title, String description) {
        this.title       = title;
        this.description = description;
    }

    public static List<TemplateInstructionCategory> getCategories() {
        List<TemplateInstructionCategory> categories = new ArrayList<>();
        for (TemplateInstructionCategory c : values()) {
            categories.add(c);
        }
        categories.sort(new Comparator<TemplateInstructionCategory>() {
            @Override
            public int compare(TemplateInstructionCategory o1, TemplateInstructionCategory o2) {
                return o1.title.compareTo(o2.title);
            }
        });
        return categories;
    }

    public String getName() {
        return name();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}

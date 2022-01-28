/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.txt file in the project root.
 */

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.txt file in the project root.
 */

package org.entityc.compiler.doc.meta;

import org.entityc.compiler.model.foundation.MFObject;

public class TemplateInstructionArgumentMeta extends MFObject {

    private Class   type;
    private String  name;
    private String  description;
    private boolean optional;
    private boolean keyword;

    public TemplateInstructionArgumentMeta(Class type, String name, String description, boolean optional, boolean keyword) {
        super();
        this.optional    = optional;
        this.keyword     = keyword;
        this.type        = type;
        this.name        = name;
        this.description = description;
    }

    public boolean isOptional() {
        return optional;
    }

    public boolean isKeyword() {
        return keyword;
    }

    public Class getType() {
        return type;
    }

    public String getTypeName() {
        return type.getSimpleName();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

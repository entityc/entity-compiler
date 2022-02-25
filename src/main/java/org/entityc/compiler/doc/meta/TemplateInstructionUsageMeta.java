/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.txt file in the project root.
 */

package org.entityc.compiler.doc.meta;

import org.entityc.compiler.model.foundation.MFObject;

public class TemplateInstructionUsageMeta extends MFObject {

    private final String  title;
    private final String  usage;
    private final String  description;
    private boolean fullUsage = false;

    public TemplateInstructionUsageMeta(String usage) {
        super();
        this.title       = null;
        this.usage       = usage;
        this.description = null;
    }

    public TemplateInstructionUsageMeta(String title, String usage, String description) {
        super();
        this.title       = title;
        this.usage       = usage;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getUsage() {
        if (isFullUsage()) {
            return usage;
        }
        return ("`$[" + usage.substring(1) + "`]`").replaceAll("``", "");
    }

    public boolean isFullUsage() {
        return fullUsage;
    }

    public void setFullUsage(boolean fullUsage) {
        this.fullUsage = fullUsage;
    }

    public String getDirectUsage() {
        return usage;
    }

    public String getDescription() {
        return description;
    }
}

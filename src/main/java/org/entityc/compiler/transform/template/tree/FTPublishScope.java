/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

public enum FTPublishScope {
    Author("author"),
    Publisher("publisher");

    static {
        GetDefault().isDefault = true;
    }

    private final String  name;
    private       boolean isDefault;

    FTPublishScope(String name) {
        this.name = name;
    }

    static public FTPublishScope getByName(String name) {
        for (FTPublishScope pp : values()) {
            if (pp.name.equals(name)) {
                return pp;
            }
        }
        return null;
    }

    static public FTPublishScope GetDefault() {
        return Author;
    }

    public String getName() {
        return name;
    }

    public boolean isDefault() {
        return isDefault;
    }
}

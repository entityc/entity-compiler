/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.config;

public enum MTRepositoryType {
    LOCAL("local"),
    GITHUB("github"),
    ;

    private final String name;

    MTRepositoryType(String name) {
        this.name = name;
    }

    public static MTRepositoryType FromName(String name) {
        for (MTRepositoryType t : values()) {
            if (t.name.equals(name)) {
                return t;
            }
        }
        return null;
    }
}

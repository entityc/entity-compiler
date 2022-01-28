/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

public class CSStringConstant extends CSStatement {

    private final String value;

    public CSStringConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

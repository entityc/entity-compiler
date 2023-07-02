/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.domain;

import org.entityc.compiler.util.ECStringUtil;

public enum MTNamingMethod {

    STANDARD("standard", ""),
    UNDERSCORE("underscore", "_"),
    UNDERSCORE_LOWERCASE("underscoreLowercase", "_"),
    UNDERSCORE_UPPERCASE("underscoreUppercase", "_"),
    LOWERCASE("lowercase", ""),
    UPPERCASE("uppercase", ""),
    CAPITALIZE("capitalize", ""),
    DASHES_LOWERCASE("dashesLowercase", "-"),
    DASHES_UPPERCASE("dashesUppercase", "-"),
    PARENT_PREFIX("parentPrefix", ""),
    ;

    private final String name;
    private final String delimiter;

    MTNamingMethod(String name, String delimiter) {
        this.name      = name;
        this.delimiter = delimiter;
    }

    public static MTNamingMethod fromName(String name) {
        for (MTNamingMethod nm : values()) {
            if (nm.name.equals(name)) {
                return nm;
            }
        }
        return null;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String rename(String name) {
        switch (this) {
            case STANDARD:
                return name;
            case UNDERSCORE:
                return ECStringUtil.CamelToSeparator(name,delimiter);
            case UNDERSCORE_LOWERCASE:
            case DASHES_LOWERCASE:
                return ECStringUtil.CamelToSeparator(name, delimiter).toLowerCase();
            case UNDERSCORE_UPPERCASE:
            case DASHES_UPPERCASE:
                return ECStringUtil.CamelToSeparator(name, delimiter).toUpperCase();
            case LOWERCASE:
                return name.toLowerCase();
            case UPPERCASE:
                return name.toUpperCase();
            case CAPITALIZE:
                return ECStringUtil.Capitalize(name);
        }
        return name;
    }
}

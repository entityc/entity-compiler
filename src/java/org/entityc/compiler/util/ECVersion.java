/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.util;

public class ECVersion {

    private final Integer major;
    private final Integer minor;
    private final Integer fix;

    public ECVersion(Integer major, Integer minor, Integer fix) {
        this.major = major;
        this.minor = minor;
        this.fix = fix;
    }

    public String toString() {
        if (major != null) {
            if (minor != null) {
                if (fix != null) {
                    return major + "." + minor + "." + fix;
                } else {
                    return major + "." + minor;
                }
            } else {
                return "" + major;
            }
        }
        return "0.0.0";
    }
}

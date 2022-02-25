/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.domain;

import static org.entityc.compiler.model.domain.MTNamingMethod.STANDARD;

public class MTNaming {

    private MTNamingMethod method          = STANDARD;
    private String         prefix;
    private String         suffix;
    private boolean        useUnitInNaming = true;

    public MTNaming(MTNamingMethod method, String prefix, String suffix) {
        if (method != null) {
            this.method = method;
        }
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public boolean isUseUnitInNaming() {
        return useUnitInNaming;
    }

    public void setUseUnitInNaming(boolean useUnitInNaming) {
        this.useUnitInNaming = useUnitInNaming;
    }

    public MTNamingMethod getMethod() {
        return method;
    }

    public void setMethod(MTNamingMethod method) {
        if (method != null) {
            this.method = method;
        }
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String rename(String name) {
        return rename(null, name);
    }

    public String rename(String parentName, String name) {
        String renamedName = method.rename(name);
        if (prefix != null) {
            renamedName = prefix + renamedName;
        }
        if (suffix != null) {
            renamedName = renamedName + suffix;
        }
        return renamedName;
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.entity;

import java.util.Stack;

public class MTSecondaryAttribute extends MTAttribute {

    private final String             fullName;
    private final Stack<MTAttribute> path;

    public MTSecondaryAttribute(String fullName, Stack<MTAttribute> path) {
        super(fullName, path.lastElement());
        this.fullName = fullName;
        this.path     = path;
    }

    public String getFullName() {
        return fullName;
    }

    public Stack<MTAttribute> getPath() {
        return path;
    }

    public MTAttribute getPrimaryAttribute() {
        if (path.size() > 0) {
            return path.firstElement();
        }
        return null;
    }

    public MTAttribute getLeafAttribute() {
        if (path.size() > 1) {
            return path.lastElement();
        }
        return null;
    }

    @Override
    public boolean isSecondary() {
        return true;
    }
}

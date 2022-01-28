/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.generator;

import org.entityc.compiler.model.MTRoot;

public abstract class Generator {

    protected String name;

    abstract void generate(MTRoot model);

    public String getName() {
        return name;
    }
}

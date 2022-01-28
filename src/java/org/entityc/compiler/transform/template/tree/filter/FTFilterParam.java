/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.filter;

import org.entityc.compiler.transform.template.tree.FTNode;
import org.entityc.compiler.transform.template.tree.FTVisitor;

public class FTFilterParam extends FTNode {

    private final String name;
    private final String description;

    public FTFilterParam(String name, String description) {
        super(null);
        this.name        = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(FTVisitor visitor) {

    }

    @Override
    public int getTemplateLexerSymbol() {
        return 0;
    }

    public String getDescription() {
        return description;
    }
}

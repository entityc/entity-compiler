/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

import java.util.ArrayList;
import java.util.List;

public class CSSwitch extends CSStatement {

    private final CSVariable   variable;
    private final List<CSCase> cases = new ArrayList<>();

    public CSSwitch(CSVariable variable) {
        this.variable = variable;
    }

    @Override
    public void accept(CSVisitor visitor) {
        for (CSCase csCase : cases) {
            visitor.visit(csCase);
        }
    }

    public void addCase(CSCase csCase) {
        cases.add(csCase);
    }

    public CSVariable getVariable() {
        return variable;
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

public class CSMethodArgument extends CSAnnotatableNode {

    private CSVariable  argumentVariable;
    private CSStatement statement;

    public CSMethodArgument(CSType type, String name) {
        this.argumentVariable = new CSVariable(type, name);
    }

    public CSMethodArgument(CSVariable argumentVariable) {
        this.argumentVariable = argumentVariable;
    }

    public CSMethodArgument(CSStatement statement) {
        this.statement = statement;
    }

    public void accept(CSVisitor visitor) {
    }

    public CSVariable getArgumentVariable() {
        return argumentVariable;
    }

    public CSStatement getStatement() {
        return statement;
    }
}

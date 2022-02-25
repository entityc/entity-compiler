/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

public class CSReturnStatement extends CSStatement {

    private CSStatement        statement;
    private CSMethodInvocation methodInvocation;

    public CSReturnStatement(CSStatement statement) {
        this.statement = statement;
    }

    public CSReturnStatement(CSMethodInvocation methodInvocation) {
        this.methodInvocation = methodInvocation;
    }

    public static CSReturnStatement ReturnStatementWithEnumItem(CSEnumItem enumItem) {
        return new CSReturnStatement(new CSStatement(enumItem.getName()));
    }

    public CSMethodInvocation getMethodInvocation() {
        return methodInvocation;
    }

    public CSStatement getStatement() {
        return statement;
    }
}

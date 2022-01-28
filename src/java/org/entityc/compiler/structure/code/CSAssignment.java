/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

public class CSAssignment extends CSStatement {

    private CSVariable  leftHandVariable;
    private CSStatement rightHandStatement;

    public CSAssignment(CSVariable left, CSStatement right) {
        this.leftHandVariable = left;
        this.rightHandStatement = right;
    }

    public CSVariable getLeftHandVariable() {
        return leftHandVariable;
    }

    public void setLeftHandVariable(CSVariable leftHandVariable) {
        this.leftHandVariable = leftHandVariable;
    }

    public CSStatement getRightHandStatement() {
        return rightHandStatement;
    }

    public void setRightHandStatement(CSExpression rightHandExpression) {
        this.rightHandStatement = rightHandExpression;
    }

    public void accept(CSVisitor visitor) {
    }
}

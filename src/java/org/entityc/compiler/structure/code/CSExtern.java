/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

public class CSExtern extends CSNode {

    private final String className;
    private final String variableName;

    public CSExtern(String className, String variableName) {
        this.className = className;
        this.variableName = variableName;
    }

    @Override
    public void accept(CSVisitor visitor) {

    }

    @Override
    public boolean equals(Object otherExtern) {
        if (!(otherExtern instanceof CSExtern)) {
            return false;
        }
        boolean bothVariableNamesNull    = variableName == null && ((CSExtern) otherExtern).getVariableName() == null;
        boolean bothVariableNamesNotNull = variableName != null && ((CSExtern) otherExtern).getVariableName() != null;
        if (className == null || ((CSExtern) otherExtern).getClassName() == null) {
            return false;
        }

        if (className.equals(((CSExtern) otherExtern).getClassName())) {
            if (bothVariableNamesNull) {
                return true;
            }
            if (bothVariableNamesNotNull) {
                return variableName.equals(((CSExtern) otherExtern).getVariableName());
            }
        }
        return false;
    }

    public String getVariableName() {
        return variableName;
    }

    public String getClassName() {
        return className;
    }
}

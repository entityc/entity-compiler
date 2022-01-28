/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

public class CSOperator extends CSNode {

    private final Operation operation;

    public CSOperator(Operation operation) {
        this.operation = operation;
    }

    public void accept(CSVisitor visitor) {
    }

    public Operation getOperation() {
        return operation;
    }

    public enum Operation {
        NEW("new"),
        ADD("+"),
        SUB("-"),
        MULT("*"),
        DIV("/"),
        SHIFT_LEFT("<<"),
        SHIFT_RIGHT(">>"),
        BITWISE_AND("&"),
        BITWISE_OR("|"),
        BITWISE_INV("~"),
        IS_EQUAL("=="),
        NOT_EQUAL("!="),
        LOGICAL_AND("&&"),
        LOGICAL_OR("||"),
        SELECT_CONTROL("?"),
        SELECT_MUX(":");

        private final String symbol;

        Operation(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }
    }
}

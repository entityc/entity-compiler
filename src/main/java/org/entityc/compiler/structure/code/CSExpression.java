/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

import static org.entityc.compiler.structure.code.CSOperator.Operation.SELECT_CONTROL;
import static org.entityc.compiler.structure.code.CSOperator.Operation.SELECT_MUX;

public class CSExpression extends CSStatement {

    private final CSExpressionType type;
    private       CSStatement      leftExpression;
    private       CSVariable       leftVariable;
    private       CSOperator       operator;
    private       CSOperator       secondaryOperator;
    private       CSStatement      middleExpression;
    private       CSStatement      rightExpression;

    public CSExpression(CSVariable variable) {
        this.leftVariable = variable;
        this.type = CSExpressionType.VARIABLE;
    }

    public CSExpression(CSOperator.Operation operation, CSExpression rightExpression) {
        this.rightExpression = rightExpression;
        this.operator = new CSOperator(operation);
        this.type = CSExpressionType.UNARY;
    }

    public CSExpression(CSVariable leftVariable, CSOperator.Operation operation, CSVariable rightVariable) {
        this.leftVariable = leftVariable;
        this.operator = new CSOperator(operation);
        this.rightExpression = new CSExpression(rightVariable);
        this.type = CSExpressionType.BINARY;
    }

    public CSExpression(CSVariable leftVariable, CSOperator.Operation operation, CSExpression rightExpression) {
        this.leftVariable = leftVariable;
        this.operator = new CSOperator(operation);
        this.rightExpression = rightExpression;
        this.type = CSExpressionType.BINARY;
    }

    public CSExpression(CSExpression leftExpression, CSOperator.Operation operation, CSExpression rightExpression) {
        this.leftExpression = leftExpression;
        this.operator = new CSOperator(operation);
        this.rightExpression = rightExpression;
        this.type = CSExpressionType.BINARY;
    }

    public CSExpression(CSExpression leftExpression, CSOperator.Operation leftOperation, CSExpression middleExpression, CSOperator.Operation rightOperation, CSExpression rightExpression) {
        this.leftExpression = leftExpression;
        this.operator = new CSOperator(leftOperation);
        this.middleExpression = middleExpression;
        this.secondaryOperator = new CSOperator(rightOperation);
        this.rightExpression = rightExpression;
        this.type = CSExpressionType.TERNARY;
    }

    public CSExpression(int constant) {
        this.leftVariable = new CSVariable(new CSType(CSType.CSNativeType.INT32), "" + constant);
        this.type = CSExpressionType.VARIABLE;
    }

    public CSExpression(long constant) {
        this.leftVariable = new CSVariable(new CSType(CSType.CSNativeType.INT64), "" + constant);
        this.type = CSExpressionType.VARIABLE;
    }

    public static CSExpression BooleanToInt(CSVariable variable) {
        return new CSExpression(new CSExpression(variable), SELECT_CONTROL, new CSExpression(new CSVariable(new CSType(CSType.CSNativeType.INT32), "1")), SELECT_MUX, new CSExpression(new CSVariable(new CSType(CSType.CSNativeType.INT32), "0")));
    }

    public CSExpressionType getType() {
        return type;
    }

    public CSVariable getLeftVariable() {
        return leftVariable;
    }

    public void setLeftVariable(CSVariable leftVariable) {
        this.leftVariable = leftVariable;
    }

    public CSOperator getOperator() {
        return operator;
    }

    public void setOperator(CSOperator operator) {
        this.operator = operator;
    }

    public CSStatement getRightExpression() {
        return rightExpression;
    }

    public void setRightExpression(CSExpression rightExpression) {
        this.rightExpression = rightExpression;
    }

    public void accept(CSVisitor visitor) {
    }

    @Override
    public String toString() {
        if (type == CSExpressionType.VARIABLE) {
            return leftVariable.toString();
        } else if (type == CSExpressionType.UNARY) {
            return "(" + operator.getOperation().getSymbol() + rightExpression.toString() + ")";
        } else if (type == CSExpressionType.BINARY) {
            if (leftVariable != null && operator != null && rightExpression != null) {
                return "(" + leftVariable + " " + operator.getOperation().getSymbol() + " " + rightExpression.toString() + ")";
            }

            if (leftExpression != null && operator != null && rightExpression != null) {

                return "(" + leftExpression + " " + operator.getOperation().getSymbol() + " " + rightExpression.toString() + ")";
            }
        } else if (type == CSExpressionType.TERNARY) {
            return "(" + leftExpression.toString() + " " + operator.getOperation().getSymbol() + " " + middleExpression.toString() + " " + secondaryOperator.getOperation().getSymbol() + " " + rightExpression.toString() + ")";
        }

        return "INVALID EXPRESSION";
    }

    public enum CSExpressionType {
        VARIABLE,
        UNARY,
        BINARY,
        TERNARY
    }
}

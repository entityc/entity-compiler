/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.expression;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.doc.annotation.ModelMethod;
import org.entityc.compiler.doc.annotation.ModelMethodParameter;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.domain.MTDEntity;
import org.entityc.compiler.model.language.MTLanguage;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.List;

@ModelClass(type = ModelClassType.EXPRESSION,
    description = "Represents an operation inside an expression. Each type of operation (add, subtract, not, etc.) can "
                  + "have its own number of inputs, where each input is an expression. The output of the operation is "
                  + "effectively an object of this class. You can assign the object of this class as the operand of another "
                  + "operation, thus creating a multi-operation expression.")
public class MTOperation extends MTExpression {

    private final Operator           op;
    private final List<MTExpression> operands = new ArrayList<>();

    public MTOperation(ParserRuleContext ctx, Operator op, List<MTExpression> operands) {
        super(ctx);
        this.op = op;
        for (MTExpression expression : operands) {
            if (expression != null) {
                this.operands.add(expression);
            }
            else {
                ECLog.logError(this, "Found null operand to operation: " + op.getSymbol());
            }
        }
    }

    @Override
    public boolean isOperation() {
        return true;
    }

    @Override
    public String mapToLanguage(MTLanguage language, MTDEntity domainEntity, String objectName) {
        String mappedToText = "<<INVALID_OPERATION>>";
        switch (getOp().getOperandCount()) {
            case 1:
                mappedToText = language.getSymbolForOperator(getOp().name()) + getOperand(0).mapToLanguage(language,
                                                                                                           domainEntity,
                                                                                                           null);
                break;
            case 2:
                mappedToText = getOperand(0).mapToLanguage(language, domainEntity, null)
                               + " " + language.getSymbolForOperator(getOp().name()) + " "
                               + getOperand(1).mapToLanguage(language, domainEntity, null);
                break;
            case 3:
                mappedToText = getOperand(0).mapToLanguage(language, domainEntity, null)
                               + " " + language.getSymbolsForOperator(getOp().name()).get(0) + " "
                               + getOperand(1).mapToLanguage(language, domainEntity, null)
                               + " " + language.getSymbolsForOperator(getOp().name()).get(1) + " "
                               + getOperand(2).mapToLanguage(language, domainEntity, null);
                break;
        }
        return mappedToText;
    }

    @ModelMethod(description = "Returns the operator of the operation. This is the actual add, subtract, multiply, etc.")
    public Operator getOp() {
        return op;
    }

    @ModelMethod(description =
        "Returns the operand expression for a specified input of the operation. An operation can have "
        + "up to 3 inputs (you can use the `operandCount` property to know how many this one has).")
    public MTExpression getOperand(
        @ModelMethodParameter(description = "The index of the specific operand (input) of the operation you want to be returned.")
            int i) {
        if (i < operands.size()) {
            return operands.get(i);
        }
        return null;
    }

    @ModelMethod(description = "Returns the number of operands (inputs) for this operation.")
    public int getOperandCount() {
        return operands.size();
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @ModelMethod(description = "Returns a string representation for this operation (for debug purposes).")
    @Override
    public String toString() {
        switch (getOp().getOperandCount()) {
            case 1:
                return op.getSymbol() + getOperand(0).toString();
            case 2:
                return getOperand(0).toString() + " " + op.getSymbol() + " " + getOperand(1).toString();
            case 3:
                return getOperand(0).toString() + " ? " + getOperand(1).toString() + " : " + getOperand(2).toString();
        }
        return "";
    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        boolean needsResolve = false;

        for (MTExpression operand : operands) {
            if (operand != null && operand.resolveReferences(space, pass)) {
                needsResolve = true;
            }
        }
        return needsResolve;
    }

    @ModelMethod(description = "Returns a string representation of the operator of this operation (e.g., `+`, `-`, `*`, etc.).")
    public String getOperatorSymbol() {
        return op.symbol;
    }

    public enum Operator {
        PLUS("+", 2),
        MINUS("-", 2),
        TIMES("*", 2),
        DIVIDE("/", 2),
        MODULO("%", 2),
        LOGICAL_NOT("!", 1),
        LOGICAL_AND("&&", 2),
        LOGICAL_OR("||", 2),
        BITWISE_NOT("~", 1),
        BITWISE_AND("&", 2),
        BITWISE_OR("|", 2),
        BITWISE_XOR("^", 2),
        IS_EQUAL("==", 2),
        NOT_EQUAL("!=", 2),
        GREATER_THAN(">", 2),
        GREATER_THAN_OR_EQUAL(">=", 2),
        LESS_THAN("<", 2),
        LESS_THAN_OR_EQUAL("<=", 2),
        SELECT("?", 3),
        DOT(".", 1);

        private final int    operandCount;
        private final String symbol;

        Operator(String symbol, int operandCount) {
            this.symbol       = symbol;
            this.operandCount = operandCount;
        }

        public static boolean isValidOperator(String symbol) {
            for (Operator operator : values()) {
                if (operator.symbol.equals(symbol)) {
                    return true;
                }
            }
            return false;
        }

        public static Operator getOperatorBySymbol(String symbol) {
            for (Operator operator : values()) {
                if (operator.symbol.equals(symbol)) {
                    return operator;
                }
            }
            return null;
        }

        public int getOperandCount() {
            return operandCount;
        }

        public String getSymbol() {
            return symbol;
        }

        public boolean isUnary() {
            return operandCount == 1;
        }

        public boolean isBinary() {
            return operandCount == 2;
        }

        public boolean isTertiary() {
            return operandCount == 3;
        }
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.expression;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.TemplateOperator;
import org.entityc.compiler.model.expression.MTOperation;
import org.entityc.compiler.model.foundation.MFObject;
import org.entityc.compiler.transform.template.formatter.ConfigurableElement;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.FTVisitor;
import org.entityc.compiler.util.ECLog;
import org.entityc.compiler.util.ECStringUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class FTOperation extends FTExpression {

    private final Operator op;
    List<FTExpression> operands = new ArrayList<>();

    public FTOperation(ParserRuleContext ctx, Operator op, List<FTExpression> operands) {
        super(ctx);
        this.op = op;
        for (FTExpression expression : operands) {
            if (expression != null) {
                this.operands.add(expression);
            } else {
                ECLog.logError(this, "Found null operand to operation: " + op.getSymbol());
            }
        }
    }

    public Operator getOp() {
        return op;
    }

    public int getOperandCount() {
        return operands.size();
    }

    public FTExpression getOperand(int i) {
        if (i < operands.size()) {
            return operands.get(i);
        }
        return null;
    }

    @Override
    public Object getValue(FTTransformSession session) {
        Object value = null;
        Object arg1 = operands.size() > 0 ?
                      operands.get(0).getValue(session) :
                      null;

        // Process inputs first (automatic casting where supported)
        switch (op) {
            case PLUS:
            case MINUS:
            case TIMES:
            case DIVIDE:
            case MODULO: {
                if (arg1 instanceof Boolean) {
                    arg1 = Long.valueOf(((Boolean) arg1) ?
                                        1 :
                                        0);
                }
                Object arg2 = operands.size() > 1 && op != Operator.DOT ?
                              operands.get(1).getValue(session) :
                              null;
                if (arg2 != null && arg2 instanceof Boolean) {
                    arg2 = Long.valueOf(((Boolean) arg2) ?
                                        1 :
                                        0);
                }
            }
            break;
            case LOGICAL_AND:
            case LOGICAL_NOT:
            case LOGICAL_OR: {
                if (arg1 instanceof Long) {
                    arg1 = Boolean.valueOf(((Long) arg1) != 0);
                } else if (arg1 instanceof Double) {
                    arg1 = Boolean.valueOf(((Double) arg1) != 0);
                } else if (!(arg1 instanceof Boolean)) {
                    ECLog.logFatal(this, "Operand 1 must be a number or boolean for operation: " + op.getSymbol()
                                         + " with arg: " + arg1);
                }
//                if (arg2 != null) {
//                    if (arg2 instanceof Long) {
//                        arg2 = Boolean.valueOf(((Long) arg2) != 0);
//                    } else if (arg2 instanceof Double) {
//                        arg2 = Boolean.valueOf(((Double) arg2) != 0);
//                    } else if (!(arg2 instanceof Boolean)) {
//                        ECLog.logFatal(this, "Operand 2 must be a number or boolean for operation: " + op.getSymbol() + " with arg: " + arg2);
//                    }
//                }
            }
            break;
            case GREATER_THAN:
            case GREATER_THAN_OR_EQUAL:
            case LESS_THAN:
            case LESS_THAN_OR_EQUAL:
            case IS_EQUAL:
            case NOT_EQUAL: {
                if (arg1 instanceof Long) {
                    arg1 = Double.valueOf(((Long) arg1).doubleValue());
                }
                Object arg2 = operands.size() > 1 && op != Operator.DOT ?
                              operands.get(1).getValue(session) :
                              null;
                if (arg2 instanceof Long) {
                    arg2 = Double.valueOf(((Long) arg2).doubleValue());
                }
            }
            break;
            case SELECT: {
                if (arg1 instanceof Long) {
                    arg1 = Boolean.valueOf(((Long) arg1) != 0);
                } else if (arg1 instanceof Double) {
                    arg1 = Boolean.valueOf(((Double) arg1) != 0);
                } else if (!(arg1 instanceof Boolean)) {
                    ECLog.logFatal(this, "Operand must be a number or boolean for operation: " + op.getSymbol());
                }

                Object arg2 = operands.size() > 1 && op != Operator.DOT ?
                              operands.get(1).getValue(session) :
                              null;
                Object arg3 = operands.size() > 2 && op != Operator.DOT ?
                              operands.get(2).getValue(session) :
                              null;
                if (arg2 instanceof Boolean) {
                    arg2 = Long.valueOf(((Boolean) arg2) ?
                                        1 :
                                        0);
                }
                if (arg3 instanceof Boolean) {
                    arg3 = Long.valueOf(((Boolean) arg3) ?
                                        1 :
                                        0);
                }
            }
            break;
        }

        boolean notSupported = false;
        // now do the operation
        switch (op) {
            case PLUS: {
                Object arg2 = operands.size() > 1 && op != Operator.DOT ?
                              operands.get(1).getValue(session) :
                              null;
                if (arg2 == null) {
                    if (arg1 instanceof Long) {
                        value = Long.valueOf(((Number) arg1).longValue());
                    } else if (arg1 instanceof Number) {
                        value = Double.valueOf(((Number) arg1).doubleValue());
                    } else {
                        notSupported = true;
                    }
                } else {
                    if (arg1 instanceof Long && arg2 instanceof Long) {
                        value = Long.valueOf(((Number) arg1).longValue() + ((Number) arg2).longValue());
                    } else if (arg1 instanceof Number && arg2 instanceof Number) {
                        value = Double.valueOf(((Number) arg1).doubleValue() + ((Number) arg2).doubleValue());
                    } else if (arg1 instanceof String && arg2 instanceof String) {
                        value = arg1 + ((String) arg2);
                    } else {
                        notSupported = true;
                    }
                }
            }
            break;
            case MINUS: {
                Object arg2 = operands.size() > 1 && op != Operator.DOT ?
                              operands.get(1).getValue(session) :
                              null;
                if (arg2 == null) {
                    if (arg1 instanceof Long) {
                        value = Long.valueOf(-((Number) arg1).longValue());
                    } else if (arg1 instanceof Number) {
                        value = Double.valueOf(-((Number) arg1).doubleValue());
                    } else {
                        notSupported = true;
                    }
                } else {
                    if (arg1 instanceof Long && arg2 instanceof Long) {
                        value = Long.valueOf(((Number) arg1).longValue() - ((Number) arg2).longValue());
                    } else if (arg1 instanceof Number && arg2 instanceof Number) {
                        value = Double.valueOf(((Number) arg1).doubleValue() - ((Number) arg2).doubleValue());
                    } else {
                        notSupported = true;
                    }
                }
            }
            break;
            case TIMES: {
                Object arg2 = operands.size() > 1 && op != Operator.DOT ?
                              operands.get(1).getValue(session) :
                              null;
                if (arg1 instanceof Long && arg2 instanceof Long) {
                    value = Long.valueOf(((Number) arg1).longValue() * ((Number) arg2).longValue());
                } else if (arg1 instanceof Number && arg2 instanceof Number) {
                    value = Double.valueOf(((Number) arg1).doubleValue() * ((Number) arg2).doubleValue());
                } else {
                    notSupported = true;
                }
            }
            break;
            case DIVIDE: {
                Object arg2 = operands.size() > 1 && op != Operator.DOT ?
                              operands.get(1).getValue(session) :
                              null;
                if (arg1 instanceof Long && arg2 instanceof Long) {
                    value = Long.valueOf(((Number) arg1).longValue() / ((Number) arg2).longValue());
                } else if (arg1 instanceof Number && arg2 instanceof Number) {
                    value = Double.valueOf(((Number) arg1).doubleValue() / ((Number) arg2).doubleValue());
                } else {
                    notSupported = true;
                }
            }
            break;
            case MODULO: {
                Object arg2 = operands.size() > 1 && op != Operator.DOT ?
                              operands.get(1).getValue(session) :
                              null;
                if (arg1 instanceof Long && arg2 instanceof Long) {
                    value = Long.valueOf(((Number) arg1).longValue() % ((Number) arg2).longValue());
                } else if (arg1 instanceof Double && arg2 instanceof Double) {
                    value = Double.valueOf(((Number) arg1).doubleValue() % ((Number) arg2).doubleValue());
                } else {
                    notSupported = true;
                }
            }
            break;
            case LOGICAL_AND: {
                if (arg1 instanceof Boolean && !Boolean.valueOf(((Boolean) arg1))) {
                    value = false;
                } else {
                    Object arg2 = operands.size() > 1 && op != Operator.DOT ?
                                  operands.get(1).getValue(session) :
                                  null;

                    if (arg1 instanceof Boolean && arg2 instanceof Boolean) {
                        value = Boolean.valueOf(((Boolean) arg1) && ((Boolean) arg2));
                    } else {
                        notSupported = true;
                    }
                }
            }
            break;
            case LOGICAL_OR: {
                if (arg1 instanceof Boolean && Boolean.valueOf(((Boolean) arg1))) {
                    value = true;
                } else {
                    Object arg2 = operands.size() > 1 && op != Operator.DOT ?
                                  operands.get(1).getValue(session) :
                                  null;
                    if (arg1 instanceof Boolean && arg2 instanceof Boolean) {
                        value = Boolean.valueOf(((Boolean) arg1) || ((Boolean) arg2));
                    } else {
                        notSupported = true;
                    }
                }
            }
            break;
            case LOGICAL_NOT: {
                if (arg1 instanceof Boolean) {
                    value = Boolean.valueOf(!((Boolean) arg1));
                } else {
                    notSupported = true;
                }
            }
            break;
            case IS_EQUAL: {
                Object arg2 = operands.size() > 1 && op != Operator.DOT ?
                              operands.get(1).getValue(session) :
                              null;
                if (arg1 instanceof Number && arg2 instanceof Number) {
                    value = Boolean.valueOf(((Number) arg1).doubleValue() == ((Number) arg2).doubleValue());
                } else if (arg1 instanceof String && arg2 instanceof String) {
                    value = Boolean.valueOf(arg1.equals(arg2));
                } else if (arg1 instanceof MTOperation.Operator && arg2 instanceof FTOperand) {
                    value = Boolean.valueOf(((MTOperation.Operator) arg1).name().equals(((FTOperand) arg2).getText()));
                } else {
                    if (arg1 == null || arg2 == null) {
                        value = (arg1 == arg2);
                    } else {
                        value = arg1.equals(arg2);
//                        ECLog.logError("OP1 is a: " + (arg1 != null ? arg1.getClass().getSimpleName() : "null"));
//                        ECLog.logError("OP2 is a: " + (arg2 != null ? arg2.getClass().getSimpleName() : "null"));
//                        notSupported = true;
                    }
                }
            }
            break;
            case NOT_EQUAL: {
                Object arg2 = operands.size() > 1 && op != Operator.DOT ?
                              operands.get(1).getValue(session) :
                              null;
                if (arg1 instanceof Number && arg2 instanceof Number) {
                    value = Boolean.valueOf(((Number) arg1).doubleValue() != ((Number) arg2).doubleValue());
                } else if (arg1 instanceof String && arg2 instanceof String) {
                    value = Boolean.valueOf(!arg1.equals(arg2));
                } else if (arg1 instanceof MTOperation.Operator && arg2 instanceof FTOperand) {
                    value = Boolean.valueOf(!((MTOperation.Operator) arg1).name().equals(((FTOperand) arg2).getText()));
                } else {
                    if (arg1 == null || arg2 == null) {
                        value = (arg1 != arg2);
                    } else {
                        value = !arg1.equals(arg2);
                    }
                }
            }
            break;
            case GREATER_THAN: {
                Object arg2 = operands.size() > 1 && op != Operator.DOT ?
                              operands.get(1).getValue(session) :
                              null;
                if (arg1 instanceof Number && arg2 instanceof Number) {
                    value = Boolean.valueOf(((Number) arg1).doubleValue() > ((Number) arg2).doubleValue());
                } else {
                    notSupported = true;
                }
            }
            break;
            case GREATER_THAN_OR_EQUAL: {
                Object arg2 = operands.size() > 1 && op != Operator.DOT ?
                              operands.get(1).getValue(session) :
                              null;

                if (arg1 instanceof Number && arg2 instanceof Number) {
                    value = Boolean.valueOf(((Number) arg1).doubleValue() >= ((Number) arg2).doubleValue());
                } else {
                    notSupported = true;
                }
            }
            break;
            case LESS_THAN: {
                Object arg2 = operands.size() > 1 && op != Operator.DOT ?
                              operands.get(1).getValue(session) :
                              null;
                if (arg1 instanceof Number && arg2 instanceof Number) {
                    value = Boolean.valueOf(((Number) arg1).doubleValue() < ((Number) arg2).doubleValue());
                } else {
                    notSupported = true;
                }
            }
            break;
            case LESS_THAN_OR_EQUAL: {
                Object arg2 = operands.size() > 1 && op != Operator.DOT ?
                              operands.get(1).getValue(session) :
                              null;
                if (arg1 instanceof Number && arg2 instanceof Number) {
                    value = Boolean.valueOf(((Number) arg1).doubleValue() <= ((Number) arg2).doubleValue());
                } else {
                    notSupported = true;
                }
            }
            break;
            case SELECT: {
                Object arg2 = operands.size() > 1 && op != Operator.DOT ?
                              operands.get(1).getValue(session) :
                              null;
                Object arg3 = operands.size() > 2 && op != Operator.DOT ?
                              operands.get(2).getValue(session) :
                              null;
                value = ((Boolean) arg1).booleanValue() ?
                        arg2 :
                        arg3;
            }
            break;

            case INSTANCE_OF:
                // TODO
                break;

            case EXTENDS:
                // TODO
                break;

            case DOT:
                if (arg1 instanceof MFObject) {
                    if (operands.get(1) instanceof FTMethodCall) {
                        FTMethodCall      methodCall = (FTMethodCall) (operands.get(1));
                        int               argCount   = methodCall.getArgumentCount();
                        ArrayList<Object> args       = new ArrayList<>();
                        for (int i = 0; i < argCount; i++) {
                            FTExpression argumentExpression = methodCall.getArgument(i);
                            Object argValue = argumentExpression != null ?
                                              argumentExpression.getValue(session) :
                                              null;
                            if (argValue != null) {
                                args.add(argValue);
                            }
                        }
                        try {
                            value = ((MFObject) arg1).callMethod(this, session, methodCall.getMethodName(),
                                                                 args);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        //ECLog.logFatal("Almost ready to support a method call on class: " + arg1.getClass().getSimpleName());
                    } else if (operands.get(1) instanceof FTOperand) {
                        FTOperand operand   = (FTOperand) operands.get(1);
                        String    fieldName = operand.getName();
                        try {
                            value = ((MFObject) arg1).getValueByName(fieldName);
                        } catch (IllegalAccessException e) {
                            ECLog.logFatal(operand, "Unable to access field \"" + fieldName + "\" for object of class "
                                                    + arg1.getClass().getSimpleName());
                        } catch (NoSuchMethodException e) {
                            ECLog.logFatal(operand,
                                           "Unable to find field \"" + fieldName + "\" for object of class " + arg1
                                           + " with error: " + e);
                        } catch (InvocationTargetException e) {
                            e.getCause().printStackTrace();
                            ECLog.logFatal(operand, "(InvocationTargetException) Unable to access field \"" + fieldName
                                                    + "\" for object of class " + arg1.getClass().getSimpleName()
                                                    + " with cause: " + e.getCause());
                        }
                    } else {
                        ECLog.logFatal("Expression not supported: " + getText());
                    }
                } else if (arg1 != null && arg1.getClass().isEnum()) {
                    Class     enumClass = arg1.getClass();
                    FTOperand operand   = (FTOperand) operands.get(1);
                    String    fieldName = operand.getName();
                    try {
                        Method method = enumClass.getMethod("get" + ECStringUtil.Capitalize(fieldName));
                        value = method.invoke(arg1);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
        if (notSupported) {
            if (op.getOperands() > 1) {
                if (arg1 == null) {
                    ECLog.logFatal(this, "First argument is null of operation: " + op.getSymbol());
                }
                ECLog.logFatal(this, "Combination of operand types not supported for operator: " + op.getSymbol());
            } else {
                ECLog.logFatal(this, "Operand type not supported for operator: " + op.getSymbol());
            }
        }
        return value;
    }

    @Override
    public String toString() {
        String fullString = "";
        if (op.getOperands() > 0) {
            fullString += operands.get(0).toString();
        }
        fullString += " " + op.getSymbol();
        if (op.getOperands() > 1) {
            fullString += " " + operands.get(1).toString();
        }
        return fullString;
    }

    @Override
    public void accept(FTVisitor visitor) {
        visitor.visit(this);
        for (FTExpression expression : operands) {
            expression.accept(visitor);
        }
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {

        boolean success = true;
        if (!op.isUnary()) {
            if (!formatOperandExpression(operands.get(0), formatController, indentLevel)) {
                success = false;
            }
        }
        formatController.addExpressionOperator(op, op.getSymbol(), this.getStartLineNumber());
        if (!formatOperandExpression(operands.get(op.isUnary() ?
                                                  0 :
                                                  1), formatController, indentLevel)) {
            success = false;
        }
        if (op.isTertiary()) {
            formatController.addExpressionElement(ConfigurableElement.ExpressionSelectItemDelim, ":",
                                                  this.getStartLineNumber());
            if (!formatOperandExpression(operands.get(2), formatController, indentLevel)) {
                success = false;
            }
        }
        return success;
    }

    private boolean formatOperandExpression(FTExpression expression, TemplateFormatController formatController, int indentLevel) {
        boolean success              = true;
        boolean addParens            = false;
        int     expressionPrecedence = expression.getPrecedence();
        if (expressionPrecedence != 0) {
            addParens = expressionPrecedence < (op.isUnary() ?
                                                op.unaryPrecedence :
                                                op.precedence);
        }
        if (addParens) {
            formatController.addExpressionElement(ConfigurableElement.ExpressionOpenParen, "(",
                                                  this.getStartLineNumber());
        }
        if (!expression.format(formatController, indentLevel)) {
            success = false;
        }
        if (addParens) {
            formatController.addExpressionElement(ConfigurableElement.ExpressionCloseParen, ")",
                                                  this.getStartLineNumber());
        }
        return success;
    }

    // Operators that start with an _ are not operators but just things found in
    // an expression where we need to know its precedence
    public enum Operator {
        _OPERAND("", 0, 100, 0),

        @TemplateOperator(
                name = "Dereference",
                usage = "*expression*`.`*field*",
                D = "Accesses a field of an object represented by the expression. If the expression does not resolve to an object, an error will occur.")
        DOT(".", 2, 80, 0),

        @TemplateOperator(
                name = "Logical NOT",
                usage = "`!`*expression*",
                D = "Accesses a field of an object represented by the expression. If the expression does not resolve to an object, an error will occur.")
        LOGICAL_NOT("!", 1, 0, 60),

        @TemplateOperator(
                name = "Multiply",
                usage = "*expression*`*`*expression*",
                D = "Multiplies the two expressions. Both expressions must evaluate to a number.")
        TIMES("*", 2, 50, 0),

        @TemplateOperator(
                name = "Divide",
                usage = "*expression*`/`*expression*",
                D = "Divides the first expression by the second. Both expressions must evaluate to a number.")
        DIVIDE("/", 2, 50, 0),

        @TemplateOperator(
                name = "Modulo",
                usage = "*expression*`%`*expression*",
                D = "Performs the modulo of the first expression with the second. Both expressions must evaluate to a number.")
        MODULO("%", 2, 50, 0),

        _METHOD_CALL("()", 1, 76, 0),

        @TemplateOperator(
                name = "Add",
                usage = "*expression*`+`*expression*",
                D = "Adds the two expressions. Both expressions must evaluate to a number.")
        PLUS("+", 2, 40, 70),

        @TemplateOperator(
                name = "Subtract",
                usage = "*expression*`-`*expression*",
                D = "Subtracts the second expression from the first. Both expressions must evaluate to a number.")
        MINUS("-", 2, 40, 70),

        @TemplateOperator(
                name = "Greater Than",
                usage = "*expression*`>`*expression*",
                D = "Evaluates to `true` if the first expression is numerically **greater than** to the second expression.")
        GREATER_THAN(">", 2, 32, 0),

        @TemplateOperator(
                name = "Greater Than or Equal",
                usage = "*expression*`>=`*expression*",
                D = "Evaluates to `true` if the first expression is numerically **greater than or equal** to the second expression.")
        GREATER_THAN_OR_EQUAL(">=", 2, 32, 0),

        @TemplateOperator(
                name = "Greater Than",
                usage = "*expression*`<`*expression*",
                D = "Evaluates to `true` if the first expression is numerically **less than** to the second expression.")
        LESS_THAN("<", 2, 32, 0),

        @TemplateOperator(
                name = "Less Than or Equal",
                usage = "*expression*`<=`*expression*",
                D = "Evaluates to `true` if the first expression is numerically **less than or equal** to the second expression.")
        LESS_THAN_OR_EQUAL("<=", 2, 32, 0),

        @TemplateOperator(
                name = "Is Equal",
                usage = "*expression*`==`*expression*",
                D = "Evaluates to `true` if the two expressions are numerically equal.")
        IS_EQUAL("==", 2, 30, 0),

        @TemplateOperator(
                name = "Is NOT Equal",
                usage = "*expression*`!=`*expression*",
                D = "Evaluates to `true` if the two expressions are **not** numerically equal.")
        NOT_EQUAL("!=", 2, 30, 0),

        INSTANCE_OF("instanceof", 2, 21, 0),
        EXTENDS("extends", 2, 20, 0),

        @TemplateOperator(
                name = "Logical AND",
                usage = "*expression*`&&`*expression*",
                D = "Evaluates to `true` if **both** expressions evaluate to `true`.")
        LOGICAL_AND("&&", 2, 11, 0),

        @TemplateOperator(
                name = "Logical OR",
                usage = "*expression*`||`*expression*",
                D = "Evaluates to `true` if **either** (or both) expressions evaluate to `true`.")
        LOGICAL_OR("||", 2, 10, 0),

        @TemplateOperator(
                name = "Select",
                usage = "*expression*`?`*expression*`:`*expression*",
                D = " If the first expression evaluates to `true` then the second expression is selected, otherwise the third expression is selected.")
        SELECT("?", 3, 5, 0),

        _FILTER("|", 2, 4, 0),
        _ARRAY("[]", 1, 3, 0),
        _NAMED_ELEMENT(":.", 1, 2, 0),
        EQUALS("=", 2, 1, 0),
        ;
        public final  int    precedence;
        private final int    operands;
        private final String symbol;
        private final int    unaryPrecedence;

        Operator(String symbol, int operands, int precedence, int unaryPrecedence) {
            this.symbol          = symbol;
            this.operands        = operands;
            this.precedence      = precedence;
            this.unaryPrecedence = unaryPrecedence;
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

        public int getOperands() {
            return operands;
        }

        public String getSymbol() {
            return symbol;
        }

        public boolean isUnary() {
            return operands == 1;
        }

        public boolean isBinary() {
            return operands == 2;
        }

        public boolean isTertiary() {
            return operands == 3;
        }
    }
}

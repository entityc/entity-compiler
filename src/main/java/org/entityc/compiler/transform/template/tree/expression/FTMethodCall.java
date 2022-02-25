/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.expression;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.foundation.MFObject;
import org.entityc.compiler.transform.template.formatter.ConfigurableElement;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.FTVisitor;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.List;

public class FTMethodCall extends FTExpression {

    private final FTExpression       operandExpression;
    private final String             methodName;
    private final List<FTExpression> arguments = new ArrayList<>();

    public FTMethodCall(ParserRuleContext ctx, FTExpression operandExpression, String methodName) {
        super(ctx);
        this.operandExpression = operandExpression;
        this.methodName        = methodName;
    }

    public void addExpression(FTExpression expression) {
        this.arguments.add(expression);
    }

    @Override
    public Object getValue(FTTransformSession session) {
        if (operandExpression == null) {
            ECLog.logFatal(this, "Cannot call method on a null object.");
        }
        Object operandObject = operandExpression.getValue(session);
        if (operandObject == null) {
            ECLog.logFatal(this, "Operand expression evaluates to null: " + operandExpression.getText());
        }
        if (!(operandObject instanceof MFObject)) {
            ECLog.logFatal(this,
                           "Cannot call method on this type of object: " + operandObject.getClass().getSimpleName());
        }
        int               argCount = this.getArgumentCount();
        ArrayList<Object> argList  = new ArrayList<>();
        for (int i = 0; i < argCount; i++) {
            FTExpression argumentExpression = this.getArgument(i);
            Object argValue = argumentExpression != null ?
                              argumentExpression.getValue(session) :
                              null;
            if (argValue != null) {
                argList.add(argValue);
            }
        }
        Object value = null;
        try {
            value = ((MFObject) operandObject).callMethod(this, session, this.getMethodName(), argList);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }

    public int getArgumentCount() {
        return arguments.size();
    }

    public FTExpression getArgument(int i) {
        return arguments.get(i);
    }

    public String getMethodName() {
        return methodName;
    }

    @Override
    public void accept(FTVisitor visitor) {
        visitor.visit(this);
        if (operandExpression != null) {
            operandExpression.accept(visitor);
        }
        for (FTExpression expression : arguments) {
            expression.accept(visitor);
        }
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success    = true;
        boolean needsParen = operandExpression != null && operandExpression.getPrecedence() < this.getPrecedence();
        if (needsParen) {
            formatController.addExpressionElement(ConfigurableElement.ExpressionOpenParen, "(",
                                                  this.getStartLineNumber());
        }
        if (operandExpression != null && !operandExpression.format(formatController, indentLevel)) {
            success = false;
        }
        if (needsParen) {
            formatController.addExpressionElement(ConfigurableElement.ExpressionCloseParen, ")",
                                                  this.getStartLineNumber());
        }
        if (operandExpression != null) {
            formatController.addExpressionOperator(FTOperation.Operator.DOT, ".", this.getStartLineNumber());
        }
        formatController.addExpressionElement(ConfigurableElement.MethodCallName, methodName,
                                              this.getStartLineNumber());
        formatController.addExpressionElement(ConfigurableElement.MethodCallOpenParen, "(", this.getStartLineNumber());
        boolean first = true;
        for (FTExpression argExpression : arguments) {
            if (!first) {
                formatController.addExpressionElement(ConfigurableElement.MethodCallDelim, ",",
                                                      this.getStartLineNumber());
            }
            if (!argExpression.format(formatController, indentLevel)) {
                success = false;
            }
            first = false;
        }
        formatController.addExpressionElement(ConfigurableElement.MethodCallCloseParen, ")", this.getStartLineNumber());
        return success;
    }
}

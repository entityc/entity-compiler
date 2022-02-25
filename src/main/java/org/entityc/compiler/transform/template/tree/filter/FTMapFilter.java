/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.filter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.expression.MTConstant;
import org.entityc.compiler.model.expression.MTExpression;
import org.entityc.compiler.model.expression.MTMethodCall;
import org.entityc.compiler.model.expression.MTOperand;
import org.entityc.compiler.model.expression.MTOperation;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.expression.FTConstant;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.transform.template.tree.expression.FTMethodCall;
import org.entityc.compiler.transform.template.tree.expression.FTOperand;
import org.entityc.compiler.transform.template.tree.expression.FTOperation;
import org.entityc.compiler.util.ECLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FTMapFilter extends FTFilter {

    public FTMapFilter() {
        super(null, "map",
              "This filter is probably the most complicated one but can be very powerful in helping to pattern "
              + "match an expression at its input with one provided as a parameter. When the expressions match, "
              + "it not only returns true but also maps operands from the input expression to the parameter "
              + "expression.");
        addSingleInputType(MTExpression.class);
        this.addFilterParam(new FTFilterParam("mapExpression",
                                              "Specifies the expression into which to map the input expression of this filter."));
    }

    @Override
    public Object filter(ParserRuleContext ctx, FTTransformSession session, Object input, List<FTExpression> paramValues, Map<String, Object> options) {
        checkInput(ctx, input, paramValues, options);
        if (input instanceof MTExpression) {
            MTExpression              inputExpression   = (MTExpression) input;
            FTExpression              mapExpression     = paramValues.get(0);
            Map<String, MTExpression> mappedExpressions = new HashMap<>();
            boolean                   couldMap          = map(mappedExpressions, inputExpression, mapExpression);
            if (couldMap) {
                for (String varName : mappedExpressions.keySet()) {
                    MTExpression expression = mappedExpressions.get(varName);
                    if (!expression.isConstant() && !expression.isOperand()) {
                        ECLog.logFatal("Variables must map to constants or identifiers.");
                    }
                    session.setValue(varName, expression.getText());
                }
            }
            else {
                ECLog.logWarning("Unable to map: " + mapExpression.getText());
            }
            return couldMap;
        }
        return null;
    }

    private boolean map(Map<String, MTExpression> mappedExpressions, MTExpression inputExpression, FTExpression mapExpression) {
        if (inputExpression.isConstant()) {
            if (mapExpression.isConstant()) {
                if (((MTConstant) inputExpression).getStringValue().equals(
                    ((FTConstant) mapExpression).getStringValue())) {
                    return true;
                }
            }
            else if (mapExpression.isOperand()) {
                mappedExpressions.put(((FTOperand) mapExpression).getName(), inputExpression);
                return true;
            }
            ECLog.logInfo(">>> Constant doesn't match either constant or operand.");
            return false;
        }
        else if (inputExpression.isMethodCall()) {
            if (!mapExpression.isMethodCall()) {
                ECLog.logInfo(">>> Method call doesn't match because other is not a method call.");
                return false;
            }
            MTMethodCall inputMethodCall   = (MTMethodCall) inputExpression;
            FTMethodCall mappingMethodCall = (FTMethodCall) mapExpression;
            if (!inputMethodCall.getName().equals(mappingMethodCall.getMethodName())) {
                ECLog.logInfo(">>> Method call doesn't match because names are different.");
                return false;
            }
            if (inputMethodCall.getArgumentCount() != mappingMethodCall.getArgumentCount()) {
                ECLog.logInfo(">>> Method call doesn't match because number of args are different.");
                return false;
            }
            for (int i = 0; i < inputMethodCall.getArgumentCount(); i++) {
                if (!map(mappedExpressions, inputMethodCall.getArgument(i), mappingMethodCall.getArgument(i))) {
                    return false;
                }
            }
            return true;
        }
        else if (inputExpression.isOperation()) {
            if (mapExpression.isOperation()) {
                MTOperation inputOperation   = (MTOperation) inputExpression;
                FTOperation mappingOperation = (FTOperation) mapExpression;
                if (inputOperation.getOp().getSymbol().equals(mappingOperation.getOp().getSymbol())) {
                    if (inputOperation.getOperandCount() != mappingOperation.getOperandCount()) {
                        return false;
                    }

                    for (int i = 0; i < inputOperation.getOperandCount(); i++) {
                        if (!map(mappedExpressions, inputOperation.getOperand(i), mappingOperation.getOperand(i))) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }
        else if (inputExpression.isOperand()) {
            if (mapExpression.isOperand()) {
                MTOperand inputOperand   = (MTOperand) inputExpression;
                FTOperand mappingOperand = (FTOperand) mapExpression;
                mappedExpressions.put(mappingOperand.getName(), inputOperand);
                return true;
            }
        }
        return false;
    }
}

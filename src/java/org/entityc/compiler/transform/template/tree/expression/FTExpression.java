/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.expression;

import org.entityc.compiler.transform.template.formatter.ConfigurableElement;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.transform.template.tree.FTNode;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.FTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class FTExpression extends FTNode {

    public FTExpression(ParserRuleContext ctx) {
        super(ctx);
    }

    public abstract Object getValue(FTTransformSession session);

    public boolean isConstant() {
        return this instanceof FTConstant;
    }

    public boolean isOperand() {
        return this instanceof FTOperand;
    }

    public boolean isOperation() {
        return this instanceof FTOperation;
    }

    public boolean isMethodCall() {
        return this instanceof FTMethodCall;
    }

    public boolean isArray() {
        return this instanceof FTArray;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        // if we are calling this method it means we forgot to support an inheriting class
        formatController.addExpressionElement(ConfigurableElement.None, "(|" + getClass().getSimpleName() + "|)", this.getStartLineNumber());
        return false;
    }

    @Override
    public void accept(FTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public int getTemplateLexerSymbol() {
        return NO_SYMBOL;
    }

    public int getPrecedence() {
        int expressionPrecedence = 0;
        if (this instanceof FTOperation) {
            expressionPrecedence = ((FTOperation) this).getOp().precedence;
        } else if (this instanceof FTFilterExpression) {
            expressionPrecedence = FTOperation.Operator._FILTER.precedence;
        } else if (this instanceof FTMethodCall) {
            expressionPrecedence = FTOperation.Operator._METHOD_CALL.precedence;
        } else if (this instanceof FTArray) {
            expressionPrecedence = FTOperation.Operator._ARRAY.precedence;
        } else if (this instanceof FTNamedElement) {
            expressionPrecedence = FTOperation.Operator._NAMED_ELEMENT.precedence;
        } else if (this instanceof FTOperand) {
            expressionPrecedence = FTOperation.Operator._OPERAND.precedence;
        }
        return expressionPrecedence;
    }
}

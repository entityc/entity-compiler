/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.expression;

import org.entityc.compiler.transform.template.TemplateLexer;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.FTVisitor;
import org.entityc.compiler.transform.template.tree.filter.FTFilter;
import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.transform.template.formatter.ConfigurableElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FTFilterExpression extends FTExpression {

    private final FTFilter           filter;
    private final List<FTExpression> parameters = new ArrayList<>();
    private final Map<String, Object> options    = new HashMap<>();
    private final ParserRuleContext   ctx;
    private       FTExpression        inputExpression;

    public FTFilterExpression(ParserRuleContext ctx, FTFilter filter) {
        super(ctx);
        this.ctx    = ctx;
        this.filter = filter;
    }

    public FTFilterExpression(ParserRuleContext ctx, FTFilter filter, String param) {
        super(ctx);
        this.ctx    = ctx;
        this.filter = filter;
        if (param != null) {
            this.parameters.add(new FTOperand(ctx, param));
        }
    }

    public FTFilterExpression(ParserRuleContext ctx, FTFilter filter, FTExpression paramExpression) {
        super(ctx);
        this.ctx    = ctx;
        this.filter = filter;
        if (paramExpression != null) {
            this.parameters.add(paramExpression);
        }
    }

    public List<FTExpression> getParameters() {
        return parameters;
    }

    public FTFilter getFilter() {
        return filter;
    }

    public void addOption(String optionName, Object value) {
        options.put(optionName, value);
    }

    @Override
    public Object getValue(FTTransformSession session) {
        Object input = inputExpression.getValue(session);
        return filter.filter(ctx, session, input, parameters, options);
    }

    public FTExpression getInputExpression() {
        return inputExpression;
    }

    public void setInputExpression(FTExpression inputExpression) {
        this.inputExpression = inputExpression;
    }

    @Override
    public void accept(FTVisitor visitor) {
        visitor.visit(this);
        inputExpression.accept(visitor);
        for (FTExpression expression : parameters) {
            expression.accept(visitor);
        }
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success    = true;
        boolean needsParen = inputExpression.getPrecedence() < this.getPrecedence();
        if (needsParen) {
            formatController.addExpressionElement(ConfigurableElement.ExpressionOpenParen, "(", this.getStartLineNumber());
        }
        if (!inputExpression.format(formatController, indentLevel)) {
            success = false;
        }
        if (needsParen) {
            formatController.addExpressionElement(ConfigurableElement.ExpressionCloseParen, ")", this.getStartLineNumber());
        }
        formatController.addExpressionElement(ConfigurableElement.FilterPipe, GetInstructionNameOfLexerSymbol(
                TemplateLexer.Pipe),
                                              this.getStartLineNumber());
        formatController.addExpressionElement(ConfigurableElement.FilterName, filter.getName(), this.getStartLineNumber());
        if (parameters.size() > 0) {
            formatController.addExpressionElement(ConfigurableElement.FilterParamColon, ":", this.getStartLineNumber());
            FTExpression exprNode = parameters.get(0);
            if (!exprNode.isOperand()) {
                formatController.addExpressionElement(ConfigurableElement.ExpressionOpenParen, "(", -1);
            }
            if (!parameters.get(0).format(formatController, indentLevel)) {
                success = false;
            }
            if (!exprNode.isOperand()) {
                formatController.addExpressionElement(ConfigurableElement.ExpressionCloseParen, ")", -1);
            }
        }
        if (options.size() > 0) {
            for (String option : options.keySet()) {
                formatController.addExpressionElement(ConfigurableElement.RequiredSpace, " ", this.getStartLineNumber());
                Object value = options.get(option);
                formatController.addExpressionElement(ConfigurableElement.ExpressionOperand, option, -1);
                if (!((value instanceof Boolean) && ((Boolean) value).booleanValue() == true)) {
                    formatController.addExpressionElement(ConfigurableElement.FilterParamColon, ":", -1);
                    formatController.addExpressionElement(ConfigurableElement.ExpressionOperand, value.toString(), -1);
                }
            }
            success = false; // TODO: not done yet
        }
        return success;
    }
}

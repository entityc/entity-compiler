/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.expression;

import org.entityc.compiler.model.foundation.MFArray;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.FTVisitor;
import org.entityc.compiler.util.ECLog;
import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.transform.template.formatter.ConfigurableElement;

import java.util.ArrayList;

public class FTArray extends FTExpression {

    ArrayList<FTExpression> expressions = new ArrayList<>();

    public FTArray(ParserRuleContext ctx) {
        super(ctx);
    }

    public void addExpression(FTExpression expression) {
        expressions.add(expression);
    }

    @Override
    public Object getValue(FTTransformSession session) {
        MFArray array = new MFArray();
        for (FTExpression expression : expressions) {
            Object value = expression.getValue(session);
            if (value == null) {
                ECLog.logFatal(expression, "Arrays cannot have null values.");
            }
            array.add(value);
        }
        return array;
    }

    @Override
    public void accept(FTVisitor visitor) {
        visitor.visit(this);
        for (FTExpression expression : expressions) {
            expression.accept(visitor);
        }
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;
        boolean first   = true;
        formatController.addExpressionElement(ConfigurableElement.ArrayPrefix, "@[", this.getStartLineNumber());
        for (FTExpression expression : expressions) {
            if (!first) {
                formatController.addExpressionElement(ConfigurableElement.ArrayElementDelim, ",", this.getStartLineNumber());
            }
            if (!(expression.format(formatController, indentLevel))) {
                success = false;
            }
            first = false;
        }
        formatController.addExpressionElement(ConfigurableElement.ArraySuffix, "]@", this.getStartLineNumber());
        return success;
    }
}

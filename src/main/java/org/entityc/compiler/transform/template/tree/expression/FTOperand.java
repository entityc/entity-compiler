/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.expression;

import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.transform.template.formatter.ConfigurableElement;

public class FTOperand extends FTExpression {

    private final String name;

    public FTOperand(ParserRuleContext ctx, String name) {
        super(ctx);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Object getValue(FTTransformSession session) {
        Object value = session.getValue(name);
        return value;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        formatController.addExpressionElement(ConfigurableElement.ExpressionOperand, name, this.getStartLineNumber());
        return true;
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

import org.entityc.compiler.transform.template.formatter.ConfigurableElement;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.transform.template.tree.expression.FTConstant;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.util.ECLog;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * This is the node that actually writes something to the output.
 */
public class FTExpressionTag extends FTNode {

    FTExpression expression;

    public FTExpressionTag(ParserRuleContext ctx, FTExpression expression) {
        super(ctx);
        this.expression = expression;
    }

    @Override
    public void transform(FTTransformSession session) {
        if (expression == null) {
            ECLog.logWarning(this, "Expression tag with null expression!");
            return;
        }
        String valueToOutput = "";
        if (expression.getValue(session) != null) {
            //ECLog.logWarning(this, "Expression tag with expression that evaluates to null: " + this.getText());
            valueToOutput = expression.getValue(session).toString();
        }
        session.sendToOutput(valueToOutput);
        super.transform(session);
    }

    @Override
    public void accept(FTVisitor visitor) {
        visitor.visit(this);
        expression.accept(visitor);
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;
        formatController.addVariableExpressionStart("${", this.getStartLineNumber());
        if (expression.isConstant() && ((FTConstant)expression).getType() == FTConstant.Type.STRING && ((FTConstant)expression).getStringValue().equals("$")) {
            formatController.addInstructionInside(ConfigurableElement.None, "$", this.getStartLineNumber());
        } else if (!expression.format(formatController, indentLevel)) {
            success = false;
        }
        formatController.addVariableExpressionEnd("}", this.getStartLineNumber());
        return success;
    }

    @Override
    public int getTemplateLexerSymbol() {
        return NO_SYMBOL;
    }
}

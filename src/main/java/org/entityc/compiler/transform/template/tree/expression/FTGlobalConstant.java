/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.expression;

import org.entityc.compiler.EntityCompiler;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.transform.template.formatter.ConfigurableElement;

public class FTGlobalConstant extends FTConstant {

    private final String     name;
    private final FTConstant defaultConstant;

    public FTGlobalConstant(ParserRuleContext ctx, String name, FTConstant defaultConstant) {
        super(ctx, defaultConstant == null ? null : defaultConstant.getStringValue());
        this.name            = name;
        this.defaultConstant = defaultConstant;
    }

    public String getName() {
        return name;
    }

    @Override
    public Object getValue(FTTransformSession session) {
        return EntityCompiler.GetDefineValue(name, getStringValue());
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean hasDefaultConstant = defaultConstant != null;
        if (hasDefaultConstant) {
            formatController.addExpressionElement(ConfigurableElement.ExpressionOpenParen, "(",
                                                  this.getStartLineNumber());
        }
        formatController.addExpressionElement(ConfigurableElement.GlobalConstantPrefix, "#", this.getStartLineNumber());
        formatController.addExpressionElement(ConfigurableElement.GlobalConstantName, name, this.getStartLineNumber());
        if (hasDefaultConstant) {
            formatController.addExpressionElement(ConfigurableElement.ExpressionSelectItemDelim, ":",
                                                  this.getStartLineNumber());
            defaultConstant.format(formatController, indentLevel);
            formatController.addExpressionElement(ConfigurableElement.ExpressionCloseParen, ")",
                                                  this.getStartLineNumber());
        }
        return true;
    }
}

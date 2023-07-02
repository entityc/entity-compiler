/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.expression;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.foundation.MFMap;
import org.entityc.compiler.transform.template.formatter.ConfigurableElement;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.FTVisitor;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.HashMap;

public class FTMap extends FTExpression {

    HashMap<FTExpression, FTExpression> expressions    = new HashMap();
    ArrayList<FTExpression>             keyExpressions = new ArrayList<>();

    public FTMap(ParserRuleContext ctx) {
        super(ctx);
    }

    public void addKeyValueExpressions(FTExpression keyExpression, FTExpression valueExpression) {
        expressions.put(keyExpression, valueExpression);
    }

    @Override
    public Object getValue(FTTransformSession session) {
        MFMap map = new MFMap();
        for (FTExpression keyExpression : keyExpressions) {
            Object keyValue   = keyExpression.getValue(session);
            Object valueValue = expressions.get(keyExpression).getValue(session);
            if (keyValue == null || valueValue == null) {
                ECLog.logFatal(keyExpression, "Maps cannot have null keys or values.");
            }
            map.put(keyValue, valueValue);
        }
        return map;
    }

    @Override
    public void accept(FTVisitor visitor) {
        visitor.visit(this);
        for (FTExpression keyExpression : keyExpressions) {
            keyExpression.accept(visitor);
            FTExpression valueExpression = expressions.get(keyExpression);
            valueExpression.accept(visitor);
        }
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;
        boolean first   = true;
        formatController.addExpressionElement(ConfigurableElement.MapPrefix, "@{", this.getStartLineNumber());
        for (FTExpression keyExpression : keyExpressions) {
            FTExpression valueExpression = expressions.get(keyExpression);
            if (!first) {
                formatController.addExpressionElement(ConfigurableElement.MapElementDelim, ",",
                                                      this.getStartLineNumber());
            }
            if (!(keyExpression.format(formatController, indentLevel))) {
                success = false;
            }
            formatController.addExpressionElement(ConfigurableElement.MapKeyValueDelim, ":", this.getStartLineNumber());
            if (!(valueExpression.format(formatController, indentLevel))) {
                success = false;
            }
            first = false;
        }
        formatController.addExpressionElement(ConfigurableElement.MapSuffix, "}@", this.getStartLineNumber());
        return success;
    }
}

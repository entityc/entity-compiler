/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.expression;

import org.entityc.compiler.transform.template.formatter.ConfigurableElement;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.util.ECLog;
import org.antlr.v4.runtime.ParserRuleContext;

public class FTConstant extends FTExpression {

    private final Type    type;
    private       Boolean booleanValue;
    private       Long    longValue;
    private       Double  doubleValue;
    private       String  stringValue;

    public FTConstant(ParserRuleContext ctx, boolean value) {
        super(ctx);
        this.type = Type.BOOLEAN;
        this.booleanValue = new Boolean(value);
    }

    public FTConstant(ParserRuleContext ctx, long value) {
        super(ctx);
        this.type = Type.LONG;
        this.longValue = new Long(value);
    }

    public FTConstant(ParserRuleContext ctx, double value) {
        super(ctx);
        this.type = Type.DOUBLE;
        this.doubleValue = new Double(value);
    }

    public FTConstant(ParserRuleContext ctx, String value) {
        super(ctx);
        this.type = Type.STRING;
        this.stringValue = value;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        if (type == Type.STRING) {
            formatController.addExpressionElement(ConfigurableElement.QuotedString, "\"" + getStringValue() + "\"", this.getStartLineNumber());
        } else {
            ConfigurableElement element = null;
            switch (type) {
                case BOOLEAN:
                    element = ConfigurableElement.NumberBoolean;
                    break;
                case LONG:
                    element = ConfigurableElement.NumberInteger;
                    break;
                case DOUBLE:
                    element = ConfigurableElement.NumberFloatingPoint;
                    break;
                default:
                    ECLog.logFatal("Unsupported constant type: " + type.name());
                    break;
            }
            formatController.addExpressionElement(element, getValue(null).toString(), this.getStartLineNumber());
        }
        return true;
    }

    public String getStringValue() {
        return stringValue;
    }

    @Override
    public Object getValue(FTTransformSession session) {
        Object value = null;
        switch (type) {
            case BOOLEAN:
                return getBooleanValue();
            case LONG:
                return getLongValue();
            case DOUBLE:
                return getDoubleValue();
            case STRING:
                return getStringValue();
        }
        return value;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public enum Type {
        BOOLEAN,
        LONG,
        DOUBLE,
        STRING
    }
}

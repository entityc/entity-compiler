/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.expression;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.doc.annotation.ModelMethod;
import org.entityc.compiler.doc.annotation.ModelMethodCategory;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.domain.MTDEntity;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTEnumItem;
import org.entityc.compiler.model.language.MTLanguage;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.util.ECLog;

@ModelClass(type = ModelClassType.EXPRESSION,
    description = "This represents a constant in a model expression.")
public class MTConstant extends MTExpression {

    private final Type       type;
    private       Boolean    booleanValue;
    private       Long       longValue;
    private       Double     doubleValue;
    private       String     stringValue;
    private       MTEnumItem enumItem;
    private       String     enumName;
    private       String     enumItemName;

    public MTConstant(ParserRuleContext ctx, boolean value) {
        super(ctx);
        this.type         = Type.BOOLEAN;
        this.booleanValue = new Boolean(value);
    }

    public MTConstant(ParserRuleContext ctx, long value) {
        super(ctx);
        this.type      = Type.LONG;
        this.longValue = new Long(value);
    }

    public MTConstant(ParserRuleContext ctx, double value) {
        super(ctx);
        this.type        = Type.DOUBLE;
        this.doubleValue = new Double(value);
    }

    public MTConstant(ParserRuleContext ctx, String value) {
        super(ctx);
        this.type        = Type.STRING;
        this.stringValue = value;
    }

    public MTConstant(ParserRuleContext ctx, String enumName, String enumItemName) {
        super(ctx);
        this.type         = Type.ENUM_ITEM;
        this.enumName     = enumName;
        this.enumItemName = enumItemName;
    }

    @ModelMethod(category = ModelMethodCategory.ENUM,
        description =
            "If the constant value is an enum item, this will return the enum item. You can use the `isEnumItem` "
            + "property to determine if this is an enum item.")
    public MTEnumItem getEnumItem() {
        return enumItem;
    }

    @ModelMethod(category = ModelMethodCategory.TYPE,
        description = "Returns the data type of this constant.")
    public Type getType() {
        return type;
    }

    @ModelMethod(category = ModelMethodCategory.TYPE,
        description = "Returns the long value of this constant.")
    public Long getLongValue() {
        return longValue;
    }

    @ModelMethod(category = ModelMethodCategory.TYPE,
        description = "Returns the double floating-point value of this constant.")
    public Double getDoubleValue() {
        return doubleValue;
    }

    @ModelMethod(category = ModelMethodCategory.TYPE,
        description = "Returns the string value of this constant.")
    public String getStringValue() {
        return stringValue;
    }

    @ModelMethod(category = ModelMethodCategory.TYPE,
        description = "Returns the boolean value of this constant.")
    public Boolean getBooleanValue() {
        return booleanValue;
    }

    @ModelMethod(category = ModelMethodCategory.ENUM,
        description = "Indicates whether this constant is of the enum item type. If so, you will need to use the "
                      + "`enumItem` property to obtain the enum item object.")
    public boolean isEnumItem() {
        return type == Type.ENUM_ITEM;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @ModelMethod(category = ModelMethodCategory.TYPE,
        description = "Returns the constant represented as a string.")
    @Override
    public String toString() {
        switch (type) {
            case BOOLEAN:
                return booleanValue ?
                       "true" :
                       "false";
            case LONG:
                return "" + longValue;
            case DOUBLE:
                return "" + doubleValue;
            case STRING:
                return stringValue;
        }

        return "";
    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        if (type != Type.ENUM_ITEM) {
            return false;
        }
        MTEnum mtEnum = space.getEnumWithName(enumName);
        if (mtEnum == null) {
            if (pass < 5) {
                return true;
            }
            ECLog.logFatal(this, "Not able to find enum \"" + enumName + "\".");
        }
        this.enumItem = mtEnum.getItemByName(enumItemName);
        if (this.enumItem == null) {
            ECLog.logFatal(this, "Not able to find item named \"" + enumItemName + "\" in enum \"" + enumName + "\".");
        }
        return false;
    }

    @Override
    public String mapToLanguage(MTLanguage language, MTDEntity domainEntity, String objectName) {
        switch (type) {
            case BOOLEAN:
                return booleanValue ?
                       language.getTrueString() :
                       language.getFalseString();
            case LONG:
                return "" + longValue;
            case DOUBLE:
                return "" + doubleValue;
            case STRING:
                return stringValue;
            case ENUM_ITEM:
                return this.enumName + "." + this.enumItemName;
        }
        return "<<INVALID_CONSTANT>>";
    }

    public enum Type {
        BOOLEAN,
        LONG,
        DOUBLE,
        STRING,
        ENUM_ITEM
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.entity;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.doc.annotation.ModelMethod;
import org.entityc.compiler.doc.annotation.ModelMethodCategory;
import org.entityc.compiler.doc.annotation.ModelMethodParameter;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.visitor.MTVisitor;

@ModelClass(type = ModelClassType.ENTITY,
    description = "Represents a data type of an attribute. Such types can be as simple as a primitive (native) type "
                  + "(like `int32`) and as complex as a secondary entity.")
public class MTType extends MTNode {

    public MTType(ParserRuleContext ctx) {
        super(ctx);
    }

    @ModelMethod(category = ModelMethodCategory.TYPE, description = "Indicates whether this type is an entity.")
    public boolean isEntityType() {
        return this instanceof MTEntity;
    }

    @ModelMethod(category = ModelMethodCategory.TYPE, description = "Indicates whether this type is a UUID.")
    public boolean isUUIDType() {
        return isNativeDataType(MTNativeType.DataType.UUID);
    }

    @ModelMethod(category = ModelMethodCategory.TYPE,
        description = "Indicates whether this type is of the specified data types.")
    public boolean isNativeDataType(
        @ModelMethodParameter(description = "The native data type from which to compare.")
            MTNativeType.DataType dataType) {
        return (this.isNativeType() && ((MTNativeType) this).isDataType(dataType));
    }

    @ModelMethod(category = ModelMethodCategory.TYPE,
        description = "Indicates whether this type is one of the native types (`int32`, `string`, etc.)")
    public boolean isNativeType() {
        return this instanceof MTNativeType;
    }

    @ModelMethod(category = ModelMethodCategory.TYPE,
        description = "Indicates whether this type is the `date` data type.")
    public boolean isDateType() {
        return isNativeDataType(MTNativeType.DataType.DATE);
    }

    public void accept(MTVisitor visitor) {

    }

    @ModelMethod(category = ModelMethodCategory.TYPE,
        description = "Returns the string representation of the type. This would be used for debug purposes.")
    public String getAsString() {
        if (isNativeType()) {
            MTNativeType nativeType = (MTNativeType) this;
            if (isByteArrayType()) {
                String str = nativeType.getDataType().getName() + "[";
                if (!nativeType.isVariableSizeArray()) {
                    str += nativeType.getArraySize();
                }
                str += "]";
                return str;
            }
            return ((MTNativeType) this).getDataType().getName();
        }
        if (isEnumType()) {
            return ((MTEnum) this).getName();
        }
        if (this instanceof MTEntity) {
            return ((MTEntity) this).getName();
        }
        if (isTypedef()) {
            return ((MTTypedef) this).getName();
        }
        if (isAssetType()) {
            return MTNativeType.DataType.STRING.getName();
        }
        return MTNativeType.DataType.INT32.getName();
    }

    @ModelMethod(category = ModelMethodCategory.TYPE,
        description = "Indicates whether this type is both an array type and also `byte` data type.")
    public boolean isByteArrayType() {
        return isNativeDataType(MTNativeType.DataType.BYTE_ARRAY);
    }

    @ModelMethod(category = ModelMethodCategory.TYPE,
        description = "Indicates whether this type is one of the integer data types.")
    public boolean isIntegerType() {
        return isNativeDataType(MTNativeType.DataType.INT32) || isNativeDataType(MTNativeType.DataType.INT64);
    }

    public String getTypeAsCaseValue() {
        if (isNativeType()) {
            return ((MTNativeType) this).getDataType().getName();
        }
        if (isEnumType()) {
            return "enum";
        }
        if (this instanceof MTEntity) {
            return "entity";
        }
        if (isTypedef()) {
            return "typedef";
        }
        return MTNativeType.DataType.INT32.getName();
    }

    @ModelMethod(category = ModelMethodCategory.TYPE,
        description = "Indicates whether this type is of `enum` type.")
    public boolean isEnumType() {
        return this instanceof MTEnum;
    }

    @ModelMethod(category = ModelMethodCategory.TYPE,
        description = "Indicates whether this type is a `typedef` type.")
    public boolean isTypedef() {
        return this instanceof MTTypedef;
    }

    public boolean isAssetType() {
        return isNativeDataType(MTNativeType.DataType.ASSET) || this instanceof MTAssetType;
    }

    @ModelMethod(category = ModelMethodCategory.TYPE,
        description = "If this type is an enum type then it returns the enum type object. Otherwise it returns `null`.")
    public MTEnum getEnumType() {
        if (isEnumType()) {
            return (MTEnum) this;
        }
        return null;
    }

    public boolean resolveReferences(MTSpace space, int pass) {
        return false;
    }
}

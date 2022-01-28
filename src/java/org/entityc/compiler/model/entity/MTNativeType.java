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
import org.entityc.compiler.util.ECLog;

@ModelClass(type = ModelClassType.ENTITY, description =
    "Represents a native data type such as `int32`, `float`, `string`, etc. "
    + "Basically anything except a secondary entity type.")
public class MTNativeType extends MTType {

    private final DataType dataType;
    private final Integer  arraySize;
    private       boolean  primaryKey;

    public MTNativeType(ParserRuleContext ctx, DataType dataType) {
        super(ctx);
        this.dataType  = dataType;
        this.arraySize = null;
    }

    public MTNativeType(ParserRuleContext ctx, String dataTypeName) {
        this(ctx, dataTypeName, null);
    }

    public MTNativeType(ParserRuleContext ctx, String dataTypeName, Integer arraySize) {
        super(ctx);
        if (dataTypeName == null || dataTypeName.length() == 0) {
            ECLog.logFatal(ctx, "Data type name is not specified!");
        }
        dataType       = DataType.FromName(dataTypeName);
        this.arraySize = arraySize;
    }

    public static boolean isNativeDataType(String dataTypeName) {
        return DataType.FromName(dataTypeName) != null;
    }

    @ModelMethod(category = ModelMethodCategory.TYPE,
        description = "Indicates whether this native type is a `uuid`.")
    public boolean isUUIDType() {
        return dataType == DataType.UUID;
    }

    @ModelMethod(category = ModelMethodCategory.TYPE,
        description = "Returns the array size of this type if it is of type `byte[]``.")
    public int getArraySize() {
        return arraySize;
    }

    @ModelMethod(category = ModelMethodCategory.TYPE,
        description = "Indicates whether this is of a `byte[]` type but with a variable (not declared) array size.")
    public boolean isVariableSizeArray() {
        return (this.dataType == DataType.BYTE_ARRAY) && (arraySize == null);
    }

    @ModelMethod(category = ModelMethodCategory.TYPE,
        description = "Indicates whether this is of the specified data type.")
    public boolean isDataType(
        @ModelMethodParameter(description = "The data type with which to compare.")
            DataType dataType) {
        return this.dataType == dataType;
    }

    @ModelMethod(category = ModelMethodCategory.TYPE,
        description = "Returns the data type enum of this native type.")
    public DataType getDataType() {
        return dataType;
    }

    @ModelMethod(category = ModelMethodCategory.PRIMARY_KEY,
        description = "Indicates whether this native type object is being used in a primary key attribute.")
    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    @ModelMethod(category = ModelMethodCategory.TYPE,
        description = "Returns the string representation of this native type.")
    public String toString() {
        return dataType.getName();
    }

    public enum DataType {
        INT64("int64"),
        INT32("int32"),
        FLOAT("float"),
        DOUBLE("double"),
        BOOLEAN("boolean"),
        UUID("uuid"),
        STRING("string"),
        DATE("date"),
        ASSET("asset"),
        BYTE_ARRAY("byte");

        String name;

        DataType(String n) {
            name = n;
        }

        public static DataType FromName(String name) {
            for (DataType dt : DataType.values()) {
                if (dt.name.equals(name)) {
                    return dt;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.sql;

import org.entityc.compiler.model.entity.MTNativeType;
import org.entityc.compiler.model.entity.MTType;

public enum SSType {
    BOOLEAN("boolean"),
    INT("int"),
    BIGINT("bigint"),
    SERIAL("serial"),
    BIGSERIAL("bigserial"),
    FLOAT("real"),
    DOUBLE("double precision"),
    BYTEA("bytea"),
    TIMESTAMP("timestamp with time zone"),
    TEXT("text"),
    UUID("uuid");

    String name;

    SSType(String name) {
        this.name = name;
    }

    static SSType FromName(String name) {
        for (SSType type : SSType.values()) {
            if (type.name.equals(name)) {
                return type;
            }
        }
        return null;
    }

    public static SSType FromMTType(MTType mttype) {
        return FromMTType(mttype, false);
    }
    public static SSType FromMTType(MTType mttype, boolean isSerial) {
        if (mttype.isNativeType()) {
            MTNativeType nativeType = (MTNativeType) mttype;
            switch (nativeType.getDataType()) {
                case FLOAT:
                    return SSType.FLOAT;
                case DOUBLE:
                    return SSType.DOUBLE;
                case INT32: {
                    return isSerial ? SSType.SERIAL : SSType.INT;
                }
                case INT64:
                    if (((MTNativeType) mttype).isPrimaryKey() || isSerial) {
                        return SSType.BIGSERIAL;
                    } else {
                        return SSType.BIGINT;
                    }
                case UUID:
                    return SSType.UUID;
                case BOOLEAN:
                    return SSType.BOOLEAN;
                case ASSET:
                    return TEXT;
                case STRING:
                    return TEXT;
                case DATE:
                    return TIMESTAMP;
                case BYTE_ARRAY:
                    return BYTEA;
            }
        } else if (mttype.isEnumType()) {
            return SSType.INT;
        }
        return null;
    }

    public String getName() {
        return name;
    }
}

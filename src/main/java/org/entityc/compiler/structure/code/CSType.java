/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

import org.entityc.compiler.model.domain.MTDomain;
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTNativeType;
import org.entityc.compiler.model.entity.MTType;

import java.util.ArrayList;
import java.util.List;

public class CSType extends CSNode implements CSReferenceResolution {

    private CSNativeType     nativeType;
    private CSClass          classType;
    private CSEnum           enumType;
    private String           className; // until we can resolve to the class
    private String           entityName;
    private boolean          reference;
    private List<CSType>     templateArgumentTypes;
    private CSFunction       function;
    private CSCollectionType collectionType = CSCollectionType.NONE;

    public CSType(CSNativeType type) {
        this.nativeType = type;
    }

    public CSType(CSClass classType) {
        this.classType = classType;
    }

    public CSType(CSFunction function) {
        this.function = function;
    }

    public CSType(CSEnum enumType) {
        this.enumType = enumType;
    }

    public CSType(String className, String entityName) {
        this.className = className;
        this.entityName = entityName;
    }

    public CSType(String name) {
        this.className = name;
        this.entityName = name;
    }

    public static CSType FromMTType(MTNativeType type) {
        return FromMTType(type, false);
    }

    public static CSType FromMTType(MTNativeType type, boolean reference) {
        return FromMTType(null, type, reference);
    }

    public static CSType FromMTType(MTDomain domain, MTType type, boolean reference) {
        CSType              csType       = null;
        CSType.CSNativeType csNativeType = null;
        if (type.isNativeType()) {
            MTNativeType nativeType = (MTNativeType) type;
            switch (nativeType.getDataType()) {
                case INT32:
                    csNativeType = CSType.CSNativeType.INT32;
                    break;
                case INT64:
                    csNativeType = CSType.CSNativeType.INT64;
                    break;
                case FLOAT:
                    csNativeType = CSType.CSNativeType.FLOAT;
                    break;
                case ASSET:
                case STRING:
                    csNativeType = CSType.CSNativeType.STRING;
                    break;
                case BOOLEAN:
                    csNativeType = CSType.CSNativeType.BOOLEAN;
                    break;
                case DATE:
                    csNativeType = CSType.CSNativeType.DATE;
            }
            if (csNativeType != null) {
                csType = new CSType(csNativeType);
            }
        } else if (type.isEnumType()) {
            csType = new CSType(new CSEnum(domain, (MTEnum) type));
        }
        return csType;
    }

    public static CSType FromMTAttribute(MTDomain domain, MTAttribute attribute) {
        CSType csType = FromMTType(domain, attribute.getType());
        if (csType != null) {
            CSCollectionType collectionType = CSCollectionType.NONE;
            if (attribute.isArray()) {
                collectionType = attribute.isUnique() ? CSCollectionType.SET : CSCollectionType.LIST;
            }
            csType.setCollectionType(collectionType);
        }
        return csType;
    }

    public static CSType FromMTType(MTDomain domain, MTType type) {
        return FromMTType(domain, type, false);
    }

    public boolean isReference() {
        return reference;
    }

    public void setReference(boolean reference) {
        this.reference = reference;
    }

    public CSNativeType getNativeType() {
        return nativeType;
    }

    public boolean isClassType() {
        return classType != null;
    }

    public CSClass getClassType() {
        return classType;
    }

    public void setClassType(CSClass classType) {
        this.classType = classType;
    }

    public String getClassName() {
        if (classType != null) {
            return classType.getName();
        } else {
            return className;
        }
    }

    public boolean isFunctionType() {
        return function != null;
    }

    public CSFunction getFunction() {
        return function;
    }

    public String getEnumName() {
        if (isEnumType()) {
            return enumType.getName();
        }
        return null;
    }

    public boolean isEnumType() {
        return enumType != null;
    }

    public boolean isNativeType() {
        return this.nativeType != null;
    }

    public void setNativeType(CSNativeType nativeType) {
        this.nativeType = nativeType;
    }

    public boolean isNativeType(CSNativeType nativeType) {
        return this.nativeType == nativeType;
    }

    public void accept(CSVisitor visitor) {
    }

    @Override
    public boolean resolveReferences(CSTop top, int pass) {
        if (pass == 0) {
            if (className != null && className.length() > 0 && classType == null) {
                classType = top.getClassByName(className);
            }
        }
        return false;
    }

    public void addTemplateArgumentType(CSType argumentType) {
        if (templateArgumentTypes == null) {
            templateArgumentTypes = new ArrayList<>();
        }
        templateArgumentTypes.add(argumentType);
    }

    public List<CSType> getTemplateArgumentTypes() {
        return templateArgumentTypes;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public CSEnum getEnumType() {
        return enumType;
    }

    public CSCollectionType getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(CSCollectionType collectionType) {
        this.collectionType = collectionType;
    }

    public enum CSNativeType {
        VOID,
        INT64,
        INT32,
        FLOAT,
        DOUBLE,
        BOOLEAN,
        STRING,
        DATE,
        LIST,
        MAP,
    }

    public enum CSCollectionType {
        NONE,
        LIST,
        SET, // list with only unique entries
    }
}

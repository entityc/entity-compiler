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
import org.entityc.compiler.model.MTReferenceResolution;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.domain.MTNamed;
import org.entityc.compiler.model.expression.MTConstant;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@ModelClass(type = ModelClassType.ENTITY, description = "Represents an attribute of an entity.")
public class MTAttribute extends MTNode implements MTReferenceResolution, MTNamed, MTTemplateSupport {

    private final List<MTBitField>            bitFields   = new ArrayList<>();
    private final List<MTAttributeConstraint> constraints = new ArrayList<>();
    private final String                      name;
    private final String                      typeName;
    private       String                      entityName;
    private       MTEntity                    entity;
    private       MTType                      type;
    private       MTUnit                      unit;
    private       String                      unitName;
    private       int                         order;
    private       boolean                     primaryKey;
    private       boolean                     unique;
    private       boolean                     nullable;
    private       boolean                     creation;
    private       boolean                     modification;
    private       boolean                     array;
    private       boolean                     ordered;
    private       boolean                     parent;
    private       boolean                     virtual;
    private       boolean                     sequential;
    private       boolean                     humanReadableIdentification;
    private       String                      contentType;
    private       int                         lastLowBit  = 0;
    private       MTRelationship              relationship;
    private       MTConstant                  defaultValue;

    public MTAttribute(ParserRuleContext ctx, MTEntity entity, String typeName, String name) {
        this(ctx, entity, typeName, name, null, null);
    }

    public MTAttribute(ParserRuleContext ctx, MTEntity entity, String typeName, String name, String unitName, Integer arraySize) {
        super(ctx);
        this.entity     = entity;
        this.entityName = entity.getName();
        this.typeName   = typeName;
        this.unitName   = unitName;
        this.order      = entity != null ?
                          entity.getPrimaryAttributeCount() :
                          0;
        if (MTNativeType.isNativeDataType(typeName)) {
            this.type = new MTNativeType(ctx, typeName, arraySize);
        }
        this.name = name;
    }

    public MTAttribute(ParserRuleContext ctx, MTEntity entity, MTType type, String name) {
        super(ctx);
        this.entity     = entity;
        this.entityName = entity.getName();
        this.type       = type;
        this.name       = name;
        this.typeName   = type != null ?
                          type.toString() :
                          null;
    }

    public MTAttribute(ParserRuleContext ctx, String entityName, String typeName, String name) {
        super(ctx);
        this.entityName = entityName;
        this.typeName   = typeName;
        this.name       = name;
    }

    protected MTAttribute(String secondaryName, MTAttribute primaryAttribute) {
        super(primaryAttribute.getParserRuleContext());
        this.name                        = secondaryName;
        this.description = primaryAttribute.getDescription();
        this.setSummary(primaryAttribute.getSummary());;
        this.setDetail(primaryAttribute.getDetail());;
        this.entityName                  = primaryAttribute.entityName;
        this.entity                      = primaryAttribute.entity;
        this.unit                        = primaryAttribute.unit;
        this.unitName                    = primaryAttribute.unitName;
        this.order                       = primaryAttribute.order;
        this.primaryKey                  = primaryAttribute.primaryKey;
        this.unique                      = primaryAttribute.unique;
        this.nullable                    = primaryAttribute.nullable;
        this.creation                    = primaryAttribute.creation;
        this.modification                = primaryAttribute.modification;
        this.array                       = primaryAttribute.array;
        this.ordered                     = primaryAttribute.ordered;
        this.parent                      = primaryAttribute.parent;
        this.virtual                     = primaryAttribute.virtual;
        this.sequential                  = primaryAttribute.sequential;
        this.humanReadableIdentification = primaryAttribute.humanReadableIdentification;
        this.contentType                 = primaryAttribute.contentType;
        this.lastLowBit                  = primaryAttribute.lastLowBit;
        this.relationship                = primaryAttribute.relationship;
        this.defaultValue                = primaryAttribute.defaultValue;
        this.type                        = primaryAttribute.type;
        this.typeName                    = primaryAttribute.typeName;
        this.bitFields.addAll(primaryAttribute.bitFields);
        this.constraints.addAll(primaryAttribute.constraints);
    }

    static public MTAttribute Copy(MTAttribute fromAttribute, MTEntity toEntity) {
        MTAttribute attribute = new MTAttribute(fromAttribute.getName(), fromAttribute);

        attribute.entityName = toEntity.getName();
        attribute.entity     = toEntity;

        return attribute;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description = "Returns the name of this attribute as it was declared in the model.")
    @Override
    public String getName() {
        return name;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description =
                    "Returns the data type of this attribute. The type can be a native type (such as an int32, string, etc.) "
                    + "or can be a secondary entity. If you are looking for it to be a secondary entity, it would be better "
                    + "to use the `isSecondaryEntityType` property then `typeEntity` if it is a secondary entity.")
    public MTType getType() {
        return type;
    }

    public void gatherSecondaries(HashMap<String, MTSecondaryAttribute> secondaryAttributeMap, HashMap<String, MTSecondaryRelationship> secondaryRelationshipMap, Stack<MTAttribute> runningPath) {
        if (!isSecondaryEntityType()) {
            if (runningPath.size() == 0) {
                return;
            }
            // we found a leaf primary attribute
            Stack<MTAttribute> path = new Stack<>();
            path.addAll(runningPath);
            path.push(this);
            String fullPathString = String.join(".",
                                                path.stream().map(attr -> attr.getName()).collect(Collectors.toList()));
            MTSecondaryAttribute secondaryAttribute = new MTSecondaryAttribute(fullPathString, path);
            secondaryAttributeMap.put(fullPathString, secondaryAttribute);
            return;
        }
        MTEntity secondaryEntity = getTypeEntity();
        runningPath.push(this);
        secondaryEntity.gatherSecondaries(secondaryAttributeMap, secondaryRelationshipMap, runningPath);
        runningPath.pop();
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description = "Indicates whether the the data type of this attribute is a secondary entity.")
    public boolean isSecondaryEntityType() {
        if (type == null) {
            ECLog.logFatal(this, "Attribute named \"" + name + "\" has invalid type \"" + typeName + "\"");
        }
        return type.isEntityType() && ((MTEntity) type).isSecondary();
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description =
                    "If the data type of this attribute is a secondary entity, then it returns that secondary entity. "
                    + "Otherwise it returns `null`.")
    public MTEntity getTypeEntity() {
        return type.isEntityType() ?
               (MTEntity) type :
               null;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description = "Indicates whether this attribute is an attribute of a secondary entity.")
    public boolean isSecondary() {
        return false; // this is overridden by MTSecondaryAttribute as true
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description =
                    "Indicates whether this attribute has been declared `virtual`. A virtual attribute is one that "
                    + "is not persistent and is backed by code to create its value - code that a developer writes.")
    public boolean isVirtual() {
        return virtual;
    }

    public void setVirtual(boolean virtual) {
        this.virtual = virtual;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description = "Returns an optional default value declaration of the attribute.")
    public MTConstant getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(MTConstant defaultValue) {
        this.defaultValue = defaultValue;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description =
                    "Indicates whether this attribute has been declared `optional`. When an attribute is declared as "
                    + "`optional` it means that it can be null. For secondary entities, if the instantiation of "
                    + "a secondary entity is declared as `optional` then all its attributes are nullable in that "
                    + "context.")
    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description =
                    "Returns a string that represents the textual representation of the data type for this attribute. This is not intended to be used to generate code - its best to use the `getType()` method "
                    + "and send it through the `language` filter which will result in a language specific "
                    + "representation of the type (assuming you have the language definition for your target language). "
                    + "You can also use this for debug purposes.")
    public String getTypeName() {
        return typeName;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description = "Returns the parent entity of this attribute.")
    public MTEntity getEntity() {
        return entity;
    }

    public void setEntity(MTEntity entity) {
        this.entity = entity;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description = "Returns the name of the parent entity of this attribute as it was declared in the model.")
    public String getEntityName() {
        return entityName;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description = "Returns the declared unit of this attribute as a unit object.")
    public MTUnit getUnit() {
        return unit;
    }

    public void setUnit(MTUnit unit) {
        this.unit = unit;
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
            description = "When an attribute is created for the purpose of returning the effective set of "
                          + "attributes, attributes created due to a relationship, then this returns the relationship that "
                          + "this attribute represents. For example, if you have a many-to-one relationship, an effective "
                          + "attribute will be created to contain the unique ID of the other end of this relationship.")
    public MTRelationship getRelationship() {
        return relationship;
    }

    public void setRelationship(MTRelationship relationship) {
        this.relationship = relationship;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description =
                    "Indicates whether this attribute was declared as `unique`. If so, the database should be configured "
                    + "or code should be synthesized so as to assure that all objects of its entity will have a unique "
                    + "value across all values of this attribute.")
    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description =
                    "Indicates whether this attribute was declared with the `creation` qualifier. This should only "
                    + "occur with a `date` attribute that represents the creation date for objects of this entity.")
    public boolean isCreation() {
        return creation;
    }

    public void setCreation(boolean creation) {
        this.creation = creation;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description =
                    "Indicates whether this attribute was declared with the `modification` qualifier. This should only "
                    + "occur with a `date` attribute that represents the last modification date for objects of this "
                    + "entity.")
    public boolean isModification() {
        return modification;
    }

    public void setModification(boolean modification) {
        this.modification = modification;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description = "Indicates whether this attribute was declared using the `many` keyword.")
    public boolean isArray() {
        return array;
    }

    public void setArray(boolean array) {
        this.array = array;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description =
                    "Indicates whether this attribute was declared using the `ordered` keyword. This only applies "
                    + "to array type attributes (that is, have been declared with `many` also). Using the `ordered` "
                    + "keyword should cause this attribute to have a field in the database to keep track of the order "
                    + "for each item in the array of objects for this attribute.")
    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description =
                    "Indicates whether this attribute was declared using the `parent` keyword. Attributes declared "
                    + "this way is intended to indicate that objects of this entity will likely be grouped by "
                    + "this attribute. Its up to the templates to look at this flag and synthesize appropriate "
                    + "code to provide the grouped representation.")
    public boolean isParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description =
                    "Indicates whether this attribute was declared using the `sequential` keyword. When you use this "
                    + "keyword it means you want this attribute to be auto incremented for each new object of its "
                    + "entity that is created.")
    public boolean isSequential() {
        return sequential;
    }

    public void setSequential(boolean sequential) {
        this.sequential = sequential;
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
            description =
                    "For attributes that were created from relationships (effective attributes), this indicates whether "
                    + "the relationship it was created from is declared as a `parent` relationship.")
    public boolean isParentRelationship() {
        return relationship != null && relationship.isParent();
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description =
                    "Indicates whether this attribute was declared with a default value. If so, you can obtain the "
                    + "default value from the `defaultValue` property of this attribute.")
    public boolean hasDefaultValue() {
        return defaultValue != null;
    }

    public void addBitField(MTBitField bitField) {
        bitField.setLow(lastLowBit);
        lastLowBit += bitField.getWidth();

        bitFields.add(bitField);
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description = "Returns the bit fields declared as part of this attribute.")
    public List<MTBitField> getBitFields() {
        return bitFields;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description = "Indicates whether this attribute was declared with bit fields.")
    public boolean hasBitFields() {
        return bitFields.size() > 0;
    }

    @ModelMethod(category = ModelMethodCategory.PRIMARY_KEY,
            description =
                    "An attribute that represents a primary key has a type that indicates it is a primary key. This "
                    + "method returns the type but with the primary key indicator set to false.")
    public MTType getNonPrimaryKeyType() {
        if (!type.isNativeType()) {
            return type;
        }
        MTNativeType nativeType = (MTNativeType) type;
        MTType       newType    = new MTNativeType(null, nativeType.getDataType());
        ((MTNativeType) newType).setPrimaryKey(false);
        return newType;
    }

    @ModelMethod(category = ModelMethodCategory.PRIMARY_KEY,
            description = "Indicates if this attribute represents the primary key of its entity.")
    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
        if (type instanceof MTNativeType) {
            ((MTNativeType) type).setPrimaryKey(primaryKey);
        }
    }

    public void accept(MTVisitor visitor) {

    }

    public void checkValidReferences(MTSpace space) {
        if (entity == null) {
            ECLog.logFatal(this, "REFERR: Not able to find entity \"" + entityName + "\" for attribute: " + name);
        }
        if (type == null) {
            ECLog.logFatal(this, "REFERR: Not able to find type \"" + typeName + "\" for attribute: " + name);
        }
        if (unitName != null && unit == null) {
            ECLog.logFatal(this, "REFERR: Not able to find unit \"" + unitName + "\" for attribute: " + name);
        }
    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {

        boolean anotherPass = false;

        if (entity == null) {
            entity = space.getEntityWithName(entityName);
            if (entity == null) {
                return true;
            }
        }
        if (type == null) {
            if (MTNativeType.isNativeDataType(typeName)) {
                type = new MTNativeType(null, typeName);
            } else {
                anotherPass = true;
            }
        }
        if (unitName != null && unit == null) {
            unit = MTUnit.UnitWithName(unitName);
            if (pass > 7 && unit == null) {
                ECLog.logFatal(this, "Specified unit is not supported: " + unitName);
            }
            anotherPass = true;
        }
        if (defaultValue != null) {
            anotherPass = defaultValue.resolveReferences(space, pass);
        }
        if (type != null) {
            return type.resolveReferences(space, pass) || anotherPass;
        }

        // try to find type by name
        // • Look for enums
        // • Look for other entities with the type name
        //   but if they have a primary key, flag an error - those belong under relationships

        MTEnum mtEnum = space.getEnumWithName(typeName);
        if (mtEnum != null) {
            type = mtEnum;
            return anotherPass;
        }

        MTTypedef typedef = space.getTypedefWithName(typeName);
        if (typedef != null) {
            type = typedef;
            return anotherPass;
        }

        MTEntity otherEntity = space.getEntityWithName(typeName);
        if (otherEntity != null) {
            if (!this.entity.isView() && otherEntity.hasPrimaryKey()) {
                ECLog.logFatal(this, "An attribute cannot be of an entity type unless the entity has no primary key.");
                return anotherPass;
            }

            if (otherEntity.getName().equals(entity.getName())) {
                ECLog.logFatal(this, "ERROR: An attribute cannot be of its own entity type.");
                return anotherPass;
            }

            type = otherEntity;
        }
        return anotherPass;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description =
                    "For attributes that represent an asset and have been defined with a content type, this will return "
                    + "that defined content type.")
    public String getContentType() {
        return contentType != null ?
               contentType :
               "text/plain";
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description =
                    "Indicates whether an attribute has been defined using **both** the `unique` and `parent` keywords. "
                    + "This can be used to establish unique constraints such that objects of this entity must have "
                    + "unique values of this attribute per parent object.")
    public boolean isParentUnique() {
        return parent && unique;
    }

    public void addConstraint(MTAttributeConstraint constraint) {
        this.constraints.add(constraint);
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description = "Returns an optional list of constraints defined for this attribute.")
    public List<MTAttributeConstraint> getConstraints() {
        return constraints;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description = "Indicates whether this attribute has any defined constraints.")
    public boolean hasConstraints() {
        return constraints.size() > 0;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description = "Returns the order in which this attribute was declared among the other attributes of this entity.")
    public int getOrder() {
        return order;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description = "Indicates whether this attribute has been defined with a unit.")
    public boolean hasUnit() {
        return unit != null;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
            description =
                    "For attributes that are of a secondary entity type, this indicates whether the attribute as been "
                    + "tagged with the specified tag..")
    public boolean secondaryEntityIsTagged(
            @ModelMethodParameter(description = "The tag with which to check.")
            String tag) {
        if (!isSecondaryEntityType()) {
            return false;
        }
        return type.hasTag(tag);
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description =
                    "For those attributes that are defined as the `byte` data type, this returns the size of an array "
                    + "that is holding values for this attribute.")
    public Integer getByteArraySize() {
        if (type.isByteArrayType()) {
            return ((MTNativeType) type).getArraySize();
        }
        return null;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description = "Indicates whether this attribute has been declared as `optional`.")
    public boolean isOptional() {
        return nullable;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
            description = "Returns the space associated with this attribute. This property is provided more out of "
                          + "convenience as you can also obtain this from its entity.")
    public MTSpace getSpace() {
        if (entity != null) {
            return entity.getSpace();
        }
        return null;
    }
}

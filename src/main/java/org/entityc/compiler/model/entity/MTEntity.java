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
import org.entityc.compiler.model.MTModule;
import org.entityc.compiler.model.MTReferenceResolution;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.domain.MTDERelationship;
import org.entityc.compiler.model.domain.MTNamed;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.util.ECLog;
import org.entityc.compiler.util.ECStringUtil;

import java.util.*;
import java.util.stream.Collectors;

import static org.entityc.compiler.doc.annotation.ModelMethodCategory.ENTITY_TEMPLATE;

@ModelClass(type = ModelClassType.ENTITY, description = "Represents an entity in your model.")
public class MTEntity extends MTType implements MTReferenceResolution, MTNamed, MTTemplateSupport {

    protected final Map<String, MTEnum>                      enumMap                   = new HashMap<>();
    protected final Map<String, MTAttribute>                 attributeMap              = new HashMap<>();
    protected final List<MTRelationship>                     relationships             = new ArrayList<>();
    protected final List<MTRelationship>                     implicitRelationships     = new ArrayList<>();
    protected final List<MTAttribute>                        primaryAttributes         = new ArrayList<>();
    protected final MTModule                                 module;
    protected final String                                   name;
    private final   List<MTUniqueness>                       uniquenesses              = new ArrayList<>();
    private final   Map<String, Integer>                     fieldNumbers              = new HashMap<>();
    protected       HashMap<String, MTSecondaryAttribute>    secondaryAttributeMap     = null;
    protected       HashMap<String, MTSecondaryRelationship> secondaryRelationshipMap  = null;
    protected       MTPrimaryKey                             primaryKey;
    private         boolean                                  implicit                  = false;
    private         boolean                                  transientType             = false;
    private         boolean                                  batchable                 = false;
    private         boolean                                  extern                    = false;
    private         boolean                                  declaredAsPrimary;
    private         boolean                                  declaredAsSecondary;
    private         boolean                                  abstractEntity;
    private         boolean                                  primaryParentRelationship = false;
    private         String                                   templateName; // if created from an entity template
    private         int                                      largestFieldNumber        = 0;
    private         MTSpace                                  space;
    private         MTAttribute                              manyAttribute             = null; // if the entity was created from a "many <Entity> attributeName"
    protected       Set<String>                              realms = new HashSet<>(); // a space entities can live in separate from the others - such as for composite entities

    public MTEntity(ParserRuleContext ctx, MTModule module, String name) {
        super(ctx);
        this.module = module;
        this.name   = name;
        if (!Character.isUpperCase(name.charAt(0))) {
            ECLog.logFatal(ctx, "Entity names must start with an uppercase character.");
        }
    }

    public MTEntity(ParserRuleContext ctx, MTAttribute manyAttribute) {
        super(ctx);
        this.module        = manyAttribute.getEntity().getModule();
        this.name          = manyAttribute.getEntity().getName() + ECStringUtil.Capitalize(manyAttribute.getName());
        this.manyAttribute = manyAttribute;
    }

    @ModelMethod(category = ModelMethodCategory.MODULE,
        description = "Returns the module associated with this entity.")
    public MTModule getModule() {
        return module;
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY,
        description = "Returns the name of this entity as it was declared in the model.")
    @Override
    public String getName() {
        return name;
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY,
        description = "Adds the entity to a realm.")
    public void addRealm(String realm) {
        //ECLog.logInfo("Added entity: " + getName() + " to realm " + realm);
        realms.add(realm);
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY,
        description = "Returns true if this entity is part of a realm.")
    public boolean isInRealm(String realm) {
        return this.realms.contains(realm);
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY,
        description = "Returns true if this entity is a composite entity.")
    public boolean isCompositeEntity() {
        return this instanceof MTCompositeEntity;
    }

    public static MTEntity AddImplicitManyToManyEntity(MTSpace space, MTEntity fromEntity, MTEntity toEntity) {
        MTEntity combinedEntity       = CombineEntities(fromEntity, toEntity);
        MTEntity alreadyCreatedEntity = space.getEntityWithName(combinedEntity.getName());
        if (alreadyCreatedEntity != null) {
            return alreadyCreatedEntity; // already created by the other direction
        }
        //ECLog.logInfo("CREATING MANY TO MANY TABLE FOR: " + fromEntity.getName() + " + " + toEntity.getName());
        combinedEntity.setImplicit(true);
        combinedEntity.setDeclaredAsPrimary(true);
        space.addImplicitEntity(combinedEntity);
        MTRelationship fromRelationship = new MTRelationship(null,
                                                             ECStringUtil.Uncapitalize(fromEntity.getName()),
                                                             combinedEntity.getName(),
                                                             HalfRelationshipPlurality.ONE, fromEntity.getName(),
                                                             false, false, null, null, null);
        MTRelationship fromReverseRelationship = new MTRelationship(null,
                                                                    ECStringUtil.Uncapitalize(combinedEntity.getName()),
                                                                    fromEntity.getName(),
                                                                    HalfRelationshipPlurality.MANY,
                                                                    combinedEntity.getName(), false, false, null, null,
                                                                    null);
        fromRelationship.setImplicit(true);
        fromReverseRelationship.setImplicit(true);
        combinedEntity.addRelationship(fromRelationship);
        fromEntity.addRelationship(fromReverseRelationship);
        MTRelationship toRelationship = new MTRelationship(null, ECStringUtil.Uncapitalize(toEntity.getName()),
                                                           combinedEntity.getName(),
                                                           HalfRelationshipPlurality.ONE, toEntity.getName(),
                                                           false, false, null, null, null);
        MTRelationship toReverseRelationship = new MTRelationship(null,
                                                                  ECStringUtil.Uncapitalize(combinedEntity.getName()),
                                                                  toEntity.getName(), HalfRelationshipPlurality.MANY,
                                                                  combinedEntity.getName(), false, false, null, null,
                                                                  null);
        toRelationship.setImplicit(true);
        toReverseRelationship.setImplicit(true);
        combinedEntity.addRelationship(toRelationship);
        toEntity.addRelationship(toReverseRelationship);
        if (fromEntity.getModule() != null) {
            fromEntity.getModule().addEntity(combinedEntity);
        }
        combinedEntity.addTag(MTRelationship.INTERNAL_MANY_TO_MANY_TAG);

        // resolve references since we did this after the big resolve loop
        combinedEntity.resolveReferences(space, 0);
        fromEntity.resolveReferences(space, 0);
        toEntity.resolveReferences(space, 0);

        return combinedEntity;
    }

    public static MTEntity CombineEntities(MTEntity entity1, MTEntity entity2) {
        String   combinedName   = CombinedEntityName(entity1, entity2);
        MTEntity combinedEntity = new MTEntity(null, entity1.getModule(), combinedName);
        return combinedEntity;
    }

    public void addRelationship(MTRelationship relationship) {
        if (this.isImplicit() || relationship.isImplicit()) {
            relationship.setImplicit(true);
            implicitRelationships.add(relationship);
            return;
        }
        if (relationship.isParent() && !relationship.isOptional()) {
            primaryParentRelationship = true;
        }
        relationships.add(relationship);
    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        boolean needsAnotherPass = false;

        for (MTAttribute attribute : getAttributes()) {
            if (attribute.resolveReferences(space, pass)) {
                needsAnotherPass = true;
            }
        }
        for (MTRelationship relationship : getRelationships()) {
            if (relationship.resolveReferences(space, pass)) {
                needsAnotherPass = true;
            }
        }

        for (MTRelationship relationship : getImplicitRelationships()) {
            if (relationship.resolveReferences(space, pass)) {
                needsAnotherPass = true;
            }
        }

        return needsAnotherPass;
    }

    public static String CombinedEntityName(MTEntity entity1, MTEntity entity2) {
        // make sure the name is predictable every time by using
        // the names themselves to determine the ordering
        ArrayList<String> sortedNames = new ArrayList<>();
        sortedNames.add(entity1.getName());
        sortedNames.add(entity2.getName());
        sortedNames.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        return sortedNames.get(0) + sortedNames.get(1) + "Many";
    }

    @ModelMethod(
        category = ModelMethodCategory.ENTITY,
        description =
            "Indicates whether this entity was created by the compiler because it represents an implicit entity, "
            + "such as in a many-to-many relationship.")
    public boolean isImplicit() {
        return implicit;
    }

    public void setImplicit(boolean implicit) {
        this.implicit = implicit;
    }

    @ModelMethod(
        category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns the list of attributes of this entity.")
    public List<MTAttribute> getAttributes() {
        return primaryAttributes;
    }

    @ModelMethod(
        category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns the list of relationships of this entity.")
    public List<MTRelationship> getRelationships() {
        return relationships;
    }

    @ModelMethod(
        category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns the list of implicit relationships, where an implicit relationship is one that is to " +
                      "an implicit entity. An implicit entity is created by the compiler when it transforms a " +
                      "many-to-many relationship into this implicit entity along with implicit one-to-many and " +
                      "many-to-one relationships.")
    public List<MTRelationship> getImplicitRelationships() {
        return implicitRelationships;
    }

    public MTAttribute getManyAttribute() {
        return manyAttribute;
    }

    public HashMap<String, MTSecondaryAttribute> getSecondaryAttributeMap() {
        return secondaryAttributeMap;
    }

    public HashMap<String, MTSecondaryRelationship> getSecondaryRelationshipMap() {
        return secondaryRelationshipMap;
    }

    // TODO Needs to be cached
    @ModelMethod(
        category = ModelMethodCategory.ATTRIBUTE,
        description = "This attribute list includes not just those attributes directly declared in its entity but "
                      + "also those attributes declared in instantiated secondary entities.")
    public List<MTAttribute> getFlatAttributeList() {
        List<MTAttribute> flatList = new ArrayList<>(primaryAttributes);
        flatList.addAll(secondaryAttributeMap.values());
        flatList.sort(new Comparator<MTAttribute>() {
            @Override
            public int compare(MTAttribute o1, MTAttribute o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return flatList;
    }

    // TODO Needs to be cached
    @ModelMethod(
        category = ModelMethodCategory.ATTRIBUTE,
        description = "This list contains all the attribute names of not just the directly declared attributes "
                      + "in its entity but also those of instantiated secondary entities.")
    public List<String> getFlatAttributeNameList() {
        ArrayList<String> fullNameList = new ArrayList<>();
        fullNameList.addAll(primaryAttributes.stream().map(attr -> attr.getName()).collect(Collectors.toList()));
        fullNameList.addAll(secondaryAttributeMap.keySet());
        return fullNameList.stream().sorted().collect(Collectors.toList());
    }

    @ModelMethod(
        category = ModelMethodCategory.ATTRIBUTE,
        description =
            "Returns an attribute by its full name, where the full name is with regards to secondary entities "
            + "being used as attributes. For instance, If an entity instantiates a secondary entity named "
            + "say `Profile` as `profile`, and that secondary entity instantiates another secondary entity "
            + "named `Picture` as `picture` which has an attribute say `size`, then the full name of the `size` "
            + "attribute would be `profile.picture.size`. So this method is called on the top primary entity.")
    public MTAttribute attributeByFullName(
        @ModelMethodParameter(description = "The full name of the attribute based on its secondary entity hierarchy.")
            String fullName) {
        MTSecondaryAttribute secondaryAttribute = secondaryAttributeMap.get(fullName);
        if (secondaryAttribute != null) {
            return secondaryAttribute.getLeafAttribute();
        }
        return attributeMap.get(fullName);
    }

    public List<MTRelationship> getFlatRelationshipList() {
        List<MTRelationship> flatList = new ArrayList<>(relationships);
        flatList.addAll(secondaryRelationshipMap.values());
        flatList.sort(new Comparator<MTRelationship>() {
            @Override
            public int compare(MTRelationship o1, MTRelationship o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return flatList;
    }

    @ModelMethod(
        category = ModelMethodCategory.ENTITY,
        description =
            "Indicates whether an entity was declared with the `extern` keyword. This would happen if reference "
            + "is made to an entity but the entity is not implemented in this application space.")
    public boolean isExtern() {
        return extern;
    }

    public void setExtern(boolean extern) {
        this.extern = extern;
    }

    // TODO: Consider deleting
    public boolean isBatchable() {
        return batchable;
    }

    // TODO: Consider deleting
    public void setBatchable(boolean batchable) {
        this.batchable = batchable;
    }

    @ModelMethod(
        category = ENTITY_TEMPLATE,
        description = "If this entity was created from the declaration of an entity template, then this will "
                      + "return the name of that entity template.")
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public boolean isFromTemplate(String name) {
        return templateName != null && templateName.equals(name);
    }

    @ModelMethod(
        category = ModelMethodCategory.ENTITY,
        description = "Returns the space in which this entity was declared.")
    public MTSpace getSpace() {
        return space;
    }

    public void setSpace(MTSpace space) {
        this.space = space;
    }

    @ModelMethod(
        category = ModelMethodCategory.ENTITY,
        description = "Indicates whether this entity was declared `transient`. Transient entities are those which have "
                      + "objects at runtime but are not persisted in any way.")
    public boolean isTransient() {
        return transientType;
    }

    public void setTransient(boolean transientType) {
        this.transientType = transientType;
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY_TEMPLATE,
        description = "Indicates whether this entity was made as a result of expanding an entity template.")
    public boolean isMadeFromTemplate() {
        return this.templateName != null;
    }

    @ModelMethod(
        category = ModelMethodCategory.ENTITY,
        description = "Indicates whether this entity was declared `primary` or if it was inferred as primary. If an "
                      + "entity has a primary key it is inferred to be a primary entity.")
    public boolean isPrimary() {
        return isDeclaredAsPrimary() || (primaryKey != null && primaryKey.getAttributes().size() > 0) || implicit;
    }

    @ModelMethod(
        category = ModelMethodCategory.ENTITY,
        description = "Indicates whether this entity was **declared** `primary`. If the entity was **not** declared "
                      + "with the `primary` keyword then this will return false even if it is implied as primary.")
    public boolean isDeclaredAsPrimary() {
        return declaredAsPrimary;
    }

    public void setDeclaredAsPrimary(boolean declaredAsPrimary) {
        this.declaredAsPrimary = declaredAsPrimary;
    }

    public void addEnum(MTEnum mtEnum) {
        enumMap.put(mtEnum.getName(), mtEnum);
    }

    @ModelMethod(category = ModelMethodCategory.ENUM,
        description = "Returns an enum declared in this entity by a specified name.")
    public MTEnum getEnum(
        @ModelMethodParameter(description = "The name of the enum to return.")
            String name) {
        return enumMap.get(name);
    }

    @ModelMethod(
        category = ModelMethodCategory.ENUM,
        description = "Returns a list of enums that have been declared in this entity.")
    public List<MTEnum> getEnums() {
        return new ArrayList<>(enumMap.values());
    }

    public void addAttribute(MTAttribute attribute) {
        //ECLog.logInfo(">>> Adding attribute: " + attribute.getName());
        primaryAttributes.add(attribute);
        attributeMap.put(attribute.getName(), attribute);
    }

    public void accept(MTVisitor visitor) {
        visitor.visit(primaryKey);
        for (MTAttribute attribute : getAttributes()) {
            visitor.visit(attribute);
        }
        for (MTRelationship relationship : getRelationships()) {
            visitor.visit(relationship);
        }
        for (MTRelationship relationship : getImplicitRelationships()) {
            visitor.visit(relationship);
        }
    }

    @ModelMethod(
        category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns the number of attributes declared in this entity.")
    public int getAttributeCount() {
        return attributeMap.size();
    }

    @ModelMethod(
        category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns a list of attributes that are either directly declared or are inferred to be present "
                      + "from its relationships. An attribute is inferred if its associated relationship is **to** a single "
                      + "entity (one-to-one or many-to-one) and will be the same type as the primary key of that other "
                      + "entity. Essentially it is intended to contain the unique ID of the other entity object much like "
                      + "a foreign key column in an SQL database.")
    public List<MTAttribute> getEffectiveAttributes() {
        List<MTAttribute> effectiveAttributes = new ArrayList<>();
        if (this.hasPrimaryKey()) {
            effectiveAttributes.addAll(this.getPrimaryKey().getAttributes());
        }
        List<MTAttribute> attributes = this.getAttributes();
        for (MTAttribute attribute : attributes) {
            effectiveAttributes.add(attribute);
        }
        List<MTRelationship> relationships = this.getRelationships();

        for (MTRelationship relationship : relationships) {
            if (relationship.getEffectiveAttribute() != null) {
                effectiveAttributes.add(relationship.getEffectiveAttribute());
            }
        }
        return effectiveAttributes;
    }

    @ModelMethod(
        category = ModelMethodCategory.PRIMARY_KEY,
        description = "Indicates whether this entity is declared to have a primary key attribute.")
    public boolean hasPrimaryKey() {
        return primaryKey != null && primaryKey.getAttributes().size() > 0;
    }

    @ModelMethod(
        category = ModelMethodCategory.PRIMARY_KEY,
        description = "Returns the primary key associated with this entity.")
    public MTPrimaryKey getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(MTPrimaryKey primaryKey) {
        this.primaryKey = primaryKey;
    }

    public int getPrimaryAttributeCount() {
        return attributeMap.size();
    }

    @ModelMethod(
        category = ModelMethodCategory.ATTRIBUTE,
        description = "Indicates whether this entity defines any bit fields.")
    public boolean hasBitFields() {
        for (MTAttribute attribute : attributeMap.values()) {
            if (attribute.hasBitFields()) {
                return true;
            }
        }
        return false;
    }

    @ModelMethod(
        category = ModelMethodCategory.RELATIONSHIP,
        description = "Indicates whether this entity defines any relationships.")
    public boolean hasRelationships() {
        return relationships.size() > 0 || (secondaryRelationshipMap != null && secondaryRelationshipMap.size() > 0);
    }

    @ModelMethod(
        category = ModelMethodCategory.RELATIONSHIP,
        description = "Indicates whether this entity has any implicit relationships. Implicit means undeclared "
                      + "relationships that are inferred as a result of declared relationships in other entities.")
    public boolean hasImplicitRelationships() {
        return implicitRelationships.size() > 0;
    }

    @ModelMethod(
        category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns the list of parent relationships of this entity. A parent relationship is one that "
                      + "is declared with the `parent` keyword to indicate that their identity is fully or partially "
                      + "scoped by this parent entity.")
    public List<MTRelationship> getParentRelationships() {
        ArrayList<MTRelationship> parentRelationships = new ArrayList<>();
        for (MTRelationship relationship : relationships) {
            if (relationship.isParent()) {
                parentRelationships.add(relationship);
            }
        }
        return parentRelationships;
    }

    @ModelMethod(
        category = ModelMethodCategory.RELATIONSHIP,
        description = "Indicates whether it has a least one relationship declared as parent.")
    public boolean hasParentRelationship() {
        for (MTRelationship relationship : relationships) {
            if (relationship.isParent()) {
                return true;
            }
        }
        return false;
    }


    @ModelMethod(
            category = ModelMethodCategory.RELATIONSHIP,
            description = "Indicates whether it has a least one relationship declared as parent to the specified entity.")
    public boolean hasParentRelationshipToEntity(MTEntity parentEntity) {
        for (MTRelationship relationship : relationships) {
            if (relationship.isParent() && relationship.getTo().getEntityName().equals(parentEntity.name)) {
                return true;
            }
        }
        return false;
    }

    @ModelMethod(
        category = ModelMethodCategory.RELATIONSHIP,
        description = "Indicates whether all relationships of this entity that are declared as `parent` are also declared as `optional`.")
    public boolean allParentRelationshipsOptional() {
        for (MTRelationship relationship : relationships) {
            if (relationship.isParent() && !relationship.isOptional()) {
                return false;
            }
        }
        return true;
    }

    @ModelMethod(
        category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns a relationship of this entity that is declared as `parent` and **not** declared as "
                      + "`optional`.")
    public MTRelationship getSingleParentRelationship() {
        for (MTRelationship relationship : relationships) {
            if (relationship.isParent() && !relationship.isOptional()) {
                return relationship;
            }
        }
        return null;
    }

    @ModelMethod(
        category = ModelMethodCategory.PRIMARY_KEY,
        description = "Returns the data type of the primary key of this entity. If the entity does not have a primary "
                      + "key then `null` is returned.")
    public MTType getPkType() {
        MTAttribute pkAttribute = getPrimaryKeyAttribute();
        return pkAttribute != null ?
               pkAttribute.getType() :
               null;
    }

    @ModelMethod(
        category = ModelMethodCategory.PRIMARY_KEY,
        description = "Returns the attribute of the primary key for this entity. If the entity does not have a primary "
                      + "key then `null` is returned.")
    public MTAttribute getPrimaryKeyAttribute() {
        if (primaryKey == null) {
            return null;
            //ECLog.logFatal("Entity \"" + name + "\" does not have a primary key!");
        }
        if (primaryKey.getAttributes().size() != 1) {
            return null;
        }
        return primaryKey.getAttributes().get(0);
    }

    public void checkValidReferences(MTSpace space) {
        for (MTAttribute attribute : getAttributes()) {
            attribute.checkValidReferences(space);
        }
        if (!isSecondary()) {
            mapoutSecondaries();
        }
    }

    @ModelMethod(
        category = ModelMethodCategory.ENTITY,
        description =
            "Indicates whether this entity is a secondary entity, either because it is declared as one or implied. If "
            + "the entity does not have a primary key, then it is implied to be a secondary entity.")
    public boolean isSecondary() {
        return isDeclaredAsSecondary() || (!isDeclaredAsPrimary()
                                           && !implicit
                                           && !transientType
                                           && (primaryKey == null || primaryKey.getAttributes().size() == 0));
    }

    private void mapoutSecondaries() {
        this.secondaryAttributeMap    = new HashMap<>();
        this.secondaryRelationshipMap = new HashMap<>();
        Stack<MTAttribute> runningPath = new Stack<>();
        gatherSecondaries(secondaryAttributeMap, secondaryRelationshipMap, runningPath);
    }

    @ModelMethod(
        category = ModelMethodCategory.ENTITY,
        description = "Indicates whether this entity was **declared** `secondary`. If the entity was **not** declared "
                      + "with the `secondary` keyword then this will return false even if it is implied as secondary.")
    public boolean isDeclaredAsSecondary() {
        return declaredAsSecondary;
    }

    public void setDeclaredAsSecondary(boolean declaredAsSecondary) {
        this.declaredAsSecondary = declaredAsSecondary;
    }

    public void gatherSecondaries(HashMap<String, MTSecondaryAttribute> secondaryAttributeMap, HashMap<String, MTSecondaryRelationship> secondaryRelationshipMap, Stack<MTAttribute> runningPath) {
        for (MTAttribute attribute : attributeMap.values()) {
            attribute.gatherSecondaries(secondaryAttributeMap, secondaryRelationshipMap, runningPath);
        }
        if (isSecondary()) {
            for (MTRelationship relationship : relationships) {
                String fullPathString = String.join(".",
                                                    runningPath.stream().map(attr -> attr.getName()).collect(
                                                        Collectors.toList()));
                MTSecondaryRelationship secondaryRelationship = new MTSecondaryRelationship(fullPathString, runningPath,
                                                                                            relationship);
                secondaryRelationshipMap.put(fullPathString, secondaryRelationship);
            }
        }
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description =
            "Given an entity object, this method will try to find and return a relationship on this entity that is to "
            + "the provided entity. If none is found, `null` will be returned.")
    public MTRelationship findRelationshipWithToEntity(
        @ModelMethodParameter(description = "The entity of which to search.")
            MTEntity entity) {
        List<MTRelationship> foundRelationships = findPossibleRelationshipsWithEntity(entity);

        if (foundRelationships.size() == 0) {
            return null;
        }

        if (foundRelationships.size() == 1) {
            return foundRelationships.get(0);
        }

        // look for a reverse name to clarify
        ArrayList<MTRelationship> nameMatchedRelationships = new ArrayList<>();
        for (MTRelationship relationship : foundRelationships) {
            for (MTRelationship reverseRelationship : entity.findPossibleRelationshipsWithEntity(this)) {
                if ((relationship.getReverseName() != null && reverseRelationship.getName().equals(
                    relationship.getReverseName()))
                    || (reverseRelationship.getReverseName() != null && reverseRelationship.getReverseName().equals(
                    relationship.getName()))) {
                    // this one looks like the one!
                    nameMatchedRelationships.add(relationship);
                }
            }
        }
        if (nameMatchedRelationships.size() == 1) {
            return nameMatchedRelationships.get(0);
        }

        System.err.println("ERROR: Ambiguous relationships. Try using reverse name specifier.");
        return null;
    }

    @ModelMethod(
        category = ModelMethodCategory.RELATIONSHIP,
        description = "Given an entity, this method finds possible relationships to this entity.")
    public List<MTRelationship> findPossibleRelationshipsWithEntity(
        @ModelMethodParameter(description = "The entity from which to find relationships.")
            MTEntity entity
                                                                   ) {
        ArrayList<MTRelationship> foundRelationships = new ArrayList<>();
        for (MTRelationship relationship : relationships) {
            String toEntityName = relationship.getTo().getEntityName();
            if (toEntityName != null && toEntityName.equals(entity.name)) {
                foundRelationships.add(relationship);
            }
        }
        return foundRelationships;
    }

    @ModelMethod(
        category = ModelMethodCategory.RELATIONSHIP,
        description = "Using the relationship that is both a `parent` relationship and **not optional**, it returns "
                      + "the \"to\" entity of the relationship.")
    public MTEntity getPrimaryParentEntity() {
        return getPrimaryParentRelationship().getTo().getEntity();
    }

    @ModelMethod(
        category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns the relationship that is both a `parent` relationship and **not** `optional`")
    public MTRelationship getPrimaryParentRelationship() {
        for (MTRelationship relationship : relationships) {
            if (relationship.isParent() && !relationship.isOptional()) {
                return relationship;
            }
        }
        return null;
    }

    @ModelMethod(
        category = ModelMethodCategory.VIEW,
        description = "Indicates whether this entity is really a View.")
    public boolean isView() {
        return this instanceof MTView;
    }

    @ModelMethod(
        category = ModelMethodCategory.ATTRIBUTE,
        description = "Indicates whether any of the attributes of this entity are of the specified data type.")
    public boolean hasAttributeOfDataType(
        @ModelMethodParameter(description = "The data type of which to search for attributes.")
            MTNativeType.DataType dataType) {
        for (MTAttribute attribute : attributeMap.values()) {
            if (attribute.getType().isNativeDataType(dataType)) {
                return true;
            }
        }
        return false;
    }

    @ModelMethod(
        category = ModelMethodCategory.ATTRIBUTE,
        description = "Indicates whether this entity has an attribute with the specified name.")
    public boolean hasAttributeNamed(
        @ModelMethodParameter(description = "The name of the attribute.")
            String name) {
        return attributeMap.containsKey(name);
    }

    @ModelMethod(
        category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns an attribute of this entity with the specified name.")
    public MTAttribute attributeNamed(
        @ModelMethodParameter(description = "The name of the attribute to return.")
            String name) {
        return getAttributeByName(name);
    }

    // not exposed to templates
    public MTAttribute getAttributeByName(String attributeName) {
        MTAttribute attribute = attributeMap.get(attributeName);
        if (attribute == null && primaryKey != null) {
            attribute = primaryKey.getAttributeByName(attributeName);
        }
        return attribute;
    }

    @ModelMethod(
        category = ModelMethodCategory.RELATIONSHIP,
        description = "Indicates whether this entity has an relationship with the specified name.")
    public boolean hasRelationshipNamed(String name) {
        for (MTRelationship relationship : relationships) {
            if (relationship.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @ModelMethod(
        category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns an relationship of this entity with the specified name.")
    public MTRelationship relationshipNamed(
        @ModelMethodParameter(description = "The name of the relationship to return.")
        String name) {
        return getRelationshipByName(name);
    }

    @ModelMethod(
        category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns the number of declared relationships.")
    public int getRelationshipCount() {
        return relationships.size();
    }

    @ModelMethod(
        category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns the list of declared uniqueness constraints between relationships.")
    public List<MTUniqueness> getUniquenesses() {
        return uniquenesses;
    }

    public void addUniqueness(MTUniqueness uniqueness) {
        this.uniquenesses.add(uniqueness);
    }

    @ModelMethod(
        category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns a list of relationships from this entity to a specified entity.")
    public List<MTRelationship> getRelationshipsWithToEntity(
        @ModelMethodParameter(description = "The entity that the relationships are **to**.")
            MTEntity toEntity) {
        List<MTRelationship> matchingRelationships = new ArrayList<>();
        for (MTRelationship relationship : relationships) {
            if (relationship.getTo().getEntityName().equals(toEntity.getName())) {
                matchingRelationships.add(relationship);
            }
        }
        return matchingRelationships;
    }

    @ModelMethod(
        category = ModelMethodCategory.RELATIONSHIP,
        description =
            "Returns the one relationship of this entity that is both declared as `parent` and **not** declared "
            + "as `optional` and is **to** the named entity.")
    public MTRelationship primaryParentRelationship(
        @ModelMethodParameter(description = "The name of the entity that the relationship is **to**.")
            String entityName) {
        for (MTRelationship relationship : relationships) {
            if (relationship.getTo().getEntityName().equals(entityName) && relationship.isParent()
                && !relationship.isOptional()) {
                return relationship;
            }
        }
        return null;
    }

    @ModelMethod(
        category = ModelMethodCategory.RELATIONSHIP,
        description =
            "Returns a relationship of this entity by name. If a relationship is not found with this name `null` "
            + "will be returned.")
    public MTRelationship getRelationshipByName(
        @ModelMethodParameter(description = "The name of the relationship to return.")
            String relationshipName) {
        for (MTRelationship relationship : relationships) {
            if (relationship.getName().equals(relationshipName)) {
                return relationship;
            }
        }
        return null;
    }

    @ModelMethod(
        category = ModelMethodCategory.ATTRIBUTE,
        description = "Indicates whether this entity has any attributes.")
    public boolean hasAttributes() {
        return !attributeMap.isEmpty();
    }

    @ModelMethod(
        category = ENTITY_TEMPLATE,
        description = "Returns the entity associated with a specified entity template argument.")
    public MTEntity entityOfTemplateArg(
        @ModelMethodParameter(description =
            "The convention is usually an upper case letter but depends on how the entity "
            + "template was defined. For instance, if it is defined as: `Factory<C>` "
            + "then you would pass the string \"C\" to get the entity associated with "
            + "that argument that was used when instantiating this entity template.")
            String templateArgName) {
        for (MTRelationship relationship : relationships) {
            if (relationship.getTemplateArgName() != null
                && relationship.getTemplateArgName().equals(templateArgName)) {
                return relationship.getTo().getEntity();
            }
        }
        return null;
    }

    @ModelMethod(
        category = ModelMethodCategory.TAGGING,
        description = "Indicates whether this entity has at least one attribute with the specified tag.")
    public boolean hasAttributeTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        for (MTAttribute attribute : getAttributes()) {
            if (attribute.hasTag(tag)) {
                return true;
            }
        }
        return false;
    }

    @ModelMethod(
        category = ModelMethodCategory.TAGGING,
        description = "Returns an attribute of this entity that is tagged with the specified tag.")
    public MTAttribute attributeTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        for (MTAttribute attribute : getAttributes()) {
            if (attribute.hasTag(tag)) {
                return attribute;
            }
        }
        return null;
    }

    @ModelMethod(
        category = ModelMethodCategory.TAGGING,
        description = "Indicates whether this entity has at least one relationship with the specified tag.")
    public boolean hasRelationshipTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
        String tag) {
        for (MTRelationship relationship : getRelationships()) {
            if (relationship.hasTag(tag)) {
                return true;
            }
        }
        return false;
    }

    @ModelMethod(
        category = ModelMethodCategory.TAGGING,
        description = "Indicates whether this entity has at least one relationship to a named other entity.")
    public boolean hasRelationshipToEntityNamed(
        @ModelMethodParameter(description = "The name of the other entity.")
        String toEntityName) {
        for (MTRelationship relationship : getRelationships()) {
            if (relationship.getTo().getEntityName().equals(toEntityName)) {
                return true;
            }
        }
        return false;
    }

    @ModelMethod(
        category = ModelMethodCategory.TAGGING,
        description = "Returns a relationship of this entity that is tagged with the specified tag.")
    public MTRelationship relationshipTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        for (MTRelationship relationship : getRelationships()) {
            if (relationship.hasTag(tag)) {
                return relationship;
            }
        }
        return null;
    }

    @ModelMethod(
        category = ModelMethodCategory.TAGGING,
        description = "Indicates whether the entity **to** which a relationship references is tagged with the "
                      + "specified tag.")
    public boolean hasRelationshipToEntityTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        for (MTRelationship relationship : getRelationships()) {
            if (relationship.getTo().getEntity().hasTag(tag)) {
                return true;
            }
        }
        return false;
    }

    @ModelMethod(
        category = ModelMethodCategory.TAGGING,
        description = "Returns the entity **to** which a relationship references that is tagged with the "
                      + "specified tag.")
    public MTRelationship relationshipToEntityTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        for (MTRelationship relationship : getRelationships()) {
            if (relationship.getTo().getEntity().hasTag(tag)) {
                return relationship;
            }
        }
        return null;
    }

    public void setFieldNumber(String fieldName, int number) {
        //ECLog.logInfo("SET " + fieldName + " = " + number);
        fieldNumbers.put(fieldName, number);
        if (number > largestFieldNumber) {
            largestFieldNumber = number;
        }
    }

    @ModelMethod(
        category = ModelMethodCategory.ATTRIBUTE,
        description =
            "Returns the field number of the specified attribute. Field numbers are assigned to attributes in "
            + "the order in which they are read from the model source file.")
    public int fieldNumber(
        @ModelMethodParameter(description = "The attribute associated with the field number that you want returned.")
            MTAttribute attribute) {
        return getFieldNumberByName(attribute.getName());
    }

    @ModelMethod(
        category = ModelMethodCategory.ATTRIBUTE,
        description =
            "Returns the field number of the specified field name. Field numbers are assigned to attributes in "
            + "the order in which they are read from the model source file.")
    public int getFieldNumberByName(
        @ModelMethodParameter(description =
            "The field name (attribute or relationship) associated with the field number "
            + "that you want returned.")
            String fieldName) {
        if (!fieldNumbers.containsKey(fieldName)) {
            //ECLog.logInfo("auto " + fieldName + " = " + largestFieldNumber);
            int number = largestFieldNumber++;
            fieldNumbers.put(fieldName, number);
            return number;
        }
        int number = fieldNumbers.get(fieldName);
        if (number == 0) {
            //ECLog.logInfo("auto " + fieldName + " = " + largestFieldNumber);
            number = largestFieldNumber++;
            fieldNumbers.put(fieldName, number);
        }
        else {
            //ECLog.logInfo(fieldName + " = " + number);
        }
        return number;
    }

    @ModelMethod(
        category = ModelMethodCategory.RELATIONSHIP,
        description =
            "Returns the field number of the specified relationship. Field numbers are assigned to relationships in "
            + "the order in which they are read from the model source file.")
    public int fieldNumberByRelationship(
        @ModelMethodParameter(description = "The relationship associated with the field number that you want returned.")
            MTRelationship relationship) {
        //ECLog.logInfo("(r) Getting field number for field: " + relationship.getName());
        return getFieldNumberByName(relationship.getName());
    }

    @ModelMethod(
        category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns the field number of the specified *domain* relationship. This is the same order as the "
                      + "non-domain specific relationship.")
    public int fieldNumberByDomainRelationship(
        @ModelMethodParameter(description = "The domain relationship associated with the field number that you want returned.")
            MTDERelationship relationship) {
        //ECLog.logInfo("(dr) Getting field number for field: " + relationship.getRelationshipName());
        return getFieldNumberByName(relationship.getRelationshipName());
    }

    private int fieldNumberByName(String fieldName) {
        //ECLog.logInfo("Getting field number for field: " + fieldName);
        return getFieldNumberByName(fieldName);
    }

    @ModelMethod(
        category = ModelMethodCategory.ENTITY,
        description =
            "Returns the list of entities representing the a chain of primary parents to this entity. At the top "
            + "of the list is the top parent and at the bottom is this entity.")
    public List<MTEntity> getPrimaryParentAncestory() {
        Vector<MTEntity> list   = new Vector<>();
        MTEntity         entity = this;
        while (entity.hasPrimaryParentRelationship()) {
            entity = entity.getPrimaryParentRelationship().getTo().getEntity();
            list.insertElementAt(entity, 0);
        }
        return list;
    }

    @ModelMethod(
        category = ModelMethodCategory.RELATIONSHIP,
        description = "Indicates whether this entity has a primary parent relationship. A primary parent relationship "
                      + "is one which is declared `parent` and **not** declared `optional`.")
    public boolean hasPrimaryParentRelationship() {
        return primaryParentRelationship;
    }

    @ModelMethod(
        category = ModelMethodCategory.TAGGING,
        description = "Indicates whether this entity has a type (which can be another entity) that is tagged with the "
                      + "specified tag.")
    public boolean hasAttributeOfTypeTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        for (MTAttribute attribute : getAttributes()) {
            if (attribute.getType().hasTag(tag)) {
                return true;
            }
        }
        return false;
    }

    @ModelMethod(
        category = ModelMethodCategory.TAGGING,
        description =
            "Returns an attribute of this entity that has a type (which can be another entity) that is tagged with the "
            + "specified tag.")
    public MTAttribute attributeOfTypeTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        for (MTAttribute attribute : getAttributes()) {
            if (attribute.getType().hasTag(tag)) {
                return attribute;
            }
        }
        return null;
    }
}

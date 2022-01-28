/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.entity;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.MTModule;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.util.ECLog;
import org.entityc.compiler.util.ECStringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MTView extends MTEntity {

    private final MTEntity                          entity;
    private final List<MTTagSet>                    includedTagSets             = new ArrayList<>();
    private final List<MTTagSet>                    excludedTagSets             = new ArrayList<>();
    private final Map<String, MTAttributeReference> foreignAttributeMap         = new HashMap(); // included from other entities were a relationship exists
    private final List<MTViewReference>             foreignViewReferences       = new ArrayList<>(); // views of many relationship entities
    private       List<String>                      includedAttributeNames      = new ArrayList<>();
    private       List<String>                      includedRelationshipNames   = new ArrayList<>();
    private       List<String>                      excludedAttributeNames      = new ArrayList<>();
    private       List<String>                      excludedRelationshipNames   = new ArrayList<>();
    private       boolean                           includedPrimaryKey          = false;
    private       boolean                           excludedPrimaryKey          = false;
    private       boolean                           includedAssetAttributes     = false;
    private       boolean                           excludedAssetAttributes     = false;
    private       boolean                           includedArrayAttributes     = false;
    private       boolean                           excludedArrayAttributes     = false;
    private       boolean                           includedParentRelationships = false;
    private       boolean                           excludedParentRelationships = false;
    private       boolean                           distill                     = true;
    private       boolean                           distillNoParents            = false;
    private       boolean                           needsDistilling             = false;
    private       List<MTAttributeReference>        foreignAttributeReferences  = new ArrayList<>();
    private       boolean                           defaultView                 = false;
    private       Map<String, String>               viewArrayNames              = new HashMap<>(); // arrayName: viewName
    private       Map<String, MTView>               viewArrays                  = new HashMap<>(); // views of this entity
    private       int                               viewMagicNumber             = 0;

    public MTView(ParserRuleContext ctx, MTModule module, MTEntity entity, String name, boolean defaultView) {
        super(ctx, module, name);
        this.defaultView = defaultView;
        this.entity      = entity;
    }

    public boolean isIncludedPrimaryKey() {
        return includedPrimaryKey;
    }

    public void setIncludedPrimaryKey(boolean includedPrimaryKey) {
        this.includedPrimaryKey = includedPrimaryKey;
    }

    public boolean isExcludedPrimaryKey() {
        return excludedPrimaryKey;
    }

    public void setExcludedPrimaryKey(boolean excludedPrimaryKey) {
        this.excludedPrimaryKey = excludedPrimaryKey;
    }

    public boolean isIncludedParentRelationships() {
        return includedParentRelationships;
    }

    public void setIncludedParentRelationships(boolean includedParentRelationships) {
        this.includedParentRelationships = includedParentRelationships;
    }

    public boolean isExcludedParentRelationships() {
        return excludedParentRelationships;
    }

    public void setExcludedParentRelationships(boolean excludedParentRelationships) {
        this.excludedParentRelationships = excludedParentRelationships;
    }

    public boolean isDistill() {
        return distill;
    }

    public boolean isDistillNoParents() {
        return distillNoParents;
    }

    public void setDistill(boolean distill, boolean noparents) {
        this.distill          = distill;
        this.distillNoParents = noparents;
    }

    public boolean isIncludedArrayAttributes() {
        return includedArrayAttributes;
    }

    public void setIncludedArrayAttributes(boolean includedArrayAttributes) {
        this.includedArrayAttributes = includedArrayAttributes;
    }

    public boolean isExcludedArrayAttributes() {
        return excludedArrayAttributes;
    }

    public void setExcludedArrayAttributes(boolean excludedArrayAttributes) {
        this.excludedArrayAttributes = excludedArrayAttributes;
    }

    public boolean isIncludedAssetAttributes() {
        return includedAssetAttributes;
    }

    public void setIncludedAssetAttributes(boolean includedAssetAttributes) {
        this.includedAssetAttributes = includedAssetAttributes;
    }

    public boolean isExcludedAssetAttributes() {
        return excludedAssetAttributes;
    }

    public void setExcludedAssetAttributes(boolean excludedAssetAttributes) {
        this.excludedAssetAttributes = excludedAssetAttributes;
    }

    public MTEntity getEntity() {
        return entity;
    }

    public void includeAttributesByName(List<String> attributeNames) {
        includedAttributeNames.addAll(attributeNames);
    }

    public void excludeAttributesByName(List<String> attributeNames) {
        excludedAttributeNames.addAll(attributeNames);
    }

    public void includeRelationshipsByName(List<String> relationshipNames) {
        includedRelationshipNames.addAll(relationshipNames);
    }

    public void excludeRelationshipsByName(List<String> relationshipNames) {
        excludedRelationshipNames.addAll(relationshipNames);
    }

    public void addViewArrayName(String viewName, String arrayName) {
        viewArrayNames.put(arrayName, viewName);
    }

    public void addForeignViewReference(MTViewReference reference) {
        foreignViewReferences.add(reference);
    }

    public void includeTagSet(MTTagSet tagSet) {
        this.includedTagSets.add(tagSet);
    }

    public void excludeTagSet(MTTagSet tagSet) {
        this.excludedTagSets.add(tagSet);
    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        needsDistilling = true;
        boolean anotherPass = false;
        boolean including   = !includedAttributeNames.isEmpty() || includedAssetAttributes || includedArrayAttributes
                              || includedParentRelationships || includedPrimaryKey
                              || !includedRelationshipNames.isEmpty() || !includedTagSets.isEmpty();
        boolean excluding   = !excludedAttributeNames.isEmpty() || excludedAssetAttributes || excludedArrayAttributes
                              || excludedParentRelationships || excludedPrimaryKey
                              || !excludedRelationshipNames.isEmpty() || !excludedTagSets.isEmpty();
        if (including && excluding) {
            ECLog.logFatal(
                "View + " + getName() + " cannot both include and exclude attributes/relationships from entity "
                + entity.getName());
        }
        List<String> names = including ?
                             includedAttributeNames :
                             excludedAttributeNames;

        for (String name : names) {
            if (entity.getAttributeByName(name) == null) {
                if (pass == 0) {
                    anotherPass = true;
                }
                else {
                    //ECLog.logInfo(entity.getParserRuleContext(), "View " + getName() + " > Entity " + entity.getName() + " does not have an attribute by the name of " + name);
                }
            }
        }

        // we will turn off support for individual foreign attributes for now
        //anotherPass = resolveForeignAttributeReferences(pass, anotherPass);

        anotherPass = resolveForeignViewReferences(pass, anotherPass);

        if (anotherPass) {
            return anotherPass;
        }

        this.attributeMap.clear();
        List<MTAttribute> allAttributes = new ArrayList<>(entity.getAttributes());
        if (entity.hasPrimaryKey()) {
            //ECLog.logInfo("Adding pk attribute " + entity.getName() + "." + entity.getPrimaryKeyAttribute().getName());
            allAttributes.add(entity.getPrimaryKeyAttribute());
        }
        for (MTAttribute attribute : allAttributes) {
            if (attribute.getType() == null) {
                if (attribute.getTypeName() != null) {
                    //  MTEntity typeEntity = model.getEntityWithName(attribute.getTypeName());
                    if (attribute.resolveReferences(space, 0)) {
                        ECLog.logError(attribute, "Attribute does not have a type: " + attribute.getName());
                        return true;
                    }
                }
                continue;
            }
            boolean addToAttributeMap = false;
            if (including) {
                if (includedAttributeNames.contains(attribute.getName())
                    || (includedPrimaryKey && attribute.isPrimaryKey())
                    || (includedArrayAttributes && attribute.isArray())
                ) {
                    addToAttributeMap = true;
                }
                else if (includedTagSets.size() > 0) {
                    for (MTTagSet tagSet : includedTagSets) {
                        if (tagSet.matches(attribute.getTags())) {
                            addToAttributeMap = true;
                            break;
                        }
                    }
                }
            }
            else {
                if (!excluding) {
                    addToAttributeMap = true;
                }
                else {
                    if (excludedAssetAttributes && (attribute.getType().isAssetType())) {
                        continue;
                    }
                    if (excludedArrayAttributes && attribute.isArray()) {
                        continue;
                    }
                    if (excludedPrimaryKey && attribute.isPrimaryKey()) {
                        continue;
                    }
                    if (excludedTagSets.size() > 0) {
                        boolean excludeAttribute = false;
                        for (MTTagSet tagSet : excludedTagSets) {
                            if (tagSet.matches(attribute.getTags())) {
                                excludeAttribute = true;
                                break;
                            }
                        }
                        if (excludeAttribute) {
                            continue;
                        }
                    }
                    // if excluding or neither including nor excluding
                    if (!excludedAttributeNames.contains(attribute.getName())) {
                        addToAttributeMap = true;
                    }
                }
            }
            if (addToAttributeMap) {
                attributeMap.put(attribute.getName(), attribute);
            }
        }
        this.relationships.clear();
        for (MTRelationship relationship : entity.getRelationships()) {
            boolean add = true;
            if (including) {
                if (includedParentRelationships) {
                    add = relationship.isParent();
                }
                else {
                    add = includedRelationshipNames.contains(relationship.getName());
                }
            }
            else if (excluding) {
                if (excludedParentRelationships) {
                    add = !relationship.isParent();
                }
                else {
                    add = !excludedRelationshipNames.contains(relationship.getName());
                }
            }
            if (add) {
                relationships.add(relationship);
            }
        }

        return anotherPass;
    }

    private boolean resolveForeignViewReferences(int pass, boolean anotherPass) {

        for (MTViewReference viewReference : foreignViewReferences) {
            MTEntity referenceEntity  = null;
            String   relationshipName = viewReference.getRelationshipName();
            String   entityName       = viewReference.getEntityName();

            if (relationshipName != null) {
                MTRelationship relationship = entity.getRelationshipByName(relationshipName);
                if (relationship == null) {
                    ECLog.logFatal(entity, "Entity of view " + this.getName() + " does not have a relationship named: "
                                           + relationshipName);
                }
                viewReference.setResolvedRelationship(relationship);
                referenceEntity = relationship.getTo().getEntity();
            }
            else if (entityName != null) {
                for (MTRelationship relationship : entity.getRelationships()) {
                    if (relationship.getTo().getEntityName().equals(entityName)) {
                        viewReference.setResolvedRelationship(relationship);
                        referenceEntity = relationship.getTo().getEntity();
                        break;
                    }
                }
            }
            if (referenceEntity == null) {
                ECLog.logError(this, "Couldn't resolve entity: " + entityName + " (pass: " + pass + ")");
                anotherPass = (pass < 5);
                if (!anotherPass) {
                    ECLog.logFatal(this, "The entity of this view does not appear to have a relationship with entity: "
                                         + entityName);
                }
            }
        }
        return anotherPass;
    }

    private boolean resolveForeignAttributeReferences(int pass, boolean anotherPass) {
        for (MTAttributeReference reference : foreignAttributeReferences) {

            MTEntity referenceEntity  = null;
            String   relationshipName = reference.getRelationshipName();
            String   entityName       = reference.getEntityName();

            if (relationshipName != null) {
                MTRelationship relationship = entity.getRelationshipByName(relationshipName);
                if (relationship == null) {
                    ECLog.logFatal(entity, "Entity of view " + this.getName() + " does not have a relationship named: "
                                           + relationshipName);
                }
                reference.setResolvedRelationship(relationship);
                referenceEntity = relationship.getTo().getEntity();
            }
            else if (entityName != null) {
                for (MTRelationship relationship : entity.getRelationships()) {
                    if (relationship.getTo().getEntityName().equals(entityName)) {
                        reference.setResolvedRelationship(relationship);
                        referenceEntity = relationship.getTo().getEntity();
                        break;
                    }
                }
            }
            if (referenceEntity == null) {
                anotherPass = (pass < 5);
            }
            reference.setResolvedEntity(referenceEntity);
            List<MTAttributeReference> references = new ArrayList<>();
            if (reference.getAttributeName() != null) {
                references.add(reference);
            }
            else {
                // add a new reference for each attribute
                //ECLog.logInfo("VIEW MAGIC: " + getEntity().getName() + "." + getName() + ": Expanding to all attributes of entity: " + referenceEntity.getName());
                for (MTAttribute attribute : referenceEntity.getAttributes()) {
                    if (attribute.isCreation() || attribute.isModification()) {
                        continue; // skip those
                    }
                    MTAttributeReference attributeReference = new MTAttributeReference(entityName, relationshipName,
                                                                                       attribute.getName(), null);
                    attributeReference.setResolvedRelationship(reference.getResolvedRelationship());
                    attributeReference.setResolvedEntity(reference.getResolvedEntity());
                    references.add(attributeReference);
                    //ECLog.logInfo("VIEW MAGIC: -- added attribute: " + attribute.getName());
                }
            }
            for (MTAttributeReference attributeReference : references) {
                MTAttribute referencedAttribute = referenceEntity.getAttributeByName(
                    attributeReference.getAttributeName());
                if (referencedAttribute == null) {
                    ECLog.logFatal(referenceEntity,
                                   "Expecting entity " + referenceEntity.getName() + " to have an attribute named: "
                                   + attributeReference.getAttributeName());
                }
                attributeReference.setResolvedAttribute(referencedAttribute);
                //ECLog.logInfo("VIEW MAGIC: -- put attribute > " + attributeReference.getResolvedAttributeName() + ": " + attributeReference.getResolvedAttribute().getName());
                foreignAttributeMap.put(attributeReference.getResolvedAttributeName(), attributeReference);
            }
        }
        foreignAttributeReferences = new ArrayList<>(foreignAttributeMap.values()); // "all" attributes are now expanded
        return anotherPass;
    }

    public void distill(MTSpace space) {
        if (!needsDistilling) {
            return;
        }
        needsDistilling = false;
        if (distill || distillNoParents) {
            //ECLog.logInfo("=========================================================================== " + entity.getName() + "." + getName());
            //ECLog.logInfo("Distilling view: " + getFullname() + " (" + this + ") " + (distillNoParents ? "NO PARENTS" : "with parents"));
            // convert the primary key and relationships to attributes
            for (MTRelationship relationship : relationships) {
                if (distillNoParents && relationship.isParent()) {
                    continue;
                }
                if (relationship.getTo().getPlurality() == HalfRelationshipPlurality.ONE) {
                    // don't include if there is a view reference to the entity it points to
                    boolean foundReference = false;
                    for (MTViewReference viewReference : foreignViewReferences) {
                        if (relationship.getName().equals(viewReference.getResolvedRelationship().getName())) {
                            foundReference = true;
                            break;
                        }
                    }
                    if (foundReference) {
                        continue; // don't create ID attribute for this relationship.
                    }
                    MTAttribute attribute = new MTAttribute(relationship.getParserRuleContext(),
                                                            this,
                                                            relationship.getTo().getEntity().getPrimaryKeyAttribute().getTypeName(),
                                                            relationship.getName() + "Id");
                    attribute.setRelationship(relationship);
                    attribute.setDescription(
                        "Unique ID of a related " + relationship.getTo().getEntityName() + " object.");
                    this.addAttribute(attribute);
                    //ECLog.logInfo("Converted relationship \"" + relationship.getName() + "\" to attribute on view: " + getName() + " of type: " + attribute.getType().getTypeAsString());
                }
            }
            relationships.clear();
//            if (hasPrimaryKey()) {
//                this.addAttribute(getPrimaryKeyAttribute());
//            }
            // transform these attribute references to attributes on this view
            //distillAttributeReferences(model);

            distillViewReferences(space);

            if (viewArrays.size() > 0) {
                // create an attribute array of a type of the view
                for (String arrayName : viewArrays.keySet()) {
                    MTView      view                 = viewArrays.get(arrayName);
                    MTAttribute arrayAttributeOfView = new MTAttribute(null, view, view, arrayName);
                    arrayAttributeOfView.setArray(true);
                    this.addAttribute(arrayAttributeOfView);
                }
            }
        }
    }

    private void distillViewReferences(MTSpace space) {
        for (MTViewReference viewReference : foreignViewReferences) {
            MTAttribute attribute = new MTAttribute(viewReference.getResolvedRelationship().getParserRuleContext(),
                                                    this,
                                                    viewReference.getResolvedView(),
                                                    viewReference.getAsName() != null ?
                                                    viewReference.getAsName() :
                                                    ECStringUtil.Uncapitalize(viewReference.getEntityName())
            );
            attribute.resolveReferences(space, 0);
            if (viewReference.isAsArray()) {
                attribute.setArray(true);
            }
            this.addAttribute(attribute);
        }
    }

    private void distillAttributeReferences(MTSpace space) {
        for (MTAttributeReference reference : foreignAttributeReferences) {
            //ECLog.logInfo("VIEW MAGIC: Converting " + reference.getEntityName() + "." + reference.getAttributeName() + " TO " + getEntity().getName() + "." + getName() + "." + reference.getAttributeName());
            Integer arraySize = null;
            if (reference.getResolvedAttribute().getType().isByteArrayType()) {
                arraySize = ((MTNativeType) reference.getResolvedAttribute().getType()).getArraySize();
            }
            MTAttribute attribute = new MTAttribute(reference.getResolvedRelationship().getParserRuleContext(),
                                                    this,
                                                    reference.getResolvedAttribute().getTypeName(),
                                                    reference.getResolvedAttributeName(),
                                                    reference.getResolvedAttribute().getUnit().getName(),
                                                    arraySize);
            attribute.setDescription(reference.getResolvedAttribute().getDescription());
            attribute.setArray(reference.getResolvedAttribute().isArray());
            attribute.resolveReferences(space, 0);
            this.addAttribute(attribute);
        }
    }

    public String getFullname() {
        return entity.getName() + ECStringUtil.Capitalize(name);
    }

    public boolean isDefaultView() {
        return defaultView;
    }

    public MTView makeView(MTEntity parentEntity, Map<String, String> templateArgToEntityMap, boolean defaultView) {
        MTView copiedView = new MTView(getParserRuleContext(), module, parentEntity, name, defaultView);
        copiedView.distill          = distill;
        copiedView.distillNoParents = distillNoParents;
        copiedView.needsDistilling  = true;
        copiedView.description      = description;

        copiedView.includedAttributeNames    = new ArrayList<>(includedAttributeNames);
        copiedView.includedRelationshipNames = new ArrayList<>(includedRelationshipNames);
        copiedView.excludedAttributeNames    = new ArrayList<>(excludedAttributeNames);
        copiedView.excludedRelationshipNames = new ArrayList<>(excludedRelationshipNames);
        copiedView.includedPrimaryKey        = includedPrimaryKey;
        copiedView.excludedPrimaryKey        = excludedPrimaryKey;
        copiedView.includedAssetAttributes   = includedAssetAttributes;
        copiedView.excludedAssetAttributes   = excludedAssetAttributes;
        copiedView.includedArrayAttributes   = includedArrayAttributes;
        copiedView.excludedArrayAttributes   = excludedArrayAttributes;

        copiedView.viewArrayNames  = new HashMap<>(viewArrayNames);
        copiedView.viewArrays      = new HashMap<>(viewArrays);
        copiedView.viewMagicNumber = viewMagicNumber;

        for (MTViewReference viewReference : foreignViewReferences) {
            String actualEntityName = viewReference.getEntityName();
            if (viewReference.getEntityName() != null) {
                String mappedEntityName = templateArgToEntityMap.get(viewReference.getEntityName());
                if (mappedEntityName != null) {
                    actualEntityName = mappedEntityName;
                }
            }
            MTViewReference copiedViewReference = new MTViewReference(actualEntityName,
                                                                      viewReference.getRelationshipName(),
                                                                      viewReference.getViewName(),
                                                                      viewReference.getAsName(),
                                                                      viewReference.isAsArray());
            copiedView.foreignViewReferences.add(copiedViewReference);
        }

        // search for template arguments in the foreign attribute reference list and replace
        for (MTAttributeReference reference : this.getForeignAttributeReferences()) {
            MTAttributeReference copiedReference = new MTAttributeReference(reference.getEntityName(),
                                                                            reference.getRelationshipName(),
                                                                            reference.getAttributeName(),
                                                                            reference.getAttributeAsName());
            if (reference.getEntityName() != null) {
                String actualEntityName = templateArgToEntityMap.get(reference.getEntityName());
                if (actualEntityName != null) {
                    copiedReference.setEntityName(actualEntityName);
                }
            }
            copiedView.addForeignAttributeReference(copiedReference);
        }
        return copiedView;
    }

    public List<MTAttributeReference> getForeignAttributeReferences() {
        return foreignAttributeReferences;
    }

    public void addForeignAttributeReference(MTAttributeReference reference) {
        this.foreignAttributeReferences.add(reference);
    }

    public boolean hasForeignAttributes(FTTransformSession session) {
        return foreignAttributeReferences.size() > 0 || foreignViewReferences.size() > 0;
    }

    public boolean hasForeignViews(FTTransformSession session) {
        return !foreignViewReferences.isEmpty();
    }

    public MTAttributeReference getAttributeReference(String attributeName) {
        return foreignAttributeMap.get(attributeName);
    }

    public boolean isAttributeReference(String attributeName) {
        return foreignAttributeMap.get(attributeName) != null;
    }
}

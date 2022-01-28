/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.domain;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.doc.annotation.ModelMethod;
import org.entityc.compiler.doc.annotation.ModelMethodCategory;
import org.entityc.compiler.doc.annotation.ModelMethodParameter;
import org.entityc.compiler.model.MTModule;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.MTReferenceResolution;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTRelationship;
import org.entityc.compiler.model.entity.MTSecondaryAttribute;
import org.entityc.compiler.model.entity.MTType;
import org.entityc.compiler.model.entity.MTView;
import org.entityc.compiler.model.foundation.MFArray;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@ModelClass(type = ModelClassType.DOMAIN, description = "Represents an entity in your model in the context of a domain.")
public class MTDEntity extends MTNode implements MTReferenceResolution, MTDomainBased, MTNamed {

    private final Map<String, MTDEInterface>    interfaceMap                = new HashMap<>();
    private final Map<String, String>           excludedAttributes          = new HashMap<>();
    //    private final Map<String, MTDEAttributeCache>    attributeCacheMap           = new HashMap<>();
//    private final Map<String, MTDERelationshipCache> relationshipCacheMap        = new HashMap<>();
    private final Map<String, MTDERelationship> domainRelationshipMap       = new HashMap<>();
    private final List<MTDERelationship>        declaredDomainRelationships = new ArrayList<>();
    private final Map<String, MTDView>          domainViewMap               = new HashMap<>();
    private final Map<String, MTDEAttribute>    domainAttributeMap          = new HashMap<>();
    private final Map<String, MTAttribute>      addedAttributeMap           = new HashMap<>();
    private final List<MTDEAttribute>           declaredDomainAttributes    = new ArrayList<>();
    protected     String                        entityName;
    protected     MTDomain                      domain;
    private       MTDEAttribute                 domainPrimaryKeyAttribute;
    private       MTDApplyTemplate              applyTemplate;
    private       MTEntity                      entity;
    private       String                        explicitName;
    private       boolean                       resolvedReferences          = false;
    private       int                           sinceSchemaVersion          = 0;

    public MTDEntity(ParserRuleContext ctx, MTDomain domain, String entityName) {
        super(ctx);
        this.domain     = domain;
        this.entityName = entityName;
    }

    public MTDEntity(ParserRuleContext ctx, MTDomain domain, MTEntity entity) {
        super(ctx);
        this.domain     = domain;
        this.entity     = entity;
        this.entityName = entity.getName();
        if (entity instanceof MTView) {
            ECLog.logFatal("WRONG constructor, should be MTDomainView!!");
        }
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns the list of domain relationships that are explicitly defined in the domain.")
    public List<MTDERelationship> getDeclaredDomainRelationships() {
        return declaredDomainRelationships;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns the list of domain attributes that are explicitly defined in the domain.")
    public List<MTDEAttribute> getDeclaredDomainAttributes() {
        return declaredDomainAttributes;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns an ordered list of domain attributes, where the order is defined by the order "
                      + "in which they are defined. It also includes attributes that were not defined, where they "
                      + "are placed at the end of the list.")
    public List<MTDEAttribute> getOrderedDomainAttributes() {
        ArrayList<MTDEAttribute> list = new ArrayList<>(declaredDomainAttributes);
        for (MTDEAttribute mtdeAttribute : domainAttributeMap.values()) {
            if (!declaredDomainAttributes.contains(mtdeAttribute)) {
                list.add(mtdeAttribute);
            }
        }
        return list;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Indicates if any attributes were declared in this domain entity declaration.")
    public boolean hasDeclaredDomainAttributes() {
        return declaredDomainAttributes.size() > 0;
    }

    public void addAttribute(MTAttribute attribute) {
        if (entity != null && entity.hasAttributeNamed(attribute.getName())) {
            ECLog.logFatal(attribute, "The entity already has an attribute by the name: " + attribute.getName());
        }
        if (addedAttributeMap.containsKey(attribute.getName())) {
            ECLog.logFatal(attribute, "The domain entity already has an attribute by the name: " + attribute.getName());
        }
        addedAttributeMap.put(attribute.getName(), attribute);
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY,
        description = "As new entities are added it can be helpful to have a record of when an entity was introduced. "
                      + "The \"since schema version\" provides the schema version that this entity was added.")
    public int getSinceSchemaVersion() {
        return sinceSchemaVersion;
    }

    public void setSinceSchemaVersion(int sinceSchemaVersion) {
        this.sinceSchemaVersion = sinceSchemaVersion;
    }

    @Deprecated
    public MTDApplyTemplate getApplyTemplate() {
        return applyTemplate;
    }

    @Deprecated
    public void setApplyTemplate(MTDApplyTemplate applyTemplate) {
        this.applyTemplate = applyTemplate;
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY,
        description = "This returns the name of the non-domain specific entity on which this domain entity is based.")
    public String getEntityName() {
        return entityName;
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY,
        description = "This returns the non-domain specific entity on which this domain entity is based.")
    public MTEntity getEntity() {
        return entity;
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY,
        description = "This returns the explicitly defined name for this domain entity. If no such name was defined it "
                      + "returns `null`.")
    public String getExplicitName() {
        return explicitName;
    }

    public void setExplicitName(String explicitName) {
        this.explicitName = explicitName;
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY,
        description =
            "This returns the full name of this domain entity which includes not only its domain based name but is "
            + "also preceded with the domain's namespace and the delimiter used in the namespace and between "
            + "the namespace and the domain entity name can be provided.")
    @Override
    public String getFullname(@ModelMethodParameter
                                  (description = "The delimiter string to use between segments of the namespace "
                                                 + "and between the namespace and the domain entity name.")
                                  String delim) {
        return domain.getNamespace().getFullname(delim) + delim + getName();
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY,
        description = "This returns the name of this domain entity. If the domain has any naming conventions or this "
                      + "entity was explicitly renamed, then this name will be different than the entity from which "
                      + "it is based.")
    @Override
    public String getName() {
        if (this.explicitName != null) {
            return explicitName;
        }
        if (entity == null) {
            ECLog.logWarning(this, "Entity not resolved yet for: " + entityName);
            return entityName;
        }
        if (entity.getManyAttribute() != null) {
            return domain.getNameFromDomainNaming(entity.getManyAttribute().getEntity())
                   + domain.getNameFromDomainNaming(entity.getManyAttribute());
        }
        return domain.getNameFromDomainNaming(entity);
    }

    @ModelMethod(category = ModelMethodCategory.INTERFACE,
        description = "This returns an interface by name that was defined as part of this domain entity.")
    public MTDEInterface getInterface(String name) {
        return interfaceMap.get(name);
    }

    public void addInterface(MTDEInterface mtInterface) {
        interfaceMap.put(mtInterface.getInterfaceName(), mtInterface);
    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {

        if (domain != null && domain.isSpecialized()) {
            domain = domain.getBaseDomain();
        }

        if (resolvedReferences) {
            return false;
        }
        boolean anotherPass = false;

        if (this.entity == null) {
            MTEntity entity = space.getEntityWithName(entityName);
            if (entity == null) {
                anotherPass = true;
                return anotherPass;
            }
            else {
                this.entity = entity;
            }
        }

        if (entity != null) {

            for (MTAttribute attribute : addedAttributeMap.values()) {
                attribute.setEntity(entity);
                attribute.resolveReferences(space, pass);
            }
            if (entity.hasPrimaryKey()) {
                if (getDomainPrimaryKeyAttribute(true) == null) {
                    anotherPass = true;
                }
            }
            for (MTAttribute attribute : entity.getAttributes()) {
                getDomainAttributeByName(attribute.getName(), true);
            }
            for (MTAttribute attribute : addedAttributeMap.values()) {
                if (entity.hasAttributeNamed(attribute.getName())) {
                    ECLog.logFatal(attribute,
                                   "The entity already has an attribute by the name: " + attribute.getName());
                }
                getDomainAttributeByName(attribute.getName(), true);
            }
            for (MTRelationship relationship : entity.getRelationships()) {
                getDomainEntityRelationshipByName(relationship.getName(), true);
            }
        }

        // we may have to remove primary key attributes if they somehow got added before
        // attributes were resolved.
        List<MTDEAttribute> primaryKeys = new ArrayList<>();
        for (MTDEAttribute domainAttribute : domainAttributeMap.values()) {
            if (domainAttribute.resolveReferences(space, pass)) {
                anotherPass = true;
            }
            if (domainAttribute.getAttribute() != null && domainAttribute.getAttribute().isPrimaryKey()) {
                primaryKeys.add(domainAttribute);
            }
        }
        for (MTDEAttribute pk : primaryKeys) {
            domainAttributeMap.remove(pk.getAttributeName());
        }
        primaryKeys.clear();

        for (MTDERelationship domainRelationship : domainRelationshipMap.values()) {
            if (domainRelationship.resolveReferences(space, pass)) {
                anotherPass = true;
            }
        }

        for (MTDView domainView : domainViewMap.values()) {
            if (domainView.resolveReferences(space, pass)) {
                anotherPass = true;
            }
        }

        for (MTDEInterface mtInterface : getInterfaces()) {
            if (mtInterface.resolveReferences(space, pass)) {
                anotherPass = true;
            }
        }

        /* TODO: The cache object is being replaced by tagging - keep this here during that transition
        for (MTDERelationshipCache domainEntityRelationshipCache : relationshipCacheMap.values()) {
            // expand entity scoped ones to matching relationships
            if (domainEntityRelationshipCache.getScope() != MTDERelationshipCache.RelationshipScope.ENTITY) {
                continue;
            }
            String   entityName  = domainEntityRelationshipCache.getName();
            MTEntity spaceEntity = space.getEntityWithName(entityName);
            if (spaceEntity == null) {
                if (pass < 3) {
                    anotherPass = true;
                } else {
                    //ECLog.logError(this, "Specified domain entity \"" + name + "\" does not correspond to a proper model entity");
                }
            } else {
                for (MTRelationship relationship : spaceEntity.getRelationships(true)) {
                    MTEntity toEntity = relationship.getTo().getEntity();
                    if (entityName.equals(toEntity.getName())
                            || (toEntity.isMadeFromTemplate() && entityName.equals(toEntity.getTemplateName()))) {
                        MTDERelationshipCache specificDomainRelationshipCache = new MTDERelationshipCache(relationship.getParserRuleContext(),
                                                                                                          MTDERelationshipCache.RelationshipScope.RELATIONSHIP);
                        specificDomainRelationshipCache.setName(relationship.getName());
                        specificDomainRelationshipCache.setCache(domainEntityRelationshipCache.getCache());
                        relationshipCacheMap.put(relationship.getName(), specificDomainRelationshipCache);
                    }
                }
            }
        } */
        if (!anotherPass) {
            resolvedReferences = true;
            processAttributeReplacement();
        }
        return anotherPass;
    }

    @ModelMethod(category = ModelMethodCategory.PRIMARY_KEY,
        description = "Returns the domain specific version of the entity's primary key attribute.")
    public MTDEAttribute getDomainPrimaryKeyAttribute(
        @ModelMethodParameter(description =
            "If the domain based primary key attribute has not yet been created, specifying "
            + "`true` will make sure it is created before returning it. Otherwise `null` "
            + "will be returned if it was not yet created.")
            boolean createIfNeeded) {
        if (createIfNeeded && domainPrimaryKeyAttribute == null && entity != null && entity.hasPrimaryKey()) {
            domainPrimaryKeyAttribute = new MTDEAttribute(null, this.domain, entity.getPrimaryKeyAttribute());
        }
        return domainPrimaryKeyAttribute;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns the domain specific version of the specified attribute.")
    public MTDEAttribute getDomainAttributeByName(
        @ModelMethodParameter(description = "The name of the attribute to return.")
            String attributeName,
        @ModelMethodParameter(description = "If the domain based attribute has not yet been created, specifying "
                                            + "`true` will make sure it is created before returning it. Otherwise `null` "
                                            + "will be returned if it was not yet created.")
            boolean createIfNeeded) {
        MTDEAttribute domainAttribute = domainAttributeMap.get(attributeName);
        if (createIfNeeded && domainAttribute == null) {
            //ECLog.logInfo("Created domain: " + domain.getName() + " attribute: " + attributeName + " for entity: " + entityName);
            domainAttribute = new MTDEAttribute(null, this.domain, entityName, attributeName);
            domainAttribute.setDomainEntity(this);
            domainAttribute.resolveReferences(this.domain.getSpace(), 0);
            if (domainAttribute.getAttribute() == null || !domainAttribute.getAttribute().isPrimaryKey()) {
                // if a primary key gets in because its attribute is still unresolved, we will delete it
                // during resolution time
                domainAttributeMap.put(attributeName, domainAttribute);
            }
        }
        return domainAttribute;
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns the domain specific version of the specified relationship.")
    public MTDERelationship getDomainEntityRelationshipByName(
        @ModelMethodParameter(description = "The name of the relationship to return.")
            String name,
        @ModelMethodParameter(description = "If the domain based relationship has not yet been created, specifying "
                                            + "`true` will make sure it is created before returning it. Otherwise `null` "
                                            + "will be returned if it was not yet created.")
            boolean createIfNeeded) {
        MTDERelationship domainRelationship = domainRelationshipMap.get(name);
        if (createIfNeeded && domainRelationship == null) {
            domainRelationship = new MTDERelationship(null, this, name);
            domainRelationshipMap.put(name, domainRelationship);
        }
        return domainRelationship;
    }

    @ModelMethod(category = ModelMethodCategory.INTERFACE,
        description = "Returns all the interfaces associated with this domain entity.")
    public Collection<MTDEInterface> getInterfaces() {
        return interfaceMap.values();
    }

    private void processAttributeReplacement() {
        HashMap<String, MTDEAttribute> replacedAttributes = new HashMap<>();
        for (MTDEAttribute domainAttribute : domainAttributeMap.values()) {
            if (domainAttribute.hasReplacedBitFields()) {
                for (MTDEAttributeBitField bitField : domainAttribute.getReplacedBitFields()) {
                    String replacedAttributeName = bitField.getReplacedAttributeName();
                    if (replacedAttributes.containsKey(replacedAttributeName)) {
                        ECLog.logFatal(domainAttribute, "Attribute cannot be replaced by a bit field more than once.");
                    }
                    if (domainAttributeMap.containsKey(replacedAttributeName)) {
                        replacedAttributes.put(replacedAttributeName, domainAttributeMap.get(replacedAttributeName));
                    }
                }
            }
        }
        for (MTDEAttribute domainAttribute : replacedAttributes.values()) {
            domainAttributeMap.remove(domainAttribute.getAttribute().getName());
        }
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Indicates whether this domain entity has an attribute by the specified name.")
    public boolean hasAttributeNamed(
        @ModelMethodParameter(description = "The name of the domain attribute of which to check the presence.")
            String name) {
        return domainAttributeMap.containsKey(name);
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns the domain attribute by its name if it exists, otherwise it returns `null`.")
    public MTDEAttribute attributeNamed(
        @ModelMethodParameter(description = "The name of the domain attribute to return.")
            String name) {
        return domainAttributeMap.get(name);
    }

    @ModelMethod(category = ModelMethodCategory.PRIMARY_KEY,
        description = "Indicates whether this domain entity has a primary key.")
    public boolean hasPrimaryKey() {
        return entity.hasPrimaryKey();
    }

    @ModelMethod(category = ModelMethodCategory.PRIMARY_KEY,
        description = "Returns the data type of the primary key attribute of this domain entity.")
    public MTType getPkType() {
        MTDEAttribute pkAttribute = getPrimaryKeyAttribute();
        return pkAttribute != null ?
               pkAttribute.getType() :
               null;
    }

    @ModelMethod(category = ModelMethodCategory.PRIMARY_KEY,
        description = "Returns the primary key attribute of this domain entity.")
    public MTDEAttribute getPrimaryKeyAttribute() {
        return domainPrimaryKeyAttribute;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
        description = "Indicates if this domain entity has a domain entity with the specified tag.")
    public boolean hasAttributeOfTypeTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        for (MTDEAttribute mtdeAttribute : getAttributes()) {
            MTType type = mtdeAttribute.getType();
            if (type instanceof MTEnum) {
                MTDEnum mtdEnum = domain.getDomainEnum((MTEnum) type, true);
                if (mtdEnum.hasTag(tag)) {
                    return true;
                }
            }
            else if (type instanceof MTEntity) {
                MTDEntity mtdEntity = domain.getDomainEntity((MTEntity) type, true);
                if (mtdEntity.hasTag(tag)) {
                    return true;
                }
            }
            else if (type.hasTag(tag)) {
                return true;
            }
        }
        return false;
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY,
        description = "Returns the full list of domain attributes of this domain entity.")
    public Collection<MTDEAttribute> getAttributes() {
        return domainAttributeMap.values();
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
        description =
            "Returns the first domain attribute of this domain entity that has the specified tag. Of course, in "
            + "theory there could be more than one, so this should be used when a template is expecting there "
            + "to be just one per domain entity.")
    public MTDEAttribute attributeOfTypeTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        for (MTDEAttribute mtdeAttribute : getAttributes()) {
            MTType type = mtdeAttribute.getType();
            if (type instanceof MTEnum) {
                MTDEnum mtdEnum = domain.getDomainEnum((MTEnum) type, true);
                if (mtdEnum.hasTag(tag)) {
                    return mtdeAttribute;
                }
            }
            else if (type instanceof MTEntity) {
                MTDEntity mtdEntity = domain.getDomainEntity((MTEntity) type, true);
                if (mtdEntity.hasTag(tag)) {
                    return mtdeAttribute;
                }
            }
            else if (type.hasTag(tag)) {
                return mtdeAttribute;
            }
        }
        return null;
    }

    public MTDEAttribute addDomainAttributeWithName(String attributeName) {
        MTDEAttribute domainAttribute = getDomainAttributeByName(attributeName, true);
        this.declaredDomainAttributes.add(domainAttribute);
        return domainAttribute;
    }

    public MTDERelationship addDomainRelationshipWithName(String relationshipName) {
        MTDERelationship domainRelationship = getDomainEntityRelationshipByName(relationshipName, true);
        this.declaredDomainRelationships.add(domainRelationship);
        return domainRelationship;
    }

    public MTAttribute getAddedAttributeByName(String attributeName) {
        return addedAttributeMap.get(attributeName);
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
        description =
            "Indicates whether a primary parent entity to this domain entity has been tagged with the specified "
            + "tag. A primary parent entity is the entity referenced by a relationship of this entity that has "
            + "been declared as a `parent` relationship and **not** declared `optional`.")
    public boolean hasPrimaryParentEntityTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        if (!hasPrimaryParentRelationship()) {
            return false;
        }
        MTDERelationship mtdeRelationship = primaryParentRelationship();
        if (mtdeRelationship == null) {
            return false;
        }
        return (mtdeRelationship.hasToEntityTagged(tag));
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
        description =
            "Returns the primary parent entity to this domain entity if it has been tagged with the specified "
            + "tag. A primary parent entity is the entity referenced by a relationship of this entity that has "
            + "been declared as a `parent` relationship and **not** declared `optional`.")
    public MTDEntity primaryParentEntityTagged(
        @ModelMethodParameter(description = "The tag to check.")
            String tag) {
        if (!hasPrimaryParentEntityTagged(tag)) {
            return null;
        }
        MTDERelationship mtdeRelationship = primaryParentRelationship();
        return mtdeRelationship.getTo().getEntity();
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Indicates whether this domain entity has a primary parent relationship. A primary parent "
                      + "relationship is one that has been declared as `parent` and **not** declared `optional`.")
    public boolean hasPrimaryParentRelationship() {
        return entity != null && entity.hasPrimaryParentRelationship();
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns the primary parent entity to this domain entity. A primary parent entity is the entity "
                      + "referenced by a relationship of this entity that has "
                      + "been declared as a `parent` relationship and **not** declared `optional`.")
    public MTDEntity primaryParentEntity() {
        return primaryParentRelationship().getTo().getEntity();
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns the primary parent relationship of this domain entity. A primary parent "
                      + "relationship is one that has been declared as `parent` and **not** declared `optional`.")
    public MTDERelationship primaryParentRelationship() {
        return getDomainEntityRelationshipByName(entity.getPrimaryParentRelationship().getName(), true);
    }

    public MTDView getDomainEntityViewByName(String viewName, boolean createIfNeeded) {
        MTDView domainEntityView = domainViewMap.get(viewName);
        if (domainEntityView == null && domain.getParentDomain() != null) {
            MTDEntity entityInParentDomain = domain.getParentDomain().getDomainEntityByName(getEntityName());
            if (entityInParentDomain != null) {
                MTDView parentDomainEntityView = entityInParentDomain.getDomainEntityViewByName(viewName, true);
                if (parentDomainEntityView != null) {
                    domainEntityView = MTDView.Copy(domain, parentDomainEntityView, entityName);
                    domainEntityView.resolveReferences(domain.getSpace(), 0);
                    addDomainView(domainEntityView);
                }
            }
        }
        if (domainEntityView == null && createIfNeeded) {
            MTDView domainView = domain.getDomainViewByName(viewName);
            if (domainView != null) {
                domainEntityView = MTDView.DomainEntityView(domainView, entityName);
                addDomainView(domainEntityView);
            }
        }
        return domainEntityView;
    }

    public void addDomainView(MTDView domainView) {
        //ECLog.logInfo("Adding " + domainView.getDomain().getName() + "::" + domainView.getEntityName() + "::" + domainView.getViewName() + "");
        domainViewMap.put(domainView.getViewName(), domainView);
    }

    @ModelMethod(category = ModelMethodCategory.MODULE,
        description = "Returns the module associated with this domain entity.")
    public MTModule getModule() {
        return entity != null ?
               entity.getModule() :
               null;
    }

    @ModelMethod(category = ModelMethodCategory.DOMAIN,
        description = "Returns the domain associated with this domain entity.")
    public MTDomain getDomain() {
        return domain;
    }

    public void addExcludedAttributeName(String name) {
        excludedAttributes.put(name, name);
    }

    public boolean isExcludedAttribute(String name) {
        return excludedAttributes.get(name) != null;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns all the domain attributes from this domain entity.")
    public Collection<MTDEAttribute> getDomainAttributes() {
        return domainAttributeMap.values();
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY,
        description = "Returns the description of this domain entity. If the domain entity does not have its own "
                      + "description, then the description of the entity is returned.")
    @Override
    public String getDescription() {
        if (description != null && description.length() > 0) {
            return description;
        }
        if (entity != null) {
            return entity.getDescription();
        }
        return null;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
        description = "Indicates whether this domain entity has an attribute tagged with the specified tag.")
    public boolean hasAttributeTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        for (MTDEAttribute attribute : declaredDomainAttributes) {
            if (attribute.hasTag(tag)) {
                return true;
            }
        }
        return false;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
        description = "Indicates whether this domain entity has an attribute who's tag starts with the specified tag prefix.")
    public boolean hasAttributeWithTagPrefixed(
        @ModelMethodParameter(description = "The tag prefix with which to search.")
            String tagPrefix) {
        for (MTDEAttribute attribute : declaredDomainAttributes) {
            if (attribute.hasTagPrefixed(tagPrefix)) {
                return true;
            }
        }
        return false;
    }

    private void buildFlatSecondaryEntityList(Collection<MTDEntity> flatList) {

        for (MTDEAttribute mtdeAttribute : getAttributes()) {
            if (mtdeAttribute.getAttribute().isSecondaryEntityType()) {
                MTDEntity mtdEntity = mtdeAttribute.getTypeEntity();
                flatList.add(mtdEntity);
                mtdEntity.buildFlatSecondaryEntityList(flatList);
            }
        }
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY,
        description = "Returns a list of all the secondary domain entities hierarchically that make up this "
                      + "domain entity.")
    public Collection<MTDEntity> getFlatSecondaryEntityList() {
        ArrayList<MTDEntity> flatList = new ArrayList<>();
        buildFlatSecondaryEntityList(flatList);
        return flatList;
    }

    private void buildFlatDeclaredAttributeList(HashMap<String, MTSecondaryAttribute> secondaryAttributeMap,
                                                Stack<String> attributePath,
                                                Collection<MTDEAttribute> list, MFArray ofTypes) {
        boolean keepSecondaryEntities = ofTypes != null && ofTypes.contains("entity");
        for (MTDEAttribute mtdeAttribute : declaredDomainAttributes) {
            String typeName = mtdeAttribute.getType().getText();
            if (mtdeAttribute.getType() instanceof MTEntity) {
                typeName = "entity";
            }
            else if (mtdeAttribute.getType() instanceof MTEnum) {
                typeName = "enum";
            }
            if (mtdeAttribute.getAttribute().isSecondaryEntityType()) {
                if (keepSecondaryEntities) {
                    list.add(mtdeAttribute);
                }
                attributePath.push(mtdeAttribute.getAttribute().getName());
                mtdeAttribute.getTypeEntity().buildFlatDeclaredAttributeList(secondaryAttributeMap, attributePath, list,
                                                                             ofTypes);
                attributePath.pop();
            }
            else if (ofTypes == null || ofTypes.getValues().contains(typeName)) {
                if (attributePath.isEmpty()) {
                    list.add(mtdeAttribute);
                }
                else {
                    String fullAttributeName = String.join(".", attributePath);
                    if (!fullAttributeName.isEmpty()) {
                        fullAttributeName += ".";
                    }
                    fullAttributeName += mtdeAttribute.getAttributeName();
                    if (secondaryAttributeMap.containsKey(fullAttributeName)) {
                        MTSecondaryAttribute secondaryAttribute = secondaryAttributeMap.get(fullAttributeName);
                        MTDEAttribute mtdeSecondaryAttribute = new MTDEAttribute(
                            mtdeAttribute.getParserRuleContext(), domain, secondaryAttribute);
                        mtdeSecondaryAttribute.acquireTags(mtdeAttribute);
                        mtdeSecondaryAttribute.acquireDescriptions(mtdeAttribute);
                        list.add(mtdeSecondaryAttribute);
                    }
                }
            }
        }
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description =
            "Returns a list of all the declared domain attributes of this domain entity that are declared in this "
            + "domain hierarchically (secondary entity hierarchy).")
    public Collection<MTDEAttribute> getDeclaredAttributes() {
        return declaredDomainAttributes;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns a list of all the domain attributes of this domain entity that are declared in this "
                      + "domain hierarchically (secondary entity hierarchy).")
    public Collection<MTDEAttribute> getFlatDeclaredAttributeList() {
        return getFlatDeclaredAttributeList(null);
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns a list of domain attributes of this domain entity that are of one of the specified "
                      + "data types and that are declared in this domain hierarchically (secondary entity hierarchy).")
    public Collection<MTDEAttribute> getFlatDeclaredAttributeList(MFArray ofTypes) {
        ArrayList<MTDEAttribute> declaredList = new ArrayList<>();
        buildFlatDeclaredAttributeList(getEntity().getSecondaryAttributeMap(), new Stack<>(), declaredList, ofTypes);
        return declaredList;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
        description = "Returns the domain attribute tagged with the specified tag. Although it is possible to have "
                      + "more than one, this method is used when expecting just one by nature of the tag.")
    public MTDEAttribute attributeTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        for (MTDEAttribute attribute : declaredDomainAttributes) {
            if (attribute.hasTag(tag)) {
                return attribute;
            }
        }
        return null;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
        description = "Returns all the domain attributes tagged with the specified tag.")
    public Collection<MTDEAttribute> attributesTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        ArrayList<MTDEAttribute> list = new ArrayList<>();
        for (MTDEAttribute attribute : declaredDomainAttributes) {
            if (attribute.hasTag(tag)) {
                list.add(attribute);
            }
        }
        return list;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
        description = "Indicates whether this domain entity has a domain relationship with the specified tag.")
    public boolean hasRelationshipTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        for (MTDERelationship relationship : declaredDomainRelationships) {
            if (relationship.hasTag(tag)) {
                return true;
            }
        }
        return false;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
        description = "Returns the domain relationship tagged with the specified tag. Although it is possible to have "
                      + "more than one, this method is used when expecting just one by nature of the tag.")
    public MTDERelationship relationshipTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        for (MTDERelationship relationship : declaredDomainRelationships) {
            if (relationship.hasTag(tag)) {
                return relationship;
            }
        }
        return null;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
        description = "Returns all the domain relationships tagged with the specified tag.")
    public Collection<MTDERelationship> relationshipsTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        ArrayList<MTDERelationship> list = new ArrayList<>();
        for (MTDERelationship relationship : declaredDomainRelationships) {
            if (relationship.hasTag(tag)) {
                list.add(relationship);
            }
        }
        return list;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
        description =
            "Indicates whether this domain entity has a relationship **to** a domain entity with the specified "
            + "tag.")
    public boolean hasRelationshipToEntityTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        for (MTDERelationship relationship : getRelationships()) {
            if (relationship.getTo().getEntity().hasTag(tag)) {
                return true;
            }
        }
        return false;
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns the all the domain relationships of this domain entity.")
    public Collection<MTDERelationship> getRelationships() {
        return domainRelationshipMap.values();
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
        description =
            "Returns the first found domain relationship of this domain entity that is tagged with the specified "
            + "tag. Although it is possible to have more than one, this method is used when expecting just one "
            + "by nature of the tag ")
    public MTDERelationship relationshipToEntityTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        for (MTDERelationship relationship : getRelationships()) {
            if (relationship.getTo().getEntity().hasTag(tag)) {
                return relationship;
            }
        }
        return null;
    }

    @ModelMethod(category = ModelMethodCategory.CONFIGURATION,
        description = "Returns the space in which this domain entity was defined.")
    public MTSpace getSpace() {
        if (entity != null) {
            return entity.getSpace();
        }
        return null;
    }
}

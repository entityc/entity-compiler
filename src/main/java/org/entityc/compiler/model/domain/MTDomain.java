/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.domain;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.OrderedHashSet;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.model.MTModule;
import org.entityc.compiler.model.MTNamespace;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.MTReferenceResolution;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTEnumItem;
import org.entityc.compiler.model.entity.MTRelationship;
import org.entityc.compiler.model.entity.MTTemplateSupport;
import org.entityc.compiler.model.entity.MTTypedef;
import org.entityc.compiler.model.entity.MTView;
import org.entityc.compiler.model.tagging.MTTagContext;
import org.entityc.compiler.model.tagging.MTTagDef;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ModelClass(type = ModelClassType.DOMAIN, description = "Represents a domain.")
public class MTDomain extends MTNode implements MTReferenceResolution, MTTemplateSupport, MTNamed {

    public static final String MTVariableDomain = "domain";

    private final MTSpace                                  space;
    private final String                                   parentDomainName;
    private final Map<String, MTDomain>                    extendingDomainMap          = new HashMap<>(); // those domains extending this one
    private final String                                   name;
    private final Map<String, String>                      explicitAttributeNames      = new HashMap<>();
    private final List<String>                             subsetEntityNames           = new ArrayList<>();
    private final Map<String, List<String>>                subsetEntityAttributeNames  = new HashMap<>();
    private final Map<String, Boolean>                     includeEntityAttributeNames = new HashMap<>();
    private final Map<Class, MTNaming>                     domainNamingByClass         = new HashMap<>();
    private final OrderedHashSet<MTDEntity>                declaredDomainEntities      = new OrderedHashSet<>();
    private final Set<MTDEntity>                           domainEntities              = new HashSet<>();
    private final Map<String, MTDSpace>                    domainSpacesByName          = new HashMap<>();
    private final Map<String, MTDModule>                   domainModulesByName         = new HashMap<>();
    private final Map<String, MTDEntity>                   domainEntitiesByName        = new HashMap<>();
    private final Map<String, MTDView>                     domainViewsByName           = new HashMap<>();
    private final Map<String, MTDEnum>                     domainEnumsByName           = new HashMap<>();
    private final Map<String, MTDTypedef>                  domainTypedefsByName        = new HashMap<>();
    private final Map<String, MTNaming>                    domainNamingByEnumName      = new HashMap<>();//
    private final Map<MTTagContext, Map<String, MTTagDef>> tagDefinitionsByContext     = new HashMap<>();
    private final List<String>                             specializedAsNames          = new ArrayList<>();
    private       MTDomain                                 parentDomain; // extended from this domain
    private       MTModule                                 module;
    private       MTNamespace                              namespace;
    private       boolean                                  flattenSecondaryEntities    = false;
    // include / exclude
    private       Boolean                                  includeEntityNames;
    private       String                                   primaryKeyName;
    private       MTDApplyTemplate                         applyTemplate;
    private       boolean                                  isSpecialized;

    public MTDomain(ParserRuleContext ctx, MTModule module, String name, String parentDomainName) {
        super(ctx);
        this.module           = module;
        this.space            = module.getSpace();
        this.name             = name;
        this.parentDomainName = parentDomainName;
    }

    public MTDomain(ParserRuleContext ctx, MTSpace space, String name, String parentDomainName) {
        super(ctx);
        this.space            = space;
        this.name             = name;
        this.parentDomainName = parentDomainName;
    }

    public void mergeInSpecializedDomain(MTDomain specializedDomain) {
        //ECLog.logInfo("Merging Specialized Domain: " + specializedDomain.getName() + "(" + specializedDomain.getSpecializedAsNames().get(0) + ")");
        super.acquireTags(specializedDomain);
        super.acquireDescriptions(specializedDomain);
        if (specializedDomain.explicitAttributeNames.size() > 0) {
            explicitAttributeNames.putAll(specializedDomain.explicitAttributeNames);
        }
        if (specializedDomain.subsetEntityNames.size() > 0) {
            setSubsetOfEntityNames(specializedDomain.subsetEntityNames, specializedDomain.includeEntityNames);
        }
        if (specializedDomain.subsetEntityAttributeNames.size() > 0) {
            for (String entityName : specializedDomain.subsetEntityAttributeNames.keySet()) {
                setSubsetOfEntityAttributeNames(entityName,
                                                specializedDomain.subsetEntityAttributeNames.get(entityName),
                                                specializedDomain.includeEntityAttributeNames.get(entityName));
            }
        }
        domainNamingByClass.putAll(specializedDomain.domainNamingByClass);
        declaredDomainEntities.addAll(specializedDomain.declaredDomainEntities);
        domainEntities.addAll(specializedDomain.domainEntities);
        domainSpacesByName.putAll(specializedDomain.domainSpacesByName);
        domainModulesByName.putAll(specializedDomain.domainModulesByName);
        domainEntitiesByName.putAll(specializedDomain.domainEntitiesByName);
        domainViewsByName.putAll(specializedDomain.domainViewsByName);
        domainEnumsByName.putAll(specializedDomain.domainEnumsByName);
        domainTypedefsByName.putAll(specializedDomain.domainTypedefsByName);
        domainNamingByEnumName.putAll(specializedDomain.domainNamingByEnumName);
        // don't need to copy tagDefinitionsByContext since its not used for specialized domains
        if (specializedDomain.applyTemplate != null) {
            applyTemplate = specializedDomain.applyTemplate;
        }
        specializedAsNames.addAll(specializedDomain.specializedAsNames);
        if (specializedDomain.namespace != null) {
            namespace = specializedDomain.namespace;
        }
    }

    private void setSubsetOfEntityNames(List<String> entityNames, boolean forInclude) {
        if (includeEntityNames != null && includeEntityNames != forInclude) {
            System.err.println("ERROR: Cannot both include and exclude a list of entities.");
            return;
        }
        subsetEntityNames.addAll(entityNames);
        includeEntityNames = forInclude;
    }

    private void setSubsetOfEntityAttributeNames(String entityName, List<String> attributeNames, boolean forInclude) {
        Boolean includeAttributeNames = includeEntityAttributeNames.get(entityName);
        if (includeAttributeNames != null && includeAttributeNames != forInclude) {
            System.err.println("ERROR: Cannot both include and exclude a list of entities.");
            return;
        }
        List<String> subsetAttributeNames = subsetEntityAttributeNames.get(entityName);
        if (subsetAttributeNames == null) {
            subsetAttributeNames = new ArrayList<>();
            subsetEntityAttributeNames.put(entityName, subsetAttributeNames);
        }
        subsetAttributeNames.addAll(attributeNames);
        includeEntityAttributeNames.put(entityName, forInclude);
    }

    public boolean isSpecialized() {
        return isSpecialized;
    }

    public MTDomain getBaseDomain() {
        if (space != null) {
            return space.getDomainWithName(getName());
        }
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    public void addSpecializedAsName(String specializedAsName) {
        this.isSpecialized = true;
        this.specializedAsNames.add(specializedAsName);
    }

    public List<String> getSpecializedAsNames() {
        return specializedAsNames;
    }

    public void addTagDef(MTTagDef tagDef) {
        if (tagDef.getTagContext() == null) {
            return;
        }
        Map<String, MTTagDef> tagDefInContext = tagDefinitionsByContext.get(tagDef.getTagContext());
        if (tagDefInContext == null) {
            tagDefInContext = new HashMap<>();
            tagDefinitionsByContext.put(tagDef.getTagContext(), tagDefInContext);
        }

        if (tagDefInContext.containsKey(tagDef.getTag())) {
            ECLog.logWarning(tagDef, "Duplicate tag definition for: " + tagDef);
        }
        tagDefInContext.put(tagDef.getTag(), tagDef);
    }

    public boolean hasTagDefs() {
        return hasEntityTagDefs() || hasAttributeTagDefs() || hasRelationshipTagDefs();
    }

    public boolean hasEntityTagDefs() {
        Map<String, MTTagDef> tagDefMap = tagDefinitionsByContext.get(MTTagContext.ENTITY);
        return tagDefMap != null && tagDefMap.size() > 0;
    }

    public boolean hasAttributeTagDefs() {
        Map<String, MTTagDef> tagDefMap = tagDefinitionsByContext.get(MTTagContext.ATTRIBUTE);
        return tagDefMap != null && tagDefMap.size() > 0;
    }

    public boolean hasRelationshipTagDefs() {
        Map<String, MTTagDef> tagDefMap = tagDefinitionsByContext.get(MTTagContext.RELATIONSHIP);
        return tagDefMap != null && tagDefMap.size() > 0;
    }

    public List<MTTagDef> getEntityTagDefs() {
        Map<String, MTTagDef> tagDefMap = tagDefinitionsByContext.get(MTTagContext.ENTITY);
        return tagDefMap != null ?
               new ArrayList<>(tagDefMap.values()) :
               new ArrayList<>();
    }

    public MTTagDef getEntityTagDef(FTTransformSession transformSession, String tag) {
        Map<String, MTTagDef> tagDefMap = tagDefinitionsByContext.get(MTTagContext.ENTITY);
        return tagDefMap != null ?
               tagDefMap.get(tag) :
               null;
    }

    public List<MTTagDef> getAttributeTagDefs() {
        Map<String, MTTagDef> tagDefMap = tagDefinitionsByContext.get(MTTagContext.ATTRIBUTE);
        return tagDefMap != null ?
               new ArrayList<>(tagDefMap.values()) :
               new ArrayList<>();
    }

    public MTTagDef getAttributeTagDef(FTTransformSession transformSession, String tag) {
        Map<String, MTTagDef> tagDefMap = tagDefinitionsByContext.get(MTTagContext.ATTRIBUTE);
        return tagDefMap != null ?
               tagDefMap.get(tag) :
               null;
    }

    public List<MTTagDef> getRelationshipTagDefs() {
        Map<String, MTTagDef> tagDefMap = tagDefinitionsByContext.get(MTTagContext.RELATIONSHIP);
        return tagDefMap != null ?
               new ArrayList<>(tagDefMap.values()) :
               new ArrayList<>();
    }

    public MTTagDef getRelationshipTagDef(FTTransformSession transformSession, String tag) {
        Map<String, MTTagDef> tagDefMap = tagDefinitionsByContext.get(MTTagContext.RELATIONSHIP);
        return tagDefMap != null ?
               tagDefMap.get(tag) :
               null;
    }

    public void setDomainNamingToEnum(String enumName, MTNaming naming) {
        domainNamingByEnumName.put(enumName, naming);
    }

    public MTNaming getDomainNamingForEnum(String enumName) {
        return domainNamingByEnumName.get(enumName);
    }

    public MTDomain getParentDomain() {
        return parentDomain;
    }

    private void addExtendingDomain(MTDomain extendingDomain) {
        extendingDomainMap.put(extendingDomain.getName(), extendingDomain);
    }

    public MTDApplyTemplate getApplyTemplate() {
        return applyTemplate;
    }

    public void setApplyTemplate(MTDApplyTemplate applyTemplate) {
        this.applyTemplate = applyTemplate;
    }

    public void setIncludeEntityNames(List<String> entityNames) {
        setSubsetOfEntityNames(entityNames, true);
    }

    public void setExcludeEntityNames(List<String> entityNames) {
        setSubsetOfEntityNames(entityNames, false);
    }

    public void setIncludeEntityAttributeNames(String entityName, List<String> attributeNames) {
        setSubsetOfEntityAttributeNames(entityName, attributeNames, true);
    }

    public void setExcludeEntityAttributeNames(String entityName, List<String> attributeNames) {
        setSubsetOfEntityAttributeNames(entityName, attributeNames, false);
    }

    public boolean isEntityAttributeIncluded(String entityName, String attributeName) {
        if (!isEntityIncluded(entityName)) {
            return false;
        }
        List<String> subsetAttributeNames = subsetEntityAttributeNames.get(entityName);
        if (subsetAttributeNames == null) {
            return true;
        }
        if (includeEntityAttributeNames.get(entityName)) {
            return subsetAttributeNames.contains(attributeName);
        }
        return !subsetAttributeNames.contains(attributeName);
    }

    public boolean isEntityIncluded(String entityName) {
        if (subsetEntityNames.isEmpty()) {
            return true; // all included by default
        }
        if (includeEntityNames) {
            return subsetEntityNames.contains(entityName);
        }
        return !subsetEntityNames.contains(entityName);
    }

    public void setExplicitAttributeName(String attributeName, String explicitAttributeName) {
        explicitAttributeNames.put(attributeName, explicitAttributeName);
    }

    public boolean hasExplicitAttributeName(String attributeName) {
        return explicitAttributeNames.containsKey(attributeName);
    }

    public String getExplicitAttributeName(String attributeName) {
        return explicitAttributeNames.get(attributeName);
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }

    public MTNamespace getNamespace() {
        return namespace;
    }

    public void setNamespace(MTNamespace namespace) {
        this.namespace = namespace;
        if (namespace.isRelativeToSpace()) {
            namespace.setSpace(this.space);
        }
    }

    public Set<MTDEntity> getDomainEntities() {
        return domainEntities;
    }

    public List<MTDEnum> getDomainEnums() {
        return new ArrayList<>(domainEnumsByName.values());
    }

    public MTDEntity getDomainEntityByName(String entityName) {
        return this.domainEntitiesByName.get(entityName);
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    // This should be run after the last resolveReferences has run
    public void checkValidReferences(MTSpace space) {
        for (MTDEntity domainEntity : domainEntities) {
            if (domainEntity.getEntity() == null) {
                ECLog.logFatal(domainEntity, "Could not find entity: " + domainEntity.getEntityName());
            }
        }
    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        if (pass == 0) {
            //ECLog.logInfo("====================================================");
            //ECLog.logInfo("DOMAIN CREATE PASS for domain: " + getName());
            // create domain entities that don't already exist
            for (MTEntity entity : space.getEntities()) {
                getDomainEntity(entity, true);
            }
            // create domain enums that don't already exist
            for (MTEnum mtEnum : space.getEnums()) {
                getDomainEnum(mtEnum, true);
            }
        }

        boolean needsAnotherPass = false;

        if (parentDomainName != null && parentDomain == null) {
            parentDomain = space.getDomainWithName(parentDomainName);
            if (parentDomain != null) {
                parentDomain.addExtendingDomain(this);
            }
            else {
                needsAnotherPass = true;
            }
        }
        if (needsAnotherPass) {
            return needsAnotherPass;
        }

        for (MTDEntity domainEntity : domainEntities) {
            if (domainEntity.resolveReferences(space, pass)) {
                needsAnotherPass = true;
            }
        }
        for (MTDModule domainModule : getDomainModules()) {
            if (domainModule.resolveReferences(space, pass)) {
                needsAnotherPass = true;
            }
        }
        for (MTDEnum domainEnum : domainEnumsByName.values()) {
            if (domainEnum.resolveReferences(space, pass)) {
                needsAnotherPass = true;
            }
        }
        return needsAnotherPass;
    }

    public MTDEntity getDomainEntity(MTEntity entity, boolean createIfNeeded) {
        if (entity == null) {
            ECLog.logFatal(this, "CANT GET A DOMAIN ENTITY FOR A NULL ENTITY!");
        }
        MTDEntity domainEntity = this.domainEntitiesByName.get(entity.getName());
        if (domainEntity == null) {
            if (parentDomain != null) {
                domainEntity = parentDomain.getDomainEntity(entity, false);
            }
        }
        if (createIfNeeded && domainEntity == null) {
            domainEntity = new MTDEntity(entity.getParserRuleContext(), this, entity);
            this.addDomainEntity(domainEntity, false);
        }
        return domainEntity;
    }

    public MTDView getDomainViewByName(String viewName) {
        MTDView domainView = domainViewsByName.get(viewName);
        if (domainView == null && parentDomain != null) {
            MTDView parentDomainView = parentDomain.getDomainViewByName(viewName);
            if (parentDomainView != null) {
                domainView = MTDView.Copy(this, parentDomainView);
                addDomainView(domainView);
            }
        }
        return domainView;
    }

    public void addDomainView(MTDView domainView) {
        domainViewsByName.put(domainView.getViewName(), domainView);
    }

    public MTDEnum getDomainEnum(MTEnum mtEnum, boolean createIfNeeded) {
        MTDEnum domainEnum = this.domainEnumsByName.get(mtEnum.getName());
        if (createIfNeeded && domainEnum == null) {
            domainEnum = new MTDEnum(mtEnum.getParserRuleContext(), this, mtEnum);
            this.domainEnumsByName.put(mtEnum.getName(), domainEnum);
        }
        return domainEnum;
    }

    public List<MTDModule> getDomainModules() {
        return new ArrayList<>(domainModulesByName.values());
    }

    public void addDomainEntity(MTDEntity entity, boolean declared) {
        if (declared) {
            this.declaredDomainEntities.add(entity);
        }
        this.domainEntities.add(entity);
        this.domainEntitiesByName.put(entity.getEntityName(), entity);
    }

    public void addDomainEnum(MTDEnum mtdEnum) {
        this.domainEnumsByName.put(mtdEnum.getEnumName(), mtdEnum);
    }

    public MTDTypedef getDomainTypedef(MTTypedef mtTypedef, boolean createIfNeeded) {
        MTDTypedef domainTypedef = this.domainTypedefsByName.get(mtTypedef.getName());
        if (createIfNeeded && domainTypedef == null) {
            domainTypedef = new MTDTypedef(mtTypedef.getParserRuleContext(), this, mtTypedef);
            this.domainTypedefsByName.put(mtTypedef.getName(), domainTypedef);
        }
        return domainTypedef;
    }

    public void addDomainModule(MTDModule domainModule) {
        this.domainModulesByName.put(domainModule.getModuleName(), domainModule);
    }

    public MTDModule getDomainModule(MTModule module, boolean createIfNeeded) {
        MTDModule domainModule = this.domainModulesByName.get(module.getName());
        if (createIfNeeded && domainModule == null) {
            domainModule = new MTDModule(module.getParserRuleContext(), this, module);
            this.domainModulesByName.put(module.getName(), domainModule);
        }
        return domainModule;
    }

    public MTDSpace getDomainSpace(MTSpace space, boolean createIfNeeded) {
        MTDSpace domainSpace = this.domainSpacesByName.get(space.getName());
        if (createIfNeeded && domainSpace == null) {
            domainSpace = new MTDSpace(space.getParserRuleContext(), this, space);
            this.domainSpacesByName.put(space.getName(), domainSpace);
        }
        return domainSpace;
    }

    public boolean attributeHasReplacedBitfield(MTAttribute attribute) {
        return attributeReplacedBitfield(attribute) != null;
    }

    public MTDEAttributeBitField attributeReplacedBitfield(MTAttribute attribute) {
        for (MTDEntity entity : domainEntities) {
            if (!entity.getName().equals(attribute.getEntity().getName())) {
                continue;
            }
            for (MTDEAttribute domainAttribute : entity.getDomainAttributes()) {
                for (MTDEAttributeBitField bitField : domainAttribute.getReplacedBitFields()) {
                    if (bitField.getReplacedAttributeName().equals(attribute.getName())) {
                        return bitField;
                    }
                }
            }
        }
        return null;
    }

    public boolean isFlattenSecondaryEntities() {
        return flattenSecondaryEntities;
    }

    public void setFlattenSecondaryEntities(boolean flattenSecondaryEntities) {
        this.flattenSecondaryEntities = flattenSecondaryEntities;
    }

    public MTModule getModule() {
        return module;
    }

    public MTSpace getSpace() {
        if (space == null && module != null) {
            return module.getSpace();
        }
        return space;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MTDomain) {
            return name.equals(((MTDomain) obj).name);
        }
        return false;
    }

    public void registerNamingForClass(MTNaming naming, Class classWithNaming) {
        domainNamingByClass.put(classWithNaming, naming);
    }

    public String getNameOfNode(MTNode node) {
        if (node instanceof MTDomainBased) {
            return ((MTNamed) node).getName();
        }
        else if (node instanceof MTView) {
            MTView view = (MTView) node;
            if (view.getEntity() == null) {
                ECLog.logFatal(view, "No entity associated with view!: " + view.getName());
            }
            MTDEntity domainEntity = domainEntitiesByName.get(view.getEntity().getName());
            String entityName = domainEntity.getExplicitName() != null ?
                                domainEntity.getExplicitName() :
                                domainEntity.getEntity().getName();
            String fullViewName = entityName + view.getName();
            return getNameFromDomainNaming(view, fullViewName);
        }
        else if (node instanceof MTEntity) {
            MTEntity    entity        = ((MTEntity) node);
            MTAttribute manyAttribute = entity.getManyAttribute();
            if (manyAttribute != null) {
                MTNaming naming = getNamingForClass(node.getClass());
                return getNameOfNode(manyAttribute.getEntity()) + naming.getMethod().getDelimiter() + getNameOfNode(
                    manyAttribute);
            }
            MTDEntity domainEntity = domainEntitiesByName.get(entity.getName());
            if (domainEntity != null) {
                return domainEntity.getName();
            }
            else {
                return getNameFromDomainNaming((MTNamed) node);
            }
        }
        else if (node instanceof MTAttribute) {
            MTAttribute attribute    = (MTAttribute) node;
            MTEntity    entity       = attribute.getEntity();
            MTDEntity   domainEntity = getDomainEntity(entity, true);
            if (domainEntity == null) {
                ECLog.logFatal("Cannot find domain entity: " + entity.getName());
            }
            return getNameOfNode(domainEntity.getDomainAttributeByName(attribute.getName(), true));
        }
        else if (node instanceof MTRelationship) {
            MTRelationship relationship = (MTRelationship) node;
            MTEntity       entity       = relationship.getFrom().getEntity();
            if (entity == null) {
                String fromRelationshipName = relationship.getFrom().getEntityName();
                entity = space.getEntityWithName(fromRelationshipName);
                if (entity != null) {
                    relationship.getFrom().setEntity(entity);
                }
                else {
                    MTNaming relationshipNaming = getNamingForClass(MTRelationship.class);
                    if (relationshipNaming != null) {
                        //ECLog.logInfo("RESORTING TO RELATIONSHIP NAMING FOR: " + relationship.getName());
                        return relationshipNaming.rename(relationship.getName());
                    }
                    else {
                        return relationship.getName();
                    }
                    //ECLog.logFatal("Cannot find from entity: " + relationship.getFrom().getEntityName() + " in relationship: " + fromRelationshipName);
                }
            }
            MTDEntity domainEntity = getDomainEntity(entity, true);
            if (domainEntity == null) {
                ECLog.logFatal("Cannot find domain entity: " + entity.getName());
            }
            return getNameOfNode(domainEntity.getDomainEntityRelationshipByName(relationship.getName(), true));
        }
        else if (node instanceof MTEnum) {
            MTEnum  mtEnum     = (MTEnum) node;
            MTDEnum domainEnum = getDomainEnum(mtEnum, true);
            return domainEnum.getName();
        }
        else if (node instanceof MTEnumItem) {
            MTEnumItem  mtEnumItem     = (MTEnumItem) node;
            MTDEnum     domainEnum     = getDomainEnum(mtEnumItem.getEnum(), true);
            MTDEnumItem domainEnumItem = domainEnum.getDomainEnumItem(mtEnumItem, true);
            return domainEnumItem.getName();
        }

        return null;
    }

    String getNameFromDomainNaming(MTNamed node) {
        return getNameFromDomainNaming(node, null);
    }

    public String getNameFromDomainNaming(MTNamed node, String alternateName) {

        if (node == null) {
            for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
                System.out.println(ste);
            }
            ECLog.logFatal("The node to rename is null!");
        }
        String nodeName = alternateName != null ?
                          alternateName :
                          node.getName();
        if (node instanceof MTEnumItem) {
            MTEnumItem item   = (MTEnumItem) node;
            MTNaming   naming = this.getDomainNamingForEnum(item.getEnum().getName());
            if (naming != null) {
                return naming.rename(nodeName);
            }
        }
        MTNaming naming = getNamingForClass(node.getClass());
        if (naming == null) {
            //ECLog.logWarning((MTNode) node, "No declared naming for " + node.getClass().getSimpleName());
            return nodeName;
        }
        return naming.rename(nodeName);
    }

    public MTNaming getNamingForClass(Class classWithNaming) {
        return domainNamingByClass.get(classWithNaming);
    }

    public OrderedHashSet<MTDEntity> getDeclaredDomainEntities() {
        return this.declaredDomainEntities;
    }
}

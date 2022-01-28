/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.domain;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.MTReferenceResolution;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTRelationship;
import org.entityc.compiler.model.entity.MTTagSet;
import org.entityc.compiler.model.entity.MTView;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MTDView extends MTNode implements MTNamed, MTReferenceResolution {

    private final String                        viewName;
    private final String                        entityName;
    private final Map<String, MTDEAttribute>    domainAttributeMap                      = new HashMap<>();
    private final Map<String, MTDERelationship> domainRelationshipMap                   = new HashMap<>();
    private final List<String>                  includedAttributeNames                  = new ArrayList<>();
    private final List<MTTagSet>                includedAttributeTagSets                = new ArrayList<>();
    private final List<MTTagSet>                includedAttributeSecondaryEntityTagSets = new ArrayList<>();
    private final List<String>                  includedRelationshipNames               = new ArrayList<>();
    private final List<String>                  includedRelationshipEntityNames         = new ArrayList<>();
    private final List<MTTagSet>                includedRelationshipTagSets             = new ArrayList<>();
    private final List<String>                  excludedAttributeNames                  = new ArrayList<>();
    private final List<MTTagSet>                excludedAttributeTagSets                = new ArrayList<>();
    private final List<MTTagSet>                excludedAttributeSecondaryEntityTagSets = new ArrayList<>();
    private final List<String>                  excludedRelationshipNames               = new ArrayList<>();
    private final List<String>                  excludedRelationshipEntityNames         = new ArrayList<>();
    private final List<MTTagSet>                excludedRelationshipTagSets             = new ArrayList<>();
    private final Map<String, String>           relationshipEntityNameToName            = new HashMap<>();
    private final Map<String, String>           relationshipNameToName                  = new HashMap<>();
    private final Map<String, String>           relationshipEntityNameWithViewName      = new HashMap<>();
    private final Map<String, String>           relationshipNameWithViewName            = new HashMap<>();
    private final List<String>                  relationshipEntityNameWithPrimaryKey    = new ArrayList<>();
    private final List<String>                  relationshipNameWithPrimaryKey          = new ArrayList<>();
    private       MTDomain                      domain;
    private       boolean                       relationshipWithPrimaryKey              = false;
    private       String                        relationshipWithViewName;
    private       MTEntity                      entity;
    private       MTDEAttribute                 primaryKeyAttribute;
    private       boolean                       includedPrimaryKey                      = false;
    private       boolean                       excludedPrimaryKey                      = false;
    private       boolean                       includedArrayAttributes                 = false;
    private       boolean                       includedAllAttributes                   = false;
    private       boolean                       excludedAllAttributes                   = false;
    private       boolean                       excludedArrayAttributes                 = false;
    private       boolean                       includedCreationAttributes              = false;
    private       boolean                       excludedCreationAttributes              = false;
    private       boolean                       includedModificationAttributes          = false;
    private       boolean                       excludedModificationAttributes          = false;
    private       boolean                       includedParentRelationships             = false;
    private       boolean                       excludedParentRelationships             = false;
    private       boolean                       includedOneRelationships                = false;
    private       boolean                       excludedOneRelationships                = false;
    private       boolean                       includedManyRelationships               = false;
    private       boolean                       excludedManyRelationships               = false;
    private       boolean                       includedAllRelationships                = false;
    private       boolean                       excludedAllRelationships                = false;
    private       boolean                       resolvedReferences                      = false;

    public MTDView(ParserRuleContext ctx, MTDomain domain, String viewName) {
        super(ctx);
        this.domain     = domain;
        this.entityName = null; // not with respect to any entity
        this.viewName   = viewName;
    }

    public MTDView(ParserRuleContext ctx, MTDomain domain, String entityName, String viewName) {
        super(ctx);
        this.domain     = domain;
        this.entityName = entityName;
        this.viewName   = viewName;
    }

    public static MTDView Copy(MTDomain otherDomain, MTDView domainView) {
        return Copy(otherDomain, domainView, null);
    }

    public static MTDView Copy(MTDomain otherDomain, MTDView domainView, String entityName) {
        MTDView domainEntityView = new MTDView(domainView.getParserRuleContext(), otherDomain, entityName,
                                               domainView.getViewName());
        domainEntityView.includedPrimaryKey       = domainView.includedPrimaryKey;
        domainEntityView.excludedPrimaryKey       = domainView.excludedPrimaryKey;
        domainEntityView.includedAllAttributes    = domainView.includedAllAttributes;
        domainEntityView.excludedAllAttributes    = domainView.excludedAllAttributes;
        domainEntityView.includedAllRelationships = domainView.includedAllRelationships;
        domainEntityView.excludedAllRelationships = domainView.excludedAllRelationships;
        domainEntityView.includedAttributeNames.addAll(domainView.includedAttributeNames);
        domainEntityView.excludedAttributeNames.addAll(domainView.excludedAttributeNames);
        domainEntityView.includedAttributeTagSets.addAll(domainView.includedAttributeTagSets);
        domainEntityView.excludedAttributeTagSets.addAll(domainView.excludedAttributeTagSets);
        domainEntityView.includedArrayAttributes        = domainView.includedArrayAttributes;
        domainEntityView.excludedArrayAttributes        = domainView.excludedArrayAttributes;
        domainEntityView.includedCreationAttributes     = domainView.includedCreationAttributes;
        domainEntityView.excludedCreationAttributes     = domainView.excludedCreationAttributes;
        domainEntityView.includedModificationAttributes = domainView.includedModificationAttributes;
        domainEntityView.excludedModificationAttributes = domainView.excludedModificationAttributes;
        domainEntityView.includedRelationshipTagSets.addAll(domainView.includedRelationshipTagSets);
        domainEntityView.excludedRelationshipTagSets.addAll(domainView.excludedRelationshipTagSets);
        domainEntityView.includedRelationshipEntityNames.addAll(domainView.includedRelationshipEntityNames);
        domainEntityView.excludedRelationshipEntityNames.addAll(domainView.excludedRelationshipEntityNames);
        domainEntityView.includedRelationshipNames.addAll(domainView.includedRelationshipNames);
        domainEntityView.excludedRelationshipNames.addAll(domainView.excludedRelationshipNames);
        domainEntityView.includedParentRelationships = domainView.includedParentRelationships;
        domainEntityView.excludedParentRelationships = domainView.excludedParentRelationships;
        domainEntityView.includedOneRelationships    = domainView.includedOneRelationships;
        domainEntityView.excludedOneRelationships    = domainView.excludedOneRelationships;
        domainEntityView.includedManyRelationships   = domainView.includedManyRelationships;
        domainEntityView.excludedManyRelationships   = domainView.excludedManyRelationships;
        domainEntityView.relationshipEntityNameToName.putAll(domainView.relationshipEntityNameToName);
        domainEntityView.relationshipNameToName.putAll(domainView.relationshipNameToName);
        domainEntityView.relationshipEntityNameWithViewName.putAll(domainView.relationshipEntityNameWithViewName);
        domainEntityView.relationshipNameWithViewName.putAll(domainView.relationshipNameWithViewName);
        domainEntityView.relationshipEntityNameWithPrimaryKey.addAll(domainView.relationshipEntityNameWithPrimaryKey);
        domainEntityView.relationshipNameWithPrimaryKey.addAll(domainView.relationshipNameWithPrimaryKey);
        domainEntityView.relationshipWithPrimaryKey = domainView.relationshipWithPrimaryKey;
        domainEntityView.relationshipWithViewName   = domainView.relationshipWithViewName;
        return domainEntityView;
    }

    public String getViewName() {
        return viewName;
    }

    /**
     * Creates a entity specific view from a domain view.
     *
     * @param domainView The domain view from which to copy.
     * @param entityName The name of the entity to which to make this specific.
     * @return The entity specific domain view.
     */
    public static MTDView DomainEntityView(MTDView domainView, String entityName) {
        //ECLog.logInfo("MAKING ENTITY SPECIFIC VIEW from VIEW " + domainView.getViewName() + " for ENTITY " + entityName + " in DOMAIN " + domainView.getDomain().getName());
        MTDView domainEntityView = Copy(domainView.getDomain(), domainView, entityName);
        domainEntityView.resolveReferences(domainView.getDomain().getSpace(), 0);
        return domainEntityView;
    }

    public MTDomain getDomain() {
        return domain;
    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        if (domain != null && domain.isSpecialized()) {
            domain = domain.getBaseDomain();
        }
        if (resolvedReferences) {
            return false;
        }
        // if this represents a domain only view, then there is nothing to resolve
        if (entityName == null) {
            resolvedReferences = true;
            return false;
        }

        // first make sure we resolve the entity we are a view of
        if (this.entity == null) {
            this.entity = space.getEntityWithName(entityName);
            if (this.entity == null) {
                return true; // can't proceed until this is resolved first
            }
        }
        boolean anotherPass = false;

        // resolve attributes next
        boolean includingAttributes = includedAllAttributes
                                      || !includedAttributeNames.isEmpty()
                                      || includedArrayAttributes
                                      || includedCreationAttributes
                                      || includedModificationAttributes
                                      || !includedAttributeTagSets.isEmpty();
        boolean excludingAttributes = excludedAllAttributes
                                      || !excludedAttributeNames.isEmpty()
                                      || excludedArrayAttributes
                                      || excludedCreationAttributes
                                      || excludedModificationAttributes
                                      || !excludedAttributeTagSets.isEmpty();
        if (includingAttributes && excludingAttributes) {
            ECLog.logFatal("View + " + getName() + " cannot both include and exclude attributes from entity "
                           + entity.getName());
        }
        List<String> names = includingAttributes ?
                             includedAttributeNames :
                             excludedAttributeNames;

        // make sure all attribute names have been resolved in pass 0 before going on
        for (String name : names) {
            if (entity.getAttributeByName(name) == null) {
                if (pass == 0) {
                    anotherPass = true;
                }
            }
        }

        if (anotherPass) {
            return anotherPass;
        }

        boolean includingRelationships = includedAllRelationships
                                         || !includedRelationshipNames.isEmpty()
                                         || !includedRelationshipTagSets.isEmpty()
                                         || includedParentRelationships
                                         || includedOneRelationships
                                         || includedManyRelationships
                                         || !includedRelationshipEntityNames.isEmpty()
                                         || relationshipWithPrimaryKey
                                         || relationshipWithViewName != null
                                         || !relationshipEntityNameWithViewName.isEmpty();
        boolean excludingRelationships = excludedAllRelationships
                                         || !excludedRelationshipNames.isEmpty()
                                         || !excludedRelationshipTagSets.isEmpty()
                                         || excludedParentRelationships
                                         || excludedOneRelationships
                                         || excludedManyRelationships
                                         || !excludedRelationshipEntityNames.isEmpty();

        names = includingRelationships ?
                includedRelationshipNames :
                excludedRelationshipNames;

        // make sure all attribute names have been resolved in pass 0 before going on
        for (String name : names) {
            if (entity.getRelationshipByName(name) == null) {
                if (pass == 0) {
                    anotherPass = true;
                }
            }
        }

        if (anotherPass) {
            return anotherPass;
        }

        if (!excludedPrimaryKey && entity.hasPrimaryKey()) {
            primaryKeyAttribute = new MTDEAttribute(getParserRuleContext(), domain, entity.getPrimaryKeyAttribute());
        }

        //
        // Build the Domain Attribute Map
        //
        this.domainAttributeMap.clear();
        for (MTAttribute attribute : entity.getAttributes()) {
            if (attribute.getType() == null) {
                if (attribute.getTypeName() != null) {
                    //  MTEntity typeEntity = model.getEntityWithName(attribute.getTypeName());
                    if (attribute.resolveReferences(space, 0)) {
                        ECLog.logError(attribute, "Attribute does not have a type: " + attribute.getName());
                        anotherPass = true;
                        break;
                    }
                }
                continue;
            }
            boolean addToAttributeMap = false;
            if (includingAttributes) {
                if (includedAllAttributes
                    || includedAttributeNames.contains(attribute.getName())
                    || (includedArrayAttributes && attribute.isArray())
                    || (includedCreationAttributes && attribute.isCreation())
                    || (includedModificationAttributes && attribute.isModification())
                ) {
                    addToAttributeMap = true;
                }
                else if (!includedAttributeTagSets.isEmpty()) {
                    for (MTTagSet tagSet : includedAttributeTagSets) {
                        if (tagSet.matches(attribute.getTags())) {
                            addToAttributeMap = true;
                            break;
                        }
                    }
                }
            }
            else {
                if (!excludingAttributes) {
                    addToAttributeMap = true;
                }
                else {
                    if (excludedAllAttributes
                        || (excludedArrayAttributes && attribute.isArray())
                        || (excludedCreationAttributes && attribute.isCreation())
                        || (excludedModificationAttributes && attribute.isModification())) {
                        continue;
                    }
                    else if (!excludedAttributeTagSets.isEmpty()) {
                        boolean excludeAttribute = false;
                        for (MTTagSet tagSet : excludedAttributeTagSets) {
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
                MTDEAttribute domainAttribute = new MTDEAttribute(getParserRuleContext(), domain, attribute);
                domainAttributeMap.put(attribute.getName(), domainAttribute);
            }
        }
        for (MTDEAttribute mtdeAttribute : domainAttributeMap.values()) {
            if (mtdeAttribute.resolveReferences(space, pass)) {
                anotherPass = true;
            }
        }

        if (anotherPass) {
            return anotherPass;
        }

        // Build Domain Relationshp Map
        this.domainRelationshipMap.clear();
        for (MTRelationship relationship : entity.getRelationships()) {
            boolean add = true;
            if (includingRelationships) {
                add = false;
                if (includedAllRelationships
                    || (includedParentRelationships && relationship.isParent())
                    || (includedOneRelationships && relationship.getTo().isOne())
                    || (includedManyRelationships && relationship.getTo().isMany())) {
                    add = true;
                }
                else {
                    add = includedRelationshipNames.contains(relationship.getName())
                          || includedRelationshipEntityNames.contains(relationship.getTo().getEntityName());
                }
            }
            else if (excludingRelationships) {
                if (excludedAllRelationships) {
                    add = false;
                }
                else if ((excludedParentRelationships && relationship.isParent())
                         || (excludedOneRelationships && relationship.getTo().isOne())
                         || (excludedManyRelationships && relationship.getTo().isMany())) {
                    add = false;
                }
                else {
                    add = !(excludedRelationshipNames.contains(relationship.getName())
                            || excludedRelationshipEntityNames.contains(relationship.getTo().getEntityName()));
                }
            }
            if (add) {
                MTDERelationship domainRelationship = new MTDERelationship(getParserRuleContext(), domain,
                                                                           relationship);

                // handle rename
                if (relationshipNameToName.containsKey(relationship.getName())) {
                    domainRelationship.setExplicitName(relationshipNameToName.get(relationship.getName()));
                }
                else if (relationshipEntityNameToName.containsKey(relationship.getTo().getEntityName())) {
                    domainRelationship.setExplicitName(
                        relationshipEntityNameToName.get(relationship.getTo().getEntityName()));
                }
                if (!relationshipEntityNameWithViewName.isEmpty() || relationshipWithViewName != null) {
                    // assign view if specified
                    String withViewName = null;
                    if (relationshipWithViewName != null) {
                        withViewName = relationshipWithViewName;
                    }
                    else if (relationshipNameWithViewName.containsKey(relationship.getName())) {
                        withViewName = relationshipNameWithViewName.get(relationship.getName());
                    }
                    else if (relationshipEntityNameWithViewName.containsKey(relationship.getTo().getEntityName())) {
                        withViewName = relationshipEntityNameWithViewName.get(relationship.getTo().getEntityName());
                    }
                    if (withViewName != null) {
                        domainRelationship.setWithViewName(withViewName);
                    }
                }
                if (relationshipWithPrimaryKey
                    || relationshipNameWithPrimaryKey.contains(relationship.getName())
                    || relationshipEntityNameWithPrimaryKey.contains(relationship.getTo().getEntityName())) {
                    domainRelationship.setWithPrimaryKey(true);
                }
                domainRelationshipMap.put(relationship.getName(), domainRelationship);
            }
        }
        for (MTDERelationship mtdeRelationship : domainRelationshipMap.values()) {
            if (mtdeRelationship.resolveReferences(space, pass)) {
                anotherPass = true;
            }
        }

        if (!anotherPass) {
            resolvedReferences = true;
        }
        return anotherPass;
    }

    @Override
    public String getName() {
        MTNaming naming = domain.getNamingForClass(MTView.class);
        if (naming == null) {
            ECLog.logFatal("View naming not defined in domain: " + domain.getName());
        }
        return naming.rename(entityName + viewName);
    }

    public MTDEAttribute getPrimaryKeyAttribute() {
        return primaryKeyAttribute;
    }

    public boolean isRelationshipWithPrimaryKey() {
        return relationshipWithPrimaryKey;
    }

    public void setRelationshipWithPrimaryKey(boolean relationshipWithPrimaryKey) {
        this.relationshipWithPrimaryKey = relationshipWithPrimaryKey;
    }

    public String getRelationshipWithViewName() {
        return relationshipWithViewName;
    }

    public void setRelationshipWithViewName(String relationshipWithViewName) {
        this.relationshipWithViewName = relationshipWithViewName;
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

    public boolean isIncludedArrayAttributes() {
        return includedArrayAttributes;
    }

    public void setIncludedArrayAttributes(boolean includedArrayAttributes) {
        this.includedArrayAttributes = includedArrayAttributes;
    }

    public boolean isIncludedAllAttributes() {
        return includedAllAttributes;
    }

    public void setIncludedAllAttributes(boolean includedAllAttributes) {
        this.includedAllAttributes = includedAllAttributes;
    }

    public boolean isExcludedAllAttributes() {
        return excludedAllAttributes;
    }

    public void setExcludedAllAttributes(boolean excludedAllAttributes) {
        this.excludedAllAttributes = excludedAllAttributes;
    }

    public boolean isExcludedArrayAttributes() {
        return excludedArrayAttributes;
    }

    public void setExcludedArrayAttributes(boolean excludedArrayAttributes) {
        this.excludedArrayAttributes = excludedArrayAttributes;
    }

    public boolean isIncludedCreationAttributes() {
        return includedCreationAttributes;
    }

    public void setIncludedCreationAttributes(boolean includedCreationAttributes) {
        this.includedCreationAttributes = includedCreationAttributes;
    }

    public boolean isExcludedCreationAttributes() {
        return excludedCreationAttributes;
    }

    public void setExcludedCreationAttributes(boolean excludedCreationAttributes) {
        this.excludedCreationAttributes = excludedCreationAttributes;
    }

    public boolean isIncludedModificationAttributes() {
        return includedModificationAttributes;
    }

    public void setIncludedModificationAttributes(boolean includedModificationAttributes) {
        this.includedModificationAttributes = includedModificationAttributes;
    }

    public boolean isExcludedModificationAttributes() {
        return excludedModificationAttributes;
    }

    public void setExcludedModificationAttributes(boolean excludedModificationAttributes) {
        this.excludedModificationAttributes = excludedModificationAttributes;
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

    public boolean isIncludedOneRelationships() {
        return includedOneRelationships;
    }

    public void setIncludedOneRelationships(boolean includedOneRelationships) {
        this.includedOneRelationships = includedOneRelationships;
    }

    public boolean isExcludedOneRelationships() {
        return excludedOneRelationships;
    }

    public void setExcludedOneRelationships(boolean excludedOneRelationships) {
        this.excludedOneRelationships = excludedOneRelationships;
    }

    public boolean isIncludedManyRelationships() {
        return includedManyRelationships;
    }

    public void setIncludedManyRelationships(boolean includedManyRelationships) {
        this.includedManyRelationships = includedManyRelationships;
    }

    public boolean isExcludedManyRelationships() {
        return excludedManyRelationships;
    }

    public void setExcludedManyRelationships(boolean excludedManyRelationships) {
        this.excludedManyRelationships = excludedManyRelationships;
    }

    public boolean isIncludedAllRelationships() {
        return includedAllRelationships;
    }

    public void setIncludedAllRelationships(boolean includedAllRelationships) {
        this.includedAllRelationships = includedAllRelationships;
    }

    public boolean isExcludedAllRelationships() {
        return excludedAllRelationships;
    }

    public void setExcludedAllRelationships(boolean excludedAllRelationships) {
        this.excludedAllRelationships = excludedAllRelationships;
    }

    public void addIncludedAttributeName(String name) {
        includedAttributeNames.add(name);
    }

    public void addExcludedAttributeName(String name) {
        excludedAttributeNames.add(name);
    }

    public void addIncludedRelationshipName(String name) {
        includedRelationshipNames.add(name);
    }

    public void addExcludedRelationshipName(String name) {
        excludedRelationshipNames.add(name);
    }

    public void addIncludedRelationshipEntityName(String name) {
        includedRelationshipEntityNames.add(name);
    }

    public void addExcludedRelationshipEntityName(String name) {
        excludedRelationshipEntityNames.add(name);
    }

    public void addIncludedAttributeTagSet(MTTagSet tagSet) {
        includedAttributeTagSets.add(tagSet);
    }

    public void addExcludedAttributeTagSet(MTTagSet tagSet) {
        excludedAttributeTagSets.add(tagSet);
    }

    public void addIncludedAttributeSecondaryEntityTagSet(MTTagSet tagSet) {
        includedAttributeSecondaryEntityTagSets.add(tagSet);
    }

    public void addExcludedAttributeSecondaryEntityTagSet(MTTagSet tagSet) {
        excludedAttributeSecondaryEntityTagSets.add(tagSet);
    }

    public void addIncludedRelationshipTagSet(MTTagSet tagSet) {
        includedRelationshipTagSets.add(tagSet);
    }

    public void addExcludedRelationshipTagSet(MTTagSet tagSet) {
        excludedRelationshipTagSets.add(tagSet);
    }

    public void renameRelationshipOfEntityName(String entityName, String relationshipName) {
        relationshipEntityNameToName.put(entityName, relationshipName);
    }

    public void renameRelationship(String fromName, String toName) {
        relationshipNameToName.put(fromName, toName);
    }

    public void setViewNameForRelationshipName(String relationshipName, String viewName) {
        relationshipNameWithViewName.put(relationshipName, viewName);
    }

    public void setViewNameForRelationshipEntityName(String entityName, String viewName) {
        relationshipEntityNameWithViewName.put(entityName, viewName);
    }

    public void setPrimaryKeyForRelationshipName(String relationshipName) {
        relationshipNameWithPrimaryKey.add(relationshipName);
    }

    public void setPrimaryKeyForRelationshipEntityName(String enittyName) {
        relationshipEntityNameWithPrimaryKey.add(enittyName);
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    public MTDEAttribute getDomainAttributeByName(String attributeName, boolean createIfNeeded) {
        MTDEAttribute domainAttribute = domainAttributeMap.get(attributeName);
        if (createIfNeeded && domainAttribute == null) {
            //ECLog.logInfo("Created domain: " + domain.getName() + " attribute: " + attributeName + " for entity: " + entityName);
            domainAttribute = new MTDEAttribute(null, this.domain, entityName, attributeName);
            domainAttribute.setViewName(this.viewName);
            domainAttribute.resolveReferences(this.domain.getSpace(), 0);
            domainAttributeMap.put(attributeName, domainAttribute);
        }
        return domainAttribute;
    }

    public boolean hasPrimaryKey() {
        return primaryKeyAttribute != null;
    }

    public Collection<MTDEAttribute> getAttributes() {
        return domainAttributeMap.values();
    }

    public Collection<MTDERelationship> getRelationships() {
        return domainRelationshipMap.values();
    }
}

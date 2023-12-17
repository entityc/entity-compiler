/*
 * Copyright (c) 2019-2023 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform;

import org.entityc.compiler.model.MTModule;
import org.entityc.compiler.model.MTRoot;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.config.MTTransform;
import org.entityc.compiler.model.domain.MTDEAttribute;
import org.entityc.compiler.model.domain.MTDERelationship;
import org.entityc.compiler.model.domain.MTDEntity;
import org.entityc.compiler.model.domain.MTDomain;
import org.entityc.compiler.model.entity.*;
import org.entityc.compiler.structure.sql.SSSourceFile;
import org.entityc.compiler.util.ECLog;
import org.entityc.compiler.util.ECStringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This transform
 */
public class MTVReleasedTransform extends MTBaseTransform {
    private final MTDomain releasedDomain;
    private String compositEntityNamePrefix = "";
    private SSSourceFile sourceFile;

    public static final String realm = "Released";

    public MTVReleasedTransform(MTRoot root, String configurationName) {
        super("Released", root, configurationName);
        releasedDomain = root.getSpace().getDomainWithName("Released");
        compositEntityNamePrefix = "Released";
    }

    @Override
    public boolean canStart() {
        return true;
    }

    @Override
    public void start(String ignore) {

        super.start(realm);

        MTTransform transform = getConfiguration().getTransformByName(getName());

        MTSpace space = this.root.getSpace();

        Map<String, MTEntity> nonCompositeEntityMap = new HashMap<>();
        Map<String, MTCompositeEntity> compositeEntityConstituentMap = new HashMap<>();
        Map<String, MTEntity> releasedRelatedEntityMap = new HashMap<>();
        Map<String, MTEntity> releasedVarientOfEntityMap = new HashMap<>();

        List<MTRelationship> relationshipsToResolve = new ArrayList<>();
        List<MTCompositeEntity> compositeEntityList = new ArrayList<>();

        for (MTModule module : space.getModules()) {
            //ECLog.logInfo("--------------------------------------------------------- MODULE: " + module.getName());
            if (module.isIncluded()) {
                //ECLog.logInfo("Ignoring module: " + module.getName());
                continue;
            }
            //
            // Find all the ones that are not involved in versioning and add them
            // to our new "version-less" realm.
            //
            for (MTEntity entity : module.getEntities()) {
                if (entity.hasTag("release:binder")
                    || entity.hasTag("release:object")
                    || entity.hasTag("release:version")) {
                    continue;
                }
                entity.addRealm(realm);
            }
            //
            // Go through all the entities, find the ones that have both an object and version,
            // create a new composite entity and then copy over the attributes (we will copy the
            // relationships in another pass).
            //
            for (MTEntity entity : module.getEntities()) {
                if (entity.isIncluded() || entity.isExtern() || entity.isImplicit() || entity.isTransient()
                    || !entity.hasTag("release:binder")) {
                    continue;
                }

                MTRelationship topRelationship = null;
                MTRelationship objectRelationship = null;
                MTRelationship versionRelationship = null;

                for (MTRelationship relationship : entity.getRelationships()) {
                    MTEntity toEntity = relationship.getTo().getEntity();
                    if (relationship.isParent() && toEntity.hasTag("release:top")) {
                        topRelationship = relationship;
                    }
                    else if (toEntity.hasTag("release:object")) {
                        objectRelationship = relationship;
                    }
                    else if (toEntity.hasTag("release:version")) {
                        versionRelationship = relationship;
                    }
                }
                if (topRelationship == null) {
                    ECLog.logFatal(entity, "Unable to find release top.");
                }
                if (objectRelationship == null) {
                    ECLog.logFatal(entity, "Unable to find release object.");
                }
                if (versionRelationship == null) {
                    ECLog.logFatal(entity, "Unable to find release version.");
                }

                MTEntity releaseEntity = topRelationship.getTo().getEntity();
                MTEntity objectEntity = objectRelationship.getTo().getEntity();
                MTEntity versionEntity = versionRelationship.getTo().getEntity();

                // keep track of ones related to the Released entities
                releasedRelatedEntityMap.put(entity.getName(), entity); // binder
                releasedRelatedEntityMap.put(releaseEntity.getName(), releaseEntity);
                releasedRelatedEntityMap.put(objectEntity.getName(), objectEntity);
                releasedRelatedEntityMap.put(versionEntity.getName(), versionEntity);

                //
                // Now create a new entity that unifies these three
                //
                MTCompositeEntity compositeEntity = new MTCompositeEntity(objectEntity.getParserRuleContext(),
                    objectEntity.getModule(),
                    compositEntityNamePrefix + objectEntity.getName());
                compositeEntity.addRealm(realm);
                releasedRelatedEntityMap.put(compositeEntity.getName(), compositeEntity);
                releasedVarientOfEntityMap.put(objectEntity.getName(), compositeEntity);

                compositeEntity.addConstituentEntity(MTCompositeEntity.BinderTag, entity);

                //
                // Object Entity Primary Key
                //
                MTAttribute primaryKeyAttribute = new MTAttribute(objectEntity.getParserRuleContext(),
                    compositeEntity, objectEntity.getPrimaryKeyAttribute().getTypeName(),
                    objectEntity.getPrimaryKeyAttribute().getName());
                MTPrimaryKey newPrimaryKey = new MTPrimaryKey(objectEntity.getPrimaryKey().getParserRuleContext());
                newPrimaryKey.addAttribute(primaryKeyAttribute);
                compositeEntity.setPrimaryKey(newPrimaryKey);

                //
                // Foreign from Release
                //
                MTRelationship releaseFKRelationship = new MTRelationship(
                    topRelationship.getParserRuleContext(),
                    ECStringUtil.Uncapitalize(releaseEntity.getName()),
                    compositeEntity.getName(),
                    HalfRelationshipPlurality.ONE,
                    releaseEntity.getName(), true, true, null, null, null);
                releaseFKRelationship.addTag("ignore");
                compositeEntity.addRelationship(releaseFKRelationship);
                compositeEntity.addConstituentEntity(MTCompositeEntity.ReleaseTag, releaseEntity);
                relationshipsToResolve.add(releaseFKRelationship);
                //
                // Object Entity Attributes
                //
                for (MTAttribute attribute : objectEntity.getAttributes()) {
                    if (attribute.isModification() || versionEntity.hasAttributeNamed(attribute.getName())) {
                        continue;
                    }
                    MTAttribute compositAttribute = MTAttribute.Copy(attribute, compositeEntity);
                    compositeEntity.addAttribute(MTCompositeEntity.ObjectTag, compositAttribute, objectEntity);
                }

                //
                // Version Entity Attributes
                //
                for (MTAttribute attribute : versionEntity.getAttributes()) {
                    if (attribute.isCreation()) {
                        continue;
                    }
                    MTAttribute compositAttribute = MTAttribute.Copy(attribute, compositeEntity);
                    compositAttribute.setDescription(attribute.getDescription());
                    compositeEntity.addAttribute(MTCompositeEntity.VersionTag, compositAttribute, versionEntity);
                }

                compositeEntityList.add(compositeEntity);
                //ECLog.logInfo("Creating Composite Entity: " + compositeEntity.getName());
                compositeEntityConstituentMap.put(objectEntity.getName(), compositeEntity);
                compositeEntityConstituentMap.put(versionEntity.getName(), compositeEntity);

                // create a one to many relationship on the release entity to this composite entity
                MTRelationship releaseRelationship = new MTRelationship(
                    topRelationship.getParserRuleContext(),
                    ECStringUtil.Uncapitalize(compositEntityNamePrefix) + ECStringUtil.Capitalize(ECStringUtil.Pluralize(objectRelationship.getName())),
                    releaseEntity.getName(),
                    HalfRelationshipPlurality.MANY,
                    compositeEntity.getName(), true, false, null, null, null);
                releaseEntity.addRelationship(releaseRelationship);
                relationshipsToResolve.add(releaseRelationship);

                // Relationships are more complicated so we first need to process all
                // the entities first, then do another pass so we can randomly reference the other
                // entities.
            }
        }
        //ECLog.logInfo("FOUND " + compositeEntityList.size() + " composite entities");

        //
        // Now we need to see all the other entities related to the object or version entities and convert them also
        // to composite entities (even though they don't have version entities) so that we don't have non-composite
        // entities making reference to composite entities.
        //
        boolean foundReleasedRelated;
        int newReleasedEntities = 0;
        do {
            foundReleasedRelated = false;
            for (MTModule module : space.getModules()) {

                if (module.isIncluded()) {
                    continue;
                }

                // TODO: we need to also check relationships from composite to not-fully-composite since it may be assumed
                for (MTEntity entity : module.getEntities()) {
                    if (entity.isIncluded() || entity.isExtern() || entity.isImplicit()
                        || entity.isSecondary() || entity.isTransient()) {
                        continue;
                    }
                    if (entity.isCompositeEntity() || releasedVarientOfEntityMap.containsKey(entity.getName())) {
                        continue; // we have already determined this is release related
                    }

                    if (entity.hasTagPrefixed("release:")) {
                        continue;
                    }

                    //
                    // Determine if this entity has a relationship with a "Released" entity and if so, get the
                    // top Release entity.
                    //
                    MTEntity releaseEntity = null;
                    boolean isRelatedToReleased = false;
                    for (MTRelationship relationship : entity.getRelationships()) {
                        MTEntity toEntity = relationship.getTo().getEntity();
                        if (releasedVarientOfEntityMap.containsKey(toEntity.getName())) {
                            //ECLog.logInfo("----- is related to released via relationship: " + entity.getName() + "." + relationship.getName() + " toEntity = " + toEntity.getName());
                            MTEntity relatedEntity = releasedVarientOfEntityMap.get(toEntity.getName());
                            if (relatedEntity.isCompositeEntity()) {
                                releaseEntity = ((MTCompositeEntity) relatedEntity).getConstituentEntity(MTCompositeEntity.ReleaseTag);
                                isRelatedToReleased = true;
                                break;
                            }
                        }
                    }

                    //
                    // If it is not related to a released entity then skip the rest.
                    //
                    if (!isRelatedToReleased) {
                        continue;
                    }

                    //
                    // Create the Released "composite" entity. Even though it will be created as a composite entity, it will only have
                    // the object and release constituents.
                    //
                    foundReleasedRelated = true;
                    MTCompositeEntity releasedEntity = new MTCompositeEntity(entity.getParserRuleContext(), entity.getModule(), compositEntityNamePrefix + entity.getName());
                    releasedVarientOfEntityMap.put(entity.getName(), releasedEntity);
                    releasedEntity.addConstituentEntity(MTCompositeEntity.ObjectTag, entity);
                    compositeEntityConstituentMap.put(entity.getName(), releasedEntity);
                    releasedEntity.addRealm(realm);
                    compositeEntityList.add(releasedEntity);
                    //ECLog.logInfo("Creating Released Entity: " + releasedEntity.getName() + " related to object: " + entity.getName());
                    newReleasedEntities++;

                    //
                    // copy primary key
                    //
                    MTAttribute primaryKeyAttribute = new MTAttribute(entity.getParserRuleContext(),
                        releasedEntity, entity.getPrimaryKeyAttribute().getTypeName(),
                        entity.getPrimaryKeyAttribute().getName());
                    MTPrimaryKey newPrimaryKey = new MTPrimaryKey(entity.getPrimaryKey().getParserRuleContext());
                    newPrimaryKey.addAttribute(primaryKeyAttribute);
                    releasedEntity.setPrimaryKey(newPrimaryKey);


                    //
                    // Create relationship from this Released entity back to the "release" entity
                    //
                    MTRelationship releaseFKRelationship = new MTRelationship(
                        releasedEntity.getParserRuleContext(),
                        ECStringUtil.Uncapitalize(releaseEntity.getName()),
                        releasedEntity.getName(),
                        HalfRelationshipPlurality.ONE,
                        releaseEntity.getName(), true, true, null, null, null);
                    releaseFKRelationship.addTag("ignore");
                    releasedEntity.addRelationship(releaseFKRelationship);
                    releasedEntity.addConstituentEntity(MTCompositeEntity.ReleaseTag, releaseEntity);
                    relationshipsToResolve.add(releaseFKRelationship);

                    // create a one to many relationship on the release entity to this composite entity
                    MTRelationship releaseRelationship = new MTRelationship(
                        releasedEntity.getParserRuleContext(),
                        ECStringUtil.Uncapitalize(compositEntityNamePrefix) + ECStringUtil.Capitalize(ECStringUtil.Pluralize(entity.getName())),
                        releaseEntity.getName(),
                        HalfRelationshipPlurality.MANY,
                        releasedEntity.getName(), true, false, null, null, null);
                    releaseEntity.addRelationship(releaseRelationship);
                    relationshipsToResolve.add(releaseRelationship);

                    //
                    // copy attributes
                    //
                    for (MTAttribute attribute : entity.getAttributes()) {
                        MTAttribute releasedAttribute = MTAttribute.Copy(attribute, releasedEntity);
                        releasedEntity.addAttribute(releasedAttribute);
                    }

                    //
                    // copy relationships but change TO relationship to the Released version
                    //
                    for (MTRelationship relationship : entity.getRelationships()) {
                        MTEntity toEntity = relationship.getTo().getEntity();

                        //ECLog.logInfo("CHECKING relationships for: " + entity.getName() + " relationship to entity: " + toEntity.getName());
                        MTCompositeEntity toCompositeEntity = compositeEntityConstituentMap.get(toEntity.getName());
                        if (toCompositeEntity != null && toCompositeEntity.isCompositeEntity() && !((MTCompositeEntity) releasedEntity).hasConstituentEntity(MTCompositeEntity.ReleaseTag)) {
                            //ECLog.logInfo("COPYING CONSTITUENT ENTITY (" + MTCompositeEntity.ReleaseTag + ") "+ toCompositeEntity.getConstituentEntity(MTCompositeEntity.ReleaseTag).getName() + " to Composite Entity: " + toCompositeEntity.getName());
                            MTEntity toEntityReleaseEntity = toCompositeEntity.getConstituentEntity(MTCompositeEntity.ReleaseTag);
                            releasedEntity.addConstituentEntity(MTCompositeEntity.ReleaseTag, toEntityReleaseEntity);
//                            compositeEntityConstituentMap.put(toEntityReleaseEntity.getName(), releasedEntity);
                        }

                        if (!compositeEntityConstituentMap.containsKey(toEntity.getName())) {
                            // we can copy the relationship exactly - just have to fix the "from" which is done
                            // in the Copy method.
                            MTRelationship newRelationship = MTRelationship.Copy(relationship, releasedEntity, null);
                            //ECLog.logInfo("Adding Composite Object Relationship to non-composite entity: " + newRelationship.getName());
                            releasedEntity.addRelationship(newRelationship);
                            continue;
                        }
                        if (compositeEntityConstituentMap.containsKey(toEntity.getName())) {
                            MTEntity releasedToEntity = compositeEntityConstituentMap.get(toEntity.getName());
                            releasedToEntity.addRealm(realm);
                            //ECLog.logInfo("Adding REALM " + realm + " to release entity: " + releasedToEntity.getName());
                            MTRelationship releasedRelationship = MTRelationship.Copy(relationship, releasedEntity, releasedToEntity);
                            releasedEntity.addRelationship(releasedRelationship);
                        }
                    }
                    releasedRelatedEntityMap.put(releasedEntity.getName(), releasedEntity);
                    releasedEntity.getModule().addEntity(releasedEntity);
                    space.addEntity(releasedEntity);
                }
            }
        }
        while (foundReleasedRelated);
        //ECLog.logInfo("FOUND " + newReleasedEntities + " released entities");
        //
        // Now that all the composite entities have been created, we can copy the relationships.
        //
        //ECLog.logInfo("Resolving relationships...");
        for (MTCompositeEntity compositeEntity : compositeEntityList) {
            //ECLog.logInfo("Looking at composite entity: " + compositeEntity.getName());
            MTEntity objectEntity = compositeEntity.getConstituentEntity(MTCompositeEntity.ObjectTag);
            MTEntity versionEntity = compositeEntity.getConstituentEntity(MTCompositeEntity.VersionTag);
            for (MTRelationship relationship : objectEntity.getRelationships()) {
                if (compositeEntity.hasRelationshipNamed(relationship.getName())) {
                    //ECLog.logWarning("We have already copied relationship: " + relationship);
                    continue;
                }
                MTEntity toEntity = relationship.getTo().getEntity();
                // don't take relationships to version
                if (versionEntity != null && toEntity.getName().equals(versionEntity.getName())) {
                    continue;
                }
                if (!compositeEntityConstituentMap.containsKey(toEntity.getName())) {
                    // we can copy the relationship exactly - just have to fix the "from" which is done
                    // in the Copy method.
                    MTRelationship newRelationship = MTRelationship.Copy(relationship, compositeEntity, null);
                    //ECLog.logInfo("---- Adding Composite Object Relationship to non-composite entity: " + newRelationship);
                    compositeEntity.addRelationship(newRelationship);
                    relationshipsToResolve.add(newRelationship);
                    continue;
                }
                MTCompositeEntity toCompositeEntity = compositeEntityConstituentMap.get(toEntity.getName());
                MTRelationship newRelationship = MTRelationship.Copy(relationship, compositeEntity, toCompositeEntity);
                compositeEntity.addRelationship(newRelationship);
                relationshipsToResolve.add(newRelationship);
                //ECLog.logInfo("Adding Composite Object Relationship to Composite object: " + newRelationship);
            }
            if (versionEntity != null) {
                //ECLog.logInfo("Entity " + compositeEntity.getName() + " has Version entity " + versionEntity.getName() + " has " + versionEntity.getRelationshipCount() + " relationships.");
                for (MTRelationship relationship : versionEntity.getRelationships()) {
                    MTEntity toEntity = relationship.getTo().getEntity();
                    if (toEntity == null) {
                        ECLog.logWarning("Relationship " + versionEntity.getName() + "." + relationship.getName() + " does NOT have a \"to\" entity!");
                        continue;
                    }
                    // don't take relationships to object parent
                    if (relationship.isParent() && toEntity.getName().equals(objectEntity.getName())) {
                        continue;
                    }
                    if (!compositeEntityConstituentMap.containsKey(toEntity.getName())) {
                        // we can copy the relationship exactly - just have to fix the "from" which is done
                        // in the Copy method.
                        MTRelationship newRelationship = MTRelationship.Copy(relationship, compositeEntity, null);
                        //ECLog.logInfo("Adding Composite Version Relationship to non-composite entity: " + newRelationship.getName());
                        compositeEntity.addRelationship(newRelationship);
                        relationshipsToResolve.add(newRelationship);
                        continue;
                    }
                    MTCompositeEntity toCompositeEntity = compositeEntityConstituentMap.get(toEntity.getName());
                    MTRelationship newRelationship = MTRelationship.Copy(relationship, compositeEntity, toCompositeEntity);
                    //ECLog.logInfo("Adding Composite Version Relationship to Composite object: " + newRelationship);
                    compositeEntity.addRelationship(newRelationship);
                    relationshipsToResolve.add(newRelationship);
                }
            }
            compositeEntity.getModule().addEntity(compositeEntity);
            space.addEntity(compositeEntity);
        }

        // ------------------------------------------------
        // It turns out that for any entity related to a
        // Released entity we also need to create a Released
        // version of it so it.
        // ------------------------------------------------

        for (MTRelationship relationship : relationshipsToResolve) {
            relationship.resolveReferences(space, 3);
            if (relationship.getTo().getEntity() == null) {
                ECLog.logFatal("Why cant we resolve this relationship?: " + relationship.getName());
            }
            else {
                //ECLog.logInfo("Newly created relationship is resolved: " + relationship.getFrom().getEntityName() + "." + relationship.getName());
            }
        }
//        for (MTModule module : space.getModules()) {
//
//            if (module.isIncluded()) {
//                continue;
//            }
//
//            for (MTEntity entity : module.getEntities()) {
//                if (entity.isIncluded() || entity.isExtern() || entity.isImplicit()
//                    || entity.isSecondary()) {
//                    continue;
//                }
//                if (releasedRelatedEntityMap.containsKey(entity.getName())) {
//                    continue; // we have already determined this is release related
//                }
//                //ECLog.logInfo("FOUND NON-RELEASED ENTITY: " + entity.getName());
//            }
//        }
    }
}

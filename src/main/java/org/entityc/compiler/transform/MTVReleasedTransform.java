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

    public static final String realm = "released";

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

        Map<String, MTCompositeEntity> compositeEntityMap = new HashMap<>();

        for (MTModule module : space.getModules()) {
            //ECLog.logInfo("--------------------------------------------------------- MODULE: " + module.getName());
            if (module.isIncluded()) {
                //ECLog.logInfo("Ignoring module: " + module.getName());
                continue;
            }
            // Find all the ones that are not involved in versioning and add them
            // to our new "version-less" realm.
            for (MTEntity entity : module.getEntities()) {
                if (entity.hasTag("release:binder")
                    || entity.hasTag("release:object")
                    || entity.hasTag("release:version")) {
                    continue;
                }
                entity.addRealm(realm);
            }
            List<MTCompositeEntity> compositeEntityList = new ArrayList<>();
            for (MTEntity entity : module.getEntities()) {
                if (!entity.hasTag("release:binder") || entity.isIncluded() || entity.isExtern() || entity.isImplicit()) {
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

                // Now create a new entity that unifies these three

                MTCompositeEntity compositeEntity = new MTCompositeEntity(objectEntity.getParserRuleContext(), objectEntity.getModule(), compositEntityNamePrefix + objectEntity.getName());
                compositeEntity.addRealm(realm);

                //
                // Object Entity Primary Key
                //
                MTAttribute primaryKeyAttribute = new MTAttribute(objectEntity.getParserRuleContext(),
                    compositeEntity, objectEntity.getPrimaryKeyAttribute().getTypeName(),
                    ECStringUtil.Uncapitalize(compositEntityNamePrefix) + ECStringUtil.Capitalize(objectEntity.getPrimaryKeyAttribute().getName()));
                MTPrimaryKey newPrimaryKey = new MTPrimaryKey(objectEntity.getPrimaryKey().getParserRuleContext());
                newPrimaryKey.addAttribute(primaryKeyAttribute);
                compositeEntity.setPrimaryKey(newPrimaryKey);

                //
                // Foreign from Release
                //
                MTAttribute releaseEntityForeignKey = MTAttribute.Copy(releaseEntity.getPrimaryKeyAttribute(), compositeEntity);
                releaseEntityForeignKey.setPrimaryKey(false);
                compositeEntity.addAttribute(MTCompositeEntity.ReleaseTag, releaseEntityForeignKey, releaseEntity);

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
                compositeEntityMap.put(objectEntity.getName(), compositeEntity);
                compositeEntityMap.put(versionEntity.getName(), compositeEntity);

                // Relationships are more complicated so we first need to process all
                // the entities first, then do another pass so we can randomly reference the other
                // entities.
            }
            //ECLog.logInfo("FOUND " + compositeEntityList.size() + " composite objects in module: " + module.getName());
            for (MTCompositeEntity compositeEntity : compositeEntityList) {
                MTEntity objectEntity = compositeEntity.getConstituentEntity(MTCompositeEntity.ObjectTag);
                MTEntity versionEntity = compositeEntity.getConstituentEntity(MTCompositeEntity.VersionTag);
                //ECLog.logInfo("Object entity " + objectEntity.getName() + " has " + objectEntity.getRelationshipCount() + " relationships.");
                for (MTRelationship relationship : objectEntity.getRelationships()) {
                    //ECLog.logInfo(">>>> relationship: " + relationship.getName());
                    if (compositeEntity.hasRelationshipNamed(relationship.getName())) {
                        ECLog.logWarning("We have already copied relationship: " + relationship.getName());
                        continue;
                    }
                    MTEntity toEntity = relationship.getTo().getEntity();
                    // don't take relationships to version
                    if (toEntity.getName().equals(versionEntity.getName())) {
                        continue;
                    }
                    if (!compositeEntityMap.containsKey(toEntity.getName())) {
                        // we can copy the relationship exactly - just have to fix the "from" which is done
                        // in the Copy method.
                        MTRelationship newRelationship = MTRelationship.Copy(relationship, compositeEntity, null);
                        //ECLog.logInfo("Adding Composite Object Relationship to non-composite entity: " + newRelationship.getName());
                        compositeEntity.addRelationship(newRelationship);
                        continue;
                    }
                    MTCompositeEntity toCompositeEntity = compositeEntityMap.get(toEntity.getName());
                    MTRelationship newRelationship = MTRelationship.Copy(relationship, compositeEntity, toCompositeEntity);
                    compositeEntity.addRelationship(newRelationship);
                    //ECLog.logInfo("Adding Composite Object Relationship to Composite object: " + newRelationship.getName());
                }
                //ECLog.logInfo("Version entity " + versionEntity.getName() + " has " + versionEntity.getRelationshipCount() + " relationships.");
                for (MTRelationship relationship : versionEntity.getRelationships()) {
                    MTEntity toEntity = relationship.getTo().getEntity();
                    // don't take relationships to object parent
                    if (relationship.isParent() && toEntity.getName().equals(objectEntity.getName())) {
                        continue;
                    }
                    if (!compositeEntityMap.containsKey(toEntity.getName())) {
                        // we can copy the relationship exactly - just have to fix the "from" which is done
                        // in the Copy method.
                        MTRelationship newRelationship = MTRelationship.Copy(relationship, compositeEntity, null);
                        //ECLog.logInfo("Adding Composite Version Relationship to non-composite entity: " + newRelationship.getName());
                        compositeEntity.addRelationship(newRelationship);
                        continue;
                    }
                    MTCompositeEntity toCompositeEntity = compositeEntityMap.get(toEntity.getName());
                    MTRelationship newRelationship = MTRelationship.Copy(relationship, compositeEntity, toCompositeEntity);
                    //ECLog.logInfo("Adding Composite Version Relationship to Composite object: " + newRelationship.getName());
                    compositeEntity.addRelationship(newRelationship);
                }
                compositeEntity.getModule().addEntity(compositeEntity);
                space.addEntity(compositeEntity);
            }
        }

    }
}

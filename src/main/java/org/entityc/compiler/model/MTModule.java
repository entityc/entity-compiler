/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.doc.annotation.ModelMethod;
import org.entityc.compiler.doc.annotation.ModelMethodCategory;
import org.entityc.compiler.doc.annotation.ModelMethodParameter;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.domain.MTNamed;
import org.entityc.compiler.model.entity.HalfRelationshipPlurality;
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTEntityTemplate;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTRelationship;
import org.entityc.compiler.model.entity.MTTemplateSupport;
import org.entityc.compiler.model.entity.MTTypedef;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ModelClass(type = ModelClassType.ENTITY, description = "Represents a grouping of entities.")
public class MTModule extends MTNode implements MTTemplateSupport, MTNamed {

    private final MTSpace                       space;
    private final String                        name;
    private final Map<String, MTEntityTemplate> entityTemplateMap = new HashMap<>();
    private final Map<String, MTEntity>         entityMap         = new HashMap<>();
    private final Map<String, MTEnum>           enumMap           = new HashMap<>();
    private final Map<String, MTTypedef>        typedefMap        = new HashMap<>();

    public MTModule(ParserRuleContext ctx, MTSpace space, String name) {
        super(ctx);
        this.space = space;
        this.name  = name;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @ModelMethod(category = ModelMethodCategory.ENTITY,
        description = "Indicates whether the specified entity is externally declared (such as in a different space).")
    public boolean isExternEntity(
        @ModelMethodParameter(description = "The name of the entity to check.")
            String entityName) {
        MTEntity entity = entityMap.get(entityName);
        return entity != null && entity.isExtern();
    }

    public void addEntity(MTEntity entity) {
        if (entity instanceof MTEntityTemplate) {
            addEntityTemplate((MTEntityTemplate) entity);
            return;
        }
        //ECLog.logInfo("Adding entity \"" + entity.getName() + "\" to module \"" + this.getName() + "\"" + (entity.isImplicit() ? " as implicit" : ""));
        entityMap.put(entity.getName(), entity);
    }

    public void addEntityTemplate(MTEntityTemplate entityTemplate) {
        this.entityTemplateMap.put(entityTemplate.getName(), entityTemplate);
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY, description = "Returns an entity of this module by its name.")
    public MTEntity getEntity(
        @ModelMethodParameter(description = "The name of the entity to return.")
            String name) {
        return entityMap.get(name);
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY, description = "Returns the number of entities declared in this module.")
    public int getEntityCount() {
        return entityMap.size();
    }

    public void addEnum(MTEnum mtEnum) {
        enumMap.put(mtEnum.getName(), mtEnum);
    }

    @ModelMethod(category = ModelMethodCategory.ENUM, description = "Returns an enum of this module by its name.")
    public MTEnum getEnum(
        @ModelMethodParameter(description = "The name of the enum to return.")
            String name) {
        return enumMap.get(name);
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY, description = "Returns all the enums of this module.")
    public List<MTEnum> getEnums() {
        ArrayList<MTEnum> all = new ArrayList<>(enumMap.values());
        all.sort(new Comparator<MTEnum>() {
            @Override
            public int compare(MTEnum o1, MTEnum o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return all;
    }

    public boolean hasEnums() {
        return enumMap.size() > 0;
    }

    public void addTypedef(MTTypedef typedef) {
        typedefMap.put(typedef.getName(), typedef);
    }

    @ModelMethod(category = ModelMethodCategory.TYPEDEF, description = "Returns a typedef of this module by its name.")
    public MTTypedef getTypedef(
        @ModelMethodParameter(description = "The name of the typedef to return.")
            String name) {
        return typedefMap.get(name);
    }

    @ModelMethod(category = ModelMethodCategory.TYPEDEF, description = "Returns all the typedefs of this module.")
    public List<MTTypedef> getTypedefs() {
        ArrayList<MTTypedef> all = new ArrayList<>(typedefMap.values());
        all.sort(new Comparator<MTTypedef>() {
            @Override
            public int compare(MTTypedef o1, MTTypedef o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return all;
    }

    @ModelMethod(category = ModelMethodCategory.TYPEDEF, description = "Returns the number of typedefs in this module.")
    public int getTypedefCount() {
        return typedefMap.size();
    }

    @ModelMethod(category = ModelMethodCategory.ENUM, description = "Returns the number of enums in this module.")
    public int getEnumCount() {
        return enumMap.size();
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY,
        description = "Indicates if this module contains any primary entities. If it contains at least one it would "
                      + "return `true`, otherwise it would mean that it only contains secondary entities and would "
                      + "return `false`.")
    public boolean hasPrimaryEntities() {
        for (MTEntity entity : getEntities()) {
            if (entity.isPrimary()) {
                return true;
            }
        }
        return false;
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY, description = "Returns all the entities of this module.")
    public List<MTEntity> getEntities() {
        ArrayList<MTEntity> all = new ArrayList<>(entityMap.values());
        all.sort(new Comparator<MTEntity>() {
            @Override
            public int compare(MTEntity o1, MTEntity o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return all;
    }

    public boolean resolveReferences(MTSpace space, int pass) {
        // this is where we should check to make sure any references indeed exist in our
        // model and create appropriate links
        boolean needsAnotherPass = false;

        for (MTEntity entity : getEntities()) {
            if (entity.resolveReferences(space, pass)) {
                needsAnotherPass = true;
            }
        }

        return needsAnotherPass;
    }

    @ModelMethod(category = ModelMethodCategory.MODULE,
        description = "Returns all the other modules that entities of this module make reference to.")
    public List<MTModule> getReferencedModules() {
        return getReferencedModules(false);
    }

    @ModelMethod(category = ModelMethodCategory.MODULE,
        description = "Returns all the other modules that entities of this module make reference to.")
    public List<MTModule> getReferencedModules(
        @ModelMethodParameter(description = "Whether to include one-to-one or many-to-one relationships when determining "
                                            + "references.")
            Boolean includeToOneRelationships) {
        Map<String, MTModule> referencedModules = new HashMap<>();
        for (MTEntity entity : entityMap.values()) {
            for (MTAttribute attribute : entity.getAttributes()) {
                if (attribute.getType() == null) {
                    attribute.resolveReferences(entity.getModule().getSpace(), 0);
                    if (attribute.getType() == null) {
                        ECLog.logFatal(attribute, "Attribute \"" + attribute.getName() + "\" has no type.");
                    }
                }
                if (attribute.getType().isEnumType()) { // locate module for any enums used as types
                    MTEnum mtEnum = (MTEnum) attribute.getType();
                    if (mtEnum.getModule() != null && !mtEnum.getModule().getName().equals(name)) {
                        referencedModules.put(mtEnum.getModule().getName(), mtEnum.getModule());
                    }
                }
                else if (attribute.getTypeEntity() != null) {
                    MTEntity secondaryEntity = attribute.getTypeEntity();
                    MTModule secondaryModule = secondaryEntity.getModule();
                    if (secondaryModule != null && !secondaryModule.getName().equals(name)) {
                        referencedModules.put(secondaryModule.getName(), secondaryModule);
                    }
                }
            }
            for (MTRelationship relationship : entity.getRelationships()) {
                if (relationship.getTo().getEntity() != null && (
                    relationship.getTo().getPlurality() == HalfRelationshipPlurality.MANY
                    || includeToOneRelationships) && !relationship.isParent()) {
                    MTModule module = relationship.getTo().getEntity().getModule();
                    if (module != null && !module.getName().equals(name)) {
                        referencedModules.put(module.getName(), module);
                    }
                }
            }
        }
        return new ArrayList<>(referencedModules.values());
    }

    @ModelMethod(category = ModelMethodCategory.CONFIGURATION,
        description = "Returns the space in which this module was declared.")
    public MTSpace getSpace() {
        return space;
    }

    @ModelMethod(category = ModelMethodCategory.MODULE, description = "Returns the name of this module.")
    @Override
    public String getName() {
        return name;
    }
}

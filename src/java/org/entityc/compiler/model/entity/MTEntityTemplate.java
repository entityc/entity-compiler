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
import org.entityc.compiler.model.MTModule;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.util.ECLog;
import org.entityc.compiler.util.ECStringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An Entity Template is the declaration of an entity using  MyEntity<T,S> so that it can be expanded into
 * other more specific entities. The name is a bit confusing with the use of Template but that is how most
 * languages refer to this pattern.
 */
@ModelClass(type = ModelClassType.ENTITY,
    description =
        "An Entity Template is the declaration of an entity in a parameterized way, such as `MyEntity<T,S>` where "
        + "`T` and `S` are parameters that are used in the body of the definition to parameterize the entity. This "
        + "entity template can then be used as a template to create other entities. Currently template entites "
        + "can only be used in a special usage of a relationship.")
public class MTEntityTemplate extends MTEntity {

    protected List<String> templateArgs = new ArrayList<>();

    public MTEntityTemplate(ParserRuleContext ctx, MTModule module, String name, List<String> templateArgs) {
        super(ctx, module, name);
        if (templateArgs != null) {
            this.templateArgs = templateArgs;
        }
    }

    public MTEntity makeEntity(MTEntity parentEntity, String asEntityName, MTEntityTemplateInstantiation instantiation, MTSpace space) {
        List<String> templateArgEntityNames = instantiation.getTemplateArgEntityNames();
        MTEntity entity = new MTEntity(instantiation.getParserRuleContext(),
                                       parentEntity.getModule(), asEntityName);
        entity.setDeclaredAsPrimary(true);
        entity.setBatchable(true);
        entity.setTemplateName(name);
        entity.attributeMap.putAll(this.attributeMap);
        entity.addTagsWithValues(this.getTagsWithValues());
        entity.setIncluded(parentEntity.isIncluded());

        // copy the primary key
        MTPrimaryKey copiedPrimaryKey = new MTPrimaryKey(getParserRuleContext());
        for (MTAttribute pka : this.primaryKey.getAttributes()) {
            MTAttribute copiedPKAttribute = MTAttribute.Copy(pka, entity);
            copiedPrimaryKey.addAttribute(copiedPKAttribute);
        }
        entity.primaryKey = copiedPrimaryKey;

        // Map the template arguments to the entity names in the instantiation
        Map<String, String> argToEntityName = new HashMap<>();
        if (templateArgs.size() != templateArgEntityNames.size()) {
            ECLog.logFatal(this, "The number of arguments of the template do not match that of its instantiation.");
        }
        for (int i = 0; i < templateArgs.size(); i++) {
            argToEntityName.put(templateArgs.get(i), templateArgEntityNames.get(i));
        }

        for (MTAttribute attribute : this.getAttributes()) {
            entity.addAttribute(MTAttribute.Copy(attribute, entity));
        }

        Map<String, MTRelationship> argToRelationship = new HashMap<>();

        MTRelationship parentRelationship = null;
        // copy the relationships, substituting template variable with instantiated entity
        for (MTRelationship relationship : this.relationships) {
            //  look for template arguments that need to be resolved.
            String templateArgName  = relationship.getTo().getEntityName();
            String mappedEntityName = argToEntityName.get(templateArgName);
            if (mappedEntityName != null) {
                MTRelationship resolvedRelationship = new MTRelationship(instantiation.getParserRuleContext(),
                                                                         ECStringUtil.Uncapitalize(mappedEntityName),
                                                                         entity.getName(),
                                                                         relationship.getTo().getPlurality(),
                                                                         mappedEntityName,
                                                                         relationship.isOptional(),
                                                                         relationship.isParent(),
                                                                         relationship.getReverseName(),
                                                                         relationship.getToEntityIdName(),
                                                                         templateArgName);
                entity.addRelationship(resolvedRelationship);
                argToRelationship.put(relationship.getTo().getEntityName(), resolvedRelationship);
                if (relationship.isParent() && !relationship.isOptional()) {
                    parentRelationship = resolvedRelationship;
                }
            }
            else {
                MTRelationship copiedRelationship = new MTRelationship(instantiation.getParserRuleContext(),
                                                                       relationship.getName(),
                                                                       entity.getName(),
                                                                       relationship.getTo().getPlurality(),
                                                                       relationship.getTo().getEntityName(),
                                                                       relationship.isOptional(),
                                                                       relationship.isParent(),
                                                                       relationship.getReverseName(),
                                                                       relationship.getToEntityIdName(),
                                                                       templateArgName);
                entity.addRelationship(copiedRelationship);
                if (relationship.isParent() && !relationship.isOptional()) {
                    parentRelationship = copiedRelationship;
                }
            }
        }

        if (parentRelationship != null) {
            for (int i = 0; i < instantiation.getTemplateArgEntityNames().size(); i++) {
                String  templateArg = templateArgs.get(i);
                Boolean unique      = instantiation.getTemplateArgUnique().get(i);
                if (unique) {
                    MTUniqueness uniqueness = new MTUniqueness(instantiation.getParserRuleContext(),
                                                               parentRelationship);
                    MTRelationship relationship = argToRelationship.get(templateArg);
                    if (relationship != null) {
                        uniqueness.setRelationship(relationship);
                        entity.addUniqueness(uniqueness);
                        //ECLog.logInfo(instantiation.getParserRuleContext(), entity.getName() + ": Added uniqueness between relationship " + parentRelationship.getTo().getEntityName() + " and relationship " + relationship.getName());
                    }
                }
            }
        }
        else {
            for (int i = 0; i < instantiation.getTemplateArgEntityNames().size(); i++) {
                Boolean unique = instantiation.getTemplateArgUnique().get(i);
                if (unique) {
                    ECLog.logFatal(instantiation.getParserRuleContext(),
                                   "Unable to set unique constraints because no parent relationship found for entity: "
                                   + parentEntity.getName());
                }
            }
        }

        space.addEntity(entity);
        if (module != null) {
            module.addEntity(entity);
        }
        entity.resolveReferences(space, 0);
        return entity;
    }

    public void addTemplateArg(String arg) {
        this.templateArgs.add(arg);
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY, description = "Returns the list of template arguments.")
    public List<String> getTemplateArgs() {
        return this.templateArgs;
    }
}

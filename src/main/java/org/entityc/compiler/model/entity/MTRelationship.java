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
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.util.ECLog;

import java.util.List;

import static org.entityc.compiler.model.entity.MTNativeType.DataType.INT64;

@ModelClass(type = ModelClassType.ENTITY, description = "Represents a relationship of an entity.")
public class MTRelationship extends MTNode implements MTReferenceResolution, MTTemplateSupport, MTNamed {

    public static final String INTERNAL_MANY_TO_MANY_TAG = "internal:many-to-many";

    private final String             name;
    private final MTRelationshipHalf to;
    private final MTRelationshipHalf from;
    private final boolean            optional;
    private final boolean            parent;
    private final String             reverseName;
    private final String             toEntityIdName;
    private final String             templateArgName;
    private       boolean            implicit;
    private       MTRelationship     reverseRelationship;
    private       MTAttribute        effectiveAttribute;

    public MTRelationship(ParserRuleContext ctx,
                          String name,
                          String fromEntityName,
                          HalfRelationshipPlurality toPlurality,
                          String toEntityName,
                          boolean optional,
                          boolean parent,
                          String reverseName,
                          String toEntityIdName,
                          String templateArgName
                         ) {
        super(ctx);
        this.name            = name;
        this.from            = new MTRelationshipHalf(ctx, fromEntityName);
        this.to              = new MTRelationshipHalf(ctx, toPlurality, toEntityName);
        this.optional        = optional;
        this.parent          = parent;
        this.reverseName     = reverseName;
        this.toEntityIdName  = toEntityIdName;
        this.templateArgName = templateArgName;
    }

    protected MTRelationship(String secondaryName, MTRelationship primaryRelationship) {
        super(primaryRelationship.getParserRuleContext());
        this.name            = secondaryName;
        this.from            = primaryRelationship.from;
        this.to              = primaryRelationship.to;
        this.optional        = primaryRelationship.optional;
        this.parent          = primaryRelationship.parent;
        this.reverseName     = primaryRelationship.reverseName;
        this.toEntityIdName  = primaryRelationship.toEntityIdName;
        this.templateArgName = primaryRelationship.templateArgName;
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Indicates whether the relationship was created because although it was not declared it can be "
                      + "implied based on relationships declared to this entity.")
    public boolean isImplicit() {
        return implicit;
    }

    public void setImplicit(boolean implicit) {
        this.implicit = implicit;
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "When a relationship is defined as part of an entity template, it has a template argument that "
                      + "will be used when an entity is created from it. This will return the name of that argument.")
    public String getTemplateArgName() {
        return templateArgName;
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns the \"to\" part of the relationship. This part tells you about the entity to which this "
                      + "relationship is bound.")
    public MTRelationshipHalf getTo() {
        return to;
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description =
            "Returns the \"from\" part of the relationship which references the entity in which this relationship "
            + "is defined.")
    public MTRelationshipHalf getFrom() {
        return from;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns the effective attribute associated with this relationship. The effective attribute "
                      + "can help you persist the relationship in a database.")
    public MTAttribute getEffectiveAttribute() {
        if (effectiveAttribute == null
            && to.getEntity() != null && from.getEntity() != null
        ) {
            if (to.getPlurality() == HalfRelationshipPlurality.ONE) {
                MTAttribute primarykeyAttribute = to.getEntity().getPrimaryKey().getAttributes().get(0);
                String      typeName            = INT64.getName();
                if (primarykeyAttribute.getType().isNativeType()) {
                    typeName = ((MTNativeType) (primarykeyAttribute.getType())).getDataType().getName();
                }
                effectiveAttribute = new MTAttribute(null, from.getEntity(), typeName, name + "Id");
                effectiveAttribute.setRelationship(this);
            }
            else if (to.getPlurality() == HalfRelationshipPlurality.MANY && !isParent()) {
                effectiveAttribute = new MTAttribute(null, from.getEntity(), to.getEntity(), name);
                effectiveAttribute.setArray(true);
                effectiveAttribute.setRelationship(this);
            }
        }
        return effectiveAttribute;
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description =
            "Indicates whether this relationship was defined with the `parent` keyword. Relationships defined "
            + "this way are ones that imply that this entity considers its identity as being the combination "
            + "of its parent entity and itself.")
    public boolean isParent() {
        return parent;
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Indicates whether this relationship was defined with the `parent` keyword and **not** with "
                      + "the `optional` keyword. Within the set of relationships of an entity, only one relationship "
                      + "should be defined this way.")
    public boolean isPrimaryParent() {
        return isParent() && !isOptional();
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Indicates whether this relationship was defined with the `optional` keyword, indicating that "
                      + "relationships to the other entity is not required.")
    public boolean isOptional() {
        return optional;
    }

    public void setToPlurality(HalfRelationshipPlurality toPlurality) {
        this.to.setPlurality(toPlurality);
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns the relationship from the perspective \"to\" entity to this entity, that is, the "
                      + "reverse relationship.")
    public MTRelationship getReverseRelationship() {
        return reverseRelationship;
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description =
            "Indicates whether this relationship is a one-to-many; where in a one-to-many relationship an object "
            + "of this entity can have multiple relationships with objects of another entity but not the other way "
            + "around.")
    public boolean isOneToMany() {
        return getFullRelationshipPlurality() == FullRelationshipPlurality.ONE_TO_MANY;
    }

    public FullRelationshipPlurality getFullRelationshipPlurality() {
        if (from.getPlurality() == HalfRelationshipPlurality.ONE) {
            if (to.getPlurality() == HalfRelationshipPlurality.ONE) {
                return FullRelationshipPlurality.ONE_TO_ONE;
            }
            else {
                return FullRelationshipPlurality.ONE_TO_MANY;
            }
        }
        if (to.getPlurality() == null || to.getPlurality() == HalfRelationshipPlurality.ONE) {
            return FullRelationshipPlurality.MANY_TO_ONE;
        }
        else {
            return FullRelationshipPlurality.MANY_TO_MANY;
        }
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description =
            "Indicates whether this relationship is a many-to-many; where in a many-to-many relationship multiple "
            + "objects of this entity can have multiple relationships with objects of another entity.")
    public boolean isManyToMany() {
        return getFullRelationshipPlurality() == FullRelationshipPlurality.MANY_TO_MANY;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        this.from.setEntity(space.getEntityWithName(from.getEntityName()));

        if (this.to.getEntity() == null) {
            if (this.to.getTemplateInstantiation() != null) {
                MTEntityTemplate template = space.getEntityTemplateWithName(
                    this.to.getTemplateInstantiation().getEntityTemplateName());
                if (template == null) {
                    ECLog.logFatal(this.to.getTemplateInstantiation(), "Unable to find entity template with name: "
                                                                       + this.to.getTemplateInstantiation().getEntityTemplateName());
                }
                this.to.setEntity(
                    template.makeEntity(from.getEntity(), to.getEntityName(), this.to.getTemplateInstantiation(),
                                        space));
            }
            else {
                this.to.setEntity(space.getEntityWithName(to.getEntityName()));
            }
        }

        if (this.from.getEntityName() == null) {
            System.err.println("ERROR: Could not resolve from entity: " + this.from.getEntityName());
            return false;
        }
        if (this.to.getEntityName() == null) {
            System.err.println("ERROR: Could not resolve to entity: " + this.to.getEntityName());
            return false;
        }

        // find a list of possble relationships between entities
        if (to.getEntity() == null) {
            MTEntity toEntity = space.getEntityWithName(to.getEntityName());
            if (toEntity == null) {
                if (pass > 7) {
                    ECLog.logFatal(this, "Unable to find entity named: " + to.getEntityName());
                }
                return false;
            }
            to.setEntity(toEntity);
        }
        List<MTRelationship> possibleFromRelationships = to.getEntity().findPossibleRelationshipsWithEntity(
            from.getEntity());

        boolean found      = false;
        boolean duplicates = false;

        if (pass == 0) {
            for (MTRelationship fromRelationship : possibleFromRelationships) {
                if ((this.getReverseName() != null && fromRelationship.getName().equals(this.getReverseName()))
                    || (fromRelationship.getReverseName() != null && fromRelationship.getReverseName().equals(
                    this.getName()))) {
                    if (found) {
                        duplicates = true;
                        ECLog.logError(
                            "Found duplicate reverse named relationship matches for: " + from.getEntityName() + " > "
                            + getReverseName() + " < " + to.getEntityName());
                        break;
                    }
                    else {
                        // good named pairing
                        //ECLog.logInfo("NAMED Relationship \"" + getName() + "\" pairing between " + from.getEntityName() + "." + this.getName() + " and " + to.getEntityName() + "." + fromRelationship.getName());
                        this.reverseRelationship = fromRelationship;
                        this.from.setPlurality(fromRelationship.to.getPlurality());
                        found = true;
                    }
                }
            }
            return !duplicates;
        }

        if (pass == 1) {
            boolean alreadyProcessed = false;
            // if it wasn't resolved in step 1
            if (this.reverseRelationship == null) {
                for (MTRelationship fromRelationship : possibleFromRelationships) {
                    if (found) {
                        duplicates = true;
                        ECLog.logWarning(fromRelationship,
                                         "Found duplicate relationship matches between " + from.getEntity().getName()
                                         + "." + this.getName() + " and " + to.getEntity().getName() + "."
                                         + fromRelationship.getName());
                        break;
                    }
                    else {
                        // good unnamed pairing
                        //System.out.println("unnamed Relationship \"" + getName() + "\" pairing between " + fromEntity.getName() + "." + this.getName() + " and " + toEntity.getName() + "." + fromRelationship.getName());
                        this.reverseRelationship = fromRelationship;
                        this.from.setPlurality(fromRelationship.to.getPlurality());
                        found = true;
                    }
                }
            }
            else {
                alreadyProcessed = true;
            }

            if (duplicates) {
                return false;
            }

            if (!found && !alreadyProcessed) {
                //System.out.println("Could not find reverse relationship between " + fromEntityName + " and " + toEntityName);
            }
        }

        return false;
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns the name of the reverse relationship.")
    public String getReverseName() {
        return reverseName;
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP, description = "Gets the name of the relationship.")
    @Override
    public String getName() {
        return name;
    }

    public MTRelationship makeCopyFromEntityName(String fromEntityNameOfCopy) {
        MTRelationship copy = new MTRelationship(null, name, fromEntityNameOfCopy, from.getPlurality(),
                                                 to.getEntityName(), optional, parent, reverseName, toEntityIdName,
                                                 templateArgName);
        return copy;
    }

    // not used much, so don't document it but keep it for when we need it
    public String getToEntityIdName() {
        return this.toEntityIdName;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
        description = "Indicates whether the \"to\" entity of this relationship has been tagged with the specified tag.")
    public boolean hasToEntityTagged(
        @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        return to != null && to.getEntity().hasTag(tag);
    }
}

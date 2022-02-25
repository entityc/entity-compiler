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
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.MTReferenceResolution;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.entity.MTRelationship;
import org.entityc.compiler.model.visitor.MTVisitor;

@ModelClass(type = ModelClassType.DOMAIN,
    description = "Represents a relationship in your model in the context of a domain.")
public class MTDERelationship extends MTNode implements MTNamed, MTDomainBased, MTReferenceResolution {

    private final String               relationshipName;
    private       MTDomain             domain;
    private       MTDEntity            domainEntity;
    private       MTRelationship       relationship;
    private       String               explicitName;
    private       String               withViewName; // to-entity should map to a specific view
    private       boolean              withPrimaryKey; // to-entity should map as its primary key only
    private       MTDERelationshipHalf to;
    private       boolean              resolvedReferences = false;

    public MTDERelationship(ParserRuleContext ctx, MTDEntity domainEntity, String relationshipName) {
        super(ctx);
        this.domain           = domainEntity.getDomain();
        this.domainEntity     = domainEntity;
        this.relationshipName = relationshipName;
    }

    public MTDERelationship(ParserRuleContext ctx, MTDomain domain, MTRelationship relationship) {
        super(ctx);
        this.domain           = domain;
        this.relationship     = relationship;
        this.relationshipName = relationship.getName();
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns the name of the model relationship as it was declared.")
    public String getRelationshipName() {
        return relationshipName;
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns the model relationship.")
    public MTRelationship getRelationship() {
        return relationship;
    }

    public boolean isWithPrimaryKey() {
        return withPrimaryKey;
    }

    public void setWithPrimaryKey(boolean withPrimaryKey) {
        this.withPrimaryKey = withPrimaryKey;
    }

    public String getWithViewName() {
        return withViewName;
    }

    public void setWithViewName(String withViewName) {
        this.withViewName = withViewName;
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description =
            "If this relationship was explicitly renamed within its domain, it will return that name. Otherwise "
            + "it will return `null`.")
    public String getExplicitName() {
        return explicitName;
    }

    public void setExplicitName(String explicitName) {
        this.explicitName = explicitName;
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY,
        description = "Returns the parent domain entity of this domain relationship.")
    public MTDEntity getDomainEntity() {
        return domainEntity;
    }

    public void setDomainEntity(MTDEntity domainEntity) {
        this.domainEntity = domainEntity;
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Indicates if this relationship is a one-to-many.")
    public boolean isOneToMany() {
        return relationship.isOneToMany();
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Indicates if this relationship is a many-to-many.")
    public boolean isManyToMany() {
        return relationship.isManyToMany();
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

        if (relationship == null) {
            relationship = domainEntity.getEntity().getRelationshipByName(relationshipName);
            if (relationship == null) {
                anotherPass = true;
            }
        }
        if (relationship != null && to == null) {
            to = new MTDERelationshipHalf(relationship.getParserRuleContext(), domain, relationship.getTo(),
                                          withViewName);
            to.setAsPrimaryKey(withPrimaryKey);
            if (to.resolveReferences(space, pass)) {
                anotherPass = true;
            }
        }
        if (!anotherPass) {
            resolvedReferences = true;
        }
        return anotherPass;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description =
            "This returns the full name of this domain relationship which includes not only its domain based name "
            + "but is also preceded with the domain's entity's full name. The delimiter can be provided which "
            + "is used between all parts of the full name.")
    @Override
    public String getFullname(
        @ModelMethodParameter(description = "A delimiter to place between the segments of the domain namespace as well "
                                            + "as between that namespace and the domain entity name and between the "
                                            + "domain entity name and the domain relationship name.")
            String delim) {
        return domainEntity.getFullname(delim) + delim + getName();
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description =
            "Returns the domain relationship name which is the result of applying any defined naming conventions "
            + "or explicit renaming.")
    @Override
    public String getName() {
        if (explicitName != null) {
            return explicitName;
        }
        if (relationship == null) {
            relationship = domainEntity.getEntity().getRelationshipByName(relationshipName);
            if (relationship == null) {
                //ECLog.logWarning(this, "Relationship not resolved yet for: " + relationshipName);
                return relationshipName;
            }
        }
        return domain.getNameFromDomainNaming(relationship);
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Indicates whether this domain relationship has a description.")
    public boolean hasDescription() {
        String desc = getDescription();
        return desc != null && desc.length() > 0;
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns the description of this domain relationship, if it exists. Otherwise it will return `null`.")
    public String getDescription() {
        if (description != null && description.length() > 0) {
            return description;
        }
        else if (relationship.hasDescription()) {
            return relationship.getDescription();
        }
        return null;
    }

    @ModelMethod(category = ModelMethodCategory.DOMAIN,
        description = "Returns the domain associated with this domain relationship.")
    @Override
    public MTDomain getDomain() {
        return domain;
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns the \"to\" half of this domain relationship.")
    public MTDERelationshipHalf getTo() {
        return to;
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns whether this relationship was declared as a parent.")
    public boolean isParent() {
        return relationship != null && relationship.isParent();
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns whether this relationship was declared as optional.")
    public boolean isOptional() {
        return relationship != null && relationship.isOptional();
    }

    @ModelMethod(category = ModelMethodCategory.RELATIONSHIP,
        description = "Returns whether this relationship was declared as a parent and **not** declared optional.")
    public boolean isPrimaryParent() {
        return relationship != null && relationship.isParent() && !relationship.isOptional();
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
        description = "Returns whether this relationship's \"to\" entity is tagged with the specified tag.")
    public boolean hasToEntityTagged(
        @ModelMethodParameter(description = "The tag with which to check.")
            String tag) {
        return to != null && to.getEntity().hasTag(tag);
    }
}

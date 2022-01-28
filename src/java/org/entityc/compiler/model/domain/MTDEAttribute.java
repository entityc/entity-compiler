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
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.model.entity.MTAttributeConstraint;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTType;
import org.entityc.compiler.model.entity.MTUnit;
import org.entityc.compiler.model.expression.MTConstant;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.util.ECLog;
import org.entityc.compiler.util.ECStringUtil;

import java.util.ArrayList;
import java.util.List;

@ModelClass(type = ModelClassType.DOMAIN, description = "Represents an attribute in your model in the context of a domain.")
public class MTDEAttribute extends MTNode implements MTNamed, MTDomainBased, MTReferenceResolution {

    private final String                        attributeName;
    private final List<MTDEAttributeBitField>   replacedBitFields  = new ArrayList<>();
    private final List<MTDEAttributeConstraint> constraints        = new ArrayList<>();
    private       MTDomain                      domain;
    private       MTDEntity                     domainEntity;
    private       MTAttribute                   attribute;
    private       String                        entityName;
    private       String                        viewName;
    private       String                        explicitAttributeName;
    private       int                           sinceSchemaVersion = 0;

    public MTDEAttribute(ParserRuleContext ctx, MTDomain domain, String entityName, String attributeName) {
        super(ctx);
        this.domain        = domain;
        this.entityName    = entityName;
        this.attributeName = attributeName;
    }

    public MTDEAttribute(ParserRuleContext ctx, MTDomain domain, MTAttribute attribute) {
        super(ctx);
        this.domain        = domain;
        this.attribute     = attribute;
        this.attributeName = attribute.getName();
    }

    @Override
    public void addTag(String tag) {
        super.addTag(tag);
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description =
            "As new attributes are added it can be helpful to have a record of when an attribute was introduced. "
            + "The \"since schema version\" provides the schema version that this attribute was added.")
    public int getSinceSchemaVersion() {
        return sinceSchemaVersion;
    }

    public void setSinceSchemaVersion(int sinceSchemaVersion) {
        this.sinceSchemaVersion = sinceSchemaVersion;
    }

    @ModelMethod(category = ModelMethodCategory.VIEW,
        description = "Returns the name of the view that this domain attribute to which this is associated.")
    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY,
        description = "Returns the domain entity to which this belongs.")
    public MTDEntity getDomainEntity() {
        return domainEntity;
    }

    public void setDomainEntity(MTDEntity domainEntity) {
        this.domainEntity = domainEntity;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "For those domain attributes that were renamed explicitly, this returns that name. Otherwise "
                      + "`null` is returned.")
    public String getExplicitAttributeName() {
        return explicitAttributeName;
    }

    public void setExplicitAttributeName(String explicitAttributeName) {
        this.explicitAttributeName = explicitAttributeName;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns the name of the model's attribute name (not the domain one).")
    public String getAttributeName() {
        return attributeName;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns the domain-specific bit fields (replacing the model defined ones)")
    public List<MTDEAttributeBitField> getReplacedBitFields() {
        return replacedBitFields;
    }

    public void addReplacedBitField(MTDEAttributeBitField bitField) {
        this.replacedBitFields.add(bitField);
    }

    @ModelMethod(category = ModelMethodCategory.DOMAIN,
        description = "Returns the domain associated with this domain attribute.")
    public MTDomain getDomain() {
        return domain;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description =
            "This returns the full name of this domain attribute which includes not only its domain based name "
            + "but is also preceded with the domain's entity's full name. The delimiter can be provided which "
            + "is used between all parts of the full name.")
    @Override
    public String getFullname(
        @ModelMethodParameter(description = "A delimiter to place between the segments of the domain namespace as well "
                                            + "as between that namespace and the domain entity name and between the "
                                            + "domain entity name and the domain attribute name.")
            String delim) {
        return domainEntity.getFullname(delim) + delim + getName();
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description =
            "Returns the domain attribute name which is the result of applying any defined naming conventions "
            + "or explicit renaming.")
    @Override
    public String getName() {
        // if they explicitly wanted to rename the attribute of a specified entity
        if (this.explicitAttributeName != null) {
            return explicitAttributeName;
        }
        if (domain == null || domain.getSpace() == null) {
            return null;
        }

        if (attribute == null) {
            resolveReferences(domain.getSpace(), 0);
        }
        if (attribute == null) {
            // if the domain wants to rename attributes of some name
            if (domain.hasExplicitAttributeName(attributeName)) {
                return domain.getExplicitAttributeName(attributeName);
            }
            if (domain.getNamingForClass(MTAttribute.class) != null) {
                return domain.getNamingForClass(MTAttribute.class).rename(attributeName);
            }
            ECLog.logFatal("No attribute naming defined for domain \"" + domain.getName() + "\".");
        }
        if (attribute.isPrimaryKey() && domain.getPrimaryKeyName() != null) {
            return domain.getPrimaryKeyName();
        }
        String nameWithUnit = attributeName;
        if (attribute.hasUnit()
            && (domain.getNamingForClass(MTAttribute.class) == null
                || domain.getNamingForClass(MTAttribute.class).isUseUnitInNaming())) {
            nameWithUnit = attributeName + "In" + ECStringUtil.Capitalize(attribute.getUnit().getName());
        }

        // if the domain wants to rename attributes of some name
        if (domain.hasExplicitAttributeName(attributeName)) {
            return domain.getExplicitAttributeName(attributeName);
        }

        return domain.getNameFromDomainNaming(attribute, nameWithUnit);
    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        boolean anotherPass = false;

        if (domain != null && domain.isSpecialized()) {
            domain = domain.getBaseDomain();
        }
        if (attribute == null) {
            MTEntity entity = space.getEntityWithName(entityName);
            if (entity == null) {
                anotherPass = pass < 7;
            }
            else {
                MTAttribute attribute = null;
                if (viewName != null && domainEntity != null) {
                    MTDView       domainView      = domainEntity.getDomainEntityViewByName(viewName, true);
                    MTDEAttribute domainAttribute = domainView.getDomainAttributeByName(attributeName, true);
                    if (domainAttribute != null) {
                        attribute = domainAttribute.getAttribute();
                    }
                }
                else {
                    attribute = entity.getAttributeByName(attributeName);
                    if (attribute == null) {
                        attribute = domainEntity.getAddedAttributeByName(attributeName);
                    }
                }
                if (attribute == null) {
                    anotherPass = pass < 7;
                }
                else {
                    this.attribute = attribute;
                }
            }
        }
        else {
            for (MTDEAttributeBitField bitField : replacedBitFields) {
                if (bitField.resolveReferences(space, pass)) {
                    anotherPass = true;
                }
            }
        }
        return anotherPass;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns the model (non-domain specific) attribute of this domain attribute.")
    public MTAttribute getAttribute() {
        return attribute;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "If this domain attribute is of enum type, then the domain enum is returned as such.")
    public MTDEnum getTypeEnum() {
        if (getType().isEnumType()) {
            return domain.getDomainEnum((MTEnum) getType(), true);
        }
        return null;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns the type of this attribute.")
    public MTType getType() {
        if (attribute == null) {
            ECLog.logFatal(this, "Could not resolve attribute name: " + entityName + "." + attributeName + " in domain "
                                 + domain.getName());
        }
        return attribute.getType();
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "If the type of this attribute is of an entity type, then that domain entity is returned. "
                      + "Otherwise `null` is returned.")
    public MTDEntity getTypeEntity() {
        if (getType().isEntityType()) {
            return domain.getDomainEntity((MTEntity) getType(), true);
        }
        return null;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns the default value if one was assigned on the model attribute.")
    public MTConstant getDefaultValue() {
        return attribute.getDefaultValue();
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Indicates whether this attribute was declared as virtual. A virtual attribute is one in which "
                      + "you do not persist and is driven by code synthesized from a template.")
    public boolean isVirtual() {
        return attribute.isVirtual();
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns the declared domain attribute description. If that is not available then it will return "
                      + "the model attribute description.")
    @Override
    public String getDescription() {
        if (description != null && description.length() > 0) {
            return description;
        }
        if (attribute != null) {
            return attribute.getDescription();
        }
        return null;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Indicates whether this attribute was declared with a unit of measure.")
    public boolean hasUnit() {
        return getUnit() != null;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "If this attribute was declared with a unit of measure it returns that unit. Otherwise it will "
                      + "return `null`.")
    public MTUnit getUnit() {
        if (attribute != null) {
            return attribute.getUnit();
        }
        return null;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description =
            "Returns the list of constraints that have been declared on this domain entity. The constraints are "
            + "domain constraints.")
    public List<MTDEAttributeConstraint> getConstraints() {
        if (attribute.getConstraints().size() > 0 && constraints.size() == 0) {
            for (MTAttributeConstraint constraint : attribute.getConstraints()) {
                constraints.add(new MTDEAttributeConstraint(this.getParserRuleContext(), this, constraint));
            }
        }
        return constraints;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns the a domain constraint by name.")
    public MTDEAttributeConstraint getConstraintByName(
        @ModelMethodParameter(description = "The name of the constraint as declared in the model.")
            String name) {
        for (MTDEAttributeConstraint constraint : constraints) {
            if (constraint.getName().equals(name)) {
                return constraint;
            }
        }
        return null;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Indicates whether this domain attribute has defined bit fields to that replace the model bit fields.")
    public boolean hasReplacedBitFields() {
        return replacedBitFields.size() > 0;
    }

    @ModelMethod(category = ModelMethodCategory.CONFIGURATION,
        description = "Returns the space in which this attribute was declared.")
    public MTSpace getSpace() {
        if (attribute != null && attribute.getEntity() != null) {
            return attribute.getEntity().getSpace();
        }
        return null;
    }
}

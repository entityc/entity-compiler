/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.filter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.MTModule;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.domain.MTDConstant;
import org.entityc.compiler.model.domain.MTDEAttribute;
import org.entityc.compiler.model.domain.MTDEAttributeConstraint;
import org.entityc.compiler.model.domain.MTDERelationship;
import org.entityc.compiler.model.domain.MTDEntity;
import org.entityc.compiler.model.domain.MTDEnum;
import org.entityc.compiler.model.domain.MTDEnumItem;
import org.entityc.compiler.model.domain.MTDomain;
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.model.entity.MTAttributeConstraint;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTEnumItem;
import org.entityc.compiler.model.entity.MTRelationship;
import org.entityc.compiler.model.entity.MTTypedef;
import org.entityc.compiler.model.expression.MTConstant;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.transform.template.tree.expression.FTOperand;
import org.entityc.compiler.transform.template.tree.expression.FTOperation;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class FTDomainFilter extends FTFilter {

    public FTDomainFilter() {
        super(null, "domain",
              "Allows you to convert a model element (such as an entity or attribute) into a domain-specific "
              + "version of that element. The domain-specific element can have altered properties such as a "
              + "different name or different tags. For convenience this filter can also accept domain specific elements "
              + "in which case it effectively replaces the domain with the specified one.");
        this.addFilterParam(new FTFilterParam("domainName",
                                              "Specifies a specific domain name or domain object to filter "
                                              + "through. If not specified, the currently defined domain is used."));
        this.addCollectionInputType(MTAttribute.class);
        this.addCollectionInputType(MTAttributeConstraint.class);
        this.addSingleInputType(MTSpace.class);
        this.addSingleInputType(MTModule.class);
        this.addSingleInputType(MTEntity.class);
        this.addSingleInputType(MTDEntity.class);
        this.addSingleInputType(MTDEnum.class);
        this.addSingleInputType(MTDEnumItem.class);
        this.addSingleInputType(MTAttribute.class);
        this.addSingleInputType(MTDEAttribute.class);
        this.addSingleInputType(MTRelationship.class);
        this.addSingleInputType(MTDERelationship.class);
        this.addSingleInputType(MTEnum.class);
        this.addSingleInputType(MTEnumItem.class);
        this.addSingleInputType(MTTypedef.class);
        this.addSingleInputType(MTConstant.class);
    }

    @Override
    public Object filter(ParserRuleContext ctx, FTTransformSession session, Object input, List<FTExpression> paramValues, Map<String, Object> options) {
        checkInput(ctx, input, paramValues, options);

        //ECLog.logInfo("Starting Domain Filter...");

        // get the specified or default domain
        MTDomain domain = (MTDomain) session.getValue("domain");
        if (paramValues.size() > 0) {
            FTExpression domainParamExpression = paramValues.get(0);
            if (domainParamExpression.isOperand()) {
                String directDomainName = ((FTOperand) domainParamExpression).getName();
                if (Character.isUpperCase(directDomainName.charAt(0))) {
                    if (session.getSpace() == null) {
                        ECLog.logFatal(ctx, "Unable fo find domain \"" + directDomainName
                                            + "\" because no space is defined.");
                    }
                    domain = session.getSpace().getDomainWithName(directDomainName);
                    if (domain == null) {
                        ECLog.logFatal(ctx, "No such domain named \"" + directDomainName + "\" in space \""
                                            + session.getSpace().getName() + "\"");
                    }
                }
                else {
                    Object value = session.getValue(directDomainName);
                    if (value instanceof MTDomain) {
                        domain = (MTDomain) value;
                    }
                    if (domain == null) {
                        ECLog.logFatal(ctx, "The variable \"" + directDomainName + "\" is not a domain object.");
                    }
                }
            }
            else {
                if (domainParamExpression.isOperation()) {
                    FTOperation operation = (FTOperation) domainParamExpression;
                    if (operation.getOperandCount() == 2) {
                        Object value = domainParamExpression.getValue(session);
                        if (value == null) {
                            ECLog.logFatal(ctx, "Domain filter does not support a null parameter.");
                        }
                        else if (value instanceof MTDomain) {
                            domain = (MTDomain) value;
                        }
                        else {
                            ECLog.logFatal(ctx, "Unsupported parameter expression for domain filter: "
                                                + domainParamExpression.getText()
                                                + " does not evaluate to a domain, instead of class: "
                                                + value.getClass().getSimpleName());
                        }
                    }
                }
                if (domain == null) {
                    ECLog.logFatal(ctx, "Unsupported parameter expression for domain filter: "
                                        + domainParamExpression.getText());
                }
            }
        }
        else {
            if (domain == null) {
                ECLog.logFatal(ctx, "No current domain!");
            }
        }

        if (input instanceof Collection) {
            List<MTDEAttribute> domainAttributes = new ArrayList<>();
            for (MTAttribute attribute : (Collection<MTAttribute>) input) {
                MTEntity  entity       = attribute.getEntity();
                MTDEntity domainEntity = domain.getDomainEntity(entity, true);
                domainAttributes.add(domainEntity.getDomainAttributeByName(attribute.getName(), true));
            }
            return domainAttributes;
        }

        // strip off the domain part if passing in a domain specific thing
        if (input instanceof MTDEntity) {
            input = ((MTDEntity) input).getEntity();
        }
        else if (input instanceof MTDEAttribute) {
            input = ((MTDEAttribute) input).getAttribute();
        }
        else if (input instanceof MTDERelationship) {
            input = ((MTDERelationship) input).getRelationship();
        }
        else if (input instanceof MTDEnum) {
            input = ((MTDEnum) input).getEnum();
        }
        else if (input instanceof MTDEnumItem) {
            input = ((MTDEnumItem) input).getItem();
        }

        if (input instanceof MTSpace) {
            return domain.getDomainSpace((MTSpace) input, true);
        }
        else if (input instanceof MTModule) {
            return domain.getDomainModule((MTModule) input, true);
        }
        else if (input instanceof MTEntity) {
            return domain.getDomainEntity((MTEntity) input, true);
        }
        else if (input instanceof MTAttribute) {
            MTAttribute attribute    = (MTAttribute) input;
            MTEntity    entity       = attribute.getEntity();
            MTDEntity   domainEntity = domain.getDomainEntity(entity, true);
            return domainEntity.getDomainAttributeByName(attribute.getName(), true);
        }
        else if (input instanceof MTAttributeConstraint) {
            MTAttributeConstraint attributeConstraint = (MTAttributeConstraint) input;
            MTAttribute           attribute           = attributeConstraint.getAttribute();
            MTEntity              entity              = attribute.getEntity();
            MTDEntity             domainEntity        = domain.getDomainEntity(entity, true);
            MTDEAttribute domainAttribute = domainEntity.getDomainAttributeByName(attribute.getName(),
                                                                                  true);
            return new MTDEAttributeConstraint(ctx, domainAttribute, attributeConstraint);
        }
        else if (input instanceof MTRelationship) {
            MTRelationship relationship = (MTRelationship) input;
            MTEntity       entity       = relationship.getFrom().getEntity();
            MTDEntity      domainEntity = domain.getDomainEntity(entity, true);
            return domainEntity.getDomainEntityRelationshipByName(relationship.getName(), true);
        }
        else if (input instanceof MTEnum) {
            return domain.getDomainEnum((MTEnum) input, true);
        }
        else if (input instanceof MTEnumItem) {
            MTDEnum domainEnum = domain.getDomainEnum(((MTEnumItem) input).getEnum(), true);
            if (domainEnum != null) {
                return domainEnum.getDomainEnumItem((MTEnumItem) input, true);
            }
        }
        else if (input instanceof MTTypedef) {
            return domain.getDomainTypedef((MTTypedef) input, true);
        }
        else if (input instanceof MTConstant) {
            return new MTDConstant(ctx, domain, (MTConstant) input);
        }

        return null;
    }
}

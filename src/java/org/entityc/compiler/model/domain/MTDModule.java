/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.domain;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.MTModule;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.MTReferenceResolution;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.config.MTTemplate;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.visitor.MTVisitor;

import java.util.ArrayList;
import java.util.List;

public class MTDModule extends MTNode implements MTReferenceResolution, MTDomainBased, MTNamed {

    private MTDomain         domain;
    private MTModule         module;
    private String           moduleName;
    private MTDApplyTemplate applyTemplate;

    public MTDModule(ParserRuleContext ctx, MTDomain domain, MTModule module) {
        super(ctx);
        this.domain = domain;
        this.module = module;
    }

    public MTDModule(ParserRuleContext ctx, MTDomain domain, String moduleName) {
        super(ctx);
        this.domain     = domain;
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public MTModule getModule() {
        return module;
    }

    public MTDApplyTemplate getApplyTemplate() {
        return applyTemplate;
    }

    public void setApplyTemplate(MTDApplyTemplate applyTemplate) {
        this.applyTemplate = applyTemplate;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        if (domain != null && domain.isSpecialized()) {
            domain = domain.getBaseDomain();
        }
        if (module == null) {
            module = space.getModuleWithName(moduleName);
        }
        return module == null;
    }

    @Override
    public MTDomain getDomain() {
        return domain;
    }

    @Override
    public String getFullname(String delim) {
        return null;
    }

    @Override
    public String getName() {
        return domain.getNameFromDomainNaming(module);
    }

    public List<MTEntity> getEntitiesForTemplate(MTTemplate template) {
        ArrayList<MTEntity> entities = new ArrayList<>();
        if (module != null) {
            if (applyTemplate == null || applyTemplate.getTemplateName().equals(template.getName())) {
                for (MTEntity moduleEntity : module.getEntities()) {
                    MTDEntity domainEntity = domain.getDomainEntity(moduleEntity, true);
                    if (domainEntity.getApplyTemplate() == null
                        || domainEntity.getApplyTemplate().getTemplateName().equals(template.getName())) {
                        entities.add(moduleEntity);
                    }
                }
            }
            else if (applyTemplate != null && !applyTemplate.getTemplateName().equals(template.getName())) {
                // only add those entities that match
                for (MTEntity moduleEntity : module.getEntities()) {
                    MTDEntity domainEntity = domain.getDomainEntity(moduleEntity, true);
                    if (domainEntity.getApplyTemplate() != null
                        && domainEntity.getApplyTemplate().getTemplateName().equals(template.getName())) {
                        entities.add(moduleEntity);
                    }
                }
            }
        }
        return entities;
    }
}

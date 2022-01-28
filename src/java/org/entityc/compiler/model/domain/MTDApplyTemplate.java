/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.domain;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.visitor.MTVisitor;

import javax.json.JsonObject;

public class MTDApplyTemplate extends MTNode implements MTDomainBased {

    private final String     templateName;
    private       boolean    defaultApplyTemplate;
    private       String     description;
    private       JsonObject config;
    private       MTDomain   domain;
    private       MTDModule  domainModule;
    private       MTDEntity  domainEntity;

    public MTDApplyTemplate(ParserRuleContext ctx, MTDEntity domainEntity, String templateName) {
        super(ctx);
        this.domainEntity = domainEntity;
        this.templateName = templateName;
    }

    public MTDApplyTemplate(ParserRuleContext ctx, MTDModule domainModule, String templateName) {
        super(ctx);
        this.domainModule = domainModule;
        this.templateName = templateName;
    }

    public MTDApplyTemplate(ParserRuleContext ctx, MTDomain domain, String templateName) {
        super(ctx);
        this.domain       = domain;
        this.templateName = templateName;
    }

    public MTDModule getDomainModule() {
        return domainModule;
    }

    @Override
    public MTDomain getDomain() {
        return domain;
    }

    public MTDEntity getDomainEntity() {
        return domainEntity;
    }

    public boolean isDefaultApplyTemplate() {
        return defaultApplyTemplate;
    }

    public void setDefaultApplyTemplate(boolean defaultApplyTemplate) {
        this.defaultApplyTemplate = defaultApplyTemplate;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public JsonObject getConfig() {
        return config;
    }

    public void setConfig(JsonObject config) {
        this.config = config;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @Override
    public String getFullname(String delim) {
        return domain.getNamespace().getFullname(delim) + delim + getTemplateName();
    }

    public String getTemplateName() {
        return templateName;
    }
}

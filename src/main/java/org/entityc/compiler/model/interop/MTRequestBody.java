/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.interop;

import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.MTReferenceResolution;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.domain.MTDView;
import org.entityc.compiler.model.domain.MTDomain;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTTemplateSupport;
import org.entityc.compiler.model.entity.MTView;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class MTRequestBody extends MTNode implements MTReferenceResolution, MTTemplateSupport {

    private String   contentType;
    private String   viewName;
    private String   domainName;
    private String   entityName;
    private MTEntity entity;
    private MTDomain domain;
    private MTView   view;
    private MTDView  domainView;

    public MTRequestBody(ParserRuleContext ctx) {
        super(ctx);
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public MTDomain getDomain() {
        return domain;
    }

    public MTView getView() {
        return view;
    }

    public MTEntity getEntity() {
        return entity;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        //ECLog.logInfo("Resolving BODY references in REQUEST");
        boolean anotherPass = false;
        if (domain == null) {
            //ECLog.logInfo("Resolving DOMAIN references in REQUEST : " + domainName);
            domain = space.getDomainWithName(domainName);
            if (domain == null) {
                anotherPass = true;
            }
        }
        if (entity == null) {
            //ECLog.logInfo("Resolving ENTITY references in REQUEST : " + entityName);
            entity = space.getEntityWithName(entityName);
            if (entity == null) {
                anotherPass = true;
            }
        }

        if (entity != null) {
            if (domainView == null) {
                // TODO make this work
                //ECLog.logInfo("Resolving VIEW references in REQUEST : " + viewName);
            }
        }
        return anotherPass;
    }
}

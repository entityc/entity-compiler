/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.interop;

import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.MTReferenceResolution;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.entity.MTTemplateSupport;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.util.ECLog;
import org.antlr.v4.runtime.ParserRuleContext;

public class MTInterfaceOperation extends MTNode implements MTTemplateSupport, MTReferenceResolution {

    protected MTInterface       mtInterface;
    protected String            name;
    protected MTRequest         request;
    protected MTResponse        response;
    private   MTOperationConfig config;
    private   boolean           abstractOp;

    public MTInterfaceOperation(ParserRuleContext ctx, MTInterface mtInterface, String name) {
        super(ctx);
        this.mtInterface = mtInterface;
        if (mtInterface == null) {
            ECLog.logFatal("WHAT!! NO INTERFACE!!");
        }
        this.name = name;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    public String getName() {
        return name;
    }

    public MTRequest getRequest() {
        return request;
    }

    public void setRequest(MTRequest request) {
        this.request = request;
    }

    public MTResponse getResponse() {
        return response;
    }

    public void setResponse(MTResponse response) {
        this.response = response;
    }

    public MTOperationConfig getConfig() {
        return config;
    }

    public void setConfig(MTOperationConfig config) {
        this.config = config;
    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        return false;
    }

    public boolean isAbstract() {
        return abstractOp;
    }

    public void setAbstract(boolean abstractOp) {
        this.abstractOp = abstractOp;
    }
}

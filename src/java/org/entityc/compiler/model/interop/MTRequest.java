/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.interop;

import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.MTReferenceResolution;
import org.entityc.compiler.model.MTTransformableString;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.domain.MTDEInterfaceOperation;
import org.entityc.compiler.model.entity.MTTemplateSupport;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class MTRequest extends MTNode implements MTReferenceResolution, MTTemplateSupport {

    private MTDEInterfaceOperation domainEntityInterfaceOperation;
    private MTRequestBody          body;
    private MTRequestEndpoint      endpoint;
    private String                 method;
    private MTTransformableString  transformableDescription;

    public MTRequest(ParserRuleContext ctx) {
        super(ctx);
    }

    public MTRequest(ParserRuleContext ctx, MTDEInterfaceOperation domainEntityInterfaceOperation) {
        super(ctx);
        this.domainEntityInterfaceOperation = domainEntityInterfaceOperation;
    }

    public MTDEInterfaceOperation getDomainEntityInterfaceOperation() {
        return domainEntityInterfaceOperation;
    }

    public MTRequestEndpoint getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(MTRequestEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String getDescription() {
        return transformableDescription != null ? transformableDescription.toString() : null;
    }

    @Override
    public void setDescription(String description) {
        transformableDescription = new MTTransformableString(description);
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public MTRequestBody getBody() {
        return body;
    }

    public void setBody(MTRequestBody body) {
        this.body = body;
    }

    public boolean hasBody() {
        return body != null;
    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        boolean needsAnotherPass = false;
        if (endpoint != null) {
            if (endpoint.resolveReferences(space, pass)) {
                needsAnotherPass = true;
            }
        }
        if (body != null) {
            if (body.resolveReferences(space, pass)) {
                needsAnotherPass = true;
            }
        }

        if (domainEntityInterfaceOperation != null && transformableDescription != null) {
            transformableDescription.transform(domainEntityInterfaceOperation);
        }

        return needsAnotherPass;
    }
}

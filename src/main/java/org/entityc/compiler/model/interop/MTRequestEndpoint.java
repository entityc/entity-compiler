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
import org.entityc.compiler.model.visitor.MTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MTRequestEndpoint extends MTNode implements MTReferenceResolution {

    private final MTRequest                           request;
    private final Map<String, MTRequestEndpointParam> paramMap = new HashMap<>();
    private       boolean                             transformStrings;
    private       MTTransformableString               transformablePath;
    private       MTTransformableString               transformableDescription;

    public MTRequestEndpoint(ParserRuleContext ctx, MTRequest request, String path) {
        super(ctx);
        this.request = request;
        this.setPath(path);
    }

    public boolean isTransformStrings() {
        return transformStrings;
    }

    public void setTransformStrings(boolean transformStrings) {
        this.transformStrings = transformStrings;
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

    public void addParam(MTRequestEndpointParam param) {
        paramMap.put(param.getName(), param);
    }

    public MTRequestEndpointParam getParam(String name) {
        return paramMap.get(name);
    }

    public boolean hasParam(String name) {
        return paramMap.containsKey(name);
    }

    public Collection<MTRequestEndpointParam> getParams() {
        return paramMap.values();
    }

    public MTRequestEndpoint clone() {
        MTRequestEndpoint clonePath = new MTRequestEndpoint(null, request, getPath());
        clonePath.paramMap.putAll(paramMap);
        return clonePath;
    }

    public String getPath() {
        return transformablePath != null ? transformablePath.toString() : null;
    }

    public void setPath(String path) {
        transformablePath = new MTTransformableString(path);
    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        if (!transformStrings) {
            return false;
        }
        MTDEInterfaceOperation deio = getRequest().getDomainEntityInterfaceOperation();

        if (deio != null) {
            if (transformablePath != null) {
                transformablePath.transform(deio);
            }
            if (transformableDescription != null) {
                transformableDescription.transform(deio);
            }
        }
        return false;
    }

    public MTRequest getRequest() {
        return request;
    }

    public boolean isDomainEntitySpecific() {
        return request != null && request.getDomainEntityInterfaceOperation() != null;
    }
}

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
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MTResponse extends MTNode implements MTReferenceResolution, MTTemplateSupport {

    private final List<MTResponseStatus>        statusList      = new ArrayList<>();
    private final Map<String, MTResponseStatus> statusMapByName = new HashMap<>();
    private       MTResponseBody                body;

    public MTResponse(ParserRuleContext ctx) {
        super(ctx);
    }

    public void addStatus(MTResponseStatus status) {
        this.statusList.add(status);
        if (status.getName() != null) {
            this.statusMapByName.put(status.getName().toLowerCase(), status);
        }
    }

    public List<MTResponseStatus> getStatusList() {
        return this.statusList;
    }

    public List<MTResponseStatus> getStatusListSortedByCode() {
        List<MTResponseStatus> sortedStatusList = new ArrayList<>(statusList);
        sortedStatusList.sort(new Comparator<MTResponseStatus>() {
            @Override
            public int compare(MTResponseStatus o1, MTResponseStatus o2) {
                return o1.getCode().compareTo(o2.getCode());
            }
        });
        return sortedStatusList;
    }

    public MTResponseStatus getStatusByName(String name) {
        return this.statusMapByName.get(name.toLowerCase());
    }

    public boolean hasStatusByName(String name) {
        return this.statusMapByName.get(name.toLowerCase()) != null;
    }

    public boolean hasBody() {
        return body != null;
    }

    public MTResponseBody getBody() {
        return body;
    }

    public void setBody(MTResponseBody body) {
        this.body = body;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        boolean needsAnotherPass = false;
        for (MTResponseStatus responseStatus : statusList) {
            if (responseStatus.resolveReferences(space, pass)) {
                needsAnotherPass = true;
            }
        }
        if (body != null) {
            if (body.resolveReferences(space, pass)) {
                needsAnotherPass = true;
            }
        }
        return needsAnotherPass;
    }
}

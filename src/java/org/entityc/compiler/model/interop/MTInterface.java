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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MTInterface extends MTNode implements MTTemplateSupport, MTReferenceResolution {

    private final String                            name;
    private final Map<String, MTInterfaceOperation> operationMap = new HashMap<>();
    private       String                            type;

    public MTInterface(ParserRuleContext ctx, String name) {
        super(ctx);
        this.name = name;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addOperation(MTInterfaceOperation operation) {
        this.operationMap.put(operation.getName(), operation);
    }

    public MTInterfaceOperation getOperation(String name) {
        return operationMap.get(name);
    }

    public List<MTInterfaceOperation> getOperations() {
        return new ArrayList<>(operationMap.values());
    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        for (MTInterfaceOperation operation : operationMap.values()) {
            operation.resolveReferences(space, pass);
        }
        return false;
    }
}

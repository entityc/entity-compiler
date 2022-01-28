/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.domain;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.MTReferenceResolution;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.interop.MTInterface;
import org.entityc.compiler.model.visitor.MTVisitor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MTDEInterface extends MTNode implements MTReferenceResolution {

    private final MTDEntity                           domainEntity;
    private final String                              interfaceName;
    private final Map<String, MTDEInterfaceOperation> operationMap = new HashMap<>();
    private       MTDomain                            domain;
    private       MTInterface                         abstractInterface;
    private       String                              description;

    public MTDEInterface(ParserRuleContext ctx, MTDomain domain, MTDEntity domainEntity, String interfaceName) {
        super(ctx);
        this.domain        = domain;
        this.domainEntity  = domainEntity;
        this.interfaceName = interfaceName;
    }

    public String getName() {
        return interfaceName;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public MTDomain getDomain() {
        return domain;
    }

    public MTDEntity getDomainEntity() {
        return domainEntity;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public MTInterface getAbstractInterface() {
        return abstractInterface;
    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        boolean anotherPass = false;
        if (domain != null && domain.isSpecialized()) {
            domain = domain.getBaseDomain();
        }
        if (abstractInterface == null) {
            abstractInterface = space.getInterface(interfaceName);
            anotherPass       = abstractInterface == null;
        }
        for (MTDEInterfaceOperation operation : operationMap.values()) {
            if (operation.resolveReferences(space, pass)) {
                anotherPass = true;
            }
        }
        return anotherPass;
    }

    public void addOperation(MTDEInterfaceOperation operation) {
        //ECLog.logInfo("Adding operation: " + operation.getName());
        operationMap.put(operation.getName(), operation);
    }

    public Collection<MTDEInterfaceOperation> getOperations() {
        return operationMap.values();
    }

    public MTDEInterfaceOperation getOperationByName(String operationName) {
        return operationMap.get(operationName);
    }

    @Override
    public void accept(MTVisitor visitor) {

    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.domain;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.EntityCompiler;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.expression.MTExpression;
import org.entityc.compiler.model.interop.MTInterface;
import org.entityc.compiler.model.interop.MTInterfaceOperation;
import org.entityc.compiler.model.interop.MTOperationConfigArgument;
import org.entityc.compiler.model.language.MTLanguage;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.util.ECLog;

import java.util.HashMap;
import java.util.Map;

public class MTDEInterfaceOperationConfig extends MTExpression {

    private final String                 name; // optional, used so expressions can reference the config arguments
    private final String                 interfaceName;
    private final Map<String, String>    assignments = new HashMap<>();
    private       String                 contextDomainName;
    private       String                 contextEntityName;
    private       String                 abstractOperationName;
    private       MTDEInterfaceOperation extendedOperation;
    private       MTInterfaceOperation   abstractOperation;

    public MTDEInterfaceOperationConfig(ParserRuleContext ctx, String name, String interfaceName, MTDEInterfaceOperation extendedOperation) {
        super(ctx);
        this.name              = name;
        this.interfaceName     = interfaceName;
        this.extendedOperation = extendedOperation;
    }

    public MTDEInterfaceOperationConfig(ParserRuleContext ctx, String name, String interfaceName, String abstractOperationName) {
        super(ctx);
        this.name                  = name;
        this.interfaceName         = interfaceName;
        this.abstractOperationName = abstractOperationName;
    }

    public String getContextDomainName() {
        return contextDomainName;
    }

    public void setContextDomainName(String contextDomainName) {
        this.contextDomainName = contextDomainName;
    }

    public MTInterfaceOperation getAbstractOperation() {
        if (abstractOperation == null) {
            if (extendedOperation != null) {
                abstractOperation = extendedOperation.getAbstractOperation();
                if (abstractOperation == null) {
                    ECLog.logFatal("NO ABSTRACT OPERATION NAMED: " + abstractOperationName);
                }
            }
        }
        return abstractOperation;
    }

    public Map<String, String> getAssignments() {
        return assignments;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    public void addConfigAssignment(String configName, String configValue) {
        assignments.put(configName, configValue);
    }

    public String valueForConfigName(String configName) {
        return assignments.get(configName);
    }

    public int getAssignmentCount() {
        return assignments.size();
    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        MTInterface mtInterface = space.getInterface(interfaceName);
        if (mtInterface == null) {
            ECLog.logFatal("Unable to find interfaced named: " + interfaceName);
        }

        if (extendedOperation != null) {
            this.copyFrom(extendedOperation.getExtendingOperationConfig());
        }
        else {
            abstractOperation = space.getOperationByName(mtInterface, abstractOperationName);
            if (abstractOperation != null) {
                // copy DEFAULT VALUES
                for (MTOperationConfigArgument argument : abstractOperation.getConfig().getArgumentList()) {
                    if (argument.isContext()) {
                        if (argument.getType() == MTOperationConfigArgument.ArgumentType.Domain) {
                            assignments.put(argument.getName(), contextDomainName);
                        }
                        else if (argument.getType() == MTOperationConfigArgument.ArgumentType.Entity) {
                            assignments.put(argument.getName(), contextEntityName);
                        }
                    }
                    else {
                        if (argument.getDefaultValue() != null) {
                            if (assignments.get(argument.getName()) == null) {
                                assignments.put(argument.getName(), argument.getDefaultValue());
                            }
                        }
                    }
                }
                validate();
            }
            else {
                if (EntityCompiler.isVerbose()) {
                    ECLog.logInfo("STILL CANT RESOLVE interfaceOperation on pass: " + pass + " for operation: "
                                  + abstractOperationName);
                }
            }
        }
        return abstractOperation == null && pass < 6;
    }

    public void copyFrom(MTDEInterfaceOperationConfig fromConfig) {
        for (String key : fromConfig.assignments.keySet()) {
            if (!fromConfig.abstractOperation.getConfig().getArgumentByName(key).isContext()) {
                assignments.put(key, fromConfig.assignments.get(key));
            }
        }
        this.abstractOperation = fromConfig.abstractOperation;
    }

    private void validate() {
        if (abstractOperation == null) {
            return;
        }
        if (contextDomainName == null) {
            ECLog.logFatal("No domain context set for operation: " + abstractOperationName);
        }
        if (contextEntityName == null) {
            ECLog.logFatal("No entity context set for operation: " + abstractOperationName);
        }
        for (String argumentName : assignments.keySet()) {
            if (!abstractOperation.getConfig().isArgument(argumentName)) {
                ECLog.logFatal(this, "Argument specified is not valid for this operation: " + argumentName);
            }
        }
        boolean foundMissing = false;
        for (MTOperationConfigArgument argument : abstractOperation.getConfig().getArgumentList()) {
            if (argument.isContext()) {
                continue; // ignore these here
            }
            if (assignments.containsKey(argument.getName())) {
                continue;
            }
            else {
                foundMissing = true;
                ECLog.logError(this, "Value not specified for operation argument: " + argument.getName());
            }
        }
        if (foundMissing) {
            System.exit(1);
        }
    }

    @Override
    public String mapToLanguage(MTLanguage language, MTDEntity domainEntity, String objectName) {
        return "<<NOT_SUPPORTED>>";
    }

    public Object getArg(FTTransformSession session, String name) {
        String                    valueName = assignments.get(name);
        MTOperationConfigArgument arg       = abstractOperation.getConfig().getArgumentByName(name);
        switch (arg.getType()) {
            case Domain:
                return session.getSpace().getDomainWithName(valueName);
//            case View:
//                return session.getModel().getEntityWithName(getContextEntityName()).getViewByName(valueName);
            case Entity:
                return session.getSpace().getEntityWithName(valueName);
            case Attribute:
                return session.getSpace().getEntityWithName(getContextEntityName()).getAttributeByName(valueName);
        }
        return null;
    }

    public String getContextEntityName() {
        return contextEntityName;
    }

    public void setContextEntityName(String contextEntityName) {
        this.contextEntityName = contextEntityName;
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.domain;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.MTReferenceResolution;
import org.entityc.compiler.model.MTRoot;
import org.entityc.compiler.model.MTStringTransformer;
import org.entityc.compiler.model.MTTransformableString;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.entity.MTTemplateSupport;
import org.entityc.compiler.model.interop.MTInterface;
import org.entityc.compiler.model.interop.MTInterfaceOperation;
import org.entityc.compiler.model.interop.MTOperationConfig;
import org.entityc.compiler.model.interop.MTOperationConfigArgument;
import org.entityc.compiler.model.interop.MTRequest;
import org.entityc.compiler.model.interop.MTRequestBody;
import org.entityc.compiler.model.interop.MTRequestEndpoint;
import org.entityc.compiler.model.interop.MTRequestEndpointParam;
import org.entityc.compiler.model.interop.MTResponse;
import org.entityc.compiler.model.interop.MTResponseBody;
import org.entityc.compiler.model.interop.MTResponseStatus;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.transform.template.StringTemplateTransform;
import org.entityc.compiler.util.ECLog;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.exit;

public class MTDEInterfaceOperation extends MTNode implements MTTemplateSupport, MTReferenceResolution, MTStringTransformer {

    // declared context
    private final String                       name;
    private final String                       extendedOperationName;
    private final MTDEInterface                domainEntityInterface;
    protected     MTRequest                    request;
    protected     MTResponse                   response;
    private       MTDEInterfaceOperation       resolvedOperation;
    private       MTInterfaceOperation         abstractOperation;
    private       MTDEInterfaceOperation       extendedOperation;
    private       MTDEInterfaceOperationConfig extendingOperationConfig;
    private       MTTransformableString        transformableDescription;

    public MTDEInterfaceOperation(ParserRuleContext ctx, MTDEInterface domainEntityInterface, String name, String extendedOperationName) {
        super(ctx);
        this.domainEntityInterface = domainEntityInterface;
        this.name                  = name;
        this.extendedOperationName = extendedOperationName;
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

    public String getExtendedOperationName() {
        return extendedOperationName;
    }

    public String getAbstractOperationName() {
        return getAbstractOperation().getName();
    }

    public MTInterfaceOperation getAbstractOperation() {
        if (abstractOperation != null) {
            return abstractOperation;
        }
        if (extendedOperation != null) {
            //ECLog.logInfo("Unable to find abstract operation, looking into extended operation: " + extendedOperation.getName() + " (from " + name + ")");
            return extendedOperation.getAbstractOperation();
        }
        return null;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return transformableDescription != null ?
               transformableDescription.toString() :
               null;
    }

    @Override
    public void setDescription(String description) {
        transformableDescription = new MTTransformableString(description);
    }

    private boolean isExtendedAbstract() {
        return abstractOperation != null;
    }

    public MTDEInterfaceOperation getResolvedOperation() {
        if (this.resolvedOperation != null) {
            return this.resolvedOperation;
        }

        if (abstractOperation == null && extendedOperation == null) {
            ECLog.logFatal("NO EXTENDED OPERATION named: " + extendedOperationName);
            return null;
        }

        MTDEInterfaceOperation resolvedExtendedOperation = isExtendedAbstract() ?
                                                           null :
                                                           extendedOperation.getResolvedOperation();

        MTDEInterfaceOperation resolvedOperation = new MTDEInterfaceOperation(getParserRuleContext(),
                                                                              domainEntityInterface, name,
                                                                              extendedOperationName);
        resolvedOperation.setDescriptionFrom(this, isExtendedAbstract() ?
                                                   abstractOperation :
                                                   resolvedExtendedOperation);

        resolvedOperation.abstractOperation = abstractOperation;
        resolvedOperation.extendedOperation = extendedOperation;
        MTRequest extendedRequest = isExtendedAbstract() ?
                                    abstractOperation.getRequest() :
                                    resolvedExtendedOperation.getRequest();
        MTResponse extendedResponse = isExtendedAbstract() ?
                                      abstractOperation.getResponse() :
                                      resolvedExtendedOperation.getResponse();

        if (extendingOperationConfig == null) {
            if (isExtendedAbstract()) {
                extendingOperationConfig = new MTDEInterfaceOperationConfig(getParserRuleContext(), "c",
                                                                            getInterface().getName(),
                                                                            getAbstractOperationName());
            }
            else {
                extendingOperationConfig = new MTDEInterfaceOperationConfig(getParserRuleContext(), "c",
                                                                            getInterface().getName(),
                                                                            resolvedExtendedOperation);
            }
        }
        extendingOperationConfig.setContextDomainName(domainEntityInterface.getDomain().getName());
        extendingOperationConfig.setContextEntityName(domainEntityInterface.getDomainEntity().getEntityName());
        for (int i = 0; i < 10; i++) {
            if (!extendingOperationConfig.resolveReferences(domainEntityInterface.getDomain().getSpace(), i)) {
                break;
            }
        }
        resolvedOperation.setExtendingOperationConfig(extendingOperationConfig);

        // -----------
        //   REQUEST
        // -----------
        MTRequest resolvedRequest = new MTRequest(request != null ?
                                                  request.getParserRuleContext() :
                                                  null, resolvedOperation);
        resolvedRequest.setDescriptionFrom(request, extendedRequest);

        // - METHOD
        String method = null;
        if (request != null && request.getMethod() != null) {
            method = request.getMethod();
        }
        else if (extendedRequest != null) {
            method = extendedRequest.getMethod();
        }
        resolvedRequest.setMethod(method);

        // - ENDPOINT
        //      need to expand the endpoint from the extended request
        MTRequestEndpoint resolvedRequestEndpoint = null;
        if (request != null && request.getEndpoint() != null && request.getEndpoint().getPath() != null) {
            String endpointPath = null;
            if (request.getEndpoint().getPath().equals("inherited")) {
                if (extendedRequest.getEndpoint() == null) {
                    ECLog.logFatal(request.getEndpoint(), "Cannot inherit from an unspecified endpoint.");
                }
                endpointPath = extendedRequest.getEndpoint().getPath();
            }
            else {
                // if its not inherited, use the new one
                endpointPath = request.getEndpoint().getPath();
            }
            resolvedRequestEndpoint = new MTRequestEndpoint(null, resolvedRequest, endpointPath);
            resolvedRequestEndpoint.setTransformStrings(true);
            // copy any new query parameters, but throw error for any specified path parameters
            for (MTRequestEndpointParam param : request.getEndpoint().getParams()) {
                resolvedRequestEndpoint.addParam(param);
            }
        }
        else if (extendedRequest != null && extendedRequest.getEndpoint() != null) {
            resolvedRequestEndpoint = new MTRequestEndpoint(null, resolvedRequest,
                                                            extendedRequest.getEndpoint().getPath());
            resolvedRequestEndpoint.setTransformStrings(true);
            // copy any new query parameters, but throw error for any specified path parameters
            if (request != null) {
                for (MTRequestEndpointParam param : extendedRequest.getEndpoint().getParams()) {
                    resolvedRequestEndpoint.addParam(param);
                }
            }
        }
        resolvedRequest.setEndpoint(resolvedRequestEndpoint);

        // - BODY
        boolean hasDomainRequestBody   = request != null && request.getBody() != null;
        boolean hasAbstractRequestBody = extendedRequest != null && extendedRequest.getBody() != null;
        if (hasDomainRequestBody || hasAbstractRequestBody) {

            MTRequestBody resolvedRequestBody = new MTRequestBody(hasDomainRequestBody ?
                                                                  request.getBody().getParserRuleContext() :
                                                                  extendedRequest.getBody().getParserRuleContext());

            MTRequestBody domainRequestBody = hasDomainRequestBody ?
                                              request.getBody() :
                                              null;
            resolvedRequestBody.setEntityName(getDomainEntity().getEntityName());

            // -- Content Type
            String contentType = hasDomainRequestBody && domainRequestBody.getContentType() != null
                                 ?
                                 domainRequestBody.getContentType()
                                 :
                                 extendedRequest.getBody().getContentType();
            resolvedRequestBody.setContentType(contentType);

            // -- View Name
            boolean useLocalValue = hasDomainRequestBody && domainRequestBody.getViewName() != null;
            String viewName = useLocalValue
                              ?
                              domainRequestBody.getViewName()
                              :
                              getAbstractOperation().getRequest().getBody().getViewName();
            String assignedViewName = null;

            if (extendingOperationConfig != null) {
                assignedViewName = extendingOperationConfig.valueForConfigName(viewName);
            }
            if (assignedViewName == null) {
                assignedViewName = getConfigStringValue(viewName);
            }
            if (assignedViewName == null) {
                ECLog.logFatal((useLocalValue ?
                                "[local] " :
                                "") + "The operation config view parameter \"" + viewName
                               + "\" has NOT been assigned for entity: " + getDomainEntity().getEntityName()
                               + " and operation: " + getAbstractOperation().getName());
            }
            else {
                //ECLog.logInfo((useLocalValue ? "[local] " : "") + "The operation config view parameter \"" + viewName + "\" has been assigned for entity: " + getDomainEntity().getEntityName() + " and operation: " + getAbstractOperation().getName());
            }
            resolvedRequestBody.setViewName(assignedViewName);

            // -- Domain Name
            String domainName = hasDomainRequestBody && domainRequestBody.getDomainName() != null
                                ?
                                domainRequestBody.getDomainName()
                                :
                                getAbstractOperation().getRequest().getBody().getDomainName();
            String assignedDomainName = null;

            if (extendingOperationConfig != null) {
                assignedDomainName = extendingOperationConfig.valueForConfigName(domainName);
            }
            if (assignedDomainName == null) {
                assignedDomainName = getConfigStringValue(domainName);
            }
            if (assignedDomainName == null) {
                ECLog.logFatal("The operation config domain parameter \"" + domainName + "\" has not been assigned");
            }
            resolvedRequestBody.setDomainName(assignedDomainName);

            resolvedRequest.setBody(resolvedRequestBody);
        }

        resolvedOperation.setRequest(resolvedRequest);

        // ------------
        //   RESPONSE
        // ------------
        MTResponse resolvedResponse = new MTResponse(response != null ?
                                                     response.getParserRuleContext() :
                                                     null);

        // - STATUS
        //   if domain context specifies any status, use only the domain status
        //   otherwise use the abstract
        //
        if (response != null && response.getStatusList() != null && response.getStatusList().size() > 0) {

            for (MTResponseStatus status : response.getStatusList()) {
                MTResponseStatus resolvedStatus = new MTResponseStatus(status.getParserRuleContext(), status.getCode());
                resolvedStatus.setDescription(status.getDescription());
                if (status.getConditionExpression() != null) {
                    resolvedStatus.setConditionExpression(status.getConditionExpression());
                }
                resolvedResponse.addStatus(resolvedStatus);
            }
        }

        // - BODY
        boolean hasDomainResponseBody   = response != null && response.getBody() != null;
        boolean hasAbstractResponseBody = extendedResponse != null && extendedResponse.getBody() != null;
        if (hasDomainResponseBody || hasAbstractResponseBody) {
            MTResponseBody resolvedResponseBody = new MTResponseBody(hasDomainResponseBody ?
                                                                     response.getBody().getParserRuleContext() :
                                                                     extendedResponse.getBody().getParserRuleContext());

            MTResponseBody domainResponseBody = hasDomainResponseBody ?
                                                response.getBody() :
                                                null;
            resolvedResponseBody.setEntityName(getDomainEntity().getEntityName());

            // -- Content Type
            String contentType = hasDomainResponseBody && domainResponseBody.getContentType() != null
                                 ?
                                 domainResponseBody.getContentType()
                                 :
                                 extendedResponse.getBody().getContentType();
            resolvedResponseBody.setContentType(contentType);

            // -- View Name
            String viewName = hasDomainResponseBody && domainResponseBody.getViewName() != null
                              ?
                              domainResponseBody.getViewName()
                              :
                              getAbstractOperation().getResponse().getBody().getViewName();
            String assignedViewName = null;

            if (extendingOperationConfig != null) {
                assignedViewName = extendingOperationConfig.valueForConfigName(viewName);
            }

            if (assignedViewName == null) {
                assignedViewName = getConfigStringValue(viewName);
            }
            if (assignedViewName == null) {
                ECLog.logFatal("The operation config parameter \"" + viewName + "\" has not been assigned");
            }
            resolvedResponseBody.setViewName(assignedViewName);

            // -- Domain Name
            String domainName = hasDomainResponseBody && domainResponseBody.getDomainName() != null
                                ?
                                domainResponseBody.getDomainName()
                                :
                                getAbstractOperation().getResponse().getBody().getDomainName();
            String assignedDomainName = null;
            if (extendingOperationConfig != null) {
                assignedDomainName = extendingOperationConfig.valueForConfigName(domainName);
            }
            if (assignedDomainName == null) {
                assignedDomainName = getConfigStringValue(domainName);
            }
            if (assignedDomainName == null) {
                ECLog.logFatal("The operation config parameter \"" + assignedDomainName + "\" has not been assigned");
            }
            resolvedResponseBody.setDomainName(assignedDomainName);

            resolvedResponse.setBody(resolvedResponseBody);
        }
        resolvedOperation.setResponse(resolvedResponse);

        for (int i = 0; i < 10; i++) {
            if (!resolvedOperation.resolveReferences(domainEntityInterface.getDomain().getSpace(), i)) {
                break;
            }
        }

        // transform the description
        if (resolvedOperation.transformableDescription != null) {
            resolvedOperation.transformableDescription.transform(this);
        }

        this.resolvedOperation = resolvedOperation;
        return resolvedOperation;
    }

    private String getConfigStringValue(String name) {
        String                    returnValue = null;
        MTOperationConfigArgument arg         = null;
        if (isExtendedAbstract()) {
            arg = abstractOperation.getConfig().getArgumentByName(name);
            if (arg != null) {
                returnValue = arg.getDefaultValue();
            }
            else {
                ECLog.logFatal("No argument defined: " + name);
            }
        }
        else {
            MTDEInterfaceOperation operation = resolvedOperation != null ?
                                               resolvedOperation :
                                               extendedOperation;
            returnValue = operation.getConfig().valueForConfigName(name);
            if (returnValue == null) {
                MTInterfaceOperation rootAbstractOperation = operation.getAbstractOperation();
                if (rootAbstractOperation != null) {
                    arg = rootAbstractOperation.getConfig().getArgumentByName(name);
                    if (arg != null) {
                        returnValue = arg.getDefaultValue();
                    }
                }
            }
        }
        return returnValue;
    }

    public MTInterface getInterface() {
        return domainEntityInterface.getAbstractInterface();
    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        boolean needsAnotherPass = false;
        if (abstractOperation == null) {
            MTInterfaceOperation abstractOperation = space.getOperationByName(getInterface(), extendedOperationName);
            if (abstractOperation != null) {
                this.abstractOperation = abstractOperation;
                //ECLog.logInfo("FOUND operation: " + getInterface().getName() + "." + extendedOperationName);
                if (!validConfig(space)) {
                    exit(1);
                }
            }
            else {
                if (extendedOperationName.equals(name)) {
                    ECLog.logFatal("An operation cannot extend itself!!");
                }
                MTDEInterfaceOperation extendedOperation = domainEntityInterface.getOperationByName(
                    extendedOperationName);
                if (extendedOperation != null) {
                    if (extendedOperation.getName().equals(name)) {
                        ECLog.logFatal("An operation cannot extend itself!!");
                    }
                    //ECLog.logInfo("FOUND extended operation: " + extendedOperationName + " (from " + name + ")");
                    this.extendedOperation = extendedOperation;
                }
                else {
                    if (getInterface() == null) {
                        ECLog.logError("Operation is not part of an interface: " + extendedOperationName);
                    }
                    else {
                        ECLog.logError("So far cant find operation: " + getInterface().getName() + "."
                                       + extendedOperationName);
                    }
                    needsAnotherPass = true;
                }
            }
        }
        if (request != null) {
            if (request.resolveReferences(space, pass)) {
                needsAnotherPass = true;
            }
        }
        if (response != null) {
            if (response.resolveReferences(space, pass)) {
                needsAnotherPass = true;
            }
        }
        return needsAnotherPass;
    }

    private boolean validConfig(MTSpace space) {
        MTOperationConfig config = this.abstractOperation.getConfig();
        if (config.numberOfContextArguments() != 2) {
            ECLog.logError(this,
                           "Incorrect number of context parameters for operation: " + this.abstractOperation.getName());
            return false;
        }
        MTOperationConfigArgument domainContext = config.getArgumentByType(
            MTOperationConfigArgument.ArgumentType.Domain);
        MTOperationConfigArgument entityContext = config.getArgumentByType(
            MTOperationConfigArgument.ArgumentType.Entity);
        if (domainContext == null) {
            ECLog.logError(this, "Expecting a context parameter to be \"domain\" type for operation: "
                                 + this.abstractOperation.getName());
            return false;
        }

        if (entityContext == null) {
            ECLog.logError(this, "Expecting a context parameter to be \"entity\" type for operation: "
                                 + this.abstractOperation.getName());
            return false;
        }

        if (config == null && extendingOperationConfig == null) {
            ECLog.logError(this, "No configuration for operation: " + this.abstractOperation.getName());
            return false;
        }

        boolean error = false;
        for (int i = 0; i < config.getArgumentList().size(); i++) {
            MTOperationConfigArgument argument              = config.getArgumentList().get(i);
            String                    argumentName          = argument.getName();
            String                    argumentAssignedValue = null;

            if (argument.isContext()) {
                if (argument.getType() == MTOperationConfigArgument.ArgumentType.Domain) {
                    argumentAssignedValue = getDomain().getName();
                }
                else if (argument.getType() == MTOperationConfigArgument.ArgumentType.Entity) {
                    argumentAssignedValue = getDomainEntity().getEntityName();
                }
            }
            else {
                argumentAssignedValue = extendingOperationConfig != null ?
                                        extendingOperationConfig.valueForConfigName(argumentName) :
                                        null;
                if (argumentAssignedValue == null) {
                    argumentAssignedValue = argument.getDefaultValue();
                }
            }

            if (argumentAssignedValue == null) {
                ECLog.logError(this, "No assignment was made to argument: " + this.abstractOperation.getName() + "."
                                     + argumentName);
                error = true;
            }
            MTOperationConfigArgument.ArgumentType type = argument.getType();
            if (type == MTOperationConfigArgument.ArgumentType.Domain) {
                if (space.getDomainWithName(argumentAssignedValue) == null) {
                    ECLog.logError(this, "Assigned domain is never defined: " + argumentAssignedValue);
                    error = true;
                }
            }
        }
        return !error;
    }

    // used to transform strings in a domain interface operation
    public String transformString(String input) {
        if (input == null) {
            return null;
        }
        Map<MTOperationConfigArgument.ArgumentType, Map<String, String>> argumentTypeMapMap = new HashMap<>();
        for (MTOperationConfigArgument.ArgumentType type : MTOperationConfigArgument.ArgumentType.values()) {
            argumentTypeMapMap.put(type, new HashMap<>());
        }

        if (getExtendingOperationConfig().getAbstractOperation() == null) {
            ECLog.logFatal("NO EXTENDING OPERATION CONFIG!");
        }
        for (MTOperationConfigArgument argument : getExtendingOperationConfig().getAbstractOperation().getConfig().getArgumentList()) {
            String assignedName = getExtendingOperationConfig().getAssignments().get(argument.getName());
            argumentTypeMapMap.get(argument.getType()).put(argument.getName(), assignedName);
        }

        MTSpace miniSpace = getDomain().getSpace().makeMiniSpace(
            argumentTypeMapMap.get(MTOperationConfigArgument.ArgumentType.Domain),
            argumentTypeMapMap.get(MTOperationConfigArgument.ArgumentType.Entity),
            getDomainEntity().getEntity(),
            argumentTypeMapMap.get(MTOperationConfigArgument.ArgumentType.View));

        MTRoot miniRoot = new MTRoot(this.getParserRuleContext());
        miniRoot.setSpace(miniSpace);
        StringTemplateTransform transform = new StringTemplateTransform(miniRoot, input);

        for (MTOperationConfigArgument argument : getExtendingOperationConfig().getAbstractOperation().getConfig().getArgumentList()) {
            switch (argument.getType()) {
                case Domain:
                    transform.addReadOnlyVariable(argument.getName(), miniSpace.getDomainWithName(argument.getName()));
                    break;
                case View:

                    break;
                case Entity:
                    transform.addReadOnlyVariable(argument.getName(), miniSpace.getEntityWithName(argument.getName()));
                    break;
            }
        }

        if (!transform.canStart()) {
            ECLog.logFatal("Not able to transform path \"" + input + "\".");
        }

        transform.start();

        return transform.getOutputStringValue();
    }

    public MTDEInterfaceOperationConfig getExtendingOperationConfig() {
        return extendingOperationConfig;
    }

    public MTDomain getDomain() {
        return domainEntityInterface.getDomain();
    }

    public MTDEntity getDomainEntity() {
        return domainEntityInterface.getDomainEntity();
    }

    public void setExtendingOperationConfig(MTDEInterfaceOperationConfig extendingOperationConfig) {
        this.extendingOperationConfig = extendingOperationConfig;
    }

    public MTDEInterfaceOperationConfig getConfig() {
        return extendingOperationConfig;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }
}

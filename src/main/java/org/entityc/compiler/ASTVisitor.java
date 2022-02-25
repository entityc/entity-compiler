/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler;

import org.entityc.compiler.model.MTCodeFormat;
import org.entityc.compiler.model.MTModule;
import org.entityc.compiler.model.MTNamespace;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.MTRoot;
import org.entityc.compiler.model.config.MTConfiguration;
import org.entityc.compiler.model.config.MTDirectory;
import org.entityc.compiler.model.config.MTProtoc;
import org.entityc.compiler.model.config.MTRepository;
import org.entityc.compiler.model.config.MTRepositoryImport;
import org.entityc.compiler.model.config.MTRepositoryType;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.config.MTSpaceImport;
import org.entityc.compiler.model.config.MTSpaceInclude;
import org.entityc.compiler.model.config.MTTemplate;
import org.entityc.compiler.model.config.MTTransform;
import org.entityc.compiler.model.domain.MTDApplyTemplate;
import org.entityc.compiler.model.domain.MTDEAttribute;
import org.entityc.compiler.model.domain.MTDEAttributeBitField;
import org.entityc.compiler.model.domain.MTDEInterface;
import org.entityc.compiler.model.domain.MTDEInterfaceOperation;
import org.entityc.compiler.model.domain.MTDEInterfaceOperationConfig;
import org.entityc.compiler.model.domain.MTDERelationship;
import org.entityc.compiler.model.domain.MTDEntity;
import org.entityc.compiler.model.domain.MTDEnum;
import org.entityc.compiler.model.domain.MTDEnumItem;
import org.entityc.compiler.model.domain.MTDModule;
import org.entityc.compiler.model.domain.MTDView;
import org.entityc.compiler.model.domain.MTDomain;
import org.entityc.compiler.model.domain.MTNaming;
import org.entityc.compiler.model.domain.MTNamingMethod;
import org.entityc.compiler.model.entity.HalfRelationshipPlurality;
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.model.entity.MTAttributeConstraint;
import org.entityc.compiler.model.entity.MTBitField;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTEntityTemplate;
import org.entityc.compiler.model.entity.MTEntityTemplateInstantiation;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTEnumItem;
import org.entityc.compiler.model.entity.MTNativeType;
import org.entityc.compiler.model.entity.MTPrimaryKey;
import org.entityc.compiler.model.entity.MTRelationship;
import org.entityc.compiler.model.entity.MTTagSet;
import org.entityc.compiler.model.entity.MTTypedef;
import org.entityc.compiler.model.entity.MTUnit;
import org.entityc.compiler.model.entity.MTView;
import org.entityc.compiler.model.expression.MTConstant;
import org.entityc.compiler.model.expression.MTExpression;
import org.entityc.compiler.model.expression.MTMethodCall;
import org.entityc.compiler.model.expression.MTOperand;
import org.entityc.compiler.model.expression.MTOperation;
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
import org.entityc.compiler.model.language.MTLanguage;
import org.entityc.compiler.model.language.MTLanguageFunction;
import org.entityc.compiler.model.language.MTLanguageFunctionArgument;
import org.entityc.compiler.model.tagging.MTTagContext;
import org.entityc.compiler.model.tagging.MTTagDef;
import org.entityc.compiler.model.tagging.MTTagValueDef;
import org.entityc.compiler.util.ECLog;
import org.entityc.compiler.util.ECStringUtil;
import org.entityc.compiler.util.ECVersion;
import org.antlr.v4.runtime.tree.TerminalNode;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;
import java.util.stream.Collectors;

public class ASTVisitor extends EntityLanguageBaseVisitor {

    private final Map<String, List<EntityLanguageParser.ViewContext>> abstractViewContexts = new HashMap<>();
    private final Stack<MTSpace>                                      spaceStack           = new Stack<>();
    private       MTRoot                                              root;
    private       MTDomain                                            currentDomain;
    private       MTModule                                            currentModule;
    private       MTEntity                                            currentEntity;
    private       MTAttribute                                         currentAttribute;
    private       MTDModule                                           currentDomainModule;
    private       MTDEntity                                           currentDomainEntity;
    private       MTDEnum                                             currentDomainEnum;
    private       MTLanguage                                          currentLanguage;
    private       MTConfiguration                                     currentConfiguration;
    private       MTInterface                                         currentInterface;
    private       MTRequest                                           currentRequest;
    private       MTDEInterface                                       currentDomainEntityInterface;
    private       MTDEInterfaceOperation                              currentDomainEntityInterfaceOperation;
    private       MTSpace                                             foundSpace;

    public MTSpace getFoundSpace() {
        return foundSpace;
    }

    public MTRoot visitRoot(EntityLanguageParser.RootContext ctx, MTRoot root, MTSpace space) {
        final String fatalMessage = " cannot be declared since a space has not yet been defined.";
        if (root == null) {
            root = new MTRoot(ctx);
        }
        this.root = root;

        if (!hasCurrentSpace()) {
            if (space != null) {
                pushSpace(space);
            } else if (root.getSpace() != null) {
                pushSpace(root.getSpace());
            }
        }
        if (ctx.space().size() > 1) {
            ECLog.logFatal("There can only be one space declaration.");
        }

        if (ctx.space().size() > 0) {
            visitSpace(ctx.space(0));
        }

        for (EntityLanguageParser.EntityContext ec : ctx.entity()) {
            if (!hasCurrentSpace()) {
                ECLog.logFatal("Entity" + fatalMessage);
            }
            MTEntity entity = (MTEntity) visit(ec);
            if (entity != null) {
                currentSpace().addEntity(entity);
            }
        }

        for (EntityLanguageParser.EnumStatementContext esc : ctx.enumStatement()) {
            if (!hasCurrentSpace()) {
                ECLog.logFatal("Enum" + fatalMessage);
            }
            MTEnum mtEnum = visitEnumStatement(esc);
            currentSpace().addEnum(mtEnum);
        }

        for (EntityLanguageParser.ModuleContext moduleContext : ctx.module()) {
            if (!hasCurrentSpace()) {
                ECLog.logFatal("Module" + fatalMessage);
            }
            MTModule module = (MTModule) visit(moduleContext);
            currentSpace().addModule(module);
        }

        for (EntityLanguageParser.DomainContext domainContext : ctx.domain()) {
            if (!hasCurrentSpace()) {
                ECLog.logFatal("Domain" + fatalMessage);
            }
            MTDomain domain = (MTDomain) visit(domainContext);
            currentSpace().addDomain(domain);
        }

        for (EntityLanguageParser.LanguageContext languageContext : ctx.language()) {
            if (!hasCurrentSpace()) {
                ECLog.logFatal("Language" + fatalMessage);
            }
            MTLanguage language = (MTLanguage) visit(languageContext);
            currentSpace().addLanguage(language);
        }

        for (EntityLanguageParser.AbstractInterfaceStatementContext interfaceStatementContext : ctx.abstractInterfaceStatement()) {
            if (!hasCurrentSpace()) {
                ECLog.logFatal("Interface" + fatalMessage);
            }
            currentSpace().addInterface(visitAbstractInterfaceStatement(interfaceStatementContext));
            currentInterface = null;
        }

        for (EntityLanguageParser.TypedefStatementContext typedefStatementContext : ctx.typedefStatement()) {
            if (!hasCurrentSpace()) {
                ECLog.logFatal("Typedef" + fatalMessage);
            }
            MTTypedef typedef = visitTypedefStatement(typedefStatementContext);
            currentSpace().addTypedef(typedef);
        }

        if (ctx.configuration() != null) {
            for (EntityLanguageParser.ConfigurationContext configurationContext : ctx.configuration()) {
                root.addConfiguration(visitConfiguration(configurationContext));
            }
        }

        if (ctx.units() != null) {
            for (EntityLanguageParser.UnitsContext unitsContext : ctx.units()) {
                visitUnits(unitsContext);
            }
        }

        if (ctx.formatting() != null) {
            for (EntityLanguageParser.FormattingContext formattingContext : ctx.formatting()) {
                for (EntityLanguageParser.FormatStatementContext statementContext : formattingContext.formattingBody().formatStatement()) {
                    visitFormatStatement(statementContext); // should add it to the root node
                }
            }
        }

        return root;
    }

    @Override
    public Object visitUnits(EntityLanguageParser.UnitsContext ctx) {
        if (ctx.unitsBody() != null) {
            if (EntityCompiler.isVerbose()) {
                ECLog.logInfo("READING UNITS...");
            }
            for (EntityLanguageParser.UnitDefinitionContext unitDefinitionContext : ctx.unitsBody().unitDefinition()) {
                String                                         name         = unitDefinitionContext.id(0).getText();
                String                                         baseUnitName = unitDefinitionContext.EXTENDS() != null ?
                                                                              unitDefinitionContext.id(1).getText() :
                                                                              null;
                EntityLanguageParser.UnitDefinitionBodyContext body         = unitDefinitionContext.unitDefinitionBody();
                String                                         abbr         = null;
                double                                         multiplier   = 1.0;
                if (body != null) {
                    for (EntityLanguageParser.UnitDefinitionFieldContext field : body.unitDefinitionField()) {
                        if (field.ABBR() != null) {
                            abbr = ECStringUtil.ProcessParserString(field.STRING().getText());
                        } else if (field.MULTIPLIER() != null) {
                            multiplier = Double.valueOf(field.FLOAT().getText());
                        }
                    }
                }
                MTUnit.AddUnit(name, abbr, baseUnitName, multiplier);
            }
        }
        return null;
    }

    @Override
    public MTConfiguration visitConfiguration(EntityLanguageParser.ConfigurationContext ctx) {
        currentConfiguration = new MTConfiguration(ctx, this.root, ctx.id().getText());
        visitChildren(ctx);
        return currentConfiguration;
    }

    @Override
    public MTInterface visitAbstractInterfaceStatement(EntityLanguageParser.AbstractInterfaceStatementContext ctx) {
        currentInterface = new MTInterface(ctx, ctx.id().getText());
        EntityLanguageParser.InterfaceBodyContext body = ctx.interfaceBody();
        if (body.descriptionStatement() != null) {
            setNodeDescription(currentInterface, body.descriptionStatement(), false);
        }
        if (body.interfaceType() != null && body.interfaceType().size() > 0) {
            currentInterface.setType(body.interfaceType(0).getText());
        }
        if (body.operation() != null) {
            for (EntityLanguageParser.OperationContext operationContext : body.operation()) {
                currentInterface.addOperation(visitOperation(operationContext));
            }
        }
        return currentInterface;
    }

    @Override
    public MTTypedef visitTypedefStatement(EntityLanguageParser.TypedefStatementContext ctx) {

        int       bitWidth = ctx.INT32_TYPE() != null ?
                             32 :
                             64;
        MTTypedef typedef  = new MTTypedef(ctx, currentModule, bitWidth, ctx.id().getText());
        if (ctx.typedefBody().descriptionStatement() != null) {
            setNodeDescription(typedef, ctx.typedefBody().descriptionStatement(), false);
        }

        if (ctx.typedefBody().tagStatement() != null) {
            typedef.addTagsWithValues(tagStringsFromTagStatements(ctx.typedefBody().tagStatement()));
        }

        for (EntityLanguageParser.BitfieldContext bitfieldContext : ctx.typedefBody().bitfield()) {
            typedef.addBitField(visitBitfield(bitfieldContext));
        }
        if (currentModule != null) {
            currentModule.addTypedef(typedef);
        }
        return typedef;
    }

    @Override
    public MTInterfaceOperation visitOperation(EntityLanguageParser.OperationContext ctx) {
        MTInterfaceOperation operation = new MTInterfaceOperation(ctx, currentInterface, ctx.id().getText());
        operation.setAbstract(ctx.ABSTRACT() != null);
        EntityLanguageParser.OperationBodyContext body = ctx.operationBody();
        if (body.descriptionStatement() != null) {
            setNodeDescription(operation, body.descriptionStatement(), false);
        }
        if (body.operationConfig() != null && body.operationConfig().size() > 0) {
            operation.setConfig(visitOperationConfig(body.operationConfig(0)));
        }
        if (body.operationRequest() != null && body.operationRequest().size() > 0) {
            operation.setRequest(visitOperationRequest(body.operationRequest(0)));
        }
        if (body.operationResponse() != null && body.operationResponse().size() > 0) {
            operation.setResponse(visitOperationResponse(body.operationResponse(0)));
            operation.setResponse(visitOperationResponse(body.operationResponse(0)));
        }

        return operation;
    }

    @Override
    public MTOperationConfig visitOperationConfig(EntityLanguageParser.OperationConfigContext ctx) {
        MTOperationConfig                                config = new MTOperationConfig(ctx);
        EntityLanguageParser.OperationConfigBlockContext block  = ctx.operationConfigBlock();

        for (EntityLanguageParser.OperationConfigContextContext contextContext : block.operationConfigContext()) {
            config.addArgument(visitOperationConfigContext(contextContext));
        }
        for (EntityLanguageParser.OperationConfigArgumentContext argumentContext : block.operationConfigArgument()) {
            config.addArgument(visitOperationConfigArgument(argumentContext));
        }
        return config;
    }

    @Override
    public MTRequest visitOperationRequest(EntityLanguageParser.OperationRequestContext ctx) {

        EntityLanguageParser.OperationRequestBlockContext block = ctx.operationRequestBlock();
        currentRequest = new MTRequest(ctx, currentDomainEntityInterfaceOperation);

        // method
        if (block.operationRequestMethod() != null
            && block.operationRequestMethod().size() > 0) {
            currentRequest.setMethod(block.operationRequestMethod(0).id().getText());
        }
        // body
        if (block.operationRequestBody() != null
            && block.operationRequestBody().size() > 0) {
            MTRequestBody body = visitOperationRequestBody(ctx.operationRequestBlock().operationRequestBody(0));
            currentRequest.setBody(body);
        }

        if (block.operationRequestEndpoint() != null && block.operationRequestEndpoint().size() > 0) {
            EntityLanguageParser.OperationRequestEndpointContext endpointContext = block.operationRequestEndpoint(0);
            currentRequest.setEndpoint(visitOperationRequestEndpoint(endpointContext));
        }
        return currentRequest;
    }

    @Override
    public MTResponse visitOperationResponse(EntityLanguageParser.OperationResponseContext ctx) {
        MTResponse                                         response = new MTResponse(ctx);
        EntityLanguageParser.OperationResponseBlockContext block    = ctx.operationResponseBlock();

        if (block.operationResponseStatus() != null) {
            for (EntityLanguageParser.OperationResponseStatusContext statusContext : block.operationResponseStatus()) {
                response.addStatus(visitOperationResponseStatus(statusContext));
            }
        }
        // body
        if (block.operationResponseBody() != null
            && block.operationResponseBody().size() > 0) {
            MTResponseBody body = visitOperationResponseBody(block.operationResponseBody(0));
            response.setBody(body);
        }
        return response;
    }

    @Override
    public MTOperationConfigArgument visitOperationConfigContext(EntityLanguageParser.OperationConfigContextContext ctx) {
        MTOperationConfigArgument.ArgumentType contextType = MTOperationConfigArgument.ArgumentType.FromName(
                ctx.operationConfigContextType().getText());
        MTOperationConfigArgument              argument    = new MTOperationConfigArgument(ctx, contextType,
                                                                                           ctx.id(0).getText(),
                                                                                           ctx.id().size() > 1 ?
                                                                                           ctx.id(1).getText() :
                                                                                           null, true);
        if (ctx.operationContextBlock().descriptionStatement() != null) {
            setNodeDescription(argument, ctx.operationContextBlock().descriptionStatement(), false);
        }
        return argument;
    }

    @Override
    public MTOperationConfigArgument visitOperationConfigArgument(EntityLanguageParser.OperationConfigArgumentContext ctx) {
        MTOperationConfigArgument.ArgumentType argumentType = MTOperationConfigArgument.ArgumentType.FromName(
                ctx.operationConfigArgumentType().getText());
        MTOperationConfigArgument              argument     = new MTOperationConfigArgument(ctx, argumentType,
                                                                                            ctx.id(0).getText(),
                                                                                            ctx.id().size() > 1 ?
                                                                                            ctx.id(1).getText() :
                                                                                            null, false);
        if (ctx.operationArgumentBlock().descriptionStatement() != null) {
            setNodeDescription(argument, ctx.operationArgumentBlock().descriptionStatement(), false);
        }
        return argument;
    }

    @Override
    public MTRequestBody visitOperationRequestBody(EntityLanguageParser.OperationRequestBodyContext ctx) {
        MTRequestBody                                         body  = new MTRequestBody(ctx);
        EntityLanguageParser.OperationRequestBodyBlockContext block = ctx.operationRequestBodyBlock();
        if (block.operationBodyContentType() != null && block.operationBodyContentType().size() > 0) {
            body.setContentType(ECStringUtil.ProcessParserString(block.operationBodyContentType(0).STRING().getText()));
        }
        if (block.operationBodyDomain() != null && block.operationBodyDomain().size() > 0) {
            body.setDomainName(block.operationBodyDomain(0).id().getText());
        }
        if (block.operationBodyView() != null && block.operationBodyView().size() > 0) {
            body.setViewName(block.operationBodyView(0).id().getText());
        }
        return body;
    }

    @Override
    public MTRequestEndpoint visitOperationRequestEndpoint(EntityLanguageParser.OperationRequestEndpointContext ctx) {

        String pathUrlString = null;

        if (ctx.STRING() != null) {
            pathUrlString = ctx.STRING().getText();
        }
        MTRequestEndpoint                                         path  = new MTRequestEndpoint(ctx, currentRequest,
                                                                                                pathUrlString);
        EntityLanguageParser.OperationRequestEndpointBlockContext block = ctx.operationRequestEndpointBlock();

        if (block != null) {
            if (block.descriptionStatement() != null) {
                setNodeDescription(path, block.descriptionStatement(), false);
            }
            if (block.operationRequestEndpointParam() != null) {
                for (EntityLanguageParser.OperationRequestEndpointParamContext paramContext : block.operationRequestEndpointParam()) {
                    path.addParam(visitOperationRequestEndpointParam(paramContext));
                }
            }
        }
        return path;
    }

    @Override
    public MTResponseStatus visitOperationResponseStatus(EntityLanguageParser.OperationResponseStatusContext ctx) {
        String statusText = null;
        if (ctx.id() != null) {
            statusText = ctx.id().getText();
        } else if (ctx.INTEGER() != null) {
            statusText = ctx.INTEGER().getText();
        }
        MTResponseStatus                                         status = new MTResponseStatus(ctx, statusText);
        EntityLanguageParser.OperationResponseStatusBlockContext block  = ctx.operationResponseStatusBlock();

        if (block.descriptionStatement() != null) {
            setNodeDescription(status, block.descriptionStatement(), false);
        }

        EntityLanguageParser.OperationRequestStatusExpressionContext expressionContext = ctx.operationRequestStatusExpression();
        if (expressionContext != null && expressionContext.expression() != null) {
            status.setConditionExpression(visitExpression(expressionContext.expression()));
        }

        return status;
    }

    @Override
    public MTResponseBody visitOperationResponseBody(EntityLanguageParser.OperationResponseBodyContext ctx) {
        MTResponseBody                                         body  = new MTResponseBody(ctx);
        EntityLanguageParser.OperationResponseBodyBlockContext block = ctx.operationResponseBodyBlock();
        if (block.operationBodyContentType() != null && block.operationBodyContentType().size() > 0) {
            body.setContentType(ECStringUtil.ProcessParserString(block.operationBodyContentType(0).STRING().getText()));
        }
        if (block.operationBodyDomain() != null && block.operationBodyDomain().size() > 0) {
            body.setDomainName(block.operationBodyDomain(0).id().getText());
        }
        if (block.operationBodyView() != null && block.operationBodyView().size() > 0) {
            body.setViewName(block.operationBodyView(0).id().getText());
        }
        return body;
    }

    @Override
    public MTRequestEndpointParam visitOperationRequestEndpointParam(EntityLanguageParser.OperationRequestEndpointParamContext ctx) {

        String                paramName = ctx.id().getText();
        boolean               query     = ctx.QUERY() != null;
        String                typeName  = ctx.type().getText();
        MTNativeType.DataType dataType  = MTNativeType.DataType.FromName(typeName);

        if (dataType == null) {
            ECLog.logWarning(ctx.type(), "Operation parameters must be of a native type. Using int32.");
            dataType = MTNativeType.DataType.INT32;
        }
        MTRequestEndpointParam param = new MTRequestEndpointParam(ctx, query, new MTNativeType(ctx.type(), dataType),
                                                                  paramName);

        if (ctx.operationRequestEndpointParamBlock() != null
            && ctx.operationRequestEndpointParamBlock().descriptionStatement() != null) {
            setNodeDescription(param, ctx.operationRequestEndpointParamBlock().descriptionStatement(), false);
        }
        return param;
    }

    @Override
    public MTExpression visitExpression(EntityLanguageParser.ExpressionContext ctx) {

        MTExpression expression = null;
        //ECLog.logInfo(">> Visiting expression: " + ctx.getText());
        if (ctx.bop != null) {
            // Binary operator
            String opSymbol = ctx.bop.getText();
            if (MTOperation.Operator.isValidOperator(opSymbol)) {
                MTOperation.Operator operator           = MTOperation.Operator.getOperatorBySymbol(opSymbol);
                List<MTExpression>   operandExpressions = new ArrayList<>(3);
                if (ctx.ID() != null) {
                    MTOperand operand = new MTOperand(ctx, MTOperand.Type.UNKNOWN, ctx.ID().getText());
                    if (operand != null) {
                        operandExpressions.add(operand);
                    }
                    //ECLog.logInfo(">>>> With Operator: " + opSymbol + " and ID: " + ctx.ID().getText());
                } else if (ctx.methodCall() != null) {
                    MTExpression methodCall = visitMethodCall(ctx.methodCall());
                    if (methodCall != null) {
                        operandExpressions.add(methodCall);
                    }
                    //ECLog.logInfo(">>>> With Operator: " + opSymbol + " and method call: " + ctx.methodCall().getText());
                } else {
                    //ECLog.logInfo(">>>> With Operator: " + opSymbol);
                }

                int numOperands = operator.getOperandCount();
                if (ctx.expression().size() != numOperands) {
                    ECLog.logFatal(ctx, "Invalid number of operands for operation: " + opSymbol);
                }
                for (EntityLanguageParser.ExpressionContext expressionContext : ctx.expression()) {
                    MTExpression operandExpression = visitExpression(expressionContext);
                    if (operandExpression != null) {
                        operandExpressions.add(operandExpression);
                    } else {
                        ECLog.logFatal(expressionContext, "Unable to parse: " + expressionContext.getText());
                    }
                }
                if (operandExpressions.size() < numOperands) {
                    ECLog.logFatal(ctx, "Unable to parse all operands for operation: " + opSymbol);
                }

                expression = new MTOperation(ctx, operator, operandExpressions);
            }
        } else if (ctx.methodCall() != null) {
            // Method call
            expression = visitMethodCall(ctx.methodCall());
        } else if (ctx.primary() != null) {
            // Constant or a variable
            EntityLanguageParser.ConstantContext constantContext = ctx.primary().constant();
            if (constantContext != null) {
                expression = visitConstant(ctx.primary().constant());
            } else if (ctx.primary().expression() != null) {
                expression = visitExpression(ctx.primary().expression());
            } else if (ctx.primary().ident() != null) {

                // could be either an entity attribute name or a config of an interface
                if (currentDomainEntityInterfaceOperation != null
                    && currentDomainEntityInterfaceOperation.getExtendingOperationConfig() != null) {
                    String extendingConfigName = currentDomainEntityInterfaceOperation.getExtendingOperationConfig().getName();
                    if (extendingConfigName != null) {
                        if (ctx.primary().ident().getText().equals(extendingConfigName)) {
                            expression = currentDomainEntityInterfaceOperation.getExtendingOperationConfig();
                            return expression;
                        }
                    }
                } else {
                    //ECLog.logInfo(ctx.primary(), "No domain operation context here: " + ctx.primary().ID().getText());
                }
                // variable to an attribute name or other variable in the model
                if (currentDomainEntity == null) {
                    expression = new MTOperand(ctx.primary(), MTOperand.Type.ATTRIBUTE, currentEntity.getName(),
                                               ctx.primary().ident().getText());
                    //ECLog.logFatal(ctx.primary(), "Attribute references can only be made in the context of an entity.");
                } else {
                    expression = new MTOperand(ctx.primary(), MTOperand.Type.ATTRIBUTE,
                                               currentDomainEntity.getEntityName(), ctx.primary().ident().getText());
                }
            }
        } else if (ctx.prefix != null) {
            String opSymbol = ctx.prefix.getText();
            if (MTOperation.Operator.isValidOperator(opSymbol)) {
                if (ctx.expression().size() < 1) {
                    ECLog.logFatal(ctx, "Unsupported expression: " + ctx.getText());
                }
                MTOperation.Operator operator = MTOperation.Operator.getOperatorBySymbol(opSymbol);
                List<MTExpression>   operands = new ArrayList<>();
                operands.add(visitExpression(ctx.expression().get(0)));
                expression = new MTOperation(ctx, operator, operands);
            }
        }
        if (expression == null) {
            ECLog.logFatal(ctx, "Unsupported expression: " + ctx.getText());
        }
        return expression;
    }

    @Override
    public MTConstant visitConstant(EntityLanguageParser.ConstantContext ctx) {
        MTConstant expression = null;
        if (ctx.STRING() != null) {
            expression = new MTConstant(ctx, ctx.STRING().getText());
        } else if (ctx.INTEGER() != null) {
            expression = new MTConstant(ctx, Long.valueOf(ctx.INTEGER().getText()));
        } else if (ctx.FLOAT() != null) {
            expression = new MTConstant(ctx, Double.valueOf(ctx.FLOAT().getText()));
        } else if (ctx.BOOLEAN != null) {
            boolean value = ctx.BOOLEAN.getText().equals("true");
            expression = new MTConstant(ctx, value);
        } else {
            ECLog.logFatal(ctx, "Unknown constant: " + ctx.getText());
        }
        return expression;
    }

    @Override
    public MTMethodCall visitMethodCall(EntityLanguageParser.MethodCallContext ctx) {
        MTMethodCall methodCall = null;

        methodCall = new MTMethodCall(ctx, ctx.ID().getText());

        if (ctx.expressionList() != null && ctx.expressionList().expression().size() > 0) {
            for (EntityLanguageParser.ExpressionContext expressionContext : ctx.expressionList().expression()) {
                MTExpression operandExpression = visitExpression(expressionContext);
                if (operandExpression != null) {
                    methodCall.addArgument(operandExpression);
                } else {
                    ECLog.logFatal(expressionContext, "Unable to parse: " + expressionContext.getText());
                }
            }
        }

        return methodCall;
    }

    @Override
    public Object visitRepository(EntityLanguageParser.RepositoryContext ctx) {
        MTRepository                               repository            = new MTRepository(ctx, ctx.id().getText());
        EntityLanguageParser.RepositoryBodyContext repositoryBodyContext = ctx.repositoryBody();

        List<EntityLanguageParser.RepositoryOrganizationContext> repositoryOrganizationContexts = repositoryBodyContext.repositoryOrganization();
        if (repositoryOrganizationContexts != null && repositoryOrganizationContexts.size() > 0) {
            repository.setOrganization(
                    ECStringUtil.ProcessParserString(repositoryOrganizationContexts.get(0).STRING().getText()));
        }

        List<EntityLanguageParser.RepositoryNameContext> repositoryNameContexts = repositoryBodyContext.repositoryName();
        if (repositoryNameContexts != null && repositoryNameContexts.size() > 0) {
            repository.setRepoName(ECStringUtil.ProcessParserString(repositoryNameContexts.get(0).STRING().getText()));
        }

        List<EntityLanguageParser.RepositoryTagContext> repositoryTagContexts = repositoryBodyContext.repositoryTag();
        if (repositoryTagContexts != null && repositoryTagContexts.size() > 0) {
            repository.setTag(ECStringUtil.ProcessParserString(repositoryTagContexts.get(0).STRING().getText()));
        }

        List<EntityLanguageParser.RepositoryPathContext> repositoryPathContexts = repositoryBodyContext.repositoryPath();
        if (repositoryPathContexts != null && repositoryPathContexts.size() > 0) {
            repository.setPath(ECStringUtil.ProcessParserString(repositoryPathContexts.get(0).STRING().getText()));
        }

        List<EntityLanguageParser.RepositoryTypeContext> repositoryTypeContexts = repositoryBodyContext.repositoryType();
        if (repositoryTypeContexts != null && repositoryTypeContexts.size() > 0) {
            MTRepositoryType type = MTRepositoryType.FromName(repositoryTypeContexts.get(0).id().getText());
            if (type == null) {
                ECLog.logFatal(repositoryTypeContexts.get(0),
                               "Unknown repository type: " + repositoryTypeContexts.get(0).id().getText());
            }
            repository.setType(type);
        }
        currentSpace().addRepository(repository);

        return repository;
    }

    public MTSpace currentSpace() {
        return hasCurrentSpace() ?
               spaceStack.peek() :
               null;
    }

    private boolean hasCurrentSpace() {
        return !spaceStack.empty();
    }

    public void pushSpace(MTSpace space) {
        spaceStack.push(space);
    }

    @Override
    public MTSpace visitSpace(EntityLanguageParser.SpaceContext ctx) {
        MTSpace space = new MTSpace(ctx, ctx.id().getText());
        if (hasCurrentSpace()) {
            currentSpace().addConnectedToSpace(space);
        }
        if (foundSpace == null) {
            foundSpace = space;
        }
        pushSpace(space);
        EntityLanguageParser.SpaceBodyContext body = ctx.spaceBody();

        int numNamespaces = body.spaceNamespace().size();
        if (numNamespaces > 1) {
            ECLog.logError(body, "Only one namespace declaration is allowed.");
        } else if (numNamespaces == 1) {
            EntityLanguageParser.SpaceNamespaceContext ns = body.spaceNamespace(0);
            space.setNamespace(
                    new MTNamespace(ns, segmentsForPathID(ns.namespaceIdent().id()).toArray(new String[0]), false));
        }

        List<EntityLanguageParser.SpaceImportContext> importContexts = body.spaceImport();

        setNodeDescription(space, body.descriptionStatement(), false);
        space.addTagsWithValues(tagStringsFromTagStatements(body.tagStatement()));

        if (body.spaceMetadata().size() > 1) {
            ECLog.logFatal(ctx, "Only one instance of metadata allowed here.");
        }
        if (body.spaceMetadata().size() == 1) {
            JsonObject metadataObject = visitJsonObj(body.spaceMetadata(0).jsonObj());
            space.setMetadata(metadataObject);
        }
//        for (EntityLanguageParser.SpaceImportContext importContext : importContexts) {
//            ECLog.logInfo(importContext, "Importing from here");
//            MTSpaceImport spaceImport = visitSpaceImport(importContext);
//            space.addImport(spaceImport);
//        }
//        for (EntityLanguageParser.SpaceIncludeContext includeContext : body.spaceInclude()) {
//            MTSpaceInclude spaceInclude = visitSpaceInclude(includeContext);
//            space.addInclude(spaceInclude);
//        }

//        ECLog.logInfo(ctx, "VISITING CHILDREN!!");
        visitChildren(ctx);
        return space;
    }

    @Override
    public Object visitDefaultTemplateConfig(EntityLanguageParser.DefaultTemplateConfigContext ctx) {
        JsonObject configData = visitJsonObj(ctx.jsonObj());
        currentConfiguration.setDefaultTemplateConfig(configData);
        return configData;
    }

    @Override
    public MTProtoc visitProtoc(EntityLanguageParser.ProtocContext ctx) {
        MTProtoc protoc = new MTProtoc(ctx, currentConfiguration);
        for (EntityLanguageParser.SpaceImportContext importContext : ctx.protocBody().spaceImport()) {
            protoc.addImport(visitSpaceImport(importContext));
        }
        for (EntityLanguageParser.SpaceIncludeContext includeContext : ctx.protocBody().spaceInclude()) {
            protoc.addInclude(visitSpaceInclude(includeContext));
        }
        for (EntityLanguageParser.OutputSpecContext outputSpecContext : ctx.protocBody().outputSpec()) {
            if (outputSpecContext.id().size() == 1) {
                protoc.setSourceOutputName(outputSpecContext.id(0).getText());
            } else if (outputSpecContext.id(0).getText().equals("source")) {
                protoc.setSourceOutputName(outputSpecContext.id(1).getText());
            } else if (outputSpecContext.id(0).getText().equals("header")) {
                protoc.setHeaderOutputName(outputSpecContext.id(1).getText());
            }
        }
        if (ctx.protocBody().protocLanguage().size() > 0) {
            protoc.setLanguageName(ctx.protocBody().protocLanguage(0).id().getText());
        }
        if (EntityCompiler.isVerbose()) {
            ECLog.logInfo("Made Protoc with language: " + protoc.getLanguageName());
        }
        currentConfiguration.addProtoc(protoc);
        return protoc;
    }

    //
    // REPOSITORY
    //

    @Override
    public MTSpaceImport visitSpaceImport(EntityLanguageParser.SpaceImportContext ctx) {
        MTSpaceImport modelImport = new MTSpaceImport();
        for (EntityLanguageParser.IdContext idContext : ctx.idList().id()) {
            MTRepositoryImport repoImport     = new MTRepositoryImport(ctx, false);
            String             filename       = idContext.getText();
            String             repositoryName = idText(ctx.id());
            repoImport.setFilename(filename);
            repoImport.setRepositoryName(repositoryName);
            modelImport.addImport(repoImport);
        }
        currentSpace().addImport(modelImport);
        return modelImport;
    }

    @Override
    public MTSpaceInclude visitSpaceInclude(EntityLanguageParser.SpaceIncludeContext ctx) {
        MTSpaceInclude spaceInclude = new MTSpaceInclude();

        for (EntityLanguageParser.IdContext idContext : ctx.idList().id()) {
            MTRepositoryImport modelImport    = new MTRepositoryImport(ctx, true);
            String             filename       = idContext.getText();
            String             repositoryName = idText(ctx.id());
            modelImport.setFilename(filename);
            modelImport.setRepositoryName(repositoryName);
            spaceInclude.addImport(modelImport);
        }
        if (ctx.spaceIncludeBody() != null) {
            if (ctx.spaceIncludeBody().spaceIncludeImportEnum() != null) {
                for (EntityLanguageParser.SpaceIncludeImportEnumContext enumContext : ctx.spaceIncludeBody().spaceIncludeImportEnum()) {
                    for (EntityLanguageParser.IdContext idContext : enumContext.idList().id()) {
                        spaceInclude.addEnumName(idContext.getText());
                    }
                }
            }
            if (ctx.spaceIncludeBody().spaceIncludeImportEntity() != null) {
                for (EntityLanguageParser.SpaceIncludeImportEntityContext entityContext : ctx.spaceIncludeBody().spaceIncludeImportEntity()) {
                    for (EntityLanguageParser.IdContext idContext : entityContext.idList().id()) {
                        spaceInclude.addEntityName(idContext.getText());
                    }
                }
            }
        }
        currentSpace().addInclude(spaceInclude);
        return spaceInclude;
    }

    @Override
    public MTModule visitModule(EntityLanguageParser.ModuleContext ctx) {
        String   moduleName = ctx.id().getText();
        MTModule module     = currentSpace().getModuleWithName(moduleName);
        if (module == null) {
            module = new MTModule(ctx, currentSpace(), moduleName);
        }
        currentModule = module;
        setNodeDescription(currentModule, ctx.moduleBody().descriptionStatement(), false);
        currentModule.addTagsWithValues(tagStringsFromTagStatements(ctx.moduleBody().tagStatement()));
        visit(ctx.moduleBody());
        currentModule = null;
        return module;
    }

    private void setNodeDescription
            (MTNode node,
             List<EntityLanguageParser.DescriptionStatementContext> statementContexts,
             boolean append) {
        StringBuilder              mainBuilder    = new StringBuilder();
        StringBuilder              summaryBuilder = new StringBuilder();
        StringBuilder              detailBuilder  = new StringBuilder();
        Map<String, StringBuilder> builderMap     = new HashMap<>();
        builderMap.put("main", mainBuilder);
        builderMap.put("summary", summaryBuilder);
        builderMap.put("detail", detailBuilder);
        for (EntityLanguageParser.DescriptionStatementContext statementContext : statementContexts) {
            List<String> types = new ArrayList<>();
            if (statementContext.id().size() == 0) {
                types.add("main"); // default when none specified
            } else {
                types =
                        statementContext.id().stream()
                                        .map(EntityLanguageParser.IdContext::getText)
                                        .collect(Collectors.toList());
            }
            String lineWithQuotes = statementContext.STRING().getSymbol().getText();
            String line           = ECStringUtil.ProcessParserString(lineWithQuotes).trim();

            if (line.equals("")) {
                line = "\n\n";
            }

            for (String type : types) {
                if (!builderMap.containsKey(type)) {
                    ECLog.logFatal("Description type of \"" + type + "\" not supported.");
                }
                StringBuilder builder       = builderMap.get(type);
                int           builderLength = builder.length();
                if (builderLength > 0) {
                    char lastChar = builder.charAt(builderLength - 1);
                    if (lastChar != '\n' && line.charAt(0) != '\n') {
                        builder.append(" ");
                    }
                }
                builder.append(line);
            }
        }
        String mainString = mainBuilder.toString();
        if (!mainString.isEmpty()) {
            mainString = mainString.replace("\\\"", "\"");
            if (append) {
                node.appendDescription(mainString);
            } else {
                node.setDescription(mainString);
            }
        }
        String summaryString = summaryBuilder.toString();
        if (!summaryString.isEmpty()) {
            summaryString = summaryString.replace("\\\"", "\"");
            if (append) {
                node.appendSummary(summaryString);
            } else {
                node.setSummary(summaryString);
            }
        }
        String detailString = detailBuilder.toString();
        if (!detailString.isEmpty()) {
            detailString = detailString.replace("\\\"", "\"");
            if (append) {
                node.appendDetail(detailString);
            } else {
                node.setDetail(detailString);
            }
        }
    }

    private List<Vector<String>> tagStringsFromTagStatements(List<EntityLanguageParser.TagStatementContext> tagStatementContexts) {
        List<Vector<String>> tags = new ArrayList<>();
        for (EntityLanguageParser.TagStatementContext statementContext : tagStatementContexts) {
            for (EntityLanguageParser.TagItemContext tagItem : statementContext.tagItem()) {
                Vector<String> tagWithValue = new Vector<>();
                tagWithValue.add(ECStringUtil.ProcessParserString(tagItem.STRING(0).getText()));
                if (tagItem.STRING().size() > 1) {
                    tagWithValue.add(ECStringUtil.ProcessParserString(tagItem.STRING(1).getText()));
                }
                tags.add(tagWithValue);
            }
        }
        return tags;
    }

    @Override
    public MTEntity visitEntity(EntityLanguageParser.EntityContext ctx) {
        List<String>                                   templateArgs        = new ArrayList<>();
        String                                         entityName          = null;
        EntityLanguageParser.EntityTemplateDeclContext templateDeclContext = ctx.entityDecl().entityTemplateDecl();
        currentDomainEntity = null;

        if (templateDeclContext != null) {
            entityName = templateDeclContext.id(0).getText();
            for (int i = 1; i < templateDeclContext.id().size(); i++) {
                templateArgs.add(templateDeclContext.id(i).getText());
            }
            currentEntity = new MTEntityTemplate(ctx, currentModule, entityName, templateArgs);
        } else {
            entityName    = ctx.entityDecl().entityName().id().getText();
            currentEntity = new MTEntity(ctx, currentModule, entityName);
        }

        currentEntity.setExtern(ctx.entityDecl().EXTERN() != null);
        currentEntity.setTransient(ctx.entityDecl().TRANSIENT() != null);

        if (ctx.entityDecl().PRIMARY() != null) {
            currentEntity.setDeclaredAsPrimary(true);
        } else if (ctx.entityDecl().SECONDARY() != null) {
            currentEntity.setDeclaredAsSecondary(true);
        }

        EntityLanguageParser.EntityBodyContext body = ctx.entityBody();

        setNodeDescription(currentEntity, body.descriptionStatement(), false);
        currentEntity.addTagsWithValues(tagStringsFromTagStatements(body.tagStatement()));

        // Primary Key

        List<MTAttribute> primaryKeyAttributes = new ArrayList<>();

        // Single
        for (EntityLanguageParser.PrimarykeySingleContext singleContext : body.primarykeySingle()) {
            MTAttribute attribute = (MTAttribute) visit(singleContext.attribute());
            primaryKeyAttributes.add(attribute);
        }

        // Multiple
        for (EntityLanguageParser.PrimarykeyMultipleContext multipleContext : body.primarykeyMultiple()) {
            for (EntityLanguageParser.AttributeContext attributeContext : multipleContext.attributesBody().attribute()) {
                MTAttribute attribute = (MTAttribute) visit(attributeContext);
                primaryKeyAttributes.add(attribute);
            }
        }

        if (primaryKeyAttributes.size() > 0) {
            if (currentEntity.isDeclaredAsSecondary()) {
                ECLog.logError(ctx.entityDecl().entityName().id(),
                               "A secondary entity \"" + currentEntity.getName() + "\" cannot declare a primary key!");
            }
            MTPrimaryKey primaryKey = new MTPrimaryKey(ctx);
            for (MTAttribute attribute : primaryKeyAttributes) {
                primaryKey.addAttribute(attribute);
            }
            currentEntity.setPrimaryKey(primaryKey);
        } else {
            if (currentEntity.isDeclaredAsPrimary() && !currentEntity.isTransient()) {
                ECLog.logError(ctx.entityDecl().entityName().id(),
                               "A primary entity \"" + currentEntity.getName() + "\" must declare a primary key!");
            }
        }

        // Enums
        for (EntityLanguageParser.EnumStatementContext esc : body.enumStatement()) {
            MTEnum mtEnum = visitEnumStatement(esc);
            currentSpace().addEnum(mtEnum);
        }

        // Attributes
        for (EntityLanguageParser.AttributesContext attributesContext : body.attributes()) {
            for (EntityLanguageParser.AttributeContext attributeContext : attributesContext.attributesBody().attribute()) {
                MTAttribute attribute = (MTAttribute) visit(attributeContext);
                currentEntity.addAttribute(attribute);
            }
        }

        // Relationship with other entities
        for (EntityLanguageParser.RelationshipsContext relationshipsContext : body.relationships()) {
            for (EntityLanguageParser.RelationshipStatementContext relationshipStatementContext : relationshipsContext.relationshipsBody().relationshipStatement()) {
                MTRelationship relationship = (MTRelationship) visit(relationshipStatementContext);
                currentEntity.addRelationship(relationship);
            }
        }

        // Secondary Entity Instantiations
//        for (EntityLanguageParser.SecondaryEntitiesContext secondaryEntitiesContext : body.secondaryEntities()) {
//            for (EntityLanguageParser.SecondaryEntityInstantiationContext instantiationContext : secondaryEntitiesContext.secondaryEntitiesBody().secondaryEntityInstantiation()) {
//                MTEntityInstantiation instantiation = (MTEntityInstantiation) visit(instantiationContext);
//                currentEntity.addSecondaryEntityInstantiation(instantiation);
//            }
//        }

        if (currentModule != null) {
            currentModule.addEntity(currentEntity);
        }
        currentSpace().addEntity(currentEntity);

        MTEntity returnEntity = currentEntity;
        currentEntity = null;

        return returnEntity;
    }

    @Override
    public MTEnum visitEnumStatement(EntityLanguageParser.EnumStatementContext ctx) {
        String  enumName = ctx.id().getText();
        boolean extern   = ctx.EXTERN() != null;
        if (extern) {
            MTEnum existingEnum = currentSpace().getEnumWithName(enumName);
            if (existingEnum != null) {
                return null; // ignore the extern one if the actual one exists.
            }
        }
        MTEnum mtEnum = new MTEnum(ctx, currentModule, currentEntity, ctx.id().getText());
        mtEnum.setExtern(extern);
        if (ctx.descriptionStatement() != null && ctx.descriptionStatement().size() > 0) {
            setNodeDescription(mtEnum, ctx.descriptionStatement(), false);
        }
        mtEnum.addTagsWithValues(tagStringsFromTagStatements(ctx.tagStatement()));
        for (EntityLanguageParser.EnumItemContext ic : ctx.enumItem()) {
            String     itemName   = ic.id().getText();
            Integer    itemNumber = Integer.valueOf(ic.INTEGER().getSymbol().getText());
            MTEnumItem mtEnumItem = mtEnum.addItem(ic, itemName, itemNumber);
            if (ic.enumItemBody() != null && ic.enumItemBody().tagStatement() != null) {
                mtEnumItem.addTagsWithValues(tagStringsFromTagStatements(ic.enumItemBody().tagStatement()));
            }
            if (ic.enumItemBody() != null && ic.enumItemBody().descriptionStatement() != null
                && ic.enumItemBody().descriptionStatement().size() > 0) {
                setNodeDescription(mtEnumItem, ic.enumItemBody().descriptionStatement(), false);
            }
        }
        if (currentModule != null) {
            //ECLog.logInfo("Adding " + (extern ? "extern " : "") + "enum \"" + mtEnum.getName() + "\" to module: " + currentModule.getName());
            currentModule.addEnum(mtEnum);
        }
        if (currentEntity != null) {
            currentEntity.addEnum(mtEnum);
        }
        currentSpace().addEnum(mtEnum);
        return mtEnum;
    }

    @Override
    public MTDView visitView(EntityLanguageParser.ViewContext ctx) {
        String  currentEntityName = currentDomainEntity != null ?
                                    currentDomainEntity.getEntityName() :
                                    null;
        MTDView domainView        = new MTDView(ctx, currentDomain, currentEntityName, ctx.id().getText());
        // , ctx.DEFAULT() != null
        if (ctx.viewBlock() != null) {
            EntityLanguageParser.ViewBlockContext viewBlockContext = ctx.viewBlock();
            if (viewBlockContext.descriptionStatement() != null) {
                setNodeDescription(domainView, viewBlockContext.descriptionStatement(), false);
            }
            domainView.addTagsWithValues(tagStringsFromTagStatements(viewBlockContext.tagStatement()));

            // ATTRIBUTES
            if (viewBlockContext.viewAttributes() != null && viewBlockContext.viewAttributes().size() > 0) {
                if (viewBlockContext.viewAttributes().size() > 1) {
                    ECLog.logFatal(viewBlockContext.viewAttributes(1),
                                   "Only specify one attributes section of a view.");
                }
                // INCLUDE
                EntityLanguageParser.ViewAttributesContext attributesContext = viewBlockContext.viewAttributes(0);
                for (EntityLanguageParser.ViewAttributeIncludeContext vaiContext : attributesContext.viewAttributesBlock().viewAttributeInclude()) {
                    boolean secondaryEntities = vaiContext.SECONDARY() != null && vaiContext.ENTITIES() != null;
                    if (vaiContext.TAGGED() != null) {
                        // TAGS
                        for (MTTagSet tagSet : visitViewTaggedList(vaiContext.viewTaggedList())) {
                            if (secondaryEntities) {
                                domainView.addIncludedAttributeSecondaryEntityTagSet(tagSet);
                            } else {
                                domainView.addIncludedAttributeTagSet(tagSet);
                            }
                        }
                    } else if (vaiContext.ARRAY() != null) {
                        domainView.setIncludedArrayAttributes(true);
                    } else if (vaiContext.CREATION() != null) {
                        domainView.setIncludedCreationAttributes(true);
                    } else if (vaiContext.MODIFICATION() != null) {
                        domainView.setIncludedModificationAttributes(true);
                    } else if (vaiContext.viewIdentifierList() != null && !vaiContext.viewIdentifierList().isEmpty()) {
                        for (EntityLanguageParser.IdContext attrName : vaiContext.viewIdentifierList().id()) {
                            domainView.addIncludedAttributeName(attrName.getText());
                        }
                    } else {
                        domainView.setIncludedAllAttributes(true);
                    }
                }
                // EXCLUDE
                for (EntityLanguageParser.ViewAttributeExcludeContext vaeContext : attributesContext.viewAttributesBlock().viewAttributeExclude()) {
                    boolean secondaryEntities = vaeContext.SECONDARY() != null && vaeContext.ENTITIES() != null;
                    if (vaeContext.TAGGED() != null) {
                        // TAGS
                        for (MTTagSet tagSet : visitViewTaggedList(vaeContext.viewTaggedList())) {
                            if (secondaryEntities) {
                                domainView.addExcludedAttributeSecondaryEntityTagSet(tagSet);
                            } else {
                                domainView.addExcludedAttributeTagSet(tagSet);
                            }
                        }
                    } else if (vaeContext.ARRAY() != null) {
                        domainView.setExcludedArrayAttributes(true);
                    } else if (vaeContext.CREATION() != null) {
                        domainView.setExcludedCreationAttributes(true);
                    } else if (vaeContext.MODIFICATION() != null) {
                        domainView.setExcludedModificationAttributes(true);
                    } else if (vaeContext.viewIdentifierList() != null && !vaeContext.viewIdentifierList().isEmpty()) {
                        for (EntityLanguageParser.IdContext attrName : vaeContext.viewIdentifierList().id()) {
                            domainView.addExcludedAttributeName(attrName.getText());
                        }
                    } else {
                        domainView.setExcludedAllAttributes(true);
                    }
                }
            }
            // RELATIONSHIPS
            if (viewBlockContext.viewRelationships() != null && viewBlockContext.viewRelationships().size() > 0) {
                if (viewBlockContext.viewRelationships().size() > 1) {
                    ECLog.logFatal(viewBlockContext.viewRelationships(1),
                                   "Only specify one relationships section of a view.");
                }
                // INCLUDE
                EntityLanguageParser.ViewRelationshipsContext relationshipsContext = viewBlockContext.viewRelationships(
                        0);
                for (EntityLanguageParser.ViewRelationshipIncludeContext vriContext : relationshipsContext.viewRelationshipsBlock().viewRelationshipInclude()) {
                    boolean hasAs                     = vriContext.AS() != null;
                    boolean hasWithView               = vriContext.WITH() != null && vriContext.VIEW() != null;
                    boolean entityRef                 = vriContext.ENTITY() != null;
                    int     usedIdCount               = (hasAs ?
                                                         1 :
                                                         0) + (hasWithView ?
                                                               1 :
                                                               0);
                    boolean hasEntityOrRelationshipId = vriContext.id().size() > usedIdCount;
                    int     viewIdIndex               = hasEntityOrRelationshipId ?
                                                        1 :
                                                        0;
                    String  relationshipOrEntityName  = hasEntityOrRelationshipId ?
                                                        vriContext.id(0).getText() :
                                                        null;

                    if (vriContext.TAGGED() != null) {
                        for (MTTagSet tagSet : visitViewTaggedList(vriContext.viewTaggedList())) {
                            domainView.addIncludedRelationshipTagSet(tagSet);
                        }
                    } else if (!hasEntityOrRelationshipId) {
                        if (vriContext.PARENT() != null) {
                            domainView.setIncludedParentRelationships(true);
                        } else if (vriContext.TOONE() != null) {
                            domainView.setIncludedOneRelationships(true);
                        } else if (vriContext.TOMANY() != null) {
                            domainView.setIncludedManyRelationships(true);
                        }
                    } else {

                        if (entityRef) {
                            domainView.addIncludedRelationshipEntityName(relationshipOrEntityName);
                            if (vriContext.AS() != null) {
                                domainView.renameRelationshipOfEntityName(relationshipOrEntityName,
                                                                          vriContext.id(1).getText());
                                viewIdIndex++;
                            }
                        } else {
                            domainView.addIncludedRelationshipName(relationshipOrEntityName);
                            if (vriContext.AS() != null) {
                                domainView.renameRelationship(relationshipOrEntityName, vriContext.id(1).getText());
                                viewIdIndex++;
                            }
                        }
                    }
                    if (vriContext.WITH() != null) {
                        if (vriContext.VIEW() != null) {
                            String viewName = vriContext.id(viewIdIndex).getText();
                            if (relationshipOrEntityName == null) {
                                domainView.setRelationshipWithViewName(viewName);
                            } else if (entityRef) {
                                domainView.setViewNameForRelationshipEntityName(relationshipOrEntityName, viewName);
                            } else {
                                domainView.setViewNameForRelationshipName(relationshipOrEntityName, viewName);
                            }
                        } else if (vriContext.PRIMARYKEY() != null) {
                            if (relationshipOrEntityName == null) {
                                domainView.setRelationshipWithPrimaryKey(true);
                            } else if (entityRef) {
                                domainView.setPrimaryKeyForRelationshipEntityName(relationshipOrEntityName);
                            } else {
                                domainView.setPrimaryKeyForRelationshipName(relationshipOrEntityName);
                            }
                        }
                    }
                }
                // EXCLUDE
                for (EntityLanguageParser.ViewRelationshipExcludeContext vreContext : relationshipsContext.viewRelationshipsBlock().viewRelationshipExclude()) {
                    boolean hasEntityOrRelationshipId = vreContext.id() != null;
                    if (vreContext.TAGGED() != null) {
                        for (MTTagSet tagSet : visitViewTaggedList(vreContext.viewTaggedList())) {
                            domainView.addExcludedRelationshipTagSet(tagSet);
                        }
                    } else if (!hasEntityOrRelationshipId) {
                        if (vreContext.PARENT() != null) {
                            domainView.setExcludedParentRelationships(true);
                        } else if (vreContext.TOONE() != null) {
                            domainView.setExcludedOneRelationships(true);
                        } else if (vreContext.TOMANY() != null) {
                            domainView.setExcludedManyRelationships(true);
                        } else {
                            domainView.setExcludedAllRelationships(true);
                        }
                    } else if (vreContext.TOONE() != null || vreContext.TOMANY() != null
                               || vreContext.PARENT() != null) {
                        boolean entityRef = vreContext.ENTITY() != null;

                        String relationshipOrEntityName = vreContext.id().getText();
                        if (entityRef) {
                            domainView.addExcludedRelationshipEntityName(relationshipOrEntityName);
                        } else {
                            domainView.addExcludedRelationshipName(relationshipOrEntityName);
                        }
                    } else {
                        domainView.setExcludedAllRelationships(true);
                    }
                }
            }
        }
        return domainView;
    }

    @Override
    public List<MTTagSet> visitViewTaggedList(EntityLanguageParser.ViewTaggedListContext ctx) {
        List<MTTagSet> tagSets = new ArrayList<>();
        for (EntityLanguageParser.ViewTaggedListItemContext itemContext : ctx.viewTaggedListItem()) {
            tagSets.add(visitViewTaggedListItem(itemContext));
        }
        return tagSets;
    }

    @Override
    public MTTagSet visitViewTaggedListItem(EntityLanguageParser.ViewTaggedListItemContext ctx) {
        MTTagSet tagSet = new MTTagSet(ctx);
        for (TerminalNode tagNode : ctx.STRING()) {
            tagSet.addTag(ECStringUtil.ProcessParserString(tagNode.getText()));
        }
        return tagSet;
    }

    @Override
    public MTRelationship visitRelationshipStatement(EntityLanguageParser.RelationshipStatementContext ctx) {
        HalfRelationshipPlurality toPlurality = HalfRelationshipPlurality.ONE;
        if (ctx.MANY() != null) {
            toPlurality = HalfRelationshipPlurality.MANY;
        }
        String                        fromEntityName   = currentEntity.getName();
        String                        toEntityName     = null;
        String                        relationshipName = null;
        MTEntityTemplateInstantiation instantiation    = null;
        if (ctx.relationshipTemplateAs() != null) {
            instantiation = new MTEntityTemplateInstantiation(ctx.relationshipTemplateAs(),
                                                              ctx.relationshipTemplateAs().id().getText());
            for (EntityLanguageParser.RelationshipTemplateArgContext argContext : ctx.relationshipTemplateAs().relationshipTemplateArg()) {
                instantiation.addTemplateArgEntityName(argContext.id().getText(), argContext.UNIQUE() != null);
            }
        }
        toEntityName = ctx.id(0).getText();
        if (ctx.id().size() == 1) {
            relationshipName = ECStringUtil.Uncapitalize(toEntityName);
        } else {
            relationshipName = ctx.id(1).getText();
        }

        boolean optional    = ctx.OPTIONAL() != null;
        boolean parent      = ctx.PARENT() != null;
        String  reverseName = null;

        if (ctx.relationshipReverseName() != null && ctx.relationshipReverseName().id() != null) {
            reverseName = ctx.relationshipReverseName().id().getText();
        }

        String toEntityIdName = null;
        if (ctx.relationshipIdName() != null && ctx.relationshipIdName().id() != null) {
            toEntityIdName = ctx.relationshipIdName().id().getText();
        }

        MTRelationship relationship = new MTRelationship(ctx, relationshipName, fromEntityName, toPlurality,
                                                         toEntityName, optional, parent, reverseName, toEntityIdName,
                                                         null);

        if (instantiation != null) {
            relationship.getTo().setTemplateInstantiation(instantiation);
        }

        if (ctx.relationshipBody() != null) {
            setNodeDescription(relationship, ctx.relationshipBody().descriptionStatement(), false);
            relationship.addTagsWithValues(tagStringsFromTagStatements(ctx.relationshipBody().tagStatement()));
        }

        return relationship;
    }

    @Override
    public MTAttributeConstraint visitAttributeConstraint(EntityLanguageParser.AttributeConstraintContext ctx) {
        String                                              constraintName = ctx.id().getText();
        EntityLanguageParser.AttributeConstraintBodyContext block          = ctx.attributeConstraintBody();
        if (block == null || block.expression().size() == 0) {
            ECLog.logFatal("Attribute constraints must have an expression. Constraint " + currentEntity.getName() + "."
                           + currentAttribute.getName() + "." + constraintName + " does not.");
        }
        if (ctx.attributeConstraintBody().expression().size() > 1) {
            ECLog.logFatal(
                    "Attribute constraints can only have one expression. Constraint " + currentEntity.getName() + "."
                    + currentAttribute.getName() + "." + constraintName + " is specified with "
                    + ctx.attributeConstraintBody().expression().size() + " expressions: "
                    + ctx.attributeConstraintBody().expression().toString());
        }
        MTAttributeConstraint constraint = new MTAttributeConstraint(ctx, currentAttribute, constraintName,
                                                                     visitExpression(block.expression(0)));

        if (block.descriptionStatement() != null) {
            setNodeDescription(constraint, block.descriptionStatement(), false);
        }

        if (block.tagStatement() != null) {
            constraint.addTagsWithValues(tagStringsFromTagStatements(block.tagStatement()));
        }

        return constraint;
    }

    @Override
    public MTDomain visitDomain(EntityLanguageParser.DomainContext ctx) {
        String  name                = ctx.ID(0).getText();
        boolean extending           = ctx.EXTENDS() != null;
        boolean specializing        = !extending && (ctx.ID().size() > 1);
        String  extendingDomainName = extending ?
                                      ctx.ID(1).getText() :
                                      null;
        String  specializingName    = specializing ?
                                      ctx.ID(1).getText() :
                                      null;

        if (currentSpace().getDomainWithName(name) != null) {
            currentDomain = currentSpace().getDomainWithName(name);
        } else {
            currentDomain = new MTDomain(ctx, currentSpace(), name, extendingDomainName);
        }
        if (specializingName != null) {
            currentDomain.addSpecializedAsName(specializingName);
        }

        EntityLanguageParser.DomainBodyContext block = ctx.domainBody();

        setNodeDescription(currentDomain, block.descriptionStatement(), specializing);

        if (block.tagStatement() != null) {
            currentDomain.addTagsWithValues(tagStringsFromTagStatements(block.tagStatement()));
        }

        List<EntityLanguageParser.DomainTaggingContext> taggingContexts = block.domainTagging();
        for (EntityLanguageParser.DomainTaggingContext taggingContext : taggingContexts) {
            visit(taggingContext);
        }
        List<EntityLanguageParser.DomainNamingContext> namingContexts = block.domainNaming();
        for (EntityLanguageParser.DomainNamingContext namingContext : namingContexts) {
            visit(namingContext);
        }

        List<EntityLanguageParser.DomainEnumContext> domainEnumContexts = block.domainEnum();
        for (EntityLanguageParser.DomainEnumContext domainEnumContext : domainEnumContexts) {
            visit(domainEnumContext);
        }

        List<EntityLanguageParser.DomainEntityContext> domainEntityContexts = block.domainEntity();
        for (EntityLanguageParser.DomainEntityContext domainEntityContext : domainEntityContexts) {
            visit(domainEntityContext);
        }

        List<EntityLanguageParser.DomainViewContext> domainViewContexts = block.domainView();
        for (EntityLanguageParser.DomainViewContext domainViewContext : domainViewContexts) {
            currentDomain.addDomainView(visitView(domainViewContext.view()));
        }

        List<EntityLanguageParser.DomainModuleContext> domainModuleContexts = block.domainModule();
        for (EntityLanguageParser.DomainModuleContext domainModuleContext : domainModuleContexts) {
            visit(domainModuleContext);
        }

        List<EntityLanguageParser.DomainAttributesContext> domainAttributesContexts = block.domainAttributes();
        for (EntityLanguageParser.DomainAttributesContext domainAttributesContext : domainAttributesContexts) {
            visit(domainAttributesContext);
        }

        int numNamespaces = block.domainNamespace().size();
        if (numNamespaces > 1) {
            ECLog.logError(block, "Only one namespace declaration is allowed.");
        } else if (numNamespaces == 1) {
            EntityLanguageParser.DomainNamespaceContext ns = block.domainNamespace(0);
            currentDomain.setNamespace(
                    new MTNamespace(ns, segmentsForPathID(ns.namespaceIdent().id()).toArray(new String[0]),
                                    ns.SPACE() != null));
        }

        if (block.domainFlattenSecondaryEntities() != null
            && block.domainFlattenSecondaryEntities().size() > 0) {
            currentDomain.setFlattenSecondaryEntities(true);
        }

        for (EntityLanguageParser.DomainApplyTemplateContext context : block.domainApplyTemplate()) {
            if (currentDomain.getApplyTemplate() != null) {
                ECLog.logFatal(context, "Only one template can be applied to a single domain.");
            }
            currentDomain.setApplyTemplate(visitDomainApplyTemplate(context));
        }

        return currentDomain;
    }

    private List<String> segmentsForPathID(List<EntityLanguageParser.IdContext> nodes) {
        ArrayList<String> segments = new ArrayList<>();
        for (EntityLanguageParser.IdContext node : nodes) {
            segments.add(node.getText());
        }
        return segments;
    }

    @Override
    public MTDApplyTemplate visitDomainApplyTemplate(EntityLanguageParser.DomainApplyTemplateContext ctx) {
        MTDApplyTemplate                                    applyTemplate = new MTDApplyTemplate(ctx, currentDomain,
                                                                                                 ctx.id().getText());
        EntityLanguageParser.DomainApplyTemplateBodyContext body          = ctx.domainApplyTemplateBody();
        if (body != null) {
            if (body.descriptionStatement() != null) {
                setNodeDescription(applyTemplate, body.descriptionStatement(), false);
            }
            if (body.tagStatement() != null) {
                applyTemplate.addTagsWithValues(tagStringsFromTagStatements(body.tagStatement()));
            }
            if (body.templateConfig() != null && body.templateConfig().size() > 0) {
                EntityLanguageParser.TemplateConfigContext configContext = body.templateConfig().get(0);
                applyTemplate.setConfig(visitJsonObj(configContext.jsonObj()));
            }
        }
        return applyTemplate;
    }

    @Override
    public JsonObject visitJsonObj(EntityLanguageParser.JsonObjContext ctx) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        for (EntityLanguageParser.JsonPairContext context : ctx.jsonPair()) {
            String name  = ECStringUtil.ProcessParserString(context.STRING().getText());
            Object value = visitJsonValue(context.jsonValue());
            if (value instanceof JsonArray) {
                builder.add(name, (JsonArray) value);
            } else if (value instanceof JsonObject) {
                builder.add(name, (JsonObject) value);
            } else if (value instanceof JsonValue) {
                builder.add(name, (JsonValue) value);
            } else if (value instanceof Long) {
                builder.add(name, ((Long) value).longValue());
            } else if (value instanceof Double) {
                builder.add(name, ((Double) value).doubleValue());
            } else if (value instanceof String) {
                builder.add(name, (String) value);
            }
        }
        return builder.build();
    }

    @Override
    public Object visitJsonValue(EntityLanguageParser.JsonValueContext ctx) {
        if (ctx.jsonArr() != null) {
            return visitJsonArr(ctx.jsonArr());
        } else if (ctx.jsonObj() != null) {
            return visitJsonObj(ctx.jsonObj());
        } else if (ctx.STRING() != null) {
            return ECStringUtil.ProcessParserString(ctx.STRING().getText());
        } else if (ctx.FLOAT() != null) {
            return Double.valueOf(ctx.FLOAT().getText());
        } else if (ctx.INTEGER() != null) {
            return Long.valueOf(ctx.INTEGER().getText());
        } else if (ctx.getText().equals("true")) {
            return JsonValue.TRUE;
        } else if (ctx.getText().equals("false")) {
            return JsonValue.FALSE;
        } else if (ctx.getText().equals("null")) {
            return JsonValue.NULL;
        }
        ECLog.logFatal(ctx, "Specified Json value is not supported: " + ctx.getText());
        return null;
    }

    @Override
    public Object visitDomainTagging(EntityLanguageParser.DomainTaggingContext ctx) {
        for (EntityLanguageParser.DomainTaggingTagContext taggingTagContext : ctx.domainTaggingTag()) {
            MTTagDef     tagDef     = visitDomainTaggingTag(taggingTagContext);
            String       context    = ctx.id().getText();
            MTTagContext tagContext = null;
            if (context.equals("entity")) {
                tagContext = MTTagContext.ENTITY;
            } else if (context.equals("attribute")) {
                tagContext = MTTagContext.ATTRIBUTE;
            } else if (context.equals("relationship")) {
                tagContext = MTTagContext.RELATIONSHIP;
            } else if (context.equals("domain")) {
                tagContext = MTTagContext.DOMAIN;
            } else if (context.equals("enum")) {
                tagContext = MTTagContext.ENUM;
            } else if (context.equals("enum_item")) {
                tagContext = MTTagContext.ENUM_ITEM;
            }
            tagDef.setTagContext(tagContext);
            currentDomain.addTagDef(tagDef);
        }
        return null;
    }

    @Override
    public MTTagDef visitDomainTaggingTag(EntityLanguageParser.DomainTaggingTagContext ctx) {
        boolean startsWith = ctx.STARTSWITH() != null;
        MTTagDef tagDef = new MTTagDef(ctx, ECStringUtil.ProcessParserString(ctx.STRING().getText()), startsWith);
        if (ctx.descriptionStatement() != null) {
            setNodeDescription(tagDef, ctx.descriptionStatement(), false);
        }
        if (ctx.tagStatement() != null) {
            tagDef.addTagsWithValues(tagStringsFromTagStatements(ctx.tagStatement()));
        }
        if (!ctx.domainTaggingTagValue().isEmpty()) {
            if (ctx.domainTaggingTagValue().size() > 1) {
                ECLog.logFatal(ctx.domainTaggingTagValue(1),
                               "A tag definition can only have at most one value definition.");
            }
            EntityLanguageParser.DomainTaggingTagValueContext valueContext = ctx.domainTaggingTagValue(0);
            MTNativeType.DataType                             dataType     = MTNativeType.DataType.FromName(
                    valueContext.domainTaggingTagValueType().getText());
            MTTagValueDef                                     tagValueDef  = new MTTagValueDef(valueContext, dataType);
            if (valueContext.descriptionStatement() != null) {
                setNodeDescription(tagValueDef, valueContext.descriptionStatement(), false);
            }
            if (valueContext.tagStatement() != null) {
                tagValueDef.addTagsWithValues(tagStringsFromTagStatements(valueContext.tagStatement()));
            }
            tagDef.setValueDef(tagValueDef);
        }
        return tagDef;
    }

    @Override
    public Object visitDomainNaming(EntityLanguageParser.DomainNamingContext ctx) {

        MTNamingMethod method         = null;
        String         prefix         = null;
        String         suffix         = null;
        String         primaryKeyName = null;
        Boolean        withUnits      = null;

        for (EntityLanguageParser.DomainNamingMethodContext methodContext : ctx.domainNamingBody().domainNamingMethod()) {
            method = MTNamingMethod.fromName(methodContext.ID().getSymbol().getText());
        }
        for (EntityLanguageParser.DomainNamingPrefixContext prefixContext : ctx.domainNamingBody().domainNamingPrefix()) {
            prefix = ECStringUtil.ProcessParserString(prefixContext.STRING().getText());
        }

        for (EntityLanguageParser.DomainNamingSuffixContext suffixContext : ctx.domainNamingBody().domainNamingSuffix()) {
            suffix = ECStringUtil.ProcessParserString(suffixContext.STRING().getText());
        }

        for (EntityLanguageParser.DomainNamingPrimaryKeyContext pkContext : ctx.domainNamingBody().domainNamingPrimaryKey()) {
            primaryKeyName = pkContext.ID().getSymbol().getText();
        }

        if (ctx.domainNamingBody().domainNamingWithoutUnits() != null
            && ctx.domainNamingBody().domainNamingWithoutUnits().size() > 0) {
            withUnits = false;
        }
        if (ctx.domainNamingBody().domainNamingWithUnits() != null
            && ctx.domainNamingBody().domainNamingWithUnits().size() > 0) {
            withUnits = true;
        }

        List<Class> classesWithNaming = new ArrayList<>();
        for (EntityLanguageParser.NamingClassContext namingClassContext : ctx.namingClass()) {
            if (namingClassContext.SPACE() != null) {
                classesWithNaming.add(MTSpace.class);
            } else if (namingClassContext.MODULE() != null) {
                classesWithNaming.add(MTModule.class);
            } else if (namingClassContext.ENTITY() != null) {
                classesWithNaming.add(MTEntity.class);
                classesWithNaming.add(MTView.class);
            } else if (namingClassContext.ATTRIBUTE() != null) {
                classesWithNaming.add(MTAttribute.class);
            } else if (namingClassContext.RELATIONSHIP() != null) {
                classesWithNaming.add(MTRelationship.class);
            } else if (namingClassContext.ENUM() != null) {
                classesWithNaming.add(MTEnum.class);
            } else if (namingClassContext.ENUMITEM() != null) {
                classesWithNaming.add(MTEnumItem.class);
            } else if (namingClassContext.TYPEDEF() != null) {
                classesWithNaming.add(MTTypedef.class);
            } else {
                ECLog.logError("That naming class is not supported: " + namingClassContext.getText());
            }
        }

        MTNaming naming = new MTNaming(method, prefix, suffix);
        if (withUnits != null) {
            naming.setUseUnitInNaming(withUnits);
        }

        for (Class classWithNaming : classesWithNaming) {
            currentDomain.registerNamingForClass(naming, classWithNaming);

            if (classWithNaming == MTAttribute.class && primaryKeyName != null) {
                currentDomain.setPrimaryKeyName(primaryKeyName);
            }
        }
        return null;
    }

    @Override
    public MTDModule visitDomainModule(EntityLanguageParser.DomainModuleContext ctx) {
        MTDModule domainModule = new MTDModule(ctx, currentDomain, ctx.ID().getText());
        currentDomain.addDomainModule(domainModule);
        currentDomainModule = domainModule;
        EntityLanguageParser.DomainModuleBodyContext block = ctx.domainModuleBody();

        if (block != null) {

            if (block.descriptionStatement() != null) {
                setNodeDescription(currentDomainModule, block.descriptionStatement(), false);
            }

            if (block.tagStatement() != null) {
                currentDomainModule.addTagsWithValues(tagStringsFromTagStatements(block.tagStatement()));
            }

            if (block.domainModuleApplyTemplate() != null && block.domainModuleApplyTemplate().size() > 0) {
                //ECLog.logInfo(ctx, "FOUND domain module APPLY TEMPLATE!");
                if (block.domainModuleApplyTemplate().size() != 1) {
                    ECLog.logFatal("Domain module can only have one applied template.");
                }
                MTDApplyTemplate applyTemplate = visitDomainModuleApplyTemplate(
                        block.domainModuleApplyTemplate().get(0));
                currentDomainModule.setApplyTemplate(applyTemplate);
            }
        }
        return domainModule;
    }

    @Override
    public Object visitDomainEntity(EntityLanguageParser.DomainEntityContext ctx) {
        List<TerminalNode> ids = ctx.ID();

        String currentDomainEntityName = ids.get(0).getSymbol().getText();
        this.currentDomainEntity = new MTDEntity(ctx, this.currentDomain, currentDomainEntityName);
        this.currentDomain.addDomainEntity(currentDomainEntity, true);

        if (ids.size() > 1) {
            String explicitEntityName = ids.get(1).getSymbol().getText();
            this.currentDomainEntity.setExplicitName(explicitEntityName);
        }

        EntityLanguageParser.DomainEntityBodyContext block = ctx.domainEntityBody();

        if (block != null) {

            if (block.descriptionStatement() != null) {
                setNodeDescription(currentDomainEntity, block.descriptionStatement(), false);
            }

            if (block.tagStatement() != null) {
                currentDomainEntity.addTagsWithValues(tagStringsFromTagStatements(block.tagStatement()));
            }

            for (EntityLanguageParser.DomainAttributesContext domainAttributesContext : block.domainAttributes()) {
                visit(domainAttributesContext);
            }

            for (EntityLanguageParser.DomainRelationshipsContext domainRelationshipsContext : block.domainRelationships()) {
                visit(domainRelationshipsContext);
            }

            for (EntityLanguageParser.ViewContext viewContext : block.view()) {
                currentDomainEntity.addDomainView(visitView(viewContext));
            }

            for (EntityLanguageParser.DomainInterfaceStatementContext interfaceStatementContext : block.domainInterfaceStatement()) {
                MTDEInterface mtInterface = visitDomainInterfaceStatement(interfaceStatementContext);
                currentDomainEntity.addInterface(mtInterface);
            }

            for (EntityLanguageParser.DomainEntityApplyTemplateContext context : block.domainEntityApplyTemplate()) {
                if (currentDomainEntity.getApplyTemplate() != null) {
                    ECLog.logFatal(context, "Only one template can be applied to a single domain entity.");
                }
                currentDomainEntity.setApplyTemplate(visitDomainEntityApplyTemplate(context));
            }
        }
        currentDomainEntity = null;
        return null;
    }

    @Override
    public Object visitDomainEnum(EntityLanguageParser.DomainEnumContext ctx) {

        MTNamingMethod method = null;
        String         prefix = null;
        String         suffix = null;

        String currentDomainEnumName = ctx.ID().getText();
        this.currentDomainEnum = new MTDEnum(ctx, this.currentDomain, currentDomainEnumName);
        this.currentDomain.addDomainEnum(currentDomainEnum);

        EntityLanguageParser.DomainEnumBodyContext block = ctx.domainEnumBody();

        if (block != null) {

            if (block.descriptionStatement() != null) {
                setNodeDescription(currentDomainEnum, block.descriptionStatement(), false);
            }

            if (block.tagStatement() != null) {
                currentDomainEnum.addTagsWithValues(tagStringsFromTagStatements(block.tagStatement()));
            }

            if (block.domainEnumItemRenameTo() != null) {
                for(EntityLanguageParser.DomainEnumItemRenameToContext renameToContext : block.domainEnumItemRenameTo()) {
                    MTDEnumItem domainEnumItem = currentDomainEnum.getDomainEnumItem(renameToContext.id(0).getText(), true);
                    domainEnumItem.setExplicitName(renameToContext.id(1).getText());
                }
            }

            for (EntityLanguageParser.DomainEnumItemContext itemContext : block.domainEnumItem()) {
                String      itemName    = itemContext.ID().getText();
                MTDEnumItem mtdEnumItem = null;
                if (currentDomainEnum.getEnum() != null) {
                    MTEnumItem enumItem = currentDomainEnum.getEnum().getItemByName(itemName);
                    mtdEnumItem = currentDomainEnum.getDomainEnumItem(enumItem, true);
                } else {
                    mtdEnumItem = currentDomainEnum.getDomainEnumItem(itemName, true);
                }

                EntityLanguageParser.DomainEnumItemBodyContext itemBodyContext = itemContext.domainEnumItemBody();
                if (itemBodyContext != null) {
                    if (itemBodyContext.descriptionStatement() != null) {
                        setNodeDescription(mtdEnumItem, itemBodyContext.descriptionStatement(), false);
                    }

                    if (itemBodyContext.tagStatement() != null) {
                        mtdEnumItem.addTagsWithValues(tagStringsFromTagStatements(itemBodyContext.tagStatement()));
                    }
                }
            }

            EntityLanguageParser.DomainEnumNamingItemContext namingItemContext = block.domainEnumNamingItem(0);
            if (namingItemContext != null) {

                for (EntityLanguageParser.DomainNamingMethodContext methodContext : namingItemContext.domainEnumNamingBody().domainNamingMethod()) {
                    method = MTNamingMethod.fromName(methodContext.ID().getSymbol().getText());
                }
                for (EntityLanguageParser.DomainNamingPrefixContext prefixContext : namingItemContext.domainEnumNamingBody().domainNamingPrefix()) {
                    prefix = ECStringUtil.ProcessParserString(prefixContext.STRING().getText());
                }

                for (EntityLanguageParser.DomainNamingSuffixContext suffixContext : namingItemContext.domainEnumNamingBody().domainNamingSuffix()) {
                    suffix = ECStringUtil.ProcessParserString(suffixContext.STRING().getText());
                }
                MTNaming naming = new MTNaming(method, prefix, suffix);
                currentDomain.setDomainNamingToEnum(ctx.ID().getText(), naming);
            }
        }
        return null;
    }

    @Override
    public MTDEInterface visitDomainInterfaceStatement(EntityLanguageParser.DomainInterfaceStatementContext ctx) {

        currentDomainEntityInterface = new MTDEInterface(ctx, currentDomain, currentDomainEntity, ctx.id().getText());

        EntityLanguageParser.DomainInterfaceBodyContext block = ctx.domainInterfaceBody();

        if (block != null && block.descriptionStatement() != null) {
            setNodeDescription(currentDomainEntityInterface, block.descriptionStatement(), false);
        }

        for (EntityLanguageParser.DomainOperationContext operationContext : block.domainOperation()) {
            MTDEInterfaceOperation domainOperation = visitDomainOperation(operationContext);
            currentDomainEntityInterface.addOperation(domainOperation);
        }

        return currentDomainEntityInterface;
    }

    @Override
    public MTDEInterfaceOperation visitDomainOperation(EntityLanguageParser.DomainOperationContext ctx) {

        currentDomainEntityInterfaceOperation = new MTDEInterfaceOperation(ctx, currentDomainEntityInterface,
                                                                           ctx.id(0).getText(), ctx.id(1).getText());
        if (ctx.EXTENDS() != null) {

            EntityLanguageParser.ExtendingOperationBodyContext body = ctx.extendingOperationBody();
            if (body != null) {
                if (body.descriptionStatement() != null) {
                    setNodeDescription(currentDomainEntityInterfaceOperation, body.descriptionStatement(), false);
                }
                if (body.extendingOperationConfig() != null && body.extendingOperationConfig().size() > 0) {
                    currentDomainEntityInterfaceOperation.setExtendingOperationConfig(
                            visitExtendingOperationConfig(body.extendingOperationConfig(0)));
                }
                if (body.operationRequest() != null && body.operationRequest().size() > 0) {
                    currentDomainEntityInterfaceOperation.setRequest(visitOperationRequest(body.operationRequest(0)));
                }
                if (body.operationResponse() != null && body.operationResponse().size() > 0) {
                    currentDomainEntityInterfaceOperation.setResponse(
                            visitOperationResponse(body.operationResponse(0)));
                }

                visitExtendingOperationBody(body);
            }
        }
        MTDEInterfaceOperation returnOperation = currentDomainEntityInterfaceOperation;
        currentDomainEntityInterfaceOperation = null;
        return returnOperation;
    }

    @Override
    public MTDEInterfaceOperationConfig visitExtendingOperationConfig
            (EntityLanguageParser.ExtendingOperationConfigContext ctx) {
        MTDEInterfaceOperationConfig extendingConfig = new MTDEInterfaceOperationConfig(ctx, ctx.id() != null ?
                                                                                             ctx.id().getText() :
                                                                                             null,
                                                                                        currentDomainEntityInterface.getInterfaceName(),
                                                                                        currentDomainEntityInterfaceOperation.getExtendedOperationName());
        for (EntityLanguageParser.ExtendingOperationAssignmentContext assignmentContext : ctx.extendingOperationConfigBlock().extendingOperationAssignment()) {
            extendingConfig.addConfigAssignment(assignmentContext.id(0).getText(), assignmentContext.id(1).getText());
        }
        extendingConfig.setContextDomainName(currentDomain.getName());
        extendingConfig.setContextEntityName(currentDomainEntity.getEntityName());
        return extendingConfig;
    }

    @Override
    public Object visitDomainVirtualAttribute(EntityLanguageParser.DomainVirtualAttributeContext ctx) {
        // these are installed in the base entity model and thus appear in all other domains but still must be virtual
        // so may not render in some domains
        return null;
    }

    @Override
    public Object visitDomainAttribute(EntityLanguageParser.DomainAttributeContext ctx) {
        String attributeName = ctx.id().getText();
        if (currentDomainEntity != null) {
            MTDEAttribute                                   domainEntityAttribute = currentDomainEntity.addDomainAttributeWithName(
                    attributeName);
            EntityLanguageParser.DomainAttributeBodyContext bodyContext           = ctx.domainAttributeBody();
            if (bodyContext != null) {
                if (bodyContext.descriptionStatement() != null) {
                    setNodeDescription(domainEntityAttribute, bodyContext.descriptionStatement(), false);
                }
                if (bodyContext.tagStatement() != null) {
                    domainEntityAttribute.addTagsWithValues(tagStringsFromTagStatements(bodyContext.tagStatement()));
                }
            }
        }
        return null;
    }

    @Override
    public Object visitDomainRelationship(EntityLanguageParser.DomainRelationshipContext ctx) {
        String relationshipName = ctx.id().getText();
        if (currentDomainEntity != null) {
            MTDERelationship                                   domainEntityAttribute = currentDomainEntity.addDomainRelationshipWithName(
                    relationshipName);
            EntityLanguageParser.DomainRelationshipBodyContext bodyContext           = ctx.domainRelationshipBody();
            if (bodyContext != null) {
                if (bodyContext.descriptionStatement() != null) {
                    setNodeDescription(domainEntityAttribute, bodyContext.descriptionStatement(), false);
                }
                if (bodyContext.tagStatement() != null) {
                    domainEntityAttribute.addTagsWithValues(tagStringsFromTagStatements(bodyContext.tagStatement()));
                }
            }
        }
        return null;
    }

    @Override
    public Object visitDomainAttributesRenameTo(EntityLanguageParser.DomainAttributesRenameToContext ctx) {
        String attributeName        = ctx.id(0).getText();
        String renamedAttributeName = ctx.id(1).getText();
        if (currentDomainEntity == null) {
            currentDomain.setExplicitAttributeName(attributeName, renamedAttributeName);
        } else {
            currentDomainEntity.getDomainAttributeByName(attributeName, true).setExplicitAttributeName(
                    renamedAttributeName);
        }
        return null;
    }

    @Override
    public Object visitDomainAttributeExclude(EntityLanguageParser.DomainAttributeExcludeContext ctx) {
        String attributeNameToExclude = ctx.id().getText();
        if (currentDomainEntity != null) {
            currentDomainEntity.addExcludedAttributeName(attributeNameToExclude);
        }
        return null;
    }

    @Override
    public Object visitDomainAttributeAdd(EntityLanguageParser.DomainAttributeAddContext ctx) {
        String      attributeName = ctx.attribute().id(0).getText();
        MTAttribute attribute     = visitAttribute(ctx.attribute());
        ECLog.logInfo(ctx, "Adding domain entity attribute: " + attribute.getName());
        if (currentDomainEntity != null) {
            currentDomainEntity.addAttribute(attribute);
        }
        return null;
    }

    @Override
    public MTAttribute visitAttribute(EntityLanguageParser.AttributeContext ctx) {
        String attributeName = ctx.id(0).getText();

        if (attributeName.startsWith("'")) {
            attributeName = attributeName.substring(1, attributeName.length() - 1);
        }

        String typeName = null;
        if (ctx.type() != null) {
            if (ctx.type().BYTE_TYPE() != null) {
                typeName = ctx.type().BYTE_TYPE().getText();
            } else {
                typeName = ctx.type().getText();
            }
        }
        if (typeName == null || typeName.length() == 0) {
            ECLog.logFatal(ctx, "No type for attribute: " + attributeName);
        }

        Integer arraySize = null; // variable length when null
        if (ctx.type().INTEGER() != null) {
            arraySize = Integer.valueOf(ctx.type().INTEGER().getText()); // should be constrainted to this
        }

        String unitName = null;
        if (ctx.id().size() > 1 && ctx.IN() != null) {
            unitName = ctx.id(1).getText();
        }

        MTEntity    entity    = currentDomainEntity != null ?
                                currentDomainEntity.getEntity() :
                                currentEntity;
        MTAttribute attribute = new MTAttribute(ctx, entity, typeName, attributeName, unitName, arraySize);
        this.currentAttribute = attribute;

        int        indexOfDefaultValueId = unitName == null ?
                                           1 :
                                           2;
        MTConstant defaultValue          = null;
        if (ctx.constant() != null) {
            defaultValue = visitConstant(ctx.constant());
        } else if (ctx.id().size() > indexOfDefaultValueId) {
            EntityLanguageParser.IdContext enumItemContext = ctx.id(indexOfDefaultValueId);
            String                         enumItemName    = enumItemContext.getText();
            if (attribute.getTypeName() == null) {
                ECLog.logFatal(enumItemContext,
                               "The default value can only be an enum item if the attribute type is an enum.");
            }
            defaultValue = new MTConstant(enumItemContext, attribute.getTypeName(), enumItemName);
        }
        if (defaultValue != null) {
            attribute.setDefaultValue(defaultValue);
        }

        for (EntityLanguageParser.AttributeQualifierContext qualifier : ctx.attributeQualifier()) {
            if (qualifier.UNIQUE() != null) {
                attribute.setUnique(true);
            }
            if (qualifier.OPTIONAL() != null) {
                attribute.setNullable(true);
            }
            if (qualifier.CREATION() != null) {
                attribute.setCreation(true);
            }
            if (qualifier.MODIFICATION() != null) {
                attribute.setModification(true);
            }
            if (qualifier.MANY() != null) {
                attribute.setArray(true);
            }
            if (qualifier.ORDERED() != null) {
                attribute.setOrdered(true);
            }
            if (qualifier.PARENT() != null) {
                attribute.setParent(true);
            }
            if (qualifier.VIRTUAL() != null) {
                attribute.setVirtual(true);
            }
            if (qualifier.SEQUENTIAL() != null) {
                attribute.setSequential(true);
            }
        }

        EntityLanguageParser.AttributeBodyContext bodyContext = ctx.attributeBody();
        if (bodyContext != null) {
            if (bodyContext.descriptionStatement() != null) {
                setNodeDescription(attribute, bodyContext.descriptionStatement(), false);
            }
            if (bodyContext.tagStatement() != null) {
                attribute.addTagsWithValues(tagStringsFromTagStatements(bodyContext.tagStatement()));
            }
            if (bodyContext.contentType() != null && bodyContext.contentType().size() > 0) {
                attribute.setContentType(
                        ECStringUtil.ProcessParserString(bodyContext.contentType(0).STRING().getSymbol().getText()));
            }
            if (bodyContext.bitfield() != null && bodyContext.bitfield().size() > 0) {
                for (EntityLanguageParser.BitfieldContext bitfieldContext : bodyContext.bitfield()) {
                    attribute.addBitField(visitBitfield(bitfieldContext));
                }
            }
            if (bodyContext.attributeConstraint() != null) {
                for (EntityLanguageParser.AttributeConstraintContext acc : bodyContext.attributeConstraint()) {
                    attribute.addConstraint(visitAttributeConstraint(acc));
                }
            }
        }

        return attribute;
    }

    @Override
    public Object visitDomainAttributesRenameAppendPrepend
            (EntityLanguageParser.DomainAttributesRenameAppendPrependContext ctx) {
        return null;
    }

    @Override
    public Object visitDomainAttributeReplaces(EntityLanguageParser.DomainAttributeReplacesContext ctx) {

        String replacingAttributeName = ctx.id(0).getText();
        String typeName               = ctx.type().getText();
        MTUnit unit                   = null;

        if (ctx.id().size() > 1) {
            String unitName = ctx.id(1).getText();
            unit = MTUnit.UnitWithName(unitName);
            if (unit == null) {
                ECLog.logFatal(ctx.id(1), "Specified unit is not supported: " + unitName);
            }
        }
        MTNativeType.DataType dataType = MTNativeType.DataType.FromName(typeName);

        MTAttribute attribute = new MTAttribute(ctx, currentDomainEntity.getEntityName(), typeName,
                                                replacingAttributeName);
        if (unit != null) {
            attribute.setUnit(unit);
        }
        currentDomainEntity.addAttribute(attribute);
        MTDEAttribute domainAttribute = currentDomainEntity.getDomainAttributeByName(replacingAttributeName, true);

        int numberOfBits = 0;
        if (dataType != null) {
            if (dataType == MTNativeType.DataType.INT32) {
                numberOfBits = 32;
            } else if (dataType == MTNativeType.DataType.INT64) {
                numberOfBits = 64;
            }
        }
        if (numberOfBits == 0) {
            ECLog.logFatal(ctx.type(), "The replacing type must be an int32 or int64 type.");
            return null;
        }

        for (EntityLanguageParser.BitfieldContext bitfieldContext : ctx.replacesBody().bitfield()) {
            MTBitField            bitField                = visitBitfield(bitfieldContext);
            MTDEAttributeBitField domainAttributeBitField = new MTDEAttributeBitField(bitfieldContext, domainAttribute,
                                                                                      bitField.getHigh(),
                                                                                      bitField.getLow(),
                                                                                      bitField.getName());
            domainAttribute.addReplacedBitField(domainAttributeBitField);
        }
        // TODO: check for overlapping bit fields - display error if so

        return null;
    }

    @Override
    public MTBitField visitBitfield(EntityLanguageParser.BitfieldContext ctx) {
        String name = null;
        if (ctx.UNUSED() != null) {
            name = ctx.UNUSED().getText();
        } else {
            name = ctx.id().getText();
        }
        EntityLanguageParser.BitCountContext bitCountContext = ctx.bitCount();
        MTBitField bitField = new MTBitField(ctx, Integer.valueOf(bitCountContext.INTEGER().getText()),
                                             name);
        if (ctx.UNUSED() != null) {
            bitField.setUnused(true);
        }
        if (ctx.descriptionStatement() != null) {
            setNodeDescription(bitField, ctx.descriptionStatement(), false);
        }
        return bitField;
    }

    @Override
    public JsonArray visitJsonArr(EntityLanguageParser.JsonArrContext ctx) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (EntityLanguageParser.JsonValueContext context : ctx.jsonValue()) {
            Object value = visitJsonValue(context);
            if (value instanceof JsonArray) {
                builder.add((JsonArray) value);
            } else if (value instanceof JsonObject) {
                builder.add((JsonObject) value);
            } else if (value instanceof JsonValue) {
                builder.add((JsonValue) value);
            } else if (value instanceof Long) {
                builder.add(((Long) value).longValue());
            } else if (value instanceof Double) {
                builder.add(((Double) value).doubleValue());
            } else if (value instanceof String) {
                builder.add((String) value);
            }
        }
        return builder.build();
    }

    @Override
    public MTDApplyTemplate visitDomainEntityApplyTemplate(EntityLanguageParser.DomainEntityApplyTemplateContext
                                                                   ctx) {
        MTDApplyTemplate                                    applyTemplate = new MTDApplyTemplate(ctx,
                                                                                                 currentDomainEntity,
                                                                                                 ctx.id().getText());
        EntityLanguageParser.DomainApplyTemplateBodyContext body          = ctx.domainApplyTemplateBody();
        if (ctx.domainApplyTemplateBody().descriptionStatement() != null) {
            setNodeDescription(applyTemplate, ctx.domainApplyTemplateBody().descriptionStatement(), false);
        }
        if (body.templateConfig() != null && body.templateConfig().size() > 0) {
            EntityLanguageParser.TemplateConfigContext configContext = body.templateConfig().get(0);
            applyTemplate.setConfig(visitJsonObj(configContext.jsonObj()));
        }
        return applyTemplate;
    }

    @Override
    public MTDApplyTemplate visitDomainModuleApplyTemplate(EntityLanguageParser.DomainModuleApplyTemplateContext
                                                                   ctx) {
        MTDApplyTemplate                                    applyTemplate = new MTDApplyTemplate(ctx,
                                                                                                 currentDomainModule,
                                                                                                 ctx.id().getText());
        EntityLanguageParser.DomainApplyTemplateBodyContext body          = ctx.domainApplyTemplateBody();
        if (ctx.domainApplyTemplateBody().descriptionStatement() != null) {
            setNodeDescription(applyTemplate, ctx.domainApplyTemplateBody().descriptionStatement(), false);
        }
        if (body.templateConfig() != null && body.templateConfig().size() > 0) {
            EntityLanguageParser.TemplateConfigContext configContext = body.templateConfig().get(0);
            applyTemplate.setConfig(visitJsonObj(configContext.jsonObj()));
        }
        return applyTemplate;
    }

    @Override
    public MTDirectory visitDirectory(EntityLanguageParser.DirectoryContext ctx) {
        MTDirectory                            output = new MTDirectory(ctx, ctx.id().getText());
        EntityLanguageParser.OutputBodyContext body = ctx.outputBody();
        if (body.descriptionStatement() != null) {
            setNodeDescription(output, body.descriptionStatement(), false);
        }
        if (body.outputPath().size() == 0) {
            ECLog.logFatal("An output must define a path.");
        }
        String path;
        EntityLanguageParser.OutputPathContext outputPathContext = body.outputPath().get(body.outputPath().size() - 1);
        if (outputPathContext.id() != null) {
            String idPath = idText(outputPathContext.id());
            if (idPath == null) {
                ECLog.logFatal("Command line define variable \"" + outputPathContext.id().getText() + "\" not set.");
            }
            path = idPath;
        } else {
            path = ECStringUtil.ProcessParserString(outputPathContext.STRING().getText());
        }
        output.setPath(path);
        currentConfiguration.addOutput(output);
        return output;
    }

    private String idText(EntityLanguageParser.IdContext idNode) {
        if (idNode.macro() != null) {
            String macroName    = idNode.macro().ident(0).getText();
            String defaultValue = null;
            if (idNode.macro().ident().size() > 1) {
                defaultValue = idNode.macro().ident(1).getText();
            } else if (idNode.macro().STRING() != null) {
                defaultValue = ECStringUtil.ProcessParserString(idNode.macro().STRING().getText());
            }
            String value = EntityCompiler.GetDefineValue(macroName, defaultValue);
            if (value == null) {
                ECLog.logFatal(idNode, "Unable to determine value.");
            }
            return value;
        }
        return idNode.getText();
    }

    @Override
    public Object visitTemplates(EntityLanguageParser.TemplatesContext ctx) {
        MTRepositoryImport repositoryImport = null;
        if (ctx.templatesBody().templatesImport() != null) {
            repositoryImport = visitTemplatesImport(ctx.templatesBody().templatesImport());
        }

        if (ctx.templatesBody().defaultTemplateConfig() != null) {
            visitDefaultTemplateConfig(ctx.templatesBody().defaultTemplateConfig());
        }

        for (EntityLanguageParser.TemplateContext templateContext : ctx.templatesBody().template()) {
            MTTemplate template = visitTemplate(templateContext);
            if (repositoryImport != null) {
                MTRepositoryImport thisImport = new MTRepositoryImport(ctx.templatesBody().templatesImport(), false);
                thisImport.setRepositoryName(repositoryImport.getRepositoryName());
                thisImport.setFilename(template.getFilename());
                template.setRepositoryImport(thisImport);
            }
        }
        return null;
    }

    @Override
    public MTRepositoryImport visitTemplatesImport(EntityLanguageParser.TemplatesImportContext ctx) {
        String             repositoryName   = ctx.id().getText();
        MTRepositoryImport repositoryImport = new MTRepositoryImport(ctx, false);
        repositoryImport.setRepositoryName(repositoryName);
        return repositoryImport;
    }

    @Override
    public MTTemplate visitTemplate(EntityLanguageParser.TemplateContext ctx) {
        MTTemplate template = new MTTemplate(ctx, currentConfiguration, ctx.id().getText());

        if (ctx.STRING() != null) {
            template.setDirectoryName(ECStringUtil.ProcessParserString(ctx.STRING().getText()));
        }
        EntityLanguageParser.TemplateBodyContext body = ctx.templateBody();
        if (body == null) {
            ECLog.logFatal(ctx, "Invalid template definition.");
        }
        if (body.outputSpec().size() > 1) {
            ECLog.logFatal(ctx, "Only one output specification can be used.");
        }
        for (EntityLanguageParser.OutputSpecContext specContext : body.outputSpec()) {
            if (specContext.id().size() == 2) {
                template.addNamedOutput(specContext.id(0).getText(), specContext.id(1).getText());
            } else if (specContext.id().size() == 1) {
                template.addNamedOutput(specContext.PRIMARY().getText(), specContext.id(0).getText());
            } else {
                ECLog.logFatal(ctx, "Template output spec requires 2 arguments.");
            }
        }
        if (body.descriptionStatement() != null) {
            setNodeDescription(template, body.descriptionStatement(), false);
        }
        if (body.templateConfig() != null) {
            if (body.templateConfig() != null && body.templateConfig().size() > 0) {
                EntityLanguageParser.TemplateConfigContext configContext = body.templateConfig().get(0);
                template.setConfig(visitJsonObj(configContext.jsonObj()));
            }
        }
        template.setContextual(ctx.CONTEXTUAL() != null);
        currentConfiguration.addTemplate(template);
        return template;
    }

    @Override
    public MTTransform visitTransform(EntityLanguageParser.TransformContext ctx) {
        MTTransform transform = new MTTransform(ctx, currentConfiguration, ctx.id().getText());
        for (EntityLanguageParser.OutputSpecContext specContext : ctx.transformBody().outputSpec()) {
            if (specContext.id().size() == 2) {
                transform.addNamedOutput(specContext.id(0).getText(), specContext.id(1).getText());
            } else if (specContext.id().size() == 1) {
                transform.addNamedOutput(specContext.PRIMARY().getText(), specContext.id(0).getText());
            } else {
                ECLog.logFatal(ctx, "Transform output spec requires 2 arguments.");
            }
        }
        currentConfiguration.addTransform(transform);
        return transform;
    }

    @Override
    public Object visitLanguage(EntityLanguageParser.LanguageContext ctx) {
        currentLanguage = new MTLanguage(ctx, ctx.id().getText());
        for (EntityLanguageParser.LanguageBodyContext bodyContext : ctx.languageBody()) {
            visit(bodyContext);
        }
        return currentLanguage;
    }

    @Override
    public Object visitLanguageSelf(EntityLanguageParser.LanguageSelfContext ctx) {
        currentLanguage.setSelfKeyword(ctx.id().getText());
        return null;
    }

    @Override
    public Object visitLanguageType(EntityLanguageParser.LanguageTypeContext ctx) {
        String mappedToTypeName = null;
        if (ctx.STRING() != null) {
            mappedToTypeName = ECStringUtil.ProcessParserString(ctx.STRING().getText());
        } else {
            mappedToTypeName = ctx.id().getText();
        }
        currentLanguage.addType(ctx.type().getText(), mappedToTypeName, ctx.REF() != null, ctx.NULLABLE() != null);
        return null;
    }

    @Override
    public Object visitLanguageComments(EntityLanguageParser.LanguageCommentsContext ctx) {
        for (EntityLanguageParser.CommentsBodyContext body : ctx.commentsBody()) {
            String commentText = ECStringUtil.ProcessParserString(body.STRING().getText());
            if (body.LINE() != null) {
                currentLanguage.setLineComment(commentText);
            } else if (body.BLOCK_START() != null) {
                currentLanguage.setBlockCommentStart(commentText);
            } else if (body.BLOCK_END() != null) {
                currentLanguage.setBlockCommentEnd(commentText);
            }
        }
        return null;
    }

    @Override
    public Object visitLanguageOperators(EntityLanguageParser.LanguageOperatorsContext ctx) {
        for (EntityLanguageParser.OperatorsBodyContext body : ctx.operatorsBody()) {
            String       operatorName = body.id().getText();
            List<String> symbols      = new ArrayList<>();
            for (TerminalNode symbolString : body.STRING()) {
                symbols.add(ECStringUtil.ProcessParserString(symbolString.getText()));
            }
            currentLanguage.addOperator(operatorName, symbols);
        }
        return null;
    }

    @Override
    public Object visitLanguageFunctions(EntityLanguageParser.LanguageFunctionsContext ctx) {
        for (EntityLanguageParser.FunctionsBodyContext body : ctx.functionsBody()) {
            String             name     = body.id(0).getText();
            String             mapping  = ECStringUtil.ProcessParserString(body.STRING().getText());
            MTLanguageFunction function = new MTLanguageFunction(body, currentLanguage, name, mapping);
            for (int i = 0; i < body.type().size(); i++) {
                String                     typeStr = body.type(i).getText();
                String                     argName = body.id(i + 1).getText();
                MTNativeType.DataType      argType = MTNativeType.DataType.FromName(typeStr);
                MTLanguageFunctionArgument arg     = new MTLanguageFunctionArgument(body.id(i + 1), function, argType,
                                                                                    argName);
                function.addArg(arg);
            }
            currentLanguage.addFunction(function);
        }
        return null;
    }

    @Override
    public Object visitVersion(EntityLanguageParser.VersionContext ctx) {
        Integer[] parts = new Integer[]{0, 0, 0};

        int    partIndex     = 0;
        String versionString = ctx.VERSIONNUM().getText();
        for (String partStr : versionString.split(".")) {
            parts[partIndex++] = Integer.valueOf(partStr);
            if (partIndex == 3) {
                break;
            }
        }

        return new ECVersion(parts[0], parts[1], parts[2]);
    }

    @Override
    public Object visitFormatStatement(EntityLanguageParser.FormatStatementContext ctx) {
        String       formatName = ctx.id().getText();
        JsonObject   settings   = visitJsonObj(ctx.jsonObj());
        MTCodeFormat codeFormat = new MTCodeFormat(ctx, formatName, settings);
        root.addCodeFormat(codeFormat);
        return super.visitFormatStatement(ctx);
    }
}

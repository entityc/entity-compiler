/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.entityc.compiler.EntityCompiler;
import org.entityc.compiler.model.MTNamespace;
import org.entityc.compiler.model.config.MTConfiguration;
import org.entityc.compiler.model.config.MTFile;
import org.entityc.compiler.model.config.MTRepository;
import org.entityc.compiler.model.config.MTRepositoryImport;
import org.entityc.compiler.repository.RepositoryCache;
import org.entityc.compiler.repository.RepositoryFile;
import org.entityc.compiler.repository.RepositoryImportManager;
import org.entityc.compiler.transform.MTBaseTransform;
import org.entityc.compiler.transform.TransformManager;
import org.entityc.compiler.transform.template.tree.FTAuthor;
import org.entityc.compiler.transform.template.tree.FTBreak;
import org.entityc.compiler.transform.template.tree.FTCall;
import org.entityc.compiler.transform.template.tree.FTCapture;
import org.entityc.compiler.transform.template.tree.FTCase;
import org.entityc.compiler.transform.template.tree.FTComment;
import org.entityc.compiler.transform.template.tree.FTContainerNode;
import org.entityc.compiler.transform.template.tree.FTContinue;
import org.entityc.compiler.transform.template.tree.FTDescription;
import org.entityc.compiler.transform.template.tree.FTDo;
import org.entityc.compiler.transform.template.tree.FTElse;
import org.entityc.compiler.transform.template.tree.FTElseIf;
import org.entityc.compiler.transform.template.tree.FTExit;
import org.entityc.compiler.transform.template.tree.FTExpressionTag;
import org.entityc.compiler.transform.template.tree.FTFile;
import org.entityc.compiler.transform.template.tree.FTForeach;
import org.entityc.compiler.transform.template.tree.FTFunction;
import org.entityc.compiler.transform.template.tree.FTFunctionArgument;
import org.entityc.compiler.transform.template.tree.FTIf;
import org.entityc.compiler.transform.template.tree.FTImport;
import org.entityc.compiler.transform.template.tree.FTInstall;
import org.entityc.compiler.transform.template.tree.FTLet;
import org.entityc.compiler.transform.template.tree.FTLoad;
import org.entityc.compiler.transform.template.tree.FTLog;
import org.entityc.compiler.transform.template.tree.FTOutlet;
import org.entityc.compiler.transform.template.tree.FTPreserve;
import org.entityc.compiler.transform.template.tree.FTPublishPhase;
import org.entityc.compiler.transform.template.tree.FTPublishScope;
import org.entityc.compiler.transform.template.tree.FTPublisher;
import org.entityc.compiler.transform.template.tree.FTReceive;
import org.entityc.compiler.transform.template.tree.FTReturn;
import org.entityc.compiler.transform.template.tree.FTSend;
import org.entityc.compiler.transform.template.tree.FTSource;
import org.entityc.compiler.transform.template.tree.FTSwitch;
import org.entityc.compiler.transform.template.tree.FTTemplate;
import org.entityc.compiler.transform.template.tree.expression.FTArray;
import org.entityc.compiler.transform.template.tree.expression.FTConstant;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.transform.template.tree.expression.FTFilterExpression;
import org.entityc.compiler.transform.template.tree.expression.FTGlobalConstant;
import org.entityc.compiler.transform.template.tree.expression.FTMethodCall;
import org.entityc.compiler.transform.template.tree.expression.FTNamedElement;
import org.entityc.compiler.transform.template.tree.expression.FTOperand;
import org.entityc.compiler.transform.template.tree.expression.FTOperation;
import org.entityc.compiler.transform.template.tree.filter.FTFilter;
import org.entityc.compiler.transform.template.tree.filter.FTFilterManager;
import org.entityc.compiler.util.ECLog;
import org.entityc.compiler.util.ECStringUtil;
import org.entityc.compiler.util.ECVersion;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class TemplateASTVisitor extends TemplateGrammerBaseVisitor {

    private final Stack<Stack<FTContainerNode>> stacks      = new Stack<>();
    private final MTConfiguration               configuration;
    private final MTRepository                  repository;
    private final String                        templateName;
    private final Stack<FTAuthor>               authorStack = new Stack<>();
    private       FTTemplate                    template;
    private       FTPublisher                   currentPublisher;
    private       boolean                       suppressImport;

    {
        // cast
    }

    public TemplateASTVisitor(String templateName, MTConfiguration configuration, MTRepository repository, boolean suppressImport) {
        super();
        this.configuration  = configuration;
        this.repository     = repository;
        this.templateName   = templateName;
        this.suppressImport = suppressImport;
    }

    @Override
    public Object visitTemplate(TemplateGrammer.TemplateContext ctx) {
        template = new FTTemplate(ctx);
        template.setName(templateName);
        template.setRepository(repository);
        push(template);

        for (TemplateGrammer.ChunkContext chunk : ctx.chunk()) {
            visit(chunk);
        }
        return template;
    }

    private void push(FTContainerNode containerNode) {
        //System.out.println("PUSH: " + containerNode.getClass().getSimpleName());
        if (stacks.isEmpty()) {
            pushOnNewStack(containerNode);
        } else {
            topStack().push(containerNode);
        }
    }

    private void pushOnNewStack(FTContainerNode containerNode) {
        //System.out.println(">>>> STACK PUSH");
        Stack<FTContainerNode> newStack = new Stack<>();
        newStack.push(containerNode);
        stacks.push(newStack);
    }

    private Stack<FTContainerNode> topStack() {
        return stacks.peek();
    }

    @Override
    public Object visitChunk(TemplateGrammer.ChunkContext ctx) {
        if (ctx.COMMENT() != null) {
            FTComment comment = new FTComment(ctx);
            currentContainer(ctx).addChild(comment);
        }
        return super.visitChunk(ctx);
    }

    private FTContainerNode currentContainer(ParserRuleContext ctx) {
        if (topStack().isEmpty()) {
            ECLog.logFatal(ctx, "Unbalanced block instructions.");
        }
        return topStack().peek();
    }

    @Override
    public Object visitOther(TemplateGrammer.OtherContext ctx) {
        if (topStack().isEmpty()) {
            return null;
        }
        StringBuilder sb                   = new StringBuilder();
        boolean       previousWasEndOfLine = true;

        TerminalNode firstOtherOfLine = null;
        TerminalNode lastOtherOnLine  = null;
        for (int i = 0; i < ctx.Other().size(); i++) {
            TerminalNode other = ctx.Other(i);
            if (firstOtherOfLine == null) {
                firstOtherOfLine = other;
            }
            // split up pieces before they cross line, we want to be
            // able to discard an indent chunk more easily
            if (other.getText().equals("\n")) {
                sb.append(other.getText());
                String text = sb.toString();
                FTSource source = new FTSource(ctx, text,
                                               firstOtherOfLine.getSymbol().getLine(),
                                               firstOtherOfLine.getSymbol().getCharPositionInLine(),
                                               other.getSymbol().getLine(),
                                               other.getSymbol().getCharPositionInLine());
                boolean textIsJustSpaces = source.isJustSpaces();
                boolean isLastNode       = i == (ctx.Other().size() - 1);
                boolean possibleIndent   = previousWasEndOfLine && textIsJustSpaces && isLastNode;
                source.setPossibleTemplateIndent(possibleIndent);
                currentContainer(ctx).addChild(source);
                sb                   = new StringBuilder();
                previousWasEndOfLine = true;
                firstOtherOfLine     = null;
            } else {
                sb.append(other.getText());
                lastOtherOnLine = other;
            }
        }
        if (sb.length() > 0) {
            String text = sb.toString();
            FTSource source = new FTSource(ctx, text,
                                           firstOtherOfLine.getSymbol().getLine(),
                                           firstOtherOfLine.getSymbol().getCharPositionInLine(),
                                           lastOtherOnLine.getSymbol().getLine(),
                                           lastOtherOnLine.getSymbol().getCharPositionInLine());
            boolean textIsJustSpaces = source.isJustSpaces() && !text.endsWith("\n");
            boolean possibleIndent   = previousWasEndOfLine && textIsJustSpaces;
//            if (possibleIndent) {
//                ECLog.logInfo(ctx, "POSSIBLE INDENT");
//            }
            source.setPossibleTemplateIndent(possibleIndent);
            currentContainer(ctx).addChild(source);
        }
        return super.visitOther(ctx);
    }

    @Override
    public Object visitBlock(TemplateGrammer.BlockContext ctx) {
        return super.visitBlock(ctx);
    }

    @Override
    public Object visitBlockEnd(TemplateGrammer.BlockEndContext ctx) {
        if (ctx.If() != null) {
            currentContainerTop(ctx).setBlockEndContext(ctx);
            popStack();
            return null;
        }
        if (ctx.Switch() != null) {
            currentContainerTop(ctx).setBlockEndContext(ctx);
            popStack();
        }
        currentContainer(ctx).setBlockEndContext(ctx);

        if (ctx.Publisher() != null) {
            currentPublisher = null; // leaving the publisher block
        }
        if (ctx.Author() != null) {
            if (authorStack.isEmpty()) {
                ECLog.logFatal(ctx, "Imbalance of $[author] instructions.");
            }
            authorStack.pop();
        }
        FTContainerNode containerNode        = currentContainer(ctx);
        String          expectingInstruction = null;
        if (!containerNode.isSameAsContext(ctx)) {
            expectingInstruction = containerNode.getInstructionName();
        }
        if (expectingInstruction != null) {
            ECLog.logFatal(ctx, "Imbalanced blocks, was expecting $[/" + expectingInstruction
                                + "] to match start of block on line " + containerNode.getStartLineNumber());
            return null;
        }

        pop();
        return super.visitBlockEnd(ctx);
    }

    private FTContainerNode currentContainerTop(ParserRuleContext ctx) {
        if (topStack().isEmpty()) {
            ECLog.logFatal(ctx, "Unbalanced block instructions.");
        }
        return topStack().elementAt(0);
    }

    private void popStack() {
        //System.out.println("<<<< STACK POP");
        stacks.pop();
    }

    private void pop() {
        //System.out.println("POP: " + topStack().peek().getClass().getSimpleName());
        topStack().pop();
    }

    @Override
    public Object visitLanguageTag(TemplateGrammer.LanguageTagContext ctx) {
        template.setLanguage(ctx.identifier().getText());
        return template.getLanguage();
    }

    @Override
    public Object visitDomainTag(TemplateGrammer.DomainTagContext ctx) {
        String defaultDomainName = ctx.identifier().getText();
        template.setDefaultDomainName(defaultDomainName);
        return super.visitDomainTag(ctx);
    }

    @Override
    public ECVersion visitVersionTag(TemplateGrammer.VersionTagContext ctx) {
        String    fullVersionText = ctx.VERSION_NUM().getText();
        String[]  textParts       = fullVersionText.split("\\.");
        Integer[] parts           = new Integer[3];
        for (int i = 0; i < 3; i++) {
            parts[i] = Integer.valueOf(textParts[i]);
        }
        ECVersion version = new ECVersion(parts[0], parts[1], parts[2]);
        template.setVersion(version);
        return version;
    }

    @Override
    public FTDescription visitDescriptionTag(TemplateGrammer.DescriptionTagContext ctx) {
        List<String> categories = new ArrayList<>();

        if (ctx.identifier().size() > 0) {
            for (TemplateGrammer.IdentifierContext identifierContext : ctx.identifier()) {
                categories.add(identifierContext.getText());
            }
        } else {
            categories.add(FTDescription.DefaultCategory);
        }

        String        descriptionText = ECStringUtil.ProcessParserString(ctx.STRING().getText());
        FTDescription descriptionNode = new FTDescription(ctx, categories, descriptionText);

        FTContainerNode containerNode = currentContainer(ctx);
        if (containerNode instanceof FTFile) {
            containerNode = topStack().get(0); //(FTContainerNode) containerNode).
        }

        containerNode.addDescription(descriptionNode);
        return descriptionNode;
    }

    @Override
    public FTInstall visitInstallTag(TemplateGrammer.InstallTagContext ctx) {
        FTExpression sourceArg        = resolveFileArg(ctx.fileArg(0));
        FTExpression destNamespaceArg = resolveFileArg(ctx.fileArg(1));
        FTInstall    install          = new FTInstall(ctx, ctx.Copy() != null, sourceArg, destNamespaceArg);
        install.setSourceRepositoryName(repository.getName());
        currentContainer(ctx).addChild(install);
        return install;
    }

    @Override
    public Object visitFileTag(TemplateGrammer.FileTagContext ctx) {
        int          argIndex     = 0;
        FTExpression namespaceArg = null;
        if (ctx.fileArg().size() == 3) {
            // namespace filename extension
            namespaceArg = resolveFileArg(ctx.fileArg(0));
            argIndex++;
        }

        if (ctx.fileArg().size() < 2) {
            ECLog.logFatal(ctx, "File tags must have at least two arguments.");
        }

        FTExpression filenameArg  = resolveFileArg(ctx.fileArg(argIndex++));
        FTExpression extensionArg = resolveFileArg(ctx.fileArg(argIndex++));

        FTFile file = new FTFile(ctx, currentContainer(ctx), ctx.IfDoesNotExist() != null, namespaceArg, filenameArg,
                                 extensionArg);
        currentContainer(ctx).addChild(file);
        push(file);
        return super.visitFileTag(ctx);
    }

    public FTExpression resolveFileArg(TemplateGrammer.FileArgContext fileArg) {
        if (fileArg.expression() != null) {
            return visitExpression(fileArg.expression());
        }
        return null;
    }

    @Override
    public Object visitLoadTag(TemplateGrammer.LoadTagContext ctx) {

        int argIndex = 0;

        FTExpression namespaceArg = resolveFileArg(ctx.fileArg(argIndex++));
        FTExpression filenameArg  = resolveFileArg(ctx.fileArg(argIndex++));
        FTExpression extensionArg = resolveFileArg(ctx.fileArg(argIndex++));

        FTLoad.Type type = FTLoad.Type.fromName(ctx.identifier().getText());
        if (type == null) {
            ECLog.logFatal(ctx.identifier(), "Unknown loader: " + ctx.identifier().getText());
        }
        FTLoad load = new FTLoad(ctx, type, namespaceArg, filenameArg, extensionArg);

        currentContainer(ctx).addChild(load);

        return super.visitLoadTag(ctx);
    }

    @Override
    public Object visitImportTag(TemplateGrammer.ImportTagContext ctx) {

        String templatePath       = null;
        String fromRepositoryName = null;
        int    identifierIndex    = 0;
        if (ctx.STRING() != null) {
            templatePath = ECStringUtil.ProcessParserString(ctx.STRING().getText());
        } else if (ctx.identifier() != null) {
            templatePath = ctx.identifier(0).getText();
            identifierIndex++;
        }
        if (ctx.identifier().size() > identifierIndex) {
            fromRepositoryName = ctx.identifier(identifierIndex).getText();
        }
        // This node is only added so it can be used for other things such as
        // the code reformatter, otherwise it is ignored during execution
        FTImport ftImport = new FTImport(ctx, templatePath, fromRepositoryName);
        currentContainer(ctx).addChild(ftImport);

        if (fromRepositoryName == null) {
            fromRepositoryName = repository.getName();
        }
        if (this.suppressImport) {
            return null;
        }
        String templateName = templatePath;
        if (templateName.contains("/")) {
            templateName = templateName.substring(templateName.lastIndexOf('/') + 1);
        }
        if (EntityCompiler.isVerbose()) {
            ECLog.logInfo(ctx, "Found Import of template: " + templatePath);
            if (fromRepositoryName == null) {
                ECLog.logError("Without source repository!");
            }
        }
        MTBaseTransform transform = TransformManager.GetTransformByName(templateName);
        if (transform == null) {
            File templateFile = TransformManager.FindTemplateFile(templateName + ".eml");
            if (templateFile == null) {
                RepositoryImportManager importManager = new RepositoryImportManager(
                        RepositoryCache.CacheStructure.UserCache);
                MTRepositoryImport repositoryImport = new MTRepositoryImport(null, false);
                repositoryImport.setRepositoryName(fromRepositoryName);
                repositoryImport.setFilename(templatePath);
                RepositoryFile repositoryFile = importManager.importFromRepository(configuration.getSpace(),
                                                                                   repositoryImport, "eml", false);
                templateFile = new File(repositoryFile.getFilepath());
            }
            if (EntityCompiler.isVerbose()) {
                ECLog.logInfo("Importing template: " + templateName);
            }
            FTTemplate importedTemplate = configuration.parseTemplate(null, new MTFile(null, templateFile),
                                                                      fromRepositoryName);
            importedTemplate.setImported(true);
            currentContainer(ctx).addChild(importedTemplate);
            currentContainer(ctx).resolveFunctionCalls(importedTemplate);

            return null;
        } else {
            if (EntityCompiler.isVerbose()) {
                ECLog.logInfo(ctx, "Template already loaded: " + templateName);
            }
            //TODO: ? currentContainer().addChild(transform);
        }
        if (!(transform instanceof FileTemplateTransform)) {
            ECLog.logFatal(ctx, "When looking for template named \"" + templateName
                                + "\" found a transform but its not a template.");
        }

        FileTemplateTransform fileTemplateTransform = (FileTemplateTransform) transform;

        if (EntityCompiler.isVerbose()) {
            ECLog.logInfo("Loading file template template: " + templateName);
        }
        fileTemplateTransform.load();

        FTTemplate importedTemplate = fileTemplateTransform.getTemplate();
        importedTemplate.setImported(true);
        currentContainer(ctx).addChild(importedTemplate);
        currentContainer(ctx).resolveFunctionCalls(importedTemplate);

        return super.visitImportTag(ctx);
    }

    @Override
    public Object visitFunctionDeclTag(TemplateGrammer.FunctionDeclTagContext ctx) {
        FTFunction function = new FTFunction(ctx, currentContainer(ctx), ctx.identifier().getText());
        if (ctx.nodeDescription() != null) {
            for (int i = 0; i < ctx.nodeDescription().size(); i++) {
                function.addDescription(visitNodeDescription(ctx.nodeDescription(i)));
            }
        }

        if (ctx.functionDeclArgList().size() > 0) {
            for (TemplateGrammer.FunctionDeclArgContext functionDeclArgContext : ctx.functionDeclArgList(
                    0).functionDeclArg()) {
                FTFunctionArgument argument = new FTFunctionArgument(functionDeclArgContext,
                                                                     functionDeclArgContext.identifier().getText());
                function.addInputArgument(argument);
                if (ctx.nodeDescription() != null) {
                    for (int i = 0; i < functionDeclArgContext.nodeDescription().size(); i++) {
                        argument.addDescription(visitNodeDescription(functionDeclArgContext.nodeDescription(i)));
                    }
                }
            }
            if (ctx.functionDeclArgList().size() > 1) {
                for (TemplateGrammer.FunctionDeclArgContext functionDeclArgContext : ctx.functionDeclArgList(
                        1).functionDeclArg()) {
                    FTFunctionArgument argument = new FTFunctionArgument(functionDeclArgContext,
                                                                         functionDeclArgContext.identifier().getText());
                    function.addOutputArgument(argument);
                    if (ctx.nodeDescription() != null) {
                        for (int i = 0; i < functionDeclArgContext.nodeDescription().size(); i++) {
                            argument.addDescription(visitNodeDescription(functionDeclArgContext.nodeDescription(i)));
                        }
                    }
                }
            }
        }

        currentContainer(ctx).addChild(function);
        push(function);
        return super.visitFunctionDeclTag(ctx);
    }

    @Override
    public FTDescription visitNodeDescription(TemplateGrammer.NodeDescriptionContext ctx) {
        List<String> categories = new ArrayList<>();

        if (ctx.identifier().size() > 0) {
            for (TemplateGrammer.IdentifierContext identifierContext : ctx.identifier()) {
                categories.add(identifierContext.getText());
            }
        } else {
            categories.add(FTDescription.DefaultCategory);
        }

        String        descriptionText = ECStringUtil.ProcessParserString(ctx.STRING().getText());
        FTDescription descriptionNode = new FTDescription(ctx, categories, descriptionText);

        return descriptionNode;
    }

    @Override
    public Object visitCallTag(TemplateGrammer.CallTagContext ctx) {
        String functionName = ctx.identifier().getText();
        // search for function by this name back from here in scope
        FTFunction function = currentContainer(ctx).findFunctionWithName(functionName);

        FTCall call = null;
        if (function == null) {
            call = new FTCall(ctx, functionName);
        } else {
            call = new FTCall(ctx, function);
        }
        call.setExplicit(ctx.Explicit() != null);

        List<String> usedFunctionArgs = new ArrayList<>();
        if (ctx.inputCallArgList() != null) {
            // inputs
            for (TemplateGrammer.CallArgContext callArgContext : ctx.inputCallArgList().callArg()) {
                String argName = callArgContext.identifier().getText();

                if (function != null && !function.hasInputArgName(argName)) {
                    ECLog.logFatal(callArgContext.identifier(),
                                   "Argument \"" + argName + "\" not found in the input of function \"" + functionName
                                   + "\".");
                }
                usedFunctionArgs.add(argName);
                call.setInputArgValueExpression(argName, visitExpression(callArgContext.expression()));
            }
        }

        if (ctx.outputCallArgList() != null) {
            // outputs
            for (TemplateGrammer.CallArgContext callArgContext : ctx.outputCallArgList().callArg()) {
                String argName = callArgContext.identifier().getText();
                if (function != null && !function.hasOutputArgName(argName)) {
                    ECLog.logFatal(callArgContext.identifier(),
                                   "Argument \"" + argName + "\" not found in the output of function \"" + functionName
                                   + "\".");
                }
                FTExpression operand = visitExpression(callArgContext.expression());
                if (!operand.isOperand()) {
                    ECLog.logFatal(callArgContext.expression(),
                                   "Output assignment of function \"" + functionName + "\" cannot be an expression.");
                }
                call.setOutputArgOperand(argName, (FTOperand) operand);
            }
        }

        if (function != null) {
            if (!call.isExplicit()) {
                // for those not assigned from function, simply use an expression that is the name of the argument.
                for (FTFunctionArgument argument : function.getInputArguments()) {
                    if (usedFunctionArgs.contains(argument.getName())) {
                        continue;
                    }
                    call.setInputArgValueExpression(argument.getName(), new FTOperand(ctx, argument.getName()));
                }
            } else {
                if (usedFunctionArgs.size() < ctx.inputCallArgList().callArg().size()) {
                    ECLog.logFatal(ctx, "The call with explicit arguments is missing some arguments.");
                }
            }
        }
        currentContainer(ctx).addChild(call);
        return super.visitCallTag(ctx);
    }

    @Override
    public Object visitForeachTag(TemplateGrammer.ForeachTagContext ctx) {
        FTExpression collectionExpression = visitExpression(ctx.expression().get(0));
        FTExpression conditionalExpression = ctx.expression().size() > 1 ?
                                             visitExpression(ctx.expression().get(1)) :
                                             null;
        FTForeach                         foreach           = null;
        TemplateGrammer.IdentifierContext identifierContext = ctx.identifier();
        String loopVariableName = identifierContext != null ?
                                  identifierContext.getText() :
                                  null;
        if (conditionalExpression != null) {
            foreach = new FTForeach(ctx, currentContainer(ctx),
                                    loopVariableName,
                                    collectionExpression,
                                    conditionalExpression);
        } else {
            foreach = new FTForeach(ctx, currentContainer(ctx),
                                    loopVariableName,
                                    collectionExpression);
        }
        currentContainer(ctx).addChild(foreach);
        push(foreach);
        return super.visitForeachTag(ctx);
    }

    @Override
    public FTFilterExpression visitFilter(TemplateGrammer.FilterContext ctx) {
        String filterName = null;
        if (ctx.identifier() != null) {
            filterName = ctx.identifier().getText();
        }
        if (filterName != null) {
            FTFilter filter = FTFilterManager.getInstance().getFilterByName(filterName);
            if (filter == null) {
                ECLog.logFatal(ctx, "No such filter named: " + filterName);
            }
            FTFilterExpression filterExpression = null;
            if (ctx.filterParamExpression() != null && ctx.filterParamExpression().identifier() != null) {
                String param = ctx.filterParamExpression().identifier().getText();
                filterExpression = new FTFilterExpression(ctx, filter, param);
            } else if (ctx.filterParamExpression() != null && ctx.filterParamExpression().expression() != null) {
                filterExpression = new FTFilterExpression(ctx, filter,
                                                          visitExpression(ctx.filterParamExpression().expression()));
            } else {
                filterExpression = new FTFilterExpression(ctx, filter);
            }
            int numOptions = ctx.filterOption().size();
            int optionNum  = 0;

            while (optionNum < numOptions) {
                TemplateGrammer.FilterOptionContext foc        = ctx.filterOption(optionNum++);
                String                              optionName = foc.identifier(0).getText();
                Object                              value      = true;
                if (foc.identifier().size() > 1) {
                    value = foc.identifier(1).getText();
                } else if (foc.constant() != null) {
                    value = foc.constant().getText();
                }
                if (value instanceof String) {
                    String valueStr = (String) value;
                    if (valueStr.equals("true")) {
                        value = true;
                    } else if (valueStr.equals("false")) {
                        value = false;
                    }
                }
                filterExpression.addOption(optionName, value);
            }
            return filterExpression;
        }
        ECLog.logFatal(ctx, "Could not find filter name.");
        return null;
    }

//    @Override
//    public FTExpression visitFilterParamExpression(TemplateGrammer.FilterParamExpressionContext ctx) {
//        FTOperation.Operator operator = ctx.bop != null ? FTOperation.Operator.getOperatorBySymbol(ctx.bop.getText()) : null;
//        if (operator != null) {
//
//            if (operator != FTOperation.Operator.DOT) {
//                ECLog.logFatal(ctx, "Unsupported operation in a filter parameter expression: " + operator.toString());
//            }
//
//            List<FTExpression> operands = new ArrayList<>();
//            if (ctx.filterParamExpression() != null) {
//                FTExpression leftExpression = visitFilterParamExpression(ctx.filterParamExpression());
//                if (leftExpression != null) {
//                    operands.add(leftExpression);
//                }
//            }
//
//            TemplateGrammer.MethodCallContext methodCallContext = ctx.methodCall();
//            if (methodCallContext != null) {
//                FTMethodCall methodCall = new FTMethodCall(methodCallContext, methodCallContext.identifier().getText());
//                for (TemplateGrammer.ExpressionContext expressionContext : methodCallContext.expressionList().expression()) {
//                    methodCall.addExpression(visitExpression(expressionContext));
//                }
//                operands.add(methodCall);
//            }
//
//            FTOperation operation = new FTOperation(ctx, operator, operands);
//            return operation;
//        } else {
//            if (ctx.identifier() != null) {
//                return new FTOperand(ctx.identifier(), ctx.identifier().getText());
//            }
//        }
//
//        return null;
//    }

    @Override
    public Object visitBreakTag(TemplateGrammer.BreakTagContext ctx) {
        currentContainer(ctx).addChild(new FTBreak(ctx));
        return super.visitBreakTag(ctx);
    }

    @Override
    public Object visitContinueTag(TemplateGrammer.ContinueTagContext ctx) {
        currentContainer(ctx).addChild(new FTContinue(ctx));
        return super.visitContinueTag(ctx);
    }

    @Override
    public Object visitExitTag(TemplateGrammer.ExitTagContext ctx) {
        currentContainer(ctx).addChild(new FTExit(ctx));
        return super.visitExitTag(ctx);
    }

    @Override
    public Object visitIfTag(TemplateGrammer.IfTagContext ctx) {
        FTExpression condition = visitExpression(ctx.expression());
        FTIf         ftIf      = new FTIf(ctx, currentContainer(ctx), condition);
        currentContainer(ctx).addChild(ftIf);
        pushOnNewStack(ftIf);
        return super.visitIfTag(ctx);
    }

    @Override
    public FTExpression visitExpression(TemplateGrammer.ExpressionContext ctx) {
        //ECLog.logInfo("Parsing expression: " + ctx.getText());
        if (ctx.prefix != null || ctx.bop != null) {
            // unary operator or
            // binary operator
            // or select
            boolean unary  = ctx.prefix != null;
            boolean binary = ctx.bop != null;

            //ECLog.logInfo("Expression type: " + (unary ? "Unary" : (binary ? "Binary" : "Other")));

            // look for the filter expression
            if (binary && ctx.bop.getText().equals("|") && ctx.filter() != null) {
                FTFilterExpression filterExpression = visitFilter(ctx.filter());
                filterExpression.setInputExpression(visitExpression(ctx.expression(0)));
                return filterExpression;
            }

            String operatorStr = null;
            if (binary && FTOperation.Operator.isValidOperator(ctx.bop.getText())) {
                operatorStr = ctx.bop.getText();
            } else if (unary) {
                operatorStr = ctx.prefix.getText();
            }

            FTOperation.Operator operator = FTOperation.Operator.getOperatorBySymbol(operatorStr);

            if (operator == null) {
                ECLog.logFatal(ctx, "Unknown operator in expression: " + operatorStr);
            }
            if (binary && operator == FTOperation.Operator.DOT) {
                FTExpression operandExpression = visitExpression(ctx.expression(0));

                if (ctx.methodCall() != null) {
                    TemplateGrammer.MethodCallContext methodCallContext = ctx.methodCall();
                    FTMethodCall methodCall = new FTMethodCall(methodCallContext,
                                                               operandExpression,
                                                               methodCallContext.identifier().getText());
                    if (methodCallContext.expressionList() != null) {
                        for (TemplateGrammer.ExpressionContext expressionContext : methodCallContext.expressionList().expression()) {
                            methodCall.addExpression(visitExpression(expressionContext));
                        }
                    }
                    return methodCall;
                }
            }

            List<FTExpression> inputs      = new ArrayList<>();
            int                numOperands = operator.getOperands();
            if (unary && (operator == FTOperation.Operator.PLUS || operator == FTOperation.Operator.MINUS)) {
                numOperands = 1;
            }

            int expressionInputCount = ctx.expression().size() + (ctx.identifier() != null ?
                                                                  1 :
                                                                  0);
            if (expressionInputCount != numOperands) {
                ECLog.logFatal(ctx, "Invalid number of operands for operation: " + operator.getSymbol());
            }
            for (TemplateGrammer.ExpressionContext expressionContext : ctx.expression()) {
                FTExpression operandExpression = visitExpression(expressionContext);
                if (operandExpression != null) {
                    inputs.add(operandExpression);
                } else {
                    ECLog.logError(expressionContext, "Not able to parse expression: " + expressionContext.getText());
                }
            }
            if (ctx.identifier() != null) {
                inputs.add(new FTOperand(ctx, ctx.identifier().getText()));
            }
            if (inputs.size() != numOperands) {
                ECLog.logFatal(ctx, "Unable to process all operands of operation: " + operator.getSymbol());
            }

            FTOperation operation = new FTOperation(ctx, operator, inputs);
            return operation;
        } else if (ctx.primary() != null) {
            if (ctx.primary().identifier() != null) {
                return new FTOperand(ctx.primary().identifier(), ctx.primary().identifier().getText());
            } else if (ctx.primary().constant() != null) {
                return visitConstant(ctx.primary().constant());
            } else if (ctx.primary().expression() != null) {
                return visitExpression(ctx.primary().expression()); // for the case '(' expression ')'
            }
        } else if (ctx.methodCall() != null) {
            FTExpression operandExpression = ctx.expression().size() > 0 ?
                                             visitExpression(ctx.expression(0)) :
                                             null;
            TemplateGrammer.MethodCallContext methodCallContext = ctx.methodCall();
            FTMethodCall methodCall = new FTMethodCall(methodCallContext, operandExpression,
                                                       methodCallContext.identifier().getText());
            if (methodCallContext.expressionList() != null) {
                for (TemplateGrammer.ExpressionContext expressionContext : methodCallContext.expressionList().expression()) {
                    methodCall.addExpression(visitExpression(expressionContext));
                }
            }
            return methodCall;
        } else if (ctx.arraySpecifier() != null) {
            return visitArraySpecifier(ctx.arraySpecifier());
        }
        return null;
    }

    @Override
    public FTArray visitArraySpecifier(TemplateGrammer.ArraySpecifierContext ctx) {
        FTArray array = new FTArray(ctx);
        if (ctx.expressionList() != null) {
            for (TemplateGrammer.ExpressionContext expressionContext : ctx.expressionList().expression()) {
                array.addExpression(visitExpression(expressionContext));
            }
        }
        return array;
    }

    @Override
    public FTConstant visitConstant(TemplateGrammer.ConstantContext ctx) {
        if (ctx.BOOLEAN != null) {
            boolean value = ctx.BOOLEAN.getText().equals("true");
            return new FTConstant(ctx, value);
        } else if (ctx.INTEGER() != null) {
            return new FTConstant(ctx, Long.parseLong(ctx.INTEGER().getText()));
        } else if (ctx.FLOAT() != null) {
            return new FTConstant(ctx, Double.parseDouble(ctx.FLOAT().getText()));
        } else if (ctx.STRING() != null) {
            return new FTConstant(ctx, ECStringUtil.ProcessParserString(ctx.STRING().getText()));
        } else if (ctx.HashConstant() != null) {
            String name = ctx.HashConstant().getText();
            if (name.startsWith("#")) {
                name = name.substring(1);
            }
            String defaultValue = null;
            if (ctx.constant() != null) {
                FTConstant defaultConstant = visitConstant(ctx.constant());
                defaultValue = defaultConstant.getStringValue();
            }
            return new FTGlobalConstant(ctx, name, defaultValue);
        } else if (ctx.builtinType() != null) {
            List<String> parts = new ArrayList<>();
            for (TerminalNode node : ctx.IDENT()) {
                parts.add(node.getText());
            }
            FTNamedElement namedElement = new FTNamedElement(ctx.builtinType(), ctx.builtinType().getText(), parts);
            return namedElement;
        }
        return null;
    }

    @Override
    public Object visitElseifTag(TemplateGrammer.ElseifTagContext ctx) {
        FTExpression condition = visitExpression(ctx.expression());
        FTElseIf     ftElseIf  = new FTElseIf(ctx, currentContainer(ctx), condition);
        currentContainer(ctx).addChild(ftElseIf);
        push(ftElseIf);
        return super.visitElseifTag(ctx);
    }

    @Override
    public Object visitElseTag(TemplateGrammer.ElseTagContext ctx) {
        FTElse ftElse = new FTElse(ctx, currentContainer(ctx));
        currentContainer(ctx).addChild(ftElse);
        push(ftElse);
        return super.visitElseTag(ctx);
    }

    @Override
    public Object visitSwitchTag(TemplateGrammer.SwitchTagContext ctx) {
        FTExpression condition = visitExpression(ctx.expression());

        FTSwitch ftSwitch = new FTSwitch(ctx, currentContainer(ctx), condition);
        currentContainer(ctx).addChild(ftSwitch);
        push(ftSwitch);
        super.visitSwitchTag(ctx);
        return null;
    }

    @Override
    public Object visitCaseTag(TemplateGrammer.CaseTagContext ctx) {
        List<String> caseArgs = new ArrayList<>();
        for (TemplateGrammer.CaseArgContext i : ctx.caseArg()) {
            caseArgs.add(i.getText());
        }
        FTContainerNode container = currentContainer(ctx);
        if (container instanceof FTCase) {
            popStack();
        }
        container = currentContainer(ctx);
        FTCase ftCase = new FTCase(ctx, container, caseArgs);
        if (container instanceof FTSwitch) {
            FTSwitch parentSwitch = (FTSwitch) container;
            parentSwitch.addCase(ftCase);
            pushOnNewStack(ftCase);
            super.visitCaseTag(ctx);
        } else {
            ECLog.logFatal(ctx, "Case statement not inside a parent switch block.");
        }
        return ftCase;
    }

    @Override
    public Object visitDefaultTag(TemplateGrammer.DefaultTagContext ctx) {
        FTContainerNode container = currentContainer(ctx);
        if (container instanceof FTCase) {
            popStack();
        }
        container = currentContainer(ctx);
        FTCase defaultCase = new FTCase(ctx, container);
        if (container instanceof FTSwitch) {
            FTSwitch parentSwitch = (FTSwitch) container;
            parentSwitch.addCase(defaultCase);
            pushOnNewStack(defaultCase);
            super.visitDefaultTag(ctx);
        } else {
            ECLog.logFatal(ctx, "Default statement not inside a parent switch block.");
        }
        return defaultCase;
    }

    @Override
    public Object visitVariableTag(TemplateGrammer.VariableTagContext ctx) {

        FTExpression expression = null;
        if (ctx.expression() == null) {
            expression = new FTConstant(ctx, "$");
        } else {
            expression = visitExpression(ctx.expression());
        }
        FTExpressionTag expressionTag = new FTExpressionTag(ctx, expression);
        currentContainer(ctx).addChild(expressionTag);
        return super.visitVariableTag(ctx);
    }

    @Override
    public FTLet visitLetTag(TemplateGrammer.LetTagContext ctx) {
        String       leftVariableName = ECStringUtil.ProcessParserString(ctx.identifier().getText());
        FTExpression rightExpression  = visitExpression(ctx.expression());
        FTLet        let              = new FTLet(ctx, leftVariableName, rightExpression);
        currentContainer(ctx).addChild(let);
        return let;
    }

    @Override
    public FTDo visitDoTag(TemplateGrammer.DoTagContext ctx) {
        FTExpression rightExpression = visitExpression(ctx.expression());
        FTDo         ftDo            = new FTDo(ctx, rightExpression);
        currentContainer(ctx).addChild(ftDo);
        return ftDo;
    }

    @Override
    public FTCapture visitCaptureTag(TemplateGrammer.CaptureTagContext ctx) {
        String    leftVariableName = ECStringUtil.ProcessParserString(ctx.identifier().getText());
        FTCapture captureBlock     = new FTCapture(ctx, currentContainer(ctx), leftVariableName);
        currentContainer(ctx).addChild(captureBlock);
        push(captureBlock);
        return captureBlock;
    }

    @Override
    public FTReceive visitReceiverTag(TemplateGrammer.ReceiverTagContext ctx) {
        String    leftVariableName = ECStringUtil.ProcessParserString(ctx.identifier().getText());
        FTReceive receive          = new FTReceive(ctx, ctx.Distinct() != null, leftVariableName);
        if (ctx.nodeDescription() != null) {
            for (int i = 0; i < ctx.nodeDescription().size(); i++) {
                receive.addDescription(visitNodeDescription(ctx.nodeDescription(i)));
            }
        }
        currentContainer(ctx).addChild(receive);
        return receive;
    }

    @Override
    public Object visitReturnTag(TemplateGrammer.ReturnTagContext ctx) {
        FTReturn ftReturn = new FTReturn(ctx);
        currentContainer(ctx).addChild(ftReturn);
        return ftReturn;
    }

    @Override
    public Object visitSendTag(TemplateGrammer.SendTagContext ctx) {
        String leftVariableName = ECStringUtil.ProcessParserString(ctx.identifier().getText());
        FTSend sendBlock        = new FTSend(ctx, currentContainer(ctx), leftVariableName);
        currentContainer(ctx).addChild(sendBlock);
        push(sendBlock);
        return sendBlock;
    }

    @Override
    public FTLog visitLogTag(TemplateGrammer.LogTagContext ctx) {
        String levelName = ctx.identifier() != null ?
                           ECStringUtil.ProcessParserString(ctx.identifier().getText()) :
                           null;
        FTLog logBlock = new FTLog(ctx, currentContainer(ctx), levelName);
        currentContainer(ctx).addChild(logBlock);
        push(logBlock);
        return logBlock;
    }

    @Override
    public FTPreserve visitPreserveTag(TemplateGrammer.PreserveTagContext ctx) {
        String     preserveBlockName = ECStringUtil.ProcessParserString(ctx.identifier(0).getText());
        FTPreserve preserveBlock     = new FTPreserve(ctx, currentContainer(ctx), preserveBlockName);
        if (ctx.identifier().size() > 1) {
            for (int i = 1; i < ctx.identifier().size(); i++) {
                String deprecatedName = ECStringUtil.ProcessParserString(ctx.identifier(i).getText());
                preserveBlock.addDeprecatedName(deprecatedName);
            }
        }
        currentContainer(ctx).addChild(preserveBlock);
        push(preserveBlock);
        return preserveBlock;
    }

    @Override
    public FTPublisher visitPublisherTag(TemplateGrammer.PublisherTagContext ctx) {
        if (currentPublisher != null) {
            ECLog.logFatal(ctx, "Publishers cannot be nested.");
        }
        MTNamespace namespace = new MTNamespace(ctx,
                                                segmentsForPathID(ctx.namespaceIdent().IDENT()).toArray(new String[0]),
                                                false);
        FTPublisher publisher = new FTPublisher(ctx, currentContainer(ctx), namespace);
        template.addPublisher(publisher);
        if (ctx.nodeDescription() != null) {
            for (int i = 0; i < ctx.nodeDescription().size(); i++) {
                publisher.addDescription(visitNodeDescription(ctx.nodeDescription(i)));
            }
        }
        currentContainer(ctx).addChild(publisher);
        push(publisher);
        currentPublisher = publisher;
        return publisher;
    }

    private List<String> segmentsForPathID(List<TerminalNode> nodes) {
        ArrayList<String> segments = new ArrayList<>();
        for (TerminalNode node : nodes) {
            segments.add(node.getText());
        }
        return segments;
    }

    @Override
    public Object visitOutletTag(TemplateGrammer.OutletTagContext ctx) {
        if (currentPublisher == null) {
            ECLog.logFatal(ctx, "Outlet cannot be defined inside a template that is not defined as a publisher.");
        }
        FTOutlet outlet = new FTOutlet(ctx, currentContainer(ctx), ctx.IDENT().getText(), currentPublisher);
        currentPublisher.addOutlet(outlet);
        if (ctx.nodeDescription() != null) {
            for (int i = 0; i < ctx.nodeDescription().size(); i++) {
                outlet.addDescription(visitNodeDescription(ctx.nodeDescription(i)));
            }
        }
        currentContainer(ctx).addChild(outlet);
        push(outlet);
        return outlet;
    }

    @Override
    public FTAuthor visitAuthorTag(TemplateGrammer.AuthorTagContext ctx) {
        Set<MTNamespace> namespaces = null;
        FTPublishPhase   phase      = null;
        FTPublishScope   scope      = null;

        if (ctx.namespaceIdentList() != null) {
            namespaces = new HashSet<>();
            for (TemplateGrammer.NamespaceIdentContext namespaceIdentContext : ctx.namespaceIdentList().namespaceIdent()) {
                namespaces.add(
                        new MTNamespace(ctx, segmentsForPathID(namespaceIdentContext.IDENT()).toArray(new String[0]),
                                        false));
            }
        }

        FTAuthor parentAuthor = null;
        if (!authorStack.isEmpty()) {
            parentAuthor = authorStack.peek();
        }
        String outletName = null;
        if (ctx.Outlet() != null) {
            if (parentAuthor == null && namespaces == null) {
                ECLog.logFatal(ctx, "Cannot author to an outlet without specifying a publisher.");
            }
            outletName = ctx.IDENT().getText();
        }

        // author options
        for (TemplateGrammer.AuthorOptionContext option : ctx.authorOption()) {
            String optionName = option.identifier(0).getText();
            if (option.identifier().size() == 1) {
                ECLog.logFatal(ctx, "Invalid Publish option: " + optionName);
            }
            String optionValue = option.identifier(1).getText();
            if (optionName.equals("phase")) {
                phase = FTPublishPhase.getByName(optionValue);
                if (phase == null) {
                    ECLog.logFatal(ctx, "Publish phase not supported: " + optionValue);
                }
            } else if (optionName.equals("scope")) {
                scope = FTPublishScope.getByName(optionValue);
                if (scope == null) {
                    ECLog.logFatal(ctx, "Publish scope not supported: " + optionValue);
                }
            } else {
                ECLog.logFatal(ctx, "Publish option not supported: " + optionName);
            }
        }

        FTAuthor author = new FTAuthor(ctx, template, currentContainer(ctx), parentAuthor, namespaces, outletName,
                                       phase,
                                       scope);
        if (ctx.nodeDescription() != null) {
            for (int i = 0; i < ctx.nodeDescription().size(); i++) {
                author.addDescription(visitNodeDescription(ctx.nodeDescription(i)));
            }
        }
        if (parentAuthor == null) {
            template.addAuthor(author);
        }
        this.authorStack.push(author);
        currentContainer(ctx).addChild(author);
        push(author);
        return author;
    }
}

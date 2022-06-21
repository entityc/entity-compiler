/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template;

import org.entityc.compiler.transform.template.tree.FTAuthor;
import org.entityc.compiler.transform.template.tree.FTBaseVisitor;
import org.entityc.compiler.transform.template.tree.FTBreak;
import org.entityc.compiler.transform.template.tree.FTCall;
import org.entityc.compiler.transform.template.tree.FTCapture;
import org.entityc.compiler.transform.template.tree.FTCase;
import org.entityc.compiler.transform.template.tree.FTContinue;
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
import org.entityc.compiler.transform.template.tree.FTInstall;
import org.entityc.compiler.transform.template.tree.FTLet;
import org.entityc.compiler.transform.template.tree.FTLoad;
import org.entityc.compiler.transform.template.tree.FTLog;
import org.entityc.compiler.transform.template.tree.FTOutlet;
import org.entityc.compiler.transform.template.tree.FTPreserve;
import org.entityc.compiler.transform.template.tree.FTPrompt;
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
import org.entityc.compiler.transform.template.tree.filter.FTDomainFilter;
import org.entityc.compiler.transform.template.tree.filter.FTFilter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class TemplateDocExtractor extends FTBaseVisitor {

    private final Set<String>             callsToExternalFunctions = new HashSet<>();
    private final Set<String>             referencedDomainNames    = new HashSet<>();
    private final Map<String, FTFunction> functionsByName          = new HashMap<>();
    private final Set<String>             referencedTags           = new HashSet<>();
    private final Set<FTTemplate>         importedTemplates        = new HashSet<>();
    private       boolean                 variableReferencedDomain = false;
    private       boolean                 hasVariableTags          = false;
    private       int                     templateLevel            = 0;
    private       boolean                 generatesFiles           = false;
    private       boolean                 supportsPublishing       = false;

    public TemplateDocExtractor(FTTemplate template) {
        super(template);
    }

    public Set<FTTemplate> getImportedTemplates() {
        return importedTemplates;
    }

    public boolean isHasVariableTags() {
        return hasVariableTags;
    }

    public Set<String> getReferencedTags() {
        return referencedTags;
    }

    public Map<String, FTFunction> getFunctionsByName() {
        return functionsByName;
    }

    public Set<String> getCallsToExternalFunctions() {
        return callsToExternalFunctions;
    }

    public Set<String> getReferencedDomainNames() {
        return referencedDomainNames;
    }

    public boolean isVariableReferencedDomain() {
        return variableReferencedDomain;
    }

    public boolean hasOnlyFunctions() {
        // TODO: it might be imported into a template that has an output already setup
        // we need some other way to tell or not even implement this.
        return !supportsPublishing && !generatesFiles && functionsByName.size() > 0;
    }

    @Override
    public void visitCall(FTCall call) {
        if (!inImportBlock() && call.isExternalFunction()) {
            callsToExternalFunctions.add(call.getFunctionName());
        }
    }

    private boolean inImportBlock() {
        return templateLevel > 1;
    }

    @Override
    public void visitFile(FTFile ftFile) {
        this.generatesFiles = true;
    }

    @Override
    public void visitFunction(FTFunction ftFunction) {
        if (!inImportBlock()) {
            functionsByName.put(ftFunction.getName(), ftFunction);
        }
    }

    @Override
    public void visitFunctionArgument(FTFunctionArgument ftFunctionArgument) {

    }

    @Override
    public void visitPreserve(FTPreserve ftPreserve) {

    }

    @Override
    public void visitTemplate(FTTemplate template) {
        if (templateLevel == 1) {
            if (!importedTemplates.stream().anyMatch(new Predicate<FTTemplate>() {
                @Override
                public boolean test(FTTemplate ftTemplate) {
                    return ftTemplate.getName().equals(template.getName());
                }
            })) {
                importedTemplates.add(template);
            }
        }
        if (template.getDefaultDomainName() != null) {
            referencedDomainNames.add(template.getDefaultDomainName());
        }
        templateLevel++;
    }

    @Override
    public void visitFilterExpression(FTFilterExpression filterExpression) {
        if (filterExpression.getFilter() instanceof FTDomainFilter) {
            List<FTExpression> params = filterExpression.getParameters();
            if (params.size() > 0) {
                FTExpression domainParameter = params.get(0);
                if (domainParameter instanceof FTOperand) {
                    FTOperand domainParam = (FTOperand) domainParameter;
                    String    domainName  = domainParam.getName();
                    //ECLog.logInfo(domainParam, "Reference to Domain: " + domainName);
                    referencedDomainNames.add(domainName);
                } else {
                    variableReferencedDomain = true;
                    //ECLog.logInfo(domainParameter, "Has variable domain.");
                }
            }
        }
    }

    @Override
    public void visitMethodCall(FTMethodCall methodCall) {
        // Look for tag references
        String  methodName  = methodCall.getMethodName();
        boolean isTagMethod = methodName.endsWith("Tagged") || methodName.equals("hasTag");
        if (isTagMethod && methodCall.getArgumentCount() == 1) {
            FTExpression argExpression = methodCall.getArgument(0);
            if (argExpression instanceof FTConstant) {
                FTConstant constant = (FTConstant) argExpression;
                String     tag      = constant.getStringValue();
                //ECLog.logInfo("Found tag based method call: " + tag);
                this.addReferencedTag(tag);
            } else {
                hasVariableTags = true;
            }
        }
    }

    private void addReferencedTag(String tag) {
        if (templateLevel == 1) {
            this.referencedTags.add(tag);
        }
    }

    @Override
    public void visitTemplateEnd(FTTemplate template) {
        templateLevel--;
    }

    @Override
    public void visitPublisher(FTPublisher publisher) {
        supportsPublishing = true;
    }

    @Override
    public void visitAuthor(FTAuthor publish) {
        supportsPublishing = true;
    }

    @Override
    public void visitAuthorEnd(FTAuthor author) {

    }

    @Override
    public void visitOutlet(FTOutlet outlet) {
        supportsPublishing = true;
    }

    @Override
    public void visitSource(FTSource source) {

    }

    @Override
    public void visitBreak(FTBreak ftBreak) {

    }

    @Override
    public void visitCapture(FTCapture capture) {

    }

    @Override
    public void visitCase(FTCase ftCase) {

    }

    @Override
    public void visitContinue(FTContinue ftContinue) {

    }

    @Override
    public void visitDo(FTDo ftDo) {

    }

    @Override
    public void visitElse(FTElse ftElse) {

    }

    @Override
    public void visitElseIf(FTElseIf ftElseIf) {

    }

    @Override
    public void visitExit(FTExit exit) {

    }

    @Override
    public void visitExpressionTag(FTExpressionTag expressionTag) {

    }

    @Override
    public void visitForeach(FTForeach ftForeach) {

    }

    @Override
    public void visitIf(FTIf ftIf) {

    }

    @Override
    public void visitInstall(FTInstall install) {

    }

    @Override
    public void visitLet(FTLet let) {

    }

    @Override
    public void visitLoad(FTLoad load) {

    }

    @Override
    public void visitLog(FTLog log) {

    }

    @Override
    public void visitPrompt(FTPrompt prompt) {

    }

    @Override
    public void visitReceive(FTReceive receive) {

    }

    @Override
    public void visitReturn(FTReturn ftReturn) {

    }

    @Override
    public void visitSend(FTSend send) {

    }

    @Override
    public void visitSwitch(FTSwitch ftSwitch) {

    }

    @Override
    public void visitArray(FTArray array) {

    }

    @Override
    public void visitConstant(FTConstant constant) {

    }

    @Override
    public void visitExpression(FTExpression expression) {

    }

    @Override
    public void visitGlobalConstant(FTGlobalConstant globalConstant) {

    }

    @Override
    public void visitNamedElement(FTNamedElement namedElement) {

    }

    @Override
    public void visitOperand(FTOperand operand) {

    }

    @Override
    public void visitOperation(FTOperation operation) {

    }

    @Override
    public void visitFilter(FTFilter filter) {

    }
}

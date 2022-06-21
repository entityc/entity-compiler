/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

import org.entityc.compiler.EntityCompiler;
import org.entityc.compiler.model.MTCompiler;
import org.entityc.compiler.model.MTRoot;
import org.entityc.compiler.model.config.MTConfiguration;
import org.entityc.compiler.model.config.MTDirectory;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.config.MTTransform;
import org.entityc.compiler.model.domain.MTDomain;
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTRelationship;
import org.entityc.compiler.model.language.MTLanguage;
import org.entityc.compiler.protobuf.PBASTVisitor;
import org.entityc.compiler.protobuf.PBLoaderExtractor;
import org.entityc.compiler.util.ECLog;
import org.entityc.compiler.util.ECSessionFiles;
import org.entityc.compiler.util.ECSessionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.function.Predicate;

public class FTTransformSession {

    private static final List<String>           kUnasignable   = Arrays.asList("null", "true", "false");
    private final        MTSpace                space;
    private final        Stack<Scope>           scopeStack     = new Stack<>();
    private final        Stack<FTBodyBlock>     bodyBlockStack = new Stack<>();
    private final        Map<String, FTReceive> receiveMap     = new HashMap<>();
    private final        ECSessionFiles         sessionFiles;
    private final        Map<String, Scope>     authorScopeMap = new HashMap<>();
    private              FTTemplate             template;
    private              MTTransform            templateTransform;
    private              MTDirectory            namedOutput;
    private              MTConfiguration        configuration;
    private              boolean                pendingBreak;
    private              boolean                pendingContinue;
    private              boolean                pendingExit;
    private              boolean                pendingReturn;
    private              StringBuffer           stringOutputBuffer;
    private              boolean                debugMode;

    public FTTransformSession(MTRoot root, MTConfiguration configuration, FTTemplate template) {
        this.space = root.getSpace();
        this.configuration = configuration;
        this.template = template;
        if (EntityCompiler.isVerbose()) {
            ECLog.logInfo("============= CREATED SCOPE FOR template: " + template.getName() + " ==================");
        }
        pushScope(new Scope());
        if (configuration != null) {
            this.configuration = configuration;
            this.templateTransform = configuration.getTransformByName(template.getName());
            this.templateTransform.setVersion(this.template.getVersion());
            String outputName = this.templateTransform.getOutputNameByLocalName("primary");
            namedOutput = this.configuration.getOutputByName(outputName);
            addReadonlyNamedValue("compiler", new MTCompiler(space.getParserRuleContext()));
            addReadonlyNamedValue("configuration", configuration);
            addReadonlyNamedValue("space", space);
            addReadonlyNamedValue("rootTemplate", templateTransform);
            if (template.getDefaultDomainName() != null) {
                addReadonlyNamedValue("domain", space.getDomainWithName(template.getDefaultDomainName()));
            }
        } else {
            stringOutputBuffer = new StringBuffer();
        }
        addReadonlyNamedValue("true", Boolean.TRUE);
        addReadonlyNamedValue("false", Boolean.FALSE);
        sessionFiles = ECSessionManager.getInstance().getSessionFiles(template.getName());
    }

    public void addReadonlyNamedValue(String name, Object value) {
        scopeStack.peek().readonlyNames.add(name);
        scopeStack.peek().namedValues.put(name, value);
    }

    public MTConfiguration getConfiguration() {
        return configuration;
    }

    public boolean isPendingReturn() {
        return pendingReturn;
    }

    public void setPendingReturn(boolean pendingReturn) {
        this.pendingReturn = pendingReturn;
    }

    public boolean isPendingExit() {
        return pendingExit;
    }

    public void setPendingExit(boolean pendingExit) {
        this.pendingExit = pendingExit;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    /*
    This is used when we want to record that the file should be considered a session file but we are
    not going to execute its generating template code because it has the ifnotexist set.
     */
    public void registerFileBlock(FTFile fileBlock) {
        sessionFiles.addFilePath(fileBlock.getFullFilePath());
    }
    public void pushFileBlock(FTFile fileBlock) {
        fileBlock.getBody().clear();
        bodyBlockStack.push(fileBlock);
        sessionFiles.addFilePath(fileBlock.getFullFilePath());
    }

    public void popFileBlock() {
        if (bodyBlockStack.empty()) {
            ECLog.logFatal("Found end of file block without the file start.");
        }
        FTBodyBlock fileBlock = bodyBlockStack.pop();
        if (!(fileBlock instanceof FTFile)) {
            ECLog.logFatal("Unbalanced body blocks.");
        }
    }

    public void pushCaptureBlock(FTCapture captureBlock) {
        captureBlock.getBody().clear();
        bodyBlockStack.push(captureBlock);
    }

    public void popCaptureBlock() {
        if (bodyBlockStack.empty()) {
            ECLog.logFatal("An end of a block set was specified with no set block start.");
        }
        FTBodyBlock captureBlock = bodyBlockStack.pop();
        if (!(captureBlock instanceof FTCapture)) {
            ECLog.logFatal("Unbalanced body blocks.");
        }
        setValue(((FTCapture) captureBlock).getVariableName(), captureBlock.getBody().getText());
    }

    public void setValue(String variableName, Object value) {
        if (kUnasignable.contains(variableName)) {
            ECLog.logFatal("Cannot set a value to a reserved word: " + variableName);
        }
        Scope currentScope = scopeStack.peek();
        if (currentScope.readonlyNames.contains(variableName)) {
            ECLog.logFatal("Variable \"" + variableName + "\" is read only.");
        }
        currentScope.namedValues.put(variableName, value);
        if (value instanceof Collection) {
            scopeStack.peek().namedValues.put(variableName + "#count", ((Collection) value).size());
        }
    }

    public void pushSendBlock(FTSend sendBlock) {
        sendBlock.getBody().clear();
        bodyBlockStack.push(sendBlock);
    }

    public void popSendBlock() {
        if (bodyBlockStack.empty()) {
            ECLog.logFatal("An end of a block set was specified with no set block start.");
        }
        FTBodyBlock sendBlock = bodyBlockStack.pop();
        if (!(sendBlock instanceof FTSend)) {
            ECLog.logFatal("Unbalanced body blocks.");
        }

        String    receiveName = ((FTSend) sendBlock).getVariableName();
        FTReceive receive     = receiveMap.get(receiveName);
        if (receive == null) {
            ECLog.logFatal((FTSend) sendBlock, "Could not find receive named: " + receiveName);
        }
        receive.receive(sendBlock.getBody().getText());
    }

    public void setLoopValue(String variableName, Object value, int index, Collection list) {
        setValue(variableName, value);
        setValue(variableName + "#first", index == 0);
        setValue(variableName + "#last", index == (list.size() - 1));
        setValue(variableName + "#index", index);
        setValue(variableName + "#count", list.size());
    }

    public void pushLogBlock(FTLog logBlock) {
        logBlock.getBody().clear();
        bodyBlockStack.push(logBlock);
    }

    public void popLogBlock() {
        if (bodyBlockStack.empty()) {
            ECLog.logFatal("ERROR: An end of a block set was specified with no set block start.");
        }
        FTBodyBlock logBlock = bodyBlockStack.pop();
        if (!(logBlock instanceof FTLog)) {
            ECLog.logFatal("Unbalanced body blocks.");
        }
        ((FTLog) logBlock).log();
    }

    public void pushPromptBlock(FTPrompt promptBlock) {
        promptBlock.getBody().clear();
        bodyBlockStack.push(promptBlock);
    }

    public void popPromptBlock() {
        if (bodyBlockStack.empty()) {
            ECLog.logFatal("ERROR: An end of a block set was specified with no set block start.");
        }
        FTBodyBlock promptBlock = bodyBlockStack.pop();
        if (!(promptBlock instanceof FTPrompt)) {
            ECLog.logFatal("Unbalanced body blocks.");
        }
        ((FTPrompt) promptBlock).prompt(this);
    }

    public void pushPreserveBlock(FTPreserve preserveBlock) {
        preserveBlock.getBody().clear();
        bodyBlockStack.push(preserveBlock);
    }

    public void popPreserveBlock(FTTransformSession session) {
        if (bodyBlockStack.empty()) {
            ECLog.logFatal("ERROR: An end of a block set was specified with no set block start.");
        }
        FTBodyBlock block = bodyBlockStack.pop();
        if (!(block instanceof FTPreserve)) {
            ECLog.logFatal("Unbalanced body blocks.");
        }
        FTPreserve preserve     = (FTPreserve) block;
        MTLanguage language     = session.getSpace().getLanguageWithName(session.getTemplate().getLanguage());
        String     commentStart = "//";
        String     commentEnd   = null;
        if (language == null) {
            ECLog.logError(session.getTemplate(), "Cannot find language \"" + session.getTemplate().getLanguage() + "\" so defaulting line comment to //");
        } else if (language.getLineComment() == null) {
            if (language.getBlockCommentStart() != null && language.getBlockCommentEnd() != null) {
                commentStart = language.getBlockCommentStart();
                commentEnd = language.getBlockCommentEnd();
            } else {
                ECLog.logError("Cannot find declaration of line or block comment for language \"" + session.getTemplate().getLanguage() + "\" so defaulting line comment to //");
            }
        } else {
            commentStart = language.getLineComment();
        }
        // get parent FTFile, get old file and look for text between:
        String startMarker = preserve.getStartMarker(commentStart, commentEnd);
        String endMarker   = preserve.getEndMarker(commentStart, commentEnd);
        Optional<FTBodyBlock> optional = bodyBlockStack.stream().filter(new Predicate<FTBodyBlock>() {
            @Override
            public boolean test(FTBodyBlock ftBodyBlock) {
                return ftBodyBlock instanceof FTFile;
            }
        }).findFirst();
        if (!optional.isPresent()) {
            ECLog.logFatal("The preserve instruction must be contained within a file instruction.");
        }
        FTFile           fileBlock             = (FTFile) optional.get();
        Optional<String> extractedTextOptional = fileBlock.extract(startMarker, endMarker);
        if (!extractedTextOptional.isPresent() && preserve.getDeprecatedNames().size() > 0) {
            for (String deprecatedName : preserve.getDeprecatedNames()) {
                extractedTextOptional = fileBlock.extract(preserve.getStartMarker(commentStart, commentEnd, deprecatedName), preserve.getEndMarker(commentStart, commentEnd, deprecatedName));
                if (extractedTextOptional.isPresent()) {
                    break;
                }
            }
        }

        if (!extractedTextOptional.isPresent() || extractedTextOptional.get().equals("")) {
            extractedTextOptional = Optional.of(preserve.getBody().getText());
        }
        sendToOutput(startMarker + "\n");
        sendToOutput(extractedTextOptional.get());
        sendToOutput(endMarker + "\n");
    }

    public MTSpace getSpace() {
        return space;
    }

    public FTTemplate getTemplate() {
        return template;
    }

    protected void sendToOutput(String text) {
//        if (debugMode) {
//            ECLog.logInfo("DEBUG>> OUTPUT>> " + text);
//        }
        // this is for interface strings that contain template elements
        if (stringOutputBuffer != null) {
            stringOutputBuffer.append(text);
            return;
        }

        if (bodyBlockStack.empty()) {
            return; // ignore
        }
        FTBodyBlock bodyBlock = bodyBlockStack.peek();
        bodyBlock.getBody().append(text);
    }

    public void setTemplate(FTTemplate template) {
        this.template = template;
    }

    protected void sendToOutput(FTReceive receive) {
        boolean isDistinct = receive.isDistinct();
        receive = new FTReceive(null, isDistinct, receive.getVariableName()); //

        receiveMap.put(receive.getVariableName(), receive);
        if (bodyBlockStack.empty()) {
            return; // ignore
        }
        FTBodyBlock bodyBlock = bodyBlockStack.peek();
        bodyBlock.getBody().append(receive);
    }

    public MTDirectory getNamedOutput() {
        return namedOutput;
    }

    protected void unsetValues(Set<String> keys) {
        for (String key : keys) {
            this.removeValue(key);
        }
    }

    public void removeValue(String name) {
        scopeStack.peek().namedValues.remove(name);
    }

    public boolean isPendingBreak() {
        return pendingBreak;
    }

    public void setPendingBreak(boolean pendingBreak) {
        this.pendingBreak = pendingBreak;
    }

    public boolean isPendingContinue() {
        return pendingContinue;
    }

    public void setPendingContinue(boolean pendingContinue) {
        this.pendingContinue = pendingContinue;
    }

    public void applyLoader(FTLoad load, PBLoaderExtractor loader) {
        MTDomain currentDomain = (MTDomain) getValue(MTDomain.MTVariableDomain);
        if (currentDomain == null) {
            ECLog.logFatal(load, "Unable to run Load without first setting a domain.");
        }

        for (MTEntity entity : space.getEntities()) {
            applyLoaderOnEntity(loader, currentDomain, entity);
        }
    }

    public Object getValue(String variableName) {
//        if (debugMode) {
//            ECLog.logInfo("DEBUG>> Getting value for \"" + variableName + "\" => " + scopeStack.peek().namedValues.get(variableName));
//        }
        Scope currentScope = scopeStack.peek();
        boolean isInternalVariable = variableName.startsWith("__");
        for (Scope scope = currentScope; scope != null; scope = isInternalVariable ? scope.parentScopeForInternalVariables : scope.parentScope) {
            Object value = scope.namedValues.get(variableName);
            if (value != null) {
//                if (scope.name != null) {
//                    ECLog.logInfo("Getting value for " + variableName + " from scope: " + scope.name);
//                }
                return value;
            }
        }
        return null;
    }

    private void applyLoaderOnEntity(PBLoaderExtractor loader, MTDomain currentDomain, MTEntity entity) {
        int    nextFieldNumber = 0;
        String messageName     = currentDomain.getNameOfNode(entity);
        if (loader.isValidMessage(messageName)) {
            ArrayList<String> fields = new ArrayList<>();
            if (entity.hasPrimaryKey()) {
                MTAttribute pkAttribute = entity.getPrimaryKeyAttribute();
                fields.add(pkAttribute.getName());
            }
            for (MTAttribute attribute : entity.getAttributes()) {
                fields.add(attribute.getName());
            }
            for (MTRelationship relationship : entity.getRelationships()) {
                fields.add(relationship.getName());
            }

            for (String fieldName : fields) {
                PBASTVisitor.PBField pbField = loader.getField(messageName, fieldName);
                if (pbField == null) {
                    continue;
                }
                if (pbField.getNumber() > nextFieldNumber) {
                    nextFieldNumber = pbField.getNumber();
                }
            }
        }
        if (entity.hasPrimaryKey()) {
            MTAttribute          pkAttribute = entity.getPrimaryKeyAttribute();
            PBASTVisitor.PBField field       = loader.getField(messageName, pkAttribute.getName());
            if (field == null || field.getNumber() == 0) {
                entity.setFieldNumber(pkAttribute.getName(), ++nextFieldNumber);
            } else {
                entity.setFieldNumber(pkAttribute.getName(), field.getNumber());
            }
        }
        for (MTAttribute attribute : entity.getAttributes()) {
            PBASTVisitor.PBField field = loader.getField(messageName, attribute.getName());
            if (field == null || field.getNumber() == 0) {
                entity.setFieldNumber(attribute.getName(), ++nextFieldNumber);
            } else {
                entity.setFieldNumber(attribute.getName(), field.getNumber());
            }
        }
        for (MTRelationship relationship : entity.getRelationships()) {
            PBASTVisitor.PBField field = loader.getField(messageName, relationship.getName());
            if (field == null || field.getNumber() == 0) {
                entity.setFieldNumber(relationship.getName(), ++nextFieldNumber);
            } else {
                entity.setFieldNumber(relationship.getName(), field.getNumber());
            }
        }
    }

    public String getOutputStringValue() {
        return stringOutputBuffer.toString();
    }

    public void pushFunctionScope(FTFunction function) {
        Scope newScope = new Scope();
        newScope.parentScopeForInternalVariables = scopeStack.size() > 0 ? scopeStack.peek() : null;
        //ECLog.logInfo("------------- Pushing Function Scope: " + function.getName());
        pushScope(newScope);
    }

    public void popFunctionScope() {
        popScope();
    }

    public void pushAuthorScope(FTAuthor author) {
        String publisherId = author.getTopAuthor().getUniqueId();
        Scope  scopeToUse  = null;
        if (!authorScopeMap.containsKey(publisherId)) {
            scopeToUse = new Scope();
            scopeToUse.name = publisherId;
            authorScopeMap.put(publisherId, scopeToUse);
        } else {
            scopeToUse = authorScopeMap.get(publisherId);
        }
        scopeToUse.parentScope = scopeStack.size() > 0 ? scopeStack.peek() : null;
        scopeToUse.parentScopeForInternalVariables = scopeToUse.parentScope;
        pushScope(scopeToUse);
    }

    private void pushScope(Scope scope) {
        if (false && EntityCompiler.isVerbose()) {
            ECLog.logInfo("++++++ PUSHED SCOPE");
        }
        scopeStack.push(scope);
    }

    private void popScope() {
        if (false && EntityCompiler.isVerbose()) {
            ECLog.logInfo("------ POPPED SCOPE");
        }
        scopeStack.pop();
    }
    public void popAuthorScope() {
        popScope(); // its removed from the stack but not from the map
    }

    /**
     * Called after the template or transform has completed its run. This is used to remove any files created
     * during the last session that were not generated in this session. This can happen for instance when something
     * was renamed and a corresponding filename has essentially changed.
     */
    public void close() {
    }

    private class Scope {

        private final Map<String, Object> namedValues   = new HashMap<>();
        private final List<String>        readonlyNames = new ArrayList<>();
        String name;
        private Scope parentScope;
        private Scope parentScopeForInternalVariables;
    }
}

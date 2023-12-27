/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.formatter;

import org.entityc.compiler.model.MTCodeFormat;
import org.entityc.compiler.model.MTRoot;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.transform.template.StringTemplateTransform;
import org.entityc.compiler.transform.template.tree.FTComment;
import org.entityc.compiler.transform.template.tree.FTContainerNode;
import org.entityc.compiler.transform.template.tree.FTNode;
import org.entityc.compiler.transform.template.tree.FTTemplate;
import org.entityc.compiler.transform.template.tree.expression.FTOperation;
import org.entityc.compiler.util.ECLog;
import org.entityc.compiler.util.ECStringUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.*;

public class TemplateFormatController {

    private final static String INSTRUCTION_PREFIX = "$[";
    private final static String INSTRUCTION_SUFFIX = "]";
    private final static String INDENT = "    ";
    private final static int WRAP_MARGIN = 80;
    private final StringBuilder builder = new StringBuilder();
    private final Stack<TextSegment> textStack = new Stack<>();
    private final FormatPreferenceManager formatPreferenceManager;
    private int currentTextBodyLevel = 0;
    private boolean forceDescriptionNodes = false;

    public TemplateFormatController(MTCodeFormat codeFormat) {
        formatPreferenceManager = new FormatPreferenceManager(codeFormat);
    }

    public static String FormatCodeAsString(String code, MTCodeFormat codeFormat) {
        MTRoot root = new MTRoot(null);
        MTSpace space = new MTSpace(null, "formatSpace");
        root.setSpace(space);
        StringTemplateTransform transform = new StringTemplateTransform(root, code);
        transform.load(true);
        FTTemplate ftTemplate = transform.getTemplate();
        TemplateFormatController formatController = new TemplateFormatController(codeFormat);
        ftTemplate.format(formatController, -1);
        String resultText = formatController.getResult();
        if (resultText == null || resultText.isEmpty()) {
            resultText = code;
        }
        return resultText;
    }

    private String getResult() {
        synthesizeOutput();
        return builder.toString();
    }

    private void synthesizeOutput() {
        // resolve spacing
        for (int i = 0; i < textStack.size(); i++) {
            TextSegment segment = textStack.elementAt(i);
            TextSegment nextSegment = (i < (textStack.size() - 1)) ?
                textStack.elementAt(i + 1) :
                null;
            if (segment.type == TextSegmentType.Source) {
                continue;
            }
            ElementFormatPreference preference = (ElementFormatPreference) formatPreferenceManager.getPreferenceByName(
                ElementFormatPreference.getPreferenceName(segment.element));
            boolean requireBefore = segment.element.requiredSpaceBefore;
            if (requireBefore || (preference != null && preference.isSpaceBefore())) {
                segment.spaceBefore = true;
            }
            boolean requiredAfter = segment.element.requiredSpaceAfter && (nextSegment != null
                && !nextSegment.element.cancelPriorRequiredAfter);
            if (requiredAfter || (preference != null && preference.isSpaceAfter())) {
                segment.spaceAfter = true;
            }
        }

        removeUnnecessaryIndents();
        addNewIndents();
        performPreAlignment();
        wrapDescriptions();

        // output resolved segments
        boolean previousSegmentEnabledLineAfter = false;
        int currentCharPos = 0;
        for (int i = 0; i < textStack.size(); i++) {
            TextSegment prevPrevSegment = i > 1 ?
                textStack.elementAt(i - 2) :
                null;
            TextSegment prevSegment = i > 0 ?
                textStack.elementAt(i - 1) :
                null;
            TextSegment segment = textStack.elementAt(i);
            TextSegment nextSegment = (i < (textStack.size() - 1)) ?
                textStack.elementAt(i + 1) :
                null;
            TextSegment nextNextSegment = (i < (textStack.size() - 2)) ?
                textStack.elementAt(i + 2) :
                null;

            if (segment.type.inSourceFlow()) {
                String segmentText = segment.text;
                builder.append(segmentText);
                segment.finalCharPos = currentCharPos;
                currentCharPos = ECStringUtil.CharPositionOfLastChar(currentCharPos, segment.text);
                previousSegmentEnabledLineAfter = false;
                continue;
            }

            if (segment.enableIndent && !segment.suppressIndent) {
                for (int j = 0; j < segment.indentLevel; j++) {
                    builder.append(INDENT);
                }
                currentCharPos += INDENT.length() * segment.indentLevel;
            }
            if (segment.getAlignToSegment() != null) {
                builder.append("\n");
                for (int j = 0; j < segment.getAlignToSegment().finalCharPos; j++) {
                    builder.append(" ");
                }
                currentCharPos = segment.getAlignToSegment().finalCharPos;
            }
            else if (!previousSegmentEnabledLineAfter && segment.enableNewLineBefore) {
                builder.append("\n");
                currentCharPos = 0;
            }
            else if (segment.spaceBefore) {
                builder.append(" ");
                currentCharPos++;
            }

            boolean includeThisElement = true;

            // Look for instruction SUFFIX ( ] )
            if (nextSegment != null
                && !nextSegment.type.inSourceFlow()
                && (segment.element == InstructionBlockEndSuffix
                || segment.element == InstructionBlockStartSuffix
                || segment.element == InstructionSuffix)
                && (prevSegment.type == TextSegmentType.Comment || nextNextSegment.type == TextSegmentType.Comment ||  nextSegment.startLineNumber != segment.endLineNumber)) {
                includeThisElement = false;
            }
            // Look for instruction PREFIX ( $[, $[/ )
            if (prevSegment != null
                && !prevSegment.type.inSourceFlow()
                && (segment.element == InstructionPrefix
                || segment.element == InstructionBlockEndPrefix
                || segment.element == InstructionBlockStartPrefix)
                && (nextSegment.type == TextSegmentType.Comment || prevPrevSegment.type == TextSegmentType.Comment || prevSegment.startLineNumber != segment.endLineNumber)) {
                includeThisElement = false;
            }

            if (segment.text != null && includeThisElement) {
                // the actual segment
                builder.append(segment.text);
                segment.finalCharPos = currentCharPos;
                currentCharPos += segment.text.length();
            }
            boolean addSpace = false;
            if (segment.spaceAfter &&
                nextSegment != null
                && !nextSegment.spaceBefore
                && nextSegment.element != ConfigurableElement.RequiredSpace
                && nextSegment.element != ConfigurableElement.FunctionDelim
                && nextSegment.element != ConfigurableElement.FunctionCallDelim
                && nextSegment.element != ConfigurableElement.FunctionCallArgDelim
                && nextSegment.element != ConfigurableElement.FunctionCloseParen
                && nextSegment.element != ConfigurableElement.FunctionCallCloseParen
                && nextSegment.element != ConfigurableElement.FunctionOpenParen
                && nextSegment.element != ConfigurableElement.FunctionCallOpenParen
            ) {
                addSpace = true;
            }
            if (segment.enableNewLineAfter) {
                builder.append("\n");
                currentCharPos = 0;
            }
            else if (addSpace && !nextSegment.enableNewLineBefore) {
                builder.append(" ");
                currentCharPos++;
            }
            previousSegmentEnabledLineAfter = segment.enableNewLineAfter;
        }
    }

    void removeUnnecessaryIndents() {
        ArrayList<TextSegment> potentiallyRemovableSegments = new ArrayList<>();
        boolean foundNonBlankSource = false;
        int level = 0;
        TextSegment firstConsecutiveInstructionSegmentOfALine = null;
        boolean foundInstruction = false;
        for (int i = 0; i < textStack.size(); i++) {
            TextSegment segment = textStack.elementAt(i);
            TextSegment nextSegment = (i + 1) < textStack.size() ?
                textStack.elementAt(i + 1) :
                null;
            if (nextSegment == null) {
                continue;
            }
            if (segment.element.isInstructionRelated()) {
                foundInstruction = true;
            }
            if (firstConsecutiveInstructionSegmentOfALine == null && (
                segment.element == ConfigurableElement.InstructionPrefix
                    || segment.element
                    == ConfigurableElement.InstructionBlockStartPrefix)) {
                firstConsecutiveInstructionSegmentOfALine = segment;
            }
            else if ((segment.isVariable() && segment.element == ConfigurableElement.VariablePrefix)
                || segment.isSource()) {
                firstConsecutiveInstructionSegmentOfALine = null;
            }
            if (nextSegment.startLineNumber != -1 && segment.endLineNumber != -1
                && nextSegment.startLineNumber > segment.endLineNumber) {
                segment.enableNewLineAfter = true;
                //ECLog.logInfo(segment.endLineNumber + "," + nextSegment.startLineNumber + ": Segment \"" + segment.text + "\" forced line after.");
                if (!foundNonBlankSource && foundInstruction) {
                    for (TextSegment removeSegment : potentiallyRemovableSegments) {
                        removeSegment.text = "";
                    }
                    potentiallyRemovableSegments.clear();
                }
//                if (foundNonBlankSource && firstConsecutiveInstructionSegmentOfALine != null) {
//                    // TODO: not sure if this is really a good idea, think about it more
//                    // TODO: also it should apply this to the remaining segments on the line as well
//                    //firstConsecutiveInstructionSegmentOfALine.enableNewLineBefore = true;
//                }
                firstConsecutiveInstructionSegmentOfALine = null;
                foundNonBlankSource = false;
                foundInstruction = false;
                level = nextSegment.textBodyLevel;
                if (nextSegment.isSource() && !nextSegment.text.equals("\n") && nextSegment.isBlank()) {
                    potentiallyRemovableSegments.add(nextSegment);
                }
                else if (nextSegment.isVariable() || (nextSegment.isSource() && !nextSegment.isBlank())) {
                    potentiallyRemovableSegments.clear();
                    foundNonBlankSource = true;
                }
                continue;
            }

            if (level >= 0 && (segment.textBodyLevel == -1 || segment.textBodyLevel == level)) {
                if (segment.isSource()) {
                    if (segment.isBlank()) {
                        if (!foundNonBlankSource) {
                            potentiallyRemovableSegments.add(segment);
                        }
                    }
                    else {
                        potentiallyRemovableSegments.clear();
                        foundNonBlankSource = true;
                    }
                }
                else if (segment.type == TextSegmentType.Variable) {
                    potentiallyRemovableSegments.clear();
                    foundNonBlankSource = true;
                }
            }
        }
        // remove source segments with "" text
        Stack<TextSegment> newTextStack = new Stack<>();
        for (int i = 0; i < textStack.size(); i++) {
            TextSegment segment = textStack.elementAt(i);
            if (segment.isSource() && segment.text.equals("")) {
                continue;
            }
            newTextStack.push(segment);
        }
        textStack.clear();
        textStack.addAll(newTextStack);
    }

    private void addNewIndents() {
        for (int i = 0; i < textStack.size(); i++) {
            TextSegment segment = textStack.elementAt(i);
            TextSegment nextSegment = (i + 1) < textStack.size() ?
                textStack.elementAt(i + 1) :
                null;
            if (nextSegment == null) {
                continue;
            }
            if (nextSegment.startLineNumber != -1 && segment.endLineNumber != -1
                && nextSegment.startLineNumber > segment.endLineNumber) {
                if (nextSegment.element == ConfigurableElement.InstructionBlockStartPrefix
                    || nextSegment.element == ConfigurableElement.InstructionPrefix
                    || nextSegment.element == ConfigurableElement.InstructionBlockEndPrefix) {
                    nextSegment.enableIndent = true;
                }
            }
        }
    }

    private void performPreAlignment() {
        // As we go through the inside of instructions, make note of segments that the preferences say should be
        // aligned TO. Then when we find the FROM, match up with the TO.
        Stack<ConfigurableElementScope> currentScopeStack = new Stack<>();
        ConfigurableElementScope currentScope = ConfigurableElementScope.TopScope;
        Stack<Map<String, TextSegment>> alignToAvailableTargetsStack = new Stack<>();
        Map<String, TextSegment> alignToAvailableTargets = new HashMap<>();
        Stack<Map<String, TextSegment>> ifElementStack = new Stack<>();
        Map<String, TextSegment> ifElements = new HashMap<>();
        for (int i = 0; i < textStack.size(); i++) {
            TextSegment segment = textStack.elementAt(i);

            // LOOK FOR SCOPE START
            ConfigurableElementScope possibleNextScope = ConfigurableElementScope.FindWithStartElement(currentScope,
                segment.element);
            if (possibleNextScope != null) {
                // push this scope and the align targets, pop when it sees the end element of the scope
                currentScopeStack.push(possibleNextScope);
                currentScope = possibleNextScope;
                alignToAvailableTargetsStack.push(alignToAvailableTargets);
                alignToAvailableTargets = new HashMap<>(alignToAvailableTargets); // copy it
                ifElementStack.push(ifElements);
                ifElements = new HashMap<>(ifElements);
            }

            // LOOK FOR ALIGNMENT TARGETS
            if (formatPreferenceManager.hasAlignmentPreferenceToElement(currentScope, segment.element)) {
                // only the first occurrence can be a target
                if (!alignToAvailableTargets.containsKey(segment.element.name())) {
                    alignToAvailableTargets.put(segment.element.name(), segment);
                }
            }
            // LOOK FOR CONDITIONAL ALIGNMENT CONDITIONS
            if (formatPreferenceManager.hasAlignmentPreferenceIfElement(segment.element)) {
                ifElements.put(segment.element.name(), segment);
            }

            // PROCESS "FROM" SEGMENTS - CONNECT THEM TO TARGET SEGMENTS
            List<TextSegment> segmentsCanAlignTo = new ArrayList<>();

            for (String elementToName : alignToAvailableTargets.keySet()) {
                TextSegment alignToSegment = alignToAvailableTargets.get(elementToName);
                segmentsCanAlignTo.add(alignToSegment);
            }
            for (TextSegment alignToSegment : segmentsCanAlignTo) {
                if (formatPreferenceManager.hasAlignmentPreference(currentScope, segment.element /* from */,
                    alignToSegment.element /* to */)) {
                    ElementFormatPreference preference = formatPreferenceManager.getAlignmentPreference(currentScope,
                        segment.element,
                        alignToSegment.element);
                    if (preference.getIfBetweenElement() == null) {
                        if (segment != alignToSegment) {
                            segment.setAlignToSegment(alignToSegment);
                        }
                    }
                    else {
                        if (ifElements.containsKey(preference.getIfBetweenElement().name())) {
                            if (preference.getIfBetweenElement()
                                == ifElements.get(preference.getIfBetweenElement().name()).element) {
                                if (segment != alignToSegment) {
                                    segment.setAlignToSegment(alignToSegment);
                                }
                            }
                        }
                    }
                    break;
                }
            }

            // LOOK FOR SCOPE END
            if (currentScope != null && segment.element == currentScope.scopeEndElement) {
                currentScopeStack.pop();
                if (currentScopeStack.isEmpty()) {
                    currentScope = ConfigurableElementScope.TopScope;
                }
                else {
                    currentScope = currentScopeStack.peek();
                }
                alignToAvailableTargets = alignToAvailableTargetsStack.pop();
                ifElements = ifElementStack.pop();
            }
        }
    }

    private void wrapDescriptions() {
        Stack<TextSegment> transformedTextStack = new Stack<>();

        for (int i = 0; i < textStack.size(); i++) {
            TextSegment segment = textStack.elementAt(i);

            if (segment.element == ConfigurableElement.DescriptionString) {
                int startOfDescriptionInstruction = rewindToElement(textStack, i,
                    new ConfigurableElement[]{ConfigurableElement.Description, ConfigurableElement.InstructionName});
                // this could either be an instruction or a node, look back one to find out
                TextSegment backOneSegment = textStack.elementAt(startOfDescriptionInstruction - 1);
                boolean isDescriptionInstruction = backOneSegment.element == ConfigurableElement.InstructionPrefix;
                if (isDescriptionInstruction) {
                    startOfDescriptionInstruction--; // include this instruction prefix in what is duplicated for extra lines
                }
                Vector<TextSegment> descriptionStartSegments = getSegmentsInRange(textStack,
                    startOfDescriptionInstruction, i - 1);

                String textToWrap = ECStringUtil.ProcessParserString(segment.text);
                List<String> lines;
                if (textToWrap.contains("\n") || textToWrap.equals("")) {
                    lines = new ArrayList<>();
                    lines.add(textToWrap);
                }
                else {
                    lines = ECStringUtil.SplitIntoLines(textToWrap,
                        WRAP_MARGIN);
                }
                TextSegment endSegment = isDescriptionInstruction ?
                    textStack.elementAt(i + 1) :
                    null;
                boolean first = true;
                for (String line : lines) {
                    if (first) {
                        // reuse this node, just overwrite its text
                        segment.text = "\"" + line + "\"";
                        transformedTextStack.push(segment);
                    }
                    else {
                        if (endSegment != null) {
                            transformedTextStack.push(new TextSegment(endSegment));
                        }
                        for (TextSegment startSegment : descriptionStartSegments) {
                            transformedTextStack.push(new TextSegment(startSegment));
                        }

                        TextSegment dupSegment = new TextSegment(segment);
                        dupSegment.text = "\"" + line + "\"";
                        transformedTextStack.push(dupSegment);
                    }

                    first = false;
                }
            }
            else {
                transformedTextStack.push(segment);
            }
        }
        this.textStack.clear();
        this.textStack.addAll(transformedTextStack);
    }

    private int rewindToElement(Stack<TextSegment> textStack, int fromIndex, ConfigurableElement[] untilElements) {
        int offset = 0;
        TextSegment backSegment;
        do {
            offset--;
            if (fromIndex + offset <= 0) {
                assert (fromIndex + offset > 0);
            }
            backSegment = textStack.elementAt(fromIndex + offset);
        } while (!Arrays.stream(untilElements).anyMatch(backSegment.element::equals));
        return fromIndex + offset;
    }

    private Vector<TextSegment> getSegmentsInRange(Stack<TextSegment> textStack, int fromIndex, int toIndex) {
        Vector<TextSegment> segments = new Vector<>();
        for (int i = fromIndex; i <= toIndex; i++) {
            segments.add(textStack.elementAt(i));
        }
        return segments;
    }

    public boolean isForceDescriptionNodes() {
        return forceDescriptionNodes;
    }

    public void addSourceString(String sourceStr, int startLine, int endLine) {
        TextSegment sourceTextSegment = new TextSegment(TextSegmentType.Source, ConfigurableElement.None, startLine,
            endLine, sourceStr);
        sourceTextSegment.textBodyLevel = currentTextBodyLevel;
        textStack.push(sourceTextSegment);
    }

    public void writeResult(File file) {
        synthesizeOutput();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.append(builder);
            bufferedWriter.close();
        } catch (IOException e) {
            ECLog.logFatal("Unable to write formatted template to file: " + file.getPath());
        }
    }

    public boolean isIgnoreFailedFormatting() {
        boolean ignoreFailedFormatting = true; // TODO: make sure we fully test this
        return ignoreFailedFormatting;
    }

    public void addExpressionElement(ConfigurableElement element, String expressionText, int lineNumber) {
        TextSegment segment = new TextSegment(TextSegmentType.Expression, element, expressionText);
        segment.startLineNumber = segment.endLineNumber = lineNumber;
        segment.textBodyLevel = currentTextBodyLevel;
        textStack.push(segment);
    }

    public void addExpressionOperator(FTOperation.Operator operator, String expressionText, int lineNumber) {
        ConfigurableElement element;
        if (operator.isUnary()) {
            element = ConfigurableElement.ExpressionOperatorUnary;
        }
        else {
            switch (operator) {
                case DOT:
                    element = ConfigurableElement.ExpressionOperatorDot;
                    break;
                case EQUALS:
                    element = ConfigurableElement.ExpressionOperatorEquals;
                    break;
                default:
                    element = ConfigurableElement.ExpressionOperator;
            }
        }
        TextSegment segment = new TextSegment(TextSegmentType.Expression, element, expressionText);
        segment.startLineNumber = segment.endLineNumber = lineNumber;
        segment.operator = operator;
        segment.textBodyLevel = currentTextBodyLevel;
        textStack.push(segment);
    }

    public void addInstructionInside(ConfigurableElement element, String insideText, int lineNumber) {
        TextSegment segment = new TextSegment(TextSegmentType.Instruction, element, insideText);
        segment.startLineNumber = segment.endLineNumber = lineNumber;
        segment.textBodyLevel = currentTextBodyLevel;
        textStack.push(segment);
    }

    public void addVariableExpressionStart(String insideText, int lineNumber) {
        TextSegment segment = new TextSegment(TextSegmentType.Variable, ConfigurableElement.VariablePrefix, insideText);
        segment.startLineNumber = segment.endLineNumber = lineNumber;
        segment.textBodyLevel = currentTextBodyLevel;
        textStack.push(segment);
    }

    public void addVariableExpressionEnd(String insideText, int lineNumber) {
        TextSegment segment = new TextSegment(TextSegmentType.Variable, ConfigurableElement.VariableSuffix, insideText);
        segment.startLineNumber = segment.endLineNumber = lineNumber;
        segment.textBodyLevel = currentTextBodyLevel;
        textStack.push(segment);
    }

    public void addInstructionBlockEnd(int indentLevel, FTNode node) {
        int startLineNumber = node.getStartLineNumber();
        int endLineNumber = node.getEndLineNumber();
        if (node instanceof FTContainerNode) {
            startLineNumber = ((FTContainerNode) node).getBlockEndStartLine();
            endLineNumber = ((FTContainerNode) node).getBlockEndEndLine();
        }
        TextSegment prefixSegment = new TextSegment(TextSegmentType.Instruction,
            ConfigurableElement.InstructionPrefix,
            startLineNumber, endLineNumber, INSTRUCTION_PREFIX);
        if (node.canSuppressIndent()) {
            String instructionSettingName = InstructionFormatPreference.getPreferenceName(node.getInstructionName());
            if (formatPreferenceManager.hasPreferenceByName(instructionSettingName)) {
                InstructionFormatPreference preference = (InstructionFormatPreference) formatPreferenceManager.getPreferenceByName(
                    instructionSettingName);
                prefixSegment.suppressIndent = preference.isSuppressIndent();
            }
        }
        prefixSegment.indentLevel = indentLevel;
        prefixSegment.textBodyLevel = currentTextBodyLevel;
        textStack.push(prefixSegment);

        TextSegment instructionNameSegment = new TextSegment(TextSegmentType.Instruction,
            ConfigurableElement.InstructionNameBlockEnd,
            startLineNumber, endLineNumber, "end" + node.getInstructionName());
        instructionNameSegment.indentLevel = indentLevel;
        instructionNameSegment.textBodyLevel = currentTextBodyLevel;
        textStack.push(instructionNameSegment);

        TextSegment suffixSegment = new TextSegment(TextSegmentType.Instruction,
            InstructionBlockEndSuffix,
            startLineNumber, endLineNumber, INSTRUCTION_SUFFIX);
        suffixSegment.indentLevel = indentLevel;
        suffixSegment.textBodyLevel = currentTextBodyLevel;
        textStack.push(suffixSegment);
        if ((node instanceof FTContainerNode) && ((FTContainerNode) node).hasOwnBody()) {
            currentTextBodyLevel--;
        }
    }

    public void addSimpleInstruction(int indentLevel, FTNode node) {
        this.addInstructionStart(indentLevel, node);
        this.addInstructionEnd(node);
    }

    public void addInstructionPrefix(int indentLevel, FTNode node) {
        boolean isContainerType = node instanceof FTContainerNode;
        TextSegment startSegment = new TextSegment(TextSegmentType.Instruction,
            isContainerType ?
                ConfigurableElement.InstructionBlockStartPrefix :
                ConfigurableElement.InstructionPrefix,
            node.getStartLineNumber(), node.getEndLineNumber(),
            INSTRUCTION_PREFIX);
        String instructionSettingName = InstructionFormatPreference.getPreferenceName(node.getInstructionName());
        if (formatPreferenceManager.hasPreferenceByName(instructionSettingName)) {
            InstructionFormatPreference preference = (InstructionFormatPreference) formatPreferenceManager.getPreferenceByName(
                instructionSettingName);
            startSegment.suppressIndent = preference.isSuppressIndent();
        }
        startSegment.indentLevel = indentLevel;
        startSegment.textBodyLevel = currentTextBodyLevel;
        textStack.push(startSegment);

    }

    public void addInstructionStart(int indentLevel, FTNode node) {
        boolean isContainerType = node instanceof FTContainerNode;
        addInstructionPrefix(indentLevel, node);
        TextSegment instructionNameSegment = new TextSegment(TextSegmentType.Instruction,
            ConfigurableElement.InstructionName,
            node.getStartLineNumber(), node.getEndLineNumber(),
            node.getInstructionName());
        instructionNameSegment.indentLevel = indentLevel;
        instructionNameSegment.textBodyLevel = currentTextBodyLevel;
        textStack.push(instructionNameSegment);
        if (isContainerType && ((FTContainerNode) node).hasOwnBody()) {
            currentTextBodyLevel++;
        }
    }

    public void addInstructionEnd(FTNode node) {
        boolean isContainerType = node instanceof FTContainerNode;
        TextSegment segment = new TextSegment(TextSegmentType.Instruction,
            isContainerType ?
                ConfigurableElement.InstructionBlockStartSuffix :
                ConfigurableElement.InstructionSuffix,
            node.getStartLineNumber(), node.getEndLineNumber(), INSTRUCTION_SUFFIX);
        segment.textBodyLevel = currentTextBodyLevel;
        textStack.push(segment);
    }

    public void addExplicitInstructionStart(int indentLevel, String instructionName, int startLineNumber,
                                            int endLineNumber) {
        TextSegment startSegment = new TextSegment(TextSegmentType.Instruction,
            ConfigurableElement.InstructionPrefix,
            startLineNumber, endLineNumber, INSTRUCTION_PREFIX);
        startSegment.indentLevel = indentLevel;
        startSegment.textBodyLevel = currentTextBodyLevel;
        textStack.push(startSegment);
        TextSegment instructionNameSegment = new TextSegment(TextSegmentType.Instruction,
            ConfigurableElement.InstructionName,
            startLineNumber, endLineNumber, instructionName);
        instructionNameSegment.indentLevel = indentLevel;
        textStack.push(instructionNameSegment);
    }

    public void addExplicitInstructionEnd(int startLineNumber, int endLineNumber, boolean forceLineAfter) {
        TextSegment segment = new TextSegment(TextSegmentType.Instruction,
            ConfigurableElement.InstructionSuffix,
            startLineNumber, endLineNumber, INSTRUCTION_SUFFIX);
        segment.enableNewLineAfter = forceLineAfter;
        segment.textBodyLevel = currentTextBodyLevel;
        textStack.push(segment);
    }

    public void addComment(FTComment comment) {
        String commentText = comment.getText();
        commentText = commentText.replace("$[*", "/*").replace("*]", "*/");
        TextSegment segment = new TextSegment(TextSegmentType.Comment, ConfigurableElement.None,
            comment.getStartLineNumber(),
            comment.getEndLineNumber(), commentText);
        segment.textBodyLevel = currentTextBodyLevel;
        textStack.push(segment);
    }
}

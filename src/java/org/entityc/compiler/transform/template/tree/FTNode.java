/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

import org.entityc.compiler.model.foundation.MFObject;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.transform.template.TemplateLexer;

import java.util.ArrayList;
import java.util.List;

public abstract class FTNode extends MFObject {

    public final static int                 NO_SYMBOL = 0;
    protected           List<FTDescription> descriptionNodes;
    private             int                 startLineNumber;
    private             int                 startCharPosition;
    private             int                 endLineNumber;
    private             int                 endCharPosition;
    private             String              sourceName;
    private             String              text;

    public FTNode(ParserRuleContext ctx) {
        setParserContext(ctx);
    }

    private void setParserContext(ParserRuleContext ctx) {
        if (ctx != null) {
            startLineNumber   = ctx.start.getLine();
            startCharPosition = ctx.start.getCharPositionInLine() + 1;
            endLineNumber     = ctx.stop.getLine();
            endCharPosition   = ctx.stop.getCharPositionInLine() + 1;
            sourceName        = ctx.start.getTokenSource().getSourceName();
            text              = ctx.getText();
        }
    }

    public static String GetInstructionNameOfLexerSymbol(int symbolNumber) {
        String symbol = TemplateLexer.VOCABULARY.getLiteralName(symbolNumber);
        return symbol.substring(1, symbol.length() - 1);
    }

    public String getText() {
        return text;
    }

    public void transform(FTTransformSession session) {

    }

    public int getStartCharPosition() {
        return startCharPosition;
    }

    public int getEndLineNumber() {
        return endLineNumber;
    }

    public int getEndCharPosition() {
        return endCharPosition;
    }

    public String getSourceName() {
        return sourceName;
    }

    abstract public void accept(FTVisitor visitor);

    public boolean format(TemplateFormatController formatController, int indentLevel) {
        int symbolNumber = getTemplateLexerSymbol();
        if (symbolNumber != NO_SYMBOL) {
            formatController.addSimpleInstruction(indentLevel, this);
        }
        return false;
    }

    abstract public int getTemplateLexerSymbol();

    public boolean canSuppressIndent() {
        return false;
    }

    public String getInstructionName() {
        int symbolNumber = getTemplateLexerSymbol();
        if (symbolNumber != NO_SYMBOL) {
            String symbol = TemplateLexer.VOCABULARY.getLiteralName(symbolNumber);
            return symbol.substring(1, symbol.length() - 1);
        }
        return "<<UNKNOWN>>";
    }

    public int getStartLineNumber() {
        return startLineNumber;
    }

    public void addDescription(FTDescription description) {
        if (descriptionNodes == null) {
            descriptionNodes = new ArrayList<>();
        }
        descriptionNodes.add(description);
    }

    protected ArrayList<FTDescription> getMergedDescriptionNodes() {
        ArrayList<FTDescription> mergedDescriptionNodes    = new ArrayList<>();
        if (descriptionNodes == null) {
            return mergedDescriptionNodes;
        }
        FTDescription            lastMergedDescriptionNode = null;
        for (FTDescription descriptionNode : descriptionNodes) {
            if (descriptionNode.isMultiline() || descriptionNode.isEmpty()) {
                if (lastMergedDescriptionNode != null) {
                    mergedDescriptionNodes.add(lastMergedDescriptionNode);
                    lastMergedDescriptionNode = null;
                }
                mergedDescriptionNodes.add(descriptionNode);
                continue;
            }
            if (lastMergedDescriptionNode == null) {
                lastMergedDescriptionNode = descriptionNode;
                continue;
            }
            if (lastMergedDescriptionNode.hasSameCategories(descriptionNode)) {
                lastMergedDescriptionNode.append(descriptionNode);
            } else {
                mergedDescriptionNodes.add(lastMergedDescriptionNode);
                lastMergedDescriptionNode = descriptionNode;
            }
        }
        if (lastMergedDescriptionNode != null) {
            mergedDescriptionNodes.add(lastMergedDescriptionNode);
        }
        return mergedDescriptionNodes;
    }

    protected void addDescriptionToFormatController(TemplateFormatController formatController) {
        ArrayList<FTDescription> mergedDescriptionNodes    = getMergedDescriptionNodes();
        for (FTDescription descriptionNode : mergedDescriptionNodes) {
            descriptionNode.format(formatController);
        }
    }

    public boolean hasDescription() {
        return descriptionNodes != null && !descriptionNodes.isEmpty();
    }

    public String getDescription() {
        return this.descriptionInCategory(FTDescription.DefaultCategory);
    }

    public String descriptionInCategory(String category) {
        StringBuilder       descriptionBuilder = new StringBuilder();
        List<FTDescription> descriptions       = this.getDescriptionsForCategory(category);
        for (FTDescription description : descriptions) {
            String  text     = description.getDescriptionText();
            boolean takeAsIs = text.contains("\n");
            if (takeAsIs) {
                descriptionBuilder.append(text);
            } else if (text.equals("")) {
                descriptionBuilder.append("\n\n"); // consider this a marker between paragraphs
            } else {
                if (descriptionBuilder.length() > 0) {
                    descriptionBuilder.append(" ");
                }
                descriptionBuilder.append(text.trim());
            }
        }
        return descriptionBuilder.toString();
    }

    public List<FTDescription> getDescriptionsForCategory(String category) {
        ArrayList<FTDescription> descriptions = new ArrayList<>();
        if (descriptionNodes == null) {
            return descriptions;
        }
        for (FTDescription description : descriptionNodes) {
            if (description.hasCategory(category)) {
                descriptions.add(description);
            }
        }
        return descriptions;
    }

    public boolean hasDescriptionInCategory(String category) {
        if (descriptionNodes == null) {
            return false;
        }
        for (FTDescription description : descriptionNodes) {
            if (description.hasCategory(category)) {
                return true;
            }
        }
        return false;
    }
}

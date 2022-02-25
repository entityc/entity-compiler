/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

import org.entityc.compiler.EntityCompiler;
import org.entityc.compiler.transform.template.TemplateGrammer;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.System.exit;

public class FTContainerNode extends FTNode {

    static int            currentLine          = 0;
    static List<FTSource> possibleIndents      = new ArrayList<>();
    static int            possibleIndentsTextBodyLevel = 0;
    static int            currentTextBodyLevel = 0;

    TemplateGrammer.BlockEndContext blockEndContext;

    /**
     * A child node such that inside a foreach or if or anything
     * that represents a container.
     */
    private List<FTNode>    children = new ArrayList<>();
    private FTContainerNode parent;

    public FTContainerNode(ParserRuleContext ctx, FTContainerNode parent) {
        super(ctx);
        this.parent = parent;
    }

    // This code really should not be here, it should be pulled out into its own
    // visitor class so it doesn't polute this class
    static void checkIndent(FTNode node, int lineNumber, boolean lastNodeOfContainer) {
        if ((node instanceof FTSource)) {
            FTSource source = (FTSource) node;
            if (!source.isJustSpaces()) {
                if (lineNumber != currentLine || currentTextBodyLevel != possibleIndentsTextBodyLevel) {
                    // process previous line
                    for (FTSource s : possibleIndents) {
                        if (s.isPossibleTemplateIndent()) {
                            s.setShouldRemove(true);
                            //ECLog.logInfo("FILE " + s.getSourceName().substring(s.getSourceName().lastIndexOf('/')) + " LINE " + s.getLine() + " with \"" + s.getText().replaceAll("\n", "\\n") + "\" ++ Will be removed (just spaces).");
                        }
                    }
                    currentLine = lineNumber;
                    possibleIndents.clear();
                    return;
                }
                //ECLog.logInfo("RESETTING||LINE " + source.getLine() + " with \"" + source.getText().replaceAll("\n", "\\\\n") + "\" xx");
                currentLine = 0;
                possibleIndents.clear();
                return;
            }
            if (lineNumber != currentLine) {
                // process previous line
                for (FTSource s : possibleIndents) {
                    if (s.isPossibleTemplateIndent()) {
                        s.setShouldRemove(true);
                        //ECLog.logInfo("FILE " + s.getSourceName().substring(s.getSourceName().lastIndexOf('/')) + " LINE " + s.getLine() + " with \"" + s.getText().replaceAll("\n", "\\n") + "\" ++ Will be removed (different line). last: " + lastNodeOfContainer);
                    }
                }
                currentLine = lineNumber;
                possibleIndents.clear();
            }
            //ECLog.logInfo("QUEUING||LINE " + source.getLine() + " with \"" + source.getText().replaceAll("\n", "\\n") + "\" ++ last: " + lastNodeOfContainer);
            possibleIndents.add(source);
            possibleIndentsTextBodyLevel = currentTextBodyLevel;
            if ((source.isJustSpaces() && lastNodeOfContainer)) {
                // process previous line
                for (FTSource s : possibleIndents) {
                    if (s.isPossibleTemplateIndent()) {
                        s.setShouldRemove(true);
                        //ECLog.logInfo("FILE " + s.getSourceName().substring(s.getSourceName().lastIndexOf('/')) + " LINE " + s.getLine() + " with \"" + s.getText().replaceAll("\n", "\\n") + "\" ++ Will be removed (last line). last: " + lastNodeOfContainer);
                    }
                }
                currentLine = lineNumber;
                possibleIndents.clear();
            }
        } else if (node instanceof FTExpressionTag && (currentTextBodyLevel == possibleIndentsTextBodyLevel)) {
            if (lineNumber != currentLine) {
                // process previous line
                for (FTSource s : possibleIndents) {
                    if (s.isPossibleTemplateIndent()) {
                        s.setShouldRemove(true);
                        //ECLog.logInfo("LINE " + s.getLine() + " with \"" + s.getText().replaceAll("\n", "\\n") + "\" ++ Will be removed.");
                    }
                }
                currentLine = lineNumber;
                possibleIndents.clear();
                return;
            }
            //ECLog.logInfo("RESETTING||LINE " + node.getStartLineNumber() + " with \"" + node.getText().replaceAll("\n", "\\n") + "\" xx {} RESETTING");
            currentLine = 0;
            possibleIndents.clear();
        }
    }

    public TemplateGrammer.BlockEndContext getBlockEndContext() {
        return blockEndContext;
    }

    public void setBlockEndContext(TemplateGrammer.BlockEndContext blockEndContext) {
        this.blockEndContext = blockEndContext;
    }

    public boolean isSameAsContext(TemplateGrammer.BlockEndContext ctx) {
        return ctx.getChildCount() > 1 && ctx.getChild(1).getText().equals(getInstructionName());
    }

    FTContainerNode getParent() {
        return parent;
    }

    public void insertTopContainer(FTContainerNode otherContainer) {
        for (FTNode child : children) {
            otherContainer.addChild(child); // handles setting parent also
        }
        children = new ArrayList<>(); // all moved so now reset
        this.addChild(otherContainer);
    }

    public void addChild(FTNode child) {
        if (child == this) {
            System.err.println("HEY DONT ADD YOURSELF AS A CHILD!!");
            exit(1);
        }
        this.children.add(child);
        if (child instanceof FTContainerNode) {
            ((FTContainerNode) child).parent = this;
        }
    }

    public FTFunction findFunctionWithName(String functionName) {
        if (EntityCompiler.isVerbose()) {
            ECLog.logInfo("Looking for function " + functionName + "() in a container of class " + this.getClass().getSimpleName());
            if (this instanceof FTTemplate) {
                ECLog.logInfo("Looking for function " + functionName + "() in template " + ((FTTemplate)this).getName());
                if (children.size() == 0) {
                    ECLog.logError("NO CHILDREN!!");
                }
            }
        }
        for (int i = children.size() - 1; i >= 0; i--) {
            FTNode child = children.get(i);
            if (child instanceof FTFunction && ((FTFunction) child).getName().equals(functionName)) {
                return (FTFunction) child;
            } else if (child instanceof FTTemplate) {
                if (((FTTemplate) child).hasFunctionWithName(functionName)) {
                    if (EntityCompiler.isVerbose()) {
                        ECLog.logInfo("Looking for function " + functionName + "() in template " + ((FTTemplate)child).getName());
                    }
                    return ((FTTemplate) child).getFunctionWithName(functionName);
                } else {
                }
            }
        }
        if (parent != null) {
            return parent.findFunctionWithName(functionName);
        }
        return null;
    }

    public void resolveFunctionCalls(FTNode insideNode) {
        if (insideNode instanceof FTContainerNode) {
            for (FTNode childNode : ((FTContainerNode) insideNode).getChildren()) {
                resolveFunctionCalls(childNode);
            }
        } else {
            if (insideNode instanceof FTCall) {
                ((FTCall) insideNode).resolve(this);
            }
        }
    }

    public void transform(FTTransformSession session) {
        //ECLog.logInfo(this, "Transforming container: " + this.getClass().getSimpleName());
        transformChildren(session, false);
    }

    void transformChildren(FTTransformSession session, boolean skipLastIfElse) {
        Iterator<FTNode> it = children.iterator();
        while (it.hasNext()) {
            if (session.isPendingExit()) {
                break;
            }
            FTNode child = it.next();
            if (skipLastIfElse && !it.hasNext() && (child instanceof FTElseIf || child instanceof FTElse)) {
                break;
            }
            child.transform(session);
            if (session.isPendingBreak() || session.isPendingContinue() || session.isPendingReturn()) {
                break;
            }
        }
    }

    public void processIndents(boolean top) {
        if (top) {
            currentLine = 0;
            possibleIndents.clear();
        }
        //ECLog.logInfo("PROCESSING INDENTS FOR: " + this.getClass().getSimpleName() + " has " + getChildren().size() + " children");
        int i     = 0;
        int limit = getChildren().size();
        for (FTNode child : getChildren()) {
            if (child == null) {
                continue;
            }
            checkIndent(child, child.getStartLineNumber(), i == limit - 1);
            if (child instanceof FTContainerNode) {
                if (((FTContainerNode) child).hasOwnBody()) {
                    //ECLog.logInfo(child, "+++ TEXT BODY INDENT");
                    currentTextBodyLevel++;
                }
                ((FTContainerNode) child).processIndents(false);
                if (((FTContainerNode) child).hasOwnBody()) {
                    //ECLog.logInfo(child, "--- TEXT BODY INDENT");
                    currentTextBodyLevel--;
                }
            }
            i++;
        }
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;
        super.format(formatController, indentLevel);
        if (!formatChildren(formatController, indentLevel)) {
            success = false;
        }
        formatController.addInstructionBlockEnd(indentLevel, this);
        return success;
    }

    public boolean formatChildren(TemplateFormatController format, int indentLevel) {
        boolean success = true;
        for (FTNode child : getChildren()) {
            if (child instanceof FTDescription) {
                continue;
            }
            if (!child.format(format, indentLevel + 1)) {
                if (!format.isIgnoreFailedFormatting()) {
                    success = false;
                    break;
                }
            }
        }
        return success;
    }

    public List<FTNode> getChildren() {
        return children;
    }

    @Override
    public void accept(FTVisitor visitor) {
        visitor.visit(this);
        for (FTNode child : children) {
            child.accept(visitor);
        }
        visitor.visitEnd(this);
    }

    @Override
    public int getTemplateLexerSymbol() {
        return NO_SYMBOL;
    }

    /**
     * Indicates if a container's child FTSource nodes
     * belong to its own body of text. Classes that extend
     * this class should override this method.
     *
     * @return true if it does.
     */
    public boolean hasOwnBody() {
        return false;
    }
}

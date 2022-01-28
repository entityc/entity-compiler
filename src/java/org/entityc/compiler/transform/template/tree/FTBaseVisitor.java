/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

import org.entityc.compiler.transform.template.tree.expression.FTFilterExpression;
import org.entityc.compiler.transform.template.tree.expression.FTMethodCall;

public abstract class FTBaseVisitor implements FTVisitor {

    protected FTTemplate template;

    public FTBaseVisitor(FTTemplate template) {
        this.template = template;
    }

    @Override
    public void visit(FTNode node) {
        if (node instanceof FTTemplate) {
            visitTemplate((FTTemplate) node);
        } else if (node instanceof FTCall) {
            visitCall((FTCall) node);
        } else if (node instanceof FTFile) {
            visitFile((FTFile) node);
        } else if (node instanceof FTFunction) {
            visitFunction((FTFunction) node);
        } else if (node instanceof FTFunctionArgument) {
            visitFunctionArgument((FTFunctionArgument) node);
        } else if (node instanceof FTPreserve) {
            visitPreserve((FTPreserve) node);
        } else if (node instanceof FTFilterExpression) {
            visitFilterExpression((FTFilterExpression) node);
        } else if (node instanceof FTMethodCall) {
            visitMethodCall((FTMethodCall) node);
        } else if (node instanceof FTPublisher) {
            visitPublisher((FTPublisher) node);
        } else if (node instanceof FTAuthor) {
            visitAuthor((FTAuthor) node);
        } else if (node instanceof FTOutlet) {
            visitOutlet((FTOutlet) node);
        } else if (node instanceof FTSource) {
            visitSource((FTSource) node);
        } else if (node instanceof FTExpressionTag) {
            visitExpressionTag((FTExpressionTag) node);
        } else if (node instanceof FTExit) {
            visitExit((FTExit) node);
        } else if (node instanceof FTContinue) {
            visitContinue((FTContinue) node);
        } else if (node instanceof FTBreak) {
            visitBreak((FTBreak) node);
        }
    }

    @Override
    public void visitEnd(FTNode node) {
        if (node instanceof FTTemplate) {
            visitTemplateEnd((FTTemplate) node);
        } else if (node instanceof FTAuthor) {
            visitAuthorEnd((FTAuthor) node);
        }
    }
}

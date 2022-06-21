/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

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

// Only implements a subset for now.
public interface FTVisitor {

    void visit(FTNode node);

    void visitEnd(FTNode node);

    void visitCall(FTCall call);

    void visitFile(FTFile ftFile);

    void visitFunction(FTFunction ftFunction);

    void visitFunctionArgument(FTFunctionArgument ftFunctionArgument);

    void visitPreserve(FTPreserve ftPreserve);

    void visitTemplate(FTTemplate template);

    void visitFilterExpression(FTFilterExpression filterExpression);

    void visitMethodCall(FTMethodCall methodCall);

    void visitTemplateEnd(FTTemplate template);

    void visitPublisher(FTPublisher publisher);

    void visitAuthor(FTAuthor author);

    void visitAuthorEnd(FTAuthor author);

    void visitOutlet(FTOutlet outlet);

    void visitSource(FTSource source);

    void visitBreak(FTBreak ftBreak);

    void visitCapture(FTCapture capture);

    void visitCase(FTCase ftCase);

    void visitContinue(FTContinue ftContinue);

    void visitDo(FTDo ftDo);

    void visitElse(FTElse ftElse);

    void visitElseIf(FTElseIf ftElseIf);

    void visitExit(FTExit exit);

    void visitExpressionTag(FTExpressionTag expressionTag);

    void visitForeach(FTForeach ftForeach);

    void visitIf(FTIf ftIf);

    void visitInstall(FTInstall install);

    void visitLet(FTLet let);

    void visitLoad(FTLoad load);

    void visitLog(FTLog log);

    void visitPrompt(FTPrompt prompt);

    void visitReceive(FTReceive receive);

    void visitReturn(FTReturn ftReturn);

    void visitSend(FTSend send);

    void visitSwitch(FTSwitch ftSwitch);

    void visitArray(FTArray array);

    void visitConstant(FTConstant constant);

    void visitExpression(FTExpression expression);

    void visitGlobalConstant(FTGlobalConstant globalConstant);

    void visitNamedElement(FTNamedElement namedElement);

    void visitOperand(FTOperand operand);

    void visitOperation(FTOperation operation);

    void visitFilter(FTFilter filter);
}

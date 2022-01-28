/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

public interface CSVisitor {

    void visit(CSNode node);

    void visitTop(CSTop top);

    void visitSourceFile(CSSourceFile sourceFile);

    void visitHeaderFile(CSHeaderFile headerFile);

    void visitEnum(CSEnum sourceEnum);

    void visitClass(CSClass sourceClass);

    void visitVariableDeclaration(CSVariableDeclaration declaration);

    void visitMethodArgument(CSMethodArgument methodArgument);

    void visitMethod(CSMethod method);

    void visitMethodPrototype(CSMethodPrototype methodPrototype);

    void visitExpression(CSExpression expression);

    void visitReturnStatement(CSReturnStatement s);

    void visitAssignment(CSAssignment assignment);

    void visitMethodInvocation(CSMethodInvocation methodInvocation);

    void visitStatement(CSStatement statement);

    void visitMap(CSMap map);

    void visitBetween(Class after, Class before);

    void visitFunction(CSFunction function);

    void visitFunctionPrototype(CSFunctionPrototype functionPrototype);

    void visitSwitch(CSSwitch csSwitch);

    void visitCase(CSCase csCase);

    void visitStringConstant(CSStringConstant stringConstant);

    void visitIfElse(CSIfElse ifElse);
}

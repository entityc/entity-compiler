/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

public abstract class CSBaseVisitor implements CSVisitor {

    public void visit(CSNode node) {
        if (node.isVisited()) {
            return;
        }
        if (node instanceof CSTop) {
            visitTop((CSTop) node);
        } else if (node instanceof CSHeaderFile) {
            visitHeaderFile((CSHeaderFile) node);
        } else if (node instanceof CSSourceFile) {
            visitSourceFile((CSSourceFile) node);
        } else if (node instanceof CSEnum) {
            visitEnum((CSEnum) node);
        } else if (node instanceof CSClass) {
            visitClass((CSClass) node);
        } else if (node instanceof CSVariableDeclaration) {
            visitVariableDeclaration((CSVariableDeclaration) node);
        } else if (node instanceof CSFunction) {
            visitFunction((CSFunction) node);
        } else if (node instanceof CSFunctionPrototype) {
            visitFunctionPrototype((CSFunctionPrototype) node);
        } else if (node instanceof CSMethod) {
            visitMethod((CSMethod) node);
        } else if (node instanceof CSMethodPrototype) {
            visitMethodPrototype((CSMethodPrototype) node);
        } else if (node instanceof CSReturnStatement) {
            visitReturnStatement((CSReturnStatement) node);
        } else if (node instanceof CSAssignment) {
            visitAssignment((CSAssignment) node);
        } else if (node instanceof CSMethodInvocation) {
            visitMethodInvocation((CSMethodInvocation) node);
        } else if (node instanceof CSMap) {
            visitMap((CSMap) node);
        } else if (node instanceof CSIfElse) {
            visitIfElse((CSIfElse) node);
        } else if (node instanceof CSSwitch) {
            visitSwitch((CSSwitch) node);
        } else if (node instanceof CSCase) {
            visitCase((CSCase) node);
        } else if (node instanceof CSStringConstant) {
            visitStringConstant((CSStringConstant) node);
            // the CSStatement should be LAST - after its subclasses
        } else if (node instanceof CSStatement) {
            visitStatement((CSStatement) node);
        }
    }

    public abstract void visitBetween(Class after, Class before);
}

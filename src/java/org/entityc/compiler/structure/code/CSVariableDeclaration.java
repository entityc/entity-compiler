/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

public class CSVariableDeclaration extends CSAnnotatableNode {

    private final CSVariable     variable;
    private final CSMemberAccess memberAccess;
    private       CSMemberScope  memberScope;
    private       boolean        definition;
    private       boolean        weakReference;
    private       CSComment      comment;
    private       CSStatement    assignmentStatement;

    public CSVariableDeclaration(CSVariable variable, CSMemberAccess memberAccess) {
        this.variable = variable;
        this.memberAccess = memberAccess;
    }

    public CSVariable getVariable() {
        return variable;
    }

    public void accept(CSVisitor visitor) {
        visitor.visit(variable);
    }

    public CSMemberAccess getMemberAccess() {
        return memberAccess;
    }

    public boolean isDefinition() {
        return definition;
    }

    public void setDefinition(boolean definition) {
        this.definition = definition;
    }

    public boolean isWeakReference() {
        return weakReference;
    }

    public void setWeakReference(boolean weakReference) {
        this.weakReference = weakReference;
    }

    public CSComment getComment() {
        return comment;
    }

    public void setComment(CSComment comment) {
        this.comment = comment;
    }

    public CSStatement getAssignmentStatement() {
        return assignmentStatement;
    }

    public void setAssignmentStatement(CSStatement assignmentExpression) {
        this.assignmentStatement = assignmentExpression;
    }

    public CSMemberScope getMemberScope() {
        return memberScope;
    }

    public void setMemberScope(CSMemberScope memberScope) {
        this.memberScope = memberScope;
    }
}

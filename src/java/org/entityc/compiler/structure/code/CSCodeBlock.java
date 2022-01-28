/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

import java.util.ArrayList;
import java.util.List;

public class CSCodeBlock extends CSAnnotatableNode {

    List<CSVariableDeclaration> declarations = new ArrayList<>();
    List<CSStatement>           statements   = new ArrayList<>();

    public List<CSVariableDeclaration> getVariableDeclarations() {
        return declarations;
    }

    public void addVariableDeclaration(CSVariableDeclaration declaration) {
        declarations.add(declaration);
        declaration.setParentNode(this);
    }

    public void addStatement(CSStatement statement) {
        statements.add(statement);
        statement.setParentNode(this);
    }

    public void accept(CSVisitor visitor) {
        for (CSVariableDeclaration declaration : declarations) {
            visitor.visit(declaration);
        }
        if (declarations.size() > 0) {
            visitor.visitBetween(CSVariableDeclaration.class, CSStatement.class);
        }
        for (CSStatement statement : statements) {
            visitor.visit(statement);
        }
    }

    public List<CSStatement> getStatements() {
        return statements;
    }
}

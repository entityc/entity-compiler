/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.sql;

public interface SSVisitor {

    void visit(SSNode node);

    void visitSchema(SSSchema schema);

    void visitSourceFile(SSSourceFile sourceFile);

    void visitTable(SSTable table);

    void visitColumn(SSColumn column);

    void visitIndex(SSIndex index);

    void visitView(SSView view);

    void visitSequence(SSSequence sequence);
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.sql;

public abstract class SSBaseVisitor implements SSVisitor {

    public void visit(SSNode node) {
        if (node instanceof SSSchema) {
            visitSchema((SSSchema) node);
        } else if (node instanceof SSSourceFile) {
            visitSourceFile((SSSourceFile) node);
        } else if (node instanceof SSTable) {
            visitTable((SSTable) node);
        } else if (node instanceof SSColumn) {
            visitColumn((SSColumn) node);
        } else if (node instanceof SSIndex) {
            visitIndex((SSIndex) node);
        } else if (node instanceof SSView) {
            visitView((SSView) node);
        } else if (node instanceof SSSequence) {
            visitSequence((SSSequence) node);
        }
    }
}

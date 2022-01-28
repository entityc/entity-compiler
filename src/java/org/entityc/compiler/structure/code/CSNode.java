/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

public abstract class CSNode {

    private boolean visited;
    private CSNode  parentNode;

    abstract public void accept(CSVisitor visitor);

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public CSNode getParentNode() {
        return parentNode;
    }

    protected void setParentNode(CSNode parentNode) {
        this.parentNode = parentNode;
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CSNamespaceTreeNode extends CSNode {

    private final Map<String, CSNamespaceTreeNode> children = new HashMap<>();
    private       String                           name;
    private       CSNamespaceTreeNode              parent;

    public CSNamespaceTreeNode() {

    }

    private CSNamespaceTreeNode(CSNamespaceTreeNode parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    @Override
    public void accept(CSVisitor visitor) {

    }

    public String getName() {
        return name;
    }

    public CSNamespaceTreeNode getParent() {
        return parent;
    }

    private CSNamespaceTreeNode addChild(String name) {

        CSNamespaceTreeNode child = new CSNamespaceTreeNode(this, name);
        children.put(name, child);
        return child;
    }

    private void buildNamePath(ArrayList<String> namePath) {
        if (parent != null) {
            parent.buildNamePath(namePath);
        }
        namePath.add(name);
    }

    public Collection<String> getNamePath() {
        ArrayList<String> namePath = new ArrayList<>();
        buildNamePath(namePath);
        return namePath;
    }
}

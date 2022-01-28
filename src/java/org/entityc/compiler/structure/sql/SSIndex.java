/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.sql;

import java.util.ArrayList;
import java.util.List;

public class SSIndex extends SSNode {

    private final String name;

    private final SSTable        table;
    private final List<SSColumn> columns = new ArrayList<>();

    public SSIndex(String name, SSTable table) {
        this.name = name;
        this.table = table;
    }

    public SSIndex(String name, SSTable table, SSColumn column) {
        this.name = name;
        this.table = table;
        this.addColumn(column);
    }

    public void addColumn(SSColumn column) {
        columns.add(column);
    }

    public SSTable getTable() {
        return table;
    }

    @Override
    public void accept(SSVisitor visitor) {

    }

    public boolean equals(Object otherObject) {
        if (!(otherObject instanceof SSIndex)) {
            return false;
        }
        SSIndex otherIndex = (SSIndex) otherObject;

        if (!otherIndex.getName().equals(name)) {
            return false;
        }

        if (columns.size() != otherIndex.getColumns().size()) {
            return false;
        }

        for (SSColumn c : columns) {
            if (!otherIndex.getColumns().contains(c)) {
                return false;
            }
        }

        return true;
    }

    public String getName() {
        return name;
    }

    public List<SSColumn> getColumns() {
        return columns;
    }
}

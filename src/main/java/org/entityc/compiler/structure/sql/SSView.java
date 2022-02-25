/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.sql;

import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.List;

public class SSView extends SSNode {

    private final String         name;
    private final String         fullname;
    private final SSTable        primaryTable;
    private final List<SSColumn> columns = new ArrayList<>();

    public SSView(String name, SSTable primaryTable, String fullname) {
        this.name = name;
        this.primaryTable = primaryTable;
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public SSTable getPrimaryTable() {
        return primaryTable;
    }

    public String getName() {
        return name;
    }

    public List<SSColumn> getColumns() {
        return columns;
    }

    public void addColumn(SSColumn column) {
        if (column != null) {
            this.columns.add(column);
        } else {
            ECLog.logError("Trying to add null column to table: " + primaryTable.getName() + " for view " + name);
        }
    }

    @Override
    public void accept(SSVisitor visitor) {

    }
}

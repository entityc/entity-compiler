/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.sql;

import java.util.ArrayList;
import java.util.List;

public class SSUniqueConstraint extends SSNode {

    private final List<String> columnNames = new ArrayList<>();

    public SSUniqueConstraint() {
        super();
    }

    public SSUniqueConstraint(String firstColumnName) {
        super();
        columnNames.add(firstColumnName);
    }

    public void addColumnName(String columnName) {
        columnNames.add(columnName);
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    @Override
    public void accept(SSVisitor visitor) {

    }
}

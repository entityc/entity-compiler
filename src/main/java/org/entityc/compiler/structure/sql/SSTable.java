/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SSTable extends SSNode {

    private final String                    name;
    private final String                    entityName;
    private final List<SSColumn>            columns                      = new ArrayList<>();
    private final Map<String, SSColumn>     columnByAttributeName        = new HashMap<>();
    private final List<SSForeignKey>        foreignKeys                  = new ArrayList<>();
    private final Map<String, SSForeignKey> foreignKeyByRelationshipName = new HashMap<>();
    private final List<SSUniqueConstraint>  uniqueConstraints            = new ArrayList<>();
    private       SSComment                 comment;

    public SSTable(String tableName, String entityName) {
        this.name       = tableName;
        this.entityName = entityName;
    }

    public List<SSColumn> getColumns() {
        return columns;
    }

    public void addColumn(SSColumn column) {
        columns.add(column);
        columnByAttributeName.put(column.getAttributeName(), column);
        column.setTable(this);
    }

    public void addForeignKey(SSForeignKey foreignKey) {
        for (SSForeignKey fk : foreignKeys) {
            if (foreignKey.equals(fk)) {
                return; // don't add duplicates
            }
        }
        foreignKeys.add(foreignKey);
        foreignKeyByRelationshipName.put(foreignKey.getRelationshipName(), foreignKey);
    }

    public void removeForeignKey(SSForeignKey foreignKey) {
        foreignKeys.remove(foreignKey);
    }

    public boolean hasForeignKeys() {
        return foreignKeys != null && foreignKeys.size() > 0;
    }

    public SSColumn getColumnByAttributeName(String attributeName) {
        return columnByAttributeName.get(attributeName);
    }

    public SSForeignKey getForeignKeyByRelationshipName(String relationshipName) {
        return foreignKeyByRelationshipName.get(relationshipName);
    }

    @Override
    public void accept(SSVisitor visitor) {
        for (SSColumn column : columns) {
            visitor.visit(column);
        }
    }

    public String getEntityName() {
        return entityName;
    }

    public boolean hasForeignKeyToTable(SSTable otherTable) {
        return foreignKeyToTable(otherTable) != null;
    }

    public SSForeignKey foreignKeyToTable(SSTable otherTable) {
        for (SSForeignKey foreignKey : foreignKeys) {
            if (foreignKey.getForeignTableName().equals(otherTable.getName())) {
                return foreignKey;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public boolean hasForeignKey(SSForeignKey foreignKey) {
        for (SSForeignKey fk : getForeignKeys()) {
            if (fk.equals(foreignKey)) {
                return true;
            }
        }
        return false;
    }

    public List<SSForeignKey> getForeignKeys() {
        return foreignKeys;
    }

    public SSColumn getLocalColumnWithRelationshipName(String relationshipName) {
        SSForeignKey fk = foreignKeyByRelationshipName.get(relationshipName);
        if (fk == null) {
            return null;
        }
        return fk.getLocalColumn();
    }

    public SSComment getComment() {
        return comment;
    }

    public void setComment(SSComment comment) {
        this.comment = comment;
    }

    public List<SSUniqueConstraint> getUniqueConstraints() {
        return uniqueConstraints;
    }

    public void addUniqueConstraint(SSUniqueConstraint uniqueConstraint) {
        this.uniqueConstraints.add(uniqueConstraint);
    }

    public SSColumn getPrimaryKeyColumn() {
        for (SSColumn column : columns) {
            if (column.isPrimaryKey()) {
                return column;
            }
        }
        return null;
    }
}

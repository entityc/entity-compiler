/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.sql;

public class SSForeignKey extends SSNode {

    private final String   relationshipName;
    private final SSColumn localColumn;
    private final String   foreignColumnName;
    private final String   foreignTableName;
    private final boolean  deleteCascade;
    private       boolean  declareInAlterTable;

    public SSForeignKey(String relationshipName, SSColumn localColumn, String foreignTableName, String foreignColumnName, boolean deleteCascade) {
        this.relationshipName = relationshipName;
        this.localColumn = localColumn;
        this.foreignTableName = foreignTableName;
        this.foreignColumnName = foreignColumnName;
        this.deleteCascade = deleteCascade;
    }

    public String getRelationshipName() {
        return relationshipName;
    }

    public boolean isDeleteCascade() {
        return deleteCascade;
    }

    @Override
    public void accept(SSVisitor visitor) {

    }

    public boolean isDeclareInAlterTable() {
        return declareInAlterTable;
    }

    public void setDeclareInAlterTable(boolean declareInAlterTable) {
        this.declareInAlterTable = declareInAlterTable;
    }

    public boolean equals(Object otherObject) {
        if (!(otherObject instanceof SSForeignKey)) {
            return false;
        }
        SSForeignKey otherForeignKey = (SSForeignKey) otherObject;

        if (!otherForeignKey.getLocalColumn().equals(localColumn)) {
            return false;
        }

        if (!otherForeignKey.getForeignTableName().equals(getForeignTableName())) {
            return false;
        }

        return otherForeignKey.getForeignColumnName().equals(getForeignColumnName());
    }

    public SSColumn getLocalColumn() {
        return localColumn;
    }

    public String getForeignTableName() {
        return foreignTableName;
    }

    public String getForeignColumnName() {
        return foreignColumnName;
    }
}

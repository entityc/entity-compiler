/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.sql;

import org.entityc.compiler.util.ECLog;

public class SSColumn extends SSNode {

    private final SSType    type;
    private final String    name;
    private final String    attributeName;
    private       String    tableName;
    private       String    asName;
    private       boolean   primaryKey;
    private       boolean   unique;
    private       boolean   notNull;
    private       Integer   lengthConstraint;
    private       boolean   parent;
    private       boolean   sequential;
    private       SSColumn  parentIdColumn;
    private       boolean   creation;
    private       boolean   modification;
    private       SSColumn  foriegnKeyColumn;
    private       SSComment comment;
    private       int       sinceSchemaVersion;
    private       Long      defaultValue;

    public SSColumn(SSType type, String columnName, String attributeName) {
        this.type               = type;
        this.name               = columnName;
        this.attributeName      = attributeName;
        this.sinceSchemaVersion = 0; // unknown
        if (columnName == null) {
            int l = columnName.length();
            ECLog.logInfo("column name has " + l + " characters.");
            ECLog.logFatal("Can't create a column without a name for attribute: " + attributeName);
        }
    }

    public Long getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Long defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean hasDefaultValue() {
        return defaultValue != null;
    }

    public boolean isParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }

    public int getSinceSchemaVersion() {
        return sinceSchemaVersion;
    }

    public void setSinceSchemaVersion(int sinceSchemaVersion) {
        this.sinceSchemaVersion = sinceSchemaVersion;
    }

    public Integer getLengthConstraint() {
        return lengthConstraint;
    }

    public void setLengthConstraint(Integer lengthConstraint) {
        this.lengthConstraint = lengthConstraint;
    }

    public String getAsName() {
        return asName;
    }

    public void setAsName(String asName) {
        this.asName = asName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTable(SSTable table) {
        if (table == null) {
            ECLog.logError("Trying to set a null table on column: " + name);
            String forceCrash = null;
            try {
                forceCrash.length();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            return;
        }
        if (this.tableName != null) {
            ECLog.logFatal("Can't set a column table more than once!");
        }
        this.tableName = table.getName();
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(SSVisitor visitor) {

    }

    public SSType getType() {
        return type;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public SSColumn getForiegnKeyColumn() {
        return foriegnKeyColumn;
    }

    public void setForiegnKeyColumn(SSColumn foriegnKeyColumn) {
        this.foriegnKeyColumn = foriegnKeyColumn;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public String getAttributeName() {
        return attributeName;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SSColumn)) {
            return false;
        }
        SSColumn otherForiegnKeyColumn = ((SSColumn) obj).getForiegnKeyColumn();
        if (this.name == null) {
            ECLog.logFatal("Column with no name??");
        }
        return this.name.equals(((SSColumn) obj).getName())
               && this.type == ((SSColumn) obj).getType()
               && this.primaryKey == ((SSColumn) obj).isPrimaryKey()
               && this.unique == ((SSColumn) obj).isUnique()
               && this.notNull == ((SSColumn) obj).isNotNull()
               && ((this.foriegnKeyColumn == null && otherForiegnKeyColumn == null)
                   || this.foriegnKeyColumn != null
                      && otherForiegnKeyColumn != null
                      && this.foriegnKeyColumn.equals(otherForiegnKeyColumn));
    }

    public boolean isCreation() {
        return creation;
    }

    public void setCreation(boolean creation) {
        this.creation = creation;
    }

    public boolean isModification() {
        return modification;
    }

    public void setModification(boolean modification) {
        this.modification = modification;
    }

    public SSComment getComment() {
        return comment;
    }

    public void setComment(SSComment comment) {
        this.comment = comment;
    }

    public boolean isSequential() {
        return sequential;
    }

    public void setSequential(boolean sequential) {
        this.sequential = sequential;
    }

    public SSColumn getParentIdColumn() {
        return parentIdColumn;
    }

    public void setParentIdColumn(SSColumn parentIdColumn) {
        this.parentIdColumn = parentIdColumn;
    }
}

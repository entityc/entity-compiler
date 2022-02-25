/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.sql;

import com.google.gson.annotations.Expose;

public class SSSchema extends SSNode {

    @Expose
    private final String       description;
    @Expose
    private final SSSourceFile sourceFile;
    @Expose
    private       Integer      version;

    public SSSchema(Integer version, SSSourceFile sourceFile, String description) {
        this.version = version;
        this.sourceFile = sourceFile;
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public int findSchemaVersionForAttribute(String entityName, String attributeName) {
        SSTable table = sourceFile.getTableByEntityName(entityName);
        if (table != null) {
            SSColumn column = table.getColumnByAttributeName(attributeName);
            if (column != null) {
                return column.getSinceSchemaVersion();
            }
        }
        return 0;
    }

    public void copySinceVersions(SSSchema fromSchema) {
        int numCopied = 0;
        for (SSTable table : fromSchema.getSourceFile().getTablesInOrder()) {
            for (SSColumn column : table.getColumns()) {
                int sinceVersion = column.getSinceSchemaVersion();
                if (sinceVersion > 0) {
                    this.annotateSchemaVersionOnColumn(table.getEntityName(), column.getAttributeName(), sinceVersion);
                    numCopied++;
                }
            }
        }
    }

    public SSSourceFile getSourceFile() {
        return sourceFile;
    }

    public void annotateSchemaVersionOnColumn(String entityName, String attributeName, int version) {
        SSTable table = sourceFile.getTableByEntityName(entityName);
        if (table != null) {
            SSColumn column = table.getColumnByAttributeName(attributeName);
            if (column != null) {
                column.setSinceSchemaVersion(version);
            }
        }
    }

    @Override
    public void accept(SSVisitor visitor) {
        visitor.visit(sourceFile);
    }
}

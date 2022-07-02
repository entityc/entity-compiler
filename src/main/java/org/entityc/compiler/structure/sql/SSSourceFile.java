/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.sql;

import com.google.gson.annotations.Expose;
import org.entityc.compiler.util.ECLog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SSSourceFile extends SSNode implements Serializable {

    @Expose
    private final String               name;
    @Expose
    private final String               extension;
    @Expose
    private final Map<String, SSTable> tablesByName       = new HashMap<>();
    @Expose
    private final Map<String, SSView>  viewsByFullName    = new HashMap<>();
    @Expose
    private final Map<String, SSTable> tablesByEntityName = new HashMap<>();
    @Expose
    private final List<SSTable>        tablesInOrder      = new ArrayList<>();

    @Expose
    private final Map<String, SSIndex> indexesByName = new HashMap<>();

    @Expose
    private final List<SSSequence> sequences = new ArrayList<>();

    public SSSourceFile(String name, String extension) {
        this.name      = name;
        this.extension = extension;
    }

    public List<SSTable> getTablesInOrder() {
        if (tablesInOrder.isEmpty()) {
            processTableDependency();
        }
        return tablesInOrder;
    }

    public void addTable(SSTable table) {
        if (tablesByName.containsKey(table.getName())) {
            ECLog.logWarning("Tried to add table multiple times: " + table.getName());
            return;
        }
        tablesByName.put(table.getName(), table);
        tablesByEntityName.put(table.getEntityName(), table);
    }

    public void addSequence(SSSequence sequence) {
        sequences.add(sequence);
    }

    public void addView(SSView view) {
        viewsByFullName.put(view.getFullname(), view);
    }

    public boolean hasTableByName(String tableName) {
        return tablesByName.containsKey(tableName);
    }

    public SSTable getTableByEntityName(String entityName) {
        if (tablesByEntityName == null) {
            return null;
        }
        return tablesByEntityName.get(entityName);
    }

    public SSTable getTableByName(String name) {
        return tablesByName.get(name);
    }

    public void accept(SSVisitor visitor) {
        for (SSTable table : tablesInOrder) {
            visitor.visit(table);
        }
        for (SSView view : viewsByFullName.values()) {
            visitor.visit(view);
        }
        for (SSIndex index : getIndexes()) {
            visitor.visit(index);
        }
        for (SSSequence sequence : getSequences()) {
            visitor.visit(sequence);
        }
    }

    public Collection<SSIndex> getIndexes() {
        return indexesByName.values();
    }

    public Collection<SSSequence> getSequences() {
        return sequences;
    }

    public String getFilename() {
        return getName() + "." + getExtension();
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public void processTableDependency() {
        tablesInOrder.clear();

        HashMap<String, Boolean> processedTable = new HashMap<>();
        for (SSTable table : tablesByName.values()) {
            processTableOrder(table, processedTable);
        }

        // Now find foreign key recursive loops that need to be
        // broken by moving the foreign key declaration out of any
        // create table statements
        for (SSTable table : tablesInOrder) {

            if (table instanceof SSAlteredTable) {
                continue;
            }

            List<SSForeignKey> invalidForeignKeys = new ArrayList<>();
            for (SSForeignKey fk : table.getForeignKeys()) {
                SSTable fktable = tablesByName.get(fk.getForeignTableName());
                if (fktable == null) {
                    invalidForeignKeys.add(fk);
                    //System.err.println("Cannot find foreign key table: " + fk.getForeignTableName());
                    continue;
                }
                if (table.getName().equals(fktable.getName())) {
                    continue; // not worried about to self
                }
                SSForeignKey returnFK = fktable.foreignKeyToTable(table);
                if (returnFK != null && !returnFK.isDeclareInAlterTable() && table.hasForeignKeyToTable(fktable)) {
                    fk.setDeclareInAlterTable(true);
                }
            }
            for (SSForeignKey fk : invalidForeignKeys) {
                table.removeForeignKey(fk);
            }
        }
    }

    private void processTableOrder(SSTable table, HashMap<String, Boolean> processedTable) {
        processedTable.put(table.getName(), true);
        if (table.hasForeignKeys()) {
            for (SSForeignKey fk : table.getForeignKeys()) {
                SSTable fktable = tablesByName.get(fk.getForeignTableName());
                if (tablesInOrder.contains(fktable)) {
                    continue;
                }
                if (fktable != null && processedTable.get(fktable.getName()) == null) {
                    processTableOrder(fktable, processedTable);
                }
            }
        }
        if (!tablesInOrder.contains(table)) {
            tablesInOrder.add(table);
        }
    }

    public boolean isAltered() {
        return !this.getTablesInOrder().isEmpty();
    }

    public void processDependencyIssues() {
        ArrayList<SSAlteredTable> newAlteredTables = new ArrayList<>();

        for (SSTable table : this.getTablesInOrder()) {
            if (table instanceof SSAlteredTable) {
                continue; // don't look at these tables
            }

            boolean                 foundDependencyIssue = false;
            SSAlteredTable          alteredTable         = new SSAlteredTable(table);
            ArrayList<SSForeignKey> foreignKeysToRemove  = new ArrayList<>();
            for (SSForeignKey foreignKey : table.getForeignKeys()) {
                if (foreignKey.isDeclareInAlterTable()) {
                    foundDependencyIssue = true;
                    alteredTable.addForeignKey(foreignKey);
                    foreignKeysToRemove.add(foreignKey);
                }
            }

            if (foundDependencyIssue) {
                for (SSForeignKey foreignKey : foreignKeysToRemove) {
                    table.removeForeignKey(foreignKey);
                }
                newAlteredTables.add(alteredTable);
            }
        }
        for (SSAlteredTable alteredTable : newAlteredTables) {
            tablesInOrder.add(alteredTable);
        }
    }

    public void addIndex(SSIndex index) {
        if (hasIndex(index)) {
            System.err.println("ERROR: Duplicate index names.");
        }
        indexesByName.put(index.getName(), index);
    }

    public boolean hasIndex(SSIndex index) {
        return indexesByName.get(index.getName()) != null;
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.generator;

import org.entityc.compiler.EntityCompiler;
import org.entityc.compiler.structure.sql.SSAlteredColumn;
import org.entityc.compiler.structure.sql.SSAlteredForeignKey;
import org.entityc.compiler.structure.sql.SSAlteredTable;
import org.entityc.compiler.structure.sql.SSBaseVisitor;
import org.entityc.compiler.structure.sql.SSColumn;
import org.entityc.compiler.structure.sql.SSDroppedColumn;
import org.entityc.compiler.structure.sql.SSDroppedForeignKey;
import org.entityc.compiler.structure.sql.SSDroppedIndex;
import org.entityc.compiler.structure.sql.SSDroppedTable;
import org.entityc.compiler.structure.sql.SSForeignKey;
import org.entityc.compiler.structure.sql.SSIndex;
import org.entityc.compiler.structure.sql.SSSchema;
import org.entityc.compiler.structure.sql.SSSequence;
import org.entityc.compiler.structure.sql.SSSourceFile;
import org.entityc.compiler.structure.sql.SSTable;
import org.entityc.compiler.structure.sql.SSUniqueConstraint;
import org.entityc.compiler.structure.sql.SSView;
import org.entityc.compiler.util.ECLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import static org.entityc.compiler.structure.sql.SSType.*;

public class SSVPostgresGenerator extends SSBaseVisitor {

    final         String INDENT = "    ";
    private final String fullDirPath;
    PrintStream output = null;
    private SSSchema currentSchema;

    public SSVPostgresGenerator(String fullDirPath) {
        this.fullDirPath = fullDirPath;
    }

    @Override
    public void visitSchema(SSSchema schema) {
        currentSchema = schema;
        schema.accept(this);
    }

    @Override
    public void visitSourceFile(SSSourceFile sourceFile) {
        String  outputDirectoryPath    = fullDirPath;
        boolean hasOutputDirectoryPath = outputDirectoryPath != null && outputDirectoryPath.length() > 0;

        if (!hasOutputDirectoryPath) {
            return;
        }

        if (!EntityCompiler.ensureDirectory(outputDirectoryPath)) {
            return;
        }

        String fullCreateSourceFilepath = getOutputFilepath(currentSchema);

        try {
            FileOutputStream cfos = new FileOutputStream(fullCreateSourceFilepath);
            output = new PrintStream(cfos);

            if (currentSchema.getVersion() == 1) {
                addCommonFunctions();
            }

            output.println("--");
            output.println("-- TABLES");
            output.println("--");
            output.println();

            sourceFile.accept(this);

            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getOutputFilepath(SSSchema schema) {
        return fullDirPath + File.separator + "V" + schema.getVersion() + "__" + schema.getSourceFile().getFilename();
    }

    private void addCommonFunctions() {
        output.println("--");
        output.println("-- COMMON FUNCTIONS");
        output.println("--");
        output.println("CREATE OR REPLACE FUNCTION update_modified_on_column()");
        output.println("    RETURNS TRIGGER AS $$");
        output.println("BEGIN");
        output.println("  NEW.modified_on = now();");
        output.println("  RETURN NEW;");
        output.println("END;");
        output.println("$$ language 'plpgsql';");
        output.println();
    }

    public void removeOutputFile(SSSchema schema) {
        File f = new File(getOutputFilepath(schema));
        f.delete();
    }

    @Override
    public void visitTable(SSTable table) {

        if (table instanceof SSDroppedTable) {
            visitDroppedTable((SSDroppedTable) table);
        } else if (table instanceof SSAlteredTable) {
            visitAlteredTable((SSAlteredTable) table);
        } else {
            visitCreateTable(table);
        }
    }

    @Override
    public void visitSequence(SSSequence sequence) {
        String sequenceName = sequence.getColumn().getTableName() + "_" + sequence.getColumn().getName() + "_seq";
        output.println("CREATE SEQUENCE IF NOT EXISTS " + sequenceName);
        output.println("    AS integer");
        output.println("    START WITH " + sequence.getStartValue());
        output.println("    INCREMENT BY " + sequence.getIncrementBy());
        output.println("    NO MINVALUE");
        output.println("    NO MAXVALUE");
        output.println("    CACHE 1;");
        output.println("");
        output.println("ALTER SEQUENCE " + sequenceName + " OWNED BY " + sequence.getColumn().getTableName() + "." + sequence.getColumn().getName() + ";");
        output.println("ALTER TABLE ONLY " + sequence.getColumn().getTableName() + " ALTER COLUMN " + sequence.getColumn().getName() + " SET DEFAULT nextval('" + sequenceName + "'::regclass);");
    }

    private void visitDroppedTable(SSDroppedTable droppedTable) {
        output.println("DROP TABLE " + droppedTable.getName() + " CASCADE;");
    }

    private void visitAlteredTable(SSAlteredTable alteredTable) {
        List<String> droppedColumnNames   = new ArrayList<>();
        List<String> alteredColumnNames   = new ArrayList<>();
        List<String> addedColumnNames     = new ArrayList<>();
        List<String> addedForeignKeyNames = new ArrayList<>();
        SSColumn     seqCol               = null;
        SSColumn     parentCol            = null;
        output.println("DO");
        output.println("$$");
        output.println("    BEGIN");
        output.println("        BEGIN");
        boolean                hasForeignKeys = false;

        for (SSForeignKey fk : alteredTable.getForeignKeys()) {
            if (!(fk instanceof SSDroppedForeignKey)) {
                hasForeignKeys = true;
                break;
            }
        }
        ListIterator<SSColumn> cit            = alteredTable.getColumns().listIterator();
        String                 indent         = "            " + INDENT;
        while (cit.hasNext()) {
            SSColumn column = cit.next();
            if (column instanceof SSDroppedColumn) {
                output.println("            ALTER TABLE IF EXISTS " + alteredTable.getName());
                output.print(indent + "DROP COLUMN " + column.getName() + " CASCADE");
                droppedColumnNames.add(column.getName());
                output.println(";");
            } else if (column instanceof SSAlteredColumn) {
                SSAlteredColumn alteredColumn = (SSAlteredColumn) column;
                alteredColumnNames.add(column.getName());
                if (alteredColumn.isTypeChanged()) {
                    output.println("            ALTER TABLE IF EXISTS " + alteredTable.getName());
                    output.print(indent + "ALTER COLUMN " + column.getName());
                    if (alteredColumn.getType() == SERIAL || alteredColumn.getType() == BIGSERIAL) {
                        ECLog.logFatal("Column \"" + alteredColumn.getName() + "\" on table \"" + alteredColumn.getTableName() + "\" cannot be changed to SERIAL");
                    }
                    output.println(" SET DATA TYPE " + alteredColumn.getType().getName().toUpperCase() + ';');
                }
                if (alteredColumn.isNotNullChanged()) {
                    output.println("            ALTER TABLE IF EXISTS " + alteredTable.getName());
                    output.print(indent + "ALTER COLUMN " + column.getName());
                    output.print(" " + (alteredColumn.isNotNull() ?
                                  "SET" :
                                  "DROP") + " NOT NULL;");
                }
                if (alteredColumn.isDefaultValueChanged()) {
                    output.println("            ALTER TABLE IF EXISTS " + alteredTable.getName());
                    output.print(indent + "ALTER COLUMN " + column.getName());
                    if (alteredColumn.hasDefaultValue()) {
                        output.print(" SET DEFAULT " + alteredColumn.getDefaultValue());
                    } else {
                        output.print(" DROP DEFAULT");
                    }
                    output.println(";");
                }
            } else {
                output.println("            ALTER TABLE IF EXISTS " + alteredTable.getName());
                printColumn(indent + "ADD COLUMN ", column, false, true);
                addedColumnNames.add(column.getName());
                if (column.isSequential()) {
                    seqCol = column;
                    if (column.isParent()) {
                        parentCol = column.getParentIdColumn();
                    }
                }
            }
        }

        ListIterator<SSForeignKey> fkit = alteredTable.getForeignKeys().listIterator();
        String alterTableSql = "            ALTER TABLE IF EXISTS " + alteredTable.getName();

        while (fkit.hasNext()) {
            SSForeignKey foreignKey = fkit.next();
            if (foreignKey instanceof SSDroppedForeignKey) {
                // by including CASCADE in DROP it takes care of this
            } else {
                if (alterTableSql != null) {
                    output.println(alterTableSql);
                    alterTableSql = null;
                }
                if (foreignKey instanceof SSAlteredForeignKey) {
                    output.println(indent + "DROP CONSTRAINT " + alteredTable.getName() + "_" + foreignKey.getLocalColumn().getName() + "_fkey,");
                }
                output.print(indent + "ADD FOREIGN KEY (" + foreignKey.getLocalColumn().getName() + ") REFERENCES "
                             + foreignKey.getForeignTableName() + "(" + foreignKey.getForeignColumnName() + ")");
                if (foreignKey.isDeleteCascade()) {
                    output.print(" ON DELETE CASCADE");
                } else {
                    output.print(" ON DELETE SET NULL");
                }
                addedForeignKeyNames.add(foreignKey.getLocalColumn().getName());
            }
            if (fkit.hasNext()) {
                output.print(",");
            }

            if (fkit.hasNext()) {
                output.println();
            }
        }
        if (alterTableSql == null) {
            output.println(indent + ";");
        }
        boolean hasAnException = droppedColumnNames.size() > 0 || addedColumnNames.size() > 0
                                 || addedColumnNames.size() > 0;
        if (hasAnException) {
            indent = "        " + INDENT;
            output.println("        EXCEPTION");
            if (droppedColumnNames.size() > 0) {
                output.println(indent
                               + "WHEN INVALID_COLUMN_REFERENCE THEN RAISE NOTICE 'Unable to drop one of the following columns in the "
                               + alteredTable.getName() + " table: " + String.join(", ", droppedColumnNames) + "';");
            }
            if (addedColumnNames.size() > 0) {
                output.println(indent
                               + "WHEN DUPLICATE_COLUMN THEN RAISE NOTICE 'Unable to add one of the following columns in the "
                               + alteredTable.getName() + " table: " + String.join(", ", addedColumnNames) + "';");
            }
            if (addedColumnNames.size() > 0) {
                output.println(indent
                               + "WHEN INVALID_FOREIGN_KEY THEN RAISE NOTICE 'Unable to add one of the following foreign keys in the "
                               + alteredTable.getName() + " table: " + String.join(", ", addedForeignKeyNames) + "';");
            }
        }
        output.println("        END;");
        output.println("    END;");
        output.println("$$;");
        if (seqCol != null && parentCol != null) {
            output.println();
            output.println("CREATE OR REPLACE FUNCTION " + alteredTable.getName() + "_sequential_set()");
            output.println("    RETURNS TRIGGER AS $$");
            output.println("BEGIN");
            output.println(
                    "    NEW." + seqCol.getName() + " = (SELECT COALESCE((SELECT MAX(t." + seqCol.getName() + ") FROM "
                    + alteredTable.getName() + " t " + "WHERE " + parentCol.getName() + " = NEW." + parentCol.getName()
                    + "),0) + 1);");
            output.println("    RETURN NEW;");
            output.println("END;");
            output.println("$$ language 'plpgsql';");
            output.println();
            output.println("CREATE TRIGGER " + alteredTable.getName() + "_sequential_set_trigger");
            output.println("BEFORE INSERT ON " + alteredTable.getName());
            output.println("FOR EACH ROW EXECUTE PROCEDURE " + alteredTable.getName() + "_sequential_set();");
        }
    }

    private void printColumn(String prefix, SSColumn column, boolean moreToCome, boolean end) {
        String columnName = column.getName();
        if (isReservedWord(columnName)) {
            columnName = "\"" + columnName + "\"";
        }
        if (column.getType() == null) {
            return;
        }
        output.print(prefix + columnName + " " + column.getType().getName().toUpperCase());
        if (column.isPrimaryKey()) {
            output.print(" PRIMARY KEY");
        }
        if (column.isCreation() && column.getType() == TIMESTAMP) {
            output.print(" DEFAULT NOW()");
        } else if (column.isModification() && column.getType() == TIMESTAMP) {
            // don't make it not null
        } else {
            if (column.isNotNull()) {
                output.print(" NOT NULL");
            }
        }
        if (column.hasDefaultValue()) {
            output.print(" DEFAULT " + column.getDefaultValue());
        }
        if (column.getLengthConstraint() != null) {
            output.print(" CHECK (OCTET_LENGTH(" + columnName + ") = " + column.getLengthConstraint() + ")");
        }
        if (moreToCome) {
            output.print(",");
        }
        if (column.getComment() != null) {
            output.print("-- " + column.getComment().getMessage());
        }
        output.println(end ? ";" : "");
    }

    @Override
    public void visitView(SSView view) {
        visitCreateView(view);
    }

    public void visitCreateView(SSView view) {
        Map<String, String> shortTableNames = new HashMap<>();
        shortTableNames.put(view.getPrimaryTable().getName(), "p");
        Set<String> uniqueTableNames = new HashSet<>();
        for (SSColumn column : view.getColumns()) {
            if (column.getTableName() == null) {
                ECLog.logFatal("Found column \"" + column.getName() + "\" without a table set.");
            }
            if (view.getPrimaryTable() != null && !column.getTableName().equals(view.getPrimaryTable().getName())) {
                uniqueTableNames.add(column.getTableName());
            }
        }
        int i = 1;
        for (String tableName : uniqueTableNames) {
            shortTableNames.put(tableName, "j" + (i++));
        }
        List<String> fullColumnNames = new ArrayList<>();
        for (SSColumn column : view.getColumns()) {
            String name = shortTableNames.get(column.getTableName()) + "." + column.getName();
            if (column.getAsName() != null) {
                name = name + " AS " + column.getAsName();
            }
            fullColumnNames.add(name);
        }
        String fields = String.join(", ", fullColumnNames);

        output.println("CREATE OR REPLACE VIEW " + view.getFullname() + " AS");
        output.println("  SELECT " + fields);
        output.print("  FROM " + view.getPrimaryTable().getName() + " p");

        Iterator<String> it = uniqueTableNames.iterator();
        while (it.hasNext()) {
            String   tableName        = it.next();
            SSTable  table            = currentSchema.getSourceFile().getTableByName(tableName);
            SSColumn primaryKeyColumn = table.getPrimaryKeyColumn();
            if (primaryKeyColumn == null) {
                ECLog.logFatal("Expecting table " + tableName + " to have a primary key column.");
            }
            output.println();
            output.print("  INNER JOIN " + tableName + " " + shortTableNames.get(tableName) + " ON p."
                         + primaryKeyColumn.getName() + " = " + shortTableNames.get(tableName) + "."
                         + primaryKeyColumn.getName());
        }
        output.println(";");
        output.println();
    }

    private void visitCreateTable(SSTable table) {

        if (table.getComment() != null) {
            output.println("-- " + table.getComment().getMessage());
        }
        output.println("CREATE TABLE IF NOT EXISTS " + table.getName() + " (");

        boolean hasForeignKeys       = table.getForeignKeys().size() > 0;
        boolean hasUniqueConstraints = table.getUniqueConstraints().size() > 0;

        // COLUMNS
        ArrayList<SSColumn> sortedColumnList = new ArrayList<>(table.getColumns());
        sortedColumnList.sort(new Comparator<SSColumn>() {
            @Override
            public int compare(SSColumn o1, SSColumn o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        ListIterator<SSColumn> cit = sortedColumnList.listIterator();
        while (cit.hasNext()) {
            SSColumn column = cit.next();
            printColumn(INDENT, column, hasForeignKeys || hasUniqueConstraints || cit.hasNext(), false);
        }
        ArrayList<SSForeignKey> sortedForeignKeyList = new ArrayList<>(table.getForeignKeys());
        sortedForeignKeyList.sort(new Comparator<SSForeignKey>() {
            @Override
            public int compare(SSForeignKey o1, SSForeignKey o2) {
                return o1.getLocalColumn().getName().compareTo(o2.getLocalColumn().getName());
            }
        });

        ListIterator<SSForeignKey> fkit = sortedForeignKeyList.listIterator();
        while (fkit.hasNext()) {
            SSForeignKey foreignKey = fkit.next();
            output.print(INDENT + "FOREIGN KEY (" + foreignKey.getLocalColumn().getName() + ") REFERENCES "
                         + foreignKey.getForeignTableName() + "(" + foreignKey.getForeignColumnName() + ")");
            if (foreignKey.isDeleteCascade()) {
                output.print(" ON DELETE CASCADE");
            } else {
                output.print(" ON DELETE SET NULL");
            }
            if (hasUniqueConstraints || fkit.hasNext()) {
                output.print(",");
            }

            output.println();
        }
        ListIterator<SSUniqueConstraint> ucit = table.getUniqueConstraints().listIterator();
        while (ucit.hasNext()) {
            SSUniqueConstraint uniqueConstraint = ucit.next();
            List<String>       columnNames      = new ArrayList<>();
            for (String columnName : uniqueConstraint.getColumnNames()) {
                columnNames.add(columnName);
            }
            output.print(INDENT + "UNIQUE (" + String.join(", ", columnNames) + ")");
            if (ucit.hasNext()) {
                output.print(",");
            }
            output.println();
        }

        output.println(");");

        boolean  hasModificationColumn = false;
        boolean  hasSequentialColumn   = false;
        SSColumn seqCol                = null;
        SSColumn parentCol             = null;
        for (SSColumn col : table.getColumns()) {
            if (col.isModification()) {
                hasModificationColumn = true;
            }
            if (col.isSequential() && col.isParent()) {
                hasSequentialColumn = true;
                seqCol              = col;
                parentCol           = col.getParentIdColumn();
            }
        }

        if (hasSequentialColumn) {
            output.println();
            output.println("CREATE OR REPLACE FUNCTION " + table.getName() + "_sequential_set()");
            output.println("    RETURNS TRIGGER AS $$");
            output.println("BEGIN");
            output.println(
                    "    NEW." + seqCol.getName() + " = (SELECT COALESCE((SELECT MAX(t." + seqCol.getName() + ") FROM "
                    + table.getName() + " t WHERE " + parentCol.getName() + " = NEW." + parentCol.getName()
                    + "),0) + 1);");
            output.println("    RETURN NEW;");
            output.println("END;");
            output.println("$$ language 'plpgsql';");
            output.println();
            output.println("CREATE TRIGGER " + table.getName() + "_sequential_set_trigger");
            output.println("BEFORE INSERT ON " + table.getName());
            output.println("FOR EACH ROW EXECUTE PROCEDURE " + table.getName() + "_sequential_set();");
        }

        // TODO This needs to be part of the altering process to add or drop it as necessary
        if (hasModificationColumn) {
            output.println();
            output.println("CREATE TRIGGER " + table.getName() + "_updatemodifiedon");
            output.println("BEFORE INSERT OR UPDATE ON " + table.getName());
            output.println("FOR EACH ROW EXECUTE PROCEDURE update_modified_on_column();");
        }
        output.println();
        boolean printedComment = false;
        if (table.getComment() != null) {
            output.println(
                    "COMMENT ON TABLE " + table.getName() + " IS '" + escapedString(table.getComment().getMessage())
                    + "';");
            printedComment = true;
        }
        for (SSColumn column : table.getColumns()) {
            if (column.getComment() != null) {
                output.println(
                        "COMMENT ON COLUMN " + table.getName() + "." + column.getName() + " IS '" + escapedString(
                                column.getComment().getMessage()) + "';");
                printedComment = true;
            }
        }

        if (printedComment) {
            output.println();
        }
    }

    private String escapedString(String string) {
        return string.replaceAll("'", "''");
    }

    @Override
    public void visitColumn(SSColumn column) {
    }

    @Override
    public void visitIndex(SSIndex index) {

        if (index instanceof SSDroppedIndex) {
            output.println("DROP INDEX IF EXISTS " + index.getName() + ";");
        } else {
            String columnDecl = "";
            if (index.getColumns().size() == 1) {
                columnDecl = index.getColumns().get(0).getName() + " ASC";
            } else {
                List<String> columnnames = new ArrayList<>();
                for (SSColumn column : index.getColumns()) {
                    columnnames.add(column.getName() + " ASC");
                }
                columnDecl = String.join(", ", columnnames);
            }
            output.println("CREATE INDEX IF NOT EXISTS " + index.getName() + " ON " + index.getTable().getName() + "("
                           + columnDecl + ");");
        }
    }

    private boolean isReservedWord(String word) {
        return word.equals("order");
    }
}

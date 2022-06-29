/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.sql;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.entityc.compiler.EntityCompiler;
import org.entityc.compiler.util.ECLog;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class SSSchemaVersioning {

    private static String basePath = "";

    public static void SaveSchemaVersion(SchemaPointer pointer, Integer version) {
        String pointerFilename = pointer == SchemaPointer.Read ?
                                 "readVersion" :
                                 "writeVersion";
        String filepath        = basePath + File.separator + pointerFilename + ".json";
        Gson   gson            = new Gson();
        try {
            ensureBasePathDirectoryExists();
            FileOutputStream fos    = new FileOutputStream(filepath);
            JsonWriter       writer = new JsonWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
            writer.setIndent("  ");
            gson.toJson(version, Integer.class, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            ECLog.logError("Unable to write schema version file: " + filepath);
        } catch (IOException e) {
            ECLog.logError("Unable to write schema version file: " + filepath);
        }
    }

    private static void ensureBasePathDirectoryExists() {
        EntityCompiler.ensureDirectory(basePath);
    }

    public static Integer LoadSchemaVersion(SchemaPointer pointer) {
        String pointerFilename = pointer == SchemaPointer.Read ?
                                 "readVersion" :
                                 "writeVersion";
        String filepath        = basePath + File.separator + pointerFilename + ".json";
        Gson   gson            = new Gson();
        try {
            FileInputStream fis     = new FileInputStream(filepath);
            JsonReader      reader  = new JsonReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
            Integer         version = gson.fromJson(reader, Integer.class);
            reader.close();
            return version;
        } catch (FileNotFoundException e) {
            return 0;
        } catch (IOException e) {
        }
        return 0;
    }

    public static void SaveAdvanceRequest(boolean request) {
        String filepath = basePath + File.separator + "advanceRequest.json";
        Gson   gson     = new Gson();
        try {
            ensureBasePathDirectoryExists();
            FileOutputStream fos    = new FileOutputStream(filepath);
            JsonWriter       writer = new JsonWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
            writer.setIndent("  ");
            gson.toJson(request, Boolean.class, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            ECLog.logError("Unable to write schema version file: " + filepath);
        } catch (IOException e) {
            ECLog.logError("Unable to write schema version file: " + filepath);
        }
    }

    public static Boolean LoadAdvanceRequest() {
        String filepath = basePath + File.separator + "advanceRequest.json";
        Gson   gson     = new Gson();
        try {
            FileInputStream fis     = new FileInputStream(filepath);
            JsonReader      reader  = new JsonReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
            Boolean         request = gson.fromJson(reader, Boolean.class);
            reader.close();
            return request;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
        }
        return false;
    }

    public static void DeleteEntireSchema() {
        String schemaDirectoryPath = basePath;
        if (schemaDirectoryPath == null) {
            ECLog.logError("Must specify schema directory.");
            return;
        }
        File schemaDirectory = new File(schemaDirectoryPath);
        File[] schemaFiles = schemaDirectory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().startsWith("v") && pathname.getName().endsWith("_schema.json");
            }
        });
        if (schemaFiles != null) {
            for (File f : schemaFiles) {
                f.delete();
            }
        }
        File f = new File(schemaDirectoryPath + File.separator + "readVersion.json");
        f.delete();
        f = new File(schemaDirectoryPath + File.separator + "writeVersion.json");
        f.delete();

        String modelDirectoryPath = basePath;
        if (schemaDirectoryPath != null) {
            File modelDirectory = new File(modelDirectoryPath);
            File[] modelFiles = modelDirectory.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().startsWith("V") && pathname.getName().endsWith("__model.sql");
                }
            });
            if (modelFiles != null) {
                for (File fd : modelFiles) {
                    fd.delete();
                }
            }
        }
    }

    public static void SaveSchema(SSSchema schema) {
        String filepath = basePath + File.separator + "v" + schema.getVersion() + "_schema.json";
        Gson   gson     = new Gson();
        try {
            ensureBasePathDirectoryExists();
            FileOutputStream fos    = new FileOutputStream(filepath);
            JsonWriter       writer = new JsonWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
            writer.setIndent("  ");
            gson.toJson(schema, SSSchema.class, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            ECLog.logError("Unable to write schema version file: " + filepath);
        } catch (IOException e) {
            ECLog.logError("Unable to write schema version file: " + filepath);
        }
    }

    public static SSSchema LoadSchema(Integer version) {
        String baselineFilepath = basePath + File.separator + "v" + version + "_schema.json";
        Gson   gson             = new Gson();
        try {
            FileInputStream fis    = new FileInputStream(baselineFilepath);
            JsonReader      reader = new JsonReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
            SSSchema        schema = gson.fromJson(reader, SSSchema.class);
            reader.close();
            return schema;
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return null;
    }

    public static SSSourceFile generateMigration(SSSchema baselineSchema, SSSchema nextSchema) {

        SSSourceFile nextSourceFile = nextSchema.getSourceFile();
        nextSourceFile.processTableDependency();
        nextSourceFile.processDependencyIssues();

        if (baselineSchema == null) {
            return nextSourceFile;
        }

        SSSourceFile baselineSourceFile = baselineSchema.getSourceFile();
        baselineSourceFile.processTableDependency();
        baselineSourceFile.processDependencyIssues();

        SSSourceFile migrationSourceFile = new SSSourceFile(nextSourceFile.getName(), nextSourceFile.getExtension());

        // Tables Dropped
        for (SSTable baselineTable : baselineSourceFile.getTablesInOrder()) {
            if (nextSourceFile.getTableByName(baselineTable.getName()) == null) {
                // must be dropping it
                //System.out.println("Migration: Dropping table: " + baselineTable.getName());
                SSDroppedTable droppedTable = new SSDroppedTable(baselineTable);
                migrationSourceFile.addTable(droppedTable);
            }
        }

        // Tables Added
        for (SSTable nextTable : nextSourceFile.getTablesInOrder()) {
            if (baselineSourceFile.getTableByName(nextTable.getName()) == null) {
                // must be adding it
                //System.out.println("Migration: Adding table: " + nextTable.getName());
                migrationSourceFile.addTable(nextTable);
            }
        }

        // Tables Altered
        for (SSTable nextTable : nextSourceFile.getTablesInOrder()) {
            SSTable baselineTable = baselineSourceFile.getTableByName(nextTable.getName());
            if (baselineTable == null) {
                continue;
            }

            SSAlteredTable alteredTable    = new SSAlteredTable(baselineTable);
            boolean        foundAlteration = false;

            // look for dropped columns
            for (SSColumn baselineColumn : baselineTable.getColumns()) {
                if (nextTable.getColumnByAttributeName(baselineColumn.getAttributeName()) == null) {
                    if (baselineColumn.getName() == null) {
                        ECLog.logFatal("Baseline column name is null!!");
                    }
                    SSDroppedColumn droppedColumn = new SSDroppedColumn(baselineColumn);
                    alteredTable.addColumn(droppedColumn);
                    foundAlteration = true;
                }
            }

            // look for added columns
            for (SSColumn nextColumn : nextTable.getColumns()) {
                if (baselineTable.getColumnByAttributeName(nextColumn.getAttributeName()) == null) {
                    nextSchema.annotateSchemaVersionOnColumn(nextTable.getEntityName(), nextColumn.getAttributeName(),
                                                             nextSchema.getVersion());
                    SSColumn addedColumn = new SSColumn(nextColumn.getType(), nextColumn.getName(),
                                                        nextColumn.getAttributeName());
                    addedColumn.setNotNull(nextColumn.isNotNull());
                    addedColumn.setDefaultValue(nextColumn.getDefaultValue());
                    addedColumn.setSinceSchemaVersion(nextSchema.getVersion());
                    alteredTable.addColumn(addedColumn);
                    for (SSForeignKey nextForeignKey : nextTable.getForeignKeys()) {
                        if (nextForeignKey.getLocalColumn().getName().equals(addedColumn.getName())) {
                            alteredTable.addForeignKey(nextForeignKey);
                        }
                    }
                    foundAlteration = true;
                }
            }

            // look for altered foreign key constraints
            for (SSForeignKey nextForeignKey : nextTable.getForeignKeys()) {
                SSForeignKey baselineForeignKey = baselineTable.getForeignKeyByRelationshipName(
                        nextForeignKey.getRelationshipName());
                if (baselineForeignKey == null) {
                    continue;
                }
                SSAlteredForeignKey alteredForeignKey = new SSAlteredForeignKey(baselineForeignKey, nextForeignKey);
                if (alteredForeignKey.isAltered()) {
                    alteredTable.addForeignKey(alteredForeignKey);
                    foundAlteration = true;
                }
            }

            // look for altered columns
            for (SSColumn nextColumn : nextTable.getColumns()) {
                SSColumn baselineColumn = baselineTable.getColumnByAttributeName(nextColumn.getAttributeName());
                if (baselineColumn == null) {
                    continue;
                }

                SSAlteredColumn alteredColumn = new SSAlteredColumn(baselineColumn, nextColumn);
                if (alteredColumn.isAltered()) {
                    alteredTable.addColumn(alteredColumn);
                    foundAlteration = true;
                }
            }

            // -- when a column is dropped its foreign key constraints are also dropped so this
            // -- isn't necessary
            // look for dropped foreign keys
//            for (SSForeignKey baselineForeignKey : baselineTable.getForeignKeys()) {
//                if (!nextTable.hasForeignKey(baselineForeignKey)) {
//                    alteredTable.addForeignKey(new SSDroppedForeignKey(baselineForeignKey));
//                    foundAlteration = true;
//                }
//            }

            // -- don't need this since foreign key constraints are added when a new column is added
            // -- also, there is a bug where this creates redundant foreign key constraints
            // look for added foreign keys
//            for (SSForeignKey nextForeignKey : nextTable.getForeignKeys()) {
//                if (!baselineTable.hasForeignKey(nextForeignKey)) {
//                    alteredTable.addForeignKey(nextForeignKey);
//                    foundAlteration = true;
//                }
//            }

            if (foundAlteration) {
                migrationSourceFile.addTable(alteredTable);
            }
        }

        // Indexes Dropped
        for (SSIndex baselineIndex : baselineSourceFile.getIndexes()) {
            if (!nextSourceFile.hasIndex(baselineIndex)) {
                // must be dropping it
                //System.out.println("Migration: Dropping index: " + baselineIndex.getName());
                SSDroppedIndex droppedIndex = new SSDroppedIndex(baselineIndex.getName(), baselineIndex.getTable());
                migrationSourceFile.addIndex(droppedIndex);
            }
        }

        // Indexes Added
        for (SSIndex nextIndex : nextSourceFile.getIndexes()) {
            if (!baselineSourceFile.hasIndex(nextIndex)) {
                // must be adding it
                //System.out.println("Migration: Adding index: " + nextIndex.getName());
                migrationSourceFile.addIndex(nextIndex);
            }
        }

        migrationSourceFile.processDependencyIssues();
        return migrationSourceFile;
    }

    public static void setBasePath(String basePath) {
        SSSchemaVersioning.basePath = basePath;
    }

    public enum SchemaPointer {
        Read,
        Write
    }
}

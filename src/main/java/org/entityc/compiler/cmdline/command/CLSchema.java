/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.txt file in the project root.
 */

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.txt file in the project root.
 */

package org.entityc.compiler.cmdline.command;

import org.entityc.compiler.cmdline.CommandLine;
import org.entityc.compiler.project.ProjectManager;
import org.entityc.compiler.structure.sql.SSSchemaVersioning;
import org.entityc.compiler.util.ECLog;

import java.util.Scanner;
import java.util.Set;

import static org.entityc.compiler.structure.sql.SSSchemaVersioning.LoadSchemaVersion;

public class CLSchema extends CLCommand {

    public CLSchema(CommandLine commandLine) {
        super(commandLine, "schema", "Used to manage the database schema of the project.", "");
    }

    @Override
    public void run(String[] args) {

        ProjectManager projectManager = ProjectManager.getInstance();
        projectManager.start(false);

        Set<String> directories = projectManager.getSchemaDirectories();
        if (directories.size() == 0) {
            ECLog.log("No schema directories found. You may need to run a build so that it will be created.");
        }

        if (directories.size() > 1) {
            ECLog.logWarning("Multiple schemas are currently not supported. Only the first one found will be used.");
        }

        SSSchemaVersioning.setBasePath(directories.stream().findFirst().get());

        if (args.length > 0) {
            String subCommand = args[0];
            if (subCommand.equals("advance")) {
                if (args.length > 2) {
                    ECLog.logFatal(getName() + " " + subCommand + " has too many arguments");
                }
                if (args.length == 1) {
                    SSSchemaVersioning.SaveAdvanceRequest(true);
                } else {
                    String subCommandArg = args[1];
                    if (subCommandArg.equals("cancel")) {
                        SSSchemaVersioning.SaveAdvanceRequest(false);
                    } else {
                        ECLog.logFatal("The " + getName() + " " + subCommand + " command does not accept option: "
                                       + subCommandArg);
                    }
                }
            } else if (subCommand.equals("delete")) {
                boolean doTheDelete = false;
                if (args.length > 1) {
                    String deleteOption = args[1];
                    if (deleteOption.equals("-f") || deleteOption.equals("--force")) {
                        doTheDelete = true;
                    } else {
                        ECLog.logFatal("Unknown option for delete: " + deleteOption);
                    }
                }
                if (!doTheDelete) {
                    System.out.print(
                            "Are you sure you want to delete your schema? To confirm enter the word 'delete': ");
                    Scanner scanner = new Scanner(System.in);
                    String  value   = scanner.nextLine();
                    if (value.equals("delete")) {
                        doTheDelete = true;
                    }
                }
                if (doTheDelete) {
                    SSSchemaVersioning.DeleteEntireSchema();
                    ECLog.log(
                            "Entire schema has been " + CLCommand.StdoutColor.RedForeground.getStringValue() + "DELETED"
                            + StdoutColor.Default.getStringValue());
                } else {
                    ECLog.log("Delete aborted.");
                    return;
                }
            } else {
                ECLog.logFatal("Command " + getName() + " does not support sub-command: " + subCommand);
            }
        }
        for (String schemaDirectory : directories) {
            SSSchemaVersioning.setBasePath(schemaDirectory);
            Integer readVersion = LoadSchemaVersion(SSSchemaVersioning.SchemaPointer.Read);
            ECLog.log("");
            ECLog.log("Schema Directory: " + schemaDirectory);
            ECLog.log("    Previous version:          " + ((readVersion > 0) ?
                                                           ("" + readVersion) :
                                                           "(none)"));
            ECLog.log("    Current version:           " + LoadSchemaVersion(SSSchemaVersioning.SchemaPointer.Write));
            ECLog.log("    Version advance directive: " + (SSSchemaVersioning.LoadAdvanceRequest() ?
                                                           colorize("advance if schema changes",
                                                                    StdoutColor.GreenForeground) :
                                                           colorize("don't advance", StdoutColor.RedForeground)));
        }
        ECLog.log("");
    }

    @Override
    public void printUsage() {
        super.printUsageWithArguments("[advance [cancel]] | [delete [-f,--force]]");
    }
}

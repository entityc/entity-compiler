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

import org.entityc.compiler.EntityCompiler;
import org.entityc.compiler.cmdline.CommandLine;
import org.entityc.compiler.model.MTRoot;
import org.entityc.compiler.model.config.MTConfiguration;
import org.entityc.compiler.project.ProjectManager;
import org.entityc.compiler.repository.RepositoryFile;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.List;

public class CLBuild extends CLCommand {

    public CLBuild(CommandLine commandLine) {
        super(commandLine,
              "build",
              "Builds an Entity Compiler project.",
              "Builds the Entity Compiler project using the specified configuration name.");
    }

    @Override
    public void run(String[] args) {

        // must have at least one argument
        if (args.length == 0) {
            printUsage();
            return;
        }

        String       configurationName = args[0];
        List<String> defines           = new ArrayList<>();
        boolean quietMode = false;

        // Gather the source file names
        ArrayList<String> sourceFilenames = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            String arg = args[i];
            if (!arg.startsWith("-")) {
                if (arg.contains("*")) {
                    sourceFilenames.addAll(super.processWildcardArg(arg));
                } else {
                    sourceFilenames.add(arg);
                }
            } else if (arg.equals("-D")) {
                defines.add(args[++i]);
            } else if (arg.startsWith("-D")) {
                defines.add(arg.substring(2));
            } else if (arg.equals("-q") || arg.equals("--quiet")) {
                quietMode = true;
            } else if (arg.equals("-tp")) {
                if (i == args.length-1) {
                    ECLog.logFatal("Missing template path.");
                }
                String paths = args[++i];
                for (String path : paths.split(":")) {
                    commandLine.AddToTemplateSearchPath(path);
                }

            }
        }

        // set the global name/value pairs from command line
        for (String defineStatement : defines) {
            String[] nameValue = defineStatement.split("=");
            if (nameValue.length != 2) {
                ECLog.logFatal("-D option must be of the form <name>=<value>.");
            }
            EntityCompiler.SetDefineValue(nameValue[0], nameValue[1]);
        }

        // startup our project
        ProjectManager.getInstance().start(quietMode);

        // Convert them to repository imports
        ArrayList<RepositoryFile> repositoryFiles = new ArrayList<>();
        for (String sourceFilename : sourceFilenames) {
            repositoryFiles.add(new RepositoryFile(sourceFilename, false));
        }

        // create the root node and run the parser
        MTRoot root = new MTRoot(null);
        EntityCompiler.parseSourceFiles(root, null, repositoryFiles, false);

        // Get the configuration that was requested and make sure it is there
        MTConfiguration configuration = root.getConfiguration(configurationName);
        if (configuration == null) {
            ECLog.logFatal(
                    "Unable to find a configuration named \"" + configurationName + "\" in the specified files.");
        }

        // Run the compiler for our configuration
        ProjectManager.getInstance().beginConfiguration(configurationName);
        EntityCompiler.RunConfiguration(configuration);
        ProjectManager.getInstance().endActiveConfiguration();

        // close out our session
        ProjectManager.getInstance().close();
        if (!quietMode) {
            ECLog.log(colorize("Success", StdoutColor.GreenForeground));
        }
    }

    @Override
    public void printUsage() {
        super.printUsageWithArguments("<config-name> [<file> ...] [-tp <path>][-q,--quiet][-D<name>=<value> ...]");
    }
}

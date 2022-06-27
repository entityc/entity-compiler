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

public class CLBuild extends CLCommand {

    public CLBuild(CommandLine commandLine) {
        super(commandLine,
              "build",
              "Builds an Entity Compiler project.",
              "Builds the Entity Compiler project using the specified configuration name.");
    }

    @Override
    public void run(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }

        String configurationName = args[0];

        // Gather up the source file names
        ArrayList<String> sourceFilenames = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            String arg = args[i];
            if (arg.contains("*")) {
                sourceFilenames.addAll(super.processWildcardArg(arg));
            } else {
                sourceFilenames.add(arg);
            }
        }
        ProjectManager.getInstance().start();

        // Convert them to repository imports
        ArrayList<RepositoryFile> repositoryFiles = new ArrayList<>();
        for (String sourceFilename : sourceFilenames) {
            repositoryFiles.add(new RepositoryFile(sourceFilename, false));
        }
        MTRoot root = new MTRoot(null);
        EntityCompiler.parseSourceFiles(root, null, repositoryFiles, false);

        MTConfiguration configuration = root.getConfiguration(configurationName);

        if (configuration == null) {
            ECLog.logFatal("Unable to find a configuration named \"" + configurationName + "\" in the setup file.");
        }

        ProjectManager.getInstance().beginConfiguration(configurationName);
        // Run the compiler for our configuration
        EntityCompiler.RunConfiguration(configuration);

        ProjectManager.getInstance().endActiveConfiguration();

        // close out our session
        ProjectManager.getInstance().close();
        ECLog.log(StdoutColor.GreenForeground.getStringValue() + "Success");
    }

    @Override
    public void printUsage() {
        super.printUsageWithArguments("<config-name> [<file>]");
    }
}

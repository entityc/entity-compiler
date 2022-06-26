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
import org.entityc.compiler.util.ECLog;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CLStatus extends CLCommand {

    public CLStatus(CommandLine commandLine) {
        super(commandLine,
              "status",
              "Displays the status of an Entity Compiler project.",
              "Displays the status of this Entity Compiler project (if exists).");
    }

    @Override
    public void printUsage() {
        super.printUsageWithArguments("");
    }

    @Override
    public void run(String[] args) {

        ProjectManager projectManager = ProjectManager.getInstance();

        projectManager.start();

        projectManager.getSessionFiles(null);

        String invocationDirectory = null;
        try {
            invocationDirectory = new File(".").getCanonicalPath();
            String projectBaseDirPath = ProjectManager.getInstance().findECDirectory(invocationDirectory);
            if (projectBaseDirPath != null) {
                projectManager.loadProjectFiles();
                ECLog.log("");
                ECLog.log("Configurations:");
                Collection<String> sortedNames = projectManager.getConfigurationNames().stream().sorted().collect(Collectors.toList());
                for (String name : sortedNames) {
                    ECLog.log("    " + name);
                }
                ECLog.log("");
                ECLog.log("Templates:");
                Collection<String> sortedTemplateUris = projectManager.getTemplateUris().stream().sorted().collect(Collectors.toList());
                for (String uri : sortedTemplateUris) {
                    ECLog.log("    " + uri);
                }
            } else {
                ECLog.logFatal("This directory is not an Entity Compiler project directory.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

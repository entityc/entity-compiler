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

import org.apache.commons.io.FileUtils;
import org.entityc.compiler.cmdline.CommandLine;
import org.entityc.compiler.project.ProjectManager;
import org.entityc.compiler.util.ECLog;

import java.io.File;
import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CLInfo extends CLCommand {

    public CLInfo(CommandLine commandLine) {
        super(commandLine,
              "info",
              "Displays some info about this Entity Compiler project.",
              "Displays various types of info about this Entity Compiler project.");
    }

    @Override
    public void printUsage() {
        super.printUsageWithArguments("[-c,--configs][-t,--templates]");
    }

    @Override
    public void run(String[] args) {

        ProjectManager projectManager = ProjectManager.getInstance();

        projectManager.start();

        boolean showConfigs = false;
        boolean showTemplates = false;
        for (int i=0; i<args.length;i++) {
            String arg = args[i];
            if (arg.equals("-c") || arg.equals("--configs")) {
                showConfigs = true;
            } else if (arg.equals("-t") || arg.equals("--templates")) {
                showTemplates = true;
            }
        }
        boolean showSources = true;

        projectManager.loadProjectFiles();
        if (showSources) {
            displayItems("Source Files", projectManager.getSourceFiles());
        }
        if (showConfigs) {
            displayItems("Configurations", projectManager.getConfigurationNames());
        }
        if (showTemplates) {
            displayItems("Templates", projectManager.getTemplateUris());
        }
    }

    private void displayItems(String message, Set<String> listOfItems) {
        ECLog.log("");
        ECLog.log(message + ":");
        Collection<String> sortedNames = listOfItems.stream().sorted().collect(Collectors.toList());
        for (String name : sortedNames) {
            ECLog.log("    " + name);
        }
    }
}

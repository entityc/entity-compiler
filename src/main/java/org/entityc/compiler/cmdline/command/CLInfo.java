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
import org.entityc.compiler.project.GeneratedFile;
import org.entityc.compiler.project.ProjectManager;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CLInfo extends CLCommand {

    public CLInfo(CommandLine commandLine) {
        super(commandLine,
              "info",
              "Displays some info about this Entity Compiler project.",
              "Displays various types of info about this Entity Compiler project.");
    }

    @Override
    public void printUsage() {
        super.printUsageWithArguments("[-s,--sources][-c,--configs][-t,--templates][-g,--generated][-a,--all]");
    }

    @Override
    public void run(String[] args) {

        ProjectManager projectManager = ProjectManager.getInstance();
        projectManager.start();

        boolean showSources = false;
        boolean showConfigs        = false;
        boolean showTemplates      = false;
        boolean showGeneratedFiles = false;
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.equals("-c") || arg.equals("--configs")) {
                showConfigs = true;
            } else if (arg.equals("-s") || arg.equals("--sources")) {
                showSources = true;
            } else if (arg.equals("-t") || arg.equals("--templates")) {
                showTemplates = true;
            } else if (arg.equals("-g") || arg.equals("--generated")) {
                showGeneratedFiles = true;
            } else if (arg.equals("-a") || arg.equals("--all")) {
                showConfigs = true;
                showSources = true;
                showTemplates = true;
                showGeneratedFiles = true;
            }
        }

        if (!showConfigs && !showTemplates && !showGeneratedFiles) {
            showSources = true;
        }

        projectManager.loadProjectFiles();

        if (showSources) {
            displayItems("Source Files", projectManager.getSourceFiles());
        }

        if (showGeneratedFiles) {
            final List<GeneratedFile> files     = projectManager.getGeneratedFiles();
            Set<String>               filenames = new HashSet<>();
            for (GeneratedFile file : files) {
                StringBuilder sb = new StringBuilder();
                sb.append(ProjectManager.getInstance().getRelativePath(new File(file.getFilepath())));
                Set<String> configurationNames = file.getConfigurationNames();
                if (configurationNames.size() > 1) {
                    sb.append(" (" + configurationNames.size() + " configurations)");
                }
                filenames.add(sb.toString());
            }
            displayItems("Generated Files", filenames, StdoutColor.GreenForeground);
        }

        if (showConfigs) {
            displayItems("Configurations", projectManager.getConfigurationNames(), StdoutColor.CyanForeground);
        }

        if (showTemplates) {
            displayItems("Templates", projectManager.getTemplateUris(), StdoutColor.BlueForeground);
        }
    }
}

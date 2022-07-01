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

public class CLInit extends CLCommand {

    public CLInit(CommandLine commandLine) {
        super(commandLine,
              "init",
              "Initializes a directory as an Entity Compiler project.",
              "Initializes the current working directory to be an Entity Compiler project directory. "
              + "This will allow the Entity Compiler to keep track of build sessions for this project and keep other "
              + "project information specific to the Entity Compiler in one place.");
    }

    @Override
    public void printUsage() {
        super.printUsageWithArguments("[-f,--force]");
    }

    @Override
    public void run(String[] args) {

        boolean force               = args.length > 0 && (args[0].equals("-f") || args[0].equals("--force"));
        String  invocationDirectory = null;
        try {
            invocationDirectory = new File(".").getCanonicalPath();
            String projectBaseDirPath = ProjectManager.getInstance().findECDirectory(invocationDirectory);
            if (projectBaseDirPath != null && projectBaseDirPath.equals(invocationDirectory)) {
                ECLog.log("Project directory already established here.");
            } else if (projectBaseDirPath == null || (force && !projectBaseDirPath.equals(invocationDirectory))) {
                if (force) {
                    ProjectManager.getInstance().setProjectBaseDirPath(invocationDirectory);
                }
                ProjectManager.getInstance().start(false);
                ProjectManager.getInstance().close();
                ECLog.log("This directory (" + invocationDirectory + ") is now an Entity Compiler project directory.");
            } else {
                ECLog.logError("There is already a project directory above this directory: " + projectBaseDirPath);
                ECLog.log("If you still want to initialize this directory (" + invocationDirectory
                          + ") as a project directory use the -f option.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

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
        projectManager.start(false);
        ECLog.log("Project directory: " +  ProjectManager.getInstance().getProjectBaseDirPath());
    }
}

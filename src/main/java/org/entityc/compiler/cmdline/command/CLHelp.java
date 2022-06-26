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
import org.entityc.compiler.util.ECLog;

public class CLHelp extends CLCommand {

    public CLHelp(CommandLine commandLine) {
        super(commandLine,
              "help",
              "Provides help on usage for this app.",
              "Provides usage help on a specific command or on the entire app.");
    }

    @Override
    public void run(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }

        String    commandName = args[0];
        CLCommand command     = commandLine.getCommandByName(commandName);
        if (command == null) {
            ECLog.logError("Unknown command line command: " + commandName);
            commandLine.printUsage();
            System.exit(1);
        }

        command.printUsage();
    }

    @Override
    public void printUsage() {
        super.printUsageWithArguments("[<command-name>]");
    }
}

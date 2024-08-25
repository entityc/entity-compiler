/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.txt file in the project root.
 */

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.cmdline;

import org.entityc.compiler.cmdline.command.CLBuild;
import org.entityc.compiler.cmdline.command.CLCommand;
import org.entityc.compiler.cmdline.command.CLFormat;
import org.entityc.compiler.cmdline.command.CLHelp;
import org.entityc.compiler.cmdline.command.CLInfo;
import org.entityc.compiler.cmdline.command.CLInit;
import org.entityc.compiler.cmdline.command.CLSchema;
import org.entityc.compiler.cmdline.command.CLSetup;
import org.entityc.compiler.cmdline.command.CLStatus;
import org.entityc.compiler.util.ECStringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.exit;
import static org.entityc.compiler.EntityCompiler.COMPILER_VERSION;
import static org.entityc.compiler.EntityCompiler.LANGUAGE_VERSION;

public class CommandLine {

    private final Map<String, CLCommand> commands = new HashMap<>();

    public  boolean      help                 = false;
    public  boolean      version              = false;
    public  boolean      verbose              = false;
    public  List<String> sourceFileNames      = new ArrayList<>();
    public  boolean      advanceSchemaVersion = false;
    public  boolean      deleteSchema         = false;
    public  String       configurationName;
    public  String       templateToRun;
    private static List<String> templateSearchPaths  = new ArrayList<>();

    public CommandLine() {
        addCommand(new CLHelp(this));
        addCommand(new CLInit(this));
        addCommand(new CLBuild(this));
        addCommand(new CLSchema(this));
        addCommand(new CLSetup(this));
        addCommand(new CLFormat(this));
        addCommand(new CLStatus(this));
        addCommand(new CLInfo(this));
    }

    private void addCommand(CLCommand command) {
        commands.put(command.getName(), command);
    }

    public static void AddToTemplateSearchPath(String path) {
        templateSearchPaths.add(path);
    }

    public static final List<String> GetTemplateSearchPaths() {
        return templateSearchPaths;
    }

    public void run(String[] args) {
        if (args.length == 0) {
            printUsage();
            exit(0);
        }

        reset(); // in case it is executed multiple times in a single java vm

        int commandIndex = 0;

        String option = args[0];

        if (option.equals("--verbose")) {
            if (args.length == 1) {
                printUsage();
                exit(0);
            }
            verbose = true;
            commandIndex = 1;
        }

        if (option.equals("-h") || option.equals("-help") || option.equals("--help")) {
            printUsage();
            exit(0);
        }

        if (option.equals("-v") || option.equals("-version") || option.equals("--version")) {
            System.out.println("ec compiler version " + COMPILER_VERSION + ", language version " + LANGUAGE_VERSION);
            exit(0);
        }

        String commandName = args[commandIndex];

        CLCommand command = commands.get(commandName);
        String cmdArgString = args.length == 1 ?
                              null :
                              args[commandIndex + 1];

        if (cmdArgString != null && (cmdArgString.equals("-h") || cmdArgString.equals("-help") || cmdArgString.equals(
                "--help"))) {
            command.printUsage();
            exit(0);
        }

        String[] cmdArgsArray = new String[args.length - (commandIndex+1)];
        for (int i = commandIndex+1; i < args.length; i++) {
            cmdArgsArray[i - (commandIndex+1)] = args[i];
        }
        command.run(cmdArgsArray);
    }

    public void printUsage() {
        System.out.println("usage: ec [--version] [--help] [--verbose]");
        System.out.println("       <command> [<args>]");
        System.out.println();
        System.out.println("The following commands are available:");
        int maxLen = 0;
        for (String cmdName : commands.keySet()) {
            if (cmdName.length() > maxLen) {
                maxLen = cmdName.length();
            }
        }
        for (String cmdName : commands.keySet()) {
            CLCommand cmd = commands.get(cmdName);
            System.out.println(
                    "   " + cmd.getName() + ECStringUtil.RepeatString(" ", maxLen + 3 - cmd.getName().length())
                    + cmd.getSummary());
        }
    }

    public final CLCommand getCommandByName(String name) {
        return commands.get(name);
    }

    private static void reset() {
        templateSearchPaths.clear();
    }
}

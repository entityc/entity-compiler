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

    private final Map<String, String> defineValues         = new HashMap<>();
    public        boolean             help                 = false;
    public        boolean             version              = false;
    public        boolean             verbose              = false;
    public        List<String>        sourceFileNames      = new ArrayList<>();
    public        boolean             advanceSchemaVersion = false;
    public        boolean             deleteSchema         = false;
    public        String              configurationName;
    public        List<String>        templateSearchPaths  = new ArrayList<>();
    public        String              templateToRun;
    public        String              templateToFormat;
    public        String              templateToFormatInPath;
    public        String              templateToFormatOutPath;
    public        String              setupUri;

    public CommandLine() {
        addCommand(new CLHelp(this));
        addCommand(new CLInit(this));
        addCommand(new CLBuild(this));
        addCommand(new CLSchema(this));
        addCommand(new CLSetup(this));
        addCommand(new CLFormat(this));
        addCommand(new CLStatus(this));
    }

    private void addCommand(CLCommand command) {
        commands.put(command.getName(), command);
    }

    public String getDefineValue(String name) {
        return defineValues.get(name);
    }

    public void run(String[] args) {
        if (args.length == 0) {
            printUsage();
            exit(0);
        }

        String commandName = args[0];

        if (commandName.equals("-h") || commandName.equals("-help") || commandName.equals("--help")) {
            printUsage();
            exit(0);
        }

        if (commandName.equals("-v") || commandName.equals("-version") || commandName.equals("--version")) {
            System.out.println("ec compiler version " + COMPILER_VERSION + ", language version " + LANGUAGE_VERSION);
            exit(0);
        }

        if (!commands.containsKey(commandName)) {
            System.err.println("Unknown command: " + commandName);
            printUsage();
            exit(1);
        }

        CLCommand command = commands.get(commandName);
        String cmdArgString = args.length == 1 ?
                              null :
                              args[1];

        if (cmdArgString != null && (cmdArgString.equals("-h") || cmdArgString.equals("-help") || cmdArgString.equals(
                "--help"))) {
            command.printUsage();
            exit(0);
        }

        String[] cmdArgsArray = new String[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            cmdArgsArray[i - 1] = args[i];
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

        /*
        System.out.println("-c configName  The name of the configuration to use.");
        System.out.println(
                "-asv           Advances the current schema version the next time a new schema version is generated.");
        System.out.println("-sdelete       Deletes all the schema files.");
        System.out.println("-tp path:...   Specifies colon delimited search path for templates.");
        System.out.println("-rt name       Runs only the specified template.");
        System.out.println("-tf name       Formats template file.");
        System.out.println("-tfin path     Formats template file specifying its file path.");
        System.out.println(
                "-tfout path    Sends the formatted file to the specified file path (default is same as -tfin path).");
        System.out.println("-D name=value  Defines a variable to a value - they can be accessed via templates.");
        System.out.println("-setup uri     Creates a new project using the specified setup URI, where the URI is:");
        System.out.println("       site:organization/reponame:tag/setupName");
        System.out.println("               site         - only github is currently supported.");
        System.out.println("               organization - The github organization for the setup repo.");
        System.out.println("               reponame     - The name of the repo.");
        System.out.println("               tag          - The tag from which to pull the setup files.");
        System.out.println(
                "               setupName    - Path and name of setup file to run (e.g. setups/BasicWebAppSetup).");
        System.out.println(
                "       This option requires that you use the -D option to define the following variable names:");
        System.out.println(
                "               appIdentifier      - A unique name for your app (e.g., basic-app). This will be the name");
        System.out.println(
                "                                    of the created project directory. This would likely be its github");
        System.out.println(
                "                                    repository name as well if it where to be uploaded there.");
        System.out.println("               appName            - A name for your app (e.g., BasicApp).");
        System.out.println(
                "               apiPrefixNamespace - Represents a URL path prefix for all endpoints of the app. This");
        System.out.println(
                "                                    variable should use a \".\" as a delimiter (e.g., api.basicapp");
        System.out.println(
                "                                    which would result in api/basicapp/ as a url path prefix.");
        System.out.println(
                "               appBasePackage     - This is the base Java package to use for all generated code for the app.");

         */
    }

    public final CLCommand getCommandByName(String name) {
        return commands.get(name);
    }
}

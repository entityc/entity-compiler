/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler;

import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.exit;

class CommandLineParser {

    private final Map<String, String> defineValues         = new HashMap<>();
    public        boolean             help                 = false;
    public        boolean             version              = false;
    public        boolean             verbose              = false;
    public        List<String>        sourceFileNames      = new ArrayList<>();
    public        boolean             advanceSchemaVersion = false;
    public        boolean             deleteSchema         = false;
    public        String              configurationName;
    public        List<String>        templateSearchPaths  = new ArrayList<>();
    public        String              templateToFormat;
    public        String              templateToFormatInPath;
    public        String              templateToFormatOutPath;
    public        String              setupUri;

    public String getDefineValue(String name) {
        return defineValues.get(name);
    }

    public boolean parse(String[] args) {
        int index = 0;
        while (index < args.length) {
            String arg = args[index++];

            if (!arg.startsWith("-")) {
                if (arg.contains("*")) {
                    processWildcardArg(arg);
                } else {
                    sourceFileNames.add(arg);
                }
                continue;
            }

            if (arg.equals("-h") || arg.equals("-help") || arg.equals("--help")) {
                help = true;
                return false;
            }

            if (arg.equals("-v") || arg.equals("-version") || arg.equals("--version")) {
                version = true;
                return false;
            }

            if (arg.equals("-verbose") || arg.equals("--verbose")) {
                verbose = true;
            }

            if ((arg.equals("-c") || arg.equals("-config") || arg.equals("--config")) && index < args.length) {
                configurationName = args[index++];
            }

            if (arg.equals("-tp") && index < args.length) {
                String pathsString = args[index++];
                for (String path : pathsString.split(":")) {
                    templateSearchPaths.add(path);
                }
            }

            if (arg.equals("-tf") && index < args.length) {
                templateToFormat = args[index++];
            }

            if (arg.equals("-tfin") && index < args.length) {
                templateToFormatInPath = args[index++];
            }
            if (arg.equals("-tfout") && index < args.length) {
                templateToFormatOutPath = args[index++];
            }

            if (arg.equals("-s") || arg.equals("-setup") || arg.equals("--setup")) {
                setupUri = args[index++];
            }

            if (arg.equals("-asv")) {
                advanceSchemaVersion = true;
            } else if (arg.equals("-sdelete")) {
                deleteSchema = true;
            }

            if (arg.equals("-D") && (index < args.length)) {
                String   nameValue    = args[index++];
                String[] nameAndValue = nameValue.split("=");
                if (nameAndValue.length == 2) {
                    defineValues.put(nameAndValue[0], nameAndValue[1]);
                }
            }
        }
        return false;
    }

    private void processWildcardArg(String arg) {
        String[] args = arg.split("\\/");
        if (args.length == 1) {
            processSingleDirWildcardArg(arg, ".");
        } else {
            int lastSegmentIndex = args.length - 1;
            if (!args[lastSegmentIndex].contains("*")) {
                System.err.println("ERROR: wildcards only supported in last path segment.");
                exit(1);
            }
            String dirPath = args[0];
            for (int i = 1; i < args.length - 1; i++) {
                dirPath += File.separator + args[i];
            }
            processSingleDirWildcardArg(args[lastSegmentIndex], dirPath);
        }
    }

    private void processSingleDirWildcardArg(String arg, String dirpath) {
        File       dir        = new File(dirpath);
        FileFilter fileFilter = new WildcardFileFilter(arg);
        File[]     files      = dir.listFiles(fileFilter);
        for (File file : files) {
            sourceFileNames.add(dirpath + File.separator + file.getName());
        }
    }

    public void printUsage() {
        System.out.println("ec [options] sourceFile [...]");
        System.out.println();
        System.out.println("Options:");
        System.out.println("-h             Print this help.");
        System.out.println("-v             Print compiler version.");
        System.out.println("-verbose       Outputs informational messages.");
        System.out.println("-c configName  The name of the configuration to use.");
        System.out.println("-asv           Advances the current schema version the next time a new schema version is generated.");
        System.out.println("-sdelete       Deletes all the schema files.");
        System.out.println("-tp path:...   Specifies colon delimited search path for templates.");
        System.out.println("-tf name       Formats template file.");
        System.out.println("-tfin path     Formats template file specifying its file path.");
        System.out.println("-tfout path    Sends the formatted file to the specified file path (default is same as -tfin path).");
        System.out.println("-D name=value  Defines a variable to a value - they can be accessed via templates.");
        System.out.println("-setup uri     Creates a new project using the specified setup URI, where the URI is:");
        System.out.println("       site:organization/reponame:tag");
        System.out.println("               site         - only github is currently supported.");
        System.out.println("               organization - The github organization for the setup repo.");
        System.out.println("               reponame     - The name of the repo.");
        System.out.println("               tag          - The tag from which to pull the setup files.");
        System.out.println("       This option requires that you use the -D option to define the following variable names:");
        System.out.println("               appIdentifier      - A unique name for your app (e.g., basic-app). This will be the name");
        System.out.println("                                    of the created project directory. This would likely be its github");
        System.out.println("                                    repository name as well if it where to be uploaded there.");
        System.out.println("               appName            - A name for your app (e.g., BasicApp).");
        System.out.println("               apiPrefixNamespace - Represents a URL path prefix for all endpoints of the app. This");
        System.out.println("                                    variable should use a \".\" as a delimiter (e.g., api.basicapp");
        System.out.println("                                    which would result in api/basicapp/ as a url path prefix.");
        System.out.println("               appBasePackage     - This is the base Java package to use for all generated code for the app.");
    }
}

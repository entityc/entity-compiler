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

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.entityc.compiler.cmdline.CommandLine;
import org.entityc.compiler.util.ECLog;
import org.entityc.compiler.util.ECStringUtil;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public abstract class CLCommand {

    protected final CommandLine commandLine;
    protected final int         HELP_LINE_WIDTH = 80;
    private final   String      name;
    private final   String      summary;
    private final   String      description;

    CLCommand(CommandLine commandLine, String name, String summary, String description) {
        this.commandLine = commandLine;
        this.name        = name;
        this.summary     = summary;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSummary() {
        return summary;
    }

    abstract public void printUsage();

    abstract public void run(String[] args);

    void printUsageWithArguments(String arguments) {
        printCommandHelpHeader();
        final String prefix       = "usage: ec " + name;
        final String spacing      = " ";
        final String indent       = ECStringUtil.RepeatString(" ", prefix.length() + spacing.length());
        String       wrappedUsage = ECStringUtil.WrapString(arguments, indent, HELP_LINE_WIDTH);
        ECLog.log(prefix + spacing + wrappedUsage);
        ECLog.log("");
    }

    void printCommandHelpHeader() {
        ECLog.log("");
        final String prefix       = name;
        final String spacing      = " -- ";
        final String indent       = ECStringUtil.RepeatString(" ", prefix.length() + spacing.length());
        String       wrappedUsage = ECStringUtil.WrapString(description, indent, HELP_LINE_WIDTH);
        System.out.print(prefix + spacing);
        System.out.println(wrappedUsage);
        ECLog.log("");
    }

    protected List<String> processWildcardArg(String arg) {
        String[] args = arg.split("\\/");
        if (args.length == 1) {
            return processSingleDirWildcardArg(arg, ".");
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
            return processSingleDirWildcardArg(args[lastSegmentIndex], dirPath);
        }
    }

    protected List<String> processSingleDirWildcardArg(String arg, String dirpath) {
        ArrayList<String> sourceFileNames = new ArrayList<>();
        File              dir             = new File(dirpath);
        FileFilter        fileFilter      = new WildcardFileFilter(arg);
        File[]            files           = dir.listFiles(fileFilter);
        for (File file : files) {
            sourceFileNames.add(dirpath + File.separator + file.getName());
        }
        return sourceFileNames;
    }
}

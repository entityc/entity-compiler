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
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.System.exit;

public abstract class CLCommand {

    protected final static int         DISPLAY_LINE_WIDTH = 80;
    protected final        CommandLine commandLine;
    private final          String      name;
    private final          String      summary;
    private final          String      description;

    CLCommand(CommandLine commandLine, String name, String summary, String description) {
        this.commandLine = commandLine;
        this.name        = name;
        this.summary     = summary;
        this.description = description;
    }

    public static void displayItems(String message, Set<String> listOfItems) {
        displayItems(message, listOfItems, StdoutColor.Default);
    }

    public static void displayItems(String message, Set<String> listOfItems, StdoutColor itemColor) {
        ECLog.log("");
        ECLog.log(ECStringUtil.WrapString(message + ":", "", DISPLAY_LINE_WIDTH));
        Collection<String> sortedNames = listOfItems.stream().sorted().collect(Collectors.toList());
        for (String name : sortedNames) {
            ECLog.log("    " + (itemColor != null ?
                                itemColor.stringValue :
                                "") + name);
        }
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
        String       wrappedUsage = ECStringUtil.WrapString(arguments, indent, DISPLAY_LINE_WIDTH);
        ECLog.log(prefix + spacing + wrappedUsage);
        ECLog.log("");
    }

    void printCommandHelpHeader() {
        ECLog.log("");
        final String prefix       = name;
        final String spacing      = " -- ";
        final String indent       = ECStringUtil.RepeatString(" ", prefix.length() + spacing.length());
        String       wrappedUsage = ECStringUtil.WrapString(description, indent, DISPLAY_LINE_WIDTH);
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

    protected String colorize(String text, StdoutColor color) {
        return color.getStringValue() + text + StdoutColor.Default.getStringValue();
    }

    public enum StdoutColor {
        Default(0),
        Brighter(1),
        Underlined(4),
        Flashing(5),
        BlackForeground(30),
        RedForeground(31),
        GreenForeground(32),
        YellowForeground(33),
        BlueForeground(34),
        PurpleForeground(35),
        CyanForeground(36),
        WhiteForeground(37),
        BlackBackground(40),
        RedBackground(41),
        GreenBackground(42),
        YellowBackground(43),
        BlueBackground(44),
        PurpleBackground(45),
        CyanBackground(46),
        WhiteBackground(47),
        ;

        final int    ansiCode;
        final String stringValue;

        StdoutColor(int ansiCode) {
            this.ansiCode    = ansiCode;
            this.stringValue = "\033[1;" + ansiCode + "m";
        }

        public int getAnsiCode() {
            return ansiCode;
        }

        public String getStringValue() {
            return stringValue;
        }
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.util;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.cmdline.command.CLCommand;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.transform.template.tree.FTNode;

import java.io.File;

import static java.lang.System.exit;

public class ECLog {

    private static final String NONE    = "";
    private static final String INFO    = "INFO";
    private static final String WARNING = "WARNING";
    private static final String ERROR   = "ERROR";
    private static final String FATAL   = "FATAL";

    static boolean exitOnFatal = true;

    public static void SetExitOnFatal(boolean shouldExit) {
        exitOnFatal = shouldExit;
    }

    public static void logInfo(ParserRuleContext ctx, String message) {
        log(INFO, ctx, message, false);
    }

    private static void log(String level, ParserRuleContext ctx, String message, boolean fatal) {
        if (ctx == null) {
            log(level, message, fatal);
        } else {
            String filename = filenameFromFullPath(ctx.getStart().getTokenSource().getSourceName());
            System.out.println(level + ": " + filename + "(" + ctx.getStart().getLine() + "," + (
                    ctx.getStart().getCharPositionInLine() + 1) + ") " + message + endColorization());
        }
        if (fatal) {
            if (exitOnFatal) {
                exit(1);
            } else {
                throw new RuntimeException(message);
            }
        }
    }

    private static void log(String level, String message, boolean fatal) {
        if (level.isEmpty()) {
            System.out.println(message);
        } else {
            System.out.println(colorizedLevel(level) + ": " + message + endColorization());
        }
        if (fatal) {
            exit(1);
        }
    }

    private static String filenameFromFullPath(String fullPath) {
        if (fullPath == null) {
            return "??";
        }
        int lastSlash = fullPath.lastIndexOf(File.separator);
        if (lastSlash == -1) {
            return fullPath;
        }
        return fullPath.substring(lastSlash + 1);
    }

    private static String endColorization() {
        return CLCommand.StdoutColor.Default.getStringValue();
    }

    private static String colorizedLevel(String level) {
        if (level.equals(WARNING)) {
            level = CLCommand.StdoutColor.YellowForeground.getStringValue() + level;
        } else if (level.equals(ERROR) || level.equals(FATAL)) {
            level = CLCommand.StdoutColor.RedForeground.getStringValue() + level;
        }
        return level;
    }

    public static void logWarning(ParserRuleContext ctx, String message) {
        log(WARNING, ctx, message, false);
    }

    public static void logError(ParserRuleContext ctx, String message) {
        log(ERROR, ctx, message, false);
    }

    public static void logFatal(ParserRuleContext ctx, String message) {
        log(FATAL, ctx, message, true);
    }

    public static void logInfo(FTNode node, String message) {
        log(INFO, node, message, false);
    }

    private static void log(String level, FTNode node, String message, boolean fatal) {
        if (node == null || node.getSourceName() == null) {
            log(level, message, fatal);
            return;
        }
        String filename = filenameFromFullPath(node.getSourceName());
        System.out.println(
                level + ": " + filename + "(" + node.getStartLineNumber() + "," + node.getStartCharPosition() + ") "
                + message + endColorization());
        if (fatal) {
            exit(1);
        }
    }

    public static void logWarning(FTNode node, String message) {
        log(WARNING, node, message, false);
    }

    public static void logError(FTNode node, String message) {
        log(ERROR, node, message, false);
    }

    public static void logFatal(FTNode node, String message) {
        log(FATAL, node, message, true);
    }

    public static void logWarning(MTNode node, String message) {
        log(WARNING, node, message, false);
    }

    private static void log(String level, MTNode node, String message, boolean fatal) {
        if (node == null) {
            log(level, message, fatal);
            return;
        }
        String filename = filenameFromFullPath(node.getSourceName());
        System.out.println(
                colorizedLevel(level) + ": " + filename + "(" + node.getStartLineNumber() + ","
                + node.getStartCharPosition() + ") "
                + message + endColorization());
        if (fatal) {
            exit(1);
        }
    }

    public static void logError(MTNode node, String message) {
        log(ERROR, node, message, false);
    }

    public static void logFatal(MTNode node, String message) {
        log(FATAL, node, message, true);
    }

    public static void logWarning(String message) {
        log(WARNING, message, false);
    }

    public static void logError(String message) {
        log(ERROR, message, false);
    }

    public static void logFatal(String message) {
        log(FATAL, message, true);
    }

    public static void logInfo(String message) {
        log(INFO, message, false);
    }

    public static void log(String message) {
        log(NONE, message, false);
    }
}

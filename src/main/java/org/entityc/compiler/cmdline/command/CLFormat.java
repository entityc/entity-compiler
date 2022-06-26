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

public class CLFormat extends CLCommand {

    public CLFormat(CommandLine commandLine) {
        super(commandLine,
              "format",
              "Formats template source files.",
              "Formats template source files given formatting preferences.");
    }

    @Override
    public void printUsage() {
        super.printUsageWithArguments("[-p <prefs-file> [<file> ...]");
    }

    @Override
    public void run(String[] args) {
/*
        // CODE FORMATTING
        if (cmdLineParser.templateToFormat != null) {
            MTSpace space = new MTSpace(null, "formatterSpace");
            root.setSpace(space);
            MTConfiguration config = new MTConfiguration(null, root, "formatter");
            root.addConfiguration(config);

            MTFile file = null;
            for (String basePath : cmdLineParser.templateSearchPaths) {
                File f = new File(basePath + File.separator + cmdLineParser.templateToFormat + ".eml");
                if (f.exists()) {
                    file = new MTFile(null, f);
                }
            }
            MTTemplate mtTemplate = new MTTemplate(null, config, file);
            TransformManager.AddTransform(new FileTemplateTransform(config, mtTemplate, file.getPath()));
            FTTemplate ftTemplate = mtTemplate.parse((FTTransformSession) null, true);
            File       outFile    = null;
            if (cmdLineParser.templateToFormatOutPath != null) {
                outFile = new File(cmdLineParser.templateToFormatOutPath);
            } else {
                outFile = new File(file.getPath());
            }
            MTCodeFormat codeFormat = root.getCodeFormat("Default");
            ftTemplate.formatCodeToFile(outFile, codeFormat);
            exit(0);
        }
        // CODE FORMATTING
        if (cmdLineParser.templateToFormatInPath != null) {
            MTSpace space = new MTSpace(null, "formatterSpace");
            root.setSpace(space);
            MTConfiguration config = new MTConfiguration(null, root, "formatter");
            root.addConfiguration(config);
            MTFile file = null;
            File   f    = new File(cmdLineParser.templateToFormatInPath);
            if (f.exists()) {
                file = new MTFile(null, f);
            } else {
                ECLog.logFatal("The specified template file to format does not exist: "
                               + cmdLineParser.templateToFormatInPath);
            }

            MTTemplate mtTemplate = new MTTemplate(null, config, file);
            TransformManager.AddTransform(new FileTemplateTransform(config, mtTemplate, file.getPath()));
            FTTemplate ftTemplate = mtTemplate.parse((FTTransformSession) null, true);
            File       outFile    = null;
            if (cmdLineParser.templateToFormatOutPath != null) {
                outFile = new File(cmdLineParser.templateToFormatOutPath);
            } else {
                outFile = new File(file.getPath());
            }
            MTCodeFormat codeFormat = root.getCodeFormat("Default");
            ftTemplate.formatCodeToFile(outFile, codeFormat);
            exit(0);
        }

 */
    }
}

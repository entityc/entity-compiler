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
import org.entityc.compiler.model.MTCodeFormat;
import org.entityc.compiler.model.MTRoot;
import org.entityc.compiler.model.config.MTConfiguration;
import org.entityc.compiler.model.config.MTFile;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.config.MTTemplate;
import org.entityc.compiler.transform.TransformManager;
import org.entityc.compiler.transform.template.FileTemplateTransform;
import org.entityc.compiler.transform.template.tree.FTTemplate;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.util.ECLog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class CLFormat extends CLCommand {

    public CLFormat(CommandLine commandLine) {
        super(commandLine,
              "format",
              "Formats template source files.",
              "Formats template source files given formatting preferences.");
    }

    @Override
    public void printUsage() {
        super.printUsageWithArguments("[-o <output-directory>] [<file> ...]");
    }

    @Override
    public void run(String[] args) {
        List<String> filenames       = new ArrayList<>();
        String       outputDirectory = null;
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.equals("-o")) {
                if (i == args.length) {
                    ECLog.logFatal("Missing output directory.");
                }
                outputDirectory = args[++i];
            } else {
                if (arg.contains("*")) {
                    filenames.addAll(processWildcardArg(arg));
                } else {
                    filenames.add(arg);
                }
            }
        }
        MTRoot  root  = new MTRoot(null);
        MTSpace space = new MTSpace(null, "formatterSpace");
        root.setSpace(space);
        MTConfiguration config = new MTConfiguration(null, root, "formatter");
        root.addConfiguration(config);
        MTFile file = null;

        for (String filename : filenames) {
            File f = new File(filename);
            if (f.exists()) {
                file = new MTFile(null, f);
            } else {
                ECLog.logFatal("The specified template file to format does not exist: "
                               + filename);
            }

            MTTemplate mtTemplate = new MTTemplate(null, config, file);
            TransformManager.AddTransform(new FileTemplateTransform(config, mtTemplate, file.getPath()));
            FTTemplate ftTemplate = mtTemplate.parse((FTTransformSession) null, true);
            File       outFile    = null;
            if (outputDirectory != null) {
                outFile = new File(outputDirectory + File.separator + file.getPath());
            } else {
                outFile = new File(file.getPath());
            }
            MTCodeFormat codeFormat = root.getCodeFormat("Default");
            ftTemplate.formatCodeToFile(outFile, codeFormat);
        }
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform;

import org.entityc.compiler.cmdline.CommandLine;
import org.entityc.compiler.model.MTRoot;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TransformManager {

    private static final Map<String, MTBaseTransform> transforms = new HashMap<>();

    public static void LoadBuiltins(MTRoot model, String configurationName) {
        // Server
        AddTransform(new MTVImplicitTransform(model, configurationName));
        AddTransform(new MTVPostgresTransform(model, configurationName));
        AddTransform(new MTVReleasedTransform(model, configurationName));
    }

    public static void AddTransform(MTBaseTransform transform) {
        transforms.put(transform.getName(), transform);
    }

    public static MTBaseTransform GetTransformByName(String name) {
        return transforms.get(name);
    }

    private static File SearchForTemplateFile(File directory, String templateFilename) {
        if (directory.exists() && directory.isDirectory()) {
            String fullPath = directory.getPath() + File.separator + templateFilename;
            File   file     = new File(fullPath);
            if (file.exists() && !file.isDirectory()) {
                return file;
            }
            File[] subDirs = directory.listFiles(File::isDirectory);
            for (File subDir : subDirs) {
                File foundFile = SearchForTemplateFile(subDir, templateFilename);
                if (foundFile != null) {
                    return foundFile;
                }
            }
        }
        return null;
    }

    public static File FindTemplateFile(String templateFilename) {
        for (String searchPath : CommandLine.GetTemplateSearchPaths()) {
            File dir       = new File(searchPath);
            File foundFile = SearchForTemplateFile(dir, templateFilename);
            if (foundFile != null) {
                return foundFile;
            }
        }
        return null;
    }
}

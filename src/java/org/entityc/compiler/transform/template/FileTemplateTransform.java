/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template;

import org.entityc.compiler.model.MTCodeFormat;
import org.entityc.compiler.model.MTRoot;
import org.entityc.compiler.model.config.MTConfiguration;
import org.entityc.compiler.model.config.MTTemplate;
import org.entityc.compiler.transform.TransformManager;
import org.entityc.compiler.util.ECLog;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import java.io.File;
import java.io.IOException;

public class FileTemplateTransform extends TemplateTransform {

    private final String     templateFilename;
    private final MTTemplate mtTemplate;
    private       File       file;

    public FileTemplateTransform(MTRoot model, String configurationName, MTTemplate mtTemplate, String filename) {
        super(mtTemplate.getName(), model, configurationName, mtTemplate.getRepositoryImport() != null ? mtTemplate.getRepositoryImport().getRepositoryName() : null);
        this.mtTemplate = mtTemplate;
        this.templateFilename = filename;
    }

    public FileTemplateTransform(MTConfiguration configuration, MTTemplate mtTemplate, String filename) {
        super(mtTemplate.getName(), configuration.getRoot(), configuration.getName(), mtTemplate.getRepositoryImport() != null ? mtTemplate.getRepositoryImport().getRepositoryName() : null);
        this.mtTemplate = mtTemplate;
        this.templateFilename = filename;
    }

    @Override
    public boolean canStart() {
        return findTemplateFile();
    }

    public boolean findTemplateFile() {
        if (this.mtTemplate.getRepositoryImport() != null || mtTemplate.isNotDeclared()) {
            this.file = new File(templateFilename);
            return true;
        }
        this.file = TransformManager.FindTemplateFile(templateFilename);
        return this.file != null;
    }

    @Override
    public void load() {
        this.load(false);
    }

    public void load(boolean suppressImport) {

        //ECLog.logInfo("Parsing template: " + getName());

        if (!findTemplateFile()) {
            ECLog.logFatal("ERROR: Unable to find a template file with the name: " + templateFilename);
        }

        //ECLog.logInfo("Reading template file: " + file.getAbsolutePath());
        CharStream input = null;
        try {
            input = CharStreams.fromFileName(file.getAbsolutePath());
        } catch (IOException e) {
            ECLog.logFatal("Unable to open template source file: " + file.getAbsolutePath() + " (" + mtTemplate.getName() + ": " + templateFilename + ")");
        }
        loadFromStream(mtTemplate.getName(), file.getName(), input, suppressImport);
    }

    public void formatCode(MTCodeFormat codeFormat) {
        load(true);
        template.formatCodeToFile(file, codeFormat);
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.config;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.doc.annotation.ModelMethod;
import org.entityc.compiler.doc.annotation.ModelMethodCategory;
import org.entityc.compiler.transform.MTBaseTransform;
import org.entityc.compiler.transform.TransformManager;
import org.entityc.compiler.transform.template.FileTemplateTransform;
import org.entityc.compiler.transform.template.tree.FTTemplate;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.util.ECLog;

@ModelClass(type = ModelClassType.CONFIGURATION, description = "Represents a template configuration.")
public class MTTemplate extends MTTransform {

    private boolean            contextual;
    private MTRepositoryImport repositoryImport;
    private String             directoryName;
    private MTFile             file;

    public MTTemplate(ParserRuleContext ctx, MTConfiguration configuration, String name) {
        super(ctx, configuration, name);
    }

    public MTTemplate(ParserRuleContext ctx, MTConfiguration configuration, MTFile file) {
        super(ctx, configuration, file.getNameWithoutExtension());
        this.file = file;
    }

    public MTFile getFile() {
        return file;
    }

    @ModelMethod(category = ModelMethodCategory.CONFIGURATION,
        description = "Returns the filename of the template which is preceded by a directory name if available.")
    public String getFilename() {
        String filename = "";
        if (this.getDirectoryName() != null) {
            filename = this.getDirectoryName() + "/";
        }
        filename += this.getName();
        return filename;
    }

    @ModelMethod(category = ModelMethodCategory.CONFIGURATION,
        description = "Returns the directory name if available. Otherwise returns `null`")
    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public boolean isNotDeclared() {
        return file != null;
    }

    @ModelMethod(category = ModelMethodCategory.CONFIGURATION,
        description = "Returns an object that defines how the template will be imported.")
    public MTRepositoryImport getRepositoryImport() {
        return repositoryImport;
    }

    public void setRepositoryImport(MTRepositoryImport repositoryImport) {
        this.repositoryImport = repositoryImport;
    }

    @Deprecated
    public boolean isContextual() {
        return contextual;
    }

    @Deprecated
    public void setContextual(boolean contextual) {
        this.contextual = contextual;
    }

    @ModelMethod(category = ModelMethodCategory.CONFIGURATION,
        description = "Returns the output directory considered to be the primary output for the template.")
    public MTDirectory getPrimaryOutputDirectory() {
        String directoryName = getOutputNameByLocalName("primary");
        if (directoryName == null) {
            return null;
        }
        return this.configuration.getOutputByName(directoryName);
    }

    public FTTemplate parse(FTTransformSession session, boolean suppressImport) {
        MTBaseTransform transform = TransformManager.GetTransformByName(getName());
        if (transform == null) {
            ECLog.logFatal(this, "No template named \"" + getName() + "\".");
        }
        if (!(transform instanceof FileTemplateTransform)) {
            ECLog.logFatal(this, "When looking for template named \"" + getName()
                                 + "\" found a transform but its not a template.");
        }

        return parse((FileTemplateTransform) transform, suppressImport);
    }

    public FTTemplate parse(FileTemplateTransform transform, boolean suppressImport) {
        transform.load(suppressImport);
        return transform.getTemplate();
    }
}

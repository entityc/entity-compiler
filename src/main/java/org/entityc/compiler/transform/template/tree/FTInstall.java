/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

import org.antlr.v4.runtime.ParserRuleContext;
import org.apache.commons.io.FileUtils;
import org.entityc.compiler.doc.annotation.TemplateInstruction;
import org.entityc.compiler.doc.annotation.TemplateInstructionArgument;
import org.entityc.compiler.doc.annotation.TemplateInstructionCategory;
import org.entityc.compiler.model.config.MTDirectory;
import org.entityc.compiler.model.config.MTFile;
import org.entityc.compiler.model.config.MTRepository;
import org.entityc.compiler.model.config.MTRepositoryImport;
import org.entityc.compiler.model.config.MTTemplate;
import org.entityc.compiler.model.domain.MTDomain;
import org.entityc.compiler.model.language.MTLanguage;
import org.entityc.compiler.repository.RepositoryCache;
import org.entityc.compiler.repository.RepositoryFile;
import org.entityc.compiler.repository.RepositoryImportManager;
import org.entityc.compiler.transform.template.FileTemplateTransform;
import org.entityc.compiler.transform.template.TemplateLexer;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.transform.template.tree.expression.FTConstant;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.util.ECLog;

import java.io.File;
import java.io.IOException;

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.InstructionKeyword;
import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.RequiredSpace;

@TemplateInstruction(category = TemplateInstructionCategory.FILE_IO,
    name = "install",
    usage = "`install `[`copy`]` `*sourceExpression*` `*destExpression*",
    summary = "Provides an easy way to install files into your local project.",
    description = "When setting up a project using files from a library, this instruction makes it easy "
                  + "to install files from that library into the project directory structure. The files being installed "
                  + "have their native file extension (not the one we use for templates) so they are easily "
                  + "viewable and edited (using the code highlighter appropriate for them). You can also include "
                  + "template code in them and the template code will execute during the install process. "
                  + "Of course, the presence of the template code may interfere with the code highlighter and "
                  + "as well, the template code will not be appropriately highlighted but when the amount of "
                  + "template code is relatively small it can be very advantageous to use this install method over "
                  + "just making it a template and running the template.")
public class FTInstall extends FTNode {

    private final FTExpression sourceExpression;
    private final FTExpression destNamespaceExpression;
    private       String       sourceRepositoryName;
    private       boolean      copyOnly;

    public FTInstall(ParserRuleContext ctx,
                     @TemplateInstructionArgument(optional = true, keyword = true,
                         description = "If specified, the file will be copied verbatim without trying to execute "
                                       + "any template code.")
                         Boolean copy,
                     @TemplateInstructionArgument(
                         description = "Defines the path and filename with extension of the source file. The path "
                                       + "is relative to the directory in which the template was configured.")
                         FTExpression sourceExpression,
                     @TemplateInstructionArgument(
                         description = "Defines the destination path and directory name to copy the file into. "
                                       + "The path is relative to the directory in which the template was configured.")
                     FTExpression destExpression) {
        super(ctx);
        this.copyOnly                = copy;
        this.sourceExpression        = sourceExpression;
        this.destNamespaceExpression = destExpression;
    }

    public boolean isCopyOnly() {
        return copyOnly;
    }

    public String getSourceRepositoryName() {
        return sourceRepositoryName;
    }

    public void setSourceRepositoryName(String sourceRepositoryName) {
        this.sourceRepositoryName = sourceRepositoryName;
    }

    @Override
    public void transform(FTTransformSession session) {
        if (!sourceExpression.isConstant()) {
            ECLog.logFatal("Only string constant supported for source directory/file.");
        }
        Object destFilepathObject = destNamespaceExpression.getValue(session);
        if (!(destFilepathObject instanceof String)) {
            ECLog.logFatal(this, "Destination expression of install instruction must resolve to a string value.");
        }
        String destFilepath   = (String) destFilepathObject;
        String sourceFilePath = ((FTConstant) sourceExpression).getStringValue();

        String filename = sourceFilePath;
        if (filename.contains("/")) {
            filename = filename.substring(filename.lastIndexOf('/') + 1);
        }

        if (sourceRepositoryName == null) {
            ECLog.logFatal(this, "Cannot install files without an associated Repository.");
        }

        MTRepository repository = session.getConfiguration().getSpace().getRepositoryByName(sourceRepositoryName);

        if (repository == null) {
            ECLog.logFatal(this, "Unable to find repository: " + sourceRepositoryName);
        }

        //ECLog.logInfo("Full relative path for install source file: " + repository.getPath() + "/" + sourceFilePath);

        String sourceFileExtension    = null;
        String sourceFilename         = null;
        int    lastPathSeparatorIndex = sourceFilePath.lastIndexOf(File.separator);
        int    lastExtensionIndex     = sourceFilePath.lastIndexOf(".");
        if (lastExtensionIndex != -1) {
            if (lastPathSeparatorIndex == -1 || lastExtensionIndex > lastPathSeparatorIndex) {
                sourceFileExtension = sourceFilePath.substring(lastExtensionIndex + 1);
                sourceFilePath      = sourceFilePath.substring(0, lastExtensionIndex);
                if (lastPathSeparatorIndex != -1) {
                    sourceFilename = sourceFilePath.substring(lastPathSeparatorIndex + 1);
                }
                else {
                    sourceFilename = sourceFilePath;
                }
            }
        }

        RepositoryImportManager importManager = new RepositoryImportManager(
            RepositoryCache.CacheStructure.UserCache);
        MTRepositoryImport repositoryImport = new MTRepositoryImport(null, false);
        repositoryImport.setRepositoryName(sourceRepositoryName);
        repositoryImport.setFilename(sourceFilePath);
        RepositoryFile repositoryFile = importManager.importFromRepository(session.getConfiguration().getSpace(),
                                                                           repositoryImport, sourceFileExtension,
                                                                           false);
        File fileToInstall = new File(repositoryFile.getFilepath());

        MTDomain   domain   = (MTDomain) session.getValue("domain");
        MTLanguage language = (MTLanguage) session.getValue("language");

        String sourceFilenameToInstall = null;
        // need to run this as if it is a template being run within the running template but its output automatically goes to the destination directory specified.
        try {
            sourceFilenameToInstall = fileToInstall.getCanonicalPath();
            //ECLog.logInfo(">>>> File to install: " + sourceFilenameToInstall + " to: " + destFilepath + "/" + sourceFilePath + "." + sourceFileExtension);
        } catch (IOException e) {
            ECLog.logFatal(this, e.getMessage());
        }

        FTTemplate template = new FTTemplate(null);
        template.setName(sourceFilename + "." + sourceFileExtension);
        if (domain != null) {
            template.setDefaultDomainName(domain.getName());
        }
        if (language != null) {
            template.setLanguage(language.getName());
        }

        MTDirectory directoryOfTemplateFile = new MTDirectory(null, "something");
        directoryOfTemplateFile.setPath(destFilepath);
        MTFile templateFile = new MTFile(directoryOfTemplateFile, fileToInstall);

        MTTemplate         mtTemplate         = new MTTemplate(null, session.getConfiguration(), templateFile);
        MTRepositoryImport mtRepositoryImport = new MTRepositoryImport(null, false);
        mtRepositoryImport.setRepositoryName(repository.getName());
        mtTemplate.setRepositoryImport(mtRepositoryImport);
        FileTemplateTransform fileTemplateTransform = new FileTemplateTransform(session.getConfiguration(), mtTemplate,
                                                                                sourceFilenameToInstall);

        if (!copyOnly) {
            fileTemplateTransform.load();
        }
        FTTemplate ftTemplate = fileTemplateTransform.getTemplate();
        // add a file instruction to write the entire "template" to the destination
        FTFile ftFile = new FTFile(null, ftTemplate, false, destNamespaceExpression,
                                   new FTConstant(null, sourceFilename),
                                   new FTConstant(null, sourceFileExtension));
        if (copyOnly) {
            try {
                ftFile.open(session);
                FileUtils.copyFile(fileToInstall, new File(ftFile.getFullFilePath()));
            } catch (IOException e) {
                ECLog.logFatal(this, "Unable to copy file \"" + fileToInstall.getName() + "\" in install.");
            }
            return;
        }
        ftTemplate.insertTopContainer(ftFile);
        ftTemplate.transform(session); // run it with this session
    }

    @Override
    public void accept(FTVisitor visitor) {

    }

    @Override
    public int getTemplateLexerSymbol() {
        return TemplateLexer.Install;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;

        formatController.addInstructionStart(indentLevel, this);
        if (copyOnly) {
            formatController.addInstructionInside(InstructionKeyword, "copy", -1);
        }
        if (!sourceExpression.format(formatController, indentLevel)) {
            success = false;
        }
        formatController.addInstructionInside(RequiredSpace, " ", this.getStartLineNumber());
        if (!destNamespaceExpression.format(formatController, indentLevel)) {
            success = false;
        }
        formatController.addInstructionEnd(this);
        return success;
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.EntityCompiler;
import org.entityc.compiler.doc.annotation.TemplateInstruction;
import org.entityc.compiler.doc.annotation.TemplateInstructionArgument;
import org.entityc.compiler.doc.annotation.TemplateInstructionCategory;
import org.entityc.compiler.model.config.MTDirectory;
import org.entityc.compiler.transform.template.TemplateLexer;
import org.entityc.compiler.transform.template.formatter.ConfigurableElement;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.transform.template.tree.expression.FTConstant;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.util.ECLog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Optional;

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.RequiredSpace;

@TemplateInstruction(category = TemplateInstructionCategory.FILE_IO,
    name = "file",
    usage = "`file `[`ifdoesnotexist`]` `*pathExpression*` `*filenameExpression*` `*extensionExpression*",
    summary = "Allows you to direct the template output to a file.",
    description =
        "Defines a block of the template who's output will be directed to a specified file. The base directory "
        + "is the working directory where the compiler was invoked."
        + "Note that each of the arguments are expressions, which means they can come from an expression or simply "
        + "from a string constant. Following the current working directory, the "
        + "*pathExpression* is used to further provide a path to the ultimate directory where the file "
        + "*pathExpression*`/`*filenameExpression*`.`*extensionExpression* will be written. You **cannot** nest "
        + "a `file` instruction inside another `file` instruction.",
    contents = "The template block inside the bounds of this instruction will be executed and its output sent "
               + "to the file specified (aside from the behavior of the `ifdoesnotexist` option if specified). Any "
               + "other output redirecting block instruction contained in here will pause the writing to the file "
               + "until that block has finished. At the end of this block, the file will be closed.")
public class FTFile extends FTContainerNode implements FTBodyBlock {

    private final FTExpression namespaceExpression;
    private final FTExpression filenameExpression;
    private final FTExpression extensionExpression;
    private final FTBody       body           = new FTBody();
    private       String       fullFilePath;
    private       boolean      ifDoesNotExist;
    private       boolean      makeExecutable = false; // open() will set this for close()

    public FTFile(ParserRuleContext ctx,
                  FTContainerNode parent,
                  @TemplateInstructionArgument(keyword = true,
                      description = "If specified, the file will only be written if it does not already exist. This "
                                    + "would be used for files you want to initially setup for the user "
                                    + "but after this initial setup, the user is expected to maintain the file.")
                      Boolean ifdoesnotexist,
                  @TemplateInstructionArgument(
                      description =
                          "The path of the directory containing the destination file. This path is relative to the "
                          + "directory where the compiler was launched.")
                      FTExpression pathExpression,
                  @TemplateInstructionArgument(
                      description = "This is the filename for the output file.")
                      FTExpression filenameExpression,
                  @TemplateInstructionArgument(
                      description = "This is the file extension for the output file.")
                      FTExpression extensionExpression) {
        super(ctx, parent);
        this.ifDoesNotExist      = ifdoesnotexist;
        this.namespaceExpression = pathExpression;
        this.filenameExpression  = filenameExpression;
        this.extensionExpression = extensionExpression;
    }

    public String getFullFilePath() {
        return fullFilePath;
    }

    @Override
    public FTBody getBody() {
        return body;
    }

    @Override
    public void transform(FTTransformSession session) {
        if (open(session)) {
            super.transform(session);
            close(session);
        }
    }

    public boolean open(FTTransformSession session) {
        String      namespace = null;
        MTDirectory directory = null;
        if (namespaceExpression != null) {
            if (namespaceExpression.isConstant()) {
                namespace = ((FTConstant) namespaceExpression).getStringValue();
            }
            else {
                Object namespaceValue = this.namespaceExpression.getValue(session);
                if (namespaceValue == null) {
                    ECLog.logFatal(namespaceExpression, "Unable to resolve namespace expression.");
                }
                else if (namespaceValue instanceof MTDirectory) {
                    directory = (MTDirectory) namespaceValue;
                    namespace = directory.getPath();
                }
                else {
                    namespace = namespaceValue.toString();
                }
            }
        }

        if (filenameExpression == null) {
            ECLog.logFatal(this, "Cannot open file without knowing the filename.");
        }

        if (session.getNamedOutput() == null) {
            ECLog.logFatal(this, "The template must define a named output.");
        }

        String baseFilename = null;
        if (filenameExpression.isConstant()) {
            baseFilename = ((FTConstant) filenameExpression).getStringValue();
        }
        else {
            Object filteredResult = this.filenameExpression.getValue(session).toString();
            //System.out.println("filteredResult = " + filteredResult.toString());
            baseFilename = filteredResult instanceof String ?
                           (String) filteredResult :
                           "";
        }

        String extension = null;
        if (extensionExpression.isConstant()) {
            extension = ((FTConstant) extensionExpression).getStringValue();
        }
        else {
            extension = extensionExpression.getValue(session).toString();
        }

        makeExecutable = extensionIsExecutable(extension);

        if (baseFilename == null || baseFilename.length() == 0
            || extension == null || extension.length() == 0) {
            ECLog.logFatal(this, "Unable to construct filename.");
        }

        String fullBasePath = directory != null ?
                              "" :
                              session.getNamedOutput().getPath();

        if (namespace != null && namespace.length() > 0) {
            if (!fullBasePath.isEmpty()) {
                fullBasePath += File.separator;
            }
            fullBasePath += namespace;
        }

        if (!fullBasePath.isEmpty()) {
            EntityCompiler.ensureDirectory(fullBasePath);
        }

        this.fullFilePath = (fullBasePath.isEmpty() ?
                             "" :
                             fullBasePath + File.separator) + baseFilename + "." + extension;

        File theFile = new File(this.fullFilePath);
        if (isIfDoesNotExist() && theFile.exists()) {
            session.registerFileBlock(this);
            return false;
        }
        session.pushFileBlock(this);

        return true;
    }

    public void close(FTTransformSession session) {
        try {
            PrintStream output = new PrintStream(this.fullFilePath);
            output.print(body.getText());
            session.popFileBlock();
            output.close();
            if (makeExecutable) {
                File file = new File(this.fullFilePath);
                file.setExecutable(true);
            }
        } catch (FileNotFoundException e) {
            ECLog.logFatal(this, "Unable to open template output file: " + this.fullFilePath);
        }
    }

    private boolean extensionIsExecutable(String extension) {
        final String[] executableExtensions = new String[]{"sh", "csh", "ksh"};
        for (String exeExt : executableExtensions) {
            if (extension.equals(exeExt)) {
                return true;
            }
        }
        return false;
    }

    public boolean isIfDoesNotExist() {
        return ifDoesNotExist;
    }

    public void setIfDoesNotExist(boolean ifDoesNotExist) {
        this.ifDoesNotExist = ifDoesNotExist;
    }

    public Optional<String> extract(String startMarker, String endMarker) {
        if (startMarker == null || endMarker == null) {
            ECLog.logError("Either start or end marker is missing.");
            return null;
        }
        BufferedReader reader              = null;
        StringBuffer   extractedTextBuffer = new StringBuffer();
        boolean        foundStart          = false;
        boolean        foundBoth           = false;
        try {
            reader = new BufferedReader(new FileReader(this.fullFilePath), 1024);
            do {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                if (foundStart) {
                    if (line.contains(endMarker)) {
                        foundBoth = true;
                        break;
                    }
                    extractedTextBuffer.append(line + "\n");
                }
                else if (line.contains(startMarker)) {
                    foundStart = true;
                }
            } while (reader.ready());

            reader.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        if (!foundBoth) {
            return Optional.empty();
        }
        return Optional.of(extractedTextBuffer.toString());
    }

    @Override
    public void accept(FTVisitor visitor) {
        super.accept(visitor);
        namespaceExpression.accept(visitor);
        filenameExpression.accept(visitor);
        extensionExpression.accept(visitor);
    }

    @Override
    public int getTemplateLexerSymbol() {
        return TemplateLexer.File;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;

        formatController.addInstructionStart(indentLevel, this);
        if (ifDoesNotExist) {
            formatController.addInstructionInside(ConfigurableElement.None, "ifdoesnotexist",
                                                  this.getStartLineNumber());
            formatController.addInstructionInside(RequiredSpace, " ", this.getStartLineNumber());
        }
        if (!namespaceExpression.format(formatController, indentLevel)) {
            success = false;
        }
        formatController.addInstructionInside(RequiredSpace, " ", this.getStartLineNumber());
        if (!filenameExpression.format(formatController, indentLevel)) {
            success = false;
        }
        formatController.addInstructionInside(RequiredSpace, " ", this.getStartLineNumber());
        if (!extensionExpression.format(formatController, indentLevel)) {
            success = false;
        }
        formatController.addInstructionEnd(this);
        if (!super.formatChildren(formatController, indentLevel)) {
            success = false;
        }
        formatController.addInstructionBlockEnd(indentLevel, this);
        return success;
    }

    @Override
    public boolean hasOwnBody() {
        return true;
    }
}

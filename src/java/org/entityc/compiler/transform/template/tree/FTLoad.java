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
import org.entityc.compiler.protobuf.PBLoaderExtractor;
import org.entityc.compiler.transform.template.TemplateLexer;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.util.ECLog;

import java.io.File;

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.InstructionArgument;
import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.RequiredSpace;

@TemplateInstruction(category = TemplateInstructionCategory.FILE_IO,
    name = "load",
    usage = "`load `*type* *pathExpression* *filenameExpression* *extensionExpression*",
    summary = "Loads specifically supported files that can add certain data to the model.",
    description = "This instruction is used to load specific types of data from a file that will alter specific data "
                  + "in the model. As with `file`, the same three arguments *path* *filename* *extension* are used "
                  + "specify the file. The *type* argument specifies the type of file to be loaded. Currently "
                  + "only `proto` is supported. This loader expects the file to be a Protobuf protocol file and "
                  + "parses it to extract field numbers and assign to appropriate attributes.")
public class FTLoad extends FTNode {

    private final Type         type;
    private final FTExpression namespaceExpression;
    private final FTExpression filenameExpression;
    private final FTExpression extensionExpression;

    public FTLoad(ParserRuleContext ctx,
                  @TemplateInstructionArgument(description = "One of a supported type of file to read. Only `proto` is "
                                                             + "supported at this time.")
                      Type type,
                  @TemplateInstructionArgument(
                      description =
                          "The path of the directory containing the source file. This path is relative to the "
                          + "directory where the compiler was launched."
                  )
                      FTExpression pathExpression,
                  @TemplateInstructionArgument(
                      description = "This is the filename of the input file.")
                      FTExpression filenameExpression,
                  @TemplateInstructionArgument(
                      description = "This is the file extension of the input file.")
                      FTExpression extensionExpression) {
        super(ctx);
        this.type                = type;
        this.namespaceExpression = pathExpression;
        this.filenameExpression  = filenameExpression;
        this.extensionExpression = extensionExpression;
    }

    @Override
    public void transform(FTTransformSession session) {
        String namespace = null;
        if (namespaceExpression != null) {
            namespace = namespaceExpression.getValue(session).toString();
        }

        String baseFilename = null;
        if (filenameExpression != null) {
            baseFilename = filenameExpression.getValue(session).toString();
        }

        String extension = null;
        if (extensionExpression != null) {
            extension = extensionExpression.getValue(session).toString();
        }

        if (baseFilename == null || baseFilename.length() == 0
            || extension == null || extension.length() == 0) {
            ECLog.logFatal(this, "Unable to construct filename.");
        }

        String fullBasePath = "";

        if (session.getNamedOutput().getPath() != null && session.getNamedOutput().getPath().length() > 0) {
            fullBasePath = session.getNamedOutput().getPath();
        }

        if (namespace != null && namespace.length() > 0) {
            if (fullBasePath.length() > 0) {
                fullBasePath += File.separator;
            }
            fullBasePath += namespace;
            EntityCompiler.ensureDirectory(fullBasePath);
        }

        String fullFilename = ((fullBasePath.length() > 0) ?
                               (fullBasePath + File.separator) :
                               "") + baseFilename + "." + extension;

        if (type == Type.Protobuf) {
            // open the file and run the parser
            PBLoaderExtractor loader = new PBLoaderExtractor();
            loader.load(fullFilename);
            session.applyLoader(this, loader);
        }
        else {
            ECLog.logFatal(this, "Unknown type of file to load.");
        }
    }

    @Override
    public void accept(FTVisitor visitor) {
        visitor.visit(this);
        namespaceExpression.accept(visitor);
        filenameExpression.accept(visitor);
        extensionExpression.accept(visitor);
    }

    @Override
    public int getTemplateLexerSymbol() {
        return TemplateLexer.Load;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;

        formatController.addInstructionStart(indentLevel, this);
        formatController.addInstructionInside(InstructionArgument, type.name, this.getStartLineNumber());
        formatController.addInstructionInside(RequiredSpace, " ", this.getStartLineNumber());
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
        return success;
    }

    public enum Type {
        Protobuf("proto");

        String name;

        Type(String name) {
            this.name = name;
        }

        public static Type fromName(String name) {
            for (Type type : values()) {
                if (type.name.equals(name)) {
                    return type;
                }
            }
            return null;
        }
    }
}

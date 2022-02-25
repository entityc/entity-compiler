/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.protobuf;

import org.entityc.compiler.protobuf.Protobuf3Lexer;
import org.entityc.compiler.protobuf.Protobuf3Parser;
import org.entityc.compiler.util.ECLog;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PBLoaderExtractor {

    PBASTVisitor                 visitor;
    Protobuf3Parser.ProtoContext protoContext;

    public boolean load(String filepath) {
        visitor = new PBASTVisitor();
        if (filepath == null) {
            ECLog.logFatal("Trying to compile a null proto filepath!");
        }
        CharStream input = null;
        try {
            input = CharStreams.fromFileName(filepath);
        } catch (IOException e) {
            return false;
        }
        Protobuf3Lexer    lexer  = new Protobuf3Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Protobuf3Parser   parser = new Protobuf3Parser(tokens);

        protoContext = parser.proto();
        visitor.visit(protoContext);
        return true;
    }

    public List<String> getMessages() {
        return new ArrayList<>(visitor.getMessageFieldNumberMap().keySet());
    }

    public List<String> getFieldsForMessage(String message) {

        Map<String, PBASTVisitor.PBField> fieldMap = visitor.getMessageFieldNumberMap().get(message);
        if (fieldMap != null) {
            return new ArrayList<>(fieldMap.keySet());
        }
        return null;
    }

    public PBASTVisitor.PBField getField(String messageName, String entityFieldName) {
        Map<String, PBASTVisitor.PBField> fieldMap = visitor.getMessageFieldNumberMap().get(messageName);
        if (fieldMap != null) {
            return fieldMap.get(entityFieldName);
        }
        return null;
    }

    public boolean isValidMessage(String message) {
        return visitor.getMessageFieldNumberMap().containsKey(message);
    }

    public String getStringOptionValue(String optionName) {
        Object value = visitor.getExtractedOptions().get(optionName);
        if (value instanceof String) {
            return (String) value;
        }
        return null;
    }

    public List<String> matchingEnumNames(List<String> enumNames) {
        List<String> matchingEnumNames = new ArrayList<>();
        for (String enumName : enumNames) {
            if (visitor.getEnumContextMap().containsKey(enumName)) {
                matchingEnumNames.add(enumName);
            }
        }
        return matchingEnumNames;
    }

    public void extractEnumsAsProto(List<String> enumNames, PrintStream printStream) {

        // package
        if (protoContext.packageStatement() != null && protoContext.packageStatement().size() > 0) {
            Protobuf3Parser.PackageStatementContext ctx         = protoContext.packageStatement(0);
            String                                  packageText = ctx.fullIdent().getText();
            printStream.println("package " + packageText + ";");
            printStream.println();
        }

        // options
        if (protoContext.option() != null && protoContext.option().size() > 0) {
            for (Protobuf3Parser.OptionContext ctx : protoContext.option()) {
                printStream.println(ctx.OPTION().getText() + " " + ctx.optionName().getText() + "=" + ctx.constant().getText() + ctx.SEMI().getText());
            }
            printStream.println();
        }

        for (String enumName : enumNames) {
            Protobuf3Parser.EnumDefinitionContext ctx = visitor.getEnumContextMap().get(enumName);
            printStream.println("enum " + ctx.enumName().Ident().getText() + " {");
            for (Protobuf3Parser.EnumFieldContext fieldContext : ctx.enumBody().enumField()) {
                printStream.println("    " + fieldContext.Ident().getText() + " = " + fieldContext.IntLit().getText() + ";");
            }
            printStream.println("}");
            printStream.println();
        }
    }
}

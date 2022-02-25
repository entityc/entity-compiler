/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.language;

import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.expression.MTOperation;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.util.ECLog;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MTLanguage extends MTNode {

    private final String                          name;
    private final Map<String, String>             types           = new HashMap<>();
    private final Map<String, String>             nullableTypes   = new HashMap<>();
    private final Map<String, Boolean>            referenceMap    = new HashMap<>();
    private final Map<String, List<String>>       operatorSymbols = new HashMap<>();
    private final List<String>                    keywords        = new ArrayList<>();
    private final Map<String, MTLanguageFunction> functionMap     = new HashMap<>();
    private       String                          selfKeyword;
    private       String                          lineComment;
    private       String                          blockCommentStart;
    private       String                          blockCommentEnd;
    private       String                          fileExtension;
    private       String                          trueString      = "true";
    private       String                          falseString     = "false";
    private       String                          nullString      = "null";

    public MTLanguage(ParserRuleContext ctx, String name) {
        super(ctx);
        this.name = name;
    }

    public MTLanguageFunction getFunction(String name) {
        return functionMap.get(name);
    }

    public void addFunction(MTLanguageFunction function) {
        this.functionMap.put(function.getName(), function);
    }

    public String getTrueString() {
        return trueString;
    }

    public void setTrueString(String trueString) {
        this.trueString = trueString;
    }

    public String getFalseString() {
        return falseString;
    }

    public void setFalseString(String falseString) {
        this.falseString = falseString;
    }

    public String getNullString() {
        return nullString;
    }

    public void setNullString(String nullString) {
        this.nullString = nullString;
    }

    public String getLineComment() {
        return lineComment;
    }

    public void setLineComment(String lineComment) {
        this.lineComment = lineComment;
    }

    public String getBlockCommentStart() {
        return blockCommentStart;
    }

    public void setBlockCommentStart(String blockCommentStart) {
        this.blockCommentStart = blockCommentStart;
    }

    public String getBlockCommentEnd() {
        return blockCommentEnd;
    }

    public void setBlockCommentEnd(String blockCommentEnd) {
        this.blockCommentEnd = blockCommentEnd;
    }

    public void addType(String ecType, String languageType, boolean reference, boolean nullable) {
        if (nullable) {
            nullableTypes.put(ecType, languageType);
        } else {
            types.put(ecType, languageType);
        }
        referenceMap.put(ecType, reference);
    }

    public boolean isReferenceForType(String ecType) {
        return referenceMap.get(ecType);
    }

    public boolean hasNullableForType(String ecType) {
        return nullableTypes.containsKey(ecType);
    }

    public String getLanguageType(String ecType, boolean nullable) {
        String type = nullable ? nullableTypes.get(ecType) : types.get(ecType);
        if (type == null) {
            type = nullable ? types.get(ecType) : nullableTypes.get(ecType);
        }
        return type;
    }

    public String getSelfKeyword() {
        return selfKeyword;
    }

    public void setSelfKeyword(String selfKeyword) {
        this.selfKeyword = selfKeyword;
    }

    public String getName() {
        return name;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public boolean addOperator(String name, String symbol) {
        List<String> symbols = new ArrayList<>();
        symbols.add(symbol);
        return addOperator(name, symbols);
    }

    public boolean addOperator(String name, List<String> symbols) {
        try {
            if (MTOperation.Operator.valueOf(name.toUpperCase()) == null) {
                return false;
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
        operatorSymbols.put(name, symbols);
        return true;
    }

    public void addKeyword(String keyword) {
        keywords.add(keyword);
    }

    public boolean isKeyword(String identifier) {
        return keywords.contains(identifier);
    }

    public String getSymbolForOperator(String operator) {
        if (operatorSymbols.containsKey(operator.toLowerCase())) {
            return operatorSymbols.get(operator.toLowerCase()).get(0);
        }
        ECLog.logFatal("Cannot find symbol: " + operator);
        return null;
    }

    public List<String> getSymbolsForOperator(String operator) {
        return operatorSymbols.get(operator.toLowerCase());
    }

    @Override
    public void accept(MTVisitor visitor) {

    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.formatter;

import com.google.common.collect.Maps;

import java.util.Map;

public enum ConfigurableElement {
    ArrayElementDelim(false, false, true, true),
    ArrayPrefix(false, false, true, true),
    ArraySuffix(false, false, true, true),
    MapElementDelim(false, false, true, true),
    MapKeyValueDelim(false, false, true, true),
    MapPrefix(false, false, true, true),
    MapSuffix(false, false, true, true),
    CaseItemDelim(false, false, true, true),
    ConditionalCloseParen(false, false, true, true),
    ConditionalOpenParen(false, false, true, true),
    Description(true, true, false, false),
    DescriptionCategory(true, true, false, false),
    DescriptionCategoryDelim(false, false, true, true, true),
    DescriptionString(true, true, false, false),
    ExpressionCloseParen(false, false, true, true),
    ExpressionOpenParen(false, false, true, true),
    ExpressionOperand(false, false, true, true),
    ExpressionOperator(false, false, true, true),
    ExpressionOperatorDot(false, false, true, true),
    ExpressionOperatorEquals(false, false, true, true),
    ExpressionOperatorUnary(false, false, true, true),
    ExpressionSelectItemDelim(false, false, true, true),
    FilterName(false, false, true, true),
    FilterParamColon(false, false, true, true),
    FilterPipe(false, false, true, true),
    FunctionArgName(false, false, true, true),
    FunctionCallArgDelim(false, false, true, true),
    FunctionCallArgExpression(false, false, true, true),
    FunctionCallArgName(false, false, true, true),
    FunctionCallCloseParen(false, false, true, true),
    FunctionCallDelim(false, false, true, true),
    FunctionCallName(false, false, true, true),
    FunctionCallOpenParen(false, false, true, true),
    FunctionCallOutputDecl(false, false, true, true),
    FunctionCloseParen(false, false, true, true),
    FunctionDelim(false, false, true, true),
    FunctionName(false, false, true, true),
    FunctionOpenParen(false, false, true, true),
    FunctionOutputDecl(false, false, true, true),
    GlobalConstantName(false, false, false, true),
    GlobalConstantPrefix(false, false, true, false),
    InstructionArgument(true, true, false, false),
    InstructionArgumentDelim(false, false, true, true, true),
    InstructionBlockEndPrefix(false, false, false, true), // $[/
    InstructionBlockEndSuffix(false, false, true, false, true), // ]
    InstructionBlockStartPrefix(false, false, false, true), // $[
    InstructionBlockStartSuffix(false, false, true, false, true), // ]
    InstructionKeyword(true, true, false, false),
    InstructionName(false, true, true, true),
    InstructionNameBlockEnd(false, false, true, true),
    InstructionPrefix(false, false, false, true), // $[
    InstructionSuffix(false, false, true, false, true), // ]
    MethodCallCloseParen(false, false, true, true),
    MethodCallDelim(false, false, true, true),
    MethodCallName(false, false, true, true),
    MethodCallOpenParen(false, false, true, true),
    NamedElementDelim(false, false, true, true),
    NamedElementName(false, false, true, true),
    NamedElementType(false, false, true, true),
    None(false, false, false, false),
    NumberBoolean(false, false, true, true),
    NumberFloatingPoint(false, false, true, true),
    NumberInteger(false, false, true, true),
    QuotedString(false, false, true, true),
    RequiredSpace(false, false, false, false),
    VariablePrefix(false, false, false, true), // ${
    VariableSuffix(false, false, true, false) // }
    ,
    ;
    boolean             requiredSpaceBefore;
    boolean             requiredSpaceAfter;
    boolean             optionalSpaceBefore;
    boolean             optionalSpaceAfter;
    boolean             cancelPriorRequiredAfter;

    ConfigurableElement(boolean requiredSpaceBefore, boolean requiredSpaceAfter, boolean optionalSpaceBefore, boolean optionalSpaceAfter) {
        this.requiredSpaceBefore = requiredSpaceBefore;
        this.requiredSpaceAfter = requiredSpaceAfter;
        this.optionalSpaceBefore = optionalSpaceBefore;
        this.optionalSpaceAfter = optionalSpaceAfter;
        this.cancelPriorRequiredAfter = false;
    }

    ConfigurableElement(boolean requiredSpaceBefore, boolean requiredSpaceAfter, boolean optionalSpaceBefore, boolean optionalSpaceAfter, boolean cancelPriorRequiredAfter) {
        this.requiredSpaceBefore = requiredSpaceBefore;
        this.requiredSpaceAfter = requiredSpaceAfter;
        this.optionalSpaceBefore = optionalSpaceBefore;
        this.optionalSpaceAfter = optionalSpaceAfter;
        this.cancelPriorRequiredAfter = cancelPriorRequiredAfter;
    }

    private static final Map<String, ConfigurableElement> mapByName =
            Maps.newHashMapWithExpectedSize(ConfigurableElement.values().length);

    static {
        for (ConfigurableElement element : ConfigurableElement.values()) {
            mapByName.put(element.name(), element);
        }
    }
    public boolean isInstructionRelated() {
        return this.name().startsWith("Instruction");
    }

    public static ConfigurableElement findByName(String name) {
        return mapByName.get(name);
    }
}


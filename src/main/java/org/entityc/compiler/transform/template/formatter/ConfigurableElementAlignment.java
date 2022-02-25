/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.formatter;

import java.util.Arrays;

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.*;
import static org.entityc.compiler.transform.template.formatter.ConfigurableElementScope.*;

public enum ConfigurableElementAlignment {
    DescriptionToInstructionName(new ConfigurableElementScope[]{NonBlockInstructionScope, InstructionBlockStartScope},
                                 Description, InstructionName),
    InstructionSuffixToInstructionPrefix(NonBlockInstructionScope, InstructionSuffix, InstructionPrefix),
    InstructionBlockStartSuffixToInstructionBlockStartPrefix(InstructionBlockStartScope, InstructionBlockStartSuffix,
                                                             InstructionBlockStartPrefix),
    FunctionOpenParenToInstructionName(FunctionArgScope, FunctionOpenParen, InstructionName),
    FunctionArgNameToFunctionArgName(FunctionArgScope, FunctionArgName, FunctionArgName),
    DescriptionToFunctionArgName(FunctionArgScope, Description, FunctionArgName),
    FunctionCloseParenToFunctionOpenParen(FunctionArgScope, FunctionCloseParen, FunctionOpenParen),
    FunctionCallOpenParenToInstructionName(FunctionCallArgScope, FunctionCallOpenParen, InstructionName),
    FunctionCallArgNameToFunctionCallArgName(FunctionCallArgScope, FunctionCallArgName, FunctionCallArgName),
    FunctionCallCloseParenToFunctionCallOpenParen(FunctionCallArgScope, FunctionCallCloseParen, FunctionCallOpenParen),
    ;
    public ConfigurableElementScope[] scopes;
    public ConfigurableElement        fromElement;
    public ConfigurableElement        toElement;
    public boolean                    optional;

    ConfigurableElementAlignment(ConfigurableElementScope scope, ConfigurableElement fromElement, ConfigurableElement toElement) {
        this.scopes      = new ConfigurableElementScope[]{scope};
        this.fromElement = fromElement;
        this.toElement   = toElement;
    }

    ConfigurableElementAlignment(ConfigurableElementScope[] scopes, ConfigurableElement fromElement, ConfigurableElement toElement) {
        this.scopes      = scopes;
        this.fromElement = fromElement;
        this.toElement   = toElement;
    }

    public static boolean isSupported(ConfigurableElementScope scope, ConfigurableElement fromElement, ConfigurableElement toElement) {
        for (ConfigurableElementAlignment alignment : values()) {
            if (Arrays.stream(alignment.scopes).anyMatch(scope::equals) && alignment.fromElement == fromElement
                && alignment.toElement == toElement) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSupported(ConfigurableElement fromElement, ConfigurableElement toElement) {
        for (ConfigurableElementAlignment alignment : values()) {
            if (alignment.fromElement == fromElement && alignment.toElement == toElement) {
                return true;
            }
        }
        return false;
    }

    public static ConfigurableElementAlignment alignmentForFromElementToElement(ConfigurableElement fromElement, ConfigurableElement toElement) {
        for (ConfigurableElementAlignment alignment : values()) {
            if (alignment.fromElement == fromElement && alignment.toElement == toElement) {
                return alignment;
            }
        }
        return null;
    }
}

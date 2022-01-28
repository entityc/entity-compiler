/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.formatter;

public enum ConfigurableElementScope {
    TopScope(null, null),
    NonBlockInstructionScope(TopScope, ConfigurableElement.InstructionPrefix, ConfigurableElement.InstructionSuffix),
    InstructionBlockStartScope(TopScope, ConfigurableElement.InstructionBlockStartPrefix,
                               ConfigurableElement.InstructionBlockStartSuffix),
    FunctionArgScope(InstructionBlockStartScope, ConfigurableElement.FunctionOpenParen,
                     ConfigurableElement.FunctionCloseParen),
    FunctionCallArgScope(NonBlockInstructionScope, ConfigurableElement.FunctionCallOpenParen,
                     ConfigurableElement.FunctionCallCloseParen),
    ;
    public ConfigurableElement      scopeStartElement;
    public ConfigurableElement      scopeEndElement;
    public ConfigurableElementScope parentScope;

    ConfigurableElementScope(ConfigurableElement scopeStartElement, ConfigurableElement scopeEndElement) {
        this.scopeStartElement = scopeStartElement;
        this.scopeEndElement   = scopeEndElement;
    }

    ConfigurableElementScope(ConfigurableElementScope parentScope, ConfigurableElement scopeStartElement, ConfigurableElement scopeEndElement) {
        this.parentScope       = parentScope;
        this.scopeStartElement = scopeStartElement;
        this.scopeEndElement   = scopeEndElement;
    }

    static public ConfigurableElementScope FindWithStartElement(ConfigurableElementScope parentScope, ConfigurableElement scopeStartElement) {
        for (ConfigurableElementScope scope : values()) {
            if (scope.parentScope == parentScope && scope.scopeStartElement == scopeStartElement) {
                return scope;
            }
        }
        return null;
    }
}

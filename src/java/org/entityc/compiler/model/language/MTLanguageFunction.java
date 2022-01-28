/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.language;

import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

public class MTLanguageFunction extends MTNode {

    private final MTLanguage                       language;
    private final String                           name;
    private final List<MTLanguageFunctionArgument> args = new ArrayList<>();
    private final String                           languageMapping;

    public MTLanguageFunction(ParserRuleContext ctx, MTLanguage language, String name, String languageMapping) {
        super(ctx);
        this.language = language;
        this.name = name;
        this.languageMapping = languageMapping;
    }

    public MTLanguage getLanguage() {
        return language;
    }

    public String getName() {
        return name;
    }

    public List<MTLanguageFunctionArgument> getArgs() {
        return args;
    }

    public String getLanguageMapping() {
        return languageMapping;
    }

    public void addArg(MTLanguageFunctionArgument arg) {
        args.add(arg);
    }

    @Override
    public void accept(MTVisitor visitor) {

    }
}

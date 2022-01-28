/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.interop;

import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.entity.MTTemplateSupport;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class MTOperationConfigArgument extends MTNode implements MTTemplateSupport {

    private final ArgumentType type;
    private final String       name;
    private final String       defaultValue;
    private final boolean      context;

    public MTOperationConfigArgument(ParserRuleContext ctx, ArgumentType type, String name, String defaultValue, boolean context) {
        super(ctx);
        this.type = type;
        this.name = name;
        this.defaultValue = defaultValue;
        this.context = context;
    }

    public boolean isContext() {
        return context;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public ArgumentType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    public enum ArgumentType {
        Domain("domain"),
        View("view"),
        Attribute("attribute"),
        Entity("entity"),
        ;
        String name;

        ArgumentType(String name) {
            this.name = name;
        }

        public static ArgumentType FromName(String name) {
            for (ArgumentType type : values()) {
                if (type.name.equals(name)) {
                    return type;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.interop;

import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

/*
  The configuration of an operation pertains to an interface that has been defined outside the scope of any
  entity. As such, these act as parameters to a parameterized operation. When instantiated into an entity,
  these will be filled in by the entity (or set via context).
 */
public class MTOperationConfig extends MTNode {

    private final List<MTOperationConfigArgument> argumentList = new ArrayList<>();

    public MTOperationConfig(ParserRuleContext ctx) {
        super(ctx);
    }

    public void addArgument(MTOperationConfigArgument argument) {
        argumentList.add(argument);
    }

    public List<MTOperationConfigArgument> getArgumentList() {
        return argumentList;
    }

    public MTOperationConfigArgument getArgumentByName(String name) {
        for (MTOperationConfigArgument arg : argumentList) {
            if (arg.getName().equals(name)) {
                return arg;
            }
        }
        return null;
    }

    public MTOperationConfigArgument getArgumentByType(MTOperationConfigArgument.ArgumentType type) {
        for (MTOperationConfigArgument arg : argumentList) {
            if (arg.getType() == type) {
                return arg;
            }
        }
        return null;
    }

    public boolean isArgument(String name) {
        for (MTOperationConfigArgument argument : argumentList) {
            if (argument.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public int numberOfContextArguments() {
        int numContextArgs = 0;
        for (MTOperationConfigArgument argument : argumentList) {
            if (argument.isContext()) {
                numContextArgs++;
            }
        }
        return numContextArgs;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }
}

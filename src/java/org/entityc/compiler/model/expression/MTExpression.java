/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.expression;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.doc.annotation.ModelMethod;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.domain.MTDEntity;
import org.entityc.compiler.model.language.MTLanguage;

@ModelClass(type = ModelClassType.EXPRESSION,
    description =
        "An expression is a tree of operations, constants, methods and operands that evaluate to something at "
        + "runtime. An example of an expression is like a constraint placed on an attribute.")
public abstract class MTExpression extends MTNode {

    public MTExpression(ParserRuleContext ctx) {
        super(ctx);
    }

    public boolean resolveReferences(MTSpace space, int pass) {
        return false;
    }

    @ModelMethod(description = "Indicates whether this expression is a constant.")
    public boolean isConstant() {
        return this instanceof MTConstant;
    }

    @ModelMethod(description = "Indicates whether this expression is an operation.")
    public boolean isOperation() {
        return this instanceof MTOperation;
    }

    @ModelMethod(description = "Indicates whether this expression is a method call.")
    public boolean isMethodCall() {
        return this instanceof MTMethodCall;
    }

    @ModelMethod(description = "Indicates whether this expression is an operand (variable).")
    public boolean isOperand() {
        return this instanceof MTOperand;
    }

    public abstract String mapToLanguage(MTLanguage language, MTDEntity domainEntity, String objectName);
}

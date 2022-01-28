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
import org.entityc.compiler.doc.annotation.ModelMethodParameter;
import org.entityc.compiler.model.MTRoot;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.domain.MTDEntity;
import org.entityc.compiler.model.language.MTLanguage;
import org.entityc.compiler.model.language.MTLanguageFunction;
import org.entityc.compiler.model.language.MTLanguageFunctionArgument;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.transform.template.StringTemplateTransform;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ModelClass(type = ModelClassType.EXPRESSION,
    description =
        "A method call in a model expression must map to a language specific method. For instance, if we define "
        + "the following function in the Java language: `functions { length(string str) \"${str}.length()\" }` "
        + "then we can use `length(string str)` method in our expressions. Passing the object of this class through "
        + "the `language` filter will result in remapping it to use the `length()` method on the `str` string "
        + "object.")
public class MTMethodCall extends MTExpression {

    private final String             name;
    private final List<MTExpression> arguments = new ArrayList<>();

    public MTMethodCall(ParserRuleContext ctx, String name) {
        super(ctx);
        this.name = name;
    }

    @ModelMethod(description = "Returns all the expressions assigned to the arguments of the method.")
    public List<MTExpression> getArguments() {
        return arguments;
    }

    @ModelMethod(description = "Returns the number of arguments of the method call.")
    public int getArgumentCount() {
        return arguments.size();
    }

    @ModelMethod(description = "Returns the expression of a specified argument of the method call.")
    public MTExpression getArgument(
        @ModelMethodParameter(description = "The index of argument (where 0 is the first).")
            int i) {
        return arguments.get(i);
    }

    @ModelMethod(description = "Returns the name of the method call.")
    public String getName() {
        return name;
    }

    public void addArgument(MTExpression argExpression) {
        arguments.add(argExpression);
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        boolean needsResolve = false;
        for (MTExpression expression : arguments) {
            if (expression.resolveReferences(space, pass)) {
                needsResolve = true;
            }
        }
        return needsResolve;
    }

    @ModelMethod(description = "Returns the string representation of the method call (good for debug).")
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(name);
        sb.append("(");
        boolean first = true;
        for (MTExpression expression : arguments) {
            if (!first) {
                sb.append(",");
            }
            sb.append(expression.toString());
            first = false;
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public String mapToLanguage(MTLanguage language, MTDEntity domainEntity, String objectName) {
        if (language == null) {
            ECLog.logFatal(this, "Cannot map to a language because no language set.");
        }
        MTLanguageFunction function = language.getFunction(name);
        if (function == null) {
            ECLog.logFatal("Function " + name + "() not found in language " + language.getName());
        }
        if (function.getArgs().size() != arguments.size()) {
            ECLog.logFatal("Cannot map function in expression to a language function \"" + name
                           + "\", number of arguments differ.");
        }
        String finalMapping = function.getLanguageMapping();
        // replace argument in mapping with what is mapped to the argument
        // in the function

        Map<String, Object> argMap = new HashMap<>();
        for (int i = 0; i < function.getArgs().size(); i++) {
            MTLanguageFunctionArgument functionArgument = function.getArgs().get(i);
            MTExpression               methodArgument   = arguments.get(i);
            if (!methodArgument.isOperand() || ((MTOperand) methodArgument).getType() != MTOperand.Type.ATTRIBUTE) {
                ECLog.logFatal("Argument to language function must be an attribute, all others not supported.");
            }
            String attributeName = methodArgument.mapToLanguage(language, domainEntity, null);
            argMap.put(functionArgument.getName(), attributeName);
        }
        // the method call in the constraint has an argument named by an attribute.
        MTRoot  root  = new MTRoot(this.getParserRuleContext());
        MTSpace space = new MTSpace(this.getParserRuleContext(), "languageMap");
        root.setSpace(space);
        StringTemplateTransform stringTemplateTransform = new StringTemplateTransform(root,
                                                                                      function.getLanguageMapping());
        stringTemplateTransform.addReadOnlyVariables(argMap);
        stringTemplateTransform.start();
        finalMapping = stringTemplateTransform.getOutputStringValue();
        return finalMapping;
    }
}

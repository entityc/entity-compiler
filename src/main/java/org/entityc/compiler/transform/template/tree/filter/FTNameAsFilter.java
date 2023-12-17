/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.filter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.domain.MTNamingMethod;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.transform.template.tree.expression.FTOperand;
import org.entityc.compiler.util.ECLog;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FTNameAsFilter extends FTFilter {

    public FTNameAsFilter() {
        super(null, "nameas",
            "Changes the input string (considered a \"name\" because it is using camel case) to another string based on the specified method.");
        this.addFilterParam(new FTFilterParam("method",
            "Specifies the naming method: " + String.join(", ", Arrays.stream(MTNamingMethod.values()).map(MTNamingMethod::getName).toArray(String[]::new))));
        addSingleInputType(String.class, "The camel case name string to change.");
    }

    @Override
    public Object filter(ParserRuleContext ctx, FTTransformSession session, Object input, List<FTExpression> paramValues, Map<String, Object> options) {
        checkInput(ctx, input, paramValues, options);

        if (paramValues.size() == 0) {
            ECLog.logFatal(ctx, "Must specify a naming method. Valid values are: " + String.join(", ", Arrays.stream(MTNamingMethod.values()).map(MTNamingMethod::getName).toArray(String[]::new)));
        }

        if (input instanceof String) {
            String inputString = (String) input;
            FTExpression methodParam = paramValues.get(0);
            String methodName = null;
            if (methodParam.isOperand()) {
                methodName = ((FTOperand) methodParam).getName();
            }
            else
            {
                ECLog.logFatal(ctx, "Naming method must be specified directly. For example: entity.name|nameas:uppercase");
            }
            MTNamingMethod namingMethod = MTNamingMethod.fromName(methodName);
            if (namingMethod == null) {
                ECLog.logFatal(ctx, "Unknown naming method: " + methodName);
            }
            return namingMethod.rename(inputString);
        }

        return null;
    }
}

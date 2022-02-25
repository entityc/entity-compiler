/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.filter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;

import java.util.List;
import java.util.Map;

public class FTLowercaseFilter extends FTFilter {

    public FTLowercaseFilter() {
        super(null, "lowercase",
              "Forces all characters of the input to be lowercase.");
        addSingleInputType(String.class, "The string to make all lowercase.");
    }

    @Override
    public Object filter(ParserRuleContext ctx, FTTransformSession session, Object input, List<FTExpression> paramValues, Map<String, Object> options) {
        checkInput(ctx, input, paramValues, options);
        if (input instanceof String) {
            String inputString = (String) input;
            return inputString.toLowerCase();
        }
        return null;
    }
}

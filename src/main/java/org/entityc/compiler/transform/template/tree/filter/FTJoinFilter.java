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

public class FTJoinFilter extends FTFilter {

    public FTJoinFilter() {
        super(null, "join",
              "Given a multiline string, it joins all lines into one (replacing all carriage returns with a space).");
        addSingleInputType(String.class, "The base string to start joining onto.");
    }

    @Override
    public Object filter(ParserRuleContext ctx, FTTransformSession session, Object input, List<FTExpression> paramValues, Map<String, Object> options) {
        checkInput(ctx, input, paramValues, options);
        if (input instanceof String) {
            String inputString = (String) input;
            return inputString.replaceAll("\n", " ");
        }
        return null;
    }
}

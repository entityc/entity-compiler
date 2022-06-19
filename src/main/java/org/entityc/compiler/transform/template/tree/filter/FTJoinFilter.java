/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.filter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.foundation.MFArray;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;

import java.util.List;
import java.util.Map;

public class FTJoinFilter extends FTFilter {

    private final FTFilterOption delimiterOption = new FTFilterOption("delim", "The delimiter to place between the joined strings. (default: a space)");

    public FTJoinFilter() {
        super(null, "join",
              "Given a multiline string, it joins all lines into one (replacing all carriage returns with a space). Given an array of strings, it joins the strings by inserted the provided delimiter between them.");
        addSingleInputType(String.class, "The base string to start joining onto.");
        addSingleInputType(MFArray.class, "The array of strings to join.");
        addFilterOption(delimiterOption);
    }

    @Override
    public Object filter(ParserRuleContext ctx, FTTransformSession session, Object input, List<FTExpression> paramValues, Map<String, Object> options) {
        checkInput(ctx, input, paramValues, options);
        String delim = " ";
        if (hasOptionValue(options, delimiterOption)) {
            delim = getOptionStringValue(options, delimiterOption);
        }
        if (input instanceof String) {
            String inputString = (String) input;
            return inputString.replaceAll("\n", delim);
        } else if (input instanceof MFArray) {
            MFArray array = (MFArray) input;
            StringBuilder sb = new StringBuilder();
            for (long i = 0; i< array.getCount(); i++) {
                String str = array.get(i).toString();
                if (i>0) {
                    sb.append(delim);
                }
                sb.append(str);
            }
            return sb.toString();
        }
        return null;
    }
}

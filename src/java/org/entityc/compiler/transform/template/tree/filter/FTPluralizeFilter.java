/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.filter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.util.ECStringUtil;

import java.util.List;
import java.util.Map;

public class FTPluralizeFilter extends FTFilter {

    public FTPluralizeFilter() {
        super(null, "plural",
              "This will attempt to pluralize the last word of the input string. If it can't determine "
              + "the pluralization it may just return the same string.");
        addSingleInputType(String.class, "The string to pluralize.");
    }

    @Override
    public Object filter(ParserRuleContext ctx, FTTransformSession session, Object input, List<FTExpression> paramValues, Map<String, Object> options) {
        checkInput(ctx, input, paramValues, options);
        if (input instanceof String) {
            String inputString = (String) input;
            return ECStringUtil.Pluralize(inputString);
        }
        return null;
    }
}

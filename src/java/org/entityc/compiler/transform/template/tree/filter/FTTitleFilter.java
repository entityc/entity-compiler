/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.filter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.domain.MTNamed;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.util.ECStringUtil;

import java.util.List;
import java.util.Map;

public class FTTitleFilter extends FTFilter {

    public FTTitleFilter() {
        super(null, "title",
              "Given a string of words in camel case format, this will capitalize each word in the "
              + "string except those words that are typically not capitalized in a title (such as 'of', 'the'. "
              + "'and', etc.).");
        addSingleInputType(String.class,
                           "The string representation of some type of identifier or name that "
                           + "us using camel case to combine words without spaces.");
        addSingleInputType(MTNamed.class, "Any class that has a `name` property or `getName()` method.");
    }

    @Override
    public Object filter(ParserRuleContext ctx, FTTransformSession session, Object input, List<FTExpression> paramValues, Map<String, Object> options) {
        checkInput(ctx, input, paramValues, options);
        if (input instanceof MTNamed) {
            input = ((MTNamed) input).getName();
        }
        if (input instanceof String) {
            String inputString = (String) input;
            return ECStringUtil.CamelToTitle(inputString);
        }
        return null;
    }
}

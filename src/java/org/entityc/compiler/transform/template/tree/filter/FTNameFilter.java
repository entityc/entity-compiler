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

import java.util.List;
import java.util.Map;

public class FTNameFilter extends FTFilter {

    public FTNameFilter() {
        super(null, "name",
              "This simply calls `getName()` on the input. It is more of a convenient way to get the name by using a filter.");
        addSingleInputType(MTNamed.class, "Any class that has a `name` property or `getName()` method.");
    }

    @Override
    public Object filter(ParserRuleContext ctx, FTTransformSession session, Object input, List<FTExpression> paramValues, Map<String, Object> options) {
        checkInput(ctx, input, paramValues, options);
        if (input instanceof MTNamed) {
            return ((MTNamed) input).getName();
        }
        return null;
    }
}

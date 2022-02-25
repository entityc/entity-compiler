/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.filter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.MTNamespace;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;

import java.io.File;
import java.util.List;
import java.util.Map;

public class FTPathFilter extends FTFilter {

    public FTPathFilter() {
        super(null, "path",
              "This will convert a string or namespace object into a string where by a '/' character is "
              + "used as a delimiter instead of a '.'. This is useful when you need to convert a namespace "
              + "into a filepath.");
        addSingleInputType(String.class,
                           "The string that represents a period-delimited namespace (for instance: com.example.something).");
        addSingleInputType(MTNamespace.class, "A namespace object that is obtained from an object in your model.");
    }

    @Override
    public Object filter(ParserRuleContext ctx, FTTransformSession session, Object input, List<FTExpression> paramValues, Map<String, Object> options) {
        checkInput(ctx, input, paramValues, options);
        String inputString = null;
        if (input instanceof String) {
            inputString = (String) input;
        }
        else if (input instanceof MTNamespace) {
            MTNamespace namespace = (MTNamespace) input;
            inputString = namespace.toString();
        }
        if (inputString != null) {
            return inputString.replaceAll("\\.", File.separator);
        }
        return null;
    }
}

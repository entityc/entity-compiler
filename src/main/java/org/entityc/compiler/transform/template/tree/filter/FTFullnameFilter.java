/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.filter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.domain.MTDomainBased;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.expression.FTConstant;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.util.ECLog;

import java.util.List;
import java.util.Map;

public class FTFullnameFilter extends FTFilter {

    FTFilterParam delimiterParam = new FTFilterParam("delimiter", "Specifies a delimiter to use instead of a period.");

    public FTFullnameFilter() {
        super(null, "fullname",
              "When the input is an domain-based entity, it will combine the namespace of the domain with the "
              + "name of the entity in that domain to form a period based full name (e.g., `com.example.model.Widget`). "
              + "This can be useful for construction a Java import statement, for instance.");
        addSingleInputType(MTDomainBased.class,
                           "Any class that is with respect to a domain (such as MTDEntity).");
        addFilterParam(delimiterParam);
    }

    @Override
    public Object filter(ParserRuleContext ctx, FTTransformSession session, Object input, List<FTExpression> paramValues, Map<String, Object> options) {
        checkInput(ctx, input, paramValues, options);
        String delim = ".";
        if (paramValues.size() > 0) {
            Object delimParam = paramValues.get(0);
            if (delimParam instanceof FTConstant) {
                delim = ((FTConstant) delimParam).getStringValue();
            }
            else {
                ECLog.logFatal("Parameter to " + getName() + " filter must be a string constant.");
            }
        }
        if (input instanceof MTDomainBased) {
            return ((MTDomainBased) input).getFullname(delim);
        }
        return null;
    }
}

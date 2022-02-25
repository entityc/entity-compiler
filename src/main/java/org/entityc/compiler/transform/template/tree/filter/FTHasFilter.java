/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.filter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.domain.MTDEntity;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.util.ECLog;

import java.util.List;
import java.util.Map;

// lets not include this right now - there should be other ways to do this - @TemplateFilter(D = "")
public class FTHasFilter extends FTFilter {

    private final FTFilterOption attributeOption = new FTFilterOption("attribute",
                                                                      "If specified, the filter checks if the domain entity has the specified attribute by name.");
    private final FTFilterOption viewOption      = new FTFilterOption("view",
                                                                      "If specified, the filter checks if the domain entity has the specified view by name.");

    public FTHasFilter() {
        super(null, "has", "deprecated");
        addSingleInputType(MTDEntity.class);
        addFilterOption(attributeOption);
        addFilterOption(viewOption);
    }

    @Override
    public Object filter(ParserRuleContext ctx, FTTransformSession session, Object input, List<FTExpression> paramValues, Map<String, Object> options) {
        checkInput(ctx, input, paramValues, options);

        if (input == null) {
            return false;
        }

        if (input instanceof MTDEntity) {
            MTDEntity domainEntity  = (MTDEntity) input;
            String    attributeName = getOptionStringValue(options, attributeOption);
            if (attributeName != null) {
                return domainEntity.getDomainAttributeByName(attributeName, true) != null;
            }
            String viewName = getOptionStringValue(options, viewOption);
            if (viewName != null) {
                boolean hasView = domainEntity.getDomainEntityViewByName(viewName, true) != null;
                return hasView;
            }
        }
        ECLog.logFatal(ctx, "Unsupported input of has filter: " + input.getClass().getSimpleName());
        return false;
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.filter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class FTTypeFilter extends FTFilter {

    private final FTFilterOption singleOption = new FTFilterOption("single",
                                                                   "This will filter the attribute list such that it only contains non-array attributes.");

    public FTTypeFilter() {
        super(null, "type",
              "Filters attribute lists optionally by its type (only array/single type supported currently).");
        addCollectionInputType(MTAttribute.class);
        addFilterOption(singleOption);
    }

    @Override
    public Object filter(ParserRuleContext ctx, FTTransformSession session, Object input, List<FTExpression> paramValues, Map<String, Object> options) {
        checkInput(ctx, input, paramValues, options);

        Collection<MTAttribute> attributes = (Collection<MTAttribute>) input;
        boolean                 onlySingle = getOptionBooleanValue(options, singleOption);

        List<MTAttribute> filteredList = new ArrayList<>();
        for (MTAttribute attribute : attributes) {
            if (onlySingle && attribute.isArray()) {
                continue;
            }
            filteredList.add(attribute);
        }
        return filteredList;
    }
}

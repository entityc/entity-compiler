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
import org.entityc.compiler.transform.template.tree.expression.FTOperand;
import org.entityc.compiler.util.ECLog;
import org.entityc.compiler.util.ECStringUtil;

import java.util.List;
import java.util.Map;

// lets not include this right now - there should be other ways to do this - @TemplateFilter(D = "")
public class FTIsFilter extends FTFilter {

    private final FTFilterParam kindOfThingOption = new FTFilterParam("thingOfThing",
                                                                      "The kind of thing you want to check for such as identifier.");

    public FTIsFilter() {
        super(null, "is", "Checks to see if the input is a particular kind of thing. For now "
                          + "only `identifier` is supported which checks to make sure it is a string that starts "
                          + "with a letter and contains no special characters or spaces..");
        addSingleInputType(String.class);
        addFilterParam(kindOfThingOption);
    }

    private enum KindOfThing {
        IDENTIFIER("identifier"),
        NAMESPACE("namespace"),
        CAPITALIZED("capitalized"),
        UNCAPITALIZED("uncapitalized"),
        ;
        String name;

        KindOfThing(String name) {
            this.name = name;
        }

        static boolean isValidKind(String name) {
            for (KindOfThing item : values()) {
                if (item.name.equals(name)) {
                    return true;
                }
            }
            return false;
        }

        static KindOfThing getByName(String name) {
            for (KindOfThing item : values()) {
                if (item.name.equals(name)) {
                    return item;
                }
            }
            return null;
        }
    }

    @Override
    public Object filter(ParserRuleContext ctx, FTTransformSession session, Object input, List<FTExpression> paramValues, Map<String, Object> options) {
        checkInput(ctx, input, paramValues, options);

        if (input == null) {
            return false;
        }
        if (paramValues.size() == 0) {
            ECLog.logFatal(ctx, "Must specify a kind of thing for this filter.");
        }

        FTExpression kindOfThingExpression = paramValues.get(0);
        if (!kindOfThingExpression.isOperand()) {
            ECLog.logFatal(ctx, "Expecting the kind of thing you want to check for.");
        }
        String kindOfThingValue = ((FTOperand) kindOfThingExpression).getName();
        if (!KindOfThing.isValidKind(kindOfThingValue)) {
            ECLog.logFatal(ctx, "Unknown kind of thing: " + kindOfThingValue);
        }
        if (input instanceof String) {
            String stringValue = (String) input;
            switch (KindOfThing.getByName(kindOfThingValue)) {

                case IDENTIFIER:
                    return ECStringUtil.IsIdentifier(stringValue);
                case NAMESPACE:
                    return ECStringUtil.IsNamespace(stringValue);
                case CAPITALIZED:
                    return ECStringUtil.IsCapitalized(stringValue);
                case UNCAPITALIZED:
                    return ECStringUtil.IsUncapitalized(stringValue);
            }
        } else {
            ECLog.logFatal(ctx, "Filter " + getName() + ":" + kindOfThingValue
                                + " requires a string input value.");
        }
        ECLog.logFatal(ctx, "Unsupported input of has filter: " + input.getClass().getSimpleName());
        return false;
    }
}

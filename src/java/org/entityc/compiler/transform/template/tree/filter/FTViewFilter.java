/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.filter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.domain.MTDEntity;
import org.entityc.compiler.model.domain.MTDView;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.transform.template.tree.expression.FTOperand;
import org.entityc.compiler.transform.template.tree.expression.FTOperation;
import org.entityc.compiler.util.ECLog;

import java.util.List;
import java.util.Map;

public class FTViewFilter extends FTFilter {

    public FTViewFilter() {
        super(null, "view",
              "Given a domain entity and the name of a view, this will return the view object.");
        this.addSingleInputType(MTDEntity.class);
        this.addFilterParam(new FTFilterParam("viewName", "Specifies a specific view name to get from the entity."));
    }

    @Override
    public Object filter(ParserRuleContext ctx, FTTransformSession session, Object input, List<FTExpression> paramValues, Map<String, Object> options) {
        checkInput(ctx, input, paramValues, options);

        MTDEntity entity = (MTDEntity) input;
        if (entity == null) {
            ECLog.logFatal(ctx, "The view filter cannot accept a null input.");
        }
        MTDView view = null;
        if (paramValues.size() == 0) {
            ECLog.logFatal(ctx, "View name must be specified with view filter.");
        }
        FTExpression viewParamExpression = paramValues.get(0);
        if (viewParamExpression.isOperand()) {
            String viewName = ((FTOperand) viewParamExpression).getName();
            if (Character.isUpperCase(viewName.charAt(0))) {
                view = entity.getDomainEntityViewByName(viewName, true);
                if (view == null) {
                    ECLog.logFatal(ctx,
                                   "Unable fo find view \"" + viewName + "\" in entity \"" + entity.getName() + "\".");
                }
            }
            else {
                Object value = session.getValue(viewName);
                if (value instanceof MTDView) {
                    view = (MTDView) value;
                }
                if (view == null) {
                    ECLog.logFatal(ctx, "The variable \"" + viewName + "\" is not a view object.");
                }
            }
        }
        else {
            if (viewParamExpression.isOperation()) {
                FTOperation operation = (FTOperation) viewParamExpression;
                if (operation.getOperandCount() == 2) {
                    Object value = viewParamExpression.getValue(session);
                    if (value == null) {
                        ECLog.logFatal(ctx, "No value for view expression: " + viewParamExpression.getText() + ".");
                    }
                    else if (value instanceof MTDView) {
                        view = (MTDView) value;
                    }
                    else {
                        ECLog.logFatal(ctx, "Unsupported parameter expression for view filter: "
                                            + viewParamExpression.getText() + " does not evaluate to a view.");
                    }
                }
            }
            if (view == null) {
                ECLog.logFatal(ctx,
                               "Unsupported parameter expression for view filter: " + viewParamExpression.getText());
            }
        }

        return view;
    }
}

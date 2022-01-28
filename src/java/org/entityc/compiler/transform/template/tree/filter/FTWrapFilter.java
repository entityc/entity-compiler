/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.filter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.expression.FTConstant;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.util.ECLog;
import org.entityc.compiler.util.ECStringUtil;

import java.util.List;
import java.util.Map;

public class FTWrapFilter extends FTFilter {

    private final FTFilterOption lineWidthOption = new FTFilterOption("lineWidth",
                                                                      "Specifies the number of characters per line before wrapping. Default is 80.");

    public FTWrapFilter() {
        super(null, "wrap",
              "Given a sentence or paragraph string and an optional line width, this will perform a word wrap "
              + "of the string to the output. You can optionally provide a line prefix (such as a line "
              + "comment designator). The column position of the first line is used as the starting column "
              + "position of each wrapped line of text.");
        addSingleInputType(String.class, "The string you want to wrap.");
        this.addFilterParam(
            new FTFilterParam("prefix", "The line prefix to include for each line. The default is '//'."));
        this.addFilterOption(lineWidthOption);
    }

    @Override
    public Object filter(ParserRuleContext ctx, FTTransformSession session, Object input, List<FTExpression> paramValues, Map<String, Object> options) {
        checkInput(ctx, input, paramValues, options);
        String prefix = "//";
        if (!paramValues.isEmpty()) {
            FTExpression paramExpression = paramValues.get(0);
            if (paramExpression.isConstant()) {
                prefix = ((FTConstant) paramExpression).getStringValue();
            }
        }
        if (input instanceof String) {
            ParserRuleContext markerStartContext = ctx;
            do {
                markerStartContext = markerStartContext.getParent();
                if (markerStartContext.getText().startsWith("$[")) {
                    markerStartContext = null;
                    break;
                }
            } while (!markerStartContext.getText().startsWith("${"));
            if (markerStartContext == null) {
                ECLog.logFatal(ctx, "The wrap filter can only be used inside ${ ... } markers.");
            }
            int paragraphStartPosition = markerStartContext.start.getCharPositionInLine();
            int lineWidth              = 80;
            if (options.containsKey(lineWidthOption.getName())) {
                Object lineWidthValue = options.get(lineWidthOption.getName());
                if (lineWidthValue instanceof Number) {
                    lineWidth = ((Number) lineWidthValue).intValue();
                }
                else if (lineWidthValue instanceof String) {
                    lineWidth = Integer.parseInt((String) lineWidthValue);
                }
                else if (lineWidthValue == null) {
                    ECLog.logFatal("Option \"" + lineWidthOption.getName() + "\" of filter \"" + getName()
                                   + "\" must have a value assigned to it.");
                }
                else {
                    ECLog.logFatal("Filter \"" + getName() + "\" does not support the specified value ("
                                   + lineWidthValue.getClass().getSimpleName() + ") for lineWidth.");
                }
            }
            StringBuilder prefixBuilder = new StringBuilder(prefix);
            for (int i = prefix.length(); i < paragraphStartPosition; i++) {
                prefixBuilder.append(' ');
            }

            String inputString = ECStringUtil.WrapString((String) input, prefixBuilder.toString(), lineWidth);

            return inputString;
        }
        return null;
    }
}

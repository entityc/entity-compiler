/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.filter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.transform.template.tree.FTNode;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.FTVisitor;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class FTFilter extends FTNode {

    private final String                      name;
    private final String                      description;
    private final List<FTFilterParam>         validParameters      = new ArrayList<>();
    private final Map<String, FTFilterOption> validOptions         = new HashMap<>();
    private final List<FTFilterInputType>     singleInputTypes     = new ArrayList<>();
    private final List<FTFilterInputType>     collectionInputTypes = new ArrayList<>();

    public FTFilter(ParserRuleContext ctx, String name, String description) {
        super(ctx);
        this.name        = name;
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public Collection<FTFilterOption> getValidOptions() {
        return validOptions.values();
    }

    public List<FTFilterParam> getValidParameters() {
        return validParameters;
    }

    public boolean hasValidParameter() {
        return validParameters.size() > 0;
    }

    public boolean hasValidOption() {
        return validOptions.size() > 0;
    }

    public List<FTFilterInputType> getSingleInputTypes() {
        return singleInputTypes;
    }

    public List<FTFilterInputType> getCollectionInputTypes() {
        return collectionInputTypes;
    }

    protected void addFilterParam(FTFilterParam param) {
        this.validParameters.add(param);
    }

    protected void addFilterOption(FTFilterOption option) {
        this.validOptions.put(option.getName(), option);
    }

    protected void addSingleInputType(Class typeClass) {
        this.singleInputTypes.add(new FTFilterInputType(typeClass, false));
    }

    protected void addSingleInputType(Class typeClass, String description) {
        this.singleInputTypes.add(new FTFilterInputType(typeClass, false, description));
    }

    protected void addCollectionInputType(Class typeClass) {
        this.collectionInputTypes.add(new FTFilterInputType(typeClass, true));
    }

    protected void addCollectionInputType(Class typeClass, String description) {
        this.collectionInputTypes.add(new FTFilterInputType(typeClass, true, description));
    }

    public abstract Object filter(ParserRuleContext ctx, FTTransformSession session, Object input, List<FTExpression> paramExpressions, Map<String, Object> options);

    protected void checkInput(ParserRuleContext ctx, Object input, List<FTExpression> paramValues, Map<String, Object> options) {

        boolean valid = true;

        if (input == null) {
            return;
        }
        if (paramValues.size() > validParameters.size()) {
            ECLog.logError(ctx, "Incorrect number of parameters specified for filter: " + getName());
            for (FTExpression strVal : paramValues) {
                ECLog.logError("param: " + strVal.getText());
            }
            valid = false;
        }

        for (String optionName : options.keySet()) {
            if (!validOptions.containsKey(optionName)) {
                ECLog.logError(ctx, "Invalid option \"" + optionName + "\" for filter: " + getName());
                valid = false;
            }
        }
        if (!valid) {
            System.exit(1);
        }
        // check input against its supported types.
        if (input instanceof Collection) {
            if (((Collection) input).size() > 0) {
                Object item = ((Collection) input).iterator().next();
                for (FTFilterInputType validInputType : collectionInputTypes) {
                    if (validInputType.getTypeClass().equals(item.getClass())) {
                        return;
                    }
                }
                ECLog.logFatal(ctx, "Collection input type " + item.getClass().getSimpleName()
                                    + " is not supported for filter: " + this.name);
            }
        }
        else {
            for (FTFilterInputType validInputType : singleInputTypes) {
                if (validInputType.getTypeClass().isInstance(input)) {
                    return;
                }
            }
            ECLog.logFatal(ctx, "Input type " + input.getClass().getSimpleName() + " is not supported for filter: "
                                + this.name);
        }
    }

    public String getName() {
        return name;
    }

    public Boolean getOptionBooleanValue(Map<String, Object> options, FTFilterOption option) {
        Object value = getOptionValue(options, option);
        if (value == null || !(value instanceof Boolean)) {
            return false;
        }
        else {
            return (Boolean) value;
        }
    }

    private Object getOptionValue(Map<String, Object> options, FTFilterOption option) {
        return options.get(option.getName());
    }

    public String getOptionStringValue(Map<String, Object> options, FTFilterOption option) {
        Object value = getOptionValue(options, option);
        if (value == null || !(value instanceof String)) {
            return null;
        }
        else {
            return (String) value;
        }
    }

    public boolean hasOptionValue(Map<String, Object> options, FTFilterOption option) {
        return options.containsKey(option.getName());
    }

    @Override
    public void accept(FTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public int getTemplateLexerSymbol() {
        return NO_SYMBOL;
    }
}

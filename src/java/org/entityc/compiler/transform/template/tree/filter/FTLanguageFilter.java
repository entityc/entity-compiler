/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.filter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.domain.MTDEAttributeConstraintExpression;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTNativeType;
import org.entityc.compiler.model.entity.MTType;
import org.entityc.compiler.model.entity.MTTypedef;
import org.entityc.compiler.model.expression.MTExpression;
import org.entityc.compiler.model.language.MTLanguage;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.util.ECLog;

import java.util.List;
import java.util.Map;

public class FTLanguageFilter extends FTFilter {

    private final FTFilterOption isReferenceOption = new FTFilterOption("isReference",
                                                                        "When this option is specified, it returns a boolean as to if it is a reference type object (`true`) or not (`false`).");
    private final FTFilterOption nullableOption    = new FTFilterOption("nullable",
                                                                        "Some language have both native and object versions of their types. For instance Java has `int` and `Integer`. The biggest difference is that `Integer` is an object and thus can be null whereas `int` cannot be null. Using this option specifies you want the version of this type that can be null.");
    private final FTFilterOption hasNullableOption = new FTFilterOption("hasNullable",
                                                                        "Use this option to find out if the input type has a nullable version in the template's language.");
    private final FTFilterOption selfOption        = new FTFilterOption("self",
                                                                        "Uses the `self` keyword in this language (for Java this would be `this`) as the object from which to reference the input.");
    private final FTFilterOption objectOption      = new FTFilterOption("object",
                                                                        "Allows you to directly specify the object name from which the input is referenced.");

    public FTLanguageFilter() {
        super(null, "language",
              "Using this filter allows the template to remain agnostic to the syntax of the language for which "
              + "they are generating code. For instance, if you feed in a type object, it will use the "
              + "language assigned to the template and look up the keyword for the type in that language. "
              + "For instance, if the type is `string` and the language is `java`, it would output `String`.");
        addSingleInputType(MTType.class);
        addSingleInputType(MTNativeType.class);
        addSingleInputType(MTEnum.class);
        addSingleInputType(MTTypedef.class);
        addSingleInputType(MTDEAttributeConstraintExpression.class);
        addSingleInputType(MTExpression.class);
        addFilterOption(isReferenceOption);
        addFilterOption(nullableOption);
        addFilterOption(hasNullableOption);
        addFilterOption(selfOption);
        addFilterOption(objectOption);
    }

    @Override
    public Object filter(ParserRuleContext ctx, FTTransformSession session, Object input, List<FTExpression> paramValues, Map<String, Object> options) {
        checkInput(ctx, input, paramValues, options);
        MTLanguage language = session.getSpace().getLanguageWithName(session.getTemplate().getLanguage());

//        if (language == null) {
//            ECLog.logError("TEMPLATE: " + session.getTemplate().getName() + " has NO LANGUAGE!");
//        } else {
//            ECLog.logInfo("LANGUAGE FILTER: " + language.getName());
//        }

        boolean isReferenceOptionActive = getOptionBooleanValue(options, isReferenceOption);
        boolean nullableOptionActive    = getOptionBooleanValue(options, nullableOption);
        boolean hasNullableOptionActive = getOptionBooleanValue(options, hasNullableOption);
        boolean selfOptionActive        = getOptionBooleanValue(options, selfOption);
        String  objectName              = getOptionStringValue(options, objectOption);
        if (selfOptionActive) {
            objectName = language.getSelfKeyword();
        }

        if (input instanceof MTDEAttributeConstraintExpression) {
            MTDEAttributeConstraintExpression constraintExpression = (MTDEAttributeConstraintExpression) input;
            return ((MTDEAttributeConstraintExpression) input).mapToLanguage(language,
                                                                             constraintExpression.getDomainAttribute().getDomainEntity(),
                                                                             objectName);
        }
        else if (input instanceof MTExpression) {
            return ((MTExpression) input).mapToLanguage(language, null, objectName);
        }
        MTType type = (MTType) input;

        if (type == null) {
            ECLog.logFatal(ctx, "Can't language filter a null type.");
        }
        if (type instanceof MTEntity) {
            if (hasNullableOptionActive) {
                return true;
            }
            else {
                return ((MTEntity) type).getName();
            }
        }
        if (language != null) {
            if (type.isEnumType()) {
                if (isReferenceOptionActive) {
                    return language.isReferenceForType("enum");
                }
                else if (hasNullableOptionActive) {
                    return language.hasNullableForType("enum");
                }
                else {
                    return ((MTEnum) type).getName();
                }
            }
            else if (type.isTypedef()) {
                int bitWidth = ((MTTypedef) type).getBitWidth();
                String nativeTypeName = bitWidth <= 32 ?
                                        MTNativeType.DataType.INT32.getName() :
                                        MTNativeType.DataType.INT64.getName();
                if (isReferenceOptionActive) {
                    return language.isReferenceForType("typedef");
                }
                else if (hasNullableOptionActive) {
                    return false;
                }
                else {
                    return language.getLanguageType(nativeTypeName, nullableOptionActive);
                }
            }
            else if (type.isNativeType()) {
                if (isReferenceOptionActive) {
                    return language.isReferenceForType(((MTNativeType) type).getDataType().getName());
                }
                else if (hasNullableOptionActive) {
                    return language.hasNullableForType(((MTNativeType) type).getDataType().getName());
                }
                else {
                    String inputTypeName  = ((MTNativeType) type).getDataType().getName();
                    String outputTypeName = language.getLanguageType(inputTypeName, nullableOptionActive);
                    if (outputTypeName == null || outputTypeName.length() == 0) {
                        ECLog.logFatal(ctx, "Unable to get typename for type: " + inputTypeName);
                    }
                    return outputTypeName;
                }
            }
        }
        else {
            if (type.isNativeType()) {
                if (isReferenceOptionActive) {
                    return false;
                }
                else if (hasNullableOptionActive) {
                    return false;
                }
                else {
                    return ((MTNativeType) type).getDataType().getName();
                }
            }
        }
        return input;
    }
}

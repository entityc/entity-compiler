/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.filter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.domain.MTDEAttribute;
import org.entityc.compiler.model.domain.MTDERelationship;
import org.entityc.compiler.model.domain.MTDEntity;
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTNativeType;
import org.entityc.compiler.model.entity.MTType;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.util.ECLog;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FTJsonFilter extends FTFilter {

    private final FTFilterOption escapedOption = new FTFilterOption("escaped",
                                                                    "If specified, any double quotes will be converted to escaped double quotes. ");

    public FTJsonFilter() {
        super(null, "json",
              "This is used on an entity object that you want to convert into a JSON string. For attribute "
              + "values it will randomly generate based on their type. This is currently only used when "
              + "generating Postman configuration files to provide a sample POST body.");
        addSingleInputType(MTDEntity.class);
        addCollectionInputType(MTDEAttribute.class);
        addFilterOption(escapedOption);
    }

    @Override
    public Object filter(ParserRuleContext ctx, FTTransformSession session, Object input, List<FTExpression> paramValues, Map<String, Object> options) {
        checkInput(ctx, input, paramValues, options);

        //ECLog.logInfo("Starting JSON Filter...");
        boolean escaped = getOptionBooleanValue(options, escapedOption);

        Collection<MTDEAttribute>    domainAttributes    = null;
        Collection<MTDERelationship> domainRelationships = null;

        if (input instanceof MTDEntity) {
            domainAttributes    = ((MTDEntity) input).getAttributes();
            domainRelationships = ((MTDEntity) input).getRelationships();
        }
        else {
            domainAttributes = (Collection<MTDEAttribute>) input;
        }

        JsonObjectBuilder builder         = Json.createObjectBuilder();
        Random            randomGenerator = new Random(7);
//        boolean isView = domainAttributes.iterator().next().getAttribute().getEntity() instanceof MTView;
        for (MTDEAttribute domainAttribute : domainAttributes) {
            if (domainAttribute.getAttribute() == null) {
                ECLog.logWarning(
                    "Found null attribute on domain attribute of name: " + domainAttribute.getAttributeName());
                domainAttribute.resolveReferences(domainAttribute.getDomain().getSpace(), 0);
                if (domainAttribute.getAttribute() == null) {
                    ECLog.logError(ctx,
                                   "No attribute for domain attribute: " + domainAttribute.getDomainEntity().getName()
                                   + "." + domainAttribute.getName());
                    continue;
                }
            }
            MTAttribute attribute = domainAttribute.getAttribute();
            if (attribute.isCreation() || attribute.isModification()) {
                continue; // skip over something controlled by the server
            }
            MTType attributeType = attribute.getType();
            if (attributeType.isEnumType()) {
                MTEnum mtEnum   = (MTEnum) attributeType;
                int    numItems = mtEnum.getItems().size();
                if (numItems == 0) {
                    ECLog.logError(ctx, "Enum \"" + mtEnum.getName() + "\" has no items.");
                    continue;
                }
                if (domainAttribute.getAttribute().isArray()) {
                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                    for (int i = 0; i < randomGenerator.nextInt(numItems); i++) {
                        String itemName = domainAttribute.getDomain().getNameOfNode(mtEnum.getItem(i));
                        arrayBuilder.add(itemName);
                    }
                    builder.add(domainAttribute.getName(), arrayBuilder.build());
                }
                else {
                    int    itemNum  = randomGenerator.nextInt(numItems);
                    String itemName = domainAttribute.getDomain().getNameOfNode(mtEnum.getItem(itemNum));
                    builder.add(domainAttribute.getName(), itemName);
                }
            }
            else if (attributeType.isNativeType()) {
                switch (((MTNativeType) attributeType).getDataType()) {
                    case INT32: {
                        builder.add(domainAttribute.getName(), randomGenerator.nextInt(50000));
                    }
                    break;
                    case INT64: {
                        builder.add(domainAttribute.getName(), randomGenerator.nextInt(1000000));
                    }
                    break;
                    case FLOAT: {
                        builder.add(domainAttribute.getName(),
                                    (float) randomGenerator.nextInt(50000) / ((float) randomGenerator.nextInt(100)
                                                                              + 1.0));
                    }
                    break;
                    case DOUBLE: {
                        builder.add(domainAttribute.getName(),
                                    (double) randomGenerator.nextInt(50000) / ((double) randomGenerator.nextInt(100)
                                                                               + 1.0));
                    }
                    break;
                    case BOOLEAN: {
                        builder.add(domainAttribute.getName(), false);
                    }
                    break;

                    case STRING: {
                        if (domainAttribute.getAttribute().isArray()) {
                            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                            for (int i = 0; i < (randomGenerator.nextInt(5) + 1); i++) {
                                arrayBuilder.add(makeRandomString(randomGenerator));
                            }
                            builder.add(domainAttribute.getName(), arrayBuilder);
                        }
                        else {
                            builder.add(domainAttribute.getName(), makeRandomString(randomGenerator));
                        }
                    }
                    break;

                    case UUID: {
                        builder.add(domainAttribute.getName(), "uuid");
                    }
                    break;
                    case DATE: {
                        builder.add(domainAttribute.getName(), 1605000000000L + randomGenerator.nextInt(717610851));
                    }
                    break;
                    default:
                        builder.add(domainAttribute.getName(), "unsupported type");
                        break;
                }
            }
        }
        if (domainRelationships != null) {
            for (MTDERelationship relationship : domainRelationships) {
                if (relationship.getRelationship().getTo().isOne()) {
                    JsonObjectBuilder refObject = Json.createObjectBuilder();
                    refObject.add("id", "uuid");
                    builder.add(relationship.getName(), refObject.build());
                }
            }
        }
        JsonObject jsonObject = builder.build();
        String     jsonString = jsonObject.toString();
        //ECLog.logInfo("Done JSON Filter.");
        if (escaped) {
            return jsonString.replaceAll("\"", "\\\\\"");
        }
        else {
            return jsonString;
        }
    }

    private String makeRandomString(Random randomGenerator) {
        StringBuilder stringBuilder = new StringBuilder();
        int           length        = randomGenerator.nextInt(7) + 3;
        for (int i = 0; i < length; i++) {
            char ch = (char) ('a' + randomGenerator.nextInt(26));
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }
}

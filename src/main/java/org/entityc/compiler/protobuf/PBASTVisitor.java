/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.protobuf;

import org.entityc.compiler.protobuf.Protobuf3BaseVisitor;
import org.entityc.compiler.protobuf.Protobuf3Parser;
import org.entityc.compiler.util.ECStringUtil;

import java.util.HashMap;
import java.util.Map;

public class PBASTVisitor extends Protobuf3BaseVisitor {

    private Map<String, Map<String, PBField>>                  messageFieldNumberMap = new HashMap<>();
    private String                                             currentMessageName    = null;
    private Map<String, Object>                                extractedOptions      = new HashMap<>();
    private Map<String, Protobuf3Parser.EnumDefinitionContext> enumContextMap        = new HashMap<>();

    public Map<String, Protobuf3Parser.EnumDefinitionContext> getEnumContextMap() {
        return enumContextMap;
    }

    public Map<String, Object> getExtractedOptions() {
        return extractedOptions;
    }

    public Map<String, Map<String, PBField>> getMessageFieldNumberMap() {
        return messageFieldNumberMap;
    }

    @Override
    public Object visitMessage(Protobuf3Parser.MessageContext ctx) {
        this.currentMessageName = ctx.messageName().getText();

        if (!messageFieldNumberMap.containsKey(currentMessageName)) {
            messageFieldNumberMap.put(currentMessageName, new HashMap<>());
        }
        return super.visitMessage(ctx);
    }

    @Override
    public Object visitField(Protobuf3Parser.FieldContext ctx) {
        if (ctx.fieldName().Ident() == null) {
            return super.visitField(ctx);
        }
        String fieldName = ctx.fieldName().Ident().getText();
        //ECLog.logInfo("Loading field: " + fieldName);
        String fieldNumberStr = ctx.fieldNumber() != null ? ctx.fieldNumber().getText() : null;
        if (fieldNumberStr != null) {
            Integer         fieldNumber     = Integer.valueOf(fieldNumberStr);
            String          entityFieldName = null;
            EntityFieldType entityFieldType = EntityFieldType.Unknown;
            if (ctx.ecFieldData() != null) {
                String typeString = ctx.ecFieldData().Ident(0).getText();
                if (typeString.equals("pk")) {
                    entityFieldType = EntityFieldType.PrimaryKey;
                } else if (typeString.equals("a")) {
                    entityFieldType = EntityFieldType.Attribute;
                } else if (typeString.equals("r")) {
                    entityFieldType = EntityFieldType.Relationship;
                }
                entityFieldName = ctx.ecFieldData().Ident(1).getText();
                PBField              field             = new PBField(fieldName, fieldNumber, entityFieldType, entityFieldName);
                Map<String, PBField> attributeFieldMap = messageFieldNumberMap.get(currentMessageName);
                attributeFieldMap.put(entityFieldName, field);
            }
        }

        return super.visitField(ctx);
    }

    @Override
    public Object visitEcFieldData(Protobuf3Parser.EcFieldDataContext ctx) {
        return super.visitEcFieldData(ctx);
    }

    @Override
    public Object visitOption(Protobuf3Parser.OptionContext ctx) {
        if (ctx.optionName() != null) {
            Object value = objectForConstant(ctx.constant());
            if (value != null) {
                extractedOptions.put(ctx.optionName().getText(), value);
            }
        }
        return super.visitOption(ctx);
    }

    private Object objectForConstant(Protobuf3Parser.ConstantContext constantContext) {
        if (constantContext.StrLit() != null) {
            return ECStringUtil.ProcessParserString(constantContext.StrLit().getText());
        }
        if (constantContext.BoolLit() != null) {
            return Boolean.valueOf(constantContext.BoolLit().getText());
        }
        if (constantContext.IntLit() != null) {
            return Integer.valueOf(constantContext.IntLit().getText());
        }
        if (constantContext.FloatLit() != null) {
            return Float.valueOf(constantContext.FloatLit().getText());
        }
        return null;
    }

    @Override
    public Object visitEnumDefinition(Protobuf3Parser.EnumDefinitionContext ctx) {
        String enumName = ctx.enumName().Ident().getText();
        enumContextMap.put(enumName, ctx);
        return super.visitEnumDefinition(ctx);
    }

    public enum EntityFieldType {
        Unknown,
        PrimaryKey,
        Attribute,
        Relationship
    }

    public class PBField {

        final private String          name;
        final private Integer         number;
        final private EntityFieldType entityFieldType;
        final private String          entityFieldName;

        PBField(String name, Integer number, EntityFieldType entityFieldType, String entityFieldName) {
            this.name = name;
            this.number = number;
            this.entityFieldType = entityFieldType;
            this.entityFieldName = entityFieldName;
        }

        public String getName() {
            return name;
        }

        public Integer getNumber() {
            return number;
        }

        public EntityFieldType getEntityFieldType() {
            return entityFieldType;
        }

        public String getEntityFieldName() {
            return entityFieldName;
        }
    }
}

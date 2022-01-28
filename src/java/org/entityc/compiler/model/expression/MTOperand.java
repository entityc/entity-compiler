/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.expression;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.doc.annotation.ModelMethod;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.domain.MTDEAttribute;
import org.entityc.compiler.model.domain.MTDEntity;
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTEnumItem;
import org.entityc.compiler.model.language.MTLanguage;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.List;

@ModelClass(type = ModelClassType.EXPRESSION,
    description = "This represents some operand of an expression such as a parameter of an entity or an enum item.")
public class MTOperand extends MTExpression {

    Type         type;
    List<String> names = new ArrayList<>(3);
    boolean      resolved;

    // resolved
    MTAttribute attribute;
    MTEnumItem  enumItem;

    public MTOperand(ParserRuleContext ctx, Type type, String name1) {
        super(ctx);
        this.type = type;
        this.names.add(name1);
    }

    public MTOperand(ParserRuleContext ctx, Type type, String name1, String name2) {
        super(ctx);
        this.type = type;
        this.names.add(name1);
        this.names.add(name2);
    }

    public MTOperand(ParserRuleContext ctx, Type type, String name1, String name2, String name3) {
        super(ctx);
        this.type = type;
        this.names.add(name1);
        this.names.add(name2);
        this.names.add(name3);
    }

    @ModelMethod(description = "Indicates the type of operand this is.")
    public Type getType() {
        return type;
    }

    @ModelMethod(description = "If this operand is an attribute of an entity this will return the attribute object.")
    public MTAttribute getAttribute() {
        return attribute;
    }

    @ModelMethod(description = "If this operand is an enum item this will return the enum item object.")
    public MTEnumItem getEnumItem() {
        return enumItem;
    }

    @ModelMethod(description = "Returns a string representing the operand (for debug purposes).")
    @Override
    public String toString() {
        return getFullname();
    }

    private String getFullname() {
        StringBuilder sb    = new StringBuilder();
        boolean       first = true;
        for (String name : names) {
            if (!first) {
                sb.append(".");
                first = false;
            }
            sb.append(name);
        }
        return sb.toString();
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    public boolean resolveReferences(MTSpace space, int pass) {
        if (this.resolved) {
            return false;
        }

        boolean exceededPassLimit = pass > 7;
        switch (type) {
            case ATTRIBUTE: {
                if (names.size() < 2) {
                    ECLog.logFatal(this, "Reference to an attribute in an expression must involve the entity name.");
                }
                // names[0] is the entity name
                // names[1] is the attribute name
                String   entityName    = names.get(0);
                String   attributeName = names.get(1);
                MTEntity entity        = space.getEntityWithName(entityName);
                if (entity == null) {
                    if (exceededPassLimit) {
                        //ECLog.logFatal(this, "Could not resolve entity named \"" + entityName + "\"");
                        return false;
                    }
                    break;
                }
                MTAttribute attribute = entity.getAttributeByName(attributeName);
                if (attribute == null) {
                    if (exceededPassLimit) {
                        ECLog.logFatal(this, "Attribute named \"" + attributeName + "\" of entity \"" + entityName
                                             + "\" does not exist.");
                    }
                    break;
                }
                this.attribute = attribute;
                this.resolved  = true;
            }
            break;

            case ENUM_ITEM: {
                if (names.size() < 2) {
                    ECLog.logFatal(this, "Reference to an enum item in an expression must involve the enum name.");
                }
                String entityName = null;
                String enumName   = null;
                String itemName   = null;
                if (names.size() == 2) {
                    // names[0] is the enum name
                    // names[1] is the enum item name
                    enumName = names.get(0);
                    itemName = names.get(1);
                }
                else if (names.size() == 3) {
                    entityName = names.get(0);
                    enumName   = names.get(1);
                    itemName   = names.get(2);
                }

                MTEntity entity = null;
                MTEnum   ecEnum = null;
                if (entityName != null) {
                    entity = space.getEntityWithName(entityName);
                    if (entity == null) {
                        if (exceededPassLimit) {
                            ECLog.logFatal(this, "Entity named \"" + entityName + "\" is not defined.");
                        }
                        break;
                    }
                    ecEnum = entity.getEnum(enumName);
                    if (ecEnum == null) {
                        if (exceededPassLimit) {
                            ECLog.logFatal(this, "Enum named \"" + enumName + "\" of entity \"" + entityName
                                                 + "\" does not exist.");
                        }
                        break;
                    }
                }
                else {
                    ecEnum = space.getEnumWithName(enumName);
                    if (ecEnum == null && exceededPassLimit) {
                        ECLog.logFatal(this, "Enum named \"" + enumName + "\" does not exist.");
                    }
                    break;
                }

                MTEnumItem item = ecEnum.getItemByName(itemName);
                if (item == null) {
                    if (exceededPassLimit) {
                        ECLog.logFatal(this, "Enum item named \"" + itemName + "\" of enum \"" + enumName
                                             + "\" does not exist.");
                    }
                    break;
                }
                resolved = true;
            }
            break;
        }
        return !resolved;
    }

    @Override
    public String mapToLanguage(MTLanguage language, MTDEntity domainEntity, String objectName) {
        switch (type) {
            case UNKNOWN:
            case ENTITY:
                return names.size() > 0 ?
                       names.get(0) :
                       "";
            case ATTRIBUTE: {
                String attributeName = names.size() > 1 ?
                                       names.get(1) :
                                       "";
                if (domainEntity != null) {
                    MTDEAttribute domainAttribute = domainEntity.getDomainAttributeByName(attributeName, true);
                    if (domainAttribute != null) {
                        attributeName = domainAttribute.getName();
                    }
                }
                if (objectName != null) {
                    attributeName += objectName + "." + attributeName;
                }
                return attributeName;
            }
        }
        return "<<INVALID_OPERAND>>";
    }

    public enum Type {
        UNKNOWN,
        ENTITY,
        ATTRIBUTE,
        ENUM_ITEM,
    }
}

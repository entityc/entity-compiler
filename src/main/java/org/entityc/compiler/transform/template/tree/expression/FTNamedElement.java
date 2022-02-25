/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.expression;

import org.entityc.compiler.model.domain.MTDomain;
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTTypedef;
import org.entityc.compiler.model.interop.MTInterface;
import org.entityc.compiler.model.interop.MTInterfaceOperation;
import org.entityc.compiler.model.language.MTLanguage;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.util.ECLog;
import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.transform.template.formatter.ConfigurableElement;

import java.util.List;

/**
 * A reference to a particular domain, entity, attribute, etc. by its name.
 */
public class FTNamedElement extends FTConstant {

    private final List<String> nameParts;
    private final Type         type;

    public FTNamedElement(ParserRuleContext ctx, String typeName, List<String> nameParts) {
        super(ctx, null);
        Type type = Type.findByName(typeName);
        if (type == null) {
            ECLog.logFatal(ctx, "Unknown type: " + typeName);
        }
        this.type = type;
        this.nameParts = nameParts;
    }

    @Override
    public Object getValue(FTTransformSession session) {

        switch (this.type) {
            case DomainType: {
                if (nameParts.size() > 1) {
                    ECLog.logFatal("Domains are globally referenced, no such domain: " + getFullName());
                }
                String   domainName = leafName();
                MTDomain domain     = session.getSpace().getDomainWithName(domainName);
                if (domain == null) {
                    ECLog.logFatal(this, "Unable to find domain with name: " + domainName);
                }
                return domain;
            }
            case EntityType: {
                if (nameParts.size() > 1) {
                    ECLog.logFatal("Entities are globally referenced, no such entity: " + getFullName());
                }
                String   entityName = leafName();
                MTEntity entity     = session.getSpace().getEntityWithName(entityName);
                if (entity == null) {
                    ECLog.logFatal(this, "Unable to find entity with name: " + entityName);
                }
                return entity;
            }
            case AttributeType: {
                if (nameParts.size() > 2) {
                    ECLog.logFatal("Attributes are referenced by their entities, no such attribute: " + getFullName());
                }
                String   entityName    = nameParts.get(0);
                String   attributeName = nameParts.get(1);
                MTEntity entity        = session.getSpace().getEntityWithName(entityName);
                if (entity == null) {
                    ECLog.logFatal(this, "Unable to find entity with name: " + entityName);
                }
                MTAttribute attribute = entity.getAttributeByName(attributeName);
                if (attribute == null) {
                    ECLog.logFatal(this, "Unable to find attribute with name: " + getFullName());
                }
                return attribute;
            }
            case EnumType: {
                if (nameParts.size() > 1) {
                    ECLog.logFatal("Enums are globally referenced, no such enum: " + getFullName());
                }
                String enumName = leafName();
                MTEnum mtEnum   = session.getSpace().getEnumWithName(enumName);
                if (mtEnum == null) {
                    ECLog.logFatal(this, "Unable to find enum with name: " + enumName);
                }
                return mtEnum;
            }
            case TypedefType: {
                if (nameParts.size() > 1) {
                    ECLog.logFatal("Typedefs are globally referenced, no such typedef: " + getFullName());
                }
                String    typedefName = leafName();
                MTTypedef typedef     = session.getSpace().getTypedefWithName(typedefName);
                if (typedef == null) {
                    ECLog.logFatal(this, "Unable to find typedef with name: " + typedefName);
                }
                return typedef;
            }
            case InterfaceType: {
                if (nameParts.size() > 1) {
                    ECLog.logFatal("Interfaces are globally referenced, no such interface: " + getFullName());
                }
                String      interfaceName = leafName();
                MTInterface mtInterface   = session.getSpace().getInterface(interfaceName);
                if (mtInterface == null) {
                    ECLog.logFatal(this, "Unable to find interface with name: " + interfaceName);
                }
                return mtInterface;
            }
            case OperationType: {
                if (nameParts.size() > 2) {
                    ECLog.logFatal("Operations are referenced by their interfaces, no such operation: " + getFullName());
                }
                String      interfaceName = nameParts.get(0);
                String      operationName = nameParts.get(1);
                MTInterface mtInterface   = session.getSpace().getInterface(interfaceName);
                if (mtInterface == null) {
                    ECLog.logFatal(this, "Unable to find interface with name: " + interfaceName);
                }
                MTInterfaceOperation operation = mtInterface.getOperation(operationName);
                if (operation == null) {
                    ECLog.logFatal(this, "Unable to find operation with name: " + getFullName());
                }
                return operation;
            }
            case LanguageType: {
                if (nameParts.size() > 1) {
                    ECLog.logFatal("Languages are globally referenced, no such language: " + getFullName());
                }
                String     languageName = leafName();
                MTLanguage language     = session.getSpace().getLanguageWithName(languageName);
                if (language == null) {
                    ECLog.logFatal(this, "Unable to find language with name: " + languageName);
                }
                return language;
            }
        }
        ECLog.logFatal("Unabled to find named element: " + getFullName());
        return null;
    }

    public String getFullName() {
        return String.join(".", nameParts);
    }

    public String leafName() {
        return this.nameParts.get(this.nameParts.size() - 1);
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        formatController.addExpressionElement(ConfigurableElement.NamedElementType, type.name, this.getStartLineNumber());
        formatController.addExpressionElement(ConfigurableElement.NamedElementDelim, ":", this.getStartLineNumber());
        formatController.addExpressionElement(ConfigurableElement.NamedElementName, getFullName(), this.getStartLineNumber());
        return true;
    }

    public enum Type {
        DomainType("domain"),
        EntityType("entity"),
        AttributeType("attribute"),
        EnumType("enum"),
        TypedefType("typedef"),
        InterfaceType("interface"),
        OperationType("operation"),
        LanguageType("language");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        static Type findByName(String name) {
            for (Type type : values()) {
                if (type.name.equals(name)) {
                    return type;
                }
            }
            return null;
        }
    }
}

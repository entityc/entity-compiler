/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

import org.entityc.compiler.model.MTNamespace;
import org.entityc.compiler.model.domain.MTDomain;
import org.entityc.compiler.model.entity.MTEntity;

import java.util.ArrayList;
import java.util.List;

public class CSClass extends CSAnnotatableNode implements CSReferenceResolution {

    private final List<CSClass>               implementsClasses          = new ArrayList<>();
    private final List<CSVariableDeclaration> memberVariableDeclarations = new ArrayList<>();
    private final List<CSMethod>              methods                    = new ArrayList<>();
    private final List<CSMethodPrototype>     methodPrototypes           = new ArrayList<>();
    private       String                      name;
    private       String                      entityName;
    private       CSNamespace                 namespace;
    private       CSClass                     extendsClass;
    private       CSComment                   comment;
    private       ClassType                   type                       = ClassType.Implementation;

    public CSClass(CSNamespace namespace, String className, String entityName) {
        this.namespace = namespace;
        this.name = className;
        this.entityName = entityName;
    }

    public CSClass(MTNamespace namespace, String className, String entityName) {
        this.namespace = namespace != null ? new CSNamespace(namespace.getSegments()) : null;
        this.name = className;
        this.entityName = entityName;
    }

    public CSClass(String namespace, String className, String entityName) {
        this.namespace = new CSNamespace(namespace.split("\\."));
        this.name = className;
        this.entityName = entityName;
    }

    public CSClass(MTDomain domain, MTEntity entity) {
        this.namespace = new CSNamespace(domain.getNamespace().getSegments());
        this.name = domain.getNameOfNode(entity);
        this.entityName = entity.getName();
    }

    public CSMethod getMethod(String name) {
        for (CSMethod method : methods) {
            if (method.getName().equals(name)) {
                return method;
            }
        }
        return null;
    }

    public ClassType getType() {
        return type;
    }

    public void setType(ClassType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CSVariableDeclaration> getMemberVariableDeclarations() {
        return memberVariableDeclarations;
    }

    public void addMemberVariableDeclaration(CSVariableDeclaration declaration) {
        memberVariableDeclarations.add(declaration);
        declaration.setParentNode(this);
    }

    public void addMethod(CSMethod method) {
        methods.add(method);
        method.setParentNode(this);
    }

    public void addMethodPrototype(CSMethodPrototype methodPrototype) {
        methodPrototypes.add(methodPrototype);
        methodPrototype.setParentNode(this);
    }

    public void accept(CSVisitor visitor) {
        for (CSVariableDeclaration decl : memberVariableDeclarations) {
            visitor.visit(decl);
        }
        for (CSMethodPrototype methodPrototype : methodPrototypes) {
            visitor.visit(methodPrototype);
        }
        for (CSMethod method : methods) {
            visitor.visit(method);
        }
    }

    @Override
    public boolean resolveReferences(CSTop top, int pass) {
        boolean needsAnotherPass = false;
        for (CSVariableDeclaration declaration : memberVariableDeclarations) {
            boolean result = declaration.getVariable().resolveReferences(top, pass);
            if (result) {
                needsAnotherPass = true;
            }
        }
        return needsAnotherPass;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public CSComment getComment() {
        return comment;
    }

    public void setComment(CSComment comment) {
        this.comment = comment;
    }

    public CSNamespace getNamespace() {
        return namespace;
    }

    public void setNamespace(CSNamespace namespace) {
        this.namespace = namespace;
    }

    public CSClass getExtendsClass() {
        return extendsClass;
    }

    public void setExtendsClass(CSClass extendsClass) {
        this.extendsClass = extendsClass;
    }

    public List<CSClass> getImplementsClasses() {
        return implementsClasses;
    }

    public void addImplementsClass(CSClass csClass) {
        this.implementsClasses.add(csClass);
    }

    public enum ClassType {
        Implementation,
        Definition,
        Interface,
    }
}

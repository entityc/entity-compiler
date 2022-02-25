/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

import org.entityc.compiler.model.domain.MTDomain;
import org.entityc.compiler.util.ECStringUtil;

public class CSVariable extends CSNode implements CSReferenceResolution {

    private CSType     type;
    private String     name;
    private boolean    member;
    private CSVariable parentVariable;
    private CSVariable subVariable;
    private MTDomain   domain;
    private boolean    referenceWithGetter;

    public CSVariable(String name) {
        this.type = null;
        this.name = name;
    }

    public CSVariable(CSType type, String name) {
        this.type = type;
        this.name = name;
    }

    public CSVariable(CSVariable variable) {
        this.type = variable.type;
        this.name = variable.name;
        this.member = variable.member;
        this.domain = variable.domain;
    }

    public CSVariable(CSClass csClass, MTDomain domain) {
        this.type = new CSType(csClass);
        this.name = ECStringUtil.EntityNameToVariableName(csClass.getName());
        this.member = false;
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CSType getType() {
        return type;
    }

    public void setType(CSType type) {
        this.type = type;
    }

    public void accept(CSVisitor visitor) {
    }

    public boolean isMember() {
        return member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }

    @Override
    public boolean resolveReferences(CSTop top, int pass) {
        if (type != null && !type.isNativeType()) {
            return type.resolveReferences(top, pass);
        }
        return false;
    }

    public CSVariable getSubVariable() {
        return subVariable;
    }

    public void setSubVariable(CSVariable subVariable) {
        this.subVariable = subVariable;
        subVariable.parentVariable = this;
    }

    public CSVariable getTopParentVariable() {
        if (parentVariable != null) {
            return parentVariable.getTopParentVariable();
        }
        return this;
    }

    public MTDomain getDomain() {
        return domain;
    }

    public void setDomain(MTDomain domain) {
        this.domain = domain;
    }

    public String toString() {
        if (this.domain == null) {
            return name;
        }
        StringBuilder builder           = new StringBuilder();
        CSVariable    topParentVariable = this.getTopParentVariable();
        if (topParentVariable.isMember()) {
            builder.append("this.");
        }
        CSVariable var = topParentVariable;
        do {
            if (var.isReferenceWithGetter()) {
                builder.append("get" + ECStringUtil.VariableInMethodName(var.getName()) + "()");
            } else {
                builder.append(var.getName());
            }
            var = var.getSubVariable();
            if (var != null) {
                builder.append(".");
            }
        } while (var != null);
        return builder.toString();
    }

    public boolean isReferenceWithGetter() {
        return referenceWithGetter;
    }

    public void setReferenceWithGetter(boolean referenceWithGetter) {
        this.referenceWithGetter = referenceWithGetter;
    }
}

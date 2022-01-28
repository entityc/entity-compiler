/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

import org.entityc.compiler.util.ECStringUtil;

import java.util.ArrayList;
import java.util.List;

public class CSMethod extends CSCodeBlock {

    private final List<CSMethodArgument> arguments    = new ArrayList<>();
    private final List<CSClass>          throwClasses = new ArrayList<>();
    private       CSAnnotation           annotation;
    private       String                 name;
    private       CSType                 returnType;
    private       CSMemberAccess         memberAccess = CSMemberAccess.PRIVATE;
    private       CSMemberScope          memberScope  = CSMemberScope.OBJECT;
    private       CSComment              comment;

    public CSMethod(CSType returnType, String name) {
        this.returnType = returnType;
        this.name = name;
    }

    public static CSMethod Getter(CSVariable variable, CSMemberAccess memberAccess) {
        CSMethod getter = new CSMethod(variable.getType(), "get" + ECStringUtil.VariableInMethodName(variable.getName()));
        getter.setMemberAccess(memberAccess);
        CSReturnStatement returnStatement = new CSReturnStatement(new CSExpression(variable));
        getter.addStatement(returnStatement);
        return getter;
    }

    public static CSMethod Setter(CSVariable variable, CSMemberAccess memberVisibility) {
        CSMethod setter = new CSMethod(null, "set" + ECStringUtil.VariableInMethodName(variable.getName()));
        setter.setMemberAccess(memberVisibility);
        setter.addArgument(new CSMethodArgument(variable));
        CSVariable memberVariable = new CSVariable(variable.getType(), variable.getName());
        memberVariable.setMember(true);
        setter.addStatement(new CSAssignment(memberVariable, new CSExpression(variable)));
        return setter;
    }

    public void addArgument(CSMethodArgument argument) {
        arguments.add(argument);
        argument.setParentNode(this);
    }

    public static CSMethod BitFieldGetter(CSVariable variable, CSMemberAccess memberAccess, CSVariable sourceVariable, int highBit, int lowBit) {
        CSExpression fullExpression;
        CSMethod     getter = new CSMethod(sourceVariable.getType(), "get" + ECStringUtil.VariableInMethodName(sourceVariable.getName()));
        getter.setMemberAccess(memberAccess);
        int numBits = highBit - lowBit + 1;

        if (sourceVariable.getType().getNativeType() == CSType.CSNativeType.BOOLEAN) {
            int          mask           = (1 << lowBit);
            CSExpression maskExpression = new CSExpression(variable, CSOperator.Operation.BITWISE_AND, new CSExpression(mask));
            fullExpression = new CSExpression(maskExpression, CSOperator.Operation.NOT_EQUAL, new CSExpression(0));
        } else {
            CSExpression shiftExpression = new CSExpression(variable, CSOperator.Operation.SHIFT_RIGHT, new CSExpression(lowBit));
            int          mask            = (1 << numBits) - 1;
            fullExpression = new CSExpression(shiftExpression, CSOperator.Operation.BITWISE_AND, new CSExpression(mask));
        }
        CSReturnStatement returnStatement = new CSReturnStatement(fullExpression);
        getter.addStatement(returnStatement);
        return getter;
    }

    public static CSMethod BitFieldSetter(CSVariable variable, CSMemberAccess memberVisibility, CSVariable sourceVariable, int highBit, int lowBit) {
        CSMethod setter = new CSMethod(null, "set" + ECStringUtil.VariableInMethodName(sourceVariable.getName()));
        setter.setMemberAccess(memberVisibility);
        setter.addArgument(new CSMethodArgument(sourceVariable));
        CSVariable memberVariable = new CSVariable(variable.getType(), variable.getName());
        memberVariable.setMember(true);
        CSExpression bitsetMask       = new CSExpression(CSExpression.BooleanToInt(sourceVariable), CSOperator.Operation.SHIFT_LEFT, new CSExpression(lowBit));
        CSExpression notBitsetMask    = new CSExpression(CSOperator.Operation.BITWISE_INV, bitsetMask);
        CSExpression maskedExpression = new CSExpression(variable, CSOperator.Operation.BITWISE_AND, notBitsetMask);
        CSExpression fullExpression   = new CSExpression(maskedExpression, CSOperator.Operation.BITWISE_OR, bitsetMask);
        setter.addStatement(new CSAssignment(memberVariable, fullExpression));
        return setter;
    }

    public List<CSMethodArgument> getArguments() {
        return arguments;
    }

    public CSAnnotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(CSAnnotation annotation) {
        this.annotation = annotation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CSType getReturnType() {
        return returnType;
    }

    public void setReturnType(CSType returnType) {
        this.returnType = returnType;
    }

    public CSMemberAccess getMemberAccess() {
        return memberAccess;
    }

    public void setMemberAccess(CSMemberAccess memberAccess) {
        this.memberAccess = memberAccess;
    }

    public CSComment getComment() {
        return comment;
    }

    public void setComment(CSComment comment) {
        this.comment = comment;
    }

    public void addThrowClass(CSClass throwClass) {
        throwClasses.add(throwClass);
    }

    public List<CSClass> getThrowClasses() {
        return throwClasses;
    }

    public boolean hasThrowClasses() {
        return throwClasses.size() > 0;
    }

    public CSMemberScope getMemberScope() {
        return memberScope;
    }

    public void setMemberScope(CSMemberScope memberScope) {
        this.memberScope = memberScope;
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

import java.util.ArrayList;
import java.util.List;

public class CSMethodInvocation extends CSStatement {

    private final String                 methodName;
    private final List<CSMethodArgument> arguments = new ArrayList<>();
    private       CSVariable             variable;
    private       String                 className;
    private       boolean                inline;

    public CSMethodInvocation(CSVariable variable, String methodName) {
        this.variable = variable;
        this.methodName = methodName;
    }

    public CSMethodInvocation(String className, String methodName) {
        this.className = className;
        this.methodName = methodName;
    }

    public List<CSMethodArgument> getArguments() {
        return arguments;
    }

    public void addArgument(CSMethodArgument argument) {
        arguments.add(argument);
    }

    @Override
    public void accept(CSVisitor visitor) {

    }

    public String getMethodName() {
        return methodName;
    }

    public CSVariable getVariable() {
        return variable;
    }

    public boolean hasArguments() {
        return arguments != null && arguments.size() > 0;
    }

    public String getClassName() {
        return className;
    }

    public boolean isInline() {
        return inline;
    }

    public void setInline(boolean inline) {
        this.inline = inline;
    }
}

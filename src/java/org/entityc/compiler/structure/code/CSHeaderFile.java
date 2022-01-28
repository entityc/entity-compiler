/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

import java.util.ArrayList;
import java.util.List;

public class CSHeaderFile extends CSSourceFile {

    private final List<CSFunctionPrototype> functionPrototypes = new ArrayList<>();
    private       CSSourceFile              sourceFile;

    public CSHeaderFile(String name, String extension) {
        super(name, extension);
    }

    public CSSourceFile getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(CSSourceFile sourceFile) {
        this.sourceFile = sourceFile;
    }

    public void addFunctionPrototype(CSFunctionPrototype functionPrototype) {
        this.functionPrototypes.add(functionPrototype);
    }

    public List<CSFunctionPrototype> getFunctionPrototypes() {
        return functionPrototypes;
    }

    @Override
    public void accept(CSVisitor visitor) {
        if (getSourceClass() != null) {
            visitor.visit(getSourceClass());
        } else if (getSourceEnum() != null) {
            visitor.visit(getSourceEnum());
        }
        for (CSFunctionPrototype functionPrototype : functionPrototypes) {
            visitor.visit(functionPrototype);
        }
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

import org.entityc.compiler.model.MTNamespace;

import java.util.ArrayList;
import java.util.List;

public class CSSourceFile extends CSNode implements CSReferenceResolution {

    private final String           name;
    private final String           extension;
    private final List<CSImport>   imports   = new ArrayList<>();
    private final List<CSExtern>   externs   = new ArrayList<>();
    private final List<CSFunction> functions = new ArrayList<>();
    private       CSClass          sourceClass;
    private       CSEnum           sourceEnum;
    private       CSNamespace      namespace;

    public CSSourceFile(String name, String extension) {
        this.name = name;
        this.extension = extension;
    }

    public CSClass getSourceClass() {
        return sourceClass;
    }

    public void setSourceClass(CSClass sourceClass) {
        this.sourceClass = sourceClass;
        this.sourceClass.setNamespace(this.namespace);
    }

    public CSEnum getSourceEnum() {
        return sourceEnum;
    }

    public void setSourceEnum(CSEnum sourceEnum) {
        this.sourceEnum = sourceEnum;
    }

    public void addExtern(CSExtern extern) {
        for (CSExtern existingExtern : externs) {
            if (existingExtern.equals(extern)) {
                return; // ignore if already exists
            }
        }
        externs.add(extern);
    }

    public List<CSExtern> getExterns() {
        return externs;
    }

    public boolean hasExterns() {
        return externs.size() > 0;
    }

    public void addImport(CSImport importStmt) {
        for (CSImport existingImport : imports) {
            if (existingImport.getArgument().equals(importStmt.getArgument())) {
                return; // ignore if already exists
            }
        }
        imports.add(importStmt);
    }

    public List<CSImport> getImports() {
        return imports;
    }

    public boolean hasImports() {
        return imports.size() > 0;
    }

    public void accept(CSVisitor visitor) {
        if (sourceClass != null) {
            visitor.visit(sourceClass);
        } else if (sourceEnum != null) {
            visitor.visit(sourceEnum);
        }
        for (CSFunction function : functions) {
            visitor.visit(function);
        }
    }

    public String getFilename() {
        return getName() + "." + getExtension();
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public boolean resolveReferences(CSTop top, int pass) {
        if (sourceClass != null) {
            return sourceClass.resolveReferences(top, pass);
        }
        return false;
    }

    public CSNamespace getNamespace() {
        return namespace;
    }

    public void setNamespace(MTNamespace namespace) {
        this.namespace = new CSNamespace(namespace.getSegments());
    }

    public void setNamespace(CSNamespace namespace) {
        this.namespace = namespace;
    }

    public List<CSFunction> getFunctions() {
        return functions;
    }

    public void addFunction(CSFunction function) {
        this.functions.add(function);
    }
}

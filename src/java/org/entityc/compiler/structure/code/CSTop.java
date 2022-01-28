/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSTop extends CSNode {

    private final List<CSSourceFile>   sourceFiles       = new ArrayList<>();
    private final List<CSHeaderFile>   headerFiles       = new ArrayList<>();
    private final Map<String, CSClass> sourceClassByName = new HashMap<>();
    private       CSSourceFile         currentSourceFile;
    private       CSHeaderFile         currentHeaderFile;

    public CSClass getClassByName(String name) {
        return sourceClassByName.get(name);
    }

    public void addSourceFile(CSSourceFile sourceFile) {
        sourceFiles.add(sourceFile);
        currentSourceFile = sourceFile;
    }

    public void addHeaderFile(CSHeaderFile headerFile) {
        headerFiles.add(headerFile);
        currentHeaderFile = headerFile;
    }

    @Override
    public void accept(CSVisitor visitor) {
        for (CSSourceFile sourceFile : sourceFiles) {
            visitor.visit(sourceFile);
        }
        for (CSHeaderFile headerFile : headerFiles) {
            visitor.visit(headerFile);
        }
    }

    public CSSourceFile getCurrentSourceFile() {
        return currentSourceFile;
    }

    public void setCurrentSourceFile(CSSourceFile currentSourceFile) {
        this.currentSourceFile = currentSourceFile;
    }

    public List<CSSourceFile> getSourceFiles() {
        return sourceFiles;
    }

    public List<CSHeaderFile> getHeaderFiles() {
        return headerFiles;
    }

    public CSHeaderFile getCurrentHeaderFile() {
        return currentHeaderFile;
    }

    public void resolveReferences() {
        // locate and map all source files
        for (CSSourceFile sourceFile : sourceFiles) {
            CSClass sourceClass = sourceFile.getSourceClass();
            if (sourceClass != null) {
                sourceClassByName.put(sourceClass.getName(), sourceClass);
            }
        }
        for (int pass = 0; pass < 10; pass++) {
            boolean needsAnotherPass = false;
            for (CSSourceFile sourceFile : sourceFiles) {
                if (sourceFile.resolveReferences(this, pass)) {
                    needsAnotherPass = true;
                }
            }
            if (!needsAnotherPass) {
                break;
            }
        }
    }
}

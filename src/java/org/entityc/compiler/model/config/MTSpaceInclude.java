/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.config;

import java.util.ArrayList;
import java.util.List;

public class MTSpaceInclude {

    private final List<MTRepositoryImport> imports           = new ArrayList<>();
    private final List<String>             importEnumNames   = new ArrayList<>(); // enums to import even if in an include
    private final List<String>             importEntityNames = new ArrayList<>(); // entities to import even if in an include

    public List<String> getImportEntityNames() {
        return importEntityNames;
    }

    public List<String> getImportEnumNames() {
        return importEnumNames;
    }

    public List<MTRepositoryImport> getImports() {
        return imports;
    }

    public void addImport(MTRepositoryImport modelImport) {
        imports.add(modelImport);
    }

    public void addEnumName(String name) {
        importEnumNames.add(name);
    }

    public void addEntityName(String name) {
        importEntityNames.add(name);
    }
}

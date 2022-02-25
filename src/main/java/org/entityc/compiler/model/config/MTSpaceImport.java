/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.config;

import java.util.ArrayList;
import java.util.List;

public class MTSpaceImport {

    private final List<MTRepositoryImport> imports = new ArrayList<>();

    public List<MTRepositoryImport> getImports() {
        return imports;
    }

    public void addImport(MTRepositoryImport modelImport) {
        imports.add(modelImport);
    }
}

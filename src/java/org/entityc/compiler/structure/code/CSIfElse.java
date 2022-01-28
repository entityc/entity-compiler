/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

import java.util.ArrayList;
import java.util.List;

public class CSIfElse extends CSStatement {

    private final List<CSStatement> conditions = new ArrayList<>();
    private final List<CSCodeBlock> codeBlocks = new ArrayList<>();
    private       CSCodeBlock       elseCodeBlock;

    public void addIf(CSStatement condition, CSCodeBlock codeBlock) {
        conditions.add(condition);
        codeBlocks.add(codeBlock);
    }

    public void addIf(CSStatement condition, CSStatement statement) {
        conditions.add(condition);
        CSCodeBlock codeBlock = new CSCodeBlock();
        codeBlock.addStatement(statement);
        codeBlocks.add(codeBlock);
    }

    public CSCodeBlock getElseCodeBlock() {
        return elseCodeBlock;
    }

    public void setElseCodeBlock(CSCodeBlock elseCodeBlock) {
        this.elseCodeBlock = elseCodeBlock;
    }

    public int size() {
        return conditions.size();
    }

    public CSStatement getCondition(int num) {
        if (num >= conditions.size()) {
            return null;
        }
        return conditions.get(num);
    }

    public CSCodeBlock getCodeBlock(int num) {
        if (num >= codeBlocks.size()) {
            return null;
        }
        return codeBlocks.get(num);
    }
}

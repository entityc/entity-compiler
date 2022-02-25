/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.formatter;

enum TextSegmentType {
    Instruction,
    Variable,
    Expression,
    Comment,
    Source,
    ;

    public boolean inSourceFlow() {
        return this == Source || this == Variable;
    }
}

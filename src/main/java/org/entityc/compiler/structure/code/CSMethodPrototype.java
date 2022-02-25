/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

public class CSMethodPrototype extends CSAnnotatableNode {

    private final CSMethod       method;
    private final CSMemberAccess memberAccess;
    private       CSComment      comment;

    public CSMethodPrototype(CSMethod method, CSMemberAccess memberAccess) {
        this.method = method;
        this.memberAccess = memberAccess;
    }

    public CSMethodPrototype(CSMethod method) {
        this.method = method;
        this.memberAccess = CSMemberAccess.NONE;
    }

    public CSMethod getMethod() {
        return method;
    }

    public CSComment getComment() {
        return comment;
    }

    public void setComment(CSComment comment) {
        this.comment = comment;
    }
}

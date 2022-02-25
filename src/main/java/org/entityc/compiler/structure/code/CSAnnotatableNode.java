/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CSAnnotatableNode extends CSNode {

    private final List<CSAnnotation> annotations = new ArrayList<>();

    public void addAnnotation(CSAnnotation annotation) {
        this.annotations.add(annotation);
    }

    public Collection<CSAnnotation> getAnnotations() {
        return annotations;
    }

    @Override
    public void accept(CSVisitor visitor) {

    }
}

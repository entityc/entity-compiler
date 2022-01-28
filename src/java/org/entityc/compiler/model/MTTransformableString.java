/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model;

public class MTTransformableString {

    private final String rawValue;
    private       String transformedValue;

    public MTTransformableString(String rawValue) {
        this.rawValue = rawValue;
    }

    public String toString() {
        if (transformedValue != null) {
            return transformedValue;
        }
        return rawValue;
    }

    public void transform(MTStringTransformer transformer) {
        if (transformedValue == null && rawValue != null && rawValue.contains("${")) {
            transformedValue = transformer.transformString(rawValue);
        }
    }
}

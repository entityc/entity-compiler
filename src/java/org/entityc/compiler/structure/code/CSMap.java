/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

import java.util.HashMap;
import java.util.Map;

public class CSMap extends CSStatement {

    private final Map<String, Object> map = new HashMap<>();

    public void put(String key, Object value) {
        map.put(key, value);
    }

    public Map<String, Object> getMap() {
        return map;
    }

    @Override
    public void accept(CSVisitor visitor) {

    }
}

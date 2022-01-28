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

public class CSAnnotation extends CSNode {

    private final String              name;
    private final List<Object>        argumentArray = new ArrayList<>();
    private final Map<String, Object> argumentMap   = new HashMap<>();

    public CSAnnotation(String name) {
        this.name = name;
    }

    public CSAnnotation(String name, Object[] stringArgs) {
        this.name = name;
        for (Object arg : stringArgs) {
            argumentArray.add(arg);
        }
    }

    public CSAnnotation(String name, Map<String, Object> argumentMap) {
        this.name = name;
        this.add(argumentMap);
    }

    public void add(Map<String, Object> argumentMap) {
        this.argumentMap.putAll(argumentMap);
    }

    public CSAnnotation(String name, CSVariable variable) {
        this.name = name;
        argumentArray.add(variable);
    }

    public List<Object> getArgumentArray() {
        return argumentArray;
    }

    public Map<String, Object> getArgumentMap() {
        return argumentMap;
    }

    public void add(Object argument) {
        this.argumentArray.add(argument);
    }

    public void add(Object[] arguments) {
        for (Object argument : arguments) {
            this.argumentArray.add(argument);
        }
    }

    public void add(CSVariable variable) {
        this.argumentArray.add(variable);
    }

    public void put(String name, Object value) {
        argumentMap.put(name, value);
    }

    public void accept(CSVisitor visitor) {
    }

    public String getName() {
        return name;
    }

    public boolean hasArgumentArray() {
        return argumentArray.size() > 0;
    }

    public boolean hasArgumentMap() {
        return argumentMap.size() > 0;
    }
}

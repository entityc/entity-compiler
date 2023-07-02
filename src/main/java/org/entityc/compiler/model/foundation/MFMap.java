/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.foundation;

import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.doc.annotation.ModelMethod;
import org.entityc.compiler.doc.annotation.ModelMethodParameter;
import org.entityc.compiler.util.ECLog;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@ModelClass(type = ModelClassType.FOUNDATION,
        description = "This class represents a map of objects. "
                      + "To create an empty map you can use: `$[let myMap = @{}@]` then can use these methods on that "
                      + "variable (`myMap` in this example) to access the map functionality that is exposed here.")
public class MFMap extends MFObject {

    private final HashMap map = new HashMap();

    public MFMap() {
        super();
    }

    public MFMap(Map map) {
        super();
        map.putAll(map);
    }

    @ModelMethod(description =
            "Returns all the keys of the map")
    public Collection getKeys() {
        return map.keySet();
    }

    @ModelMethod(description =
            "Returns all the values of the map. When iterating this array of values with a `foreach` you do "
            + "**not** need to use this method.")
    public Collection getValues() {
        return map.values();
    }

    @ModelMethod(description =
            "Allows you to add an object to this array. The `do` template instruction can be used to do "
            + "this (e.g., `$[do array.add(obj)]`. This method returns the array itself so you "
            + "can chain other array methods after this one.")
    public MFMap put(
            @ModelMethodParameter(description = "The key to put to the map.")
            Object key,
            @ModelMethodParameter(description = "The object to set to the map.")
            Object o) {
        map.put(key, o);
        return this;
    }

    @ModelMethod(description = "Allows you to make a copy of this map. Since it returns the new map "
                               + "you can chain other map methods after this one.")
    public MFMap copy() {
        MFMap newMap = new MFMap();
        newMap.map.putAll(this.map);
        return newMap;
    }

    @ModelMethod(description =
            "Allows you to add all elements of another map to this map. The `do` template instruction "
            + "can be used to do  this (e.g., `$[do map.putAll(otherMap)]`. This method returns the "
            + "map itself so you can chain other map methods after this one.")
    public MFMap putAll(
            @ModelMethodParameter(description = "The other map to add to this map.")
            MFMap otherMap) {
        map.putAll(otherMap.map);
        return this;
    }

    @ModelMethod(description = "Allows you to remove a specified object from the map based on its key. This method returns the "
                               + "map itself so you can chain other map methods after this one.")
    public MFMap remove(
            @ModelMethodParameter(description = "The key to remove from this map.")
            Object key,
            @ModelMethodParameter(description = "The object to remove from this map.")
            Object o) {
        map.remove(key, o);
        return this;
    }

    @ModelMethod(description = "Allows you to remove a specified entry from the map based on its key. This method returns the "
                               + "map itself so you can chain other map methods after this one.")
    public MFMap remove(
            @ModelMethodParameter(description = "The key to remove from this map.")
            Object key) {
        map.remove(key);
        return this;
    }

    @ModelMethod(description = "This method removes all items in the map.")
    public MFMap clear() {
        map.clear();
        return this;
    }

    @ModelMethod(description = "Returns the value in the map by its specified key.")
    public Object get(
            @ModelMethodParameter(description = "The key.")
            Object key) {
        return map.get(key);
    }

    @ModelMethod(description = "Indicates if the map is empty (no items).")
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @ModelMethod(description = "Indicates if the map contains the specified key.")
    public boolean containsKey(
            @ModelMethodParameter(description = "The key of which to determine its presence in the map.")
            Object key) {
        return map.containsKey(key);
    }

    @ModelMethod(description = "Indicates if the map contains the specified value.")
    public boolean containsValue(
            @ModelMethodParameter(description = "The object of which to determine its presence in the map.")
            Object value) {
        return map.containsValue(value);
    }

    @ModelMethod(description = "Returns the number of items in the map.")
    public int getCount() {
        return map.size();
    }
}

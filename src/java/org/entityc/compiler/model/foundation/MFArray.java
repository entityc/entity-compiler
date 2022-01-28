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

import java.util.ArrayList;
import java.util.List;

@ModelClass(type = ModelClassType.FOUNDATION,
    description = "This class represents a ordered array of objects. "
                  + "To create an empty array you can use: `$[let myList = @[]@]` then can use these methods on that "
                  + "variable (`myList` in this example) to access the array functionality that is exposed here.")
public class MFArray extends MFObject {

    private final ArrayList array = new ArrayList();

    public MFArray() {
        super();
    }

    public MFArray(List list) {
        super();
        array.addAll(list);
    }

    @ModelMethod(description = "Returns all the values of the array. When iterating this array with a `foreach` you do "
                               + "**not** need to use this method.")
    public ArrayList getValues() {
        return array;
    }

    @ModelMethod(description =
        "Allows you to add an object to this array. The `do` template instruction can be used to do "
        + "this (e.g., `$[do array.add(obj)]`. This method returns the array itself so you "
        + "can chain other array methods after this one.")
    public MFArray add(
        @ModelMethodParameter(description = "The object to add to the array.")
            Object o) {
        if (!array.add(o)) {
            ECLog.logFatal("Unable to add to array: " + o.toString());
        }
        return this;
    }

    @ModelMethod(description = "Allows you to make a copy of this array. Since it returns the new array "
                               + "you can chain other array methods after this one.")
    public MFArray copy() {
        MFArray newArray = new MFArray();
        newArray.array.addAll(this.array);
        return newArray;
    }

    @ModelMethod(description =
        "Allows you to add all elements of another array to this array. The `do` template instruction "
        + "can be used to do  this (e.g., `$[do array.addAll(otherArray)]`. This method returns the "
        + "array itself so you can chain other array methods after this one.")
    public MFArray addAll(
        @ModelMethodParameter(description = "The other array to add to this array.")
            MFArray otherArray) {
        array.addAll(otherArray.array);
        return this;
    }

    @ModelMethod(description = "Allows you to remove a specified object from the array. This method returns the "
                               + "array itself so you can chain other array methods after this one.")
    public MFArray remove(
        @ModelMethodParameter(description = "The object to remove from this array.")
            Object o) {
        if (!array.remove(o)) {
            ECLog.logFatal("Unable to remove from array: " + o.toString());
        }
        return this;
    }

    @ModelMethod(description = "This method removes all items in the array.")
    public MFArray clear() {
        array.clear();
        return this;
    }

    @ModelMethod(description = "Returns the last item in the array.")
    public Object last() {
        if (array.size() == 0) {
            return null;
        }
        return array.get(array.size() - 1);
    }

    @ModelMethod(description = "Returns the first item in the array.")
    public Object first() {
        if (array.size() == 0) {
            return null;
        }
        return array.get(0);
    }

    @ModelMethod(description = "Returns the specified item by its index into the array.")
    public Object get(
        @ModelMethodParameter(description = "The index into the array that points to the item to be returned.")
            Long index) {
        return array.get(index.intValue());
    }

    @ModelMethod(description =
        "Returns the index into the array associated with the specified object. If the object is "
        + "not found, a -1 is returned.")
    public int indexOf(
        @ModelMethodParameter(description = "The object of which to locate its index.")
            Object o) {
        return array.indexOf(o);
    }

    @ModelMethod(description = "Indicates if the array is empty (no items).")
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @ModelMethod(description = "Indicates if the array contains the specified object.")
    public boolean contains(
        @ModelMethodParameter(description = "The object of which to determine its presence in the array.")
            Object object) {
        return array.contains(object);
    }

    @ModelMethod(description = "Returns the number of items in the array.")
    public int getCount() {
        return array.size();
    }
}

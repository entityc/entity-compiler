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

import java.util.Set;

@ModelClass(type = ModelClassType.FOUNDATION,
    description = "This class represents a unique set of objects. You cannot create an object of this class "
                  + "in template code, however, some classes have methods that will return such an object. Since "
                  + "this class does not offer methods to manipulate the object, it should be considered immutable. "
                  + "You can iterate through the items in a set using the `foreach` instruction. For example: "
                  + "`$[foreach item in mySet] ... $[/foreach]` would allow you to process each item(object) in the set.")
public class MFSet extends MFObject {

    private final Set set;

    public MFSet(Set set) {
        super();
        this.set = set;
    }

    public Set getValues() {
        return set;
    }

    @ModelMethod(description = "Indicates if the set is empty (no items).")
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @ModelMethod(description = "Indicates if the set contains the specified object.")
    public boolean contains(
        @ModelMethodParameter(description = "The object of which to determine its presence in the set.")
            Object object) {
        return set.contains(object);
    }

    @ModelMethod(description = "Returns the number of items in the set.")
    public int getCount() {
        return set.size();
    }
}

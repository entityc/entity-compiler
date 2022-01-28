/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.filter;

import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FTFilterManager {

    private static FTFilterManager instance = null;

    private final Map<String, FTFilter> filterMap      = new HashMap<>();
    private final List<FTFilter>        filtersInOrder = new ArrayList<>();

    private FTFilterManager() {
        Reflections                    reflections = new Reflections(FTFilter.class.getPackage().getName());
        Set<Class<? extends FTFilter>> classes     = reflections.getSubTypesOf(FTFilter.class);
        for (Class<? extends FTFilter> filterClass : classes) {
            try {
                FTFilter instance = filterClass.newInstance();
                if (filterMap.containsKey(instance.getName())) {
                    continue; // duplicate
                }
                filterMap.put(instance.getName(), instance);
                filtersInOrder.add(instance);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        filtersInOrder.sort(new Comparator<FTFilter>() {
            @Override
            public int compare(FTFilter o1, FTFilter o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    public static FTFilterManager getInstance() {
        if (instance == null) {
            instance = new FTFilterManager();
        }
        return instance;
    }

    public FTFilter getFilterByName(String name) {
        return filterMap.get(name);
    }

    public List<FTFilter> getFilters() {
        return filtersInOrder;
    }
}

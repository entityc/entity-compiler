/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.entity;

import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.doc.annotation.ModelMethod;
import org.entityc.compiler.doc.annotation.ModelMethodCategory;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.visitor.MTVisitor;

import java.util.HashMap;
import java.util.Map;

@ModelClass(type = ModelClassType.ENTITY,
    description =
        "Represents a unit of measure used in the naming and/or source of info of an attribute. When declaring attributes "
        + "that is associated with some unit of measure, its best to assign a unit to the attribute. Not only does it help other people "
        + "working with this attribute, it can be used by templates to automatically add code to convert "
        + "from one unit to another as data flows from one domain to another. Units are easily defined and imported from a central "
        + "repository. Units can be based on other units with an associated multiplier.")
public class MTUnit extends MTNode {

    private static final Map<String, MTUnit> units = new HashMap<>();
    private final        String              name;
    private final        String              abbreviation;
    private              MTUnit              baseUnit;
    private              String              baseUnitName;
    private              double              multiplier;

    private MTUnit(String name, String abbreviation) {
        this.name         = name;
        this.abbreviation = abbreviation;
    }

    private MTUnit(String name, String abbreviation, String baseUnitName, double multiplier) {
        this.name         = name;
        this.abbreviation = abbreviation;
        this.baseUnitName = baseUnitName;
        this.multiplier   = multiplier;
    }

    public static void AddUnit(String name, String abbreviation) {
        units.put(name, new MTUnit(name, abbreviation));
    }

    public static void AddUnit(String name, String abbreviation, String baseUnitName, double multiplier) {
        units.put(name, new MTUnit(name, abbreviation, baseUnitName, multiplier));
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "When a unit is based from another unit, it should have a multiplier. A multiplier is defined as "
                      + "the number of items in this unit per the number of items in base unit. For example, if this "
                      + "unit is millimeters and the base unit is meters, then the multiplier would be 1000. This method "
                      + "returns the units multiplier. ")
    public double getMultiplier() {
        return multiplier;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "When a unit is based from another unit, this returns that base unit. ")
    public MTUnit getBaseUnit() {
        if (baseUnitName != null && baseUnit == null) {
            baseUnit = UnitWithName(baseUnitName);
        }
        return baseUnit;
    }

    public static MTUnit UnitWithName(String name) {
        return units.get(name);
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Indicates whether this unit extends from a base unit. ")
    public boolean hasBaseUnit() {
        return baseUnitName != null;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns the name of the unit. ")
    public String getName() {
        return name;
    }

    @ModelMethod(category = ModelMethodCategory.ATTRIBUTE,
        description = "Returns an abbreviation of this unit. ")
    public String getAbbreviation() {
        return abbreviation;
    }

    public String toString() {
        return name;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }
}

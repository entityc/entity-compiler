/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.formatter;

import java.util.HashMap;
import java.util.Map;

public class ElementFormatPreference implements FormatPreference {

    private final ConfigurableElement              configurableElement;
    private       boolean                          spaceBefore;
    private       boolean                          spaceAfter;
    private       Map<String, ConfigurableElement> alignedToElementByScope = new HashMap<>();
    private       ConfigurableElement              ifBetweenElement; // if this element is between them

    public ElementFormatPreference(ConfigurableElement configurableElement) {
        this.configurableElement = configurableElement;
    }

    public ConfigurableElement getIfBetweenElement() {
        return ifBetweenElement;
    }

    public void setIfBetweenElement(ConfigurableElement ifBetweenElement) {
        this.ifBetweenElement = ifBetweenElement;
    }

    public ConfigurableElement getAlignedToElement(ConfigurableElementScope scope) {
        return alignedToElementByScope.get(scope.name());
    }

    public boolean hasAlignedElement(ConfigurableElement alignedToElement, ConfigurableElementScope scope) {
        if (!alignedToElementByScope.containsKey(scope.name())) {
            return false;
        }
        return alignedToElementByScope.get(scope.name()) == alignedToElement;
    }

    public void setAlignedToElement(ConfigurableElement alignedToElement, ConfigurableElementScope scope) {
        this.alignedToElementByScope.put(scope.name(), alignedToElement);
    }

    public ConfigurableElement getConfigurableElement() {
        return configurableElement;
    }

    public boolean isSpaceBefore() {
        return spaceBefore;
    }

    public void setSpaceBefore(boolean spaceBefore) {
        this.spaceBefore = spaceBefore;
    }

    public boolean isSpaceAfter() {
        return spaceAfter;
    }

    public void setSpaceAfter(boolean spaceAfter) {
        this.spaceAfter = spaceAfter;
    }

    @Override
    public String getName() {
        return getPreferenceName(configurableElement);
    }

    public static String getPreferenceName(ConfigurableElement element) {
        return GetPreferencePrefix() + "." + element.name();
    }

    public static String GetPreferencePrefix() {
        return "Element";
    }
}
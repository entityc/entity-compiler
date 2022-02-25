/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.formatter;

import org.entityc.compiler.model.MTCodeFormat;
import org.entityc.compiler.transform.template.TemplateInstructions;
import org.entityc.compiler.util.ECLog;

import javax.json.JsonObject;
import java.util.HashMap;
import java.util.Map;

public class FormatPreferenceManager {

    private final Map<String, FormatPreference>              formatPreferenceMap        = new HashMap<>();
    private final Map<String, Map<String, FormatPreference>> alignToFormatPreferenceMap = new HashMap<>();
    private final Map<String, Map<String, FormatPreference>> alignIfFormatPreferenceMap = new HashMap<>();
    private final MTCodeFormat                               codeFormat;

    public FormatPreferenceManager(MTCodeFormat codeFormat) {
        this.codeFormat = codeFormat;
        loadCodeFormat();
    }

    private void loadCodeFormat() {
        if (this.codeFormat == null) {
            return;
        }
        JsonObject settings = this.codeFormat.getSettings();
        for (String settingName : settings.keySet()) {
            String[] parts = settingName.split("\\.");
            if (parts[0].equals(ElementFormatPreference.GetPreferencePrefix())) {
                loadElementCodeFormatSetting(settings, settingName);
            } else if (parts[0].equals(InstructionFormatPreference.GetPreferencePrefix())) {
                loadInstructionCodeFormatSetting(settings, settingName);
            } else {
                ECLog.logFatal("Settings that start with \"" + parts[0] + "\" are not supported.");
            }
        }
    }

    private void loadElementCodeFormatSetting(JsonObject settings, String settingName) {
        String[] parts = settingName.split("\\.");
        if (parts.length < 3) {
            ECLog.logFatal("Element format setting name unknown: " + settingName);
        }

        String              configurableElementName = parts[1];
        ConfigurableElement element                 = ConfigurableElement.findByName(configurableElementName);
        if (element == null) {
            ECLog.logFatal("Format element \"" + configurableElementName + "\" is not supported.");
            return; // removes warning
        }
        String subSettingName = parts[2];
        ElementFormatPreference preference = (ElementFormatPreference) formatPreferenceMap.get(
                ElementFormatPreference.getPreferenceName(element));
        boolean needToCreateNewPreference = preference == null;
        if (needToCreateNewPreference) {
            preference = new ElementFormatPreference(element);
        }
        switch (subSettingName) {
            case "spaceBefore":
                if (!element.optionalSpaceBefore) {
                    ECLog.logFatal(
                            "On formatting element \"" + configurableElementName + "\", setting \"" + subSettingName
                            + "\" cannot be set.");
                }
                preference.setSpaceBefore(settings.getBoolean(settingName));
                break;
            case "spaceAfter":
                if (!element.optionalSpaceAfter) {
                    ECLog.logFatal(
                            "On formatting element \"" + configurableElementName + "\", setting \"" + subSettingName
                            + "\" cannot be set.");
                }
                preference.setSpaceAfter(settings.getBoolean(settingName));
                break;
            case "alignTo":
                if (parts.length < 4) {
                    ECLog.logFatal(
                            "Invalid \"alignTo\" setting, should be <element>.alignTo.<element>, where <element> is a supported language element.");
                }
                String toElementName = parts[3];
                ConfigurableElement toElement = ConfigurableElement.findByName(toElementName);
                if (toElement == null) {
                    ECLog.logFatal("Format element \"" + toElementName + "\" is not supported.");
                }
                if (!ConfigurableElementAlignment.isSupported(element, toElement)) {
                    ECLog.logFatal("Alignment of format element \"" + configurableElementName + "\" to element \""
                                   + toElementName + "\" is not supported.");
                }
                ConfigurableElementAlignment alignment = ConfigurableElementAlignment.alignmentForFromElementToElement(
                        element, toElement);
                for (ConfigurableElementScope scope : alignment.scopes) {
                    preference.setAlignedToElement(toElement, scope);
                }
                if (parts.length == 6 && parts[4].equals("if")) {
                    String              ifElementName = parts[5];
                    ConfigurableElement ifElement     = ConfigurableElement.findByName(ifElementName);
                    if (ifElement == null) {
                        ECLog.logFatal(
                                "Invalid element \"" + ifElementName + "\" to use as a condition for alignment.");
                    }
                    preference.setIfBetweenElement(ifElement);
                    alignIfFormatPreferenceMap.put(ifElementName, new HashMap<>());
                }
                Map<String, FormatPreference> fromElementMap = alignToFormatPreferenceMap.get(toElementName);
                if (fromElementMap == null) {
                    fromElementMap = new HashMap<>();
                }
                for (ConfigurableElementScope scope : alignment.scopes) {
                    fromElementMap.put(configurableElementName, preference);
                }
                alignToFormatPreferenceMap.put(toElementName, fromElementMap);
                break;
            default:
                ECLog.logFatal("On formatting element \"" + configurableElementName + "\", setting \"" + subSettingName
                               + "\" not supported.");
                break;
        }
        if (needToCreateNewPreference) {
            formatPreferenceMap.put(preference.getName(), preference);
        }
    }

    private void loadInstructionCodeFormatSetting(JsonObject settings, String settingName) {
        String[] parts = settingName.split("\\.");
        if (parts.length < 3) {
            ECLog.logFatal("Instruction format setting name unknown: " + settingName);
        }

        String instructionName = parts[1];

        if (!TemplateInstructions.isValidInstructionName(instructionName)) {
            ECLog.logFatal("Instruction format setting specifies an unsupported instruction: " + instructionName);
        }
        String subSettingName = parts[2];
        InstructionFormatPreference preference = (InstructionFormatPreference) formatPreferenceMap.get(
                InstructionFormatPreference.getPreferenceName(instructionName));
        boolean needToCreateNewPreference = preference == null;
        if (needToCreateNewPreference) {
            preference = new InstructionFormatPreference(instructionName);
        }
        if (subSettingName.equals("suppressIndent")) {
            preference.setSuppressIndent(settings.getBoolean(settingName));
        } else {
            ECLog.logFatal("On formatting instruction \"" + instructionName + "\", setting \"" + subSettingName
                           + "\" not supported.");
        }
        if (needToCreateNewPreference) {
            formatPreferenceMap.put(preference.getName(), preference);
        }
    }

    public boolean hasAlignmentPreference(ConfigurableElementScope scope, ConfigurableElement fromElement, ConfigurableElement toElement) {
        String preferenceName = ElementFormatPreference.getPreferenceName(fromElement);
        if (hasPreferenceByName(preferenceName)) {
            ElementFormatPreference preference = (ElementFormatPreference) getPreferenceByName(preferenceName);
            if (preference.hasAlignedElement(toElement, scope)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPreferenceByName(String name) {
        return formatPreferenceMap.containsKey(name);
    }

    public FormatPreference getPreferenceByName(String name) {
        return formatPreferenceMap.get(name);
    }

    public ElementFormatPreference getAlignmentPreference(ConfigurableElementScope scope, ConfigurableElement fromElement, ConfigurableElement toElement) {
        String preferenceName = ElementFormatPreference.getPreferenceName(fromElement);
        if (hasPreferenceByName(preferenceName)) {
            ElementFormatPreference preference = (ElementFormatPreference) getPreferenceByName(preferenceName);
            if (preference.hasAlignedElement(toElement, scope)) {
                return preference;
            }
        }
        return null;
    }

    public boolean hasAlignmentPreferenceToElement(ConfigurableElementScope scope, ConfigurableElement toElement) {
        boolean showlog = false && toElement == ConfigurableElement.InstructionName;
        if (showlog) {
            ECLog.logInfo("hasAlignmentPreferenceToElement(scope=" + scope.name() + ", toElement=" + toElement.name());
        }
        Map<String, FormatPreference> preferencesMap = alignToFormatPreferenceMap.get(toElement.name());
        if (preferencesMap == null) { // || !preferencesMap.containsKey(toElement.name() + "::" + scope.name())) {
            if (showlog) {
                ECLog.logInfo("----> NO prreference map to element");
            }
            return false;
        }
        FormatPreference preference = preferencesMap.get(toElement.name());
        if (preference != null && preference instanceof ElementFormatPreference) {
            ElementFormatPreference elementFormatPreference = (ElementFormatPreference) preference;
            if (elementFormatPreference.hasAlignedElement(toElement, scope)) {
                if (showlog) {
                    ECLog.logInfo("----> YES - allowed scope");
                }
                return true;
            }
        } else {
            if (showlog) {
                ECLog.logInfo("----> YES");
            }
            return true;
        }

        if (showlog) {
            ECLog.logInfo("----> NO");
        }
        return false;
    }

    public boolean hasAlignmentPreferenceIfElement(ConfigurableElement ifElement) {
        return (alignIfFormatPreferenceMap.containsKey(ifElement.name()));
    }
}

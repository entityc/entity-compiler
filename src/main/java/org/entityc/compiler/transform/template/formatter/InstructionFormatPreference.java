/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.formatter;

public class InstructionFormatPreference implements FormatPreference {

    private String  instructionName;
    private boolean suppressIndent;

    public InstructionFormatPreference(String instructionName) {
        this.instructionName = instructionName;
    }

    public boolean isSuppressIndent() {
        return suppressIndent;
    }

    public void setSuppressIndent(boolean suppressIndent) {
        this.suppressIndent = suppressIndent;
    }

    @Override
    public String getName() {
        return getPreferenceName(instructionName);
    }

    public static String getPreferenceName(String instructionName) {
        return GetPreferencePrefix() + "." + instructionName;
    }

    public static String GetPreferencePrefix() {
        return "Instruction";
    }
}

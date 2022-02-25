/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template;

import org.entityc.compiler.model.MTRoot;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import java.util.HashMap;
import java.util.Map;

public class StringTemplateTransform extends TemplateTransform {

    private final String              inputStringValue;
    private final Map<String, Object> initialReadOnlyVariables = new HashMap<>();
    private       String              outputStringValue;

    public StringTemplateTransform(MTRoot root, String stringValue) {
        super("internal", root, null, null);
        this.inputStringValue = stringValue;
    }

    public void addReadOnlyVariables(Map<String, Object> variables) {
        initialReadOnlyVariables.putAll(variables);
    }

    public void addReadOnlyVariable(String name, Object value) {
        initialReadOnlyVariables.put(name, value);
    }

    @Override
    public boolean canStart() {
        return inputStringValue != null;
    }

    @Override
    public void start() {
        load();
        FTTransformSession session = new FTTransformSession(root, null, template);
        session.setDebugMode(false);
        for (String name : initialReadOnlyVariables.keySet()) {
            session.addReadonlyNamedValue(name, initialReadOnlyVariables.get(name));
        }
        template.transform(session);
        outputStringValue = session.getOutputStringValue();
    }

    @Override
    public void load() {
        load(false);
    }

    public void load(boolean suppressImport) {
        CharStream input = CharStreams.fromString(inputStringValue);
        loadFromStream("<string template>", "String", input, suppressImport);
    }

    public String getOutputStringValue() {
        return outputStringValue;
    }
}

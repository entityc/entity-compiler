/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.config;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.util.ECVersion;

import javax.json.JsonObject;
import java.util.HashMap;
import java.util.Map;

public class MTTransform extends MTNode {

    private final String              name;
    private final Map<String, String> namedOutputs = new HashMap<>();
    protected     MTConfiguration     configuration;
    private       String              description;
    private       ECVersion           version;
    private       JsonObject          config;

    public MTTransform(ParserRuleContext ctx, MTConfiguration configuration, String name) {
        super(ctx);
        this.configuration = configuration;
        this.name          = name;
    }

    public JsonObject getConfig() {
        return config;
    }

    public void setConfig(JsonObject config) {
        this.config = config;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public ECVersion getVersion() {
        return version;
    }

    public void setVersion(ECVersion version) {
        this.version = version;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    public String getName() {
        return name;
    }

    public void addNamedOutput(String localName, String outputName) {
        namedOutputs.put(localName, outputName);
    }

    public String getOutputNameByLocalName(String localName) {
        return namedOutputs.get(localName);
    }

    public boolean isTemplate() {
        return this instanceof MTTemplate;
    }
}

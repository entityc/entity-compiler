/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model;

import org.entityc.compiler.model.config.MTConfiguration;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.entity.MTTemplateSupport;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MTRoot extends MTNode implements MTTemplateSupport {

    private final Map<String, MTConfiguration> configurationMap = new HashMap<>();
    private final Map<String, MTCodeFormat>    codeFormatMap    = new HashMap<>();
    private       MTSpace                      space            = null;

    public MTRoot(ParserRuleContext ctx) {
        super(ctx);
    }

    @Override
    public void accept(MTVisitor visitor) {
        space.accept(visitor);
    }

    public MTSpace getSpace() {
        return space;
    }

    public void setSpace(MTSpace space) {
        this.space = space;
    }

    public boolean hasSpace() {
        return space != null;
    }

    public void resolveReferences(boolean distill) {
        // this is where we should check to make sure any references indeed exist in our
        // model and create appropriate links
        for (int pass = 0; pass < 10; pass++) {
            boolean needsAnotherPass = false;

            if (space != null) {
                if (space.resolveReferences(space, pass)) {
                    needsAnotherPass = true;
                }
            }
            if (!needsAnotherPass) {
                break;
            }
        }

        for (int pass = 0; pass < 10; pass++) {
            boolean needsAnotherPass = false;

            for (MTConfiguration configuration : configurationMap.values()) {
                if (configuration.resolveReferences(space, pass)) {
                    needsAnotherPass = true;
                }
            }

            if (!needsAnotherPass) {
                break;
            }
        }
    }

    public List<String> getConfigurationNames() {
        return new ArrayList(configurationMap.keySet());
    }

    public int configurationCount() {
        return configurationMap.size();
    }

    public MTConfiguration getConfiguration(String name) {
        return configurationMap.get(name);
    }

    public void addConfiguration(MTConfiguration configuration) {
        configurationMap.put(configuration.getName(), configuration);
    }

    public List<String> getCodeFormatNames() {
        return new ArrayList<>(codeFormatMap.keySet());
    }

    public int codeFormatCount() {
        return codeFormatMap.size();
    }

    public MTCodeFormat getCodeFormat(String name) {
        return codeFormatMap.get(name);
    }

    public void addCodeFormat(MTCodeFormat codeFormat) {
        codeFormatMap.put(codeFormat.getName(), codeFormat);
    }
}

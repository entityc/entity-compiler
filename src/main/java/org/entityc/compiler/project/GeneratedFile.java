/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.txt file in the project root.
 */

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.txt file in the project root.
 */

package org.entityc.compiler.project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GeneratedFile {

    final private String                 filepath;
    final private List<GenerationOrigin> origins = new ArrayList<>();

    public GeneratedFile(String filepath, String configurationName, String templateUri) {
        this.filepath = filepath;
        this.origins.add(new GenerationOrigin(configurationName, templateUri));
    }

    public GeneratedFile(String filepath) {
        this.filepath = filepath;
    }

    public String getFilepath() {
        return filepath;
    }

    public boolean hasTemplateOrigin(String templateUri) {
        for (GenerationOrigin origin : origins) {
            if (origin.matchesTemplateWithoutTag(templateUri)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasConfigurationOrigin(String configurationName) {
        for (GenerationOrigin origin : origins) {
            if (origin.getConfigurationName().equals(configurationName)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSingleConfigurationOrigin(String configurationName) {
        if (origins.size() > 1) {
            return false;
        }
        for (GenerationOrigin origin : origins) {
            if (origin.getConfigurationName().equals(configurationName)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> getConfigurationNames() {
        HashSet<String> configurationNames = new HashSet<>();
        for (GenerationOrigin origin : origins) {
            configurationNames.add(origin.getConfigurationName());
        }
        return configurationNames;
    }

    public Set<String> getTemplateUris() {
        HashSet<String> templateUris = new HashSet<>();
        for (GenerationOrigin origin : origins) {
            templateUris.add(origin.getTemplateUri());
        }
        return templateUris;
    }

    public GeneratedFile extractForConfiguration(String configurationName, boolean remove) {
        GeneratedFile singleConfigurationGeneratedFile = new GeneratedFile(this.filepath);
        for (GenerationOrigin origin : origins) {
            if (origin.getConfigurationName().equals(configurationName)) {
                singleConfigurationGeneratedFile.origins.add(origin);
            }
        }
        if (remove) {
            origins.removeAll(singleConfigurationGeneratedFile.origins);
        }
        return singleConfigurationGeneratedFile;
    }
}

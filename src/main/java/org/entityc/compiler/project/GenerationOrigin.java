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

public class GenerationOrigin {

    private String configurationName;
    private String templateUri;

    public GenerationOrigin(String configurationName, String templateUri) {
        this.configurationName = configurationName;
        this.templateUri       = templateUri;
    }

    public String getConfigurationName() {
        return configurationName;
    }

    public String getTemplateUri() {
        return templateUri;
    }

    public boolean matchesTemplateWithoutTag(String otherTemplateUri) {
        return stripVersion(templateUri).equals(stripVersion(otherTemplateUri));
    }

    public String stripVersion(String uri) {
        int lastColon = uri.lastIndexOf(':');
        return uri.substring(0, lastColon) + uri.substring(
                uri.indexOf('/', lastColon) + 1);
    }
}

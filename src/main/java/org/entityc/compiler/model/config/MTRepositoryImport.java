/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.config;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.doc.annotation.ModelMethod;
import org.entityc.compiler.doc.annotation.ModelMethodCategory;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.visitor.MTVisitor;

@ModelClass(type = ModelClassType.CONFIGURATION,
    description = "Contains configuration for importing files from a repository.")
public class MTRepositoryImport extends MTNode {

    private String  filename;
    private String  repositoryName;
    private boolean includeOnly;

    public MTRepositoryImport(ParserRuleContext ctx, boolean includeOnly) {
        super(ctx);
        this.includeOnly = includeOnly;
    }

    @ModelMethod(category = ModelMethodCategory.CONFIGURATION,
        description =
            "Indicates whether this import should only use the file as like a header file or where everything in "
            + "the imported source file is essentially declared as extern. This would be used in the case of "
            + "entities where you don't intend to implement them in code directly but need to "
            + "know how they are defined so code knows how to interface with them in some way.")
    public boolean isIncludeOnly() {
        return includeOnly;
    }

    public void setIncludeOnly(boolean includeOnly) {
        this.includeOnly = includeOnly;
    }

    @ModelMethod(category = ModelMethodCategory.CONFIGURATION, description = "Returns the filename of this import.")
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @ModelMethod(category = ModelMethodCategory.CONFIGURATION,
        description = "Returns an identifer which is basically the *repository name*`.`*filename*.")
    public String getIdentifier() {
        return repositoryName + "." + filename;
    }

    @ModelMethod(category = ModelMethodCategory.CONFIGURATION,
        description = "Returns the declared name of the repository object that this import operation will use as "
                      + "its source repository.")
    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }
}

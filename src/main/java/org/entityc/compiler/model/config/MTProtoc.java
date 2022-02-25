/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.config;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.MTReferenceResolution;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Runs protobuf compiler
 */
public class MTProtoc extends MTNode implements MTReferenceResolution {

    private final MTConfiguration          configuration;
    private final List<MTSpaceImport>      imports           = new ArrayList<>();
    private final List<MTSpaceInclude>     includes          = new ArrayList<>();
    private final List<MTRepositoryImport> repositoryImports = new ArrayList<>();
    private       String                   languageName;
    private       MTDirectory              sourceOutput;
    private       MTDirectory              headerOutput;
    private       String                   sourceOutputName;
    private       String                   headerOutputName;

    public MTProtoc(ParserRuleContext ctx, MTConfiguration configuration) {
        super(ctx);
        this.configuration = configuration;
    }

    public List<MTSpaceInclude> getIncludes() {
        return includes;
    }

    public MTConfiguration getConfiguration() {
        return configuration;
    }

    public String getSourceOutputName() {
        return sourceOutputName;
    }

    public void setSourceOutputName(String sourceOutputName) {
        this.sourceOutputName = sourceOutputName;
    }

    public String getHeaderOutputName() {
        return headerOutputName;
    }

    public void setHeaderOutputName(String headerOutputName) {
        this.headerOutputName = headerOutputName;
    }

    public List<MTRepositoryImport> getRepositoryImports() {
        return repositoryImports;
    }

    public void addImport(MTSpaceImport modelImport) {
        imports.add(modelImport);
        repositoryImports.addAll(modelImport.getImports());
    }

    public void addInclude(MTSpaceInclude modelInclude) {
        includes.add(modelInclude);
        repositoryImports.addAll(modelInclude.getImports());
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public MTDirectory getSourceOutput() {
        return sourceOutput;
    }

    public void setSourceOutput(MTDirectory sourceOutput) {
        this.sourceOutput = sourceOutput;
    }

    public MTDirectory getHeaderOutput() {
        return headerOutput;
    }

    public void setHeaderOutput(MTDirectory headerOutput) {
        this.headerOutput = headerOutput;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        boolean hasError = false;

        //ECLog.logInfo("Protoc Resolve References, pass " + pass);
        if (sourceOutputName == null) {
            ECLog.logError(this, "Source output not specified.");
            hasError = true;
        }
        if (languageName.equals("cpp") && headerOutputName == null) {
            ECLog.logError(this, "Header output not specified.");
            hasError = true;
        }
        if (hasError) {
            System.exit(1);
        }
        if (pass < 3) {
            this.sourceOutput = configuration.getOutputByName(sourceOutputName);
            if (headerOutputName != null) {
                this.headerOutput = configuration.getOutputByName(headerOutputName);
            }
            return true;
        }
        if (this.sourceOutput == null) {
            ECLog.logError(this, "Could not find source output named: " + sourceOutputName);
            hasError = true;
        }
        if (languageName.equals("cpp") && this.headerOutput == null) {
            ECLog.logError(this, "Could not find header output named: " + headerOutputName);
            hasError = true;
        }
        if (hasError) {
            System.exit(1);
        }
        return false;
    }
}

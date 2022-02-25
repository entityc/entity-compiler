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
import org.entityc.compiler.doc.annotation.ModelMethodParameter;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.MTReferenceResolution;
import org.entityc.compiler.model.MTRoot;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.transform.template.FileTemplateTransform;
import org.entityc.compiler.transform.template.tree.FTTemplate;
import org.entityc.compiler.transform.template.tree.FTTransformSession;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ModelClass(type = ModelClassType.CONFIGURATION,
    description = "This class represents a compiler configuration specific for the building of your application.")
public class MTConfiguration extends MTNode implements MTReferenceResolution {

    private final String                   name;
    private final Map<String, MTDirectory> outputMap         = new HashMap<>();
    private final Map<String, MTTransform> namedTransformMap = new HashMap<>();
    private final List<MTTransform>        transformsInOrder = new ArrayList<>();
    private final List<MTProtoc>           protocs           = new ArrayList<>();
    private final MTRoot                   root;
    private       MTSpace                  space;
    private       JsonObject               defaultTemplateConfig;

    public MTConfiguration(ParserRuleContext ctx, MTRoot root, String name) {
        super(ctx);
        this.root = root;
        this.name = name;
    }

    public JsonObject getDefaultTemplateConfig() {
        return defaultTemplateConfig;
    }

    public void setDefaultTemplateConfig(JsonObject defaultTemplateConfig) {
        this.defaultTemplateConfig = defaultTemplateConfig;
    }

    public List<MTProtoc> getProtocs() {
        return protocs;
    }

    public void addOutput(MTDirectory output) {
        outputMap.put(output.getName(), output);
    }

    public void addTemplate(MTTemplate template) {
        addTransform(template);
    }

    public void addTransform(MTTransform transform) {
        transformsInOrder.add(transform);
        namedTransformMap.put(transform.getName(), transform);
    }

    @ModelMethod(category = ModelMethodCategory.CONFIGURATION,
        description = "With the name of an output, this returns the directory object representing the output that a template can be configured to use.")
    public MTDirectory getOutputByName(
        @ModelMethodParameter(description =
            "The name of the output as it is defined inside the `config {}` block of your "
            + "model.")
            String name) {
        return outputMap.get(name);
    }

    public MTTransform getTransformByName(String name) {
        return namedTransformMap.get(name);
    }

    public List<MTTransform> getTransforms() {
        return transformsInOrder;
    }

    @ModelMethod(category = ModelMethodCategory.CONFIGURATION,
        description = "Returns all the templates that have been declared in the `config {}` block of your model.")
    public List<MTTemplate> getTemplates() {
        ArrayList<MTTemplate> templates = new ArrayList<>();
        for (MTTransform transform : transformsInOrder) {
            if (transform.isTemplate()) {
                templates.add((MTTemplate) transform);
            }
        }
        return templates;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @ModelMethod(category = ModelMethodCategory.CONFIGURATION,
        description = "Returns the name of this configuration.")
    public String getName() {
        return name;
    }

    @ModelMethod(category = ModelMethodCategory.CONFIGURATION,
        description = "Returns the top space of your application.")
    public MTSpace getSpace() {
        return space;
    }

    public void addProtoc(MTProtoc protoc) {
        protocs.add(protoc);
    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        boolean needsAnotherPass = false;
        for (MTProtoc protoc : protocs) {
            if (protoc.resolveReferences(space, pass)) {
                needsAnotherPass = true;
            }
        }
        if (this.space == null) {
            this.space = space;
            if (this.space == null) {
                needsAnotherPass = true;
            }
        }
        return needsAnotherPass;
    }

    public FTTemplate parseTemplate(FTTransformSession session, MTFile file) {
        return parseTemplate(session, file, null);
    }

    public FTTemplate parseTemplate(FTTransformSession session, MTFile file, String sourceRepositoryName) {
        try {
            MTTemplate template = new MTTemplate(null, this, file);
            if (sourceRepositoryName != null) {
                MTRepositoryImport repositoryImport = new MTRepositoryImport(null, false);
                repositoryImport.setRepositoryName(sourceRepositoryName);
                template.setRepositoryImport(repositoryImport);
            }
            FileTemplateTransform templateTransform = new FileTemplateTransform(root, name, template, file.getPath());
            FTTemplate            ftTemplate        = template.parse(templateTransform, false);
            ftTemplate.extractDocumentationData(this, space); // TODO: do we need to do this every time?
            return ftTemplate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public MTRoot getRoot() {
        return root;
    }
}

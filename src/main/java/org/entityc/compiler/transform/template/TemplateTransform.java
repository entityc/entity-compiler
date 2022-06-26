/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template;

import org.entityc.compiler.EntityCompiler;
import org.entityc.compiler.model.MTRoot;
import org.entityc.compiler.model.domain.MTDApplyTemplate;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.transform.MTBaseTransform;
import org.entityc.compiler.transform.template.tree.FTTemplate;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.util.ECANTLRErrorListener;
import org.entityc.compiler.util.ECLog;
import org.entityc.compiler.util.ECStringUtil;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class TemplateTransform extends MTBaseTransform {

    private final String           sourceRepositoryName;
    protected     FTTemplate       template;
    private       MTDApplyTemplate applyTemplate;
    private       MTEntity         entity;
    private       JsonObject       config;

    public TemplateTransform(String name, MTRoot root, String configurationName, String sourceRepositoryName) {
        super(name, root, configurationName);
        this.sourceRepositoryName = sourceRepositoryName;
    }

    public JsonObject getConfig() {
        return config;
    }

    public void setConfig(JsonObject config) {
        this.config = config;
    }

    public FTTemplate getTemplate() {
        return template;
    }

    public void start(MTDApplyTemplate applyTemplate, MTEntity entity) {
        this.applyTemplate = applyTemplate;
        this.entity = entity;
        start();
    }

    public void start() {
        load();
        run();
    }

    public void load() {

    }

    public void run() {
        if (EntityCompiler.isVerbose()) {
            ECLog.logInfo("  RUNNING template: " + getName());
        }
        FTTransformSession session = new FTTransformSession(root, getConfiguration(), template);
        //ECLog.logInfo("Applying global configuration config: " + session.getConfiguration().getDefaultTemplateConfig());
        applyConfig(session, session.getConfiguration().getDefaultTemplateConfig());
        if (applyTemplate != null) {
            if (applyTemplate.getDomain() != null) {
                template.setDefaultDomainName(applyTemplate.getDomain().getName());
            } else if (applyTemplate.getDomainEntity() != null) {
                template.setDefaultDomainName(applyTemplate.getDomainEntity().getDomain().getName());
            }
            if (applyTemplate.getDomainModule() != null) {
                session.setValue("module", applyTemplate.getDomainModule().getModule());
            }
            session.setValue("entity", entity);
            JsonObject config = applyTemplate.getConfig();
            applyConfig(session, config);
        } else if (config != null) {
            applyConfig(session, config);
        }
        if (EntityCompiler.isVerbose()) {
            session.setDebugMode(true);
        }
        template.transform(session);
        session.close();
    }

    private void applyConfig(FTTransformSession session, JsonObject config) {
        if (config != null) {
            for (String key : config.keySet()) {
                JsonValue value = config.get(key);
                switch (value.getValueType()) {
                    case STRING:
                        session.setValue(key, ECStringUtil.ProcessParserString(value.toString()));
                        break;
                    case NUMBER: {
                        JsonNumber number = (JsonNumber) value;
                        if (number.isIntegral()) {
                            session.setValue(key, number.longValue());
                        } else {
                            session.setValue(key, number.doubleValue());
                        }
                    }
                    break;
                    case TRUE:
                        session.setValue(key, Boolean.valueOf(true));
                        break;
                    case FALSE:
                        session.setValue(key, Boolean.valueOf(false));
                        break;
                }
            }
        }
    }

    protected void loadFromStream(String templateName, String directoryName, String filename, CharStream input, boolean suppressImport) {
        ECANTLRErrorListener errorListener = new ECANTLRErrorListener(filename);
        TemplateLexer        lexer         = new TemplateLexer(input);
        CommonTokenStream    tokens        = new CommonTokenStream(lexer);
        TemplateGrammer      parser        = new TemplateGrammer(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);
        TemplateGrammer.TemplateContext templateContext = parser.template();

        if (templateName == null) {
            ECLog.logFatal("No template name given!");
        }
        if (root == null) {
            ECLog.logFatal("No root node!");
        }
        if (root.getSpace() == null) {
            ECLog.log("No space defined!");
        }
        TemplateASTVisitor visitor = new TemplateASTVisitor(templateName, getConfiguration(), root.getSpace().getRepositoryByName(sourceRepositoryName), suppressImport);
        template = (FTTemplate) visitor.visit(templateContext);
        template.setName(getName());
        template.setDirectoryPath(directoryName);
        template.processIndents(true);
    }
}

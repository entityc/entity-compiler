/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.DocumentationManager;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.doc.annotation.ModelMethod;
import org.entityc.compiler.doc.annotation.ModelMethodCategory;
import org.entityc.compiler.doc.annotation.ModelMethodParameter;
import org.entityc.compiler.model.MTCodeFormat;
import org.entityc.compiler.model.config.MTConfiguration;
import org.entityc.compiler.model.config.MTRepository;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.domain.MTDomain;
import org.entityc.compiler.model.domain.MTNamed;
import org.entityc.compiler.model.language.MTLanguage;
import org.entityc.compiler.transform.template.TemplateDocExtractor;
import org.entityc.compiler.transform.template.TemplatePublishing;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.util.ECVersion;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.InstructionArgument;

/**
 * The top most node of the template
 */
@ModelClass(type = ModelClassType.TEMPLATE,
        description = "Represents an actual template containing code to execute.")
public class FTTemplate extends FTContainerNode implements MTNamed {

    private final  List<FTFunction>         calledExternalFunctions = new ArrayList<>();
    private final  Map<String, FTPublisher> publishers              = new HashMap<>();
    private final  List<FTAuthor>           authors                 = new ArrayList<>();
    private        String                   name;
    private        ECVersion                version;
    private        String                   language;
    private        String                   defaultDomainName;
    private        boolean                  imported;
    private        List<String>             referencedDomainNames   = new ArrayList<>();
    private        Map<String, FTFunction>  functionsByName         = new HashMap<>();
    private        List<String>             referencedTags          = new ArrayList<>();
    private        Set<FTTemplate>          importedTemplates       = new HashSet<>();
    private        boolean              hasOnlyFunctions        = false;
    private        MTRepository         repository;
    private static DocumentationManager documentationManager    = new DocumentationManager();

    public FTTemplate(ParserRuleContext ctx) {
        super(ctx, null);
    }

    @ModelMethod(category = ModelMethodCategory.AUTHOR,
            description = "Returns the authors declared in this template.")
    public List<FTAuthor> getAuthors() {
        return authors;
    }

    @ModelMethod(category = ModelMethodCategory.PUBLISHER,
            description = "Indicates whether this template contains any publishers.")
    public boolean hasPublishers() {
        return !this.publishers.isEmpty();
    }

    @ModelMethod(category = ModelMethodCategory.PUBLISHER,
            description = "Returns the publishers declared in this template.")
    public Collection<FTPublisher> getPublishers() {
        return publishers.values();
    }

    public void addPublisher(FTPublisher publisher) {
        this.publishers.put(publisher.getNamespace().toString(), publisher);
    }

    public void addAuthor(FTAuthor author) {
        this.authors.add(author);
    }

    @ModelMethod(category = ModelMethodCategory.AUTHOR,
            description = "Indicates whether this template contains any authors.")
    public boolean hasAuthors() {
        return !this.authors.isEmpty();
    }

    @ModelMethod(category = ModelMethodCategory.TEMPLATE,
            description = "Returns the templates that this template has imported.")
    public Set<FTTemplate> getImportedTemplates() {
        return importedTemplates;
    }

    @ModelMethod(category = ModelMethodCategory.TEMPLATE,
            description = "Indicates whether this template has imported other templates.")
    public boolean hasImportedTemplates() {
        return importedTemplates.size() > 0;
    }

    @ModelMethod(category = ModelMethodCategory.FUNCTION,
            description = "Indicates whether this template has only function definitions.")
    public boolean hasOnlyFunctions() {
        return hasOnlyFunctions;
    }

    // wait until it is implemented
//    @ModelMethod(category = ModelMethodCategory.FUNCTION,
//        D = "Returns the functions defined outside the template that are called by this template.")
    public List<FTFunction> calledExternalFunctions() {
        return calledExternalFunctions;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
            description = "Returns a list of tag names that are referenced by this template.")
    public List<String> referencedTags() {
        return referencedTags;
    }

    @ModelMethod(category = ModelMethodCategory.TEMPLATE,
            description =
                    "Indicates whether this template object has been imported. If so it is likely in a list returned by "
                    + "`importedTemplates`.")
    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    @ModelMethod(category = ModelMethodCategory.FUNCTION,
            description = "Indicates whether this template has at least one function definition.")
    public boolean hasFunctions() {
        return !functionsByName.isEmpty();
    }

    @ModelMethod(category = ModelMethodCategory.DOMAIN,
            description = "Returns the default domain name declared by this template.")
    public String getDefaultDomainName() {
        return defaultDomainName;
    }

    public void setDefaultDomainName(String defaultDomainName) {
        this.defaultDomainName = defaultDomainName;
    }

    @Override
    public void transform(FTTransformSession session) {
        FTTemplate prevTemplate = session.getTemplate();
        session.setTemplate(this);
        session.addReadonlyNamedValue("__template", this);

        MTDomain   previousDomain   = (MTDomain) session.getValue("domain");
        MTLanguage previousLanguage = (MTLanguage) session.getValue("language");
        if (defaultDomainName != null) {
            MTDomain defaultDomain = session.getSpace().getDomainWithName(defaultDomainName);
            if (defaultDomain != null) {
                session.addReadonlyNamedValue("domain", defaultDomain);
            }
        }
        if (language != null) {
            MTLanguage defaultLanguage = session.getSpace().getLanguageWithName(this.language);
            if (defaultLanguage != null) {
                session.addReadonlyNamedValue("language", defaultLanguage);
            }
        }
        documentationManager.build();
        session.addReadonlyNamedValue("__documentationManager", documentationManager);
        super.transform(session);
        if (previousDomain != null) {
            session.addReadonlyNamedValue("domain", previousDomain);
        }
        if (previousLanguage != null) {
            session.addReadonlyNamedValue("language", previousLanguage);
        }
        if (prevTemplate != null) {
            session.setTemplate(prevTemplate);
            session.addReadonlyNamedValue("__template", prevTemplate);
        }
    }

    @Override
    @ModelMethod(category = ModelMethodCategory.TEMPLATE,
            description = "Returns the name of this template.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ECVersion getVersion() {
        return version;
    }

    public void setVersion(ECVersion version) {
        this.version = version;
    }

    @ModelMethod(category = ModelMethodCategory.LANGUAGE,
            description = "Returns the name of the language defined by this template.")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @ModelMethod(category = ModelMethodCategory.DOMAIN,
            description = "Returns the names of all the domains referenced by this template (such as those by the domain filter).")
    public List<String> getReferencedDomainNames() {
        return referencedDomainNames;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
            description = "Indicates whether this template makes reference to any tags.")
    public boolean hasReferencedTags() {
        return referencedTags.size() > 0;
    }

    @ModelMethod(category = ModelMethodCategory.DOMAIN,
            description = "Indicates whether this template makes reference to any domains.")
    public boolean hasReferencedDomains() {
        return referencedDomainNames.size() > 0;
    }

    public void extractDocumentationData(MTConfiguration configuration, MTSpace space) {
        TemplateDocExtractor extractor = new TemplateDocExtractor(this);
        this.accept(extractor);
        boolean loggedHeader = false;

        this.importedTemplates = new HashSet<>(extractor.getImportedTemplates());
        this.functionsByName   = extractor.getFunctionsByName();

        for (String functionName : extractor.getCallsToExternalFunctions()) {
            if (!this.hasFunctionWithName(functionName)) {
                //calledExternalFunctions.add()
            }
        }

        this.referencedDomainNames = new ArrayList<>(extractor.getReferencedDomainNames());
        this.referencedTags        = new ArrayList<>(extractor.getReferencedTags());
        this.hasOnlyFunctions      = extractor.hasOnlyFunctions();
    }

    @ModelMethod(category = ModelMethodCategory.FUNCTION,
            description = "Indicates whether this template has a function by the specified name.")
    public boolean hasFunctionWithName(
            @ModelMethodParameter(description = "The name of the function to check.")
            String functionName) {
        if (functionsByName.containsKey(functionName)) {
            return true;
        }
        boolean found = false;
        for (int i = getChildren().size() - 1; i >= 0; i--) {
            FTNode child = getChildren().get(i);
            if (child instanceof FTTemplate) {
                found = ((FTTemplate) child).hasFunctionWithName(functionName);
                if (found) {
                    break;
                }
            }
        }
        return found;
    }

    public void formatCodeToFile(File file, MTCodeFormat codeFormat) {
        TemplateFormatController formatController = new TemplateFormatController(codeFormat);
        format(formatController, -1);
        formatController.writeResult(file);
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        int lineNumber = 0;
        if (this.language != null) {
            formatController.addExplicitInstructionStart(0, "language", lineNumber, lineNumber);
            formatController.addInstructionInside(InstructionArgument, this.language, lineNumber);
            formatController.addExplicitInstructionEnd(lineNumber, lineNumber, true);
            //lineNumber++;
        }
        if (this.defaultDomainName != null) {
            formatController.addExplicitInstructionStart(0, "domain", lineNumber, lineNumber);
            formatController.addInstructionInside(InstructionArgument, this.defaultDomainName, lineNumber);
            formatController.addExplicitInstructionEnd(lineNumber, lineNumber, true);
            //lineNumber++;
        }
        if (hasDescription()) {
            for (FTDescription descriptionNode : getMergedDescriptionNodes()) {
                descriptionNode.format(formatController, indentLevel);
            }
        }
        return formatChildren(formatController, indentLevel);
    }

    public void processPublishing(TemplatePublishing publishingHouse, TemplatePublishing.Mode mode) {
        publishingHouse.setTemplate(this);
        publishingHouse.setMode(mode);
        this.accept(publishingHouse);
    }

    @Override
    public void accept(FTVisitor visitor) {
        super.accept(visitor);
    }

    @ModelMethod(category = ModelMethodCategory.FUNCTION,
            description = "Returns the function by the specified name. This function must be declared in this template.")
    public FTFunction getFunctionWithName(
            @ModelMethodParameter(description = "The name of the function to return.")
            String functionName) {
        FTFunction function = functionsByName.get(functionName);
        if (function == null) {
            for (FTNode child : getChildren()) {
                //for (int i = getChildren().size() - 1; i >= 0; i--) {
                //    FTNode child = getChildren().get(i);
                if (child instanceof FTTemplate) {
                    function = ((FTTemplate) child).getFunctionWithName(functionName);
                    if (function != null) {
                        break;
                    }
                }
            }
        }
        return function;
    }

    @ModelMethod(category = ModelMethodCategory.FUNCTION,
            description = "Returns all the functions declared in this template.")
    public Collection<FTFunction> getFunctions() {
        return functionsByName.values();
    }

    @Override
    public int getTemplateLexerSymbol() {
        return GetTemplateLexerSymbol();
    }

    public static int GetTemplateLexerSymbol() {
        return 0;
    }

    public void setRepository(MTRepository repository) {
        this.repository = repository;
    }

    public MTRepository getRepository() {
        return repository;
    }

    public String getUri() {
        if (repository != null) {
            return repository.getUri() + "/" + getName();
        }
        return getName();
    }
}

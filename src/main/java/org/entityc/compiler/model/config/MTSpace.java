/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.config;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.EntityCompiler;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.doc.annotation.ModelMethod;
import org.entityc.compiler.doc.annotation.ModelMethodCategory;
import org.entityc.compiler.doc.annotation.ModelMethodParameter;
import org.entityc.compiler.model.MTModule;
import org.entityc.compiler.model.MTNamespace;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.MTReferenceResolution;
import org.entityc.compiler.model.domain.MTDomain;
import org.entityc.compiler.model.domain.MTNamed;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTEntityTemplate;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTTemplateSupport;
import org.entityc.compiler.model.entity.MTTypedef;
import org.entityc.compiler.model.interop.MTInterface;
import org.entityc.compiler.model.interop.MTInterfaceOperation;
import org.entityc.compiler.model.language.MTLanguage;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.util.ECLog;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ModelClass(type = ModelClassType.CONFIGURATION,
        description = "The space class represents the top of your entity model and also "
                      + "defines information about where you can import entities along with "
                      + "other elements such as units, domains, templates, etc. that are "
                      + "important for application synthesis. Most templates start with the space object to obtains the "
                      + "list of modules or entities in your model.")
public class MTSpace extends MTNode implements MTReferenceResolution, MTTemplateSupport, MTNamed {

    private final Map<String, MTModule>         moduleMap            = new HashMap<>();
    private final Map<String, MTEntity>         entityMap            = new HashMap<>();
    private final Map<String, MTEntityTemplate> entityTemplateMap    = new HashMap<>();
    private final Map<String, MTEntity>         implicitEntityMap    = new HashMap<>();
    private final Map<String, MTDomain>         domainMap            = new HashMap<>();
    private final Map<String, List<MTDomain>>   specializedDomainMap = new HashMap<>();
    private final Map<String, MTEnum>           enumMap              = new HashMap<>();
    private final Map<String, MTLanguage>       languageMap          = new HashMap<>();
    private final Map<String, MTInterface>      interfaceMap         = new HashMap<>();
    private final Map<String, MTTypedef>        typedefMap           = new HashMap<>();
    private final String                        name;
    private       MTRepository                  repositoryThatImportedThisSpace;
    private final List<MTSpaceInclude>          includes             = new ArrayList<>();
    private final List<MTRepositoryImport>      repositoryImports    = new ArrayList<>();
    private final List<String>                  importEnumNames      = new ArrayList<>(); // enums to import even if in an include
    private final List<String>                  importEntityNames    = new ArrayList<>(); // entities to import even if in an include
    private final HashMap<String, MTSpace>      connectedToSpaces    = new HashMap<>();
    private final Map<String, MTRepository>     repositoryMap        = new HashMap<>();
    private       boolean                       includeMode;
    private       MTNamespace                   namespace;
    private       JsonObject                    metadata;

    public MTSpace(ParserRuleContext ctx, String name) {
        super(ctx);
        this.name = name;
    }

    @ModelMethod(category = ModelMethodCategory.DOMAIN,
            description =
                    "Returns the namespace defined for this space. This can serve as the base code namespace for code "
                    + "generated in different domains. To do this a domain would define its namespace relative to this "
                    + "namespace using the appropriate syntax.")
    public MTNamespace getNamespace() {
        return namespace;
    }

    public void setNamespace(MTNamespace namespace) {
        this.namespace = namespace;
    }

    public void setMetadata(JsonObject metadata) {
        this.metadata = metadata;
    }

    @ModelMethod(category = ModelMethodCategory.CONFIGURATION,
            description =
                    "Spaces can define a dictionary of name/value pairs that provide some meta data about the space and "
                    + "basically the application. This method allows you to get a value by its name.")
    public Object getMetadataValue(
            @ModelMethodParameter(description = "The name of the meta data.")
            String name) {
        if (this.metadata != null && metadata.containsKey(name)) {
            return metadata.getString(name);
        }
        return null;
    }

    @ModelMethod(category = ModelMethodCategory.CONFIGURATION,
            description = "Indicates whether this space has a metadata name/value for the specified name.")
    public boolean hasMetadataValue(
            @ModelMethodParameter(description = "The name of the metadata value to return.")
            String name) {
        if (this.metadata != null) {
            return this.metadata.containsKey(name);
        }
        return false;
    }

    public void addRepository(MTRepository repository) {
        repositoryMap.put(repository.getName(), repository);
    }

    @ModelMethod(category = ModelMethodCategory.CONFIGURATION, description = "Returns a repository object by its name.")
    public MTRepository getRepositoryByName(
            @ModelMethodParameter(description = "The name of the repository to return.")
            String name) {
        return repositoryMap.get(name);
    }

    @ModelMethod(category = ModelMethodCategory.CONFIGURATION, description = "Returns the repository object by its name.")
    public MTRepository getRepository(
            @ModelMethodParameter(description = "The name of the repository.")
            String name) {
        return repositoryMap.get(name);
    }

    public void addModule(MTModule module) {
        module.setIncluded(includeMode);
        moduleMap.put(module.getName(), module);
    }

    public void setIncludeMode(boolean includeMode) {
        this.includeMode = includeMode;
    }

    @ModelMethod(category = ModelMethodCategory.CONFIGURATION,
            description = "Returns the names of the enums that have been imported into this space.")
    public List<String> getImportEnumNames() {
        return importEnumNames;
    }

    @ModelMethod(category = ModelMethodCategory.CONFIGURATION,
            description = "Returns the names of the entities that have been imported into this space.")
    public List<String> getImportEntityNames() {
        return importEntityNames;
    }

    public List<MTRepositoryImport> getRepositoryImports() {
        return repositoryImports;
    }

    public void addImport(MTSpaceImport mtSpaceImport) {
        repositoryImports.addAll(mtSpaceImport.getImports());
    }

    public void addInclude(MTSpaceInclude mtSpaceInclude) {
        includes.add(mtSpaceInclude);
        repositoryImports.addAll(mtSpaceInclude.getImports());
        importEnumNames.addAll(mtSpaceInclude.getImportEnumNames());
        importEntityNames.addAll(mtSpaceInclude.getImportEntityNames());
    }

    public void addConnectedToSpace(MTSpace space) {
        if (EntityCompiler.isVerbose()) {
            ECLog.logInfo(getName() + " > ADDING CONNECTED TO SPACE: " + space.getName());
        }
        this.connectedToSpaces.put(space.getName(), space);
    }

    @Override
    public String getName() {
        return name;
    }

    public final Collection<MTRepository> getRepositories() {
        return repositoryMap.values();
    }

    public List<MTSpace> getConnectedToSpaces() {
        return new ArrayList<>(this.connectedToSpaces.values());
    }

    public boolean isConnectedToSpace(String name) {
        return this.connectedToSpaces.containsKey(name);
    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        boolean needsAnotherPass = false;
        for (MTSpace connectedToSpace : connectedToSpaces.values()) {
            if (connectedToSpace.resolveReferences(this, pass)) {
                needsAnotherPass = true;
            }
        }
        for (MTModule module : getModules()) {
            if (module.resolveReferences(this, pass)) {
                needsAnotherPass = true;
            }
        }
        for (MTEntity entity : getEntities()) {
            if (entity.getModule() == null) {
                if (entity.resolveReferences(this, pass)) {
                    needsAnotherPass = true;
                }
            }
        }

        // Merge specialized domains to their base domain
        for (String domainName : specializedDomainMap.keySet()) {
            List<MTDomain> specializedDomains = specializedDomainMap.get(domainName);
            MTDomain       baseDomain         = domainMap.get(domainName);
            if (baseDomain == null) {
                ECLog.logFatal(
                        "Unable to find domain named \"" + domainName + "\" for specialization ("
                        + specializedDomains.get(
                                0).getSpecializedAsNames().get(0) + ") in space: " + this.getName());
            }
            for (MTDomain specializedDomain : specializedDomains) {
                baseDomain.mergeInSpecializedDomain(specializedDomain);
            }
        }
        specializedDomainMap.clear();

        for (MTDomain domain : domainMap.values()) {
            //ECLog.logInfo("RESOLVING references for domain: " + domain.getName());
            if (domain.resolveReferences(this, pass)) {
                needsAnotherPass = true;
            }
        }

        return needsAnotherPass;
    }

    public MTSpace getConnectedToSpace(String name) {
        return this.connectedToSpaces.get(name);
    }

    @ModelMethod(category = ModelMethodCategory.MODULE,
            description = "Returns all modules that have been imported into this space.")
    public List<MTModule> getModules() {
        return new ArrayList<>(moduleMap.values());
    }

    public void addEntity(MTEntity entity) {
        String   entityName     = entity.getName();
        MTEntity existingEntity = entityMap.get(entityName);
        if (existingEntity == null || existingEntity.isExtern()) {
            boolean shouldSetAsInclude = includeMode && !importEntityNames.contains(entityName);
            if (shouldSetAsInclude) {
                entity.setIncluded(shouldSetAsInclude);
            }
            if (entity instanceof MTEntityTemplate) {
                addEntityTemplate((MTEntityTemplate) entity);
                return;
            }
            //ECLog.logInfo(getName() + " > Adding entity: " + entity.getName() + (entity.isIncluded() ? " INCLUDED" : ""));
            entity.setSpace(this);
            entityMap.put(entityName, entity);
        }
    }

    public void addEntityTemplate(MTEntityTemplate entityTemplate) {
        this.entityTemplateMap.put(entityTemplate.getName(), entityTemplate);
    }

    public void addImplicitEntity(MTEntity entity) {
        //ECLog.logInfo("ADDED IMPLICIT ENTITY: " + entity.getName());
        implicitEntityMap.put(entity.getName(), entity);
    }

    public void addDomain(MTDomain domain) {
        if (domain.getSpecializedAsNames().size() > 0) {
            List<MTDomain> specializedDomains = specializedDomainMap.get(domain.getName());
            if (specializedDomains == null) {
                specializedDomains = new ArrayList<>();
            }
            specializedDomains.add(domain);
            specializedDomainMap.put(domain.getName(), specializedDomains);
        } else {
            domainMap.put(domain.getName(), domain);
        }
    }

    public void addEnum(MTEnum mtEnum) {
        String enumName     = mtEnum.getName();
        MTEnum existingEnum = enumMap.get(enumName);
        if (existingEnum == null || existingEnum.isExtern()) {
            boolean shouldSetAsInclude = includeMode && !importEnumNames.contains(enumName);
            mtEnum.setIncluded(shouldSetAsInclude);
            enumMap.put(mtEnum.getName(), mtEnum);
        }
    }

    public void addLanguage(MTLanguage language) {
        languageMap.put(language.getName(), language);
    }

    public void accept(MTVisitor visitor) {
        for (MTEnum mtEnum : enumMap.values()) {
            visitor.visit(mtEnum);
        }
        for (MTEntity entity : entityMap.values()) {
            visitor.visit(entity);
        }
        for (MTEntity entity : implicitEntityMap.values()) {
            visitor.visit(entity);
        }
        for (MTSpace space : connectedToSpaces.values()) {
            visitor.visit(space);
        }
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY,
            description = "Returns an entity template by its name.")
    public MTEntityTemplate getEntityTemplateWithName(
            @ModelMethodParameter(description = "The name of the entity to return.")
            String name) {
        return entityTemplateMap.get(name);
    }

    @ModelMethod(category = ModelMethodCategory.MODULE,
            description = "Returns a module by its name.")
    public MTModule getModuleWithName(
            @ModelMethodParameter(description = "The name of the module to return.")
            String name) {
        return moduleMap.get(name);
    }

    @ModelMethod(category = ModelMethodCategory.ENUM,
            description = "Returns an enum by its name.")
    public MTEnum getEnumWithName(
            @ModelMethodParameter(description = "The name of the enum to return.")
            String name) {
        MTEnum mtEnum = enumMap.get(name);
        if (mtEnum == null) {
            for (MTModule module : moduleMap.values()) {
                mtEnum = module.getEnum(name);
                if (mtEnum != null) {
                    break;
                }
            }
        }
        return mtEnum;
    }

    @ModelMethod(category = ModelMethodCategory.TYPEDEF,
            description = "Returns a typedef by its name.")
    public MTTypedef getTypedefWithName(
            @ModelMethodParameter(description = "The name of the typedef to return.")
            String name) {
        MTTypedef typedef = typedefMap.get(name);
        if (typedef == null) {
            for (MTModule module : moduleMap.values()) {
                typedef = module.getTypedef(name);
                if (typedef != null) {
                    break;
                }
            }
        }
        return typedef;
    }

    @ModelMethod(category = ModelMethodCategory.TYPEDEF,
            description = "Returns the number of typedefs declared in this space.")
    public int getTypedefCount() {
        return typedefMap.size();
    }

    @ModelMethod(category = ModelMethodCategory.LANGUAGE,
            description = "Returns a language object by its name.")
    public MTLanguage getLanguageWithName(
            @ModelMethodParameter(description = "The name of the language to return.")
            String name) {
        return languageMap.get(name);
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY,
            description = "Returns a the total number of entities in this space including implicit entities.")
    public int getEntityCount() {
        return entityMap.size() + implicitEntityMap.size();
    }

    @ModelMethod(category = ModelMethodCategory.ENUM,
            description = "Returns a the number of enums in this space.")
    public int getEnumCount() {
        return enumMap.size();
    }

    @ModelMethod(category = ModelMethodCategory.INTERFACE,
            description = "Returns all the interfaces declared in this space.")
    public List<MTInterface> getInterfaces() {
        return new ArrayList<>(interfaceMap.values());
    }

    @ModelMethod(category = ModelMethodCategory.INTERFACE,
            description = "Returns an interface by its name.")
    public MTInterface getInterface(
            @ModelMethodParameter(description = "The name of the interface to return.")
            String name) {
        return interfaceMap.get(name);
    }

    public void addInterface(MTInterface mtInterface) {
        interfaceMap.put(mtInterface.getName(), mtInterface);
    }

    public void addTypedef(MTTypedef typedef) {
        typedef.setIncluded(includeMode);
        typedefMap.put(typedef.getName(), typedef);
    }

    @ModelMethod(category = ModelMethodCategory.TYPEDEF,
            description = "Returns all the typedefs declared in this space, sorted by name.")
    public List<MTTypedef> getTypedefs() {
        ArrayList<MTTypedef> all = new ArrayList<>(typedefMap.values());
        all.sort(new Comparator<MTTypedef>() {
            @Override
            public int compare(MTTypedef o1, MTTypedef o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return all;
    }

    @ModelMethod(category = ModelMethodCategory.TYPEDEF,
            description = "Given an interface object, this will return an operation by its name.")
    public MTInterfaceOperation getOperationByName(
            @ModelMethodParameter(description = "The interface object that contains the operation.")
            MTInterface mtInterface,
            @ModelMethodParameter(description = "The name of the operation to return.")
            String extendedOperationName) {

        if (mtInterface == null) {
            return null;
        }
        MTInterface intf = interfaceMap.get(mtInterface.getName());

        if (intf != null) {
            MTInterfaceOperation op = intf.getOperation(extendedOperationName);
            return op;
        }
        return null;
    }

    public MTSpace makeMiniSpace(Map<String, String> domainsReferencedAs, Map<String, String> entitiesReferenedAs, MTEntity contextEntity, Map<String, String> viewsReferencedAs) {
        MTSpace miniModel = new MTSpace(null, "_internal");

        for (String domainName : domainsReferencedAs.keySet()) {
            MTDomain domain = getDomainWithName(domainsReferencedAs.get(domainName));
            if (domain == null) {
                ECLog.logFatal("When making mini model, unable to find domain: " + domainsReferencedAs.get(domainName));
            }
            miniModel.domainMap.put(domainName, domain);
        }

        for (String entityName : entitiesReferenedAs.keySet()) {
            MTEntity entity = getEntityWithName(entitiesReferenedAs.get(entityName));
            if (entity == null) {
                ECLog.logFatal("When making mini model, unable to find entity: " + entityName);
            }
            miniModel.entityMap.put(entityName, entity);
        }

        return miniModel;
    }

    public MTDomain getDomainWithName(String name) {
        return domainMap.get(name);
    }

    public MTEntity getEntityWithName(String name) {
        MTEntity entity = entityMap.get(name);
        if (entity == null) {
            entity = implicitEntityMap.get(name);
        }
        return entity;
    }

    // from a template

    @ModelMethod(category = ModelMethodCategory.ENTITY,
            description = "Returns an entity from its name.")
    public MTEntity entity(
            @ModelMethodParameter(description = "The name of the entity to return.")
            String name) {
        return getEntityWithName(name);
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY,
            description = "Returns a domain from its name.")
    public MTDomain domain(
            @ModelMethodParameter(description = "The name of the domain to return.")
            String name) {
        return getDomainWithName(name);
    }

    @ModelMethod(category = ModelMethodCategory.ENTITY,
            description = "Indicates if there is a domain by the specified name.")
    public boolean hasDomain(
            @ModelMethodParameter(description = "The name of the domain in question.")
            String name) {
        return domainMap.containsKey(name);
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
            description = "Returns the first entity found with the specified tag. This should only be used when it is "
                          + "expected that only one is tagged with the tag by nature of the entity. Use `entitiesTagged()` if "
                          + "you are expecting multiple entities that are found.")
    public MTEntity entityTagged(
            @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        for (MTEntity entity : getEntities()) {
            if (entity.hasTag(tag)) {
                return entity;
            }
        }
        return null;
    }

    public List<MTEntity> getEntities() {
        ArrayList<MTEntity> all = new ArrayList<>(entityMap.values());
        all.addAll(implicitEntityMap.values());
        all.sort(new Comparator<MTEntity>() {
            @Override
            public int compare(MTEntity o1, MTEntity o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return all;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
            description = "Indicates if there is at least one entity tagged with the specified tag.")
    public boolean hasEntityTagged(
            @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        for (MTEntity entity : getEntities()) {
            if (entity.hasTag(tag)) {
                return true;
            }
        }
        return false;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
            description = "Returns all entities found with the specified tag.")
    public List<MTEntity> entitiesTagged(
            @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        ArrayList<MTEntity> list = new ArrayList<>();
        for (MTEntity entity : getEntities()) {
            if (entity.hasTag(tag)) {
                list.add(entity);
            }
        }
        return list;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
            description = "Indicates if there is at least one domain tagged with the specified tag.")
    public boolean hasDomainTagged(
            @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        for (MTDomain domain : getDomains()) {
            if (domain.hasTag(tag)) {
                return true;
            }
        }
        return false;
    }

    @ModelMethod(category = ModelMethodCategory.DOMAIN,
            description = "Returns all domains declared in this space.")
    public List<MTDomain> getDomains() {
        return new ArrayList<>(domainMap.values());
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
            description = "Returns all domains that are tagged with a specified tag.")
    public List<MTDomain> domainsTagged(
            @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        ArrayList<MTDomain> list = new ArrayList<>();
        for (MTDomain domain : getDomains()) {
            if (domain.hasTag(tag)) {
                list.add(domain);
            }
        }
        return list;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
            description = "Returns all enums that are tagged with a specified tag.")
    public MTEnum enumTagged(
            @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        for (MTEnum mtEnum : getEnums()) {
            if (mtEnum.hasTag(tag)) {
                return mtEnum;
            }
        }
        return null;
    }

    @ModelMethod(category = ModelMethodCategory.ENUM,
            description = "Returns all enums declared in this space.")
    public List<MTEnum> getEnums() {
        ArrayList<MTEnum> all = new ArrayList<>(enumMap.values());
        all.sort(new Comparator<MTEnum>() {
            @Override
            public int compare(MTEnum o1, MTEnum o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return all;
    }

    @ModelMethod(category = ModelMethodCategory.TAGGING,
            description = "Indicates if there is at least one enum tagged with the specified tag.")
    public boolean hasEnumTagged(
            @ModelMethodParameter(description = "The tag with which to search.")
            String tag) {
        for (MTEnum mtEnum : getEnums()) {
            if (mtEnum.hasTag(tag)) {
                return true;
            }
        }
        return false;
    }

    public void checkValidReferences() {
        for (MTEntity entity : getEntities()) {
            entity.checkValidReferences(this);
        }
        for (MTDomain domain : getDomains()) {
            domain.checkValidReferences(this);
        }
    }

    public MTRepository getRepositoryThatImportedThisSpace() {
        return repositoryThatImportedThisSpace;
    }

    public void setRepositoryThatImportedThisSpace(MTRepository repositoryThatImportedThisSpace) {
        this.repositoryThatImportedThisSpace = repositoryThatImportedThisSpace;
    }
}

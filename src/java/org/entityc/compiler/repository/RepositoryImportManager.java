/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.repository;

import org.entityc.compiler.EntityCompiler;
import org.entityc.compiler.model.config.MTRepository;
import org.entityc.compiler.model.config.MTRepositoryImport;
import org.entityc.compiler.model.config.MTRepositoryType;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.util.ECLog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositoryImportManager {

    private final RepositoryCache                           repositoryCache;
    private final Map<MTRepositoryType, RepositoryImporter> importersByType             = new HashMap<>();
    private       List<RepositoryFile>                      repositoryFilesInOrder      = new ArrayList<>();
    private       Map<String, RepositoryFile>               repositoryFilesByIdentifier = new HashMap<>();

    public RepositoryImportManager(RepositoryCache.CacheStructure structure) {
        this.repositoryCache = new RepositoryCache(structure);
        importersByType.put(MTRepositoryType.GITHUB, new GitHubRepositoryImporter());
        importersByType.put(MTRepositoryType.LOCAL, new LocalRepositoryImporter());
    }

    public RepositoryCache getRepositoryCache() {
        return repositoryCache;
    }

    public List<RepositoryFile> getRepositoryFiles() {
        return new ArrayList<>(repositoryFilesInOrder);
    }

    public RepositoryFile getRepositoryFileByName(String identifier) {
        return repositoryFilesByIdentifier.get(identifier);
    }

    public RepositoryFile importFromRepository(MTSpace space, MTRepositoryImport repositoryImport,
                                               String extension, boolean asInclude) {

        String       repositoryName = repositoryImport.getRepositoryName();
        MTRepository repository     = space.getRepositoryByName(repositoryName);
        if (repository == null) {
            ECLog.logFatal(repositoryImport, "In space \"" + space.getName() + "\" cannot find a repository named \"" + repositoryName + "\".");
        } else if (!repository.isValid()) {
            ECLog.logFatal(repository, "Repository specification is not valid.");
        }

        String name     = repositoryImport.getFilename();
        String filename = name + "." + extension;

        RepositoryFile cacheRepositoryFile = repositoryCache.getRepositoryFile(repository, filename, asInclude);

        if (cacheRepositoryFile.exists()) { // check if it exists in the cache already
            if (EntityCompiler.isVerbose()) {
                ECLog.logInfo("Found file already in cache: " + cacheRepositoryFile.getFilepath());
            }
            addRepositoryFile(cacheRepositoryFile);
            return cacheRepositoryFile;
        }
        RepositoryFile file = importersByType.get(repository.getType()).importFromRepository(repository, repositoryImport, cacheRepositoryFile, extension);
        if (file == null || !file.exists()) {
            ECLog.logFatal("Unable to import file: " + repositoryName + "/" + repositoryImport.getFilename() + "." + extension);
        }
        addRepositoryFile(file);
        return file;
    }

    private void addRepositoryFile(RepositoryFile repositoryFile) {
        repositoryFilesInOrder.add(repositoryFile);
        repositoryFilesByIdentifier.put(repositoryFile.getFilepath(), repositoryFile);
    }

    private String fileIdentifier(String repositoryName, String filename) {
        return repositoryName + "." + filename;
    }

    private String getRepositoryCachePath(MTRepository repository) {
        return repository.getOrganization() + File.separator + repository.getRepoName() + File.separator + repository.getTag();
    }

    public void close() {
        repositoryFilesInOrder = new ArrayList<>();
        repositoryFilesByIdentifier = new HashMap<>();
        for (RepositoryImporter importer : importersByType.values()) {
            importer.close();
        }
    }
}

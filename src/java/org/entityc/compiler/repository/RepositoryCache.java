/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.repository;

import org.entityc.compiler.model.config.MTRepository;
import org.entityc.compiler.model.config.MTRepositoryType;

import java.io.File;
import java.util.UUID;

public class RepositoryCache {

    private final File           baseCacheDirectory;
    private final CacheStructure structure;

    public RepositoryCache(CacheStructure structure) {
        this.structure = structure;
        String baseDirectory = null;
        switch (structure) {
            case UserCache:
                baseDirectory = System.getProperty("user.home") + File.separator + ".ec" + File.separator + "cache";
                break;
            case TempDirectory:
                baseDirectory = "/tmp/" + UUID.randomUUID();
                break;
        }
        baseCacheDirectory = new File(baseDirectory);
        baseCacheDirectory.mkdirs();
    }

    public File getBaseCacheDirectory() {
        return baseCacheDirectory;
    }

    public RepositoryFile getRepositoryFile(MTRepository repository, String filename, boolean asInclude) {
        String outputFilepath = baseCacheDirectory.getAbsolutePath() + File.separator;

        switch (structure) {
            case TempDirectory:
                outputFilepath += filename;
                break;
            case UserCache: {
                String pathPart = repository.getPath() != null && !repository.getPath().isEmpty() ? repository.getPath() + File.separator : "";
                String completeFilename = pathPart + filename;
                if (repository.getType() == MTRepositoryType.LOCAL) {
                    outputFilepath = completeFilename;
                } else {
                    outputFilepath += getRepositoryCachePath(repository) + File.separator + completeFilename;
                }
            }
                break;
        }

        RepositoryFile repositoryFile = new RepositoryFile(repository, outputFilepath, asInclude);
        return repositoryFile;
    }

    private String getRepositoryCachePath(MTRepository repository) {
        return repository.getOrganization() + File.separator + repository.getRepoName() + File.separator + repository.getTag();
    }

    public enum CacheStructure {
        UserCache,
        TempDirectory
    }
}

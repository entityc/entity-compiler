/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.repository;

import org.apache.commons.io.FileUtils;
import org.entityc.compiler.model.config.MTRepository;
import org.entityc.compiler.model.config.MTRepositoryType;
import org.entityc.compiler.util.ECLog;

import java.io.File;
import java.io.IOException;
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

    public RepositoryFile getRepositoryFile(MTRepository repository, String filename, boolean asInclude) {
        String outputFilepath = baseCacheDirectory.getAbsolutePath() + File.separator;

        switch (structure) {
            case TempDirectory:
                outputFilepath += filename;
                break;
            case UserCache: {
                String pathPart         = repository.getPath() != null && !repository.getPath().isEmpty() ?
                                          repository.getPath() + File.separator :
                                          "";
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
        return repository.getOrganization() + File.separator + repository.getRepoName() + File.separator
               + repository.getTag();
    }

    public void validateRepositoryInCache(MTRepository repository) {
        if (structure != CacheStructure.UserCache || repository.getType() == MTRepositoryType.LOCAL || !repository.isValid()) {
            return; // not this one
        }
        String commitSha1Filename = getBaseCacheDirectory().getAbsolutePath() + File.separator + getRepositoryCachePath(
                repository) + File.separator + ".commitSha1";
        File   file               = new File(commitSha1Filename);
        String cacheSHA1          = null;
        if (file.exists()) {
            try {
                cacheSHA1 = FileUtils.readFileToString(file);
            } catch (IOException e) {
                // oh well, assume it is not there
            }
        }
        String repositoryCommitSHA1 = repository.getCommitSHA1();
        if (repositoryCommitSHA1 == null || cacheSHA1 == null || !repository.getCommitSHA1().equals(cacheSHA1)) {
            invalidateCacheDirectory(repository);
            if (repositoryCommitSHA1 != null) {
                try {
                    FileUtils.write(file, repositoryCommitSHA1);
                } catch (IOException e) {
                    ECLog.logWarning("Unable to record repository sha1 in cache directory: " + commitSha1Filename);
                }
            }
        }
    }

    public void invalidateCacheDirectory(MTRepository repository) {
        if (structure != CacheStructure.UserCache) {
            return;
        }
        String fullPath = getBaseCacheDirectory().getAbsolutePath() + File.separator + getRepositoryCachePath(
                repository);
        try {
            FileUtils.cleanDirectory(new File(fullPath));
        } catch (IOException e) {
            ECLog.logWarning("Unable to remove cache directory: " + fullPath);
        }
    }

    public File getBaseCacheDirectory() {
        return baseCacheDirectory;
    }

    public enum CacheStructure {
        UserCache,
        TempDirectory
    }
}

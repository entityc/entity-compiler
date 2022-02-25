/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.repository;

import org.entityc.compiler.EntityCompiler;
import org.entityc.compiler.model.config.MTRepository;
import org.entityc.compiler.model.config.MTRepositoryImport;
import org.entityc.compiler.util.ECLog;
import org.apache.commons.io.IOUtils;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GitHubRepositoryImporter implements RepositoryImporter {

    private GitHub github = null;

    public GitHubRepositoryImporter() {
        super();
    }

    @Override
    public RepositoryFile importFromRepository(MTRepository repository, MTRepositoryImport repositoryImport, RepositoryFile cachedRepositoryFile, String extension) {
        try {
            if (github == null) {
                github = GitHub.connect();
            }
        } catch (IOException e) {
            ECLog.logFatal("Unable to connect to GitHub: " + repository.getOrganization() + "/" + repository.getRepoName() + "\n" + e.getMessage());
        }
        String pathPart = repository.getPath() != null && !repository.getPath().isEmpty() ? repository.getPath() + "/" : "";
        String gitRepoPath = pathPart + repositoryImport.getFilename() + "." + extension;
        GHRepository repo = null;
        try {
            repo        = github.getRepository(repository.getOrganization() + "/" + repository.getRepoName());
        } catch (IOException e) {
            ECLog.logFatal("Unable to connect to repository: " + repository.getOrganization() + "/" + repository.getRepoName());
        }
        try {
            ECLog.logInfo("Downloading file: " + gitRepoPath + " " + repository.getTag());
            GHContent    fileContent = repo.getFileContent(gitRepoPath, repository.getTag());
            if (fileContent == null) {
                ECLog.logFatal(repositoryImport, "Could not find file in repository: " + cachedRepositoryFile.getFilepath() + " with tag " + repository.getTag());
            }
            String outputFilepath = cachedRepositoryFile.getFilepath();
            // in case the filename has sub directories, make sure they are created
            if (outputFilepath.contains(File.separator)) {

                int index = outputFilepath.lastIndexOf(File.separatorChar);
                if (index > 0) {
                    String dirPath = outputFilepath.substring(0, index);
                    if (!EntityCompiler.ensureDirectory(dirPath)) {
                        ECLog.logFatal("Not able to create directory for template file: " + outputFilepath);
                    }
                }
            }
            if (EntityCompiler.isVerbose()) {
                ECLog.logInfo("Importing file \"" + gitRepoPath + "\" from GitHub by tag \"" + repository.getTag() + "\".");
            }

            File             outputFile = new File(outputFilepath);
            FileOutputStream fos        = new FileOutputStream(outputFile);
            IOUtils.copy(fileContent.read(), fos);
            fos.close();
        } catch (IOException e) {
            ECLog.logFatal("Unable to import file from GitHub: " + repository.getOrganization() + "/" + repository.getRepoName() + "/" + gitRepoPath + " tag: " + repository.getTag() + " message: " + e.getMessage());
        }
        return cachedRepositoryFile;
    }

    @Override
    public void close() {
        github = null;
    }
}

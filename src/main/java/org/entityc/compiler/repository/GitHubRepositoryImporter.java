/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.repository;

import org.apache.commons.io.IOUtils;
import org.entityc.compiler.EntityCompiler;
import org.entityc.compiler.model.config.MTRepository;
import org.entityc.compiler.model.config.MTRepositoryImport;
import org.entityc.compiler.util.ECLog;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRef;
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

    private void showGithubConnectionError( MTRepository repository )
    {
        ECLog.logError(
            "Unable to connect to GitHub: " + repository.getOrganization() + "/" + repository.getRepoName());
        ECLog.log("This program uses the following Github API library: https://github-api.kohsuke.org\n" +
            "One of the easiest ways to configure Github access is to set the GITHUB_OAUTH environment variable to your\n" +
            "Github Personal Access Token. See the above URL for other ways to configure Github access.");
        System.exit(1);
    }
    @Override
    public RepositoryFile importFromRepository(MTRepository repository, MTRepositoryImport repositoryImport, RepositoryFile cachedRepositoryFile, String extension, String alternatePath) {
        try {
            if (github == null) {
                github = GitHub.connect();
            }
        } catch (IOException e) {
            showGithubConnectionError(repository);
        }
        String pathPart = alternatePath != null ?
                          (alternatePath + "/") :
                          repository.getPath() != null && !repository.getPath().isEmpty() ?
                          repository.getPath() + "/" :
                          "";
        String       gitRepoPath = pathPart + repositoryImport.getFilename() + "." + extension;
        GHRepository repo        = null;
        try {
            repo = github.getRepository(repository.getOrganization() + "/" + repository.getRepoName());
        } catch (IOException e) {
            ECLog.logFatal("Unable to connect to repository: " + repository.getOrganization() + "/"
                           + repository.getRepoName());
        }
        try {
            if (!repositoryImport.isQuietly()) {
                ECLog.logInfo("Downloading file: " + gitRepoPath + " " + repository.getTag());
            }
            GHContent fileContent = repo.getFileContent(gitRepoPath, repository.getTag());
            if (fileContent == null) {
                ECLog.logFatal(repositoryImport,
                               "Could not find file in repository: " + cachedRepositoryFile.getFilepath() + " with tag "
                               + repository.getTag());
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
                ECLog.logInfo(
                        "Importing file \"" + gitRepoPath + "\" from GitHub by tag \"" + repository.getTag() + "\".");
            }

            File             outputFile = new File(outputFilepath);
            FileOutputStream fos        = new FileOutputStream(outputFile);
            IOUtils.copy(fileContent.read(), fos);
            fos.close();
        } catch (IOException e) {
            ECLog.logFatal("Unable to import file from GitHub: " + repository.getOrganization() + "/"
                           + repository.getRepoName() + "/" + gitRepoPath + " tag: " + repository.getTag()
                           + " message: " + e.getMessage());
        }
        return cachedRepositoryFile;
    }

    @Override
    public void updateRepositoryCommitSHA1(MTRepository repository) {

        if (repository.getCommitSHA1() != null) {
            return; // we are good
        }

        try {
            if (github == null) {
                github = GitHub.connect();
            }
        } catch (IOException e) {
            showGithubConnectionError(repository);
        }

        GHRepository repo = null;
        try {
            repo = github.getRepository(repository.getOrganization() + "/" + repository.getRepoName());
        } catch (IOException e) {
            ECLog.logFatal("Unable to connect to repository: " + repository.getOrganization() + "/"
                           + repository.getRepoName());
        }

        String sha1 = null;
        try {
            GHBranch branch = repo.getBranch(repository.getTag());
            if (branch != null) {
                sha1 = branch.getSHA1();
            }
        } catch (IOException e) {
            // could not find the commit, maybe its not a branch name
        }
        try {
            GHRef ref = repo.getRef("tags/" + repository.getTag());
            sha1 = ref.getObject().getSha();
        } catch (IOException e) {
            // must not be a tag
        }

        repository.setCommitSHA1(sha1);
    }

    @Override
    public void close() {
        github = null;
    }
}

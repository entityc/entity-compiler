/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.config;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.util.ECLog;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MTRepository extends MTNode {

    private final Map<String, String> templatePaths    = new HashMap<>();
    private       MTRepositoryType    type;
    private       String              name;
    private       String              organization;
    private       String              path;
    private       String              tag;
    private       String              repoName;
    private       boolean             indexedTemplates = false;
    private       String              setupFilename;

    public MTRepository(ParserRuleContext ctx, String name) {
        super(ctx);
        this.name = name;
    }

    public MTRepository(String setupUri) {
        Integer colonIndex = setupUri.indexOf(":");
        if (colonIndex == -1) {
            ECLog.logFatal("Setup URI must define a site.");
        }
        String siteIdentifier = setupUri.substring(0, colonIndex);
        if (!siteIdentifier.equals("github")) {
            ECLog.logFatal("The only setup site supported currently is github.");
        }
        type = MTRepositoryType.GITHUB;
        Integer firstPathDelimIndex = setupUri.indexOf("/");
        if (firstPathDelimIndex == -1) {
            ECLog.logFatal("Invalid setup URI - nothing after organization name");
        }
        organization = setupUri.substring(colonIndex + 1, firstPathDelimIndex);
        Integer tagDelimIndex = setupUri.indexOf(":", colonIndex + 1);
        if (tagDelimIndex == -1) {
            ECLog.logFatal("You need to specify a tag at the end of the setup URI.");
        }
        tag      = setupUri.substring(tagDelimIndex + 1);
        setupUri = setupUri.substring(0, tagDelimIndex);
        Integer secondPathDelimIndex = setupUri.indexOf("/", firstPathDelimIndex + 1);
        boolean hasNoPath            = secondPathDelimIndex == -1;
        if (hasNoPath) {
            secondPathDelimIndex = setupUri.length();
        }
        repoName = setupUri.substring(firstPathDelimIndex + 1, secondPathDelimIndex);
        path     = "";
        if (!hasNoPath) {
            path = setupUri.substring(secondPathDelimIndex + 1);
            Integer lastPathDelimIndex = path.lastIndexOf("/");
            if (lastPathDelimIndex == -1) {
                setupFilename = "Setup";
            }
            else {
                setupFilename = path.substring(lastPathDelimIndex + 1);
                path          = path.substring(0, lastPathDelimIndex);
            }
        }
    }

    public String getSetupFilename() {
        return setupFilename;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public MTRepositoryType getType() {
        return type;
    }

    public void setType(MTRepositoryType type) {
        this.type = type;
    }

    public boolean isValid() {
        if (type == MTRepositoryType.GITHUB) {
            return organization != null && organization.length() > 0
                   && path != null && path.length() > 0
                   && tag != null && tag.length() > 0
                   && repoName != null && repoName.length() > 0;
        }
        else if (type == MTRepositoryType.LOCAL) {
            return path != null && path.length() > 0;
        }
        return false;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    public void addTemplatePath(String templateName, String repoPath) {
        if (this.templatePaths.containsKey(templateName)) {
            ECLog.logWarning("In repository " + getName() + " there are multiple templates named: " + templateName);
            return;
        }
        this.templatePaths.put(templateName, repoPath);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplatePath(String templateName) {
        return this.templatePaths.get(templateName);
    }

    public boolean indexTemplateFilesInGitHubRepo(GitHub gitHub) {
        if (!indexedTemplates) {
            try {
                GHRepository repo = gitHub.getRepository(
                    this.getOrganization() + "/" + this.getRepoName());
                List<GHContent> directoryContents = repo.getDirectoryContent(this.getPath());
                indexTemplateFilesInGithubDirectory(repo, directoryContents);
                indexedTemplates = true;
            } catch (IOException e) {
                ECLog.logFatal(this, "Unable to index template files on github.");
            }
        }
        return indexedTemplates;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void indexTemplateFilesInGithubDirectory(GHRepository repo, List<GHContent> directoryContents) throws IOException {
        for (GHContent content : directoryContents) {
            if (content.isDirectory()) {
                List<GHContent> subDirContents = repo.getDirectoryContent(content.getPath());
                indexTemplateFilesInGithubDirectory(repo, subDirContents);
                continue;
            }
            String filename = content.getName();
            if (filename.endsWith(".eml")) {
                String templateName = filename.substring(0, filename.length() - 4); // chop off extension
                addTemplatePath(templateName, content.getPath());
            }
        }
    }
}

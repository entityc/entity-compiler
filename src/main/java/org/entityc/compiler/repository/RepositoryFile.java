/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.repository;

import org.entityc.compiler.model.config.MTRepository;
import org.entityc.compiler.model.config.MTRepositoryType;
import org.entityc.compiler.util.ECLog;

import java.io.File;

public class RepositoryFile {

    private final MTRepository repository;
    private final String       filepath;
    private final boolean      included;

    public RepositoryFile(MTRepository repository, String filepath, boolean included) {
        this.repository = repository;
        this.filepath = filepath;
        this.included = included;
    }

    public RepositoryFile(String filepath, boolean included) {
        this.repository = null;
        this.filepath = filepath;
        this.included = included;
    }

    public MTRepository getRepository() {
        return repository;
    }

    public String getFilepath() {
        return filepath;
    }

    public boolean isIncluded() {
        return included;
    }

    public boolean exists() {
        File checkFile = new File(filepath);
        boolean exists = checkFile.exists();
        if (!exists && repository.getType() == MTRepositoryType.LOCAL) {
            ECLog.logWarning("File doesn't exist: " + filepath);
        }
        return exists;
    }
}

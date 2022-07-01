/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.repository;

import org.entityc.compiler.model.config.MTRepository;
import org.entityc.compiler.model.config.MTRepositoryImport;

public interface RepositoryImporter {

    RepositoryFile importFromRepository(MTRepository repository, MTRepositoryImport repositoryImport, RepositoryFile cachedRepositoryFile, String extension, String alternatePath);
    void updateRepositoryCommitSHA1(MTRepository repository);

    void close();
}

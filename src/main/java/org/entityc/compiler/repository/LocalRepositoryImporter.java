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
import org.entityc.compiler.util.ECStringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class LocalRepositoryImporter implements RepositoryImporter {

    @Override
    public RepositoryFile importFromRepository(MTRepository repository, MTRepositoryImport repositoryImport, RepositoryFile cachedRepositoryFile, String extension, String alternatePath) {
        String outputFilepath = cachedRepositoryFile.getFilepath();
        // in case the filename has sub directories, make sure they are created

        String directoryPath = ECStringUtil.DirectoryPath(outputFilepath);
        if (directoryPath != null) {
            if (!EntityCompiler.ensureDirectory(directoryPath)) {
                ECLog.logFatal("Not able to create directory for template file: " + outputFilepath);
            }
        }

        String inputFilepath = repository.getPath() + "/" + repositoryImport.getFilename() + "." + extension;
        if (inputFilepath.equals(outputFilepath)) {
            //ECLog.logInfo("Don't need to copy file: " + cachedRepositoryFile.getFilepath());
        } else {
            try {
                FileInputStream fis = new FileInputStream(inputFilepath);

                ECLog.logInfo("Copying file " + inputFilepath + " to " + outputFilepath);
                File             outputFile = new File(outputFilepath);
                FileOutputStream fos        = new FileOutputStream(outputFile);
                IOUtils.copy(fis, fos);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
                ECLog.logFatal("Unable to import file: " + inputFilepath + " >> " + e.getMessage());
            }
        }
        return cachedRepositoryFile;
    }

    @Override
    public void updateRepositoryCommitSHA1(MTRepository repository) {
        // nothing to do for this type of repository
    }

    @Override
    public void close() {
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ECSessionManager {

    private static final String                      ECDirectoryName       = ".ec";
    private static final String                      SessionsDirectoryName = "sessions";
    private static final ECSessionManager            instance              = new ECSessionManager();
    private final        Map<String, ECSessionFiles> sessionsByName        = new HashMap<>();
    private              String                      projectBaseDirPath; // the directory with the .ec directory in it
    private              String                      cwdPath;
    private              File                        ecSessionsDirectory;
    private              boolean                     createIfDoesntExist   = false;

    private ECSessionManager() {
    }

    public static ECSessionManager getInstance() {
        return instance;
    }

    public boolean isCreateIfDoesntExist() {
        return createIfDoesntExist;
    }

    public void setCreateIfDoesntExist(boolean createIfDoesntExist) {
        this.createIfDoesntExist = createIfDoesntExist;
    }

    public String getProjectBaseDirPath() {
        return projectBaseDirPath;
    }

    public void setProjectBaseDirPath(String projectBaseDirPath) {
        this.projectBaseDirPath = projectBaseDirPath;
    }

    public void start() {
        if (projectBaseDirPath == null) {
            projectBaseDirPath = ".";
        }
        File    cwd   = new File(projectBaseDirPath);
        boolean found = false;
        try {
            this.cwdPath = cwd.getCanonicalPath();
            found = findECDirectory(this.cwdPath);
        } catch (IOException e) {
            found = false;
        }
        if (!found) {
            if (!createIfDoesntExist) {
                ECLog.logFatal("Unable to locate the project base directory. Ensure the compiler is invoked inside the project directory structure.");
            }
        }

        if (ecSessionsDirectory == null) {
            ecSessionsDirectory = new File(projectBaseDirPath + File.separator + ECDirectoryName + File.separator + SessionsDirectoryName);
        }
        ecSessionsDirectory.mkdirs();
    }

    private boolean findECDirectory(String fromHerePath) {
        if (fromHerePath.equals(File.separator)) {
            return false; // can't go up any more
        }
        File ecDirectory = new File(fromHerePath + File.separator + ECDirectoryName);
        if (ecDirectory.exists()) {
            this.projectBaseDirPath = fromHerePath;
            return true;
        }
        File parentDirectory = new File(fromHerePath + File.separator + "..");
        try {
            return findECDirectory(parentDirectory.getCanonicalPath());
        } catch (IOException e) {
            return false;
        }
    }

    public ECSessionFiles getSessionFiles(String sessionName) {
        //we need to make sure all paths added to this session are relative to the found single project base path

        if (!sessionsByName.containsKey(sessionName)) {
            sessionsByName.put(sessionName, new ECSessionFiles(sessionName, ecSessionsDirectory.getAbsolutePath()));
        }
        return sessionsByName.get(sessionName);
    }

    public void close() {
        for (ECSessionFiles sessionFiles : sessionsByName.values()) {
            sessionFiles.removeOldFiles();
            sessionFiles.save();
        }
    }
}

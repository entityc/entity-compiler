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
            try {
                String invocationDirectory = new File(".").getCanonicalPath();
                projectBaseDirPath = findECDirectory(invocationDirectory);
                if (projectBaseDirPath == null) {
                    projectBaseDirPath = invocationDirectory;
                }
            } catch (IOException e) {
                ECLog.logFatal("Unable to locate current working directory");
            }
        }

        if (ecSessionsDirectory == null) {
            ecSessionsDirectory = new File(
                    projectBaseDirPath + File.separator + ECDirectoryName + File.separator + SessionsDirectoryName);
        }
        ecSessionsDirectory.mkdirs();
    }

    private String findECDirectory(String fromHerePath) {
        if (fromHerePath.equals(File.separator)) {
            return null; // can't go up any more
        }
        File ecDirectory = new File(fromHerePath + File.separator + ECDirectoryName);
        if (ecDirectory.exists()) {
            return fromHerePath;
        }
        File parentDirectory = new File(fromHerePath + File.separator + "..");
        try {
            return findECDirectory(parentDirectory.getCanonicalPath());
        } catch (IOException e) {
            return null;
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

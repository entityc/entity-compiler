/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.txt file in the project root.
 */

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.project;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.io.FileUtils;
import org.entityc.compiler.util.ECLog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectManager {

    private static final String                      ECDirectoryName       = ".ec";
    private static final String                      SessionsDirectoryName = "sessions";
    private static final ProjectManager              instance              = new ProjectManager();
    private final        Map<String, ECSessionFiles> sessionsByName        = new HashMap<>();
    private              List<GeneratedFile>         generatedFiles        = new ArrayList<>();
    private              Set<String>                 configurationNames    = null;
    private              Set<String>                 templateUris          = null;
    private              File                        projectDirectory; // the directory with the .ec directory in it
    private              File                        ecDirectory;
    private              File                        ecSessionsDirectory;

    private ProjectManager() {
    }

    public void start() {
        if (projectDirectory == null) {
            try {
                String invocationDirectory  = new File(".").getCanonicalPath();
                String projectDirectoryPath = findECDirectory(invocationDirectory);
                if (projectDirectoryPath == null) {
                    projectDirectoryPath = invocationDirectory;
                }
                projectDirectory = new File(projectDirectoryPath);
            } catch (IOException e) {
                ECLog.logFatal("Unable to locate current working directory");
            }
        }

        ecDirectory         = new File(projectDirectory.getAbsolutePath() + File.separator + ECDirectoryName);
        ecSessionsDirectory = new File(ecDirectory.getPath() + File.separator + SessionsDirectoryName);
        ecSessionsDirectory.mkdirs();
    }

    public void loadProjectFiles() {
        validate();
        File generatedFile = new File(ecDirectory.getPath() + File.separator + "generated.json");
        if (generatedFile.exists()) {
            Gson gson = new Gson();
            try {
                FileInputStream fis    = new FileInputStream(generatedFile);
                JsonReader      reader = new JsonReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
                this.generatedFiles = gson.fromJson(reader, new TypeToken<ArrayList<GeneratedFile>>() {
                }.getType());
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void validate() {
        if (!ecDirectory.exists()) {
            ECLog.logFatal("No project directory found.");
        }
    }

    public String findECDirectory(String fromHerePath) {
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

    public void addGeneratedFile(String path, String configurationName, String templateUri) {
        generatedFiles.add(new GeneratedFile(path, configurationName, templateUri));
        configurationNames = null;
        templateUris       = null;
    }

    public void close() {

        for (ECSessionFiles sessionFiles : sessionsByName.values()) {
            sessionFiles.removeOldFiles();
            sessionFiles.save();
        }

        Gson   gson     = new Gson();
        String filepath = ecDirectory.getPath() + File.separator + "generated.json";
        try {
            FileOutputStream fos    = new FileOutputStream(filepath);
            JsonWriter       writer = new JsonWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
            gson.toJson(generatedFiles, ArrayList.class, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            ECLog.logWarning("Unable to save project files.");
        } catch (IOException e) {
            ECLog.logWarning("Unable to save project files.");
        }
    }

    public Set<String> getConfigurationNames() {
        if (configurationNames == null) {
            configurationNames = new HashSet<>();

            for (GeneratedFile file : generatedFiles) {
                Set<String> names = file.getConfigurationNames();
                configurationNames.addAll(names);
            }
        }
        return configurationNames;
    }

    public Set<String> getTemplateUris() {
        if (templateUris == null) {
            templateUris = new HashSet<>();

            for (GeneratedFile file : generatedFiles) {
                Set<String> uris = file.getTemplateUris();
                templateUris.addAll(uris);
            }
        }
        return templateUris;
    }

    public List<GeneratedFile> getGeneratedFilesByTemplateUri(String templateUri) {
        ArrayList<GeneratedFile> files = new ArrayList<>();
        for (GeneratedFile file : generatedFiles) {
            if (file.hasTemplateOrigin(templateUri)) {
                files.add(file);
            }
        }
        return files;
    }

    public List<GeneratedFile> getGeneratedFilesByConfigurationName(String configurationName) {
        ArrayList<GeneratedFile> files = new ArrayList<>();
        for (GeneratedFile file : generatedFiles) {
            if (file.hasConfigurationOrigin(configurationName)) {
                files.add(file);
            }
        }
        return files;
    }

    public Set<String> getSourceFiles() {
        List<String> sourceFilenames = new ArrayList<>();
        Collection<File> sourceFiles = FileUtils.listFiles(
                new File(ProjectManager.getInstance().getProjectBaseDirPath()),
                new String[]{"eml", "edl"}, true);
        String   cwdPath  = (new File(".")).getAbsolutePath();
        String[] cwdParts = cwdPath.split(File.separator);
        for (File sourceFile : sourceFiles) {
            String   absolutePath = sourceFile.getAbsolutePath();
            String[] sourceParts  = absolutePath.split(File.separator);

            int lastMatchingIndex = -1;
            for (int i = 0; i < cwdParts.length; i++) {
                if (i + 1 == sourceParts.length) {
                    break;
                }
                if (!cwdParts[i].equals(sourceParts[i])) {
                    break;
                }
                lastMatchingIndex = i;
            }
            int           numBackDirs = cwdParts.length - 1 - (lastMatchingIndex + 1);
            StringBuilder sb          = new StringBuilder();
            for (int i = 0; i < numBackDirs; i++) {
                sb.append("..");
                sb.append(File.separator);
            }
            for (int i = lastMatchingIndex + 1; i < sourceParts.length; i++) {
                sb.append(sourceParts[i]);
                if (i != (sourceParts.length) - 1) {
                    sb.append(File.separator);
                }
            }
            sourceFilenames.add(sb.toString());
        }
        return sourceFilenames.stream().sorted().collect(Collectors.toSet());
    }

    public String getProjectBaseDirPath() {
        return projectDirectory.getAbsolutePath();
    }

    public static ProjectManager getInstance() {
        return instance;
    }

    public void setProjectBaseDirPath(String projectDirectoryPath) {
        this.projectDirectory = new File(projectDirectoryPath);
    }
}

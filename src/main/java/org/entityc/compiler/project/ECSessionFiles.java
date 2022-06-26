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
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.entityc.compiler.EntityCompiler;
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
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class ECSessionFiles {

    /**
     * Name of the session that will be used in the naming of the session file.
     */
    private final String sessionName;
    /**
     * The location to store a session file containing the list of files generated in the session.
     */
    private final String sessionSaveDirFilepath;
    Set<String> filePaths = new HashSet<>();

    public ECSessionFiles(String sessionName, String sessionSaveDirFilepath) {
        this.sessionName = sessionName;
        this.sessionSaveDirFilepath = sessionSaveDirFilepath;
    }

    public void addFilePath(String filePath) {
        filePaths.add(filePath);
    }

    public void removeOldFiles() {
        Set<String> filesNotInLatestSessionFiles = load();
        if (filesNotInLatestSessionFiles == null) {
            return;
        }
        // remove those not in current this.filePaths
        filesNotInLatestSessionFiles.removeAll(this.filePaths);

        for (String pathOfFileToRemove : filesNotInLatestSessionFiles) {
            //ECLog.logInfo("Deleting previously generated file no longer being generated: " + pathOfFileToRemove);
            java.io.File file = new File(pathOfFileToRemove);
            if (EntityCompiler.isVerbose() && !file.delete()) {
                ECLog.logWarning("Unable to delete old generated file: " + pathOfFileToRemove);
            }
        }
    }

    /**
     * Load the last set of files generated.
     * @return The set of last files generated.
     */
    private Set<String> load() {
        Set<String> returnSet = new HashSet<>();
        Gson        gson      = new Gson();
        String      filepath  = sessionFilename();
        try {
            FileInputStream fis    = new FileInputStream(filepath);
            JsonReader      reader = new JsonReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
            returnSet = gson.fromJson(reader, Set.class);
            reader.close();
        } catch (FileNotFoundException e) {
            // will happen the first time since the file doesn't exist
        } catch (IOException e) {
        }
        return returnSet;
    }

    private String sessionFilename() {
        return sessionSaveDirFilepath + File.separator + sessionName + ".json";
    }

    public void save() {
        ArrayList<String> sortedFilePaths = new ArrayList<>(this.filePaths);
        sortedFilePaths.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        Gson   gson     = new Gson();
        String filepath = sessionFilename();
        try {
            EntityCompiler.ensureDirectory(sessionSaveDirFilepath);
            FileOutputStream fos    = new FileOutputStream(filepath);
            JsonWriter       writer = new JsonWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
            writer.setIndent("  ");
            gson.toJson(sortedFilePaths, ArrayList.class, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.config;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.visitor.MTVisitor;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MTDirectory extends MTNode {

    private final String name;
    private       String path;

    public MTDirectory(ParserRuleContext ctx, String name) {
        super(ctx);
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    public String getName() {
        return name;
    }

    public List<MTDirectory> getSubDirectories() {
        File                   thisDirectory = new File(path);
        File[]                 directories   = thisDirectory.listFiles(File::isDirectory);
        ArrayList<MTDirectory> mtDirectories = new ArrayList<>();
        for (File dir : directories) {
            MTDirectory mtDirectory = new MTDirectory(null, dir.getName());
            mtDirectory.setPath(dir.getPath());
            mtDirectories.add(mtDirectory);
        }
        return mtDirectories;
    }

    public boolean hasFilesWithExtension(String extension) {
        File   thisDirectory = new File(path);
        File[] files         = thisDirectory.listFiles(File::isFile);
        for (File file : files) {
            String filename = file.getName();
            if (filename.endsWith("." + extension)) {
                return true;
            }
        }
        return false;
    }

    public List<MTFile> filesWithExtension(String extension) {
        File              thisDirectory = new File(path);
        File[]            files         = thisDirectory.listFiles(File::isFile);
        ArrayList<MTFile> filenames     = new ArrayList<>();
        for (File file : files) {
            String filename = file.getName();
            if (filename.endsWith("." + extension)) {
                filenames.add(new MTFile(this, file));
            }
        }
        return filenames;
    }

    public MTFile findTemplateFileWithName(String filename) {
        String extension     = "eml";
        File   thisDirectory = new File(path);
        File[] files         = thisDirectory.listFiles(File::isFile);
        for (File file : files) {
            if (file.getName().equals(filename + "." + extension)) {
                return new MTFile(this, file);
            }
        }
        for (MTDirectory subDir : getSubDirectories()) {
            MTFile file = subDir.findTemplateFileWithName(filename);
            if (file != null) {
                return file;
            }
        }

//        ECLog.logError("Unable to find file: " + filename + "." + extension + " in directory " + name);

        return null;
    }

    public String pathRelativeToPath(String baseDirPath) {
        Path   toPath          = Paths.get(path);
        Path   fromPath        = Paths.get(baseDirPath);
        String dirPath         = path;
        Path   relativePath    = fromPath.relativize(toPath);
        String relativePathStr = relativePath.toString();
        if (relativePathStr.equals("")) {
            return null;
        }
        return relativePathStr;
    }
}

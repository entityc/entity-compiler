/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.config;

import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.util.ECLog;

import java.io.File;

public class MTFile extends MTNode {

    private final MTDirectory directory;
    private final File        file;

    public MTFile(MTDirectory directory, File file) {
        super(null);
        this.directory = directory;
        this.file      = file;
        if (file == null) {
            ECLog.logFatal(this, "Can't create MTFile with null file.");
        }
    }

    public MTDirectory getDirectory() {
        return directory;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    public String getName() {
        return file.getName();
    }

    public String getNameWithoutExtension() {
        int extensionIndex = file.getName().lastIndexOf('.');
        return file.getName().substring(0, extensionIndex);
    }

    public String pathRelativeToPath(String baseDirPath) {
        String filePath = getPath();
        if (!filePath.startsWith(baseDirPath)) {
            return null; // is not relative to the directory
        }
        return filePath.substring(baseDirPath.length() + 1);
    }

    public String getPath() {
        return file.getPath();
    }
}

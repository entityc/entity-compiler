/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.txt file in the project root.
 */

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.txt file in the project root.
 */

package org.entityc.compiler.util;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class LogHandler extends Handler {

    @Override
    public void publish(LogRecord record) {
        String   fullClassPath = record.getSourceClassName();
        String[] parts         = fullClassPath.split("\\.");
        String   justClassName = parts[parts.length - 1];

        StringBuilder sb = new StringBuilder();
        sb.append(record.getLevel().getName());
        sb.append(": ");
        sb.append(justClassName);
        sb.append(".");
        sb.append(record.getSourceMethodName());
        sb.append("()| ");
        sb.append(record.getMessage());
        if (record.getLevel() == Level.SEVERE) {
            System.err.println(sb);
        } else {
            System.out.println(sb);
        }
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {

    }
}

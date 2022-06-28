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

package org.entityc.compiler.cmdline.command;

import org.entityc.compiler.cmdline.CommandLine;
import org.entityc.compiler.structure.sql.SSSchemaVersioning;

public class CLSchema extends CLCommand {

    public CLSchema(CommandLine commandLine) {
        super(commandLine,
              "schema",
              "Used to manage the database schema of the project.",
              "");
    }

    @Override
    public void printUsage() {
        super.printUsageWithArguments("advance|delete");
    }

    @Override
    public void run(String[] args) {

        // TODO: SSSchemaVersioning.DeleteEntireSchema();
    }
}

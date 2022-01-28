/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.sql;

public class SSAlteredTable extends SSTable {

    public SSAlteredTable(SSTable originalTable) {
        super(originalTable.getName(), originalTable.getEntityName());
    }

    public boolean isAltered() {
        return !this.getColumns().isEmpty();
    }
}

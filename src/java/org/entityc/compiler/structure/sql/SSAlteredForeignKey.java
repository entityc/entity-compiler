/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.sql;

public class SSAlteredForeignKey extends SSForeignKey {

    private final boolean changedDeleteCascade;

    public SSAlteredForeignKey(SSForeignKey baselineForeignKey, SSForeignKey nextForeignKey) {
        super(nextForeignKey.getRelationshipName(), nextForeignKey.getLocalColumn(),
              nextForeignKey.getForeignTableName(), nextForeignKey.getForeignColumnName(),
              nextForeignKey.isDeleteCascade());
        this.changedDeleteCascade = baselineForeignKey.isDeleteCascade() != nextForeignKey.isDeleteCascade();
    }

    public boolean isChangedDeleteCascade() {
        return changedDeleteCascade;
    }
    public boolean isAltered() {
        return this.changedDeleteCascade;
    }
}

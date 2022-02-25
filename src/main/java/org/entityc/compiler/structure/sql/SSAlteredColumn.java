/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.sql;

public class SSAlteredColumn extends SSColumn {

    private final boolean typeChanged;
    private final boolean nameChanged;
    private final boolean primaryKeyChanged;
    private final boolean uniqueChanged;
    private final boolean notNullChanged;
    private final boolean defaultValueChanged;
    private       boolean foriegnKeyColumnChanged;

    public SSAlteredColumn(SSColumn baselineColumn, SSColumn nextColumn) {
        super(nextColumn.getType(), nextColumn.getName(), nextColumn.getAttributeName());

        this.typeChanged = baselineColumn.getType() != nextColumn.getType();
        this.nameChanged = !baselineColumn.getName().equals(nextColumn.getName());

        this.setPrimaryKey(nextColumn.isPrimaryKey());
        this.primaryKeyChanged = baselineColumn.isPrimaryKey() != nextColumn.isPrimaryKey();

        this.setUnique(nextColumn.isUnique());
        this.uniqueChanged = baselineColumn.isUnique() != nextColumn.isUnique();

        this.setNotNull(nextColumn.isNotNull());
        this.notNullChanged = baselineColumn.isNotNull() != nextColumn.isNotNull();

        this.setDefaultValue(nextColumn.getDefaultValue());
        this.defaultValueChanged = !baselineColumn.hasDefaultValue() && nextColumn.hasDefaultValue()
                                   || baselineColumn.hasDefaultValue() && !nextColumn.hasDefaultValue()
                                   || (baselineColumn.hasDefaultValue() && nextColumn.hasDefaultValue()
                                       && !baselineColumn.getDefaultValue().equals(nextColumn.getDefaultValue()));

        this.setForiegnKeyColumn(nextColumn.getForiegnKeyColumn());
        SSColumn baselineFKC = baselineColumn.getForiegnKeyColumn();
        SSColumn nextFKC     = nextColumn.getForiegnKeyColumn();
        if (baselineFKC != null) {
            this.foriegnKeyColumnChanged = !baselineFKC.equals(nextFKC);
        }
    }

    public boolean isTypeChanged() {
        return typeChanged;
    }

    public boolean isNameChanged() {
        return nameChanged;
    }

    public boolean isPrimaryKeyChanged() {
        return primaryKeyChanged;
    }

    public boolean isUniqueChanged() {
        return uniqueChanged;
    }

    public boolean isNotNullChanged() {
        return notNullChanged;
    }

    public boolean isDefaultValueChanged() {
        return defaultValueChanged;
    }

    public boolean isForiegnKeyColumnChanged() {
        return foriegnKeyColumnChanged;
    }

    public boolean isAltered() {
        return this.typeChanged || this.nameChanged || this.primaryKeyChanged || this.uniqueChanged
               || this.notNullChanged || this.foriegnKeyColumnChanged;
    }
}

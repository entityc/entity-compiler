/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform;

import org.entityc.compiler.EntityCompiler;
import org.entityc.compiler.generator.SSVPostgresGenerator;
import org.entityc.compiler.model.MTRoot;
import org.entityc.compiler.model.config.MTDirectory;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.config.MTTransform;
import org.entityc.compiler.model.domain.MTDEAttribute;
import org.entityc.compiler.model.domain.MTDEntity;
import org.entityc.compiler.model.domain.MTDomain;
import org.entityc.compiler.model.entity.FullRelationshipPlurality;
import org.entityc.compiler.model.entity.HalfRelationshipPlurality;
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTEntityTemplate;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTNativeType;
import org.entityc.compiler.model.entity.MTPrimaryKey;
import org.entityc.compiler.model.entity.MTRelationship;
import org.entityc.compiler.model.entity.MTType;
import org.entityc.compiler.model.entity.MTUniqueness;
import org.entityc.compiler.structure.sql.SSColumn;
import org.entityc.compiler.structure.sql.SSComment;
import org.entityc.compiler.structure.sql.SSForeignKey;
import org.entityc.compiler.structure.sql.SSIndex;
import org.entityc.compiler.structure.sql.SSSchema;
import org.entityc.compiler.structure.sql.SSSchemaVersioning;
import org.entityc.compiler.structure.sql.SSSequence;
import org.entityc.compiler.structure.sql.SSSourceFile;
import org.entityc.compiler.structure.sql.SSTable;
import org.entityc.compiler.structure.sql.SSType;
import org.entityc.compiler.structure.sql.SSUniqueConstraint;
import org.entityc.compiler.util.ECLog;
import org.entityc.compiler.util.ECStringUtil;

import java.util.HashMap;
import java.util.Map;

// TODO: Create index from parent relationships

public class MTVPostgresTransform extends MTBaseTransform {

    private final Map<String, SSTable> tablesByName         = new HashMap<>();
    private final MTDomain             databaseDomain;
    private       SSSourceFile         sourceFile;
    private       SSTable              currentTable;
    private       boolean              advanceSchemaVersion = false;
    private       int                  readSchemaVersion;
    private       int                  writeSchemaVersion;

    public MTVPostgresTransform(MTRoot root, String configurationName) {
        super("Postgres", root, configurationName);
        databaseDomain = root.getSpace().getDomainWithName("Database");
    }

    @Override
    public void start() {

        super.start();

        MTTransform config           = getConfiguration().getTransformByName(getName());
        String      outputName       = config.getOutputNameByLocalName("primary");
        MTDirectory output           = getConfiguration().getOutputByName(outputName);
        String      schemaOutputName = config.getOutputNameByLocalName("schema");
        MTDirectory schemaOutput     = getConfiguration().getOutputByName(schemaOutputName);
        SSSchemaVersioning.setBasePath(schemaOutput.getPath());

        boolean requestToAdvance = SSSchemaVersioning.LoadAdvanceRequest();
        advanceSchemaVersion = EntityCompiler.ShouldAdvanceSchemaVersion();

        if (requestToAdvance && advanceSchemaVersion) {
            ECLog.logFatal("You already requested to advance the schema version. Exiting.");
        }

        if (!requestToAdvance && advanceSchemaVersion) {
            SSSchemaVersioning.SaveAdvanceRequest(advanceSchemaVersion);
        }

        this.readSchemaVersion  = SSSchemaVersioning.LoadSchemaVersion(SSSchemaVersioning.SchemaPointer.Read);
        this.writeSchemaVersion = SSSchemaVersioning.LoadSchemaVersion(SSSchemaVersioning.SchemaPointer.Write);

        if (requestToAdvance) {
            this.readSchemaVersion = this.writeSchemaVersion;
        }

        SSSchema currentSchema = null;

        if (this.readSchemaVersion > 0) {
            currentSchema = SSSchemaVersioning.LoadSchema(this.readSchemaVersion);
        }
        writeSchemaVersion = readSchemaVersion + 1;

        SSSchema newSchema = new SSSchema(writeSchemaVersion, this.getSourceFile(), "some description");
        if (currentSchema != null) {
            newSchema.copySinceVersions(currentSchema); // retain the previously annotated versions
        }
        SSSourceFile migrationSourceFile = SSSchemaVersioning.generateMigration(currentSchema, newSchema);
        boolean      migrationIsEmpty    = !migrationSourceFile.isAltered();
        boolean      changeToOldSchema   = readSchemaVersion > 0 && !migrationIsEmpty;

        SSSchema migrationSchema = new SSSchema(newSchema.getVersion(), migrationSourceFile,
                                                "some description");
        SSVPostgresGenerator sqlGenerator = new SSVPostgresGenerator(output.getPath());

        if (migrationIsEmpty) {
            sqlGenerator.removeOutputFile(migrationSchema);
            return; // nothing to do
        }

        annotateModelWithSchemaVersions(currentSchema, newSchema);

        sqlGenerator.visit(migrationSchema);
        SSSchemaVersioning.SaveSchema(newSchema);

        if (!requestToAdvance || changeToOldSchema) {
            if (changeToOldSchema && requestToAdvance) {
                SSSchemaVersioning.SaveAdvanceRequest(false); // clear it
            }
            SSSchemaVersioning.SaveSchemaVersion(SSSchemaVersioning.SchemaPointer.Read, readSchemaVersion);
            SSSchemaVersioning.SaveSchemaVersion(SSSchemaVersioning.SchemaPointer.Write, writeSchemaVersion);
        }
    }

    public SSSourceFile getSourceFile() {
        return sourceFile;
    }

    private void annotateModelWithSchemaVersions(SSSchema currentSchema, SSSchema nextSchema) {
        MTDomain databaseDomain = root.getSpace().getDomainWithName("Database");
        if (databaseDomain == null) {
            return; // don't do this
        }
        for (MTEntity entity : root.getSpace().getEntities()) {
            for (MTAttribute attribute : entity.getAttributes()) {
                int nextSchemaVersion = nextSchema.findSchemaVersionForAttribute(entity.getName(),
                                                                                 attribute.getName());
                int sinceSchemaVersion = nextSchemaVersion;
                if (sinceSchemaVersion == 0 && currentSchema != null) {
                    sinceSchemaVersion = currentSchema.findSchemaVersionForAttribute(entity.getName(),
                                                                                     attribute.getName());
                }
                if (sinceSchemaVersion > 0) {
                    MTDEntity domainEntity = databaseDomain.getDomainEntityByName(entity.getName());
                    if (domainEntity != null) {
                        MTDEAttribute domainAttribute = domainEntity.getDomainAttributeByName(attribute.getName(),
                                                                                              true);
                        if (domainAttribute != null) {
                            domainAttribute.setSinceSchemaVersion(nextSchemaVersion);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void visitRoot(MTRoot root) {
        MTSpace space        = root.getSpace();
        String  databaseName = (String) space.getMetadataValue("databaseName");
        if (databaseName == null || databaseName.equals("")) {
            databaseName = space.getName();
        }
        sourceFile = new SSSourceFile(databaseName, "sql");

        root.accept(this);
    }

    @Override
    public void visitEntity(MTEntity entity) {

        if (entity.isIncluded() || entity.isExtern() || entity.isTransient()) {
            currentTable = null;
            return;
        }

        if (entity.isSecondary() && !entity.isImplicit()) {
            return; // skip secondary entities
        }

        String tableName = databaseDomain.getNameOfNode(entity);
        currentTable = new SSTable(tableName, entity.getName());
        if (entity.hasDescription()) {
            currentTable.setComment(new SSComment(entity.getDescription()));
        }
        tablesByName.put(tableName, currentTable);
        sourceFile.addTable(currentTable);

        entity.accept(this);

        // create unique constraints
        if (!entity.getUniquenesses().isEmpty()) {
            for (MTUniqueness uniqueness : entity.getUniquenesses()) {
                SSUniqueConstraint uniqueConstraint = new SSUniqueConstraint();

                SSColumn parentColumn = currentTable.getLocalColumnWithRelationshipName(
                        uniqueness.getParentRelationship().getName());
                if (parentColumn == null) {
                    continue;
                }
                uniqueConstraint.addColumnName(parentColumn.getName());

                SSColumn otherColumn = currentTable.getLocalColumnWithRelationshipName(
                        uniqueness.getRelationship().getName());
                if (otherColumn == null) {
                    continue;
                }
                uniqueConstraint.addColumnName(otherColumn.getName());

                currentTable.addUniqueConstraint(uniqueConstraint);
            }
        }
    }

    @Override
    public void visitEnum(MTEnum mtenum) {
        mtenum.accept(this);
    }

    @Override
    public void visitPrimaryKey(MTPrimaryKey primaryKey) {
        if (primaryKey.getAttributes().size() == 1) {
            visitAttribute(primaryKey.getAttributes().get(0));
        }
    }

    @Override
    public void visitAttribute(MTAttribute attribute) {

        if (currentTable == null || attribute == null || (attribute.getEntity() instanceof MTEntityTemplate)) {
            return;
        }
        if (attribute.isVirtual()) {
            return;
        }
        if (databaseDomain.attributeHasReplacedBitfield(attribute)) {
            if (attribute.isArray()) {
                System.err.println(
                        "ERROR: Replacing attribute \"" + attribute.getEntity().getName() + "." + attribute.getName()
                        + "\" defined as many is not allowed. Exiting.");
                System.exit(1);
            }
            return;
        }

        if (attribute.getType() == null) {
            attribute.resolveReferences(databaseDomain.getSpace(), 0);
            if (attribute.getType() == null) {
                ECLog.logFatal(attribute, "Attribute \"" + attribute.getEntity().getName() + "." + attribute.getName()
                                          + "\" has no type.");
            }
        }
        processAttribute(attribute);
    }

    private void processAttribute(MTAttribute attribute) {
        MTType type = attribute.getType();
        if (attribute.isArray()) {
            MTDEntity attributeDomainEntity = databaseDomain.getDomainEntity(attribute.getEntity(), true);
            // create an implicit entity that will become a table the way we want
            MTEntity manyEntity = new MTEntity(null, attribute);
            manyEntity.setImplicit(true);

            if (attribute.getType().isEntityType()) {
                // copy the attributes if a secondary entity
                MTEntity entityType = (MTEntity) attribute.getType();
                if (entityType.isSecondary()) {
                    for (MTAttribute typeAttribute : entityType.getAttributes()) {
                        manyEntity.addAttribute(typeAttribute);
                    }
                } else {
                    ECLog.logFatal("Entities used as attribute types must be secondary entities.");
                }
            } else {
                // value attribute
                MTAttribute valueAttribute = new MTAttribute(null, manyEntity, attribute.getTypeName(), "value");
                manyEntity.addAttribute(valueAttribute);
            }
            if (attribute.isOrdered()) {
                // order attribute
                MTAttribute orderAttribute = new MTAttribute(null, manyEntity, "int32", "order");
                manyEntity.addAttribute(orderAttribute);
            }

            MTRelationship thisRelationship = new MTRelationship(null, attribute.getName(),
                                                                 attribute.getEntity().getName(),
                                                                 HalfRelationshipPlurality.MANY, manyEntity.getName(),
                                                                 attribute.isNullable(), false,
                                                                 attribute.getEntity().getName(), null, null);
            attribute.getEntity().addRelationship(thisRelationship);
            MTRelationship reverseRelationship = new MTRelationship(null, ECStringUtil.Uncapitalize(
                    attribute.getEntity().getName()),
                                                                    manyEntity.getName(), HalfRelationshipPlurality.ONE,
                                                                    attribute.getEntity().getName(), false, true,
                                                                    attribute.getName(), null, null);
            manyEntity.addRelationship(reverseRelationship);
            root.getSpace().addImplicitEntity(manyEntity);
            //model.resolveReferences(true);
        } else {
            if (attribute.isOrdered()) {
                ECLog.logError(
                        "Single value attribute \"" + attribute.getEntity().getName() + "." + attribute.getName()
                        + "\" can not be ordered. Ignoring...");
            }
            String columnName = databaseDomain.getNameOfNode(attribute);
            if (columnName == null) {
                ECLog.logFatal("Cannot create column without a name for attribute: " + attribute.getName());
            }
            if (attribute.isSecondaryEntityType()) {
                // copy the attributes if a secondary entity
                MTEntity entityType = (MTEntity) attribute.getType();
                for (MTAttribute typeAttribute : entityType.getAttributes()) {
                    // need to make a new attribute but named as the concatenation of the parent attribute name and secondary attribute name
                    MTAttribute secondaryAttribute = new MTAttribute(attribute.getParserRuleContext(),
                                                                     attribute.getEntity(), typeAttribute.getTypeName(),
                                                                     attribute.getName() + ECStringUtil.Capitalize(
                                                                             typeAttribute.getName()),
                                                                     typeAttribute.getUnit() == null ?
                                                                     null :
                                                                     typeAttribute.getUnit().getName(),
                                                                     typeAttribute.getByteArraySize());
                    secondaryAttribute.setNullable(typeAttribute.isNullable() || attribute.isNullable());
                    secondaryAttribute.setDefaultValue(typeAttribute.getDefaultValue());
                    secondaryAttribute.resolveReferences(root.getSpace(), 1);
                    processAttribute(secondaryAttribute);
                }
                for (MTRelationship typeRelationship : entityType.getRelationships()) {
                    MTRelationship secondaryRelationship = new MTRelationship(typeRelationship.getParserRuleContext(),
                                                                              attribute.getName()
                                                                              + ECStringUtil.Capitalize(
                                                                                      typeRelationship.getName()),
                                                                              typeRelationship.getFrom().getEntityName(),
                                                                              HalfRelationshipPlurality.ONE,
                                                                              typeRelationship.getTo().getEntityName(),
                                                                              typeRelationship.isOptional()
                                                                              || attribute.isOptional()
                                                                              || attribute.isNullable(),
                                                                              typeRelationship.isParent(),
                                                                              null, null, null);
                    secondaryRelationship.resolveReferences(root.getSpace(), 1);
                    visitRelationship(secondaryRelationship);
                }
            } else {
                SSColumn column = new SSColumn(
                        SSType.FromMTType(type, attribute.isSequential() && !attribute.isParent()), columnName,
                        attribute.getName());
                if (type.isNativeDataType(MTNativeType.DataType.BYTE_ARRAY)
                    && !((MTNativeType) attribute.getType()).isVariableSizeArray()) {
                    column.setLengthConstraint(((MTNativeType) attribute.getType()).getArraySize());
                }
                column.setPrimaryKey(attribute.isPrimaryKey());
                column.setNotNull(!attribute.isNullable());
                column.setCreation(attribute.isCreation());
                column.setModification(attribute.isModification());
                column.setSequential(attribute.isSequential());
                column.setParent(attribute.isParent());
                if (attribute.hasDefaultValue()) {
                    switch (attribute.getDefaultValue().getType()) {
                        case LONG:
                            column.setDefaultValue(attribute.getDefaultValue().getLongValue());
                            break;
                        case ENUM_ITEM:
                            column.setDefaultValue(Long.valueOf(attribute.getDefaultValue().getEnumItem().getValue()));
                            break;
                    }
                }

// We should do something like this but have it controlled via the domain
//                if (attribute.isParent()) {
//                    String delimiter = databaseDomain.getNamingForClass(
//                            MTEntity.class).getMethod().getDelimiter();
//                    String indexName = databaseDomain.getNameOfNode(attribute.getEntity())
//                                       + delimiter + databaseDomain.getNameOfNode(attribute) + delimiter
//                                       + "idx";
//                    SSIndex index = new SSIndex(indexName, currentTable, column);
//                    ECLog.logInfo("Created index: " + indexName);
//                    sourceFile.addIndex(index);
//                }

                if (attribute.isSequential() && !attribute.isParent()) {
                    SSSequence sequence = new SSSequence(column, 1, 1);
                    sourceFile.addSequence(sequence);
                }

                SSColumn parentColumn = null;
                for (MTRelationship relationship : attribute.getEntity().getRelationships()) {
                    if ((relationship.isParent() && !relationship.isOptional()) || relationship.getTo().isOne()) {
                        MTEntity relationshipToEntity = relationship.getTo().getEntity();
                        if (relationshipToEntity == null) {
                            relationshipToEntity = root.getSpace().getEntityWithName(
                                    relationship.getTo().getEntityName());
                            if (relationshipToEntity == null) {
                                ECLog.logWarning("Relationship \"" + relationship.getName() + "\" of entity \""
                                                 + attribute.getEntity().getName() + "\" cannot find a \"to\" named: "
                                                 + relationship.getTo().getEntityName());
                                continue;
                            }
                        }
                        String relationshipName = databaseDomain.getNameOfNode(relationship);
                        if (relationshipName == null) {
                            ECLog.logFatal(relationship,
                                           "Unable to get name for relationship: " + relationship.getName());
                        }
                        String parentColumnName = relationshipName + "_id";
                        parentColumn = new SSColumn(SSType.FromMTType(
                                relationshipToEntity.getPrimaryKey().getAttributes().get(0).getType()),
                                                    parentColumnName, parentColumnName);
                        column.setParentIdColumn(parentColumn);
                        break;
                    }
                }
                if (attribute.isParentUnique() && parentColumn != null) {
                    SSUniqueConstraint uniqueConstraint = new SSUniqueConstraint();
                    uniqueConstraint.addColumnName(column.getName());
                    uniqueConstraint.addColumnName(parentColumn.getName());
                    currentTable.addUniqueConstraint(uniqueConstraint);
                } else if (attribute.isUnique()) {
                    column.setUnique(attribute.isUnique());
                    currentTable.addUniqueConstraint(new SSUniqueConstraint(column.getName()));
                }
                if (attribute.hasDescription()) {
                    column.setComment(new SSComment(attribute.getDescription()));
                }
                currentTable.addColumn(column);
            }
        }
    }

    @Override
    public void visitRelationship(MTRelationship relationship) {
        if (currentTable == null) {
            return;
        }

        MTEntity fromEntity = relationship.getFrom().getEntity();
        MTEntity toEntity   = relationship.getTo().getEntity();
        if (toEntity == null || fromEntity == null) {
            relationship.resolveReferences(databaseDomain.getSpace(), 0);
            toEntity   = relationship.getTo().getEntity();
            fromEntity = relationship.getFrom().getEntity();
        }
        MTPrimaryKey toPrimaryKey = toEntity.getPrimaryKey();
        if (toPrimaryKey == null) {
            return;
        }
        FullRelationshipPlurality plurality      = relationship.getFullRelationshipPlurality();
        MTPrimaryKey              fromPrimaryKey = fromEntity.getPrimaryKey();
        if (!relationship.getFrom().getEntity().isImplicit()) {
            if (fromPrimaryKey == null && plurality != FullRelationshipPlurality.MANY_TO_ONE) {
                return;
            }
        }
        if (toPrimaryKey.getAttributes().size() != 1
            || (fromPrimaryKey != null && fromPrimaryKey.getAttributes().size() != 1)) {
            return; // unsupported right now
        }

        switch (plurality) {
            case MANY_TO_ONE:
            case ONE_TO_ONE: {
                SSColumn column = createForeignKeyColumn(relationship);
                currentTable.addColumn(column);
                SSForeignKey foreignKey = createForeignKey(relationship.getName(), column, toEntity, toPrimaryKey,
                                                           !relationship.isOptional());
                currentTable.addForeignKey(foreignKey);
                if (plurality == FullRelationshipPlurality.MANY_TO_ONE && relationship.isParent()) {
                    String delimiter = databaseDomain.getNamingForClass(
                            MTEntity.class).getMethod().getDelimiter();
                    String indexName = databaseDomain.getNameOfNode(relationship.getFrom().getEntity())
                                       + delimiter + databaseDomain.getNameOfNode(relationship) + delimiter
                                       + "idx";
                    SSIndex index = new SSIndex(indexName, currentTable, column);
                    sourceFile.addIndex(index);
                }
            }
            break;

            case MANY_TO_MANY:
            case ONE_TO_MANY:
                break; // nothing to do here, the other end handles this one
        }
    }

    private SSColumn createForeignKeyColumn(MTRelationship relationship) {
        MTPrimaryKey toPrimaryKey        = relationship.getTo().getEntity().getPrimaryKey();
        MTAttribute  primaryKeyAttribute = toPrimaryKey.getAttributes().get(0);
        MTType       type                = primaryKeyAttribute.getNonPrimaryKeyType();
        String       typeName            = "";

        if (type.isNativeType()) {
            typeName = ((MTNativeType) type).getDataType().getName();
        }

        FullRelationshipPlurality plurality = relationship.getFullRelationshipPlurality();
        MTAttribute relationshipAttribute = new MTAttribute(null, relationship.getFrom().getEntity(),
                                                            typeName, relationship.getName() + "Id");

        String columnName = databaseDomain.getNameOfNode(relationshipAttribute);
        if (columnName == null) {
            ECLog.logFatal(relationship,
                           "Can't find domain name for relationship attribute: " + relationshipAttribute.getName());
        }
        SSColumn column = new SSColumn(SSType.FromMTType(type), columnName, relationshipAttribute.getName());
        column.setUnique(plurality == FullRelationshipPlurality.ONE_TO_ONE);
        column.setNotNull(!relationship.isOptional());
        column.setPrimaryKey(false);

        return column;
    }

    private SSForeignKey createForeignKey(String relationshipName, SSColumn column, MTEntity toEntity, MTPrimaryKey
            toPrimaryKey, boolean deleteCascade) {
        String foreignTableName  = databaseDomain.getNameOfNode(toEntity);
        String foreignColumnName = databaseDomain.getNameOfNode(toPrimaryKey.getAttributes().get(0));
        SSForeignKey foreignKey = new SSForeignKey(relationshipName, column, foreignTableName, foreignColumnName,
                                                   deleteCascade);

        return foreignKey;
    }

    private String manyToManyTableName(MTEntity fromEntity, MTEntity toEntity) {
        MTEntity combinedEntity = MTEntity.CombineEntities(fromEntity, toEntity);
        return databaseDomain.getNameOfNode(combinedEntity);
    }

    private SSColumn createColumnForPrimaryKey(MTAttribute primaryKeyAttribute, boolean unique, boolean nonNull,
                                               boolean keepAsPrimaryKey) {
        MTType type = keepAsPrimaryKey ?
                      primaryKeyAttribute.getType() :
                      primaryKeyAttribute.getNonPrimaryKeyType();
        String columnName = databaseDomain.getNameOfNode(primaryKeyAttribute);
        if (columnName == null) {
            ECLog.logFatal(primaryKeyAttribute,
                           "Cannot create column name for primary key: " + primaryKeyAttribute.getName());
        }
        SSColumn column = new SSColumn(SSType.FromMTType(type), columnName, primaryKeyAttribute.getName());
        column.setUnique(unique);
        column.setNotNull(nonNull);
        column.setPrimaryKey(false);
        return column;
    }

    @Override
    public boolean flattenSecondaryEntities() {
        return databaseDomain.isFlattenSecondaryEntities();
    }

    @Override
    public String[] requiredDomainNames() {
        return new String[]{"Database"};
    }

    @Override
    public boolean canStart() {
        return hasRequiredDomains();
    }
}

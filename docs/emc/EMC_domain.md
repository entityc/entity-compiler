# Domain Model Classes

Model classes that are domain specific are of this type. The classes are briefly described in the following table, then each will be described in more detail in the sub-sections below the table.

| Class | Description |
|-----|-----|
|[`MTDEAttribute`](#class_MTDEAttribute)|Represents an attribute in your model in the context of a domain.|
|[`MTDEAttributeConstraintExpression`](#class_MTDEAttributeConstraintExpression)|Represents a constraint on an attribute in the form of an expression.|
|[`MTDERelationship`](#class_MTDERelationship)|Represents a relationship in your model in the context of a domain.|
|[`MTDEntity`](#class_MTDEntity)|Represents an entity in your model in the context of a domain.|
|[`MTDEnum`](#class_MTDEnum)|Represents an enum in the context of a domain.|
|[`MTDEnumItem`](#class_MTDEnumItem)|Represents an enum item in the context of a domain.|
|[`MTDomain`](#class_MTDomain)|Represents a domain.|

<a name="class_MTDEAttribute"></a>
## MTDEAttribute Class

Represents an attribute in your model in the context of a domain.

The following properties and methods, grouped by category, are available for this class:

### Domain Category

These methods relate to a domain.

<hr/>

#### `MTDomain` **`domain`**

Returns the domain associated with this domain attribute.


### Entity Category

These methods relate to an entity.

<hr/>

#### `MTDEntity` **`domainEntity`**

Returns the domain entity to which this belongs.


### Attribute Category

These methods relate to attributes.

<hr/>

#### `MTAttribute` **`attribute`**

Returns the model (non-domain specific) attribute of this domain attribute.

<hr/>

#### `String` **`attributeName`**

Returns the name of the model's attribute name (not the domain one).

<hr/>

#### `MTDEAttributeConstraint constraintByName(String name)`

Returns the a domain constraint by name.

| Parameter | Description |
|-----|-----|
|`String name` | The name of the constraint as declared in the model. |

<hr/>

#### `List` **`constraints`**

Returns the list of constraints that have been declared on this domain entity. The constraints are domain constraints.

<hr/>

#### `MTConstant` **`defaultValue`**

Returns the default value if one was assigned on the model attribute.

<hr/>

#### `String` **`description`**

Returns the declared domain attribute description. If that is not available then it will return the model attribute description.

<hr/>

#### `String` **`explicitAttributeName`**

For those domain attributes that were renamed explicitly, this returns that name. Otherwise `null` is returned.

<hr/>

#### `String fullname(String delim)`

This returns the full name of this domain attribute which includes not only its domain based name but is also preceded with the domain's entity's full name. The delimiter can be provided which is used between all parts of the full name.

| Parameter | Description |
|-----|-----|
|`String delim` | A delimiter to place between the segments of the domain namespace as well as between that namespace and the domain entity name and between the domain entity name and the domain attribute name. |

<hr/>

#### `boolean` **`hasReplacedBitFields`**

Indicates whether this domain attribute has defined bit fields to that replace the model bit fields.

<hr/>

#### `boolean` **`hasUnit`**

Indicates whether this attribute was declared with a unit of measure.

<hr/>

#### `boolean` **`isVirtual`**

Indicates whether this attribute was declared as virtual. A virtual attribute is one in which you do not persist and is driven by code synthesized from a template.

<hr/>

#### `String` **`name`**

Returns the domain attribute name which is the result of applying any defined naming conventions or explicit renaming.

<hr/>

#### `List` **`replacedBitFields`**

Returns the domain-specific bit fields (replacing the model defined ones)

<hr/>

#### `int` **`sinceSchemaVersion`**

As new attributes are added it can be helpful to have a record of when an attribute was introduced. The "since schema version" provides the schema version that this attribute was added.

<hr/>

#### `MTType` **`type`**

Returns the type of this attribute.

<hr/>

#### `MTDEntity` **`typeEntity`**

If the type of this attribute is of an entity type, then that domain entity is returned. Otherwise `null` is returned.

<hr/>

#### `MTDEnum` **`typeEnum`**

If this domain attribute is of enum type, then the domain enum is returned as such.

<hr/>

#### `MTUnit` **`unit`**

If this attribute was declared with a unit of measure it returns that unit. Otherwise it will return `null`.


### View Category

These methods relate to views.

<hr/>

#### `String` **`viewName`**

Returns the name of the view that this domain attribute to which this is associated.


### Configuration Category

These methods relate to a part of application configuration.

<hr/>

#### `MTSpace` **`space`**

Returns the space in which this attribute was declared.



<a name="class_MTDERelationship"></a>
## MTDERelationship Class

Represents a relationship in your model in the context of a domain.

The following properties and methods, grouped by category, are available for this class:

### Domain Category

These methods relate to a domain.

<hr/>

#### `MTDomain` **`domain`**

Returns the domain associated with this domain relationship.


### Entity Category

These methods relate to an entity.

<hr/>

#### `MTDEntity` **`domainEntity`**

Returns the parent domain entity of this domain relationship.


### Relationship Category

These methods relate to relationships.

<hr/>

#### `String` **`description`**

Returns the description of this domain relationship, if it exists. Otherwise it will return `null`.

<hr/>

#### `String` **`explicitName`**

If this relationship was explicitly renamed within its domain, it will return that name. Otherwise it will return `null`.

<hr/>

#### `String fullname(String delim)`

This returns the full name of this domain relationship which includes not only its domain based name but is also preceded with the domain's entity's full name. The delimiter can be provided which is used between all parts of the full name.

| Parameter | Description |
|-----|-----|
|`String delim` | A delimiter to place between the segments of the domain namespace as well as between that namespace and the domain entity name and between the domain entity name and the domain relationship name. |

<hr/>

#### `boolean` **`hasDescription`**

Indicates whether this domain relationship has a description.

<hr/>

#### `boolean` **`isManyToMany`**

Indicates if this relationship is a many-to-many.

<hr/>

#### `boolean` **`isOneToMany`**

Indicates if this relationship is a one-to-many.

<hr/>

#### `boolean` **`isOptional`**

Returns whether this relationship was declared as optional.

<hr/>

#### `boolean` **`isParent`**

Returns whether this relationship was declared as a parent.

<hr/>

#### `boolean` **`isPrimaryParent`**

Returns whether this relationship was declared as a parent and **not** declared optional.

<hr/>

#### `String` **`name`**

Returns the domain relationship name which is the result of applying any defined naming conventions or explicit renaming.

<hr/>

#### `MTRelationship` **`relationship`**

Returns the model relationship.

<hr/>

#### `String` **`relationshipName`**

Returns the name of the model relationship as it was declared.

<hr/>

#### `MTDERelationshipHalf` **`to`**

Returns the "to" half of this domain relationship.


### Tagging Category

These methods relate to the tagging.

<hr/>

#### `boolean hasToEntityTagged(String tag)`

Returns whether this relationship's "to" entity is tagged with the specified tag.

| Parameter | Description |
|-----|-----|
|`String tag` | The tag with which to check. |



<a name="class_MTDEntity"></a>
## MTDEntity Class

Represents an entity in your model in the context of a domain.

The following properties and methods, grouped by category, are available for this class:

### Domain Category

These methods relate to a domain.

<hr/>

#### `MTDomain` **`domain`**

Returns the domain associated with this domain entity.


### Module Category

These methods relate to a module.

<hr/>

#### `MTModule` **`module`**

Returns the module associated with this domain entity.


### Entity Category

These methods relate to an entity.

<hr/>

#### `Collection` **`attributes`**

Returns the full list of domain attributes of this domain entity.

<hr/>

#### `String` **`description`**

Returns the description of this domain entity. If the domain entity does not have its own description, then the description of the entity is returned.

<hr/>

#### `MTEntity` **`entity`**

This returns the non-domain specific entity on which this domain entity is based.

<hr/>

#### `String` **`entityName`**

This returns the name of the non-domain specific entity on which this domain entity is based.

<hr/>

#### `String` **`explicitName`**

This returns the explicitly defined name for this domain entity. If no such name was defined it returns `null`.

<hr/>

#### `Collection` **`flatSecondaryEntityList`**

Returns a list of all the secondary domain entities hierarchically that make up this domain entity.

<hr/>

#### `String fullname(String delim)`

This returns the full name of this domain entity which includes not only its domain based name but is also preceded with the domain's namespace and the delimiter used in the namespace and between the namespace and the domain entity name can be provided.

| Parameter | Description |
|-----|-----|
|`String delim` | The delimiter string to use between segments of the namespace and between the namespace and the domain entity name. |

<hr/>

#### `String` **`name`**

This returns the name of this domain entity. If the domain has any naming conventions or this entity was explicitly renamed, then this name will be different than the entity from which it is based.

<hr/>

#### `int` **`sinceSchemaVersion`**

As new entities are added it can be helpful to have a record of when an entity was introduced. The "since schema version" provides the schema version that this entity was added.


### Primary Key Category

These methods relate to a primary key.

<hr/>

#### `MTDEAttribute domainPrimaryKeyAttribute(boolean createIfNeeded)`

Returns the domain specific version of the entity's primary key attribute.

| Parameter | Description |
|-----|-----|
|`boolean createIfNeeded` | If the domain based primary key attribute has not yet been created, specifying `true` will make sure it is created before returning it. Otherwise `null` will be returned if it was not yet created. |

<hr/>

#### `boolean` **`hasPrimaryKey`**

Indicates whether this domain entity has a primary key.

<hr/>

#### `MTType` **`pkType`**

Returns the data type of the primary key attribute of this domain entity.

<hr/>

#### `MTDEAttribute` **`primaryKeyAttribute`**

Returns the primary key attribute of this domain entity.


### Attribute Category

These methods relate to attributes.

<hr/>

#### `MTDEAttribute attributeNamed(String name)`

Returns the domain attribute by its name if it exists, otherwise it returns `null`.

| Parameter | Description |
|-----|-----|
|`String name` | The name of the domain attribute to return. |

<hr/>

#### `Collection` **`declaredAttributes`**

Returns a list of all the declared domain attributes of this domain entity that are declared in this domain hierarchically (secondary entity hierarchy).

<hr/>

#### `List` **`declaredDomainAttributes`**

Returns the list of domain attributes that are explicitly defined in the domain.

<hr/>

#### `MTDEAttribute domainAttributeByName(String attributeName, boolean createIfNeeded)`

Returns the domain specific version of the specified attribute.

| Parameter | Description |
|-----|-----|
|`String attributeName` | The name of the attribute to return. |
|`boolean createIfNeeded` | If the domain based attribute has not yet been created, specifying `true` will make sure it is created before returning it. Otherwise `null` will be returned if it was not yet created. |

<hr/>

#### `Collection` **`domainAttributes`**

Returns all the domain attributes from this domain entity.

<hr/>

#### `Collection flatDeclaredAttributeList(MFArray ofTypes)`

Returns a list of domain attributes of this domain entity that are of one of the specified data types and that are declared in this domain hierarchically (secondary entity hierarchy).

| Parameter | Description |
|-----|-----|
|`MFArray ofTypes` | *no description* |

<hr/>

#### `Collection` **`flatDeclaredAttributeList`**

Returns a list of all the domain attributes of this domain entity that are declared in this domain hierarchically (secondary entity hierarchy).

<hr/>

#### `boolean hasAttributeNamed(String name)`

Indicates whether this domain entity has an attribute by the specified name.

| Parameter | Description |
|-----|-----|
|`String name` | The name of the domain attribute of which to check the presence. |

<hr/>

#### `boolean` **`hasDeclaredDomainAttributes`**

Indicates if any attributes were declared in this domain entity declaration.

<hr/>

#### `List` **`orderedDomainAttributes`**

Returns an ordered list of domain attributes, where the order is defined by the order in which they are defined. It also includes attributes that were not defined, where they are placed at the end of the list.


### Relationship Category

These methods relate to relationships.

<hr/>

#### `List` **`declaredDomainRelationships`**

Returns the list of domain relationships that are explicitly defined in the domain.

<hr/>

#### `MTDERelationship domainEntityRelationshipByName(String name, boolean createIfNeeded)`

Returns the domain specific version of the specified relationship.

| Parameter | Description |
|-----|-----|
|`String name` | The name of the relationship to return. |
|`boolean createIfNeeded` | If the domain based relationship has not yet been created, specifying `true` will make sure it is created before returning it. Otherwise `null` will be returned if it was not yet created. |

<hr/>

#### `boolean` **`hasPrimaryParentRelationship`**

Indicates whether this domain entity has a primary parent relationship. A primary parent relationship is one that has been declared as `parent` and **not** declared `optional`.

<hr/>

#### `MTDEntity` **`primaryParentEntity`**

Returns the primary parent entity to this domain entity. A primary parent entity is the entity referenced by a relationship of this entity that has been declared as a `parent` relationship and **not** declared `optional`.

<hr/>

#### `MTDERelationship` **`primaryParentRelationship`**

Returns the primary parent relationship of this domain entity. A primary parent relationship is one that has been declared as `parent` and **not** declared `optional`.

<hr/>

#### `Collection` **`relationships`**

Returns the all the domain relationships of this domain entity.


### Tagging Category

These methods relate to the tagging.

<hr/>

#### `MTDEAttribute attributeOfTypeTagged(String tag)`

Returns the first domain attribute of this domain entity that has the specified tag. Of course, in theory there could be more than one, so this should be used when a template is expecting there to be just one per domain entity.

| Parameter | Description |
|-----|-----|
|`String tag` | The tag with which to search. |

<hr/>

#### `MTDEAttribute attributeTagged(String tag)`

Returns the domain attribute tagged with the specified tag. Although it is possible to have more than one, this method is used when expecting just one by nature of the tag.

| Parameter | Description |
|-----|-----|
|`String tag` | The tag with which to search. |

<hr/>

#### `Collection attributesTagged(String tag)`

Returns all the domain attributes tagged with the specified tag.

| Parameter | Description |
|-----|-----|
|`String tag` | The tag with which to search. |

<hr/>

#### `boolean hasAttributeOfTypeTagged(String tag)`

Indicates if this domain entity has a domain entity with the specified tag.

| Parameter | Description |
|-----|-----|
|`String tag` | The tag with which to search. |

<hr/>

#### `boolean hasAttributeTagged(String tag)`

Indicates whether this domain entity has an attribute tagged with the specified tag.

| Parameter | Description |
|-----|-----|
|`String tag` | The tag with which to search. |

<hr/>

#### `boolean hasAttributeWithTagPrefixed(String tagPrefix)`

Indicates whether this domain entity has an attribute who's tag starts with the specified tag prefix.

| Parameter | Description |
|-----|-----|
|`String tagPrefix` | The tag prefix with which to search. |

<hr/>

#### `boolean hasPrimaryParentEntityTagged(String tag)`

Indicates whether a primary parent entity to this domain entity has been tagged with the specified tag. A primary parent entity is the entity referenced by a relationship of this entity that has been declared as a `parent` relationship and **not** declared `optional`.

| Parameter | Description |
|-----|-----|
|`String tag` | The tag with which to search. |

<hr/>

#### `boolean hasRelationshipTagged(String tag)`

Indicates whether this domain entity has a domain relationship with the specified tag.

| Parameter | Description |
|-----|-----|
|`String tag` | The tag with which to search. |

<hr/>

#### `boolean hasRelationshipToEntityTagged(String tag)`

Indicates whether this domain entity has a relationship **to** a domain entity with the specified tag.

| Parameter | Description |
|-----|-----|
|`String tag` | The tag with which to search. |

<hr/>

#### `MTDEntity primaryParentEntityTagged(String tag)`

Returns the primary parent entity to this domain entity if it has been tagged with the specified tag. A primary parent entity is the entity referenced by a relationship of this entity that has been declared as a `parent` relationship and **not** declared `optional`.

| Parameter | Description |
|-----|-----|
|`String tag` | The tag to check. |

<hr/>

#### `MTDERelationship relationshipTagged(String tag)`

Returns the domain relationship tagged with the specified tag. Although it is possible to have more than one, this method is used when expecting just one by nature of the tag.

| Parameter | Description |
|-----|-----|
|`String tag` | The tag with which to search. |

<hr/>

#### `MTDERelationship relationshipToEntityTagged(String tag)`

Returns the first found domain relationship of this domain entity that is tagged with the specified tag. Although it is possible to have more than one, this method is used when expecting just one by nature of the tag.

| Parameter | Description |
|-----|-----|
|`String tag` | The tag with which to search. |

<hr/>

#### `Collection relationshipsTagged(String tag)`

Returns all the domain relationships tagged with the specified tag.

| Parameter | Description |
|-----|-----|
|`String tag` | The tag with which to search. |


### Configuration Category

These methods relate to a part of application configuration.

<hr/>

#### `MTSpace` **`space`**

Returns the space in which this domain entity was defined.


### Interface Category

These methods relate to an interface.

<hr/>

#### `MTDEInterface interface(String name)`

This returns an interface by name that was defined as part of this domain entity.

| Parameter | Description |
|-----|-----|
|`String name` | *no description* |

<hr/>

#### `Collection` **`interfaces`**

Returns all the interfaces associated with this domain entity.





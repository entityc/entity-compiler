# Tagging Model Classes

Model classes used to describe tags are of this type. The classes are briefly described in the following table, then each will be described in more detail in the sub-sections below the table.

| Class | Description |
|-----|-----|
|[`MTTagDef`](#class_MTTagDef)|This class represents the definition of a tag. This is mostly to allow documentation of tags that are used in templates and placed on entities and attributes (including domain entities and domain attributes).|
|[`MTTagValueDef`](#class_MTTagValueDef)|This class represents the definition of an optional value that can be assigned to a tag.|

<a name="class_MTTagDef"></a>
## MTTagDef Class

This class represents the definition of a tag. This is mostly to allow documentation of tags that are used in templates and placed on entities and attributes (including domain entities and domain attributes).

The following properties and methods, grouped by category, are available for this class:

### Type Category

These methods relate to data types.

<hr/>

#### `MTTagValueDef` **`valueDef`**

Returns an object that describes the data type of a value assigned to this tag.


### Tagging Category

These methods relate to the tagging.

<hr/>

#### `boolean` **`hasValue`**

Indicates whether this tag is assigned a value.

<hr/>

#### `boolean` **`isStartsWith`**

Indicates whether this tag is one in which the developer would use as a tag prefix, that is all tags that start with this string value are associated with this tag.

<hr/>

#### `String` **`name`**

Returns the tag name (this is the same as the `tag` property).

<hr/>

#### `String` **`tag`**

Returns the tag as a string.

<hr/>

#### `MTTagContext` **`tagContext`**

Returns an object that describes the context when this tag would be used.



<a name="class_MTTagValueDef"></a>
## MTTagValueDef Class

This class represents the definition of an optional value that can be assigned to a tag.

The following properties and methods are available for this class:

<hr/>

#### `DataType` **`dataType`**

Returns the data type expected for a value set on a tag.





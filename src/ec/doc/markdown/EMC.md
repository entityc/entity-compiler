$[let doNotEditMessage = "DO NOT EDIT THIS DOCUMENT, IT WAS GENERATED BY FILE: " + __template.name]
$[let alarm = "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"]
[//]: # (${alarm})
[//]: # (${doNotEditMessage})
[//]: # (${alarm})

# Entity Model Classes

As the Entity Compiler parses your model (written in the Entity Definition Language), it creates a tree of Entity Model Class objects. The most obvious class would be the one that describes an entity which is called `MTEntity`.  The top nodes of this tree is given to each template that is run, allowing it to extract any information about your entity model that it needs to generate the code it is designed to generate.

These entity model classes are grouped into the following types:

$[let manager = __documentationManager]

| Model Class Type | Description |
|---|---|
$[foreach type in manager.modelClassTypes]
$[if manager.modelClassCount(type) == 0]$[continue]$[/if]
| [${type.title}](EMC_${type.packageName}.md) | ${type.description} |
$[/foreach]

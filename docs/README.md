# Entity Compiler Documentation

This directory contains all the formal documentation for the compiler.

> Do **not** edit any of the markdown files in this directory. They are either synthesized from documentation 
> contained in the source code of the compiler or copied from the `src/ec/doc/markdown` directory.

It is organized in the following major categories:

|         Category         | Description |
|-------------------------------------------------|---|
| [Entity Description Language (EDL)](edl/EDL.md) | This is the language used to define your entities, domains, languages, interfaces, units and finally how to configure an application/project so that the Entity Compiler can generate source code. |
| [Entity Transform Language (ETL)](etl/ETL.md)   | Templates, written in the Entity Transform Language, are used to transform your entity and domain definitions into code or other renderings. A template typically looks very much like what it is going to generate except that it has special tags to insert variables, repeat blocks of your template, conditionally include blocks of your template, and many other powerful features for constructing code. |
| [Entity Model Classes (EMC)](emc/EMC.md)        | As the Entity Compiler parses your model (written in the Entity Definition Language), it creates a tree of Entity Model Class objects. The most obvious class would be the one that describes an entity which is called `MTEntity`.  The top nodes of this tree is given to each template that is run, allowing it to extract any information about your entity model that it needs to generate the code it is designed to generate. |
| [Built-in Transforms](Transforms.md)            | The compiler has a built-in transform for creating a Postgres database from your entity model. A transform is basically like a template but written in the compilers native language.|

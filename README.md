# Entity Compiler

[![Java 8+](https://img.shields.io/badge/java-8+-4c7e9f.svg)](http://java.oracle.com)
[![License](https://img.shields.io/badge/license-BSD-blue.svg)](https://raw.githubusercontent.com/antlr/antlr4/master/LICENSE.txt)

The **Entity Compiler** provides a language to model entities in your enterprise application along with a compiler and template engine to synthesize code responsible for the storage, transfer and management of data relating to those entities.

The entities are specified in an implementation agnostic way, you then apply templates that synthesize the entities to code. You can write your own templates or use templates shared by others via a github repository.

## Project Goals

The initial goal of the Entity Compiler is to allow an organization to standardize on a representation of the entities used in the organization and then provide the tools for each team in the organization to synthesize those entities (using templates) to their code base. This gives product features an even playing field across server and client components of the organization's enterprise application. The initial goals are:

- Standardization of entity representation
- Entity definitions under source management (currently `git` is supported) and accessible by all teams.
- Allow each team in an organization to develop their own templates or share common templates also stored under source management and accessible by all teams.
- Promote collaboration across all teams of a larger enterprise application.
- Primarily targets the data layer of the enterprise application (typically boilerplate - not fun to write code) freeing up developers to spend time writing the business logic (the fun stuff).
- As an organization builds a library of templates,  building the systems those templates target is much much faster. For instance, if you construct a set of templates to build a microservice, creating a second microservice based on different entities is extremely fast.

The ultimate goal of the Entity Compiler is to allow the sharing of templates and even entities across the industry.

- Faster adoption of technology and standards. Open source projects that wish to promote their standard (e.g., Spring Boot, Quarkus, Django, Flask, etc.) would provide Entity Compiler templates that would allow a potential adopter to quickly synthesize a starting point for their project based on that standard.
- Promotes collaboration between teams across the industry.

Most of all, the goal of this project is to bring together people that share a vision of putting entities at the top of an organization's enterprise while also accelerating development through a synthesis of entities to code.

## Documentation

Documentation on the compiler and template language can be found in the [docs](docs) directory. It reads more like a reference manual than a document for learning. A better place to start for learning are the tutorials.

## Tutorials

The best way to understand how the Entity Compiler works is to check out the [tutorials](https://github.com/entityc/ec-tutorials). There are currently two tutorials:

|Tutorial|Description|
| ------	| ---------	|
| [Entity Compiler](https://github.com/entityc/ec-tutorials/EntityCompiler) | Gives you the basics of the Entity Compiler, both its entity language and template engine. |
| [Tutorial Microservice](https://github.com/entityc/ec-tutorials/TutorialMicroservice) | Demonstrates the potential of the Entity Compiler by building an entire microservice along with an Web Admin Console and student website.|

## Libraries

The following libraries are available that support the Entity Compiler. Each has their own Github repository:

| <div style="width:150px">Library</div>| Description |
|:----------------- | -----------	|
| [ec-std-lib](https://github.com/entityc/ec-std-lib) | The intention of this library is to serve as a base set of domains, languages, units, etc. that can be used with most applications of the Entity Compiler. They attempt to be language agnostic and can help to provide some standardization at a basic level.|
|  [ec-springboot-lib](https://github.com/entityc/ec-springboot-lib) | This library contains templates and domains specific for building Spring Boot microservices. |

## State of the Project

Although many working microservices have been built using this compiler and its two template libraries, they are not quite ready for generating production level code. They should be considered Alpha level - not optimized for performance, low test coverage and not functionally complete. It is certainly at a level to play with and see the capability it provides in synthesizing code. For those who wish to develop their own template library you could achieve production quality that way, but you are encouraged to contribute with others on an open source library such as the two above.  

## Licensing

All projects of the EntityC Organization are under the BSD 3-clause License.

See [LICENSE.md](LICENSE.md) for details.

## Contributing

Contributors are welcome in all areas of an enterprise application from database to web design.  In contributing to improve existing templates or adding new ones, you are in effect improving every enterprise application that uses them to build their code.

Before contributing please read the following [Contributing Guidelines](CONTRIBUTING.md).

Below are areas where substantial work has been done but where contribution could greatly improve them. Anyone new to the compiler may want to start in one of these areas.

| Existing Area | What is needed |
| -------------	| -------------- |
| Database      | Currently only Postgres is supported but even that can be improved. It is written not as a template but as a transform in Java code. Other database support could be developed in the same way or using templates.|
| Spring Boot Microservice | A large set of templates already [exist](https://github.com/entityc/ec-springboot-lib) and allow you to build a basic microservice but it could use many improvements not to just the base microservice but also add-on features. |
| Bootstrap based Web Admin Console | The Spring Boot Microservice library includes templates to synthesize a web based Admin Console (using Thymeleaf and Bootstrap) but the UI/UX of the console is minimal and can use a lot of work. |
| Testing | In each of the above areas, test case development is still very much needed. This may be a good area of contribution for people who are learning the compiler. Those more familiar with the compiler can help to validate the test cases.  |
| IntelliJ Plugins | There are two IntelliJ plugins, one for the Entity Description Language and another for the Entity Transform Language, however each only support syntax highlighting. It would be way better if they also supported code completion, alt-clicking to go to definition, find usages, refactoring, etc. |

The following are new areas that would widen the application of the compiler:

| New Area | Description |
| --------	| -----------	|
| Python-based Microservice | To demonstrate the flexibility of the Entity Compiler it would be great to have a set of templates to synthesize a Python based microservice.|
| Ruby-based Microservice | Again, to demonstrate the flexibility of the Entity Compiler it would be great to have a set of templates to synthesize a Ruby based microservice.|
| Kotlin/Java Client API | Having the ability to synthesize a network API in Java or Kotlin would make it easy for Android apps to interface with a microservice also built using the Entity Compiler.|
| Swift/Objective-C Client API | Likewise for iOS synthesizing a network API in Swift or Objective-C would make it easy for iOS apps to interface with a microservice also built using the Entity Compiler.|
| Other IDE Plugins | Adding support to other IDEs such as Eclipse and Visual Studio Code would be a huge help. |

## Code of Conduct

The Code of Conduct governs how we behave in public or in private whenever the project will be judged by our actions. We expect it to be honored by everyone who contributes to this project.

See [Code of Conduct](CODE_OF_CONDUCT.md) for details.

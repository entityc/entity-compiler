[//]: # (!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!)
[//]: # (DO NOT EDIT THIS DOCUMENT, IT WAS GENERATED BY FILE: GettingStarted.md)
[//]: # (!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!)

# Getting Started

## Prerequisites

You will need to have the following installed on your machine:

* Java 8 (version 1.8) or above.
* Apache **Maven** 3.6.0 or above.

## Installing IntelliJ Plugins

If you are using IntelliJ, installing EntityC plugins greatly enhances your development. Although only code highlighting and template code formatting is supported, just that is very helpful.

| Plugin | Description |
| ------	| -----------	|
| `Entity-Model-JetBrains-Plugin-0.1.zip`| Supports syntax highlighting for entity definition language files (`.edl`).|
| `Entity-Template-JetBrains-Plugin-0.1.zip`| Supports syntax highlighting for the template language files (`.eml`).|

These plugin files are located in the `bin` directory of this tutorial.

To install:

1. Open IntelliJ and go to **Preferences...**.
2. On the left side of the Preferences panel, select **Plugins**.
3. Just below the panel title bar is another bar that has a settings gear icon towards the right. Click on that gear icon and select **Install Plugin from Disk...**.
4. Now select the first plugin in the table above from the tutorial's `bin` directory and hit ok.
5. At this point, **do not restart IntellJ**, instead repeat the procedure for the other plugin above.
6. Even after the second plugin install, **do not restart IntelliJ** even when it prompts you.
7. Hit OK on the preferences panel to save and close it.
8. **Quit** IntelliJ and re-launch

The reason quitting and relaunching IntelliJ instead of restarting is because sometimes restarting from its prompts does not work properly.


## Building the Compiler

## Running

## Where to go from here

The best place to start is to take the [Entity Compiler tutorial](https://github.com/entityc/ec-tutorials/EntityCompiler). It steps you through the basics then all the way up to the more advanced features. When you have finished that base tutorial there is another tutorial that steps you through building a complete [Spring Boot based microservice](https://github.com/entityc/ec-tutorials/TutorialMicroservice).

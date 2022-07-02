/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.entityc.compiler.cmdline.CommandLine;
import org.entityc.compiler.model.MTRoot;
import org.entityc.compiler.model.config.MTConfiguration;
import org.entityc.compiler.model.config.MTProtoc;
import org.entityc.compiler.model.config.MTRepositoryImport;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.config.MTSpaceInclude;
import org.entityc.compiler.model.config.MTTemplate;
import org.entityc.compiler.model.config.MTTransform;
import org.entityc.compiler.protobuf.PBLoaderExtractor;
import org.entityc.compiler.repository.RepositoryCache;
import org.entityc.compiler.repository.RepositoryFile;
import org.entityc.compiler.repository.RepositoryImportManager;
import org.entityc.compiler.transform.MTBaseTransform;
import org.entityc.compiler.transform.TransformManager;
import org.entityc.compiler.transform.template.FileTemplateTransform;
import org.entityc.compiler.transform.template.TemplatePublishing;
import org.entityc.compiler.transform.template.TemplateTransform;
import org.entityc.compiler.util.ECANTLRErrorListener;
import org.entityc.compiler.util.ECLog;
import org.entityc.compiler.util.ECStringUtil;
import org.entityc.compiler.util.LogHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static java.lang.System.exit;

public class EntityCompiler {

    public static final  String              COMPILER_VERSION   = "0.13.1";
    public static final  String              LANGUAGE_VERSION   = "0.12.3";
    private static final Map<String, String> defineValues       = new HashMap<>();
    private static final Set<String>         templateSearchPath = new HashSet<>();
    private static       CommandLine         commandLine;

    public static void main(String[] args) {

        setupLogger();

        commandLine = new CommandLine();
        commandLine.run(args);
    }

    private static void setupLogger() {
        LogManager.getLogManager().reset();
        Logger rootLogger = LogManager.getLogManager().getLogger("");
        rootLogger.addHandler(new LogHandler());
    }

    public static void RunConfiguration(MTConfiguration configuration) {
        MTRoot root = configuration.getRoot();
        EntityCompiler.LoadTransforms(root, configuration);
        EntityCompiler.RunTransforms(root, configuration);
        EntityCompiler.RunProtoc(root, configuration);
    }

    public static void LoadTransforms(MTRoot root, MTConfiguration configuration) {
        String configurationName = configuration.getName();
        // Load any built in transforms (such as the postgres one)
        TransformManager.LoadBuiltins(root, configurationName);

        // templates
        boolean failedToLoadTransform = false;
        for (MTTransform transformSpec : configuration.getTransforms()) {
            if (transformSpec.isTemplate()) {
                MTTemplate template = (MTTemplate) transformSpec;
                if (commandLine.templateToRun != null && !template.getName().equals(commandLine.templateToRun)) {
                    continue;
                }
                RepositoryFile repositoryFile;
                String         templateFilename;
                if (template.getRepositoryImport() != null) {
                    if (commandLine.verbose) {
                        ECLog.logInfo("Getting template " + template.getName() + " from repository: "
                                      + template.getRepositoryImport().getRepositoryName());
                    }
                    RepositoryImportManager importManager = new RepositoryImportManager(
                            RepositoryCache.CacheStructure.UserCache);
                    repositoryFile = importManager.importFromRepository(root.getSpace(), template.getRepositoryImport(),
                                                                        "eml", false);
                    if (repositoryFile == null) {
                        ECLog.logFatal("Unable to import template: " + template.getName());
                    }
                    templateFilename = repositoryFile.getFilepath();
                } else {
                    templateFilename = transformSpec.getName() + ".eml";
                }
                FileTemplateTransform templateTransform = new FileTemplateTransform(root, configurationName, template,
                                                                                    templateFilename);
                TransformManager.AddTransform(templateTransform);
            }
            MTBaseTransform transform = TransformManager.GetTransformByName(transformSpec.getName());
            if (transform == null) {
                ECLog.logError("Unable to find builtin transform named: " + transformSpec.getName());
                failedToLoadTransform = true;
            } else if (!transform.canStart()) {
                ECLog.logError("Cannot start transform named: " + transformSpec.getName());
                failedToLoadTransform = true;
            }
        }
        if (failedToLoadTransform) {
            exit(1);
        }
    }

    public static void RunTransforms(MTRoot root, MTConfiguration configuration) {
        root.resolveReferences(false);
        //model.processAssetAttributes();
        root.getSpace().checkValidReferences();

        TransformManager.GetTransformByName("Implicit").start();

        for (MTTransform transformSpec : configuration.getTransforms()) {
            MTBaseTransform transform = TransformManager.GetTransformByName(transformSpec.getName());
            if (transform instanceof FileTemplateTransform) {
                continue; // skip templates for now
            }
            if (commandLine.verbose) {
                System.out.println("Running " + "transform" + " " + transform.getName());
            }
            transform.start();
        }

        root.resolveReferences(true);
        root.getSpace().checkValidReferences();

        ArrayList<FileTemplateTransform> fileTemplatesToRun = new ArrayList<>();
        for (MTTransform transformSpec : configuration.getTransforms()) {
            if (transformSpec instanceof MTTemplate) {
                if (((MTTemplate) transformSpec).isContextual()) {
                    continue; // don't automatically run these
                }
                MTBaseTransform transform = TransformManager.GetTransformByName(transformSpec.getName());
                if (transform instanceof FileTemplateTransform) {
                    TemplateTransform templateTransform = (TemplateTransform) transform;
                    templateTransform.setConfig(transformSpec.getConfig());
                    if (commandLine.verbose) {
                        System.out.println("Loading template " + templateTransform.getName());
                    }
                    templateTransform.load();
                    fileTemplatesToRun.add((FileTemplateTransform) templateTransform);
                    if (commandLine.verbose) {
                        System.out.println("Finished Loading template " + templateTransform.getName());
                    }
                }
            }
        }
        // Now that they are all parsed we can try to connect publish submissions to outlets.

        TemplatePublishing publishingHouse = new TemplatePublishing();

        for (FileTemplateTransform transform : fileTemplatesToRun) {
            transform.getTemplate().processPublishing(publishingHouse, TemplatePublishing.Mode.IndexPublishers);
        }
        for (FileTemplateTransform transform : fileTemplatesToRun) {
            transform.getTemplate().processPublishing(publishingHouse, TemplatePublishing.Mode.PairAuthorsToOutlets);
        }

        for (FileTemplateTransform transform : fileTemplatesToRun) {
            transform.run();
        }
    }

    public static void RunProtoc(MTRoot root, MTConfiguration configuration) {
        // PROTOBUF COMPILER
        for (MTProtoc protoc : configuration.getProtocs()) {

            String languageOption = null;
            if (protoc.getLanguageName() == null) {
                ECLog.logError(protoc, "Skipping protoc because no language was defined.");
                continue;
            }

            RepositoryImportManager importManager = new RepositoryImportManager(
                    RepositoryCache.CacheStructure.TempDirectory);
            String baseDirectoryPath = importManager.getRepositoryCache().getBaseCacheDirectory().getPath();

            if (protoc.getLanguageName().equals("cpp")) {
                languageOption = "--cpp_out=" + baseDirectoryPath;
            } else if (protoc.getLanguageName().equals("java")) {
                languageOption = "--java_out=" + baseDirectoryPath;
            }

            if (languageOption == null) {
                ECLog.logError(protoc, "Skipping protoc because specified language \"" + protoc.getLanguageName()
                                       + "\" is not supported.");
                continue;
            }

            for (MTRepositoryImport repositoryImport : protoc.getRepositoryImports()) {
                importManager.importFromRepository(root.getSpace(), repositoryImport, "proto",
                                                   repositoryImport.isIncludeOnly());
            }

            ProcessBuilder processBuilder = new ProcessBuilder("protoc", languageOption,
                                                               "--proto_path=" + baseDirectoryPath);
            for (RepositoryFile repositoryFile : importManager.getRepositoryFiles()) {
                processBuilder.command().add(repositoryFile.getFilepath());
            }
            File errorOutputFile = new File(baseDirectoryPath + File.separator + "ERROR.txt");
            processBuilder.redirectError(errorOutputFile);
            try {
                if (EntityCompiler.isVerbose()) {
                    ECLog.logInfo("Running PROTOC...");
                }
//                ECLog.logInfo("Running PROTOC: " + processBuilder.command());
                Process process = processBuilder.start();
                process.waitFor();
            } catch (IOException e) {
                ECLog.logFatal("Unable to run protoc with exception: " + e);
                e.printStackTrace();
            } catch (InterruptedException e) {
                ECLog.logFatal("Unable to complete running protoc: " + e.getStackTrace());
            }

            for (MTSpaceInclude modelInclude : protoc.getIncludes()) {
                List<String> enumNames = modelInclude.getImportEnumNames();
                if (enumNames == null || enumNames.size() == 0) {
                    continue;
                }
                //ECLog.logInfo("Found enums to extract: " + String.join(", ", enumNames));
                for (MTRepositoryImport repositoryImport : modelInclude.getImports()) {
                    RepositoryFile repositoryFile = importManager.getRepositoryFileByName(
                            repositoryImport.getIdentifier());
                    if (repositoryFile == null) {
                        ECLog.logFatal("Unable to find repository file " + repositoryImport.getIdentifier()
                                       + " from repository " + repositoryImport.getRepositoryName());
                    }
                    String            protoFilepath     = repositoryFile.getFilepath();
                    PBLoaderExtractor pbLoaderExtractor = new PBLoaderExtractor();
                    pbLoaderExtractor.load(protoFilepath);
                    List<String> containingEnums = pbLoaderExtractor.matchingEnumNames(enumNames);

                    if (containingEnums.size() > 0) {
                        //ECLog.logInfo("Found enums (" + String.join(", ", containingEnums) + ") in: " + repositoryImport.getFilename());
                        // create a sub directory to put a smaller version of this proto file with just the specified enums
                        // then run protoc there and copy the output to the appropriate place
                        File subDirectory = new File(baseDirectoryPath + File.separator + "partials");
                        ensureDirectory(subDirectory);
                        File partialProtoFile = new File(
                                subDirectory.getAbsolutePath() + File.separator + repositoryImport.getFilename()
                                + ".proto");
                        try {
                            PrintStream fps = new PrintStream(partialProtoFile);
                            fps.println("syntax = \"proto2\";");
                            pbLoaderExtractor.extractEnumsAsProto(containingEnums, fps);
                            fps.close();
                            //ECLog.logInfo("Wrote partial proto: " + partialProtoFile.getAbsolutePath());
                        } catch (FileNotFoundException e) {
                            ECLog.logFatal("Unable to write to file: " + partialProtoFile.getAbsolutePath());
                        }
                        // invoke protoc on it and copy resulting file
                        // allow this to overwrite the same output files by leaving the languageOption the same
                        ProcessBuilder procBuilder = new ProcessBuilder("protoc", languageOption, "--proto_path="
                                                                                                  + subDirectory.getAbsolutePath());
                        procBuilder.command().add(partialProtoFile.getAbsolutePath());
                        File errorOutFile = new File(subDirectory.getAbsolutePath() + File.separator + "ERROR.txt");
                        procBuilder.redirectError(errorOutFile);
                        try {
                            //ECLog.logInfo("Running PROTOC on partial enum proto: " + procBuilder.command());
                            Process process = procBuilder.start();
                            process.waitFor();
                        } catch (IOException e) {
                            ECLog.logFatal("Unable to run protoc with exception: " + e.getStackTrace());
                        } catch (InterruptedException e) {
                            ECLog.logFatal("Unable to complete running protoc: " + e.getStackTrace());
                        }
                        if (errorOutFile.length() != 0) {
                            // Look to see if there are any errors
                            ECLog.logError("Trouble running protoc in directory: " + subDirectory.getAbsolutePath());
                            ECLog.logError("> " + procBuilder.command());
                            try {
                                FileInputStream fis = new FileInputStream(errorOutFile);

                                IOUtils.copy(fis, System.err);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            exit(1);
                        }
                        repositoryImport.setIncludeOnly(false); // force its output to be copied
                    }
                }
            }

            if (errorOutputFile.length() != 0) {
                // Look to see if there are any errors
                ECLog.logError("Trouble running protoc - produced errors:");
                try {
                    FileInputStream fis = new FileInputStream(errorOutputFile);

                    IOUtils.copy(fis, System.err);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                exit(1);
            }

            // now we have to copy the output files to the specified locations
            // For cpp it is: Name.pb.cc and Name.pb.h
            boolean isCpp  = protoc.getLanguageName().equals("cpp");
            boolean isJava = protoc.getLanguageName().equals("java");
            if (isCpp || isJava) {
                if (protoc.getSourceOutput() != null) {
                    for (MTRepositoryImport repositoryImport : protoc.getRepositoryImports()) {
                        if (repositoryImport.isIncludeOnly()) {
                            continue;
                        }
                        String packagePath = null;
                        if (isJava) {
                            RepositoryFile repositoryFile = importManager.getRepositoryFileByName(
                                    repositoryImport.getIdentifier());
                            String            protoFilepath     = repositoryFile.getFilepath();
                            PBLoaderExtractor pbLoaderExtractor = new PBLoaderExtractor();
                            pbLoaderExtractor.load(protoFilepath);
                            String javaPackage = pbLoaderExtractor.getStringOptionValue("java_package");
                            if (javaPackage != null) {
                                packagePath = javaPackage.replaceAll("\\.", File.separator);
                            }
                        }

                        try {
                            if (packagePath != null) {
                                String destPath = protoc.getSourceOutput().getPath() + File.separator + packagePath;
                                if (!ensureDirectory(destPath)) {
                                    ECLog.logFatal("Unable to create directory: " + packagePath);
                                }
                            }
                            String sourceFilename = null;
                            if (isCpp) {
                                sourceFilename = repositoryImport.getFilename() + ".pb.cc";
                            } else {
                                sourceFilename =
                                        ECStringUtil.SeparatedStringToCamel(repositoryImport.getFilename(), "_", true)
                                        + ".java";
                                if (packagePath != null) {
                                    sourceFilename = packagePath + File.separator + sourceFilename;
                                }
                            }
                            File fromFile = new File(baseDirectoryPath + File.separator + sourceFilename);
                            //ECLog.logInfo("Copying " + fromFile.getAbsolutePath() + " to " + protoc.getSourceOutput().getPath());
                            FileInputStream fis = new FileInputStream(fromFile);
                            File toFile = new File(
                                    protoc.getSourceOutput().getPath() + File.separator + sourceFilename);
                            FileOutputStream fos = new FileOutputStream(toFile);
                            IOUtils.copy(fis, fos);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    ECLog.logError("NO source output specified!");
                }
            }
            if (isCpp) {
                if (protoc.getHeaderOutput() != null) {
                    for (MTRepositoryImport repositoryImport : protoc.getRepositoryImports()) {
                        if (repositoryImport.isIncludeOnly()) {
                            continue;
                        }
                        try {
                            String headerFilename = repositoryImport.getFilename() + ".pb.h";
                            File   fromFile       = new File(baseDirectoryPath + File.separator + headerFilename);
                            if (EntityCompiler.isVerbose()) {
                                ECLog.logInfo("Copying " + fromFile.getAbsolutePath() + " to "
                                              + protoc.getHeaderOutput().getPath());
                            }
                            FileInputStream fis = new FileInputStream(fromFile);
                            File toFile = new File(
                                    protoc.getHeaderOutput().getPath() + File.separator + headerFilename);
                            FileOutputStream fos = new FileOutputStream(toFile);
                            IOUtils.copy(fis, fos);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    ECLog.logError("NO header output specified!");
                }
            }
        }
    }

    public static final boolean isVerbose() {
        return commandLine != null && commandLine.verbose;
    }

    public static boolean ensureDirectory(File dir) {
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            return created && dir.exists();
        }
        return true;
    }

    public static boolean ensureDirectory(String directoryPath) {
        File dir = new File(directoryPath);
        return ensureDirectory(dir);
    }

    public static final String GetDefineValue(String name, String defaultValue) {
        String value = GetDefineValue(name);
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    public static String GetDefineValue(String name) {
        return defineValues.get(name);
    }

    public static void SetDefineValue(String name, String value) {
        defineValues.put(name, value);
    }

    public static final boolean ShouldAdvanceSchemaVersion() {
        return commandLine.advanceSchemaVersion;
    }

    public static void parseSourceFiles(MTRoot root, MTSpace space, List<RepositoryFile> repositoryFiles, boolean ignoreSpaceRequirement) {
        // parse each source file and
        for (RepositoryFile repositoryFile : repositoryFiles) {
            if (space != null) {
                space.setIncludeMode(repositoryFile.isIncluded());
            }
            CharStream input = null;
            try {
                input = CharStreams.fromFileName(repositoryFile.getFilepath());
            } catch (IOException e) {
                ECLog.logFatal("Unable to open source file: " + repositoryFile.getFilepath());
            }

            boolean firstInput = root.getSpace() == null;
            //ECLog.logInfo("Parsing: " + repositoryFile.getFilepath());
            MTSpace foundSpace = parseInput(repositoryFile.getFilepath(), input, root, space);
            if (space != null) {
                space.setIncludeMode(false);
            }
            if (foundSpace != null) {
                foundSpace.setRepositoryThatImportedThisSpace(repositoryFile.getRepository());
            }
            if (firstInput && foundSpace != null) {
                root.setSpace(foundSpace);
            }
            if (!ignoreSpaceRequirement && firstInput && foundSpace == null) {
                ECLog.logFatal("The first specified source file needs to contain the primary space declaration.");
            }
            if (foundSpace != null) {

                RepositoryImportManager importManager = new RepositoryImportManager(
                        RepositoryCache.CacheStructure.UserCache);

                for (MTRepositoryImport repoImport : foundSpace.getRepositoryImports()) {
                    if (isVerbose()) {
                        ECLog.logInfo("Found something to import: " + repoImport.getFilename());
                    }
                    importManager.importFromRepository(foundSpace, repoImport, "edl", repoImport.isIncludeOnly());
                }

                if (isVerbose()) {
                    ECLog.logInfo("Found " + importManager.getRepositoryFiles().size() + " things that were imported.");
                }
                parseSourceFiles(root, foundSpace, importManager.getRepositoryFiles(), ignoreSpaceRequirement);
                importManager.close();
            }
        }
    }

    private static MTSpace parseInput(String filename, CharStream input, MTRoot root, MTSpace space) {
        ECANTLRErrorListener errorListener = new ECANTLRErrorListener(filename);
        EntityLanguageLexer  lexer         = new EntityLanguageLexer(input);
        CommonTokenStream    tokens        = new CommonTokenStream(lexer);
        EntityLanguageParser parser        = new EntityLanguageParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);
        EntityLanguageParser.RootContext rootContext = parser.root();

        ASTVisitor visitor = new ASTVisitor();
        visitor.visitRoot(rootContext, root, space);
        return visitor.getFoundSpace();
    }
}


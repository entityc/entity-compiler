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
import org.entityc.compiler.model.MTCodeFormat;
import org.entityc.compiler.model.MTModule;
import org.entityc.compiler.model.MTRoot;
import org.entityc.compiler.model.config.MTConfiguration;
import org.entityc.compiler.model.config.MTDirectory;
import org.entityc.compiler.model.config.MTFile;
import org.entityc.compiler.model.config.MTProtoc;
import org.entityc.compiler.model.config.MTRepository;
import org.entityc.compiler.model.config.MTRepositoryImport;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.config.MTSpaceInclude;
import org.entityc.compiler.model.config.MTTemplate;
import org.entityc.compiler.model.config.MTTransform;
import org.entityc.compiler.model.domain.MTDEntity;
import org.entityc.compiler.model.domain.MTDModule;
import org.entityc.compiler.model.domain.MTDomain;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.protobuf.PBLoaderExtractor;
import org.entityc.compiler.repository.RepositoryCache;
import org.entityc.compiler.repository.RepositoryFile;
import org.entityc.compiler.repository.RepositoryImportManager;
import org.entityc.compiler.structure.sql.SSSchemaVersioning;
import org.entityc.compiler.transform.MTBaseTransform;
import org.entityc.compiler.transform.TransformManager;
import org.entityc.compiler.transform.template.FileTemplateTransform;
import org.entityc.compiler.transform.template.TemplatePublishing;
import org.entityc.compiler.transform.template.TemplateTransform;
import org.entityc.compiler.transform.template.tree.FTTemplate;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.util.ECANTLRErrorListener;
import org.entityc.compiler.util.ECLog;
import org.entityc.compiler.util.ECSessionManager;
import org.entityc.compiler.util.ECStringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static java.lang.System.exit;

class LogHandler extends Handler {

    @Override
    public void publish(LogRecord record) {
        String   fullClassPath = record.getSourceClassName();
        String[] parts         = fullClassPath.split("\\.");
        String   justClassName = parts[parts.length - 1];

        StringBuilder sb = new StringBuilder();
        sb.append(record.getLevel().getName());
        sb.append(": ");
        sb.append(justClassName);
        sb.append(".");
        sb.append(record.getSourceMethodName());
        sb.append("()| ");
        sb.append(record.getMessage());
        if (record.getLevel() == Level.SEVERE) {
            System.err.println(sb);
        } else {
            System.out.println(sb);
        }
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {

    }
}

public class EntityCompiler {

    public static final  String            COMPILER_VERSION = "0.12.0";
    public static final  String            LANGUAGE_VERSION = "0.12.0";
    private static       CommandLineParser cmdLineParser;

    public static final List<String> GetTemplateSearchPaths() {
        return cmdLineParser.templateSearchPaths;
    }

    public static final String GetDefineValue(String name, String defaultValue) {
        String value = cmdLineParser.getDefineValue(name);
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    public static final boolean ShouldAdvanceSchemaVersion() {
        return cmdLineParser.advanceSchemaVersion;
    }

    public static final boolean isVerbose() {
        return cmdLineParser != null && cmdLineParser.verbose;
    }

    private static String readLineByLineJava8(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }

    public static void main(String[] args) {

        setupLogger();

        cmdLineParser = new CommandLineParser();

        cmdLineParser.parse(args);

        if (cmdLineParser.help) {
            cmdLineParser.printUsage();
            exit(0);
        }

        if (cmdLineParser.version) {
            System.out.println("ec compiler version " + COMPILER_VERSION + ", language version " + LANGUAGE_VERSION);
            exit(0);
        }

        boolean setupMode = cmdLineParser.setupUri != null;

        boolean codeFormattingOnly = !setupMode && (cmdLineParser.templateToFormat != null
                                                    || cmdLineParser.templateToFormatInPath != null);
        if (cmdLineParser.sourceFileNames.size() < 1) {
            if (cmdLineParser.deleteSchema) {
                SSSchemaVersioning.DeleteEntireSchema();
                exit(0);
            }
            if (!codeFormattingOnly && !setupMode) {
                System.err.println("ERROR: Must specify at least one source file.");
                exit(1);
            }
        }

        // root node for all things read in during this execution session
        MTRoot root = new MTRoot(null);

        List<String> sourceFilenames   = new ArrayList<>();
        String       configurationName = cmdLineParser.configurationName;

        if (setupMode) {
            // site : organization / repo-name / path-to-setup-edl
            MTRepository repository = new MTRepository(cmdLineParser.setupUri);
            repository.setName("SetupRepo");
            if (cmdLineParser.getDefineValue("appIdentifier") == null) {
                ECLog.logFatal("Must define variable \"appIndentifier\" on the command line.");
            }
            EntityCompiler.ensureDirectory(cmdLineParser.getDefineValue("appIdentifier"));
            MTRepositoryImport repositoryImport = new MTRepositoryImport(null, false);
            MTSpace            space            = new MTSpace(null, "Setup");
            root.setSpace(space);
            space.addRepository(repository);
            repositoryImport.setRepositoryName(repository.getName());
            repositoryImport.setFilename(repository.getSetupFilename());
            RepositoryImportManager importManager = new RepositoryImportManager(
                    RepositoryCache.CacheStructure.TempDirectory);
            ECLog.logInfo("Fetching setup: " + repository.getSetupFilename());
            RepositoryFile repositoryFile = importManager.importFromRepository(space,
                                                                               repositoryImport, "edl", false);
            sourceFilenames.add(repositoryFile.getFilepath());
            configurationName = "Setup";
        } else {
            sourceFilenames.addAll(cmdLineParser.sourceFileNames);
        }

        ECSessionManager.getInstance().start();

        // get all filenames passed on command line and parse those files
        ArrayList<RepositoryFile> repositoryFiles = new ArrayList<>();
        for (String sourceFilename : sourceFilenames) {
            repositoryFiles.add(new RepositoryFile(sourceFilename, false));
        }
        parseSourceFiles(root, null, repositoryFiles, codeFormattingOnly);

        // CODE FORMATTING
        if (cmdLineParser.templateToFormat != null) {
            MTSpace space = new MTSpace(null, "formatterSpace");
            root.setSpace(space);
            MTConfiguration config = new MTConfiguration(null, root, "formatter");
            root.addConfiguration(config);

            MTFile file = null;
            for (String basePath : cmdLineParser.templateSearchPaths) {
                File f = new File(basePath + File.separator + cmdLineParser.templateToFormat + ".eml");
                if (f.exists()) {
                    file = new MTFile(null, f);
                }
            }
            MTTemplate mtTemplate = new MTTemplate(null, config, file);
            TransformManager.AddTransform(new FileTemplateTransform(config, mtTemplate, file.getPath()));
            FTTemplate ftTemplate = mtTemplate.parse((FTTransformSession) null, true);
            File       outFile    = null;
            if (cmdLineParser.templateToFormatOutPath != null) {
                outFile = new File(cmdLineParser.templateToFormatOutPath);
            } else {
                outFile = new File(file.getPath());
            }
            MTCodeFormat codeFormat = root.getCodeFormat("Default");
            ftTemplate.formatCodeToFile(outFile, codeFormat);
            exit(0);
        }
        // CODE FORMATTING
        if (cmdLineParser.templateToFormatInPath != null) {
            MTSpace space = new MTSpace(null, "formatterSpace");
            root.setSpace(space);
            MTConfiguration config = new MTConfiguration(null, root, "formatter");
            root.addConfiguration(config);
            MTFile file = null;
            File   f    = new File(cmdLineParser.templateToFormatInPath);
            if (f.exists()) {
                file = new MTFile(null, f);
            } else {
                ECLog.logFatal("The specified template file to format does not exist: "
                               + cmdLineParser.templateToFormatInPath);
            }

            MTTemplate mtTemplate = new MTTemplate(null, config, file);
            TransformManager.AddTransform(new FileTemplateTransform(config, mtTemplate, file.getPath()));
            FTTemplate ftTemplate = mtTemplate.parse((FTTransformSession) null, true);
            File       outFile    = null;
            if (cmdLineParser.templateToFormatOutPath != null) {
                outFile = new File(cmdLineParser.templateToFormatOutPath);
            } else {
                outFile = new File(file.getPath());
            }
            MTCodeFormat codeFormat = root.getCodeFormat("Default");
            ftTemplate.formatCodeToFile(outFile, codeFormat);
            exit(0);
        }

        // Get configuration name from command line - this is used to only execute a configuration
        // by that name from the files read in.
        if (configurationName == null) {
            if (root.getConfigurationNames().size() == 1) {
                configurationName = root.getConfigurationNames().get(0);
            }
        }

        MTConfiguration configuration = configurationName != null ?
                                        root.getConfiguration(configurationName) :
                                        null;

        if (configuration == null) {
            ECLog.logFatal("Need to specify a configuration.");
        }
        if (setupMode) {
            // add the project directory we created above to the start of the output path
            MTDirectory outputDirectory = configuration.getOutputByName("SetupTargetDir");
            if (outputDirectory == null) {
                ECLog.logFatal("Setup needs an output defined by the name of \"SetupTargetDir\".");
            }
            outputDirectory.setPath(cmdLineParser.getDefineValue("appIdentifier") + "/" + outputDirectory.getPath());

            outputDirectory = configuration.getOutputByName("ProjectTopDir");
            if (outputDirectory != null) {
                outputDirectory.setPath(
                        cmdLineParser.getDefineValue("appIdentifier") + "/" + outputDirectory.getPath());
            }
        }


        // Load any built in transforms (such as the postgres one)
        TransformManager.LoadBuiltins(root, configurationName);

        if (cmdLineParser.deleteSchema) {
            SSSchemaVersioning.DeleteEntireSchema();
        }

        // templates
        boolean failedToLoadTransform = false;
        for (MTTransform transformSpec : configuration.getTransforms()) {
            if (transformSpec.isTemplate()) {
                MTTemplate     template = (MTTemplate) transformSpec;
                RepositoryFile repositoryFile;
                String         templateFilename;
                if (template.getRepositoryImport() != null) {
                    if (cmdLineParser.verbose) {
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

        if (cmdLineParser.verbose) {
            ECLog.logInfo("RESOLVING REFERENCES...");
        }
        root.resolveReferences(false);
        //model.processAssetAttributes();
        root.getSpace().checkValidReferences();

        TransformManager.GetTransformByName("Implicit").start();

        for (MTTransform transformSpec : configuration.getTransforms()) {
            MTBaseTransform transform = TransformManager.GetTransformByName(transformSpec.getName());
            if (transform instanceof FileTemplateTransform) {
                continue; // skip templates for now
            }
            if (cmdLineParser.verbose) {
                System.out.println("Running " + "transform" + " " + transform.getName());
            }
            transform.start();
        }

        root.resolveReferences(true);
        root.getSpace().checkValidReferences();

        ArrayList<FileTemplateTransform> fileTemplatesToRun = new ArrayList<>();
        // Non-Contextual Templates
        for (MTTransform transformSpec : configuration.getTransforms()) {
            if (transformSpec instanceof MTTemplate) {
                if (((MTTemplate) transformSpec).isContextual()) {
                    continue; // don't automatically run these
                }
                MTBaseTransform transform = TransformManager.GetTransformByName(transformSpec.getName());
                if (transform instanceof FileTemplateTransform) {
                    TemplateTransform templateTransform = (TemplateTransform) transform;
                    templateTransform.setConfig(transformSpec.getConfig());
                    if (cmdLineParser.verbose) {
                        System.out.println("Loading template " + templateTransform.getName());
                    }
                    templateTransform.load();
                    fileTemplatesToRun.add((FileTemplateTransform) templateTransform);
                    if (cmdLineParser.verbose) {
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

        // Contextual Templates
        for (MTTransform transformSpec : configuration.getTransforms()) {
            if (transformSpec instanceof MTTemplate) {
                if (!((MTTemplate) transformSpec).isContextual()) {
                    continue;
                }

                MTBaseTransform transform = TransformManager.GetTransformByName(transformSpec.getName());
                if (!(transform instanceof TemplateTransform)) {
                    continue;
                }

                String templateName = transformSpec.getName();

                for (MTDomain domain : root.getSpace().getDomains()) {
                    for (MTEntity entity : root.getSpace().getEntities()) {
                        MTDEntity domainEntity = domain.getDomainEntity(entity, true);
                        if (domainEntity.getApplyTemplate() != null
                            && domainEntity.getApplyTemplate().getTemplateName().equals(templateName)) {
                            if (EntityCompiler.isVerbose()) {
                                ECLog.logInfo(
                                        "Running entity contextual template " + transform.getName() + " for domain "
                                        + domain.getName() + " on entity " + entity.getName());
                            }
                            ((TemplateTransform) transform).start(domainEntity.getApplyTemplate(), entity);
                        } else {
                            MTModule module = entity.getModule();
                            MTDModule domainModule = module != null ?
                                                     domain.getDomainModule(module, true) :
                                                     null;
                            if (domainModule != null && domainModule.getApplyTemplate() != null
                                && domainModule.getApplyTemplate().getTemplateName().equals(templateName)) {
                                if (EntityCompiler.isVerbose()) {
                                    ECLog.logInfo(
                                            "Running module contextual template " + transform.getName() + " for domain "
                                            + domain.getName() + " in module " + module.getName() + " on entity "
                                            + entity.getName());
                                }
                                ((TemplateTransform) transform).start(domainModule.getApplyTemplate(), entity);
                            } else if (domain.getApplyTemplate() != null
                                       && domain.getApplyTemplate().getTemplateName().equals(templateName)) {
                                if (EntityCompiler.isVerbose()) {
                                    ECLog.logInfo(
                                            "Running domain contextual template " + transform.getName() + " for domain "
                                            + domain.getName() + " on entity " + entity.getName());
                                }
                                ((TemplateTransform) transform).start(domain.getApplyTemplate(), entity);
                            }
                        }
                    }
                }
            }
        }

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
        ECSessionManager.getInstance().close();
    }

    private static void parseSourceFiles(MTRoot root, MTSpace space, List<RepositoryFile> repositoryFiles, boolean ignoreSpaceRequirement) {
        // parse each source file and
        for (RepositoryFile repositoryFile : repositoryFiles) {
            if (space != null) {
                space.setIncludeMode(repositoryFile.isIncluded());
            }
            CharStream input = null;
            try {
                input = CharStreams.fromFileName(repositoryFile.getFilepath());
            } catch (IOException e) {
                System.err.println("ERROR: Unable to open source file: " + repositoryFile.getFilepath());
                exit(1);
            }

            boolean firstInput = root.getSpace() == null;
            //ECLog.logInfo("Parsing: " + repositoryFile.getFilepath());
            MTSpace foundSpace = parseInput(repositoryFile.getFilepath(), input, root, space);
            if (space != null) {
                space.setIncludeMode(false);
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

    private static void setupLogger() {
        LogManager.getLogManager().reset();
        Logger rootLogger = LogManager.getLogManager().getLogger("");
        rootLogger.addHandler(new LogHandler());
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
}


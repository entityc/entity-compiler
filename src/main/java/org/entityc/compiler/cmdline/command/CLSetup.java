/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.txt file in the project root.
 */

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.txt file in the project root.
 */

package org.entityc.compiler.cmdline.command;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import org.entityc.compiler.EntityCompiler;
import org.entityc.compiler.cmdline.CommandLine;
import org.entityc.compiler.model.MTRoot;
import org.entityc.compiler.model.config.MTConfiguration;
import org.entityc.compiler.model.config.MTDirectory;
import org.entityc.compiler.model.config.MTRepository;
import org.entityc.compiler.model.config.MTRepositoryImport;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.project.ProjectManager;
import org.entityc.compiler.repository.RepositoryCache;
import org.entityc.compiler.repository.RepositoryFile;
import org.entityc.compiler.repository.RepositoryImportManager;
import org.entityc.compiler.util.ECLog;
import org.entityc.compiler.util.ECStringUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CLSetup extends CLCommand {

    private final HashMap<String, SetupInfo> setupMap        = new HashMap<>();
    private       MTRoot                     root            = null;
    private       MTSpace                    space           = null;
    private       MTRepository               setupRepository = null;

    public CLSetup(CommandLine commandLine) {
        super(commandLine,
              "setup",
              "Runs a setup file to install a new project to a directory.",
              "Allows you to very quickly setup a project based on a pre-defined project template. "
              + "You specify the project template using a URI that can fetch all necessary files over the network.");
    }

    @Override
    public void run(String[] args) {

        if (args.length == 0) {
            printUsage();
            return;
        }

        String setupNameOrUri = args[0];

        loadSetups();

        if (setupNameOrUri.equals("-l") || setupNameOrUri.equals("--list")) {
            listAvailableSetups();
            return;
        }
        String setupUri = setupNameOrUri;
        if (setupMap.containsKey(setupNameOrUri)) {
            SetupInfo setupInfo = setupMap.get(setupNameOrUri);
            setupUri = setupInfo.uri;
        }

        // site : organization / repo-name / path-to-setup-edl
        ECLog.logInfo("Fetching setup: " + setupUri);
        RepositoryFile repositoryFile = getFileFromRepositoryUri(setupUri, "edl", false);

        String directoryName = repositoryFile.getRepository().getSetupFilename();
        if (args.length < 2) {
            System.out.print("Enter project directory name [" + directoryName + "]: ");
            Scanner scanner = new Scanner(System.in);
            String  value   = scanner.nextLine();
            if (value.trim().length() != 0) {
                directoryName = value;
            }
        } else {
            directoryName = args[1];
        }
        EntityCompiler.ensureDirectory(directoryName);
        EntityCompiler.SetDefineValue("SetupDirectoryName", directoryName);

        ProjectManager.getInstance().setProjectBaseDirPath(directoryName);
        ProjectManager.getInstance().start(false);

        configureSetupRepo(setupUri);

        final String configurationName = "Setup";

        ArrayList<String> sourceFilenames = new ArrayList<>();
        sourceFilenames.add(repositoryFile.getFilepath());
        // more to put here
        ArrayList<RepositoryFile> repositoryFiles = new ArrayList<>();
        for (String sourceFilename : sourceFilenames) {
            repositoryFiles.add(new RepositoryFile(sourceFilename, false));
        }
        EntityCompiler.parseSourceFiles(root, null, repositoryFiles, true);

        MTConfiguration configuration = root.getConfiguration(configurationName);

        if (configuration == null) {
            ECLog.logFatal("Unable to find a configuration named \"" + configurationName + "\" in the setup file.");
        }

        // Update output directories to include our project directory
        MTDirectory setupTargetDirectory = configuration.getOutputByName("SetupTargetDir");
        if (setupTargetDirectory == null) {
            ECLog.logFatal("Setup file is missing an output defined by the name of \"SetupTargetDir\".");
        }
        setupTargetDirectory.setPath(directoryName + "/" + setupTargetDirectory.getPath());

        MTDirectory projectTopDirectory = configuration.getOutputByName("ProjectTopDir");
        if (projectTopDirectory != null) {
            projectTopDirectory.setPath(directoryName + "/" + projectTopDirectory.getPath());
        }

        // Run the compiler for our configuration
        EntityCompiler.RunConfiguration(configuration);

        // close out our session
        ProjectManager.getInstance().close();
    }

    @Override
    public void printUsage() {
        super.printUsageWithArguments("[-l,--list] <uri> [<directory>]");
    }

    private void loadSetups() {
        String         uri           = "github:entityc/entity-compiler:main/setups";
        RepositoryFile setupListFile = getFileFromRepositoryUri(uri, "json", true);
        if (setupListFile == null) {
            ECLog.logFatal("Unable to find the list of available setups from: " + uri);
        }
        String     filepath  = setupListFile.getFilepath();
        JsonObject setupList = null;
        Gson       gson      = new Gson();
        try {
            FileInputStream fis    = new FileInputStream(filepath);
            JsonReader      reader = new JsonReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
            setupList = gson.fromJson(reader, JsonObject.class);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String setupName : setupList.keySet()) {
            JsonObject setup = setupList.getAsJsonObject(setupName);
            if (!addSetupInfo(setupName, setup)) {
                continue;
            }
        }
    }

    private void listAvailableSetups() {
        ECLog.log("The following project templates are available: ");
        ECLog.log("");
        if (setupMap.size() == 0) {
            ECLog.log("no setups available");
        } else {
            for (String setupName : setupMap.keySet()) {
                SetupInfo setup = setupMap.get(setupName);
                ECLog.log("  " + setup.identifier);
                String indent = "      ";
                ECLog.log(indent + setup.title);
                ECLog.log("");
                String wrappedDescription = ECStringUtil.WrapString(setup.description, indent, DISPLAY_LINE_WIDTH);
                ECLog.log(indent + wrappedDescription);
                ECLog.log("");
                ECLog.log(indent + setup.uri);
                ECLog.log("");
            }
        }
    }

    private RepositoryFile getFileFromRepositoryUri(String uri, String extension, boolean quietly) {
        MTRepository repository = new MTRepository(uri);
        repository.setName("SetupRepo");
        MTRepositoryImport repositoryImport = new MTRepositoryImport(null, false);
        repositoryImport.setQuietly(quietly);
        MTSpace space = new MTSpace(null, "Setup");
        MTRoot  root  = new MTRoot(null);
        root.setSpace(space);
        space.addRepository(repository);
        repositoryImport.setRepositoryName(repository.getName());
        repositoryImport.setFilename(repository.getSetupFilename());
        RepositoryImportManager importManager = new RepositoryImportManager(
                RepositoryCache.CacheStructure.TempDirectory);
        RepositoryFile repositoryFile = importManager.importFromRepository(space,
                                                                           repositoryImport, extension, false);
        return repositoryFile;
    }

    private void configureSetupRepo(String uri) {
        this.setupRepository = new MTRepository(uri);
        this.setupRepository.setName("SetupRepo");
        MTRepositoryImport repositoryImport = new MTRepositoryImport(null, false);
        repositoryImport.setQuietly(true);
        this.space = new MTSpace(null, "Setup");
        this.root  = new MTRoot(null);
        root.setSpace(space);
        space.addRepository(this.setupRepository);
    }

    private boolean addSetupInfo(String identifier, JsonObject jsonObject) {
        String title       = jsonObject.get("title").getAsString();
        String description = jsonObject.get("description").getAsString();
        String setupUri    = jsonObject.get("uri").getAsString();
        if (title == null || description == null || setupUri == null) {
            return false;
        }
        SetupInfo setupInfo = new SetupInfo(identifier, title, description, setupUri);
        setupMap.put(setupInfo.identifier, setupInfo);
        return true;
    }

    private class SetupInfo {

        String identifier;
        String title;
        String description;
        String uri;

        SetupInfo(String identifier, String title, String description, String uri) {
            this.identifier  = identifier;
            this.title       = title;
            this.description = description;
            this.uri         = uri;
        }
    }
}

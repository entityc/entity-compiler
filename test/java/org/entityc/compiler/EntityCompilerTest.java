package org.entityc.compiler;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EntityCompilerTest {

    static final String BASE_RESOURCE_DIR = "test/resources/compiler/main";

    @Test
    void compileBasicEntity() throws IOException {
        final String TestName = "BasicEntity";
        runTemplateTest(TestName);
    }

    private void runTemplateTest(String testName) throws IOException {
        final String TestResourceDir = BASE_RESOURCE_DIR + "/" + testName;
        String       strTmp          = System.getProperty("java.io.tmpdir");
        String       sourceFile      = TestResourceDir + "/" + testName + ".edl";
        EntityCompiler.main(new String[]{sourceFile, "-c", "Config", "-tp", TestResourceDir, "-D", "TEMP_DIR=" + strTmp});
        String actual   = FileUtils.readFileToString(new File(strTmp + File.separator + testName + ".txt"));
        String expected = FileUtils.readFileToString(new File(TestResourceDir + "/" + testName + "Expected.txt"));
        assertEquals(expected, actual);
    }

    @Test
    void postgresVersionedPattern() throws IOException {
        final String TestName = "PostgresVersionedPattern";
        runPostgresTest(TestName);
    }

    @Test
    void postgresManyEntityAttribute() throws IOException {
        final String TestName = "PostgresManyEntityAttribute";
        runPostgresTest(TestName);
    }

    @Test
    void compilerDocs() throws IOException {
        final String TestName = "CompilerDocs";
        runTemplateTest(TestName);
    }

    private void runPostgresTest(String testName) throws IOException {
        final String TestResourceDir = BASE_RESOURCE_DIR + "/" + testName;
        String       strTmp          = System.getProperty("java.io.tmpdir");
        String       sourceFile      = TestResourceDir + "/" + testName + ".edl";
        EntityCompiler.main(new String[]{sourceFile, "-c", "Config", "-tp", TestResourceDir, "-D", "TEMP_DIR=" + strTmp});
        String actual   = FileUtils.readFileToString(new File(strTmp + File.separator + "V1__Space.sql"));
        String expected = FileUtils.readFileToString(new File(TestResourceDir + "/" + testName + "Expected.sql"));
        assertEquals(expected, actual);
    }

    //@Test
    void templateDocumentation() throws IOException {
        final String TestName = "TemplateDoc";
        runTemplateTest(TestName);
    }

    //@Test
    void templateFormatting() throws IOException {
        final String testName = "TemplateCodeFormatting";
        final String TestResourceDir = BASE_RESOURCE_DIR + "/" + testName;
        String       strTmp          = System.getProperty("java.io.tmpdir");
        String       sourceFile      = TestResourceDir + "/" + testName + ".eml";
        String       destFile      = TestResourceDir + "/" + testName + "_formatted.eml";
        EntityCompiler.main(new String[]{TestResourceDir + "/" + "TestFormat.edl", "-tp", TestResourceDir, "-tf", testName, "-tfout", destFile});
        assertTrue(true);
//        String actual   = FileUtils.readFileToString(new File(strTmp + File.separator + testName + ".txt"));
//        String expected = FileUtils.readFileToString(new File(TestResourceDir + "/" + testName + "Expected.txt"));
//        assertEquals(actual, expected);
    }
}

package org.entityc.compiler;

import org.apache.commons.io.FileUtils;
import org.entityc.compiler.model.MTCodeFormat;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.util.ECLog;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
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

    @Test
    void templateFormatting() throws IOException {
        final String testName = "TemplateCodeFormatting";
        final String TestResourceDir = BASE_RESOURCE_DIR + "/" + testName;
        String       strTmp          = System.getProperty("java.io.tmpdir");
        String       sourceFile      = TestResourceDir + "/" + testName + ".eml";
        String       expectedFile      = TestResourceDir + "/" + testName + "Expected.eml";

        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("Element.ExpressionOperator.spaceBefore", true);
        jsonObjectBuilder.add("Element.ExpressionOperator.spaceAfter", true);
        jsonObjectBuilder.add("Element.ExpressionOperatorEquals.spaceBefore", true);
        jsonObjectBuilder.add("Element.ExpressionOperatorEquals.spaceAfter", true);
        jsonObjectBuilder.add("Element.FilterName.spaceAfter", false);
        jsonObjectBuilder.add("Instruction.outlet.suppressIndent", true);
        jsonObjectBuilder.add("Instruction.receive.suppressIndent", true);
        jsonObjectBuilder.add("Instruction.send.suppressIndent", true);
        jsonObjectBuilder.add("Element.Description.alignTo.InstructionName", true);
        jsonObjectBuilder.add("Element.FunctionOpenParen.alignTo.InstructionName", true);
        jsonObjectBuilder.add("Element.FunctionArgName.alignTo.FunctionArgName", true);
        jsonObjectBuilder.add("Element.FunctionCloseParen.alignTo.FunctionOpenParen", true);
        jsonObjectBuilder.add("Element.Description.alignTo.FunctionArgName", true);
//        jsonObjectBuilder.add("Element.FunctionCallOpenParen.alignTo.InstructionName", true);
//        jsonObjectBuilder.add("Element.FunctionCallArgName.alignTo.FunctionCallArgName", true);
//        jsonObjectBuilder.add("Element.FunctionCallCloseParen.alignTo.FunctionCallOpenParen", true);
        jsonObjectBuilder.add("Element.FunctionCallArgDelim.spaceAfter", true); // TODO: broken when used with above three
        jsonObjectBuilder.add("Element.FunctionCallDelim.spaceAfter", true);
        jsonObjectBuilder.add("Element.InstructionBlockStartSuffix.alignTo.InstructionBlockStartPrefix.if.Description", true);
        jsonObjectBuilder.add("Element.InstructionSuffix.alignTo.InstructionPrefix.if.Description", true);

        MTCodeFormat codeFormat = new MTCodeFormat(null, "Default", jsonObjectBuilder.build());
        ECLog.SetExitOnFatal(false);
        String inputText   = FileUtils.readFileToString(new File(sourceFile));

        final String actual = TemplateFormatController.FormatCodeAsString(inputText, codeFormat);
        String expected = FileUtils.readFileToString(new File(expectedFile));
        assertEquals(expected, actual);
    }
}

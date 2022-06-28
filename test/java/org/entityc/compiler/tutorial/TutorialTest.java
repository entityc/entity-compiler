package org.entityc.compiler.tutorial;

import org.entityc.compiler.EntityCompiler;
import org.apache.commons.io.FileUtils;
import org.entityc.compiler.util.ECLog;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TutorialTest {

    static final String   BASE_RESOURCE_DIR = "test/resources/tutorial";
    static final String[] TwoFiles          = new String[]{"Space.edl", "Configuration.edl"};
    static final String[] ThreeFiles        = new String[]{"Space.edl", "Configuration.edl", "Units.edl"};
    static final String[] FourFiles         = new String[]{"Space.edl", "Configuration.edl", "Units.edl", "Domains.edl"};

    @Test
    void testModule1Session1() throws IOException {
        runSession(1, 1, TwoFiles);
    }

    private void runSession(int moduleNumber, int sessionNumber, String[] files) throws IOException {
        final String TestResourceDir = BASE_RESOURCE_DIR + File.separator + "module" + moduleNumber + File.separator + "session" + sessionNumber;
        String       strTmp          = System.getProperty("java.io.tmpdir");
        PrintStream  console         = System.out;
        File         actualFile      = new File(strTmp + File.separator + "actual.txt");
        System.setOut(new PrintStream(actualFile));

        List<String> cmdLine = new ArrayList<>();
        cmdLine.add("build");
        cmdLine.add("Tutorial");
        cmdLine.add("--quiet");

        cmdLine.add("-tp");
        cmdLine.add(TestResourceDir);

        for (String filename : files) {
            cmdLine.add(TestResourceDir + File.separator + filename);
        }
        cmdLine.add("-DTEMP_DIR=" + strTmp);

        EntityCompiler.main(cmdLine.toArray(new String[0]));

        System.setOut(console);

        File   expectedFile = new File(TestResourceDir + File.separator + "expected.txt");
        String actual       = FileUtils.readFileToString(actualFile);
        String expected     = FileUtils.readFileToString(expectedFile);
        assertEquals(expected, actual);
    }

    @Test
    void testModule1Session2() throws IOException {
        runSession(1, 2, TwoFiles);
    }

    @Test
    void testModule1Session3() throws IOException {
        runSession(1, 3, ThreeFiles);
    }

    @Test
    void testModule1Session4() throws IOException {
        runSession(1, 4, ThreeFiles);
    }

    @Test
    void testModule1Session5() throws IOException {
        runSession(1, 5, ThreeFiles);
    }

    @Test
    void testModule2Session1() throws IOException {
        runSession(2, 1, ThreeFiles);
    }

    @Test
    void testModule2Session2() throws IOException {
        runSession(2, 2, ThreeFiles);
    }

    @Test
    void testModule2Session3() throws IOException {
        runSession(2, 3, ThreeFiles);
    }

    @Test
    void testModule3Session1() throws IOException {
        runSession(3, 1, ThreeFiles);
    }

    @Test
    void testModule3Session2() throws IOException {
        runSession(3, 2, ThreeFiles);
    }

    @Test
    void testModule3Session3() throws IOException {
        runSession(3, 3, ThreeFiles);
    }

    @Test
    void testModule3Session4() throws IOException {
        runSession(3, 4, ThreeFiles);
    }

    @Test
    void testModule3Session5() throws IOException {
        runSession(3, 5, ThreeFiles);
    }

    @Test
    void testModule3Session6() throws IOException {
        runSession(3, 6, ThreeFiles);
    }

    @Test
    void testModule4Session1() throws IOException {
        runSession(4, 1, ThreeFiles);
    }

    @Test
    void testModule4Session2() throws IOException {
        runSession(4, 2, ThreeFiles);
    }

    @Test
    void testModule4Session3() throws IOException {
        runSession(4, 3, ThreeFiles);
    }

    @Test
    void testModule5Session2() throws IOException {
        runSession(5, 2, ThreeFiles);
    }

    @Test
    void testModule5Session3() throws IOException {
        runSession(5, 3, FourFiles);
    }
}

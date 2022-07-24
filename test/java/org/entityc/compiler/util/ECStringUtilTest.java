package org.entityc.compiler.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ECStringUtilTest {

    @Test
    void entityNameToVariableName() {
        assertEquals(ECStringUtil.EntityNameToVariableName("EntityName"), "entityName");
    }

    @Test
    void variableInMethodName() {
        assertEquals(ECStringUtil.VariableInMethodName("attributeName"), "AttributeName");
    }

    @Test
    void words() {
        assertEquals(ECStringUtil.Words("thisShouldBeWords"), "this Should Be Words");
    }

    @Test
    void camelToSeparator() {
        assertEquals(ECStringUtil.CamelToSeparator("thisShouldBeWords", "="), "this=Should=Be=Words");
    }

    @Test
    void stripQuotes() {
        assertEquals(ECStringUtil.ProcessParserString("\"WithoutQuotes\""), "WithoutQuotes");
    }

    @Test
    void doubleEscapeDoubleQuotes() {
        assertEquals("\\\\\"DoubleQuotes\\\\\"", ECStringUtil.DoubleEscapeDoubleQuotes("\"DoubleQuotes\""));
    }

    @Test
    void isPath() {
        assertEquals(true, ECStringUtil.IsPath(""));
        assertEquals(true, ECStringUtil.IsPath("this-is-ok"));
        assertEquals(true, ECStringUtil.IsPath("this/is-ok"));
        assertEquals(true, ECStringUtil.IsPath("this/is/ok"));
        assertEquals(false, ECStringUtil.IsPath("this-os&bad"));
        assertEquals(false, ECStringUtil.IsPath("this/is+bad"));
    }
    @Test
    void stripLineEnd() {
        assertEquals(ECStringUtil.StripLineEnd("WithoutCR\n"), "WithoutCR");
        assertEquals(ECStringUtil.StripLineEnd("WithoutCR"), "WithoutCR");
    }

    @Test
    void camelToTitle() {
        assertEquals(ECStringUtil.CamelToTitle("makeThisATitle"), "Make This a Title");
    }

    @Test
    void capitalize() {
        assertEquals(ECStringUtil.Capitalize("bob"), "Bob");
    }

    @Test
    void separatedStringToCamel() {
        assertEquals(ECStringUtil.SeparatedStringToCamel("this Is An Attribute Name", " ", true), "ThisIsAnAttributeName");
        assertEquals(ECStringUtil.SeparatedStringToCamel("this is an attribute name", " ", true), "ThisIsAnAttributeName");
    }

    @Test
    void uncapitalize() {
        assertEquals(ECStringUtil.Uncapitalize("Bob"), "bob");
    }

    @Test
    void startsWithWord() {
        assertTrue(ECStringUtil.StartsWithWord("someVariable", "some"));
        assertFalse(ECStringUtil.StartsWithWord("somevariable", "some"));
    }

    @Test
    void splitByHash() {
        assertArrayEquals(ECStringUtil.SplitByHash("bunch#of#hashes"), new String[]{"bunch", "of", "hashes"});
    }

    @Test
    void wrapString() {
        assertEquals(ECStringUtil.WrapString("%% This is a long description that will word wrap after a certain number of characters.", "%% ", 20),
                     "%% This is a long\n" +
                             "%% description that\n" +
                             "%% will word wrap\n" +
                             "%% after a certain\n" +
                             "%% number of\n" +
                             "%% characters.");
    }
}
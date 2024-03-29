/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.util;

import org.atteo.evo.inflector.English;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ECStringUtil {

    static final String[] WordsNotToCapitalize = {"a", "an", "the", "at", "by", "for", "in", "of", "on", "to",
                                                  "up", "and", "as", "but", "or", "and", "nor."};

    public static String EntityNameToVariableName(String entityName) {
        return entityName.substring(0, 1).toLowerCase() + entityName.substring(1);
    }

    public static String VariableInMethodName(String variableName) {
        return variableName.substring(0, 1).toUpperCase() + variableName.substring(1);
    }

    public static String Words(String value) {
        return CamelToSeparator(value, " ");
    }

    public static String CamelToSeparator(String name, String separator) {
        if (name.length() == 1) {
            return name;
        }
        StringBuilder outputString = new StringBuilder();
        boolean       lastWasUpper = false;
        for (int i = 0; i < name.length() - 1; i++) {
            char    ch          = name.charAt(i);
            char    chNext      = name.charAt(i + 1);
            boolean chUpper     = Character.isUpperCase(ch);
            boolean chNextUpper = Character.isUpperCase(chNext);
            if (lastWasUpper && chUpper && !chNextUpper) {
                outputString.append(separator);
            }
            outputString.append(ch);
            if (!chUpper && chNextUpper) {
                outputString.append(separator);
            }
            if (i == (name.length() - 2)) {
                outputString.append(chNext);
            }
            lastWasUpper = chUpper;
        }
        return outputString.toString();
    }

    /**
     * Make sure characters that need to be escaped are escaped. This should be used when putting a string
     * value into a string constant in generated code so that characters are escaped the way they were when
     * initially read in.
     *
     * @param inputString The string with special characters that need to be escaped.
     * @return The same string but with special characters escaped.
     */
    public static String EscapeString(String inputString) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inputString.length(); i++) {
            char ch = inputString.charAt(i);
            switch (ch) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                default:
                    sb.append(ch);
                    break;
            }
        }

        return sb.toString();
    }

    public static String ProcessParserString(String inputString) {
        String finalString = inputString;
        if (finalString.length() > 1) {
            String firstChar = finalString.substring(0, 1);
            String lastChar  = finalString.substring(finalString.length() - 1);
            if (firstChar.equals("'") || firstChar.equals("\"") && (lastChar.equals("'") || lastChar.equals("\""))) {
                finalString = finalString.substring(1, finalString.length() - 1);
            }
        }
        if (finalString.length() > 0) {
            finalString = finalString.replaceAll("\\\\\\\"", "\"");
        }
        return finalString;
    }

    /**
     * When reading in text with double quotes, before they can be written out they need to first
     * be double escaped so the end result is a single escaped double quote - just like the very original
     * input text that was parsed.
     *
     * @param inputString The input string with double quotes.
     * @return Double escaped double quoted string.
     */
    public static String DoubleEscapeDoubleQuotes(String inputString) {
        String finalString = inputString;
        if (finalString.length() > 0) {

            finalString = finalString.replace("\"", "\\\\\"");
        }
        return finalString;
    }

    public static String StripLineEnd(String input) {
        int len = input.length();
        while (input.endsWith("\n")) {
            input = input.substring(0, len - 1);
            len   = input.length();
        }
        return input;
    }

    public static String CamelToTitle(String name) {
        String   wordsString = CamelToSeparator(name, " ");
        String[] words       = wordsString.split(" ");

        for (int i = 0; i < words.length; i++) {
            boolean uppercase = true;
            String  word      = words[i];
            if (i == 0 || i == words.length - 1) {
                // keep uppercase
            } else {
                for (String notCapWord : WordsNotToCapitalize) {
                    if (word.equalsIgnoreCase(notCapWord)) {
                        uppercase = false;
                        break;
                    }
                }
            }
            if (uppercase) {
                word = Capitalize(word);
            } else {
                word = word.toLowerCase();
            }
            words[i] = word;
        }
        return String.join(" ", words);
    }

    public static String Capitalize(String value) {
        if (value.length() == 0) {
            return value;
        }
        String returnValue = value.substring(0, 1).toUpperCase();
        returnValue += value.substring(1);
        return returnValue;
    }

    public static String Pluralize(String text) {
        // only pluralize the last word
        String[] words    = text.split(" ");
        int      numWords = words.length;
        String   lastWord = words[numWords - 1];

        lastWord = English.plural(lastWord);

        StringBuilder sb = new StringBuilder();
        if (numWords == 1) {
            return lastWord;
        }
        for (int i = 0; i < numWords - 1; i++) {
            sb.append(words[i]).append(" ");
        }
        sb.append(lastWord);
        return sb.toString();
    }

    public static String SeparatedStringToCamel(String name, String separator, boolean capitalize) {
        StringBuilder outputString = new StringBuilder();
        boolean       capNextChar  = capitalize;
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (ch == separator.charAt(0)) {
                capNextChar = true;
                continue;
            }
            if (capNextChar) {
                ch          = Character.toUpperCase(ch);
                capNextChar = false;
            }
            outputString.append(ch);
        }
        return outputString.toString();
    }

    public static String Uncapitalize(String value) {
        if (value.length() == 0) {
            return value;
        }
        String returnValue = value.substring(0, 1).toLowerCase();
        returnValue += value.substring(1);
        return returnValue;
    }

    public static boolean StartsWithWord(String name, String word) {
        int lengthOfWord = word.length();
        if (!name.startsWith(word) || name.length() <= lengthOfWord) {
            return false;
        }
        return Character.isUpperCase(name.charAt(lengthOfWord));
    }

    public static String[] SplitByHash(String nameWithHashInIt) {
        return nameWithHashInIt.split("\\#");
    }

    public static String WrapString(String input, String linePrefix, int lineWidth) {
        int blockWidth = lineWidth - linePrefix.length();
        if (blockWidth < 1) {
            ECLog.logError("Line wrap request not valid because the linePrefix (" + linePrefix.length()
                           + ") is greater than or equal to line width (" + lineWidth + ").");
            return input; // don't wrap
        }
        if (input.length() <= blockWidth) {
            return input; // nothing to do
        }

        String[]      words          = input.split(" ");
        int           widthRemaining = blockWidth;
        StringBuilder output         = new StringBuilder();
        boolean       addSpace       = false;
        for (String word : words) {
            int spaceWidth = addSpace ?
                             1 :
                             0;
            if ((word.length() + spaceWidth) < widthRemaining) {
                if (addSpace) {
                    output.append(" ");
                }
            } else {
                // wrap
                output.append("\n");
                output.append(linePrefix);
                widthRemaining = blockWidth;
            }
            addSpace = true;
            output.append(word);
            widthRemaining -= word.length();
        }
        return output.toString();
    }

    public static List<String> SplitIntoLines(String input, int lineWidth) {
        List<String>  lines          = new ArrayList<>();
        String[]      words          = input.split(" ");
        int           widthRemaining = lineWidth;
        StringBuilder output         = new StringBuilder();
        boolean       addSpace       = false;
        for (String word : words) {
            int spaceWidth = addSpace ?
                             1 :
                             0;
            if ((word.length() + spaceWidth) < widthRemaining) {
                if (addSpace) {
                    output.append(" ");
                }
            } else {
                // wrap
                lines.add(output.toString());
                output         = new StringBuilder();
                widthRemaining = lineWidth;
            }
            addSpace = true;
            output.append(word);
            widthRemaining -= word.length();
        }
        if (output.length() > 0) {
            lines.add(output.toString());
        }
        return lines;
    }

    public static int CharPositionOfLastChar(int startingPosition, String text) {
        int lastLineFeedIndex = text.lastIndexOf("\n");
        if (lastLineFeedIndex == -1) {
            return startingPosition + text.length();
        }
        String lastLineText = text.substring(lastLineFeedIndex + 1);
        return lastLineText.length();
    }

    public static String GetClassNameFromFullName(String fullTypeName) {
        String shortName         = fullTypeName;
        int    indexOfLastDollar = fullTypeName.lastIndexOf("$");
        int    indexOfLastDot    = fullTypeName.lastIndexOf(".");
        int    maxIndex          = Math.max(indexOfLastDollar, indexOfLastDot);
        if (maxIndex > 0) {
            shortName = fullTypeName.substring(maxIndex + 1);
        }
        return shortName;
    }

    public static boolean IsNamespace(String text) {
        if (text == null || text.length() == 0) {
            return false;
        }
        for (int i = 1; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (!(Character.isLetterOrDigit(ch) || ch == '.')) {
                return false;
            }
        }
        for (String seg : text.split("\\.")) {
            if (!IsIdentifier(seg)) {
                return false;
            }
        }
        return true;
    }

    public static boolean IsIdentifier(String text) {
        //ECLog.logFatal("Checking if identifier: \"" + text + "\"...");
        if (text == null || text.length() == 0) {
            //ECLog.logFatal("In the text \"" + text + "\" is empty!");
            return false;
        }
        if (!Character.isAlphabetic(text.charAt(0))) {
            //ECLog.logFatal("In the text \"" + text + "\" the first letter is not a letter: [" + text.charAt(0) + "]");
            return false;
        }
        for (int i = 1; i < text.length(); i++) {
            if (!Character.isLetterOrDigit(text.charAt(i))) {
                //ECLog.logFatal("In the text \"" + text + "\" there is a non-letter/number: [" + text.charAt(i) + "]");
                return false;
            }
        }
        return true;
    }

    public static boolean IsPath(String text) {
        if (text == null || text.length() == 0) {
            return true;
        }
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (!(Character.isLetterOrDigit(ch) || ch == '-' || ch == '/')) {
                return false;
            }
        }
        return true;
    }

    public static boolean IsCapitalized(String text) {
        if (text == null || text.length() == 0) {
            return false;
        }
        return Character.isAlphabetic(text.charAt(0)) && Character.isUpperCase(text.charAt(0));
    }

    public static boolean IsUncapitalized(String text) {
        if (text == null || text.length() == 0) {
            return false;
        }
        return Character.isAlphabetic(text.charAt(0)) && !Character.isUpperCase(text.charAt(0));
    }

    public static String RepeatString(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static String DirectoryPath(String fullPathWithFile) {
        String directoryPath = null;
        if (fullPathWithFile.contains(File.separator)) {
            directoryPath = fullPathWithFile.substring(0, fullPathWithFile.lastIndexOf(File.separator));
        }
        return directoryPath;
    }

    public static String FilenameFromPath(String fullPathWithFile) {
        String filename = fullPathWithFile;
        if (fullPathWithFile.contains(File.separator)) {
            filename = fullPathWithFile.substring(fullPathWithFile.lastIndexOf(File.separator) + 1);
        }
        return filename;
    }

    public static String PathWithSeparator(String path) {
        if (path == null || path.length() == 0) {
            return "";
        }
        return path + File.separator;
    }

    public static String ExtensionFromPath(String fullPathWithExtension) {
        String extension = fullPathWithExtension;
        if (fullPathWithExtension.contains(".")) {
            extension = fullPathWithExtension.substring(fullPathWithExtension.lastIndexOf(".") + 1);
        }
        return extension;
    }

}

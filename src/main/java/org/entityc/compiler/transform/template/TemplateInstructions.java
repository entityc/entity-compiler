/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template;

import org.entityc.compiler.transform.template.tree.FTNode;
import org.entityc.compiler.transform.template.tree.FTTemplate;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.entityc.compiler.transform.template.tree.FTNode.NO_SYMBOL;

public class TemplateInstructions {

    private final static Map<String, Class<? extends FTNode>> instructionNodeClasses = new HashMap<>();

    public static boolean isValidInstructionName(String instructionName) {
        if (instructionNodeClasses.isEmpty()) {
            FTTemplate unused = new FTTemplate(null);

            Package     packageWithAllInstructions = unused.getClass().getPackage();
            Reflections reflections                = new Reflections(packageWithAllInstructions.getName());

            Set<Class<? extends FTNode>> allNodeClasses =
                    reflections.getSubTypesOf(FTNode.class);

            for (Class<? extends FTNode> cls : allNodeClasses) {
                try {
                    Method  method                = cls.getDeclaredMethod("GetTemplateLexerSymbol");
                    Integer symbolNumber          = (Integer) method.invoke(cls);
                    String  symbolInstructionName = getInstructionNameFromSymbol(symbolNumber);
                    if (symbolInstructionName != null) {
                        instructionNodeClasses.put(symbolInstructionName, cls);
                        continue;
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    //e.printStackTrace();
                }
                try {
                    Method    method        = cls.getDeclaredMethod("GetTemplateLexerSymbols");
                    Integer[] symbolNumbers = (Integer[]) method.invoke(cls);
                    boolean   found         = false;
                    for (Integer symbolNumber : symbolNumbers) {
                        String symbolInstructionName = getInstructionNameFromSymbol(symbolNumber);
                        if (symbolInstructionName != null) {
                            instructionNodeClasses.put(symbolInstructionName, cls);
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        continue;
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    //e.printStackTrace();
                }
                try {
                    Method method             = cls.getDeclaredMethod("GetInstructionName");
                    String clsInstructionName = (String) method.invoke(cls);
                    if (clsInstructionName != null) {
                        instructionNodeClasses.put(clsInstructionName, cls);
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    //e.printStackTrace();
                }
            }
        }
        return instructionNodeClasses.containsKey(instructionName);
    }

    private static String getInstructionNameFromSymbol(Integer symbolNumber) {
        if (symbolNumber != NO_SYMBOL) {
            String symbol = TemplateLexer.VOCABULARY.getLiteralName(symbolNumber);
            return symbol.substring(1, symbol.length() - 1);
        }
        return null;
    }
}

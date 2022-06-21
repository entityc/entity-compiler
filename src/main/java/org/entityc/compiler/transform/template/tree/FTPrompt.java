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

package org.entityc.compiler.transform.template.tree;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.TemplateInstruction;
import org.entityc.compiler.doc.annotation.TemplateInstructionArgument;
import org.entityc.compiler.doc.annotation.TemplateInstructionCategory;
import org.entityc.compiler.model.entity.MTNativeType;
import org.entityc.compiler.transform.template.TemplateLexer;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.util.ECLog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.InstructionArgument;
import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.InstructionArgumentDelim;

@TemplateInstruction(category = TemplateInstructionCategory.FILE_IO,
        name = "prompt",
        usage = "`prompt `*variable*",
        summary = "Prompts the user on the command line for the input of a single string value.",
        description = "This instruction allows you to prompt the user (with a provided message) to enter a value "
                      + "with standard input. This instruction should only used in situations when it is ok to prompt "
                      + "the user since it will halt execution while waiting for the user to enter a string value.",
        contents = "The template block is executed and its output sent to the system output. Then immediately after "
                   + "that it will expect the user to enter a string value to standard input (where entering a return "
                   + "will end the input)."
)
public class FTPrompt extends FTContainerNode implements FTBodyBlock {

    private final FTBody       body = new FTBody();
    private final String       variableName;
    private final MTNativeType nativeType;

    public FTPrompt(ParserRuleContext ctx, FTContainerNode parent,
                    @TemplateInstructionArgument(optional = false,
                            description = "The variable name is the variable that will receive the value input by the "
                                          + "user. It is also used to fetch the default value in the event the user "
                                          + "just hits return.")
                    String variableName,
                    @TemplateInstructionArgument(optional = true,
                            description = "The data type for the variable that is to receive the user's response. If "
                                          + "the value doesn't match the type, it will through error and ask again.")
                    MTNativeType nativeType) {
        super(ctx, parent);
        this.variableName = variableName;
        this.nativeType   = nativeType;
    }

    @Override
    public FTBody getBody() {
        return body;
    }

    @Override
    public void transform(FTTransformSession session) {
        session.pushPromptBlock(this);
        super.transformChildren(session, false);
        session.popPromptBlock();
    }

    @Override
    public int getTemplateLexerSymbol() {
        return TemplateLexer.Prompt;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;

        formatController.addInstructionStart(indentLevel, this);
        if (variableName != null) {
            formatController.addInstructionInside(InstructionArgument, variableName, this.getStartLineNumber());
            if (nativeType != null) {
                formatController.addInstructionInside(InstructionArgumentDelim, ":", this.getStartLineNumber());
                formatController.addInstructionInside(InstructionArgument, nativeType.getDataType().getName(), this.getStartLineNumber());
            }
        }
        formatController.addInstructionEnd(this);
        super.formatChildren(formatController, indentLevel);
        formatController.addInstructionBlockEnd(indentLevel, this);
        return success;
    }

    @Override
    public boolean hasOwnBody() {
        return true;
    }

    public static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isFloat(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isDouble(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void prompt(FTTransformSession session) {
        String  text   = body.getText();
        boolean asking = true;
        while (asking) {
            asking = false;
            System.out.print(text);
            Scanner scanner = new Scanner(System.in);
            String  value   = scanner.nextLine();
            if (value.trim().length() == 0) {
                value = session.getValue(variableName).toString();
            }
            try {
                switch (nativeType.getDataType()) {

                    case INT64:
                        session.setValue(variableName, Long.parseLong(value));
                        break;
                    case INT32:
                        session.setValue(variableName, Integer.parseInt(value));
                        break;
                    case FLOAT:
                        session.setValue(variableName, Float.parseFloat(value));
                        break;
                    case DOUBLE:
                        session.setValue(variableName, Double.parseDouble(value));
                        break;
                    case BOOLEAN:
                        if (value.equals("t") || value.equals("1") || value.equals("y") || value.equals("yes")) {
                            value = "true";
                        } else if (value.equals("f") || value.equals("0") || value.equals("n") || value.equals("no")) {
                            value = "false";
                        }
                        if (value.equals("true") || value.equals("false")) {
                            session.setValue(variableName, Boolean.parseBoolean(value));
                        } else {
                            System.err.println(
                                    "ERROR: Invalid boolean value \"" + value + "\" must be \"true\", \"false\", \"yes\", or \"no\".");
                            asking = true;
                        }
                        break;
                    case UUID:
                        UUID uuid = UUID.fromString(value);
                        session.setValue(variableName, uuid);
                        break;
                    case STRING:
                        session.setValue(variableName, value);
                        break;
                    case DATE:
                        DateFormat dateFormat = new SimpleDateFormat();
                        Date date = dateFormat.parse(value);
                        session.setValue(variableName, date);
                        break;
                }
            } catch (NumberFormatException nfe) {
                if (nativeType.getDataType() == MTNativeType.DataType.INT32
                    || nativeType.getDataType() == MTNativeType.DataType.INT64) {
                    System.err.println("ERROR: Invalid integer: " + value);
                } else {
                    System.err.println("ERROR: Invalid number: " + value);
                }
                asking = true;
            } catch (ParseException pe) {
                System.err.println("ERROR: Invalid date: " + value);
                asking = true;
            } catch (IllegalArgumentException iae) {
                System.err.println("ERROR: Invalid UUID: " + value);
                asking = true;
            }
        }
    }
}


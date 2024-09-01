/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.EntityCompiler;
import org.entityc.compiler.doc.annotation.TemplateInstruction;
import org.entityc.compiler.doc.annotation.TemplateInstructionArgument;
import org.entityc.compiler.doc.annotation.TemplateInstructionCategory;
import org.entityc.compiler.transform.template.TemplateLexer;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.*;

@TemplateInstruction(category = TemplateInstructionCategory.FUNCTION,
    name = "function",
    usage = "`function `*name*`(`[*input*[`,` *input*]]`)`[`-> (`*output*[`,`*output*]`)`",
    summary = "Allows you to define a function with both inputs and outputs.",
    description = "The function instruction allows you to define a block of template code that you can call by name, "
                  + "passing values in and receiving values out. A function is called with a `call` instruction. "
                  + "Functions can be declared at any scope and calls to those functions must be made within the same "
                  + "scope. If you have two functions declared with the same name inside the same scope, the most "
                  + "recently defined function to the point of the `call` is used.",
    contents =
        "The template code inside the function is executed upon calling the function. The limitation with the `file` "
        + "instruction still holds, you cannot execute a `file` instruction inside another `file` "
        + "instruction, even if it is included in a function. If you do include a `file` instruction in a "
        + "function just make sure no other `file` instruction is active at the time you call the function.",
    seeAlso = {"call", "return"}
)
public class FTFunction extends FTContainerNode {

    private final String                          name;
    private final List<FTFunctionArgument>        inputArgumentsInOrder  = new ArrayList<>();
    private final List<FTFunctionArgument>        outputArgumentsInOrder = new ArrayList<>();
    private final Map<String, FTFunctionArgument> inputArguments         = new HashMap<>();
    private final Map<String, FTFunctionArgument> outputArguments        = new HashMap<>();

    public FTFunction(ParserRuleContext ctx, FTContainerNode parent,
                      @TemplateInstructionArgument(description = "The name of the function.")
                          String name) {
        super(ctx, parent);
        this.name = name;
    }

    public boolean hasInputs() {
        return inputArgumentsInOrder.size() > 0;
    }

    public boolean hasOutputs() {
        return outputArgumentsInOrder.size() > 0;
    }

    public List<FTFunctionArgument> getInputArguments() {
        return inputArgumentsInOrder;
    }

    public void addInputArgument(
        @TemplateInstructionArgument(optional = true,
            description = "An input argument of the function.")
            FTFunctionArgument input) {
        inputArgumentsInOrder.add(input);
        inputArguments.put(input.getName(), input);
    }

    public void addOutputArgument(
        @TemplateInstructionArgument(optional = true,
            description = "An output argument of the function.")
            FTFunctionArgument output) {
        outputArgumentsInOrder.add(output);
        outputArguments.put(output.getName(), output);
    }

    public boolean hasOutputArgName(String argName) {
        return outputArguments.containsKey(argName);
    }

    public List<FTFunctionArgument> getOutputArguments() {
        return outputArgumentsInOrder;
    }

    public String getName() {
        return name;
    }

    public boolean hasInputArgName(String argName) {
        return inputArguments.containsKey(argName);
    }

    @Override
    public void transform(FTTransformSession session) {
    }

    public Map<String, Object> call(FTTransformSession session) {

        if (EntityCompiler.isVerbose()) {
            ECLog.logInfo("Calling function: " + getName());
        }
        // run the function code
        super.transform(session);

        // process the outputs
        Map<String, Object> outputValues = new HashMap<>();
        for (FTFunctionArgument outputArgument : outputArgumentsInOrder) {
            Object value = session.getValue(outputArgument.getName());
            if (value != null) {
                outputValues.put(outputArgument.getName(), value);
            }
            else {
                //ECLog.logInfo("Output " + outputArgName + " is null.");
            }
        }
        if (session.isPendingReturn()) {
            session.setPendingReturn(false); // now clear it
        }
        return outputValues;
    }

    @Override
    public int getTemplateLexerSymbol() {
        return GetTemplateLexerSymbol();
    }

    public static int GetTemplateLexerSymbol() {
        return TemplateLexer.Function;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;
        formatController.addInstructionStart(indentLevel, this);
        formatController.addInstructionInside(FunctionName, name, this.getStartLineNumber());
        this.addDescriptionToFormatController(formatController);
        formatController.addInstructionInside(FunctionOpenParen, "(", -1);
        boolean first = true;
        for (FTFunctionArgument arg : inputArgumentsInOrder) {
            if (!first) {
                formatController.addInstructionInside(FunctionDelim, ",", -1);
            }
            formatController.addInstructionInside(FunctionArgName, arg.getName(), -1);
            arg.addDescriptionToFormatController(formatController);
            first = false;
        }
        formatController.addInstructionInside(FunctionCloseParen, ")", -1);
        if (outputArgumentsInOrder.size() > 0) {
            formatController.addInstructionInside(FunctionOutputDecl, "->", -1);
            formatController.addInstructionInside(FunctionOpenParen, "(", -1);
            first = true;
            for (FTFunctionArgument arg : outputArgumentsInOrder) {
                if (!first) {
                    formatController.addInstructionInside(FunctionDelim, ",", -1);
                }
                formatController.addInstructionInside(FunctionArgName, arg.getName(), -1);
                arg.addDescriptionToFormatController(formatController);
                first = false;
            }
            formatController.addInstructionInside(FunctionCloseParen, ")", -1);
        }
        formatController.addInstructionEnd(this);
        super.formatChildren(formatController, indentLevel);
        formatController.addInstructionBlockEnd(indentLevel, this);
        return success;
    }
}

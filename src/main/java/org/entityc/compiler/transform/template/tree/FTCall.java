/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.TemplateInstruction;
import org.entityc.compiler.doc.annotation.TemplateInstructionArgument;
import org.entityc.compiler.doc.annotation.TemplateInstructionCategory;
import org.entityc.compiler.transform.template.TemplateLexer;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.transform.template.tree.expression.FTOperand;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.*;

@TemplateInstruction(category = TemplateInstructionCategory.FUNCTION,
    name = "call",
    usage = "`call `*functionName*`(`[*inputName*`:`*variable*[`,`*inputName*`:`*variable*]]`)`[` -> (`[*outputName*`:`*variable*[`,`*outputName*`:`*variable*]]`)`",
    summary = "Calls a function by name with inputs and outputs.",
    description = "This instruction is used to call a function by its name and has the ability to map inputs by name "
                  + "assign them to variables to provide values to the inputs. As well, you can map outputs to variable "
                  + "names where upon calling the function will result in those variables being assigned values from "
                  + "the function.",
    seeAlso = {"function"}
)
public class FTCall extends FTNode {

    private final String                    functionName;
    private final Map<String, FTExpression> inputArgValueExpressions = new HashMap<>();
    private final List<String>              inputArgNamesInOrder     = new ArrayList<>();
    private final Map<String, FTOperand>    outputArgOperands        = new HashMap<>();
    private final List<String>              outputArgNamesInOrder    = new ArrayList<>();
    private       FTFunction                function;
    private       boolean                   explicit;

    public FTCall(ParserRuleContext ctx, FTFunction function) {
        super(ctx);
        this.function     = function;
        this.functionName = function.getName();
    }

    public FTCall(ParserRuleContext ctx,
                  @TemplateInstructionArgument(description = "The name of the function to call.")
                      String functionName) {
        super(ctx);
        this.functionName = functionName;
    }

    public boolean isExternalFunction() {
        return this.function == null;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setOutputArgOperand(String name, FTOperand operand) {
        outputArgOperands.put(name, operand);
        outputArgNamesInOrder.add(name);
    }

    public void resolve(FTContainerNode parentContainer) {
        if (this.function != null) {
            return; // no need to resolve
        }
        this.function = parentContainer.findFunctionWithName(functionName);
        if (this.function == null) {
            ECLog.logFatal(this, "Could not find function: " + functionName);
        }

        for (FTFunctionArgument argument : function.getOutputArguments()) {
            if (outputArgOperands.containsKey(argument.getName())) {
                continue;
            }
            if (!function.hasOutputArgName(argument.getName())) {
                ECLog.logFatal(this, "Argument \"" + argument.getName() + "\" not found in the output of function \""
                                     + functionName + "\".");
            }
        }
        if (!isExplicit()) {
            // for those not assigned from function, simply use an expression that is the name of the argument.
            for (FTFunctionArgument argument : function.getInputArguments()) {
                if (inputArgValueExpressions.containsKey(argument.getName())) {
                    continue;
                }
                if (!function.hasInputArgName(argument.getName())) {
                    ECLog.logFatal(this, "Argument \"" + argument.getName() + "\" not found in the input of function \""
                                         + functionName + "\".");
                }

                this.setInputArgValueExpression(argument.getName(), new FTOperand(null, argument.getName()));
            }
        }
        else {
            if (inputArgValueExpressions.size() < function.getInputArguments().size()) {
                ECLog.logFatal(this, "The call with explicit arguments is missing some arguments.");
            }
        }
    }

    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    public void setInputArgValueExpression(String name, FTExpression value) {
        inputArgValueExpressions.put(name, value);
        inputArgNamesInOrder.add(name);
    }

    @Override
    public void transform(FTTransformSession session) {

        if (function == null) {
            ECLog.logFatal(this, "Could not call unresolved function: " + functionName);
        }
        Map<String, Object> values = new HashMap<>();

        // first resolve the input argument value expressions
        for (String name : inputArgValueExpressions.keySet()) {
            FTExpression expression = inputArgValueExpressions.get(name);
            values.put(name, expression.getValue(session));
        }
        // push a new value map onto a stack and set only the arguments of the function onto
        // the new value map of this scope.
        session.pushFunctionScope(function);

        // now copy the resolved input argument values to the new value map
        for (String name : inputArgValueExpressions.keySet()) {
            session.setValue(name, values.get(name));
        }
        Map<String, Object> outputs = function.call(session);

        // restore previous value map
        session.popFunctionScope();

        // assign output values based on call
        for (String outputName : outputArgOperands.keySet()) {
            session.setValue(outputArgOperands.get(outputName).getName(), outputs.get(outputName));
        }

        if (session.isPendingReturn()) {
            session.setPendingReturn(false); // now clear it
        }
    }

    @Override
    public void accept(FTVisitor visitor) {
        visitor.visit(this);
        for (FTExpression expression : inputArgValueExpressions.values()) {
            expression.accept(visitor);
        }
        for (FTExpression expression : outputArgOperands.values()) {
            expression.accept(visitor);
        }
    }

    @Override
    public int getTemplateLexerSymbol() {
        return GetTemplateLexerSymbol();
    }

    public static int GetTemplateLexerSymbol() {
        return TemplateLexer.Call;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;
        formatController.addInstructionStart(indentLevel, this);
        formatController.addInstructionInside(FunctionCallName, functionName, this.getStartLineNumber());
        formatController.addInstructionInside(FunctionCallOpenParen, "(", this.getStartLineNumber());
        boolean first = true;
        for (String inputArgName : inputArgNamesInOrder) {
            if (!first) {
                formatController.addInstructionInside(FunctionCallDelim, ",", this.getStartLineNumber());
            }
            formatController.addInstructionInside(FunctionCallArgName, inputArgName, this.getStartLineNumber());
            formatController.addInstructionInside(FunctionCallArgDelim, ":", this.getStartLineNumber());
            if (!inputArgValueExpressions.get(inputArgName).format(formatController, indentLevel)) {
                success = false;
            }
            first = false;
        }
        formatController.addInstructionInside(FunctionCallCloseParen, ")", this.getStartLineNumber());
        if (outputArgOperands.size() > 0) {
            formatController.addInstructionInside(FunctionCallOutputDecl, "->", this.getStartLineNumber());
            formatController.addInstructionInside(FunctionCallOpenParen, "(", this.getStartLineNumber());
            first = true;
            for (String outputArgName : outputArgNamesInOrder) {
                FTExpression expression = outputArgOperands.get(outputArgName);
                if (!first) {
                    formatController.addInstructionInside(FunctionCallDelim, ",", this.getStartLineNumber());
                }
                formatController.addInstructionInside(FunctionCallArgName, outputArgName, this.getStartLineNumber());
                formatController.addInstructionInside(FunctionCallArgDelim, ":", this.getStartLineNumber());
                if (!(expression.format(formatController, indentLevel))) {
                    success = false;
                }
                first = false;
            }
            formatController.addInstructionInside(FunctionCallCloseParen, ")", this.getStartLineNumber());
        }
        formatController.addInstructionEnd(this);
        return success;
    }
}

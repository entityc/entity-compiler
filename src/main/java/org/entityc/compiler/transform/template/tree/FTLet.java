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
import org.entityc.compiler.transform.template.tree.expression.FTOperation;

import java.util.ArrayList;

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.ExpressionOperand;

@TemplateInstruction(category = TemplateInstructionCategory.ASSIGNMENT,
        name = "let",
        usage = "`let `*variable*` `*assignmentOperator*` `*expression*",
        summary = "This is a straight forward way to assign values to a variable.",
        description = "Assigns the resulting value after evaluating the provided *expression* to the provided *variable*.")
public class FTLet extends FTNode {

    private final String               leftVariableName;
    private final FTExpression         rightExpression;
    private final FTOperation.Operator assignmentOperator;
    private final int                  assignmentOperatorSymbolType;

    public FTLet(ParserRuleContext ctx,
                 @TemplateInstructionArgument(
                         description = "The variable to receive the assigned value."
                 )
                 String variable,
                 @TemplateInstructionArgument(
                         description = "Typically this is just `=` but also supported are `+=`, `-=`, `*=` and `/=`. "
                                       + "The `+=` can be used with string variables to concatenate string values."
                 )
                 FTOperation.Operator assignmentOperator,
                 int assignmentOperatorSymbolType,
                 @TemplateInstructionArgument(
                         description = "The expression that will be evaluated."
                 )
                 FTExpression expression) {
        super(ctx);
        this.leftVariableName             = variable;
        this.assignmentOperator           = assignmentOperator;
        this.assignmentOperatorSymbolType = assignmentOperatorSymbolType;
        this.rightExpression              = expression;
    }

    public FTOperation.Operator getAssignmentOperator() {
        return assignmentOperator;
    }

    public String getLeftVariableName() {
        return leftVariableName;
    }

    @Override
    public void transform(FTTransformSession session) {
        FTExpression finalExpression = rightExpression;
        if (finalExpression != null) {
            if (assignmentOperator != FTOperation.Operator.EQUALS) {
                FTOperand               leftOperand = new FTOperand(null, leftVariableName);
                ArrayList<FTExpression> operands    = new ArrayList<>();
                operands.add(leftOperand);
                operands.add(rightExpression);
                finalExpression = new FTOperation(null, assignmentOperator, operands);
            }
        }
        Object value = finalExpression != null ?
                       finalExpression.getValue(session) :
                       null;
        session.setValue(leftVariableName, value);
    }

    @Override
    public void accept(FTVisitor visitor) {
        visitor.visit(this);
        rightExpression.accept(visitor);
    }

    @Override
    public int getTemplateLexerSymbol() {
        return TemplateLexer.Let;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;

        formatController.addInstructionStart(indentLevel, this);
        formatController.addExpressionElement(ExpressionOperand, leftVariableName, this.getStartLineNumber());
        formatController.addExpressionOperator(assignmentOperator,
                                               GetInstructionNameOfLexerSymbol(assignmentOperatorSymbolType),
                                               this.getStartLineNumber());
        if (!rightExpression.format(formatController, indentLevel)) {
            success = false;
        }
        formatController.addInstructionEnd(this);

        return success;
    }
}

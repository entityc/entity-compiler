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
import org.entityc.compiler.util.ECLog;

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.InstructionArgument;

@TemplateInstruction(category = TemplateInstructionCategory.FILE_IO,
    name = "assert",
    usage = "`assert `*condition* *level*",
    summary = "Tests the provided condition expression and if fails will print the provided message. "
              + "If the *level* is not specified or is `fatal` the compiler will exit.",
    description = "This is similar to a `$[log fatal]` but with some advantages. Firstly, it is more compact "
                  + "since it basically includes a surrounding `if` block. Secondly, you have the ability to assert "
                  + "to a non-fatal level but still have that exit (such as when you are debugging). Thirdly, you can "
                  + "perform multiple asserts at an `error` level followed by a final `fatal` assert that will fire "
                  + "if any error` asserts occurred previously - thus allowing you to find and report possibly "
                  + "multiple issues before exiting.",
    contents = "If the assert condition is **not** met, the contents of this assert block is sent to the log as the level "
               + "provided."
)
public class FTAssert extends FTContainerNode implements FTBodyBlock {

    private final FTBody body  = new FTBody();
    private       FTExpression condition = null;
    private       FTLog.Level  level = FTLog.Level.FATAL;

    public FTAssert(ParserRuleContext ctx, FTContainerNode parent,
                    @TemplateInstructionArgument(optional = false,
                    description = "The assert executes when this condition evaluates to **false**. The condition "
                                  + "can involve one of the special assert global variables (e.g., `__assert_error`, "
                                  + "`__assert_warning`, ...) to gather up many asserts into a fatal assert. For "
                                  + "instance, `$[assert !__assert_error fatal]Cannot proceed.$[/assert]`")
                    FTExpression condition,
                    @TemplateInstructionArgument(optional = true,
                     description = "The level can be used to classify the level of severity of the log output. "
                                   + "The options are `info`, `debug`, `warning`, `error` and `fatal`. For the "
                                   + "first three the only difference in the output is the prefix of each line "
                                   + "which is the level name in all caps followed by `> `. For `error`, output "
                                   + "is directed to system error. For `fatal`, output is also sent to system "
                                   + "error but after the output, the compiler terminates, stopping any further "
                                   + "execution of the template.")
                     String level) {
        super(ctx, parent);
        this.condition = condition;
        if (level != null) {
            this.level = FTLog.Level.FromName(level);
            if (this.level == null) {
                ECLog.logFatal(ctx, "Unknown log level: " + level);
            }
        }
    }

    @Override
    public FTBody getBody() {
        return body;
    }

    public void runAssert(FTTransformSession session) {

        String text = body.getText();

        if (FTIf.IsConditionMet(condition, session)) {
            return; // do nothing
        }

        switch (getLevel()) {
            case INFO:
                session.setValue("__assert_info", true);
                ECLog.logInfo(text);
                break;
            case DEBUG:
                session.setValue("__assert_debug", true);
                ECLog.logInfo(text);
                break;
            case WARNING:
                session.setValue("__assert_warning", true);
                ECLog.logWarning(text);
                break;
            case ERROR:
                session.setValue("__assert_error", true);
                ECLog.logError(text);
                break;
            case FATAL:
                session.setValue("__assert_fatal", true);
                ECLog.logFatal(text);
                break;
        }
    }

    public FTLog.Level getLevel() {
        return level;
    }

    @Override
    public void transform(FTTransformSession session) {
        session.pushAssertBlock(this);
        super.transformChildren(session, false);
        session.popAssertBlock();
    }

    @Override
    public int getTemplateLexerSymbol() {
        return TemplateLexer.Assert;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;

        formatController.addInstructionStart(indentLevel, this);
        if (!condition.format(formatController, indentLevel)) {
            success = false;
        }
        formatController.addInstructionInside(InstructionArgument, level.name, this.getStartLineNumber());
        formatController.addInstructionEnd(this);
        super.formatChildren(formatController, indentLevel);
        formatController.addInstructionBlockEnd(indentLevel, this);
        return success;
    }

    @Override
    public boolean hasOwnBody() {
        return true;
    }

}

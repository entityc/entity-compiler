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
import org.entityc.compiler.util.ECLog;

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.InstructionArgument;

@TemplateInstruction(category = TemplateInstructionCategory.FILE_IO,
    name = "log",
    usage = "`log `*level*",
    summary = "Sends template output to system output for logging purposes.",
    description = "This instruction is designed to help you debug a template by allowing you to send output to the "
                  + "system output. It has multiple levels of log output, including one that will allow you to "
                  + "terminate the compiler after it sends it output to system out.",
    contents = "The template block is executed and its output sent to the system output. If it contains a "
               + "`capture` block or any other such block instruction that redirects output, as soon as that "
               + "block has finished, it will resume with sending output to the system out until it reaches the "
               + "log's template block."
)
public class FTLog extends FTContainerNode implements FTBodyBlock {

    private final FTBody body  = new FTBody();
    private       Level  level = null;

    public FTLog(ParserRuleContext ctx, FTContainerNode parent,
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
        if (level != null) {
            this.level = Level.FromName(level);
            if (this.level == null) {
                ECLog.logFatal(ctx, "Unknown log level: " + level);
            }
        }
    }

    @Override
    public FTBody getBody() {
        return body;
    }

    public void log() {
        String text = body.getText();
        if (level == null) {
            ECLog.log(text);
            return;
        }
        switch (getLevel()) {
            case INFO:
                ECLog.logInfo(text);
                break;
            case DEBUG:
                ECLog.logInfo(text);
                break;
            case WARNING:
                ECLog.logWarning(text);
                break;
            case ERROR:
                ECLog.logError(text);
                break;
            case FATAL:
                ECLog.logFatal(text);
                break;
        }
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public void transform(FTTransformSession session) {
        session.pushLogBlock(this);
        super.transformChildren(session, false);
        session.popLogBlock();
    }

    @Override
    public int getTemplateLexerSymbol() {
        return TemplateLexer.Log;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;

        formatController.addInstructionStart(indentLevel, this);
        if (level != null) {
            formatController.addInstructionInside(InstructionArgument, level.name, this.getStartLineNumber());
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

    public enum Level {
        INFO("info"),
        DEBUG("debug"),
        WARNING("warning"),
        ERROR("error"),
        FATAL("fatal"),
        ;

        String name;

        Level(String name) {
            this.name = name;
        }

        public static Level FromName(String name) {
            for (Level level : values()) {
                if (level.name.equals(name)) {
                    return level;
                }
            }
            return null;
        }
    }
}

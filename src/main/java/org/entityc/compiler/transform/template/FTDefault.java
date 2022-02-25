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

package org.entityc.compiler.transform.template;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.TemplateInstruction;
import org.entityc.compiler.doc.annotation.TemplateInstructionCategory;
import org.entityc.compiler.transform.template.tree.FTCase;
import org.entityc.compiler.transform.template.tree.FTContainerNode;

@TemplateInstruction(category = TemplateInstructionCategory.CONTROL_FLOW,
    name = "default",
    usage = "`default`",
    summary = "The default case of a switch/case structure.",
    description = "When all `case` instructions do not match the switch expression value, it will branch "
                  + "to this instruction (if it is defined) and execute its code.",
    contents = "Code to execute when none of the `case` instructions match the switch expression value.",
    seeAlso = {"switch", "case"})
public class FTDefault extends FTCase {

    public FTDefault(ParserRuleContext ctx, FTContainerNode parent) {
        super(ctx, parent);
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.doc.annotation.ModelMethod;
import org.entityc.compiler.doc.annotation.ModelMethodCategory;
import org.entityc.compiler.doc.annotation.ModelMethodParameter;
import org.entityc.compiler.doc.annotation.TemplateInstruction;
import org.entityc.compiler.doc.annotation.TemplateInstructionArgument;
import org.entityc.compiler.doc.annotation.TemplateInstructionCategory;
import org.entityc.compiler.model.MTNamespace;
import org.entityc.compiler.transform.template.TemplateLexer;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.InstructionArgument;

@TemplateInstruction(category = TemplateInstructionCategory.PUBLISHING,
    name = "publisher",
    usage = "`publisher `*namespace*",
    summary = "Defines a block that can contain publishing outlets where authors can send code/text.",
    description = "This instruction allows you to establish a block of template code as a **publisher**. Inside the publisher "
                  + "block you can declare one or more **outlets** that will serve as insertion points for authors.")
@ModelClass(type = ModelClassType.TEMPLATE,
    description = "The Publisher class is responsible for inserting code from authors to its outlets. Inside the publisher "
                  + "block it will declare one or more outlets that an author can connect with to author code into.")
public class FTPublisher extends FTContainerNode {

    final private MTNamespace                 namespace;
    final private Map<String, List<FTOutlet>> outletsByName  = new HashMap<>();
    final private List<FTOutlet>              outletsInOrder = new ArrayList<>();

    public FTPublisher(ParserRuleContext ctx, FTContainerNode parent,
                       @TemplateInstructionArgument(
                           description = "A publisher defines a namespace that is intended to be unique "
                                         + "among all other publishers that an application developer would "
                                         + "encounter. Using a **reverse domain name** at the start of this "
                                         + "identifier would be best.")
                           MTNamespace namespace) {
        super(ctx, parent);
        this.namespace = namespace;
    }

    @ModelMethod(category = ModelMethodCategory.NAMESPACE,
        description = "Returns the namespace for this publisher.")
    public MTNamespace getNamespace() {
        return namespace;
    }

    public void addOutlet(FTOutlet outlet) {
        outletsInOrder.add(outlet);
        String outletName = outlet.getName();
        if (!outletsByName.containsKey(outletName)) {
            outletsByName.put(outletName, new ArrayList<>());
        }
        outletsByName.get(outletName).add(outlet);
    }

    @ModelMethod(category = ModelMethodCategory.OUTLET,
        description = "Returns an outlet of this publisher by name.")
    public List<FTOutlet> getOutletsByName(
        @ModelMethodParameter(description = "The name of the outlet to return.")
            String outletName) {
        return outletsByName.get(outletName);
    }

    @ModelMethod(category = ModelMethodCategory.OUTLET,
        description = "Returns all outlets of this publisher in order of declaration.")
    public List<FTOutlet> getOutlets() {
        return outletsInOrder;
    }

    @ModelMethod(category = ModelMethodCategory.OUTLET,
        description = "Indicates if this publisher has any outlets.")
    public boolean hasOutlets() {
        return !outletsInOrder.isEmpty();
    }

    @Override
    public int getTemplateLexerSymbol() {
        return TemplateLexer.Publisher;
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;

        formatController.addInstructionStart(indentLevel, this);
        formatController.addInstructionInside(InstructionArgument, namespace.toString(), this.getStartLineNumber());
        this.addDescriptionToFormatController(formatController);
        formatController.addInstructionEnd(this);
        if (!super.formatChildren(formatController, indentLevel)) {
            success = false;
        }
        formatController.addInstructionBlockEnd(indentLevel, this);
        return success;
    }
}

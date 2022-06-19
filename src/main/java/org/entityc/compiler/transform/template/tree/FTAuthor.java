/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.EntityCompiler;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.doc.annotation.ModelMethod;
import org.entityc.compiler.doc.annotation.ModelMethodCategory;
import org.entityc.compiler.doc.annotation.TemplateInstruction;
import org.entityc.compiler.doc.annotation.TemplateInstructionArgument;
import org.entityc.compiler.doc.annotation.TemplateInstructionCategory;
import org.entityc.compiler.model.MTNamespace;
import org.entityc.compiler.model.foundation.MFSet;
import org.entityc.compiler.transform.template.TemplateLexer;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.entityc.compiler.transform.template.tree.expression.FTOperation;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.*;

@TemplateInstruction(category = TemplateInstructionCategory.PUBLISHING,
        name = "author",
        usage = "`author to `*namespaces*` `[`outlet `*outletName*` `[*phase*]` `[*scope*]]",
        summary = "Allows you to send text/code to the outlet of a publisher.",
        description =
                "The author instruction defines a block of template that you want to be inserted at a publisher's outlet. "
                + "How this template code is executed by the publisher can be specified in this instruction with the "
                + "phase and scope options.",
        contents = "The block of template code this instruction surrounds will be executed by the publisher "
                   + "when it reaches the referenced outlet during its execution but only during its configured "
                   + "publisher execution *phase*. Essentially the publisher makes an execution pass for each "
                   + "of its phases, thus allowing an action decide when it executes. The configured *scope* "
                   + "of this action will determine the runtime environment that is used for execution.")
@ModelClass(type = ModelClassType.TEMPLATE,
        description = "A template author connects itself to a publisher outlet for the purpose of "
                      + "authoring (sending) code there.")
public class FTAuthor extends FTContainerNode {

    private final Set<MTNamespace> namespaces;
    private final String           outletName;
    private final FTPublishPhase   phase;
    private final FTPublishScope   scope;
    private final FTTemplate       template; // useful to know where the authoring came from for auditing purposes
    private final FTAuthor         parentAuthor;
    private final Set<MTNamespace> fullNamespaces;
    private final String           uniqueId;
    private final List<FTAuthor>   childAuthors = new ArrayList<>();
    private       FTOutlet         outlet;

    public FTAuthor(ParserRuleContext ctx, FTTemplate template, FTContainerNode parent, FTAuthor parentAuthor,
                    @TemplateInstructionArgument(
                            description =
                                    "One or more publisher namespaces (separated by a comma ',') to which the authoring "
                                    + "should be performed. "
                                    + "Any authoring options will be applied to all specified publisher namespaces.")
                    Set<MTNamespace> namespaces,
                    @TemplateInstructionArgument(
                            optional = true,
                            description = "The name of the outlet to which you want to author this block of template.")
                    String outletName,
                    @TemplateInstructionArgument(
                            optional = true,
                            description =
                                    "Sets the publishing phase when you want the authoring to occur. The default is "
                                    + "the `Connect` phase.")
                    FTPublishPhase phase,
                    @TemplateInstructionArgument(
                            optional = true,
                            description = "Sets the publishing scope this code should execute within. The default is "
                                          + "the `Author` scope.")
                    FTPublishScope scope) {
        super(ctx, parent);
        this.parentAuthor = parentAuthor;
        if (this.parentAuthor != null) {
            this.parentAuthor.childAuthors.add(this);
        }
        this.template   = template;
        this.namespaces = namespaces;
        this.outletName = outletName;
        this.phase      = phase != null ?
                          phase :
                          FTPublishPhase.Connect;
        this.scope      = scope != null ?
                          scope :
                          FTPublishScope.Author;
        uniqueId        = ctx.start.getTokenSource().getSourceName() + "::" + ctx.start.getLine() + ":"
                          + ctx.start.getCharPositionInLine() + 1;
        if (parentAuthor == null) {
            fullNamespaces = namespaces;
        } else if (namespaces != null) {
            fullNamespaces = new HashSet<>();
            if (EntityCompiler.isVerbose() && namespaces.size() > 1) {
                ECLog.logInfo("Author: " + this.getUniqueId() + " has " + namespaces.size()
                              + " publishers.");
            }
            for (MTNamespace namespace : namespaces) {
                for (MTNamespace publisherNamespace : parentAuthor.getFullNamespaces()) {
                    fullNamespaces.add(publisherNamespace.combine(ctx, namespace));
                }
            }
        } else {
            fullNamespaces = parentAuthor.getFullNamespaces();
        }
    }

    public Set<MTNamespace> getFullNamespaces() {
        return fullNamespaces;
    }

    @ModelMethod(category = ModelMethodCategory.NAMESPACE,
            description =
                    "Returns the set of declared namespaces of this author. If this author instruction is embedded in "
                    + "another author instruction then this will not be a full namespace.")
    public Set<MTNamespace> getNamespaces() {
        return namespaces;
    }

    @ModelMethod(category = ModelMethodCategory.NAMESPACE,
            description = "Returns the set of **full** namespaces associated with this author. A **full** namespace is "
                          + "one which combines parent author namespaces with this author namespaces.")
    public MFSet getFullPublisherNamespaces() {
        return new MFSet(fullNamespaces);
    }

    public void getIntermediateParents(Set<FTAuthor> authors) {
        if (!hasParent() || parentAuthor.outletName != null) {
            return;
        }
        authors.add(parentAuthor);
    }

    public boolean hasIntermediateParents() {

        if (!hasParent()) {
            return false;
        }
        if (parentAuthor.outletName != null) {
            return false;
        }
        return parentAuthor.hasIntermediateParents();
    }

    @ModelMethod(category = ModelMethodCategory.AUTHOR,
            description = "Returns the parent author instruction to this one.")
    public FTAuthor getParentAuthor() {
        return parentAuthor;
    }

    @ModelMethod(category = ModelMethodCategory.AUTHOR,
            description = "Indicates whether this author has a parent author.")
    public boolean hasParent() {
        return parentAuthor != null;
    }

    @ModelMethod(category = ModelMethodCategory.OUTLET,
            description = "Indicates whether this author has children authors that connect to an outlet and there are "
                          + "intermediate parent authors (that is, authors that don't connect to an outlet).")
    public boolean hasChildOutletsWithIntermediateParents() {
        for (FTAuthor child : getChildOutletAuthors()) {
            if (child.hasIntermediateParents()) {
                return true;
            }
        }
        return false;
    }

    @ModelMethod(category = ModelMethodCategory.AUTHOR,
            description = "Returns the list (if any) of child authors.")
    public List<FTAuthor> getChildAuthors() {
        return childAuthors;
    }

    @ModelMethod(category = ModelMethodCategory.OUTLET,
            description = "Returns the list of child authors that connect to an outlet.")
    public List<FTAuthor> getChildOutletAuthors() {
        ArrayList<FTAuthor> outletAuthors = new ArrayList<>();
        for (FTAuthor childAuthor : childAuthors) {
            if (childAuthor.isOutletAuthor()) {
                outletAuthors.add(childAuthor);
            }
            if (childAuthor.hasChildAuthors()) {
                outletAuthors.addAll(childAuthor.getChildOutletAuthors());
            }
        }
        return outletAuthors;
    }

    @ModelMethod(category = ModelMethodCategory.OUTLET,
            description = "Indicates whether this author has child authors that connect to an outlet.")
    public boolean hasChildOutletAuthors() {
        return !getChildOutletAuthors().isEmpty();
    }

    @ModelMethod(category = ModelMethodCategory.AUTHOR,
            description = "Indicates whether this author has child authors.")
    public boolean hasChildAuthors() {
        return !childAuthors.isEmpty();
    }

    @ModelMethod(category = ModelMethodCategory.OUTLET,
            description = "Indicates whether this author connects to an outlet.")
    public boolean isOutletAuthor() {
        return outletName != null;
    }

    @ModelMethod(category = ModelMethodCategory.AUTHOR,
            description = "Returns the top author of an author hierarchy starting from this author.")
    public FTAuthor getTopAuthor() {
        if (parentAuthor != null) {
            return parentAuthor.getTopAuthor();
        }
        return this;
    }

    @ModelMethod(category = ModelMethodCategory.NAMESPACE,
            description = "Returns the namespace of the top author in an author hierarchy starting from this author.")
    public MTNamespace getTopPublisherNamespace() {
        if (parentAuthor != null) {
            return parentAuthor.getTopPublisherNamespace();
        }
        return namespaces.iterator().next();
    }

    @ModelMethod(category = ModelMethodCategory.TEMPLATE,
            description = "Returns the template in which this author was declared.")
    public FTTemplate getTemplate() {
        return template;
    }

    @ModelMethod(category = ModelMethodCategory.OUTLET,
            description = "If this author defines an outlet, this returns that outlet. Otherwise, it returns `null`.")
    public FTOutlet getOutlet() {
        return outlet;
    }

    public void setOutlet(FTOutlet outlet) {
        this.outlet = outlet;
    }

    @ModelMethod(category = ModelMethodCategory.PUBLISHER,
            description = "Returns the declared publishing phase in which this author should execute.")
    public FTPublishPhase getPhase() {
        return phase;
    }

    @ModelMethod(category = ModelMethodCategory.PUBLISHER,
            description = "Returns the declared scope in which this author will execute.")
    public FTPublishScope getScope() {
        return scope;
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o) {
            return true;
        }
        // null check
        if (o == null) {
            return false;
        }
        // type check and cast
        if (getClass() != o.getClass()) {
            return false;
        }
        FTAuthor author = (FTAuthor) o;
        return uniqueId.equals(author.getUniqueId());
    }

    @ModelMethod(category = ModelMethodCategory.AUTHOR,
            description = "Returns a unique ID for this author.")
    public String getUniqueId() {
        return uniqueId;
    }

    @Override
    public void transform(FTTransformSession session) {
        // don't run its contents
    }

    public void transformFromOutlet(FTTransformSession session) {
        super.transform(session);
    }

    @Override
    public int hashCode() {
        return uniqueId.hashCode();
    }

    @Override
    public boolean format(TemplateFormatController formatController, int indentLevel) {
        boolean success = true;

        //   : Author To namespaceIdent? (Outlet IDENT authorOption* descriptionTag*)?

        formatController.addInstructionStart(indentLevel, this);
        formatController.addInstructionInside(InstructionKeyword, "to", this.getStartLineNumber());
        if (this.namespaces != null) {
            for (Iterator<MTNamespace> it = this.namespaces.iterator(); it.hasNext(); ) {
                formatController.addInstructionInside(InstructionArgument, it.next().toString(),
                                                      this.getStartLineNumber());
                if (it.hasNext()) {
                    formatController.addInstructionInside(InstructionArgumentDelim, ",", -1);
                }
            }
        }
        if (this.getOutletName() != null) {
            formatController.addInstructionInside(InstructionKeyword, "outlet", this.getStartLineNumber());
            formatController.addInstructionInside(InstructionArgument, this.getOutletName(), this.getStartLineNumber());
            if (this.scope != null && !this.scope.isDefault()) {
                formatController.addInstructionInside(InstructionKeyword, "scope", -1);
                formatController.addExpressionOperator(FTOperation.Operator.EQUALS,
                                                       FTOperation.Operator.EQUALS.getSymbol(), -1);
                formatController.addInstructionInside(InstructionKeyword, this.scope.getName(), -1);
            }
            if (this.phase != null && !this.phase.isDefault()) {
                formatController.addInstructionInside(InstructionKeyword, "phase", -1);
                formatController.addExpressionOperator(FTOperation.Operator.EQUALS,
                                                       FTOperation.Operator.EQUALS.getSymbol(), -1);
                formatController.addInstructionInside(InstructionKeyword, this.phase.getName(), -1);
            }
        }
        this.addDescriptionToFormatController(formatController);
        formatController.addInstructionEnd(this);
        super.formatChildren(formatController, indentLevel);
        formatController.addInstructionBlockEnd(indentLevel, this);
        return success;
    }

    @ModelMethod(category = ModelMethodCategory.OUTLET,
            description = "Returns the outlet for this author, if it has one. Otherwise it will return `null`.")
    public String getOutletName() {
        return outletName;
    }

    @ModelMethod(category = ModelMethodCategory.NAMESPACE,
            description = "Returns the set of publisher namespaces used by this author.")
    public MFSet getPublisherNamespaces() {
        return new MFSet(namespaces);
    }

    @Override
    public int getTemplateLexerSymbol() {
        return GetTemplateLexerSymbol();
    }

    public static int GetTemplateLexerSymbol() {
        return TemplateLexer.Author;
    }
}

/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

import org.entityc.compiler.transform.template.TemplateLexer;
import org.entityc.compiler.transform.template.formatter.ConfigurableElement;
import org.entityc.compiler.transform.template.formatter.TemplateFormatController;
import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.util.ECStringUtil;

import java.util.ArrayList;
import java.util.List;

import static org.entityc.compiler.transform.template.formatter.ConfigurableElement.*;

public class FTDescription extends FTNode {

    public final static String       DefaultCategory = "main";
    final               List<String> categories      = new ArrayList<>();
    private             String       descriptionText;

    public FTDescription(ParserRuleContext ctx, List<String> categories, String descriptionText) {
        super(ctx);
        this.categories.addAll(categories);
        this.descriptionText = descriptionText;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    @Override
    public void accept(FTVisitor visitor) {

    }

    @Override
    public int getTemplateLexerSymbol() {
        return TemplateLexer.Description;
    }

    public boolean hasCategory(String category) {
        return categories.contains(category);
    }

    public boolean format(TemplateFormatController formatController, int indentLevel) {
        formatController.addInstructionStart(indentLevel, this);
        formatCategoriesAndText(formatController);
        formatController.addInstructionEnd(this);
        return true;
    }

    private void formatCategoriesAndText(TemplateFormatController formatController) {
        boolean first = true;
        if (categories.size() != 1 || !categories.get(0).equals(DefaultCategory)) {
            for (String category : categories) {
                if (!first) {
                    formatController.addInstructionInside(ConfigurableElement.DescriptionCategoryDelim, ",", -1);
                }
                formatController.addInstructionInside(DescriptionCategory, category, -1);
                first = false;
            }
        }
        formatController.addInstructionInside(DescriptionString, "\"" + ECStringUtil.DoubleEscapeDoubleQuotes(descriptionText) + "\"",
                                              -1);
    }

    public boolean format(TemplateFormatController formatController) {
        formatController.addInstructionInside(Description, "D", -1);
        formatCategoriesAndText(formatController);
        return true;
    }

    public void append(FTDescription description) {
        this.descriptionText += " " + description.descriptionText.trim();
    }

    public boolean isMultiline() {
        return this.descriptionText.contains("\n");
    }

    public boolean isEmpty() {
        return this.descriptionText.isEmpty();
    }

    public boolean hasSameCategories(FTDescription otherDescription) {
        return categories.size() == otherDescription.categories.size() && categories.containsAll(
                otherDescription.categories);
    }
}

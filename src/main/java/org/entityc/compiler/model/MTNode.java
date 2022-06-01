/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model;

import org.entityc.compiler.model.foundation.MFObject;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.util.ECLog;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Pattern;

public abstract class MTNode extends MFObject {

    private final List<String>        tags      = new ArrayList<>();
    private final Map<String, Object> tagValues = new HashMap<>();
    protected     String              description;
    private       String              summary;
    private       String              detail;
    private       int                 startLineNumber;
    private       int                 startCharPosition;
    private       int                 endLineNumber;
    private       int                 endCharPosition;
    private       String              sourceName;
    private       ParserRuleContext   parserRuleContext;
    private       boolean             included  = false;

    public MTNode() {
    }

    public MTNode(ParserRuleContext ctx) {
        setParserContext(ctx);
    }

    private void setParserContext(ParserRuleContext ctx) {
        if (ctx != null) {
            parserRuleContext = ctx;
            startLineNumber = ctx.start.getLine();
            startCharPosition = ctx.start.getCharPositionInLine() + 1;
            endLineNumber = ctx.stop.getLine();
            endCharPosition = ctx.stop.getCharPositionInLine() + 1;
            sourceName = ctx.start.getTokenSource().getSourceName();
        }
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public boolean hasSummary() {
        return this.summary != null && !this.summary.isEmpty();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean hasDetail() {
        return this.detail != null && !this.detail.isEmpty();
    }

    public boolean isIncluded() {
        return included;
    }

    public void setIncluded(boolean included) {
        this.included = included;
    }

    abstract public void accept(MTVisitor visitor);

    public boolean hasDescription() {
        return this.description != null && !this.description.isEmpty();
    }

    public int getStartLineNumber() {
        return startLineNumber;
    }

    public int getStartCharPosition() {
        return startCharPosition;
    }

    public int getEndLineNumber() {
        return endLineNumber;
    }

    public int getEndCharPosition() {
        return endCharPosition;
    }

    public String getSourceName() {
        return sourceName;
    }

    public ParserRuleContext getParserRuleContext() {
        return parserRuleContext;
    }

    public void setDescriptionFrom(MTNode primary, MTNode secondary) {
        String descriptionToCopy = null;
        if (primary != null) {
            descriptionToCopy = primary.getDescriptionWithFallback(secondary);
        } else if (secondary != null) {
            descriptionToCopy = secondary.getDescription();
        }
        if (descriptionToCopy != null) {
            setDescription(descriptionToCopy);
        }
    }

    public String getDescriptionWithFallback(MTNode fromOtherNode) {
        if (getDescription() != null && getDescription().length() > 0) {
            return getDescription();
        } else if (fromOtherNode != null && fromOtherNode.getDescription() != null && fromOtherNode.getDescription().length() > 0) {
            return fromOtherNode.getDescription();
        }
        return null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void appendDescription(String description) {
        String spaceOrNot = this.description == null || this.description.endsWith("\n") ? "" : " ";
        this.description += spaceOrNot + description;
    }

    public void appendSummary(String summary) {
        String spaceOrNot = this.summary == null || this.summary.endsWith("\n") ? "" : " ";
        this.summary += spaceOrNot + summary;
    }

    public void appendDetail(String detail) {
        String spaceOrNot = this.detail == null || this.detail.endsWith("\n") ? "" : " ";
        this.detail += spaceOrNot + detail;
    }

    public String getText() {
        return parserRuleContext.getText();
    }

    public boolean hasTags() {
        return tags.size() > 0;
    }

    public boolean hasTag(String tag) {
        for (String t : tags) {
            if (tag.equals(t)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasTagPrefixed(String tagPrefix) {
        for (String t : tags) {
            if (t.startsWith(tagPrefix)) {
                return true;
            }
        }
        return false;
    }

    public Collection<String> getTagsMinusPrefix(String tagPrefix) {
        ArrayList<String> tagSuffixes  = new ArrayList<>();
        int               prefixLength = tagPrefix.length();
        for (String t : tags) {
            if (t.startsWith(tagPrefix)) {
                tagSuffixes.add(t.substring(prefixLength));
            }
        }
        return tagSuffixes;
    }

    public void addTagsWithValues(List<Vector<String>> tagsToAdd) {
        for (Vector<String> tagParts : tagsToAdd) {
            String tag = tagParts.elementAt(0);
            addTag(tag);
            if (tagParts.size() > 1) {
                tagValues.put(tag, tagParts.elementAt(1));
            }
        }
    }

    public void addTag(String tag) {
        if (!Pattern.matches("^[a-zA-Z0-9\\-:]++$", tag)) {
            ECLog.logFatal(this, "Tag string not valid: " + tag);
        }
        tags.add(tag);
    }

    public List<String> getTags() {
        return tags;
    }

    public List<Vector<String>> getTagsWithValues() {
        List<Vector<String>> tagList = new ArrayList<>();
        for (String tag : tags) {
            Vector<String> tagWithValue = new Vector<>();
            tagWithValue.add(tag);
            if (tagValues.get(tag) != null) {
                tagWithValue.add((String)tagValues.get(tag));
            }
            tagList.add(tagWithValue);
        }
        return tagList;
    }

    public String tagsSeparatedBy(String delimiter) {
        if (delimiter == null) {
            delimiter = ", ";
        }
        return String.join(delimiter, tags);
    }

    public Object tagValue(FTTransformSession session, String tag) {
        return tagValues.get(tag);
    }

    public void setTagValue(String tag, Object value) {
        tagValues.put(tag, value);
    }

    public void acquireTags(MTNode otherNode) {
        tags.addAll(otherNode.tags);
        tagValues.putAll(otherNode.tagValues);
    }

    public void acquireDescriptions(MTNode otherNode) {
        appendDescription(otherNode.getDescription());
        appendSummary(otherNode.getSummary());
        appendDetail(otherNode.getDetail());
    }
}

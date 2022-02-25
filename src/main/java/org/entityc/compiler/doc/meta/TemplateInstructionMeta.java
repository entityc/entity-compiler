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

package org.entityc.compiler.doc.meta;

import org.entityc.compiler.doc.annotation.TemplateInstructionCategory;
import org.entityc.compiler.model.foundation.MFObject;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TemplateInstructionMeta extends MFObject {

    private TemplateInstructionCategory           category;
    private String                                name;
    private String                                summary;
    private String                                description;
    private TemplateInstructionUsageMeta          primaryUsage;
    private String                                contents;
    private boolean                               blockType;
    private String[]                              seeAlsoClassNames;
    private List<TemplateInstructionArgumentMeta> arguments = new ArrayList<>();
    private List<TemplateInstructionUsageMeta>    usages    = new ArrayList<>();
    private List<TemplateInstructionMeta>         seeAlso   = new ArrayList<>();

    public TemplateInstructionMeta(TemplateInstructionCategory category,
                                   String name,
                                   String summary,
                                   String description,
                                   TemplateInstructionUsageMeta primaryUsage,
                                   String contents,
                                   String[] seeAlsoClassNames) {
        super();
        this.category          = category;
        this.name              = name;
        this.summary           = summary;
        this.description       = description;
        this.primaryUsage      = primaryUsage;
        this.contents          = contents;
        this.seeAlsoClassNames = seeAlsoClassNames;
    }

    public String getSummary() {
        return summary;
    }

    public List<TemplateInstructionMeta> getSeeAlso() {
        return seeAlso;
    }

    public boolean hasSeeAlso() {
        return seeAlso.size() > 0;
    }

    public String getPrimaryUsage() {
        if (hasMultipleUsages()) {
            return primaryUsage.getDirectUsage();
        }
        return primaryUsage.getUsage();
    }

    public boolean hasMultipleUsages() {
        return usages.size() > 0;
    }

    public boolean isFullUsage() {
        return primaryUsage.isFullUsage();
    }

    public String getContents() {
        return contents;
    }

    public boolean isBlockType() {
        return blockType;
    }

    public void setBlockType(boolean blockType) {
        this.blockType = blockType;
    }

    public TemplateInstructionCategory getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<TemplateInstructionUsageMeta> getUsages() {
        return usages;
    }

    public boolean hasArguments() {
        return !arguments.isEmpty();
    }

    public List<TemplateInstructionArgumentMeta> getArguments() {
        return arguments;
    }

    public void addArgument(TemplateInstructionArgumentMeta argumentMeta) {
        this.arguments.add(argumentMeta);
    }

    public void addUsages(List<TemplateInstructionUsageMeta> usages) {
        this.usages.addAll(usages);
    }

    public void resolveSeeAlso(Map<String, TemplateInstructionMeta> instructionMetaMap) {
        for (String seeAlsoClassName : seeAlsoClassNames) {
            if (!instructionMetaMap.containsKey(seeAlsoClassName)) {
                ECLog.logFatal("Instruction declared a see also class that doesn't exist: " + seeAlsoClassName);
            }
            this.seeAlso.add(instructionMetaMap.get(seeAlsoClassName));
        }
    }
}

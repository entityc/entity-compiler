/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model;

import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.entity.MTTemplateSupport;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.stream.Stream;

public class MTNamespace extends MTNode implements MTTemplateSupport {

    private final boolean  relativeToSpace;
    private String[] segments;
    private MTSpace  space;

    public MTNamespace(ParserRuleContext ctx, String[] segments, boolean relativeToSpace) {
        super(ctx);
        this.segments = segments;
        this.relativeToSpace = relativeToSpace;
    }

    public MTSpace getSpace() {
        return space;
    }

    public void setSpace(MTSpace space) {
        this.space = space;
    }

    public boolean isRelativeToSpace() {
        return relativeToSpace;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    public int getSegmentCount() {
        return segments.length;
    }

    public String toString() {
        return getFullname(".");
    }

    public String getFullname(String delim) {
        String output = "";
        if (relativeToSpace && space != null && space.getNamespace() != null) {
            output = space.getNamespace().getFullname(delim);
            if (segments.length > 0) {
                output += delim;
            }
        }
        for (int i = 0; i < segments.length; i++) {
            if (i > 0) {
                output += delim;
            }
            output += segments[i];
        }
        return output;
    }

    public MTNamespace combine(ParserRuleContext ctx, MTNamespace childNamespace) {
        String[] combinedSegments = null;
        if (segments == null) {
            combinedSegments = childNamespace.getSegments();
        } else if (childNamespace.getSegments() == null) {
            combinedSegments = segments;
        } else {
            combinedSegments = Stream.of(segments, childNamespace.segments).flatMap(Stream::of).toArray(String[]::new);
        }

        return new MTNamespace(ctx, combinedSegments, relativeToSpace);
    }

    public String[] getSegments() {
        return segments;
    }

    public void setSegments(String[] segments) {
        this.segments = segments;
    }
}

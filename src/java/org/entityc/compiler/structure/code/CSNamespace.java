/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.structure.code;

import java.util.ArrayList;

public class CSNamespace extends CSNode {

    private String[] segments;

    public CSNamespace(String[] segments) {
        this.segments = segments;
    }

    @Override
    public void accept(CSVisitor visitor) {

    }

    public String[] getSegments() {
        return segments;
    }

    public void addSegment(String segment) {
        ArrayList<String> segmentList = new ArrayList<String>();
        for (String s : segments) {
            segmentList.add(s);
        }
        segmentList.add(segment);
        segments = segmentList.toArray(segments);
    }
}

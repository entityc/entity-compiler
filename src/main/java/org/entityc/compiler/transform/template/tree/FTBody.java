/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree;

import java.util.ArrayList;
import java.util.List;

public class FTBody {

    List<BodyElement> elements = new ArrayList<>();

    public void append(String text) {
        elements.add(new BodyText(text));
    }

    public void append(FTReceive receive) {
        elements.add(new BodyReceive(receive));
    }

    public String getText() {
        StringBuffer buffer = new StringBuffer();
        for (BodyElement element : elements) {
            buffer.append(element.getText());
        }
        return buffer.toString();
    }

    public void clear() {
        elements.clear();
    }

    private abstract class BodyElement {

        abstract String getText();
    }

    private class BodyText extends BodyElement {

        private final String text;

        BodyText(String text) {
            this.text = text;
        }

        String getText() {
            return text;
        }
    }

    private class BodyReceive extends BodyElement {

        private final FTReceive receive;

        BodyReceive(FTReceive receive) {
            this.receive = receive;
        }

        @Override
        String getText() {
            return receive.getText();
        }
    }
}

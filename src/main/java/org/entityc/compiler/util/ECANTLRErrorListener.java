/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.util;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class ECANTLRErrorListener extends BaseErrorListener {

    private final String filename;

    public ECANTLRErrorListener(String filename) {
        this.filename = filename;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        System.err.println(filename + ":" + line + "," + charPositionInLine + " " + msg);
//        super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
    }
}

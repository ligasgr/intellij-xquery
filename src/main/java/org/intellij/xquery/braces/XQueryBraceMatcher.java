/*
 * Copyright 2013-2014 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
 * (see the CONTRIBUTORS file).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.intellij.xquery.braces;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.intellij.xquery.psi.XQueryBasicTypes;
import org.intellij.xquery.psi.XQueryTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * User: ligasgr
 * Date: 06/07/13
 * Time: 23:35
 */
public class XQueryBraceMatcher implements PairedBraceMatcher {
    private static final BracePair[] PAIRS = new BracePair[]{
            new BracePair(XQueryTypes.L_C_BRACE, XQueryTypes.R_C_BRACE, true),
            new BracePair(XQueryTypes.L_BRACKET, XQueryTypes.R_BRACKET, false),
            new BracePair(XQueryTypes.L_PAR, XQueryTypes.R_PAR, false),
            new BracePair(XQueryTypes.XMLSTARTTAGSTART, XQueryTypes.XMLEMPTYELEMENTEND, false),
            new BracePair(XQueryTypes.XMLSTARTTAGSTART, XQueryTypes.XMLTAGEND, false),
            new BracePair(XQueryTypes.XMLENDTAGSTART, XQueryTypes.XMLTAGEND, false),
            new BracePair(XQueryTypes.DIR_COMMENT_BEGIN, XQueryTypes.DIR_COMMENT_END, false),
            new BracePair(XQueryTypes.PRAGMA_BEGIN, XQueryTypes.PRAGMA_END, false),
            new BracePair(XQueryTypes.CDATA_BEGIN, XQueryTypes.CDATA_END, false),
            new BracePair(XQueryTypes.PI_BEGIN, XQueryTypes.PI_END, false),
            new BracePair(XQueryBasicTypes.EXPR_COMMENT_START, XQueryBasicTypes.EXPR_COMMENT_END, false),
    };

    @Override
    public BracePair[] getPairs() {
        return PAIRS;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType,
                                                   @Nullable IElementType contextType) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}


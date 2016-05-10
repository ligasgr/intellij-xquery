/*
 * Copyright 2013-2016 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.quotes;

import com.intellij.codeInsight.editorActions.QuoteHandler;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

import static org.intellij.xquery.psi.XQueryTypes.*;

public class XQueryQuoteHandler implements QuoteHandler {
    private final TokenSet myLiteralTokenSet;

    public XQueryQuoteHandler() {
        myLiteralTokenSet = TokenSet.create(OPENINGQUOT, OPENINGAPOS, CLOSINGQUOT, CLOSINGAPOS, CHAR, STRINGCHAR, PREDEFINEDENTITYREF, CHARREF);
    }

    @Override
    public boolean isClosingQuote(HighlighterIterator iterator, int offset) {
        IElementType tokenType = iterator.getTokenType();
        if (tokenType == CLOSINGQUOT || tokenType == CLOSINGAPOS){
            int start = iterator.getStart();
            int end = iterator.getEnd();
            return end - start >= 1 && offset == end - 1;
        }

        return false;
    }

    @Override
    public boolean isOpeningQuote(HighlighterIterator iterator, int offset) {
        if (OPENINGQUOT == iterator.getTokenType() || OPENINGAPOS == iterator.getTokenType()){
            int start = iterator.getStart();
            return offset == start;
        }

        return false;
    }

    @Override
    public boolean hasNonClosedLiteral(Editor editor, HighlighterIterator iterator, int offset) {
        int start = iterator.getStart();
        try {
            Document doc = editor.getDocument();
            CharSequence chars = doc.getCharsSequence();
            int lineEnd = doc.getLineEndOffset(doc.getLineNumber(offset));

            while (!iterator.atEnd() && iterator.getStart() < lineEnd) {
                IElementType tokenType = iterator.getTokenType();

                if (myLiteralTokenSet.contains(tokenType)) {
                    if (isNonClosedLiteral(iterator, chars)) return true;
                }
                iterator.advance();
            }
        }
        finally {
            while(iterator.atEnd() || iterator.getStart() != start) iterator.retreat();
        }

        return false;
    }

    protected boolean isNonClosedLiteral(HighlighterIterator iterator, CharSequence chars) {
        if (iterator.getStart() >= iterator.getEnd() - 1 ||
                chars.charAt(iterator.getEnd() - 1) != '\"' && chars.charAt(iterator.getEnd() - 1) != '\'') {
            return true;
        }
        return false;
    }

    @Override
    public boolean isInsideLiteral(HighlighterIterator iterator) {
        return myLiteralTokenSet.contains(iterator.getTokenType());
    }
}

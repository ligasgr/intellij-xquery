/*
 * Copyright 2013 Grzegorz Ligas <ligasgr@gmail.com> and other contributors (see the CONTRIBUTORS file).
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
package org.intellij.xquery.completion.keyword;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.tree.IElementType;

import java.util.LinkedList;
import java.util.List;

import static org.intellij.xquery.lexer.XQueryLexer.KEYWORDS;

/**
 * User: ligasgr
 * Date: 28/11/13
 * Time: 14:26
 */
public class KeywordCollector {
    public List<LookupElement> getProposedLookUpItems() {
        List<LookupElement> result = new LinkedList<LookupElement>();
        for (IElementType keywordTokenType : KEYWORDS.getTypes()) {
            result.add(LookupElementBuilder.create(keywordTokenType.toString()).bold());
        }
        return result;
    }
}

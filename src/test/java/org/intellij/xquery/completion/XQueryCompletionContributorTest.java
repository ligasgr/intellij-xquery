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

package org.intellij.xquery.completion;

import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.intellij.xquery.XQueryBaseTestCase;
import org.intellij.xquery.XQueryFileType;

import java.util.List;

import static org.intellij.xquery.lexer.XQueryLexer.KEYWORDS;

/**
 * User: ligasgr
 * Date: 02/08/13
 * Time: 13:46
 */
public class XQueryCompletionContributorTest extends XQueryBaseTestCase {

    public void testKeywordsCompletion() throws Exception {
        myFixture.configureByText(XQueryFileType.INSTANCE, "<caret>");
        myFixture.complete(CompletionType.BASIC, 1);
        List<String> strings = myFixture.getLookupElementStrings();
        assertAllKeywords(KEYWORDS, strings);
    }

    private void assertAllKeywords(TokenSet keywords, List<String> strings) {
        for (IElementType keywordTokenType : keywords.getTypes()) {
            String keywordString = keywordTokenType.toString();
            assertTrue(String.format("Keyword %s missing", keywordString), strings.contains(keywordString));
        }
    }
}

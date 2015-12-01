/*
 * Copyright 2013-2015 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

import org.intellij.xquery.BaseFunctionalTestCase;
import org.intellij.xquery.XQueryFileType;

public class FunctionDeclarationBracesBodyHandlerTest extends BaseFunctionalTestCase {

    public void testAddsSemicolonAfterCompletingOpeningBrace() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "declare function foo() <caret>");
        myFixture.type("{");
        myFixture.checkResult("declare function foo() {<caret>};");
    }

    public void testAddsSemicolonAfterCompletingClosingBrace() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "declare function foo() {<caret>");
        myFixture.type("}");
        myFixture.checkResult("declare function foo() {};<caret>");
    }

    public void testAddsSemicolonAfterCompletingClosingBraceWithSomeMethodBody() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "declare function foo() {(),'abc'<caret>");
        myFixture.type("}");
        myFixture.checkResult("declare function foo() {(),'abc'};<caret>");
    }

    public void testDoesNotAddSemicolonAfterCompletingClosingBraceWithinMethodBody() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "declare function fun($sdfsdf) {(), 'abc', element abc {'safsdf'<caret>");
        myFixture.type("}");
        myFixture.checkResult("declare function fun($sdfsdf) {(), 'abc', element abc {'safsdf'}<caret>");
    }

    public void testAddaSemicolonAfterCompletingClosingBraceWhenMethodBodyContainsBraces() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "declare function fun($sdfsdf) {(), 'abc', element abc {'safsdf'}<caret>");
        myFixture.type("}");
        myFixture.checkResult("declare function fun($sdfsdf) {(), 'abc', element abc {'safsdf'}};<caret>");
    }
}
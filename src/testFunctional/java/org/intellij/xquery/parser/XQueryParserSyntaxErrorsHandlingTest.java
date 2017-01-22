/*
 * Copyright 2013-2017 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.parser;

public class XQueryParserSyntaxErrorsHandlingTest extends BaseParserTest {

    public XQueryParserSyntaxErrorsHandlingTest() {
        super("errors");
    }

    public void testIncompleteQuotString() {
        doTest(true);
    }

    public void testIncompleteAposString() {
        doTest(true);
    }

    public void testIncompleteOrExpression() {
        doTest(true);
    }

    public void testIncompleteAndExpression() {
        doTest(true);
    }

    public void testIncompleteValueComparisonExpression() {
        doTest(true);
    }

    public void testIncompleteStringConcatExpression() {
        doTest(true);
    }

    public void testIncompleteRangeExpression() {
        doTest(true);
    }

    public void testIncompleteAdditiveExpression() {
        doTest(true);
    }

    public void testIncompleteMultiplicativeExpression() {
        doTest(true);
    }

    public void testIncompleteUnionExpression() {
        doTest(true);
    }

    public void testIncompleteIntersectExceptExpression() {
        doTest(true);
    }

    public void testIncompleteInstanceOfExpression() {
        doTest(true);
    }

    public void testIncompleteTreatAsExpression() {
        doTest(true);
    }

    public void testIncompleteCastableAsExpression() {
        doTest(true);
    }

    public void testIncompleteCastAsExpression() {
        doTest(true);
    }

    public void testIncompleteValidationExpression() {
        doTest(true);
    }

    public void testIncompleteExtensionExpression() {
        doTest(true);
    }

    public void testIncompleteSimpleMapExpression() {
        doTest(true);
    }

    public void testIncompleteRootPathOrChildExpression() {
        doTest(true);
    }

    public void testIncompleteRelativePathExpression() {
        doTest(true);
    }

    public void testIncompleteForwardStepExpression() {
        doTest(true);
    }

    public void testIncompleteReverseStepExpression() {
        doTest(true);
    }

    public void testIncompleteXmlOpeningTag() {
        doTest(true);
    }

    public void testIncompleteXmlClosingTag() {
        doTest(true);
    }

    public void testIncompleteXmlTag() {
        doTest(true);
    }

    public void testIncompleteVariableDeclaration() {
        doTest(true);
    }

    public void testIncompleteFunctionDeclaration() {
        doTest(true);
    }

    public void testIncompleteMainModuleWithErrorInProlog() {
        doTest(true);
    }

    public void testIncompleteFunctionName() {
        doTest(true);
    }

    public void testIncompleteVariableName() {
        doTest(true);
    }

    public void testIncompletePredicateInXmlAttribute() {
        doTest(true);
    }

    public void testStringWithIncompleteEntityRef() {
        doTest(true);
    }
}

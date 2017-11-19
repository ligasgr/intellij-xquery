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

public class XQueryParserTest extends BaseParserTest {

    public XQueryParserTest() {
        super("valid");
    }

    public void testTour() {
        doTest(true);
    }

    public void testDefaultNamespaces() {
        doTest(true);
    }

    public void testStringConcatention() {
        doTest(true);
    }

    public void testSaxonMapSyntax() {
        doTest(true);
    }

    public void testMapSyntax() {
        doTest(true);
    }

    public void testMarklogicMapSyntax() {
        doTest(true);
    }

    public void testPrivate() {
        doTest(true);
    }

    public void testVersionInstanceOf() {
        doTest(true);
    }

    public void testFunctionReferenceDirComment() {
        doTest(true);
    }

    public void testFlwor() {
        doTest(true);
    }

    public void testPureXml() {
        doTest(true);
    }

    public void testSatisfies() {
        doTest(true);
    }

    public void testKeywordsAsVarNames() {
        doTest(true);
    }

    public void testKeywordsAsTagNames() {
        doTest(true);
    }

    public void testKeywordsAsPathSteps() {
        doTest(true);
    }

    public void testKeywordsAsPrefixedFnNames() {
        doTest(true);
    }

    public void testIncompleteTestOfKeywordsAsFunctionNames() {
        doTest(true);
    }

    public void testNamespace() {
        doTest(true);
    }

    public void testTypeswitch() {
        doTest(true);
    }

    public void testPragma() throws Exception {
        doTest(true);
    }

    public void testCData() throws Exception {
        doTest(true);
    }

    public void testProcessingInstruction() throws Exception {
        doTest(true);
    }

    public void testFunctx() throws Exception {
        doTest(true);
    }

    public void testSimpleMap() throws Exception {
        doTest(true);
    }

    public void testTumblingAndSlidingWindows() throws Exception {
        doTest(true);
    }

    public void testMultipleBindingsInForLet() throws Exception {
        doTest(true);
    }

    public void testUpdatingFacility() {
        doTest(true);
    }

    public void testTryCatch() {
        doTest(true);
    }

    public void testNamespaceAxis() {
        doTest(true);
    }

    public void testMarklogicPrivate() {
        doTest(true);
    }

    public void testMarklogicBinary() {
        doTest(true);
    }

    public void testAxisWithNameTest() {
        doTest(true);
    }

    public void testAxisWithKindTest() {
        doTest(true);
    }

    public void testMarklogicObjectNode() {
        doTest(true);
    }

    public void testMarklogicNumberNode() {
        doTest(true);
    }

    public void testMarklogicBooleanNode() {
        doTest(true);
    }

    public void testMarklogicNullNode() {
        doTest(true);
    }

    public void testMarklogicArrayNode() {
        doTest(true);
    }

    public void testMarklogicNodeSelector() {
        doTest(true);
    }

    public void testMarklogicTextSelector() {
        doTest(true);
    }

    public void testMarklogicJSONNodes() {
        doTest(true);
    }

    public void testMarklogicObjectNodes() {
        doTest(true);
    }

    public void testArray() {
        doTest(true);
    }

    public void testArrow() {
        doTest(true);
    }

    public void testStringConstructor() {
        doTest(true);
    }

    public void testUnaryLookup() {
        doTest(true);
    }

    public void testArgumentPlaceholder() {
        doTest(true);
    }

    public void testUnaryLookupAsArgument() {
        doTest(true);
    }
}
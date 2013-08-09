/*
 * Copyright 2013 Grzegorz Ligas <ligasgr@gmail.com>
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

package org.intellij.xquery.usage;

import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;
import com.intellij.usageView.UsageInfo;
import org.intellij.xquery.psi.*;

import java.util.Collection;

import static org.intellij.xquery.Assertions.assertChildOf;

/**
 * User: ligasgr
 * Date: 04/07/13
 * Time: 13:46
 */
public class XQueryFindUsageProviderTest extends LightPlatformCodeInsightFixtureTestCase {

    private final XQueryFindUsageProvider provider = new XQueryFindUsageProvider();

    @Override
    protected String getTestDataPath() {
        return "src/test/testData/org/intellij/xquery/usage";
    }

    public void testFindFunctionUsages() {
        Collection<UsageInfo> foundUsages = myFixture.testFindUsages("Function.xq");

        assertEquals(1, foundUsages.size());
        UsageInfo usageInfo = foundUsages.iterator().next();
        assertChildOf(usageInfo.getElement(), XQueryQueryBody.class);
        assertChildOf(usageInfo.getReference().resolve(), XQueryFunctionDecl.class);
    }

    public void testFindTwoFunctionUsages() {
        Collection<UsageInfo> foundUsages = myFixture.testFindUsages("FunctionTwoUsages.xq");

        assertEquals(2, foundUsages.size());
        UsageInfo usageInfo = foundUsages.iterator().next();
        assertChildOf(usageInfo.getElement(), XQueryQueryBody.class);
        assertChildOf(usageInfo.getReference().resolve(), XQueryFunctionDecl.class);
        UsageInfo secondUsageInfo = foundUsages.iterator().next();
        assertChildOf(secondUsageInfo.getElement(), XQueryQueryBody.class);
        assertChildOf(secondUsageInfo.getReference().resolve(), XQueryFunctionDecl.class);
    }

    public void testFindFunctionUsagesInAnotherFile() {
        Collection<UsageInfo> foundUsages = myFixture.testFindUsages("FunctionFromAnotherFile.xq", "AnotherFile.xq");

        assertEquals(1, foundUsages.size());
        UsageInfo usageInfo = foundUsages.iterator().next();
        assertChildOf(usageInfo.getElement(), XQueryQueryBody.class);
        assertChildOf(usageInfo.getReference().resolve(), XQueryFunctionDecl.class);
        assertEquals("AnotherFile.xq", usageInfo.getReference().resolve().getContainingFile().getName());
    }

    public void testFindFunctionUsagesWhenAnotherFileDoesNotExist() {
        Collection<UsageInfo> foundUsages = myFixture.testFindUsages("FunctionFromAnotherFile.xq");

        assertEquals(0, foundUsages.size());
    }

    public void testFunctionUsagesDescription() {
        XQueryFunctionDecl functionDeclaration = XQueryElementFactory.createFunctionDeclaration(getProject(),
                "local", "example");

        String description = provider.getNodeText(functionDeclaration, true);

        assertEquals("declare function local:example($param) {()}", description);
    }

    public void testFindVariableUsages() {
        Collection<UsageInfo> foundUsages = myFixture.testFindUsages("Variable.xq");

        assertEquals(1, foundUsages.size());
        UsageInfo usageInfo = foundUsages.iterator().next();
        assertChildOf(usageInfo.getElement(), XQueryFunctionDecl.class);
        assertChildOf(usageInfo.getReference().resolve(), XQueryVarDecl.class);
    }

    public void testFindVariableTextUsages() {
        Collection<UsageInfo> foundUsages = myFixture.testFindUsages("VariableReferencedInText.xq", "Text.txt");

        assertEquals(1, foundUsages.size());
        UsageInfo usageInfo = foundUsages.iterator().next();
        assertEquals("Text.txt", usageInfo.getElement().getContainingFile().getName());
    }

    public void testVariableUsagesDescription() {
        XQueryVarDecl variableDeclaration = XQueryElementFactory.createVariableDeclaration(getProject(), "local",
                "example");

        String description = provider.getNodeText(variableDeclaration, true);

        assertEquals("declare variable $local:example := 'value'", description);
    }

    public void testFindNamespaceNameUsages() {
        Collection<UsageInfo> foundUsages = myFixture.testFindUsages("Namespace.xq");

        assertEquals(1, foundUsages.size());
        UsageInfo usageInfo = foundUsages.iterator().next();
        assertChildOf(usageInfo.getElement(), XQueryFunctionDecl.class);
        assertChildOf(usageInfo.getReference().resolve(), XQueryNamespaceDecl.class);
    }

    public void testNamespaceNameUsagesDescription() {
        XQueryNamespaceDecl namespaceDeclaration = XQueryElementFactory.createNamespaceDeclaration(getProject(),
                "example");

        String description = provider.getNodeText(namespaceDeclaration, true);

        assertEquals("declare namespace example = 'dummy';", description);
    }

    public void testNamespaceNameInModuleUsagesDescription() {
        XQueryNamespaceName namespaceName = XQueryElementFactory.createModuleDeclarationName(getProject(), "example");

        String description = provider.getNodeText(namespaceName.getParent(), true);

        assertEquals("module namespace example = 'dummy';", description);
    }

    public void testNamespaceNameInImportUsagesDescription() {
        XQueryModuleImportPath moduleImportPath = XQueryElementFactory.createImport(getProject(), "'path.xq'");

        String description = provider.getNodeText(moduleImportPath.getParent(), true);

        assertEquals("import module namespace dummy = 'path.xq';", description);
    }

    public void testFileUsagesDescription() {
        XQueryFile file = XQueryElementFactory.createFile(getProject(), "example contents");

        String description = provider.getNodeText(file, true);

        assertEquals("example contents", description);
    }
}

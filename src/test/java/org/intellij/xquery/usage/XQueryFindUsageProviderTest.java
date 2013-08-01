/*
 * Copyright 2013 Grzegorz Ligas
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

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;
import com.intellij.usageView.UsageInfo;
import org.intellij.xquery.psi.*;

import java.util.Collection;

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
        XQueryQueryBody query = PsiTreeUtil.getParentOfType(usageInfo.getElement(), XQueryQueryBody.class);
        assertNotNull(query);
        XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) usageInfo.getReference().resolve().getParent();
        XQueryExpr functionBody = functionDeclaration.getFunctionBody().getEnclosedExpr().getExpr();
        String referencedFunctionBodyText = functionBody.getText();
        assertEquals("$local:var", referencedFunctionBodyText);
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
        XQueryFunctionDecl functionDeclaration = PsiTreeUtil.getParentOfType(usageInfo.getElement(), XQueryFunctionDecl.class);
        assertNotNull(functionDeclaration);
        XQueryVarDecl variableDeclaration = (XQueryVarDecl) usageInfo.getReference().resolve().getParent();
        String referencedVarValue = variableDeclaration.getVarValue().getText();
        assertEquals("\"value\"", referencedVarValue);
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
        XQueryFunctionDecl functionDeclaration = PsiTreeUtil.getParentOfType(usageInfo.getElement(), XQueryFunctionDecl.class);
        assertNotNull(functionDeclaration);
        XQueryNamespaceDecl namespaceDeclaration = (XQueryNamespaceDecl) usageInfo.getReference().resolve().getParent();
        String referencedVarValue = namespaceDeclaration.getURILiteral().getText();
        assertEquals("\"zzz\"", referencedVarValue);
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
        XQueryFile file = XQueryElementFactory.createFile(getProject(), "text");

        String description = provider.getNodeText(file, true);

        assertEquals("text", description);
    }
}

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

import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import com.intellij.usageView.UsageInfo;
import org.intellij.xquery.psi.*;

import java.util.Collection;

/**
 * User: ligasgr
 * Date: 04/07/13
 * Time: 13:46
 */
public class XQueryFindUsageProviderTest extends LightCodeInsightFixtureTestCase {

    @Override
    protected String getTestDataPath() {
        return "testData/org/intellij/xquery/usage";
    }

    public void testFindFunctionUsages() {
        Collection<UsageInfo> foundUsages = myFixture.testFindUsages("Function.xq");

        UsageInfo usageInfo = foundUsages.iterator().next();
        assertEquals(1, foundUsages.size());
        XQueryQueryBody query = PsiTreeUtil.getParentOfType(usageInfo.getElement(), XQueryQueryBody.class);
        assertNotNull(query);
        XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) usageInfo.getReference().resolve().getParent();
        XQueryExpr functionBody = functionDeclaration.getFunctionBody().getEnclosedExpr().getExpr();
        String referencedFunctionBodyText = functionBody.getText();
        assertEquals("$local:var", referencedFunctionBodyText);
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
}

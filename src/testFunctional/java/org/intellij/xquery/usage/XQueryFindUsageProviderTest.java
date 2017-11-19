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

package org.intellij.xquery.usage;

import com.intellij.psi.PsiElement;
import com.intellij.usageView.UsageInfo;
import org.intellij.xquery.BaseFunctionalTestCase;
import org.intellij.xquery.psi.*;

import java.util.Collection;

import static org.intellij.xquery.Assertions.assertChildOf;

public class XQueryFindUsageProviderTest extends BaseFunctionalTestCase {

    private final XQueryFindUsageProvider provider = new XQueryFindUsageProvider();

    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/usage";
    }

    public void testFindFunctionUsages() {
        Collection<UsageInfo> foundUsages = myFixture.testFindUsages("Function.xq");

        assertEquals(1, foundUsages.size());
        UsageInfo usageInfo = foundUsages.iterator().next();
        assertChildOf(usageInfo.getElement(), XQueryQueryBody.class);
        assertChildOf(resolved(usageInfo), XQueryFunctionDecl.class);
    }
    
    public void testFindTwoFunctionUsages() {
        Collection<UsageInfo> foundUsages = myFixture.testFindUsages("FunctionTwoUsages.xq");

        assertEquals(2, foundUsages.size());
        UsageInfo usageInfo = foundUsages.iterator().next();
        assertChildOf(usageInfo.getElement(), XQueryQueryBody.class);
        assertChildOf(resolved(usageInfo), XQueryFunctionDecl.class);
        UsageInfo secondUsageInfo = foundUsages.iterator().next();
        assertChildOf(secondUsageInfo.getElement(), XQueryQueryBody.class);
        assertChildOf(resolved(secondUsageInfo), XQueryFunctionDecl.class);
    }

    public void testFindFunctionUsagesInAnotherFile() {
        Collection<UsageInfo> foundUsages = myFixture.testFindUsages("FunctionFromAnotherFile.xq", "AnotherFile.xq");

        assertEquals(1, foundUsages.size());
        UsageInfo usageInfo = foundUsages.iterator().next();
        assertChildOf(usageInfo.getElement(), XQueryQueryBody.class);
        assertChildOf(resolved(usageInfo), XQueryFunctionDecl.class);
        assertEquals("AnotherFile.xq", resolved(usageInfo).getContainingFile().getName());
    }

    public void testFindFunctionUsagesWhenAnotherFileDoesNotExist() {
        Collection<UsageInfo> foundUsages = myFixture.testFindUsages("FunctionFromAnotherFile.xq");

        assertEquals(0, foundUsages.size());
    }

    public void testFunctionUsagesDescription() {
        String description = provider.getNodeText(exampleFunctionDeclaration().getFunctionName(), true);

        assertEquals("example", description);
    }

    public void testFunctionUsagesDescriptiveName() {
        String description = provider.getDescriptiveName(exampleFunctionDeclaration().getFunctionName());

        assertEquals("example", description);
    }

    public void testFunctionUsageType() {
        String type = provider.getType(exampleFunctionDeclaration().getFunctionName());

        assertEquals("function", type);
    }

    public void testFindVariableUsages() {
        Collection<UsageInfo> foundUsages = myFixture.testFindUsages("Variable.xq");

        assertEquals(1, foundUsages.size());
        UsageInfo usageInfo = foundUsages.iterator().next();
        assertChildOf(usageInfo.getElement(), XQueryFunctionDecl.class);
        assertChildOf(resolved(usageInfo), XQueryVarDecl.class);
    }

    public void testFindVariableTextUsages() {
        Collection<UsageInfo> foundUsages = myFixture.testFindUsages("VariableReferencedInText.xq", "Text.txt");

        assertEquals(0, foundUsages.size());
    }

    public void testVariableUsagesDescription() {
        String description = provider.getNodeText(exampleVariableDeclaration().getVarName(), true);

        assertEquals("example", description);
    }

    public void testVariableUsagesDescriptiveName() {
        String description = provider.getNodeText(exampleVariableDeclaration().getVarName(), true);

        assertEquals("example", description);
    }

    public void testVariableUsageType() {
        String type = provider.getType(exampleVariableDeclaration().getVarName());

        assertEquals("variable", type);
    }

    public void testFindNamespacePrefixUsages() {
        Collection<UsageInfo> foundUsages = myFixture.testFindUsages("Namespace.xq");

        assertEquals(1, foundUsages.size());
        UsageInfo usageInfo = foundUsages.iterator().next();
        assertChildOf(usageInfo.getElement(), XQueryFunctionDecl.class);
        assertChildOf(resolved(usageInfo), XQueryNamespaceDecl.class);
    }

    public void testFindPredeclaredNamespacePrefixUsages() {
        Collection<UsageInfo> foundUsages = myFixture.testFindUsages("PredeclaredNamespacePrefixUsage.xq", "LibraryModuleUsingPredeclaredNamespace.xq");

        assertEquals(3, foundUsages.size());
        for (UsageInfo usageInfo : foundUsages) {
            assertChildOf(usageInfo.getElement(), XQueryFunctionCall.class);
            assertChildOf(resolved(usageInfo), XQueryFunctionCall.class);
        }
    }

    public void testNamespaceNameUsagesDescription() {
        String description = provider.getNodeText(exampleNamespaceDeclaration().getNamespacePrefix(), true);

        assertEquals("example", description);
    }

    public void testNamespaceNameUsagesDescriptiveName() {
        String description = provider.getDescriptiveName(exampleNamespaceDeclaration().getNamespacePrefix());

        assertEquals("example", description);
    }

    public void testNamespacePrefixUsageType() {
        String type = provider.getType(exampleNamespaceDeclaration().getNamespacePrefix());

        assertEquals("namespace prefix", type);
    }

    public void testPrefixUsageType() {
        String type = provider.getType(exampleFunctionDeclaration().getFunctionName().getPrefix());

        assertEquals("namespace prefix", type);
    }

    public void testNamespaceNameInModuleUsagesDescription() {
        String description = provider.getNodeText(exampleModuleDeclaration(), true);

        assertEquals("example", description);
    }

    public void testNamespaceNameInModuleUsagesDescriptiveName() {
        String description = provider.getDescriptiveName(exampleModuleDeclaration());

        assertEquals("example", description);
    }

    public void testNamespaceNameInModuleUsageType() {
        String type = provider.getType(exampleModuleDeclaration());

        assertEquals("namespace prefix", type);
    }

    public void testNamespaceNameInImportUsagesDescription() {
        String description = provider.getNodeText(exampleImport().getNamespacePrefix(), true);

        assertEquals("dummy", description);
    }

    public void testNamespaceNameInImportUsagesDescriptiveName() {
        String description = provider.getDescriptiveName(exampleImport().getNamespacePrefix());

        assertEquals("dummy", description);
    }

    public void testNamespaceNameInImportUsageType() {
        String type = provider.getType(exampleImport().getNamespacePrefix());

        assertEquals("namespace prefix", type);
    }

    public void testFileUsagesDescription() {
        XQueryFile file = XQueryElementFactory.createFile(getProject(), "example contents");

        String description = provider.getNodeText(file, true);

        assertEquals("dummy.xq", description);
    }

    private XQueryFunctionDecl exampleFunctionDeclaration() {
        return XQueryElementFactory.createFunctionDeclaration(getProject(),
                "local", "example");
    }

    private XQueryVarDecl exampleVariableDeclaration() {
        return XQueryElementFactory.createVariableDeclaration(getProject(), "local",
                "example");
    }

    private XQueryNamespaceDecl exampleNamespaceDeclaration() {
        return XQueryElementFactory.createNamespaceDeclaration(getProject(),
                "example");
    }

    private XQueryNamespacePrefix exampleModuleDeclaration() {
        return XQueryElementFactory.createModuleDeclarationName(getProject(), "example");
    }

    private XQueryModuleImport exampleImport() {
        return (XQueryModuleImport) XQueryElementFactory.createImportPath(getProject(),
                "'path.xq'").getParent().getParent();
    }

    private PsiElement resolved(UsageInfo usageInfo) {
        return usageInfo.getReference().resolve();
    }
}

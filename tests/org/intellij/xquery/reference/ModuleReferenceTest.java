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

package org.intellij.xquery.reference;

import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import org.intellij.xquery.psi.XQueryFile;

import java.util.List;

/**
 * User: ligasgr
 * Date: 02/07/13
 * Time: 21:00
 */
public class ModuleReferenceTest extends LightCodeInsightFixtureTestCase {
    @Override
    protected String getTestDataPath() {
        return "testData/org/intellij/xquery/reference";
    }


    public void testModuleCompletion() {
        myFixture.configureByFiles("ModuleCompletion.xq");
        myFixture.complete(CompletionType.BASIC, 1);
        List<String> strings = myFixture.getLookupElementStrings();
        assertEquals(0, strings.size());
    }


    public void testModuleReference() {
        myFixture.configureByFiles("ModuleReference.xq","Module_ReferencedModule.xq");
        PsiElement element = myFixture.getFile().findElementAt(myFixture.getCaretOffset()).getParent().getParent();
        PsiReference[] references = element.getReferences();
        PsiReference reference = references[0];
        PsiElement resolvedReference = reference.resolve();
        XQueryFile referencedModule = (XQueryFile) resolvedReference;
        assertEquals("Module_ReferencedModule.xq", referencedModule.getName());
    }
}

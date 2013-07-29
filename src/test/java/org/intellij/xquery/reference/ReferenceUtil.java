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

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.testFramework.fixtures.CodeInsightTestFixture;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;

public class ReferenceUtil {
    private ReferenceUtil() {
    }

    public static <T extends PsiElement> void assertChildOf(PsiElement element, Class<T> classOfParent) {
        T parent = PsiTreeUtil.getParentOfType(element, classOfParent, false);
        Assert.assertThat(parent, CoreMatchers.is(CoreMatchers.notNullValue()));
    }

    public static <T extends PsiElement> void assertNotChildOf(PsiElement element, Class<T> classOfParent) {
        T parent = PsiTreeUtil.getParentOfType(element, classOfParent, false);
        Assert.assertThat(parent, CoreMatchers.is(CoreMatchers.nullValue()));
    }

    public static <T extends PsiElement> PsiElement getTargetOfReferenceAtCaret(CodeInsightTestFixture myFixture, Class<T> classOfSourceOfReference) {
        PsiReference reference = getReferenceAtCaret(myFixture, classOfSourceOfReference);
        return reference.resolve();
    }

    private static <T extends PsiElement> PsiReference getReferenceAtCaret(CodeInsightTestFixture myFixture, Class<T> classOfSourceOfReference) {
        PsiElement element = myFixture.getFile().findElementAt(myFixture.getCaretOffset());
        T sourceOfReference = PsiTreeUtil.getParentOfType(element, classOfSourceOfReference);
        return sourceOfReference.getReference();
    }
}
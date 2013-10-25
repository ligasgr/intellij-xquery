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

package org.intellij.xquery.annotator.duplicateFunction;

import com.intellij.psi.PsiElement;
import org.intellij.xquery.annotator.duplicateFunction.Verifier;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class VerifierTest {

    private static final String FUNCTION_NAME_TEXT = "functionName";
    private static final int ARITY = 1;
    private static final int DIFFERENT_ARITY = 2;


    private Verifier verifier = new Verifier();

    @Mock private XQueryFunctionName functionName;
    @Mock private XQueryFile file;
    @Mock private XQueryFunctionDecl functionDeclaration;
    @Mock private XQueryFunctionDecl otherFunctionDeclaration;
    @Mock private XQueryFunctionName otherFunctionName;

    @Test
    public void shouldReturnFalseWhenFunctionParentIsNotXQueryFunctionDeclaration() {
        PsiElement notXQueryFunctionDeclaration = mock(PsiElement.class);
        given(functionName.getParent()).willReturn(notXQueryFunctionDeclaration);

        boolean result = verifier.hasDuplicateDeclaration(functionName, file);

        assertThat(result, is(false));
    }

    @Test
    public void shouldReturnFalseWhenFunctionIsTheOnlyOneInTheFile() {
        given(functionName.getParent()).willReturn(functionDeclaration);
        given(file.getFunctionDeclarations()).willReturn(newArrayList(functionDeclaration));

        boolean result = verifier.hasDuplicateDeclaration(functionName, file);

        assertThat(result, is(false));
    }

    @Test
    public void shouldReturnFalseWhenOtherFunctionHasTheSameTextButDifferentArity() {
        given(functionName.getParent()).willReturn(functionDeclaration);
        given(file.getFunctionDeclarations()).willReturn(newArrayList(functionDeclaration, otherFunctionDeclaration));
        given(otherFunctionDeclaration.getFunctionName()).willReturn(otherFunctionName);
        given(otherFunctionName.getText()).willReturn(FUNCTION_NAME_TEXT);
        given(functionName.getText()).willReturn(FUNCTION_NAME_TEXT);
        given(otherFunctionDeclaration.getArity()).willReturn(ARITY);
        given(functionDeclaration.getArity()).willReturn(DIFFERENT_ARITY);

        boolean result = verifier.hasDuplicateDeclaration(functionName, file);

        assertThat(result, is(false));
    }

    @Test
    public void shouldReturnTrueWhenOtherFunctionHasTheSameTextButDifferentArity() {
        given(functionName.getParent()).willReturn(functionDeclaration);
        given(file.getFunctionDeclarations()).willReturn(newArrayList(functionDeclaration, otherFunctionDeclaration));
        given(otherFunctionDeclaration.getFunctionName()).willReturn(otherFunctionName);
        given(otherFunctionName.getText()).willReturn(FUNCTION_NAME_TEXT);
        given(otherFunctionDeclaration.getArity()).willReturn(ARITY);
        given(functionName.getText()).willReturn(FUNCTION_NAME_TEXT);
        given(functionDeclaration.getArity()).willReturn(ARITY);

        boolean result = verifier.hasDuplicateDeclaration(functionName, file);

        assertThat(result, is(true));
    }
}

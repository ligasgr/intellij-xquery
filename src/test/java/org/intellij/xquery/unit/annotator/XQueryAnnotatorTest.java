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

package org.intellij.xquery.unit.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.intellij.xquery.annotator.XQueryAnnotator;
import org.intellij.xquery.annotator.duplicateFunction.ErrorAnnotationCreator;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class XQueryAnnotatorTest {

    @InjectMocks private XQueryAnnotator annotator;

    @Mock private ErrorAnnotationCreator duplicateFunctionErrorCreator;

    @Mock private AnnotationHolder holder;
    @Mock private XQueryFunctionName element;
    @Mock private XQueryFile file;

    @Test
    public void shouldInvokeDuplicateFunctionErrorCreator() {
        given(element.getContainingFile()).willReturn(file);

        annotator.annotate(element, holder);

        verify(duplicateFunctionErrorCreator).createDuplicateFunctionDeclarationError(holder, element, file);
    }

    @Test
    public void shouldDoNothingWhenPsiElementIsNotXQueryFunction() {
        PsiElement notXQueryFunction = mock(PsiElement.class);

        annotator.annotate(notXQueryFunction, holder);

        verifyZeroInteractions(duplicateFunctionErrorCreator);
    }
}

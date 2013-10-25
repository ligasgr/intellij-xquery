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

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.util.TextRange;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class ErrorAnnotationCreatorTest {

    private static final String DESCRIPTION = "description";

    @InjectMocks private ErrorAnnotationCreator creator;

    @Mock private Verifier verifier;
    @Mock private TextRangeCalculator textRangeCalculator;
    @Mock private DescriptionCreator descriptionCreator;

    @Mock private AnnotationHolder holder;
    @Mock private XQueryFunctionName functionName;
    @Mock private XQueryFile file;
    @Mock private XQueryFunctionDecl functionDeclaration;
    @Mock private TextRange textRange;

    @Test
    public void shouldCreateErrorAnnotation() {
        given(verifier.hasDuplicateDeclaration(functionName, file)).willReturn(true);
        given(functionName.getParent()).willReturn(functionDeclaration);
        given(textRangeCalculator.calculateTextRange(functionDeclaration)).willReturn(textRange);
        given(descriptionCreator.createDescription(functionName, file)).willReturn(DESCRIPTION);

        creator.createDuplicateFunctionDeclarationError(holder, functionName, file);

        verify(holder).createErrorAnnotation(textRange, DESCRIPTION);
    }

    @Test
    public void shouldDoNothingWhenNoDuplicateFound() {
        given(verifier.hasDuplicateDeclaration(functionName, file)).willReturn(false);

        creator.createDuplicateFunctionDeclarationError(holder, functionName, file);

        verifyZeroInteractions(holder, textRangeCalculator, descriptionCreator);
    }

}

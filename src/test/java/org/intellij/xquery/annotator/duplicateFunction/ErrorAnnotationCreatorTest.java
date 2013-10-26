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
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class ErrorAnnotationCreatorTest {

    @InjectMocks private ErrorAnnotationCreator creator;

    @Mock private Determiner determiner;
    @Mock private AnnotationHolderModifier annotationHolderModifier;

    @Mock private AnnotationHolder holder;
    @Mock private XQueryFunctionName functionName;
    @Mock private XQueryFile file;
    @Mock private XQueryFile importedFile;

    @Test
    public void shouldCreateErrorAnnotationWhenDuplicateFoundInTheSameFile() {
        given(determiner.hasDuplicateDeclarationInTheSameFile(functionName, file)).willReturn(true);

        creator.createDuplicateFunctionDeclarationError(holder, functionName, file);

        verify(annotationHolderModifier).modifyHolder(holder, functionName, file);
        verifyNoMoreInteractions(annotationHolderModifier);
    }

    @Test
    public void shouldCreateErrorAnnotationWhenDuplicateFoundInImportedFile() {
        given(determiner.getImportedFileWithDuplicatedDeclaration(functionName, file)).willReturn(importedFile);

        creator.createDuplicateFunctionDeclarationError(holder, functionName, file);

        verify(annotationHolderModifier).modifyHolder(holder, functionName, importedFile);
        verifyNoMoreInteractions(annotationHolderModifier);
    }

    @Test
    public void shouldCreateErrorAnnotationTwice() {
        given(determiner.hasDuplicateDeclarationInTheSameFile(functionName, file)).willReturn(true);
        given(determiner.getImportedFileWithDuplicatedDeclaration(functionName, file)).willReturn(importedFile);

        creator.createDuplicateFunctionDeclarationError(holder, functionName, file);

        verify(annotationHolderModifier).modifyHolder(holder, functionName, file);
        verify(annotationHolderModifier).modifyHolder(holder, functionName, importedFile);
    }

    @Test
    public void shouldDoNothingWhenNoDuplicateFound() {
        given(determiner.hasDuplicateDeclarationInTheSameFile(functionName, file)).willReturn(false);
        given(determiner.getImportedFileWithDuplicatedDeclaration(functionName, file)).willReturn(null);

        creator.createDuplicateFunctionDeclarationError(holder, functionName, file);

        verifyZeroInteractions(annotationHolderModifier);
    }

}

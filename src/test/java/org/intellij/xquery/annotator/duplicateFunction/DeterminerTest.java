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
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.intellij.xquery.psi.XQueryModuleDecl;
import org.intellij.xquery.psi.XQueryPrefix;
import org.intellij.xquery.psi.XQueryURILiteral;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.intellij.xquery.annotator.duplicateFunction.Determiner.NO_CONDITION;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class DeterminerTest {

    private static final String FUNCTION_NAME_TEXT = "functionName";
    private static final int ARITY = 1;
    private static final int DIFFERENT_ARITY = 2;
    private static final String FUNCTION_NAMESPACE = "functionNamespace";
    private static final String NAMESPACE_VALUE = "namespaceValue";
    private static final String DIFFERENT_NAMESPACE_VALUE = "differentNamespaceValue";

    private Determiner determiner = new Determiner();

    @Mock private XQueryFile file;
    @Mock private XQueryFile importedFile;
    @Mock private XQueryFunctionName functionName;
    @Mock private XQueryFunctionDecl functionDeclaration;
    @Mock private XQueryFunctionName otherFunctionName;
    @Mock private XQueryFunctionDecl otherFunctionDeclaration;
    @Mock private PsiElement notXQueryFunctionDeclaration;
    @Mock private XQueryModuleDecl moduleDeclaration;
    @Mock private XQueryURILiteral uriLiteral;
    @Mock private XQueryPrefix functionNamespaceObject;

    @Before
    public void setUp() {
        given(functionName.getParent()).willReturn(functionDeclaration);
        given(file.getFunctionDeclarations()).willReturn(newArrayList(functionDeclaration, otherFunctionDeclaration));
        given(otherFunctionDeclaration.getFunctionName()).willReturn(otherFunctionName);

        given(file.getImportedFilesThatExist(NO_CONDITION)).willReturn(newArrayList(importedFile));
        given(importedFile.getModuleDeclaration()).willReturn(moduleDeclaration);
        given(moduleDeclaration.getURILiteral()).willReturn(uriLiteral);
        given(functionName.getPrefix()).willReturn(functionNamespaceObject);
        given(functionNamespaceObject.getText()).willReturn(FUNCTION_NAMESPACE);
    }
    
    @Test
    public void shouldReturnFalseWhenFunctionParentIsNotXQueryFunctionDeclaration() {
        given(functionName.getParent()).willReturn(notXQueryFunctionDeclaration);

        boolean result = determiner.hasDuplicateDeclarationInTheSameFile(functionName, file);

        assertThat(result, is(false));
    }

    @Test
    public void shouldReturnFalseWhenFunctionIsTheOnlyOneInTheFile() {
        given(file.getFunctionDeclarations()).willReturn(newArrayList(functionDeclaration));

        boolean result = determiner.hasDuplicateDeclarationInTheSameFile(functionName, file);

        assertThat(result, is(false));
    }

    @Test
    public void shouldReturnFalseWhenOtherFunctionHasTheSameTextButDifferentArity() {
        given(functionName.getName()).willReturn(FUNCTION_NAME_TEXT);
        given(functionDeclaration.getArity()).willReturn(DIFFERENT_ARITY);
        given(otherFunctionName.getName()).willReturn(FUNCTION_NAME_TEXT);
        given(otherFunctionDeclaration.getArity()).willReturn(ARITY);

        boolean result = determiner.hasDuplicateDeclarationInTheSameFile(functionName, file);

        assertThat(result, is(false));
    }

    @Test
    public void shouldReturnTrueWhenOtherFunctionHasTheSameTextAndArity() {
        givenFunctionsAreTheSame();

        boolean result = determiner.hasDuplicateDeclarationInTheSameFile(functionName, file);

        assertThat(result, is(true));
    }

    @Test
    public void shouldReturnNullWhenFunctionParentIsNotXQueryFunctionDeclaration() {
        given(functionName.getParent()).willReturn(notXQueryFunctionDeclaration);

        XQueryFile result = determiner.getImportedFileWithDuplicatedDeclaration(functionName, file);

        assertThat(result, is(nullValue()));
    }
    
    @Test
    public void shouldReturnNullWhenThereAreNoImportedFiles() {
        given(file.getImportedFilesThatExist(NO_CONDITION)).willReturn(Collections.<XQueryFile>emptyList());

        XQueryFile result = determiner.getImportedFileWithDuplicatedDeclaration(functionName, file);

        assertThat(result, is(nullValue()));
    }

    @Test
    public void shouldReturnNullWhenThereAreNoDuplicatedNamespaces() {
        given(file.mapFunctionPrefixToNamespace(FUNCTION_NAMESPACE)).willReturn(NAMESPACE_VALUE);
        given(uriLiteral.getText()).willReturn(DIFFERENT_NAMESPACE_VALUE);

        XQueryFile result = determiner.getImportedFileWithDuplicatedDeclaration(functionName, file);

        assertThat(result, is(nullValue()));
    }

    @Test
    public void shouldReturnImportedFileWhenDeclarationIsDuplicated() {
        given(file.mapFunctionPrefixToNamespace(FUNCTION_NAMESPACE)).willReturn(NAMESPACE_VALUE);
        given(uriLiteral.getText()).willReturn(NAMESPACE_VALUE);
        given(importedFile.getFunctionDeclarations()).willReturn(newArrayList(functionDeclaration, otherFunctionDeclaration));
        givenFunctionsAreTheSame();

        XQueryFile result = determiner.getImportedFileWithDuplicatedDeclaration(functionName, file);

        assertThat(result, is(importedFile));
    }

    private void givenFunctionsAreTheSame() {
        given(functionName.getName()).willReturn(FUNCTION_NAME_TEXT);
        given(functionDeclaration.getArity()).willReturn(ARITY);
        given(otherFunctionName.getName()).willReturn(FUNCTION_NAME_TEXT);
        given(otherFunctionDeclaration.getArity()).willReturn(ARITY);
    }
}


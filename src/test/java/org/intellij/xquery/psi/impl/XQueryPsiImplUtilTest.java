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

package org.intellij.xquery.psi.impl;

import org.intellij.xquery.psi.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.intellij.xquery.util.StringUtils.EMPTY;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class XQueryPsiImplUtilTest {

    private static final String NAMESPACE = "namespace";
    private static final String NAMESPACE_WITH_QUOTES = "'" + NAMESPACE + "'";

    @Test
    public void shouldReturnNamespaceForModuleDeclaration() throws Exception {
        XQueryModuleDecl moduleDecl = mock(XQueryModuleDecl.class);
        XQueryURILiteral uriLiteral = mock(XQueryURILiteral.class);
        when(uriLiteral.getText()).thenReturn(NAMESPACE_WITH_QUOTES);
        when(moduleDecl.getURILiteral()).thenReturn(uriLiteral);

        String namespace = XQueryPsiImplUtil.getNamespace(moduleDecl);

        assertThat(namespace, is(NAMESPACE));
    }

    @Test
    public void shouldReturnEmptyNamespaceWhenNoUriForModuleDeclaration() throws Exception {
        XQueryModuleDecl moduleDecl = mock(XQueryModuleDecl.class);

        String namespace = XQueryPsiImplUtil.getNamespace(moduleDecl);

        assertThat(namespace, is(EMPTY));
    }

    @Test
    public void shouldReturnNamespaceForModuleImport() throws Exception {
        XQueryURILiteral uriLiteral = mock(XQueryURILiteral.class);
        when(uriLiteral.getText()).thenReturn(NAMESPACE_WITH_QUOTES);
        XQueryModuleImportPath moduleImportPath = mock(XQueryModuleImportPath.class);
        when(moduleImportPath.getURILiteral()).thenReturn(uriLiteral);
        XQueryModuleImportNamespace moduleImportNamespace = mock(XQueryModuleImportNamespace.class);
        when(moduleImportNamespace.getModuleImportPath()).thenReturn(moduleImportPath);
        XQueryModuleImport moduleImport = mock(XQueryModuleImport.class);
        when(moduleImport.getModuleImportNamespace()).thenReturn(moduleImportNamespace);

        String namespace = XQueryPsiImplUtil.getNamespace(moduleImport);

        assertThat(namespace, is(NAMESPACE));
    }

    @Test
    public void shouldReturnEmptyNamespaceWhenNoUriForModuleImport() throws Exception {
        XQueryModuleImport moduleImport = mock(XQueryModuleImport.class);

        String namespace = XQueryPsiImplUtil.getNamespace(moduleImport);

        assertThat(namespace, is(EMPTY));
    }

    @Test
    public void shouldReturnNamespaceForNamespaceDeclaration() throws Exception {
        XQueryNamespaceDecl namespaceDecl = mock(XQueryNamespaceDecl.class);
        XQueryURILiteral uriLiteral = mock(XQueryURILiteral.class);
        when(uriLiteral.getText()).thenReturn(NAMESPACE_WITH_QUOTES);
        when(namespaceDecl.getURILiteral()).thenReturn(uriLiteral);

        String namespace = XQueryPsiImplUtil.getNamespace(namespaceDecl);

        assertThat(namespace, is(NAMESPACE));
    }

    @Test
    public void shouldReturnEmptyNamespaceWhenNoUriForNamespaceDeclaration() throws Exception {
        XQueryNamespaceDecl namespaceDecl = mock(XQueryNamespaceDecl.class);

        String namespace = XQueryPsiImplUtil.getNamespace(namespaceDecl);

        assertThat(namespace, is(EMPTY));
    }

    @Test
    public void functionWithoutPrivateAnnotationShouldBeRecognizedAsPublic() throws Exception {
        XQueryFunctionDecl funcDecl = mock(XQueryFunctionDecl.class);
        final List<XQueryAnnotation> annotations = annotationListWithoutPrivate();
        when(funcDecl.getAnnotationList()).thenReturn(annotations);

        assertThat(XQueryPsiImplUtil.functionIsPublic(funcDecl), is(true));
    }

    @Test
    public void functionWithPrivateAnnotationShouldBeRecognizedAsNonPublic() throws Exception {
        XQueryFunctionDecl funcDecl = mock(XQueryFunctionDecl.class);
        final List<XQueryAnnotation> annotations = annotationListWithPrivate();
        when(funcDecl.getAnnotationList()).thenReturn(annotations);

        assertThat(XQueryPsiImplUtil.functionIsPublic(funcDecl), is(false));
    }

    @Test
    public void variableWithoutPrivateAnnotationShouldBeRecognizedAsPublic() throws Exception {
        XQueryVarDecl varDecl = mock(XQueryVarDecl.class);
        final List<XQueryAnnotation> annotations = annotationListWithoutPrivate();
        when(varDecl.getAnnotationList()).thenReturn(annotations);

        assertThat(XQueryPsiImplUtil.variableIsPublic(varDecl), is(true));
    }

    @Test
    public void variableWithPrivateAnnotationShouldBeRecognizedAsNonPublic() throws Exception {
        XQueryVarDecl varDecl = mock(XQueryVarDecl.class);
        final List<XQueryAnnotation> annotations = annotationListWithPrivate();
        when(varDecl.getAnnotationList()).thenReturn(annotations);

        assertThat(XQueryPsiImplUtil.variableIsPublic(varDecl), is(false));
    }

    private List<XQueryAnnotation> annotationListWithoutPrivate() {
        List<XQueryAnnotation> annotations = new ArrayList<XQueryAnnotation>();

        XQueryAnnotation otherAnnotation = mock(XQueryAnnotation.class);
        XQueryAnnotationName otherName = mock(XQueryAnnotationName.class);

        when(otherName.getText()).thenReturn("other");
        when(otherAnnotation.getAnnotationName()).thenReturn(otherName);
        annotations.add(otherAnnotation);

        return annotations;
    }

    private List<XQueryAnnotation> annotationListWithPrivate() {
        List<XQueryAnnotation> annotations = new ArrayList<XQueryAnnotation>();

        XQueryAnnotation otherAnnotation = mock(XQueryAnnotation.class);
        XQueryAnnotationName otherName = mock(XQueryAnnotationName.class);

        when(otherName.getText()).thenReturn("other");
        when(otherAnnotation.getAnnotationName()).thenReturn(otherName);
        annotations.add(otherAnnotation);

        XQueryAnnotation privateAnnotation = mock(XQueryAnnotation.class);
        XQueryAnnotationName privateName = mock(XQueryAnnotationName.class);

        when(privateName.getText()).thenReturn("private");
        when(privateAnnotation.getAnnotationName()).thenReturn(privateName);
        annotations.add(privateAnnotation);

        return annotations;
    }
}

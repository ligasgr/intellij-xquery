package org.intellij.xquery.psi.impl;

import org.intellij.xquery.psi.XQueryAnnotation;
import org.intellij.xquery.psi.XQueryAnnotationName;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryVarDecl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class XQueryPsiImplUtilTest {

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

        XQueryAnnotation privateAnnocation = mock(XQueryAnnotation.class);
        XQueryAnnotationName privateName = mock(XQueryAnnotationName.class);

        when(privateName.getText()).thenReturn("private");
        when(privateAnnocation.getAnnotationName()).thenReturn(privateName);
        annotations.add(privateAnnocation);


        return annotations;
    }

}

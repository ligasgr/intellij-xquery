package org.intellij.xquery.analysis;

import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.codeInsight.daemon.impl.HighlightVisitor;
import com.intellij.codeInsight.daemon.impl.analysis.HighlightInfoHolder;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.psi.PsiElement;
import org.intellij.xquery.XQueryFileType;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class XQueryHighlightVisitorTest {

    private static final boolean ANY_VALUE = true;

    @InjectMocks private XQueryHighlightVisitor visitor;

    @Mock private DuplicateFunctionDeclarationHighlightCreator duplicateFunctionDeclarationHighlightCreator;

    @Mock private XQueryFile psiFile;
    @Mock private HighlightInfoHolder holder;

    @Test
    public void shouldBeSuitableForXQueryFile() {
        given(psiFile.getFileType()).willReturn(XQueryFileType.INSTANCE);

        boolean result = visitor.suitableForFile(psiFile);

        assertThat(result, is(true));
    }

    @Test
    public void shouldNotBeSuitableForFileOtherThanXQuery() {
        given(psiFile.getFileType()).willReturn(JavaFileType.INSTANCE);

        boolean result = visitor.suitableForFile(psiFile);

        assertThat(result, is(false));
    }

    @Test
    public void shouldRunGivenActionAndReturnTrue() {
        HighlightInfoHolder holder = mock(HighlightInfoHolder.class);
        Runnable action = mock(Runnable.class);

        boolean result = visitor.analyze(psiFile, ANY_VALUE, holder, action);

        verify(action).run();
        assertThat(result, is(true));
    }

    @Test
    public void shouldAddHighlightInfoToHolder() {
        XQueryFunctionName xQueryFunction = mock(XQueryFunctionName.class);
        HighlightInfo highlightInfo = mock(HighlightInfo.class);
        given(duplicateFunctionDeclarationHighlightCreator.createDuplicateFunctionDeclarationHighlight(xQueryFunction, psiFile)).willReturn(highlightInfo);

        visitor.visit(xQueryFunction);

        verify(holder).add(highlightInfo);
    }

    @Test
    public void shouldDoNothingWhenVisitingElementIsNotXQueryFunctionName() {
        visitor.visit(mock(PsiElement.class));

        verifyZeroInteractions(duplicateFunctionDeclarationHighlightCreator, holder);
    }

    @Test
    public void shouldHaveOrderSetToZero() {
        assertThat(visitor.order(), is(0));
    }

    @Test
    public void shouldReturnNewObjectWhenCloneIsInvoked() {
        HighlightVisitor clone = visitor.clone();

        assertThat(clone, instanceOf(XQueryHighlightVisitor.class));
        assertThat((XQueryHighlightVisitor) clone, is(not(visitor)));
    }
}

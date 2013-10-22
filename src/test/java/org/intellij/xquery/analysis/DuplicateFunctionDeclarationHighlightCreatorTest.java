package org.intellij.xquery.analysis;

import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.util.TextRange;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class DuplicateFunctionDeclarationHighlightCreatorTest {

    private static final String DESCRIPTION = "description";

    @InjectMocks @Spy private DuplicateFunctionDeclarationHighlightCreator creator;

    @Mock private DuplicateFunctionDeclarationVerifier verifier;
    @Mock private DuplicateFunctionDeclarationTextRangeCalculator textRangeCalculator;
    @Mock private DuplicateFunctionDeclarationDescriptionCreator descriptionCreator;

    @Mock private XQueryFunctionName functionName;
    @Mock private XQueryFile file;
    @Mock private XQueryFunctionDecl functionDeclaration;
    @Mock private TextRange textRange;
    @Mock private HighlightInfo highlightInfo;

    @Test
    public void shouldCreateHighlightInfo() {
        given(verifier.functionHasDuplicateDeclarationInFile(functionName, file)).willReturn(true);
        given(functionName.getParent()).willReturn(functionDeclaration);
        given(textRangeCalculator.calculateTextRange(functionDeclaration)).willReturn(textRange);
        given(descriptionCreator.createDescription(functionName, file)).willReturn(DESCRIPTION);
        doReturn(highlightInfo).when(creator).getHighlightInfo(textRange, DESCRIPTION);

        HighlightInfo result = creator.createDuplicateFunctionDeclarationHighlight(functionName, file);

        assertThat(result, is(highlightInfo));
    }

    @Test
    public void shouldReturnNullWhenNoDuplicateFound() {
        given(verifier.functionHasDuplicateDeclarationInFile(functionName, file)).willReturn(false);

        HighlightInfo result = creator.createDuplicateFunctionDeclarationHighlight(functionName, file);

        assertThat(result, is(nullValue()));
    }

}

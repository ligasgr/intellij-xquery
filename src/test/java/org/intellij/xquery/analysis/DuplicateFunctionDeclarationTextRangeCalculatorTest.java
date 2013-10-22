package org.intellij.xquery.analysis;

import com.intellij.openapi.util.TextRange;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryParamList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class DuplicateFunctionDeclarationTextRangeCalculatorTest {

    private static final int FUNCTION_DECLARATION_START_OFFSET = 1;
    private static final int FUNCTION_DECLARATION_END_OFFSET = 2;
    private static final int ANY_VALUE = 3;
    private static final int FUNCTION_DECLARATION_PARAM_LIST_END_OFFSET = 4;

    @Mock private XQueryFunctionDecl functionDeclaration;
    @Mock private XQueryParamList functionDeclarationParamList;

    private TextRange functionDeclarationTextRange = new TextRange(FUNCTION_DECLARATION_START_OFFSET, FUNCTION_DECLARATION_END_OFFSET);
    private TextRange functionDeclarationParamListTextRange = new TextRange(ANY_VALUE, FUNCTION_DECLARATION_PARAM_LIST_END_OFFSET);

    private DuplicateFunctionDeclarationTextRangeCalculator textRangeCalculator = new DuplicateFunctionDeclarationTextRangeCalculator();

    @Test
    public void shouldCalculateTextRangeBasedOnTextRangeFromFunctionDeclaration() {
        given(functionDeclaration.getTextRange()).willReturn(functionDeclarationTextRange);

        TextRange result = textRangeCalculator.calculateTextRange(functionDeclaration);

        assertThat(result.getStartOffset(), is(FUNCTION_DECLARATION_START_OFFSET));
        assertThat(result.getEndOffset(), is(FUNCTION_DECLARATION_END_OFFSET));
    }

    @Test
    public void shouldCalculateTextRangeWithEndOffsetFromParametersListWhenTheyArePresent() {
        given(functionDeclaration.getTextRange()).willReturn(functionDeclarationTextRange);
        given(functionDeclaration.getParamList()).willReturn(functionDeclarationParamList);
        given(functionDeclarationParamList.getTextRange()).willReturn(functionDeclarationParamListTextRange);

        TextRange result = textRangeCalculator.calculateTextRange(functionDeclaration);

        assertThat(result.getStartOffset(), is(FUNCTION_DECLARATION_START_OFFSET));
        assertThat(result.getEndOffset(), is(FUNCTION_DECLARATION_PARAM_LIST_END_OFFSET));
    }
}

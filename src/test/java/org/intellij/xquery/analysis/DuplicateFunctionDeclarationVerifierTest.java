package org.intellij.xquery.analysis;

import com.intellij.psi.PsiElement;
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
public class DuplicateFunctionDeclarationVerifierTest {

    private static final String FUNCTION_NAME_TEXT = "functionName";
    private static final int ARITY = 1;
    private static final int DIFFERENT_ARITY = 2;


    private DuplicateFunctionDeclarationVerifier verifier = new DuplicateFunctionDeclarationVerifier();

    @Mock private XQueryFunctionName functionName;
    @Mock private XQueryFile file;
    @Mock private XQueryFunctionDecl functionDeclaration;
    @Mock private XQueryFunctionDecl otherFunctionDeclaration;
    @Mock private XQueryFunctionName otherFunctionName;

    @Test
    public void shouldReturnFalseWhenFunctionParentIsNotXQueryFunctionDeclaration() {
        PsiElement notXQueryFunctionDeclaration = mock(PsiElement.class);
        given(functionName.getParent()).willReturn(notXQueryFunctionDeclaration);

        boolean result = verifier.functionHasDuplicateDeclarationInFile(functionName, file);

        assertThat(result, is(false));
    }

    @Test
    public void shouldReturnFalseWhenFunctionIsTheOnlyOneInTheFile() {
        given(functionName.getParent()).willReturn(functionDeclaration);
        given(file.getFunctionDeclarations()).willReturn(newArrayList(functionDeclaration));

        boolean result = verifier.functionHasDuplicateDeclarationInFile(functionName, file);

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

        boolean result = verifier.functionHasDuplicateDeclarationInFile(functionName, file);

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

        boolean result = verifier.functionHasDuplicateDeclarationInFile(functionName, file);

        assertThat(result, is(true));
    }
}

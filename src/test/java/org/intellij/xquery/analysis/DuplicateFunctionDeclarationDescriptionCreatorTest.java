package org.intellij.xquery.analysis;

import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.intellij.xquery.psi.XQueryParam;
import org.intellij.xquery.psi.XQueryParamList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.intellij.xquery.analysis.DuplicateFunctionDeclarationDescriptionCreator.DUPLICATE_FUNCTION_TOOLTIP;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class DuplicateFunctionDeclarationDescriptionCreatorTest {

    private static final String FILE_NAME = "someFile.xq";
    private static final String FUNCTION_NAME_TEXT = "someFunctionName";

    private DuplicateFunctionDeclarationDescriptionCreator producer = new DuplicateFunctionDeclarationDescriptionCreator();

    @Mock private XQueryFunctionName functionName;
    @Mock private XQueryFile file;
    @Mock private XQueryFunctionDecl functionDeclaration;

    @Before
    public void setUp() {
        given(functionName.getParent()).willReturn(functionDeclaration);
    }

    @Test
    public void shouldProduceDescriptionBasedOnFunctionNameAndFile() {
        given(functionName.getName()).willReturn(FUNCTION_NAME_TEXT);
        given(file.getName()).willReturn(FILE_NAME);

        String result = producer.createDescription(functionName, file);

        assertThat(result, is(format(DUPLICATE_FUNCTION_TOOLTIP, FUNCTION_NAME_TEXT + "()", FILE_NAME)));
    }

    @Test
    public void shouldProduceDescriptionBasedOnFunctionArgumentsWhenTheyArePresent() {
        given(functionName.getName()).willReturn(FUNCTION_NAME_TEXT);
        given(file.getName()).willReturn(FILE_NAME);
        XQueryParamList paramListObject = mock(XQueryParamList.class);
        given(functionDeclaration.getParamList()).willReturn(paramListObject);
        ArrayList<XQueryParam> listWithThreeParams = newArrayList(mock(XQueryParam.class), mock(XQueryParam.class), mock(XQueryParam.class));
        given(paramListObject.getParamList()).willReturn(listWithThreeParams);

        String result = producer.createDescription(functionName, file);

        assertThat(result, is(format(DUPLICATE_FUNCTION_TOOLTIP, FUNCTION_NAME_TEXT + "($arg1, $arg2, $arg3)", FILE_NAME)));
    }
}

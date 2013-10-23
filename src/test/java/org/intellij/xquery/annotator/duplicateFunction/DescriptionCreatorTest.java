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
import java.util.Collections;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.intellij.xquery.annotator.duplicateFunction.DescriptionCreator.DUPLICATE_FUNCTION_TOOLTIP;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class DescriptionCreatorTest {

    private static final String FILE_NAME = "someFile.xq";
    private static final String FUNCTION_NAME_TEXT = "someFunctionName";

    private DescriptionCreator producer = new DescriptionCreator();

    @Mock private XQueryFunctionName functionName;
    @Mock private XQueryFile file;
    @Mock private XQueryFunctionDecl functionDeclaration;
    @Mock private XQueryParamList paramListObject;

    @Before
    public void setUp() {
        given(functionName.getParent()).willReturn(functionDeclaration);
        given(functionName.getName()).willReturn(FUNCTION_NAME_TEXT);
        given(file.getName()).willReturn(FILE_NAME);
        given(functionDeclaration.getParamList()).willReturn(paramListObject);
    }

    @Test
    public void shouldProduceDescriptionBasedOnFunctionNameAndFile() {
        given(paramListObject.getParamList()).willReturn(Collections.<XQueryParam>emptyList());

        String result = producer.createDescription(functionName, file);

        assertThat(result, is(format(DUPLICATE_FUNCTION_TOOLTIP, FUNCTION_NAME_TEXT, FILE_NAME)));
    }

    @Test
    public void shouldProduceDescriptionBasedOnFunctionArgumentsWhenTheyArePresent() {
        ArrayList<XQueryParam> listWithThreeParams = newArrayList(mock(XQueryParam.class), mock(XQueryParam.class), mock(XQueryParam.class));
        given(paramListObject.getParamList()).willReturn(listWithThreeParams);

        String result = producer.createDescription(functionName, file);

        assertThat(result, is(format(DUPLICATE_FUNCTION_TOOLTIP, FUNCTION_NAME_TEXT + "#3", FILE_NAME)));
    }
}

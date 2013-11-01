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

package org.intellij.xquery.unit.inspection.imports;

import org.intellij.xquery.inspection.imports.FunctionNamespacesExtractor;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionInvocation;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.intellij.xquery.psi.XQueryFunctionNamespace;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * User: ligasgr
 * Date: 31/10/13
 * Time: 20:49
 */
public class FunctionNamespacesExtractorTest {

    private static final String EMPTY = null;
    private static final String NAME = "name";
    private static final String NAMESPACE = "namespace";
    private XQueryFile file;
    private FunctionNamespacesExtractor extractor;

    @Before
    public void setUp() {
        file = mock(XQueryFile.class);
        extractor = new FunctionNamespacesExtractor();
    }

    @Test
    public void shouldReturnEmptySetWhenNoFunctionInvocationsInFile() {
        given(file.getFunctionInvocations()).willReturn(Collections.<XQueryFunctionInvocation>emptyList());

        Set<String> result = extractor.getNamespacesUsedByFunctions(file);

        assertThat(result.size(), is(0));
    }

    @Test
    public void shouldReturnNullWhenNamespaceReturnedByMappingFunctionIsNull() {
        XQueryFunctionInvocation functionInvocation = functionInvocationWithNamespace(NAME);
        given(file.getFunctionInvocations()).willReturn(asList(functionInvocation));
        given(file.mapPrefixToNamespace(NAME)).willReturn(null);

        Set<String> result = extractor.getNamespacesUsedByFunctions(file);

        assertThat(result.size(), is(1));
        assertThat(result.iterator().next(), is(nullValue()));
    }

    @Test
    public void shouldReturnNamespaceReturnedByMappingFunctionWhenFunctionInvocationWithoutNamespacePrefix() {
        XQueryFunctionInvocation functionInvocation = functionInvocationWithNamespace(EMPTY);
        given(file.getFunctionInvocations()).willReturn(asList(functionInvocation));
        given(file.mapPrefixToNamespace(null)).willReturn(NAMESPACE);

        Set<String> result = extractor.getNamespacesUsedByFunctions(file);

        assertThat(result.size(), is(1));
        assertThat(result.iterator().next(), is(NAMESPACE));
    }

    @Test
    public void shouldReturnNamespaceReturnedByMappingFunction() {
        XQueryFunctionInvocation functionInvocation = functionInvocationWithNamespace(NAME);
        given(file.getFunctionInvocations()).willReturn(asList(functionInvocation));
        given(file.mapPrefixToNamespace(NAME)).willReturn(NAMESPACE);

        Set<String> result = extractor.getNamespacesUsedByFunctions(file);

        assertThat(result.size(), is(1));
        assertThat(result.iterator().next(), is(NAMESPACE));
    }

    private XQueryFunctionInvocation functionInvocationWithNamespace(String name) {
        XQueryFunctionInvocation functionInvocation = mock(XQueryFunctionInvocation.class);
        XQueryFunctionName functionName = mock(XQueryFunctionName.class);
        if (name != null) {
            XQueryFunctionNamespace namespace = mock(XQueryFunctionNamespace.class);
            given(namespace.getText()).willReturn(name);
            given(functionName.getFunctionNamespace()).willReturn(namespace);
        }
        given(functionInvocation.getFunctionName()).willReturn(functionName);
        return functionInvocation;
    }
}

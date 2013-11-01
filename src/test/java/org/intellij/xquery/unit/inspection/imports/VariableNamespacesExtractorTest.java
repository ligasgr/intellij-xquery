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

import org.intellij.xquery.inspection.imports.VariableNamespacesExtractor;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryVarName;
import org.intellij.xquery.psi.XQueryVarNamespace;
import org.intellij.xquery.psi.XQueryVarRef;
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
 * Time: 20:22
 */
public class VariableNamespacesExtractorTest {

    private static final String EMPTY = null;
    private static final String NAME = "name";
    private static final String NAMESPACE = "namespace";
    private XQueryFile file;
    private VariableNamespacesExtractor extractor;

    @Before
    public void setUp() {
        file = mock(XQueryFile.class);
        extractor = new VariableNamespacesExtractor();
    }

    @Test
    public void shouldReturnEmptySetWhenNoVariablesInFile() {
        given(file.getVariableReferences()).willReturn(Collections.<XQueryVarRef>emptyList());

        Set<String> result = extractor.getNamespacesUsedByVariables(file);

        assertThat(result.size(), is(0));
    }

    @Test
    public void shouldReturnEmptySetWhenVariableWithoutNamespacePrefix() {
        XQueryVarRef varRef = varRefWithNamespace(EMPTY);
        given(file.getVariableReferences()).willReturn(asList(varRef));

        Set<String> result = extractor.getNamespacesUsedByVariables(file);

        assertThat(result.size(), is(0));
    }

    @Test
    public void shouldReturnNullWhenNamespaceReturnedByMappingFunctionIsNull() {
        XQueryVarRef varRef = varRefWithNamespace(NAME);
        given(file.getVariableReferences()).willReturn(asList(varRef));
        given(file.mapPrefixToNamespace(NAME)).willReturn(null);

        Set<String> result = extractor.getNamespacesUsedByVariables(file);

        assertThat(result.size(), is(1));
        assertThat(result.iterator().next(), is(nullValue()));
    }

    @Test
    public void shouldReturnNamespaceReturnedByMappingFunction() {
        XQueryVarRef varRef = varRefWithNamespace(NAME);
        given(file.getVariableReferences()).willReturn(asList(varRef));
        given(file.mapPrefixToNamespace(NAME)).willReturn(NAMESPACE);

        Set<String> result = extractor.getNamespacesUsedByVariables(file);

        assertThat(result.size(), is(1));
        assertThat(result.iterator().next(), is(NAMESPACE));
    }

    private XQueryVarRef varRefWithNamespace(String name) {
        XQueryVarRef varRef = mock(XQueryVarRef.class);
        XQueryVarName varName = mock(XQueryVarName.class);
        if (name != null) {
            XQueryVarNamespace namespace = mock(XQueryVarNamespace.class);
            given(namespace.getText()).willReturn(name);
            given(varName.getVarNamespace()).willReturn(namespace);
        }
        given(varRef.getVarName()).willReturn(varName);
        return varRef;
    }
}

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
import org.intellij.xquery.inspection.imports.UnusedImportsFinder;
import org.intellij.xquery.inspection.imports.VariableNamespacesExtractor;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryModuleImport;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * User: ligasgr
 * Date: 31/10/13
 * Time: 21:13
 */
public class UnusedImportsFinderTest {

    private static final String FUNCTION_USED_NAMESPACE = "function_used_namespace";
    private static final String VARIABLE_USED_NAMESPACE = "variable_used_namespace";
    private UnusedImportsFinder unusedImportsFinder;
    private XQueryFile file;
    private Collection<XQueryModuleImport> moduleImports;
    private FunctionNamespacesExtractor functionNamespacesExtractor;
    private VariableNamespacesExtractor variableNamespacesExtractor;
    private Set<String> functionInvocationNamespaces;
    private Set<String> variableReferenceNamespaces;

    @Before
    public void setUp() {
        functionNamespacesExtractor = mock(FunctionNamespacesExtractor.class);
        variableNamespacesExtractor = mock(VariableNamespacesExtractor.class);
        unusedImportsFinder = new UnusedImportsFinder(functionNamespacesExtractor, variableNamespacesExtractor);
        file = mock(XQueryFile.class);
        moduleImports = new ArrayList<XQueryModuleImport>();
        given(file.getModuleImports()).willReturn(moduleImports);
        functionInvocationNamespaces = new HashSet<String>();
        given(functionNamespacesExtractor.getNamespacesUsedByFunctions(file)).willReturn(functionInvocationNamespaces);
        variableReferenceNamespaces = new HashSet<String>();
        given(variableNamespacesExtractor.getNamespacesUsedByVariables(file)).willReturn(variableReferenceNamespaces);
    }

    @Test
    public void shouldReturnEmptyResultsWhenNoImportsAndNoUsages() {
        Collection<XQueryModuleImport> result = unusedImportsFinder.getUnusedImports(file);

        assertThat(result.size(), is(0));
    }

    @Test
    public void shouldReturnEmptyResultsWhenNoImportsAndUsageInFunction() {
        functionInvocationNamespaces.add(FUNCTION_USED_NAMESPACE);

        Collection<XQueryModuleImport> result = unusedImportsFinder.getUnusedImports(file);

        assertThat(result.size(), is(0));
    }

    @Test
    public void shouldReturnEmptyResultsWhenNoImportsAndUsageInVariable() {
        variableReferenceNamespaces.add(VARIABLE_USED_NAMESPACE);

        Collection<XQueryModuleImport> result = unusedImportsFinder.getUnusedImports(file);

        assertThat(result.size(), is(0));
    }

    @Test
    public void shouldReturnEmptyResultsWhenNoImportsAndUsageInVariableAndFunction() {
        functionInvocationNamespaces.add(FUNCTION_USED_NAMESPACE);
        variableReferenceNamespaces.add(VARIABLE_USED_NAMESPACE);

        Collection<XQueryModuleImport> result = unusedImportsFinder.getUnusedImports(file);

        assertThat(result.size(), is(0));
    }

    @Test
    public void shouldReturnUnusedImportWhenNotUsedInFunction() {
        functionInvocationNamespaces.add(FUNCTION_USED_NAMESPACE);
        XQueryModuleImport unused = moduleImportWithNamespace("unused");
        moduleImports.add(unused);

        Collection<XQueryModuleImport> result = unusedImportsFinder.getUnusedImports(file);

        assertThat(result.size(), is(1));
        assertThat(result.iterator().next(), is(unused));
    }

    @Test
    public void shouldReturnUnusedImportWhenNotUsedByVariable() {
        variableReferenceNamespaces.add(VARIABLE_USED_NAMESPACE);
        XQueryModuleImport unused = moduleImportWithNamespace("unused");
        moduleImports.add(unused);

        Collection<XQueryModuleImport> result = unusedImportsFinder.getUnusedImports(file);

        assertThat(result.size(), is(1));
        assertThat(result.iterator().next(), is(unused));
    }

    @Test
    public void shouldReturnUnusedImportWhenNotUsedInAnyOfFoundUsages() {
        functionInvocationNamespaces.add(FUNCTION_USED_NAMESPACE);
        variableReferenceNamespaces.add(VARIABLE_USED_NAMESPACE);
        XQueryModuleImport unused = moduleImportWithNamespace("unused");
        moduleImports.add(unused);

        Collection<XQueryModuleImport> result = unusedImportsFinder.getUnusedImports(file);

        assertThat(result.size(), is(1));
        assertThat(result.iterator().next(), is(unused));
    }

    @Test
    public void shouldReturnEmptyResultsWhenUsedByFunction() {
        functionInvocationNamespaces.add(FUNCTION_USED_NAMESPACE);
        XQueryModuleImport used = moduleImportWithNamespace(FUNCTION_USED_NAMESPACE);
        moduleImports.add(used);

        Collection<XQueryModuleImport> result = unusedImportsFinder.getUnusedImports(file);

        assertThat(result.size(), is(0));
    }

    @Test
    public void shouldReturnEmptyResultsWhenUsedByVariable() {
        variableReferenceNamespaces.add(VARIABLE_USED_NAMESPACE);
        XQueryModuleImport used = moduleImportWithNamespace(VARIABLE_USED_NAMESPACE);
        moduleImports.add(used);

        Collection<XQueryModuleImport> result = unusedImportsFinder.getUnusedImports(file);

        assertThat(result.size(), is(0));
    }

    private XQueryModuleImport moduleImportWithNamespace(String name) {
        XQueryModuleImport moduleImport = mock(XQueryModuleImport.class);
        given(moduleImport.getNamespace()).willReturn(name);
        return moduleImport;
    }
}

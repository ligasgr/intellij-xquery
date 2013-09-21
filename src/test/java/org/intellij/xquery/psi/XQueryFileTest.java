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

package org.intellij.xquery.psi;

import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;
import org.intellij.xquery.XQueryBaseTestCase;

import java.util.Collection;

import static org.intellij.xquery.reference.namespace.XQueryPredeclaredNamespace.FN;
import static org.intellij.xquery.reference.namespace.XQueryPredeclaredNamespace.getMappingFromPrefix;

/**
 * User: ligasgr
 * Date: 01/08/13
 * Time: 13:30
 */
public class XQueryFileTest extends XQueryBaseTestCase {

    public void testVariableDeclarations() {
        XQueryFile file = XQueryElementFactory.createFile(getProject(), "declare variable $xxx := 'yyy';");

        Collection<XQueryVarDecl> variables = file.getVariableDeclarations();

        assertNotNull(variables);
        assertEquals(1, variables.size());
        XQueryVarDecl declaration = variables.iterator().next();
        assertEquals("xxx", declaration.getVarName().getName());
        assertEquals("'yyy'", declaration.getVarValue().getText());
    }

    public void testModuleImports() {
        XQueryFile file = XQueryElementFactory.createFile(getProject(), "import module namespace dummy = 'file.xq'");

        Collection<XQueryModuleImport> imports = file.getModuleImports();

        assertNotNull(imports);
        assertEquals(1, imports.size());
        XQueryModuleImport moduleImport = imports.iterator().next();
        assertEquals("dummy", moduleImport.getNamespaceName().getName());
        assertEquals("'file.xq'", moduleImport.getModuleImportNamespace().getText());
    }

    public void testModuleNamespaceName() {
        XQueryFile file = XQueryElementFactory.createFile(getProject(), "module namespace dummy = \"my\";");

        XQueryNamespaceName name = file.getModuleNamespaceName();

        assertNotNull(name);
        assertEquals("dummy", name.getName());
    }

    public void testNamespaceDeclarations() {
        XQueryFile file = XQueryElementFactory.createFile(getProject(), "declare namespace dummy = \"my\";");

        Collection<XQueryNamespaceDecl> namespaceDeclarations = file.getNamespaceDeclarations();

        assertNotNull(namespaceDeclarations);
        assertEquals(1, namespaceDeclarations.size());
        XQueryNamespaceDecl declaration = namespaceDeclarations.iterator().next();
        assertEquals("dummy", declaration.getNamespaceName().getName());
    }

    public void testFunctionDeclarations() {
        XQueryFile file = XQueryElementFactory.createFile(getProject(), "declare function dummy() {()};");

        Collection<XQueryFunctionDecl> namespaceDeclarations = file.getFunctionDeclarations();

        assertNotNull(namespaceDeclarations);
        assertEquals(1, namespaceDeclarations.size());
        XQueryFunctionDecl declaration = namespaceDeclarations.iterator().next();
        assertEquals("dummy", declaration.getFunctionName().getName());
    }

    public void testDefaultFunctionNamespaceWhenNotDeclared() {
        XQueryFile file = XQueryElementFactory.createFile(getProject(), "()");

        String defaultFunctionNamespace = file.getDefaultFunctionNamespace();

        assertEquals(FN.getNamespace(), defaultFunctionNamespace);
    }

    public void testDefaultFunctionNamespaceWhenDeclared() {
        XQueryFile file = XQueryElementFactory.createFile(getProject(), "declare default function namespace 'xxx';()");

        String defaultFunctionNamespace = file.getDefaultFunctionNamespace();

        assertEquals("xxx", defaultFunctionNamespace);
    }

    public void testDefaultFunctionNamespaceWhenDeclaredEmpty() {
        XQueryFile file = XQueryElementFactory.createFile(getProject(), "declare default function namespace '';()");

        String defaultFunctionNamespace = file.getDefaultFunctionNamespace();

        assertEquals("", defaultFunctionNamespace);
    }

    public void testNamespaceMappingsForPredeclaredNamespaces() {
        XQueryFile file = XQueryElementFactory.createFile(getProject(), "()");

        for (String key : getMappingFromPrefix().keySet()) {
            assertEquals(getMappingFromPrefix().get(key), file.mapPrefixToNamespace(key));
        }
    }

    public void testNamespaceMappingsWhenDefaultFunctionNamespaceNotDeclared() {
        XQueryFile file = XQueryElementFactory.createFile(getProject(), "()");

        assertEquals(FN.getNamespace(), file.mapPrefixToNamespace(null));
    }

    public void testNamespaceMappingsWhenDefaultFunctionNamespaceDeclared() {
        XQueryFile file = XQueryElementFactory.createFile(getProject(), "declare default function namespace 'xxx';()");

        assertEquals("xxx", file.mapPrefixToNamespace(null));
    }

    public void testNamespaceMappingsWhenNamespaceDeclared() {
        XQueryFile file = XQueryElementFactory.createFile(getProject(), "declare namespace ex = 'xxx';");

        assertEquals("xxx", file.mapPrefixToNamespace("ex"));
    }

    public void testNamespaceMappingsWhenModuleDeclared() {
        XQueryFile file = XQueryElementFactory.createFile(getProject(), "module namespace ex = 'xxx';");

        assertEquals("xxx", file.mapPrefixToNamespace("ex"));
    }

    public void testNamespaceMappingsWhenModuleImportedWithName() {
        XQueryFile file = XQueryElementFactory.createFile(getProject(), "import module namespace ex = 'xxx';");

        assertEquals("xxx", file.mapPrefixToNamespace("ex"));
    }

    public void testNamespaceMappingsWhenModuleImportedWithoutName() {
        XQueryFile file = XQueryElementFactory.createFile(getProject(), "import module 'xxx';");

        assertNull(file.mapPrefixToNamespace("ex"));
    }

    public void testGetNamespaceDeclarationsMatchingDefaultFunctionNamespace() {
        XQueryFile file = XQueryElementFactory.createFile(getProject(), "declare default function namespace 'xxx';" +
                "declare namespace yyy = '';" +
                "declare namespace zzz = 'zzz';" +
                "declare namespace aaa = 'xxx';" +
                "declare namespace  = 'xxx';" +
                "()");

        Collection<XQueryNamespaceDecl> results = file.getNamespaceDeclarationsMatchingDefaultNamespace();

        assertEquals(1, results.size());
        assertEquals("aaa", results.iterator().next().getNamespaceName().getText());
    }
}

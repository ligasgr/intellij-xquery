/*
 * Copyright 2013 Grzegorz Ligas <ligasgr@gmail.com>
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

import com.intellij.testFramework.LightPlatformTestCase;
import com.intellij.testFramework.PlatformTestCase;

import java.util.Collection;

/**
 * User: ligasgr
 * Date: 01/08/13
 * Time: 13:30
 */
public class XQueryFileTest extends LightPlatformTestCase {
    public XQueryFileTest() {
        PlatformTestCase.initPlatformLangPrefix();
    }

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
        assertEquals("'file.xq'", moduleImport.getModuleImportPathList().iterator().next().getText());
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
}

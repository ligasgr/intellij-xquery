/*
 * Copyright 2013-2014 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
 * (see the CONTRIBUTORS file).
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

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.LocalTimeCounter;
import org.intellij.xquery.XQueryFileType;

/**
 * User: ligasgr
 * Date: 08/06/13
 * Time: 22:01
 */
public class XQueryElementFactory {

    public static XQueryFile createFile(Project project, String text) {
        String name = "dummy.xq";
        return (XQueryFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, XQueryFileType.INSTANCE, text);
    }

    public static XQueryFile createPhysicalFile(Project project, String text) {
        String name = "dummy.xq";
        return (XQueryFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, XQueryFileType.INSTANCE, text, LocalTimeCounter.currentTime(), true);
    }

    public static XQueryVarName createVariableReference(Project project, String localVariableName) {
        final XQueryFile file = createFile(project, "$" + localVariableName);
        return PsiTreeUtil.findChildOfType(file, XQueryVarName.class);
    }

    public static XQueryVarName createVariableReference(Project project, String namespaceName,
                                                        String localVariableName) {
        final XQueryFile file = createFile(project, "$" + namespaceName + ":" + localVariableName);
        return PsiTreeUtil.findChildOfType(file, XQueryVarName.class);
    }

    public static XQueryVarDecl createVariableDeclaration(Project project, String namespaceName, String variableName) {
        final XQueryFile file = createFile(project, "declare variable $" + namespaceName + ":" + variableName + " := " +
                "'value';");
        return PsiTreeUtil.findChildOfType(file, XQueryVarDecl.class);
    }

    public static XQueryNamespacePrefix createModuleDeclarationName(Project project, String name) {
        final XQueryFile file = createFile(project, "module namespace " + name + " = 'dummy';");
        return PsiTreeUtil.findChildOfType(file, XQueryNamespacePrefix.class);
    }

    public static XQueryModuleImportPath createImportPath(Project project, String wholePathWithAposOrQuote) {
        final XQueryFile file = createFile(project, "import module namespace dummy = " + wholePathWithAposOrQuote +
                ";");
        return PsiTreeUtil.findChildOfType(file, XQueryModuleImportPath.class);
    }

    public static XQueryNamespaceDecl createNamespaceDeclaration(Project project, String namespaceName) {
        final XQueryFile file = createFile(project, "declare namespace " + namespaceName + " = 'dummy';");
        return PsiTreeUtil.findChildOfType(file, XQueryNamespaceDecl.class);
    }

    public static XQueryFunctionName createFunctionReference(Project project, String namespaceName,
                                                             String functionName) {
        final XQueryFile file = createFile(project, namespaceName + ":" + functionName + "()");
        return PsiTreeUtil.findChildOfType(file, XQueryFunctionName.class);
    }

    public static XQueryFunctionDecl createFunctionDeclaration(Project project, String namespaceName,
                                                               String functionName) {
        final XQueryFile file = createFile(project, "declare function " + namespaceName + ":"
                + functionName + "" + "($param) {()};");
        return PsiTreeUtil.findChildOfType(file, XQueryFunctionDecl.class);
    }

    public static XQueryXmlEmptyTag createXmlTag(Project project, String namespaceName,
                                                 String tagName) {
        final XQueryFile file = createFile(project, "<" + namespaceName + ":" + tagName + "/>");
        return PsiTreeUtil.findChildOfType(file, XQueryXmlEmptyTag.class);
    }

    public static XQueryDirAttributeName createAttributeName(Project project, String namespaceName,
                                                 String attributeName) {
        final XQueryFile file = createFile(project, "<abc " + namespaceName + ":" + attributeName + "='def'/>");
        return PsiTreeUtil.findChildOfType(file, XQueryDirAttributeName.class);
    }
}

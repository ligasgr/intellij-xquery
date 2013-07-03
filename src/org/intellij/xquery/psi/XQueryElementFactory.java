/*
 * Copyright 2013 Grzegorz Ligas
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
import org.intellij.xquery.XQueryFileType;

/**
 * User: ligasgr
 * Date: 08/06/13
 * Time: 22:01
 */
public class XQueryElementFactory {
    public static XQueryVarName createVariableReference(Project project, String localVariableName) {
        final XQueryFile file = createFile(project, "$" + localVariableName);
        return PsiTreeUtil.findChildOfType(file, XQueryVarName.class);
    }

    public static XQueryVarName createVariableReference(Project project, String namespaceName, String localVariableName) {
        final XQueryFile file = createFile(project, "$" + namespaceName + ":" + localVariableName);
        return PsiTreeUtil.findChildOfType(file, XQueryVarName.class);
    }

    public static XQueryNamespaceName createModuleDeclarationName(Project project, String name) {
        final XQueryFile file = createFile(project, "module namespace " + name + " = 'dummy';");
        return PsiTreeUtil.findChildOfType(file, XQueryNamespaceName.class);
    }

    public static XQueryFile createFile(Project project, String text) {
        String name = "dummy.simple";
        return (XQueryFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, XQueryFileType.INSTANCE, text);
    }
}

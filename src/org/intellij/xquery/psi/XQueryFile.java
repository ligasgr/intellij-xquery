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


import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.xquery.XQueryFileType;
import org.intellij.xquery.XQueryLanguage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Collection;

/**
 * User: ligasgr
 * Date: 10/02/13
 * Time: 18:59
 */
public class XQueryFile extends PsiFileBase {
    public XQueryFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, XQueryLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return XQueryFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "XQuery File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }

    public Collection<XQueryVarDecl> getVariableDeclarations() {
        Collection<XQueryVarDecl> variableDeclarations = PsiTreeUtil.findChildrenOfType(this, XQueryVarDecl.class);
        return variableDeclarations;
    }

    public Collection<XQueryModuleImport> getModuleImports() {
        Collection<XQueryModuleImport> moduleImports = PsiTreeUtil.findChildrenOfType(this, XQueryModuleImport.class);
        return moduleImports;
    }

    public XQueryNamespaceName getModuleNamespaceName() {
        return PsiTreeUtil.findChildOfType(this, XQueryModuleDecl.class).getNamespaceName();
    }
}
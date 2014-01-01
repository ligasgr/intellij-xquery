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

package org.intellij.xquery.refactoring;

import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.refactoring.rename.inplace.VariableInplaceRenameHandler;
import com.intellij.refactoring.rename.inplace.VariableInplaceRenamer;
import org.intellij.xquery.psi.XQueryVarName;
import org.jetbrains.annotations.NotNull;

/**
 * User: ligasgr
 * Date: 09/07/13
 * Time: 14:44
 */
public class XQueryVariableInplaceRenameHandler extends VariableInplaceRenameHandler {
    @Override
    protected boolean isAvailable(PsiElement element, Editor editor, PsiFile file) {
        return editor.getSettings().isVariableInplaceRenameEnabled() && element instanceof XQueryVarName;
    }

    protected VariableInplaceRenamer createRenamer(@NotNull PsiElement elementToRename, Editor editor) {
        return new VariableInplaceRenamer((PsiNameIdentifierOwner) elementToRename, editor);
    }
}

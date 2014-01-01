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

package org.intellij.xquery.documentation;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.impl.light.LightElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.intellij.xquery.XQueryLanguage;
import org.intellij.xquery.psi.XQueryFile;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
* User: ligasgr
* Date: 30/12/13
* Time: 23:04
*/
class XQueryDocElement extends LightElement implements PsiNamedElement {

    private final PsiElement originalElement;
    private final String namespace;
    private final String name;

    public XQueryDocElement(PsiManager mgr, PsiElement element, String namespace, String name) {
        super(mgr, XQueryLanguage.INSTANCE);
        originalElement = element;
        this.namespace = namespace;
        this.name = name;
    }

    public String getNamespace() {
        return namespace;
    }

    public PsiElement setName(@NotNull @NonNls String name) throws IncorrectOperationException {
        throw new IncorrectOperationException("Unsupported");
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "XQueryDocElement";
    }

    public PsiElement copy() {
        return this;
    }

    @Override
    public boolean isValid() {
        return originalElement != null && originalElement.isValid();
    }

    @Nullable
    public PsiFile getContainingFile() {
        if (!isValid()) {
            return null;
        }

        PsiFile file = originalElement.getContainingFile();
        final PsiElement context = originalElement.getContext();
        if (file == null && context != null) {
            file = context.getContainingFile();
        }
        PsiFile f;
        if ((f = PsiTreeUtil.getContextOfType(file, PsiFile.class, true)) instanceof XQueryFile) {
            return f;
        }
        return file;
    }
}

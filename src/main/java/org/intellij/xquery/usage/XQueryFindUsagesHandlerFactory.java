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

package org.intellij.xquery.usage;

import com.intellij.find.findUsages.FindUsagesHandler;
import com.intellij.find.findUsages.FindUsagesHandlerFactory;
import com.intellij.lang.findUsages.LanguageFindUsages;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileSystemItem;
import org.intellij.xquery.XQueryLanguage;
import org.jetbrains.annotations.NotNull;

/**
 * User: ligasgr
 * Date: 08/07/13
 * Time: 01:15
 */
public class XQueryFindUsagesHandlerFactory extends FindUsagesHandlerFactory {
    @Override
    public boolean canFindUsages(@NotNull final PsiElement element) {
        if (element instanceof PsiFileSystemItem) {
            if (((PsiFileSystemItem) element).getVirtualFile() == null) return false;
        } else if (!element.getLanguage().is(XQueryLanguage.INSTANCE)) {
            return false;
        }
        return element.isValid();
    }

    @Override
    public FindUsagesHandler createFindUsagesHandler(@NotNull final PsiElement element,
                                                     final boolean forHighlightUsages) {
        if (canFindUsages(element)) {
            return new XQueryFindUsagesHandler(element);
        }
        return null;
    }
}

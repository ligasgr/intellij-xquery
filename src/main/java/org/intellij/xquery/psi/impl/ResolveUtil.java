/*
 * Copyright 2013-2015 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.psi.impl;

import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.xquery.psi.XQueryPsiElement;

public class ResolveUtil {
    public static <T extends XQueryPsiElement> boolean processChildren(PsiElement element, PsiScopeProcessor processor,
            ResolveState substitutor, PsiElement lastParent, PsiElement place, Class<T>... childClassesToSkip) {
        PsiElement run = lastParent == null ? element.getLastChild() : lastParent.getPrevSibling();
        while (run != null) {
            if (!isAnyOf(run, childClassesToSkip) &&PsiTreeUtil.findCommonParent(place, run) != run && !run.processDeclarations(processor, substitutor,
                    null, place)) {
                return false;
            }
            run = run.getPrevSibling();
        }

        return true;
    }

    private static <T extends XQueryPsiElement>  boolean isAnyOf(PsiElement run, Class<T>... classes) {
        for (Class<T> aClass : classes) {
            if (aClass.isAssignableFrom(run.getClass())) {
                return true;
            }
        }
        return false;
    }
}

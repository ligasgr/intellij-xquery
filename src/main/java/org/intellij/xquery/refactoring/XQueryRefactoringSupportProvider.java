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

package org.intellij.xquery.refactoring;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.intellij.xquery.psi.XQueryNamespaceName;
import org.intellij.xquery.psi.XQueryVarName;

/**
 * User: ligasgr
 * Date: 09/07/13
 * Time: 13:54
 */
public class XQueryRefactoringSupportProvider extends RefactoringSupportProvider {

    @Override
    public boolean isMemberInplaceRenameAvailable(PsiElement element, PsiElement context) {
        return element instanceof XQueryVarName || element instanceof XQueryFunctionName || element instanceof
                XQueryNamespaceName;
    }

    @Override
    public boolean isSafeDeleteAvailable(PsiElement element) {
        return element instanceof XQueryVarName || element instanceof XQueryFunctionName || element instanceof
                XQueryNamespaceName;
    }
}

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
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.intellij.xquery.psi.XQueryVarName;

/**
 * User: ligasgr
 * Date: 29/12/13
 * Time: 09:25
 */
public class DocumentationProviderFactory {

    public static PsiBasedDocumentationProvider<? extends PsiElement> getDocumentationProvider(PsiElement element) {
        if (! (element.getContainingFile() instanceof XQueryFile)) new NullDocumentationProvider();
        if (element instanceof XQueryVarName) {
            return new VariableDocumentationProvider();
        }
        if (element instanceof XQueryFunctionName) {
            return new FunctionDocumentationProvider();
        }
        if (element instanceof XQueryDocElement) {
            return new LookupItemBuiltInFunctionDocumentationProvider();
        }
        return new NullDocumentationProvider();
    }

    private static class NullDocumentationProvider implements PsiBasedDocumentationProvider<PsiElement> {
        @Override
        public String generateDoc(PsiElement element) {
            return null;
        }
    }
}

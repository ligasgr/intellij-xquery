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

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import org.intellij.xquery.completion.function.BuiltInFunctionSignature;
import org.jetbrains.annotations.Nullable;

import static org.intellij.xquery.documentation.DocumentationProviderFactory.getDocumentationProvider;

/**
 * User: ligasgr
 * Date: 13/12/13
 * Time: 13:44
 */
public class XQueryDocumentationProvider extends AbstractDocumentationProvider {

    @Override
    public PsiElement getDocumentationElementForLookupItem(PsiManager psiManager, Object object, PsiElement element) {
        if (object instanceof PsiElement) {
            return (PsiElement) object;
        }
        if (object instanceof BuiltInFunctionSignature) {
            final BuiltInFunctionSignature functionSignature = ((BuiltInFunctionSignature)object);
            return new XQueryDocElement(psiManager, element, functionSignature.getNamespace(), functionSignature.getName());
        }
        return super.getDocumentationElementForLookupItem(psiManager, object, element);
    }

    @Override
    public String generateDoc(PsiElement element, @Nullable PsiElement originalElement) {
        PsiBasedDocumentationProvider documentationProvider = getDocumentationProvider(element);
        return documentationProvider.generateDoc(element);
    }
}

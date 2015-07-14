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

package org.intellij.xquery.reference.namespace;

import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.BaseScopeProcessor;
import org.intellij.xquery.psi.XQueryAttrLocalName;
import org.intellij.xquery.psi.XQueryDirAttributeName;
import org.intellij.xquery.psi.XQueryPsiElement;
import org.jetbrains.annotations.NotNull;

import static org.intellij.xquery.reference.namespace.XQuery30PredeclaredNamespaces.XMLNS;

public class XmlTagNamespaceReferenceScopeProcessor<T extends XQueryPsiElement> extends BaseScopeProcessor {
    private XQueryAttrLocalName result;
    private T myElement;

    public XmlTagNamespaceReferenceScopeProcessor(T myElement) {
        this.myElement = myElement;
    }

    public XQueryAttrLocalName getResult() {
        return result;
    }

    @Override
    public boolean execute(@NotNull PsiElement element, ResolveState state) {
        if (element instanceof XQueryDirAttributeName) {
            XQueryDirAttributeName attributeName = (XQueryDirAttributeName) element;
            if (isInlineNamespaceDeclaration(attributeName, myElement.getText())) {
                result = attributeName.getAttrLocalName();
                return false;
            }
        }
        return true;
    }

    private boolean isInlineNamespaceDeclaration(XQueryDirAttributeName attributeName, String namespaceName) {
        return attributeName.getAttrNamespace() != null
                && XMLNS.getPrefix().equals(attributeName.getAttrNamespace().getText())
                && namespaceName.equals(attributeName.getAttrLocalName().getText());
    }
}

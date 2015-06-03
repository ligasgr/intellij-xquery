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

package org.intellij.xquery.reference.xml;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.util.IncorrectOperationException;
import org.intellij.xquery.psi.XQueryElementFactory;
import org.intellij.xquery.psi.XQueryXmlEmptyTag;
import org.intellij.xquery.psi.XQueryXmlFullTag;
import org.intellij.xquery.psi.XQueryXmlTagName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class XQueryXmlTagNameReference extends PsiReferenceBase<XQueryXmlTagName> {
    public XQueryXmlTagNameReference(XQueryXmlTagName element, TextRange textRange) {
        super(element, textRange);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        PsiElement parent = myElement.getParent();
        if (parent instanceof XQueryXmlEmptyTag) {
            return myElement;
        }
        if (parent instanceof XQueryXmlFullTag) {
            return ((XQueryXmlFullTag) parent).getXmlTagNameList().get(0);
        }
        return null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return new Object[0];
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        XQueryXmlEmptyTag tag = XQueryElementFactory.createXmlTag(myElement.getProject(), "any", newElementName);
        myElement.getXmlTagLocalName().replace(tag.getXmlTagName().getXmlTagLocalName());
        return myElement;
    }
}

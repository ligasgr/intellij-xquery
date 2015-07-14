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

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.ResolveState;
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.xquery.psi.XQueryAttrLocalName;
import org.intellij.xquery.psi.XQueryDirAttribute;
import org.intellij.xquery.psi.XQueryDirAttributeList;
import org.intellij.xquery.psi.XQueryDirAttributeName;
import org.intellij.xquery.psi.XQueryPsiElement;
import org.intellij.xquery.psi.XQueryXmlEmptyTag;
import org.intellij.xquery.psi.XQueryXmlFullTag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.intellij.xquery.reference.namespace.XQuery30PredeclaredNamespaces.XMLNS;

public class XQueryXmlNamespaceReference<T extends XQueryPsiElement> extends XQueryPrefixReference<T> {
    public XQueryXmlNamespaceReference(T element, TextRange textRange) {
        super(element, textRange);
    }

    @Override
    protected Collection<ResolveResult> getPrimaryReferences() {
        XQueryAttrLocalName matchingNamespaceDeclaration = getMatchingInlineNamespaceDeclaration();
        if (matchingNamespaceDeclaration == null) {
            matchingNamespaceDeclaration = getReferenceFromXmlUpInTheHierarchy();
        }
        if (matchingNamespaceDeclaration != null) {
            return convertToResolveResults(matchingNamespaceDeclaration);
        } else {
            return Collections.emptyList();
        }
    }

    private XQueryAttrLocalName getMatchingInlineNamespaceDeclaration() {
        List<XQueryDirAttributeName> attributeNameList = getAttributeNames();
        XQueryAttrLocalName matchingAttribute = null;
        for (XQueryDirAttributeName attributeName : attributeNameList) {
            if (isInlineNamespaceDeclaration(attributeName, myElement.getText())) {
                matchingAttribute = attributeName.getAttrLocalName();
                break;
            }
        }
        return matchingAttribute;
    }

    private List<XQueryDirAttributeName> getAttributeNames() {
        XQueryDirAttributeList attributeList = getAttributeList();
        List<XQueryDirAttributeName> names = new ArrayList<XQueryDirAttributeName>();
        for (XQueryDirAttribute dirAttribute : attributeList.getDirAttributeList()) {
            names.add(dirAttribute.getDirAttributeName());
        }
        return names;
    }

    private XQueryDirAttributeList getAttributeList() {
        PsiElement grandParent = myElement.getParent().getParent();
        XQueryDirAttributeList attributeList;
        if (grandParent instanceof XQueryXmlFullTag) {
            attributeList = ((XQueryXmlFullTag) grandParent).getDirAttributeList();
        } else if (grandParent instanceof XQueryXmlEmptyTag) {
            attributeList = ((XQueryXmlEmptyTag) grandParent).getDirAttributeList();
        } else {
            attributeList = (XQueryDirAttributeList) grandParent.getParent();
        }
        return attributeList;
    }

    private boolean isInlineNamespaceDeclaration(XQueryDirAttributeName attributeName, String namespaceName) {
        return attributeName.getAttrNamespace() != null
                && XMLNS.getPrefix().equals(attributeName.getAttrNamespace().getText())
                && namespaceName.equals(attributeName.getAttrLocalName().getText());
    }

    private Collection<ResolveResult> convertToResolveResults(XQueryAttrLocalName localName) {
        Collection<ResolveResult> result = new ArrayList<ResolveResult>();
        result.add(new PsiElementResolveResult(localName));
        return result;
    }

    private XQueryAttrLocalName getReferenceFromXmlUpInTheHierarchy() {
        XmlTagNamespaceReferenceScopeProcessor processor = new XmlTagNamespaceReferenceScopeProcessor<T>(myElement);
        PsiTreeUtil.treeWalkUp(processor, myElement, null, ResolveState.initial());
        if (processor.getResult() != null) {
            return processor.getResult();
        } else {
            return null;
        }
    }

    @Override
    protected boolean includeOnlyPrimaryReferencesIfPresent() {
        return true;
    }
}

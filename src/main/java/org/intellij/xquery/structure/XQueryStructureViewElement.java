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

package org.intellij.xquery.structure;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiElement;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryVarDecl;
import org.intellij.xquery.psi.impl.XQueryPsiImplUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * User: ligasgr
 * Date: 21/08/13
 * Time: 14:41
 */
public class XQueryStructureViewElement implements StructureViewTreeElement, SortableTreeElement {

    private PsiElement element;

    public XQueryStructureViewElement(PsiElement element) {
        this.element = element;
    }

    @Override
    public Object getValue() {
        return element;
    }

    @Override
    public void navigate(boolean requestFocus) {
        if (element instanceof NavigationItem) {
            ((NavigationItem) element).navigate(requestFocus);
        }
    }

    @Override
    public boolean canNavigate() {
        return element instanceof NavigationItem && ((NavigationItem) element).canNavigate();
    }

    @Override
    public boolean canNavigateToSource() {
        return element instanceof NavigationItem && ((NavigationItem) element).canNavigateToSource();
    }

    @Override
    public String getAlphaSortKey() {
        if (element instanceof XQueryVarDecl)
            return ((XQueryVarDecl) element).getVarName().getText();
        if (element instanceof XQueryFunctionDecl)
            return ((XQueryFunctionDecl) element).getFunctionName().getText();
        return null;
    }

    @Override
    public ItemPresentation getPresentation() {
        return element instanceof NavigationItem ? ((NavigationItem) element).getPresentation() : null;
    }

    @Override
    public TreeElement[] getChildren() {
        if (element instanceof XQueryFile) {
            XQueryFile file = (XQueryFile) element;
            List<TreeElement> treeElements = new ArrayList<TreeElement>();
            for (XQueryVarDecl variableDeclaration : file.getVariableDeclarations()) {
                treeElements.add(new XQueryStructureViewElement(variableDeclaration));
            }
            for (XQueryFunctionDecl functionDeclaration : file.getFunctionDeclarations()) {
                treeElements.add(new XQueryStructureViewElement(functionDeclaration));
            }
            if (file.getQueryBody() != null) {
                treeElements.add(new XQueryStructureViewElement(file.getQueryBody()));
            }
            return treeElements.toArray(new TreeElement[treeElements.size()]);
        } else {
            return EMPTY_ARRAY;
        }
    }

    public boolean isPublic() {
        if (element instanceof XQueryVarDecl)
            return XQueryPsiImplUtil.isPublic((XQueryVarDecl) element);
        if (element instanceof XQueryFunctionDecl)
            return XQueryPsiImplUtil.isPublic((XQueryFunctionDecl) element);
        return true;
    }
}

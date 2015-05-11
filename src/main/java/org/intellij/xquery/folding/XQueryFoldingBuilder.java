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

package org.intellij.xquery.folding;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.intellij.xquery.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class XQueryFoldingBuilder extends FoldingBuilderEx implements DumbAware {

    @NotNull
    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
        if (!(root instanceof XQueryFile)) return FoldingDescriptor.EMPTY;
        XQueryFile file = (XQueryFile) root;
        List<FoldingDescriptor> descriptorList = new ArrayList<FoldingDescriptor>();


        updateImportFoldingDescriptors(descriptorList, new ArrayList<XQueryPsiElement>(file.getModuleImports()));
        updateImportFoldingDescriptors(descriptorList, new ArrayList<XQueryPsiElement>(file.getNamespaceDeclarations()));

        for (XQueryFunctionDecl function : file.getFunctionDeclarations()) {
            final XQueryFunctionBody functionBody = function.getFunctionBody();
            if (functionBody != null && functionBody.getTextLength() > 2) {
                descriptorList.add(new FoldingDescriptor(function, functionBody.getTextRange()));
            }
        }

        return descriptorList.toArray(new FoldingDescriptor[descriptorList.size()]);
    }

    private void updateImportFoldingDescriptors(List<FoldingDescriptor> descriptorList, Collection<XQueryPsiElement> elementsToFold) {
        if (elementsToFold.size() < 2) {
            return;
        }

        FoldingDescriptor folding = null;
        int numberOfAdjacentElement = 0;
        for (Iterator<XQueryPsiElement> i = elementsToFold.iterator(); i.hasNext(); ) {
            final XQueryPsiElement element = i.next();
            final TextRange elementTextRange = element.getTextRange();
            if (folding == null) {
                 folding = buildNewFoldingDescriptor(element, elementTextRange);
                 numberOfAdjacentElement = 1;
                 continue;
             }

            final boolean isAdjacent = folding.getRange().getEndOffset() + 1 == elementTextRange.getStartOffset();
            if (isAdjacent) {
                final TextRange updatedRange = new TextRange(folding.getRange().getStartOffset(), elementTextRange.getEndOffset());
                folding = new FoldingDescriptor(folding.getElement(), updatedRange);
                numberOfAdjacentElement++;
            }

            if (isAdjacent && i.hasNext()) {
                continue;
            }

            if (numberOfAdjacentElement > 1) {
                descriptorList.add(folding);
                folding = null;
            }

            if (!isAdjacent) {
                folding = buildNewFoldingDescriptor(element, elementTextRange);
                numberOfAdjacentElement = 1;
            }
        }
    }

    private FoldingDescriptor buildNewFoldingDescriptor(XQueryPsiElement element, TextRange elementTextRange) {
        FoldingDescriptor folding;
        folding = new FoldingDescriptor(element, elementTextRange);

        if (element instanceof XQueryModuleImport) {
            final TextRange updatedRange = new TextRange(folding.getRange().getStartOffset() + "import ".length(), folding.getRange().getEndOffset());
            folding = new FoldingDescriptor(folding.getElement(), updatedRange);
        }
        if (element instanceof XQueryNamespaceDecl) {
            final TextRange updatedRange = new TextRange(folding.getRange().getStartOffset() + "declare namespace ".length(), folding.getRange().getEndOffset());
            folding = new FoldingDescriptor(folding.getElement(), updatedRange);
        }
        return folding;
    }

    @Nullable
    @Override
    public String getPlaceholderText(@NotNull ASTNode astNode) {
        final PsiElement psiElement = astNode.getPsi();
        if (psiElement instanceof XQueryFunctionDecl) {
            return "{...}";
        } else if (psiElement instanceof XQueryModuleImport) {
            return "...";
        } else if (psiElement instanceof XQueryNamespaceDecl) {
            return "...";
        } else {
            return "....";
        }
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode astNode) {
        if (astNode.getElementType().equals(XQueryTypes.MODULE_IMPORT)) {
            return true;
        } else if (astNode.getElementType().equals(XQueryTypes.NAMESPACE_DECL)) {
            return true;
        }
        return false;
    }
}

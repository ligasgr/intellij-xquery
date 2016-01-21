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

package org.intellij.xquery.livetemplate;

import com.intellij.codeInsight.template.EverywhereContextType;
import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilBase;
import org.intellij.xquery.XQueryLanguage;
import org.intellij.xquery.psi.XQueryExprSingle;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryModuleDecl;
import org.intellij.xquery.psi.XQueryQueryBody;
import org.intellij.xquery.psi.XQueryTypes;
import org.intellij.xquery.psi.impl.XQueryPsiImplUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class XQueryContextType extends TemplateContextType {

    protected XQueryContextType(@NotNull @NonNls String id, @NotNull String presentableName,
                                @Nullable Class<? extends TemplateContextType> baseContextType) {
        super(id, presentableName, baseContextType);
    }

    @Override
    public boolean isInContext(@NotNull PsiFile file, int offset) {
        if (!PsiUtilBase.getLanguageAtOffset(file, offset).isKindOf(XQueryLanguage.INSTANCE)) return false;
        PsiElement elementAt = file.findElementAt(offset);
        PsiElement element = XQueryPsiImplUtil.getNextNonWhiteSpaceElement(elementAt);
        return element != null && isInContext(element);
    }

    protected abstract boolean isInContext(PsiElement element);

    protected boolean isNotBeforeModuleDeclaration(PsiElement topmostElement) {
        PsiElement[] moduleKeywords = XQueryPsiImplUtil.findChildrenOfType(topmostElement, XQueryTypes.K_MODULE);
        PsiElement nextModuleDeclaration = PsiTreeUtil.getNextSiblingOfType(topmostElement, XQueryModuleDecl.class);
        return moduleKeywords.length == 0&& nextModuleDeclaration == null;
    }

    public static class Generic extends XQueryContextType {

        protected Generic() {
            super("XQUERY_CODE", "XQuery", EverywhereContextType.class);
        }

        @Override
        protected boolean isInContext(PsiElement element) {
            return true;
        }
    }

    public static class Prolog extends XQueryContextType {
        protected Prolog() {
            super("XQUERY_PROLOG", "Prolog", Generic.class);
        }

        @Override
        protected boolean isInContext(PsiElement element) {
            PsiElement topmostElement = XQueryPsiImplUtil.getTopmostElementWithTheSameOffset(element);
            PsiElement topmostNonErrorElement = topmostElement instanceof PsiErrorElement
                    ? topmostElement.getParent()
                    : topmostElement;
            boolean isOnTheTopLevelOfTheStructure = topmostNonErrorElement.getParent() instanceof XQueryFile;
            return isOnTheTopLevelOfTheStructure && isBeforeQueryBody(topmostElement) && isNotBeforeModuleDeclaration(topmostElement);
        }

        private boolean isBeforeQueryBody(PsiElement topmostElement) {
            PsiElement previousQueryBody = PsiTreeUtil.getPrevSiblingOfType(topmostElement, XQueryQueryBody.class);
            return previousQueryBody == null && (!(topmostElement instanceof PsiErrorElement) || !topmostElement.equals(getLastChild(topmostElement)));
        }

        private PsiElement getLastChild(PsiElement topmostElement) {
            XQueryQueryBody queryBody = PsiTreeUtil.getParentOfType(topmostElement, XQueryQueryBody.class);
            return queryBody != null ? queryBody.getLastChild() : null;
        }
    }

    public static class Expression extends XQueryContextType {
        protected Expression() {
            super("XQUERY_EXPR", "Expression", Generic.class);
        }

        @Override
        protected boolean isInContext(PsiElement element) {
            PsiElement topmostExpressionElement = PsiTreeUtil.getTopmostParentOfType(element, XQueryExprSingle.class);
            PsiElement topmostElement = XQueryPsiImplUtil.getTopmostElementWithTheSameOffset(element);
            return topmostExpressionElement != null && isNotBeforeModuleDeclaration(topmostElement);
        }
    }
}

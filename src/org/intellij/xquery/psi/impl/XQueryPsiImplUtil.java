/*
 * Copyright 2013 Grzegorz Ligas
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

import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.xquery.psi.*;
import org.intellij.xquery.reference.XQueryModuleReference;
import org.intellij.xquery.reference.XQueryVariableReference;
import org.jetbrains.annotations.NotNull;

/**
 * User: ligasgr
 * Date: 10/02/13
 * Time: 18:59
 */
public class XQueryPsiImplUtil {

    private static final int DOLLAR_CHAR_LENGTH = 1;
    private static final int SEPARATOR_LENGTH = 1;

    public static String getName(XQueryModuleDecl element) {
        return element.getNameIdentifier().getText();
    }

    public static PsiElement setName(XQueryModuleDecl element, String newName) {
        XQueryModuleDeclName name = element.getModuleDeclName();
        if (name != null) {
            name.replace(XQueryElementFactory.createModuleDeclarationName(element.getProject(), newName));
        }
        return element;
    }

    public static PsiElement getNameIdentifier(XQueryModuleDecl element) {
        return PsiTreeUtil.findChildOfType(element, XQueryModuleDeclName.class);
    }


    public static PsiReference getReference(XQueryVarRef element) {
        int localNameOffset = DOLLAR_CHAR_LENGTH;
        if (element.getVarName().getVarNamespace() != null) {
            localNameOffset += element.getVarName().getVarNamespace().getTextLength() + SEPARATOR_LENGTH;
        }
        return new XQueryVariableReference(element, new TextRange(localNameOffset, element.getTextLength()));
    }

    public static String getName(XQueryVarName element) {
        if (element.getNameIdentifier() != null) {
            return element.getNameIdentifier().getText();
        } else {
            return null;
        }
    }

    public static PsiElement setName(XQueryVarName element, String newName) {
        XQueryVarName name = element;
        if (name != null) {
            XQueryVarLocalName localName = name.getVarLocalName();
            if (localName != null) {
                XQueryVarName newNameElement = XQueryElementFactory.createVariableReference(element.getProject(), newName);
                localName.replace(newNameElement.getVarLocalName());
            }
        }
        return element;
    }

    public static PsiElement getNameIdentifier(XQueryVarName element) {
        if (element == null) return null;
        return element.getVarLocalName();
    }

    public static int getTextOffset(XQueryVarName element) {
        if (element == null || element.getVarLocalName() == null) return 0;
        return getNameIdentifier(element).getTextOffset();
    }

    public static boolean processDeclarations(XQueryProlog module, @NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement place) {
        return processor.execute(module, state);
    }

    public static PsiReference getReference(XQueryModuleImportPath element) {
        if (element.getURILiteral() != null) {
            String filename = stripApostrophes(element.getURILiteral().getText());
            if (!StringUtil.isEmptyOrSpaces(filename)) {
                return new XQueryModuleReference(element, filename, new TextRange(1, element.getURILiteral().getTextLength() - 1));
            }
        }
        return null;
    }

    private static String stripApostrophes(String text) {
        return text.replaceAll("\"", "").replaceAll("'", "");
    }
}
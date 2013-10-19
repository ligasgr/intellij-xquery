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

package org.intellij.xquery.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.ResolveState;
import com.intellij.psi.TokenType;
import com.intellij.psi.impl.ResolveScopeManager;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.xquery.icons.XQueryIcons;
import org.intellij.xquery.psi.*;
import org.intellij.xquery.reference.function.XQueryFunctionReference;
import org.intellij.xquery.reference.module.XQueryModuleReference;
import org.intellij.xquery.reference.namespace.XQueryFunctionNamespaceNameReference;
import org.intellij.xquery.reference.namespace.XQueryVariableNamespaceNameReference;
import org.intellij.xquery.reference.variable.XQueryVariableReference;
import org.intellij.xquery.util.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

import static org.intellij.xquery.util.StringUtils.EMPTY;
import static org.intellij.xquery.util.StringUtils.removeQuotOrApos;

/**
 * User: ligasgr
 * Date: 10/02/13
 * Time: 18:59
 */
public class XQueryPsiImplUtil {

    private static final int DOLLAR_CHAR_LENGTH = 1;
    private static final int SEPARATOR_LENGTH = 1;

    public static String getName(XQueryNamespaceName element) {
        return element.getNameIdentifier().getText();
    }

    public static PsiElement setName(XQueryNamespaceName element, String newName) {
        XQueryNamespaceName name = element;
        if (name != null) {
            name.replace(XQueryElementFactory.createModuleDeclarationName(element.getProject(), newName));
        }
        return element;
    }

    public static PsiElement getNameIdentifier(XQueryNamespaceName element) {
        return element;
    }

    public static PsiReference getReference(XQueryVarRef element) {
        int localNameOffset = DOLLAR_CHAR_LENGTH;
        if (element.getVarName() != null) {
            if (element.getVarName().getVarNamespace() != null) {
                localNameOffset += element.getVarName().getVarNamespace().getTextLength() + SEPARATOR_LENGTH;
            }
            return new XQueryVariableReference(element, new TextRange(localNameOffset, element.getTextLength()));
        }
        return null;
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
                XQueryVarName newNameElement = XQueryElementFactory.createVariableReference(element.getProject(),
                        newName);
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

    public static boolean processDeclarations(XQueryProlog module, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state, PsiElement lastParent,
                                              @NotNull PsiElement place) {
        return processor.execute(module, state);
    }

    public static PsiReference getReference(XQueryModuleImportPath element) {
        String filename = stripApostrophes(element.getURILiteral().getText());
        if (!StringUtil.isEmptyOrSpaces(filename)) {
            return new XQueryModuleReference(element, filename, new TextRange(1,
                    element.getURILiteral().getTextLength() - 1));
        }
        return null;
    }

    private static String stripApostrophes(String text) {
        return text.replaceAll("\"", "").replaceAll("'", "");
    }

    public static PsiReference getReference(XQueryVarNamespace element) {
        return new XQueryVariableNamespaceNameReference(element, new TextRange(0, element.getTextLength()));
    }

    public static PsiReference getReference(XQueryFunctionNamespace element) {
        return new XQueryFunctionNamespaceNameReference(element, new TextRange(0, element.getTextLength()));
    }

    public static PsiReference getReference(XQueryFunctionInvocation element) {
        int localNameOffset = 0;
        if (element.getFunctionName().getFunctionNamespace() != null) {
            localNameOffset += element.getFunctionName().getFunctionNamespace().getTextLength() + SEPARATOR_LENGTH;
        }
        return new XQueryFunctionReference(element, new TextRange(localNameOffset,
                element.getFunctionName().getTextLength()));
    }

    public static String getName(XQueryFunctionName element) {
        if (element.getNameIdentifier() != null) {
            return element.getNameIdentifier().getText();
        } else {
            return null;
        }
    }

    public static PsiElement setName(XQueryFunctionName element, String newName) {
        XQueryFunctionName name = element;
        if (name != null) {
            XQueryFunctionLocalName localName = name.getFunctionLocalName();
            if (localName != null) {
                XQueryFunctionName newNameElement = XQueryElementFactory.createFunctionReference(element.getProject()
                        , "dummy", newName);
                localName.replace(newNameElement.getFunctionLocalName());
            }
        }
        return element;
    }

    public static PsiElement getNameIdentifier(XQueryFunctionName element) {
        if (element == null) return null;
        return element.getFunctionLocalName();
    }

    public static int getTextOffset(XQueryFunctionName element) {
        if (element == null || element.getFunctionLocalName() == null) return 0;
        return getNameIdentifier(element).getTextOffset();
    }

    public static SearchScope getUseScope(XQueryVarName element) {
        XQueryFunctionDecl function = PsiTreeUtil.getParentOfType(element, XQueryFunctionDecl.class, true);
        if (function != null) {
            return new LocalSearchScope(function);
        }
        XQueryQueryBody queryBody = PsiTreeUtil.getParentOfType(element, XQueryQueryBody.class, true);
        if (queryBody != null) {
            return new LocalSearchScope(queryBody);
        }
        return ResolveScopeManager.getElementUseScope(element);
    }

    public static ItemPresentation getPresentation(final XQueryFunctionDecl element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                String name = element.getFunctionName() != null ? element.getFunctionName().getText() : "";
                String tailText = (element.getParamList() != null ? element.getParamList().getText() : "") + " as ";
                String typeText = element.getSequenceType() != null ? element.getSequenceType()
                        .getText() : "item()*";
                return StringUtils.compressWhitespaces(name + tailText + typeText);
            }

            @Nullable
            @Override
            public String getLocationString() {
                return null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return functionIsPublic(element) ? XQueryIcons.FUNCTION_PUBLIC_ICON : XQueryIcons.FUNCTION_PRIVATE_ICON;
            }
        };
    }

    public static ItemPresentation getPresentation(final XQueryVarDecl element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                String name = element.getVarName() != null ? element.getVarName().getText() : "";
                String typeText = "item()*";
                if (element.getTypeDeclaration() != null) {
                    typeText = element.getTypeDeclaration().getText();
                }
                return StringUtils.compressWhitespaces(name + " as " + typeText);
            }

            @Nullable
            @Override
            public String getLocationString() {
                return null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return variableIsPublic(element) ? XQueryIcons.VARIABLE_PUBLIC_ICON : XQueryIcons.VARIABLE_PRIVATE_ICON;
            }
        };
    }

    public static ItemPresentation getPresentation(final XQueryVarName element) {
        if (element.getParent() instanceof XQueryVarDecl) {
            return ((XQueryVarDecl) element.getParent()).getPresentation();
        }
        return null;
    }

    public static ItemPresentation getPresentation(final XQueryFunctionName element) {
        if (element.getParent() instanceof XQueryFunctionDecl) {
            return ((XQueryFunctionDecl) element.getParent()).getPresentation();
        }
        return null;
    }

    public static int getArity(XQueryFunctionCall functionCall) {
        return functionCall.getArgumentList().getArgumentList().size();
    }

    public static int getArity(XQueryNamedFunctionRef functionCall) {
        return StringUtil.parseInt(functionCall.getFunctionArity().getText(), 0);
    }

    public static int getArity(XQueryFunctionDecl functionDeclaration) {
        if (functionDeclaration.getParamList() != null)
            return functionDeclaration.getParamList().getParamList().size();
        else
            return 0;
    }

    public static void delete(XQueryNamedElement namedElement) {
        PsiElement declarationElement = namedElement.getParent();
        final ASTNode parentNode = declarationElement.getParent().getNode();
        assert parentNode != null;

        ASTNode node = declarationElement.getNode();
        ASTNode prev = node.getTreePrev();
        ASTNode next = node.getTreeNext();
        parentNode.removeChild(node);
        if (prev == null || prev.getElementType() == TokenType.WHITE_SPACE) {
            while (next != null && (next.getElementType() == TokenType.WHITE_SPACE || next.getElementType() ==
                    XQueryTypes.SEPARATOR)) {
                parentNode.removeChild(next);
                next = node.getTreeNext();
            }
        }
    }

    public static boolean functionIsPublic(XQueryFunctionDecl functionDecl) {
        boolean result = true;
        for (XQueryAnnotation annotation : functionDecl.getAnnotationList()) {
            if ("private".equals(annotation.getAnnotationName().getText()))
                return false;
        }
        return result;
    }

    public static boolean variableIsPublic(XQueryVarDecl variableDeclaration) {
        boolean result = true;
        for (XQueryAnnotation annotation : variableDeclaration.getAnnotationList()) {
            if ("private".equals(annotation.getAnnotationName().getText()))
                return false;
        }
        return result;
    }

    public static boolean isExternal(XQueryVarDecl variableDeclaration) {
        return variableDeclaration.getExternalVarPart() != null;
    }

    public static String getNamespace(XQueryModuleDecl moduleDecl) {
        if (moduleDecl.getURILiteral() != null) {
            return removeQuotOrApos(moduleDecl.getURILiteral().getText());
        }
        return EMPTY;
    }

    public static String getNamespace(XQueryModuleImport moduleImport) {
        if (moduleImport.getModuleImportNamespace() != null) {
            return removeQuotOrApos(moduleImport.getModuleImportNamespace().getModuleImportPath().getURILiteral()
                    .getText());
        }
        return EMPTY;
    }

    public static String getNamespace(XQueryNamespaceDecl namespaceDecl) {
        if (namespaceDecl.getURILiteral() != null) {
            return removeQuotOrApos(namespaceDecl.getURILiteral().getText());
        }
        return EMPTY;
    }
}
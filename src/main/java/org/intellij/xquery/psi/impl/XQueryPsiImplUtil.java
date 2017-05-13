/*
 * Copyright 2013-2017 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.ResolveState;
import com.intellij.psi.TokenType;
import com.intellij.psi.impl.ResolveScopeManager;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiElementFilter;
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.xquery.icons.XQueryIcons;
import org.intellij.xquery.psi.XQueryAnnotation;
import org.intellij.xquery.psi.XQueryArrowFunctionReference;
import org.intellij.xquery.psi.XQueryArrowFunctionSpecifier;
import org.intellij.xquery.psi.XQueryAttrLocalName;
import org.intellij.xquery.psi.XQueryAttrNamespace;
import org.intellij.xquery.psi.XQueryElementFactory;
import org.intellij.xquery.psi.XQueryExprSingle;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryForBinding;
import org.intellij.xquery.psi.XQueryFunctionCall;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionInvocation;
import org.intellij.xquery.psi.XQueryFunctionLocalName;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.intellij.xquery.psi.XQueryLetBinding;
import org.intellij.xquery.psi.XQueryMarklogicAnnotation;
import org.intellij.xquery.psi.XQueryModuleDecl;
import org.intellij.xquery.psi.XQueryModuleImport;
import org.intellij.xquery.psi.XQueryModuleImportPath;
import org.intellij.xquery.psi.XQueryNamedElement;
import org.intellij.xquery.psi.XQueryNamedFunctionRef;
import org.intellij.xquery.psi.XQueryNamespaceDecl;
import org.intellij.xquery.psi.XQueryNamespacePrefix;
import org.intellij.xquery.psi.XQueryPrefix;
import org.intellij.xquery.psi.XQueryPsiElement;
import org.intellij.xquery.psi.XQueryQueryBody;
import org.intellij.xquery.psi.XQueryTypes;
import org.intellij.xquery.psi.XQueryVarDecl;
import org.intellij.xquery.psi.XQueryVarLocalName;
import org.intellij.xquery.psi.XQueryVarName;
import org.intellij.xquery.psi.XQueryVarRef;
import org.intellij.xquery.psi.XQueryVersion;
import org.intellij.xquery.psi.XQueryXmlTagName;
import org.intellij.xquery.psi.XQueryXmlTagNamespace;
import org.intellij.xquery.reference.function.XQueryFunctionReference;
import org.intellij.xquery.reference.module.XQueryModuleReference;
import org.intellij.xquery.reference.namespace.XQueryAttrNamespaceReference;
import org.intellij.xquery.reference.namespace.XQueryNamespacePrefixReference;
import org.intellij.xquery.reference.namespace.XQueryXmlTagNamespaceReference;
import org.intellij.xquery.reference.variable.XQueryVariableReference;
import org.intellij.xquery.reference.xml.XQueryXmlTagNameReference;
import org.intellij.xquery.util.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;

import java.util.Collection;

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

    public static String getName(XQueryNamespacePrefix element) {
        return element.getNameIdentifier().getText();
    }

    public static PsiElement setName(XQueryNamespacePrefix element, String newName) {
        XQueryNamespacePrefix name = element;
        if (name != null) {
            name.replace(XQueryElementFactory.createModuleDeclarationName(element.getProject(), newName));
        }
        return element;
    }

    public static PsiElement getNameIdentifier(XQueryNamespacePrefix element) {
        return element;
    }

    public static PsiReference getReference(XQueryVarRef element) {
        int localNameOffset = DOLLAR_CHAR_LENGTH;
        if (element.getVarName() != null) {
            if (element.getVarName().getPrefix() != null) {
                localNameOffset += element.getVarName().getPrefix().getTextLength() + SEPARATOR_LENGTH;
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
        if (element == null) return 0;
        PsiElement nameIdentifier = getNameIdentifier(element);
        if (nameIdentifier == null) return endOfColon(element);
        return nameIdentifier.getTextOffset();
    }

    private static int endOfColon(XQueryVarName varName) {
        if (varName.getPrefix() != null) {
            String colon = XQueryTypes.COLON.toString();
            int offset = varName.getText().indexOf(colon) + colon.length();
            return varName.getNode().getStartOffset() + offset;
        }
        return 0;
    }

    public static PsiReference getReference(XQueryModuleImportPath element) {
        String filename = stripApostrophes(element.getURILiteral().getText());
        if (!StringUtil.isEmptyOrSpaces(filename)) {
            return new XQueryModuleReference(element, filename, new TextRange(1,
                    element.getURILiteral().getTextLength() - 1));
        }
        return null;
    }

    public static String stripApostrophes(String text) {
        return text.replaceAll("\"", "").replaceAll("'", "");
    }

    public static PsiReference getReference(XQueryFunctionInvocation element) {
        if (element.getFunctionName() == null) return null;
        int localNameOffset = 0;
        if (element.getFunctionName().getPrefix() != null) {
            localNameOffset += element.getFunctionName().getPrefix().getTextLength() + SEPARATOR_LENGTH;
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

    public static String getPrefixText(XQueryFunctionName element) {
        if (element.getPrefix() != null) {
            return element.getPrefix().getText();
        } else {
            return null;
        }
    }

    public static String getLocalNameText(XQueryFunctionName element) {
        if (element.getFunctionLocalName() != null) {
            return element.getFunctionLocalName().getText();
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
        if (element == null) return 0;
        PsiElement nameIdentifier = getNameIdentifier(element);
        if (nameIdentifier == null) return endOfColon(element);
        return nameIdentifier.getTextOffset();
    }

    private static int endOfColon(XQueryFunctionName functionName) {
        if (functionName.getPrefix() != null) {
            String colon = XQueryTypes.COLON.toString();
            int offset = functionName.getText().indexOf(colon) + colon.length();
            return functionName.getNode().getStartOffset() + offset;
        }
        return 0;
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
                return element.isPublic() ? XQueryIcons.FUNCTION_PUBLIC_ICON : XQueryIcons.FUNCTION_PRIVATE_ICON;
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
                return isPublic(element) ? XQueryIcons.VARIABLE_PUBLIC_ICON : XQueryIcons.VARIABLE_PRIVATE_ICON;
            }
        };
    }

    public static ItemPresentation getPresentation(final XQueryVarName element) {
        if (element.getParent() instanceof XQueryVarDecl) {
            return ((XQueryVarDecl) element.getParent()).getPresentation();
        }
        return null;
    }


    public static ItemPresentation getPresentation(final XQueryQueryBody element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return "Query Body";
            }

            @Nullable
            @Override
            public String getLocationString() {
                return null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return XQueryIcons.QUERY_BODY;
            }
        };
    }

    public static ItemPresentation getPresentation(final XQueryFunctionName element) {
        if (element.getParent() instanceof XQueryFunctionDecl) {
            return ((XQueryFunctionDecl) element.getParent()).getPresentation();
        }
        return null;
    }

    public static int getArity(XQueryArrowFunctionReference functionCall) {
        return functionCall.getArgumentList().getArgumentList().size() + 1;
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

    public static boolean hasValidFunctionName(XQueryFunctionDecl functionDecl) {
        return functionDecl.getFunctionName() != null && functionDecl.getFunctionName().getTextLength() > 0;
    }

    public static boolean isPublic(XQueryFunctionDecl functionDecl) {
        for (XQueryAnnotation annotation : functionDecl.getAnnotationList()) {
            if ("private".equals(annotation.getAnnotationName().getText()))
                return false;
        }
        for (XQueryMarklogicAnnotation annotation : functionDecl.getMarklogicAnnotationList()) {
            if ("private".equals(annotation.getText()))
                return false;
        }
        return true;
    }

    public static boolean isPublic(XQueryVarDecl varDecl) {
        for (XQueryAnnotation annotation : varDecl.getAnnotationList()) {
            if ("private".equals(annotation.getAnnotationName().getText()))
                return false;
        }
        for (XQueryMarklogicAnnotation annotation : varDecl.getMarklogicAnnotationList()) {
            if ("private".equals(annotation.getText()))
                return false;
        }
        return true;
    }

    public static void delete(XQueryAttrLocalName namedElement) {
        PsiElement dirAttribute = namedElement.getParent().getParent();
        deleteElement(dirAttribute);
    }

    public static void delete(XQueryVarName element) {
        deleteDeclaration(element);
    }

    public static void delete(XQueryFunctionName element) {
        deleteDeclaration(element);
    }

    public static void delete(XQueryNamespacePrefix element) {
        deleteDeclaration(element);
    }

    private static void deleteDeclaration(XQueryNamedElement namedElement) {
        PsiElement declarationElement = namedElement.getParent();
        deleteElement(declarationElement);
    }

    private static void deleteElement(PsiElement element) {
        final ASTNode parentNode = element.getParent().getNode();
        assert parentNode != null;

        ASTNode node = element.getNode();
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

    public static PsiReference getReference(XQueryPrefix prefix) {
        return new XQueryNamespacePrefixReference(prefix, new TextRange(0, prefix.getTextLength()));
    }

    public static PsiReference getReference(XQueryXmlTagNamespace prefix) {
        return new XQueryXmlTagNamespaceReference(prefix, new TextRange(0, prefix.getTextLength()));
    }

    public static PsiReference getReference(XQueryAttrNamespace prefix) {
        return new XQueryAttrNamespaceReference(prefix, new TextRange(0, prefix.getTextLength()));
    }

    public static PsiReference getReference(XQueryXmlTagName element) {
        int offset = 0;
        if (element.getXmlTagNamespace() != null) {
            offset += element.getXmlTagNamespace().getTextLength() + SEPARATOR_LENGTH;
        }
        return new XQueryXmlTagNameReference(element, new TextRange(offset, element.getTextLength()));
    }

    public static String getName(XQueryPrefix element) {
        return element.getNameIdentifier().getText();
    }

    public static PsiElement setName(XQueryPrefix element, String newName) {
        XQueryPrefix name = element;
        if (name != null) {
            name.replace(XQueryElementFactory.createFunctionReference(element.getProject(), newName, "any").getPrefix());
        }
        return element;
    }

    public static String getName(XQueryXmlTagNamespace element) {
        return element.getNameIdentifier().getText();
    }

    public static PsiElement setName(XQueryXmlTagNamespace element, String newName) {
        XQueryXmlTagNamespace name = element;
        if (name != null) {
            name.replace(XQueryElementFactory.createXmlTag(element.getProject(), newName, "any").getXmlTagName().getXmlTagNamespace());
        }
        return element;
    }

    public static String getName(XQueryAttrLocalName element) {
        return element.getNameIdentifier().getText();
    }

    public static PsiElement setName(XQueryAttrLocalName element, String newName) {
        XQueryAttrLocalName name = element;
        if (name != null) {
            name.replace(XQueryElementFactory.createAttributeName(element.getProject(), "any", newName).getAttrLocalName());
        }
        return element;
    }

    public static String getName(XQueryAttrNamespace element) {
        return element.getNameIdentifier().getText();
    }

    public static PsiElement setName(XQueryAttrNamespace element, String newName) {
        XQueryAttrNamespace name = element;
        if (name != null) {
            name.replace(XQueryElementFactory.createAttributeName(element.getProject(), newName, "any").getAttrNamespace());
        }
        return element;
    }

    public static String getName(XQueryXmlTagName element) {
        return element.getNameIdentifier().getText();
    }

    public static PsiElement setName(XQueryXmlTagName element, String newName) {
        XQueryXmlTagName name = element;
        if (name != null) {
            name.getXmlTagLocalName().replace(XQueryElementFactory.createXmlTag(element.getProject(), "any", newName).getXmlTagName().getXmlTagLocalName());
        }
        return element;
    }

    public static PsiElement getNameIdentifier(XQueryPrefix element) {
        return element;
    }

    public static PsiElement getNameIdentifier(XQueryXmlTagNamespace element) {
        return element;
    }

    public static PsiElement getNameIdentifier(XQueryAttrLocalName element) {
        return element;
    }

    public static PsiElement getNameIdentifier(XQueryAttrNamespace element) {
        return element;
    }

    public static PsiElement getNameIdentifier(XQueryXmlTagName element) {
        if (element == null) return null;
        return element.getXmlTagLocalName();
    }

    public static int getTextOffset(XQueryXmlTagName element) {
        if (element == null) return 0;
        PsiElement nameIdentifier = getNameIdentifier(element);
        if (nameIdentifier == null) return endOfColon(element);
        return nameIdentifier.getTextOffset();
    }

    private static int endOfColon(XQueryXmlTagName tagName) {
        if (tagName.getXmlTagNamespace() != null) {
            String colon = XQueryTypes.COLON.toString();
            int offset = tagName.getText().indexOf(colon) + colon.length();
            return tagName.getNode().getStartOffset() + offset;
        }
        return 0;
    }

    public static boolean isEquivalentTo(XQueryPrefix element, PsiElement another) {
        return isEquivalentPrefix(element, another);
    }

    public static boolean isEquivalentTo(XQueryXmlTagNamespace element, PsiElement another) {
        return isEquivalentPrefix(element, another);
    }

    public static boolean isEquivalentTo(XQueryAttrNamespace element, PsiElement another) {
        return isEquivalentPrefix(element, another);
    }

    private static boolean isEquivalentPrefix(XQueryPsiElement element, PsiElement another) {
        boolean isElementContainingNamespace = another instanceof XQueryPrefix
                || another instanceof XQueryXmlTagNamespace
                || another instanceof XQueryAttrNamespace;
        if (!isElementContainingNamespace) {
            return false;
        }
        if (element.getContainingFile() instanceof XQueryFile && another.getContainingFile() instanceof XQueryFile) {
            XQueryFile elementFile = (XQueryFile) element.getContainingFile();
            XQueryFile anotherFile = (XQueryFile) another.getContainingFile();
            String elementNamespace = elementFile.mapFunctionPrefixToNamespace(element.getText());
            String anotherNamespace = anotherFile.mapFunctionPrefixToNamespace(another.getText());
            return elementFile.equals(anotherFile) && elementNamespace.equals(anotherNamespace);
        }
        return false;
    }

    public static PsiElement getTopmostElementWithTheSameOffset(PsiElement element) {
        if (element == null) return null;
        PsiElement lastElement = element;
        int offset = element.getTextOffset();

        while (element != null) {
            if (offset == element.getTextOffset() && !(element instanceof PsiFile)) {
                lastElement = element;
                element = element.getParent();
            } else {
                return lastElement;
            }
        }

        return lastElement;
    }

    public static PsiElement getNextNonWhiteSpaceElement(PsiElement element) {
        if (isNotWhitespace(element)) {
            return element;
        }
        PsiElement nextSibling = element.getNextSibling();
        while (nextSibling != null) {
            if (isNotWhitespace(nextSibling)) {
                return nextSibling;
            }
            nextSibling = element.getNextSibling();
        }
        return null;
    }

    public static PsiElement getPrevNonWhiteSpaceElement(PsiElement element) {
        PsiElement previousLeaf = previousLeaf(element);
        while (previousLeaf != null && (previousLeaf.getTextLength() == 0 || isWhitespace(previousLeaf))) {
            previousLeaf = previousLeaf(previousLeaf);
        }
        return previousLeaf;
    }

    public static PsiElement previousLeaf(@NotNull PsiElement current) {
        final PsiElement prevSibling = current.getPrevSibling();
        if (prevSibling != null) return PsiTreeUtil.lastChild(prevSibling);
        final PsiElement parent = current.getParent();
        if (parent == null || parent instanceof PsiFile) return null;
        PsiElement previousOfParent = previousLeaf(parent);
        if (previousOfParent == null) return null;
        return PsiTreeUtil.lastChild(previousOfParent);
    }

    private static boolean isNotWhitespace(PsiElement element) {
        return !isWhitespace(element);
    }

    private static boolean isWhitespace(PsiElement element) {
        return element instanceof PsiWhiteSpace;
    }

    public static PsiElement[] findChildrenOfType(PsiElement startingElement, final IElementType elementType) {
        return PsiTreeUtil.collectElements(startingElement, new PsiElementFilter() {
            @Override
            public boolean isAccepted(PsiElement element) {
                return element.getNode() != null && element.getNode().getElementType() == elementType;
            }
        });
    }

    public static int getTextOffset(XQueryFunctionDecl functionDecl) {
        if (functionDecl.getFunctionName() == null) return endOfFunctionKeyword(functionDecl);
        return functionDecl.getFunctionName().getTextOffset();
    }

    private static int endOfFunctionKeyword(XQueryFunctionDecl functionDecl) {
        String keyword = XQueryTypes.K_FUNCTION.toString();
        int namePositionOffset = functionDecl.getText().indexOf(keyword) + keyword.length();
        return functionDecl.getNode().getStartOffset() + namePositionOffset;
    }

    public static int getTextOffset(XQueryVarDecl varDecl) {
        if (varDecl.getVarName() == null) return endOfDollarOrVariableKeyword(varDecl);
        ;
        return varDecl.getVarName().getTextOffset();
    }

    private static int endOfDollarOrVariableKeyword(XQueryVarDecl varDecl) {
        String dollarSign = XQueryTypes.DOLLAR_SIGN.toString();
        int indexOfDollar = varDecl.getText().indexOf(dollarSign);
        int dollarOffset = varDecl.getNode().getStartOffset() + indexOfDollar + dollarSign.length();
        String keyword = XQueryTypes.K_VARIABLE.toString();
        int namePositionOffset = varDecl.getText().indexOf(keyword) + keyword.length();
        int variableOffset = varDecl.getNode().getStartOffset() + namePositionOffset;
        return indexOfDollar > -1 ? dollarOffset : variableOffset;
    }

    public static String getVersionString(XQueryVersion version) {
        return stripApostrophes(version.getText());
    }

    public static boolean isEquivalentTo(XQueryFunctionName functionName, PsiElement another) {
        if (another instanceof XQueryFunctionName) {
            if (functionName.getContainingFile() instanceof XQueryFile && another.getContainingFile() instanceof XQueryFile) {
                XQueryFunctionName anotherFunctionName = (XQueryFunctionName) another;
                XQueryFile elementFile = (XQueryFile) functionName.getContainingFile();
                XQueryFile anotherFile = (XQueryFile) another.getContainingFile();
                String elementNamespace = elementFile.mapFunctionPrefixToNamespace(functionName.getPrefixText());
                String anotherNamespace = anotherFile.mapFunctionPrefixToNamespace(anotherFunctionName.getPrefixText());
                String elementName = functionName.getLocalNameText();
                String anotherName = anotherFunctionName.getLocalNameText();
                return elementFile.equals(anotherFile)
                        && elementNamespace.equals(anotherNamespace)
                        && elementName != null && elementName.equals(anotherName);
            }
        }
        return false;
    }

    public static boolean processDeclarations(XQueryForBinding module, @NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement place) {
        return processChildrenIfPlaceIsNotPartOfSameBinding(module, processor, state, lastParent, place, XQueryForBinding.class, XQueryExprSingle.class);
    }

    public static boolean processDeclarations(XQueryLetBinding module, @NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement place) {
        return processChildrenIfPlaceIsNotPartOfSameBinding(module, processor, state, lastParent, place, XQueryLetBinding.class, XQueryExprSingle.class);
    }

    public static XQueryFunctionName getFunctionName(XQueryArrowFunctionReference functionReference) {
        if (functionReference.getArrowFunctionSpecifier().getFunctionName() != null) {
            return functionReference.getArrowFunctionSpecifier().getFunctionName();
        }
        return null;
    }

    private static <T extends XQueryPsiElement, U extends XQueryPsiElement>boolean processChildrenIfPlaceIsNotPartOfSameBinding(T module,
            @NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent,
            @NotNull PsiElement place, Class<T> bindingClass, Class<U>... childClassesToSkip) {
        PsiElement commonParent = PsiTreeUtil.findCommonParent(place, module);
        T parentBinding = PsiTreeUtil.getParentOfType(place, bindingClass, true);
        XQueryExprSingle expressionInModule = PsiTreeUtil.getChildOfType(module, XQueryExprSingle.class);
        boolean inSameBinding = commonParent != null && commonParent.equals(module) && parentBinding != null && parentBinding.equals(module);
        Collection<T> childBindings = PsiTreeUtil.findChildrenOfType(expressionInModule, bindingClass);
        boolean partOfExpressionInBinding = childBindings.contains(parentBinding);
        if (inSameBinding || partOfExpressionInBinding) {
            return processor.execute(module, state);
        } else {
            if (!processor.execute(module, state)) {
                return false;
            } else {
                return ResolveUtil.processChildren(module, processor, state, lastParent, place, childClassesToSkip);
            }
        }
    }
}
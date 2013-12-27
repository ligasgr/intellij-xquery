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

package org.intellij.xquery.documentation;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiWhiteSpace;
import org.intellij.xquery.psi.XQueryAnnotation;
import org.intellij.xquery.psi.XQueryCaseClause;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryForBinding;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionInvocation;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.intellij.xquery.psi.XQueryGroupingSpec;
import org.intellij.xquery.psi.XQueryLetBinding;
import org.intellij.xquery.psi.XQueryMultiVariableBinding;
import org.intellij.xquery.psi.XQueryParam;
import org.intellij.xquery.psi.XQueryQuantifiedExpr;
import org.intellij.xquery.psi.XQueryTypeswitchDefaultReturnClause;
import org.intellij.xquery.psi.XQueryVarDecl;
import org.intellij.xquery.psi.XQueryVarName;
import org.intellij.xquery.psi.XQueryVarRef;
import org.intellij.xquery.psi.XQueryWindowClause;
import org.jetbrains.annotations.Nullable;

import static com.intellij.psi.util.PsiTreeUtil.getParentOfType;
import static javax.xml.XMLConstants.NULL_NS_URI;
import static org.intellij.xquery.documentation.XQDocTransformer.transformXQDoc;
import static org.intellij.xquery.psi.XQueryBasicTypes.DOC_COMMENT_CONTENT;
import static org.intellij.xquery.util.StringUtils.compressWhitespaces;
import static org.intellij.xquery.util.StringUtils.normalizeWhitespaces;
import static org.intellij.xquery.util.StringUtils.removeQuotOrAposIfNeeded;

/**
 * User: ligasgr
 * Date: 13/12/13
 * Time: 13:44
 */
public class XQueryDocumentationProvider extends AbstractDocumentationProvider {
    static final String HTML_BR = "<br/>";
    static final String NAMESPACE_LABEL = "namespace: ";
    static final String FILE_LINK_TEMPLATE = "<a href='psi_element://%s'>%s</a>";

    @Override
    public PsiElement getDocumentationElementForLookupItem(PsiManager psiManager, Object object, PsiElement element) {
        if (object instanceof PsiElement)
            return (PsiElement) object;
        else {
            return null;
        }
    }

    @Override
    public String generateDoc(PsiElement element, @Nullable PsiElement originalElement) {
        if (! (element.getContainingFile() instanceof XQueryFile)) return null;
        if (element instanceof XQueryFunctionName) {
            return getDocumentationForFunction((XQueryFunctionName) element);
        }
        if (element instanceof XQueryVarName) {
            return getDocumentationForVariable((XQueryVarName) element);
        }
        return null;
    }

    private String getDocumentationForFunction(XQueryFunctionName functionName) {
        XQueryFunctionDecl elementToProduceDescription = getElementToProduceDescription(functionName);
        if (elementToProduceDescription != null) {
            return getDocumentationFromFunctionDeclaration(functionName, elementToProduceDescription);
        } else {
            return null;
        }
    }

    private String getDocumentationFromFunctionDeclaration(XQueryFunctionName functionName, XQueryFunctionDecl
            elementToProduceDescription) {
        String containingFileName = elementToProduceDescription.getContainingFile().getName();
        XQueryFile xqueryFile = (XQueryFile) functionName.getContainingFile();
        String prefix = functionName.getPrefix() != null ? functionName.getPrefix().getText() : null;
        String mappedNamespace = xqueryFile.mapFunctionPrefixToNamespace(prefix);
        String namespace = removeQuotOrAposIfNeeded(mappedNamespace != null ? mappedNamespace : NULL_NS_URI);
        String description = getDescription(elementToProduceDescription);
        String xqDocDescription = getXQDocDescription(elementToProduceDescription);

        return (getLink(containingFileName, containingFileName) + HTML_BR) +
                (NULL_NS_URI.equals(namespace) ? "" : NAMESPACE_LABEL + namespace + HTML_BR) +
                description +
                (xqDocDescription != null ? HTML_BR + HTML_BR + xqDocDescription : "");
    }

    private String getXQDocDescription(PsiElement source) {
        final PsiComment comment = getPreviousComment(source);
        if (comment != null) {
            String documentationComment = comment.getText();
            return transformXQDoc(documentationComment);
        }
        return null;
    }

    private PsiComment getPreviousComment(PsiElement sibling) {
        if (sibling == null) return null;
        for (PsiElement child = sibling.getPrevSibling();
             child != null && (child instanceof PsiWhiteSpace || child instanceof PsiComment);
             child = child.getPrevSibling()) {
            if (PsiComment.class.isInstance(child) && child.getNode().getElementType() == DOC_COMMENT_CONTENT) {
                return (PsiComment) child;
            }
        }
        return null;
    }

    private XQueryFunctionDecl getElementToProduceDescription(XQueryFunctionName functionName) {
        PsiElement elementToProduceDescription = functionName;
        if (functionName.getParent() instanceof XQueryFunctionInvocation) {
            elementToProduceDescription = functionName.getParent().getReference().resolve();
        }
        return getParentOfType(elementToProduceDescription, XQueryFunctionDecl.class);
    }

    private String getDescription(XQueryFunctionDecl fun) {
        StringBuilder sb = new StringBuilder();
        sb.append("declare ");
        sb.append(getAnnotations(fun));
        sb.append(" function ");
        sb.append(fun.getFunctionName().getText());
        if (fun.getParamList() != null) {
            sb.append(fun.getParamList().getText());
        }
        if (fun.getSequenceType() != null) {
            sb.append(" as ");
            sb.append(fun.getSequenceType().getText());
        }
        return compressWhitespaces(sb.toString());
    }

    private String getAnnotations(XQueryFunctionDecl fun) {
        StringBuilder sb = new StringBuilder();
        for (XQueryAnnotation xQueryAnnotation : fun.getAnnotationList()) {
            sb.append(xQueryAnnotation.getText());
            sb.append(" ");
        }

        return sb.toString();
    }

    private String getDocumentationForVariable(XQueryVarName varName) {
        PsiElement elementToProduceDescription = getElementToProduceDescription(varName);
        if (elementToProduceDescription != null) {
            return getDocumentationFromVariableSource(varName, elementToProduceDescription);
        } else {
            return null;
        }
    }

    private String getDocumentationFromVariableSource(XQueryVarName varName, PsiElement elementToProduceDescription) {
        String containingFileName = elementToProduceDescription.getContainingFile().getName();
        XQueryFile xqueryFile = (XQueryFile) varName.getContainingFile();
        String prefix = varName.getPrefix() != null ? varName.getPrefix().getText() : null;
        String mappedNamespace = xqueryFile.mapVariablePrefixToNamespace(prefix);
        String namespace = removeQuotOrAposIfNeeded(mappedNamespace != null ? mappedNamespace : NULL_NS_URI);
        String description = getDescription(elementToProduceDescription);
        String xqDocDescription = getXQDocDescription(elementToProduceDescription);

        return (getLink(containingFileName, containingFileName) + HTML_BR) +
                (NULL_NS_URI.equals(namespace) ? "" : NAMESPACE_LABEL + namespace + HTML_BR) +
                description +
                (xqDocDescription != null ? HTML_BR + HTML_BR + xqDocDescription : "");
    }

    private String getDescription(PsiElement elementToProduceDescription) {
        String descriptionSuffix = elementToProduceDescription.getText();
        String descriptionPrefix = "";
        if (elementToProduceDescription instanceof XQueryLetBinding) {
            descriptionPrefix = "let ";
        } else if (elementToProduceDescription instanceof XQueryForBinding) {
            descriptionPrefix = "for ";
        } else if (elementToProduceDescription instanceof XQueryGroupingSpec) {
            descriptionPrefix = "group by ";
        } else if (getParentOfType(elementToProduceDescription, XQueryQuantifiedExpr.class, true) != null) {
            XQueryQuantifiedExpr expr = getParentOfType(elementToProduceDescription, XQueryQuantifiedExpr.class, true);
            descriptionPrefix = expr.getText().split(" ")[0] + " ";
        }
        return normalizeWhitespaces(descriptionPrefix + descriptionSuffix);
    }

    private PsiElement getElementToProduceDescription(XQueryVarName varName) {
        PsiElement elementToProduceDescription = varName;
        if (varName.getParent() instanceof XQueryVarRef) {
            elementToProduceDescription = varName.getParent().getReference().resolve();
        }
        return getParentOfType(elementToProduceDescription, XQueryVarDecl.class, XQueryParam.class,
                XQueryForBinding.class, XQueryLetBinding.class, XQueryWindowClause.class, XQueryGroupingSpec.class,
                XQueryTypeswitchDefaultReturnClause.class, XQueryCaseClause.class, XQueryMultiVariableBinding.class);
    }

    private String getLink(String fullPath, String displayedName) {
        return String.format(FILE_LINK_TEMPLATE, fullPath, displayedName);
    }
}

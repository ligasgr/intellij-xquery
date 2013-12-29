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

import com.intellij.psi.PsiElement;
import org.intellij.xquery.completion.function.BuiltInFunctionTable;
import org.intellij.xquery.psi.XQueryAnnotation;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionInvocation;
import org.intellij.xquery.psi.XQueryFunctionName;

import static com.intellij.psi.util.PsiTreeUtil.getParentOfType;
import static javax.xml.XMLConstants.NULL_NS_URI;
import static org.intellij.xquery.util.StringUtils.compressWhitespaces;
import static org.intellij.xquery.util.StringUtils.removeQuotOrAposIfNeeded;

/**
 * User: ligasgr
 * Date: 29/12/13
 * Time: 09:24
 */
public class FunctionDocumentationProvider implements PsiBasedDocumentationProvider<XQueryFunctionName> {

    private static final String XQUERY_STYLE = "<style type=\"text/css\">dt {font-weight: bold}</style>\n";

    private static String wrappedWithHtmlAndStyle(String doc) {
        return "<html>\n" + XQUERY_STYLE + "<body>\n" + doc + "</body></html>\n";
    }

    @Override
    public String generateDoc(XQueryFunctionName functionName) {
        XQueryFunctionDecl elementToProduceDescription = getElementToProduceDescription(functionName);
        if (elementToProduceDescription != null) {
            return getDocumentationFromFunctionDeclaration(functionName, elementToProduceDescription).getText();
        } else {
            String name = functionName.getLocalNameText();
            String prefix = functionName.getPrefixText();
            String namespace = ((XQueryFile) functionName.getContainingFile()).mapFunctionPrefixToNamespace(prefix);
            if (BuiltInFunctionTable.isBuiltInFunction(namespace, name)) {
                return getDocumentationFromExternalFile(namespace, name);
            } else {
                return null;
            }
        }
    }

    private String getDocumentationFromExternalFile(String namespace, String name) {
        String doc = ExternalDocumentationFetcher.fetch(name);
        if (doc != null)
            return wrappedWithHtmlAndStyle(doc);
        else
            return null;
    }

    private CommentAndSignatureBasedDocumentation getDocumentationFromFunctionDeclaration(XQueryFunctionName
                                                                                                  functionName,
                                                                                          XQueryFunctionDecl
                                                                                                  elementToProduceDescription) {
        String containingFileName = elementToProduceDescription.getContainingFile().getName();
        XQueryFile xqueryFile = (XQueryFile) functionName.getContainingFile();
        String prefix = functionName.getPrefix() != null ? functionName.getPrefix().getText() : null;
        String mappedNamespace = xqueryFile.mapFunctionPrefixToNamespace(prefix);
        String namespace = removeQuotOrAposIfNeeded(mappedNamespace != null ? mappedNamespace : NULL_NS_URI);
        String description = getDescription(elementToProduceDescription);
        String xqDocDescription = XQDocDescriptionExtractor.getXQDocDescription(elementToProduceDescription);

        return new CommentAndSignatureBasedDocumentation(containingFileName, containingFileName, namespace,
                description, xqDocDescription);
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
}

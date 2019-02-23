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

package org.intellij.xquery.documentation;

import com.intellij.psi.PsiElement;
import org.intellij.xquery.psi.XQueryAnnotation;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionInvocation;
import org.intellij.xquery.psi.XQueryFunctionName;

import static com.intellij.psi.util.PsiTreeUtil.getParentOfType;
import static javax.xml.XMLConstants.DEFAULT_NS_PREFIX;
import static org.intellij.xquery.documentation.DocumentationStylist.FUNCTION_END;
import static org.intellij.xquery.documentation.DocumentationStylist.FUNCTION_START;
import static org.intellij.xquery.documentation.DocumentationStylist.wrapWithHtmlAndStyle;
import static org.intellij.xquery.util.StringUtils.compressWhitespaces;
import static org.intellij.xquery.util.StringUtils.removeQuotOrAposIfNeeded;

public class FunctionDocumentationProvider implements PsiBasedDocumentationProvider<XQueryFunctionName> {

    @Override
    public String generateDoc(XQueryFunctionName functionName) {
        XQueryFunctionDecl elementToProduceDescription = getElementToProduceDescription(functionName);
        if (elementToProduceDescription != null) {
            return wrapWithHtmlAndStyle(getDocFromFunctionDeclaration(functionName, elementToProduceDescription).getText());
        } else {
            XQueryFile file = (XQueryFile) functionName.getContainingFile();
            if (file.isBuiltInFunction(functionName)) {
                return getDocumentationFromExternalFile(functionName);
            } else {
                return null;
            }
        }
    }

    private String getDocumentationFromExternalFile(XQueryFunctionName functionName) {
        String name = functionName.getLocalNameText();
        String doc = ExternalDocumentationFetcher.fetch(functionName.getProject(), name);
        if (doc != null)
            return wrapWithHtmlAndStyle(doc);
        else
            return null;
    }

    private CommentAndSignatureBasedDocumentation getDocFromFunctionDeclaration(XQueryFunctionName
                                                                                        functionName,
                                                                                XQueryFunctionDecl
                                                                                        elementToProduceDescription) {
        String containingFileName = elementToProduceDescription.getContainingFile().getName();
        XQueryFile xqueryFile = (XQueryFile) functionName.getContainingFile();
        String prefix = functionName.getPrefix() != null ? functionName.getPrefix().getText() : null;
        String mappedNamespace = xqueryFile.mapFunctionPrefixToNamespace(prefix);
        String namespace = removeQuotOrAposIfNeeded(mappedNamespace != null ? mappedNamespace : DEFAULT_NS_PREFIX);
        String description = getSignature(elementToProduceDescription);
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

    private String getSignature(XQueryFunctionDecl fun) {
        StringBuilder sb = new StringBuilder();
        sb.append("declare ");
        sb.append(getAnnotations(fun));
        sb.append(" function ");
        sb.append(FUNCTION_START);
        sb.append(fun.getFunctionName().getText());
        sb.append(FUNCTION_END);
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

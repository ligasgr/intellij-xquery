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
import org.intellij.xquery.psi.XQueryCaseClause;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryForBinding;
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
import org.intellij.xquery.util.StringUtils;

import static com.intellij.psi.util.PsiTreeUtil.getParentOfType;
import static javax.xml.XMLConstants.DEFAULT_NS_PREFIX;
import static org.intellij.xquery.documentation.DocumentationStylist.wrapWithHtmlAndStyle;
import static org.intellij.xquery.util.StringUtils.normalizeWhitespaces;
import static org.intellij.xquery.util.StringUtils.removeQuotOrAposIfNeeded;

/**
 * User: ligasgr
 * Date: 29/12/13
 * Time: 09:25
 */
public class VariableDocumentationProvider implements PsiBasedDocumentationProvider<XQueryVarName> {
    @Override
    public String generateDoc(XQueryVarName varName) {
        PsiElement elementToProduceDescription = getElementToProduceDescription(varName);
        if (elementToProduceDescription != null) {
            return wrapWithHtmlAndStyle(getDocFromVariableSource(varName, elementToProduceDescription).getText());
        } else {
            return null;
        }
    }

    private CommentAndSignatureBasedDocumentation getDocFromVariableSource(XQueryVarName varName,
                                                                           PsiElement elementToProduceDescription) {
        String containingFileName = elementToProduceDescription.getContainingFile().getName();
        XQueryFile xqueryFile = (XQueryFile) varName.getContainingFile();
        String prefix = varName.getPrefix() != null ? varName.getPrefix().getText() : null;
        String mappedNamespace = xqueryFile.mapVariablePrefixToNamespace(prefix);
        String namespace = removeQuotOrAposIfNeeded(mappedNamespace != null ? mappedNamespace : DEFAULT_NS_PREFIX);
        String description = getDescription(elementToProduceDescription);
        String xqDocDescription = XQDocDescriptionExtractor.getXQDocDescription(elementToProduceDescription);

        return new CommentAndSignatureBasedDocumentation(containingFileName, containingFileName, namespace, description, xqDocDescription);
    }

    private String getDescription(PsiElement elementToProduceDescription) {
        String descriptionSuffix = StringUtils.stripSeparator(elementToProduceDescription.getText());
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
}

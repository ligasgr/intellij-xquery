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

package org.intellij.xquery.inspection;

import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.xquery.psi.XQueryArgument;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionCall;
import org.intellij.xquery.psi.XQueryFunctionInvocation;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.intellij.xquery.psi.XQueryPrefix;
import org.intellij.xquery.psi.XQueryStringLiteral;
import org.intellij.xquery.psi.XQueryTypes;
import org.intellij.xquery.psi.impl.XQueryPsiImplUtil;
import org.intellij.xquery.reference.namespace.XQuery30PredeclaredNamespaces;

import java.util.HashSet;
import java.util.Set;

public class QNameConstructorNamespacesExtractor {

    public Set<String> getNamespacesUsedByQNameConstructors(XQueryFile xQueryFile) {
        Set<String> usedNamespaces = new HashSet<String>();
        for (XQueryFunctionInvocation functionInvocation : xQueryFile.getFunctionInvocations()) {
            if (functionInvocation instanceof XQueryFunctionCall) {
                XQueryFunctionCall functionCall = (XQueryFunctionCall) functionInvocation;

                if (isFunctionInvocationXsQName(functionCall, xQueryFile)) {
                    XQueryArgument queryArgument = functionCall.getArgumentList().getArgumentList().get(0);

                    // xs:QName requires an xs:string literal as the argument.
                    XQueryStringLiteral stringLiteral = PsiTreeUtil.findChildOfType(queryArgument, XQueryStringLiteral.class);
                    if (stringLiteral != null) {
                        String arg = XQueryPsiImplUtil.stripApostrophes(stringLiteral.getText());
                        String[] split = arg.split(XQueryTypes.COLON.toString(), 2);
                        if (split.length == 2) {
                            usedNamespaces.add(xQueryFile.mapFunctionPrefixToNamespace(split[0]));
                        }
                    }
                }
            }
        }

        return usedNamespaces;
    }

    protected boolean isFunctionInvocationXsQName(XQueryFunctionCall functionCall, XQueryFile xQueryFile) {
        if (functionCall.getArity() == 1) {
            XQueryFunctionName functionName = functionCall.getFunctionName();
            if ("QName".equals(functionName.getLocalNameText())) {
                XQueryPrefix namespacePrefix = functionName.getPrefix();
                String namespacePrefixText = namespacePrefix != null ? namespacePrefix.getText() : null;
                String functionNamespace = xQueryFile.mapFunctionPrefixToNamespace(namespacePrefixText);

                return XQuery30PredeclaredNamespaces.XS.getNamespace().equals(functionNamespace);
            }
        }

        return false;
    }
}

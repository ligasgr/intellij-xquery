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

package org.intellij.xquery.annotator.duplicateFunction;

import com.intellij.openapi.util.Condition;
import com.intellij.psi.PsiElement;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionName;

import java.util.Collection;

import static com.intellij.util.containers.ContainerUtil.filter;

public class Verifier {

    public boolean hasDuplicateDeclaration(XQueryFunctionName functionName, XQueryFile file) {
        boolean result = false;

        if (functionName.getParent() instanceof XQueryFunctionDecl) {
            final XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) functionName.getParent();
            Collection<XQueryFunctionDecl> filteredDeclarations = filter(file.getFunctionDeclarations(), new Condition<XQueryFunctionDecl>() {
                @Override
                public boolean value(XQueryFunctionDecl otherFunctionDeclaration) {
                    return functionDeclaration != otherFunctionDeclaration;
                }
            });
            for (XQueryFunctionDecl otherFunctionDeclaration : filteredDeclarations) {
                if (textIsTheSame(functionName, otherFunctionDeclaration) && arityIsTheSame(functionName, otherFunctionDeclaration)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    private boolean textIsTheSame(XQueryFunctionName functionName, XQueryFunctionDecl otherFunctionDeclaration) {
        XQueryFunctionName otherFunctionName = otherFunctionDeclaration.getFunctionName();
        return functionName.getText().equals(otherFunctionName != null ? otherFunctionName.getText() : null);
    }

    private boolean arityIsTheSame(XQueryFunctionName functionName, XQueryFunctionDecl otherFunctionDeclaration) {
        PsiElement parent = functionName.getParent();
        return otherFunctionDeclaration.getArity() == ((XQueryFunctionDecl) parent).getArity();
    }
}

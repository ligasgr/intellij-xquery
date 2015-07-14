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

package org.intellij.xquery.inspection.namespace;

import com.intellij.codeInspection.InspectionManager;
import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.psi.PsiFile;
import org.apache.commons.lang.StringUtils;
import org.intellij.xquery.psi.XQueryDefaultFunctionNamespaceDecl;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryModuleDecl;
import org.intellij.xquery.psi.XQueryURILiteral;

import static com.intellij.codeInspection.ProblemHighlightType.GENERIC_ERROR_OR_WARNING;
import static org.intellij.xquery.reference.namespace.XQuery30PredeclaredNamespaces.FN;
import static org.intellij.xquery.util.StringUtils.removeQuotOrAposIfNeeded;

public class DefaultFunctionNamespaceSameAsModuleNamespace extends LocalInspectionTool {

    @Override
    public ProblemDescriptor[] checkFile(PsiFile file, InspectionManager manager, boolean isOnTheFly) {
        if (!(file instanceof XQueryFile)) {
            return null;
        }
        XQueryFile xQueryFile = (XQueryFile) file;
        XQueryModuleDecl moduleDeclaration = xQueryFile.getModuleDeclaration();
        XQueryDefaultFunctionNamespaceDecl defaultNamespaceFunctionDeclaration = xQueryFile.getDefaultNamespaceFunctionDeclaration();

        if (moduleDeclaration != null && defaultNamespaceFunctionDeclaration != null) {
            String namespace = moduleDeclaration.getNamespace();
            XQueryURILiteral uriLiteral = defaultNamespaceFunctionDeclaration.getURILiteral();
            String defaultFunctionNamespace = uriLiteral != null ? removeQuotOrAposIfNeeded(uriLiteral.getText()) : null;
            if (!StringUtils.equals(defaultFunctionNamespace, namespace) && !FN.getNamespace().equals(defaultFunctionNamespace)) {
                return createProblemDescriptor(manager, uriLiteral);
            }
        }

        return null;
    }

    private ProblemDescriptor[] createProblemDescriptor(InspectionManager manager, XQueryURILiteral defaultNamespaceFunctionDeclarationURILiteral) {
        ProblemDescriptor[] problemDescriptors = new ProblemDescriptor[1];
        problemDescriptors[0] = manager.createProblemDescriptor(defaultNamespaceFunctionDeclarationURILiteral, "Default function namespace should be same as module namespace", (LocalQuickFix) null,
                GENERIC_ERROR_OR_WARNING, true);

        return problemDescriptors;
    }

}

/*
 * Copyright 2013-2014 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryModuleDecl;
import org.intellij.xquery.psi.XQueryNamespacePrefix;
import org.intellij.xquery.psi.XQueryURILiteral;

import static com.intellij.codeInspection.ProblemHighlightType.GENERIC_ERROR_OR_WARNING;
import static org.apache.commons.lang.StringUtils.uncapitalize;
import static org.intellij.xquery.util.StringUtils.removeQuotOrAposIfNeeded;

public class NamespacePrefixFromFileName extends LocalInspectionTool {

    @Override
    public ProblemDescriptor[] checkFile(PsiFile file, InspectionManager manager, boolean isOnTheFly) {
        if (!(file instanceof XQueryFile)) {
            return null;
        }

        final XQueryModuleDecl moduleDeclaration = ((XQueryFile) file).getModuleDeclaration();

        if (moduleDeclaration != null && moduleDeclaration.getNamespacePrefix() != null
                && moduleDeclaration.getURILiteral() != null
                && !namespacePrefixIsDerivedFromFileName(moduleDeclaration)) {
            ProblemDescriptor[] problemDescriptors = new ProblemDescriptor[1];
            problemDescriptors[0] = manager.createProblemDescriptor(moduleDeclaration.getNamespacePrefix(), "Namespace prefix should be derived from file name part in URI", (LocalQuickFix) null,
                    GENERIC_ERROR_OR_WARNING, true);

            return problemDescriptors;
        } else {
            return null;
        }
    }

    private boolean namespacePrefixIsDerivedFromFileName(XQueryModuleDecl moduleDeclaration) {
        final XQueryNamespacePrefix namespacePrefix = moduleDeclaration.getNamespacePrefix();
        final XQueryURILiteral uriLiteral = moduleDeclaration.getURILiteral();

        final String prefix = uncapitalize(namespacePrefix.getText());
        final String filename = uncapitalize(filenameFromNamespaceURI(removeQuotOrAposIfNeeded(uriLiteral.getText())));

        if (filename == null || prefix == null) {
            return true;
        } else {
            return StringUtils.equals(prefix, filename);
        }
    }

    private static String filenameFromNamespaceURI(String namespaceURI) {
        final int lastSlash = namespaceURI.lastIndexOf("/");
        if (lastSlash == -1) {
            return null;
        }

        final String fileName = namespaceURI.substring(lastSlash + 1);
        final int lastDot = fileName.lastIndexOf(".");

        if (lastDot == -1) {
            return fileName;
        } else {
            return fileName.substring(0, lastDot);
        }
    }
}

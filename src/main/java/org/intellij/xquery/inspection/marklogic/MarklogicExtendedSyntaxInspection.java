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

package org.intellij.xquery.inspection.marklogic;

import com.intellij.codeInspection.InspectionManager;
import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.xquery.XQueryFlavour;
import org.intellij.xquery.model.XQueryLanguageVersion;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryMarklogicAnnotation;
import org.intellij.xquery.psi.XQueryMarklogicBinaryTest;
import org.intellij.xquery.psi.XQueryMarklogicCatchErrorList;
import org.intellij.xquery.psi.XQueryMarklogicCompBinaryConstructor;
import org.intellij.xquery.psi.XQueryMarklogicNamespaceAxis;
import org.intellij.xquery.psi.XQueryVersion;
import org.intellij.xquery.psi.XQueryVersionDecl;
import org.intellij.xquery.settings.XQuerySettings;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.intellij.xquery.model.XQueryLanguageVersion.V0_9_ML;
import static org.intellij.xquery.model.XQueryLanguageVersion.V1_0_ML;

public class MarklogicExtendedSyntaxInspection extends LocalInspectionTool {

    @Override
    public ProblemDescriptor[] checkFile(@NotNull PsiFile file, @NotNull InspectionManager manager, boolean isOnTheFly) {
        if (!(file instanceof XQueryFile)) {
            return null;
        }

        XQueryVersionDecl versionDecl = PsiTreeUtil.findChildOfType(file, XQueryVersionDecl.class);
        XQueryVersion version = versionDecl != null ? versionDecl.getVersion() : null;
        boolean versionIsNotMarklogicSpecific = true;
        if (version != null) {
            String versionString = version.getVersionString();
            XQueryLanguageVersion languageVersion = XQueryLanguageVersion.valueFor(versionString);
            if (V1_0_ML == languageVersion || V0_9_ML == languageVersion) {
                versionIsNotMarklogicSpecific = false;
            }
        } else if (XQueryFlavour.MARKLOGIC == XQuerySettings.getInstance(file.getProject()).getFlavour()) {
            versionIsNotMarklogicSpecific = false;
        }
        if (versionIsNotMarklogicSpecific) {
            return privateUsedInInvalidPlaces(file, manager);
        }
        return null;
    }

    private ProblemDescriptor[] privateUsedInInvalidPlaces(PsiFile file, InspectionManager manager) {
        Collection<XQueryMarklogicAnnotation> marklogicAnnotations =
                PsiTreeUtil.findChildrenOfType(file, XQueryMarklogicAnnotation.class);
        Collection<XQueryMarklogicCatchErrorList> marklogicCatchErrorLists =
                PsiTreeUtil.findChildrenOfType(file, XQueryMarklogicCatchErrorList.class);
        Collection<XQueryMarklogicNamespaceAxis> marklogicNamespaceAxes =
                PsiTreeUtil.findChildrenOfType(file, XQueryMarklogicNamespaceAxis.class);
        Collection<XQueryMarklogicBinaryTest> marklogicBinaryTests =
                PsiTreeUtil.findChildrenOfType(file, XQueryMarklogicBinaryTest.class);
        Collection<XQueryMarklogicCompBinaryConstructor> marklogicBinaryConstructors =
                PsiTreeUtil.findChildrenOfType(file, XQueryMarklogicCompBinaryConstructor.class);
        int size = marklogicAnnotations.size() + marklogicCatchErrorLists.size() + marklogicNamespaceAxes.size();
        List<ProblemDescriptor> problems = new ArrayList<ProblemDescriptor>(size);
        for (XQueryMarklogicAnnotation element : marklogicAnnotations) {
            problems.add(createProblem(manager, element));
        }
        for (XQueryMarklogicCatchErrorList element : marklogicCatchErrorLists) {
            problems.add(createProblem(manager, element));
        }
        for (XQueryMarklogicNamespaceAxis element : marklogicNamespaceAxes) {
            problems.add(createProblem(manager, element));
        }
        for (XQueryMarklogicBinaryTest element : marklogicBinaryTests) {
            problems.add(createProblem(manager, element));
        }
        for (XQueryMarklogicCompBinaryConstructor element : marklogicBinaryConstructors) {
            problems.add(createProblem(manager, element));
        }
        return problems.toArray(new ProblemDescriptor[problems.size()]);
    }

    private ProblemDescriptor createProblem(InspectionManager manager, PsiElement element) {
        return manager.createProblemDescriptor(element,
                "MarkLogic extended syntax used when version is not set to 'MarkLogic extended'.", (LocalQuickFix) null,
                ProblemHighlightType.GENERIC_ERROR, true);
    }
}

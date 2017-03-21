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

package org.intellij.xquery.inspection.marklogic;

import com.intellij.codeInspection.InspectionManager;
import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.intellij.xquery.psi.XQueryCatchClauseExpression;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryMarklogicAnnotation;
import org.intellij.xquery.psi.XQueryMarklogicAnyKindTest;
import org.intellij.xquery.psi.XQueryMarklogicArrayNodeTest;
import org.intellij.xquery.psi.XQueryMarklogicBinaryTest;
import org.intellij.xquery.psi.XQueryMarklogicBooleanNodeTest;
import org.intellij.xquery.psi.XQueryMarklogicCatchErrorList;
import org.intellij.xquery.psi.XQueryMarklogicCompArrayNodeConstructor;
import org.intellij.xquery.psi.XQueryMarklogicCompBinaryConstructor;
import org.intellij.xquery.psi.XQueryMarklogicCompBooleanNodeConstructor;
import org.intellij.xquery.psi.XQueryMarklogicCompNullNodeConstructor;
import org.intellij.xquery.psi.XQueryMarklogicCompNumberNodeConstructor;
import org.intellij.xquery.psi.XQueryMarklogicCompObjectNodeConstructor;
import org.intellij.xquery.psi.XQueryMarklogicNamespaceAxis;
import org.intellij.xquery.psi.XQueryMarklogicNullNodeTest;
import org.intellij.xquery.psi.XQueryMarklogicNumberNodeTest;
import org.intellij.xquery.psi.XQueryMarklogicObjectNodeTest;
import org.intellij.xquery.psi.XQueryMarklogicTextTest;
import org.intellij.xquery.psi.XQueryMarklogicValidation;
import org.intellij.xquery.psi.XQueryPsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.intellij.psi.util.PsiTreeUtil.findChildrenOfType;

public class MarklogicExtendedSyntaxInspection extends LocalInspectionTool {

    @Override
    public ProblemDescriptor[] checkFile(@NotNull PsiFile file, @NotNull InspectionManager manager, boolean isOnTheFly) {
        if (!(file instanceof XQueryFile)) {
            return null;
        }

        if (((XQueryFile) file).versionIsNotMarklogicSpecific()) {
            return findMarklogicExtendedSyntax(((XQueryFile) file), manager);
        }
        return null;
    }

    private ProblemDescriptor[] findMarklogicExtendedSyntax(XQueryFile file, InspectionManager manager) {
        Collection<PsiElement> marklogicExtendedSyntaxElements = new ArrayList<PsiElement>();
        addAllMarkLogicSpecifics(file, marklogicExtendedSyntaxElements, XQueryMarklogicAnnotation.class);
        addAllMarkLogicSpecifics(file, marklogicExtendedSyntaxElements, XQueryMarklogicCatchErrorList.class);
        addAllMarkLogicSpecifics(file, marklogicExtendedSyntaxElements, XQueryMarklogicNamespaceAxis.class);
        addAllMarkLogicSpecifics(file, marklogicExtendedSyntaxElements, XQueryMarklogicBinaryTest.class);
        addAllMarkLogicSpecifics(file, marklogicExtendedSyntaxElements, XQueryMarklogicCompBinaryConstructor.class);
        addAllMarkLogicSpecifics(file, marklogicExtendedSyntaxElements, XQueryMarklogicValidation.class);
        addAllMarkLogicSpecifics(file, marklogicExtendedSyntaxElements, XQueryMarklogicCompObjectNodeConstructor.class);
        addAllMarkLogicSpecifics(file, marklogicExtendedSyntaxElements, XQueryMarklogicCompNumberNodeConstructor.class);
        addAllMarkLogicSpecifics(file, marklogicExtendedSyntaxElements, XQueryMarklogicCompBooleanNodeConstructor.class);
        addAllMarkLogicSpecifics(file, marklogicExtendedSyntaxElements, XQueryMarklogicCompNullNodeConstructor.class);
        addAllMarkLogicSpecifics(file, marklogicExtendedSyntaxElements, XQueryMarklogicCompArrayNodeConstructor.class);
        addAllMarkLogicSpecifics(file, marklogicExtendedSyntaxElements, XQueryMarklogicTextTest.class);
        addAllMarkLogicSpecifics(file, marklogicExtendedSyntaxElements, XQueryMarklogicAnyKindTest.class);
        addAllMarkLogicSpecifics(file, marklogicExtendedSyntaxElements, XQueryMarklogicObjectNodeTest.class);
        addAllMarkLogicSpecifics(file, marklogicExtendedSyntaxElements, XQueryMarklogicNumberNodeTest.class);
        addAllMarkLogicSpecifics(file, marklogicExtendedSyntaxElements, XQueryMarklogicBooleanNodeTest.class);
        addAllMarkLogicSpecifics(file, marklogicExtendedSyntaxElements, XQueryMarklogicNullNodeTest.class);
        addAllMarkLogicSpecifics(file, marklogicExtendedSyntaxElements, XQueryMarklogicArrayNodeTest.class);
        List<ProblemDescriptor> problems = new ArrayList<ProblemDescriptor>(marklogicExtendedSyntaxElements.size());
        for (PsiElement element : marklogicExtendedSyntaxElements) {
            problems.add(createProblem(manager, element));
        }

        if (!file.versionIs31()) {
            Collection<XQueryCatchClauseExpression> marklogicCatchClause =
                    findChildrenOfType(file, XQueryCatchClauseExpression.class);

            for (XQueryCatchClauseExpression element : marklogicCatchClause) {
                if (element.getEnclosedExpression().getExpr() == null) {
                    problems.add(createProblem(manager, element));
                }
            }
        }
        return problems.toArray(new ProblemDescriptor[problems.size()]);
    }

    private boolean addAllMarkLogicSpecifics(PsiFile file, Collection<PsiElement> marklogicExtendedSyntaxElements,
                                             Class<? extends XQueryPsiElement> aClass) {
        return marklogicExtendedSyntaxElements.addAll(findChildrenOfType(file, aClass));
    }

    private ProblemDescriptor createProblem(InspectionManager manager, PsiElement element) {
        return manager.createProblemDescriptor(element,
                "MarkLogic extended syntax used when version is not set to 'MarkLogic extended'.", (LocalQuickFix) null,
                ProblemHighlightType.GENERIC_ERROR, true);
    }
}

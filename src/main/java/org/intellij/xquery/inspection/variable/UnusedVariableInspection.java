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

package org.intellij.xquery.inspection.variable;

import com.intellij.codeInspection.InspectionManager;
import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.psi.PsiFile;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryParam;
import org.intellij.xquery.psi.XQueryVarDecl;
import org.intellij.xquery.psi.XQueryVarName;
import org.intellij.xquery.psi.XQueryVarRef;

import java.util.ArrayList;
import java.util.List;

import static com.intellij.codeInspection.ProblemHighlightType.LIKE_UNUSED_SYMBOL;
import static org.intellij.xquery.psi.impl.XQueryPsiImplUtil.isPublic;

public class UnusedVariableInspection extends LocalInspectionTool {


    @Override
    public ProblemDescriptor[] checkFile(PsiFile file, InspectionManager manager, boolean isOnTheFly) {
        if (!(file instanceof XQueryFile)) {
            return null;
        }

        List<XQueryVarName> unusedVariables = new ArrayList<XQueryVarName>();

        XQueryFile xQueryFile = (XQueryFile) file;
        for (XQueryVarName varName : xQueryFile.getVariableNames()) {
            if (isReference(varName) || isPublicDeclaredVariable(varName)) continue;
            if (variableIsNotUsed(varName, xQueryFile))
                unusedVariables.add(varName);
        }

        if (unusedVariables.size() > 0) {
            return buildProblemDescriptionsForUnusedVariables(manager, unusedVariables);
        } else {
            return null;
        }

    }

    private boolean isPublicDeclaredVariable(XQueryVarName varName) {
        if (varName.getParent() instanceof XQueryVarDecl && ((XQueryVarDecl) varName.getParent()).isPublic()) {
            return true;
        }
        return false;
    }

    private boolean isReference(XQueryVarName varName) {
        return varName.getParent() instanceof XQueryVarRef;
    }

    private boolean variableIsNotUsed(XQueryVarName varName, XQueryFile xQueryFile) {
        boolean used = false;
        for (XQueryVarRef varRef : xQueryFile.getVariableReferences()) {
            if (varRef.getReference() != null && varRef.getReference().isReferenceTo(varName)) {
                used = true;
            }
        }
        return !used;
    }

    private ProblemDescriptor[] buildProblemDescriptionsForUnusedVariables(InspectionManager manager, List<XQueryVarName> unusedVariables) {
        ProblemDescriptor[] problemDescriptors = new ProblemDescriptor[unusedVariables.size()];
        int ind = 0;
        for (XQueryVarName varName : unusedVariables) {
            final ProblemDescriptor problemDescriptor = manager.createProblemDescriptor(varName, getDescription(varName), (LocalQuickFix) null,
                    LIKE_UNUSED_SYMBOL, true);
            problemDescriptors[ind++] = problemDescriptor;
        }
        return problemDescriptors;
    }

    private String getDescription(XQueryVarName varName) {
        String variableType = varName.getParent() instanceof XQueryParam ? "Parameter": "Variable";
        return String.format("%s '#ref' is never used", variableType);
    }

}

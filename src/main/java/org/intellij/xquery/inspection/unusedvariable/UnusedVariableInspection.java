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

package org.intellij.xquery.inspection.unusedvariable;

import com.intellij.codeInspection.InspectionManager;
import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryVarName;

import java.util.ArrayList;
import java.util.List;

import static com.intellij.codeInspection.ProblemHighlightType.LIKE_UNUSED_SYMBOL;

public class UnusedVariableInspection extends LocalInspectionTool {


    @Override
    public ProblemDescriptor[] checkFile(PsiFile file, InspectionManager manager, boolean isOnTheFly) {
        if (!(file instanceof XQueryFile)) {
            return null;
        }

        List<XQueryVarName> unusedVariables = new ArrayList<XQueryVarName>();

        XQueryFile xQueryFile = (XQueryFile) file;
        for (XQueryFunctionDecl functionDecl : xQueryFile.getFunctionDeclarations()) {
            UnusedVariableFinder unusedVariableFinder = new UnusedVariableFinder(functionDecl);
            List<XQueryVarName> functionUnusedVariables = unusedVariableFinder.findUnusedVariables();
            unusedVariables.addAll(functionUnusedVariables);
        }

        if (unusedVariables.size() > 0) {
            return buildProblemDescriptionsForUnsusedVariables(manager, unusedVariables);
        } else {
            return null;
        }

    }

    private ProblemDescriptor[] buildProblemDescriptionsForUnsusedVariables(InspectionManager manager, List<XQueryVarName> unusedVariables) {
        ProblemDescriptor[] problemDescriptors = new ProblemDescriptor[unusedVariables.size()];
        int ind = 0;
        for (PsiElement element : unusedVariables) {
            final ProblemDescriptor problemDescriptor = manager.createProblemDescriptor(element, "Unused variable", (LocalQuickFix) null,
                    LIKE_UNUSED_SYMBOL, true);
            problemDescriptors[ind++] = problemDescriptor;
        }
        return problemDescriptors;
    }

}

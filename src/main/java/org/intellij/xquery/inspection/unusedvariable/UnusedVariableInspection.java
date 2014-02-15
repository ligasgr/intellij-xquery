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
import com.intellij.psi.PsiFile;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryLetBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.intellij.codeInspection.ProblemHighlightType.LIKE_UNUSED_SYMBOL;

public class UnusedVariableInspection extends LocalInspectionTool {


    @Override
    public ProblemDescriptor[] checkFile(PsiFile file, InspectionManager manager, boolean isOnTheFly) {
        if (!(file instanceof XQueryFile)) {
            return null;
        }

        List<XQueryLetBinding> unusedBingings = new ArrayList<XQueryLetBinding>();

        XQueryFile xQueryFile = (XQueryFile) file;
        final Collection<XQueryFunctionDecl> functionDeclarations = xQueryFile.getFunctionDeclarations();
        for (XQueryFunctionDecl functionDecl : functionDeclarations) {
            UnusedVariableFinder unusedVariableFinder = new UnusedVariableFinder(functionDecl);
            final List<XQueryLetBinding> unusedVariables = unusedVariableFinder.findUnusedVariables();
            unusedBingings.addAll(unusedVariables);
        }

        if (unusedBingings.size() > 0) {
            ProblemDescriptor[] problemDescriptors = new ProblemDescriptor[unusedBingings.size()];
            int ind = 0;
            for (XQueryLetBinding binding : unusedBingings) {
                final ProblemDescriptor problemDescriptor = manager.createProblemDescriptor(binding, "Unused variable", (LocalQuickFix) null,
                        LIKE_UNUSED_SYMBOL, true);
                problemDescriptors[ind++] = problemDescriptor;
            }
            return problemDescriptors;
        } else {
            return null;
        }

    }

}

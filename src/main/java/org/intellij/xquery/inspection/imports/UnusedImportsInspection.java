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

package org.intellij.xquery.inspection.imports;

import com.intellij.codeInspection.HintAction;
import com.intellij.codeInspection.InspectionManager;
import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.psi.PsiFile;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryModuleImport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.intellij.codeInspection.ProblemHighlightType.LIKE_UNUSED_SYMBOL;

public class UnusedImportsInspection extends LocalInspectionTool {

    private static final HintAction NO_HINT_ACTION = null;
    private FunctionNamespacesExtractor functionNamespacesExtractor = new FunctionNamespacesExtractor();
    private VariableNamespacesExtractor variableNamespacesExtractor = new VariableNamespacesExtractor();
    private final UnusedImportsFinder unusedImportsFinder = new UnusedImportsFinder(functionNamespacesExtractor,
            variableNamespacesExtractor);

    @Override
    public ProblemDescriptor[] checkFile(PsiFile file, InspectionManager manager, boolean isOnTheFly) {
        if (!(file instanceof XQueryFile)) {
            return null;
        }
        List<ProblemDescriptor> problems = getUnusedImportProblems((XQueryFile) file, manager);
        return problems.toArray(new ProblemDescriptor[problems.size()]);
    }

    private List<ProblemDescriptor> getUnusedImportProblems(XQueryFile xQueryFile, InspectionManager manager) {
        Collection<XQueryModuleImport> unusedImports = unusedImportsFinder.getUnusedImports(xQueryFile);
        List<ProblemDescriptor> problems = new ArrayList<ProblemDescriptor>();
        for (XQueryModuleImport unused : unusedImports) {
            problems.add(manager.createProblemDescriptor(unused, "Unused import", LIKE_UNUSED_SYMBOL,
                    NO_HINT_ACTION, true));
        }

        return problems;
    }
}

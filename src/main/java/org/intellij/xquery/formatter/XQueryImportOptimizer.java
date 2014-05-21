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

package org.intellij.xquery.formatter;

import com.intellij.lang.ImportOptimizer;
import com.intellij.psi.PsiFile;
import org.intellij.xquery.inspection.imports.FunctionNamespacesExtractor;
import org.intellij.xquery.inspection.imports.UnusedImportsFinder;
import org.intellij.xquery.inspection.imports.VariableNamespacesExtractor;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryModuleImport;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class XQueryImportOptimizer implements ImportOptimizer {
    @Override
    public boolean supports(PsiFile psiFile) {
        return psiFile instanceof XQueryFile;
    }

    @NotNull
    @Override
    public Runnable processFile(final PsiFile psiFile) {
        FunctionNamespacesExtractor functionNamespacesExtractor = new FunctionNamespacesExtractor();
        VariableNamespacesExtractor variableNamespacesExtractor = new VariableNamespacesExtractor();
        UnusedImportsFinder unusedImportsFinder = new UnusedImportsFinder(functionNamespacesExtractor, variableNamespacesExtractor);

        Collection<XQueryModuleImport> unusedImports = unusedImportsFinder.getUnusedImports((XQueryFile) psiFile);

        return new RemoveUnusedImport(unusedImports);
    }

    private static class RemoveUnusedImport implements Runnable {

        private final Collection<XQueryModuleImport> unusedImports;

        private RemoveUnusedImport(Collection<XQueryModuleImport> unusedImports) {
            this.unusedImports = unusedImports;
        }

        @Override
        public void run() {
            for (XQueryModuleImport unusedImport : unusedImports) {
                unusedImport.delete();
            }
        }
    }
}

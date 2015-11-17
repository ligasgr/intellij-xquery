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

package org.intellij.xquery.formatter;

import com.intellij.lang.ImportOptimizer;
import com.intellij.psi.PsiFile;
import org.intellij.xquery.inspection.imports.UnusedNamespaceSourceFinder;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryModuleImport;
import org.intellij.xquery.psi.XQueryNamespaceDecl;
import org.intellij.xquery.psi.XQueryNamespaceSource;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class XQueryImportOptimizer implements ImportOptimizer {

    private final UnusedNamespaceSourceFinder<XQueryModuleImport> unusedImportsFinder = new UnusedNamespaceSourceFinder<XQueryModuleImport>() {
        @NotNull
        @Override
        protected Collection<XQueryModuleImport> getAllNamespaceSources(XQueryFile xQueryFile) {
            return xQueryFile.getModuleImports();
        }
    };
    private final UnusedNamespaceSourceFinder<XQueryNamespaceDecl> unusedNamespaceDeclarationsFinder = new UnusedNamespaceSourceFinder<XQueryNamespaceDecl>() {
        @NotNull
        @Override
        protected Collection<XQueryNamespaceDecl> getAllNamespaceSources(XQueryFile xQueryFile) {
            return xQueryFile.getNamespaceDeclarations();
        }
    };

    @Override
    public boolean supports(PsiFile psiFile) {
        return psiFile instanceof XQueryFile;
    }

    @NotNull
    @Override
    public Runnable processFile(final PsiFile psiFile) {
        Collection<XQueryModuleImport> unusedImports = unusedImportsFinder.getUnusedNamespaceSources((XQueryFile) psiFile);
        Collection<XQueryNamespaceDecl> unusedNamespaceDeclarations = unusedNamespaceDeclarationsFinder.getUnusedNamespaceSources((XQueryFile) psiFile);
        Collection<XQueryNamespaceSource> unusedNamespaceSources = new ArrayList<XQueryNamespaceSource>(unusedImports.size() + unusedNamespaceDeclarations.size());
        unusedNamespaceSources.addAll(unusedImports);
        unusedNamespaceSources.addAll(unusedNamespaceDeclarations);
        return new RemoveUnusedImport(unusedNamespaceSources);
    }

    private static class RemoveUnusedImport implements Runnable {

        private final Collection<XQueryNamespaceSource> unusedNamespaceSources;

        private RemoveUnusedImport(Collection<XQueryNamespaceSource> unusedNamespaceSources) {
            this.unusedNamespaceSources = unusedNamespaceSources;
        }

        @Override
        public void run() {
            for (XQueryNamespaceSource unusedImport : unusedNamespaceSources) {
                unusedImport.delete();
            }
        }
    }
}

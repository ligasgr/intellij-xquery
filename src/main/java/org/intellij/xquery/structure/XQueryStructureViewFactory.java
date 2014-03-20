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

package org.intellij.xquery.structure;

import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryQueryBody;
import org.intellij.xquery.psi.XQueryVarDecl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * User: ligasgr
 * Date: 21/08/13
 * Time: 14:39
 */
public class XQueryStructureViewFactory implements PsiStructureViewFactory {
    @Nullable
    @Override
    public StructureViewBuilder getStructureViewBuilder(final PsiFile psiFile) {
        return new TreeBasedStructureViewBuilder() {
            @NotNull
            @Override
            public com.intellij.ide.structureView.StructureViewModel createStructureViewModel(Editor editor) {
                return new XQueryStructureViewModel(psiFile).withSuitableClasses(
                        XQueryFunctionDecl.class, XQueryVarDecl.class, XQueryQueryBody.class
                );
            }
        };
    }
}

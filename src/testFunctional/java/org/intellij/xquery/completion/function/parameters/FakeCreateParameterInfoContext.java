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

package org.intellij.xquery.completion.function.parameters;

import com.intellij.lang.parameterInfo.CreateParameterInfoContext;
import com.intellij.lang.parameterInfo.ParameterInfoHandler;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

/**
 * User: ligasgr
 * Date: 07/12/13
 * Time: 20:54
 */
public class FakeCreateParameterInfoContext implements CreateParameterInfoContext {
    private Object[] itemsToShow;
    private PsiElement highlightedElement;
    private final Editor editor;
    private final PsiFile file;

    public FakeCreateParameterInfoContext(Editor editor, PsiFile file) {
        this.editor = editor;
        this.file = file;
    }

    @Override
    public Object[] getItemsToShow() {
        return itemsToShow;
    }

    @Override
    public void setItemsToShow(Object[] itemsToShow) {
        this.itemsToShow = itemsToShow;
    }

    @Override
    public void showHint(PsiElement element, int offset, ParameterInfoHandler handler) {
    }

    @Override
    public int getParameterListStart() {
        return editor.getCaretModel().getOffset();
    }

    @Override
    public PsiElement getHighlightedElement() {
        return highlightedElement;
    }

    @Override
    public void setHighlightedElement(PsiElement elements) {
        highlightedElement = elements;
    }

    @Override
    public Project getProject() {
        return file.getProject();
    }

    @Override
    public PsiFile getFile() {
        return file;
    }

    @Override
    public int getOffset() {
        return editor.getCaretModel().getOffset();
    }

    @Override
    @NotNull
    public Editor getEditor() {
        return editor;
    }
}

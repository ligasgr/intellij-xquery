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

package org.intellij.xquery.completion.function.parameters;

import com.intellij.lang.parameterInfo.UpdateParameterInfoContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.UserDataHolderEx;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

/**
 * User: ligasgr
 * Date: 07/12/13
 * Time: 20:54
 */
public class FakeUpdateParameterInfoContext implements UpdateParameterInfoContext {
    private final Editor editor;
    private final PsiFile file;
    private PsiElement parameterOwner;
    private int currentParameter;
    private Object highlightedParameter;
    private boolean preservedOnHintHidden;

    public FakeUpdateParameterInfoContext(Editor editor, PsiFile file) {
        this.editor = editor;
        this.file = file;
    }

    public void removeHint() {
    }

    public void setParameterOwner(PsiElement parameterOwner) {
        this.parameterOwner = parameterOwner;
    }

    public PsiElement getParameterOwner() {
        return parameterOwner;
    }

    public void setHighlightedParameter(Object parameter) {
        this.highlightedParameter = parameter;
    }

    public Object getHighlightedParameter() {
        return highlightedParameter;
    }

    public void setCurrentParameter(int index) {
        currentParameter = index;
    }

    public int getCurrentParameter() {
        return currentParameter;
    }

    public boolean isUIComponentEnabled(int index) {
        return false;
    }

    public void setUIComponentEnabled(int index, boolean enabled) {
    }

    public int getParameterListStart() {
        return editor.getCaretModel().getOffset();
    }

    public Object[] getObjectsToView() {
        return new Object[0];
    }

    public Project getProject() {
        return file.getProject();
    }

    public PsiFile getFile() {
        return file;
    }

    public int getOffset() {
        return editor.getCaretModel().getOffset();
    }

    public Editor getEditor() {
        return editor;
    }

    public boolean isInnermostContext() {
        return false;
    }

    @Override
    public boolean isSingleParameterInfo() {
        return false;
    }

    @Override
    public UserDataHolderEx getCustomContext() {
        return null;
    }

    public boolean isPreservedOnHintHidden() {
        return preservedOnHintHidden;
    }

    public void setPreservedOnHintHidden(boolean preservedOnHintHidden) {
        this.preservedOnHintHidden = preservedOnHintHidden;
    }
}

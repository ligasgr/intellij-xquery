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

package org.intellij.xquery.debugger;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.Processor;
import com.intellij.xdebugger.XDebuggerUtil;
import com.intellij.xdebugger.XSourcePosition;
import com.intellij.xdebugger.breakpoints.XBreakpointProperties;
import com.intellij.xdebugger.breakpoints.XLineBreakpoint;
import com.intellij.xdebugger.breakpoints.XLineBreakpointType;
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProvider;
import org.intellij.xquery.XQueryFileType;
import org.intellij.xquery.psi.XQueryExprSingle;
import org.jetbrains.annotations.NotNull;

public class XQueryBreakpointType extends XLineBreakpointType<XBreakpointProperties> {

    public XQueryBreakpointType() {
        super("XQueryBreakpoint", "XQuery Breakpoints");
    }

    @Override
    public boolean canPutAt(@NotNull VirtualFile file, int line, @NotNull Project project) {
        final Document document = FileDocumentManager.getInstance().getDocument(file);
        if (document == null) return false;
        final PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(document);
        if (psiFile == null) {
            return false;
        }
        final FileType fileType = psiFile.getFileType();
        return fileType == XQueryFileType.INSTANCE && hasAnExpression(project, document, line);
    }

    private boolean hasAnExpression(Project project, Document document, int line) {
        final Ref<Boolean> isPartOfExpression = Ref.create(Boolean.FALSE);
        XDebuggerUtil.getInstance().iterateLine(project, document, line, new Processor<PsiElement>() {
            @Override
            public boolean process(PsiElement psiElement) {
                if (PsiTreeUtil.getParentOfType(psiElement, XQueryExprSingle.class, false) != null) {
                    isPartOfExpression.set(Boolean.TRUE);
                    return false;
                } else {
                    return true;
                }
            }
        });
        return isPartOfExpression.get();
    }

    @Override
    public XBreakpointProperties createBreakpointProperties(@NotNull VirtualFile file, int line) {
        return null;
    }

    @Override
    public XDebuggerEditorsProvider getEditorsProvider(@NotNull XLineBreakpoint<XBreakpointProperties> breakpoint, @NotNull Project project) {
        final XSourcePosition position = breakpoint.getSourcePosition();
        if (position == null) {
            return null;
        }
        final PsiFile file = PsiManager.getInstance(project).findFile(position.getFile());
        if (file == null) {
            return null;
        }
        return new XQueryEditorsProvider();
    }
}

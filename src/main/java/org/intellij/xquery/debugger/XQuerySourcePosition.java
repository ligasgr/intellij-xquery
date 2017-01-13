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

import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.xdebugger.XSourcePosition;
import org.jetbrains.annotations.NotNull;

public class XQuerySourcePosition implements XSourcePosition {
    private final String fileName;
    private final int lineNumber;
    private final Project project;

    public XQuerySourcePosition(String fileName, int lineNumber, Project project) {
        this.fileName = fileName;
        this.lineNumber = lineNumber;
        this.project = project;
    }

    @Override
    public int getLine() {
        return lineNumber - 1;
    }

    @Override
    public int getOffset() {
        return -1;
    }

    @NotNull
    @Override
    public VirtualFile getFile() {
        VirtualFile baseDir = project.getBaseDir();
        String baseDirUrl = unifyNameFormatAndRemoveProtocol(baseDir.getUrl());
        String fileNameInBaseDir = unifyNameFormatAndRemoveProtocol(fileName).replaceFirst(baseDirUrl, "");
        VirtualFile relativePath = baseDir.findFileByRelativePath(fileNameInBaseDir);
        return relativePath;
    }

    private static String unifyNameFormatAndRemoveProtocol(String filename) {
        return filename.replaceFirst("(.*):", "").replaceAll("\\\\", "/").replaceAll("/+", "/");
    }

    @NotNull
    @Override
    public Navigatable createNavigatable(@NotNull Project project) {
        return this.getOffset() != -1?new OpenFileDescriptor(project, this.getFile(), this.getOffset()):new OpenFileDescriptor(project, this.getFile(), this.getLine(), 0);
    }
}

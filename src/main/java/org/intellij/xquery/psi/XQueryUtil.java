/*
 * Copyright 2013-2016 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.psi;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.containers.ContainerUtil;
import org.intellij.xquery.XQueryFileType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * User: ligasgr
 * Date: 08/06/13
 * Time: 21:40
 */
public class XQueryUtil {

    public static List<XQueryFile> findXQueryFileByName(Project project, final String filename,
                                                        PsiFile containingFile) {
        final String name = unifyNameFormatAndRemoveProtocol(filename);
        if (isNotAbsolutePath(name)) {
            PsiFile fileFoundRelatively = getFileByRelativePath(project, filename, containingFile);
            if (fileFoundRelatively != null) {
                List<XQueryFile> filteredList = getOnlyXQueryFiles(asList(fileFoundRelatively));
                if (filteredList.size() > 0) {
                    return filteredList;
                }
            }
        }
        List<String> splitFilename = StringUtil.split(name, "/");
        String lastPartOfTheFilename = ContainerUtil.iterateAndGetLastItem(splitFilename);
        PsiFile[] filesByName = getFilesByName(project, lastPartOfTheFilename);
        List<PsiFile> filesThatEndWithFullName = getOnlyFilesThatEndWithFullName(name, filesByName);

        return getOnlyXQueryFiles(filesThatEndWithFullName);
    }

    private static boolean isNotAbsolutePath(String name) {
        return !name.startsWith("/");
    }

    private static PsiFile getFileByRelativePath(Project project, String filename, PsiFile containingFile) {
        if (!containingFile.isPhysical()) return null;
        VirtualFile containingDirectory = containingFile.getParent().getVirtualFile();
        VirtualFile foundByRelativePath = containingDirectory.findFileByRelativePath(filename);
        if (foundByRelativePath != null) {
            PsiFile found = PsiManager.getInstance(project).findFile(foundByRelativePath);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    private static List<XQueryFile> getOnlyXQueryFiles(List<PsiFile> filesThatEndWithFullName) {
        List<XQueryFile> xqueryFiles = new ArrayList<XQueryFile>();
        for (PsiFile file : filesThatEndWithFullName) {
            if (file instanceof XQueryFile) {
                xqueryFiles.add((XQueryFile) file);
            }
        }
        return xqueryFiles;
    }

    private static List<PsiFile> getOnlyFilesThatEndWithFullName(final String name, PsiFile[] filesByName) {
        return ContainerUtil.filter(filesByName, new Condition<PsiFile>() {
            @Override
            public boolean value(PsiFile psiFile) {
                VirtualFile virtualFile = psiFile.getVirtualFile();
                if (virtualFile == null) return false;
                String canonicalPath = virtualFile.getCanonicalPath();
                if (canonicalPath == null) return false;
                return canonicalPath.endsWith(name);
            }
        });
    }

    private static PsiFile[] getFilesByName(Project project, String lastPartOfTheFilename) {
        return FilenameIndex.getFilesByName(project, lastPartOfTheFilename,
                GlobalSearchScope.getScopeRestrictedByFileTypes(GlobalSearchScope.allScope(project), XQueryFileType
                        .INSTANCE));
    }

    private static String unifyNameFormatAndRemoveProtocol(String filename) {
        return filename.replaceFirst("(.*):", "").replaceAll("\\\\", "/");
    }

    public static Collection<XQueryFile> getReferencesToExistingFilesInImport(XQueryModuleImport moduleImport) {
        Collection<XQueryFile> results = new LinkedList<XQueryFile>();
        XQueryModuleImportNamespace moduleImportNamespace = moduleImport.getModuleImportNamespace();
        if (moduleImportNamespace != null) {
            addFileIfReferencedAndExists(results, moduleImportNamespace.getModuleImportPath());
        }
        for (XQueryModuleImportPath path : moduleImport.getModuleImportPathList()) {
            addFileIfReferencedAndExists(results, path);
        }
        return results;
    }

    private static void addFileIfReferencedAndExists(Collection<XQueryFile> results, XQueryModuleImportPath path) {
        if (path.getReference() != null) {
            XQueryFile xQueryFile = (XQueryFile) path.getReference().resolve();
            if (xQueryFile != null && xQueryFile.getModuleNamespaceName() != null) {
                results.add(xQueryFile);
            }
        }
    }
}
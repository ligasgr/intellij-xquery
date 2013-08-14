/*
 * Copyright 2013 Grzegorz Ligas <ligasgr@gmail.com>
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
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.indexing.FileBasedIndex;
import org.intellij.xquery.XQueryFileType;

import java.util.*;

/**
 * User: ligasgr
 * Date: 08/06/13
 * Time: 21:40
 */
public class XQueryUtil {

    public static List<XQueryVarName> findVarNames(PsiFile file) {
        List<XQueryVarName> result = null;
        XQueryFile xQueryFile = (XQueryFile) file;
        if (xQueryFile != null) {
            Collection<XQueryVarName> variableNames = PsiTreeUtil.findChildrenOfType(xQueryFile, XQueryVarName.class);
            if (variableNames != null) {
                if (result == null) {
                    result = new ArrayList<XQueryVarName>();
                }
                result.addAll(variableNames);
            }
        }
        return result != null ? result : Collections.<XQueryVarName>emptyList();
    }

    public static List<XQueryVarName> findVarNames(PsiFile file, String key) {
        List<XQueryVarName> result = null;
        XQueryFile xQueryFile = (XQueryFile) file;
        if (xQueryFile != null) {
            Collection<XQueryVarName> variableNames = PsiTreeUtil.findChildrenOfType(xQueryFile, XQueryVarName.class);
            if (variableNames != null) {
                for (XQueryVarName variableName : variableNames) {
                    if (key.equals(variableName.getText())) {
                        if (result == null) {
                            result = new ArrayList<XQueryVarName>();
                        }
                        result.add(variableName);
                    }
                }
            }
        }
        return result != null ? result : Collections.<XQueryVarName>emptyList();
    }

    private static Collection<VirtualFile> findXQueryFiles(Project project) {
        return FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, XQueryFileType.INSTANCE,
                GlobalSearchScope.allScope(project));
    }

    public static List<XQueryFile> findXQueryFileByName(Project project, final String filename) {
        final String name = unifyNameFormatAndRemoveProtocol(filename);
        List<String> splitFilename = StringUtil.split(name, "/");
        String lastPartOfTheFilename = ContainerUtil.iterateAndGetLastItem(splitFilename);
        PsiFile[] filesByName = getFilesByName(project, lastPartOfTheFilename);
        List<PsiFile> filesThatEndWithFullName = getOnlyFilesThatEndWithFullName(name, filesByName);

        return getOnlyXQueryFiles(filesThatEndWithFullName);
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
                return canonicalPath.replaceAll("-[\\d\\.\\w-]+/", "/").endsWith(name);
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
        for (XQueryModuleImportPath path : moduleImport.getModuleImportPathList()) {
            if (path.getReference() != null) {
                XQueryFile xQueryFile = (XQueryFile) path.getReference().resolve();
                if (xQueryFile != null && xQueryFile.getModuleNamespaceName() != null) {
                    results.add(xQueryFile);
                }
            }
        }
        return results;
    }
}
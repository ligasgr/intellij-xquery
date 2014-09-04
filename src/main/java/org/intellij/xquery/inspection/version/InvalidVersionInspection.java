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

package org.intellij.xquery.inspection.version;

import com.intellij.codeInspection.InspectionManager;
import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.xquery.model.XQueryLanguageVersion;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryVersion;
import org.intellij.xquery.psi.XQueryVersionDecl;
import org.jetbrains.annotations.NotNull;

import static com.intellij.codeInspection.ProblemHighlightType.WEAK_WARNING;

public class InvalidVersionInspection extends LocalInspectionTool {

    @Override
    public ProblemDescriptor[] checkFile(@NotNull PsiFile file, @NotNull InspectionManager manager, boolean isOnTheFly) {
        if (!(file instanceof XQueryFile)) {
            return null;
        }

        XQueryVersionDecl versionDecl = PsiTreeUtil.findChildOfType(file, XQueryVersionDecl.class);
        XQueryVersion version = versionDecl != null ? versionDecl.getVersion() : null;
        if (version != null) {
            String versionString = version.getVersionString();
            XQueryLanguageVersion languageVersion = XQueryLanguageVersion.valueFor(versionString);
            if (languageVersion == null) {
                ProblemDescriptor problem = manager.createProblemDescriptor(versionDecl.getVersion(), getDescription(versionString), (LocalQuickFix) null,
                        WEAK_WARNING, true);
                return new ProblemDescriptor[]{problem};
            }
        }

        return null;
    }

    private String getDescription(String versionString) {
        StringBuilder sb = new StringBuilder();
        sb.append("'");
        sb.append(versionString);
        sb.append("'");
        sb.append(" is not a valid version string. Valid options: ");
        boolean first = true;
        for (XQueryLanguageVersion xQueryLanguageVersion : XQueryLanguageVersion.values()) {
            if (!first) {
                sb.append(", ");
            } else {
                first = false;
            }
            sb.append("'");
            sb.append(xQueryLanguageVersion.versionString);
            sb.append("'");
        }
        sb.append(".");

        return sb.toString();
    }
}

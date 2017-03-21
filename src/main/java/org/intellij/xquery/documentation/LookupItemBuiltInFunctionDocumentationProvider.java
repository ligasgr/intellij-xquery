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

package org.intellij.xquery.documentation;

import com.intellij.openapi.project.Project;
import org.intellij.xquery.psi.XQueryFile;

import static org.intellij.xquery.documentation.DocumentationStylist.wrapWithHtmlAndStyle;

public class LookupItemBuiltInFunctionDocumentationProvider implements PsiBasedDocumentationProvider<XQueryDocElement> {

    @Override
    public String generateDoc(XQueryDocElement element) {
        XQueryFile file = (XQueryFile) element.getContainingFile();
        String namespace = element.getNamespace();
        String name = element.getName();
        if (file.isBuiltInFunction(namespace, name)) {
            return getDocumentationFromExternalFile(file.getProject(), name);
        }
        return null;
    }

    private String getDocumentationFromExternalFile(Project project, String name) {
        String doc = ExternalDocumentationFetcher.fetch(project, name);
        if (doc != null)
            return wrapWithHtmlAndStyle(doc);
        else
            return null;
    }
}

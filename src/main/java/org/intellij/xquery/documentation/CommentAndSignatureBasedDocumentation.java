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

import static javax.xml.XMLConstants.DEFAULT_NS_PREFIX;
import static org.intellij.xquery.documentation.DocumentationStylist.HTML_BR;

/**
 * User: ligasgr
 * Date: 29/12/13
 * Time: 09:15
 */
public class CommentAndSignatureBasedDocumentation {
    static final String NAMESPACE_LABEL = "namespace: ";
    static final String FILE_LINK_TEMPLATE = "<a href='psi_element://%s'>%s</a>";
    private String fullFilePath;
    private String displayedFileName;
    private String namespace;
    private String declarationDescription;
    private String xqDocDescription;

    public CommentAndSignatureBasedDocumentation(String fullFilePath, String displayedFileName, String namespace,
                                                 String declarationDescription, String xqDocDescription) {
        this.fullFilePath = fullFilePath;
        this.displayedFileName = displayedFileName;
        this.namespace = namespace;
        this.declarationDescription = declarationDescription;
        this.xqDocDescription = xqDocDescription;
    }

    public String getText() {
        return (getLink(fullFilePath, displayedFileName) + HTML_BR) +
                (DEFAULT_NS_PREFIX.equals(namespace) ? "" : NAMESPACE_LABEL + namespace + HTML_BR) +
                declarationDescription +
                (xqDocDescription != null ? xqDocDescription : "");
    }

    private String getLink(String fullPath, String displayedName) {
        return String.format(FILE_LINK_TEMPLATE, fullPath, displayedName);
    }
}

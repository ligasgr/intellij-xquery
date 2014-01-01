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

package org.intellij.xquery.documentation;

import static org.intellij.xquery.util.StringUtils.normalizeWhitespaces;

/**
 * User: ligasgr
 * Date: 30/12/13
 * Time: 22:10
 */
public class DocumentationStylist {
    public static final String WRAPPER_END = "</body></html>";
    public static final String FUNCTION_START = "<code class=\"function\">";
    public static final String FUNCTION_END = "</code>";
    public static final String LABEL_START = "<dt class=\"label\">";
    public static final String LABEL_END = "</dt>";
    public static final String DL_START = "<dl>";
    public static final String DL_END = "</dl>";
    public static final String DD_START = "<dd>";
    public static final String DD_END = "</dd>";
    static final String XQUERY_STYLE = "<style type=\"text/css\">" +
            "dt {font-weight: bold}" +
            "code.function {font-weight: bold}" +
            "</style>\n";
    public static final String WRAPPER_START = "<html>\n" + XQUERY_STYLE + "<body>";
    static final String HYPHEN_WITH_SPACES = " - ";
    static final String HTML_BR = "<br/>";

    static String wrapWithHtmlAndStyle(String doc) {
        return WRAPPER_START + normalizeWhitespaces(doc) + WRAPPER_END;
    }

    static String label(String text) {
        return LABEL_START + text + LABEL_END;
    }

    static String def(String label, String contents) {
        return label(label) + DD_START + contents + DD_END;
    }

    static String defList(String contents) {
        return DL_START + contents + DL_END;
    }
}

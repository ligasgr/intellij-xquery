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

package org.intellij.xquery.util;

/**
 * User: ligasgr
 * Date: 08/09/13
 * Time: 00:11
 */
public class StringUtils {

    public static final String EMPTY = "";

    public static String removeQuotOrAposIfNeeded(String text) {
        if ((text.startsWith("'") || text.startsWith("\"")) && (text.endsWith("'") || text.endsWith("\""))) {
            return removeQuotOrApos(text);
        }
        return text;
    }

    public static String removeQuotOrApos(String text) {
        return text.substring(1, text.length() - 1);
    }

    public static String compressWhitespaces(String text) {
        return text.replaceAll("\\s+", " ");
    }

    public static String[] splitByFirstWhiteSpaceSequence(String text) {
        int indexOfWhiteSpace = text.indexOf(" ");
        if (indexOfWhiteSpace > - 1) {
            return new String[]{text.substring(0, indexOfWhiteSpace).trim(), text.substring(indexOfWhiteSpace).trim()};
        } else {
            return new String[]{text};
        }
    }

    public static String normalizeWhitespaces(String text) {
        return compressWhitespaces(text).trim();
    }

    public static String stripSeparator(String text) {
        return text.replaceAll(";","");
    }
}

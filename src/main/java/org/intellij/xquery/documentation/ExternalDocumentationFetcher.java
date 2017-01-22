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
import com.intellij.util.ResourceUtil;
import org.intellij.xquery.XQueryFlavour;
import org.intellij.xquery.settings.XQuerySettings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExternalDocumentationFetcher {

    private static final Pattern BEGINNING_OF_FUNCTION = Pattern.compile("<(?:h[0-9])><a (?:name=\"(?:.*?)\" )?id=\"(.*?)\"");
    private static final URL EXTERNAL_DOC_30 = ResourceUtil.getResource(
            ExternalDocumentationFetcher.class, "/documentation", "w3c-xpath-functions-30.html");
    private static final URL EXTERNAL_DOC_31 = ResourceUtil.getResource(
            ExternalDocumentationFetcher.class, "/documentation", "w3c-xpath-functions-31.html");

    public static String fetch(Project project, String name) {
        XQuerySettings settings = XQuerySettings.getInstance(project);
        XQueryFlavour flavour = settings.getFlavour();
        URL externalDoc = flavour == XQueryFlavour.STANDARD_31 ? EXTERNAL_DOC_31 : EXTERNAL_DOC_30;
        BufferedReader reader = getReader(externalDoc);
        if (reader != null) {
            return retrieveDoc(reader, name);
        } else {
            return null;
        }
    }

    private static BufferedReader getReader(URL url) {
        final InputStreamReader stream;
        try {
            stream = new InputStreamReader(url.openStream());
            return new BufferedReader(stream);
        } catch (IOException e) {
        }
        return null;
    }

    private static String retrieveDoc(BufferedReader reader, String name) {
        try {
            return doRetrieveDoc(reader, name);
        } catch (IOException e) {
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
            }
        }
        return null;
    }

    private static String doRetrieveDoc(BufferedReader reader, String name) throws IOException {
        String line;
        boolean functionDocFound = false;
        while ((line = reader.readLine()) != null) {
            if (isDocBegin(line, name)) {
                functionDocFound = true;
                break;
            }
        }
        if (!functionDocFound) {
            return null;
        }
        while ((line = reader.readLine()) != null && !isDefinitionStart(line));
        final StringBuilder builder = new StringBuilder(1024);
        builder.append(line);
        while ((line = reader.readLine()) != null && !isDefinitionEnd(line)) {
            builder.append(line);
            builder.append("\n");
        }
        builder.append(line);
        return builder.toString();
    }

    private static boolean isDefinitionEnd(String line) {
        return line.trim().startsWith("</dl>");
    }

    private static boolean isDefinitionStart(String line) {
        return line.trim().startsWith("<dl>");
    }

    private static boolean isDocBegin(String line, String name) {
        Matcher matcher = BEGINNING_OF_FUNCTION.matcher(line);
        while (matcher.find()) {
            if (matcher.group(1).equals("func-" + name)) {
                return true;
            }
        }
        return false;
    }
}

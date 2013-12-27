/*
 * Copyright 2013 Grzegorz Ligas <ligasgr@gmail.com> and other contributors (see the CONTRIBUTORS file).
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

import static org.intellij.xquery.documentation.XQueryDocumentationProvider.HTML_BR;
import static org.intellij.xquery.util.StringUtils.normalizeWhitespaces;
import static org.intellij.xquery.util.StringUtils.splitByFirstWhiteSpaceSequence;

/**
 * User: ligasgr
 * Date: 28/12/13
 * Time: 11:15
 */
public class XQDocTransformer {
    private static final String PARAM_TAG = "@param";
    private static final String PARAMETERS_LABEL = "Parameters:";
    private static final String HYPHEN_WITH_SPACES = " - ";

    public static String transformXQDoc(String documentationComment) {
        String commentWithoutSeparators = removeSeparators(documentationComment);
        int indexOfFirstTag = getIndexOfFirstTag(commentWithoutSeparators);
        boolean tagSectionExists = indexOfFirstTag > - 1;
        if (tagSectionExists) {
            String basicDescription = commentWithoutSeparators.substring(0, indexOfFirstTag);
            String tagsSection = commentWithoutSeparators.substring(indexOfFirstTag, commentWithoutSeparators.length());
            String xqDocTagsDescription = getXQDocTagsDescription(tagsSection);
            return normalizeWhitespaces(basicDescription) + normalizeWhitespaces(xqDocTagsDescription);
        } else {
            return normalizeWhitespaces(commentWithoutSeparators);
        }
    }

    private static String removeSeparators(String documentationComment) {
        return documentationComment.replaceAll("\\n\\s+:", " ");
    }

    private static String getXQDocTagsDescription(String tagsSection) {
        StringBuilder formattedTags = new StringBuilder();
        int indexOfTag;
        while ((indexOfTag = getIndexOfFirstTag(tagsSection)) > - 1) {
            int indexOfNextTag = tagsSection.indexOf(PARAM_TAG, 1);
            if (indexOfNextTag < 0) {
                indexOfNextTag = tagsSection.length();
            }
            String tagBody = tagsSection.substring(indexOfTag, indexOfNextTag);
            formattedTags.append(crlf());
            String[] tagAndFormattedDescription = formatTag(tagBody);
            formattedTags.append(tagAndFormattedDescription[1]);
            tagsSection = tagsSection.substring(indexOfNextTag);
        }
        return crlf() + crlf() + bold(PARAMETERS_LABEL) + formattedTags.toString();
    }

    private static String[] formatTag(String tagBody) {
        StringBuilder tagDescription = new StringBuilder();
        String[] tagAndRest = splitByFirstWhiteSpaceSequence(tagBody);
        String[] parameterNameAndDescription = splitByFirstWhiteSpaceSequence(tagAndRest[1]);
        tagDescription.append(stripDollarIfNeeded(parameterNameAndDescription[0]));
        tagDescription.append(HYPHEN_WITH_SPACES);
        tagDescription.append(parameterNameAndDescription[1]);
        return new String[]{tagAndRest[0], tagDescription.toString()};
    }

    private static String stripDollarIfNeeded(String text) {
        if (text.startsWith("$")) {
            return text.substring(1);
        }
        return text;
    }

    private static int getIndexOfFirstTag(String text) {
        return text.indexOf(PARAM_TAG);
    }

    private static String crlf() {
        return HTML_BR;
    }

    private static String bold(String text) {
        return "<b>" + text + "</b>";
    }
}

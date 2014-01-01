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

import com.intellij.util.containers.MultiMap;

import java.util.Collection;
import java.util.Set;

import static com.intellij.util.containers.ContainerUtil.set;
import static org.intellij.xquery.documentation.DocumentationStylist.HTML_BR;
import static org.intellij.xquery.documentation.DocumentationStylist.HYPHEN_WITH_SPACES;
import static org.intellij.xquery.documentation.DocumentationStylist.def;
import static org.intellij.xquery.documentation.DocumentationStylist.defList;
import static org.intellij.xquery.util.StringUtils.EMPTY;
import static org.intellij.xquery.util.StringUtils.normalizeWhitespaces;
import static org.intellij.xquery.util.StringUtils.splitByFirstWhiteSpaceSequence;

/**
 * User: ligasgr
 * Date: 28/12/13
 * Time: 11:15
 */
public class XQDocTransformer {
    public static final String PARAMETERS_LABEL = "Parameters";
    private static final String SUMMARY_LABEL = "Summary";
    public static Set<String> XQ_DOC_TAGS = set(
            "@author", "@version", "@param", "@return", "@deprecated", "@error", "@since", "@see"
    );

    public static String transformXQDoc(String documentationComment) {
        String commentWithoutSeparators = removeSeparators(documentationComment);
        int indexOfFirstTag = getIndexOfFirstTag(commentWithoutSeparators);
        boolean tagSectionExists = indexOfFirstTag > -1;
        if (tagSectionExists) {
            return getBasicAndTagDescriptions(commentWithoutSeparators.substring(0, indexOfFirstTag),
                    commentWithoutSeparators.substring(indexOfFirstTag, commentWithoutSeparators.length()));
        } else {
            return defList(def(SUMMARY_LABEL, normalizeWhitespaces(commentWithoutSeparators)));
        }
    }

    private static String getBasicAndTagDescriptions(String basicDescription, String tagsSection) {
        String xqDocTagsDescription = getXQDocTagsDescription(tagsSection);
        String desc = normalizeWhitespaces(basicDescription);
        String basicDescriptionContents = desc.length() > 0 ? def(SUMMARY_LABEL, desc) : "";
        String contents = basicDescriptionContents + normalizeWhitespaces(xqDocTagsDescription);
        return HTML_BR + HTML_BR + defList(contents);
    }

    private static String removeSeparators(String documentationComment) {
        return documentationComment.replaceAll("\\n\\s+:", " ");
    }

    private static String getXQDocTagsDescription(String tagsSection) {
        MultiMap<String, String> xqDocTags = getXQDocTags(tagsSection);
        if (xqDocTags.size() > 0) {
            return formatTags(xqDocTags);
        } else {
            return "";
        }
    }

    private static String formatTags(MultiMap<String, String> xqDocTags) {
        StringBuilder sb = new StringBuilder();
        sb.append(formatTagValuesIfExist("@author", "Author", xqDocTags));
        sb.append(formatTagValuesIfExist("@version", "Version", xqDocTags));
        sb.append(formatTagValuesIfExist("@since", "Since", xqDocTags));
        sb.append(formatTagValuesIfExist("@deprecated", "Deprecated", xqDocTags));
        sb.append(formatTagValuesIfExist("@param", PARAMETERS_LABEL, xqDocTags));
        sb.append(formatTagValuesIfExist("@return", "Returns", xqDocTags));
        sb.append(formatTagValuesIfExist("@error", "Throws errors", xqDocTags));
        sb.append(formatTagValuesIfExist("@see", "See", xqDocTags));
        return sb.toString();
    }

    private static String formatTagValuesIfExist(String tag, String tagLabel, MultiMap<String, String> xqDocTags) {
        if (xqDocTags.get(tag).size() > 0) {
            return formatTagValues(tagLabel, xqDocTags.get(tag));
        } else {
            return EMPTY;
        }
    }

    private static String formatTagValues(String tagLabel, Collection<String> tagDescriptions) {
        StringBuilder formattedTags = new StringBuilder();
        int i = 0;
        for (String paramDescription : tagDescriptions) {
            if (i != 0) {
                formattedTags.append(HTML_BR);
            }
            i++;
            formattedTags.append(paramDescription);
        }
        return def(tagLabel, formattedTags.toString());
    }

    private static MultiMap<String, String> getXQDocTags(String tagsSection) {
        MultiMap<String, String> result = new MultiMap<String, String>();
        int indexOfTag;
        while ((indexOfTag = getIndexOfFirstTag(tagsSection)) > -1) {
            int firstTagLength = splitByFirstWhiteSpaceSequence(tagsSection)[0].length();
            int indexOfNextTag = getIndexOfNextTag(tagsSection, firstTagLength);
            String tagBody = tagsSection.substring(indexOfTag, indexOfNextTag);
            String[] tagAndFormattedDescription = formatTag(tagBody);
            result.putValue(tagAndFormattedDescription[0], tagAndFormattedDescription[1]);
            tagsSection = tagsSection.substring(indexOfNextTag);
        }
        return result;
    }

    private static int getIndexOfNextTag(String tagsSection, int firstTagLength) {
        int indexOfNextTagInRest = getIndexOfFirstTag(tagsSection.substring(firstTagLength));
        if (indexOfNextTagInRest < 0) {
            return tagsSection.length();
        } else {
            return firstTagLength + indexOfNextTagInRest;
        }
    }

    private static String[] formatTag(String tagBody) {
        StringBuilder tagDescription = new StringBuilder();
        String[] tagAndRest = splitByFirstWhiteSpaceSequence(tagBody);
        String tag = tagAndRest[0];
        if ("@param".equals(tag) || "@error".equals(tag)) {
            String[] parameterNameAndDescription = splitByFirstWhiteSpaceSequence(tagAndRest[1]);
            tagDescription.append(stripDollarIfNeeded(parameterNameAndDescription[0]));
            tagDescription.append(HYPHEN_WITH_SPACES);
            if (parameterNameAndDescription.length > 1) {
                tagDescription.append(parameterNameAndDescription[1]);
            }
        } else {
            tagDescription.append(tagAndRest[1]);
        }
        return new String[]{tag, tagDescription.toString()};
    }

    private static String stripDollarIfNeeded(String text) {
        if (text.startsWith("$")) {
            return text.substring(1);
        }
        return text;
    }

    private static int getIndexOfFirstTag(String text) {
        boolean found = false;
        int min = text.length();
        for (String tag : XQ_DOC_TAGS) {
            int indexOfTag = text.indexOf(tag);
            if (indexOfTag > -1 && indexOfTag < min) {
                found = true;
                min = indexOfTag;
            }
        }
        return found ? min : -1;
    }
}

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
package org.intellij.xquery.highlighting;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.intellij.xquery.icons.XQueryIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;

/**
 * User: ligasgr
 * Date: 25/11/13
 * Time: 23:42
 */
public class XQueryColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Bad character", XQuerySyntaxHighlighter.BAD_CHARACTER),
            new AttributesDescriptor("Keyword", XQuerySyntaxHighlighter.KEYWORD),
            new AttributesDescriptor("Semicolon", XQuerySyntaxHighlighter.SEMICOLON),
            new AttributesDescriptor("String", XQuerySyntaxHighlighter.STRING),
            new AttributesDescriptor("Number", XQuerySyntaxHighlighter.NUMBER),
            new AttributesDescriptor("Operator", XQuerySyntaxHighlighter.OPERATION_SIGN),
            new AttributesDescriptor("Parentheses", XQuerySyntaxHighlighter.PARENTHESIS),
            new AttributesDescriptor("Brackets", XQuerySyntaxHighlighter.BRACKET),
            new AttributesDescriptor("Braces", XQuerySyntaxHighlighter.BRACE),
            new AttributesDescriptor("Comment", XQuerySyntaxHighlighter.COMMENT),
            new AttributesDescriptor("Xml Comment", XQuerySyntaxHighlighter.XML_COMMENT),
            new AttributesDescriptor("Xml Tag", XQuerySyntaxHighlighter.XML_TAG),
            new AttributesDescriptor("Xml Tag name", XQuerySyntaxHighlighter.XML_TAG_NAME),
            new AttributesDescriptor("Xml Attribute name", XQuerySyntaxHighlighter.XML_ATTRIBUTE_NAME),
            new AttributesDescriptor("Xml Tag data", XQuerySyntaxHighlighter.XML_TAG_DATA),
            new AttributesDescriptor("Xml Entity reference", XQuerySyntaxHighlighter.XML_ENTITY_REFERENCE),
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return XQueryIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new XQuerySyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "xquery version \"1.0\";\n" +
                "\n" +
                "(:~\n" +
                " :Just testing\n" +
                " :)\n" +
                "declare function test($argument) {\n" +
                "    (1 + 2 - 3 mod 4 * 4, true eq false, 5 > 6)\n" +
                "};\n" +
                "\n" +
                "let $tested := <list>\n" +
                "    <item name=\"df\">content &lt;</item>\n" +
                "    <item name=\"aa&nbsp;\"/>\n" +
                "    <!--<item name=\"cc\"/>-->\n" +
                "</list>\n" +
                "for $item in $tested//item\n" +
                "let $next := ($item/following-sibling::item[1])\n" +
                "return <x>{string($item/@name), '->', $next}</x>" +
                "%%%~~~~";
    }

    @Nullable
    @Override
    public java.util.Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }
    @NotNull
    @Override
    public String getDisplayName() {
        return "XQuery";
    }
}

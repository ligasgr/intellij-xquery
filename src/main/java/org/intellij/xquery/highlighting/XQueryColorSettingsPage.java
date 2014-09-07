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

package org.intellij.xquery.highlighting;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.intellij.util.containers.ContainerUtil;
import org.intellij.xquery.icons.XQueryIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;
import java.util.Map;

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
            new AttributesDescriptor("XQDoc Comment", XQuerySyntaxHighlighter.DOC_COMMENT),
            new AttributesDescriptor("XQDoc Tag", XQuerySyntaxHighlighter.DOC_COMMENT_TAG),
            new AttributesDescriptor("Xml Comment", XQuerySyntaxHighlighter.XML_COMMENT),
            new AttributesDescriptor("Xml Tag", XQuerySyntaxHighlighter.XML_TAG),
            new AttributesDescriptor("Xml Tag name", XQuerySyntaxHighlighter.XML_TAG_NAME),
            new AttributesDescriptor("Xml Attribute name", XQuerySyntaxHighlighter.XML_ATTRIBUTE_NAME),
            new AttributesDescriptor("Xml Tag data", XQuerySyntaxHighlighter.XML_TAG_DATA),
            new AttributesDescriptor("Xml Entity reference", XQuerySyntaxHighlighter.XML_ENTITY_REFERENCE),
            new AttributesDescriptor("Function call", XQuerySyntaxHighlighter.FUNCTION_CALL),
            new AttributesDescriptor("Function declaration", XQuerySyntaxHighlighter.FUNCTION_DECLARATION),
            new AttributesDescriptor("Parameter", XQuerySyntaxHighlighter.PARAMETER),
            new AttributesDescriptor("Global variable", XQuerySyntaxHighlighter.GLOBAL_VARIABLE),
            new AttributesDescriptor("Local variable", XQuerySyntaxHighlighter.LOCAL_VARIABLE),
            new AttributesDescriptor("Prefixed variable", XQuerySyntaxHighlighter.PREFIXED_VARIABLE),
            new AttributesDescriptor("Item type", XQuerySyntaxHighlighter.ITEM_TYPE),
            new AttributesDescriptor("Annotation", XQuerySyntaxHighlighter.ANNOTATION),
    };

    private static Map<String, TextAttributesKey> ATTRIBUTES_KEY_MAP = ContainerUtil.newHashMap();

    static {
        ATTRIBUTES_KEY_MAP.put("fc", XQuerySyntaxHighlighter.FUNCTION_CALL);
        ATTRIBUTES_KEY_MAP.put("fd", XQuerySyntaxHighlighter.FUNCTION_DECLARATION);
        ATTRIBUTES_KEY_MAP.put("p", XQuerySyntaxHighlighter.PARAMETER);
        ATTRIBUTES_KEY_MAP.put("gv", XQuerySyntaxHighlighter.GLOBAL_VARIABLE);
        ATTRIBUTES_KEY_MAP.put("lv", XQuerySyntaxHighlighter.LOCAL_VARIABLE);
        ATTRIBUTES_KEY_MAP.put("it", XQuerySyntaxHighlighter.ITEM_TYPE);
        ATTRIBUTES_KEY_MAP.put("a", XQuerySyntaxHighlighter.ANNOTATION);
        ATTRIBUTES_KEY_MAP.put("pv", XQuerySyntaxHighlighter.PREFIXED_VARIABLE);
    }

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
                " : Function description\n" +
                " :)\n" +
                "declare <a>private</a> <a>updating</a> variable $<gv>var</gv> as <it>xs:string</it> := 'var';\n" +
                "\n" +
                "declare <a>%private</a> <a>%another</a> function <fd>test</fd>($<p>argument</p>) {\n" +
                "    (: comment :)\n" +
                "    (1 + 2 - 3 mod 4 * 4, true eq false, 5 > 6, $<gv>var</gv>, $<p>argument</p>)\n" +
                "};\n" +
                "\n" +
                "let $<pv>local:tested</pv> := <list>\n" +
                "    <item name=\"df\">content &lt;</item>\n" +
                "    <item name=\"aa&nbsp;\"/>\n" +
                "    <!--<item name=\"cc\"/>-->\n" +
                "    <item name=\"{<fc>test</fc>()}\"/>\n" +
                "</list>\n" +
                "for $<lv>item</lv> in $<pv>local:tested</pv>//item\n" +
                "let $<lv>next</lv> := ($<lv>item</lv>/following-sibling::item[1])\n" +
                "return <x>{string($<lv>item</lv>/@name), '->', $<lv>next</lv>}</x>" +
                "%%%~~~~";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return ATTRIBUTES_KEY_MAP;
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

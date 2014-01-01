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

package org.intellij.xquery.formatter.settings;

import com.intellij.application.options.IndentOptionsEditor;
import com.intellij.application.options.SmartIndentOptionsEditor;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import org.intellij.xquery.XQueryLanguage;
import org.jetbrains.annotations.NotNull;

/**
 * User: ligasgr
 * Date: 01/09/13
 * Time: 19:15
 */
public class XQueryLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {
    @NotNull
    @Override
    public Language getLanguage() {
        return XQueryLanguage.INSTANCE;
    }

    @Override
    public IndentOptionsEditor getIndentOptionsEditor() {
        return new SmartIndentOptionsEditor();
    }

    @Override
    public CommonCodeStyleSettings getDefaultCommonSettings() {
        CommonCodeStyleSettings defaultSettings = new CommonCodeStyleSettings(getLanguage());
        CommonCodeStyleSettings.IndentOptions indentOptions = defaultSettings.initIndentOptions();
        indentOptions.INDENT_SIZE = 4;
        indentOptions.CONTINUATION_INDENT_SIZE = 8;
        indentOptions.TAB_SIZE = 4;

        return defaultSettings;
    }

    @Override
    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType settingsType) {
        if (settingsType == SettingsType.SPACING_SETTINGS) {
            consumer.showStandardOptions(
                    "SPACE_AROUND_ASSIGNMENT_OPERATORS",
                    "SPACE_AROUND_EQUALITY_OPERATORS",
                    "SPACE_AROUND_RELATIONAL_OPERATORS",
                    "SPACE_AROUND_ADDITIVE_OPERATORS",
                    "SPACE_AROUND_MULTIPLICATIVE_OPERATORS",
                    "SPACE_AROUND_UNARY_OPERATOR",
                    "SPACE_AFTER_COMMA",
                    "SPACE_BEFORE_COMMA",
                    "SPACE_BEFORE_IF_PARENTHESES",
                    "SPACE_BEFORE_SWITCH_PARENTHESES"
            );

            consumer.renameStandardOption("SPACE_AROUND_ASSIGNMENT_OPERATORS", "Assignment operator (:=)");
            consumer.renameStandardOption("SPACE_AROUND_EQUALITY_OPERATORS", "Equality operators (=, !=)");
            consumer.renameStandardOption("SPACE_AROUND_RELATIONAL_OPERATORS", "Relational operators (<, <=, >, >=)");
            consumer.renameStandardOption("SPACE_AROUND_ADDITIVE_OPERATORS", "Additive operators (+, -)");
            consumer.renameStandardOption("SPACE_AROUND_MULTIPLICATIVE_OPERATORS", "Multiplicative operator (*)");
            consumer.renameStandardOption("SPACE_AROUND_UNARY_OPERATOR", "Unary operators (+, -)");

            consumer.showCustomOption(XQueryCodeStyleSettings.class, "SPACE_AROUND_AXIS_OPERATOR",
                    "Axis operator (::)", CodeStyleSettingsCustomizable.SPACES_AROUND_OPERATORS);

            consumer.showCustomOption(XQueryCodeStyleSettings.class, "SPACE_AROUND_ASSIGNMENT_IN_PROLOG",
                    "Around '=' in declarations", CodeStyleSettingsCustomizable.SPACES_OTHER);
            consumer.showCustomOption(XQueryCodeStyleSettings.class, "SPACE_AROUND_ASSIGNMENT_IN_XML_ATTRIBUTE",
                    "Around '=' inside XML attributes", CodeStyleSettingsCustomizable.SPACES_OTHER);

            consumer.showCustomOption(XQueryCodeStyleSettings.class, "SPACE_BEFORE_TYPESWITCH_PARENTHESES",
                    "'typeswitch' parentheses", CodeStyleSettingsCustomizable.SPACES_BEFORE_PARENTHESES);
            consumer.showCustomOption(XQueryCodeStyleSettings.class, "SPACE_BEFORE_TYPE_TEST_PARENTHESES",
                    "SequenceType and NodeTest parentheses", CodeStyleSettingsCustomizable.SPACES_BEFORE_PARENTHESES);
            consumer.showCustomOption(XQueryCodeStyleSettings.class, "SPACE_BEFORE_FUNCTION_DECLARATION_PARENTHESES",
                    "Function declaration parentheses", CodeStyleSettingsCustomizable.SPACES_BEFORE_PARENTHESES);
            consumer.showCustomOption(XQueryCodeStyleSettings.class, "SPACE_BEFORE_FUNCTION_CALL_PARENTHESES",
                    "Function call parentheses", CodeStyleSettingsCustomizable.SPACES_BEFORE_PARENTHESES);
        } else if (settingsType == SettingsType.WRAPPING_AND_BRACES_SETTINGS) {
            consumer.showStandardOptions("KEEP_LINE_BREAKS");
        } else if (settingsType == SettingsType.BLANK_LINES_SETTINGS) {
            consumer.showStandardOptions("KEEP_BLANK_LINES_IN_CODE");
        }
    }

    @Override
    public String getCodeSample(@NotNull SettingsType settingsType) {
        return "declare default decimal-format decimal-separator = \"anything\" NaN = \"whatever\";\n" +
                "import schema namespace ex = \"ex\";\n" +
                "import module namespace m = \"m\";\n" +
                "declare namespace example = \"example\";\n" +
                "\n" +
                "declare variable $first as item() := if(fn:true()) then 'true' else 'false';\n" +
                "\n" +
                "declare function example (\n" +
                "        $param1 as xs:integer*,\n" +
                "        $param2 as xs:integer)\n" +
                "as xs:integer* {\n" +
                "    let $var:=(1+2, 3-4, 5*6, -1)\n" +
                "    return $var\n" +
                "};\n" +
                "\n" +
                "declare function byCase($var) {\n" +
                "    switch ($var)\n" +
                "    case \"case1\" return \"1\"\n" +
                "    default return \"default\"\n" +
                "};" +
                "\n" +
                "for $b in doc(\"http://bstore1.example.com/bib.xml\")//book,\n" +
                "    $t in $b/title,\n" +
                "    $a in $b/author\n" +
                "let\n" +
                "    $e := ($a, <tag attr='val'/>)\n" +
                "where\n" +
                "    exists($e) and 5 > 6 and 7 = 8\n" +
                "order by\n" +
                "    $t ascending, $a descending\n" +
                "return $t";
    }
}
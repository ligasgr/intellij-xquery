/*
 * Copyright 2013 Grzegorz Ligas <ligasgr@gmail.com>
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
    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType settingsType) {
        if (settingsType == SettingsType.SPACING_SETTINGS) {
            consumer.showStandardOptions(
                    "SPACE_AROUND_ASSIGNMENT_OPERATORS",
                    "SPACE_AROUND_EQUALITY_OPERATORS",
                    "SPACE_AROUND_RELATIONAL_OPERATORS",
                    "SPACE_AROUND_ADDITIVE_OPERATORS",
                    "SPACE_AROUND_MULTIPLICATIVE_OPERATORS",
                    "SPACE_AFTER_COMMA",
                    "SPACE_BEFORE_COMMA");

            consumer.renameStandardOption("SPACE_AROUND_ASSIGNMENT_OPERATORS", "Assignment operators (:=, =)");
            consumer.renameStandardOption("SPACE_AROUND_EQUALITY_OPERATORS", "Equality operators (=, !=)");
            consumer.renameStandardOption("SPACE_AROUND_RELATIONAL_OPERATORS", "Relational operators (<, <=, >, >=)");
            consumer.renameStandardOption("SPACE_AROUND_ADDITIVE_OPERATORS", "Additive operators (+, -)");
            consumer.renameStandardOption("SPACE_AROUND_MULTIPLICATIVE_OPERATORS", "Multiplicative operators (*)");
        } else if (settingsType == SettingsType.WRAPPING_AND_BRACES_SETTINGS) {
            consumer.showStandardOptions("KEEP_LINE_BREAKS");
        } else if (settingsType == SettingsType.BLANK_LINES_SETTINGS) {
            consumer.showStandardOptions("KEEP_BLANK_LINES_IN_CODE");
        }
    }

    @Override
    public String getCodeSample(@NotNull SettingsType settingsType) {
        return "declare namespace example = \"example\";\n" +
                "\n" +
                "declare variable $first := ();\n" +
                "\n" +
                "declare function example (\n" +
                "        $param1 as xs:integer*,\n" +
                "        $param2 as xs:integer)\n" +
                "as xs:integer* {\n" +
                "    let $var:=1+2, 3-4, 5*6\n" +
                "    return $var\n" +
                "};\n" +
                "\n" +
                "for $b in doc(\"http://bstore1.example.com/bib.xml\")//book,\n" +
                "    $t in $b/title,\n" +
                "    $a in $b/author\n" +
                "let\n" +
                "    $e := $a\n" +
                "where\n" +
                "    exists($e) and 5 > 6 and 7 = 8\n" +
                "order by\n" +
                "    $t ascending, $a descending\n" +
                "return $t";
    }
}
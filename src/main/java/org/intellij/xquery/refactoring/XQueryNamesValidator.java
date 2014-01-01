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

package org.intellij.xquery.refactoring;

import com.intellij.lang.refactoring.NamesValidator;
import com.intellij.openapi.project.Project;
import com.intellij.psi.TokenType;
import org.intellij.xquery.lexer.XQueryLexer;
import org.intellij.xquery.psi.XQueryTypes;

/**
 * User: ligasgr
 * Date: 12/06/13
 * Time: 17:30
 */
public class XQueryNamesValidator implements NamesValidator {

    private final XQueryLexer xQueryLexer = new XQueryLexer();

    @Override
    public boolean isKeyword(String name, Project project) {
        return false;
    }

    @Override
    public boolean isIdentifier(String name, Project project) {
        xQueryLexer.start(name);
        assert xQueryLexer.getState() == 0;

        while (xQueryLexer.getTokenType() == TokenType.WHITE_SPACE) {
            xQueryLexer.advance();
        }

        boolean b = xQueryLexer.getTokenType() == XQueryTypes.NCNAME;
        xQueryLexer.advance();

        if (xQueryLexer.getTokenType() == null) {
            return b;
        }
        return false;
    }
}

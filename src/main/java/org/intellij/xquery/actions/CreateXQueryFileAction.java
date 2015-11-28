/*
 * Copyright 2013-2015 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.actions;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.InputValidatorEx;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiDirectory;
import org.intellij.xquery.icons.XQueryIcons;

import static org.intellij.xquery.XQueryFileType.DEFAULT_EXTENSION_WITH_DOT;
import static org.intellij.xquery.actions.XQueryFileTemplates.LIBRARY_MODULE;
import static org.intellij.xquery.actions.XQueryFileTemplates.MAIN_MODULE;

public class CreateXQueryFileAction extends CreateFileFromTemplateAction implements DumbAware {

    public static final String NEW_XQUERY_FILE = "New XQuery File";
    public static final int HYPHEN = '-';
    public static final int UNDERSCORE = '_';
    public static final int DOT = '.';

    public CreateXQueryFileAction() {
        super(NEW_XQUERY_FILE, "", XQueryIcons.FILE);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CreateXQueryFileAction;
    }

    @Override
    protected void buildDialog(final Project project, PsiDirectory psiDirectory, CreateFileFromTemplateDialog.Builder
            builder) {
        builder
                .setTitle(NEW_XQUERY_FILE)
                .addKind("Library Module", XQueryIcons.FILE, LIBRARY_MODULE + DEFAULT_EXTENSION_WITH_DOT)
                .addKind("Main Module", XQueryIcons.FILE, MAIN_MODULE + DEFAULT_EXTENSION_WITH_DOT)
                .setValidator(getValidator());
    }

    @Override
    protected String getActionName(PsiDirectory psiDirectory, String s, String s2) {
        return NEW_XQUERY_FILE;
    }

    private InputValidatorEx getValidator() {
        return new InputValidatorEx() {
            @Override
            public boolean checkInput(String inputString) {
                return true;
            }

            @Override
            public boolean canClose(String inputString) {
                return ! StringUtil.isEmptyOrSpaces(inputString) && getErrorText(inputString) == null;
            }

            @Override
            public String getErrorText(String inputString) {
                String error = " is not a valid XQuery Module name";
                if (StringUtil.isEmpty(inputString)) return null;
                if (isValidFileName(inputString)) {
                    return null;
                }
                return "'" + inputString + "'" + error;
            }
        };
    }

    boolean isValidFileName(String name) {
        int length = name.length();
        if (length > 0) {
            char firstCharacter = name.charAt(0);
            if (!Character.isLetter(firstCharacter) && firstCharacter != UNDERSCORE) {
                return false;
            }
        }
        for(int i = 0; i < length; ++i) {
            char c = name.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != UNDERSCORE && c != HYPHEN && c != DOT) {
                return false;
            }
        }
        return true;
    }
}

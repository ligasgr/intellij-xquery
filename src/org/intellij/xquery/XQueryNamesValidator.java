package org.intellij.xquery;

import com.intellij.lang.refactoring.NamesValidator;
import com.intellij.openapi.project.Project;

/**
 * User: ligasgr
 * Date: 12/06/13
 * Time: 17:30
 */
public class XQueryNamesValidator implements NamesValidator {
    @Override
    public boolean isKeyword(String name, Project project) {
        return false;
    }

    @Override
    public boolean isIdentifier(String name, Project project) {
        return true;
    }
}

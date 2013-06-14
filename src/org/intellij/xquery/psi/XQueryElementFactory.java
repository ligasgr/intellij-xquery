package org.intellij.xquery.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.xquery.XQueryFileType;

/**
 * User: ligasgr
 * Date: 08/06/13
 * Time: 22:01
 */
public class XQueryElementFactory {
    public static XQueryVarRefName createVariableReference(Project project, String name) {
        final XQueryFile file = createFile(project, "$" + name);
        return PsiTreeUtil.findChildOfType(file, XQueryVarRefName.class);
    }

    public static XQueryVarName createVariableDeclaration(Project project, String name) {
        final XQueryFile file = createFile(project, "declare variable $" + name + " := 'dummy';a");
        return PsiTreeUtil.findChildOfType(file, XQueryVarName.class);
    }

    public static XQueryFile createFile(Project project, String text) {
        String name = "dummy.simple";
        return (XQueryFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, XQueryFileType.INSTANCE, text);
    }
}

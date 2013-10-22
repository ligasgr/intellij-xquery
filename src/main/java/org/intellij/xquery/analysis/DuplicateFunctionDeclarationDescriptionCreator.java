package org.intellij.xquery.analysis;

import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.intellij.xquery.psi.XQueryParamList;

import static java.lang.String.format;

public class DuplicateFunctionDeclarationDescriptionCreator {

    static final String DUPLICATE_FUNCTION_TOOLTIP = "'%s' is already defined in '%s'";

    public String createDescription(XQueryFunctionName functionName, XQueryFile file) {
        XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) functionName.getParent();
        XQueryParamList paramList = functionDeclaration.getParamList();
        StringBuilder paramString = new StringBuilder();
        if (paramList != null) {
            int numberOfParameters = paramList.getParamList().size();
            for (int i = 0; i < numberOfParameters; i++) {
                paramString.append("$arg").append(i + 1);
                if (isNotTheLastArgument(numberOfParameters, i)) {
                    paramString.append(", ");
                }
            }
        }
        return format(DUPLICATE_FUNCTION_TOOLTIP, functionName.getName() + "(" + paramString + ")", file.getName());
    }

    private boolean isNotTheLastArgument(int numberOfParameters, int currentParameterIndex) {
        return currentParameterIndex < numberOfParameters - 1;
    }
}

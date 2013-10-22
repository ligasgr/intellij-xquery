package org.intellij.xquery.analysis;

import com.google.common.base.Predicate;
import com.intellij.psi.PsiElement;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionName;

import java.util.Collection;

import static com.google.common.collect.Collections2.filter;

public class DuplicateFunctionDeclarationVerifier {

    public boolean functionHasDuplicateDeclarationInFile(XQueryFunctionName functionName, XQueryFile file) {
        boolean result = false;

        if (functionName.getParent() instanceof XQueryFunctionDecl) {
            final XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) functionName.getParent();
            Collection<XQueryFunctionDecl> filteredDeclarations = filter(file.getFunctionDeclarations(),
                    new Predicate<XQueryFunctionDecl>() {
                        @Override
                        public boolean apply(XQueryFunctionDecl otherFunctionDeclaration) {
                            return functionDeclaration != otherFunctionDeclaration;
                        }
                    });
            for (XQueryFunctionDecl otherFunctionDeclaration : filteredDeclarations) {
                if (textIsTheSame(functionName, otherFunctionDeclaration) && arityIsTheSame(functionName, otherFunctionDeclaration)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    private boolean textIsTheSame(XQueryFunctionName functionName, XQueryFunctionDecl otherFunctionDeclaration) {
        XQueryFunctionName otherFunctionName = otherFunctionDeclaration.getFunctionName();
        return functionName.getText().equals(otherFunctionName != null ? otherFunctionName.getText() : null);
    }

    private boolean arityIsTheSame(XQueryFunctionName functionName, XQueryFunctionDecl otherFunctionDeclaration) {
        PsiElement parent = functionName.getParent();
        return otherFunctionDeclaration.getArity() == ((XQueryFunctionDecl) parent).getArity();
    }
}

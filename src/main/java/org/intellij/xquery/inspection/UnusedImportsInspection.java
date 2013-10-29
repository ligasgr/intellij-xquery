package org.intellij.xquery.inspection;

import com.intellij.codeInspection.HintAction;
import com.intellij.codeInspection.InspectionManager;
import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.psi.PsiFile;
import org.intellij.xquery.psi.*;

import java.util.*;

import static com.intellij.codeInspection.ProblemHighlightType.LIKE_UNUSED_SYMBOL;
import static org.intellij.xquery.util.StringUtils.removeQuotOrApos;

public class UnusedImportsInspection extends LocalInspectionTool {

    private static final HintAction NO_HINT_ACTION = null;

    @Override
    public String getDisplayName() {
        return "Highlights unused import declarations";
    }

    @Override
    public ProblemDescriptor[] checkFile(PsiFile file, InspectionManager manager, boolean isOnTheFly) {
        if (!(file instanceof XQueryFile)) {
            return null;
        }
        List<ProblemDescriptor> problems = findUnusedImports((XQueryFile) file, manager);
        return problems.toArray(new ProblemDescriptor[problems.size()]);
    }

    private List<ProblemDescriptor> findUnusedImports(XQueryFile xQueryFile, InspectionManager manager) {
        Collection<XQueryModuleImport> unusedImports = getUnusedImports(xQueryFile);
        List<ProblemDescriptor> problems = new ArrayList<ProblemDescriptor>();
        for (XQueryModuleImport unused : unusedImports) {
            problems.add(manager.createProblemDescriptor(unused, "Unused import", LIKE_UNUSED_SYMBOL,
                    NO_HINT_ACTION, true));
        }

        return problems;
    }

    private Collection<XQueryModuleImport> getUnusedImports(XQueryFile xQueryFile) {
        List<XQueryModuleImport> unusedImports = new ArrayList<XQueryModuleImport>();
        Set<String> usedNamespaces = getUsedNamespaces(xQueryFile);
        for (XQueryModuleImport moduleImport : xQueryFile.getModuleImports()) {
            if (!moduleIsUsed(moduleImport, usedNamespaces)) {
                unusedImports.add(moduleImport);
            }
        }

        return unusedImports;
    }

    private boolean moduleIsUsed(XQueryModuleImport moduleImport, Set<String> usedNamespaces) {
        String importedNamespace = removeQuotOrApos(moduleImport.getModuleImportNamespace().getText());
        return usedNamespaces.contains(importedNamespace);
    }

    private Set<String> getUsedNamespaces(XQueryFile xQueryFile) {
        Set<String> namespaces = new HashSet<String>();
        namespaces.addAll(getNamespacesUsedByFunctions(xQueryFile));
        namespaces.addAll(getNamespacesUsedByVariables(xQueryFile));
        return namespaces;
    }

    private Set<String> getNamespacesUsedByVariables(XQueryFile xQueryFile) {
        Set<String> usedNamespaces = new HashSet<String>();
        for (XQueryVarRef variableReference : xQueryFile.getVariableReferences()) {
            XQueryVarNamespace varNamespace = variableReference.getVarName().getVarNamespace();
            if (varNamespace != null) {
                usedNamespaces.add(getNamespaceForPrefix(xQueryFile, varNamespace.getText()));
            }
        }

        return usedNamespaces;
    }

    private Set<String> getNamespacesUsedByFunctions(XQueryFile xQueryFile) {
        Set<String> usedNamespaces = new HashSet<String>();
        for (XQueryFunctionInvocation functionInvocation : xQueryFile.getFunctionInvocations()) {
            XQueryFunctionNamespace namespacePrefix = functionInvocation.getFunctionName().getFunctionNamespace();
            String functionNamespace = getFunctionNamespace(xQueryFile, namespacePrefix);
            usedNamespaces.add(functionNamespace);
        }
        return usedNamespaces;
    }

    private String getFunctionNamespace(XQueryFile xQueryFile, XQueryFunctionNamespace namespacePrefix) {
        String functionNamespace = xQueryFile.getDefaultFunctionNamespace();
        if (namespacePrefix != null) {
            functionNamespace = getNamespaceForPrefix(xQueryFile, namespacePrefix.getText());
        }
        return functionNamespace;
    }

    private String getNamespaceForPrefix(XQueryFile xQueryFile, String namespacePrefix) {
        for (XQueryModuleImport moduleImport : xQueryFile.getModuleImports()) {
            XQueryNamespaceName importNamespacePrefix = moduleImport.getNamespaceName();
            if (importNamespacePrefix != null && importNamespacePrefix.getText().equals(namespacePrefix)) {
                return removeQuotOrApos(moduleImport.getModuleImportNamespace().getText());
            }
        }

        for (XQueryNamespaceDecl namespaceDecl : xQueryFile.getNamespaceDeclarations()) {
            if (namespaceDecl.getNamespaceName().getText().equals(namespacePrefix)) {
                return removeQuotOrApos(namespaceDecl.getURILiteral().getText());
            }
        }

        return null;
    }
}

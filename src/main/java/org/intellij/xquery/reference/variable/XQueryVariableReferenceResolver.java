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

package org.intellij.xquery.reference.variable;

import com.intellij.openapi.util.Condition;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.ResolveState;
import com.intellij.psi.util.PsiTreeUtil;

import org.intellij.xquery.XQueryFlavour;
import org.intellij.xquery.model.XQueryQName;
import org.intellij.xquery.model.XQueryQNameBuilder;
import org.intellij.xquery.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.intellij.xquery.model.XQueryQNameBuilder.aXQueryQName;

/**
 * User: ligasgr
 * Date: 14/08/13
 * Time: 22:00
 */
public class XQueryVariableReferenceResolver {

    private final String checkedNamespacePrefix;
    private List<XQueryVarName> matchingVariableNames;
    private XQueryVarRef myElement;
    private static final String errNamespace = XQueryFlavour.MARKLOGIC.getBifTable().predeclaredNamespaces().getNamespaceForPrefix ("err");
    private static final List<XQueryQName<XQueryVarName>> implicitCatchVars = new ArrayList<>();

    public XQueryVariableReferenceResolver(XQueryVarRef myElement) {
        this.myElement = myElement;
        checkedNamespacePrefix = myElement.getVarName().getPrefix() != null ? myElement.getVarName().getPrefix().getText() : null;
    }

    @NotNull
    public ResolveResult[] getResolutionResults() {
        XQueryFile file = (XQueryFile) myElement.getContainingFile();
        matchingVariableNames = new ArrayList<>();
        addReferencesFromLocalScopes();
        if (matchingVariableNames.size() > 0) {
            return convertToResolveResults(matchingVariableNames);
        }
        addVariableDeclarationReferencesFromFile(file, checkedNamespacePrefix);
        addVariableDeclarationReferencesFromImportedFiles(file);
        return convertToResolveResults(matchingVariableNames);
    }

    private void addReferencesFromLocalScopes() {
        VariableReferenceScopeProcessor processor = new VariableReferenceScopeProcessor(myElement);
        PsiTreeUtil.treeWalkUp(processor, myElement, null, ResolveState.initial());
        if (processor.getResult() == null) {
        	addReferencesFromCatchClause();
	} else {
		matchingVariableNames.add (processor.getResult());
	}
    }

    private void addReferencesFromCatchClause()
    {
	XQueryQName<XQueryVarName> qname = XQueryQNameBuilder.aXQueryQName (myElement.getVarName()).build();

	for (XQueryQName<XQueryVarName> q : implicitCatchVars) {
		if (q.equals (qname) && hasCatchClauseAncester()) {
			matchingVariableNames.add (myElement.getVarName());
			return;
		}
	}
    }

    private boolean hasCatchClauseAncester()
    {
	for (PsiElement element = myElement.getContext(); element != null; element = element.getContext()) {
		if (element instanceof XQueryCatchClause) return true;
	}

	return false;
    }

    private void addVariableDeclarationReferencesFromFile(XQueryFile file, String checkedNamespace) {
        for (XQueryVarDecl varDecl : file.getVariableDeclarations()) {
            addVariableAsTargetIfMatches(varDecl, checkedNamespace);
        }
    }

    private void addVariableAsTargetIfMatches(XQueryVarDecl variableDeclaration, String checkedNamespace) {
        if (variableDeclarationWithValidName(variableDeclaration)) {
            XQueryQName<XQueryVarName> source = aXQueryQName(myElement.getVarName())
                    .withPrefix(checkedNamespace)
                    .build();
            XQueryQName<XQueryVarName> checkedQName = aXQueryQName(variableDeclaration.getVarName())
                    .build();
            if (source.equals(checkedQName)) {
                matchingVariableNames.add(checkedQName.getNamedObject());
            }
        }
    }

    private void addVariableDeclarationReferencesFromImportedFiles(XQueryFile file) {
        if (variableHasNamespacePrefix()) {
            for (XQueryFile importedFile : getFilesFromImportWithMatchingNamespacePrefix(file)) {
                addVariableDeclarationReferencesFromFile(importedFile, importedFile.getModuleNamespaceName().getText());
            }
        }
    }

    private boolean variableHasNamespacePrefix() {
        return myElement.getVarName().getPrefix() != null;
    }

    private Collection<XQueryFile> getFilesFromImportWithMatchingNamespacePrefix(XQueryFile file) {
        return file.getImportedFilesThatExist(new Condition<XQueryModuleImport>() {
            @Override
            public boolean value(XQueryModuleImport moduleImport) {
                String namespacePrefix = myElement.getVarName().getPrefix().getText();
                String prefixFromImport = moduleImport.getNamespacePrefix() != null
                        ? moduleImport.getNamespacePrefix().getText()
                        : null;
                return namespacePrefix.equals(prefixFromImport);
            }
        });
    }

    private ResolveResult[] convertToResolveResults(List<XQueryVarName> resolveResults) {
        ResolveResult[] convertedResults = new ResolveResult[resolveResults.size()];
        for (int i = 0; i < resolveResults.size(); i++) {
            convertedResults[i] = new PsiElementResolveResult(resolveResults.get(i));
        }
        return convertedResults;
    }

    private boolean variableDeclarationWithValidName(XQueryVarDecl varDecl) {
        return varDecl.getVarName() != null && varDecl.getVarName().getTextLength() > 0;
    }

	static {
		implicitCatchVars.add (new XQueryQName<> ("err", "value", errNamespace, null));
		implicitCatchVars.add (new XQueryQName<> ("err", "code", errNamespace, null));
		implicitCatchVars.add (new XQueryQName<> ("err", "description", errNamespace, null));
		implicitCatchVars.add (new XQueryQName<> ("err", "module", errNamespace, null));
		implicitCatchVars.add (new XQueryQName<> ("err", "line-number", errNamespace, null));
		implicitCatchVars.add (new XQueryQName<> ("err", "column-number", errNamespace, null));
		implicitCatchVars.add (new XQueryQName<> ("err", "additional", errNamespace, null));
	}
}

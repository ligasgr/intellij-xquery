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

package org.intellij.xquery.inspection.unusedvariable;

import org.apache.commons.lang.StringUtils;
import org.intellij.xquery.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

public class UnusedVariableFinder {

    private final XQueryFunctionDecl functionDecl;

    public UnusedVariableFinder(XQueryFunctionDecl functionDecl) {
        this.functionDecl = functionDecl;
    }

    public List<XQueryVarName> findUnusedVariables() {
        List<XQueryVarName> unusedBindings = new ArrayList<XQueryVarName>();

        final List<XQueryExprSingle> exprSingleList = extractXQueryExprSingleList();

        for (XQueryExprSingle exprSingle : exprSingleList) {
            if (exprSingle instanceof XQueryFLWORExpr) {
                List<XQueryVarName> unusedBindingsInExpression = findUnusedLetBindings((XQueryFLWORExpr) exprSingle);
                unusedBindings.addAll(unusedBindingsInExpression);
            }
        }

        return unusedBindings;
    }

    private List<XQueryVarName> findUnusedLetBindings(XQueryFLWORExpr expressionFLWOR) {
        List<XQueryVarName> varNames = findAllDeclaredVarNames(expressionFLWOR);
        List<XQueryVarRef> variableUsages = findVariableUsages(expressionFLWOR);

        return filterNotReferencedVarNames(varNames, variableUsages);
    }

    private List<XQueryVarName> findAllDeclaredVarNames(XQueryFLWORExpr expressionFLWOR) {
        List<XQueryVarName> letBindingVarNames = findLetBindingsVarNames(expressionFLWOR);
        List<XQueryVarName> forClauseVarNames = findForClauseVarNames(expressionFLWOR);

        List<XQueryVarName> allVarNames = new ArrayList<XQueryVarName>();
        allVarNames.addAll(letBindingVarNames);
        allVarNames.addAll(forClauseVarNames);
        return allVarNames;
    }

    private List<XQueryVarName> findLetBindingsVarNames(XQueryFLWORExpr expressionFLWOR) {
        List<XQueryVarName> letBindingVarNames = new ArrayList<XQueryVarName>();
        for (XQueryLetClause letClause : expressionFLWOR.getLetClauseList()) {
            final List<XQueryLetBinding> letBindingList = letClause.getLetBindingList();
            for (XQueryLetBinding letBinding : letBindingList) {
                letBindingVarNames.add(letBinding.getVarName());
            }
        }
        return letBindingVarNames;
    }

    private List<XQueryVarName> findForClauseVarNames(XQueryFLWORExpr expressionFLWOR) {
        List<XQueryVarName> forClauseVarNames = new ArrayList<XQueryVarName>();
        for (XQueryForClause forClause : expressionFLWOR.getForClauseList()) {
            for (XQueryForBinding forBinding : forClause.getForBindingList()) {
                forClauseVarNames.add(forBinding.getVarName());
            }
        }
        return forClauseVarNames;
    }

    private List<XQueryVarName> filterNotReferencedVarNames(List<XQueryVarName> declaredVariableNames, List<XQueryVarRef> variableReferences) {
        List<XQueryVarName> unusedVariables = new ArrayList<XQueryVarName>();
        for (XQueryVarName declaredVarName : declaredVariableNames) {
            boolean found = false;
            final String letBindingName = declaredVarName.getName();
            for (XQueryVarRef varRef : variableReferences) {
                final XQueryVarName varRefVarName = varRef.getVarName();
                final String varRefName = varRefVarName != null ? varRefVarName.getName() : null;
                if (StringUtils.equals(varRefName, letBindingName)) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                unusedVariables.add(declaredVarName);
            }
        }

        return unusedVariables;
    }

    private List<XQueryVarRef> findVariableUsages(XQueryFLWORExpr expressionFLWOR) {
        final UsedVariableVisitor usedVariableVisitor = new UsedVariableVisitor();
        expressionFLWOR.acceptChildren(usedVariableVisitor);
        return usedVariableVisitor.getVariableReferences();
    }


    @NotNull
    private List<XQueryExprSingle> extractXQueryExprSingleList() {
        final XQueryFunctionBody functionBody = functionDecl.getFunctionBody();
        if (functionBody == null) {
            return emptyList();
        }
        final XQueryEnclosedExpr enclosedExpr = functionBody.getEnclosedExpr();
        final XQueryExpr expr = enclosedExpr.getExpr();
        if (expr != null) {
            return expr.getExprSingleList();
        } else {
            return emptyList();
        }
    }

}

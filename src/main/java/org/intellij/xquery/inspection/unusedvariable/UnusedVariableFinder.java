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

import java.util.ArrayList;
import java.util.List;

public class UnusedVariableFinder {

    private final XQueryFunctionDecl functionDecl;

    public UnusedVariableFinder(XQueryFunctionDecl functionDecl) {
        this.functionDecl = functionDecl;
    }

    public List<XQueryLetBinding> findUnusedVariables() {
        List<XQueryLetBinding> unusedBindings = new ArrayList<XQueryLetBinding>();

        final List<XQueryExprSingle> exprSingleList = extractXQueryExprSingleList();

        for (XQueryExprSingle exprSingle : exprSingleList) {
            if (exprSingle instanceof XQueryFLWORExpr) {
                final List<XQueryLetBinding> unusedBindingsInExpression = analyzeFLWOR((XQueryFLWORExpr) exprSingle);
                unusedBindings.addAll(unusedBindingsInExpression);
            }
        }

        return unusedBindings;
    }

    private List<XQueryExprSingle> extractXQueryExprSingleList() {
        final XQueryFunctionBody functionBody = functionDecl.getFunctionBody();
        final XQueryEnclosedExpr enclosedExpr = functionBody.getEnclosedExpr();
        final XQueryExpr expr = enclosedExpr.getExpr();
        return expr.getExprSingleList();
    }

    private List<XQueryLetBinding> analyzeFLWOR(XQueryFLWORExpr expressionFLWOR) {
        List<XQueryLetBinding> letBindings = new ArrayList<XQueryLetBinding>();
        final List<XQueryLetClause> letClauseList = expressionFLWOR.getLetClauseList();
        for (XQueryLetClause letClause : letClauseList) {
            final List<XQueryLetBinding> letBindingList = letClause.getLetBindingList();
            for (XQueryLetBinding letBinding : letBindingList) {
                letBindings.add(letBinding);
            }
        }

        final UsedVariableVisitor usedVariableVisitor = new UsedVariableVisitor();
        expressionFLWOR.acceptChildren(usedVariableVisitor);

        List<XQueryLetBinding> unusedVariables = new ArrayList<XQueryLetBinding>();
        for (XQueryLetBinding letBinding : letBindings) {
            boolean found = false;
            final String letBindingName = letBinding.getVarName().getName();
            for (XQueryVarRef varRef : usedVariableVisitor.getVariableReferences()) {
                final String varRefName = varRef.getVarName().getName();
                if (StringUtils.equals(varRefName, letBindingName)) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                unusedVariables.add(letBinding);
            }
        }

        return unusedVariables;
    }

}

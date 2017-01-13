/*
 * Copyright 2013-2017 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.runner.rt.debugger.saxon;

import net.sf.saxon.expr.Expression;
import net.sf.saxon.expr.StaticContext;
import net.sf.saxon.expr.flwor.Clause;
import net.sf.saxon.expr.flwor.TraceClause;
import net.sf.saxon.expr.instruct.TraceExpression;
import net.sf.saxon.om.StructuredQName;
import net.sf.saxon.trace.TraceCodeInjector;

import static org.intellij.xquery.runner.rt.debugger.LogUtil.log;

public class SaxonExtendedTraceCodeInjector extends TraceCodeInjector {
    @Override
    public Expression inject(Expression expression, StaticContext staticContext, int construct, StructuredQName qName) {
        log("injecting trace for =" + expression.getClass() + " line: " + expression.getLocation().getLineNumber() + " construct: " + construct + " module: " + staticContext.getSystemId());
        TraceExpression trace = new TraceExpression(expression);
        trace.setNamespaceResolver(staticContext.getNamespaceResolver());
        trace.setConstructType(construct);
        trace.setObjectName(qName);
        return trace;
    }

    @Override
    public Clause injectClause(Clause clause, StaticContext env) {
        log("injecting clause for =" + clause.getClass() + " line: " + clause.getLocation().getLineNumber() + " module: " + clause.getLocation().getSystemId());
        return new TraceClause(clause, env.getNamespaceResolver());
    }
}

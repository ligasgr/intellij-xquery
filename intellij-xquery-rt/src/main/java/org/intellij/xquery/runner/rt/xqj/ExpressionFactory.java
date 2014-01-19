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

package org.intellij.xquery.runner.rt.xqj;

import org.intellij.xquery.runner.rt.XQueryContentFactory;
import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.xqj.binding.ContextItemBinder;
import org.intellij.xquery.runner.rt.xqj.binding.VariablesBinder;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQPreparedExpression;

/**
 * User: ligasgr
 * Date: 04/11/13
 * Time: 14:03
 */
public class ExpressionFactory {
    private final XQueryRunConfig config;
    private final XQueryContentFactory contentFactory;
    private VariablesBinder variablesBinder;
    private ContextItemBinder contextItemBinder;

    public ExpressionFactory(XQueryRunConfig config, XQueryContentFactory contentFactory,
                             VariablesBinder variablesBinder, ContextItemBinder contextItemBinder) {
        this.config = config;
        this.contentFactory = contentFactory;
        this.variablesBinder = variablesBinder;
        this.contextItemBinder = contextItemBinder;
    }

    public XQPreparedExpression getExpression(XQConnection connection) throws Exception {
        XQPreparedExpression preparedExpression = connection
                .prepareExpression(contentFactory.getXQueryContentAsStream());
        contextItemBinder.bindContextItem(connection, preparedExpression);
        variablesBinder.bindVariables(connection, preparedExpression);
        return preparedExpression;
    }
}

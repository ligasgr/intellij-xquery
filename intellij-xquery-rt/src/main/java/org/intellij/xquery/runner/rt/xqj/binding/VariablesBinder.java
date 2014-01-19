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

package org.intellij.xquery.runner.rt.xqj.binding;

import org.intellij.xquery.runner.rt.NameExtractor;
import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.XQueryRunnerVariable;

import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQPreparedExpression;

/**
 * User: ligasgr
 * Date: 04/11/13
 * Time: 18:20
 */
public class VariablesBinder {
    private final TypeBinderFactory binderFactory;
    private final XQueryRunConfig config;
    private final NameExtractor nameExtractor;

    public VariablesBinder(TypeBinderFactory binderFactory, XQueryRunConfig config, NameExtractor nameExtractor) {
        this.binderFactory = binderFactory;
        this.config = config;
        this.nameExtractor = nameExtractor;
    }

    public void bindVariables(XQConnection connection, XQPreparedExpression preparedExpression)
            throws Exception {
        for (XQueryRunnerVariable variable : config.getVariables()) {
            if (variable.ACTIVE) {
                TypeBinder binder = binderFactory.getBinder(variable.TYPE);
                QName name = nameExtractor.getName(variable.NAME, variable.NAMESPACE);
                binder.bind(preparedExpression, connection, name, variable.VALUE, variable.TYPE);
            }
        }
    }
}

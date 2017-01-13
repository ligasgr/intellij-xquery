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

package org.intellij.xquery.runner.rt.xqj;

import org.intellij.xquery.runner.rt.NameExtractor;
import org.intellij.xquery.runner.rt.OutputMethodFactory;
import org.intellij.xquery.runner.rt.RunnerApp;
import org.intellij.xquery.runner.rt.RunnerAppFactory;
import org.intellij.xquery.runner.rt.XQueryContentFactory;
import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.xqj.binding.ContextItemBinder;
import org.intellij.xquery.runner.rt.xqj.binding.TypeBinderFactory;
import org.intellij.xquery.runner.rt.xqj.binding.VariablesBinder;

import java.io.PrintStream;

public class XQJRunnerAppFactory implements RunnerAppFactory {

    @Override
    public RunnerApp getInstance(XQueryRunConfig config, PrintStream output) {
        DataSourceFactory dataSourceFactory = new DataSourceFactory(config);
        ConnectionFactory connectionFactory = new ConnectionFactory(config);
        TypeBinderFactory binderFactory = new TypeBinderFactory();
        NameExtractor nameExtractor = new NameExtractor();
        VariablesBinder variablesBinder = new VariablesBinder(binderFactory, config, nameExtractor);
        ContextItemBinder contextItemBinder = new ContextItemBinder(binderFactory, config);
        XQueryContentFactory contentFactory = new XQueryContentFactory(config);
        ExpressionFactory expressionFactory = new ExpressionFactory(config, contentFactory, variablesBinder,
                contextItemBinder);
        OutputMethodFactory outputMethodFactory = new OutputMethodFactory(config);
        return new XQJRunnerApp(connectionFactory, dataSourceFactory,
                expressionFactory, outputMethodFactory, output);
    }
}

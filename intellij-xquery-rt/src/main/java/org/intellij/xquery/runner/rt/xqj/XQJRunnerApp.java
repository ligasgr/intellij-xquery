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

import org.intellij.xquery.runner.rt.OutputMethodFactory;
import org.intellij.xquery.runner.rt.RunnerApp;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
import java.io.PrintStream;

public class XQJRunnerApp implements RunnerApp {

    private final ConnectionFactory connectionFactory;
    private final DataSourceFactory dataSourceFactory;
    private final ExpressionFactory expressionFactory;
    private final OutputMethodFactory outputMethodFactory;
    private final PrintStream output;

    public XQJRunnerApp(ConnectionFactory connectionFactory,
                        DataSourceFactory dataSourceFactory, ExpressionFactory expressionFactory,
                        OutputMethodFactory outputMethodFactory, PrintStream output) {
        this.connectionFactory = connectionFactory;
        this.dataSourceFactory = dataSourceFactory;
        this.expressionFactory = expressionFactory;
        this.outputMethodFactory = outputMethodFactory;
        this.output = output;
    }


    public void runApp() throws Exception {
        XQDataSource xqs = dataSourceFactory.getDataSource();
        XQConnection connection = null;
        try {
            connection = connectionFactory.getConnection(xqs);
            XQPreparedExpression preparedExpression = expressionFactory.getExpression(connection);
            XQResultSequence rs = preparedExpression.executeQuery();
            rs.writeSequence(output, outputMethodFactory.getOutputMethodProperties());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}

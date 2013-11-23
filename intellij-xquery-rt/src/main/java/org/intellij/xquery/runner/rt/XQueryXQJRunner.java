/*
 * Copyright 2013 Grzegorz Ligas <ligasgr@gmail.com> and other contributors (see the CONTRIBUTORS file).
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

package org.intellij.xquery.runner.rt;

import org.intellij.xquery.runner.rt.binding.TypeBinderFactory;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
import java.io.PrintStream;

/**
 * User: ligasgr
 * Date: 15/08/13
 * Time: 13:56
 */
public class XQueryXQJRunner implements XQueryGenericRunner {
  private final ConnectionFactory connectionFactory;
  private final DataSourceFactory dataSourceFactory;
  private final ExpressionFactory expressionFactory;
  private final OutputMethodFactory outputMethodFactory;

  public XQueryXQJRunner(XQueryRunConfig config) {
    this.dataSourceFactory = new DataSourceFactory(config);
    this.connectionFactory = new ConnectionFactory(config);
    TypeBinderFactory binderFactory = new TypeBinderFactory();
    NameExtractor nameExtractor = new NameExtractor();
    VariablesBinder variablesBinder = new VariablesBinder(binderFactory, config, nameExtractor);
    ContextItemBinder contextItemBinder = new ContextItemBinder(binderFactory, config);
    XQueryContentFactory contentFactory = new XQueryContentFactory(config);
    this.expressionFactory = new ExpressionFactory(config, contentFactory, variablesBinder, contextItemBinder);
    OutputMethodFactory outputMethodFactory = new OutputMethodFactory(config);
    this.outputMethodFactory = outputMethodFactory;
  }

  public void run(final PrintStream out) throws Exception {
    XQDataSource xqs = dataSourceFactory.getDataSource();
    XQConnection connection = null;
    try {
      connection = connectionFactory.getConnection(xqs);
      XQPreparedExpression preparedExpression = expressionFactory.getExpression(connection);
      XQResultSequence rs = preparedExpression.executeQuery();
      rs.writeSequence(out, outputMethodFactory.getOutputMethodProperties());
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }
}
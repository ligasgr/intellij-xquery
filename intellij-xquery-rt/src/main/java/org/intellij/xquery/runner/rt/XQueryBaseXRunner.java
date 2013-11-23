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

import org.basex.core.Context;
import org.basex.io.serial.Serializer;
import org.basex.query.QueryException;
import org.basex.query.QueryProcessor;
import org.basex.query.iter.Iter;
import org.basex.query.value.item.Item;

import javax.xml.namespace.QName;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

/**
 * Runner class to execute XQuery using an embedded version of BaseX.
 *
 * @author Dirk Kirsten, BaseX GmbH
 */
public class XQueryBaseXRunner implements XQueryGenericRunner {
  /**
   * Executed XQuery file factory.
   */
  private final XQueryContentFactory contentFactory;
  /**
   * .basex config file.
   */
  private final String configFile;
  /**
   * External variables, if any.
   */
  private final List<XQueryRunnerVariable> variables;
  /**
   * Context item value, if any.
   */
  private final String contextItemValue;
  /**
   * Context item type, if any.
   */
  private final String contextItemType;

  public XQueryBaseXRunner(XQueryRunConfig config) throws IOException {
    this.configFile = config.getConfigFile();
    this.variables = config.getVariables();
    this.contextItemValue = config.isContextItemFromEditorEnabled() ? config.getContextItemText() :
            FileUtil.readFile(config.getContextItemFile());
    this.contextItemType = config.getContextItemType();
    contentFactory = new XQueryContentFactory(config);
  }

  /**
   * BaseX needs the base directory, where the configuration file .basex is located.
   * So we fetch the parent directory of the given configuration file.
   *
   * @param f .basex config file
   * @return parent folder of .basex
   */
  private String getPathFromFile(final String f) {
    File fi = new File(f);
    return fi.getParentFile().getAbsolutePath();
  }

  /**
   * Bind a variable to a BaseX Query processor.
   *
   * @param qp query processor
   * @throws Exception
   */
  private void bindVariables(QueryProcessor qp)
          throws Exception {
    NameExtractor nameExtractor = new NameExtractor();
    for (XQueryRunnerVariable variable : variables) {
      if (variable.ACTIVE) {
        QName name = nameExtractor.getName(variable.NAME, variable.NAMESPACE);
        qp.bind(name.toString(), variable.VALUE, variable.TYPE);
      }
    }
  }

  /**
   * Bind a context to the query.
   *
   * @param qp query processor
   * @throws QueryException binding exception
   */
  private void bindContext(QueryProcessor qp) throws QueryException {
    qp.context(contextItemValue, contextItemType);
  }

  public void run(final PrintStream out) throws Exception {
    // set a config file .basex, if given in the configuration
    if (this.configFile != "")
      System.setProperty("org.basex.path", getPathFromFile(this.configFile));

    QueryProcessor qp = new QueryProcessor(contentFactory.getXQueryContentAsString(), new Context());
    bindVariables(qp);
    bindContext(qp);
    Serializer ser = qp.getSerializer(out);

    final Iter it = qp.iter();
    for (Item item; (item = it.next()) != null; ) {
      ser.serialize(item);
    }

    ser.close();
    qp.close();
  }
}
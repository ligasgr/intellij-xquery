package org.intellij.xquery.runner.rt;

import java.io.PrintStream;

/**
 * Generic runner to execute XQuery.
 *
 * @author Dirk Kirsten, BaseX GmbH
 */
public interface XQueryGenericRunner {
  /**
   * Run the query and print to the given output stream
   *
   * @param out serialize the result to this stream
   * @throws Exception I/O exception
   */
  public void run(final PrintStream out) throws Exception;
}

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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class XQueryContentFactory {
  private XQueryRunConfig config;

  public XQueryContentFactory(XQueryRunConfig config) {
    this.config = config;
  }

  public InputStream getXQueryContentAsStream() throws FileNotFoundException {
    return new FileInputStream(config.getMainFile());
  }

  public String getXQueryContentAsString() throws IOException {
    InputStream in = getXQueryContentAsStream();
    java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
    String t = s.hasNext() ? s.next() : "";
    in.close();

    return t;
  }
}
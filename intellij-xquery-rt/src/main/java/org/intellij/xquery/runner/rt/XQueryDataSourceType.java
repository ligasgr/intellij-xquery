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

import org.intellij.xquery.runner.rt.datasource.*;

import javax.swing.*;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * User: ligasgr
 * Date: 02/10/13
 * Time: 14:19
 */
public enum XQueryDataSourceType {
  SAXON("Saxon", true, true, false, false, asList("saxon9he.jar"), true, SaxonXQDataSourceFactory.class),
  MARKLOGIC("MarkLogic", false, true, false, true, asList("marklogic-xqj-1.0.0.jar"), false, MarklogicXQDataSourceFactory.class),
  EXIST("eXist", false, true, false, true, asList("exist-xqj-1.0.1.jar"), false, ExistXQDataSourceFactory.class),
  BASEX("BaseX", false, true, false, true, asList("basex-xqj-1.2.3.jar"), false, BaseXXQDataSourceFactory.class),
  BASEX_EMBEDDED("BaseX (embedded)", true, false, true, false, null, false, null),
  SEDNA("Sedna", false, true, false, true, asList("sedna-xqj-1.0.0.jar"), false, SednaXQDataSourceFactory.class),
  ZORBA("Zorba", false, true, false, false, asList("zorba_xqj.jar", "zorba_api.jar"), false, ZorbaXQDataSourceFactory.class);

  private String presentableName;
  private boolean configFileSupported;
  private boolean userDefinedLibraryIsSupported;
  private boolean embeddedJarIsSupported;
  private boolean database;
  private final List<String> classpathEntries;
  private final boolean jarContainsXqjApi;
  private final Class<? extends XQDataSourceFactory> dataSourceFactoryClass;

  private XQueryDataSourceType(String presentableName, boolean configFileSupported, boolean userDefinedLibraryIsSupported,
                               boolean embeddedJarIsSupported, boolean database, List<String> classpathEntries,
                               boolean jarContainsXqjApi, Class<? extends XQDataSourceFactory> dataSourceFactoryClass) {
    this.presentableName = presentableName;
    this.configFileSupported = configFileSupported;
    this.userDefinedLibraryIsSupported = userDefinedLibraryIsSupported;
    this.embeddedJarIsSupported = embeddedJarIsSupported;
    this.database = database;
    this.classpathEntries = classpathEntries;
    this.jarContainsXqjApi = jarContainsXqjApi;
    this.dataSourceFactoryClass = dataSourceFactoryClass;
  }

  public String getPresentableName() {
    return presentableName;
  }

  public boolean configFileIsSupported() {
    return configFileSupported;
  }

  public boolean userDefinedLibraryIsSupported() {
    return userDefinedLibraryIsSupported;
  }

  public boolean embeddedJarIsSupported() {
    return embeddedJarIsSupported;
  }

  public boolean connectionPropertiesAreSupported() {
    return database;
  }

  public Icon getIcon() {
    return null;
  }

  public static XQueryDataSourceType getForName(String name) {
    for (XQueryDataSourceType dataSourceType : XQueryDataSourceType.values()) {
      if (dataSourceType.toString().equals(name))
        return dataSourceType;
    }
    return null;
  }

  public List<String> getClasspathEntries() {
    return classpathEntries;
  }

  public boolean jarContainsXqjApi() {
    return jarContainsXqjApi;
  }

  public Class<? extends XQDataSourceFactory> getDataSourceFactoryClass() {
    return dataSourceFactoryClass;
  }
}

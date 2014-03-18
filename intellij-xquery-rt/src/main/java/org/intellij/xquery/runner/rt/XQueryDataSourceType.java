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

package org.intellij.xquery.runner.rt;

import org.intellij.xquery.runner.rt.vendor.saxon.SaxonRunnerAppFactory;
import org.intellij.xquery.runner.rt.xqj.XQJRunnerAppFactory;
import org.intellij.xquery.runner.rt.xqj.datasource.BaseXLocalXQDataSourceFactory;
import org.intellij.xquery.runner.rt.xqj.datasource.BaseXXQDataSourceFactory;
import org.intellij.xquery.runner.rt.xqj.datasource.ExistXQDataSourceFactory;
import org.intellij.xquery.runner.rt.xqj.datasource.MarklogicXQDataSourceFactory;
import org.intellij.xquery.runner.rt.xqj.datasource.SaxonXQDataSourceFactory;
import org.intellij.xquery.runner.rt.xqj.datasource.SednaXQDataSourceFactory;
import org.intellij.xquery.runner.rt.xqj.datasource.XQDataSourceFactory;
import org.intellij.xquery.runner.rt.xqj.datasource.ZorbaXQDataSourceFactory;

import javax.swing.Icon;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * User: ligasgr
 * Date: 02/10/13
 * Time: 14:19
 */
public enum XQueryDataSourceType {
    SAXON("Saxon", true, false, asList("saxon9he.jar"), true, SaxonXQDataSourceFactory.class),
    MARKLOGIC("MarkLogic", false, true, asList("marklogic-xqj-1.0.0.jar"), false, MarklogicXQDataSourceFactory.class),
    EXIST("eXist", false, true, asList("exist-xqj-1.0.1.jar"), false, ExistXQDataSourceFactory.class),
    BASEX("BaseX", false, true, asList("basex-xqj-1.2.3.jar"), false, BaseXXQDataSourceFactory.class),
    SEDNA("Sedna", false, true, asList("sedna-xqj-1.0.0.jar"), false, SednaXQDataSourceFactory.class),
    ZORBA("Zorba", false, false, asList("zorba_xqj.jar", "zorba_api.jar"), false, ZorbaXQDataSourceFactory.class),
    SAXON_NATIVE("Saxon (native)", true, false, asList("saxon9he.jar"), SaxonRunnerAppFactory.class),
    BASEX_LOCAL("BaseX (embedded)", false, false, asList("basex-xqj-1.3.0.jar", "basex-7.8.1.jar"), false,
            BaseXLocalXQDataSourceFactory.class);
    private final List<String> classpathEntries;
    private final boolean jarContainsXqjApi;
    private final Class<? extends XQDataSourceFactory> xqDataSourceFactoryClass;
    private final Class<? extends RunnerAppFactory> runnerAppFactoryClass;
    private String presentableName;
    private boolean configFileSupported;
    private boolean connectionPropertiesSupported;

    private XQueryDataSourceType(String presentableName, boolean configFileSupported,
                                 boolean connectionPropertiesSupported,
                                 List<String> classpathEntries,
                                 Class<? extends RunnerAppFactory> runnerAppFactoryClass) {
        this(presentableName, configFileSupported, connectionPropertiesSupported, classpathEntries, true, null,
                runnerAppFactoryClass);
    }

    private XQueryDataSourceType(String presentableName, boolean configFileSupported,
                                 boolean connectionPropertiesSupported,
                                 List<String> classpathEntries, boolean jarContainsXqjApi,
                                 Class<? extends XQDataSourceFactory> xqDataSourceFactoryClass) {
        this(presentableName, configFileSupported, connectionPropertiesSupported, classpathEntries, jarContainsXqjApi,
                xqDataSourceFactoryClass, XQJRunnerAppFactory.class);
    }

    private XQueryDataSourceType(String presentableName, boolean configFileSupported,
                                 boolean connectionPropertiesSupported,
                                 List<String> classpathEntries, boolean jarContainsXqjApi,
                                 Class<? extends XQDataSourceFactory> xqDataSourceFactoryClass,
                                 Class<? extends RunnerAppFactory> runnerAppFactoryClass) {
        this.presentableName = presentableName;
        this.configFileSupported = configFileSupported;
        this.connectionPropertiesSupported = connectionPropertiesSupported;
        this.classpathEntries = classpathEntries;
        this.jarContainsXqjApi = jarContainsXqjApi;
        this.xqDataSourceFactoryClass = xqDataSourceFactoryClass;
        this.runnerAppFactoryClass = runnerAppFactoryClass;
    }

    public static XQueryDataSourceType getForName(String name) {
        for (XQueryDataSourceType dataSourceType : XQueryDataSourceType.values()) {
            if (dataSourceType.toString().equals(name)) {
                return dataSourceType;
            }
        }
        return null;
    }

    public String getPresentableName() {
        return presentableName;
    }

    public boolean configFileIsSupported() {
        return configFileSupported;
    }

    public boolean connectionPropertiesAreSupported() {
        return connectionPropertiesSupported;
    }

    public Icon getIcon() {
        return null;
    }

    public List<String> getClasspathEntries() {
        return classpathEntries;
    }

    public boolean jarContainsXqjApi() {
        return jarContainsXqjApi;
    }

    public Class<? extends XQDataSourceFactory> getXQDataSourceFactoryClass() {
        return xqDataSourceFactoryClass;
    }

    public Class<? extends RunnerAppFactory> getRunnerAppFactoryClass() {
        return runnerAppFactoryClass;
    }
}

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

package org.intellij.xquery.runner;

import com.intellij.execution.CantRunException;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.application.ex.ApplicationManagerEx;
import com.intellij.openapi.extensions.PluginId;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourcesSettings;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;

import java.io.File;
import java.io.FilenameFilter;
import java.util.*;

class XQueryRunnerClasspathEntryGenerator {

    private static final String XQJ_API_1_0_JAR = "xqj-api-1\\.0\\.jar";
    private static final String XQJ2_0_2_0_JAR = "xqj2-0\\.2\\.0\\.jar";
    private static final String INTELLIJ_XQUERY_RT_JAR = "intellij-xquery-rt\\.jar";
    private static final String DBGP_INTERFACES_JAR = "dbgp-interfaces-.+\\.jar";
    private static final String NETTY = "netty-all-.+\\.jar";

    public String generateRunnerClasspathEntries(XQueryRunConfiguration configuration) throws CantRunException {
        XQueryDataSourceConfiguration dataSourceConfiguration = getDataSourceConfiguration(configuration);
        validate(dataSourceConfiguration);
        List<String> pluginJarsEntries = new ArrayList<String>();
        pluginJarsEntries.add(INTELLIJ_XQUERY_RT_JAR);
        pluginJarsEntries.add(DBGP_INTERFACES_JAR);
        pluginJarsEntries.add(NETTY);
        addXqjApiJarsIfNeeded(dataSourceConfiguration, pluginJarsEntries);
        if (!dataSourceConfiguration.USER_DEFINED_LIBRARY_ENABLED) {
            pluginJarsEntries.addAll(dataSourceConfiguration.TYPE.getClasspathEntries());
        }
        Set<String> classPathEntries = getPluginInternalJarEntries(getPluginPath(), pluginJarsEntries);
        if (dataSourceConfiguration.USER_DEFINED_LIBRARY_ENABLED) {
            classPathEntries.addAll(dataSourceConfiguration.USER_DEFINED_LIBRARY_PATHS);
        }
        return separateEntriesWithPathSeparator(classPathEntries);
    }

    private void addXqjApiJarsIfNeeded(XQueryDataSourceConfiguration dataSourceConfiguration, List<String> entries) {
        if (!dataSourceConfiguration.TYPE.jarContainsXqjApi()) {
            entries.add(XQJ_API_1_0_JAR);
            entries.add(XQJ2_0_2_0_JAR);
        }
    }

    private void validate(XQueryDataSourceConfiguration dataSourceConfiguration) throws CantRunException {
        if (dataSourceConfiguration == null) {
            throw new CantRunException("Configuration is not valid");
        }
    }

    private XQueryDataSourceConfiguration getDataSourceConfiguration(XQueryRunConfiguration configuration) {
        return XQueryDataSourcesSettings
                .getInstance()
                .getDataSourceConfigurationForName(configuration.getDataSourceName());
    }

    private String separateEntriesWithPathSeparator(Set<String> entrySet) {
        StringBuilder entries = new StringBuilder();
        boolean first = true;
        for (String entry : entrySet) {
            if (first) first = false;
            else entries.append(File.pathSeparatorChar);
            entries.append(entry);
        }

        return entries.toString();
    }

    private Set<String> getPluginInternalJarEntries(File pluginPath, List<String> entryList) throws CantRunException {
        Set<String> entrySet = new LinkedHashSet<String>();
        for (String entry : entryList) {
            entrySet.add(getClasspathEntryFileIfExists(pluginPath, entry).getAbsolutePath());
        }
        return entrySet;
    }

    private File getClasspathEntryFileIfExists(File pluginPath, String jarName) throws CantRunException {
        File libraryDirectory = new File(pluginPath, "lib");
        File[] files = libraryDirectory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.matches(jarName);
            }
        });
        if (files.length > 1) {
            throw new CantRunException("There should be just one matching library for " + jarName + ", found: " + files.length);
        }
        File rtJarFile = files.length == 1 ? files[0] : new File(libraryDirectory, jarName);
        if (rtJarFile.exists()) return rtJarFile;
        File classesDirectory = new File(pluginPath, "classes");
        if (classesDirectory.exists()) return classesDirectory;
        if (isTestRun(pluginPath)) {
            return pluginPath;
        } else {
            throw new CantRunException("Runtime classes not found");
        }
    }

    boolean isTestRun(File pluginPath) {
        return ApplicationManagerEx.getApplicationEx().isInternal() && new File(pluginPath, "org").exists();
    }

    File getPluginPath() {
        final PluginId pluginId = PluginManager.getPluginByClassName(getClass().getName());
        final IdeaPluginDescriptor descriptor = PluginManager.getPlugin(pluginId);
        return descriptor.getPath();
    }
}

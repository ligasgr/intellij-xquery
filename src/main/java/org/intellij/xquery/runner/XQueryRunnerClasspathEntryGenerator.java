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
import java.util.*;

/**
 * User: ligasgr
 * Date: 29/09/13
 * Time: 02:42
 */
class XQueryRunnerClasspathEntryGenerator {

    private static final String XQJ_API_1_0_JAR = "xqj-api-1.0.jar";
    private static final String XQJ2_0_2_0_JAR = "xqj2-0.2.0.jar";
    private static final String INTELLIJ_XQUERY_RT_JAR = "intellij-xquery-rt.jar";

    public String generateRunnerClasspathEntries(XQueryRunConfiguration configuration) throws CantRunException {
        XQueryDataSourceConfiguration dataSourceConfiguration = getDataSourceConfiguration(configuration);
        validate(dataSourceConfiguration);
        List<String> pluginJarsEntries = new ArrayList<String>();
        addRunnerRtJar(pluginJarsEntries);
        addXqjApiJarsIfNeeded(dataSourceConfiguration, pluginJarsEntries);
        if (!dataSourceConfiguration.USER_DEFINED_LIBRARY_ENABLED) {
            addDataSourceClassPathEntries(dataSourceConfiguration, pluginJarsEntries);
        }
        Set<String> classPathEntries = getPluginInternalJarEntries(getPluginPath(), pluginJarsEntries);
        if (dataSourceConfiguration.USER_DEFINED_LIBRARY_ENABLED) {
            classPathEntries.addAll(dataSourceConfiguration.USER_DEFINED_LIBRARY_PATHS);
        }
        return separateEntriesWithPathSeparator(classPathEntries);
    }

    private void addRunnerRtJar(List<String> entries) {
        entries.add(INTELLIJ_XQUERY_RT_JAR);
    }

    private void addDataSourceClassPathEntries(XQueryDataSourceConfiguration dataSourceConfiguration, List<String>
            entries) {
        for (String entry : dataSourceConfiguration.TYPE.getClasspathEntries()) {
            entries.add(entry);
        }
    }

    private void addXqjApiJarsIfNeeded(XQueryDataSourceConfiguration dataSourceConfiguration, List<String> entries) {
        if (!dataSourceConfiguration.TYPE.jarContainsXqjApi()) {
            entries.add(XQJ_API_1_0_JAR);
            entries.add(XQJ2_0_2_0_JAR);
        }
    }

    private File getPluginPath() {
        final PluginId pluginId = PluginManager.getPluginByClassName(getClass().getName());
        final IdeaPluginDescriptor descriptor = PluginManager.getPlugin(pluginId);
        return descriptor.getPath();
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
        StringBuffer entries = new StringBuffer();
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
        final char c = File.separatorChar;
        File rtJarFile = new File(pluginPath, "lib" + c + jarName);
        if (rtJarFile.exists()) return rtJarFile;
        File classesDirectory = new File(pluginPath, "classes");
        if (classesDirectory.exists()) return classesDirectory;
        if (isTestRun(pluginPath)) {
            return pluginPath;
        } else {
            throw new CantRunException("Runtime classes not found");
        }
    }

    private boolean isTestRun(File pluginPath) {
        return ApplicationManagerEx.getApplicationEx().isInternal() && new File(pluginPath, "org").exists();
    }
}

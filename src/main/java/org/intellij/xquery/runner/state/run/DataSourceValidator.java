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

package org.intellij.xquery.runner.state.run;

import com.intellij.execution.configurations.RuntimeConfigurationError;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourcesSettings;

/**
 * User: ligasgr
 * Date: 18/11/13
 * Time: 13:36
 */
public class DataSourceValidator
{

	public static final String DATA_SOURCE_MISSING_MESSAGE = "Data source not specified";

	public void validate (XQueryDataSourcesSettings settings, String dataSourceName)
		throws RuntimeConfigurationError
	{
		if (dataSourceName == null) {
			throw new RuntimeConfigurationError (DATA_SOURCE_MISSING_MESSAGE);
		}

		// Throws an exception if a datasource with the given name is not configured
		settings.getDataSourceConfigurationForName (dataSourceName);
	}
}

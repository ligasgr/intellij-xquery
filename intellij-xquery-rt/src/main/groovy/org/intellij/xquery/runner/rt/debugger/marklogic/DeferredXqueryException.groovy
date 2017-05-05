/*
 * Copyright 2017 OverStory Ltd <copyright@overstory.co.uk> and other contributors
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

package org.intellij.xquery.runner.rt.debugger.marklogic

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 5/2/17
 * Time: 4:13 PM
 */
class DeferredXqueryException extends Exception
{
	private final Map<String,String> status

	DeferredXqueryException (Map<String, String> status)
	{
		super (status.get ('error-msg') ?: "Unknown error")

		this.status = status
	}

	Map<String,String> getQueryStatus()
	{
		status
	}
}

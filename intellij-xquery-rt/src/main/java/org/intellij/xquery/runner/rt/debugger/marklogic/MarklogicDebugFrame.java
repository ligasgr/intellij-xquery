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

package org.intellij.xquery.runner.rt.debugger.marklogic;

import org.intellij.xquery.runner.rt.debugger.DebugFrame;
import org.intellij.xquery.runner.rt.debugger.Variable;

import java.math.BigInteger;
import java.util.*;

import static java.util.Optional.ofNullable;
import static org.intellij.xquery.runner.rt.debugger.LogUtil.log;

public class MarklogicDebugFrame implements DebugFrame
{
	private MarkLogicDebugConnector connector;
	private BigInteger requestId;
	private int lineNumber;
	private String uri;
	private String functionName;
	private List<Variable> variables;

	@Override
	public int getLineNumber ()
	{
		return lineNumber;
	}

	@Override
	public String getUri ()
	{
		return uri;
	}

	@Override
	public String getFunctionName ()
	{
		return functionName;
	}

	@Override
	public Optional<Variable> eval (String expressionString)
	{
		try {
			List<String> valueResult = connector.requestValueAndType (requestId, expressionString);

			return Optional.of (new Variable ("(anon)", valueResult.get (1), valueResult.get (0)));
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	@Override
	public List<Variable> getVariables ()
	{
		return variables;
	}

	@Override
	public String toString ()
	{
		return "MarklogicDebugFrame{" + "lineNumber=" + lineNumber + ", uri='" + uri + '\'' + ", functionName='" + functionName + "', vars=" + variables + '}';
	}
}

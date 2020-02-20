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

package org.intellij.xquery.runner.rt.vendor.marklogic;

import com.marklogic.xcc.*;
import com.marklogic.xcc.exceptions.XQueryException;
import com.marklogic.xcc.types.ValueType;
import com.marklogic.xcc.types.XName;
import com.marklogic.xcc.types.XdmValue;
import org.intellij.xquery.runner.rt.RunnerApp;
import org.intellij.xquery.runner.rt.XQueryItemType;
import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.XQueryRunnerVariable;
import org.intellij.xquery.runner.rt.debugger.marklogic.MarkLogicRunMode;
import static org.intellij.xquery.runner.rt.debugger.marklogic.MarkLogicRunMode.*;

import java.io.PrintStream;
import java.net.URI;

import static org.intellij.xquery.runner.rt.FileUtil.readFile;

public class MarklogicRunnerApp implements RunnerApp
{
	public static final String XCC_CONNECTION_URI_TEMPLATE = "xcc://%s:%s@%s:%s/%s";
	protected final XQueryRunConfig config;
	protected final PrintStream output;
	private final MarklogicTypeMapper typeMapper = new MarklogicTypeMapper();

	public MarklogicRunnerApp (XQueryRunConfig config, PrintStream output)
	{
		this.config = config;
		this.output = output;
	}

	@Override
	public void runApp() throws Exception
	{
		ContentSource contentSource = getContentSource (config);

		try (Session session = contentSource.newSession()) {
			Request request;
			MarkLogicRunMode runMode = config.getMlDebugRunMode();

			switch (runMode){
			case INVOKE:
				request = session.newModuleInvoke (config.getMainFile().substring (config.getMlDebugAppserverRoot().length()));
				break;
			case ADHOC:
				request = session.newAdhocQuery (readFile (config.getMainFile()));
				break;
			default:
				throw new IllegalStateException ("Run Mode '" + runMode + "' is only for debugging.  Choose '" + ADHOC + "' or '" + INVOKE + "' for normal Run");
			}

			setRequestVariables (request);

			new MarkLogicResultFormatter (session.submitRequest (request)).outputAsString (output);
		} catch (IllegalStateException e) {
			System.err.println (e.getMessage());
		} catch (XQueryException e) {
			System.err.println (e.toString());
		}
	}

	public static ContentSource getContentSource (XQueryRunConfig config) throws Exception
	{
		URI uri = new URI (String.format (XCC_CONNECTION_URI_TEMPLATE, config.getUsername(), config.getPassword(), config.getHost(), config.getPort(), config.getDatabaseName()));

		return ContentSourceFactory.newContentSource (uri);
	}

	protected void setRequestVariables (Request request)
	{
		for (XQueryRunnerVariable variable : config.getVariables()) {
			if (variable.ACTIVE) {
				request.setNewVariable (getName (variable.NAME, variable.NAMESPACE), getXdmValue (variable.TYPE, variable.VALUE));
			}
		}
	}

	private XdmValue getXdmValue (String type, String value)
	{
		XQueryItemType itemType = XQueryItemType.valueFor (type);
		ValueType xdmType = typeMapper.getType (itemType);
		return ValueFactory.newValue (xdmType, value);
	}

	private static XName getName (String name, String namespace)
	{
		return new XName (namespace, name);
	}
}

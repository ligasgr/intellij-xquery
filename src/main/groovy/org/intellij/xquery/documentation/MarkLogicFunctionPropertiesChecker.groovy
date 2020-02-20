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

package org.intellij.xquery.documentation

import groovy.util.slurpersupport.GPathResult
import groovy.util.slurpersupport.NodeChild

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 5/11/17
 * Time: 6:05 PM
 * This is a utility class to spot any new function doc properties that might appear in future
 * MarkLogic releases.  It is not a part of the plugin and is not run by the build.
 */
class MarkLogicFunctionPropertiesChecker
{
	private static final String DOCSNS = 'http://marklogic.com/xdmp/apidoc'
	private final Set<String> knownTags = [
	        'apidocs', 'categories', 'category', 'functions', 'function', 'summary',
		'return', 'name', 'header', 'headers', 'example', 'see-also', 'see-also-list',
		'privilege', 'response', 'usage', 'param', 'params'

	]
	private final Set<String> tagsSeen = []

	static void main (String[] args)
	{
		if (args.length != 1) {
			println "Usage: <functions XML file>"
		}

		new MarkLogicFunctionPropertiesChecker().run (args [0])
	}

	void run (String xmlPath)
	{
		GPathResult xml = new XmlSlurper (false, true).parse (new File (xmlPath))
			.declareNamespace ([apidoc: DOCSNS, xhtml: 'http://www.w3.org/1999/xhtml'])

		xml.depthFirst().findAll { GPathResult it ->
			String namespace = ((NodeChild)(it.list()[0])).namespaceURI()

			if (DOCSNS.equals (namespace)) {
				tagsSeen << it.name()
			}
		}

		tagsSeen.each {
			if ( ! knownTags.contains (it)) {
				println (it)
			}
		}
	}
}

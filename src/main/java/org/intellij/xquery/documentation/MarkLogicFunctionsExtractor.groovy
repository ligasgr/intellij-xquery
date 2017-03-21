/*
 * Copyright (c) 2017 OverStory Ltd <ron@overstory.co.uk> and other contributors
 *  (see the CONTRIBUTORS file).
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.intellij.xquery.documentation

import groovy.util.slurpersupport.GPathResult
import groovy.xml.StreamingMarkupBuilder

import java.util.zip.ZipEntry
import java.util.zip.ZipFile

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 3/20/17
 * Time: 11:03 PM
 */
class MarkLogicFunctionsExtractor
{
	List<GPathResult> modules = []
	List<GPathResult> functions = []
	Map<String,String> categoryMap = [:]

	public static void main (String[] args)
	{
		if (args.length != 2) {
			println "Usage: <input docs zip file> <output XML file>"
		}

		new MarkLogicFunctionsExtractor().run (args [0], args [1])
	}

	void run (String inputZipPath, String outputXmlPath)
	{
		ZipFile zipFile = new ZipFile (inputZipPath);

		zipFile.entries ().each { ZipEntry entry ->
			String name = entry.name

			if (name.contains ('/pubs/raw/apidoc/') && name.endsWith ('.xml')) {
				GPathResult xml = new XmlSlurper (false, true).parse (zipFile.getInputStream (entry))
					.declareNamespace ([apidoc: 'http://marklogic.com/xdmp/apidoc', xhtml: 'http://www.w3.org/1999/xhtml'])

				if (xml.name() == 'module') {
					xml.'apidoc:function'.each {
						functions << it

						categoryMap [it.'@lib'.toString()] = it.'@category'.toString()
					}
				}
			}
		}

		def builder = new StreamingMarkupBuilder()

		def xml = builder.bind {
			mkp.declareNamespace ([apidoc: 'http://marklogic.com/xdmp/apidoc', xhtml: 'http://www.w3.org/1999/xhtml'])
			delegate.'apidoc:apidocs' {
				delegate.'apidoc:categories' {
					categoryMap.each { String key, String value ->
						'apidoc:category' (prefix: key, desc: value)
					}
				}

				delegate.'apidoc:functions' {
					functions.each {
						mkp.yield (it)
					}
				}
			}
		}

		Writer writer = new FileWriter (outputXmlPath)

		writer << xml.toString()

		writer.close()
	}
}

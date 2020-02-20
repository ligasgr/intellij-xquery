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
 *
 * This class reads a Docs zip file downloaded from the MarkLogic developer site (http://docs.marklogic.com/) and extracts
 * out the XML describing API functions.  These are combined into one large XML file which is then committed as a resource
 * for use at runtime by the plugin, to generate pop-up documentation for functions (View > Quick Documentation).
 *
 * This class now also generates a new Builtin Functions (BIF) list from the documentation and writes it to that path
 * given in the third argument.
 *
 * This class is not used by the plugin at runtime.  Setup a Run Configuration to run this class as a Java application
 * with the path to the zip file as the first argument and the path to write the XML file to as the second argument.
 * The XML should be placed at src/main/resources/documentation/marklogic-functions.xml to be packaged into the plugin jar.
 */
class MarkLogicFunctionsExtractor
{
	static void main (String[] args)
	{
		if (args.length != 3) {
			println "Usage: <input docs zip file> <output XML file> <output BIF file"
			return
		}

		println ("in: ${args[0]}, XML out: ${args[1]}, BIF out: ${args[2]}")

		new MarkLogicFunctionsExtractor().run (args [0], args [1], args[2])
	}

	void run (String inputZipPath, String outputXmlPath, String outputBifPath)
	{
		List<GPathResult> functions = []
		Map<String,Map<String,Object>> categoryMap = [:]
		List<String> bifs = []

		ZipFile zipFile = new ZipFile (inputZipPath)

		// Iterate over each XML file in the Docs zip file
		zipFile.entries().each { ZipEntry entry ->
			String name = entry.name

			// Only look at raw API Doc XML files
			if (name.contains ('/pubs/raw/apidoc/') && name.endsWith ('.xml')) {
				GPathResult xml = new XmlSlurper (false, true).parse (zipFile.getInputStream (entry))
					.declareNamespace ([apidoc: 'http://marklogic.com/xdmp/apidoc', xhtml: 'http://www.w3.org/1999/xhtml'])

				// Where the top-level element is a 'module'
				if (xml.name() == 'module') {
					// Look at each function
					xml.'apidoc:function'.each { GPathResult it ->
						// There are a couple of stub functions with no names or info, ignore
						if (( ! it.'@name') || (! it.'@name'.text())) return

						GPathResult function = filterForXquery (it, xml)

						if (function == null) {
							return		// Skip the one that are not XQuery
						}

						functions << function

						String category = function.'@category'.toString()
						Map<String,Object> props = categoryMap.get (category)

						if (props == null) {
							categoryMap.put (category, [desc: function.'@category'.toString(), bucket: function.'@bucket'.toString(), count: 0])
						} else {
							props.count += 1

							if ((props.bucket == null) && (function.'@bucket'.toString())) {
								props.bucket = function.'@bucket'.toString()
							}
						}

						String bif = extractBif (function)

						if (bif) bifs << bif
					}
				}
			}
		}

		// Serialize out the filtered function definitions
		def builder = new StreamingMarkupBuilder()

		def xml = builder.bind {
			mkp.declareNamespace ([apidoc: 'http://marklogic.com/xdmp/apidoc', xhtml: 'http://www.w3.org/1999/xhtml'])
			delegate.'apidoc:apidocs' ('docs-source': inputZipPath.substring (inputZipPath.lastIndexOf ('/') + 1)) {
				mkp.yield ('\n')

				delegate.'apidoc:categories' {
					categoryMap.each { String key, Map<String,Object> props ->
						mkp.yield ('\n\t')
						'apidoc:category' (name: key, bucket: props.bucket, count: props.count)
					}

					mkp.yield ('\n')
				}

				mkp.yield ('\n')

				delegate.'apidoc:functions' {
					functions.each {
						mkp.yield ('\n\n')
						mkp.yield (it)
					}

					mkp.yield ('\n\n')
				}

				mkp.yield ('\n')
			}
		}

		// Write the XML function descriptors to disk
		Writer writer = new FileWriter (outputXmlPath)

		writer << xml.toString()
		writer.close()

		// Sort the BuiltIn Functions parameter definition file and write it out
		bifs.sort()

		writer = new FileWriter (outputBifPath)

		bifs.each { String bif ->
			writer << bif
			writer << '\n'
		}

		writer.close()
	}

	// This closure deletes elements from the given tree, it does not copy and transform
	Closure<GPathResult> filterForXquery = { GPathResult element, GPathResult xml ->
		if (element.'@class'.text() && (element.'@class'.text() != 'xquery')) {
			// Not XQuery-related, snip it out of the tree
			element.replaceNode { /* empty */ }
			null
		} else {
			if ((element.children().size() == 0) && (element.'@copy-content-from'.text())) {
				// This XQuery function definition is empty and refers to a JavScript
				// method definition to get the content.  Look it up, copy its children,
				// then serialize and re-parse the updated node.  Kiind of a faff, but
				// that's XmlSlurper for you.
				GPathResult method = findMethod (element.'@copy-content-from'.text(), xml)

				// Filter out the non-XQuery stuff before copying
				method = filterForXquery (method, xml)

				// Copy the JavavScript method's children to the empty function def
				method.children().each { Object it ->
					element << it
				}

				// XmlSlurper/GPathResult will not see updates, so we need to
				// serialize out the new state and parse it again, and explicitly
				// copy the namespaces as well (that seems to be a bug).
				String tmpXml = new StreamingMarkupBuilder().bind {
					mkp.yield (element)
				}.toString()

				element = new XmlSlurper().parseText (tmpXml)

				element.declareNamespace ([apidoc: 'http://marklogic.com/xdmp/apidoc', xhtml: 'http://www.w3.org/1999/xhtml'])
			} else {
				// Standard function def, filter out non-XQuery params and examples
				element.children().each { Object it ->
					filterForXquery (it as GPathResult, xml)
				}
			}

			element		// Return the (possibly mutated) tree element
		}
	}

	// Find the JavaScript apidoc:method referenced by an @copy-content-from attribute
	Closure<GPathResult> findMethod = { String refedMethod, GPathResult xml ->
		int dotIndex = refedMethod.indexOf ('.')

		if (dotIndex == -1) return null

		// Only split on the first dot, the "name" may contain further dots
		String object = refedMethod.substring (0, dotIndex)
		String name = refedMethod.substring (dotIndex + 1)

		xml.'apidoc:method'.find {
			(it.'@object'.text() == object) && (it.'@name'.text() == name)
		} as GPathResult
	}

	// Don't generate BIFs for these packages, they're REST endpoints
	private static final List<String> ignoreLibs = ['manage', 'rest-client']

	// Generate the list of BuiltIn Functions from the API specs
	// These look like: xdmp#document-insert#3#$uri as xs:string, $root as node(), $options as (element()|map:map)?#empty-sequence()
	static String extractBif (GPathResult function)
	{
		if (ignoreLibs.contains (function.'@lib'.text())) return null

		String lib = function.'@lib'.text()
		if (! lib) lib = function.'@object'.text()

		StringBuilder sb = new StringBuilder()

		sb.append (lib.trim()).append ('#').append (function.'@name'.text().trim()).append ('#')

		List<String> paramList = []

		function.'apidoc:params'.'apidoc:param'.each { GPathResult param ->
			if (( ! param.'@class'.text()) || (param.'@class'.text() == 'xquery')) {
				boolean optional = (param.'@optional'.text() == 'true')
				StringBuffer sb2 = new StringBuffer()

				if (optional) sb2.append ('[')
				sb2.append ('$').append (param.'@name'.text().trim()).append (' as ').append (param.'@type'.text().trim())
				if (optional) sb2.append (']')

				paramList << sb2.toString()

//				paramList << '$' + param.'@name'.text().trim() + ' as ' + param.'@type'.text().trim()
			}
		}

		sb.append (paramList.size()).append ('#')

		boolean notFirst = false

		paramList.each { String param ->
			if (notFirst) sb.append (', ')
			notFirst = true

			sb.append (param)
		}

		if (function.return.isEmpty()) {
			sb.append ('#item()*')
		} else {
			function.return.each { GPathResult ret ->
				if ((ret.attributes().size() == 0) || (ret.'@class'.text() == 'xquery')) {
					sb.append ('#').append (ret.text().trim())
				}
			}
		}

		sb.toString()
	}
}

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

package org.intellij.xquery.runner.rt.vendor.marklogic

import com.marklogic.xcc.ResultItem
import com.marklogic.xcc.ResultSequence

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 5/23/17
 * Time: 12:17 AM
 */
class MarkLogicResultFormatter
{
	ResultSequence rs

	MarkLogicResultFormatter (ResultSequence rs)
	{
		this.rs = rs
	}

	void outputAsString (PrintStream output)
	{
		switch (rs.size()) {
		case 0:
			output.println ('(empty)')
			break
		case 1:
			output.println (formattedValue (rs.resultItemAt (0)))
			break;
		default:
			rs.toResultItemArray().eachWithIndex { ResultItem item, int index ->
				String suffix = " ${index}: ${item.itemType}"

				output.print (separator (100 - suffix.length()))
				output.println (suffix)
				output.println (formattedValue (item))
			}
		}
	}

	private static String formattedValue (ResultItem item)
	{
		switch (item.itemType.toString()) {
		case 'element()':
			prettyPrintXml (item.asString())
			break
		case 'document-node()':
			prettyPrintXml (item.asString(), true)
			break
		default:
			item.asString()
		}
	}

	private static String prettyPrintXml (String src, boolean docType = false)
	{
		StringWriter out = new StringWriter()
		XmlNodePrinter nodePrinter = new XmlNodePrinter (new PrintWriter (out))

		nodePrinter.namespaceAware = true
//		nodePrinter.preserveWhitespace = true

		nodePrinter.print (new XmlParser (false,true, docType).parseText (src))

		out.toString().trim()
	}

	private static String separator (int length)
	{
		StringBuilder sb = new StringBuilder()

		(1..length).each {
			sb.append ('\u2014')
		}

		sb.toString()
	}
}

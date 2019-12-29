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

package org.intellij.xquery.documentation;

import com.intellij.util.containers.OrderedSet;
import org.jetbrains.annotations.NotNull;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.InputStream;
import java.util.*;

import static org.intellij.xquery.runner.rt.debugger.LogUtil.log;

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 4/22/12
 * Time: 6:02 PM
 */
public class MarkLogicFunctionDefs
{
	private static final MarkLogicFunctionDefs INSTANCE = new MarkLogicFunctionDefs();

	public static MarkLogicFunctionDefs instance()
	{
		return INSTANCE;
	}

	// ------------------------------------------------------------

	private static final String ML_FUNCTIONS_PATH = "documentation/marklogic-functions.xml";

	private final SAXParserFactory spf = SAXParserFactory.newInstance();
	private final Map<String,Category> categoryMap = new HashMap<>();
	private final List<Function> functions;
	private final Map<String, Function> functionMap = new HashMap<>();

	// ------------------------------------------------------------

	public Map<String,Category> getCategoryMap()
	{
		return Collections.unmodifiableMap (categoryMap);
	}

	public List<Function> getFunctions()
	{
		return Collections.unmodifiableList (functions);
	}

	public Function getFunction (String name)
	{
		return functionMap.get (name);
	}

	public List<Function> getFunctionsForPrefix (String prefix)
	{
		List<Function> list = new ArrayList<Function>();

		for (Function func : functions) {
			if (func.prefix.equals (prefix)) list.add (func);
		}

		return Collections.unmodifiableList (list);
	}

	// ------------------------------------------------------------
	// Private constructor

	private MarkLogicFunctionDefs()
	{
		functions = loadFunctions (ML_FUNCTIONS_PATH);
	}

	private List<Function> loadFunctions (String path)
	{
		List<Function> functions = new OrderedSet<>();

		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream (path);

			if (is == null) {
				log ("FunctionDefs.loadFunctions: could not load: " + path);
				return functions;
			}

			FunctionDefFunctionParser parser = new FunctionDefFunctionParser (functions, functionMap, categoryMap);
			SAXParser saxParser = spf.newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();
			xmlReader.setContentHandler (parser);
			xmlReader.parse (new InputSource (is));
			is.close();
		} catch (Exception e) {
			log ("Problem loading functions XML from '" + path + "': " + e);
			e.printStackTrace();
		}

		if (functions.size() > 0) {
			Collections.sort (functions);
			return functions;
		} else {
			return functions;
		}
	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------

	public static class Function implements Comparable<Function>
	{
		private final String docsSource;
		private final List<Parameter> parameters = new ArrayList<>();
		private final List<Example> examples = new ArrayList<>();
		private final List<String> seeAlsos = new ArrayList<>();
		private String localName;
		private final String fullName;
		private final String prefix;
		private final Category category;
		private final boolean priv;
		private final boolean hidden;
		private String returnType = null;
		private boolean varargs = false;

		private String summary = "";
		private String usage = null;
		private String algorithm = null;
		private String privilege = null;
		private int minParamCount = 0;

		public Function (String docsSource, String prefix, String localName, String fullName, boolean priv, boolean hidden, String returnType, Category category)
		{
			this.prefix = prefix;
			this.localName = localName;
			this.fullName = fullName;
			this.priv = priv;
			this.hidden = hidden;
			this.returnType = returnType;
			this.category = category;
			this.docsSource = docsSource;
		}

		void addParam (Parameter param)
		{
			parameters.add (param);

			if ( ! param.isOptional()) minParamCount++;

			if (param.isVararg()) varargs = true;
		}

		void addExample (Example example)
		{
			examples.add (example);
		}

		void addSeeAlso (String text)
		{
			seeAlsos.add (text);
		}

		public String getLocalName()
		{
			return localName;
		}

		public String getFullName()
		{
			return fullName;
		}

		public String getPrefix()
		{
			return prefix;
		}

		public boolean isPrivate()
		{
			return priv;
		}

		public boolean isHidden()
		{
			return hidden;
		}

		public List<Parameter> getParameters()
		{
			return Collections.unmodifiableList (parameters);
		}

		public String paramListAsString()
		{
			StringBuilder sb = new StringBuilder("(");

			for (Parameter param : getParameters()) {
				if (sb.length() > 1) sb.append (", ");

				if (param.isOptional()) sb.append ('[');

				sb.append ('$').append (param.getName());

				String type = param.getType();

				if ((type != null) && (type.length() > 0)) {
					sb.append (" as ").append (type);
				}

				if (param.isOptional()) sb.append (']');
			}

			sb.append (')');

			return sb.toString();
		}

		public String docAsHtml()
		{
			StringBuilder sb = new StringBuilder();

			sb.append ("<div>");
			sb.append ("<h1>");

			if (category.getBucket() != null) {
				sb.append (category.getBucket()).append (" / ");
			}

			sb.append (category.getName()).append (" (").append (category.getFunctionCount()).append (" functions)</h1>\n");

			sb.append ("<blockquote><b>").append (fullName).append ("</b> (");

			if (parameters.size() == 0) {
				sb.append (')');
			} else {
				sb.append ("<blockquote>");

				boolean first = true;

				for (Parameter param : parameters) {
					boolean isOptional = param.isOptional();
					if (first) first = false; else sb.append (",<br/>\n");
					if (isOptional) sb.append ('[');
					sb.append ("<b>$").append (param.getName()).append ("</b>");
					sb.append (" as ").append (param.getType());
					if (isOptional) sb.append (']');
				}

				sb.append ("</blockquote>)");
			}

			if (returnType != null) {
				sb.append (" as ").append (returnType);
			}
			sb.append ("</blockquote>\n");

			sb.append ("<br/>");
			sb.append ("<h1>Summary</h1>");
			sb.append ("<blockquote>").append (summary).append ("</blockquote>");

			if (parameters.size () > 0) {
				sb.append ("<br/><h1>Parameter Detail</h1>");

				for (Parameter param : parameters) {
					sb.append ("<blockquote>");
					sb.append ("<b>").append (param.getName()).append ("</b> as ").append (param.getType());
					if (param.getDescription() != null) {
						sb.append ("<blockquote>");
						if (param.isOptional()) sb.append ("[Optional] ");
						sb.append (param.getDescription());
						sb.append ("</blockquote>");
					}
					sb.append ("</blockquote");
				}
			}

			if (usage != null) {
				sb.append ("<br/><h1>Usage</h1><blockquote>").append (usage).append ("</blockquote>");
			}

			if (algorithm != null) {
				sb.append ("<br/><h1>Algorithm</h1><blockquote>").append (algorithm).append ("</blockquote>");
			}

			if (privilege != null) {
				sb.append ("<br/><h1>Privileges</h1><blockquote>").append (privilege).append ("</blockquote>");
			}

			if (seeAlsos.size() > 0) {
				sb.append ("<br/><h1>See Also</h1><blockquote>");

				boolean first = true;

				for (String seeAlso : seeAlsos) {
					if (first) first = false; else sb.append ("<br/>\n");
					sb.append (seeAlso);
				}

				sb.append ("</blockquote>\n");
			}

			if (examples.size() > 0) {
				boolean first = true;

				sb.append ("<br/><h1>Examples</h1>");

				for (Example example : examples) {
					String klass = example.klass;

					if ((klass == null) || (klass.length() == 0) || klass.equals ("xquery")) {
						if (first) first = false; else sb.append ("<hr/>\n");

						sb.append ("<blockquote><code><pre>").append (example.text).append ("</pre></code></blockquote>\n");
					}
				}
			}

			if (docsSource != null) {
				sb.append ("<br/><h1>Documentation Source</h1>");

				sb.append ("<blockquote>").append (docsSource).append ("</blockquote>");
			}

			sb.append ("</div>");

			return sb.toString();
		}

		@Override
		public int compareTo (@NotNull Function o)
		{
			return fullName.compareTo (o.fullName);
		}

		@Override
		public String toString()
		{
			return fullName;
		}
	}

	public static class Parameter
	{
		private final String name;
		private final String type;
		private final boolean optional;
		private final boolean vararg;
		private String description = "";

		public Parameter (String name, String type, boolean optional)
		{
			this.name = name;
			this.type = type;
			this.optional = optional;

			vararg = (type != null) && type.endsWith (",...");
		}

		public String getName ()
		{
			return name;
		}

		public String getType ()
		{
			return type;
		}

		public boolean isOptional()
		{
			return optional;
		}

		public boolean isVararg()
		{
			return vararg;
		}

		public String getDescription()
		{
			return description;
		}
	}

	public static class Example
	{
		public final String klass;
		public String text = "";

		public Example (String klass)
		{
			this.klass = klass;
		}

		public Example text (String text)
		{
			this.text = text;
			return this;
		}
	}

	public static class Category
	{
		private final String name;
		private final String bucket;
		private final int count;

		public Category (String name, String bucket, String count)
		{
			this.name = name;
			this.bucket = ((bucket != null) && (bucket.length() == 0)) ? null : bucket;
			this.count = Integer.parseInt (count);
		}

		public String getName()
		{
			return name;
		}

		public String getBucket()
		{
			return bucket;
		}

		public int getFunctionCount()
		{
			return count;
		}
	}

	// ------------------------------------------------------------

	private static class FunctionDefFunctionParser extends DefaultHandler
	{
		private final StringBuilder text = new StringBuilder();
		private final List<Function> functions;
		private final Map<String,Function> functionMap;
		private final Map<String,Category> categoryMap;

		private String docsSource = null;
		private Function func;
		private Parameter param;
		private Example example;

		private FunctionDefFunctionParser (List<Function> functions, Map<String, Function> functionMap, Map<String, Category> categoryMap)
		{
			this.functions = functions;
			this.functionMap = functionMap;
			this.categoryMap = categoryMap;
		}

		// ToDo [Rh]: Need to properly parse this XML into a DOM rather than faffing about with brain-damaged SAX parsing.  I should re-write this in Groovy.
		@Override
		public void startElement (String namespaceName, String localName, String qname, Attributes attributes) throws SAXException
		{
			switch (qname) {
			case "apidoc:function":
				text.setLength (0);
				func = new Function (docsSource, attributes.getValue ("lib"),
					attributes.getValue ("name"),
					(attributes.getValue ("fullname") == null) ? (attributes.getValue ("lib") + ':' + attributes.getValue ("name")) : attributes.getValue ("fullname"),
					false,
					Boolean.parseBoolean (attributes.getValue ("hidden")),
					"item()*",
					categoryMap.get (attributes.getValue ("category")));
				break;
			case "apidoc:method":
				text.setLength (0);
				func = new Function (docsSource, attributes.getValue ("object"),
					attributes.getValue ("name"),
					(attributes.getValue ("fullname") == null) ? (attributes.getValue ("object") + ':' + attributes.getValue ("name")) : attributes.getValue ("fullname"),
					false,
					Boolean.parseBoolean (attributes.getValue ("hidden")),
					"item()*",
					categoryMap.get (attributes.getValue ("category")));
				break;
			case "apidoc:name":
				text.setLength (0);
				break;
			case "apidoc:param":
				text.setLength (0);
				param = new Parameter (attributes.getValue ("name"),
					attributes.getValue ("type"),
					Boolean.valueOf (attributes.getValue ("optional")));
				break;
			case "apidoc:example":
				text.setLength (0);
				example = new Example (attributes.getValue ("class"));
				break;
			case "apidoc:category":
				categoryMap.put (attributes.getValue ("name"), new Category (attributes.getValue ("name"), attributes.getValue ("bucket"), attributes.getValue ("count")));
				break;
			case "apidoc:apidocs":
				docsSource = attributes.getValue ("docs-source");
				break;
			case "apidoc:categories":
			case "apidoc:functions":
			case "apidoc:params":
			case "apidoc:see-also-list":
			case "apidoc:headers":
			case "apidoc:header":
			case "apidoc:response":
				break;
			case "apidoc:return":
			case "apidoc:summary":
			case "apidoc:usage":
			case "apidoc:algorithm":
			case "apidoc:privilege":
			case "apidoc:see-also":
				text.setLength (0);
				break;
			default:
				String lName = qname.substring (qname.indexOf (':') + 1);
				text.append ('<').append (lName);

				for (int i = 0; i < attributes.getLength(); i++) {
					String attrName = attributes.getQName (i);

					if ( ! attrName.startsWith ("xmlns")) {
						text.append (' ').append (attributes.getQName (i)).append ("=\"").append (attributes.getValue (i)).append ('"');
					}
				}

				text.append ('>');
			}
		}

		@Override
		public void endElement (String namespaceName, String name, String qname) throws SAXException
		{
			switch (qname) {
			case "apidoc:functions":
			case "apidoc:params":
			case "apidoc:see-also-list":
			case "apidoc:headers":
			case "apidoc:header":
			case "apidoc:response":
				break;
			case "apidoc:function":
			case "apidoc:method":
				functions.add (func);

				if ( ! func.isHidden()) {
					functionMap.put (func.fullName, func);
				}
				break;
			case "apidoc:name":
				if (text.length() > 0) {
					func.localName = text.toString();
				}
				break;
			case "apidoc:param":
				param.description = text.toString();
				func.addParam (param);
				break;
			case "apidoc:return":
				func.returnType = text.toString();
				break;
			case "apidoc:summary":
				func.summary = text.toString();
				break;
			case "apidoc:example":
				func.addExample (example.text (text.toString()));
				break;
			case "apidoc:usage":
				func.usage = text.toString();
				break;
			case "apidoc:algorithm":
				func.algorithm = text.toString();
				break;
			case "apidoc:privilege":
				func.privilege = text.toString();
				break;
			case "apidoc:see-also":
				func.addSeeAlso (text.toString());
				break;
			default:
				// The trailing space here is not exactly what was parsed, but greatly improves readability for MarkLogic docs because of the way inline tags are formatted.
				text.append ("</").append (qname.substring (qname.indexOf (':') + 1)).append ("> ");
			}
		}

		@Override
		public void characters (char[] chars, int start, int length) throws SAXException
		{
			text.append (escapeMarkup (new String (chars, start, length)));
		}

		private static String escapeMarkup (String s)
		{
			return s.replace ("&", "&amp;").replace ("<", "&lt;").replace (">", "&gt;");
		}
	}
}

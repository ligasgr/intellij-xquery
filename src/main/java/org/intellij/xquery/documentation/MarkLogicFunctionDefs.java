package org.intellij.xquery.documentation;

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
	private static final MarkLogicFunctionDefs INSTANCE = new MarkLogicFunctionDefs ();

	public static MarkLogicFunctionDefs instance()
	{
		return INSTANCE;
	}

	// ------------------------------------------------------------

	private static final String ML_CATEGORIES_PATH = "documentation/marklogic-function-categories.xml";
	private static final String ML_FUNCTIONS_PATH = "documentation/marklogic-functions.xml";

	private final SAXParserFactory spf = SAXParserFactory.newInstance();
	private final List<Category> categories = new ArrayList<>();
	private final Map<String,Category> categoryMap = new HashMap<>();
	private final List<Function> functions = new ArrayList<>();
	private final Map<String, Function> functionMap = new HashMap<>();

	// ------------------------------------------------------------

	public List<Category> getCategories()
	{
		return Collections.unmodifiableList (categories);
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

	private MarkLogicFunctionDefs ()
	{
		loadCategories (ML_CATEGORIES_PATH);
		loadFunctions (ML_FUNCTIONS_PATH);
	}

	private void loadFunctions (String path)
	{
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream (path);

			if (is == null) {
				log ("FunctionDefs.loadFunctions: could not load: " + path);
				return;
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
	}

	private void loadCategories (String path)
	{
		try {
			InputStream is = getClass().getClassLoader ().getResourceAsStream (path);

			if (is == null) {
				log ("FunctionDefs.loadCategories: could not load: " + path);
				return;
			}

			FunctionDefCategoryParser parser = new FunctionDefCategoryParser (categories, categoryMap);
			SAXParser saxParser = spf.newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();
			xmlReader.setContentHandler (parser);
			xmlReader.parse (new InputSource (is));
			is.close();
		} catch (Exception e) {
			log ("Problem loading categories XML from '" + path + "': " + e);
			e.printStackTrace();
		}
	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------

	public static class Function
	{
		private final List<Parameter> parameters = new ArrayList<>();
		private final List<String> examples = new ArrayList<>();
		private final String localName;
		private final String fullName;
		private final String prefix;
		private Category category;
		private final boolean priv;
		private final boolean hidden;
		private String returnType = null;
		private boolean varargs = false;

		private String summary = "";
		private String usage = null;
		private String algorithm = null;
		private String privilege = null;
		private int minParamCount = 0;

		public Function (String prefix, String localName, String fullName, boolean priv, boolean hidden, String returnType, Category category)
		{
			this.prefix = prefix;
			this.localName = localName;
			this.fullName = fullName;
			this.priv = priv;
			this.hidden = hidden;
			this.returnType = returnType;
			this.category = category;
		}

		void addParam (Parameter param)
		{
			parameters.add (param);

			if ( ! param.isOptional()) minParamCount++;

			if (param.isVararg()) varargs = true;
		}

		void addExample (String example)
		{
			examples.add (example);
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

			for (Parameter param : getParameters ()) {
				if (sb.length() > 1) sb.append (", ");

				if (param.isOptional()) sb.append ("[");

				sb.append ("$").append (param.getName());

				String type = param.getType();

				if ((type != null) && (type.length() > 0)) {
					sb.append (" as ").append (type);
				}

				if (param.isOptional()) sb.append ("]");
			}

			sb.append (")");

			return sb.toString();
		}

		public String docAsHtml()
		{
			StringBuilder sb = new StringBuilder();

			sb.append ("<div>");
			sb.append ("<h1>").append (category.getDesc()).append (" (").append (category.getFunctionCount()).append (" functions)</h1>\n");

			sb.append ("<h1>Syntax</h1>\n");
			sb.append ("<blockquote><b>").append (fullName).append ("</b> (");

			if (parameters.size() == 0) {
				sb.append (")");
			} else {
				sb.append ("<blockquote>");

				boolean first = true;

				for (Parameter param : parameters) {
					boolean isOptional = param.isOptional();
					if (first) first = false; else sb.append (",<br/>\n");
					if (isOptional) sb.append ("[");
					sb.append ("<b>$").append (param.getName()).append ("</b>");
					sb.append (" as ").append (param.getType());
					if (isOptional) sb.append ("]");
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

			if (examples.size() > 0) {
				boolean first = true;

				sb.append ("<br/><h1>Examples</h1>");

				for (String example : examples) {
					if (first) first = false; else sb.append ("<hr/>\n");

					sb.append ("<blockquote><code><pre>").append (example).append ("</pre></code></blockquote>\n");
				}
			}

			sb.append ("</div>");

			return sb.toString();
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

	private static class FunctionDefFunctionParser extends DefaultHandler
	{
		private final StringBuilder text = new StringBuilder();
		private final List<Function> functions;
		private final Map<String,Function> functionMap;
		private final Map<String,Category> categoryMap;

		private Function func = null;
		private Parameter param = null;

		private FunctionDefFunctionParser (List<Function> functions, Map<String, Function> functionMap, Map<String, Category> categoryMap)
		{
			this.functions = functions;
			this.functionMap = functionMap;
			this.categoryMap = categoryMap;
		}

		@Override
		public void startElement (String namespaceName, String name, String qname, Attributes attributes) throws SAXException
		{
			if (qname.equals ("function")) {
				text.setLength (0);
				func = new Function (attributes.getValue ("lib"),
					attributes.getValue ("name"),
					attributes.getValue ("fullname"),
					false,
					Boolean.valueOf (attributes.getValue ("hidden")),
					"item()*",
					categoryMap.get (attributes.getValue ("lib")));
				return;
			}

			if (qname.equals ("param")) {
				text.setLength (0);
				param = new Parameter (attributes.getValue ("name"),
					attributes.getValue ("type"),
					Boolean.valueOf (attributes.getValue ("optional")));
				return;
			}

			switch (qname) {
			case "functions":
			case "params":
				break;
			case "return":
			case "summary":
			case "example":
			case "usage":
			case "algorithm":
			case "privilege":
				text.setLength (0);
				break;
			default:
				text.append ("<").append (qname);

				for (int i = 0; i < attributes.getLength(); i++) {
					text.append (" ").append (attributes.getQName (i)).append ("=\"").append (attributes.getValue (i)).append ("\"");
				}

				text.append (">");
			}
		}

		@Override
		public void endElement (String namespaceName, String name, String qname) throws SAXException
		{
			switch (qname) {
			case "functions":
			case "params":
				break;
			case "function":
				functions.add (func);

				if ( ! func.isHidden()) {
					functionMap.put (func.fullName, func);

					Category cat = categoryMap.get (func.prefix);

					if (cat != null) cat.incrementCount();
				}
				break;
			case "param":
				param.description = text.toString();
				func.addParam (param);
				break;
			case "return":
				func.returnType = text.toString();
				break;
			case "summary":
				func.summary = text.toString();
				break;
			case "example":
				func.addExample (text.toString());
				break;
			case "usage":
				func.usage = text.toString();
				break;
			case "algorithm":
				func.algorithm = text.toString();
				break;
			case "privilege":
				func.privilege = text.toString();
				break;
			default:
				// The trailing space here is not exactly what was parsed, but greatly improves readability for MarkLogic docs because of the way inline tags are formatted.
				text.append ("</").append (qname).append ("> ");
			}
		}

		@Override
		public void characters (char[] chars, int start, int length) throws SAXException
		{
			text.append (chars, start, length);
		}
	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------

	public static class Category
	{
		private final String prefix;
		private final String desc;
		private int functionCount = 0;

		public Category (String prefix, String desc)
		{
			this.prefix = prefix;
			this.desc = desc;
		}

		public String getPrefix()
		{
			return prefix;
		}

		public String getDesc()
		{
			return desc;
		}

		public int getFunctionCount()
		{
			return functionCount;
		}

		private void incrementCount()
		{
			functionCount++;
		}
	}

	// ------------------------------------------------------------

	private static class FunctionDefCategoryParser extends DefaultHandler
	{
		private final List<Category> categories;
		private final Map<String,Category> categoryMap;
		private String prefix = null;
		private String desc = null;

		private FunctionDefCategoryParser (List<Category> categories, Map<String, Category> categoryMap)
		{
			this.categories = categories;
			this.categoryMap = categoryMap;
		}

		@Override
		public void startElement (String namespaceName, String localName, String qname, Attributes attributes) throws SAXException
		{
			if (qname.equals ("category")) {
				prefix = attributes.getValue ("prefix");
				desc = attributes.getValue ("desc");
			}
		}

		@Override
		public void endElement (String namespaceName, String localName, String qname) throws SAXException
		{
			if (qname.equals ("category")) {
				Category category = new Category (prefix, desc);

				categories.add (category);
				categoryMap.put (prefix, category);
			}
		}
	}
}

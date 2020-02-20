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

package org.intellij.xquery.runner.rt.debugger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LogUtil
{
	public static int TRACE = 0;
	public static int DEBUG = 1;
	public static int INFO = 2;
	public static int WARN = 3;
	public static int ERROR = 4;

	private static int logLevel = INFO;
	private static String defaultLogLevelName = "info";

	private static final Map<String,Integer> nameToLevelMap = new HashMap<>();
	private static final Map<Integer,String> levelToNameMap = new HashMap<>();
	static {
		nameToLevelMap.put ("trace", TRACE);	levelToNameMap.put (TRACE, "trace");
		nameToLevelMap.put ("debug", DEBUG);	levelToNameMap.put (DEBUG, "debug");
		nameToLevelMap.put ("info", INFO);	levelToNameMap.put (INFO, "info");
		nameToLevelMap.put ("warn", WARN);	levelToNameMap.put (WARN, "warn");
		nameToLevelMap.put ("error", ERROR);	levelToNameMap.put (ERROR, "error");
	}

	private static final boolean LOGGING = Boolean.valueOf (System.getProperty ("xquery.debugger.logging", "false"));

	public LogUtil()
	{
		try {
			setLogLevel (System.getProperty ("xquery.debugger.logging.level", defaultLogLevelName));
		} catch (Exception e) {
			setLogLevel ("info");
		}
	}

	@SuppressWarnings ("UseOfSystemOutOrSystemErr")
	public static void log (int level, String msg, Throwable t)
	{
		if ((LOGGING && (logLevel <= level)) || (level >= ERROR)) {
			StringBuilder sb = new StringBuilder();
			SimpleDateFormat sdf = new SimpleDateFormat ("HH:mm:ss.SSS ");

			sb.append (Thread.currentThread().getName()).append ("(").append (levelToNameMap.get (level)).append ("): ");
			sb.append (sdf.format (new Date())).append (' ');
			sb.append (msg);

			System.err.println (sb.toString());
		}
	}

	public static void log (int level, String msg)
	{
		log (level, msg, null);
	}

	public static void log (String msg)
	{
		log (INFO, msg, null);
	}

	// ------------------------------------------------------------------

	public void setLogLevel (String level)
	{
		Integer intLevel = nameToLevelMap.get (level.toLowerCase());

		if (intLevel == null) throw new IllegalArgumentException ("Do not recognize logging level '" + level + "'");

		logLevel = intLevel;

	}

	public void trace (String message)
	{
		if (logLevel == TRACE) log (TRACE, message);
	}

	public void debug (String message)
	{
		if (logLevel <= DEBUG) log (DEBUG, message);
	}

	public void warn (String message)
	{
		if (logLevel <= WARN) log (WARN, message);
	}

	public void info (String message)
	{
		if (logLevel <= INFO) log (INFO, message);
	}

	public void error (String message)
	{
		if (logLevel <= ERROR) log (ERROR, message);
	}

	public void error (String message, Throwable t)
	{
		if (logLevel <= ERROR) log (ERROR, message, t);
	}
}

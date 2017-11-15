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

package org.intellij.xquery.runner.rt.debugger.saxon;

import com.codnos.dbgp.api.Breakpoint;
import com.codnos.dbgp.api.BreakpointUpdateData;
import com.codnos.dbgp.api.Context;
import com.codnos.dbgp.api.DBGpFactory;
import com.codnos.dbgp.api.DBGpIde;
import com.codnos.dbgp.api.DebuggerIde;
import com.codnos.dbgp.api.PropertyValue;
import com.codnos.dbgp.api.StackFrame;
import com.codnos.dbgp.api.Status;
import com.codnos.dbgp.api.SystemInfo;
import org.intellij.xquery.runner.rt.StringOutputStream;
import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.XQueryRunnerApp;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.jayway.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isOneOf;
import static org.intellij.xquery.runner.rt.FileTestUtil.createFileWithContents;
import static org.intellij.xquery.runner.rt.XQueryDataSourceType.SAXON_NATIVE;
import static org.intellij.xquery.runner.rt.XQueryRunConfigBuilder.runConfig;
import static org.junit.Assert.assertThat;

public class SaxonDebuggerAppTest {
    private static final int DEBUGGER_PORT = 9000;
    private final StringOutputStream outputStream = new StringOutputStream();
    private final PrintStream printStream = new PrintStream(outputStream);
    private final SpyDebuggerIde spyDebuggerIde = new SpyDebuggerIde();
    private final DBGpIde dbGpIde = DBGpFactory.ide().withPort(DEBUGGER_PORT).withDebuggerIde(spyDebuggerIde).build();

    @Before
    public void setUp() throws Exception {
        dbGpIde.startListening();
        await().until(dbGpIde::isConnected);
        spyDebuggerIde.currentStatus = Status.STARTING;
    }

    @After
    public void tearDown() throws Exception {
        dbGpIde.stopListening();
    }

    @Test
    public void shouldStartUpAndRunBasicFile() throws Exception {
        startDebuggerEngineWith("'Hello world!'");

        dbGpIde.run();

        await().until(() -> spyDebuggerIde.currentStatus == Status.STOPPED);
        assertThat(outputStream.getString(), is("Hello world!"));
    }

    @Test
    public void shouldBreakOnBreakpoint() throws Exception {
        String fileURL = startDebuggerEngineWith("xquery version '1.0';\n" +
                "'some text to break on'");

        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        dbGpIde.run();

        waitUntilHittingBreakpoint();
    }

    @Test
    public void shouldReturnBreakpointDataAfterBreakpointWasSet() throws Exception {
        String fileURL = startDebuggerEngineWith("xquery version '1.0';\n" +
                "'some text to break on'");

        Breakpoint breakpointThatWasSet = dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        Breakpoint breakpointThatWeGot = dbGpIde.breakpointGet(breakpointThatWasSet.getBreakpointId());

        assertThat(breakpointThatWeGot.getFileURL(), is(breakpointThatWasSet.getFileURL()));
        assertThat(breakpointThatWeGot.getLineNumber(), is(breakpointThatWasSet.getLineNumber()));
        assertThat(breakpointThatWeGot.isEnabled(), is(breakpointThatWasSet.isEnabled()));
        assertThat(breakpointThatWeGot.isTemporary(), is(breakpointThatWasSet.isTemporary()));
    }

    @Test
    public void shouldHaveStackDepthOfOneAtTheFirstBreakpoint() throws Exception {
        String fileURL = startDebuggerEngineWith("xquery version '1.0';\n" +
                "'some text to break on'");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        int stackDepth = dbGpIde.stackDepth();

        assertThat(stackDepth, is(1));
    }

    @Test
    public void shouldHaveStackFrameOfMainAtTheFirstBreakpoint() throws Exception {
        String fileURL = startDebuggerEngineWith("xquery version '1.0';\n" +
                "'some text to break on'");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        StackFrame stackFrame = dbGpIde.stackGet(0);

        assertThat(stackFrame.getFileURL(), is(fileURL));
        assertThat(stackFrame.getLineNumber(), is(2));
        assertThat(stackFrame.getWhere(), is(""));
    }

    @Test
    public void shouldHaveStackDepthOfTwoInsideOfFunctionInvocation() throws Exception {
        String fileURL = startDebuggerEngineWith("declare function local:f() {\n" +
                "'some text to break on'\n" +
                "};\n" +
                "local:f()");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        int stackDepth = dbGpIde.stackDepth();

        assertThat(stackDepth, is(2));
    }

    @Test
    public void shouldHaveStackFrameOfFunctionAtTheBreakpointInsideOfFunction() throws Exception {
        String fileURL = startDebuggerEngineWith("declare function local:f() {\n" +
                "'some text to break on'\n" +
                "};\n" +
                "local:f()");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        StackFrame stackFrame = dbGpIde.stackGet(0);

        assertThat(stackFrame.getFileURL(), is(fileURL));
        assertThat(stackFrame.getLineNumber(), is(2));
        assertThat(stackFrame.getWhere(), is("local:f"));
    }

    @Test
    public void shouldHaveStackFrameOfMainAtTheFramePriorToFunctionInvocation() throws Exception {
        String fileURL = startDebuggerEngineWith("declare function local:f() {\n" +
                "'some text to break on'\n" +
                "};\n" +
                "local:f()");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        StackFrame stackFrame = dbGpIde.stackGet(1);

        assertThat(stackFrame.getFileURL(), is(fileURL));
        assertThat(stackFrame.getLineNumber(), is(4));
        assertThat(stackFrame.getWhere(), is(""));
    }

    @Test
    public void shouldHaveCorrectStackFramesAtEachBreakpoint() throws Exception {
        String fileURL = startDebuggerEngineWith("declare function local:f4($w) {\n" +
                "    <window>$w</window>\n" +
                "};\n" +
                "declare function local:f3($i) {\n" +
                "    for sliding window $w in (1)\n" +
                "    start when fn:true()\n" +
                "    end when fn:true()\n" +
                "    return local:f4($w)\n" +
                "};\n" +
                "declare function local:f2() {\n" +
                "    for $i in (1, 2, 3)\n" +
                "    return local:f3($i)\n" +
                "};\n" +
                "declare function local:f1() {\n" +
                "    let $c := local:f2()\n" +
                "    return $c\n" +
                "};\n" +
                "let $a := local:f1()\n" +
                "return $a");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 5).build());
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 11).build());
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 15).build());
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 18).build());
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 19).build());

        dbGpIde.run();
        waitUntilHittingBreakpoint();

        StackFrame stackFrame = dbGpIde.stackGet(0);
        assertThat(stackFrame.getFileURL(), is(fileURL));
        assertThat(stackFrame.getLineNumber(), is(18));
        assertThat(stackFrame.getWhere(), is(""));

        dbGpIde.run();
        waitUntilHittingBreakpoint();

        stackFrame = dbGpIde.stackGet(0);
        assertThat(stackFrame.getFileURL(), is(fileURL));
        assertThat(stackFrame.getLineNumber(), is(15));
        assertThat(stackFrame.getWhere(), is("local:f1"));

        dbGpIde.run();
        waitUntilHittingBreakpoint();

        stackFrame = dbGpIde.stackGet(0);
        assertThat(stackFrame.getFileURL(), is(fileURL));
        assertThat(stackFrame.getLineNumber(), is(11));
        assertThat(stackFrame.getWhere(), is("local:f2"));

        dbGpIde.run();
        waitUntilHittingBreakpoint();

        stackFrame = dbGpIde.stackGet(0);
        assertThat(stackFrame.getFileURL(), is(fileURL));
        assertThat(stackFrame.getLineNumber(), is(5));
        assertThat(stackFrame.getWhere(), is("local:f3"));

        dbGpIde.run();
        waitUntilHittingBreakpoint();

        stackFrame = dbGpIde.stackGet(0);
        assertThat(stackFrame.getFileURL(), is(fileURL));
        assertThat(stackFrame.getLineNumber(), is(2));
        assertThat(stackFrame.getWhere(), is("local:f4"));

        dbGpIde.run();
        waitUntilHittingBreakpoint();
        dbGpIde.run();
        waitUntilHittingBreakpoint();
        dbGpIde.run();
        waitUntilHittingBreakpoint();
        dbGpIde.run();
        waitUntilHittingBreakpoint();
        dbGpIde.run();
        waitUntilHittingBreakpoint();
        dbGpIde.run();
        waitUntilHittingBreakpoint();
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        stackFrame = dbGpIde.stackGet(0);
        assertThat(stackFrame.getFileURL(), is(fileURL));
        assertThat(stackFrame.getLineNumber(), is(19));
        assertThat(stackFrame.getWhere(), is(""));
    }

    @Test
    public void shouldBreakOnSecondBreakpoint() throws Exception {
        String fileURL = startDebuggerEngineWith("let $var := 'var1'\n" +
                "return $var");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 1).build());
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        dbGpIde.run();

        waitUntilHittingBreakpoint();
    }

    @Test
    public void shouldHaveStackDepthOneOnSecondBreakpoint() throws Exception {
        String fileURL = startDebuggerEngineWith("let $var := 'var1'\n" +
                "return $var");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 1).build());
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        int stackDepth = dbGpIde.stackDepth();

        assertThat(stackDepth, is(1));
    }

    @Test
    public void shouldHaveNoVariablesOnFirstBreakpoint() throws Exception {
        String fileURL = startDebuggerEngineWith("let $var := 'var1'\n" +
                "return $var");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 1).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        Context context = dbGpIde.contextGet(0);

        assertThat(context.getVariables().size(), is(0));
    }

    @Test
    public void shouldHaveOneVariableOnSecondLine() throws Exception {
        String fileURL = startDebuggerEngineWith("let $var := 'var1'\n" +
                "return $var");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        Context context = dbGpIde.contextGet(0);

        Collection<PropertyValue> variables = context.getVariables();
        assertThat(variables.size(), is(1));
        PropertyValue propertyValue = variables.iterator().next();
        assertThat(propertyValue.getName(), is("var"));
        assertThat(propertyValue.getValue(), is("var1"));
        assertThat(propertyValue.getType(), is("xs:string"));
    }

    @Test
    public void shouldHaveCorrectTypeAndValueForSequenceType() throws Exception {
        String fileURL = startDebuggerEngineWith("let $var as xs:integer* := (1, 2, 3, 4, 5)\n" +
                "return $var");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        Context context = dbGpIde.contextGet(0);

        Collection<PropertyValue> variables = context.getVariables();
        assertThat(variables.size(), is(1));
        PropertyValue propertyValue = variables.iterator().next();
        assertThat(propertyValue.getName(), is("var"));
        assertThat(propertyValue.getValue(), is("(1, 2, 3, 4, 5)"));
        assertThat(propertyValue.getType(), is("xs:integer*"));
    }

    @Test
    public void shouldHaveCorrectTypeAndValueForMapType() throws Exception {
        String fileURL = startDebuggerEngineWith("declare namespace map = \"http://www.w3.org/2005/xpath-functions/map\";\n" +
                "let $var := map:merge((\n" +
                "   map:entry(\"Su\", \"Sunday\"),\n" +
                "   map:entry(\"Mo\", \"Monday\"),\n" +
                "   map:entry(\"Tu\", \"Tuesday\"),\n" +
                "   map:entry(\"We\", \"Wednesday\"),\n" +
                "   map:entry(\"Th\", \"Thursday\"),\n" +
                "   map:entry(\"Fr\", \"Friday\"),\n" +
                "   map:entry(\"Sa\", \"Saturday\")\n" +
                "   ))\n" +
                "return $var");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 11).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        Context context = dbGpIde.contextGet(0);

        Collection<PropertyValue> variables = context.getVariables();
        assertThat(variables.size(), is(1));
        PropertyValue propertyValue = variables.iterator().next();
        assertThat(propertyValue.getName(), is("var"));
        assertThat(propertyValue.getValue().matches("\\(([0-9a-zA-Z]+->.+)(, )*\\)"), is(true));
        List<String> expectedEntries = new ArrayList<>();
        Collections.addAll(expectedEntries, "Su->Sunday",
                "Mo->Monday",
                "Tu->Tuesday",
                "We->Wednesday",
                "Th->Thursday",
                "Fr->Friday",
                "Sa->Saturday");
        for (String expectedEntry : expectedEntries) {
            assertThat(propertyValue.getValue(), containsString(expectedEntry));
        }
        assertThat(propertyValue.getType(), is("map(*)"));
    }

    @Test
    public void shouldHaveOneGlobalVariableOnSecondLine() throws Exception {
        String fileURL = startDebuggerEngineWith("declare variable $local:var as xs:string := 'var1';\n" +
                "('asd',$local:var)");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        Context context = dbGpIde.contextGet(0);

        Collection<PropertyValue> variables = context.getVariables();
        assertThat(variables.size(), is(1));
        PropertyValue propertyValue = variables.iterator().next();
        assertThat(propertyValue.getName(), is("local:var"));
        assertThat(propertyValue.getValue(), is("var1"));
        assertThat(propertyValue.getType(), is("xs:string"));
    }

    @Test
    public void shouldStepOverToSecondLine() throws Exception {
        String fileURL = startDebuggerEngineWith("declare variable $local:var as xs:string := 'var1';\n" +
                "('asd',$local:var)");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 1).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        dbGpIde.stepOver();

        waitUntilHittingBreakpoint();
        Context context = dbGpIde.contextGet(0);
        Collection<PropertyValue> variables = context.getVariables();
        assertThat(variables.size(), is(1));
        PropertyValue propertyValue = variables.iterator().next();
        assertThat(propertyValue.getName(), is("local:var"));
        assertThat(propertyValue.getValue(), is("var1"));
        assertThat(propertyValue.getType(), is("xs:string"));
    }

    @Test
    public void shouldStepOverToNextLineInsideOfFunction() throws Exception {
        String fileURL = startDebuggerEngineWith("declare function local:f() {\n" +
                "let $a := 'some text to break on'\n" +
                "return ('another line', $a)\n" +
                "};\n" +
                "local:f()");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        dbGpIde.stepOver();

        waitUntilHittingBreakpoint();
        Context context = dbGpIde.contextGet(0);
        Collection<PropertyValue> variables = context.getVariables();
        assertThat(variables.size(), is(1));
        PropertyValue propertyValue = variables.iterator().next();
        assertThat(propertyValue.getName(), is("a"));
        assertThat(propertyValue.getValue(), is("some text to break on"));
        assertThat(propertyValue.getType(), is("xs:string"));
    }

    @Test
    public void shouldStepOutOfFunctionFromInsideOfFunction() throws Exception {
        String fileURL = startDebuggerEngineWith("declare function local:f() {\n" +
                "'some text to break on'\n" +
                "};\n" +
                "let $f := local:f()\n" +
                "return $f");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        dbGpIde.stepOver();

        waitUntilHittingBreakpoint();
        StackFrame stackFrame = dbGpIde.stackGet(0);
        assertThat(stackFrame.getFileURL(), is(fileURL));
        assertThat(stackFrame.getLineNumber(), is(5));
        assertThat(stackFrame.getWhere(), is(""));
        Context context = dbGpIde.contextGet(0);
        Collection<PropertyValue> variables = context.getVariables();
        assertThat(variables.size(), is(1));
        PropertyValue propertyValue = variables.iterator().next();
        assertThat(propertyValue.getName(), is("f"));
        assertThat(propertyValue.getValue(), is("some text to break on"));
        assertThat(propertyValue.getType(), is("xs:string"));
    }

    @Test
    public void shouldStepIntoToSecondLine() throws Exception {
        String fileURL = startDebuggerEngineWith("declare variable $local:var as xs:string := 'var1';\n" +
                "('asd',$local:var)");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 1).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        dbGpIde.stepInto();

        waitUntilHittingBreakpoint();
        Context context = dbGpIde.contextGet(0);
        Collection<PropertyValue> variables = context.getVariables();
        assertThat(variables.size(), is(1));
        PropertyValue propertyValue = variables.iterator().next();
        assertThat(propertyValue.getName(), is("local:var"));
        assertThat(propertyValue.getValue(), is("var1"));
        assertThat(propertyValue.getType(), is("xs:string"));
    }

    @Test
    public void shouldStepIntoTheFunction() throws Exception {
        String fileURL = startDebuggerEngineWith("declare function local:f() {\n" +
                "'some text to break on'\n" +
                "};\n" +
                "local:f()");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 4).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        dbGpIde.stepInto();

        waitUntilHittingBreakpoint();
        StackFrame stackFrame = dbGpIde.stackGet(0);
        assertThat(stackFrame.getFileURL(), is(fileURL));
        assertThat(stackFrame.getLineNumber(), is(2));
        assertThat(stackFrame.getWhere(), is("local:f"));
    }

    @Test
    public void shouldStepOutAsStepInOfTheFunctionIfItIsReturnedResultThatWasNotAFunctionCall() throws Exception {
        String fileURL = startDebuggerEngineWith("declare function local:f() {\n" +
                "'some text to break on'\n" +
                "};\n" +
                "let $a := local:f()\n" +
                "return $a");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        dbGpIde.stepInto();

        waitUntilHittingBreakpoint();
        StackFrame stackFrame = dbGpIde.stackGet(0);
        assertThat(stackFrame.getFileURL(), is(fileURL));
        assertThat(stackFrame.getLineNumber(), is(5));
        assertThat(stackFrame.getWhere(), is(""));
    }

    @Test
    public void shouldHaveStackOfMainAndOnlyOneFrameAfterProcessingFunction() throws Exception {
        String fileURL = startDebuggerEngineWith("declare function local:f() {\n" +
                "    let $c := 'b'\n" +
                "    return $c\n" +
                "};\n" +
                "let $f := local:f()\n" +
                "return $f");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 6).build());

        dbGpIde.run();
        waitUntilHittingBreakpoint();

        StackFrame stackFrame = dbGpIde.stackGet(0);
        assertThat(stackFrame.getFileURL(), is(fileURL));
        assertThat(stackFrame.getLineNumber(), is(6));
        assertThat(stackFrame.getWhere(), is(""));
    }

    @Test
    public void shouldStepOutOfTheFunction() throws Exception {
        String fileURL = startDebuggerEngineWith("declare function local:f() {\n" +
                "let $b := 'some text to break on'\n" +
                "return $b" +
                "};\n" +
                "let $a := local:f()\n" +
                "return $a");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        dbGpIde.stepOut();

        waitUntilHittingBreakpoint();
        StackFrame stackFrame = dbGpIde.stackGet(0);
        assertThat(stackFrame.getFileURL(), is(fileURL));
        assertThat(stackFrame.getLineNumber(), is(5));
        assertThat(stackFrame.getWhere(), is(""));
    }

    @Test
    public void shouldStopOnBreakpointWheSteppingOverEnteredFunctionWithBreakpoint() throws Exception {
        String fileURL = startDebuggerEngineWith("declare function local:f() {\n" +
                "let $a := 'some text to break on'\n" +
                "return ('another line', $a)\n" +
                "};\n" +
                "local:f()");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 3).build());
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 5).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        dbGpIde.stepOver();

        waitUntilHittingBreakpoint();
        Context context = dbGpIde.contextGet(0);
        Collection<PropertyValue> variables = context.getVariables();
        assertThat(variables.size(), is(1));
        PropertyValue propertyValue = variables.iterator().next();
        assertThat(propertyValue.getName(), is("a"));
        assertThat(propertyValue.getValue(), is("some text to break on"));
        assertThat(propertyValue.getType(), is("xs:string"));
    }

    @Test
    public void shouldProvideAtLeastBasicXmlRepresentationForXmlVariables() throws Exception {
        String fileURL = startDebuggerEngineWith("let $var := <a><b><c/><d attr='e' another='value'>f</d></b><g><h>i</h></g></a>\n" +
                "return $var");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        Context context = dbGpIde.contextGet(0);
        Collection<PropertyValue> variables = context.getVariables();

        assertThat(variables.size(), is(1));
        PropertyValue propertyValue = variables.iterator().next();
        assertThat(propertyValue.getName(), is("var"));
        assertThat(propertyValue.getValue(), is("<a><b><c/><d attr='e' another='value'>f</d></b><g><h>i</h></g></a>"));
        assertThat(propertyValue.getType(), is("item()"));
    }

    @Test
    public void shouldProvideProperXmlForExtractedXml() throws Exception {
        String fileURL = startDebuggerEngineWith("let $var := <a><b><c/><d attr='e' another='value'>f</d></b><g><h>i</h></g></a>\n" +
                "let $var1 := $var/b/d\n" +
                "return $var1");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 3).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        Context context = dbGpIde.contextGet(0);
        Collection<PropertyValue> variables = context.getVariables();

        assertThat(variables.size(), is(2));
        Iterator<PropertyValue> iterator = variables.iterator();
        iterator.next();
        PropertyValue propertyValue = iterator.next();
        assertThat(propertyValue.getName(), is("var1"));
        assertThat(propertyValue.getValue(), is("<d attr='e' another='value'>f</d>"));
        assertThat(propertyValue.getType(), is("item()"));
    }

    @Test
    public void shouldNotBreakOnRemovedBreakpoint() throws Exception {
        String fileURL = startDebuggerEngineWith("xquery version '1.0';\n" +
                "let $var1 := 'some text to break on'\n" +
                "let $var2 := 'another'\n" +
                "let $var3 := 'yet another'\n" +
                "return ($var1, $var2, $var3)");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        Breakpoint breakpointToRemove = dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 3).build());
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 4).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        Optional<Breakpoint> removedBreakpointOptional = dbGpIde.breakpointRemove(breakpointToRemove.getBreakpointId());
        dbGpIde.run();
        waitUntilHittingBreakpoint();


        StackFrame stackFrame = dbGpIde.stackGet(0);
        assertThat(stackFrame.getFileURL(), is(fileURL));
        assertThat(stackFrame.getLineNumber(), is(4));
        assertThat(stackFrame.getWhere(), is(""));
        assertThat(removedBreakpointOptional.isPresent(), is(true));
        Breakpoint removedBreakpoint = removedBreakpointOptional.get();
        assertThat(removedBreakpoint.getBreakpointId(), is(breakpointToRemove.getBreakpointId()));
        assertThat(removedBreakpoint.getFileURL(), is(Optional.of(fileURL)));
        assertThat(removedBreakpoint.getLineNumber(), is(Optional.of(3)));
    }

    @Test
    public void shouldNotBreakOnDisabledBreakpoint() throws Exception {
        String fileURL = startDebuggerEngineWith("xquery version '1.0';\n" +
                "let $var1 := 'some text to break on'\n" +
                "let $var2 := 'another'\n" +
                "let $var3 := 'yet another'\n" +
                "return ($var1, $var2, $var3)");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        Breakpoint breakpointToUpdate = dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 3).build());
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 4).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        dbGpIde.breakpointUpdate(breakpointToUpdate.getBreakpointId(), new BreakpointUpdateData(false));
        dbGpIde.run();
        waitUntilHittingBreakpoint();


        StackFrame stackFrame = dbGpIde.stackGet(0);
        assertThat(stackFrame.getFileURL(), is(fileURL));
        assertThat(stackFrame.getLineNumber(), is(4));
        assertThat(stackFrame.getWhere(), is(""));
        Breakpoint updatedBreakpoint = dbGpIde.breakpointGet(breakpointToUpdate.getBreakpointId());
        assertThat(updatedBreakpoint.getBreakpointId(), is(breakpointToUpdate.getBreakpointId()));
        assertThat(updatedBreakpoint.getFileURL(), is(Optional.of(fileURL)));
        assertThat(updatedBreakpoint.getLineNumber(), is(Optional.of(3)));
        assertThat(updatedBreakpoint.isEnabled(), is(false));
    }

    @Test
    @Ignore
    public void shouldStopAtBreakpointForXPathExtraction() throws Exception {
        String fileURL = startDebuggerEngineWith("declare function local:abc() {\n" +
                "    let $var := <a><b><c/><d attr='e' another='value'>f</d></b><g><h>i</h></g></a>\n" +
                "    let $var1 := $var/b/d\n" +
                "    return $var1\n" +
                "};\n" +
                "local:abc()");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 3).build());

        dbGpIde.run();
        waitUntilHittingBreakpoint();
    }

    @Test
    public void shouldStopAtTemporaryBreakpointOnlyOnce() throws Exception {
        String fileURL = startDebuggerEngineWith("declare function local:abc() {\n" +
                "    for $i in (1, 2, 3)\n" +
                "    return $i\n" +
                "};\n" +
                "let $a := local:abc()\n" +
                "return $a");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 3).withTemporary(true).build());
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 6).build());

        dbGpIde.run();
        waitUntilHittingBreakpoint();
        StackFrame stackFrame = dbGpIde.stackGet(0);
        assertThat(stackFrame.getFileURL(), is(fileURL));
        assertThat(stackFrame.getLineNumber(), is(3));
        assertThat(stackFrame.getWhere(), is("local:abc"));

        dbGpIde.run();
        waitUntilHittingBreakpoint();
        stackFrame = dbGpIde.stackGet(0);
        assertThat(stackFrame.getFileURL(), is(fileURL));
        assertThat(stackFrame.getLineNumber(), is(6));
        assertThat(stackFrame.getWhere(), is(""));
    }

    @Test
    public void shouldListFunctionArgumentOnTheListOfVariables() throws Exception {
        String fileURL = startDebuggerEngineWith("declare function local:f($a) {\n" +
                "fn:concat('abc', $a)\n" +
                "};\n" +
                "local:f('b')");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        Context context = dbGpIde.contextGet(0);

        Collection<PropertyValue> variables = context.getVariables();
        assertThat(variables.size(), is(1));
        PropertyValue propertyValue = variables.iterator().next();
        assertThat(propertyValue.getName(), is("a"));
        assertThat(propertyValue.getValue(), is("b"));
        assertThat(propertyValue.getType(), is("xs:string"));
    }

    @Test
    public void shouldBreakOnOneOfTheLinesOnBreakRequest() throws Exception {
        Set<Integer> lines = new HashSet<>();
        startDebuggerEngineWith("declare function local:f() {\n" +
                "    for $a in (1 to 100000)\n" +
                "    let $b := concat('some text to break on', 'some text to break on')\n" +
                "    let $c := concat('some text to break on', 'some text to break on')\n" +
                "    let $d := concat('some text to break on', 'some text to break on')\n" +
                "    let $e := concat('some text to break on', 'some text to break on')\n" +
                "    return ($a, $b, $c, $d, $e)\n" +
                "};\n" +
                "let $a := local:f()\n" +
                "return $a");
        dbGpIde.run();

        for (int i = 0; i < 10; i++) {
            dbGpIde.breakNow();
            waitUntilHittingBreakpoint();
            StackFrame stack = dbGpIde.stackGet(0);
            lines.add(stack.getLineNumber());
        }

        for (Integer line : lines) {
            assertThat(line, is(isOneOf(2, 3, 4, 5, 6, 7, 9, 10)));
        }
    }

    @Test
    public void shouldEvaluateFunctionCallInsideOfMain() throws Exception {
        String fileURL = startDebuggerEngineWith("declare variable $var := 'abc';\n" +
                "fn:concat($var, 'def')");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        Optional<PropertyValue> propertyValue = dbGpIde.eval(0, "fn:concat('abc', 'def')");

        assertThat(propertyValue.isPresent(), is(true));
        PropertyValue value = propertyValue.get();
        assertThat(value.getName(), is("evalResult"));
        assertThat(value.getType(), is("xs:string"));
        assertThat(value.getValue(), is("abcdef"));
    }


    @Test
    public void shouldEvaluateGlobalVariableInsideOfMain() throws Exception {
        String fileURL = startDebuggerEngineWith("declare variable $var := 'abc';\n" +
                "fn:concat($var, 'def')");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        Optional<PropertyValue> propertyValue = dbGpIde.eval(0, "fn:concat($var, 'def')");

        assertThat(propertyValue.isPresent(), is(true));
        PropertyValue value = propertyValue.get();
        assertThat(value.getName(), is("evalResult"));
        assertThat(value.getType(), is("xs:string"));
        assertThat(value.getValue(), is("abcdef"));
    }

    @Test
    public void shouldEvaluateGlobalVariableInsideOfFunction() throws Exception {
        String fileURL = startDebuggerEngineWith("declare variable $var := 'abc';\n" +
                "declare function local:abc() {\n" +
                "    let $var1 := 'a'\n" +
                "    return $var\n" +
                "};\n" +
                "local:abc()");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 4).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        Optional<PropertyValue> propertyValue = dbGpIde.eval(0, "fn:concat($var, 'def')");

        assertThat(propertyValue.isPresent(), is(true));
        PropertyValue value = propertyValue.get();
        assertThat(value.getName(), is("evalResult"));
        assertThat(value.getType(), is("xs:string"));
        assertThat(value.getValue(), is("abcdef"));
    }

    @Test
    public void shouldEvaluateGlobalVariableInsideOfFunctionMoreThanOnce() throws Exception {
        String fileURL = startDebuggerEngineWith("declare variable $var := 'abc';\n" +
                "declare function local:abc() {\n" +
                "    let $var1 := 'a'\n" +
                "    return $var\n" +
                "};\n" +
                "local:abc()");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 4).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        dbGpIde.eval(0, "fn:concat($var, 'def')");
        Optional<PropertyValue> propertyValue = dbGpIde.eval(0, "fn:concat($var, 'def')");

        assertThat(propertyValue.isPresent(), is(true));
        PropertyValue value = propertyValue.get();
        assertThat(value.getName(), is("evalResult"));
        assertThat(value.getType(), is("xs:string"));
        assertThat(value.getValue(), is("abcdef"));
    }

    @Test
    public void shouldEvaluateLocalVariableInsideOfMain() throws Exception {
        String fileURL = startDebuggerEngineWith("let $var := 'a'\n" +
                "    return fn:concat($var, 'b')");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 2).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        Optional<PropertyValue> propertyValue = dbGpIde.eval(0, "fn:concat($var, 'b')");

        assertThat(propertyValue.isPresent(), is(true));
        PropertyValue value = propertyValue.get();
        assertThat(value.getName(), is("evalResult"));
        assertThat(value.getType(), is("xs:string"));
        assertThat(value.getValue(), is("ab"));
    }

    @Test
    public void shouldEvaluateLocalVariableInsideOfFunction() throws Exception {
        String fileURL = startDebuggerEngineWith("declare function local:abc() {\n" +
                "    let $var := 'a'\n" +
                "    return $var\n" +
                "};\n" +
                "local:abc()");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 3).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        Optional<PropertyValue> propertyValue = dbGpIde.eval(0, "fn:concat($var, 'b')");

        assertThat(propertyValue.isPresent(), is(true));
        PropertyValue value = propertyValue.get();
        assertThat(value.getName(), is("evalResult"));
        assertThat(value.getType(), is("xs:string"));
        assertThat(value.getValue(), is("ab"));
    }

    @Test
    public void shouldEvaluateLocalVariableInsideOfFunctionMoreThanOnce() throws Exception {
        String fileURL = startDebuggerEngineWith("declare function local:abc() {\n" +
                "    let $var := 'a'\n" +
                "    return $var\n" +
                "};\n" +
                "local:abc()");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 3).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        dbGpIde.eval(0, "fn:concat($var, 'b')");
        Optional<PropertyValue> propertyValue = dbGpIde.eval(0, "fn:concat($var, 'b')");

        assertThat(propertyValue.isPresent(), is(true));
        PropertyValue value = propertyValue.get();
        assertThat(value.getName(), is("evalResult"));
        assertThat(value.getType(), is("xs:string"));
        assertThat(value.getValue(), is("ab"));
    }

    @Test
    public void shouldEvaluateGlobalVariableFromMainModuleInsideOfLibraryModule() throws Exception {
        File libraryFile = createFileWithContents("module namespace prefix = 'test';\n" +
                "declare function prefix:importedFun() {\n" +
                "let $a := 'a'\n" +
                "return $a\n" +
                "};");
        startDebuggerEngineWith("import module namespace lib = 'test' at '" + libraryFile.getName() + "';\n" +
                "declare variable $lib:var := 'abc';\n" +
                "lib:importedFun()");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(libraryFile.toURI().toASCIIString(), 4).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        Optional<PropertyValue> propertyValue = dbGpIde.eval(0, "fn:concat($prefix:var, 'def')");

        assertThat(propertyValue.isPresent(), is(true));
        PropertyValue value = propertyValue.get();
        assertThat(value.getName(), is("evalResult"));
        assertThat(value.getType(), is("xs:string"));
        assertThat(value.getValue(), is("abcdef"));
    }

    @Test
    public void shouldEvaluateGlobalVariableFromImportedModuleInsideOfMainModule() throws Exception {
        File libraryFile = createFileWithContents("module namespace prefix = 'test';\n" +
                "declare variable $prefix:var := 'abc';");
        String fileUri = startDebuggerEngineWith("import module namespace lib = 'test' at '" + libraryFile.getName() + "';\n" +
                "fn:concat('abc', 'xyz')");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileUri, 2).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        Optional<PropertyValue> propertyValue = dbGpIde.eval(0, "fn:concat($lib:var, 'def')");

        assertThat(propertyValue.isPresent(), is(true));
        PropertyValue value = propertyValue.get();
        assertThat(value.getName(), is("evalResult"));
        assertThat(value.getType(), is("xs:string"));
        assertThat(value.getValue(), is("abcdef"));
    }

    @Test
    public void shouldEvaluateLocalVariableInsideOfLibraryModule() throws Exception {
        File libraryFile = createFileWithContents("module namespace prefix = 'test';\n" +
                "declare function prefix:importedFun() {\n" +
                "let $a := 'a'\n" +
                "return fn:concat($a, 'b')\n" +
                "};");
        startDebuggerEngineWith("import module namespace lib = 'test' at '" + libraryFile.getName() + "';\n" +
                "lib:importedFun()");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(libraryFile.toURI().toASCIIString(), 4).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        Optional<PropertyValue> propertyValue = dbGpIde.eval(0, "fn:concat($a, 'def')");

        assertThat(propertyValue.isPresent(), is(true));
        PropertyValue value = propertyValue.get();
        assertThat(value.getName(), is("evalResult"));
        assertThat(value.getType(), is("xs:string"));
        assertThat(value.getValue(), is("adef"));
    }

    @Test
    public void shouldHitBreakpointInAnotherFileEvenIfLineIsSameOfTheLastFrameInMainFile() throws Exception {
        File libraryFile = createFileWithContents("module namespace prefix = 'test';\n" +
                "declare function prefix:importedFun() {\n" +
                "let $a := 'a'\n" +
                "return fn:concat($a, 'b')\n" +
                "};");
        startDebuggerEngineWith("import module namespace lib = 'test' at '" + libraryFile.getName() + "';\n" +
                "\n\n" +
                "lib:importedFun()");
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(libraryFile.toURI().toASCIIString(), 4).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();
    }

    @Test
    public void shouldNotBreakWhenConditionWasNotMet() throws Exception {
        String fileURL = startDebuggerEngineWith("let $var := 'var1'\n" +
                "let $var1 := 'var2'\n" +
                "return $var");
        dbGpIde.breakpointSet(Breakpoint.aConditionalBreakpoint(fileURL, 2, "$var = 'var'").build());
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 3).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        StackFrame stackFrame = dbGpIde.stackGet(0);

        assertThat(stackFrame.getFileURL(), is(fileURL));
        assertThat(stackFrame.getLineNumber(), is(3));
        assertThat(stackFrame.getWhere(), is(""));
    }

    @Test
    public void shouldBreakWhenConditionWasMet() throws Exception {
        String fileURL = startDebuggerEngineWith("let $var := 'var'\n" +
                "let $var1 := 'var2'\n" +
                "return $var");
        dbGpIde.breakpointSet(Breakpoint.aConditionalBreakpoint(fileURL, 2, "$var = 'var'").build());
        dbGpIde.breakpointSet(Breakpoint.aLineBreakpoint(fileURL, 3).build());
        dbGpIde.run();
        waitUntilHittingBreakpoint();

        StackFrame stackFrame = dbGpIde.stackGet(0);

        assertThat(stackFrame.getFileURL(), is(fileURL));
        assertThat(stackFrame.getLineNumber(), is(2));
        assertThat(stackFrame.getWhere(), is(""));
    }

    private void waitUntilHittingBreakpoint() {
        await().until(() -> spyDebuggerIde.currentStatus == Status.BREAK);
    }

    private String startDebuggerEngineWith(String contents) throws Exception {
        File xqueryMainFile = createFileWithContents(contents);
        XQueryRunConfig config = new XQueryRunConfig(prepareConfigurationForMainFile(xqueryMainFile));
        XQueryRunnerApp.runConfigForOutputStream(config, printStream);
        await().until(() -> spyDebuggerIde.connected);
        return xqueryMainFile.toURI().toASCIIString();
    }


    private String prepareConfigurationForMainFile(File xqueryMainFile) {
        return runConfig()
                .withTypeName(SAXON_NATIVE.toString())
                .withMainFileName(xqueryMainFile.getAbsolutePath())
                .withDebug(true)
                .withDebugPort(String.valueOf(DEBUGGER_PORT))
                .build();
    }

    private class SpyDebuggerIde implements DebuggerIde {
        boolean connected = false;
        Status currentStatus;

        @Override
        public void onConnected(SystemInfo message) throws InterruptedException {
            connected = true;
        }

        @Override
        public void onStatus(Status status, DBGpIde dbgpIde) {
            currentStatus = status;
        }
    }
}

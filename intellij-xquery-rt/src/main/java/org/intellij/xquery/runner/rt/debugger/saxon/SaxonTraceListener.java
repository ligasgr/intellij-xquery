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

import com.codnos.dbgp.api.Status;
import net.sf.saxon.Controller;
import net.sf.saxon.expr.UserFunctionCall;
import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.expr.flwor.ClauseInfo;
import net.sf.saxon.expr.instruct.TraceExpression;
import net.sf.saxon.lib.Logger;
import net.sf.saxon.lib.TraceListener;
import net.sf.saxon.om.Item;
import net.sf.saxon.om.StandardNames;
import net.sf.saxon.trace.InstructionInfo;
import org.intellij.xquery.runner.rt.debugger.DebugFrame;

import java.util.LinkedList;

import static net.sf.saxon.trace.LocationKind.FUNCTION_CALL;
import static org.intellij.xquery.runner.rt.debugger.LogUtil.log;

public class SaxonTraceListener implements TraceListener {
    private final LinkedList<String> functionNames = new LinkedList<>();
    private final SaxonDebuggerApp debugger;

    SaxonTraceListener(SaxonDebuggerApp debugger) {
        this.debugger = debugger;
    }

    @Override
    public void setOutputDestination(Logger logger) { }

    @Override
    public void open(Controller controller) { }

    @Override
    public void close() { }

    @Override
    public void startCurrentItem(Item item) { }

    @Override
    public void endCurrentItem(Item item) { }

    @Override
    public void enter(InstructionInfo instructionInfo, XPathContext xPathContext) {
        log("enter [" + myToString(instructionInfo) + "]" + instructionInfo.toString() + " line: " + instructionInfo.getLineNumber() + " constructType: " + instructionInfo.getConstructType());
        if (instructionInfo instanceof ClauseInfo) {
            log("entering clause info of clause: " + ((ClauseInfo) instructionInfo).getClause());
            return;
        }

        if (is(instructionInfo, StandardNames.XSL_FUNCTION)) {
            log("exiting enter as this is a bit faulty frame");
            return;
        }

        if (debugger.getStatus() != Status.BREAK) {
            DebugFrame currentFrame = debugger.getCurrentFrame();
            logPreviousFrame(currentFrame);
            String currentFunctionName = functionNames.size() > 0 ? functionNames.peekLast() : null;
            if (isUserFunctionCall(instructionInfo)) {
                addNewFunctionNameToStack(instructionInfo);
                logFunctionNames();
            }
            debugger.enter(new SaxonDebugFrame(instructionInfo, xPathContext, currentFunctionName, debugger.getMainModule()));
        }
    }

    @Override
    public void leave(InstructionInfo instructionInfo) {
        log("leave [" + myToString(instructionInfo) + "]" + instructionInfo.toString() + " line: " + instructionInfo.getLineNumber() + " constructType: " + instructionInfo.getConstructType());
        if (instructionInfo instanceof ClauseInfo) {
            log("leaving clause info of clause: " + ((ClauseInfo) instructionInfo).getClause());
            return;
        }

        if (is(instructionInfo, StandardNames.XSL_FUNCTION)) {
            log("exiting leave as this is a bit faulty frame");
            return;
        }
        if (debugger.getStatus() != Status.BREAK) {
            if (wasUserFunctionCall(instructionInfo)) {
                functionNames.removeLast();
            }
            debugger.leave();
        }
    }

    private void addNewFunctionNameToStack(InstructionInfo instructionInfo) {
        functionNames.add(instructionInfo.getObjectName().getDisplayName());
    }

    private boolean wasUserFunctionCall(InstructionInfo instructionInfo) {
        return is(instructionInfo, FUNCTION_CALL) && ((TraceExpression) instructionInfo).getChild() instanceof UserFunctionCall;
    }

    private boolean isUserFunctionCall(InstructionInfo instructionInfo) {
        return instructionInfo.getObjectName() != null && instructionInfo instanceof TraceExpression && is(instructionInfo, FUNCTION_CALL) && ((TraceExpression) instructionInfo).getChild() instanceof UserFunctionCall;
    }

    private boolean is(InstructionInfo instructionInfo, int code) {
        return instructionInfo.getConstructType() == code;
    }

    private void logPreviousFrame(DebugFrame currentFrame) {
        if (currentFrame != null) {
            log("previous frame will be: " + currentFrame.getUri() + " line: " + currentFrame.getLineNumber());
        }
    }

    private void logFunctionNames() {
        StringBuilder sb = new StringBuilder("all functions till now:");
        sb.append(System.lineSeparator());
        for (String f : functionNames) {
            sb.append(f);
            sb.append(System.lineSeparator());
        }
        log(sb.toString());
    }

    private String myToString(Object ob) {
        return ob.getClass().getName() + "@" + Integer.toHexString(ob.hashCode());
    }
}

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

import net.sf.saxon.expr.PackageData;
import net.sf.saxon.expr.StackFrame;
import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.expr.instruct.GlobalVariable;
import net.sf.saxon.expr.instruct.SlotManager;
import net.sf.saxon.om.Sequence;
import net.sf.saxon.query.QueryModule;
import net.sf.saxon.trace.InstructionInfo;
import net.sf.saxon.trans.XPathException;
import net.sf.saxon.value.Cardinality;
import net.sf.saxon.value.MemoClosure;
import org.intellij.xquery.runner.rt.debugger.DebugFrame;
import org.intellij.xquery.runner.rt.debugger.Variable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.intellij.xquery.runner.rt.debugger.LogUtil.log;

public class SaxonDebugFrame implements DebugFrame {
    private final int lineNumber;
    private final XPathContext xPathContext;
    private final String uri;
    private final String functionName;
    private final SaxonExpressionEvaluator evaluator;

    public SaxonDebugFrame(InstructionInfo instructionInfo, XPathContext xPathContext, String functionName, QueryModule mainModule) {
        final String uri = instructionInfo.getSystemId();
        this.xPathContext = xPathContext;
        this.uri = uri != null ? uri.replaceAll(" ", "%20=") : null;
        this.functionName = functionName;
        this.evaluator = new SaxonExpressionEvaluator(xPathContext, mainModule, uri);
        lineNumber = instructionInfo.getLineNumber();
        log("uri=" + uri + " lineNumber=" + lineNumber + " xpathcontext=" + xPathContext);
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getUri() {
        return uri;
    }

    public String getFunctionName() {
        return functionName;
    }

    @Override
    public Optional<Variable> eval(String expressionString) {
        return evaluator.eval(expressionString);
    }

    public List<Variable> getVariables() {
        final List<Variable> variables = new ArrayList<>();
        Set<String> variableNames = new HashSet<>();

        for (PackageData packageData : xPathContext.getController().getExecutable().getPackages()) {
            final List<GlobalVariable> globalVariables = packageData.getGlobalVariableList();
            if (globalVariables != null) {
                for (GlobalVariable globalVariable : globalVariables) {
                    Sequence sequence = getSequence(globalVariable);
                    String[] value = SaxonItemConverter.getSequenceValue(sequence);
                    if (value != null) {
                        String type = globalVariable.getRequiredType().getPrimaryType().toString();
                        String cardinality = Cardinality.getOccurrenceIndicator(globalVariable.getRequiredType().getCardinality());
                        variables.add(new Variable(globalVariable.getVariableQName().getDisplayName(), type + cardinality, value[0]));
                        variableNames.add(globalVariable.getVariableQName().getDisplayName());
                    }
                }
            }
        }

        final StackFrame frame = xPathContext.getStackFrame();
        final SlotManager map = frame.getStackFrameMap();

        Sequence[] values = frame.getStackFrameValues();
        for (int i = 0, valuesLength = values.length; i < valuesLength; i++) {
            final Sequence value = values[i];
            if (value != null && isAvailableForRead(value)) {
                final String name = map.getVariableMap().get(i).getDisplayName();
                if (variableNames.contains(name)) {
                    continue;
                }

                String[] sequenceValue = SaxonItemConverter.getSequenceValue(value);
                if (sequenceValue != null) {
                    variables.add(new Variable(name, sequenceValue[1], sequenceValue[0]));
                    variableNames.add(name);

                }
            }
        }
        return variables;
    }

    private boolean isAvailableForRead(Sequence value) {
        return !(value instanceof MemoClosure) || ((MemoClosure) value).isFullyRead();
    }

    private Sequence getSequence(GlobalVariable globalVariable) {
        try {
            return globalVariable.evaluateVariable(xPathContext);
        } catch (XPathException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "SaxonDebugFrame{" +
                "lineNumber=" + lineNumber +
                ", uri='" + uri + '\'' +
                ", functionName='" + functionName + '\'' +
                '}';
    }
}

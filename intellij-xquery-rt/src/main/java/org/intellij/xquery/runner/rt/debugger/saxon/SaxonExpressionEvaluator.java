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

import net.sf.saxon.Configuration;
import net.sf.saxon.expr.Expression;
import net.sf.saxon.expr.GlobalVariableReference;
import net.sf.saxon.expr.LocalVariableReference;
import net.sf.saxon.expr.PackageData;
import net.sf.saxon.expr.StackFrame;
import net.sf.saxon.expr.StaticContext;
import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.expr.flwor.LocalVariableBinding;
import net.sf.saxon.expr.instruct.GlobalVariable;
import net.sf.saxon.expr.instruct.SlotManager;
import net.sf.saxon.expr.parser.ContextItemStaticInfo;
import net.sf.saxon.expr.parser.ExpressionTool;
import net.sf.saxon.expr.parser.ExpressionVisitor;
import net.sf.saxon.expr.parser.Location;
import net.sf.saxon.expr.parser.RetainedStaticContext;
import net.sf.saxon.expr.parser.Token;
import net.sf.saxon.functions.FunctionLibrary;
import net.sf.saxon.om.NamespaceResolver;
import net.sf.saxon.om.SequenceIterator;
import net.sf.saxon.om.StructuredQName;
import net.sf.saxon.query.QueryModule;
import net.sf.saxon.trans.DecimalFormatManager;
import net.sf.saxon.trans.KeyManager;
import net.sf.saxon.trans.XPathException;
import net.sf.saxon.type.ItemType;
import net.sf.saxon.type.Type;
import org.intellij.xquery.runner.rt.debugger.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static net.sf.saxon.value.SequenceType.ANY_SEQUENCE;

public class SaxonExpressionEvaluator {
    private final XPathContext xPathContext;
    private final QueryModule mainModule;
    private final String uri;

    private final Map<StructuredQName, GlobalVariable> fixedUpGlobalVariables = new HashMap<>();

    public SaxonExpressionEvaluator(XPathContext xPathContext, QueryModule mainModule, String uri) {
        this.xPathContext = xPathContext;
        this.mainModule = mainModule;
        this.uri = uri;
    }

    public Optional<Variable> eval(String expressionString) {
        QueryModule evaluatedModule = getModule();
        StaticContext wrappingContext = wrapContext(evaluatedModule);
        if (wrappingContext != null) {
            try {
                Expression expression = ExpressionTool.make(expressionString, wrappingContext, 0, Token.EOF, null);
                final ExpressionVisitor visitor = ExpressionVisitor.make(wrappingContext);
                expression = expression.typeCheck(visitor, new ContextItemStaticInfo(Type.ITEM_TYPE, true));
                SlotManager stackFrameMap = xPathContext.getStackFrame().getStackFrameMap();
                final int variables = stackFrameMap.getNumberOfVariables();
                ExpressionTool.allocateSlots(expression, variables, stackFrameMap);
                final SequenceIterator it = expression.iterate(xPathContext);
                String[] sequenceValue = SaxonItemConverter.getSequenceValue(it);
                if (sequenceValue != null) {
                    return Optional.of(new Variable("evalResult", sequenceValue[1], sequenceValue[0]));
                } else {
                    return Optional.empty();
                }
            } catch (AssertionError | Exception e) {
                System.err.println("Unable to evaluate: '" + expressionString + "'");
                e.printStackTrace();
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    private StaticContext wrapContext(QueryModule staticContext) {
        if (staticContext == null) return null;
        return new WrappingStaticContext(staticContext);
    }

    private QueryModule getModule() {
        Iterator modules = xPathContext.getController().getExecutable().getQueryLibraryModules();
        while (modules.hasNext()) {
            QueryModule module = (QueryModule) modules.next();
            if (uri.equals(module.getLocationURI().toASCIIString())) {
                return module;
            }
        }
        return mainModule;
    }

    private class WrappingStaticContext implements StaticContext {
        private final QueryModule original;

        public WrappingStaticContext(QueryModule original) {
            this.original = original;
        }

        @Override
        public Configuration getConfiguration() {
            return original.getConfiguration();
        }

        @Override
        public PackageData getPackageData() {
            return original.getPackageData();
        }

        @Override
        public XPathContext makeEarlyEvaluationContext() {
            return original.makeEarlyEvaluationContext();
        }

        @Override
        public RetainedStaticContext makeRetainedStaticContext() {
            return original.makeRetainedStaticContext();
        }

        @Override
        public Location getContainingLocation() {
            return original.getContainingLocation();
        }

        @Override
        public void issueWarning(String message, Location locator) {
            original.issueWarning(message, locator);
        }

        @Override
        public String getSystemId() {
            return original.getSystemId();
        }

        @Override
        public String getStaticBaseURI() {
            return original.getStaticBaseURI();
        }

        @Override
        public Expression bindVariable(StructuredQName qName) throws XPathException {
            final StackFrame frame = xPathContext.getStackFrame();
            final SlotManager map = frame.getStackFrameMap();

            if (fixedUpGlobalVariables.containsKey(qName)) {
                Expression variableReference = original.bindVariable(qName);
                if (variableReference instanceof GlobalVariableReference) {
                    GlobalVariable var = fixedUpGlobalVariables.get(qName);
                    if (var.getObjectName().equals(qName)) {
                        ((GlobalVariableReference) variableReference).fixup(var);
                        return variableReference;
                    }
                }
            }
            try {
                Expression variableReference = original.bindVariable(qName);
                if (variableReference instanceof GlobalVariableReference) {

                    List<Iterator<GlobalVariable>> globalVariableIteratos = new ArrayList<>();
                    globalVariableIteratos.add(original.getModuleVariables());
                    if (original.isTopLevelModule()) {
                        globalVariableIteratos.add(original.getGlobalVariables().iterator());
                    } else {
                        globalVariableIteratos.add(mainModule.getModuleVariables());
                    }
                    for (Iterator<GlobalVariable> globalVariableIterator : globalVariableIteratos) {
                        while (globalVariableIterator.hasNext()) {
                            GlobalVariable var = globalVariableIterator.next();
                            if (var.getObjectName().equals(qName)) {
                                int slot = map.allocateSlotNumber(var.getVariableQName());
                                var.compile(xPathContext.getController().getExecutable(), slot);
                                ((GlobalVariableReference) variableReference).fixup(var);
                                fixedUpGlobalVariables.put(qName, var);
                                return variableReference;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                // do nothing, try local variables
            }

            for (int i = 0; i < map.getVariableMap().size(); i++) {
                final StructuredQName name = map.getVariableMap().get(i);
                if (name.equals(qName)) {
                    LocalVariableBinding binding = new LocalVariableBinding(qName, ANY_SEQUENCE);
                    binding.setSlotNumber(i);
                    return new LocalVariableReference(binding);
                }
            }

            XPathException err = new XPathException("Variable $" + qName.getDisplayName() + " has not been declared");
            err.setErrorCode("XPST0008");
            err.setIsStaticError(true);
            throw err;
        }

        @Override
        public FunctionLibrary getFunctionLibrary() {
            return original.getFunctionLibrary();
        }

        @Override
        public String getDefaultCollationName() {
            return original.getDefaultCollationName();
        }

        @Override
        public String getDefaultElementNamespace() {
            return original.getDefaultElementNamespace();
        }

        @Override
        public String getDefaultFunctionNamespace() {
            return original.getDefaultFunctionNamespace();
        }

        @Override
        public boolean isInBackwardsCompatibleMode() {
            return original.isInBackwardsCompatibleMode();
        }

        @Override
        public boolean isImportedSchema(String namespace) {
            return original.isImportedSchema(namespace);
        }

        @Override
        public Set<String> getImportedSchemaNamespaces() {
            return original.getImportedSchemaNamespaces();
        }

        @Override
        public NamespaceResolver getNamespaceResolver() {
            return original.getNamespaceResolver();
        }

        @Override
        public ItemType getRequiredContextItemType() {
            return original.getRequiredContextItemType();
        }

        @Override
        public DecimalFormatManager getDecimalFormatManager() {
            return original.getDecimalFormatManager();
        }

        @Override
        public int getXPathVersion() {
            return original.getXPathVersion();
        }

        @Override
        public KeyManager getKeyManager() {
            return original.getKeyManager();
        }
    }
}

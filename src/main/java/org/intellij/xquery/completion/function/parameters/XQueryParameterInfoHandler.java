/*
 * Copyright 2013-2015 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.completion.function.parameters;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.parameterInfo.CreateParameterInfoContext;
import com.intellij.lang.parameterInfo.ParameterInfoContext;
import com.intellij.lang.parameterInfo.ParameterInfoHandler;
import com.intellij.lang.parameterInfo.ParameterInfoUIContext;
import com.intellij.lang.parameterInfo.ParameterInfoUtils;
import com.intellij.lang.parameterInfo.UpdateParameterInfoContext;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiReference;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.containers.ContainerUtil;
import org.intellij.xquery.completion.function.BuiltInFunctionSignature;
import org.intellij.xquery.psi.XQueryArgumentList;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionCall;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.intellij.xquery.psi.XQueryParam;
import org.intellij.xquery.psi.XQueryTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.intellij.xquery.util.StringUtils.compressWhitespaces;

public class XQueryParameterInfoHandler implements ParameterInfoHandler<XQueryArgumentList, Object> {
    @Override
    public boolean couldShowInLookup() {
        return true;
    }

    @Nullable
    @Override
    public Object[] getParametersForLookup(LookupElement item, ParameterInfoContext context) {
        return ArrayUtil.EMPTY_OBJECT_ARRAY;
    }

    @Nullable
    @Override
    public Object[] getParametersForDocumentation(Object p, ParameterInfoContext context) {
        return null;
    }

    @Nullable
    @Override
    public XQueryArgumentList findElementForParameterInfo(CreateParameterInfoContext context) {
        return getArgumentList(context);
    }

    @Nullable
    @Override
    public XQueryArgumentList findElementForUpdatingParameterInfo(UpdateParameterInfoContext context) {
        return getArgumentList(context);
    }

    private XQueryArgumentList getArgumentList(ParameterInfoContext context) {
        PsiElement at = context.getFile().findElementAt(context.getOffset());
        return PsiTreeUtil.getParentOfType(at, XQueryArgumentList.class);
    }

    @Override
    public void showParameterInfo(@NotNull XQueryArgumentList args, CreateParameterInfoContext context) {
        XQueryFunctionCall functionCall = PsiTreeUtil.getParentOfType(args, XQueryFunctionCall.class);
        if (functionCall != null) {
            addItemsToShow(context, functionCall);
            context.showHint(args, args.getTextRange().getStartOffset(), this);
        }
    }

    private void addItemsToShow(CreateParameterInfoContext context, XQueryFunctionCall functionCall) {
        ResolveResult[] resolveResults = getFunctionWithAllArities(functionCall);
        List<XQueryFunctionDecl> functionDeclarations = extractFunctionDeclarations(resolveResults);
        if (functionDeclarations.size() > 0) {
            Collections.sort(functionDeclarations, getParameterListSizeComparator());
            context.setItemsToShow(ArrayUtil.toObjectArray(functionDeclarations));
        } else {
            XQueryFile file = (XQueryFile) functionCall.getContainingFile();
            String name = functionCall.getFunctionName().getLocalNameText();
            String prefix = functionCall.getFunctionName().getPrefixText();
            String namespace = file.mapFunctionPrefixToNamespace(prefix);
            context.setItemsToShow(ArrayUtil.toObjectArray(getFunctionsSignatures(file, name, namespace)));
        }
    }

    private Collection<BuiltInFunctionSignature> getFunctionsSignatures(XQueryFile file, String name, String namespace) {
        return file.getFunctionsSignatures(namespace, name);
    }

    private Comparator<XQueryFunctionDecl> getParameterListSizeComparator() {
        return new Comparator<XQueryFunctionDecl>() {
            @Override
            public int compare(XQueryFunctionDecl lhs, XQueryFunctionDecl rhs) {
                final int lhsSize = lhs.getParamList() != null ? lhs.getParamList().getParamList().size() : 0;
                final int rhsSize = rhs.getParamList() != null ? rhs.getParamList().getParamList().size() : 0;
                return Integer.signum(lhsSize - rhsSize);
            }
        };
    }

    private List<XQueryFunctionDecl> extractFunctionDeclarations(ResolveResult[] resolveResults) {
        List<XQueryFunctionDecl> functionDeclarations = new ArrayList<XQueryFunctionDecl>();
        for (ResolveResult result : resolveResults) {
            PsiElement element = result.getElement();
            if (element instanceof XQueryFunctionName) {
                functionDeclarations.add((XQueryFunctionDecl) element.getParent());
            }
        }
        return functionDeclarations;
    }

    private ResolveResult[] getFunctionWithAllArities(XQueryFunctionCall functionCall) {
        PsiReference reference = functionCall.getReference();
        return ((PsiPolyVariantReference) reference).multiResolve(true);
    }

    @Override
    public void updateParameterInfo(@NotNull XQueryArgumentList place, UpdateParameterInfoContext context) {
        context.setCurrentParameter(ParameterInfoUtils.getCurrentParameterIndex(place.getNode(), context.getOffset(),
                XQueryTypes.COMMA));
    }

    @Nullable
    @Override
    public String getParameterCloseChars() {
        return ParameterInfoUtils.DEFAULT_PARAMETER_CLOSE_CHARS;
    }

    @Override
    public boolean tracksParameterIndex() {
        return true;
    }

    @Override
    public void updateUI(Object p, ParameterInfoUIContext context) {
        if (p == null) {
            context.setUIComponentEnabled(false);
            return;
        }
        int index = context.getCurrentParameterIndex();
        ParameterPresentation presentation = null;
        if (p instanceof XQueryFunctionDecl) {
            presentation = buildUserFunctionPresentation((XQueryFunctionDecl) p, index);
        } else if (p instanceof BuiltInFunctionSignature) {
            presentation = buildBuiltInFunctionPresentation((BuiltInFunctionSignature) p, index);
        }
        context.setupUIComponentPresentation(presentation.text, presentation.start, presentation.end,
                presentation.disabled, false, true,
                context.getDefaultParameterColor());
    }

    private ParameterPresentation buildBuiltInFunctionPresentation(BuiltInFunctionSignature functionSignature,
                                                                   int index) {
        StringBuilder builder = new StringBuilder();
        int start = 0;
        int end = 0;
        boolean disabled = false;

        String arguments = functionSignature.getArguments();
        builder.append(arguments);
        for (int i = 0; i < index && start != - 1; ++ i) {
            start = arguments.indexOf(',', start + 1);
        }
        if (start == - 1) {
            disabled = true;
        } else {
            end = arguments.indexOf(',', start + 1);
            end = (end == - 1 ? arguments.length() : end);
        }
        return new ParameterPresentation(builder.toString(), start, end, disabled);
    }

    private ParameterPresentation buildUserFunctionPresentation(XQueryFunctionDecl functionDecl, int index) {
        StringBuilder builder = new StringBuilder();
        int start = 0;
        int end = 0;
        boolean disabled;
        List<XQueryParam> args = functionDecl.getParamList() != null ? functionDecl.getParamList().getParamList() :
                ContainerUtil.<XQueryParam>emptyList();

        for (int i = 0; i < args.size(); i++) {
            if (i != 0) builder.append(", ");
            if (index == i) start = builder.length();
            builder.append(compressWhitespaces(args.get(i).getText()));
            if (index == i) end = builder.length();
        }
        disabled = index >= args.size();
        return new ParameterPresentation(builder.toString(), start, end, disabled);
    }

    private class ParameterPresentation {
        String text;
        int start;
        int end;
        boolean disabled = false;

        public ParameterPresentation(String text, int start, int end, boolean disabled) {
            if (text.length() == 0) {
                this.text = "<no parameters>";
            } else {
                this.text = text;
            }
            this.start = start;
            this.end = end;
            this.disabled = disabled;
        }
    }
}

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

package org.intellij.xquery.inspection.marklogic;

import com.intellij.codeInspection.InspectionManager;
import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.psi.PsiFile;
import org.intellij.xquery.psi.*;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.intellij.codeInspection.ProblemHighlightType.*;

public class FnNamespaceAssumed extends LocalInspectionTool
{
	private static final String FUNCTION_CALL_ASSUMES_FN = "Function call assumes fn: namespace";
	private static final String ADD_FN_PREFIX = "Add fn: prefix";

	@Override
	public ProblemDescriptor[] checkFile (PsiFile file, InspectionManager manager, boolean isOnTheFly)
	{
		if ( ! (file instanceof XQueryFile)) {
			return null;
		}

		List<ProblemDescriptor> problems = getUnprexfixedBuiltins ((XQueryFile) file, manager);

		return problems.toArray (new ProblemDescriptor[problems.size ()]);
	}

	@Override
	public boolean runForWholeFile()
	{
		return true;
	}

	// ----------------------------------------------------------------------

	private static final AddFnPrefixQuickFix fixer = new AddFnPrefixQuickFix (ADD_FN_PREFIX);

	private static List<ProblemDescriptor> getUnprexfixedBuiltins (XQueryFile xQueryFile, InspectionManager manager)
	{
		Collection<XQueryFunctionInvocation> functionInvocations = xQueryFile.getFunctionInvocations();
		List<ProblemDescriptor> problems = new ArrayList<>();

		for (XQueryFunctionInvocation invocation : functionInvocations) {
			XQueryFunctionName funcName = invocation.getFunctionName();

			if ((funcName.getPrefixText() == null) && xQueryFile.isBuiltInFunction (funcName)) {
				problems.add (manager.createProblemDescriptor (funcName, FUNCTION_CALL_ASSUMES_FN,
					fixer, GENERIC_ERROR_OR_WARNING, true));
			}
		}

		return problems;
	}
}

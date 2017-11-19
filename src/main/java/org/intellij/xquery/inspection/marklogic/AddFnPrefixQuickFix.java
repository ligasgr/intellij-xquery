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

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.intellij.xquery.psi.XQueryElementFactory;
import org.intellij.xquery.psi.XQueryFunctionInvocation;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public class AddFnPrefixQuickFix implements LocalQuickFix
{
	private final String quickfixName;

	public AddFnPrefixQuickFix (String quickfixName)
	{
		this.quickfixName = quickfixName;
	}

	@Nls
	@NotNull
	@Override
	public String getName ()
	{
		return quickfixName;
	}

	@Nls
	@NotNull
	@Override
	public String getFamilyName ()
	{
		return quickfixName;
	}

	@Override
	public void applyFix (@NotNull Project project, @NotNull ProblemDescriptor problemDescriptor)
	{
		XQueryFunctionName funcName = (XQueryFunctionName) problemDescriptor.getPsiElement();

		if (funcName != null) {
			funcName.replace (XQueryElementFactory.createFunctionReference (project, "fn", funcName.getLocalNameText()));
		}
	}
}

package org.intellij.xquery.reference;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.intellij.xquery.XQueryIcons;
import org.intellij.xquery.psi.XQueryUtil;
import org.intellij.xquery.psi.XQueryVarName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * User: ligasgr
 * Date: 08/06/13
 * Time: 22:16
 */
public class XQueryVarNameReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

    public XQueryVarNameReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        final List<XQueryVarName> varNames = XQueryUtil.findVarNames(myElement.getContainingFile(), myElement.getText());
        List<ResolveResult> results = new ArrayList<ResolveResult>();
        for (XQueryVarName varName : varNames) {
            results.add(new PsiElementResolveResult(varName));
        }
        return results.toArray(new ResolveResult[results.size()]);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        List<XQueryVarName> varNames = XQueryUtil.findVarNames(myElement.getContainingFile());
        List<LookupElement> variants = new ArrayList<LookupElement>();
        for (final XQueryVarName varName : varNames) {
            if (varName.getText() != null && varName.getText().length() > 0) {
                variants.add(LookupElementBuilder.create(varName).
                        withIcon(XQueryIcons.FILE).
                        withTypeText(varName.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }
}

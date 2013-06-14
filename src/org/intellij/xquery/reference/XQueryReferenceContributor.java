package org.intellij.xquery.reference;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.intellij.xquery.psi.XQueryVarRefName;
import org.jetbrains.annotations.NotNull;

/**
 * User: ligasgr
 * Date: 08/06/13
 * Time: 22:24
 */
public class XQueryReferenceContributor extends PsiReferenceContributor {

    @Override
    public void registerReferenceProviders(PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(XQueryVarRefName.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                        XQueryVarRefName varRef = (XQueryVarRefName) element;
                        String text = varRef.getText();
                        if (text != null) {
                            return new PsiReference[]{new XQueryVarNameReference(element, new TextRange(0, text.length()))};
                        }
                        return new PsiReference[0];
                    }
                });
    }
}

package org.intellij.xquery.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import org.intellij.xquery.psi.XQueryNamedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * User: ligasgr
 * Date: 08/06/13
 * Time: 21:31
 */
public abstract class XQueryNamedElementImpl extends ASTWrapperPsiElement implements XQueryNamedElement {
    public XQueryNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    public PsiReference getReference() {
        PsiReference[] references = getReferences();
        return references.length == 0 ? null : references[0];
    }


    @NotNull

    public PsiReference[] getReferences() {
        return ReferenceProvidersRegistry.getReferencesFromProviders(this);
    }
}

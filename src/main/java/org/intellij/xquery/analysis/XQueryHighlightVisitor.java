package org.intellij.xquery.analysis;

import com.intellij.codeInsight.daemon.impl.HighlightVisitor;
import com.intellij.codeInsight.daemon.impl.analysis.HighlightInfoHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import org.intellij.xquery.XQueryFileType;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.jetbrains.annotations.NotNull;

public class XQueryHighlightVisitor extends PsiElementVisitor implements HighlightVisitor {

    private DuplicateFunctionDeclarationHighlightCreator duplicateFunctionDeclarationHighlightCreator = new DuplicateFunctionDeclarationHighlightCreator();
    private HighlightInfoHolder myHolder;
    private PsiFile myPsiFile;

    @Override
    public boolean suitableForFile(@NotNull PsiFile psiFile) {
        return psiFile.getFileType() instanceof XQueryFileType;
    }

    @Override
    public void visit(@NotNull PsiElement psiElement) {
        if (psiElement instanceof XQueryFunctionName) {
            myHolder.add(duplicateFunctionDeclarationHighlightCreator.createDuplicateFunctionDeclarationHighlight(((XQueryFunctionName) psiElement), (XQueryFile) myPsiFile));
        }
    }

    @Override
    public boolean analyze(@NotNull PsiFile psiFile, boolean updateWholeFile, @NotNull HighlightInfoHolder holder, @NotNull Runnable action) {
        myHolder = holder;
        myPsiFile = psiFile;
        try {
            action.run();
        } finally {
            myHolder = null;
            myPsiFile = null;
        }
        return true;
    }

    @NotNull
    @Override
    public HighlightVisitor clone() {
        return new XQueryHighlightVisitor();
    }

    @Override
    public int order() {
        return 0;
    }
}

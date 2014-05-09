package org.intellij.xquery.navigation;

import com.intellij.codeInsight.navigation.MethodNavigationOffsetProvider;
import com.intellij.codeInsight.navigation.MethodUpDownUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class XQueryFunctionNavigationOffsetProvider implements MethodNavigationOffsetProvider {
    @Override
    @Nullable
    public int[] getMethodNavigationOffsets(PsiFile file, int caretOffset) {
        if (file instanceof XQueryFile) {
            ArrayList<PsiElement> array = new ArrayList<PsiElement>();
            addNavigationElements(array, file);
            return MethodUpDownUtil.offsetsFromElements(array);
        }
        return null;
    }

    private void addNavigationElements(ArrayList<PsiElement> array, PsiElement element) {
        for (PsiElement child : element.getChildren()) {
            if (child instanceof XQueryFunctionDecl) {
                array.add(child);
                addNavigationElements(array, child);
            }
        }
    }
}

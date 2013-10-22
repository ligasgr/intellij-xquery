package org.intellij.xquery.analysis;

import com.intellij.openapi.util.TextRange;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionName;

public class DuplicateFunctionDeclarationTextRangeCalculator {

    public TextRange calculateTextRange(XQueryFunctionDecl functionDeclaration) {
        int startOffset = functionDeclaration.getTextRange().getStartOffset();
        int endOffset = functionDeclaration.getParamList() != null ?
                functionDeclaration.getParamList().getTextRange().getEndOffset()
                : functionDeclaration.getTextRange().getEndOffset();
        return new TextRange(startOffset, endOffset);
    }
}
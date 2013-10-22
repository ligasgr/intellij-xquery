package org.intellij.xquery.analysis;

import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.codeInsight.daemon.impl.HighlightInfoType;
import com.intellij.openapi.util.TextRange;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionName;

public class DuplicateFunctionDeclarationHighlightCreator {

    private DuplicateFunctionDeclarationVerifier verifier = new DuplicateFunctionDeclarationVerifier();
    private DuplicateFunctionDeclarationTextRangeCalculator textRangeCalculator = new DuplicateFunctionDeclarationTextRangeCalculator();
    private DuplicateFunctionDeclarationDescriptionCreator descriptionProducer = new DuplicateFunctionDeclarationDescriptionCreator();

    public HighlightInfo createDuplicateFunctionDeclarationHighlight(XQueryFunctionName functionName, XQueryFile file) {
        HighlightInfo result = null;
        boolean hasDuplicate = verifier.functionHasDuplicateDeclarationInFile(functionName, file);

        if (hasDuplicate) {
            XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) functionName.getParent();
            TextRange textRange = textRangeCalculator.calculateTextRange(functionDeclaration);
            String description = descriptionProducer.createDescription(functionName, file);
            result = getHighlightInfo(textRange, description);
        }
        return result;
    }

    HighlightInfo getHighlightInfo(TextRange textRange, String description) {
        return HighlightInfo.newHighlightInfo(HighlightInfoType.ERROR)
                .range(textRange)
                .descriptionAndTooltip(description)
                .create();
    }

}

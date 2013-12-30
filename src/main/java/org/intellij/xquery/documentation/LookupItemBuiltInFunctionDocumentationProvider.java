package org.intellij.xquery.documentation;

import static org.intellij.xquery.completion.function.BuiltInFunctionTable.isBuiltInFunction;
import static org.intellij.xquery.documentation.DocumentationStylist.wrapWithHtmlAndStyle;

/**
 * User: ligasgr
 * Date: 30/12/13
 * Time: 23:07
 */
public class LookupItemBuiltInFunctionDocumentationProvider implements PsiBasedDocumentationProvider<XQueryDocElement> {
    @Override
    public String generateDoc(XQueryDocElement element) {
        String namespace = element.getNamespace();
        String name = element.getName();
        if (isBuiltInFunction(namespace, name)) {
            return getDocumentationFromExternalFile(namespace, name);
        }
        return null;
    }

    private String getDocumentationFromExternalFile(String namespace, String name) {
        String doc = ExternalDocumentationFetcher.fetch(name);
        if (doc != null)
            return wrapWithHtmlAndStyle(doc);
        else
            return null;
    }
}

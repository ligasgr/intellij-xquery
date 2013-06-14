package org.intellij.xquery;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.LookAheadLexer;
import com.intellij.lexer.MergingLexerAdapter;
import static org.intellij.xquery.XQueryParserDefinition.COMMENTS;

/**
 * User: ligasgr
 * Date: 25/03/13
 * Time: 21:16
 */
public class XQueryLexer extends LookAheadLexer {

    public XQueryLexer() {
        super(new MergingLexerAdapter(new FlexAdapter(new _XQueryLexer()), COMMENTS));
    }

    public void start(CharSequence buffer, int startOffset, int endOffset, int initialState) {
        super.start(buffer, startOffset, endOffset, initialState);
    }
}

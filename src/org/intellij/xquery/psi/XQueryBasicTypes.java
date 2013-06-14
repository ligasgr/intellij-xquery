package org.intellij.xquery.psi;

import com.intellij.psi.tree.IElementType;

/**
 * User: ligasgr
 * Date: 25/03/13
 * Time: 20:22
 */
public interface XQueryBasicTypes {

    IElementType EXPR_COMMENT_CONTENT = new XQueryTokenType("ExprCommentContent");
    IElementType EXPR_COMMENT_START = new XQueryTokenType("ExprCommentStart");
    IElementType EXPR_COMMENT_END = new XQueryTokenType("ExprCommentEnd");
}

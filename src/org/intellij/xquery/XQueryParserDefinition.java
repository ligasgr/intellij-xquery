package org.intellij.xquery;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.intellij.xquery.parser.XQueryParser;
import org.intellij.xquery.psi.XQueryBasicTypes;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryTypes;
import org.jetbrains.annotations.NotNull;

import java.io.Reader;

/**
 * User: ligasgr
 * Date: 10/02/13
 * Time: 19:01
 */
public class XQueryParserDefinition implements ParserDefinition {
    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet COMMENTS = TokenSet.create(XQueryBasicTypes.EXPR_COMMENT_CONTENT, XQueryBasicTypes.EXPR_COMMENT_END, XQueryBasicTypes.EXPR_COMMENT_START);
    public static final TokenSet STRINGS = TokenSet.create(XQueryTypes.STRINGLITERAL);

    public static final IFileElementType FILE = new IFileElementType(Language.<XQueryLanguage>findInstance(XQueryLanguage.class));


    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new XQueryLexer();
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return STRINGS;
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        return new XQueryParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new XQueryFile(viewProvider);
    }

    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        return XQueryTypes.Factory.createElement(node);
    }
}

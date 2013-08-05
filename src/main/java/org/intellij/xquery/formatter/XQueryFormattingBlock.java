/*
 * Copyright 2013 Grzegorz Ligas <ligasgr@gmail.com> and other contributors (see the CONTRIBUTORS file).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.intellij.xquery.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.formatter.FormatterUtil;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.containers.ContainerUtil;
import org.intellij.xquery.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.intellij.xquery.psi.XQueryTypes.*;

/**
 * User: ligasgr
 * Date: 21/08/13
 * Time: 19:37
 */
public class XQueryFormattingBlock extends AbstractBlock {

    private static final Set<IElementType> BIN_OPERATORS = ContainerUtil.set(
            K_OR, K_AND, PIPE_PIPE, K_TO, OP_PLUS, OP_MINUS, STAR_SIGN, K_DIV, K_IDIV, K_MOD,
            K_UNION, PIPE, K_INTERSECT, K_EXCEPT, K_INSTANCE, K_OF, K_TREAT, K_AS, K_CASTABLE,
            K_CAST, EQ, NE, LT, LE, GT, GE, EQUAL, NOT_EQUAL, LT_CHAR, LE_CHARS, GT_CHAR, GE_CHARS,
            K_IS, NODECOMP_LT, NODECOMP_GT, EXCLAMATION_MARK, SLASH, SLASH_SLASH
    );

    private final SpacingBuilder spacingBuilder;
    private final CommonCodeStyleSettings settings;

    public XQueryFormattingBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment,
                                 @NotNull CommonCodeStyleSettings settings, @NotNull SpacingBuilder spacingBuilder) {
        super(node, wrap, alignment);
        this.spacingBuilder = spacingBuilder;
        this.settings = settings;
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<Block>();
        ASTNode child = myNode.getFirstChildNode();
        while (child != null) {
            if (child.getElementType() != TokenType.WHITE_SPACE && child.getTextRange().getLength() != 0) {
                Block block = new XQueryFormattingBlock(child, Wrap.createWrap(WrapType.NONE, false), null, settings,
                        spacingBuilder);
                blocks.add(block);
            }
            child = child.getTreeNext();
        }
        return blocks;
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return spacingBuilder.getSpacing(this, child1, child2);
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }

    @Override
    public Indent getIndent() {
        IElementType type = myNode.getElementType();
        ASTNode parent = myNode.getTreeParent();
        IElementType parentType = parent != null ? parent.getElementType() : null;
        IElementType prevType = getTypeOfPreviousElement(myNode);

        if (parent == null)
            return Indent.getNoneIndent();
        if (isExpressionAfterBrace(type, prevType) || isExpressionAfterParenthesis(type,
                prevType) || isXmlChild(type) || isForOrLetBinding(parentType))
            return Indent.getNormalIndent();
        if (isASingleExpression()) {
            if (parentType == IF_EXPR) {
                return Indent.getNormalIndent();
            }
            if (parentType == WHERE_CLAUSE ||
                    parentType == RETURN_CLAUSE ||
                    parentType == VAR_VALUE ||
                    parentType == ORDER_SPEC ||
                    parentType == SWITCH_RETURN_CLAUSE) {
                return Indent.getNormalIndent();
            }
        }
        if (type == SWITCH_RETURN_CLAUSE || type == SWITCH_DEFAULT_RETURN_CLAUSE || type ==
                TYPESWITCH_DEFAULT_RETURN_CLAUSE || type == CASE_CLAUSE || type == SWITCH_CASE_CLAUSE) {
            return Indent.getNormalIndent();
        }
        if (isParamOrArgumentList(parentType) && (type != L_PAR && type != R_PAR)) {
            return Indent.getContinuationIndent();
        }
        if (isChildOfSingleExpression()) {
            if (BIN_OPERATORS.contains(type) || BIN_OPERATORS.contains(prevType)) {
                return Indent.getContinuationIndent();
            }
        }
        return Indent.getNoneIndent();
    }

    private boolean isExpressionAfterBrace(IElementType type, IElementType typeOfPreviousElement) {
        return (type == EXPR || type == CONTENT_EXPR) && typeOfPreviousElement == L_C_BRACE;
    }

    private boolean isExpressionAfterParenthesis(IElementType type, IElementType typeOfPreviousElement) {
        return type == EXPR && typeOfPreviousElement == L_PAR;
    }

    @NotNull
    @Override
    public ChildAttributes getChildAttributes(int newChildIndex) {
        IElementType type = myNode.getElementType();
        Indent childIndent = calculateChildIndent(type);
        if (childIndent == null && newChildIndex > 0) {
            IElementType calculatedType = getIElementType(newChildIndex);
            childIndent = calculateChildIndent(calculatedType);
        }
        return new ChildAttributes(childIndent != null ? childIndent : Indent.getNoneIndent(), null);
    }

    private Indent calculateChildIndent(IElementType type) {
        if (type == ENCLOSED_EXPR || type == FUNCTION_DECL || type == FLWOR_EXPR || type == PARENTHESIZED_EXPR)
            return Indent.getNormalIndent();
        return null;
    }

    private boolean isASingleExpression() {
        return myNode.getPsi() instanceof XQueryExprSingle;
    }

    private IElementType getTypeOfPreviousElement(ASTNode myNode) {
        ASTNode prevSibling = FormatterUtil.getPreviousNonWhitespaceSibling(myNode);
        IElementType prevType = prevSibling != null ? prevSibling.getElementType() : null;
        return prevType;
    }

    private boolean isChildOfSingleExpression() {
        return myNode.getPsi().getParent() instanceof XQueryExprSingle;
    }

    private boolean isParamOrArgumentList(IElementType parentType) {
        return parentType == PARAM_LIST || parentType == ARGUMENT_LIST;
    }

    private boolean isForOrLetBinding(IElementType parentType) {
        return parentType == LET_BINDING || parentType == FOR_BINDING;
    }

    private boolean isXmlChild(IElementType type) {
        return type == DIR_ELEM_CONTENT;
    }

    @Nullable
    private IElementType getIElementType(int newChildIndex) {
        Block block = getSubBlocks().get(newChildIndex - 1);
        while (block instanceof XQueryFormattingBlock && !block.getSubBlocks().isEmpty()) {
            List<Block> subBlocks = block.getSubBlocks();
            Block childBlock = subBlocks.get(subBlocks.size() - 1);
            if (!(childBlock instanceof XQueryFormattingBlock)) break;
            else {
                ASTNode node = ((XQueryFormattingBlock) childBlock).getNode();
                PsiElement psi = node.getPsi();
                IElementType elementType = node.getElementType();
                if (elementType instanceof XQueryTokenType) break;
                if (psi instanceof LeafPsiElement || psi instanceof XQueryFunctionName || psi instanceof
                        XQueryVarName || psi instanceof XQueryNamespaceName)
                    break;
            }
            block = childBlock;
        }
        return block instanceof XQueryFormattingBlock ? ((XQueryFormattingBlock) block).getNode().getElementType() : null;
    }
}

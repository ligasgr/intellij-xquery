/*
 * Copyright 2013-2016 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
 * (see the CONTRIBUTORS file).
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
    IElementType DOC_COMMENT_CONTENT = new XQueryTokenType("DocCommentContent");
    IElementType DOC_COMMENT_START = new XQueryTokenType("DocCommentStart");
    IElementType DOC_COMMENT_END = new XQueryTokenType("DocCommentEnd");
}

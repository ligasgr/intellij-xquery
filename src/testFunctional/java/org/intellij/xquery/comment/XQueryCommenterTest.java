/*
 * Copyright 2013-2014 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.comment;

import com.intellij.codeInsight.generation.actions.CommentByBlockCommentAction;
import com.intellij.codeInsight.generation.actions.CommentByLineCommentAction;
import org.intellij.xquery.BaseFunctionalTestCase;
import org.intellij.xquery.XQueryFileType;

/**
 * User: ligasgr
 * Date: 05/07/13
 * Time: 23:57
 */
public class XQueryCommenterTest extends BaseFunctionalTestCase {

    private static final String NEW_LINE = "\n";
    private static final String TEXT_TO_COMMENT = "<tag><innerTag>content</innerTag></tag>";
    private static final String NOT_COMMENTED_TEXT = "some text";
    private static final String NOT_COMMENTED_LINE = NOT_COMMENTED_TEXT + NEW_LINE;
    private static final String COMMENT_START = "(:";
    private static final String COMMENT_END = ":)";
    private static final String CARET = "<caret>";
    private static final String SELECTION_START = "<selection>";
    private static final String SELECTION_END = "</selection>";
    private static final String ONE_LINE_TO_COMMENT_IN_BETWEEN_OF_TWO_OTHER_LINES = NOT_COMMENTED_LINE + CARET +
            TEXT_TO_COMMENT + NEW_LINE + NOT_COMMENTED_TEXT;
    private static final String TWO_LINES_TO_COMMENT_IN_BETWEEN_OF_TWO_OTHER_LINES = NOT_COMMENTED_LINE +
            SELECTION_START + TEXT_TO_COMMENT + NEW_LINE + TEXT_TO_COMMENT + SELECTION_END +
            NEW_LINE + NOT_COMMENTED_TEXT;

    public void testCommentByLineForSingleLine() {
        myFixture.configureByText(XQueryFileType.INSTANCE, CARET + TEXT_TO_COMMENT);
        CommentByLineCommentAction commentAction = new CommentByLineCommentAction();

        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());

        myFixture.checkResult(COMMENT_START + TEXT_TO_COMMENT + COMMENT_END);
    }

    public void testUncommentByLineForSingleLine() {
        myFixture.configureByText(XQueryFileType.INSTANCE, CARET + TEXT_TO_COMMENT);
        CommentByLineCommentAction commentAction = new CommentByLineCommentAction();
        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());

        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());

        myFixture.checkResult(TEXT_TO_COMMENT);
    }

    public void testCommentByLineForSingleLineInMultipleLines() {
        myFixture.configureByText(XQueryFileType.INSTANCE, ONE_LINE_TO_COMMENT_IN_BETWEEN_OF_TWO_OTHER_LINES);
        CommentByLineCommentAction commentAction = new CommentByLineCommentAction();

        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());

        myFixture.checkResult(NOT_COMMENTED_LINE + COMMENT_START + TEXT_TO_COMMENT + COMMENT_END + NEW_LINE +
                NOT_COMMENTED_TEXT);
    }

    public void testUncommentByLineForSingleLineInMultipleLines() {
        myFixture.configureByText(XQueryFileType.INSTANCE, ONE_LINE_TO_COMMENT_IN_BETWEEN_OF_TWO_OTHER_LINES);
        CommentByLineCommentAction commentAction = new CommentByLineCommentAction();
        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
        myFixture.getEditor().getCaretModel().moveCaretRelatively(0, -1, false, false, false);

        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());

        myFixture.getEditor().getCaretModel().moveCaretRelatively(0, -1, false, false, false);
        myFixture.checkResult(ONE_LINE_TO_COMMENT_IN_BETWEEN_OF_TWO_OTHER_LINES);
    }

    public void testCommentByLineForMultipleLinesInMultipleLines() {
        myFixture.configureByText(XQueryFileType.INSTANCE, TWO_LINES_TO_COMMENT_IN_BETWEEN_OF_TWO_OTHER_LINES);
        CommentByLineCommentAction commentAction = new CommentByLineCommentAction();

        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());

        myFixture.checkResult(
                NOT_COMMENTED_LINE +
                        COMMENT_START + SELECTION_START + TEXT_TO_COMMENT + COMMENT_END + NEW_LINE + COMMENT_START +
                        TEXT_TO_COMMENT + SELECTION_END + COMMENT_END +
                        NEW_LINE + NOT_COMMENTED_TEXT);
    }

    public void testUncommentByLineForMultipleLinesInMultipleLines() {
        myFixture.configureByText(XQueryFileType.INSTANCE, TWO_LINES_TO_COMMENT_IN_BETWEEN_OF_TWO_OTHER_LINES);
        CommentByLineCommentAction commentAction = new CommentByLineCommentAction();
        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
        int secondLineStartOffset = NOT_COMMENTED_LINE.length();
        int fourthLineStartOffset = (NOT_COMMENTED_LINE + COMMENT_START + TEXT_TO_COMMENT + COMMENT_END + NEW_LINE +
                COMMENT_START + TEXT_TO_COMMENT + COMMENT_END).length();
        myFixture.getEditor().getSelectionModel().setSelection(secondLineStartOffset, fourthLineStartOffset);

        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());

        myFixture.checkResult(TWO_LINES_TO_COMMENT_IN_BETWEEN_OF_TWO_OTHER_LINES);
    }

    public void testCommentByBlockWithoutSelection() {
        myFixture.configureByText(XQueryFileType.INSTANCE, CARET + TEXT_TO_COMMENT);
        CommentByBlockCommentAction commentAction = new CommentByBlockCommentAction();

        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());

        myFixture.checkResult(COMMENT_START + COMMENT_END + TEXT_TO_COMMENT);
        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());

        myFixture.checkResult(COMMENT_START + COMMENT_START + COMMENT_END + COMMENT_END + TEXT_TO_COMMENT);
    }

    public void testCommentByBlockForSingleLine() {
        myFixture.configureByText(XQueryFileType.INSTANCE, SELECTION_START + TEXT_TO_COMMENT + SELECTION_END +
                NEW_LINE);
        CommentByBlockCommentAction commentAction = new CommentByBlockCommentAction();

        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());

        myFixture.checkResult(SELECTION_START + COMMENT_START + TEXT_TO_COMMENT + COMMENT_END + SELECTION_END +
                NEW_LINE);
    }

    public void testUncommentByBlockForSingleLine() {
        myFixture.configureByText(XQueryFileType.INSTANCE, SELECTION_START + TEXT_TO_COMMENT + SELECTION_END +
                NEW_LINE);
        CommentByBlockCommentAction commentAction = new CommentByBlockCommentAction();
        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
        myFixture.checkResult(SELECTION_START + COMMENT_START + TEXT_TO_COMMENT + COMMENT_END + SELECTION_END +
                NEW_LINE);

        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());

        myFixture.checkResult(SELECTION_START + TEXT_TO_COMMENT + SELECTION_END + NEW_LINE);
    }

    public void testCommentByBlockForMultipleLines() {
        myFixture.configureByText(XQueryFileType.INSTANCE, TWO_LINES_TO_COMMENT_IN_BETWEEN_OF_TWO_OTHER_LINES);
        CommentByBlockCommentAction commentAction = new CommentByBlockCommentAction();

        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());

        myFixture.checkResult(
                NOT_COMMENTED_LINE + SELECTION_START +
                        COMMENT_START + TEXT_TO_COMMENT + NEW_LINE + TEXT_TO_COMMENT + COMMENT_END +
                        SELECTION_END + NEW_LINE + NOT_COMMENTED_TEXT);
    }

    public void testUncommentByBlockForMultipleLines() {
        myFixture.configureByText(XQueryFileType.INSTANCE, TWO_LINES_TO_COMMENT_IN_BETWEEN_OF_TWO_OTHER_LINES);
        CommentByBlockCommentAction commentAction = new CommentByBlockCommentAction();
        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
        myFixture.checkResult(
                NOT_COMMENTED_LINE + SELECTION_START +
                        COMMENT_START + TEXT_TO_COMMENT + NEW_LINE + TEXT_TO_COMMENT + COMMENT_END +
                        SELECTION_END + NEW_LINE + NOT_COMMENTED_TEXT);

        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());

        myFixture.checkResult(TWO_LINES_TO_COMMENT_IN_BETWEEN_OF_TWO_OTHER_LINES);
    }
}

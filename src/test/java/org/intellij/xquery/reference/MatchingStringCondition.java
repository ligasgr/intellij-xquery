package org.intellij.xquery.reference;

import com.intellij.openapi.util.Condition;

/**
* User: ligasgr
* Date: 15/08/13
* Time: 00:07
*/
public class MatchingStringCondition implements Condition<String> {
    private String matchingText;

    public MatchingStringCondition(String matchingText) {
        this.matchingText = matchingText;
    }

    @Override
    public boolean value(String text) {
        return matchingText.equals(text);
    }
}

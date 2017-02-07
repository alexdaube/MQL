package com.multitel.mql.mqlservice.domain.translators;

import com.multitel.mql.mqlservice.domain.StringQuery;

public class JunctionTranslatorState implements QueryTranslatorState {
    private final QueryTranslator queryTranslator;

    public JunctionTranslatorState(QueryTranslator queryTranslator) {
        this.queryTranslator = queryTranslator;
    }

    @Override
    public boolean translate(StringQuery stringQuery) {
        queryTranslator.changeState(new InitialTranslatorState(queryTranslator));
        return false;
    }
}

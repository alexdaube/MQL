package com.multitel.mql.mqlservice.domain.translators;

import com.multitel.mql.mqlservice.domain.InvalidQueryException;
import com.multitel.mql.mqlservice.domain.StringQuery;
import com.multitel.mql.mqlservice.domain.interpreters.*;

public class ValueTranslatorState implements QueryTranslatorState {
    private final QueryTranslator queryTranslator;
    private final Interpreter interpreter;

    public ValueTranslatorState(QueryTranslator queryTranslator) {
        interpreter = new ValueInterpreter();
        this.queryTranslator = queryTranslator;
    }

    @Override
    public boolean translate(StringQuery stringQuery) {
        stringQuery.strip();
        if (interpreter.interpret(stringQuery, queryTranslator.getQueryBuilder())) {
            return false;
        } else if (stringQuery.isEmpty()) {
            return true;
        }
        throw new InvalidQueryException("You specified an invalid value...");
    }
}

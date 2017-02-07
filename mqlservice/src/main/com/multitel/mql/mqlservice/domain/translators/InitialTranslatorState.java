package com.multitel.mql.mqlservice.domain.translators;

import com.multitel.mql.mqlservice.domain.StringQuery;
import com.multitel.mql.mqlservice.domain.interpreters.EntityInterpreter;
import com.multitel.mql.mqlservice.domain.interpreters.Interpreter;
import com.multitel.mql.mqlservice.domain.InvalidQueryException;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class InitialTranslatorState implements QueryTranslatorState {
    private final Interpreter entityInterpreter;
    private final QueryTranslator queryTranslator;

    public InitialTranslatorState(QueryTranslator queryTranslator) {
        this.queryTranslator = queryTranslator;
        Set<String> entities = new HashSet<>();
        entities.add("Employee");
        this.entityInterpreter = new EntityInterpreter(entities);
    }

    @Override
    public boolean translate(StringQuery stringQuery) {
        if (entityInterpreter.interpret(stringQuery, queryTranslator.getQueryBuilder())) {
            queryTranslator.changeState(new EntityTranslatorState(queryTranslator));
            return false;
        }
        throw new InvalidQueryException("A query should begin with the table name...");
    }
}

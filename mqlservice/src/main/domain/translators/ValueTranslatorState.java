package domain.translators;

import domain.InvalidQueryException;
import domain.QueryBuilder;
import domain.StringQuery;
import domain.interpreters.Interpreter;
import domain.interpreters.JunctionInterpreter;
import domain.interpreters.ValueInterpreter;
import domain.keyword.KeywordsResolver;

public class ValueTranslatorState implements QueryTranslatorState {
    private final Interpreter valueInterpreter;
    private final Interpreter junctionInterpreter;
    private final KeywordsResolver keywordsResolver;
    private final QueryBuilder queryBuilder;

    public ValueTranslatorState(QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
        this.valueInterpreter = new ValueInterpreter();
        this.junctionInterpreter = new JunctionInterpreter(keywordsResolver.resolveJunctions());
        this.queryBuilder = queryBuilder;
        this.keywordsResolver = keywordsResolver;
    }

    @Override
    public StateStatus translate(StringQuery stringQuery) {
        stringQuery.strip();
        if (stringQuery.isEmpty()) {
            return new StateStatus(true, this);
        } else if (valueInterpreter.interpret(stringQuery, queryBuilder)) {
            return new StateStatus(false, this);
        } else if (junctionInterpreter.interpret(stringQuery, queryBuilder)) {
            return new StateStatus(false, new JunctionTranslatorState(queryBuilder, keywordsResolver));
        }
        throw new InvalidQueryException("You specified an invalid value...");
    }
}

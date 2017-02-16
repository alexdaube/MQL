package domain.translators;

import domain.InvalidQueryException;
import domain.querybuilder.QueryBuilder;
import domain.Query;
import domain.interpreters.Interpreter;
import domain.interpreters.JunctionInterpreter;
import domain.interpreters.ValueInterpreter;
import domain.keywords.KeywordsResolver;

public class ValueTranslatorState implements QueryTranslatorState {
    private final Interpreter valueInterpreter;
    private final Interpreter junctionInterpreter;
    private final KeywordsResolver keywordsResolver;
    private final QueryBuilder queryBuilder;

    public ValueTranslatorState(QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
        this.valueInterpreter = new ValueInterpreter();
        this.junctionInterpreter = new JunctionInterpreter(keywordsResolver.resolveAndJunctions(), keywordsResolver.resolveOrJunctions());
        this.queryBuilder = queryBuilder;
        this.keywordsResolver = keywordsResolver;
    }

    @Override
    public StateStatus translate(Query query) {
        query.strip();
        if (query.isEmpty()) {
            return new StateStatus(true, this);
        } else if (valueInterpreter.interpret(query, queryBuilder)) {
            return new StateStatus(false, this);
        } else if (junctionInterpreter.interpret(query, queryBuilder)) {
            return new StateStatus(false, new JunctionTranslatorState(queryBuilder, keywordsResolver));
        }
        throw new InvalidQueryException("You specified an invalid value...");
    }
}

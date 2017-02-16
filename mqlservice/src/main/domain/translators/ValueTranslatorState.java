package domain.translators;

import domain.InvalidQueryException;
import domain.Query;
import domain.interpreters.Interpreter;
import domain.interpreters.JunctionInterpreter;
import domain.interpreters.ValueInterpreter;
import domain.keywords.KeywordsResolver;
import domain.querybuilder.QueryBuilder;

public class ValueTranslatorState implements QueryTranslatorState {
    private final Interpreter valueInterpreter;
    private final Interpreter junctionInterpreter;
    private final KeywordsResolver keywordsResolver;
    private final QueryBuilder queryBuilder;

    public ValueTranslatorState(QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
        this(new ValueInterpreter(), new JunctionInterpreter(keywordsResolver.resolveAndJunctions(),
                keywordsResolver.resolveOrJunctions()), keywordsResolver, queryBuilder);
    }

    public ValueTranslatorState(Interpreter valueInterpreter, Interpreter junctionInterpreter, KeywordsResolver keywordsResolver, QueryBuilder queryBuilder) {
        this.valueInterpreter = valueInterpreter;
        this.junctionInterpreter = junctionInterpreter;
        this.keywordsResolver = keywordsResolver;
        this.queryBuilder = queryBuilder;
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

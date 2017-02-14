package domain.translators;

import domain.InvalidQueryException;
import domain.QueryBuilder;
import domain.StringQuery;
import domain.interpreters.EntityInterpreter;
import domain.interpreters.Interpreter;
import domain.keyword.KeywordsResolver;

import java.util.HashSet;
import java.util.Set;

public class InitialTranslatorState implements QueryTranslatorState {
    private final Interpreter entityInterpreter;
    private final KeywordsResolver keywordsResolver;
    private final QueryBuilder queryBuilder;

    public InitialTranslatorState(QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
        this.entityInterpreter = new EntityInterpreter(keywordsResolver.resolveEntities());
        this.queryBuilder = queryBuilder;
        this.keywordsResolver = keywordsResolver;
    }

    @Override
    public StateStatus translate(StringQuery stringQuery) {
        if (entityInterpreter.interpret(stringQuery, queryBuilder)) {
            return new StateStatus(false, new EntityTranslatorState(queryBuilder, keywordsResolver));
        }
        throw new InvalidQueryException("A query should begin with the table name...");
    }
}

package domain.translators;

import domain.InvalidQueryException;
import domain.querybuilder.QueryBuilder;
import domain.Query;
import domain.interpreters.EntityInterpreter;
import domain.interpreters.Interpreter;
import domain.keywords.KeywordsResolver;

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
    public StateStatus translate(Query query) {
        if (entityInterpreter.interpret(query, queryBuilder)) {
            return new StateStatus(false, new EntityTranslatorState(queryBuilder, keywordsResolver));
        }
        throw new InvalidQueryException("A query should begin with the table name...");
    }
}

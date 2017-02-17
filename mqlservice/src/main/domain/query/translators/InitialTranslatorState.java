package domain.query.translators;

import domain.InvalidQueryException;
import domain.query.Query;
import domain.interpreters.EntityInterpreter;
import domain.interpreters.Interpreter;
import domain.keywords.Keywords;
import domain.keywords.KeywordsResolver;
import domain.query.builder.QueryBuilder;

public class InitialTranslatorState implements QueryTranslatorState {
    private final Interpreter entityInterpreter;
    private final KeywordsResolver keywordsResolver;
    private final QueryBuilder queryBuilder;

    public InitialTranslatorState(QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
        this(new EntityInterpreter(keywordsResolver.resolveType(Keywords.Type.ENTITY)), keywordsResolver, queryBuilder);
    }

    public InitialTranslatorState(Interpreter entityInterpreter, KeywordsResolver keywordsResolver, QueryBuilder queryBuilder) {
        this.entityInterpreter = entityInterpreter;
        this.keywordsResolver = keywordsResolver;
        this.queryBuilder = queryBuilder;
    }

    @Override
    public StateStatus translate(Query query) {
        if (entityInterpreter.interpret(query, queryBuilder)) {
            return new StateStatus(false, new EntityTranslatorState(queryBuilder, keywordsResolver));
        }
        throw new InvalidQueryException("A query should begin with the table name...");
    }
}

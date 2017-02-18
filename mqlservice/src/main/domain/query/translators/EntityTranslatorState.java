package domain.query.translators;

import domain.InvalidQueryException;
import domain.query.Query;
import domain.interpreters.AttributeInterpreter;
import domain.interpreters.Interpreter;
import domain.keywords.Keywords;
import domain.keywords.KeywordsResolver;
import domain.query.builder.QueryBuilder;

public class EntityTranslatorState implements QueryTranslatorState {
    private final Interpreter attributeInterpreter;
    private final QueryBuilder queryBuilder;
    private final KeywordsResolver keywordsResolver;

    public EntityTranslatorState(QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
        this(new AttributeInterpreter(keywordsResolver.resolveType(Keywords.Type.ATTRIBUTE)), queryBuilder, keywordsResolver);
    }

    public EntityTranslatorState(Interpreter attributeInterpreter, QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
        this.attributeInterpreter = attributeInterpreter;
        this.queryBuilder = queryBuilder;
        this.keywordsResolver = keywordsResolver;
    }

    @Override
    public StateStatus translate(Query query) {
        if (attributeInterpreter.interpret(query, queryBuilder)) {
            return new StateStatus(false, new AttributeTranslatorState(queryBuilder, keywordsResolver));
        }
        throw new InvalidQueryException("The table name should be followed by an attribute");
    }
}

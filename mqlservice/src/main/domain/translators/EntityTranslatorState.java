package domain.translators;

import domain.querybuilder.QueryBuilder;
import domain.Query;
import domain.InvalidQueryException;
import domain.interpreters.AttributeInterpreter;
import domain.interpreters.Interpreter;
import domain.keywords.KeywordsResolver;

public class EntityTranslatorState implements QueryTranslatorState {
    private final Interpreter attributeInterpreter;
    private final QueryBuilder queryBuilder;
    private final KeywordsResolver keywordsResolver;

    public EntityTranslatorState(QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
        this.attributeInterpreter = new AttributeInterpreter(keywordsResolver.resolveAttributes());
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

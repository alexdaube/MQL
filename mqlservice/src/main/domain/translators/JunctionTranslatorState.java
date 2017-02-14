package domain.translators;

import domain.InvalidQueryException;
import domain.QueryBuilder;
import domain.StringQuery;
import domain.interpreters.AttributeInterpreter;
import domain.interpreters.EntityInterpreter;
import domain.interpreters.Interpreter;
import domain.keyword.KeywordsResolver;

public class JunctionTranslatorState implements QueryTranslatorState {
    private final KeywordsResolver keywordsResolver;
    private final QueryBuilder queryBuilder;
    private final Interpreter entityInterpreter;
    private final Interpreter attributeInterpreter;

    public JunctionTranslatorState(QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
        this.keywordsResolver = keywordsResolver;
        this.queryBuilder = queryBuilder;
        this.entityInterpreter = new EntityInterpreter(keywordsResolver.resolveEntities());
        this.attributeInterpreter = new AttributeInterpreter(keywordsResolver.resolveAttributes());
    }

    @Override
    public StateStatus translate(StringQuery stringQuery) {
        if (entityInterpreter.interpret(stringQuery, queryBuilder)) {
            return new StateStatus(false, new InitialTranslatorState(queryBuilder, keywordsResolver));
        } else if (attributeInterpreter.interpret(stringQuery, queryBuilder)) {
            return new StateStatus(false, new OperatorTranslatorState(queryBuilder, keywordsResolver));
        }
        throw new InvalidQueryException("A junction should be followed by an table or an attribute...");
    }
}

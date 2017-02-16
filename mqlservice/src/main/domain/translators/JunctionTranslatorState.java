package domain.translators;

import domain.InvalidQueryException;
import domain.querybuilder.QueryBuilder;
import domain.Query;
import domain.interpreters.*;
import domain.keywords.KeywordsResolver;

public class JunctionTranslatorState implements QueryTranslatorState {
    private final KeywordsResolver keywordsResolver;
    private final QueryBuilder queryBuilder;
    private final Interpreter entityInterpreter;
    private final Interpreter attributeInterpreter;
    private final OperatorInterpreter operatorInterpreter;
    private final ValueInterpreter valueInterpreter;

    public JunctionTranslatorState(QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
        this.keywordsResolver = keywordsResolver;
        this.queryBuilder = queryBuilder;
        this.entityInterpreter = new EntityInterpreter(keywordsResolver.resolveEntities());
        this.attributeInterpreter = new AttributeInterpreter(keywordsResolver.resolveAttributes());
        this.operatorInterpreter = new OperatorInterpreter(keywordsResolver);
        this.valueInterpreter = new ValueInterpreter();
    }

    @Override
    public StateStatus translate(Query query) {
        if (entityInterpreter.interpret(query, queryBuilder)) {
            return new StateStatus(false, new EntityTranslatorState(queryBuilder, keywordsResolver));
        } else if (attributeInterpreter.interpret(query, queryBuilder)) {
            return new StateStatus(false, new AttributeTranslatorState(queryBuilder, keywordsResolver));
            // TODO: 15/02/17 Ajouter test
        } else if (operatorInterpreter.interpret(query, queryBuilder)) {
            return new StateStatus(false, new OperatorTranslatorState(queryBuilder, keywordsResolver));
        } else if (valueInterpreter.interpret(query, queryBuilder)) {
            return new StateStatus(false, new ValueTranslatorState(queryBuilder, keywordsResolver));
        }
        throw new InvalidQueryException("A junction should be followed by an table or an attribute...");
    }
}

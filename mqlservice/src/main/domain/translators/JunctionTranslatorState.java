package domain.translators;

import domain.InvalidQueryException;
import domain.Query;
import domain.interpreters.*;
import domain.keywords.KeywordsResolver;
import domain.querybuilder.QueryBuilder;

public class JunctionTranslatorState implements QueryTranslatorState {
    private final Interpreter entityInterpreter;
    private final Interpreter attributeInterpreter;
    private final Interpreter operatorInterpreter;
    private final Interpreter valueInterpreter;
    private final KeywordsResolver keywordsResolver;
    private final QueryBuilder queryBuilder;

    public JunctionTranslatorState(QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
        this(new EntityInterpreter(keywordsResolver.resolveEntities()),
                new AttributeInterpreter(keywordsResolver.resolveAttributes()),
                new OperatorInterpreter(keywordsResolver), new ValueInterpreter(),
                keywordsResolver, queryBuilder);
    }

    public JunctionTranslatorState(Interpreter entityInterpreter, Interpreter attributeInterpreter,
                                   Interpreter operatorInterpreter, Interpreter valueInterpreter,
                                   KeywordsResolver keywordsResolver, QueryBuilder queryBuilder) {
        this.entityInterpreter = entityInterpreter;
        this.attributeInterpreter = attributeInterpreter;
        this.operatorInterpreter = operatorInterpreter;
        this.valueInterpreter = valueInterpreter;
        this.keywordsResolver = keywordsResolver;
        this.queryBuilder = queryBuilder;
    }

    @Override
    public StateStatus translate(Query query) {
        if (entityInterpreter.interpret(query, queryBuilder)) {
            return new StateStatus(false, new EntityTranslatorState(queryBuilder, keywordsResolver));
        } else if (attributeInterpreter.interpret(query, queryBuilder)) {
            return new StateStatus(false, new AttributeTranslatorState(queryBuilder, keywordsResolver));
        } else if (operatorInterpreter.interpret(query, queryBuilder)) {
            return new StateStatus(false, new OperatorTranslatorState(queryBuilder, keywordsResolver));
        } else if (valueInterpreter.interpret(query, queryBuilder)) {
            return new StateStatus(false, new ValueTranslatorState(queryBuilder, keywordsResolver));
        }
        throw new InvalidQueryException("A junction should be followed by an table, an attribute, an operator or a value...");
    }
}

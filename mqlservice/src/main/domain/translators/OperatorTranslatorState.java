package domain.translators;

import domain.querybuilder.QueryBuilder;
import domain.Query;
import domain.interpreters.ValueInterpreter;
import domain.InvalidQueryException;
import domain.interpreters.Interpreter;
import domain.interpreters.OperatorInterpreter;
import domain.keywords.KeywordsResolver;

public class OperatorTranslatorState implements QueryTranslatorState {
    private final Interpreter valueInterpreter;
    private final OperatorInterpreter operatorInterpreter;
    private final KeywordsResolver keywordsResolver;
    private final QueryBuilder queryBuilder;

    public OperatorTranslatorState(QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
        this.valueInterpreter = new ValueInterpreter();
        this.operatorInterpreter = new OperatorInterpreter(keywordsResolver);
        this.keywordsResolver = keywordsResolver;
        this.queryBuilder = queryBuilder;
    }

    @Override
    public StateStatus translate(Query query) {
        if (valueInterpreter.interpret(query, queryBuilder)) {
            return new StateStatus(false, new ValueTranslatorState(queryBuilder, keywordsResolver));
        } else if (operatorInterpreter.interpret(query, queryBuilder)) {
            return new StateStatus(false, this);
        }
        throw new InvalidQueryException("An operator should be followed by an other operator or by a value...");
    }
}

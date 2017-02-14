package domain.translators;

import domain.QueryBuilder;
import domain.StringQuery;
import domain.interpreters.ValueInterpreter;
import domain.InvalidQueryException;
import domain.interpreters.Interpreter;
import domain.interpreters.OperatorInterpreter;
import domain.keyword.KeywordsResolver;

import java.util.HashSet;
import java.util.Set;

public class OperatorTranslatorState implements QueryTranslatorState {
    private final Interpreter valueInterpreter;
    private final OperatorInterpreter operatorInterpreter;
    private final KeywordsResolver keywordsResolver;
    private final QueryBuilder queryBuilder;

    public OperatorTranslatorState(QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
        this.valueInterpreter = new ValueInterpreter();
        this.operatorInterpreter = new OperatorInterpreter(keywordsResolver.resolveOperators());
        this.keywordsResolver = keywordsResolver;
        this.queryBuilder = queryBuilder;
    }

    @Override
    public StateStatus translate(StringQuery stringQuery) {
        if (valueInterpreter.interpret(stringQuery, queryBuilder)) {
            return new StateStatus(false, new ValueTranslatorState(queryBuilder, keywordsResolver));
        } else if (operatorInterpreter.interpret(stringQuery, queryBuilder)) {
            return new StateStatus(false, this);
        }
        throw new InvalidQueryException("An operator should be followed by an other operator withJunction by a value...");
    }
}

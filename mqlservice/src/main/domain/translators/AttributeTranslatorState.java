package domain.translators;

import domain.InvalidQueryException;
import domain.QueryBuilder;
import domain.StringQuery;
import domain.interpreters.Interpreter;
import domain.interpreters.OperatorInterpreter;
import domain.keyword.KeywordsResolver;

import java.util.HashSet;
import java.util.Set;

public class AttributeTranslatorState implements QueryTranslatorState {
    private final QueryBuilder queryBuilder;
    private final Interpreter interpreter;
    private final KeywordsResolver keywordsResolver;

    public AttributeTranslatorState(QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
        this.interpreter = new OperatorInterpreter(keywordsResolver.resolveOperators());
        this.queryBuilder = queryBuilder;
        this.keywordsResolver = keywordsResolver;
    }

    @Override
    public StateStatus translate(StringQuery stringQuery) {
        if (interpreter.interpret(stringQuery, queryBuilder)) {
            return new StateStatus(false, new OperatorTranslatorState(queryBuilder, keywordsResolver));
        }
        throw new InvalidQueryException("The attribute should be followed by an operator");
    }
}

package domain.translators;

import domain.InvalidQueryException;
import domain.querybuilder.QueryBuilder;
import domain.Query;
import domain.interpreters.Interpreter;
import domain.interpreters.OperatorInterpreter;
import domain.keywords.KeywordsResolver;

public class AttributeTranslatorState implements QueryTranslatorState {
    private final QueryBuilder queryBuilder;
    private final Interpreter interpreter;
    private final KeywordsResolver keywordsResolver;

    public AttributeTranslatorState(QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
        this.interpreter = new OperatorInterpreter(keywordsResolver);
        this.queryBuilder = queryBuilder;
        this.keywordsResolver = keywordsResolver;
    }

    @Override
    public StateStatus translate(Query query) {
        if (interpreter.interpret(query, queryBuilder)) {
            return new StateStatus(false, new OperatorTranslatorState(queryBuilder, keywordsResolver));
        }
        throw new InvalidQueryException("The attribute should be followed by an operator");
    }
}

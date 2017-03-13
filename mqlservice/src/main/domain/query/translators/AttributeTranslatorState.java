package domain.query.translators;

import domain.InvalidQueryException;
import domain.query.Query;
import domain.interpreters.Interpreter;
import domain.interpreters.InterpreterFactory;
import domain.keywords.KeywordsResolver;
import domain.query.builder.QueryBuilder;

public class AttributeTranslatorState implements QueryTranslatorState {
    private final QueryBuilder queryBuilder;
    private final Interpreter interpreter;
    private final KeywordsResolver keywordsResolver;

    public AttributeTranslatorState(QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
        this(queryBuilder, InterpreterFactory.basicOperators(keywordsResolver), keywordsResolver);
    }

    public AttributeTranslatorState(QueryBuilder queryBuilder, Interpreter interpreter, KeywordsResolver keywordsResolver) {
        this.queryBuilder = queryBuilder;
        this.interpreter = interpreter;
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
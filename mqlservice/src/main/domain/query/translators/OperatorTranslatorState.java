package domain.query.translators;

import com.google.gson.JsonArray;
import domain.InvalidQueryException;
import domain.interpreters.Interpreter;
import domain.interpreters.InterpreterFactory;
import domain.keywords.KeywordsResolver;
import domain.query.Query;
import domain.query.builder.QueryBuilder;

public class OperatorTranslatorState implements QueryTranslatorState {
    private final Interpreter valueInterpreter;
    private final Interpreter operatorInterpreter;
    private final KeywordsResolver keywordsResolver;
    private final QueryBuilder queryBuilder;

    public OperatorTranslatorState(QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
        this(InterpreterFactory.allValues(), InterpreterFactory.allOperators(keywordsResolver), keywordsResolver, queryBuilder);
    }

    public OperatorTranslatorState(Interpreter valueInterpreter, Interpreter operatorInterpreter,
                                   KeywordsResolver keywordsResolver, QueryBuilder queryBuilder) {
        this.valueInterpreter = valueInterpreter;
        this.operatorInterpreter = operatorInterpreter;
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

    @Override
    public JsonArray translateNextSuggestion(Query query) {
        return new JsonArray();
    }
}

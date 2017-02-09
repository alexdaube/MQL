package domain.translators;

import domain.StringQuery;
import domain.interpreters.ValueInterpreter;
import domain.InvalidQueryException;
import domain.interpreters.Interpreter;
import domain.interpreters.OperatorInterpreter;

import java.util.HashSet;
import java.util.Set;

public class OperatorTranslatorState implements QueryTranslatorState {
    private final QueryTranslator queryTranslator;
    private final Interpreter valueInterpreter;
    private final OperatorInterpreter operatorInterpreter;

    public OperatorTranslatorState(QueryTranslator queryTranslator) {
        valueInterpreter = new ValueInterpreter();
        this.queryTranslator = queryTranslator;
        Set<String> operators = new HashSet<>();
        operators.add("is");
        operators.add("equal");
        operators.add("to");
        operators.add("in");
        operators.add("or");
        operators.add("less");
        operators.add("greater");
        operatorInterpreter = new OperatorInterpreter(operators);
    }

    @Override
    public boolean translate(StringQuery stringQuery) {
        if (valueInterpreter.interpret(stringQuery, queryTranslator.getQueryBuilder())) {
            queryTranslator.changeState(new ValueTranslatorState(queryTranslator));
            return false;
        } else if (operatorInterpreter.interpret(stringQuery, queryTranslator.getQueryBuilder())) {
            return false;
        }
        throw new InvalidQueryException("An operator should be followed by an other operator withJunction by a value...");
    }
}

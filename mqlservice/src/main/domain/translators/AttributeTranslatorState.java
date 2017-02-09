package domain.translators;

import domain.InvalidQueryException;
import domain.StringQuery;
import domain.interpreters.Interpreter;
import domain.interpreters.OperatorInterpreter;

import java.util.HashSet;
import java.util.Set;

public class AttributeTranslatorState implements QueryTranslatorState {
    private final QueryTranslator queryTranslator;
    private final Interpreter interpreter;

    public AttributeTranslatorState(QueryTranslator queryTranslator) {
        Set<String> operators = new HashSet<>();
        operators.add("is");
        operators.add("equal");
        operators.add("to");
        operators.add("in");
        operators.add("or");
        operators.add("less");
        operators.add("greater");
        interpreter = new OperatorInterpreter(operators);
        this.queryTranslator = queryTranslator;
    }

    @Override
    public boolean translate(StringQuery stringQuery) {
        if (interpreter.interpret(stringQuery, queryTranslator.getQueryBuilder())) {
            queryTranslator.changeState(new OperatorTranslatorState(queryTranslator));
            return false;
        }
        throw new InvalidQueryException("The attribute should be followed by an operator");
    }
}

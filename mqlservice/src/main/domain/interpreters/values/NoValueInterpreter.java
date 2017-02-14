package domain.interpreters.values;

import domain.QueryBuilder;
import domain.StringQuery;
import domain.interpreters.Interpreter;

public class NoValueInterpreter implements Interpreter {

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        return false; // This is a normal behavior
    }
}

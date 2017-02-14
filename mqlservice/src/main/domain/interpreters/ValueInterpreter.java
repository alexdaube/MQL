package domain.interpreters;

import domain.QueryBuilder;
import domain.StringQuery;
import domain.interpreters.values.*;

public class ValueInterpreter implements Interpreter {
    private final Interpreter valueInterpreters;

    public ValueInterpreter(Interpreter valueInterpreters) {
        this.valueInterpreters = valueInterpreters;
    }

    public ValueInterpreter() {
        this(new VarcharInterpreter(new DateInterpreter(new DecimalInterpreter(
                new IntegerInterpreter(new NoValueInterpreter())))));
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        return valueInterpreters.interpret(query, queryBuilder);
    }
}

package domain.interpreters;

import domain.Query;
import domain.interpreters.values.DateInterpreter;
import domain.interpreters.values.DecimalInterpreter;
import domain.interpreters.values.IntegerInterpreter;
import domain.interpreters.values.VarcharInterpreter;
import domain.querybuilder.QueryBuilder;

public class ValueInterpreter implements Interpreter {
    private final Interpreter valueInterpreters;

    public ValueInterpreter(Interpreter valueInterpreters) {
        this.valueInterpreters = valueInterpreters;
    }

    public ValueInterpreter() {
        this(new CompositeInterpreter(
                new VarcharInterpreter(), new DateInterpreter(), new DecimalInterpreter(), new IntegerInterpreter()
        ));
    }

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
        return valueInterpreters.interpret(query, queryBuilder);
    }
}

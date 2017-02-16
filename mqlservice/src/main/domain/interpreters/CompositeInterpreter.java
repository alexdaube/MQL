package domain.interpreters;

import domain.querybuilder.QueryBuilder;
import domain.Query;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CompositeInterpreter implements Interpreter {
    private final List<Interpreter> interpreters;

    public CompositeInterpreter(Interpreter...interpreters) {
        this.interpreters = new LinkedList<>(Arrays.asList(interpreters));
    }

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
        for (Interpreter interpreter:interpreters) {
            if (interpreter.interpret(query, queryBuilder)) {
                return true;
            }
        }
        return false;
    }
}

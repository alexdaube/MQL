package domain.translators;

import domain.StringQuery;
import domain.interpreters.ValueInterpreter;
import domain.InvalidQueryException;
import domain.interpreters.Interpreter;
import domain.interpreters.JunctionInterpreter;

import java.util.HashSet;
import java.util.Set;

public class ValueTranslatorState implements QueryTranslatorState {
    private final QueryTranslator queryTranslator;
    private final Interpreter valueInterpreter;
    private final Interpreter junctionInterpreter;

    public ValueTranslatorState(QueryTranslator queryTranslator) {
        valueInterpreter = new ValueInterpreter();
        Set<String> junctions = new HashSet<>();
        junctions.add("and");
        junctions.add("or");
        junctionInterpreter = new JunctionInterpreter(junctions);
        this.queryTranslator = queryTranslator;
    }

    @Override
    public boolean translate(StringQuery stringQuery) {
        stringQuery.strip();
        if (stringQuery.isEmpty()) {
            return true;
        } else if (valueInterpreter.interpret(stringQuery, queryTranslator.getQueryBuilder())) {
            return false;
        } else if (junctionInterpreter.interpret(stringQuery, queryTranslator.getQueryBuilder())) {
            return false;
        }
        throw new InvalidQueryException("You specified an invalid value...");
    }
}

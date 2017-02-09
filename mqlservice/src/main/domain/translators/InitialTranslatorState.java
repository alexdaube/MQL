package domain.translators;

import domain.InvalidQueryException;
import domain.StringQuery;
import domain.interpreters.EntityInterpreter;
import domain.interpreters.Interpreter;

import java.util.HashSet;
import java.util.Set;

public class InitialTranslatorState implements QueryTranslatorState {
    private final Interpreter entityInterpreter;
    private final QueryTranslator queryTranslator;

    public InitialTranslatorState(QueryTranslator queryTranslator) {
        this.queryTranslator = queryTranslator;
        Set<String> entities = new HashSet<>();
        entities.add("Employee");
        this.entityInterpreter = new EntityInterpreter(entities);
    }

    @Override
    public boolean translate(StringQuery stringQuery) {
        if (entityInterpreter.interpret(stringQuery, queryTranslator.getQueryBuilder())) {
            queryTranslator.changeState(new EntityTranslatorState(queryTranslator));
            return false;
        }
        throw new InvalidQueryException("A query should begin with the table name...");
    }
}

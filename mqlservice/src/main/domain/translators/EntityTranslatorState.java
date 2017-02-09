package domain.translators;

import domain.StringQuery;
import domain.InvalidQueryException;
import domain.interpreters.AttributeInterpreter;
import domain.interpreters.Interpreter;

import java.util.HashSet;
import java.util.Set;

public class EntityTranslatorState implements QueryTranslatorState {
    private final Interpreter attributeInterpreter;
    private final QueryTranslator queryTranslator;

    public EntityTranslatorState(QueryTranslator queryTranslator) {
        Set<String> attributes = new HashSet<>();
        attributes.add("name");
        this.attributeInterpreter = new AttributeInterpreter(attributes);
        this.queryTranslator = queryTranslator;
    }

    @Override
    public boolean translate(StringQuery stringQuery) {
        if (attributeInterpreter.interpret(stringQuery, queryTranslator.getQueryBuilder())) {
            queryTranslator.changeState(new AttributeTranslatorState(queryTranslator));
            return false;
        }
        throw new InvalidQueryException("The table name should be followed by an attribute");
    }
}

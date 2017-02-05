package com.multitel.mql.mqlservice.domain.translators;

import com.multitel.mql.mqlservice.domain.InvalidQueryException;
import com.multitel.mql.mqlservice.domain.StringQuery;
import com.multitel.mql.mqlservice.domain.interpreters.*;

import java.util.LinkedList;
import java.util.List;

public class EntityTranslatorState implements QueryTranslatorState {
    private final Interpreter attributeInterpreter;
    private final QueryTranslator queryTranslator;

    public EntityTranslatorState(QueryTranslator queryTranslator) {
        List<String> attributes = new LinkedList<>();
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

package com.multitel.mql.mqlservice.domain.interpreters;

import com.multitel.mql.mqlservice.domain.QueryBuilder;
import com.multitel.mql.mqlservice.domain.StringQuery;

import java.util.List;

public class AttributeInterpreter implements Interpreter {
    private final List<String> attributes;

    public AttributeInterpreter(List<String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        for (String attribute: attributes) {
            if (query.extractFirst(attribute)) {
                queryBuilder.withAttribute(attribute);
                return true;
            }
        }
        return false;
    }
}

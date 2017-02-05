package com.multitel.mql.mqlservice.domain.interpreters;

import com.multitel.mql.mqlservice.domain.QueryBuilder;
import com.multitel.mql.mqlservice.domain.StringQuery;

import java.util.List;

public class EntityInterpreter implements Interpreter {
    private final List<String> entities;

    public EntityInterpreter(List<String> entities) {
        this.entities = entities;
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        for(String entity: entities) {
            if (query.extractFirst(entity)) {
                queryBuilder.withEntity(entity);
                return true;
            }
        }
        return false;
    }
}

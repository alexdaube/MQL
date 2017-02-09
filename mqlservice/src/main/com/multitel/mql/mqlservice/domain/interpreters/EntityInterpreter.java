package com.multitel.mql.mqlservice.domain.interpreters;

import com.multitel.mql.mqlservice.domain.QueryBuilder;
import com.multitel.mql.mqlservice.domain.StringQuery;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityInterpreter implements Interpreter {
    private final Set<String> entities;
    private final Pattern entityPattern;

    public EntityInterpreter(Set<String> entities) {
        this.entities = entities;
        entityPattern = Pattern.compile("^[\\w-]+");
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(entityPattern);
        if (matches.find()) {
            String match = matches.group();
            if (entities.contains(match)) {
                query.removeFirstMatch(entityPattern);
                queryBuilder.withEntity(match);
                return true;
            }
        }
        return false;
    }
}

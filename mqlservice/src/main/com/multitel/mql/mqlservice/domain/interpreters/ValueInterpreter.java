package com.multitel.mql.mqlservice.domain.interpreters;

import com.multitel.mql.mqlservice.domain.InvalidQueryException;
import com.multitel.mql.mqlservice.domain.QueryBuilder;
import com.multitel.mql.mqlservice.domain.StringQuery;

import java.util.regex.Pattern;

public class ValueInterpreter implements Interpreter {
    private final Pattern pattern;
    public ValueInterpreter() {
        this.pattern = Pattern.compile("^(\"[^\"]*\")|^([-]?(\\d+)(\\.\\d*)?)");
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        try {
            String value = query.extractFirst(pattern);
            queryBuilder.withValue(value);
            return true;
        } catch (InvalidQueryException ignored) { }
        return false;
    }
}

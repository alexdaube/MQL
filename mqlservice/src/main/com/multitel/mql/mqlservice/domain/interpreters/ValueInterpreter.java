package com.multitel.mql.mqlservice.domain.interpreters;

import com.multitel.mql.mqlservice.domain.QueryBuilder;
import com.multitel.mql.mqlservice.domain.StringQuery;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueInterpreter implements Interpreter {
    private final Pattern pattern;

    public ValueInterpreter() {
        this.pattern = Pattern.compile("^(\"[^\"]*\")|^([-]?(\\d+)(\\.\\d*)?)");
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(pattern);
        if (matches.find()) {
            String match = matches.group();
            query.removeFirstMatch(pattern);
            queryBuilder.withValue(match);
            return true;
        }
        return false;
    }
}

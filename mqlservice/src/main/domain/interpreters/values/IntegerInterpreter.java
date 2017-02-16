package domain.interpreters.values;

import domain.querybuilder.QueryBuilder;
import domain.StringQuery;
import domain.interpreters.Interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntegerInterpreter implements Interpreter {
    private static final Pattern INTEGER_PATTERN = Pattern.compile("^([-]?(\\d+))");

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(INTEGER_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            query.removeFirstMatch(INTEGER_PATTERN);
            queryBuilder.withInteger(Integer.parseInt(match));
            return true;
        }
        return false;
    }
}
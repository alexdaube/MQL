package domain.interpreters.values;

import domain.query.Query;
import domain.interpreters.Interpreter;
import domain.query.builder.QueryBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VarcharInterpreter implements Interpreter {
    static final Pattern VARCHAR_PATTERN = Pattern.compile("^(\"([^\"]+)\")");

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(VARCHAR_PATTERN);
        if (matches.find()) {
            String match = matches.group(2);
            query.removeFirstMatch(VARCHAR_PATTERN);
            queryBuilder.withVarchar(match);
            return true;
        }
        return false;
    }
}

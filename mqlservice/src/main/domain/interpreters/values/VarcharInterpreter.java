package domain.interpreters.values;

import domain.QueryBuilder;
import domain.StringQuery;
import domain.interpreters.Interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VarcharInterpreter implements Interpreter {
    private static final Pattern VARCHAR_PATTERN = Pattern.compile("^(\"([^\"]+)\")");
    private final Interpreter next;

    public VarcharInterpreter(Interpreter next) {
        this.next = next;
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(VARCHAR_PATTERN);
        if (matches.find()) {
            String match = matches.group(2);
            query.removeFirstMatch(VARCHAR_PATTERN);
            queryBuilder.withVarchar(match);
            return true;
        }
        return next.interpret(query, queryBuilder);
    }
}

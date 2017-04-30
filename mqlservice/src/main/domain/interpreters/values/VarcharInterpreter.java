package domain.interpreters.values;

import domain.interpreters.Interpreter;
import domain.query.Query;
import domain.query.builder.QueryBuilder;
import domain.query.builder.SuggestionBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VarcharInterpreter implements Interpreter {
    static final Pattern VARCHAR_PATTERN = Pattern.compile("^(\"([^\"]+)\")");
    private static final String VALUE = "Varchar";

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

    @Override
    public void suggest(SuggestionBuilder suggestionBuilder) {
        suggestionBuilder.withValue(VALUE);
    }
}

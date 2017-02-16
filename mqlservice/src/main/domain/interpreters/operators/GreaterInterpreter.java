package domain.interpreters.operators;

import domain.querybuilder.QueryBuilder;
import domain.StringQuery;
import domain.interpreters.Interpreter;
import domain.keyword.Keywords;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GreaterInterpreter implements Interpreter {
    private static final Pattern GREATER_PATTERN = Pattern.compile("^[\\w-]+");
    private final Keywords keywords;

    public GreaterInterpreter(Keywords keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(GREATER_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match)) {
                query.removeFirstMatch(GREATER_PATTERN);
                queryBuilder.withGreater();
                return true;
            }
        }
        return false;
    }
}

package domain.interpreters.operators;

import domain.querybuilder.QueryBuilder;
import domain.StringQuery;
import domain.interpreters.Interpreter;
import domain.keyword.Keywords;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EqualInterpreter implements Interpreter {
    private static final Pattern EQUAL_PATTERN = Pattern.compile("^[\\w-]+");
    private final Keywords keywords;

    public EqualInterpreter(Keywords keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(EQUAL_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match)) {
                query.removeFirstMatch(EQUAL_PATTERN);
                queryBuilder.withEquals();
                return true;
            }
        }
        return false;
    }
}

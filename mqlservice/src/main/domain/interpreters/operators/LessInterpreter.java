package domain.interpreters.operators;

import domain.querybuilder.QueryBuilder;
import domain.StringQuery;
import domain.interpreters.Interpreter;
import domain.keyword.Keywords;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LessInterpreter implements Interpreter {
    private static final Pattern LESS_PATTERN = Pattern.compile("^[\\w-]+");
    private final Keywords keywords;

    public LessInterpreter(Keywords keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(LESS_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match)) {
                query.removeFirstMatch(LESS_PATTERN);
                queryBuilder.withLess();
                return true;
            }
        }
        return false;
    }
}
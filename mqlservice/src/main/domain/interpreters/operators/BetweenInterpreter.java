package domain.interpreters.operators;

import domain.Query;
import domain.interpreters.Interpreter;
import domain.keywords.Keywords;
import domain.querybuilder.QueryBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BetweenInterpreter implements Interpreter {
    static final Pattern BETWEEN_PATTERN = Pattern.compile("^[\\w-]+");
    private final Keywords keywords;

    public BetweenInterpreter(Keywords keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(BETWEEN_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match.toLowerCase())) {
                query.removeFirstMatch(BETWEEN_PATTERN);
                queryBuilder.withBetween();
                return true;
            }
        }
        return false;
    }
}

package domain.interpreters.operators;

import domain.query.Query;
import domain.interpreters.Interpreter;
import domain.keywords.Keywords;
import domain.query.builder.QueryBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LessInterpreter implements Interpreter {
    static final Pattern LESS_PATTERN = Pattern.compile("^[\\w-]+|^<");
    private final Keywords keywords;

    public LessInterpreter(Keywords keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(LESS_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match.toLowerCase())) {
                query.removeFirstMatch(LESS_PATTERN);
                queryBuilder.withLess();
                return true;
            }
        }
        return false;
    }
}

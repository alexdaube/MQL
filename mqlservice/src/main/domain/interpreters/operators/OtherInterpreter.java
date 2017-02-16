package domain.interpreters.operators;

import domain.querybuilder.QueryBuilder;
import domain.StringQuery;
import domain.interpreters.Interpreter;
import domain.keyword.Keywords;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OtherInterpreter implements Interpreter {
    private static final Pattern OTHER_PATTERN = Pattern.compile("^[\\w-]+");
    private final Keywords keywords;

    public OtherInterpreter(Keywords keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(OTHER_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match.toLowerCase())) {
                query.removeFirstMatch(OTHER_PATTERN);
                return true;
            }
        }
        return false;
    }
}

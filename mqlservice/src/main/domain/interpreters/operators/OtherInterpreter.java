package domain.interpreters.operators;

import domain.query.Query;
import domain.interpreters.Interpreter;
import domain.keywords.Keywords;
import domain.query.builder.QueryBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OtherInterpreter implements Interpreter {
    static final Pattern OTHER_PATTERN = Pattern.compile("^[\\w-]+");
    private final Keywords keywords;

    public OtherInterpreter(Keywords keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(OTHER_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match)) {
                query.removeFirstMatch(OTHER_PATTERN);
                return true;
            }
        }
        return false;
    }
}

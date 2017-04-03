package domain.interpreters.operators;

import domain.interpreters.Interpreter;
import domain.keywords.Keywords;
import domain.query.Query;
import domain.query.builder.OperatorType;
import domain.query.builder.QueryBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LikeInterpreter implements Interpreter {
    static final Pattern LIKE_PATTERN = Pattern.compile("^[\\w-]+");
    private final Keywords keywords;

    public LikeInterpreter(Keywords keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(LIKE_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match)) {
                query.removeFirstMatch(LIKE_PATTERN);
                queryBuilder.withOperator(OperatorType.LIKE);
                return true;
            }
        }
        return false;
    }
}

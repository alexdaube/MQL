package domain.interpreters.operators;

import domain.query.Query;
import domain.interpreters.Interpreter;
import domain.keywords.Keywords;
import domain.query.builder.OperatorType;
import domain.query.builder.QueryBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EqualInterpreter implements Interpreter {
    static final Pattern EQUAL_PATTERN = Pattern.compile("^[\\w-]+|^=");
    private final Keywords keywords;

    public EqualInterpreter(Keywords keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(EQUAL_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match.toLowerCase())) {
                query.removeFirstMatch(EQUAL_PATTERN);
                queryBuilder.withOperator(OperatorType.EQUAL);
                return true;
            }
        }
        return false;
    }
}
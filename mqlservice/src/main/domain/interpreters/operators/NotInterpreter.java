package domain.interpreters.operators;

import domain.interpreters.Interpreter;
import domain.keywords.Keywords;
import domain.query.Query;
import domain.query.builder.OperatorType;
import domain.query.builder.QueryBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NotInterpreter implements Interpreter {
    static final Pattern NOT_PATTERN = Pattern.compile("^[\\w-]+|^!");
    private final Keywords keywords;

    public NotInterpreter(Keywords keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(NOT_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match)) {
                query.removeFirstMatch(NOT_PATTERN);
                queryBuilder.withOperator(OperatorType.NOT);
                return true;
            }
        }
        return false;
    }
}

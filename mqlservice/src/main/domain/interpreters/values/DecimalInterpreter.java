package domain.interpreters.values;

import domain.query.Query;
import domain.interpreters.Interpreter;
import domain.query.builder.QueryBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecimalInterpreter implements Interpreter {
    static final Pattern DECIMAL_PATTERN = Pattern.compile("^([-]?(\\d+)(\\.\\d*))");

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(DECIMAL_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            query.removeFirstMatch(DECIMAL_PATTERN);
            queryBuilder.withDecimal(Double.parseDouble(match));
            return true;
        }
        return false;
    }
}

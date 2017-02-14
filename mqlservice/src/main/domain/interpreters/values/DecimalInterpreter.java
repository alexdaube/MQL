package domain.interpreters.values;

import domain.QueryBuilder;
import domain.StringQuery;
import domain.interpreters.Interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecimalInterpreter implements Interpreter {
    private static final Pattern DECIMAL_PATTERN = Pattern.compile("^([-]?(\\d+)(\\.\\d*))");
    private final Interpreter next;

    public DecimalInterpreter(Interpreter next) {
        this.next = next;
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(DECIMAL_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            query.removeFirstMatch(DECIMAL_PATTERN);
            queryBuilder.withDecimal(Double.parseDouble(match));
            return true;
        }
        return next.interpret(query, queryBuilder);
    }
}

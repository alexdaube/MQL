package domain.interpreters;

import domain.QueryBuilder;
import domain.StringQuery;
import domain.keyword.Keywords;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperatorInterpreter implements Interpreter {
    private final Keywords keywords;
    private final Pattern operatorPattern;

    public OperatorInterpreter(Keywords keywords) {
        this.keywords = keywords;
        this.operatorPattern = Pattern.compile("^[\\w-]+");
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(operatorPattern);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match)) {
                query.removeFirstMatch(operatorPattern);
                queryBuilder.withOperator(match);
                return true;
            }
        }
        return false;
    }
}

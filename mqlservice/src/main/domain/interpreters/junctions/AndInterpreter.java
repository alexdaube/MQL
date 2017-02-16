package domain.interpreters.junctions;

import domain.querybuilder.QueryBuilder;
import domain.StringQuery;
import domain.interpreters.Interpreter;
import domain.keyword.Keywords;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AndInterpreter implements Interpreter {
    private static final Pattern AND_PATTERN = Pattern.compile("^[\\w]+");;
    private final Keywords keywords;

    public AndInterpreter(Keywords keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(AND_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match.toLowerCase())) {
                query.removeFirstMatch(AND_PATTERN);
                queryBuilder.and();
                return true;
            }
        }
        return false;
    }
}

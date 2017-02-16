package domain.interpreters.junctions;

import domain.querybuilder.QueryBuilder;
import domain.StringQuery;
import domain.interpreters.Interpreter;
import domain.keyword.Keywords;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrInterpreter implements Interpreter {
    private static final Pattern OR_PATTERN = Pattern.compile("^[\\w]+");
    private final Keywords keywords;

    public OrInterpreter(Keywords keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(OR_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match)) {
                query.removeFirstMatch(OR_PATTERN);
                queryBuilder.or();
                return true;
            }
        }
        return false;
    }
}

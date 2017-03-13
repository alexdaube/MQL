package domain.interpreters.junctions;

import domain.interpreters.Interpreter;
import domain.keywords.Keywords;
import domain.query.Query;
import domain.query.builder.QueryBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrInterpreter implements Interpreter {
    static final Pattern OR_PATTERN = Pattern.compile("^[\\w]+");
    private final Keywords keywords;

    public OrInterpreter(Keywords keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
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

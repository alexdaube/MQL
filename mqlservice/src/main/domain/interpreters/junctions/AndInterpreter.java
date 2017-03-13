package domain.interpreters.junctions;

import domain.query.Query;
import domain.interpreters.Interpreter;
import domain.keywords.Keywords;
import domain.query.builder.QueryBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AndInterpreter implements Interpreter {
    static final Pattern AND_PATTERN = Pattern.compile("^[\\w]+");
    ;
    private final Keywords keywords;

    public AndInterpreter(Keywords keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(AND_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match)) {
                query.removeFirstMatch(AND_PATTERN);
                queryBuilder.and();
                return true;
            }
        }
        return false;
    }
}

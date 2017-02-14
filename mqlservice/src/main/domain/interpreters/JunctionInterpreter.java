package domain.interpreters;

import domain.QueryBuilder;
import domain.StringQuery;
import domain.keyword.Keywords;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JunctionInterpreter implements Interpreter {
    private final Keywords keywords;
    private final Pattern junctionPattern;

    public JunctionInterpreter(Keywords keywords) {
        this.keywords = keywords;
        this.junctionPattern = Pattern.compile("^[\\w]+");
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(junctionPattern);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match)) {
                query.removeFirstMatch(junctionPattern);
                queryBuilder.withJunction(match);
                return true;
            }
        }
        return false;
    }
}

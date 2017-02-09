package domain.interpreters;

import domain.QueryBuilder;
import domain.StringQuery;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JunctionInterpreter implements Interpreter {
    private final Set<String> junctions;
    private final Pattern junctionPattern;

    public JunctionInterpreter(Set<String> junctions) {
        this.junctions = junctions;
        this.junctionPattern = Pattern.compile("^[\\w]+");
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(junctionPattern);
        if (matches.find()) {
            String match = matches.group();
            if (junctions.contains(match)) {
                query.removeFirstMatch(junctionPattern);
                queryBuilder.withJunction(match);
                return true;
            }
        }
        return false;
    }
}

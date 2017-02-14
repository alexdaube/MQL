package domain.interpreters;

import domain.QueryBuilder;
import domain.StringQuery;
import domain.keyword.Keywords;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityInterpreter implements Interpreter {
    private final Keywords keywords;
    private final Pattern entityPattern;

    public EntityInterpreter(Keywords keywords) {
        this.keywords = keywords;
        this.entityPattern = Pattern.compile("^[\\w-]+");
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(entityPattern);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match)) {
                query.removeFirstMatch(entityPattern);
                queryBuilder.withEntity(match);
                return true;
            }
        }
        return false;
    }
}

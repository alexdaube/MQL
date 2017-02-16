package domain.interpreters;

import domain.querybuilder.QueryBuilder;
import domain.Query;
import domain.keywords.Keywords;

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
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
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

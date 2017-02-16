package domain.interpreters;

import domain.querybuilder.QueryBuilder;
import domain.Query;
import domain.keywords.Keywords;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityInterpreter implements Interpreter {
    public static final Pattern ENTITY_PATTERN = Pattern.compile("^[\\w-]+");
    private final Keywords keywords;

    public EntityInterpreter(Keywords keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(ENTITY_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match)) {
                query.removeFirstMatch(ENTITY_PATTERN);
                queryBuilder.withEntity(keywords.parentKeyword());
                return true;
            }
        }
        return false;
    }
}

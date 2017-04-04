package domain.interpreters;

import domain.keywords.Keywords;
import domain.query.Query;
import domain.query.builder.QueryBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityInterpreter extends BaseInterpreter implements Interpreter {
    public static final Pattern ENTITY_PATTERN = Pattern.compile("^[\\w-]+");

    public EntityInterpreter(Keywords keywords) {
        super(keywords);
    }

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(ENTITY_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match)) {
                query.removeFirstMatch(ENTITY_PATTERN);
                queryBuilder.withEntity(keywords.parentOf(match));
                return true;
            }
        }
        return false;
    }
}

package domain.interpreters;

import domain.QueryBuilder;
import domain.StringQuery;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttributeInterpreter implements Interpreter {
    private final Set<String> attributes;
    private final Pattern attributePattern;

    public AttributeInterpreter(Set<String> attributes) {
        this.attributes = attributes;
        attributePattern = Pattern.compile("^[\\w-]+");
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(attributePattern);
        if (matches.find()) {
            String match = matches.group();
            if (attributes.contains(match)) {
                query.removeFirstMatch(attributePattern);
                queryBuilder.withAttribute(match);
                return true;
            }
        }
        return false;
    }
}

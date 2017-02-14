package domain.interpreters;

import domain.QueryBuilder;
import domain.StringQuery;
import domain.keyword.Keywords;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttributeInterpreter implements Interpreter {
    private final Keywords keywords;
    private final Pattern attributePattern;

    public AttributeInterpreter(Keywords keywords) {
        this.keywords = keywords;
        this.attributePattern = Pattern.compile("^[\\w-]+");
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(attributePattern);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match)) {
                query.removeFirstMatch(attributePattern);
                queryBuilder.withAttribute(match);
                return true;
            }
        }
        return false;
    }
}

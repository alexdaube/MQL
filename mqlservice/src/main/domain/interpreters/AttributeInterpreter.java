package domain.interpreters;

import domain.keywords.Keywords;
import domain.query.Query;
import domain.query.builder.QueryBuilder;
import domain.query.builder.SuggestionBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttributeInterpreter implements Interpreter {
    static final Pattern ATTRIBUTE_PATTERN = Pattern.compile("^[\\w-]+");
    private final Keywords keywords;

    public AttributeInterpreter(Keywords keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(ATTRIBUTE_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match)) {
                query.removeFirstMatch(ATTRIBUTE_PATTERN);
                queryBuilder.withAttribute(keywords.parentOf(match));
                return true;
            }
        }
        return false;
    }

    @Override
    public void suggest(SuggestionBuilder suggestionBuilder) {
        suggestionBuilder.withQueryMatching(keywords).withAllowed(keywords);
    }
}

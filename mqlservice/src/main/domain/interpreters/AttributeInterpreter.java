package domain.interpreters;

import com.google.gson.JsonArray;
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

    public JsonArray suggest(Query query) {
        SuggestionBuilder builder = new SuggestionBuilder();
        return builder
                .withHint("Query needs an attribute!").withQuery("Choices", query, keywords )
                .withAll("Possible Attributes", keywords).getSuggestions();
    }
}

package domain.query.builder;

import com.google.gson.JsonArray;
import domain.keywords.Keywords;

public interface SuggestionBuilder {
    SuggestionBuilder withValue(String type);

    SuggestionBuilder withQueryMatching(Keywords keywords);

    SuggestionBuilder withAllowed(Keywords keywords);

    JsonArray buildSuggestion();
}

package domain.query.builder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import domain.keywords.Keyword;
import domain.keywords.Keywords;
import domain.query.Query;

import java.util.stream.StreamSupport;

public class MQLSuggestionBuilder implements SuggestionBuilder {
    private Query<String> query;
    private JsonArray suggestions;
    private JsonArray queryMatchingSuggestions;
    private JsonArray allowedSuggestions;
    private JsonArray valueSuggestions;

    public MQLSuggestionBuilder(Query<String> query) {
        this.query = query;
        this.suggestions = new JsonArray();
        this.queryMatchingSuggestions = new JsonArray();
        this.allowedSuggestions = new JsonArray();
        this.valueSuggestions = new JsonArray();
    }

    public MQLSuggestionBuilder withValue(String type) {
        valueSuggestions.add(createSuggestionFromString(type));
        return this;
    }

    public MQLSuggestionBuilder withQueryMatching(Keywords keywords) {
        if (!query.getQuery().isEmpty()) {
            StreamSupport.stream(keywords.spliterator(), false)
                    .filter(keyword -> keyword.name().toLowerCase()
                            .startsWith(query.getQuery().toLowerCase()))
                    .forEach(keyword -> queryMatchingSuggestions
                            .add(createSuggestion(keyword)));
        }
        return this;
    }

    public MQLSuggestionBuilder withAllowed(Keywords keywords) {
        keywords.forEach((keyword) -> {
            allowedSuggestions.add(createSuggestion(keyword));
        });
        return this;
    }

    public JsonArray buildSuggestion() {
        createSuggestionsSection(String.format("Possible 'KEYWORDS' matching '%s'",
                query.getQuery()), queryMatchingSuggestions);
        createSuggestionsSection("Possible 'KEYWORDS'", allowedSuggestions);
        createSuggestionsSection("Possible 'VALUES' to enter", valueSuggestions);
        return this.suggestions;
    }

    private JsonObject createSuggestionFromString(String type) {
        JsonObject suggestion = new JsonObject();
        suggestion.addProperty("name", type);
        suggestion.addProperty("type", type.toUpperCase());
        return suggestion;
    }

    private void createSuggestionsSection(String title, JsonArray sectionSuggestions) {
        if (sectionSuggestions.size() != 0) {
            JsonObject section = new JsonObject();
            section.addProperty("title", title);
            section.add("suggestions", sectionSuggestions);
            suggestions.add(section);
        }
    }

    private JsonObject createSuggestion(Keyword keyword) {
        JsonObject suggestion = new JsonObject();
        suggestion.addProperty("name", keyword.name());
        suggestion.addProperty("type", keyword.type.name());
        suggestion.add("synonyms", addSynonyms(keyword));
        return suggestion;
    }

    private JsonArray addSynonyms(Keyword keyword) {
        JsonArray synonyms = new JsonArray();
        keyword.getSynonyms().forEach( synonym -> {
            synonyms.add(new JsonPrimitive(synonym));
        });
        return synonyms;
    }
}


package domain.query.builder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import domain.keywords.Keywords;
import domain.query.Query;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SuggestionBuilder {
    private JsonArray suggestions;

    public SuggestionBuilder() {
        this.suggestions = new JsonArray();
    }

    public JsonArray getSuggestions() {
        return this.suggestions;
    }

    public SuggestionBuilder withHint(String hint) {
        JsonObject section = new JsonObject();
        section.addProperty("title", "Hint");
        section.addProperty("hint", hint);
        suggestions.add(section);
        return this;
    }

    public SuggestionBuilder withQuery(String title, Query<String> query, Keywords keywords) {
        JsonObject section = new JsonObject();
        section.addProperty("title", title);
        JsonArray sectionSuggestions = new JsonArray();
        Stream tits = StreamSupport.stream(keywords.spliterator(), false)
                .filter(keyword -> keyword.name().toLowerCase().startsWith(query.getQuery().toLowerCase()));

        return this;
    }

    public SuggestionBuilder withAll(String title, Keywords keywords) {
        JsonObject section = new JsonObject();
        section.addProperty("title", title);
        JsonArray sectionSuggestions = new JsonArray();
        keywords.forEach((keyword) -> {
            JsonObject suggestion = new JsonObject();
            suggestion.addProperty("name", keyword.name());
            suggestion.addProperty("type", keyword.type.name());
            sectionSuggestions.add(suggestion);
        });
        section.add("suggestions", sectionSuggestions);
        suggestions.add(section);
        return this;
    }
}

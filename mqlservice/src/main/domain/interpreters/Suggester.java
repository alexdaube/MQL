package domain.interpreters;

import domain.query.builder.SuggestionBuilder;

public interface Suggester {
    void suggest(SuggestionBuilder suggestionBuilder);
}

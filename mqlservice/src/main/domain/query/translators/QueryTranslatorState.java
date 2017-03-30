package domain.query.translators;

import domain.query.Query;
import domain.query.builder.SuggestionBuilder;

public interface QueryTranslatorState {
    StateStatus translate(Query query);
    void translateNextSuggestion(SuggestionBuilder suggestionBuilder);
}

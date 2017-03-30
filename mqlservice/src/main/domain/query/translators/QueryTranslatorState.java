package domain.query.translators;

import com.google.gson.JsonArray;
import domain.query.Query;

public interface QueryTranslatorState {
    StateStatus translate(Query query);
    JsonArray translateNextSuggestion(Query query);
}

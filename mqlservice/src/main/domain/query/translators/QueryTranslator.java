package domain.query.translators;

import com.google.gson.JsonArray;
import domain.query.Query;

public interface QueryTranslator {
    String translate(Query query);
    JsonArray translateNextSuggestion(Query query);
}

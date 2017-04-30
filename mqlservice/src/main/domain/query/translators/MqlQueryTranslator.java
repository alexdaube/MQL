package domain.query.translators;

import com.google.gson.JsonArray;
import domain.InvalidQueryException;
import domain.query.Query;
import domain.query.builder.MQLSuggestionBuilder;
import domain.query.builder.QueryBuilder;

public class MqlQueryTranslator implements QueryTranslator {
    private final QueryBuilder queryBuilder;
    private QueryTranslatorState state;

    public MqlQueryTranslator(QueryTranslatorState initialState, QueryBuilder queryBuilder) {
        this.state = initialState;
        this.queryBuilder = queryBuilder;
    }

    public String translate(Query query) {
        translateQuery(query);
        return queryBuilder.build();
    }

    public JsonArray translateNextSuggestion(Query query) {
        try {
            translateQuery(query);
        } catch (InvalidQueryException ex) {
            MQLSuggestionBuilder suggestionBuilder = new MQLSuggestionBuilder(query);
            state.translateNextSuggestion(suggestionBuilder);
            return suggestionBuilder.buildSuggestion();
        }
        return new JsonArray();
    }

    private void translateQuery(Query query) {
        boolean isTranslated = false;
        while (!isTranslated) {
            StateStatus stateStatus = state.translate(query);
            isTranslated = stateStatus.isDone();
            state = stateStatus.nextState();
        }
    }
}

package domain.query.translators;

import com.google.gson.JsonArray;
import domain.InvalidQueryException;
import domain.keywords.KeywordsResolver;
import domain.query.Query;
import domain.query.builder.QueryBuilder;

public class MqlQueryTranslator implements QueryTranslator {
    private final QueryBuilder queryBuilder;
    private QueryTranslatorState state;

    public MqlQueryTranslator(QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
        this.state = new InitialTranslatorState(queryBuilder, keywordsResolver);
        this.queryBuilder = queryBuilder;
    }

    public String translate(Query query) {
        translateQuery(query);
        return queryBuilder.build();
    }

    public JsonArray translateNextSuggestion(Query query) {
        try {
            translateQuery(query);
        } catch(InvalidQueryException ex) {
            if (state instanceof InitialTranslatorState) {
                return state.translateNextSuggestion(query);
            } else if (state instanceof EntityTranslatorState) {
                return state.translateNextSuggestion(query);
                // Add all valid attributes
            } else if (state instanceof AttributeTranslatorState) {
                return state.translateNextSuggestion(query);
            } else if (state instanceof JunctionTranslatorState) {
                return state.translateNextSuggestion(query);
            } else if (state instanceof OperatorTranslatorState) {
                return state.translateNextSuggestion(query);
            } else if (state instanceof ValueTranslatorState) {
                return state.translateNextSuggestion(query);
            } else {
                return new JsonArray();
            }
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

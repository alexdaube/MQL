package domain.translators;

import domain.querybuilder.QueryBuilder;
import domain.Query;
import domain.keywords.KeywordsResolver;

public class QueryTranslator {
    private final QueryBuilder queryBuilder;
    private QueryTranslatorState state;

    public QueryTranslator(QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
        this.state = new InitialTranslatorState(queryBuilder, keywordsResolver);
        this.queryBuilder = queryBuilder;
    }

    public String translate(Query query) {
        boolean isTranslated = false;
        while (!isTranslated) {
            StateStatus stateStatus = state.translate(query);
            isTranslated = stateStatus.isDone();
            state = stateStatus.nextState();
        }
        return queryBuilder.build();
    }
}

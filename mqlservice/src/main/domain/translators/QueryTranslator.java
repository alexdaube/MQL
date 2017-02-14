package domain.translators;

import domain.Query;
import domain.QueryBuilder;
import domain.StringQuery;
import domain.keyword.KeywordsResolver;

public class QueryTranslator {
    private final QueryBuilder queryBuilder;
    private QueryTranslatorState state;

    public QueryTranslator(QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
        this.state = new InitialTranslatorState(queryBuilder, keywordsResolver);
        this.queryBuilder = queryBuilder;
    }

    public Query translate(StringQuery stringQuery) {
        boolean isTranslated = false;
        while (!isTranslated) {
            StateStatus stateStatus = state.translate(stringQuery);
            isTranslated = stateStatus.isDone();
            state = stateStatus.nextState();
        }
        return queryBuilder.build();
    }
}

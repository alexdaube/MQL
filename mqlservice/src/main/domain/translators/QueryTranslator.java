package domain.translators;

import domain.Query;
import domain.QueryBuilder;
import domain.StringQuery;

public class QueryTranslator {
    private final QueryBuilder queryBuilder;
    private QueryTranslatorState state;

    public QueryTranslator(QueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
        this.state = new InitialTranslatorState(this);
    }

    public Query translate(StringQuery stringQuery) {
        boolean isTranslated = false;
        while (!isTranslated) {
            isTranslated = state.translate(stringQuery);
        }
        return queryBuilder.build();
    }

    public void changeState(QueryTranslatorState state) {
        this.state = state;
    }

    public QueryBuilder getQueryBuilder() {
        return queryBuilder;
    }
}

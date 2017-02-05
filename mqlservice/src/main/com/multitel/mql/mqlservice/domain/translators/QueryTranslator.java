package com.multitel.mql.mqlservice.domain.translators;

import com.multitel.mql.mqlservice.domain.Query;
import com.multitel.mql.mqlservice.domain.QueryBuilder;
import com.multitel.mql.mqlservice.domain.StringQuery;

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

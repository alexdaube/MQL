package domain.translators;

import domain.QueryBuilder;
import domain.StringQuery;
import domain.keyword.KeywordsResolver;

public class JunctionTranslatorState implements QueryTranslatorState {
    private final KeywordsResolver keywordsResolver;
    private final QueryBuilder queryBuilder;

    public JunctionTranslatorState(QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
        this.keywordsResolver = keywordsResolver;
        this.queryBuilder = queryBuilder;
    }

    @Override
    public StateStatus translate(StringQuery stringQuery) {
        // TODO: 13/02/17 Check if entity of attribute
        return new StateStatus(false, new InitialTranslatorState(queryBuilder, keywordsResolver));
    }
}

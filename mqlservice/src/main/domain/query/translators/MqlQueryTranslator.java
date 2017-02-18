package domain.query.translators;

import domain.query.Query;
import domain.keywords.KeywordsResolver;
import domain.query.builder.QueryBuilder;

public class MqlQueryTranslator implements QueryTranslator {
    private final QueryBuilder queryBuilder;
    private QueryTranslatorState state;

    public MqlQueryTranslator(QueryBuilder queryBuilder, KeywordsResolver keywordsResolver) {
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

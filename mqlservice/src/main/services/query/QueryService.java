package services.query;

import domain.query.StringQuery;
import domain.query.translators.QueryTranslator;
import domain.DbClient;

public class QueryService {
    private final DbClient dbClient;
    private final QueryTranslator queryTranslator;

    public QueryService(DbClient dbClient, QueryTranslator queryTranslator) {
        this.dbClient = dbClient;
        this.queryTranslator = queryTranslator;
    }

    public void executeQuery(QueryDto queryDto) {
        String query = queryTranslator.translate(new StringQuery(queryDto.query));
        dbClient.execute(query);
        System.out.println(query);
    }
}

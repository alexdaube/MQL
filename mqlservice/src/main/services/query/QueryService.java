package services.query;

import domain.keywords.KeywordsResolver;
import domain.query.StringQuery;
import domain.query.builder.QueryBuilder;
import domain.query.translators.MqlQueryTranslator;
import domain.query.translators.QueryTranslator;
import domain.DbClient;
import services.locator.ServiceLocator;

import java.util.List;
import java.util.Map;

public class QueryService {
    private final DbClient dbClient;

    public QueryService() {
        this(ServiceLocator.getInstance().resolve(DbClient.class));
    }

    public QueryService(DbClient dbClient) {
        this.dbClient = dbClient;
    }

    public List<Map<String, String>> executeQuery(QueryDto queryDto) {
        QueryTranslator queryTranslator = new MqlQueryTranslator(ServiceLocator.getInstance().resolve(QueryBuilder.class),
                ServiceLocator.getInstance().resolve(KeywordsResolver.class));
        String query = queryTranslator.translate(new StringQuery(queryDto.query));
        System.out.println(query);
        return dbClient.execute(query);
    }
}

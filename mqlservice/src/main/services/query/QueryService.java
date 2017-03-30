package services.query;

import com.google.gson.JsonArray;
import domain.DbClient;
import domain.InvalidQueryException;
import domain.keywords.KeywordsResolver;
import domain.query.StringQuery;
import domain.query.builder.QueryBuilder;
import domain.query.translators.MqlQueryTranslator;
import domain.query.translators.QueryTranslator;
import services.locator.InvalidMethodException;
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

    public List<List<AttributeDto>> executeQuery(QueryDto queryDto) {
        QueryTranslator queryTranslator = ServiceLocator.getInstance().resolve(QueryTranslator.class);
        String query = queryTranslator.translate(new StringQuery(queryDto.query));
        return dbClient.execute(query);
    }

    public JsonArray getNextSuggestion(QueryDto queryDto) {
        QueryTranslator queryTranslator = ServiceLocator.getInstance().resolve(QueryTranslator.class);
        return queryTranslator.translateNextSuggestion(new StringQuery(queryDto.query));
    }
}
